
// 
// 	Author		:	Boulanger Jean-Louis	(jean.louis.boulanger@wanadoo.fr)
// 	File		:	BSystem.g
//	Date		:	Creation 18/09/2001
//	Descripton	:	Another B Parser written in ANTLR
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//	September 18 2000	v 0.1 	Creation
//		Create Bsystem by inheritance of B
//
//	April	    2002		v 0.2
//		- We introduce semantics controls RU1 RA1 (see technical documentation)
//
//	May	        2002		v 0.2.1
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  May         2003        v 0.2.2
//      - Retrieve all explicit token B_*
//
// February     2004        v 0.2.3
//      - Introduce errors handling in lexer

//  April       2004        v 0.3
//      - Restructuring and Packaging

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


// Define a Parser, calling it BSystemParser
// -----------------------------------------
class BSystemParser extends BParser;
options 
{
	    importVocab			= B;
    	exportVocab			= BSystem;	            // Call its vocabulary "BSystem" 

    	k=3 ;						                // k tokens lookahead

    	codeGenMakeSwitchThreshold 	= 2;  		    // Some optimizations
    	codeGenBitsetTestThreshold 	= 3;

    	buildAST 		    	= true;
        ASTLabelType 			= "MyNode";
} 


// Indtroduce some behaviours....

{
	String module = "BSystem.g";



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
		| 	implementation
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
machine		:   "SYSTEM"^
                    paramName
				(	constraints
				 |	mchlink
				 |	sets
				 |	constants
				 |	aconstants
				 |	properties
				 |	variables
				 |	cvariables
				 |	invariant
				 |	dynamics				// NEW 
				 |	assertions
				 |	definitions_mch
				 |	initialisation_mch
				 |	modalities				// NEW
                 |  events_mch				// NEW
                )*
			"END"! 
			EOF!
		;

events_mch	:   "EVENTS"^
                    listOperationMch
    ;

modalities :    "MODALITIES"^
                    new_select
    ;


// BJL
// For VARIANT some evolution for futur

new_select :
			"SELECT"^ 
				predicate 
			B_LEADSTO! 
				predicate 
			"WHILE"! 
				event_list 
			"INVARIANT" 
				predicate 
			"VARIANT"
				predicate 			// JRA said : "natural number expression"
			"END"
;

event_list :
			B_IDENTIFIER ("OR"^ B_IDENTIFIER)*
;


// This clause is not easy beacuse JRA use for each variable introduce in VARIABLES clause 
// the possibility to access at the after value by the operator ' 
// we can write x<x'

dynamics	:   "DYNAMICS"^ 
                    predicate_with_prime
;

refinement	:       "REFINEMENT"^
                        paramName 
					(	refines
					 |	constraints
					 |	reflink
					 |	sets 
					 |	aconstants
					 |	constants
					 |	properties
					 |	variables
					 |	cvariables
					 |	invariant
					 |	variant					// NEW 
					 |	assertions
					 |	definitions_ref
					 |	initialisation_ref
                     |  events_ref				// NEW
					)*
				"END"!
				EOF!
    ;

events_ref :    "EVENTS"^
                    listOperationRef
    ;

variant :       "VARIANT"^
                    predicate
    ;

implementation	:   "IMPLEMENTATION"^
                        paramName
					(	refines 
					 |	implink
					 |	values
					 |	sets			// Et oui il y a des ensembles
					 |	constants
					 |	properties
					 |	variables
					 |	cvariables
					 |	invariant
					 |	assertions
					 |	definitions_imp
					 |	initialisation_imp
                     |  events_imp			// NEW
					)*
				"END"!
				EOF! 	
    ;

events_imp :       "EVENTS"^	listOperationImp
    ;

// This clause is not easy beacuse JRA use for each variable introduce in VARIABLES clause 
// the possibility to access at the after value by the operator ' 
// we can write x<x'
// TO treat when more information.....

predicate_with_prime :
			predicate
    ;

// Attention on en fait peut etre beaucoup
//		( ....)' (.....)
protected
predInvertedParamInvertedQuoted:
			predInvertedParamInverted 
			(
                B_QUOTEIDENT^   predInvertedParamInverted
			)*
//			|
//			predInvertedParamInverted rr:B_QUOTEIDENT^ {#rr.setType(B_PREC);}
		;

dummy 	:
			B_PREC
		;

//My BSystem Lexer 
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

class BSystemLexer extends BLexer;
options 
{
    importVocab             =   B;
    exportVocab		        =	BSystem	;		    // Call its vocabulary "BSystem" 

	caseSensitive		    =	true	;		    // In B, the case is signifiant
	caseSensitiveLiterals	=	true	;		    // In B, the case is signifiant

	testLiterals		    =	true	;		    // automatically test for literals
    k			            =	5	;               // k characters of lookahead
}

//Introduce some behaviours....

{
	protected int 	  tokcolumn   		= 1 ;
	protected int 	  column 	    	= 1 ;
  	protected int 	  tokenNumber  		= 0;
  	protected boolean countingTokens 	= true;

    String module = "BSystem.g";


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

//B_SYSTEM	        : 	"SYSTEM"		;
//B_EVENTS	        : 	"EVENTS"		;
//B_MODALITIES	    :	"MODALITIES"	;
//B_DYNAMICS	    :	"DYNAMICS"		;
B_LEADSTO	        :	"LEADSTO"		;

