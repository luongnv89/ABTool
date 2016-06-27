
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	TreeWalker.g
//	Descripton	:	Tree Walker for Another's B Parser written in ANTLR
//	
//	Copyright 2000-2011 Boulanger Jean-Louis
//


// Releases
//	March 	2000	v0.0 	Creation
//
//	April 	2000	v0.1	Add Strng construction for out
//				with multiples generations cases
//
//	May   	2000	v0.2	Change the form of the TREE
//				Many simplifications 
//				Prise encompte des nouveaux tokens de desambiguites
//				Add debug information
//
//	June 	2000	v0.2.1	Add Record in predicate and expression
//				Add Constructor in SETS definition
//				Add clause sets in implementation
//
//	July 	2000	v0.2.2  Add negative number in a_set_value rule
//
//	September 2000	v0.2.3	Add printing for the option TEX/ASCII
//
//	January	  2001  v0.2.4	Add "Struct"
//
//	April     2001  v 0.2.5 Correct somes tabulation.
//				Add the ErrorMessage control
//
//	May	      2001  v 0.2.6 Correct somes tabulations in ASCII form
//
//	August	  2001  v 0.2.7	Correct somes tabulations in ASCII form
//				Introduce the typing information in Text decompilation
//				Intrdouce the re-init of tabulation 
//	September 2001  v 0.2.8 Correct somes tabulations in ASCII form
//				Correct the pretty printing
//
//	April	  2002	v 0.2.9	
//		        - We add the "not" operator
//		        - Introduce some pretty print
//
//	May	      2002	v 0.3.0
//		        - Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  December  2002  v 0.3.1
//              - Pretty print this file 
//              - Retry THEN in SELECT structure
//
//  January   2003  v 0.3.2
//              - Pretty print this file
//
//  July      2003  v 0.3.3
//              - Pretty print this file
//              - change list_func_call rule
//              - correct a BUG in a_func_call rule
//
//  August    2003  v 0.3.4
//              - Pretty print this file
//              - Modify rule "afc" for introduce "B_PARENT".
//                This case appeared during normalization
//              - Add the local variable module
//              - Introduce SUBST_DEF and EXP_DEF Tolen for help treewalking.
// 
//  April     2004  v 0.3.5
//              - Packaging
//  September 2004  v 0.3.5.1
//              - correct some commentary
//  October   2004  v 1.2
//              - Add POST
//  November  2004  v 1.3
//              - split Expression and B langage

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//  * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
//  **/

header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------

{
// usefull packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// our packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
    import ABTOOLS.PRINTING.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class TreeWalker extends ExpressionTreeWalker;
options
{
	importVocab 	= BPrime;
    	buildAST 	= false;
    	ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
    	codeGenMakeSwitchThreshold = 3;
    	codeGenBitsetTestThreshold = 4;

	k = 1;
}

// Indtroduce some behaviours....

{
	String module = "TreeWalker.g";

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


// Section for selecting the target ASCII/XML/HTML/TEX 
	public 		int ASCII 		= 0;
	public 		int XML			= 1;
	public		int HTML		= 2;
	public		int TEX			= 3;

	public		int ADA			= 10;
	public		int C			= 11;
    	public      	int JAVA        	= 12;

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
			errors.Internal		(	module,
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
	     errors.Internal(module, " Treewalker PB it's a null node");
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
	     errors.Internal(module, " Treewalker PB it's a null node");
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

machine         :	
        #(tt:"MACHINE"
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

refinement      :	
    #(tt:"REFINEMENT"
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

implementation  :
	#(tt:"IMPLEMENTATION"
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

clauses	:
	( clause )*
;

clause	:
                    	refines 
            |       	constraints
            |      	link
            |	    	sets
            |      	values
            |      	constants
            |      	properties
            |      	variables
            |      	invariant
            |      	assertions
            |      	definitions
            |      	initialisation
	    |      	operations  
;

/**
 *  For all Name with parameters
 **/

paramName	:	
            #(B_LPAREN 
				name:B_IDENTIFIER 		
{
	printToString(name);
	printToString(out.Separator("("));
} 
				listTypedIdentifier
{
	printToString(out.Separator(")"));
}
			)			
			|
				name1:B_IDENTIFIER
{
	printToString(name1);
}
;

listTypedIdentifier:
            #(B_COMMA
				listTypedIdentifier	
{
	printToStringln("");
	printToString(out.Separator(","));
}
			 	listTypedIdentifier
			)	
			|	typedIdentifier
;

typedIdentifier	:
			#(B_INSET
				nameRenamed
{
	printToString(out.Separator(":"));
}
				predicate 
			)	
			|	nn:nameRenamed
{
	printToString(" /* : "+nn.getBType().toString()+" */");
}
    ;

