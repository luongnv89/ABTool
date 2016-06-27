
// 
// 	Author		:	Boulanger Jean-Louis
//	Email		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	Expression.g
//	Date		:	Creation 10/07/2003
//	Descripton	:	Another B Parser written in ANTLR
//
//	
//	Copyright 2003-2010 Boulanger Jean-Louis
//

// Releases
//  July 2003	            v 0.1
//     	- Creation
//  July 2004               v 0.2
//      -Restructuring and packaging
//  September 2004          v 0.3
//      - Pretty Print
//      - Change LExpression in Expression
//  November 2004
//      - Add basic functor
//  August   2005
//      - Correct Identifer rules by introduction of protected rules
//        called DIGIT and ALPHA 
//  May 2007
//      - Pretty printing
//      - Modification de la prise en compte des ensembles d'elements


//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
// -----------------------------

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

// Define a Parser, calling it ExpressionParser
// ---------------------------------------------

class ExpressionParser extends PredicatParser;
options 
{
    importVocab			= 	PredicatLexer;
    exportVocab			=	ExpressionLexer;	// Call its vocabulary "Expression" 
    k				=	1;		        // k tokens lookahead

    codeGenMakeSwitchThreshold 	= 	2;  		    	// Some optimizations
    codeGenBitsetTestThreshold 	= 	3;

    //defaultErrorHandler = false;                  		// Don't generate parser error handlers

    buildAST 			= 	true;
    ASTLabelType 		= 	"MyNode";
} 


// Indtroduce some behaviours....
{
	String module = "Expression.g";

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


// Grammar begin  HERE
// -------------------

/**
 *	La regle de base permettant de realiser le parsing
 **/	

analyse_expression :	
            expression
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty expression !" );
            errors.WSyntaxic   ( module, "Expression is empty" );
        }
    ;

analyse_predicate :	
            predicate
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty predicate !" );
            errors.WSyntaxic   ( module, "Predicat is empty" );
        }
    ;



//
// Expression with typing 
//

expression			
    options {defaultErrorHandler = true;} 			// let ANTLR handle errors
:
	logical_1 	
        (	B_IMPLIES^
        	logical_1
        )*
;

logical_1:
  	logical_2
  	 (
  	 	(	"or"^ 
  	 	| 	B_AND^
  	 	)
  	 	 logical_2
  	 )*
;

logical_2	:
	subset_description
	( 
                ( 	B_EQUIV^ 
		|	B_EQUAL^  
		)   
		subset_description
	)*
;

subset_description:
// May 2007
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

extended_pair	:
                arithmetic_3
		( 
			a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}	
			arithmetic_3
		)*
;



arithmetic_3	:
                sequence_description
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

sequence_description:
                set_description 
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

set_description :
                "bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
	|	functional_set
;

functional_set	:
                functional_const_set 
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

functional_const_set:
    basic_constructors 
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

basic_constructors:
    new_couple 
    (
        (	B_UNION^
        |	B_INTER^
        )
        new_couple
    )*
;

new_couple	:
    arithmetic_0
    (
        B_MAPLET^
        arithmetic_0
    )*
;

arithmetic_0	:

(basic_sets B_MULT) => 
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

arithmetic_1	:
    arithmetic_2 	
    ( 
        (
            B_DIV^
        |   "mod"^
        )
        arithmetic_2
    )*
;

arithmetic_2:
    bases
    ( 
        (
            B_ADD^   
        |   B_MINUS^
        )
        bases
    )*
;

