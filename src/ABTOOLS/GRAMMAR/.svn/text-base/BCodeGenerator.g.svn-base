// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	BCodeGenerator.g
//	Date		:	Creation 27/03/2003
//	Descripton	:	Code Generator for Another B Parser written in ANTLR
//	
//	Copyright 2000-2009 Boulanger Jean-Louis
//

// Releases
//  March     2003    v 0.1    Creation
//  June      2003    v 0.2    First student version
//  July      2003    v 0.2.1  First professor version
//                              - Pretty printing
//                              - Modify list_func_call rule
//  June      2004    v 0.2.2  Passage de CodeGenerator a BCodeGenerator
//                             Pretty-Printing
//  Septembre 2004    v 0.2.3
//                             Mise en place d'un debut de generation de code JAVA
//
//  August    2005    v 0.2.4  Preety printing
//

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header 
{
    package ABTOOLS.GRAMMAR;
}

// /**
//  * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
// **/


// Import the necessary classes 
// -----------------------------

{
	import java.io.*;

// Necessaire pour gerer la classe Date pour creer 
// des variables temporelles uniques
	import java.util.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    	import ABTOOLS.ANTLR_TOOLS.*;
    	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.PRINTING.*;
    	import ABTOOLS.CODE_GENERATOR.*;
}

// Define a Tree Walker
//---------------------

class BCodeGenerator extends TreeParser;
options
{
	importVocab 	= B;
    	buildAST 	= false;
    	ASTLabelType 	= "MyNode";
 
// Copied following options from java grammar.
    	codeGenMakeSwitchThreshold = 3;
    	codeGenBitsetTestThreshold = 4;

	k = 1;
}

// Indtroduce some behaviours....

{
// Module description
    	String module = "BCodeGenerator";

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


// Manage the code Generator
	public		int ADA		= 10;
    	public      	int JAVA        = 11;

    	public      	int currenttarget = 0;

    	private static CodeGenerator cg;

 	public void setCodeGenerator ( CodeGenerator newcg)
 	{
 		cg = newcg;
 	}

	public void	selectTarget(int target )
	{
		currenttarget = target;

// Contrainte de conception
// JAVA n'est pas fortiche avec le switch, on ne peut pas mettre de chaine 
// de caractere dans le switch et le case doit etre valuee

		if (target == JAVA) 
			cg = new CG_JAVA();
		else if (target == ADA)
            		cg = new CG_ADA();
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

		if (currenttarget == JAVA)
			return ("JAVA");
		else if (currenttarget == ADA)
			return ("ADA");
		else
		{
			return (null);
		}
	}


	void InitializeModule()
	{
      
	}

	void FinalizeModule()
	{
	
	}
}

// Grammar become HERE
// -------------------

/** 
 *  Root Rule
 **/
composant
:
        (	machine
        |	refinement
        |	implementation
        )
;

/**
 * THREE type of B composants simple name or with one or more parameters
 **/
machine
{
    String tmp = "To Be Defined";
}   :
    #("MACHINE"
         tmp = pparamName
{
    System.err.println(errors.NotImplementation (tmp)); 
}
    )
;

refinement 
{
    String tmp = "To Be Defined";
}   :	
    #("REFINEMENT"
         tmp =pparamName
{
    System.err.println(errors.NotImplementation (tmp)); 
}
    )
;

pparamName	returns [String currentName]
{
    currentName = "To Be Defined";
}   :
    #(B_LPAREN name:B_IDENTIFIER 		
{
	currentName = #name.getText();
} 
        listTypedIdentifier
    )			
|
    name1:B_IDENTIFIER
{
	currentName = #name1.getText();
}
;

implementation  
{
    String tmp = "To Be Defined";
}   :
    #("IMPLEMENTATION"
{
	InitializeModule();
}
        tmp = pparamName
{
    System.out.println("Module a traiter :"+tmp);
	cg.BeginGen(tmp);

}
        clauses
{
    cg.EndGen();
	FinalizeModule();
}
    )
;

clauses
: 	
    (clause)*
;

clause:
        refines 
|      	link
|	    sets
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
        listTypedIdentifier
    )			
