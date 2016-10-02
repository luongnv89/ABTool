header 
{
    package ABTOOLS.GRAMMAR;
}
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

	import ABTOOLS.ANTLR_TOOLS.*;
	import ABTOOLS.PRINTING.*;
	import ABTOOLS.DEBUGGING.*;
}class BXML extends TreeParser;

options {
	importVocab= BSystem;
	buildAST= false;
	ASTLabelType= "MyNode";
	codeGenMakeSwitchThreshold= 3;
	codeGenBitsetTestThreshold= 4;
	k= 1;
}

{
// module
	private String module ="BXML.g";

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
	private static DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}

// Section for printing 
	
	public	StringBuffer myBuffer;

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

        	System.out.println(myBuffer.toString());
		// au cas ou ....
	}

	void printToString(String st)
	{
        	debug.print(st);
        	myBuffer.append(st);
 	}

	void printToStringln(String st)
	{
        	debug.println(st);
        	myBuffer.append(st+"\n");
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
	 }
 	}

}
composant :{
	initializeString();
	printToStringln("<AMN>");
}
(
	machine
|	refinement
|	implementation
)
{
	printToStringln("</AMN>");
	finalizeString();
}
;

machine :#("MACHINE"
{
	printToStringln("<MACHINE>");
}
        head_component
        clauses
{
	printToString("</MACHINE>");
}
    )
;

refinement :#("REFINEMENT"
{
	
	printToStringln("<REFINEMENT>");
}
	head_component
        clauses
{
	printToString("</REFINEMENT>");
}
    )
;

implementation :#("IMPLEMENTATION"                   
{
	printToStringln("<IMPLEMENTATION>");
}
        head_component
        clauses
{
	printToString("</IMPLEMENTATION>");
}
    )
;

head_component :{
	printToString("<HEAD>");
}
        paramName
{
	printToString("</HEAD>");
}
;

paramName :{
	printToString("<paramName>");
} 
(
    #(B_LPAREN 
{
	printToString("<B_LPAREN>");
} 
        paramName
        listTypedIdentifier
{
	printToString("</B_LPARENT>");
}
    )			
|   an_id
)
{
	printToString("</paramName>");
} 
;

clauses :{
	printToString("<clauses>");
} 
        (   
            clause
        )*
{
	printToString("</clauses>");
} 
;

clause :{
	printToString("<clause>");
}
( 
	constraints
|	refines 
|      	link
|	sets
|      	values
|      	constants
|      	properties
|      	variables
|      	invariant
|      	assertions
|      	definitions
|      	initialisation
|      	operations  
)
{
	printToString("<clause>");
} 
;

listTypedIdentifier :{
	printToString("<listTypedIdentifier>");
}
(
    #(B_COMMA
{
	printToString("<ListIdent>");
}
        listTypedIdentifier
        listTypedIdentifier
{
	printToString("</ListIdent>");
}	
    )	
|	typedIdentifier
)
{
	printToString("</listTypedIdentifier>");
}
;

typedIdentifier :{
	printToString("<typedIdentifier>");
}
(
    #(B_INSET
{
	printToString("<B_INSET>");
}
        nameRenamed
        predicate 
{
	printToString("</B_INSET>");
}
    )	
|	nameRenamed
)
{
	printToString("</typedIdentifier>");
}
;

constraints :#("CONSTRAINTS"
{
	printToStringln("<Constraints>");
}
        predicate
{
	printToStringln("</Constraints>");
}
    )
;

link :uses 
|	includes
|	sees
|	extendeds
|	promotes
|	imports
;

extendeds :#("EXTENDS"
{
	printToStringln("<Extends>");
}
        listInstanciation
{
	printToStringln("</Extends>");
}
    )
;

uses :#("USES"
{
	printToStringln("<Uses>");
}
        listNames			
{
	printToStringln("</Uses>");
}
    )
;

includes :#("INCLUDES"
{
	printToStringln("<Includes>");
}
        listInstanciation
{
	printToStringln("</Includes>");
}
    )
;

sees :#("SEES"
{
	printToStringln("<Sees>");
}
        listNames
{
	printToStringln("</Sees>");
}
    )
;

listNames :{
	printToString("<listNames>");
}
(
    #(B_COMMA 
{
	printToString("<B_COMMA>");
} 
        listNames
        nameRenamed
{
	printToString("</B_COMMA>");
} 
)
|	nameRenamed
)
{
	printToString("</listNames>");
}
;

listInstanciation :{
	printToString("<listInstanciation>");
}
(
    #(B_COMMA 
{
	printToString("<B_COMMA>");
} 
        listInstanciation
        paramRenameValuation
{
	printToString("</B_COMMA>");
} 
    )
