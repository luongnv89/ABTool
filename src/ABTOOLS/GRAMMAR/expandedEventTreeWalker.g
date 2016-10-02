header
{
    package ABTOOLS.GRAMMAR;
}
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
}class EventTreeWalker extends TreeParser;

options {
	importVocab= BEvent;
	buildAST= false;
	ASTLabelType= "MyNode";
	codeGenMakeSwitchThreshold= 3;
	codeGenBitsetTestThreshold= 4;
	k= 1;
}

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
composant :{
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

machine :#(tt:"SYSTEM"
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

refinement :#(tt:"REFINEMENT"
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

clauses :(clause)*
		;

clause :refines
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

variant :#(tt:"VARIANT"
{
	printToStringln(out.Clause(tt.getText()));
}
				a_variant
{
	printToStringln("");
}
			)
		;

a_variant :predicate
		;

events :#(tt:"EVENTS" 
{
	printToStringln(out.Clause(tt.getText()));
}
				listEvent
{
	printToStringln("");
}
			)
		;

listEvent :#(tt:B_SEMICOLON 
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

event :#(tt:OP_DEF
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

eventHeader :#(tt:"ref"
				nameRenamed 
{
	printToString(out.Separator(tt.getText()));
}
				listNameRenamed
			)
			|	nameRenamed
		;

listNameRenamed :#(tt:B_COMMA
				listNameRenamed
{
	printToString(out.Separator(tt.getText()));
}
				listNameRenamed
			)
			|	nameRenamed
		;

substitution_event :substitution_block_postcondition
			|	substitution_precondition_postcondition
			|	substitution_selection_postcondition
			|	substitution_unbounded_choice_postcondition
            |   instruction
		;

modalities :#(tt:"MODALITIES"
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

modality :#(t1:"ANY"
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

establish :#(tt:"ESTABLISH"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
	;

maintain :#(tt:"MAINTAIN"
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

event_list :#(tt:"ALL"
{
	printToStringln(out.Clause(tt.getText()));
}
        )
		|	listTypedIdentifier 
	;

substitution_block_postcondition :#(B_BEGIN_POST
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

protected substitution_precondition_postcondition :#(B_PRE_POST
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

protected substitution_selection_postcondition :#(B_SELECT_POST
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

protected substitution_unbounded_choice_postcondition :#(B_ANY_POST
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

// inherited from grammar TreeWalker
implementation :#(tt:"IMPLEMENTATION"
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

// inherited from grammar TreeWalker
paramName :#(B_LPAREN 
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

// inherited from grammar TreeWalker
listTypedIdentifier :#(B_COMMA
				listTypedIdentifier	
{
	printToStringln("");
	printToString(out.Separator(","));
}
			 	listTypedIdentifier
			)	
			|	typedIdentifier
;

// inherited from grammar TreeWalker
typedIdentifier :#(B_INSET
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

// inherited from grammar TreeWalker
constraints :#(tt:"CONSTRAINTS"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
link :uses 
			|	includes
			|	sees
			|	extendeds
			|	promotes
			|	imports
;

// inherited from grammar TreeWalker
extendeds :#(tt:"EXTENDS"
{
	printToStringln(out.Clause(tt.getText()));
}
				listInstanciation
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
uses :#(tt:"USES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listNames			
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
includes :#(tt:"INCLUDES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listInstanciation
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
sees :#(tt:"SEES"
{
	printToStringln(out.Clause(tt.getText()));
}
				listNames
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
listNames :#(B_COMMA 
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

// inherited from grammar TreeWalker
listInstanciation :#(B_COMMA 
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

// inherited from grammar TreeWalker
paramRenameValuation :#(t3:B_LPAREN 
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

// inherited from grammar TreeWalker
imports :#(tt:"IMPORTS"
{
	printToStringln(out.Clause(tt.getText()));
}
				  listInstanciation
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
promotes :#(tt:"PROMOTES"
{
	printToStringln(out.Clause(tt.getText()));
}
			  	listNames
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
refines :#(tt:"REFINES"				
{
	printToStringln(out.Clause(tt.getText()));
}
				 name:B_IDENTIFIER
{
	printToStringln(name);
}
			)
;

// inherited from grammar TreeWalker
constants :#(t1:"CONSTANTS"
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

// inherited from grammar TreeWalker
sets :#(tt:"SETS"
{
	printToStringln(out.Clause(tt.getText()));
}
				  sets_declaration
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
sets_declaration :#(B_SEMICOLON  
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