constraints	:
            #(tt:"CONSTRAINTS"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;

link		:	uses 
			|	includes
			|	sees
			|	extendeds
			|	promotes
			|	imports
;

extendeds	:	
            #(tt:"EXTENDS"
{
	printToStringln(out.Clause(tt.getText()));
}
				listInstanciation
{
	printToStringln("");
}
			)
;

uses		:
            #(tt:"USES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listNames			
{
	printToStringln("");
}
			)
;

includes	:	
            #(tt:"INCLUDES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listInstanciation
{
	printToStringln("");
}
			)
;

sees		:
	        #(tt:"SEES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listNames
{
	printToStringln("");
}
			)
;

listNames	:	
            #(B_COMMA 
				listNames
{
	index.Retract();
	printToStringln("");
	printToString(out.Separator(","));
	index.Add();
} 
				nameRenamed
			)
        |	nameRenamed
;

listInstanciation:	
            #(B_COMMA 
				listInstanciation
{
	printToStringln(out.Separator(""));
	index.Retract();
	printToString(out.Separator(","));
	index.Add();
}
				paramRenameValuation
			)
        |	paramRenameValuation
;

paramRenameValuation :	
            #(t3:B_LPAREN 
				paramRenameValuation
{
	printToString(out.Separator("("));
}
				list_New_Predicate
{
	printToString(out.Separator(")"));
}
			)
			|	nameRenamed
;

imports		:	
            #(tt:"IMPORTS"
{
	printToStringln(out.Clause(tt.getText()));
}
				  listInstanciation
{
	printToStringln("");
}
			)
;

promotes	:	
            #(tt:"PROMOTES"
{
	printToStringln(out.Clause(tt.getText()));
}
			  	listNames
{
	printToStringln("");
}
			)
;

refines		:	
            #(tt:"REFINES"				
{
	printToStringln(out.Clause(tt.getText()));
}
				 name:B_IDENTIFIER
{
	printToStringln(name);
}
			)
;