|
    paramRenameValuation
)
{
	printToString("<listInstanciation>");
}
;

paramRenameValuation :{
	printToStringln("<Declaration>");
}
    #(B_LPAREN 
{
	printToString("<B_PARENT>");
} 
            paramRenameValuation
            list_New_Predicate
{
	printToString("</B_PARENT>");
} 
     )
|	nameRenamed
{
	printToStringln("</Declaration>");
}
;

imports :#("IMPORTS"
{
	printToStringln("<Imports>");
}
        listInstanciation
{
	printToStringln("</Imports>");
}
    )
;

promotes :#("PROMOTES"
{
	printToStringln("<Promotes>");
}
        listNames
{
	printToStringln("</Promotes>");
}
    )
;

refines :#("REFINES"				
{
	printToStringln("<Refines>");
}
        name:an_id
{
	printToStringln(name);
	printToStringln("</Refines>");
}
    )
;

constants :#("CONSTANTS"
{
	printToStringln("<ConcreteConstants>");
}
        listTypedIdentifier		
{
	printToStringln("</ConcreteConstants>");
}
    )
|	#("CONCRETE_CONSTANTS"
{
	printToStringln("<ConcreteConstants>");
}
        listTypedIdentifier
{
	printToStringln("</ConcreteConstants>");
}
    )
|	#("VISIBLE_CONSTANTS"
{
	printToStringln("<AbstractConstants>");
}
        listTypedIdentifier
{
	printToStringln("</AbstractConstants>");
}
    )
|	#("ABSTRACT_CONSTANTS"
{
	printToStringln("<AbstractConstants>");
}
        listTypedIdentifier
{
	printToStringln("</AbstractConstants>");
}
    )
|	#("HIDDEN_CONSTANTS"
{
	printToStringln("<ConcreteConstants>");
}
        listTypedIdentifier
{
	printToStringln("</ConcreteConstants>");
}
    )
;

sets :#("SETS"
{
	printToStringln("<Sets>");
}
        sets_declaration
{
	printToStringln("</Sets>");
}
    )
;

sets_declaration :{
	printToStringln("<sets_declaration>");
}
	(
			#(B_SEMICOLON
{
	printToStringln("<B_SEMICOLON>");
}  
				sets_declaration
				sets_declaration
{
	printToStringln("</B_SEMICOLON>");
} 
			)	
			|
			#(B_COMMA
{
	printToStringln("<B_COMMA>");
} 
				sets_declaration 
				sets_declaration
{
	printToStringln("</B_COMMA>");
} 
			)
			|	set_declaration	
	)
{
	printToStringln("</sets_declaration>");
}
		;

set_declaration :{
	printToStringln("<set_declaration>");
} 
(	
    #(B_EQUAL 
{
	printToStringln("<B_EQUAL>");
} 
        an_id
        valuation_set
{
	printToStringln("</B_EQUAL>");
} 
    )
|	
    	an_id
)
{
	printToStringln("<set_declaration>");
} 
;

is_record :#("rec"
{
	printToStringln("<rec>");
}
        listrecord
{
	printToStringln("</rec>");
} 
    )
|
    #("struct"
{
	printToStringln("<struct>");
}
        listrecord
{
	printToString("</struc>");
}
    )
;

valuation_set :{
	printToString("<valuation_set>");
}
(
    	#(B_CURLYOPEN
{
	printToString("<B_CURLYOPEN>");
}
        list_couple
{
	printToString("</B_CURLYOPEN>");
}
)
|   	is_record
|	#(B_MULT 
{
	printToString("<B_MULT>");
}
        valuation_set
        valuation_set
{
	printToString("</B_MULT>");
}
)
|   	#(B_ADD 
{
	printToString("<B_ADD>");
}
        valuation_set
        valuation_set
{
	printToString("</B_ADD>");
}
    )
|	#(B_MINUS 
{
	printToString("<B_MINUS>");
}
        valuation_set
        valuation_set
{
	printToString("</B_MINUS>");
}
    )
|	an_id
|	basic_sets
)
{
	printToString("</valuation_set>");
}
;

listrecord :{
	printToString("<listrecord>");
}
(	
    #(B_COMMA 
{
	printToString("<B_COMMA>");
} 
        listrecord
        a_record
{
	printToString("</B_COMMA>");
} 
    )
|	a_record
)
{
	printToString("</listrecord>");
}
;

list_couple :{
	printToString("<list_couple>");
}
(
    #(B_COMMA 
{
	printToString("<B_COMMA>");
} 
        list_couple
        couple 
{
	printToString("</B_COMMA>");
} 
    )