// inherited from grammar TreeWalker
set_declaration :#(B_EQUAL 
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

// inherited from grammar TreeWalker
valuation_set :#(B_CURLYOPEN
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

// inherited from grammar TreeWalker
list_couple :#(tt:B_COMMA 
				list_couple
{
	printToString(out.Separator(tt.getText()));
}
				couple 
			)
        |	couple
;

// inherited from grammar TreeWalker
couple :#(t1:B_MAPLET 
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

// inherited from grammar TreeWalker
parent_couple :#(t1:B_MAPLET 
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

// inherited from grammar TreeWalker
a_set_value :name:B_IDENTIFIER
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

// inherited from grammar TreeWalker
values :#(tt:"VALUES"
{
	printToStringln(out.Clause(tt.getText()));
}
			 	list_valuation
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
list_valuation :#(tt:B_SEMICOLON 
				list_valuation 
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
}
				set_valuation
			)
        |	set_valuation
;

// inherited from grammar TreeWalker
set_valuation :#(tt:B_EQUAL 
	name:B_IDENTIFIER
{
	printToString(name);
	printToString(out.Separator(tt.getText()));
}
	new_set_or_constant_valuation
)
;

// inherited from grammar TreeWalker
new_set_or_constant_valuation :predicate
;

// inherited from grammar TreeWalker
set_interval_value :#(tt:B_EQUAL 
	a:B_IDENTIFIER
{
	printToString(a);
	printToString(" /* : "+a.getBType().toString()+" */");
	printToString(out.Separator(tt.getText()));
}
	interval_declaration
)
;

// inherited from grammar TreeWalker
interval_declaration :#(tt:B_RANGE 
	predicate
{
	printToString(out.Separator(tt.getText()));
}
	predicate
)
;

// inherited from grammar TreeWalker
set_rename_value :#(tt:B_EQUAL 
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

// inherited from grammar TreeWalker
properties :#(tt:"PROPERTIES"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
variables :#(t0:"VARIABLES"
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

// inherited from grammar TreeWalker
invariant :#(tt:"INVARIANT"
{
	printToStringln(out.Clause(tt.getText()));
}
				predicate
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
definitions :#(tt:"DEFINITIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				list_def
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
list_def :#(tt:LIST_DEF
				list_def
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
} 
				definition
			)
        |	definition
;

// inherited from grammar TreeWalker
definition :#(t1:B_DOUBLE_EQUAL 
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

// inherited from grammar TreeWalker
formalText :#(EXP_DEF   predicate)
    |	#(SUBST_DEF instruction)
//			|	operation
    ;

// inherited from grammar TreeWalker
assertions :#(tt:"ASSERTIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				list_assertions
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
list_assertions :#(tt:B_SEMICOLON  
				list_assertions
{
	printToStringln("");
	printToString(out.Separator(tt.getText()));
}
				list_assertions
			)
        |	predicate
;

// inherited from grammar TreeWalker
initialisation :#(tt:"INITIALISATION"
{
	printToStringln(out.Clause(tt.getText()));
}
				  instruction
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
operations :#(tt:"OPERATIONS"
{
	printToStringln(out.Clause(tt.getText()));
}
				listOperation
{
	printToStringln("");
}
			)
;

// inherited from grammar TreeWalker
listOperation :#(tt:B_SEMICOLON 
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

// inherited from grammar TreeWalker
operation :#(tt:OP_DEF
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

// inherited from grammar TreeWalker
operationHeader :#(tt:B_OUT
				listTypedIdentifier
{
	printToString(out.Separator(tt.getText()));
}
				paramName 
// au cas ou			listTypedIdentifier 		{printToString(out.Separator(tt.getText()));}
			)		
        |	paramName
;

// inherited from grammar TreeWalker
instruction :#(t1:PARALLEL 	
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

// inherited from grammar TreeWalker
list_or :#(t1:"OR"
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

// inherited from grammar TreeWalker
branche_when :#(tt:"WHEN"
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

// inherited from grammar TreeWalker
branche_either :#(tt:"EITHER"
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

// inherited from grammar TreeWalker
branche_or :#(tt:"OR"
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

// inherited from grammar TreeWalker
branche_then :#(tt:"THEN"			
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

// inherited from grammar TreeWalker
branche_else :#(tt:"ELSE"
{
	printToStringln("");
	printToStringln(out.KeyWord(tt.getText()));
}
				instruction
			)