|   name1:B_IDENTIFIER
;

listTypedIdentifier:
    #(B_COMMA
        listTypedIdentifier	
        listTypedIdentifier
    )	
|	typedIdentifier
;

typedIdentifier	
{
    String tmp;
}  :
    #(B_INSET
        nameRenamed
        tmp = predicate 
     )	
    |	nameRenamed
;

constraints	
{
    String tmp;
}  :
    #("CONSTRAINTS"
		tmp =	predicate
     )
;

link:
        uses 
    |	includes
    |	sees
    |	extendeds
    |	promotes
    |	imports
;

extendeds	:	
    #("EXTENDS"
        listInstanciation
    )
;

uses:
    #("USES"
            listNames
	)
;

includes:
    #("INCLUDES"
            listInstanciation
     )
;

sees:
    #(tt:"SEES"
        listNames
    )
;

listNames	:	
    #(B_COMMA 
        listNames
        nameRenamed
    )
    |	nameRenamed
;

listInstanciation:	
    #(B_COMMA 
        listInstanciation
        paramRenameValuation
     )
    |	paramRenameValuation
;

paramRenameValuation :	
    #(B_LPAREN 
            paramRenameValuation
            list_New_Predicate
     )
    |	nameRenamed
 		;

imports	:
	#("IMPORTS"
         listInstanciation
     )
;

promotes	:
    #("PROMOTES"
{
	;
}
        listNames
{
	;
}
    )
;

refines		:	
    #("REFINES"				
{
	;
}
        name:B_IDENTIFIER
{
	;
}
    )
;

constants	:	
    #("CONSTANTS"
{
	;
}
        listTypedIdentifier		
{
	;
}
    )
|
    #("CONCRETE_CONSTANTS"
{
	;
}
        listTypedIdentifier
{
	;
}
    )
|
    #("VISIBLE_CONSTANTS"
{
	;
}
        listTypedIdentifier
{
	;
}
    )
|
    #("ABSTRACT_CONSTANTS"
{
	;
}
        listTypedIdentifier
{
	;
}
    )
|
    #("HIDDEN_CONSTANTS"
{
	;
}
        listTypedIdentifier
{
	;
}
    )
;

sets:
    #("SETS"
{
	;
}
        sets_declaration
{
	;
}
    )
;


// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:	
    #(B_SEMICOLON  
        sets_declaration
{
	;
}  
        sets_declaration
    )	
|
    #(B_COMMA
        sets_declaration 
{
	;
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
	;
}
        valuation_set
    )
|	
    name:B_IDENTIFIER
{
   ;
}
;

is_record	:
    #("rec"
{
	;
}
        listrecord
{
	;
}
    )
|
    #("struct"
        listrecord
    )
;

valuation_set	:	
    #(B_CURLYOPEN
{
	;
}
        list_couple
{
	;
}
    )
|
    is_record
|
    #(B_MULT 
        valuation_set
{
	;
}
        valuation_set
    )
|
    #(B_ADD 
        valuation_set
{
	;
}
        valuation_set
    )
|
    #(B_MINUS 
        valuation_set
{
	;
}
        valuation_set
    )
|	B_IDENTIFIER
{
	;
}
    |	basic_sets
;

listrecord	:
    #(B_COMMA 
        listrecord
{
	;
}
        a_record
    )
|	a_record
;

list_couple	:
 	#(B_COMMA 
        list_couple
{
	;
}
        couple 
    )
|	couple
;

couple:
    #(B_MAPLET 
        a_set_value
{
	;
}
        a_set_value
    )
|
    #(B_LPAREN
{
	;
}
        parent_couple
{
	;
}
    )
|	a_set_value
;

parent_couple	:	
    #(B_MAPLET 
        a_set_value
{
	;
}
        a_set_value
    )
|
    #(B_COMMA  
        a_set_value
{
	;
}
        a_set_value
    )
|	a_set_value
;

a_set_value	:
		B_IDENTIFIER
{
	;
}
    |	
    #(mi:B_MINUS 	
            val1:B_NUMBER
{
	;
}
    )
|	val:B_NUMBER
{
	;
}
|	tr:"TRUE"
{
	;
}
|	fa:"FALSE"
{
	;
}
;