constants	:	
            #(t1:"CONSTANTS"
{
	printToStringln(out.Clause(t1.getText()));
}
				listTypedIdentifier		
{
	printToStringln("");
}
			)
        |
			#(t2:"CONCRETE_CONSTANTS"
{
	printToStringln(out.Clause(t2.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t3:"VISIBLE_CONSTANTS"
{
	printToStringln(out.Clause(t3.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t4:"ABSTRACT_CONSTANTS"
{
	printToStringln(out.Clause(t4.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t5:"HIDDEN_CONSTANTS"
{
	printToStringln(out.Clause(t5.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
;

sets		:	
            #(tt:"SETS"
{
	printToStringln(out.Clause(tt.getText()));
}
				  sets_declaration
{
	printToStringln("");
}
			)
;


// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:	
            #(B_SEMICOLON  
				sets_declaration
{
	printToString("\n"+out.Separator(";"));
}  
				sets_declaration
			)	
        |
            #(B_COMMA
				sets_declaration 
{
	printToString("\n"+out.Separator(","));
}  
				sets_declaration
			)
        |	set_declaration		
		;


// 12/2001 ajout impression du type de chaque ensemble
//
set_declaration :
	        #(B_EQUAL 
				  set:B_IDENTIFIER
{
	printToString(set);
	printToString(" /* : "+set.getBType().toString()+" */");
	printToString(out.Separator("="));
}
				  valuation_set
			)
			|	
				name:B_IDENTIFIER
{
	printToString(name);
	printToString(" /* : "+name.getBType().toString()+" */");
}
    ;



valuation_set	:	
     	#(B_CURLYOPEN
{
	printToString(out.Separator("{"));
}
				  list_couple
{
	printToString(out.Separator("}"));
}
			)
|     	#(t100:B_RANGE

		a_set_value
{
	printToString(out.KeyWord(t100.getText()));
}
		a_set_value
	)
|	is_record
|	#(t2:B_MULT 
		valuation_set
{
	printToString(out.KeyWord(t2.getText()));
}
		valuation_set
)
|	#(t3:B_ADD 
		valuation_set
{
	printToString(out.KeyWord(t3.getText()));
}
		valuation_set
	)
|	#(t4:B_MINUS 
		valuation_set
{
	printToString(out.KeyWord(t4.getText()));
}
		valuation_set
	)
        |	t5:B_IDENTIFIER
{
	printToString(t5);
}
        |	basic_sets
    ;


list_couple	: 	
            #(tt:B_COMMA 
				list_couple
{
	printToString(out.Separator(tt.getText()));
}
				couple 
			)
        |	couple
;

couple		:	
            #(t1:B_MAPLET 
				a_set_value
{
	printToString(out.Separator(t1.getText()));
}
				a_set_value
			)
        |
			#(t2:B_LPAREN
{
	printToString(out.Separator(t2.getText()));
}
				parent_couple
{
	printToString(out.Separator(")"));
}
			)
        |	a_set_value
;

parent_couple	:
	        #(t1:B_MAPLET 
				a_set_value
{
	printToString(out.Separator(t1.getText()));
}
				a_set_value
			)
			|
			#(t2:B_COMMA  
				a_set_value
{
	printToString(out.Separator(t2.getText()));
}
				a_set_value
			)
        |	a_set_value
;

a_set_value	:
            name:B_IDENTIFIER
{
	printToString(name);
	printToString(" /* : "+name.getBType().toString()+" */");
}
        |	
	#(mi:B_MINUS 	
		val1:B_NUMBER
{
	printToString(out.Separator(mi.getText()));
	printToString(val1);
}
			)
        |	val:B_NUMBER
{
	printToString(val);
}
        |	tr:"TRUE"
{
	printToString(out.KeyWord(tr.getText()));
}
        |	fa:"FALSE"
{
	printToString(out.KeyWord(fa.getText()));
}
    ;


values		:	
            #(tt:"VALUES"
{
	printToStringln(out.Clause(tt.getText()));
}
			 	list_valuation
{
	printToStringln("");
}
			)
;

list_valuation	:
	        #(tt:B_SEMICOLON 
				list_valuation 
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
}
				set_valuation
			)
        |	set_valuation
;

set_valuation	:	
#(tt:B_EQUAL 
	name:B_IDENTIFIER
{
	printToString(name);
	printToString(out.Separator(tt.getText()));
}
	new_set_or_constant_valuation
)
;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

new_set_or_constant_valuation:
	predicate
;

set_interval_value:	
#(tt:B_EQUAL 
	a:B_IDENTIFIER
{
	printToString(a);
	printToString(" /* : "+a.getBType().toString()+" */");
	printToString(out.Separator(tt.getText()));
}
	interval_declaration
)
;

interval_declaration:
#(tt:B_RANGE 
	predicate
{
	printToString(out.Separator(tt.getText()));
}
	predicate
)
;

set_rename_value:
#(tt:B_EQUAL 
	name1:B_IDENTIFIER
{
	printToStringln(name1);
	printToString(out.Separator(tt.getText()));
}
	name2:B_IDENTIFIER
{
	printToStringln(name2);
}
)

;

properties	:
	        #(tt:"PROPERTIES"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;

