
// 
// 	Author		:	Boulanger Jean-Louis	(jean.louis.boulanger@wanadoo.fr)
// 	File		:	BEvent.g
//	Descripton	:	Another B Parser written in ANTLR 2.7.2
//	Version		:	0.1
//	
//	Copyright 2001-2010 Boulanger Jean-Louis
//

// Releases
//	April       2002	v 0.1 	Creation
//		- Create BEvent by inheritance of B
//
//	May	        2002	v 0.1.1
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  December    2002    v 0.1.2
//      - Correct somes bugs in the grammar 
//
//  MAY         2003    v 0.2
//      - Retrieve all explicit token B_*
//      - Pretty-Printing
//
//  July        2003    v 0.3
//      - Pretty Printing
//      - add module variable
//// February     2004    v 0.3.1
//      - Finalize errors handling processus
// April        2004    v 0.4
//      - Packaging and Restructuring

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/  

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
// ----------------------------
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}


// Define a Parser, calling it BEventParser
// -----------------------------------------
class BEventParser extends BParser;
options 
{
    importVocab			        = B;
    exportVocab			        = BEventLexer;	            // Call its vocabulary "BEvent"

    k                           =7;                     // k tokens lookahead

    codeGenMakeSwitchThreshold 	= 2;  	        	    // Some optimizations
    codeGenBitsetTestThreshold 	= 3;

    //defaultErrorHandler = false;                      // Don't generate parser error handlers

    buildAST 			        = true;
    ASTLabelType 		        = "MyNode";
} 


// Introduce some behaviours....
// Remarque : Cette partie ne peut etre heritee, il faut la recopier ...
{
	String module = "BEvent.g";

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



// Grammar begin  HERE
// -------------------

/**
 *	La regle de base permettant de realiser le parsing
 **/	

// Root Rule
// Le booleen LoadLinked permet d'indiquer qu'on est authorise ou non a lire les machines abstraites liees a cette machine

composant [boolean loadLinked] 	:
{
	// We memorise the global decision
	LoadLinked = loadLinked;
}
(	  		machine
		|	refinement
		|	/* Empty sources files are *not* allowed.  */
        {						
			System.err.println ( "ABParser Warning : Empty source file!" );
			errors.WSyntaxic   ( module, "The file is empty" );
		}
)
;


// In System:
// 	the clause OPERATION was renamed EVENT.
//	all other clauses are left unchanged
//
machine		:
                "SYSTEM"^
				paramName
				(	constraints
				 |	sees					// Not all link see MR
				 |	sets
				 |	constants
				 |	aconstants
				 |	properties
				 |	variables
				 |	cvariables
				 |	invariant
				 |	assertions
				 |	initialisation_mch
				 |	events_mch
				 |	modalities
				)*
			"END"! 
			EOF!
		;

events_mch	:
        "EVENTS"^ 
            listEventMch
	;


listEventMch 	: 	
        eventMch 
        (   B_SEMICOLON^  
            eventMch
        )*
    ;

eventMch	:
                nameRenamed
				c:B_EQUAL^ 
{
	#c.setType(OP_DEF);
} 
				substitution_event_mch
	;

substitution_event_mch :
			substitution_mch
	;

modalities :
        "MODALITIES"^
            modality
	;

modality :
		"ANY"^ 
            listIdentifier 
        "WHERE"! 
            expression
        "THEN"!
            event_list
        (
            maintain
        |	establish
        )
        "END"!
		|
        "BEGIN"^
            event_list
        (
            maintain
        |	establish
        )
        "END"!
		|
        "SELECT"^
            predicate
        "THEN"!
            event_list
            establish
        "END"!			
	;

maintain :
            "MAINTAIN"^
				predicate
			"UNTIL"!
				predicate
			"VARIANT"!
				a_variant
	;

establish :
            "ESTABLISH"^
				expression		// ce n'est pas un predicat
	;

event_list :
            "ALL"
		|	listIdentifier 
	;

refinement	:
                    "REFINEMENT"^
					paramName 
					(	refines
					 |	constraints
					 |	sees				// Just SEES in MR
					 |	sets 
					 |	aconstants
					 |	constants
					 |	properties
					 |	variables
					 |	cvariables
					 |	invariant
					 |	variant				// NEW 
					 |	assertions
					 |	initialisation_ref
					 |	events_ref
					 |	modalities
					)*
				"END"!
				EOF!
;

events_ref 	:
        "EVENTS"^
            listEventRef
    ;

listEventRef 	:
        eventRef 
        (
                B_SEMICOLON^  
                eventRef
        )*
    ;


// deux cas :
//	- un event
//	- un raffinement d'event

eventRef	:	 an_event_ref
				c:B_EQUAL^ 
{
	#c.setType(OP_DEF);
} 
				substitution_event_ref
		;

an_event_ref	: nameRenamed (B_ref^ listNameRenamed)?
		;

// Rq:
// Il peut y avoir plusieurs evenement qui sont raffines par 1.
listNameRenamed	:	nameRenamed (B_COMMA^ nameRenamed)*
		;

substitution_event_ref :
			parallele_ref
	;

protected
variant :
			"VARIANT"^ 	a_variant
	;

// In MR is Expression_arithmetical
protected
a_variant :
			predicate
	;

// Substitution with POST see MR
protected
substitution_block_mch	:
				rr:"BEGIN"^
					substitution_mch
				("POST"!
					predicate
{
    #rr.setType(B_BEGIN_POST);
}
				)?
				"END"!
		;

protected
substitution_block_ref	:
				rr:"BEGIN"^
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_BEGIN_POST);
}
				)?
				"END"!
		;