a_record
{
    String tmp1;
}  	:
    #(B_SELECTOR 
            name:B_IDENTIFIER
            tmp1 =	predicate
     )
|	tmp1 =	predicate			
;

values		:	
    #(tt:"VALUES"
{
	;
}
        list_valuation
{
	;
}
    )
;

list_valuation	:	
    #(tt:B_SEMICOLON 
        list_valuation 
{
	;
}
        set_valuation
    )
|	set_valuation
;

set_valuation	:
    #(tt:B_EQUAL 
        name:B_IDENTIFIER
{
	;
}
        new_set_or_constant_valuation
    )
;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

new_set_or_constant_valuation
{
    String tmp1;
}
:
        tmp1 =	predicate
;

set_interval_value:	
    #(B_EQUAL 
            B_IDENTIFIER
            interval_declaration
        )
;

interval_declaration
{
    String tmp1;
}
    :
    #(B_RANGE 
			tmp1 =	predicate
			tmp1 =	predicate
        )
;


set_rename_value:	
        #(B_EQUAL 
            B_IDENTIFIER
            B_IDENTIFIER
        )
    ;

properties	
{
    String tmp1;
}  :
    #("PROPERTIES"
			tmp1 =	predicate
     )
;

variables	:	
    #("VARIABLES"           gTypedIdentifier	)
|   #("ABSTRACT_VARIABLES"	gTypedIdentifier	)
|	#("VISIBLE_VARIABLES"	gTypedIdentifier	)
|	#("CONCRETE_VARIABLES"	gTypedIdentifier	)
|	#("HIDDEN_VARIABLES"	gTypedIdentifier	)
;

gTypedIdentifier
{
    String tmp1;
}
:
      #(B_COMMA   gTypedIdentifier  gTypedIdentifier)
    | #(B_INSET
            name1:nameRenamed
{
    cg.DecVar(#name1.getText(),#name1.getBType().toString());
}
			tmp1 = predicate 
      )	
    | (name2:nameRenamed
{
    cg.DecVar(#name2.getText(),#name2.getBType().toString());
}
      )
;

invariant
{
    String tmp;
}
  :
    #("INVARIANT"
         tmp = predicate
     )
;

definitions	:
    #("DEFINITIONS"
         list_def
     )
;

list_def:
    #(LIST_DEF
        list_def 
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
        #(B_DOUBLE_EQUAL 
            paramName
            formalText
        )
        |	B_ASTRING
;

formalText	
{
    String tmp1;
}
  :	
        tmp1 =	predicate
    |	instruction
    |	operation
;

assertions	:	
    #("ASSERTIONS"
            list_assertions
     )
;

list_assertions	
{
    String tmp1;
}
    :
    #(B_SEMICOLON  
          list_assertions
          list_assertions
     )
|	
    tmp1 =	predicate
;

initialisation	:	
    #("INITIALISATION"
{
   cg.Constructor();
   cg.BeginOp();
}
            instruction
{
   cg.EndOp();
}
     )
;

operations	:	
    #("OPERATIONS"
        listOperation
	)
;

listOperation	:	
    #(B_SEMICOLON 
        listOperation
        operation
    )
|	operation
;

operation	:	
    #(OP_DEF
        operationHeader
{
   cg.BeginOp();
}
        instruction
{
   cg.EndOp();
}
    )
;

operationHeader	
{
    String pp = "To Be defined";
}
:	
     #(B_OUT
         pp = opNameParameter
         opparamName[pp]
     )
 |	
    opparamName[""]
;

// For generation we use a specific paramName rule.
// in this case we begin the generation of operation profil name(entrie parameter, 
// and we stop

opparamName [String pOut]
{
    String res  = "";
}
:
    #(B_LPAREN 
        name:B_IDENTIFIER 
        res = opNameParameter
{
    res = cg.constructOPHeader(#name.getText(),res + pOut);
    cg.Constructor(res);
}
    )
