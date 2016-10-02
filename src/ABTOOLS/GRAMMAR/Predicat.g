// 
// 	Author		:	Boulanger Jean-Louis
//	Email		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	Predicat.g
//	Date		:	Creation 17/09/2007
//	Descripton	:	Another B Parser written in ANTLR
//
//	
//	Copyright 2007-2010 Boulanger Jean-Louis
//

// Releases
//	September 17 2007	v 0.1 	Creation

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header
{
    package ABTOOLS.GRAMMAR;
    
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}

// Import the necessary classes 
// -----------------------------

{
	import java.io.*;
	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	//import ABTOOLS.DEBUGGING.*;
	//import ABTOOLS.ANTLR_TOOLS.MyToken;

//	import MyNode;
//	import ASTterme;
}

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

// Define a Parser, calling it PredicateParser
// ---------------------------------------------

class PredicatParser extends Parser;
options 
{
    	exportVocab			=	PredicatLexer;	// Call its vocabulary "predicate" 
    	k				=	1 ;		// k tokens lookahead

    	codeGenMakeSwitchThreshold 	= 	2;  		// Some optimizations
    	codeGenBitsetTestThreshold 	= 	3;

//defaultErrorHandler = false;     // Don't generate parser error handlers

    	buildAST 			= 	true;
        ASTLabelType 			= 	"MyNode";
} 


// Indtroduce some behaviours....
{
	String module = "predicat.g";

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

// main Rule ...

analyse_predicate :
	predicate
|	 /* Empty source files are *not* allowed.  */
{
	System.err.println ( "Warning : Empty predicat !" );
	errors.WSyntaxic   ( module, "The file is empty" );
}
;

listPredicate	:
        predicate 
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

// a REVOIR
expression :
	predicate
;
	
// A (possibly-qualified) B identifier.  
// We start with the first B_IDENTIFIER 
// and expand its name by adding dots and following B_IDENTIFIER



nameRenamed	:
	B_IDENTIFIER 
	(
		B_POINT^ 
		B_IDENTIFIER
	)*
;

nameRenamedDecorated:
	nameRenamed 
	(
		B_CPRED^
	)?
;

// Predicate
//
// Note that most of these expressions follow the pattern
//   thisLevelExpression : nextHigherPrecedenceExpression (OPERATOR nextHigherPrecedenceExpression)*
// which is a standard recursive definition for a parsing an expression.
//
//
//	A COMPLETER
//
// The operators in B have the following precedences:
// (Sees the Reference Manual of Steria Mediterrane)
//
//    highest (250)  ! # % '
//	      (230)  ~
//	      (220)  .
//	      (210)  - unary
//            (200)  **
//            (190)  * / mod 
//            (180)  + -
//	      (170)  ..
//            (160)  -> /: /= /\  /|\  < <+  <<| <= <| > >< >= \/ \|/ ^ |-> |> |>> 
//            (125)  +-> +->> --> -->> <-> >+> >-> >+>> >->> 
// * 	      (115)  ,
// * 	      (110)  /<: /<<: <: <<: 
// *          ( 60)  : <=> = 
// *          ( 40)  & or
// *          ( 30)  =>
//            ( 20)  ; || 
//    lowest  ( 10)  | 
//
//
// 
// Note that the above precedence levels map to the rules below...
//
// Once you have a precedence chart, writing the appropriate rules as below
// is usually very straightfoward




// PREDICATE => No typing
predicate	:
	plogical_1 		
	( 	B_IMPLIES^
		plogical_1
	)*
;

protected
plogical_1	:
	plogical_2 
	( 
		(	"or"^  
		| 	B_AND^ 
		)
		plogical_2
	)*
;

// On inhibe juste le typing
protected
plogical_2	:
	psubset_description
	( 
		( 	B_EQUIV^ 
		|	B_EQUAL^  
		) 
		psubset_description
	)*
;

protected
psubset_description:
// August 2010
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

protected
pextended_pair	:
	parithmetic_3	
	( 
		a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}
		parithmetic_3 
	)*