|	couple
)
{
	printToString("</list_couple>");
}
;

couple :{
	printToString("<couple>");
}
( 	
    #(B_MAPLET
{
	printToString("<B_MAPLET>");
} 
        a_set_value
        a_set_value
{
	printToString("</B_MAPLET>");
} 
    )
|   #(B_LPAREN
{
	printToString("<B_LPARENT>");
}
        parent_couple
{
	printToString("</B_LPARENT>");
}
    )
|	a_set_value
)
{
	printToString("</couple>");
} 
;

parent_couple :{
	printToString("<parent_couple>");
} 
(	
    #(B_MAPLET 
{
	printToString("<B_MAPLET>");
} 
        a_set_value
        a_set_value
{
	printToString("</B_MAPLET>");
} 
    )
|
    #(B_COMMA  
{
	printToString("<B_COMMA>");
}
        a_set_value
        a_set_value
{
	printToString("</B_COMMA>");
}
    )
|	a_set_value
)
{
	printToString("</parent_couple>");
} 
;

a_set_value :{
	printToString("<a_set_value>");
}
( 
    name:an_id
{
	printToString(name);
}
|   #(B_MINUS
{
	printToString("<B_MINUS>");
}
        B_NUMBER
{
	printToString("</B_MINUS>");
}
    )
|	B_NUMBER
|	"TRUE"
|	"FALSE"
)
{
	printToString("</a_set_value>");
} 
;

a_record :{
	printToString("<a_record>");
}
(
    #(B_SELECTOR 
{
	printToString("<B_SELECTOR>");
}
        an_id
        predicate
{
	printToString("</B_SELECTOR>");
}
    )
|	predicate
)
{
	printToString("</a_record>");
}			
;

values :#("VALUES"
{
	printToStringln("<Values>");
}
        list_valuation
{
	printToStringln("</Values>");
}
    )
;

list_valuation :{
	printToStringln("<list_valuation>");
}	
    #(B_SEMICOLON 
{
	printToStringln("<B_SEMICOLON>");
}
        list_valuation 
        set_valuation
{
	printToStringln("</B_SEMICOLON>");
}
    )
|	set_valuation
{
	printToStringln("</list_valuation>");
}
;

set_valuation :#(B_EQUAL 
{
	printToStringln("<B_EQUAL>");
}
        an_id
        new_set_or_constant_valuation
{
	printToStringln("</B_EQUAL>");
}
    )
;

new_set_or_constant_valuation :predicate
;

set_interval_value :#(B_EQUAL 
{
	printToStringln("<B_EQUAL>");
}
        an_id
        interval_declaration
{
	printToStringln("</B_EQUAL>");
}
    )
;

interval_declaration :#(B_RANGE 
{
	printToStringln("<B_RANGE>");
}
        predicate
        predicate
{
	printToStringln("</B_RANGE>");
}
    )
;

set_rename_value :#(B_EQUAL 
{
	printToStringln("<B_EQUAL>");
}
        an_id
        an_id
{
	printToStringln("</B_EQUAL>");
}
    )
;

properties :#("PROPERTIES"
{
	printToStringln("<Properties>");
}
        predicate
{
	printToStringln("</Properties>");
}
    )
;

variables :#("VARIABLES"
{
	printToStringln("<ConcreteVariables>");
} 
        listTypedIdentifier
{
	printToStringln("</ConcreteVariables>");
}
    )
|	#("ABSTRACT_VARIABLES"
{
	printToStringln("<AbstractVariables>");
} 
        listTypedIdentifier
{
	printToStringln("</AbstractVariables>");
}
    )
|	#("VISIBLE_VARIABLES"
{
	printToStringln("<AbstractVariables>");
} 
        listTypedIdentifier
{
	printToStringln("</AbstractVariables>");
}
    )
|   #("CONCRETE_VARIABLES"
{
	printToStringln("<ConcreteVariables>");
}
        listTypedIdentifier
{
	printToStringln("</ConcreteVariables>");
}
    )
|   #("HIDDEN_VARIABLES"
{
	printToStringln("<ConcreteVariables>");
}
        listTypedIdentifier
{
	printToStringln("</ConcreteVariables>");
}
    )
;

invariant :#("INVARIANT"
{
	printToStringln("<Invariant>");


}
        predicate
{
	printToStringln("</Invariant>");
}
    )
;

definitions :#("DEFINITIONS"
{
	printToStringln("<Definitions>");
}
        list_def
{
	printToStringln("</Definitions>");
}
    )
;

list_def :#(LIST_DEF
{
	printToStringln("<LIST_DEF>");
}
        list_def
        definition
{
	printToStringln("</LIST_DEF>");
}
    )