|
    (
        name1:B_IDENTIFIER
{
    res = cg.constructOPHeader(#name1.getText(),"" + pOut);
    cg.Constructor(res);
}
    )
;

opNameParameter returns [String out]
{
    String tmp1, tmp2;
                 out = "Not Defined";
}
:
    #(B_COMMA
        tmp1 = opParameters
        tmp2 = opNameParameter
{
    out = cg.concatParam(tmp1, tmp2);
}
    )
|	out = opParameters
;

opParameters returns [String out]
{
    String tmp;
           out = "Not Defined";
}  
:
    #(B_INSET
        n1:nameRenamed
        tmp = predicate 
{
    out = cg.constructDecVar(n1.getText(), n1.getBType().toString());
}
     )	
    |
	(   n2:nameRenamed
{
    out = cg.constructDecVar(n2.getText(), n2.getBType().toString());
}
    )
;

/**
 * The Generalised Substitution Language
 **/

instruction	
{
    String tmp1="";
    String tmp2= "";
    String tmp3= "";
}  
:
    #(PARALLEL 	
            instruction
            instruction
{
    System.err.println(errors.NotAllowedInImp("PARALLEL -- subst ||"));
}
     )
    |
    #(SEQUENTIAL
            instruction
{
    cg.Sequential();
}
            instruction
     )
    |
    #("skip"
{
	cg.Comment(" Skip -- do nothing");
}
    )
|
    #("BEGIN"
{
	cg.BeginOp();
}
        instruction
{
    cg.EndOp();
} 
    )
|
    #("PRE" 
        tmp1 = 	predicate
{
    System.err.println(errors.NotAllowedInImp("PRE -- subst p | I"));
}
        instruction
    )
|
    #("ASSERT"
        tmp1 =	predicate
        instruction
    )
|	
    #("IF"
        tmp1 =	predicate
        branche_then
        (branche_elsif)*
        (branche_else)?
    )
|
    #("CHOICE"
        list_or
{
    System.err.println(errors.NotAllowedInImp("CHOICE -- subst I [] J"));
}
    )
|
    #("CASE"
        tmp1 =	predicate 
{
	tmp2 = "tmp"; 	//la variable debe comenzar por letra y no por numero
	Date fec = new Date(); 
	int  dat=fec.getDay();
	int hor=fec.getHours();
	int min=fec.getMinutes();
	int sec=fec.getSeconds();
//formato de la variable: tmpXXXXXXXX

//	tmp2= tmp2 +dat+hor+min+sec;

//	tmp3.append(" "); //  aqui habra que escribir el tipo que devuelve predicate
//	tmp3.append( tmp2+" = " +tmp1+";");


}
				branche_either
				(branche_or)*
				(branche_else)?
{

}
			)
|
    #("ANY" 
        listTypedIdentifier 
        tmp1 =	predicate
        instruction
{
    System.err.println(errors.NotAllowedInImp("CHOICE -- subst I [] J"));
}
    )
|
    #("LET"
{
	;
}
				listTypedIdentifier 
{
	;
}
				list_equal 
{
	;
}
				instruction
{
	;
}
				)
			|
			#("SELECT"
{
	;
}
			tmp1 =	predicate 
{
	;
}
				instruction
{
	;
}
				(branche_when)*
				(branche_else)?
								
{
	;
}
			)
			|
			#("WHILE"
{
	;
}
			tmp1 =	predicate
{
	;
}
				instruction
				variant_or_no
{
	;
}
			)
|
    #("VAR"
        listTypedIdentifier
{
	;
}
        instruction
    )
|	simple_affect
;

list_or:
    #("OR"
        list_or
        instruction
    )
|       instruction
;


branche_when
{
    String tmp1;
}  	:	#("WHEN"
{
	;
}
			tmp1 =	predicate
{
	;
}
				instruction
			)
		;

branche_either
{
    String tmp1;
}  :
    #("EITHER"
		tmp1 =	predicate 
        instruction 		
     )
;

branche_or
{
    String tmp1;
}  	:
    #("OR"
			tmp1 =	listPredicate
            instruction
        )
;

branche_then:
	#("THEN"
            instruction
     )
;

branche_else:	
    #("ELSE"
           instruction
     )
;

branche_elsif
{
    String tmp1;
}  :
    #("ELSIF"
			tmp1 =	predicate
            instruction
        )
