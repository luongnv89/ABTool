
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jl.boulanger@wanadoo.fr
// 	File		:	Normalize.g
//	Date		:	Creation 03/09/2001
//	Descripton	:	A pre-processor based on Tree Walker for Another B Parser written
//				with ANTLR 2.7.1
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//	September 	2001	v 0.1 		Creation



//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//  * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
//  **/



// Import the necessary classes 
//-----------------------------------------------------------------------------

{
// General packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;
        import antlr.ASTFactory;

// Our Packages
	import DEBUG;
	import ErrorMessage; 
	import MyNode;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class Normalize extends TreeParser;
options
{
	importVocab 	= B;					// The language is imported and not exported
        buildAST 	= true;					// We write, we don't build tree
        ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
        codeGenMakeSwitchThreshold = 3;
        codeGenBitsetTestThreshold = 4;

	k = 1;							// On a besoin d'un seul token 
}

// Indtroduce some behaviours....
{

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
}

// Grammar become HERE
// -------------------

/**
 *	La regle "composant" permet de definir le point d'entree pour realiser le typage.
 **/

// Root Rule
composant 	:		#("MACHINE"		paramName clauses )
			|	#("REFINEMENT" 		paramName clauses )
 			|	#("IMPLEMENTATION"	paramName clauses )
		;

clauses		:		clause clauses
			| 	clause
		;

clause		:
				#("CONSTRAINTS"		predicate		)
			|	#("EXTENDS"		listInstanciation	)
			|	#("USES"	  	listNames		)
			|	#("INCLUDES"		listInstanciation	)
			|	#("SEES"		listNames		)
			|	#("IMPORTS"		listInstanciation	)
			|	#("PROMOTES"	  	listNames		)
			|	#("REFINES"  		B_IDENTIFIER		)
			|	#("CONSTANTS"		listTypedIdentifier	)
			|	#("CONCRETE_CONSTANTS"	listTypedIdentifier	)
			|	#("VISIBLE_CONSTANTS"	listTypedIdentifier	)
			|	#("ABSTRACT_CONSTANTS"	listTypedIdentifier	)
			|	#("HIDDEN_CONSTANTS"	listTypedIdentifier	)
			|	#("SETS"	  	sets_declaration	)
			|	#("VALUES"		list_valuation		)
			|	#("PROPERTIES"		predicate		)
			|	#("VARIABLES" 		listTypedIdentifier 	)
			|	#("ABSTRACT_VARIABLES" 	listTypedIdentifier 	)
			|	#("VISIBLE_VARIABLES"	listTypedIdentifier	)
			|	#("CONCRETE_VARIABLES"	listTypedIdentifier	)
			|	#("HIDDEN_VARIABLES"	listTypedIdentifier	)
			|	#("INVARIANT"		predicate		)
			|	#("DEFINITIONS" 	list_def		)
			|	#("INITIALISATION"  	instruction		)
			|	#("OPERATIONS"  	listOperation		)
			|	#("ASSERTIONS" 	 	list_assertions		)
		;
	        
/**
 * This rule is used for defined all Name with or without parameters
 **/

paramName	:		#(B_LPAREN 
					B_IDENTIFIER
					listTypedIdentifier
				)	
			|	B_IDENTIFIER 
		;

listTypedIdentifier: 		#(B_COMMA
					listTypedIdentifier
			 	 	listTypedIdentifier 
				)	
			|	typedIdentifier
		;

/** 
 * La regle "typedIdentifier" permet de prendre en compte l'extension 
 * de typage explicite de B'
 **/

typedIdentifier		:	#(B_INSET
					nameRenamed
				 	predicate
				 )	
			|
				nameRenamed
		;

listNames	:		#(B_COMMA
					listNames
					listNames
				)
			|
				nameRenamed
		;

listInstanciation:		#(B_COMMA 
					listInstanciation
					listInstanciation
				)
			|	paramRenameValuation
		;

paramRenameValuation 	:	#(B_LPAREN 
					paramRenameValuation
				  	list_New_Predicate
				)
			|
				nameRenamed
		;

// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:		#(B_SEMICOLON  sets_declaration   sets_declaration)
		| 		#(B_COMMA      sets_declaration   sets_declaration)
		|		set_declaration	
		;

set_declaration :
// Definition d'un ensemble ID = ....
				#(B_EQUAL
				  	B_IDENTIFIER
				  	valuation_set
				)
			|
// Definition d'un ID
				B_IDENTIFIER
		;

is_record 	:
				#("rec"  
					listrecord
				)
			|	#("struct"  
					listrecord
				)