|	definition
;

definition :#(B_DOUBLE_EQUAL 
{
	printToString("<Definition>");
	printToString("<Header>");
}
        paramName
{
	printToString("</Header>");
}
        formalText
{
	printToString("</Definition>");
}
    )
|	t3:B_ASTRING
{
	printToStringln("\""+t3+"\"");
}
    ;

formalText :{
	printToString("<EXP_DEF>");
}
    #(EXP_DEF   predicate)
{
	printToString("</EXP_DEF>");
}
|
{
	printToString("<SUBST_DEF>");
}
	#(SUBST_DEF instruction)
{
	printToString("</SUBST_DEF>");
}
|
{
	printToString("<OP_DEF>");
}
	operation
{
	printToString("</OP_DEF>");
}
    ;

assertions :#("ASSERTIONS"
{
	printToStringln("<Assertions>");
}
        list_assertions
{
	printToStringln("</Assertions>");
}
    )
;

list_assertions :#(B_SEMICOLON
{
	printToString("<B_SEMICOLON>");
}
        list_assertions
        list_assertions
{
	printToString("</B_SEMICOLON>");
}
)
|	predicate
;

initialisation :#("INITIALISATION"
{
	printToStringln("<Initialisation>");
}
        instruction
{
	printToStringln("</Initialisation>");
}
    )
;

operations :#("OPERATIONS"
{
	printToStringln("<Operations>");

System.out.println(myBuffer.toString());
}
        listOperation
{
	printToStringln("</Operations>");
}
    )
;

listOperation :#(B_SEMICOLON
{
	printToString("<B_SEMICOLON>");
}
        listOperation
        operation
{
	printToString("</B_SEMICOLON>");
}
    )
|	operation
;

operation :{
	printToStringln("<Operation>");
}
    #(OP_DEF
            operationHeader
            instruction
     )
{
	printToStringln("</Operation>");
}
;

operationHeader :{
	printToStringln("<Header>");
}
	(
			#(B_OUT
{
	printToStringln("<Results>");
}
				listTypedIdentifier
{
	printToStringln("</Results>");
}
				paramName
			)		
			|	paramName
	)
{
	printToStringln("</Header>");
}
;

instruction :#(PARALLEL
{
	printToStringln("<Parallel>");
}
        instruction
        instruction
{
	printToStringln("</Parallel>");
}
    )
|   #(SEQUENTIAL	
{
	printToStringln("<Sequence>");
}
        instruction
        instruction
{
	printToStringln("</Sequence>");
}
    )
|   #("skip"
{
	printToString("<Skip> </Skip>");
}
    )
|   #("BEGIN"
{
	printToStringln("<Block>");
}
        instruction
{
	printToString("</Block>");
} 
    )
|   #("PRE"
{
	printToStringln("<Pre>");
}
        predicate
        instruction
{
	printToStringln("</Pre>");
}
    )
|
    #("ASSERT"
{
    printToStringln("<Assert>");
}
        predicate
        instruction			
{
    printToString("</Assert>");
}
    )
|	#("IF"
{
    printToStringln("<If>");
}
 				predicate
				branche_then
				(branche_elsif)*
				(branche_else)?
{
	printToString("</If>");
}
    )
|   #("CHOICE"
{
	printToStringln("<Choice>");
}
				list_or
{
	printToString("</Choice>");
}
    )
|   #("CASE"
{
    printToStringln("<Case>");
}
        predicate 
        branche_either
        (branche_or)*
        (branche_else)?
{
   printToStringln("</Case>");
}
    )
|   #("ANY" 
{
	printToStringln("<Any>");
}
				listTypedIdentifier
				predicate
				instruction
{
	printToStringln("</Any>");
}
    )
|   #("LET"
{
    printToStringln("<Let>");
}
        listTypedIdentifier
        list_equal
        instruction
{
	printToStringln("</Let>");
}
    )
|   #("SELECT"
{
    printToStringln("<Select>");
}
        predicate 
        branche_then 
        (branche_when)*
        (branche_else)?
{
    printToStringln("</Select>");
}
    )
|   #("WHILE"
{
    printToStringln("<While>");
}
        predicate
        instruction
        variant_or_no
{
   printToStringln("</While>");
}
    )
|   #("VAR"
{
    printToStringln("<Var>");
}
        listTypedIdentifier
        instruction
{
    printToStringln("</Var>");
}
			)
|	simple_affect
;

list_or :#("OR"
{
    printToStringln("<Or>");
}
        list_or
        instruction
{
    printToStringln("</Or>");
}
    )