variables	:
	        #(t0:"VARIABLES"
{
	printToStringln(out.Clause(t0.getText()));
} 
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t1:"ABSTRACT_VARIABLES"
{
	printToStringln(out.Clause(t1.getText()));
} 
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t2:"VISIBLE_VARIABLES"
{
	printToStringln(out.Clause(t2.getText()));
} 
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t4:"CONCRETE_VARIABLES"
{
	printToStringln(out.Clause(t4.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
        |
			#(t5:"HIDDEN_VARIABLES"
{
	printToStringln(out.Clause(t5.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
}
			)
;

invariant	:
	        #(tt:"INVARIANT"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;



definitions	:
	        #(tt:"DEFINITIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				list_def
{
	printToStringln("");
}
			)
;

list_def	:
	        #(tt:LIST_DEF
				list_def
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
} 
				definition
			)
        |	definition
;

/**
 *  Attention deux cas
 *     - Une definition
 *     - un acces a un fichier de definition
 **/

definition	:
	        #(t1:B_DOUBLE_EQUAL 
				paramName
{
	printToString(out.Separator(t1.getText()));
}
				formalText
			)
        |	t3:B_ASTRING
{
	printToStringln("\""+t3+"\"");
}
    ;

formalText	:
        #(EXP_DEF   predicate)
    |	#(SUBST_DEF instruction)
//			|	operation
    ;

assertions	:
            #(tt:"ASSERTIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				list_assertions
{
	printToStringln("");
}
			)
;

list_assertions	:
	        #(tt:B_SEMICOLON  
				list_assertions
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
}
				list_assertions
			)
        |	predicate
;

initialisation	:
	        #(tt:"INITIALISATION"
{
	printToStringln(out.Clause(tt.getText()));
}
				  instruction
{
	printToStringln("");
}
			)
;

operations	:
	        #(tt:"OPERATIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				listOperation
{
	printToStringln("");
}
			)
;

listOperation	:
	        #(tt:B_SEMICOLON 
				listOperation
{
	index.Retract();
	printToStringln("");
	printToString(out.Separator(tt.getText())+index.getOne());
	index.Add();
}
				operation
			)
        |	operation
;

operation	:
	        #(tt:OP_DEF
				operationHeader
{
	printToStringln(out.Separator(tt.getText()));
	index.Add();
}
				instruction
{
	index.Retract();
	printToStringln("");
}
			)
;

operationHeader	:
	        #(tt:B_OUT
				listTypedIdentifier
{
	printToString(out.Separator(tt.getText()));
}
				paramName 
// au cas ou			listTypedIdentifier 		{printToString(out.Separator(tt.getText()));}
			)		
        |	paramName
;

/**
 * The Generalised Substitution Language
 **/