// Dans la regle suivante on remplace <t,u> par [t,u]
bases		:
		                basic_sets
			|	basic_value 				(B_RANGE^ 	arithmetic_0)?
			|	B_SEQEMPTY
			|	B_BRACKOPEN^ 				listPredicate 	B_BRACKCLOSE!
			|	c:B_LESS^ {#c.setType(B_BRACKOPEN);}	listPredicate 	B_GREATER!
			|	B_EMPTYSET
			|	B_CURLYOPEN^ 				value_set 	B_CURLYCLOSE!
			|	quantification
			|	q_lambda
		;

// Dans un ensemble il peut y avoir 
// Decembre 2000, un seul B_SUCH
value_set	:
            (alist_var B_SUCH) 	=> 	alist_var 	(B_SUCH^  expression)
// May 2007 - BJL 
    |	            		 	predicate 	(B_COMMA^ predicate)*
//	|				listPredicate
	;

basic_value	:
		                a:B_ADD^   {#a.setType(UNARY_ADD);} 	unary_basic_value_inverted
			|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	unary_basic_value_inverted
			|						unary_basic_value_inverted
			|	B_ASTRING
			|	is_record
			|	"TRUE"^ 
			|	"FALSE"^
;

// On a eu un cas ff~(..)~[ .. ]~
unary_basic_value_inverted:
	unary_basic_value (B_TILDE^)?
;

unary_basic_value:
    expInvertedParamInvertedQuoted 
    (	c:B_BRACKOPEN^
{
	#c.setType(APPLY_TO);
} 
        predicate 
        B_BRACKCLOSE!
    )?
|	B_NUMBER
;

expInvertedParamInvertedQuoted:	
    expInvertedParamInverted 
    (
        B_QUOTEIDENT^ expInvertedParamInverted
    )*
    // Attention on en fait peut etre beaucoup
    //			( ....)' (.....)
;

expInvertedParamInverted:
	expInvertedParam (B_TILDE^)?
;

expInvertedParam:
    expParentInverted 
    (
        B_LPAREN^ 
        list_New_Predicate 
        B_RPAREN!
    )*
;

expParentInverted	:
	(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	expression_parent (B_TILDE^)?
;

expression_parent:
    c:B_LPAREN^ 
{
	#c.setType(PARENT);
}
        expression_func_composition
        B_RPAREN!
    |	nameRenamedDecorated 
;

expression_func_composition:
        expression 
        ( 
            (
                B_SEMICOLON^ 
            | 	B_PARALLEL^
            |	B_COMMA^
            ) 
            expression
        )* 
;

basic_sets	:
        "INT"
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


// Somes imaginaries tokens for conflict
dummy 	:
    |	APPLY_TO
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

// My Expression lexer
//--------------------
{

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

class ExpressionLexer extends PredicatLexer;
options 
{
    	importVocab                 =   ExpressionLexer;
    	exportVocab		    =	Expression;		
	caseSensitive		    =	true	;
	caseSensitiveLiterals	    =	true	;

	charVocabulary              =   '\u0003' .. '\uFFFF';
	codeGenBitsetTestThreshold  =   20;

	testLiterals	    	    =	true	;		    // automatically test for literals
    	k			    =	4	;
}

// Introduce some behaviours....

{
	protected int 	  tokcolumn   		= 1 ;
	protected int 	  column 	    	= 1 ;
  	protected int 	  tokenNumber  		= 0;
  	protected boolean countingTokens 	= true;


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

WS
options {
  	paraphrase = "white space";
}
    :	
        (	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // Evil DOS
			|	'\r'    // Macintosh
			|	'\n'    // Unix (the right way)
			)
			{ newline(); }
		)
{ 
	$setType(Token.SKIP);
}		 			// avant a virer des que possible { _ttype = Token.SKIP; }
	;


// multiple-line comments
ML_COMMENT
options 
{
  	paraphrase = "a comment";
}        :
		"/*"
        (	
/*
	'\r' '\n' can be matched in one alternative or by matching
    '\r' in one iteration and '\n' in another.  I am trying to
	handle any flavor of newline that comes in, but the language
	that allows both "\r\n" and "\r" and "\n" to all be valid
	newline is ambiguous.  Consequently, the resulting grammar
	must be ambiguous.  I'm shutting this warning off.
*/
            options 
            {
                generateAmbigWarnings=false;
    		}
		   :
		        { LA(2)!='/' }? '*'
		   |	("/*") => ML_COMMENT
           |    NEWLINE
		   |	~('*'|'\n'|'\r')
		)*
        "*/" {$setType(Token.SKIP);}
|
		"/*?"
        (
/*
	'\r' '\n' can be matched in one alternative or by matching
	'\r' in one iteration and '\n' in another.  I am trying to
	handle any flavor of newline that comes in, but the language
	that allows both "\r\n" and "\r" and "\n" to all be valid
	newline is ambiguous.  Consequently, the resulting grammar
	must be ambiguous.  I'm shutting this warning off.
*/
            options 
            {
                generateAmbigWarnings=false;
    		}
        :
        { 
                LA(3)!='/' }? "?*"
           |    ("/*") => ML_COMMENT
		   |	NEWLINE
		   |	~('?'|'\n'|'\r')  
		)*
        "?*/" {$setType(Token.SKIP);}
;

protected
NEWLINE:
	(
		'\n' |
		(
			'\r' ('\n')?
		)
	)
	{ newline(); }
	;
// End of file Expression.g
