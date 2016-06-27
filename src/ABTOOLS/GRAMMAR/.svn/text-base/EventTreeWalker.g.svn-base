
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jl.boulanger@wanadoo.fr
// 	File		:	EventTreeWalker.g
//	Descripton	:	Tree Walker for Another's B Parser written in ANTLR
//				    for B Event
//	
//	Copyright 2000-2009 Boulanger Jean-Louis
//

// Releases
//	April	    2002	v 0.1	
//      - Creation
//
//	May	        2002	v 0.2	
//		- A usefull version  
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  December    2002    v 0.2.1
//      - correct some bug (post)
//
//  May         2003    v 0.2.2
//      - Retrieve all explicit token B_*
//      - Pretty-Printing
//      - Use ANTLR 2.7.2
//  April       2004    v 0.3
//      - Packaging

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

{
 /**
  * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
  **/

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// our packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.PRINTING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class EventTreeWalker extends TreeWalker;
options
{
	importVocab 	= BEvent;
    buildAST 	= false;
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
			errors.Internal		(	"EventTreeWalker.g",
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
//		myBuffer.append(index.getOne());
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
	     errors.Internal("EventTreeWalker.g", " Treewalker PB it's a null node");
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
	     errors.Internal("EventTreeWalker.g", " Treewalker PB it's a null node");
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
refinement         :	#(tt:"REFINEMENT"
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

clauses		:	(clause)*
		;

clause		:		    refines
                |      	constraints
                |      	link
                |      	sets 
                |      	constants
                |      	properties
       			|      	variables
                |      	invariant
                |       variant
       			|      	assertions
     			|      	definitions
         		|      	initialisation
                |      	operations
                |	    events
                |	    modalities
		;


variant		:	#(tt:"VARIANT"
{
	printToStringln(out.Clause(tt.getText()));
}
				a_variant
{
	printToStringln("");
}
			)
		;

a_variant	:	predicate
		;

events		:	#(tt:"EVENTS" 
{
	printToStringln(out.Clause(tt.getText()));
}
				listEvent
{
	printToStringln("");
}
			)
		;

listEvent	:	#(tt:B_SEMICOLON 
				listEvent
{
	index.Retract();
	printToStringln("");
	printToString(out.Separator(tt.getText())+index.getOne());
	index.Add();
}
				event
			)
			|	event
		;

event		:	#(tt:OP_DEF
				eventHeader
{
	printToStringln(out.Separator(tt.getText()));
	index.Add();
}
				substitution_event
{
	index.Retract();
	printToStringln("");
}
			)
		;

eventHeader 	:	
            #(tt:"ref"
				nameRenamed 
{
	printToString(out.Separator(tt.getText()));
}
				listNameRenamed
			)
			|	nameRenamed
		;

// Ce peut etre une liste d'evenement qui raffine un evenement.
listNameRenamed	:	
            #(tt:B_COMMA
				listNameRenamed
{
	printToString(out.Separator(tt.getText()));
}
				listNameRenamed
			)
			|	nameRenamed
		;

substitution_event :		
	            substitution_block_postcondition
			|	substitution_precondition_postcondition
			|	substitution_selection_postcondition
			|	substitution_unbounded_choice_postcondition
            |   instruction
		;


modalities :		#(tt:"MODALITIES"
{
	printToStringln(out.Clause(tt.getText()));
	index.Add();
}
				modality
{
	index.Retract();
	printToStringln("");
}
			)
	;

modality :		#(t1:"ANY"
{
	printToStringln(out.Clause(t1.getText()));
}
				listTypedIdentifier 
				predicate
				event_list
				(	establish
				|	maintain
				)
{
	printToStringln("");
	printToStringln(out.Clause("END"));
}
			)
		|
			#(t2:"BEGIN"
{
	printToStringln(out.Clause(t2.getText()));
}
				event_list
				(	establish
				|	maintain
				)
{
	printToStringln("");
	printToStringln(out.Clause("END"));
}
			)
		|
			#(t3:"SELECT"
{
	printToStringln(out.Clause(t3.getText()));
}
				predicate
{
	printToStringln(out.Clause("THEN"));
}
				event_list
				establish
{
	printToStringln("");
	printToStringln(out.Clause("END"));
}
			)
	;


establish :		#(tt:"ESTABLISH"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
	;

maintain :		#(tt:"MAINTAIN"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
				predicate
				a_variant
{
	printToStringln("");
}
			)
	;

event_list :
		#(tt:"ALL"
{
	printToStringln(out.Clause(tt.getText()));
}
        )
		|	listTypedIdentifier 
	;

substitution_block_postcondition	:
				#(B_BEGIN_POST
{
	printToStringln(out.Clause("BEGIN"));
}
					substitution_event
{
	printToStringln(out.Clause("POST"));
}
					predicate
{
	printToStringln(out.Clause("END"));
}
				)
		;

protected
substitution_precondition_postcondition	:
				#(B_PRE_POST
{
	printToStringln(out.Clause("PRE"));
}
					predicate
{
	printToStringln(out.Clause("THEN"));
}
					substitution_event
{
	printToStringln(out.Clause("POST"));
}
					predicate
{
	printToStringln(out.Clause("END"));
}
				)
		;

protected
substitution_selection_postcondition	:
				#(B_SELECT_POST
{
	printToStringln(out.Clause("SELECT"));
}
					predicate
{
	printToStringln(out.Clause("THEN"));
}
					substitution_event
{
	printToStringln(out.Clause("POST"));
}
					predicate
{
	printToStringln(out.Clause("END"));
}
				)
		;

protected
substitution_unbounded_choice_postcondition	:
				#(B_ANY_POST
{
	printToStringln(out.Clause("ANY"));
}
					listTypedIdentifier
{
	printToStringln(out.Clause("WHERE"));
}
					predicate
{
	printToStringln(out.Clause("THEN"));
}
					substitution_event
{
	printToStringln(out.Clause("POST"));
}
					predicate
{
	printToStringln(out.Clause("END"));
}
				)
		;


// END Of FILE EventTreeWalker.g
