;

// inherited from grammar TreeWalker
branche_elsif :#(t1:"ELSIF"
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

// inherited from grammar TreeWalker
variant_or_no :#(t1:"VARIANT"
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

// inherited from grammar TreeWalker
list_equal :#(tt:B_AND 	
				list_equal
{
	printToStringln("");
	printToString(out.Separator("&"));
}
				an_equal
			)
        |	an_equal	
;

// inherited from grammar TreeWalker
an_equal :#(tt:B_EQUAL 	
				n1:B_IDENTIFIER
{
	printToString(n1);
	printToString(out.Separator("="));
}
		 		predicate
			)
;

// inherited from grammar TreeWalker
func_call :#(t1:B_TILDE
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

// inherited from grammar TreeWalker
func_param :list_New_Predicate
;

// inherited from grammar TreeWalker
list_identifier :#(tt:B_COMMA
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

// inherited from grammar TreeWalker
a_func_call :afc
;

// inherited from grammar TreeWalker
afc :#(FUNC_CALL_PARAM
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

// inherited from grammar TreeWalker
list_func_call :#(tt:B_COMMA
		  		list_func_call
{
	printToString(out.Separator(tt.getText()));
}
			 	list_func_call
			)
        |	a_func_call
;

// inherited from grammar TreeWalker
simple_affect :#(t1:B_SIMPLESUBST 	
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

// inherited from grammar TreeWalker
is_record :#(t1:"rec"
{
	printToString(out.KeyWord(t1.getText()));
	printToString(out.Separator("("));
}
				listrecord
{
	printToString(out.Separator(")"));
}
			)
			|
			#(t01:"struct"
{
	printToString(out.KeyWord(t01.getText()));
	printToString(out.Separator("("));
}
				listrecord
{
	printToString(out.Separator(")"));
}
			)
;

// inherited from grammar TreeWalker
listrecord :#(tt:B_COMMA 
				listrecord
{
	printToString("\n"+out.Separator(","));
}
				a_record
			)
        |	a_record
;

// inherited from grammar TreeWalker
a_record :#(B_SELECTOR 
				name:B_IDENTIFIER
{
	printToString(name);
	printToString(out.Separator(":"));
}
				predicate
			)
        |	predicate			
;

// inherited from grammar TreeWalker
list_New_Predicate :#(tt:B_COMMA 
				list_New_Predicate
{
	printToString(out.Separator(tt.getText()));
}
				new_predicate
			)
        |	new_predicate
		;

// inherited from grammar TreeWalker
new_predicate :#(t1:B_SEMICOLON 
				new_predicate
{
	printToString(out.Separator(t1.getText()));
}
				predicate
			)
        |
			#(t2:B_PARALLEL  
				new_predicate
{
	printToString(out.Separator(t2.getText()));
}
				predicate
			)
        |
			predicate
;

// inherited from grammar TreeWalker
nameRenamed :n1:B_IDENTIFIER
{
	printToString(n1);
}
			|
			#(tt:B_POINT 
				nameRenamed
{
	printToString(tt.getText());
}
				nameRenamed
			)
;

// inherited from grammar TreeWalker
nameRenamedDecorated :#(tt:B_CPRED 
				nameRenamed
{
	printToString(out.Separator(tt.getText()));
}
			)
			|
			nameRenamed
;

// inherited from grammar TreeWalker
nameRenameDecoratedInverted :#(tt:B_TILDE 
					nameRenamedDecorated
{
	printToString(out.Separator(tt.getText()));
}
				)
			|	nameRenamedDecorated
;

// inherited from grammar TreeWalker
listPredicate :#(tt:ELEM_SET
		 		listPredicate
{
	printToString(out.Separator(tt.getText()));
}
			 	predicate
			)
			|	predicate
;