;

variant_or_no
{
    String tmp1,tmp2;
}  
    :
    #("VARIANT"
        #("INVARIANT"
            tmp1 =	predicate
         )
            tmp2 =	predicate 
        )
    |
    #("INVARIANT"
        #("VARIANT"
			tmp1 =	predicate 
         )
            tmp2 = 	predicate
     )
;

list_equal	: 	#(tt:B_AND 	
				list_equal
{
	;
}
				an_equal
			)
			|	an_equal	
		;


an_equal	
{
    String tmp1;
}  :	
    #(B_EQUAL 	
            B_IDENTIFIER
            tmp1 =	predicate
        )
;

// afin de prendre en compte 
//	soit f (x,y,z)
//	soit f (x) [z]
//	soit f~(x) [z]
//	soit f (x)~

func_call	: 	
    #(B_TILDE
        func_call
{
	;
}
    )
|
    #(APPLY_TO
        func_call
{
	;
} 
        list_New_Predicate
{
	;
}
    )
|
    #(t3:B_LPAREN
        func_call
{
	;
}
        list_New_Predicate
{
	;
}
    )
|
    #(t4:B_QUOTEIDENT
        func_call 			
{
	;
}
        func_call
    )
|
    nameRenamed 
;

func_param	:
    list_New_Predicate
;

list_New_Predicate:	
    #(B_COMMA 
        list_New_Predicate
        new_predicate
    )
|	new_predicate
;

new_predicate
{
    String tmp1;
}  :
    #(B_SEMICOLON 
        new_predicate
{
	;
}
        tmp1 =	predicate
    )
|
    #(B_PARALLEL  
        new_predicate
{
	;
}
        tmp1 =	predicate
    )
|
    tmp1 =	predicate
;

/**
 * Ne pas utiliser de fonction d'impression pour le renommage ....
 **/

nameRenamed	:	
    B_IDENTIFIER
|   #(B_POINT 
        nameRenamed
        nameRenamed
    )
;

nameRenamedDecorated:
    #(B_CPRED 
        nameRenamed
    )
|   nameRenamed
;

nameRenameDecoratedInverted:
    #(tt:B_TILDE 
        nameRenamedDecorated
    )
|	nameRenamedDecorated
;

list_identifier	:
    #(B_COMMA
        list_identifier
        B_IDENTIFIER
    )
|	B_IDENTIFIER
;


listPredicate returns [String st]
{
 st= "";
 String tmp1 = "";
 String tmp2 = "";

}
:
    #(B_COMMA
		 	tmp2 =	listPredicate
			tmp1 = 	predicate
        )
|   tmp1 =	predicate
;

a_func_call	:	
    #(A_FUNC_CALL
        afc
    )	
;

afc
{
   String tmp1;
}
  :
    #(FUNC_CALL_PARAM
            afc
            tmp1=	listPredicate
     )
|    #(t1:B_QUOTEIDENT
            afc
            afc
        )
|	nameRenamed
;

list_func_call	:	
    #(B_COMMA
            list_func_call
            list_func_call
     )
    |	a_func_call
;



// specific afc for simple_affect
afc_bis
{
   String tmp1;
}
    :
    #(FUNC_CALL_PARAM
            afc_bis
            tmp1 = listPredicate
{
    System.err.println(errors.NotAllowedInImp("FUNC_CALL_PARAM"));
}
     )
|
    #(B_QUOTEIDENT
            afc_bis
            afc_bis
     )
|	nameRenamed
;

// Quatre cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a,b,c :( P )
//	soit	a,b,c :: P
//	soit	P.R (xx,yy)
//	soit 	P.R
simple_affect
{
    String tmp1;
}
    :
    #(B_SIMPLESUBST 	
            var:afc_bis
			tmp1 =	predicate
{
   cg.Affectation(#var.getText(),tmp1);
}
     )
|    #(B_OUT	 	
        list_func_call
        func_call
    )
|   #(INSET
        list_func_call
        tmp1 = predicate
{
    System.err.println(errors.NotAllowedInImp("INSET -- subst x:(A)"));
}
    )