;

protected
parithmetic_3	:
	psequence_description
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

protected
psequence_description:
	pset_description 
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


//	Afin de traiter le cas particulier de bool(xx:INT) qui 
//	n'est pas permis pour f(x+1)
//	On a une expression dans bool(E) car il peut y avoir l'appartenance
protected
pset_description :
	"bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
|	pfunctional_set
		;

protected
pfunctional_set	:
	pfunctional_const_set 
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

protected
pfunctional_const_set:
	pset_constructors     
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

protected
pset_constructors:
	ppaire 
	(
		(	B_UNION^
		|	B_INTER^
		)
		ppaire
	)*
;

protected
ppaire		:
	parithmetic_0
	( 
		B_MAPLET^ 
		parithmetic_0
	)*
;

protected
parithmetic_0	:

(basic_sets B_MULT) => 
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

protected
parithmetic_1	:
	parithmetic_2 
	( 
		(	B_DIV^ 
		| 	"mod"^
		)
		parithmetic_2
	)*
;

protected
parithmetic_2	:
	pbases 	
	( 
		(	B_ADD^ 
		| 	B_MINUS^
		)
		pbases
	)*
;

// Dans la regle suivante on remplace <t,u> par [t,u]
protected
pbases		:
	B_SEQEMPTY
|	B_BRACKOPEN^ 					listPredicate B_BRACKCLOSE!
|	c:B_LESS^       {#c.setType(B_BRACKOPEN);}	listPredicate B_GREATER!
|	basic_sets
|	pbasic_value (B_RANGE^ parithmetic_0)?		// Il y avait predicate
|	B_EMPTYSET
|	B_CURLYOPEN^ pvalue_set B_CURLYCLOSE!
|	quantification
|	q_lambda
;

// Dans un ensemble il peut y avoir 
protected
pvalue_set	:
	(alist_var B_SUCH) 	=> alist_var 	 B_SUCH^  expression
|				   predicate 	(B_COMMA^ predicate)*
;

// Dans la egle on renomme +aa en unary_add aa idem avec -
protected
pbasic_value	:
	a:B_ADD^   {#a.setType(UNARY_ADD);} 	punary_basic_value_inverted
|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	punary_basic_value_inverted
|						punary_basic_value_inverted
|	B_ASTRING
|	is_rec
|	"TRUE"^ 
|	"FALSE"^
;

is_rec:
        "rec"^
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;


// On a eu un cas ff~(..)~[ .. ]~
protected
punary_basic_value_inverted:	
	punary_basic_value 
	(	B_TILDE^
	)?
;

protected
punary_basic_value:
	predInvertedParamInvertedQuoted 
	(	c:B_BRACKOPEN^ 
{
	#c.setType(APPLY_TO);
}  
		predicate 
		B_BRACKCLOSE!
	)?
|	B_NUMBER
;

// Attention on en fait peut etre beaucoup
//		( ....)' (.....)
protected
predInvertedParamInvertedQuoted:
	predInvertedParamInverted 
	(
		B_QUOTEIDENT^ 
		predInvertedParamInverted
	)*
;

protected
predInvertedParamInverted:
	predInvertedParam (B_TILDE^)?
;

protected
predInvertedParam:
	predParentInverted 
	(	B_LPAREN^ 
		list_New_Predicate 
		B_RPAREN!
	)*
;

protected
predParentInverted:
	(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	pred_parent (B_TILDE^)?
;

pred_parent	:
	c:B_LPAREN^ 
{#c.setType(PARENT);}
	pred_func_composition
	B_RPAREN!
|	nameRenamedDecorated 
;

pred_func_composition:
	predicate 
	( 
		(	B_SEMICOLON^ 
		| 	B_PARALLEL^
// Il y avait ca juste pour les predicats si pb il faudra verifier pour expression
		|	B_COMMA^
		) 
		predicate
	)* 
;

// Il faut absorber toutes les parentheses
quantification	:		
	(	B_FORALL^ 
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

q_quantification:
        alist_var 
        B_POINT!
        B_LPAREN! 
            expression 
        B_RPAREN!
;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

// Il y a de nombreux cas posant des pb d'ambiguite
//
q_lambda	:	
        (	B_LAMBDA^
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


// ATTENTION
// A verifier plus tard si on a predicate ou expression
q_operande	:
        alist_var
        B_POINT!
        B_LPAREN!
            expression
        B_SUCH^
            expression
        B_RPAREN!
;

// Some usefull list of specific objects

/**
 * Is_rec 	is applicable for no typing predicate
 * Is_record    is applicable for    typing predicate (expression)
 **/

is_struct	: 
        "struct"^ 
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;

is_record	:
        is_rec
    |	is_struct
;

listRecord	:
        a_record 
        (   
            B_COMMA^ 
            a_record
        )*
;

list_identifier	:
        B_IDENTIFIER  
        ( 
            B_COMMA^ 
            B_IDENTIFIER 
        )*
;
alist_var:
	(B_LPAREN) =>	
		B_LPAREN^   	list_identifier B_RPAREN!
|				list_identifier 
;

list_New_Predicate:
        new_predicate
        (
            B_COMMA^ 
            new_predicate
        )*
;

new_predicate	:
        expression
        (
            (	B_SEMICOLON^
            |	B_PARALLEL^
            ) 
            predicate
        )* 
;


/**
 *
 * Attention a ne pas mettre n'importe quoi ....
 *	- ne pas oublie que ca peut etre un autre record (01/2001)
 *
 * On change le : en SELECTOR pour retirer les conflits dans les treewalker
 **/

a_record	:	
	(B_IDENTIFIER B_INSET^) => B_IDENTIFIER a:B_INSET^ {#a.setType(B_SELECTOR);} pfunctional_set
|	pfunctional_set
|	is_struct
;

basic_sets:
	"INT"
|	"INT1"
|	"INTEGER"
|	"INTEGER1"
|	"BOOL"
|	"NAT"
|	"NAT1"
|	"NATURAL"
|	"NATURAL1"
;

// Somes imaginaries tokens for conflict
dummy :
	UNARY_ADD
|	UNARY_MINUS
|	ELEM_SET
|	LIST_VAR
|	APPLY_TO
|	PARENT
|	B_SELECTOR
|	PRODSET

// Basic Functor  
|	B_NOT
|	B_DOM
|	B_RAN
|   	B_MIN
|   	B_MAX
|   	B_CARD
|   	B_MOD
;



// My Predicate lexer
//--------------------

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

class PredicatLexer extends Lexer;
options 
{
    	exportVocab		=	Predicat;		// Call its vocabulary "Predicate" 

//    	filter			= 	false	;		// Don't filter but parse all
// 	tokenVocabulary		=	B	;    		// call the vocabulary "Predicate"

	caseSensitive		=	true	;		// In Predicate, the case is signifiant
	caseSensitiveLiterals	=	true	;		// In Predicate, the case is signifiant

	testLiterals		=	true	;		// automatically test for literals
    	k			=	5	;               // k characters of lookahead
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


B_LPAREN		: '('			;
B_COMMA			: ','			;
B_RPAREN		: ')'			;


B_PARALLEL 		: "||" 			;

B_DOUBLE_EQUAL 		: "==" 			;

B_MINUS 		: '-' 			;
B_ADD 			: '+' 			;

B_POWER 		: "**" 			;
B_MULT 			: '*' 			;
B_DIV 			: '/' 			;

B_AND 			: '&' 			; 

B_dollars 		: '$' 			;
B_OUT 			: "<--" 		; 
B_garde 		: "==>" 		;


B_SEMICOLON 		: ';' 			;

B_BECOME_ELEM 		: "::"  		;
B_SIMPLESUBST 		: ":=" 			;

B_IMPLIES 		: "=>" 			;
B_EQUIV 		: "<=>" 		;

B_INSET 		: ':' 			;
B_NOTINSET 		: "/:" 			;
B_SUBSET 		: "<:" 			;
B_NOTSUBSET 		: "/<:" 		;
B_STRICTSUBSET		: "<<:" 		;
B_NOTSTRICTSBSET 	: "/<<:" 		;

B_EMPTYSET 		: "{}" 			;

B_CURLYOPEN 		: '{' 			;
B_CURLYCLOSE 		: '}' 			;

B_EQUAL 		: '=' 			;
B_LESS 			: '<' 			;
B_GREATER 		: '>' 			;
B_NOTEQUAL 		: "/=" 			;
B_LESSTHANEQUAL 	: "<=" 			;
B_GREATERTHANEQUAL 	: ">=" 			;

B_PARTIAL 		: "+->" 		;
B_RELATION 		: "<->" 		;
B_TOTAL 		: "-->" 		;
B_PARTIAL_INJECT 	: ">+>" 		;
B_TOTAL_INJECT 		: ">->" 		;
B_PARTIAL_SURJECT 	: "+->>" 		;
B_TOTAL_SURJECT 	: "-->>" 		;
B_BIJECTION 		: ">->>" 		;
B_TILDE 		: '~' 			;

B_BRACKOPEN 		: '[' 			;
B_BRACKCLOSE 		: ']' 			;

B_QUOTEIDENT 		: "'" 			;

B_MAPLET 		: "|->" 		;

B_DOMAINRESTRICT 	: "<|" 			;
B_RANGERESTRICT 	: "|>" 			;
B_DOMAINSUBSTRACT 	:"<<|" 			;
B_RANGESUBSTRACT 	: "|>>" 		;
B_OVERRIDEFORWARD 	: "<+" 			;
B_OVERRIDEBACKWARD 	: "+>" 			;
B_RELPROD 		: "><"  		;


B_CONCATSEQ 		: '^' 			;
B_PREAPPSEQ 		: "->" 			;
B_APPSEQ 		: "<-" 			;		// We double the \ its a antlr constraints
B_PREFIXSEQ 		: "/|\\" 		;		// We double the \ its a antlr constraints
B_SUFFIXSEQ 		: "\\|/" 		;

B_RANGE 		: ".." 			;

B_UNION 		: "\\/" 		;		// We double the \ its a antlr constraints
B_INTER 		: "/\\" 		;		// We double the \ its a antlr constraints

B_LAMBDA 		: '%' 			;
B_FORALL 		: '!' 			;
B_EXISTS 		: '#' 			;


B_SEQEMPTY 		: "<>" | "[]" 		;

B_POINT 		: '.' 			;
//B_BECOME 		: ":(" 			; 
B_SUCH 			: '|' 			;

B_CPRED			: "$0"			;

protected
DIGIT
:
        '0' .. '9'
;


B_NUMBER 
options {
  	paraphrase = "a number";
}
    : 
        (DIGIT)+
;

protected 
ALPHA
:
      'a' .. 'z' 
    | 'A' .. 'Z'
;

B_ASTRING 
options {
  	paraphrase = "a string literal";
}  			: '"'! ( ~('"') )* '"'!	;


// a dummy rule to force vocabulary to be all characters (except special
// ones that ANTLR uses internally (0 to 2))
protected
VOCAB
options {
  	paraphrase = "an escape sequence";
}
:
	'\3'..'\377'
;


// an identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
B_IDENTIFIER
options 
{	testLiterals = true;  
	paraphrase   = "an identifer";
} 
    :
        (ALPHA) (ALPHA | DIGIT |'_')*			
    ;

WS
options {
  	paraphrase = "white space";
}  	:	(	' '
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


// multiple-line comments
ML_COMMENT
options {
  	paraphrase = "a comment";
}        :
		"/*"
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
