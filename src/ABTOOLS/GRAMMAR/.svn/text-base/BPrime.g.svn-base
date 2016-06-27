// 
// 	Author		:	Boulanger Jean-Louis	(jean.louis.boulanger@wanadoo.fr)
// 	File		:	BPrime.g
//	Date		:	Creation 01/01/2001
//	Descripton	:	Another B Parser written in ANTLR
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

//	0.1	1/01/2001	Creation de cette version pour BPRIME
//
//	0.2	7/08/2001
//	- Add the pssibility to use , for separate the sets in SETS clause.
//
//	0.3	04/2002
//	- We introduce semantics controls RU1 RA1 (see technical documentation)
//
//	0.3.1   May     2002
//	- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  0.3.2   July    2003
//  - Pretty Printing
//  - module variable
//  0.4     July    2004
//  - Packaging and Finalizing 
//  1.1     October 2004
//  - Introduced the POST substitution from Samuel Colin Work's

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 * @version
 **/

header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// our Packages
    import ABTOOLS.ANTLR_TOOLS.*;
    import ABTOOLS.DEBUGGING.*;

}


// Define a Parser, calling it BParser
//-----------------------------------------------------------------------------

class BPrimeParser extends BParser;
options 
{
    importVocab                 = B;
    exportVocab                 = BPrimeLexer;		// Call its vocabulary "BPRIME" 

    k                           = 10 ;				// k tokens lookahead

    codeGenMakeSwitchThreshold  = 2;  		// Some optimizations
    codeGenBitsetTestThreshold  = 3;

//defaultErrorHandler = false;     // Don't generate parser error handlers

    buildAST 	                = true;
    ASTLabelType 	            = "MyNode";
} 

// Indtroduce some behaviours....

{
	String module = "BPrime.g";


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
	
	public String path = "";
  
  public void setPath(String p){
     path = p;
  }

// Debug enable or disabled
	protected DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}