|   #(B_BECOME_ELEM
        list_func_call
        tmp1 = predicate
{
    System.err.println(errors.NotAllowedInImp("B_BECOME_ELEMENT -- subst x::A"));
}
    )
|   a_func_call
;



/** 
 *  PREDICATE 
 **/

predicate returns [String st]
{
                st = "";
 String tmp1 = "Not Defined";
 String tmp2 = "Not Defined";
}:	
    #(B_AND
        tmp1 = predicate
        tmp2 = predicate
{
	st = cg.And(tmp1, tmp2);
}
    )	
|   #("or"
        tmp1 = predicate
        tmp2 = predicate

{
	st = cg.Or(tmp1, tmp2);
}	
        )
|    #(B_IMPLIES
        tmp1 =	predicate
        tmp2 =	predicate
{
    System.err.println(errors.NotAllowedInImp("Imply -- pred x => y"));
}
    )
|    #(B_EQUIV
        tmp1 =	predicate
        tmp2 =	predicate
{
    System.err.println(errors.NotAllowedInImp("Equivalent -- pred x <=> y"));
}
    )
|   #(B_MULT
        tmp1 =	predicate
        tmp2 =	predicate	
{
	st = cg.Mult(tmp1, tmp2);
}
    )
|	#(t6:B_POWER
        tmp1 =	predicate
        tmp2 = 	predicate
{
	st = cg.Power(tmp1, tmp2);
}
    )
|   #(t7:B_DIV
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Div(tmp1, tmp2);
}
    )
|	#(t8:"mod"
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Mod(tmp1, tmp2);
}
    )
|	#(UNARY_ADD
			st = predicate
    )
|   #(UNARY_MINUS
        tmp1 =	predicate
{
	st = cg.Minus("", tmp1);
}
    )
| 	#(B_ADD 
        tmp1 =	predicate
        tmp1 =	predicate
{
	st = cg.Plus(tmp1, tmp2);
}
    )
|	#(B_MINUS
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Minus(tmp1, tmp2);
}
    )
|	#(B_EQUAL
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Equal(tmp1, tmp2);
}
    )
|	#(B_LESS
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Less(tmp1, tmp2);
}			
    )
|	#(B_GREATER
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.Greater(tmp1, tmp2);
}
    )
|	#(B_NOTEQUAL
        tmp1=	predicate
        tmp2 =	predicate
{
	st = cg.NotEqual(tmp1, tmp2);
}
    )
|	#(B_LESSTHANEQUAL
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.LessEqual(tmp1, tmp2);
}
    )
|	#(B_GREATERTHANEQUAL
        tmp1 =	predicate
        tmp2 =	predicate
{
	st = cg.GreaterEqual(tmp1, tmp2);
}
    )
|	#(B_INSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_NOTINSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_SUBSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_NOTSUBSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_STRICTSUBSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_NOTSTRICTSBSET
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_CONCATSEQ
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_PREAPPSEQ
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_APPSEQ
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_PREFIXSEQ
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_SUFFIXSEQ
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_RELATION		
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_PARTIAL
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_TOTAL
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_PARTIAL_INJECT
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_TOTAL_INJECT
        tmp1 =	predicate
        tmp2 =	predicate
    )
|	#(B_PARTIAL_SURJECT
        tmp1 =	predicate
        tmp2 = 	predicate
    )
|   #(B_TOTAL_SURJECT
        tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_BIJECTION
 		tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_DOMAINRESTRICT
		tmp1 =	predicate
        tmp2 =	predicate
    )
|   #(B_RANGERESTRICT
		tmp1 =	predicate
		tmp2 =	predicate
    )
|   #(B_DOMAINSUBSTRACT
		tmp1 =	predicate
		tmp2 =	predicate
    )
|   #(B_RANGESUBSTRACT		
		tmp1 =	predicate 
		tmp2 =	predicate
    )
|   #(B_OVERRIDEFORWARD		
		tmp1 =	predicate 
		tmp2 =	predicate
    )
|   #(B_OVERRIDEBACKWARD 	
		tmp1 =	predicate
		tmp2 =	predicate
    )
|   #(B_RELPROD 		
		tmp1 =	predicate 
		tmp2 =	predicate
    )