// inherited from grammar TreeWalker
predicate :#(t1:B_AND			
{
//	index.Add();
}
				predicate
{
//	index.Retract();
	printToStringln("");	
//	index.Add();
	printToString(out.Separator(t1.getText()));

}
				predicate
			)	
			|	
			#(t2:"or"
 				predicate
{
	printToString(out.Separator(t2.getText()));
}	
				predicate
			)
			|
			#(t3:B_IMPLIES
				predicate
{
	printToString(out.Separator(t3.getText()));
}
				predicate
			)
			|
			#(t4:B_EQUIV
	 			predicate
{
	printToString(out.Separator(t4.getText()));
}
				predicate
			)
			|
			#(t5:B_MULT
		 		predicate
{
	printToString(out.Separator(t5.getText()) );
}
			 	predicate
			)
			|
			#(t51:PRODSET
		 		predicate
{
	printToString(out.Separator(t51.getText()) );
}
			 	predicate
			)
			|	
			#(t6:B_POWER
				predicate
{
	printToString(out.Separator(t6.getText()) );
}
			 	predicate
			)
			|
			#(t7:B_DIV
	  			predicate
{
	printToString(out.Separator(t7.getText()) );
}
			 	predicate
			)
			|
			#(t8:"mod"
	  			predicate
{
	printToString(out.Separator(t8.getText()) );
}
			 	predicate
			)
			|
			#(t9:UNARY_ADD
{
	printToString(out.Separator(t9.getText()) );
}
			 	predicate
			)
			|
			#(t10:UNARY_MINUS
{
	printToString(out.Separator(t10.getText()));
}
			 	predicate
			)
			|
		 	#(t11:B_ADD 
	  			predicate
{
	printToString(out.Separator(t11.getText()));
}
			 	predicate
			)
			|
			#(t12:B_MINUS
		 		predicate
{
	printToString(out.Separator(t12.getText()));
}
			 	predicate
			)
			|
			#(t13:B_EQUAL
	 			predicate
{
	printToString(out.Separator(t13.getText()));
}
			 	predicate
			)
			|
			#(t14:B_LESS
		 		predicate
{
	printToString(out.Separator(t14.getText()));
}
				predicate
			)
			|
			#(t15:B_GREATER
	 			predicate
{
	printToString(out.Separator(t15.getText()));
}
				predicate
			)
			|
			#(t16:B_NOTEQUAL
				predicate
{
	printToString(out.Separator(t16.getText()));
}
				predicate
			)
			|
			#(t17:B_LESSTHANEQUAL
		 		predicate
{
	printToString(out.Separator(t17.getText()));
}
				predicate
			)
			|
			#(t18:B_GREATERTHANEQUAL
				predicate
{
	printToString(out.Separator(t18.getText()));
}
				predicate
			)
			|
			#(t19:B_INSET
				predicate
{
	printToString(out.Separator(t19.getText()));
}
				predicate
			)
			|
			#(t20:B_NOTINSET
				predicate
{
	printToString(out.Separator(t20.getText()));
}
				predicate
			)
			|
			#(t21:B_SUBSET
				predicate
{
	printToString(out.Separator(t21.getText()));
}
				predicate
			)
			|
			#(t22:B_NOTSUBSET
				predicate
{
	printToString(out.Separator(t22.getText()));
}
				predicate
			)
			|
			#(t23:B_STRICTSUBSET
				predicate 	
{
	printToString(out.Separator(t23.getText()));
}
				predicate
			)
			|
			#(t24:B_NOTSTRICTSBSET
				predicate 	
{
	printToString(out.Separator(t24.getText()));
}
				predicate
			)
			|
			#(t25:B_CONCATSEQ
				predicate 	
{
    printToString(out.Separator(t25.getText()));
}
				predicate
			)
			|
			#(t26:B_PREAPPSEQ
				predicate 	
{
    printToString(out.Separator(t26.getText()));
}
				predicate
			)
			|
			#(t27:B_APPSEQ
				predicate
{
    printToString(out.Separator(t27.getText()));
}
				predicate
			)
			|
			#(t28:B_PREFIXSEQ
				predicate
{
	printToString(out.Separator(t28.getText()));
}
				predicate
			)
			|
			#(t29:B_SUFFIXSEQ
		 		predicate
{
	printToString(out.Separator(t29.getText()));
}
				predicate
			)
			|	
            #(t30:B_RELATION		
                predicate
{
    printToString(out.Separator(t30.getText()));
}
                predicate
            )
			|	
            #(t31:B_PARTIAL
                predicate
{
    printToString(out.Separator(t31.getText()));
}
                predicate
            )
			|	
            #(t32:B_TOTAL
                predicate
{
    printToString(out.Separator(t32.getText()));
}
                predicate
            )
			|	
            #(t33:B_PARTIAL_INJECT
		        predicate
{
    printToString(out.Separator(t33.getText()));
}
                predicate
            )
			|	
            #(t34:B_TOTAL_INJECT
		        predicate
{
    printToString(out.Separator(t34.getText()));
}
                predicate
            )
			|	
            #(t35:B_PARTIAL_SURJECT
		        predicate
{
    printToString(out.Separator(t35.getText()));
}
                predicate
            )
			|
	        #(t36:B_TOTAL_SURJECT
		        predicate
{
    printToString(out.Separator(t36.getText()));
}
                predicate
            )
			|
	        #(t37:B_BIJECTION
 		        predicate
{
    printToString(out.Separator(t37.getText()));
}
                predicate
            )
			|
	        #(t38:B_DOMAINRESTRICT
		        predicate
{
    printToString(out.Separator(t38.getText()));
}
                predicate
            )
			|
	        #(t39:B_RANGERESTRICT
		        predicate
{
    printToString(out.Separator(t39.getText()));
}
                predicate
            )
			|
	        #(t40:B_DOMAINSUBSTRACT
		        predicate
{
    printToString(out.Separator(t40.getText()));
}
                predicate
            )
			|
	        #(t41:B_RANGESUBSTRACT		predicate 	{			printToString(out.Separator(t41.getText()));} 	predicate)
			|
	        #(t42:B_OVERRIDEFORWARD		predicate 	{			printToString(out.Separator(t42.getText()));} 	predicate)
			|
	        #(t43:B_OVERRIDEBACKWARD 	predicate	{			printToString(out.Separator(t43.getText()));} 	predicate)
			|
	        #(t44:B_RELPROD 		predicate 	{			printToString(out.Separator(t44.getText()));} 	predicate)
			|
	        #(t45:B_UNION 			predicate 	{			printToString(out.Separator(t45.getText()));} 	predicate)
			|
	        #(t46:B_INTER
			 			predicate
{
	printToString(out.Separator(t46.getText()));
}
					 	predicate
				)
			|	#(t48:B_MAPLET
						predicate
{
	printToString(out.Separator(t48.getText()));
}
						predicate
				)
			|	#(t49:LIST_VAR
			 			predicate
{
	printToString(out.Separator(t49.getText()));
}
						predicate
				)
			|	#(B_NOT
{
	printToString(out.KeyWord("not"));
}
					predicate
)
			|	#(B_RAN
{
	printToString(out.KeyWord("ran"));
}
					predicate
)
			|	#(B_DOM
{
	printToString(out.KeyWord("dom"));
}
					predicate
)
			|	#(B_MIN
{
	printToString(out.KeyWord("min"));
}
					predicate
)			|	#(B_MAX
{
	printToString(out.KeyWord("max"));
}
					predicate
)			|	#(B_CARD
{
	printToString(out.KeyWord("card"));
}
					predicate
)
			|	cset_description
			;

