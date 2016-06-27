
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	Normalize.g
//	Date		:	Creation 03/09/2010
//	Descripton	:	A pre-processor based on Tree Walker for Another B Tools written
//	 			with ANTLR
//	
//	Copyright 2001-2010 Boulanger Jean-Louis
//


// Releases
//	September 	2001	v 0.1
//	 	- Creation
//		- Linearise exp of type  xx , yy :  AA * BB
//		- Linearise substitution xx , yy := AA , BB
//
//	April		2002	v 0.2	
//		- Add Definition Expansion
//
//	May	  	2002	v 0.2.1
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  October     2002    v 0.2.2
//      - Pretty Printing
//
//  March       2003    v 0.2.3
//      - Pretty Printing 
//      - Bug correction
//      - Finalizing the normalisation.
//
//  July        2003    v 0.2.4
//      - Pretty Printing
//      - Correct a BUG in definition clause treatment
//      - Retry THEN in SELECT structure
//
//  August      2003    v 0.2.5
//      - Pretty Printing 
//      - Add the local variable module
//      - change "variant_or_no" rule
//      - Introduce SUBST_DEF and EXP_DEF Tolen for help treewalking.
//
//  September   2004    v 1.0
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

{
// General packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;
	import antlr.ASTFactory;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*; 
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class Normalize extends TreeParser;
options
{
        importVocab 	= BSystem;	// The language is imported and not exported
        buildAST 	= true;		// We write, we built a tree
        ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
        codeGenMakeSwitchThreshold = 3;
        codeGenBitsetTestThreshold = 4;

        k = 1;				// On a besoin d'un seul token 
}

// Indtroduce some behaviours....
{
	String module = "Normalize.g";

//	Data Temporary
	MyNode 	T1, T2, T3, T4;
	MyNode 	save[] 			= 	new MyNode[15];

	int 	save_cmp 		= 	1;

//	Definition Name and parameter
	AST	def_name		= 	null;
	AST	def_param		= 	null;
	AST	def_body		= 	null;
	boolean def			= 	false;

//	Abstract World or NOT 
	boolean abstract_m 		= 	true;

// Instance d'un GSL
	GSL uu = new GSL();


//
    boolean modify = false ;

// Error Messages
// ==============
	public ErrorMessage errors = new ErrorMessage();

	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}

// DEBUGGING
// =========

// For enabling or disabling the debug MODE
	private static DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}

// Name of current file
// ====================
	String filename;

	public void setFileName (String name)
	{
		filename = name;
	}

// Gestion de la table des definitions
// ===================================
	ListDefinition listdefinition;

	public void setListDefinition (ListDefinition ldef)
	{
		listdefinition = ldef;
	}

	public ListDefinition getListDefinition ()
	{
		return listdefinition;
	}

} // fin des fonctions communes

// Grammar become HERE
// -------------------

/**
 *	La regle "composant" permet de definir le point d'entree pour realiser le typage.
 **/

// Root Rule
composant	:
{    
   uu.setASTNodeType(MyNode.class.getName());
}
	#("MACHINE"		{abstract_m = true ;}	paramName clauses )
    |   #("REFINEMENT" 		{abstract_m = true ;}	paramName clauses )
    |   #("IMPLEMENTATION"	{abstract_m = false;}	paramName clauses )
    |   #("SYSTEM"		{abstract_m = true ;}	paramName clauses )
;

clauses		:
        (
            clause
        )*
;