|   instruction
;

branche_when :#("WHEN"
{
    printToStringln("<When>");
}
        predicate
        instruction
{
    printToStringln("</When>");
}
    )
;

branche_either :#("EITHER"
{
    printToStringln("<Either>");
}
        predicate
        instruction 
{
    printToStringln("</Either>");
}
   )
;

branche_or :#(tt:"OR"
        predicate
        instruction
    )
;

branche_then :#("THEN"
{
    printToStringln("<Then>");
}
        instruction
{
    printToStringln("</Then>");
}
    )
;

branche_else :#("ELSE"
{
    printToStringln("<Else>");
}
        instruction
{
    printToStringln("</Else>");
}
    )
;

branche_elsif :#("ELSIF"
{
    printToStringln("<Elsif>");
}
        predicate
        instruction
{
    printToStringln("</Elsif>");
}
    )
;

variant_or_no :#("VARIANT"
{
    printToStringln("<Variant>");
}
        predicate 
{
    printToStringln("</Variant>");
}
       #("INVARIANT"
{
    printToStringln("<Invariant>");
}
            predicate
{
    printToStringln("</Invariant>");
}
        )
    )
|   #("INVARIANT"
{
    printToStringln("<Invariant>");
}
        predicate
{
    printToStringln("</Invariant>");
}
        #("VARIANT" 
{
    printToStringln("<Variant>");
}
            predicate
{
    printToStringln("</Variant>");
}
        )
    ) 
;

list_equal :#(B_AND
{
    printToStringln("<B_AND>");
}
        list_equal 
        an_equal
{
    printToStringln("<B_AND>");
}
    )
|	an_equal	
;

an_equal :{
    printToStringln("<B_EQUAL>");
}
    #(B_EQUAL 	
        an_id
        predicate
    )
{
    printToStringln("</B_EQUAL>");
}
;

func_call :#(B_TILDE
{
    printToStringln("<B_TILDE>");
}
        func_call
{
    printToStringln("</B_TILDE>");
}      
    )
|   #(APPLY_TO
{
    printToStringln("<APPLY_TO>");
}
        func_call
        list_New_Predicate
{
    printToStringln("</APPLY_TO>");
}
    )
|   #(B_LPAREN
{
    printToStringln("<B_LPAREN>");
}
        func_call
        list_New_Predicate
{
    printToStringln("</B_LPAREN>");
}
    )
|   #(B_QUOTEIDENT
{
    printToStringln("<B_QUOTEIDENT>");
}
        func_call
        func_call
{
    printToStringln("<B_QUOTEIDENT>");
}  
    )
|   nameRenamed 
;

func_param :list_New_Predicate
;

a_func_call ://    #(A_FUNC_CALL
        afc
//    )	
;

afc :#(FUNC_CALL_PARAM
{
    printToStringln("<FUNC_CALL_PARAM>");
}
        afc
        listPredicate
{
    printToStringln("</FUNC_CALL_PARAM>");
}
     )
|
    #(t1:B_QUOTEIDENT
{
    printToStringln("<B_QUOTEIDENT>");
}
        afc
        afc
{
    printToStringln("</B_QUOTEIDENT>");
}
    )
|	nameRenamed
;

list_func_call :#(tt:B_COMMA
{
    printToStringln("<B_COMMA>");
}
        list_func_call
        a_func_call
{
    printToStringln("</B_COMMA>");
}
    )
|	a_func_call
;

simple_affect :#(B_SIMPLESUBST
{
    printToStringln("<B_SIMPLESUBST>");
}
	list_func_call
	predicate
{
    printToStringln("</B_SIMPLESUBST>");
}
			)
|
#(B_OUT
{
    printToStringln("<B_OUT>");
}
	list_func_call
	func_call
{
    printToStringln("</B_OUT>");
}
)
|
#(INSET
{
    printToStringln("<INSET>");
}
	list_func_call
	predicate
{
    printToStringln("</INSET>");
}
)
|
#(B_BECOME_ELEM
{
    printToStringln("<B_BECOME_ELEM>");
}
	list_func_call
	predicate
{
    printToStringln("</B_BECOME_ELEM>");
}
)
|
	a_func_call
;

an_id :{
	printToString("<Identifier>");
}
		B_IDENTIFIER
{
	printToString("</Identifier>");
}
;

// inherited from grammar POWalker
analyze_PO :#(PO 
            analyze_PO
            analyze_PO
        )
    |   analyze_APO
;

// inherited from grammar POWalker
analyze_APO :#(APO 
{
   initializeString();

   printToString("<ProofObligation>");
}
{
   printToString("<Predicate>");
}
        predicate
{
   printToString("</Predicate>");
}