// inherited from grammar TreeWalker
cset_description :basic_sets 
			|	cbasic_value
			|	#("bool"
{
	printToString(out.KeyWord("bool"));
	printToString(out.Separator("("));
}
					 predicate
{
	printToString(out.Separator(")"));
}	
				)
			|
				#(B_BRACKOPEN
{
	printToString(out.Separator("["));
}
					listPredicate
{
	printToString(out.Separator("]"));
}	
				)
			|
				#(t2:B_RANGE 		predicate
{
	printToString(out.Separator(t2.getText()));
}
				 			predicate
				)
			|	t3:B_EMPTYSET
{
	printToString(out.Separator(t3.getText()));
}
			|	#(t4:B_CURLYOPEN
{
	printToString(out.Separator("{"));
}
				 			cvalue_set
{
	printToString(out.Separator("}"));
}	
				)
			|
				t1:B_SEQEMPTY
{
	printToString(out.Separator(t1.getText()));
}
			|	is_record 
			|	quantification
			|	q_lambda
		;

// inherited from grammar TreeWalker
cvalue_set :#(t1:B_SUCH
			 		list_var
{
	printToString(out.Separator(t1.getText()));
}
			 		predicate
				)
			|	#(t2:ELEM_SET
			 		cvalue_set
{
	printToString(out.Separator(t2.getText()));
}
			 		cvalue_set
				)
			|	#(t3:B_COMMA
			 		cvalue_set
{
	printToString(out.Separator(t3.getText()));
}
			 		predicate
				)
			|	#(t4:LIST_VAR
			 		cvalue_set
{
	printToString(out.Separator(t4.getText()));
}
			 		predicate
				)
// JLB - 13/08/2011 
// discrepency between Treewalker and Btyping
			| predicate	
		;