clause	:
        #("CONSTRAINTS"		    predicate			)
    |	#("EXTENDS"		    listInstanciation	)
    |	#("USES"	  	    listNames		)
    |	#("INCLUDES"		    listInstanciation	)
    |	#("SEES"		    listNames		)
    |	#("IMPORTS"		    listInstanciation	)
    |	#("PROMOTES"	  	    listNames		)
    |	#("REFINES"  		    B_IDENTIFIER	)
    |	#("CONSTANTS"		    listTypedIdentifier	)
    |	#("CONCRETE_CONSTANTS"	    listTypedIdentifier	)
    |	#("VISIBLE_CONSTANTS"	    listTypedIdentifier	)
    |	#("ABSTRACT_CONSTANTS"	    listTypedIdentifier	)
    |	#("HIDDEN_CONSTANTS"	    listTypedIdentifier	)
    |	#("SETS"	  	    sets_declaration	)
    |	#("VALUES"		    list_valuation	)
    |	#("PROPERTIES"		    predicate		)
    |	#("VARIABLES" 		    listTypedIdentifier )
    |	#("ABSTRACT_VARIABLES" 	    listTypedIdentifier )
    |	#("VISIBLE_VARIABLES"	    listTypedIdentifier	)
    |	#("CONCRETE_VARIABLES"	    listTypedIdentifier	)
    |	#("HIDDEN_VARIABLES"	    listTypedIdentifier	)
    |	#("INVARIANT"		    predicate		)
    |	#("DEFINITIONS" 	    
{
            def = true;
} 
                                list_def	
{
            def = false;
} 
        )
    |	#("INITIALISATION"  	    instruction			)
    |	#("OPERATIONS"  	    listOperation		)
    |	#("ASSERTIONS" 	 	    list_assertions		)
    |	#("EVENTS"      	    listOperation		)
    |	#("MODALITIES" 		    predicate			)
    |	#("DYNAMICS"	 	    predicate_with_prime)
    ;

predicate_with_prime :	
	"tutu"
;

/**
 * This rule is used for defined all Name with or without parameters
 **/