// Switch de lecture des machine abstraites liees
	protected boolean LoadLinked = false;

	public ListAM listAM = new ListAM();

	public ListAM getListAM()
	{
		return listAM;
	}

	public void add_AM (MyNode t1, String type)
	{
		if ( type.compareTo("PROMOTES") != 0 )
		{
			String ext = searchExtension(path, #t1.getText());
      listAM.Add_AM(new LinkedAM(#t1.getText(), type, ext, path));
      //listAM.Add_AM(new LinkedAM(#t1.getText(), type)); 
		}
	}


// July 2001
// We prepare the PO generation by memorization of principals terms

	public ASTterme previous_gop = new ASTterme(debug);

	public ASTterme getPreviousGOP()
	{
		return previous_gop;
	}

// April 2002
// Semantic Control
	public void RU1 (MyNode node)
	{
	}

	public void RA1 (MyNode node)
	{
	}

// January 2010
// Metrics management

	public Metrics metrics = null;
	
	public Metrics getMetrics()
	{
		if (metrics != null)
		{
			return metrics;
		}
		else
		{
		// error .....
			return null;
		}
	}

// permet de gerer le type de mesure en cours
	protected int metricstype = 0;
	
	protected int unknown    	= 0;
	protected int mvariables 	= 1;
	protected int mconstants 	= 2;
	protected int maconstants 	= 3;
	protected int mset 		= 4;
	protected int mservices  	= 5;
	protected int mcvariables 	= 6;
		
	protected void ChooseMetrics (int mtype)
	{
		metricstype = mtype;
	}
	
	protected void AddMetrics()
	{
		if (metricstype == mvariables)
		{
			metrics.addVarGlobal();
		}
		else if (metricstype == mconstants)
		{
			metrics.addConstant();
		}
		else if (metricstype == maconstants)
		{
			metrics.addAConstant();
		}
		else if (metricstype == mservices)
		{
			metrics.addService();
		}
		else if (metricstype == mset)
		{
			metrics.addSet();
		}
		else if (metricstype == mcvariables)
		{
			metrics.addCVarGlobal();
		}
		else
		{
		}
	}
		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
	
	/* Searches the file 'name' with extension '.mch'
   *  if the file doesn't exist, search the file 'name' with 
   *  extension 'ref'. Returns the extension found.
   */
   public static String searchExtension(String path, String name){
   String extension = "";
   File file = new File(path,name+".mch");
   if (file.isFile()){
    extension = ".mch";
   }
   else{
    File file2 = new File(path,name+".ref");
    if (file2.isFile()){
     extension = ".ref";
    }
   }     
   return extension;
   }
	
}



// For all Name with parameters
protected
paramName	:
        B_IDENTIFIER 
        (
            B_LPAREN^
            listTypedIdentifier
            B_RPAREN!
        )?
    ;

protected
paramRename	:
        nameRenamed 
        (
            B_LPAREN^
            listTypedIdentifier
            B_RPAREN!
        )?
    ;

protected	
listTypedIdentifier:
        typedIdentifier ( B_COMMA^ typedIdentifier  )*
    ;

// An extension due to B'
protected
typedIdentifier :
        nameRenamed (B_INSET^ basic_sets)?
    ;


// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B
protected
sets_declaration:
        set_declaration 
        ( 
            (	B_SEMICOLON^ 
            | 	B_COMMA^
            ) 
            set_declaration
        )*
    ;

// He oui il y a trois valeurs pour la clause CONSTANTS
protected
constants	:
        (
            "CONSTANTS"^
        | 	"CONCRETE_CONSTANTS"^
        |	"VISIBLE_CONSTANTS"^
        )
        listTypedIdentifier
    ;

protected
aconstants	:
        (
            "ABSTRACT_CONSTANTS"^
        | 	"HIDDEN_CONSTANTS"^
        )
        listTypedIdentifier
    ;

protected
variables	:
        (
            "VARIABLES"^
        |	"ABSTRACT_VARIABLES"^
        |	"VISIBLE_VARIABLES"^
        ) 
        listTypedIdentifier
    ;


protected
operationHeader :
        paramName
    |	listTypedIdentifier B_OUT^ paramName
    ;



// Substitution
// Adding POST

protected
subst_mch	:
                identite
			|	substitution_block_mch
			|	substitution_precondition_mch
			|	assert_mch
			|	anif_mch
			|	choice_mch
			|	substitution_unbounded_choice_mch
			|	substitution_selection_mch
			|	let_mch
			|	case_mch
			|	simple_affect_ref
    ;

protected
subst_ref	:
                identite
			|	substitution_block_ref
			|	substitution_precondition_ref
			|	assert_ref
			|	anif_ref
			|	choice_ref
			|	substitution_unbounded_choice_ref
			|	substitution_selection_ref
			|	case_ref
			|	let_ref
			|	simple_affect_ref
			|	var_ref
;

protected
subst_imp	:
                identite
			|	substitution_block_imp
			|	assert_imp
			|	anif_imp
			|	case_imp
			|	var_imp
			|	while_loop
			|	simple_affect
		;

protected
any_mch		:
        "ANY"^ 
            listTypedIdentifier 
        "WHERE"! 
            expression 
        "THEN"!  
            substitution_mch
        "END"!
    ;

protected
any_ref		:
        "ANY"^ 
            listTypedIdentifier 
        "WHERE"! 
            expression 
        "THEN"!  
            parallele_ref
        "END"!
    ;

// extension B'
// 	VAR x,y,z IN ...
//	VAR x:P, y:Q, z:R IN ...

protected
var_ref		:
        "VAR"^
            listTypedIdentifier
        "IN"!
            parallele_ref
        "END"!
    ;

protected
var_imp		:
        "VAR"^
            listTypedIdentifier
        "IN"!
            sequential
        "END"!
    ;

protected
substitution_block_mch	
{
    boolean i = false;
}:
        rr:"BEGIN"^
            substitution_mch
        ("POST"!
            predicate
{
    i = true;
}
        )?
        "END"!
{
    if (i==true)
      #rr.setType(B_BEGIN_POST);
}
    ;

protected
substitution_block_ref
{ 
    boolean i = false;
}:
        rr:"BEGIN"^
            parallele_ref
        ("POST"!
            predicate
{
    i = true;
}
        )?
        "END"!
{
    if (i==true)
      #rr.setType(B_BEGIN_POST);
} 
    ;

// WHILE xx:INT DO 
protected
while_loop	:
        "WHILE"^	
            expression
        "DO"!
            sequential
            variant_or_no
        "END"!
    ;

dummy   :
    B_BEGIN_POST
;

// My BPrime lexer
//--------------
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

class BPrimeLexer extends BLexer;
options 
{
    importVocab             =   BPrimeLexer;
    exportVocab		        =	BPrime	;		// Call its vocabulary "BPrime" 

	caseSensitive		    =	true	;		// In B, the case is signifiant
	caseSensitiveLiterals	=	true	;		// In B, the case is signifiant

    //charVocabulary            = '\0' .. '\u00FF';
	charVocabulary              =   '\u0003' .. '\uFFFF';
	codeGenBitsetTestThreshold  =   20;

	testLiterals		    =	true	;		// automatically test for literals
    k			            =	5	;           // k characters of lookahead
 }
 
 //Introducsome behaviours....
 {   
    	public Metrics metrics = null ;
	
	public Metrics getMetrics()
	{
		if (metrics != null)
		{
			return metrics;
		}
		else
		{
		// error .....
			return null;
		}
	}

		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
}


CPPComment
options {
  	paraphrase = "a comment";
}           :
        "//" 
		( ~('\n') )* 
        { $setType(Token.SKIP); newline(); }		// avant a virer des que possible         {_ttype = Token.SKIP;}
;

// End of file BPrime.g