|   #(B_UNION 
		tmp1 =	predicate
		tmp2 =	predicate
    )
|   #(B_INTER
		tmp1 =	predicate
		tmp2 =	predicate
    )
|	#(B_MAPLET
		tmp1 =	predicate
		tmp2 =	predicate
    )
|	#(LIST_VAR
		tmp1 =	 predicate
		tmp2 =	predicate
    )
|	#(B_NOT	
		tmp1 =	predicate
    )
|	#(B_RAN
		tmp2 =	predicate
    )
|	#(B_DOM
		tmp1 =	predicate
    )
|	st = cset_description
;

cset_description returns [String st]
{
    String tmp1,tmp2;
    st = "Not Defined";

}  :
        basic_sets 
    |	st = cbasic_value
    |	#("bool"
            tmp1 =	predicate
        )
    |   #(B_BRACKOPEN
            tmp1 =	listPredicate
        )
    |   #(B_RANGE 		
		tmp1 =	predicate
		tmp2 =	predicate
        )
    |	B_EMPTYSET
    |	#(B_CURLYOPEN
            tmp1 = cvalue_set
        )
    |   B_SEQEMPTY
    |	is_record 
    |	quantification
    |	q_lambda
;

// Dans un ensemble il peut y avoir 
cvalue_set	 returns [String st]
{
    String tmp1, tmp2;
    st = "Not Defined";
}  
    :
    #(B_SUCH
            list_var
            tmp1 =	 predicate
        )
    |	#(B_COMMA
            tmp2 =      cvalue_set
            tmp1 =	predicate
        )
    |   tmp1 =	predicate
;

cbasic_value returns [String st] 
{
    String tmp1,tmp2;
    st = "Not Defined";
}
    :
        B_ASTRING
    |	t2:B_NUMBER
{
	st = #t2.getText();
}
    |	#(B_TILDE
            tmp1 =	predicate
        )
    |	nameRenamedDecorated
    |	#(B_LPAREN
            tmp1 =predicate
            list_New_Predicate
    )
    |	#(PARENT
            pred_func_composition
        )
    |	#(B_QUOTEIDENT 
            tmp1 =	predicate
            tmp2 =	predicate 		
        )
    |	#(APPLY_TO
            tmp1 =	predicate
            tmp2 =	 predicate
        )
    |	"TRUE"
{
    st = cg.genereTrue();
}
    |	"FALSE"
{
    st = cg.genereFalse();
}
;

pred_func_composition
{
    String tmp1;
}  
    :
    #(B_SEMICOLON
            pred_func_composition
            tmp1 =	predicate
        )
    |	#(t2:B_PARALLEL  
            pred_func_composition
            tmp1=	predicate
        )
    |	#(t3:B_COMMA	 
            pred_func_composition
            tmp1 =	predicate
        )
    |	    tmp1 =	predicate
;

basic_sets:
        "INT"
    |	"INT1"
	|	"INTEGER"
    |	"INTEGER1"
    |	"BOOL"
    |	"NAT"
    |	"NAT1"
	|	"NATURAL"
	|	"NATURAL1"
	|	"STRING"
    ;

quantification	
{
    String tmp1;
}
    :
    #(B_FORALL
        list_var
        tmp1 =	predicate
    )
|	#(B_EXISTS
        list_var
        tmp1 =	predicate
    )
;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda	:	#(B_LAMBDA
{
	;
} 
					q_operande
				)
			|	#("PI"
{
	;
} 
					q_operande
				)
			|	#("SIGMA"
{
	;
}
					q_operande
				)
			|	#(t4:"UNION"
{
	;
}
					 q_operande
				)
			|	#("INTER"
{
	;
}
					 q_operande
				)
		;

q_operande	
{
    String tmp1,tmp2;
}
    :
    #(t1:B_SUCH
            list_var 
{
	;
}
			tmp1 =	predicate
{
	;
}
			tmp2 =	predicate
{
	;
}
			)
;

list_var	:	
    #(B_LPAREN
{
	;
}
        list_identifier
{
	;
}
    )
|
{
	;
}
        list_identifier 
;




// END Of FILE BCodGenerator.g

