{
   printToString("</ProofObligation>");

   finalizeString();
}
    )
;

// inherited from grammar POWalker
list_New_Predicate :#(B_COMMA 
        list_New_Predicate
        new_predicate
    )
|	new_predicate
;

// inherited from grammar POWalker
new_predicate :#(B_SEMICOLON 
        new_predicate
        predicate
    )
|   #(B_PARALLEL  
        new_predicate
        predicate
    )
|   predicate
;

// inherited from grammar POWalker
nameRenamed :B_IDENTIFIER
|   #(B_POINT 
        nameRenamed
        nameRenamed
    )
;

// inherited from grammar POWalker
nameRenamedDecorated :#(B_CPRED 
        nameRenamed
    )
|   nameRenamed
;

// inherited from grammar POWalker
nameRenameDecoratedInverted :#(B_TILDE 
        nameRenamedDecorated
    )
|	nameRenamedDecorated
;

// inherited from grammar POWalker
list_identifier :#(B_COMMA
        list_identifier
        n1:B_IDENTIFIER
    )
|	B_IDENTIFIER
;

// inherited from grammar POWalker
listPredicate :#(B_COMMA
        listPredicate
        predicate
    )
|	predicate
;

// inherited from grammar POWalker
predicate :#(BTRUE
{
   printToString("<BTRUE>");
}
            )
|           #(B_NOT
{
	printToString("<Neg>");
}
                predicate
{
	printToString("</Neg>");
}
            )
|	        #(B_AND
{
	printToString("<And>");
}
				predicate
				predicate
{
	printToString("</And");
}
			)
|			#("or"
{
	printToString("<Or>");
}
 				predicate	
				predicate
{
	printToString("</Or>");
}
			)
|			#(B_IMPLIES
{
	printToString("<Implies>");
}
				predicate
				predicate
{
	printToString("</Implies>");
}
			)
			|
			#(B_EQUIV
{
	printToString("<Equiv>");
}
	 			predicate
				predicate
{
	printToString("</Equiv>");
}
			)
|			#(B_MULT
{
	printToString("<Mul>");
}
		 		predicate
			 	predicate
{
	printToString("</Mul>");
}
			)
|			#(B_POWER
// Normalement on devrait generer "Power" 
// mais pour etre compatble BCAML 
{
	printToString("<Puissance>");
}
				predicate
			 	predicate
{
	printToString("</Puissance>");
}
			)
|			#(B_DIV
{
	printToString("<Div>");
}
	  			predicate
			 	predicate
{
	printToString("</Div>");
}
			)
|		    #("mod"
{
	printToString("<Modulo>");
}
	  			predicate
			 	predicate
{
	printToString("</Modulo>");
}
			)
|			#(UNARY_ADD
{
	printToString("<Plus>");
}
			 	predicate
{
	printToString("</Plus>");
}
			)
|			#(UNARY_MINUS
{
	printToString("<UMinus>");
}
			 	predicate
{
	printToString("</UMinus>");
}
			)
|		 	#(B_ADD 
{
	printToString("<Plus>");
}
	  			predicate
			 	predicate
{
	printToString("</Plus>");
}
			)
|			#(B_MINUS
{
	printToString("<Minus>");
}
		 		predicate
			 	predicate
{
	printToString("</Minus>");
}
			)
|			#(B_EQUAL
{
	printToString("<Equal>");
}
	 			predicate
			 	predicate
{
	printToString("</Equal>");
}
			)
|			#(B_LESS
{
	printToString("<Less>");
}
		 		predicate
				predicate
{
	printToString("</Less>");
}
			)
|			#(B_GREATER
{
	printToString("<Greater>");
}
	 			predicate
				predicate
{
	printToString("</Greater>");
}
			)
|           #(B_NOTEQUAL
{
	printToString("<NotEqual>");
}
				predicate
				predicate
{
	printToString("</NotEqual>");
}
			)
|			#(B_LESSTHANEQUAL
{
	printToString("<LessEqual>");
}
		 		predicate
				predicate
{
	printToString("</LessEqual>");
}
			)
|			#(B_GREATERTHANEQUAL
{
	printToString("<GreaterEqual>");
}
				predicate
				predicate
{
	printToString("</GreaterEqual>");
}
			)
|			#(B_INSET
{
	printToString("<In>");
}
				predicate
				predicate
{
	printToString("</In>");
}
			)
|			#(B_NOTINSET
{
	printToString("<NotIn>");
}
				predicate
				predicate
{
	printToString("</NotIn>");
}
			)