paramName	:		
                #(bb:B_LPAREN	
			n1:B_IDENTIFIER
			l1:listTypedIdentifier
{
    String ss = #n1.getText();
    System.out.println("paramName =" + ss);

	#bb.memorizeOpname(#n1.getText());
    #n1.memorizeOpname(#n1.getText());

//	For definition, we prepare the work
	def_name 	= (MyNode) astFactory.dupTree(#n1);
	def_param	= (MyNode) astFactory.dupTree(#l1);
}
		)
		|	n2:B_IDENTIFIER
{
	#n2.memorizeOpname(#n2.getText());

//	For definition, we prepare the work
	def_name 	= (MyNode) astFactory.dupTree(#n2);
	def_param	= null;
}
;

listTypedIdentifier:
     #(B_COMMA	listTypedIdentifier	listTypedIdentifier	)	
|               typedIdentifier
;

/** 
 * La regle "typedIdentifier" permet de prendre en compte l'extension 
 * de typage explicite de B'
 **/

typedIdentifier		:
    #(B_INSET	nameRenamed	predicate	)	
|	        nameRenamed
;

listNames	:
        #(B_COMMA	listNames	listNames)
    |   nameRenamed
    ;

listInstanciation:
        #(B_COMMA 	listInstanciation	listInstanciation)
    |	            	paramRenameValuation
    ;

paramRenameValuation 	:
        #(B_LPAREN	paramRenameValuation	list_New_Predicate	)
    |	            	nameRenamed
    ;

// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:	
        #(B_SEMICOLON  	sets_declaration   sets_declaration)
    | 	#(B_COMMA      	sets_declaration   sets_declaration)
    |	                set_declaration	
    ;

set_declaration :
	#(B_EQUAL  	B_IDENTIFIER	valuation_set	)
|	            	B_IDENTIFIER
;

is_record 	:
        #("rec"  	listrecord	)
    |	#("struct"  	listrecord	)
;

valuation_set	:
        #(B_CURLYOPEN 	list_couple	)
    |	is_record
    |	#(B_RANGE	a_set_value	a_set_value)
    |	#(B_MULT	valuation_set	valuation_set)
    |	#(B_ADD 	valuation_set	valuation_set)
    |	#(B_MINUS 	valuation_set	valuation_set)
    |	B_IDENTIFIER
    |	basic_sets
    ;

listrecord	:
        #(B_COMMA	listrecord	listrecord)
    |	a_record
    ;

list_couple	:
 	#(t1:B_COMMA 	list_couple  	list_couple)
    |	couple
    ;

couple		:
        #(B_MAPLET 	a_set_value	a_set_value)
    |	#(B_LPAREN 	parent_couple)
    |	a_set_value
    ;

parent_couple:	
        #(B_COMMA 	a_set_value	a_set_value)
    |	#(B_MAPLET 	a_set_value	a_set_value)
    ;

a_set_value	:	
        B_IDENTIFIER
    |	#(UNARY_MINUS	B_NUMBER)
    |	#(B_MINUS	B_NUMBER)
    |	B_NUMBER
    |	"TRUE"
    |	"FALSE"
    ;

/**
 *	La regle "a_record" permet de definir le type de chaque champs
 **/
a_record	:
        #(B_SELECTOR	B_IDENTIFIER 	predicate)
    |	                                predicate
    ;

list_valuation	:
	#(B_SEMICOLON	list_valuation 	list_valuation)
|	                set_valuation
;

set_valuation	:
	#(B_EQUAL	B_IDENTIFIER	predicate)
;

set_interval_value:
	#(B_EQUAL	B_IDENTIFIER 	interval_declaration)
;

interval_declaration 	:
	#(B_RANGE	predicate	predicate)
;

list_def	:	
        #(LIST_DEF	list_def	list_def)
|                 	definition
;

/**
 * Attention un cas, one case
 *	- Une definition
 * Le cas lie a l'acces a un fichier de definition a ete regle dans la phase de parsing
 **/

definition	:	
        #(B_DOUBLE_EQUAL   paramName  body:formalText
{
	ADefinition adef = new ADefinition(def_name.getText(), def_param, (MyNode) astFactory.dupTree(#body.getFirstChild()));
	listdefinition.Add_AM ( adef );
}
        )   
;

formalText	:
    	#(EXP_DEF   predicate)
|	#(SUBST_DEF instruction )
/*    |	operation */
;


list_assertions	:
    #(B_SEMICOLON	list_assertions	list_assertions)
|   predicate
;

listOperation	:
    #(B_SEMICOLON
        listOperation
        listOperation
    )
|   operation
;

/* Two cases */
operation	:
#(OP_DEF
(
	(
                            #(tt:B_OUT
								listTypedIdentifier
								pp:paramName
{
	// Memorization pour introduction dans la table des symboles
	// ce n'est pas simple car les parametres de sortie sont introduit 
	// avant le nom de l'operation

 	#tt.memorizeOpname( #pp.getOpname() ); 
}
							)
							instruction
						)
					|
						(
	                        paramName
							instruction
						)
					)
				)
		;


/**
 * the Generalised Substitution Language
 **/

instruction:
                #(PARALLEL		instruction	    instruction	)
			|	#(SEQUENTIAL	instruction		instruction	)
			|	"skip" 
			|	#("BEGIN"		instruction 				)
			|	#("PRE"		    predicate		instruction	)
			|	#("ASSERT"		predicate	 	instruction	)
			|	#("IF" 	        predicate		branche_then		(branche_elsif)*		(branche_else)?)
			|	#("CHOICE"	    list_or 		            )
			|	#("CASE"		predicate		        branche_either		(branche_or)*		    (branche_else)?)
			|	#("ANY" 		listTypedIdentifier		predicate		    instruction)
			|	#("LET"		    listTypedIdentifier		list_equal	 	    instruction)
			|	#("SELECT"		predicate		        instruction 		(branche_when)*		(branche_else)?)
			|
// Deux fois la clause variant_or_no pour traiter INVARIANT et VARIANT
				#("WHILE"
                    			predicate
					instruction
					variant_or_no
				)
			|	#("VAR"
					listTypedIdentifier
					instruction
				)
			|	simple_affect

		;