;

valuation_set	:		#(B_CURLYOPEN
				  	list_couple
				 )
			|	is_record
			|	#(B_MULT 
					valuation_set
				 	valuation_set
				)
			|	#(B_ADD 
					valuation_set
				 	valuation_set
				)
			|	#(t5:B_MINUS 
					valuation_set
				 	valuation_set
				)
			|	B_IDENTIFIER
			|	basic_sets
		;

listrecord	:		#(B_COMMA 
					listrecord
					listrecord
				)
			|	a_record
		;

list_couple	: 		#(t1:B_COMMA 
					list_couple  
					list_couple
				)
			|	couple
		;

couple			:
				a_maplet
			|	#(B_LPAREN 
					parent_couple
				)
			|	a_set_value
		;

parent_couple		:
				a_maplet
			|	a_comma
		;

a_comma		 	:
				#(B_COMMA 
					a_set_value_
					a_set_value_
				)
		;

a_maplet		:
				#(B_MAPLET 
					a_set_value_
					a_set_value_
				)
		;

a_set_value		:
				B_IDENTIFIER
			|	#(UNARY_MINUS	
					B_NUMBER
				)
			|	B_NUMBER
		;

a_set_value_ :
				B_IDENTIFIER
			|	#(UNARY_MINUS
					B_NUMBER
				)
			|	B_NUMBER
			|	"TRUE"
			|	"FALSE"
		
	;

/**
 *	La regle "a_record" permet de definir le type de chaque champs
 **/
a_record	:		#(B_SELECTOR
					B_IDENTIFIER 
					predicate
				)
			|	predicate
		;

list_valuation	:		#(B_SEMICOLON 
					list_valuation 
					list_valuation
				)
			|	set_valuation
		;

set_valuation	:		#(B_EQUAL 
					B_IDENTIFIER
					new_set_or_constant_valuation
				 )
		;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

// En fait en faisant simple c'est un "predicate"
new_set_or_constant_valuation		:
			predicate
;

set_interval_value:		#(B_EQUAL 
					B_IDENTIFIER 	
					interval_declaration
				)
		;

interval_declaration 		:		
				#(B_RANGE
					predicate
				 	predicate
				)
		;

list_def	:		#(LIST_DEF
					list_def
					list_def
				)
			|	definition
		;

/**
 * Attention deux cas
 *	- Une definition
 *	- un acces a un fichier de definition
 **/
definition	:		 #(B_DOUBLE_EQUAL
					paramName
			 		formalText
				)
			|	B_ASTRING
		;

/**
 **	A FAIRE
 **	On se trouve dans le corps d'une DEFINITION,
 **	il va falloir introduire l'inhibition des messages d'erreurs 
 **	ou les transforme en WARNING
 **/
formalText	:
				predicate
			|	instruction
			|	operation
		;


list_assertions	:		#(B_SEMICOLON  
					list_assertions
			 	 	list_assertions
				)
			|
				predicate
		;

listOperation	:		#(B_SEMICOLON 
					listOperation
		 		 	listOperation
				)
			|	operation
		;

operation	:		#(OP_DEF
					(
						(	#(B_OUT
								listTypedIdentifier
								paramName
							)
							instruction
						)
					|
						(	paramName
							instruction
						)
					)
				)
		;


/**
 * the Generalised Substitution Language
 **/

instruction
		:		#(PARALLEL
					instruction
					instruction
				)
			|	#(SEQUENTIAL
					instruction
			 		instruction
				)
			|	"skip" 
			|	#("BEGIN"
					instruction 
				)
			|	#("PRE"
					predicate
					instruction
				)
			|	#("ASSERT"	
					predicate
				 	instruction
				)
			|	#("IF" 	
					predicate
					branche_then
					(branche_elsif)*
					(branche_else)?
				)
			|	#("CHOICE"
				 	list_or 
				)
			|	#("CASE"	

					predicate
					branche_either
					(branche_or)*
					(branche_else)?
				)
			|	#("ANY" 
					listTypedIdentifier
					predicate
					instruction
				)
			|	#("LET"
					listTypedIdentifier
					list_equal
				 	instruction
				)
			|	#("SELECT" 	
					predicate
					branche_then 
					(branche_when)*
					(branche_else)?
				)
			|
// Deux fois la clause variant_or_no pour traiter INVARIANT et VARIANT
				#("WHILE"
					predicate
					instruction
					variant_or_no
					variant_or_no
				)
			|	#("VAR"

					listTypedIdentifier
					instruction
				)
			|	simple_affect
		;


