header
{
    package  ABTOOLS.GRAMMAR;
}
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;

}class GeneralisedSubstitutionLanguageParser extends Parser;

options {
	importVocab=   Expression;
	exportVocab=   GeneralisedSubstitutionLanguageLexer;
	k=	1 ;
	codeGenMakeSwitchThreshold= 	2;
	codeGenBitsetTestThreshold= 	3;
	buildAST= 	true;
	ASTLabelType= 	"MyNode";
}

{
	String module = "GeneralisedSubstitutionLanguage.g";

// Error Management
	ErrorMessage errors = new ErrorMessage();

	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}

// Debug enable or disabled
	protected DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}
}
analyse_GSL :basic_substitution
;

basic_substitution :(expression GSL_SUCH ) => expression GSL_SUCH  basic_substitution
    |   (expression GSL_GUARD) => expression GSL_GUARD basic_substitution
    |   GSL_FOR_SUCH list_var B_POINT expression
 //   |   basic_substitution GSL_BOUNDED basic_substitution
;

protected list_var :(B_LPAREN) =>	B_LPAREN^   list_identifier B_RPAREN!
    |                               list_identifier 
;

protected list_identifier :B_IDENTIFIER  
        (
            B_COMMA^ 
            B_IDENTIFIER 
        )*
;

// inherited from grammar ExpressionParser
analyse_expression :expression
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty expression !" );
            errors.WSyntaxic   ( module, "Expression is empty" );
        }
    ;

// inherited from grammar ExpressionParser
analyse_predicate :predicate
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty predicate !" );
            errors.WSyntaxic   ( module, "Predicat is empty" );
        }
    ;

// inherited from grammar ExpressionParser
expression 
options {
	defaultErrorHandler= true;
}
:logical_1 	
        (	B_IMPLIES^
        	logical_1
        )*
;

// inherited from grammar ExpressionParser
logical_1 :logical_2
  	 (
  	 	(	"or"^ 
  	 	| 	B_AND^
  	 	)
  	 	 logical_2
  	 )*
;

// inherited from grammar ExpressionParser
logical_2 :subset_description
	( 
                ( 	B_EQUIV^ 
		|	B_EQUAL^  
		)   
		subset_description
	)*
;

// inherited from grammar ExpressionParser
subset_description :// May 2007
// BJL : probleme entre x,y : X et x,y
		(extended_pair
			(
				( 	B_SUBSET
				|	B_NOTSUBSET
				|	B_STRICTSUBSET
				|	B_NOTSTRICTSBSET
				|	B_INSET			// Il faut pouvoir prendre en compte f(xx) : T
				)
				arithmetic_3
			)
		) =>
		( extended_pair
			(
				( 	B_SUBSET^
				|	B_NOTSUBSET^
				|	B_STRICTSUBSET^
				|	B_NOTSTRICTSBSET^
				|	B_INSET^	// Il faut pouvoir prendre en compte f(xx) : T
				)
				arithmetic_3
			)
		)
	|	arithmetic_3
;

// inherited from grammar ExpressionParser
extended_pair :arithmetic_3
		( 
			a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}	
			arithmetic_3
		)*
;

// inherited from grammar ExpressionParser
arithmetic_3 :sequence_description
			( 
				(	B_NOTINSET^
				|	B_LESS^
				|	B_GREATER^
				|	B_NOTEQUAL^
				|	B_LESSTHANEQUAL^
				|	B_GREATERTHANEQUAL^
				)
				sequence_description
			)*
;

// inherited from grammar ExpressionParser
sequence_description :set_description 
			(
				(	B_CONCATSEQ^
				|	B_PREAPPSEQ^
				|	B_APPSEQ^
				|	B_PREFIXSEQ^
				|	B_SUFFIXSEQ^
				)
			 	set_description
			)*
;

// inherited from grammar ExpressionParser
set_description :"bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
	|	functional_set
;

// inherited from grammar ExpressionParser
functional_set :functional_const_set 
			( 
				(
					B_RELATION^
				|	B_PARTIAL^
				|	B_TOTAL^
				|	B_PARTIAL_INJECT^
				|	B_TOTAL_INJECT^
				|	B_PARTIAL_SURJECT^
				|	B_TOTAL_SURJECT^
				|	B_BIJECTION^
				)
				functional_const_set
			)*
;