protected
substitution_precondition_mch:
				rr:"PRE"^
					expression
				"THEN"!
					substitution_mch
				("POST"!
					predicate
{
#rr.setType(B_PRE_POST);
}
				)?
				"END"!
		;

protected
substitution_precondition_ref:
				rr:"PRE"^
					expression
				"THEN"!
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_PRE_POST);
}
				)?
				"END"!
		;

protected
substitution_selection_mch:
				rr:"SELECT"^ 
					expression
				"THEN"!
					substitution_mch
				("POST"!
					predicate
{
            #rr.setType(B_SELECT_POST);
}
				)?
				"END"!
		;

protected
substitution_selection_ref:
				rr:"SELECT"^ 
					expression
				"THEN"!
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_SELECT_POST);
}
				)?
				"END"!
		;

protected
substitution_unbounded_choice_mch:
				rr:"ANY"^ 
					listIdentifier 
				"WHERE"! 
					expression 
				"THEN"!  
					substitution_mch
				("POST"!
					predicate
                       {#rr.setType(B_ANY_POST);}
				)?
				"END"!
		;

protected
substitution_unbounded_choice_ref:
				rr:"ANY"^ 
					listIdentifier 
				"WHERE"! 
					expression
				"THEN"!  
					parallele_ref
				("POST"!
					predicate
                       {#rr.setType(B_ANY_POST);}
				)?
				"END"!
		;


// Attention on en fait peut etre beaucoup
//		( ....)' (.....)
protected
predInvertedParamInvertedQuoted:
//				(predInvertedParamInverted B_QUOTEIDENT^ predInvertedParamInverted)
//				=>
				predInvertedParamInverted 
				(
					B_QUOTEIDENT^ predInvertedParamInverted
				)*
//				|
//				predInvertedParamInverted rr:B_QUOTEIDENT^ {#rr.setType(B_PREC);}
		;


dummy 		:
			B_PREC
    |       B_ANY_POST
    |       B_SELECT_POST
    |       B_PRE_POST
    |       B_BEGIN_POST
;

// My B Event lexer
//------------------


// Import the necessary classes 
// ----------------------------
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}


class BEventLexer extends BLexer;
options 
{
        importVocab             =   BEventLexer   ;
        exportVocab		        =   BEvent	;		    // Call its vocabulary "BEvent" 

        caseSensitive		    =	true	;		    // In B, the case is signifiant
        caseSensitiveLiterals	=	true	;		    // In B, the case is signifiant

        testLiterals		    =	true	;		    // automatically test for literals
    	k			            =	9	    ;           // k characters of lookahead
}

//Introducsome behaviours....

{
   	String module = "(Lexer part) BEvent.g";

    	ErrorMessage errors = new ErrorMessage();
    
	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}


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


//B_SYSTEM	    : 	"SYSTEM"		;
//B_EVENTS	    : 	"EVENTS"		;
//B_MODALITIES	:	"MODALITIES"	;
//B_POST          :   "POST"          ;
//B_ESTABLISH     :   "ESTABLISH"     ;
B_ref           :   "ref"           ;
