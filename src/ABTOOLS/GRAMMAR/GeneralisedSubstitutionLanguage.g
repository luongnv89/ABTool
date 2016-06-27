
// 
// 	Author		:	Boulanger Jean-Louis
//	Email		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	Predicat.g
//	Date		:	Creation 10/04/2003
//	Descripton	:	A GSL
//
//	
//	Copyright 2003-2009 Boulanger Jean-Louis
//

// Releases
//	April 10  2003	v 0.1 	
//     Creation
//
//  February  2004   v 0.2 
//     Introduce error behavior in lexer
//
//  July      2004   v 0.3
//     Packaging and Restructuring
//
//  September 2004   v 0.3.1
//     Change LExpression to Expression
//
//  August    2005   v 0.3.2
//     Pretty printing


header
{
    package  ABTOOLS.GRAMMAR;
}

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//



// Import the necessary classes 
// -----------------------------

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;

}

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

// Define a Parser, calling it eneralisedSubstitutionLanguagParser
// ---------------------------------------------------------------

class GeneralisedSubstitutionLanguageParser extends ExpressionParser;
options 
{
        importVocab     =   Expression;
        exportVocab	=   GeneralisedSubstitutionLanguageLexer;		// Call its vocabulary "xxxx" 
    	k				            =	1 ;		// k tokens lookahead

    	codeGenMakeSwitchThreshold 	= 	2;  			// Some optimizations
    	codeGenBitsetTestThreshold 	= 	3;

//defaultErrorHandler = false;     // Don't generate parser error handlers

    	buildAST 			        = 	true;
        ASTLabelType 			    = 	"MyNode";
} 


// Indtroduce some behaviours....
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

// Grammar begin  HERE
// -------------------

/**
 *	La regle de base permettant de realiser le parsing
 **/	

analyse_GSL :
        basic_substitution
;

basic_substitution :
        (expression GSL_SUCH ) => expression GSL_SUCH  basic_substitution
    |   (expression GSL_GUARD) => expression GSL_GUARD basic_substitution
    |   GSL_FOR_SUCH list_var B_POINT expression
 //   |   basic_substitution GSL_BOUNDED basic_substitution
; 

protected
list_var	:
	 	(B_LPAREN) =>	B_LPAREN^   list_identifier B_RPAREN!
    |                               list_identifier 
;

protected
list_identifier	:
 		B_IDENTIFIER  
        (
            B_COMMA^ 
            B_IDENTIFIER 
        )*
;


// My GeneralisedSubstitutionLanguage lexer
//-----------------------------------------
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

class GeneralisedSubstitutionLanguageLexer extends ExpressionLexer;
options 
{
        importVocab		=	GeneralisedSubstitutionLanguageLexer;
        exportVocab		=	GeneralisedSubstitutionLanguage;

        caseSensitive		=	true	    ;		// In Predicate, the case is signifiant
        caseSensitiveLiterals	=	true	;		// In Predicate, the case is signifiant

        testLiterals		=	true	;		    // automatically test for literals
    	k			            =	5	;               // k characters of lookahead
}

// Introduce some behaviours....

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

GSL_SUCH        : "|"   ;
GSL_FOR_SUCH    : "@"   ;
GSL_GUARD       : "==>" ;

//GSL_BOUNDED     : "[]"  ;
SUBST_TO        : "[["  ;


B_NUMBER 
options {
  	paraphrase = "a number";
}   			: ('0'..'9')+ 		;

B_ASTRING 
options {
  	paraphrase = "a string literal";
}  			: '"'! ( ~('"') )* '"'!	;


// an identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
B_IDENTIFIER
options 
{	testLiterals = true;  
	paraphrase   = "an identifer";
} 
			:
				('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
				
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