// inherited from grammar ExpressionParser
functional_const_set :basic_constructors 
    (	
        (	B_DOMAINRESTRICT^
        |	B_RANGERESTRICT^
        |	B_DOMAINSUBSTRACT^
        |	B_RANGESUBSTRACT^
        |	B_OVERRIDEFORWARD^
        |	B_OVERRIDEBACKWARD^
        |	B_RELPROD^
        )
        basic_constructors
    )*
;

// inherited from grammar ExpressionParser
basic_constructors :new_couple 
    (
        (	B_UNION^
        |	B_INTER^
        )
        new_couple
    )*
;

// inherited from grammar ExpressionParser
new_couple :arithmetic_0
    (
        B_MAPLET^
        arithmetic_0
    )*
;

// inherited from grammar ExpressionParser
arithmetic_0 :(basic_sets B_MULT) => 
(    basic_sets
    ( 
 	a:B_MULT^
{#a.setType(PRODSET);}
        basic_sets
    )*
)
|	
    arithmetic_1 	
    ( 
        (
            B_POWER^ 
        |   B_MULT^
        )
        arithmetic_1
    )*	
;

// inherited from grammar ExpressionParser
arithmetic_1 :arithmetic_2 	
    ( 
        (
            B_DIV^
        |   "mod"^
        )
        arithmetic_2
    )*
;

// inherited from grammar ExpressionParser
arithmetic_2 :bases
    ( 
        (
            B_ADD^   
        |   B_MINUS^
        )
        bases
    )*
;

// inherited from grammar ExpressionParser
bases :basic_sets
			|	basic_value 				(B_RANGE^ 	arithmetic_0)?
			|	B_SEQEMPTY
			|	B_BRACKOPEN^ 				listPredicate 	B_BRACKCLOSE!
			|	c:B_LESS^ {#c.setType(B_BRACKOPEN);}	listPredicate 	B_GREATER!
			|	B_EMPTYSET
			|	B_CURLYOPEN^ 				value_set 	B_CURLYCLOSE!
			|	quantification
			|	q_lambda
		;

// inherited from grammar ExpressionParser
value_set :(alist_var B_SUCH) 	=> 	alist_var 	(B_SUCH^  expression)
// May 2007 - BJL 
    |	            		 	predicate 	(B_COMMA^ predicate)*
//	|				listPredicate
	;

// inherited from grammar ExpressionParser
basic_value :a:B_ADD^   {#a.setType(UNARY_ADD);} 	unary_basic_value_inverted
			|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	unary_basic_value_inverted
			|						unary_basic_value_inverted
			|	B_ASTRING
			|	is_record
			|	"TRUE"^ 
			|	"FALSE"^
;

// inherited from grammar ExpressionParser
unary_basic_value_inverted :unary_basic_value (B_TILDE^)?
;

// inherited from grammar ExpressionParser
unary_basic_value :expInvertedParamInvertedQuoted 
    (	c:B_BRACKOPEN^
{
	#c.setType(APPLY_TO);
} 
        predicate 
        B_BRACKCLOSE!
    )?
|	B_NUMBER
;

// inherited from grammar ExpressionParser
expInvertedParamInvertedQuoted :expInvertedParamInverted 
    (
        B_QUOTEIDENT^ expInvertedParamInverted
    )*
    // Attention on en fait peut etre beaucoup
    //			( ....)' (.....)
;

// inherited from grammar ExpressionParser
expInvertedParamInverted :expInvertedParam (B_TILDE^)?
;

// inherited from grammar ExpressionParser
expInvertedParam :expParentInverted 
    (
        B_LPAREN^ 
        list_New_Predicate 
        B_RPAREN!
    )*
;

// inherited from grammar ExpressionParser
expParentInverted :(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	expression_parent (B_TILDE^)?
;

// inherited from grammar ExpressionParser
expression_parent :c:B_LPAREN^ 
{
	#c.setType(PARENT);
}
        expression_func_composition
        B_RPAREN!
    |	nameRenamedDecorated 
;

// inherited from grammar ExpressionParser
expression_func_composition :expression 
        ( 
            (
                B_SEMICOLON^ 
            | 	B_PARALLEL^
            |	B_COMMA^
            ) 
            expression
        )* 
;

// inherited from grammar ExpressionParser
basic_sets :"INT"
    |	"INT1"
    |	"INTEGER"
    |	"INTEGER1"
    |	"BOOL"
    |	"NAT"
    |	"NAT1"
    |	"NATURAL"
    |	"NATURAL1"
    |	"STRING"
    ;

// inherited from grammar ExpressionParser
dummy :|	APPLY_TO
    |	B_SELECTOR	// le quote permettant de choisir un element d'une strucure
    |	LIST_VAR
    |   ELEM_SET	// Ensemble des elements d'un ensemble
    |	PARENT
    |	PRODSET
    
// Basic Functor        
    |	B_NOT
    |	B_DOM
    |	B_RAN
    |   B_MIN
    |   B_MAX
    |   B_CARD
    |   B_MOD
;

// inherited from grammar ExpressionParser
listPredicate :predicate 
        ( 
{
System.out.println("ListPredicate >>");
}
            b:B_COMMA^  {#b.setType(ELEM_SET);} 
            predicate
{
System.out.println("ListPredicate <<");
}
        )*
;

// inherited from grammar ExpressionParser
nameRenamed :B_IDENTIFIER 
	(
		B_POINT^ 
		B_IDENTIFIER
	)*
;

// inherited from grammar ExpressionParser
nameRenamedDecorated :nameRenamed 
	(
		B_CPRED^
	)?
;

// inherited from grammar ExpressionParser
predicate :plogical_1 		
	( 	B_IMPLIES^
		plogical_1
	)*
;

// inherited from grammar ExpressionParser
protected plogical_1 :plogical_2 
	( 
		(	"or"^  
		| 	B_AND^ 
		)
		plogical_2
	)*
;

// inherited from grammar ExpressionParser
protected plogical_2 :psubset_description
	( 
		( 	B_EQUIV^ 
		|	B_EQUAL^  
		) 
		psubset_description
	)*
;

// inherited from grammar ExpressionParser
protected psubset_description :// August 2010
// BJL : probleme entre x,y : X et x,y
	(pextended_pair
		(
			(
			 	B_SUBSET
			|	B_NOTSUBSET
			|	B_STRICTSUBSET
			|	B_NOTSTRICTSBSET
			
			)
			parithmetic_3
		)
	) =>
	pextended_pair
	(
		( 
			B_SUBSET^
		|	B_NOTSUBSET^
		|	B_STRICTSUBSET^
		|	B_NOTSTRICTSBSET^
		)
		parithmetic_3
	)*
	|	parithmetic_3
;

// inherited from grammar ExpressionParser
protected pextended_pair :parithmetic_3	
	( 
		a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}
		parithmetic_3 
	)*
;

// inherited from grammar ExpressionParser
protected parithmetic_3 :psequence_description
	(
		(
			B_LESS^
		|	B_GREATER^
		|	B_NOTEQUAL^
		|	B_LESSTHANEQUAL^
		|	B_GREATERTHANEQUAL^
		) 
		psequence_description
	)*
;

// inherited from grammar ExpressionParser
protected psequence_description :pset_description 
	(
		(	B_CONCATSEQ^
		|	B_PREAPPSEQ^
		|	B_APPSEQ^
		|	B_PREFIXSEQ^
		|	B_SUFFIXSEQ^
		) 
		pset_description
	)*
;

// inherited from grammar ExpressionParser
protected pset_description :"bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
|	pfunctional_set
		;

// inherited from grammar ExpressionParser
protected pfunctional_set :pfunctional_const_set 
	(
		(	B_RELATION^
		|	B_PARTIAL^
		|	B_TOTAL^
		|	B_PARTIAL_INJECT^
		|	B_TOTAL_INJECT^
		|	B_PARTIAL_SURJECT^
		|	B_TOTAL_SURJECT^
		|	B_BIJECTION^
		)
		pfunctional_const_set
	)*
;

// inherited from grammar ExpressionParser
protected pfunctional_const_set :pset_constructors     
	(
		(	B_DOMAINRESTRICT^
		|	B_RANGERESTRICT^
		|	B_DOMAINSUBSTRACT^
		|	B_RANGESUBSTRACT^
		|	B_OVERRIDEFORWARD^
		|	B_OVERRIDEBACKWARD^
		|	B_RELPROD^
	) 
	pset_constructors
)*
;

// inherited from grammar ExpressionParser
protected pset_constructors :ppaire 
	(
		(	B_UNION^
		|	B_INTER^
		)
		ppaire
	)*
;

// inherited from grammar ExpressionParser
protected ppaire :parithmetic_0
	( 
		B_MAPLET^ 
		parithmetic_0
	)*
;

// inherited from grammar ExpressionParser
protected parithmetic_0 :(basic_sets B_MULT) => 
(    basic_sets
    ( 
 	a:B_MULT^
{#a.setType(PRODSET);}
        basic_sets
    )*
)
|
	parithmetic_1 	
	( 
		( 	B_POWER^ 
		|	B_MULT^
		)
		parithmetic_1
	)*	
;

// inherited from grammar ExpressionParser
protected parithmetic_1 :parithmetic_2 
	( 
		(	B_DIV^ 
		| 	"mod"^
		)
		parithmetic_2
	)*
;

// inherited from grammar ExpressionParser
protected parithmetic_2 :pbases 	
	( 
		(	B_ADD^ 
		| 	B_MINUS^
		)
		pbases
	)*
;

// inherited from grammar ExpressionParser
protected pbases :B_SEQEMPTY
|	B_BRACKOPEN^ 					listPredicate B_BRACKCLOSE!
|	c:B_LESS^       {#c.setType(B_BRACKOPEN);}	listPredicate B_GREATER!
|	basic_sets
|	pbasic_value (B_RANGE^ parithmetic_0)?		// Il y avait predicate
|	B_EMPTYSET
|	B_CURLYOPEN^ pvalue_set B_CURLYCLOSE!
|	quantification
|	q_lambda
;

// inherited from grammar ExpressionParser
protected pvalue_set :(alist_var B_SUCH) 	=> alist_var 	 B_SUCH^  expression
|				   predicate 	(B_COMMA^ predicate)*
;

// inherited from grammar ExpressionParser
protected pbasic_value :a:B_ADD^   {#a.setType(UNARY_ADD);} 	punary_basic_value_inverted
|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	punary_basic_value_inverted
|						punary_basic_value_inverted
|	B_ASTRING
|	is_rec
|	"TRUE"^ 
|	"FALSE"^
;

// inherited from grammar ExpressionParser
is_rec :"rec"^
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;

// inherited from grammar ExpressionParser
protected punary_basic_value_inverted :punary_basic_value 
	(	B_TILDE^
	)?
;

// inherited from grammar ExpressionParser
protected punary_basic_value :predInvertedParamInvertedQuoted 
	(	c:B_BRACKOPEN^ 
{
	#c.setType(APPLY_TO);
}  
		predicate 
		B_BRACKCLOSE!
	)?
|	B_NUMBER
;

// inherited from grammar ExpressionParser
protected predInvertedParamInvertedQuoted :predInvertedParamInverted 
	(
		B_QUOTEIDENT^ 
		predInvertedParamInverted
	)*
;

// inherited from grammar ExpressionParser
protected predInvertedParamInverted :predInvertedParam (B_TILDE^)?
;

// inherited from grammar ExpressionParser
protected predInvertedParam :predParentInverted 
	(	B_LPAREN^ 
		list_New_Predicate 
		B_RPAREN!
	)*
;

// inherited from grammar ExpressionParser
protected predParentInverted :(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	pred_parent (B_TILDE^)?
;

// inherited from grammar ExpressionParser
pred_parent :c:B_LPAREN^ 
{#c.setType(PARENT);}
	pred_func_composition
	B_RPAREN!
|	nameRenamedDecorated 
;

// inherited from grammar ExpressionParser
pred_func_composition :predicate 
	( 
		(	B_SEMICOLON^ 
		| 	B_PARALLEL^
// Il y avait ca juste pour les predicats si pb il faudra verifier pour expression
		|	B_COMMA^
		) 
		predicate
	)* 
;

// inherited from grammar ExpressionParser
quantification :(	B_FORALL^ 
	| 	B_EXISTS^
	)
		(
			(B_LPAREN B_LPAREN B_IDENTIFIER B_COMMA) =>
				(B_LPAREN! q_quantification B_RPAREN!)
		|	(B_LPAREN B_IDENTIFIER B_COMMA) =>
				(q_quantification)
		|	(B_LPAREN B_IDENTIFIER B_POINT) =>
				(B_LPAREN! q_quantification B_RPAREN!)
		|	(B_LPAREN B_LPAREN B_IDENTIFIER B_RPAREN) =>
				(B_LPAREN! q_quantification B_RPAREN!)	
		|	(B_LPAREN B_IDENTIFIER B_RPAREN) =>
				(q_quantification)	
		|	(B_IDENTIFIER) =>
				(q_quantification)
		)
;

// inherited from grammar ExpressionParser
q_quantification :alist_var 
        B_POINT!
        B_LPAREN! 
            expression 
        B_RPAREN!
;

// inherited from grammar ExpressionParser
q_lambda :(	B_LAMBDA^
        |	"PI"^
        |	"SIGMA"^
        |	"UNION"^
        |	"INTER"^
        )	
        (
        	(B_LPAREN B_LPAREN B_IDENTIFIER B_COMMA)  =>	(B_LPAREN! q_operande B_RPAREN!)
        |	(B_LPAREN B_IDENTIFIER B_COMMA)           =>	(q_operande)
        |	(B_LPAREN B_IDENTIFIER B_POINT)           =>	(B_LPAREN! q_operande B_RPAREN!)
        |	(B_LPAREN B_LPAREN B_IDENTIFIER B_RPAREN) =>	(B_LPAREN! q_operande B_RPAREN!)	
        |	(B_LPAREN B_IDENTIFIER B_RPAREN)          =>	(q_operande)	
        |	(B_IDENTIFIER)                            =>	(q_operande)
        )
;

// inherited from grammar ExpressionParser
q_operande :alist_var
        B_POINT!
        B_LPAREN!
            expression
        B_SUCH^
            expression
        B_RPAREN!
;

// inherited from grammar ExpressionParser
is_struct :"struct"^ 
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;

// inherited from grammar ExpressionParser
is_record :is_rec
    |	is_struct
;

// inherited from grammar ExpressionParser
listRecord :a_record 
        (   
            B_COMMA^ 
            a_record
        )*
;

// inherited from grammar ExpressionParser
alist_var :(B_LPAREN) =>	
		B_LPAREN^   	list_identifier B_RPAREN!
|				list_identifier 
;

// inherited from grammar ExpressionParser
list_New_Predicate :new_predicate
        (
            B_COMMA^ 
            new_predicate
        )*
;

// inherited from grammar ExpressionParser
new_predicate :expression
        (
            (	B_SEMICOLON^
            |	B_PARALLEL^
            ) 
            predicate
        )* 
;

// inherited from grammar ExpressionParser
a_record :(B_IDENTIFIER B_INSET^) => B_IDENTIFIER a:B_INSET^ {#a.setType(B_SELECTOR);} pfunctional_set
|	pfunctional_set
|	is_struct
;

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}class GeneralisedSubstitutionLanguageLexer extends Lexer;

options {
	importVocab=	GeneralisedSubstitutionLanguageLexer;
	exportVocab=	GeneralisedSubstitutionLanguage;
	caseSensitive=	true	    ;
	caseSensitiveLiterals=	true	;
	testLiterals=	true	;
	k=	5	;
	charVocabulary=   '\u0003' .. '\uFFFF';
	codeGenBitsetTestThreshold=   20;
}

{
	String module = "GeneralisedSubstitutionLanguage.g";


	protected int 	  tokcolumn   		= 1 ;
	protected int 	  column 	    	= 1 ;
  	protected int 	  tokenNumber  		= 0;
  	protected boolean countingTokens 	= true;

// Error Management
	ErrorMessage errors = new ErrorMessage();

	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}


// Lexem Management
	public void consume() 
	throws CharStreamException { 
	  try {      // for error handling

		if (inputState.guessing > 0)
		{
		 if (text.length() == 0)
		 {	// remenber token start column
			tokcolumn = column;
			System.err.println("Column "+tokcolumn); 
		 }

		 if (LA(1)=='\n') 
			{column = 1;}
		 else
			{column++ ;}
		}
		super.consume();
	   }
	   catch (CharStreamException ignore) 
	   {
	   }
	}

	protected Token makeToken(int t)
	{   
		if ( t != Token.SKIP && countingTokens) 
		{
        		tokenNumber++;
    		}
		MyToken tok = (MyToken) super.makeToken(t);
		tok.setColumn(tokcolumn);
    		tok.setTokenNumber(tokenNumber);
		return tok;
	}
}
GSL_SUCH :"|"   ;

GSL_FOR_SUCH :"@"   ;

GSL_GUARD :"==>" ;

SUBST_TO :"[["  ;

B_NUMBER 
options {
	paraphrase= "a number";
}
:('0'..'9')+ 		;

B_ASTRING 
options {
	paraphrase= "a string literal";
}
:'"'! ( ~('"') )* '"'!	;

B_IDENTIFIER 
options {
	testLiterals= true;
	paraphrase= "an identifer";
}
:('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
				
			;

WS 
options {
	paraphrase= "white space";
}
:(	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // Evil DOS
			|	'\r'    // Macintosh
			|	'\n'    // Unix (the right way)
			)
			{ newline(); }
		)
		{ $setType(Token.SKIP); } 			// avant a virer des que possible { _ttype = Token.SKIP; }
	;

ML_COMMENT 
options {
	paraphrase= "a comment";
}
:"/*"
    		(	/*	'\r' '\n' can be matched in one alternative or by matching
				'\r' in one iteration and '\n' in another.  I am trying to
				handle any flavor of newline that comes in, but the language
				that allows both "\r\n" and "\r" and "\n" to all be valid
				newline is ambiguous.  Consequently, the resulting grammar
				must be ambiguous.  I'm shutting this warning off.
			*/
		options {
		   generateAmbigWarnings=false;
    		}
		   :
		   { LA(2)!='/' }? '*'
		   |	'\r' '\n'		{newline();}
		   |	'\r'			{newline();}
		   |	'\n'			{newline();}
		   |	~('*'|'\n'|'\r')
		)*
    		"*/" {$setType(Token.SKIP);}
|
		"/*?"
    		(	/*	'\r' '\n' can be matched in one alternative or by matching
				'\r' in one iteration and '\n' in another.  I am trying to
				handle any flavor of newline that comes in, but the language
				that allows both "\r\n" and "\r" and "\n" to all be valid
				newline is ambiguous.  Consequently, the resulting grammar
				must be ambiguous.  I'm shutting this warning off.
			*/
		options {
		   generateAmbigWarnings=false;
    		}
		   :
		   { LA(3)!='/' }? "?*"
		   |	'\r' '\n'		{newline();}
		   |	'\r'			{newline();}
		   |	'\n'			{newline();}
		   |	~('?'|'\n'|'\r')  
		)*
    		"?*/" {$setType(Token.SKIP);}
;

// inherited from grammar ExpressionLexer
protected NEWLINE :(
		'\n' |
		(
			'\r' ('\n')?
		)
	)
	{ newline(); }
	;

// inherited from grammar ExpressionLexer
B_LPAREN :'('			;

// inherited from grammar ExpressionLexer
B_COMMA :','			;

// inherited from grammar ExpressionLexer
B_RPAREN :')'			;

// inherited from grammar ExpressionLexer
B_PARALLEL :"||" 			;

// inherited from grammar ExpressionLexer
B_DOUBLE_EQUAL :"==" 			;

// inherited from grammar ExpressionLexer
B_MINUS :'-' 			;

// inherited from grammar ExpressionLexer
B_ADD :'+' 			;

// inherited from grammar ExpressionLexer
B_POWER :"**" 			;

// inherited from grammar ExpressionLexer
B_MULT :'*' 			;

// inherited from grammar ExpressionLexer
B_DIV :'/' 			;

// inherited from grammar ExpressionLexer
B_AND :'&' 			;

// inherited from grammar ExpressionLexer
B_dollars :'$' 			;

// inherited from grammar ExpressionLexer
B_OUT :"<--" 		;

// inherited from grammar ExpressionLexer
B_garde :"==>" 		;

// inherited from grammar ExpressionLexer
B_SEMICOLON :';' 			;

// inherited from grammar ExpressionLexer
B_BECOME_ELEM :"::"  		;

// inherited from grammar ExpressionLexer
B_SIMPLESUBST :":=" 			;

// inherited from grammar ExpressionLexer
B_IMPLIES :"=>" 			;

// inherited from grammar ExpressionLexer
B_EQUIV :"<=>" 		;

// inherited from grammar ExpressionLexer
B_INSET :':' 			;

// inherited from grammar ExpressionLexer
B_NOTINSET :"/:" 			;

// inherited from grammar ExpressionLexer
B_SUBSET :"<:" 			;

// inherited from grammar ExpressionLexer
B_NOTSUBSET :"/<:" 		;

// inherited from grammar ExpressionLexer
B_STRICTSUBSET :"<<:" 		;

// inherited from grammar ExpressionLexer
B_NOTSTRICTSBSET :"/<<:" 		;

// inherited from grammar ExpressionLexer
B_EMPTYSET :"{}" 			;

// inherited from grammar ExpressionLexer
B_CURLYOPEN :'{' 			;

// inherited from grammar ExpressionLexer
B_CURLYCLOSE :'}' 			;

// inherited from grammar ExpressionLexer
B_EQUAL :'=' 			;

// inherited from grammar ExpressionLexer
B_LESS :'<' 			;

// inherited from grammar ExpressionLexer
B_GREATER :'>' 			;

// inherited from grammar ExpressionLexer
B_NOTEQUAL :"/=" 			;

// inherited from grammar ExpressionLexer
B_LESSTHANEQUAL :"<=" 			;

// inherited from grammar ExpressionLexer
B_GREATERTHANEQUAL :">=" 			;

// inherited from grammar ExpressionLexer
B_PARTIAL :"+->" 		;

// inherited from grammar ExpressionLexer
B_RELATION :"<->" 		;

// inherited from grammar ExpressionLexer
B_TOTAL :"-->" 		;

// inherited from grammar ExpressionLexer
B_PARTIAL_INJECT :">+>" 		;

// inherited from grammar ExpressionLexer
B_TOTAL_INJECT :">->" 		;

// inherited from grammar ExpressionLexer
B_PARTIAL_SURJECT :"+->>" 		;

// inherited from grammar ExpressionLexer
B_TOTAL_SURJECT :"-->>" 		;

// inherited from grammar ExpressionLexer
B_BIJECTION :">->>" 		;

// inherited from grammar ExpressionLexer
B_TILDE :'~' 			;

// inherited from grammar ExpressionLexer
B_BRACKOPEN :'[' 			;

// inherited from grammar ExpressionLexer
B_BRACKCLOSE :']' 			;

// inherited from grammar ExpressionLexer
B_QUOTEIDENT :"'" 			;

// inherited from grammar ExpressionLexer
B_MAPLET :"|->" 		;

// inherited from grammar ExpressionLexer
B_DOMAINRESTRICT :"<|" 			;

// inherited from grammar ExpressionLexer
B_RANGERESTRICT :"|>" 			;

// inherited from grammar ExpressionLexer
B_DOMAINSUBSTRACT :"<<|" 			;

// inherited from grammar ExpressionLexer
B_RANGESUBSTRACT :"|>>" 		;

// inherited from grammar ExpressionLexer
B_OVERRIDEFORWARD :"<+" 			;

// inherited from grammar ExpressionLexer
B_OVERRIDEBACKWARD :"+>" 			;

// inherited from grammar ExpressionLexer
B_RELPROD :"><"  		;

// inherited from grammar ExpressionLexer
B_CONCATSEQ :'^' 			;

// inherited from grammar ExpressionLexer
B_PREAPPSEQ :"->" 			;

// inherited from grammar ExpressionLexer
B_APPSEQ :"<-" 			;

// inherited from grammar ExpressionLexer
B_PREFIXSEQ :"/|\\" 		;

// inherited from grammar ExpressionLexer
B_SUFFIXSEQ :"\\|/" 		;

// inherited from grammar ExpressionLexer
B_RANGE :".." 			;

// inherited from grammar ExpressionLexer
B_UNION :"\\/" 		;

// inherited from grammar ExpressionLexer
B_INTER :"/\\" 		;

// inherited from grammar ExpressionLexer
B_LAMBDA :'%' 			;

// inherited from grammar ExpressionLexer
B_FORALL :'!' 			;

// inherited from grammar ExpressionLexer
B_EXISTS :'#' 			;

// inherited from grammar ExpressionLexer
B_SEQEMPTY :"<>" | "[]" 		;

// inherited from grammar ExpressionLexer
B_POINT :'.' 			;

// inherited from grammar ExpressionLexer
B_SUCH :'|' 			;

// inherited from grammar ExpressionLexer
B_CPRED :"$0"			;

// inherited from grammar ExpressionLexer
protected DIGIT :'0' .. '9'
;

// inherited from grammar ExpressionLexer
protected ALPHA :'a' .. 'z' 
    | 'A' .. 'Z'
;

// inherited from grammar ExpressionLexer
protected VOCAB 
options {
	paraphrase= "an escape sequence";
}
:'\3'..'\377'
;