|			#(B_SUBSET
{
	printToString("<SubSet>");
}
				predicate
				predicate
{
	printToString("</SubSet>");
}
			)
|           #(B_NOTSUBSET
{
	printToString("<NotSubSet>");
}
				predicate
				predicate
{
	printToString("</NotSubSet>");
}
			)
|			#(B_STRICTSUBSET
{
	printToString("<StrictSubSet>");
}
				predicate 	
				predicate
{
	printToString("</StrictSubSet>");
}
			)
|			#(B_NOTSTRICTSBSET
{
	printToString("<NotStrictSubSet>");
}
    			predicate 	
				predicate
{
	printToString("</NotStrictSubSet>");
}
 			)
|			#(B_CONCATSEQ
{
	printToString("<ConcatSeq>");
}
				predicate 	
				predicate
{
	printToString("</ConcatSeq>");
}
			)
|			#(B_PREAPPSEQ
{
	printToString("<>");
}
				predicate 	
				predicate
			)
|			#(B_APPSEQ
				predicate
				predicate
			)
|			#(B_PREFIXSEQ
{
	printToString("<PrefixSeq>");
}
				predicate
				predicate
{
	printToString("</PrefixSeq>");
}
			)
|			#(B_SUFFIXSEQ
{
	printToString("<SuffixSeq>");
}
		 		predicate
				predicate
{
	printToString("</SuffixSeq>");
}
			)
|           #(B_RELATION		
                predicate
                predicate
            )
|	        #(B_PARTIAL
{
	printToString("<PartialFunc>");
}
                predicate
                predicate
{
	printToString("</PartialFunc>");
}
            )
|           #(B_TOTAL
{
	printToString("<TotalFunc>");
}
                predicate
                predicate
{
	printToString("</TotalFunc>");
}
            )
|	        #(B_PARTIAL_INJECT
{
	printToString("<PartialInj>");
}
		        predicate
                predicate
{
	printToString("</PartialInj>");
}
            )
|	        #(B_TOTAL_INJECT
{
	printToString("<TotalInj>");
}
		        predicate
                predicate
{
	printToString("</TotalInj>");
}
            )
|	        #(B_PARTIAL_SURJECT
{
	printToString("<PartialSurj>");
}
		        predicate
                predicate
{
	printToString("</PartialSurj>");
}
           )
|	        #(B_TOTAL_SURJECT
{
	printToString("<TotalSurj>");
}
		        predicate
                predicate
{
	printToString("</TotalSurj>");
}
            )
|           #(B_BIJECTION
{
	printToString("<TotalBij>");
}
 		        predicate
                predicate
{
	printToString("</TotalBij>");
}
            )
|	        #(B_DOMAINRESTRICT
{
	printToString("<DomRestrict>");
}
		        predicate
                predicate
{
	printToString("</DomRestrict>");
}
            )
|	        #(B_RANGERESTRICT
{
	printToString("<RanRestrict>");
}
		        predicate
                predicate
{
	printToString("</RanRestrict>");
}
            )
|	        #(B_DOMAINSUBSTRACT
{
	printToString("<DomSubstract>");
}
		        predicate
                predicate
{
	printToString("</DomSubstract>");
}
           )
|	        #(B_RANGESUBSTRACT
{
	printToString("<RanSubstract>");
}
                predicate 	
                predicate
{
	printToString("</RanSubstract>");
}
            )
|	        #(B_OVERRIDEFORWARD
{
	printToString("<OverRideFwd>");
}
                predicate 
                predicate
{
	printToString("</OverRideFwd>");
}
            )
|	        #(B_OVERRIDEBACKWARD
{
	printToString("<OverRideBck>");
}
                predicate
                predicate
{
	printToString("</OverRideBck>");
}
           )
|           #(B_RELPROD
{
	printToString("<RelProd>");
}
                predicate
                predicate
{
	printToString("</RelProd>");
}
            )
|	        #(B_UNION
{
	printToString("<UnionSets>");
}
                predicate
                predicate
{
	printToString("</UnionSets>");
}
            )
|	        #(B_INTER
{
	printToString("<InterSets>");
}
                predicate
                predicate
{
	printToString("</InterSets>");
}
            )
|	        #(B_MAPLET
{
	printToString("<Maplet>");
}
                predicate
                predicate
{
	printToString("</Maplet>");
}
            )
|	        #(LIST_VAR
                predicate
                predicate
            )
|	        #(B_RAN
{
	printToString("<Ran>");
}
                predicate
{
	printToString("</Ran>");
}
            )
|	        #(B_DOM
{
	printToString("<Dom>");
}
                predicate
{
	printToString("</Dom>");
}
            )
