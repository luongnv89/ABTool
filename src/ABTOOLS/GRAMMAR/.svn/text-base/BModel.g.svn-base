
// 
// 	Author		:	Boulanger Jean-Louis	
//  	Email       	:   	jean.louis.boulanger@wanadoo.fr
// 	File		:	BModel.g
//	Descripton	:	This file is part of Another B Parser written in ANTLR 2.7.4
//                  we implemented the B event describe in "Discrete System Models"
//	
//	Copyright 2004-2009 Boulanger Jean-Louis
//

// Releases
//	October       2004	v 1.0 	
//      - Creation


/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 * @version
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


// Define a Parser, calling it BModelParser
// -----------------------------------------
class BModelParser extends BParser;
options 
{
    importVocab			        = B;
    exportVocab			        = BModelLexer;	            // Call its vocabulary "BModel"

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
	String module = "BModel.g";

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
(	  		model
        |   refinement
		|	/* Empty sources files are *not* allowed.  */
        {						
			System.err.println ( "ABParser Warning : Empty source file!" );
			errors.WSyntaxic   ( module, "The file is empty" );
		}
)
;


// In MODEL :
model		:
        "MODEL"^   paramName
        (	
            sets
        |	constants
        |	properties
        |	variables
        |	invariant
        |   observables
        |   observations
        |	assertions
        |   modalities
        |	initialisation_mch
        |	events
        )*
        "END"! 
        EOF!
;

refinement		:
        "REFINEMENT"^   paramName
        (	
            refines
        |   sets
        |	constants
        |	properties
        |	variables
        |	invariant
        |   observables
        |   observations
        |   variant
        |	assertions
        |   modalities
        |	initialisation_ref
        |	events
        )*
        "END"! 
        EOF!
;

observables :
        "OBSERVABLES"^
            listIdentifier
;

observations :
        "OBSERVATIONS"^
            t1:expression
{
	previous_gop.putI(#t1);	// We memorize the node Invariant for GOP
}
;

events	:
        "EVENTS"^ 
            listEvent
	;


listEvent 	:
        event
        (   B_SEMICOLON^  
            event
        )*
    ;

event	:
        nameRenamed
            c:B_EQUAL^ 
{
	#c.setType(OP_DEF);
} 
        substitution_event
	;

substitution_event :
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
        |  	establish
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
				expression
	;

event_list :
        B_IDENTIFIER (b:"OR"^ B_IDENTIFIER)*
{
	#b.setType(B_OR_EVENT);
}
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
				("POST"
					predicate
                       {#rr.setType(B_BEGIN_POST);}
				)?
				"END"!
		;

protected
substitution_block_ref	:
				rr:"BEGIN"^
					parallele_ref
				("POST"!
					predicate
                        {#rr.setType(B_BEGIN_POST);}
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
                       {#rr.setType(B_PRE_POST);}
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
                       {#rr.setType(B_PRE_POST);}
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
                       {#rr.setType(B_SELECT_POST);}
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


dummy :
            B_OR_EVENT
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


class BModelLexer extends BLexer;
options 
{
        importVocab             = BModelLexer   ;
        exportVocab		        = BModel	;		    // Call its vocabulary "BModel" 

        caseSensitive		    =	true	;		    // In B, the case is signifiant
        caseSensitiveLiterals	=	true	;		    // In B, the case is signifiant

        testLiterals		    =	true	;		    // automatically test for literals
    	k			            =	9	    ;           // k characters of lookahead
}

//Introduce some behaviours....

{String module = "BModel.g";

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
