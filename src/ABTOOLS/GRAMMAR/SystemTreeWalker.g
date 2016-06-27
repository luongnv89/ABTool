
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jl.boulanger@wanadoo.fr
// 	File		:	SystemTreeWalker.g
//	Date		:	Creation 10/09/2001
//	Descripton	:	Tree Walker for Another's B Parser written in ANTLR
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//	September 	2001  	v 0.1 
//		-Creation
//
//	May	  	    2002	v 0.1.1
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//	May	  	    2003	v 0.2
//      - Retrieve all explicit token B_*
//      - Pretty Printing
//  April       2004    v 0.3
//      - Packaging

//
//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//  * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
//  **/
header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------

// A verifier ...

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// ours packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.PRINTING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class SystemTreeWalker extends TreeWalker;
options
{
	    importVocab 	= BSystem;
        buildAST 	    = false;
        ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
        codeGenMakeSwitchThreshold = 3;
        codeGenBitsetTestThreshold = 4;

        k = 1;
}

// Indtroduce some behaviours....

{

// Tabulation Management
	private static INDEX index = new INDEX();

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

// For enabling or disabling the debug MODE
	private static DEBUG debug ;

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}


// Section for selecting the target ASCII/XML/HTML or 
// more interesting ADA/C

	public 		int ASCII 		= 0;
	public 		int XML			= 1;
	public		int HTML		= 2;
	public		int TEX			= 3;

	public		int ADA			= 10;
	public		int C			= 11;

	protected	int currenttarget	= ASCII;

	// Le printer courant
	public		Writer 	file;
	private 	OUT 	out ;

	public void	selectTargetFile (Writer newfile)
	{
		file = newfile;
	}

	public void	selectTargetTex()
	{
		currenttarget = TEX;
		out           = new OutTEX();
	}

	public void	selectTarget(int target )
	{
		currenttarget = target;

// Contrainte de conception
// JAVA n'est pas fortiche avec le switch, on ne peut pas mettre de chaine 
// de caractere dans le switch et le case doit etre valuee

		if (target == ASCII) 
			out = new OutASCII();
		else if (target == XML)
            out = new OUT();
        else if (target == TEX)
            out = new OutTEX();
        else
		{
			errors.Internal		(	"TreeWalker.g",
                                    "la cible " + target + " n'existe pas (fct:selectTarget)"
						);
		}
	}

	public String	currentTarget ()
	{

// Contrainte de conception
// JAVA n'est pas fortiche avec le switch, on ne peut pas mettre de chaine 
// de caractere dans le switch et le case doit etre value.

		if (currenttarget == ASCII)
			return ("ASCII");
		else if (currenttarget == XML)
			return ("XML");
		else if (currenttarget == TEX)
			return ("TEX");
		else
		{
			return (null);
		}
	}

// Section for printing 
	
	protected	StringBuffer myBuffer;

    	public String toString()
    	{
		return myBuffer.toString();
	}
 
	void initializeString()
	{
		myBuffer = new StringBuffer();
	}

	void finalizeString()
	{
	
		// au cas ou ....
	}

	void printToString(String st)
	{
	 debug.print(st);

	 if (st.compareTo("")!=0)
	 {
// 		myBuffer.append(index.toString());
	   	myBuffer.append(st);
		myBuffer.append(index.getOne());
	 }
	 else
 		myBuffer.append(index.toString());
 	}

	void printToStringln()
	{
		myBuffer.append("\n"+index.toString());
	}

	void printToStringln(String st)
	{
	 debug.println(st);

	 if (st.compareTo("")!=0)
	 {
      	 	myBuffer.append(st+"\n");
		myBuffer.append(index.toString());
	 }
	 else
	 {
		myBuffer.append("\n");
 		myBuffer.append(index.toString());
	 }
	}

	void printToString(MyNode node)
	{
	 debug.print(node.toString());

	 if (node==null)
	 {
	     errors.Internal("TreeWalker.g", " Treewalker PB it's a null node");
	 }
	 else 
	 {
	     myBuffer.append(node.getText());
	 }
	}

	void printToStringln(MyNode node)
	{
	 debug.println(node.toString());

	 if (node==null)
	 {
	     errors.Internal("TreeWalker.g", " Treewalker PB it's a null node");
	 }
	 else 
	 {
         myBuffer.append(node.getText()+"\n");
	     myBuffer.append(index.toString());
	 }
 	}

}

// Grammar become HERE
// -------------------

/** 
 *  Root Rule
 **/
composant 	:
{
	initializeString();
	index.init();
}
				(
		 			machine
				|	refinement
				|	implementation
				)
{
	finalizeString();
}
		;

/**
 * THREE type of B composants simple name or with one or more parameters
 **/

machine         :	#(tt:"SYSTEM"
{
	index.Add();
	printToStringln(out.Clause(tt.getText()));
}
                		paramName
{
	printToStringln("");
}
				clauses
{
	printToString(out.Clause("END"));
}
                	)
                ;

refinement      :	#(tt:"REFINEMENT"
{
	index.Add();
	printToStringln(out.Clause(tt.getText()));
}
                		paramName
{
	printToStringln("");
}
             			clauses
{
	printToString(out.Clause("END"));
}
 			)
                ;

implementation  :	#(tt:"IMPLEMENTATION"                   
{
	index.Add();
	printToStringln(out.Clause(tt.getText()));
}
                		paramName                       
{
	printToStringln("");
}
                		clauses
{
	printToString(out.Clause("END"));
}
          		)
                ;



clauses		:		
    (clause)*
    //          clause clauses
	//		| 	clause
		;

clause	: 
                    refines 
            |      	constraints
			|      	link
            |	    sets
            |      	values
            |      	constants
            |      	properties
            |      	variables
            |      	cvariables
            |      	invariant
            |      	assertions
            |      	definitions
            |      	initialisation
            |	    events
            |   	modalities
            |   	dynamics
            |   	variant
;

cvariables	:	
            #(tt:"CONCRETE_VARIABLES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
			|
			#(t2:"HIDDEN_VARIABLES"
{
	printToStringln(out.Clause(t2.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
		;

events	:
            #(tt:"EVENTS"
{
	printToStringln(out.Clause(tt.getText()));
}
		 	listOperation
            )
;

modalities :
            #(tt:"MODALITIES" 
{
	printToStringln(out.Clause(tt.getText()));
}
				new_select
			)
;

new_select : 
			#(tt:"SELECT" 
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate 
{
	printToStringln(out.Clause("LEADSTO"));
}
				predicate 
{
	printToStringln(out.Clause("WHILE"));
}
				event_list
{
	printToStringln(out.Clause("INVARIANT"));
}
				predicate 
{
	printToStringln(out.Clause("VARIANT"));
}
				predicate 			// JRA said : "natural number expression"
{
	printToStringln(out.Clause("END"));
}
    )
    ;


event_list : 
			#(tt:"OR"
                event_list 
{
	printToStringln(out.Clause(tt.getText()));
}
                event_list
            )
        |   B_IDENTIFIER
;

dynamics:
            #(tt:"DYNAMICS"
{
	printToStringln(out.Clause(tt.getText()));
}
			 	predicate_with_prime
			)
;

predicate_with_prime :	
        predicate
;


variant 	:
	        #(tt:"VARIANT"
{
	printToStringln(out.KeyWord(tt.getText()));
}
				predicate
			)
;

// END Of FILE SystemTreeWalker.g
