instruction	:
	        #(t1:PARALLEL 	
				instruction 			
{
	printToStringln("");
	printToString(out.Separator(t1.getText()));
}
			 	instruction
			)
        |
			#(t2:SEQUENTIAL	
				instruction
{
	printToStringln("");
	printToString(out.Separator(t2.getText()));
}
				instruction
			)
        |
			#(t3:"skip"
{
	printToString(out.KeyWord(t3.getText()));
}
			)
        |
			#(t4:"BEGIN"
{
//	index.Add();
	printToStringln(out.KeyWord(t4.getText()));
}
				instruction
{
//	index.Retract();
	printToStringln("");
	printToString(out.KeyWord("END"));
} 
			)
        |
			#(t40:B_BEGIN_POST
{
//	index.Add();
	printToStringln(out.KeyWord(t40.getText()));
}
                instruction
{
	printToStringln("");
	printToString(out.KeyWord("POST"));
} 
                predicate
{
//	index.Retract();
	printToStringln("");
	printToString(out.KeyWord("END"));
} 
			)        |
			#(t5:"PRE"
{
	index.Add();
	printToStringln(out.KeyWord(t5.getText()));
	index.Retract();
}
				predicate
{
	printToStringln();
//	index.Add();
	printToStringln(out.KeyWord("THEN"));
//	index.Retract();
} 
				instruction
{
	printToStringln("");
	printToStringln(out.KeyWord("END"));
}
			)
        |
			#(t6:"ASSERT" 				
{
    printToStringln(out.KeyWord(t6.getText()));
}
				predicate
{
    printToStringln("");
	printToStringln(out.KeyWord("THEN"));
}
				instruction
{
    printToStringln("");
	printToString(out.KeyWord("END"));
}
			)
        |	
			#(t7:"IF"
{
    printToStringln(out.KeyWord(t7.getText()));
}
 				predicate
				branche_then
				(branche_elsif)*
				(branche_else)?
{
    printToStringln("");
	printToString(out.KeyWord("END"));
}
            )
        |
			#(t8:"CHOICE"
{
	printToStringln(out.KeyWord(t8.getText()));
	index.Retract();
}
				list_or
{
	printToStringln();
	printToString(out.KeyWord("END"));
}
			)
        |
			#(t9:"CASE"
{
    printToStringln(out.KeyWord(t9.getText()));
}
				predicate 
{
    printToStringln("");
	printToStringln(out.KeyWord("OF"));
}
				branche_either
				(branche_or)*
				(branche_else)?
{
    printToStringln("");
	printToStringln(out.KeyWord("END"));
    printToStringln("");
	printToStringln(out.KeyWord("END"));
}
			)
        |
			#("ANY" 
{
	printToStringln(out.KeyWord("ANY"));
	index.Retract();
}
				listTypedIdentifier 
{
	printToStringln();
	index.Add();
	printToStringln(out.KeyWord("WHERE"));
	index.Retract();
}
				predicate
{
	printToStringln();
	index.Add();
	printToStringln(out.KeyWord("THEN"));
	index.Retract();}
				instruction
{
	printToStringln();
	printToStringln(out.KeyWord("END"));
}
			)
        |
			#(t11:"LET"
{
	printToStringln(out.KeyWord(t11.getText()));
}
				listTypedIdentifier 
{
	printToStringln("");
	printToStringln(out.KeyWord("BE"));
}
				list_equal 
{
	printToStringln("");
	printToStringln(out.KeyWord("IN"));
}
				instruction
{
	printToStringln("");
	printToStringln(out.KeyWord("END"));
}
            )
        |
			#(t12:"SELECT"
{
	printToStringln(out.KeyWord(t12.getText()));
}
				predicate 
{
	printToStringln("");
	printToStringln(out.KeyWord("THEN"));
	index.Add();
}
				instruction
{
	index.Retract();
}
				(branche_when)*
				(branche_else)?
								
{
	printToStringln("");
	printToString(out.KeyWord("END"));
}
			)
        |
			#(t13:"WHILE"
{
	printToStringln(out.KeyWord(t13.getText()));
}
				predicate
{
	printToStringln("");
	printToStringln(out.KeyWord("DO"));
}
				instruction
				variant_or_no
{
	printToStringln("");
	printToString(out.KeyWord("END"));
}
			)
        |
			#(tt:"VAR"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				listTypedIdentifier
{
	printToStringln("");
	printToStringln(out.KeyWord("IN"));
}
				instruction
{
	printToStringln("");
	printToString(out.KeyWord("END"));
}
			)
        |	simple_affect
;

list_or         :
                        #(t1:"OR"
                                list_or                         
{
	printToStringln();
	index.Add();
	printToStringln(out.KeyWord(t1.getText()));
	index.Retract();
}
                                instruction
                        )
                |       instruction
;


branche_when	:
	        #(tt:"WHEN"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				predicate
{
	printToStringln("");
	printToStringln(out.KeyWord("THEN"));
}
				instruction
			)
;

branche_either	:
	            #(tt:"EITHER"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				predicate 
{
	printToStringln("");
	printToStringln(out.KeyWord("THEN"));
}
				instruction 
				)
;

branche_or	:
	        #(tt:"OR"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				predicate
{
	printToStringln("");
	printToStringln(out.KeyWord("THEN"));
}
				instruction
			)
;

branche_then	:	
        #(tt:"THEN"			
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
	index.Add();
}
				instruction
{
	index.Retract();
}
			)
;

branche_else	:
	        #(tt:"ELSE"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				instruction
			)
;