list_or		:		#("OR"
			  		list_or
				 	instruction
				)
			|	instruction
		;

branche_when	:		#("WHEN"
					predicate
					instruction
				)
		;
branche_either	:		#("EITHER"
					predicate
					instruction 
				)
		;

branche_or	:		#("OR"
					predicate
					instruction
				)
		;

branche_then	:		#("THEN"				
					instruction
				)
		;

branche_else	:		#("ELSE"				
					instruction
				)
		;

branche_elsif	:		#("ELSIF" 
					predicate
					instruction
				)
		;

variant_or_no	:		#("VARIANT" 	predicate)
			|	#("INVARIANT"	predicate)
		;

list_equal	: 		#(B_AND
				 	list_equal
		 		 	list_equal
				)
			|	an_equal	
		;

an_equal	:		#(B_EQUAL 
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
				#(B_TILDE
			 		func_call
 				)
			|	#(APPLY_TO 		
					func_call 	
					list_New_Predicate
				)
			| 	#(B_LPAREN
			 		func_call 
					list_New_Predicate
				)
			|	#(B_QUOTEIDENT
					func_call
				 	func_call
				)
			|
				nameRenamed
		;

func_param  		:
				list_New_Predicate
		;

list_New_Predicate	:
				#(B_COMMA 
					list_New_Predicate  
					new_predicate
				)
			|	new_predicate
		;

new_predicate		:
				#(B_SEMICOLON 
					new_predicate
					predicate
	 			)
			|	#(B_PARALLEL
					new_predicate
					predicate
				)
			|
					predicate
		;

nameRenamed 	:		B_IDENTIFIER 
			|	#(B_POINT 
					nameRenamed
					B_IDENTIFIER 	
				)
		;

nameRenamedDecorated 	:
				#(B_CPRED 
					nameRenamed
				)
			|
				nameRenamed
		;

listPredicate	:
				#(B_COMMA 
					listPredicate 
					listPredicate

				)
			|	predicate
		;

a_func_call	:		#(A_FUNC_CALL
					afc
				)	
		;

afc 	:
				#(FUNC_CALL_PARAM
					afc
				  	listPredicate
				)
			|
				#(B_QUOTEIDENT
					afc
				 	afc
			 	)
			|
				nameRenamed
		;

list_func_call	:
				#(B_COMMA  
					list_func_call
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

simple_affect	:		#(B_SIMPLESUBST
					list_func_call
					listPredicate
				)
			|	#(B_OUT	 	
					list_func_call
					func_call
				)
			|	#(INSET
					list_func_call
					predicate
				)
			|	#(sa4:B_BECOME_ELEM
				 	list_func_call
					predicate
				)
			|	a_func_call
		;



/** 
 * Clause Predicate
 * Il faut verifier la compatibilite entre les deux types
 **/

// PREDICATE 
predicate	:		#(B_AND
					predicate
					predicate
				)	
			|	#("or" 
					predicate
					predicate
				)
			|	#(B_IMPLIES	
					predicate
					predicate
				)
			|	#(B_EQUIV
					predicate
					predicate
				)
			|	#(B_MULT
					predicate
					predicate
				)
			|	#(B_POWER 
					predicate
					predicate
				)
			|	#(B_DIV
					predicate
					predicate
				)
			|	#("mod"
					predicate
					predicate
				)
			|	#(UNARY_ADD
					predicate
				)
			|	#(UNARY_MINUS
					predicate
				)
			| 	#(B_ADD
					predicate
					predicate
				)
			|	#(B_MINUS
					predicate
					predicate
				)
			|	#(B_EQUAL
 					predicate
					predicate
				)
			|	#(B_LESS
					predicate
					predicate
				)
			|	#(B_GREATER
	 				predicate
					predicate
				)
			|	#(B_NOTEQUAL
					predicate				 
					predicate
				)
			|	#(B_LESSTHANEQUAL
					predicate
					predicate
				)
			|	#(B_GREATERTHANEQUAL
					predicate 										 
					predicate
				)
			|	#(B_INSET
					predicate
					predicate
				)
			|	#(B_NOTINSET
					predicate
					predicate
				)
			|	#(B_SUBSET
					predicate
					predicate
				)
			|	#(B_NOTSUBSET
					predicate
					predicate
				)
			|	#(B_STRICTSUBSET
					predicate
					predicate
				)
			|	#(B_NOTSTRICTSBSET
					predicate
					predicate
				)
			|	#(B_CONCATSEQ
					predicate
					predicate
				)
			|	#(B_PREAPPSEQ
					predicate
					predicate
				)
			|	#(B_APPSEQ
					predicate
					predicate
				)
			|	#(B_PREFIXSEQ
					predicate
					predicate
				)
			|	#(B_SUFFIXSEQ		 
					predicate			 
					predicate
				)
			|	#(B_RELATION
					predicate
					predicate
				)
			|	#(B_PARTIAL
					predicate
					predicate
				)
			|	#(B_TOTAL
					predicate
					predicate
				)
			|	#(B_PARTIAL_INJECT
					predicate
					predicate
				)
			|	#(B_TOTAL_INJECT
					predicate
					predicate
				)
			|	#(B_PARTIAL_SURJECT	
					predicate
					predicate
				)
			|	#(B_TOTAL_SURJECT
					predicate
					predicate
				)
			|	#(B_BIJECTION
					predicate
					predicate
				)
			|	#(B_DOMAINRESTRICT
					predicate
					predicate
				)
			|	#(B_RANGERESTRICT	 
					predicate	 
					predicate
				)
			|	#(B_DOMAINSUBSTRACT
					predicate
					predicate
				)
			|	#(B_RANGESUBSTRACT
					predicate
					predicate
				)
			|	#(B_OVERRIDEFORWARD
					predicate			 
					predicate
				)
			|	#(B_OVERRIDEBACKWARD			 
					predicate
					predicate
				)
			|	#(B_RELPROD
					predicate
					predicate
				)
			|	#(B_UNION
					predicate
					predicate
				)
			|	#(B_INTER
					predicate
					predicate
				)
			|	#(B_MAPLET 
					predicate
					predicate
				)
			|	#(LIST_VAR
					predicate										 
					predicate
				)
			|	cset_description
			;