list_or		:
	#("OR"  list_or instruction)
|	                instruction
;

branche_when	:
    #("WHEN"
        predicate
        instruction
    )
;

branche_either	:
    #("EITHER"
        predicate
        instruction
    )
;

branche_or	    :
    #("OR"
        predicate
        instruction
    )
;

branche_then	:
    #("THEN"
        instruction
    )
;

branche_else	:
    #("ELSE"
        instruction
    )
;

branche_elsif	:
    #("ELSIF"
        predicate
        instruction
    )
;

variant_or_no	:	
            #("VARIANT"
				#("INVARIANT"
					predicate
				)
				predicate 
			)
        |
			#("INVARIANT"
				#("VARIANT"
					predicate 
				)
				predicate
			)
;

list_equal	:
    #(B_AND	list_equal	list_equal	)
|	          an_equal
;

an_equal	:
    #(B_EQUAL
        B_IDENTIFIER
        predicate
    )
;

/**
 * afin de prendre en compte 
 *	soit f (x,y,z)
 *	soit f (x) [z]
 *	soit f~(x) [z]
 *	soit f (x)~
 **/
func_call		: 
	#(B_TILDE	    	func_call				        )
|	#(APPLY_TO 	    	func_call	list_New_Predicate	)
| 	#(B_LPAREN	    	func_call 	list_New_Predicate	)
|	#(B_QUOTEIDENT		func_call	func_call		    )
|	                	nameRenamed
;

func_param :
    list_New_Predicate
;

list_New_Predicate	:
    #(B_COMMA
        list_New_Predicate	
        new_predicate
    )
|   new_predicate
;

new_predicate		:	
        #(B_SEMICOLON 	new_predicate	predicate	)
    |	#(B_PARALLEL	new_predicate	predicate	)
    |					predicate
;

nameRenamed 	:
	name:B_IDENTIFIER 
{
	ADefinition ll = listdefinition.lookupName(name.getText());
	if (ll != null)
	{
		def_body 	= (MyNode) astFactory.dupTree(ll.getBody()) ;
		def_param	= (MyNode) astFactory.dupTree(ll.getParam()) ;

		#nameRenamed 	= (MyNode) astFactory.dupTree(ll.getBody());
        	modify          = true;  
	}
	else
		#nameRenamed 	= #name;
}
|	#(B_POINT
		nameRenamed
		B_IDENTIFIER
	)
;

nameRenamedDecorated 	:	
	#(B_CPRED	nameRenamed	)
|			nameRenamed
    ;

listPredicate	:
//	#(B_COMMA	listPredicate	listPredicate	)
//    |	
    #(ELEM_SET	listPredicate	listPredicate	)
    |	        predicate
;

a_func_call	:
	#(A_FUNC_CALL
            a:afc
{
//             if (modify = true)
//             {
//                 ## = #a;
//                 modify = false;
//             }
}
        )
    |   afc
    ;

afc 	:
        #(FUNC_CALL_PARAM	afc	listPredicate	)
|	#(B_LPAREN		afc	listPredicate)
|	#(B_QUOTEIDENT		afc	afc		)
|	nameRenamed
;

list_func_call	:
	#(B_COMMA	list_func_call	a_func_call	)
|	               	a_func_call
;



/**
 * Cinq cas 
 *	soit 	a,b,c := f(), g(), h()
 *	soit	a,b,c <-- f(x)
 *	soit	a,b,c :( P )
 *	soit	a,b,c :: P
 *	soit	P.R (xx,yy)	ou sans parametre	P.R
 **/

// September 2001
// Dans le cas a,b,c := 1,2,3 on veut normaliser => expansion
simple_affect	:

!	#(B_SIMPLESUBST	
{
System.out.println("SIMPLE_AFFECT >>");
}
		lf:list_func_call
		lp:predicate
{
	MyNode a1 	= 	(MyNode) astFactory.dupTree(#lf) ;
	MyNode a2 	= 	(MyNode) astFactory.dupTree(#lp) ;
	MyNode temp 	= 	#(#B_SIMPLESUBST, #a1, #a2);

	#simple_affect	=	affectation(#temp);
System.out.println("SIMPLE_AFFECT <<");
}
	)
|   	#(B_OUT		    	list_func_call	func_call	)
|	#(INSET		    	list_func_call	predicate	)
|	#(B_BECOME_ELEM		list_func_call	predicate	)
|	                   	a_func_call 						//_quoted a verifier 	BJL A VOIR
;

affectation ! returns [MyNode res]
{
	res = null;
}
	:
!	#(B_SIMPLESUBST	
		lf:list_func_call
		lp:predicate
{
System.out.println("AFFECTATION >>");

	getAPred(#lp);
	getAFunc(#lf);

System.out.println("list variable  : "+ #lf.toStringList());
System.out.println("list predicate : "+ #lp.toStringList());

System.out.println("v1 : "+ #T2.toStringList());
System.out.println("p1 : "+ #T4.toStringList());

	MyNode a1 	    = (MyNode) astFactory.dupTree(#T2) ;
	MyNode a2 	    = (MyNode) astFactory.dupTree(#T4) ;
	MyNode temp 	= #(#B_SIMPLESUBST, #a1, #a2);
	save[save_cmp]	= (MyNode) astFactory.dupTree(#temp) ;

 System.out.println("Il y a "+save_cmp+" et on a Temp ="+#temp.toStringList());

	if (T1 == null && T3 == null)
	{
		#res = (MyNode) astFactory.dupTree(#save[save_cmp]);
	}
	else if (T1 != null && T3 != null)
	{
		MyNode a3 	    = (MyNode) astFactory.dupTree(#T1) ;
		MyNode a4 	    = (MyNode) astFactory.dupTree(#T3) ;
		MyNode tmp  	= #(#B_SIMPLESUBST, #a3, #a4);

		save_cmp 	    = save_cmp+1;
		MyNode tmp1 	= affectation(#tmp);
		save_cmp 	    = save_cmp-1;

		if (abstract_m == true)
			#res 		= #(#[PARALLEL ,"||"], #tmp1,#save[save_cmp] );
		else
			#res 		= #(#[SEQUENTIAL,";"], #tmp1,#save[save_cmp] );
	}
	else
 	{ 
		#res = (MyNode) astFactory.dupTree(#save[save_cmp]);
		System.out.println(errors.ListIncompleted(lf.getLineNum()));
	}

System.out.println("AFFECTATION <<");
}
                )
		;

getAFunc	:
//        (B_COMMA list_func_call a_func_call)=>
	#(ELEM_SET  
		a1:list_func_call
		r1:a_func_call
{
	T1 = (MyNode) astFactory.dupTree(#a1);
	T2 = (MyNode) astFactory.dupTree(#r1);
}
	)
|	#(B_COMMA  
		a10:list_func_call
		r10:a_func_call
{
	T1 = (MyNode) astFactory.dupTree(#a10);
	T2 = (MyNode) astFactory.dupTree(#r10);
}
	)
|	#(LIST_VAR  
		a11:list_func_call
		r11:a_func_call
{
	T1 = (MyNode) astFactory.dupTree(#a11);
	T2 = (MyNode) astFactory.dupTree(#r11);
}
	)
|	a2:a_func_call
{
	T1 = null;
	T2 = (MyNode) astFactory.dupTree(#a2);
}
;

getAPred 	:
//    (LIST_VAR predicate) =>
    #(LIST_VAR
            p1:predicate	//ml_var
            r1:predicate	// nameRenamedDecorated 
{
	T3 = (MyNode) astFactory.dupTree(#p1);
	T4 = (MyNode) astFactory.dupTree(#r1);
}
)
	|
//		(B_COMMA predicate) =>
	    #(B_COMMA
            p11:predicate	//ml_var
            r11:predicate	// nameRenamedDecorated 
{
	T3 = (MyNode) astFactory.dupTree(#p11);
	T4 = (MyNode) astFactory.dupTree(#r11);
}
)
|    p3:predicate	//nameRenamedDecorated
{
	T3 = null;
	T4 = (MyNode) astFactory.dupTree(#p3);
}
;

getATerm	:
//        (B_MULT predicate predicate) =>
    #(B_MULT  
            a1:expression_typage 
            r1:cset_description
{
	T1 = (MyNode) astFactory.dupTree(#a1);
	T2 = (MyNode) astFactory.dupTree(#r1);
}
				)
|	#(PRODSET
            a11:expression_typage 
            r11:cset_description
{
	T1 = (MyNode) astFactory.dupTree(#a11);
	T2 = (MyNode) astFactory.dupTree(#r11);
}
)
|
        a2:expression_typage
{
	T1 = null;
	T2 = (MyNode) astFactory.dupTree(#a2);
}
;

inset ! returns [MyNode res]
{
	res = null;
}
	:
!	#(B_INSET	
		lf:ml_var
		lp:expression_typage
{
System.out.println("INSET >>");

	getAPred(#lf);

System.out.println("list variable: "+ #lf.toStringList());
//System.out.println("v1 : "+ #T3.toStringList());
System.out.println("v2 : "+ #T4.toStringList());

	MyNode a1 	= (MyNode) astFactory.dupTree(#T4) ;

	getATerm(#lp);

System.out.println("list predicat: "+#lp.toStringList());
//System.out.println("p1 : "+ #T1.toStringList());
System.out.println("p2 : "+ #T2.toStringList());

	MyNode a2 	= (MyNode) astFactory.dupTree(#T2) ;

	MyNode temp 	= #(#B_INSET, #a1, #a2);

System.out.println("Il y a "+save_cmp+"et on a Temp ="+#temp.toStringList());

	save[save_cmp]	= (MyNode) astFactory.dupTree(#temp) ;
 
	if (T1 == null && T3 == null)
	{
		#res = (MyNode) astFactory.dupTree(#save[save_cmp]);
	}
	else if (T1 != null && T3 != null)
	{
		MyNode a3 	    = (MyNode) astFactory.dupTree(#T3) ;
		MyNode a4 	    = (MyNode) astFactory.dupTree(#T1) ;
		MyNode tmp  	= #(#B_INSET, #a3, #a4);

		save_cmp 	    = save_cmp+1;
		MyNode tmp1 	= inset(#tmp);
		save_cmp 	    = save_cmp-1;
		#res 		    = #(#[B_AND,"&"], #tmp1, #save[save_cmp] );
	}
	else
 	{ 
		#res = (MyNode) astFactory.dupTree(#save[save_cmp]);
		System.out.println(errors.ListIncompleted(lf.getLineNum()));
	}

System.out.println("INSET <<");
}
		)
;


expression_typage :
    #(B_MULT    expression_typage	cset_description)
|   #(PRODSET   expression_typage	expression_typage)

|	#(B_RELATION		predicate	predicate	)
|	#(B_PARTIAL			predicate	predicate	)
|	#(B_TOTAL			predicate	predicate	)
|	#(B_PARTIAL_INJECT	predicate	predicate	)
|	#(B_TOTAL_INJECT	predicate	predicate	)
|	#(B_PARTIAL_SURJECT	predicate	predicate	)
|	#(B_TOTAL_SURJECT	predicate	predicate	)
|	#(B_BIJECTION		predicate	predicate	)

|	cset_description
;
    
/** 
 * Clause Predicate
 **/

predicate	:
    #(B_AND			        predicate	predicate	)
|	#("or" 			        predicate	predicate	)
|	#(B_IMPLIES		        predicate	predicate	)
|	#(B_EQUIV		        predicate	predicate	)
|	#(B_MULT		        predicate	predicate	)
|	#(PRODSET		        predicate	predicate	)
|	#(B_POWER		        predicate	predicate	)
|	#(B_DIV		        	predicate	predicate	)
|	#("mod"			        predicate	predicate	)
|	#(UNARY_ADD		        predicate			    )
|	#(UNARY_MINUS	        predicate			    )
| 	#(B_ADD			        predicate	predicate	)
|	#(B_MINUS		        predicate	predicate	)
|	#(B_EQUAL		        predicate	predicate	)
|	#(B_LESS		        predicate	predicate	)
|	#(B_GREATER		        predicate	predicate	)
|	#(B_NOTEQUAL		    predicate	predicate	)
|	#(B_LESSTHANEQUAL	    predicate	predicate	)
|	#(B_GREATERTHANEQUAL	predicate	predicate	)

|! #(B_INSET         
        p1:ml_var 
        p2:predicate
{

// Expansion d'un B_INSET
System.out.println("Expansion INSET - LIST_VAR >>");

	MyNode a1 	= 	(MyNode) astFactory.dupTree(#p1) ;
	MyNode a2 	= 	(MyNode) astFactory.dupTree(#p2) ;
	MyNode #t2 	= 	#(#B_INSET, #a1, #a2);

	#predicate	=	inset(#t2);
System.out.println("Expansion INSET - LIST_VAR <<");
}
    )

|	#(B_NOT			predicate				)
|	#(B_DOM			predicate				)
|	#(B_MAX			predicate				)
|	#(B_MIN			predicate				)
|	#(B_CARD		predicate				)
|	#(B_RAN			predicate				)
|	#(B_NOTINSET		predicate	predicate	)
|	#(B_SUBSET		predicate	predicate	)
|	#(B_NOTSUBSET		predicate	predicate	)
|	#(B_STRICTSUBSET	predicate	predicate	)
|	#(B_NOTSTRICTSBSET	predicate	predicate	)
|	#(B_CONCATSEQ		predicate	predicate	)
|	#(B_PREAPPSEQ		predicate	predicate	)
|	#(B_APPSEQ		predicate	predicate	)
|	#(B_PREFIXSEQ		predicate	predicate	)
|	#(B_SUFFIXSEQ    	predicate	predicate	)
|	#(B_RELATION		predicate	predicate	)
|	#(B_PARTIAL			predicate	predicate	)
|	#(B_TOTAL			predicate	predicate	)
|	#(B_PARTIAL_INJECT	predicate	predicate	)
|	#(B_TOTAL_INJECT	predicate	predicate	)
|	#(B_PARTIAL_SURJECT	predicate	predicate	)
|	#(B_TOTAL_SURJECT	predicate	predicate	)
|	#(B_BIJECTION		predicate	predicate	)
|	#(B_DOMAINRESTRICT	predicate	predicate	)
|	#(B_RANGERESTRICT	predicate	predicate	)
|	#(B_DOMAINSUBSTRACT predicate	predicate	)
|	#(B_RANGESUBSTRACT	predicate	predicate	)
|	#(B_OVERRIDEFORWARD	predicate	predicate	)
|	#(B_OVERRIDEBACKWARD predicate	predicate	)
|	#(B_RELPROD			predicate	predicate	)
|	#(B_UNION			predicate	predicate	)
|	#(B_INTER			predicate	predicate	)
|	#(B_MAPLET			predicate	predicate	)
|	#(LIST_VAR			predicate	predicate)
|	cset_description
;

ml_var :
    #(LIST_VAR
        ml_var
        nameRenamedDecorated
    )
|   nameRenamedDecorated
;

cset_description	:
    basic_sets
|	cbasic_value
|	#("bool"	    	predicate			    )
|	#(B_BRACKOPEN		listPredicate			)
|	#(B_RANGE	    	predicate	predicate	)
|	B_EMPTYSET 
|	#(B_CURLYOPEN		cvalue_set			    )
|	B_SEQEMPTY
|	is_record
|	quantification
|	q_lambda
;

// Dans un ensemble il peut y avoir 
// - l_var | P
// - a,b,c
cvalue_set  	:
    	#(B_SUCH 	list_var	predicate	)
|	#(B_COMMA 	cvalue_set	predicate	)
|			            	predicate
;

cbasic_value
{ 
	boolean afaire = false;
}
 	:
        B_ASTRING
    |	B_NUMBER
    |	#(B_TILDE	predicate)
    |	nameRenamedDecorated
    |
!	#(B_LPAREN	
            pp:predicate
            lp:list_New_Predicate
{
		String      st = #pp.toString();
		ADefinition ll = listdefinition.lookupName(st);

		if (ll != null)
        {
			afaire = true;
        }
		else 
		{
			afaire = false;
		}
//		if ((def == false) & (afaire == true))

		if ((afaire == true))
		{
            MyNode a1           = (MyNode) astFactory.dupTree(ll.getParam());
			MyNode a2           = (MyNode) astFactory.dupTree(#lp) ;
			MyNode temp 		= #(#[B_SIMPLESUBST,":="], #a1, #a2);

			MyNode #result 		= affectation(#temp);

            MyNode body         = (MyNode) astFactory.dupTree(ll.getBody());
            MyNode tmp          = #([SUBST_TO,"[["], #body, #result);

            uu.goal(#tmp);
            MyNode finalResult = (MyNode) uu.getAST();

            #cbasic_value		= #finalResult;
		}
		else
        {
			#cbasic_value 		= #(#B_LPAREN, #pp, #lp);
        }
}
    )
|	#(PARENT 	    pred_func_composition 	    )
|	#(B_QUOTEIDENT 	predicate	predicate		)
|	#(APPLY_TO	    predicate	predicate		)
|	"TRUE"
|	"FALSE"
;

pred_func_composition 	:
    #(B_SEMICOLON	pred_func_composition	pred_func_composition)
|	#(B_PARALLEL	pred_func_composition	pred_func_composition)
|	#(B_COMMA	    pred_func_composition	pred_func_composition)
|	                predicate
;

/**
 * Les Types de base
 **/
basic_sets 	:
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

quantification	:
    #(B_FORALL	list_var	predicate)
|	#(B_EXISTS	list_var	predicate)
;

q_lambda	:
    #(B_LAMBDA 	q_operande)
|	#("PI"		q_operande)
|	#("SIGMA" 	q_operande)
|	#("UNION" 	q_operande)
|	#("INTER" 	q_operande)
;

q_operande	:
	#(B_SUCH	list_var	predicate	predicate)
;

list_var	:
    #(B_LPAREN 	list_identifier  )
|      			list_identifier 
;

list_identifier	: 		
    #(B_COMMA	list_identifier 	list_identifier)	
|	               B_IDENTIFIER 
;

list_var_bis		:	
    #(B_LPAREN	list_identifier_bis  )
|	            list_identifier_bis
;

list_identifier_bis	:	
    #(B_COMMA	list_identifier_bis	list_identifier_bis)
|	               B_IDENTIFIER	
;

// On remplace des variables par des expressions .....

normalize returns [MyNode res]	
{ 
	res = null;
} 	:
	(B_LPAREN  predicate B_SIMPLESUBST)
	=> 	#(B_LPAREN 
					pp1:predicate 
					#(B_SIMPLESUBST	
						lf:list_func_call
						lp:predicate
					)
				)
|	(B_LPAREN  predicate PARALLEL)
	=> 	#(B_LPAREN 
					pp2:predicate 
					#(PARALLEL	
						lf1:instruction
						lf2:instruction
					)

				)
|	(B_LPAREN  predicate SEQUENTIAL)
	=> 	#(B_LPAREN 
					pp3:predicate 
					#(SEQUENTIAL	
						lf3:instruction
						lf4:instruction
					)
				)
;

// End of file Normalize.g