|	        basic_sets 
|	        cbasic_value
|	        #("bool"
{
	printToString("<BoolEvaluation>");
}
                predicate
{
	printToString("</BoolEvaluation>");
}
            )
|           #(B_BRACKOPEN
                listPredicate
            )
|           #(B_RANGE
{
	printToString("<SetRange>");
}
 		        predicate
                predicate
{
	printToString("</SetRange>");
}
            )
|	        #(B_CURLYOPEN
                cvalue_set
            )
|	        (		
{
	printToString("<SeqEmpty>");
}
    B_SEQEMPTY
{
	printToString("</SeqEmpty>");
}
            )
|	        is_record 
|	        quantification
|	        q_lambda
;

// inherited from grammar POWalker
cvalue_set :#(t1:B_SUCH
        list_var
        predicate
    )
|	#(t2:B_COMMA
        cvalue_set
        predicate
    )
|	predicate
;

// inherited from grammar POWalker
cbasic_value :t1:B_ASTRING
{
    printToString("<STRING>");
	printToString(t1.getText());
    printToString("</STRING>");
}
|   t2:B_NUMBER
{
    printToString("<Number>");
	printToString(t2.getText());
    printToString("</Number>");
}
|   #(B_TILDE
{
    printToString("<Tilde>");
}
            predicate
{
    printToString("</Tilde>");
}
     )
|   nameRenamedDecorated
|	#(B_LPAREN
        predicate
{
	printToString("<PredParen>");
}
        list_New_Predicate
{
	printToString("</PredParen>");
}
    )
|	#(PARENT
{
	printToString("<PredParen>");
}
        pred_func_composition
{
	printToString("</PredParen>");
}
    )
|	#(B_QUOTEIDENT 
        predicate
        predicate 		
    )
|	#(APPLY_TO
        predicate
        predicate
    )
|   (
{
	printToString("<TRUE>");
}
	    "TRUE"
{
	printToString("</TRUE>");
}

    )
|   (
{
	printToString("<FALSE>");
}
	    "FALSE"
{
	printToString("</FALSE>");
}
    )
;

// inherited from grammar POWalker
pred_func_composition :#(B_SEMICOLON
{
	printToString("<RelSeqComp>");
}
        pred_func_composition 
        predicate
{
	printToString("</RelSeqComp>");
}
    )
|	#(B_PARALLEL 
{
	printToString("<ParallelComp>");
}
        pred_func_composition
        predicate
{
	printToString("</ParallelComp>");
}
    )
|	#(B_COMMA	 
        pred_func_composition
        predicate
    )
|	predicate
;

// inherited from grammar POWalker
basic_sets :{
   printToString("<SetPredefined>");
}

(
    "INT"			    	{printToString("<INT>");}
|	"INTEGER"			{printToString("<INTEGER>");}
|	"BOOL"   			{printToString("<BOOL>");}
|	"NAT"    			{printToString("<NAT>");}
|	"NAT1"	    			{printToString("<NAT1>");}
|	"NATURAL"			{printToString("<NATURAL>");}
|	"NATURAL1"			{printToString("<NATURAL1>");}
|	"STRING"			{printToString("<STRING>");}
|	B_EMPTYSET         		{printToString("<SetEmpty>");}
)
{
   printToString("</SetPredefined>");
}
;

// inherited from grammar POWalker
quantification :#(B_FORALL
{printToString("<ForAll>");}
        list_var
        predicate
{printToString("</ForAll>");}
    )
|   #(B_EXISTS
{printToString("<Exists>");}
        list_var
        predicate
{printToString("</Exists>");}
    )
;

// inherited from grammar POWalker
q_lambda :#(B_LAMBDA
{
	printToString("<Lambda>");
}
         q_operande
{
	printToString("</Lambda>");
}
   )
|	#("PI"
{
	printToString("<PI>");
}
        q_operande
{
	printToString("</PI>");
}
    )
|	#("SIGMA"
{
	printToString("<SIGMA>");
}
        q_operande
{
	printToString("</SIGMA>");
}
   )
|	#("UNION"
{
	printToString("<UnionQ>");
}
        q_operande
{
	printToString("</UnionQ>");
}
   )
|	#("INTER"
{
	printToString("<InterQ>");
}
        q_operande
{
	printToString("</InterQ>");
}
    )
;

// inherited from grammar POWalker
q_operande :#(B_SUCH
        list_var 
        predicate
        predicate
    )
;

// inherited from grammar POWalker
list_var :{
	printToString("<idList>");
}

(
	#(B_LPAREN
        	list_identifier
	)   
	|	list_identifier 
)

{
	printToString("</idList>");
}
;