cset_description
			:
				basic_sets
			|	cbasic_value
			|	#("bool"	predicate			)
			|	#(B_BRACKOPEN	listPredicate			)
			|	#(B_RANGE	predicate	predicate	)
			|	B_EMPTYSET 
			|	#(B_CURLYOPEN	cvalue_set			)
			|	B_SEQEMPTY
			|	is_record
			|	quantification
			|	q_lambda
		;

// Dans un ensemble il peut y avoir 
// - l_var | P
// - a,b,c
cvalue_set  	:		#(B_SUCH
				 	list_var
					predicate	
				)
			|	#(B_COMMA 	
					cvalue_set
					predicate
				)
			|
				predicate
		;

cbasic_value 	:
			B_ASTRING
			|
				B_NUMBER
				
			|
				#(B_TILDE
					predicate
				)
			|
				nameRenamedDecorated
			|
				#(B_LPAREN
					predicate
					list_New_Predicate
				)
			|
				#(PARENT 
					pred_func_composition 		
				)
			|
				#(B_QUOTEIDENT 
					predicate
					predicate
		 		)
			|
				#(APPLY_TO
					predicate
 					predicate
		 		)
			|	"TRUE"
			|	"FALSE"
		;

pred_func_composition 	:
				#(B_SEMICOLON 
					pred_func_composition
					pred_func_composition
				)
			|	#(B_PARALLEL
					pred_func_composition
					pred_func_composition
				)
			|	#(B_COMMA
					pred_func_composition
					pred_func_composition
				)
			|	predicate
		;

/**
 * Les Types de base
 **/
basic_sets 	:		"INT"
			|	"INT1"		
			|	"INTEGER"	
			|	"INTEGER1"	
			|	"BOOL"		
			|	"NAT"		
			|	"NAT1"		
			|	"NATURAL"	
			|	"NATURAL1"
		;

quantification	:		#(B_FORALL
					list_var
					predicate
  				)
			|	#(B_EXISTS
					list_var
					predicate
				)
		;



q_lambda	:		#(B_LAMBDA 	
					q_operande
				)
			|	#("PI"
					q_operande
				)
			|	#("SIGMA"
				 	q_operande
				)
			|	#("UNION"
				 	q_operande
				)
			|	#("INTER"
				 	q_operande
				)
		;

q_operande	:		#(B_SUCH
					list_var
					predicate
					predicate
				)
		;

list_var	:		#(B_LPAREN 
					list_identifier  
				)
			|	list_identifier 
		;

list_identifier	: 		#(B_COMMA
					list_identifier 
					list_identifier 
				)	
			|	B_IDENTIFIER 
				
		;

list_var_bis		:
				#(B_LPAREN 
					list_identifier_bis  
				)
			|	list_identifier_bis
		;

list_identifier_bis	:
		 		#(B_COMMA
					list_identifier_bis
					list_identifier_bis
				)	
			|
				B_IDENTIFIER	
		;