// inherited from grammar TreeWalker
cbasic_value :t1:B_ASTRING
{
	printToString("\""+t1+"\"");
}
			|	t2:B_NUMBER
{
	printToString(out.Separator(t2.getText()));
}
			|	#(B_TILDE
					predicate
{
	printToString(out.Separator("~"));
}
				)
			|	nameRenamedDecorated
			|	#(B_LPAREN
					predicate
{
	printToString(out.Separator("("));
}
					 list_New_Predicate
{
	printToString(out.Separator(")"));
}
				)
			|	#(PARENT
{
	printToString(out.Separator("("));
} 
					pred_func_composition
{
	printToString(out.Separator(")"));
}
				)
			|	#(B_QUOTEIDENT 
		 			predicate
{
	printToString(out.Separator("'"));
} 
		 			predicate 		
		 		 )
			|	#(APPLY_TO
					predicate
{
	printToString(out.Separator("["));
}
					 predicate
{
	printToString(out.Separator("]"));
}
				)
			|	"TRUE"
{
	printToString(out.Separator("TRUE"));
}
			|	"FALSE"
{
	printToString(out.Separator("FALSE"));
}
		;

// inherited from grammar TreeWalker
pred_func_composition :#(B_SEMICOLON 
					pred_func_composition
{
	printToString(out.Separator(";"));
} 
					predicate
				)
			|	#(t2:B_PARALLEL  
					pred_func_composition
{
	printToString(out.Separator(t2.getText()));
} 
					predicate
				)
			|	#(t3:B_COMMA	 
					pred_func_composition
{
	printToString(out.Separator(t3.getText()));
} 
					predicate
				)
			|	predicate
		;

// inherited from grammar TreeWalker
basic_sets :t1:"INT"			    {printToString(out.KeyWord(t1.getText()));}
|	t2:"INT1"			    {printToString(out.KeyWord(t2.getText()));}
|	t3:"INTEGER"			{printToString(out.KeyWord(t3.getText()));}
|	t4:"INTEGER1"			{printToString(out.KeyWord(t4.getText()));}
            |	t5:"BOOL"   			{printToString(out.KeyWord(t5.getText()));}
            |	t6:"NAT"    			{printToString(out.KeyWord(t6.getText()));}
            |	t7:"NAT1"	    		{printToString(out.KeyWord(t7.getText()));}
			|	t8:"NATURAL"			{printToString(out.KeyWord(t8.getText()));}
			|	t9:"NATURAL1"			{printToString(out.KeyWord(t9.getText()));}
			|	t10:"STRING"			{printToString(out.KeyWord(t10.getText()));}
		;

// inherited from grammar TreeWalker
quantification :#(t70:B_FORALL
{
	printToString(out.Separator(t70.getText()));
}
					list_var
{
	printToString(out.Separator(".("));
} 
					predicate
{
	printToString(out.Separator(")"));
} 
				)
			|	#(t71:B_EXISTS
{
	printToString(out.Separator(t71.getText()));
}	
					list_var
{
	printToString(out.Separator(".("));
} 
					predicate
{
	printToString(out.Separator(")"));
}
				)
		;

// inherited from grammar TreeWalker
q_lambda :#(t1:B_LAMBDA
{
	printToString(out.Separator(t1.getText()));
} 
					q_operande
				)
			|	#(t2:"PI"
{
	printToString(out.KeyWord(t2.getText()));
} 
					q_operande
				)
			|	#(t3:"SIGMA"
{
	printToString(out.KeyWord(t3.getText()));
}
					q_operande
				)
			|	#(t4:"UNION"
{
	printToString(out.KeyWord(t4.getText()));
}
					 q_operande
				)
			|	#(t5:"INTER"
{
	printToString(out.KeyWord(t5.getText()));
}
					 q_operande
				)
		;

// inherited from grammar TreeWalker
q_operande :#(t1:B_SUCH
				list_var 
{
	printToString(out.Separator(".("));
}
				predicate
{
	printToString(out.Separator(t1.getText()));
}
				predicate
{
	printToString(out.Separator(")"));
}
			)
		;

// inherited from grammar TreeWalker
list_var :#(B_LPAREN
{
	printToString(out.Separator("("));
}
				list_identifier
{
	printToString(out.Separator(")"));
} 
			)
			|
{
	printToString(out.Separator(" "));
}
				list_identifier 
		;