branche_elsif	:	
            #(t1:"ELSIF"
{
	printToStringln("");
	printToStringln(out.KeyWord(t1.getText()));
}
				predicate
{
	printToStringln("");
	printToStringln(out.KeyWord("THEN"));
}
				instruction
			)
;


variant_or_no	:	
            #(t1:"VARIANT"
				#(t2:"INVARIANT"
{
	printToStringln("");
	printToStringln(out.KeyWord(t2.getText()));
}
					predicate
				)
{
	printToStringln("");
	printToStringln(out.KeyWord(t1.getText()));
}
				predicate 
			)
        |
			#(t3:"INVARIANT"
				#(t4:"VARIANT"
{
	printToStringln("");
	printToStringln(out.KeyWord(t4.getText()));
}
					predicate 
				)
{
	printToStringln("");
	printToStringln(out.KeyWord(t3.getText()));
}
				predicate
			)
;

list_equal	:
 	        #(tt:B_AND 	
				list_equal
{
	printToStringln("");
	printToString(out.Separator("&"));
}
				an_equal
			)
        |	an_equal	
;


an_equal	:	
            #(tt:B_EQUAL 	
				n1:B_IDENTIFIER
{
	printToString(n1);
	printToString(out.Separator("="));
}
		 		predicate
			)
;

// afin de prendre en compte 
//	soit f (x,y,z)
//	soit f (x) [z]
//	soit f~(x) [z]
//	soit f (x)~

func_call	:
 	        #(t1:B_TILDE
		 		func_call
{
	printToString(out.Separator("~"));
}
 			)
        |
			#(t2:APPLY_TO
				func_call
{
	printToString(out.Separator("["));
} 
				list_New_Predicate
{
	printToString(out.Separator("]"));
}
			)
        |
		 	#(t3:B_LPAREN
		 		func_call
{
	printToString(out.Separator("("));
}
				list_New_Predicate
{
	printToString(out.Separator(")"));
}
			)
        |
			#(t4:B_QUOTEIDENT
				func_call 			
{
	printToString("'");
}
				func_call
			)
        |
			nameRenamed 
;

func_param	:	
        list_New_Predicate
;

list_identifier	:
 	        #(tt:B_COMMA
		 		list_identifier
{
	printToString(out.Separator(tt.getText()));
}
				n1:B_IDENTIFIER
{
	printToString(n1);
}
			)
			|	n2:B_IDENTIFIER
{
	printToString(n2);
}
    ;


a_func_call	:
    afc
;

 afc		:
	        #(FUNC_CALL_PARAM
			 	afc
{
	printToString(out.Separator("("));
}
			 	listPredicate
{
	printToString(out.Separator(")"));
}
		 	)
        |
			#(t1:B_QUOTEIDENT
				afc
{
	printToString(out.Separator(t1.getText()));
}
				afc
			)
        |
            #(B_LPAREN
			 	afc
{
	printToString(out.Separator("("));
}
                listPredicate
{
	printToString(out.Separator(")"));
}
            )
        |	nameRenamed
;

list_func_call	:
	        #(tt:B_COMMA
		  		list_func_call
{
	printToString(out.Separator(tt.getText()));
}
			 	list_func_call
			)
        |	a_func_call
;

// Quatre cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a,b,c :( P )
//	soit	a,b,c :: P
//	soit	P.R (xx,yy)
//	soit 	P.R

simple_affect	:	
            #(t1:B_SIMPLESUBST 	
				list_func_call
{
	printToString(out.Separator(t1.getText()));
}
				predicate
			)
        |
			#(t2:B_OUT	 	
				list_func_call
{
	printToString(out.Separator(t2.getText()));
}
				func_call
			)
        |
			#(INSET
				list_func_call
{
	printToString(out.Separator(":("));
}
				predicate
{
	printToString(out.Separator(")"));
}
			)
        |
			#(t4:B_BECOME_ELEM
			 	list_func_call
{
	printToString(out.Separator(t4.getText()));
}
				predicate
			)
        |
			a_func_call
		;

// END Of FILE TreeWalker.g
















