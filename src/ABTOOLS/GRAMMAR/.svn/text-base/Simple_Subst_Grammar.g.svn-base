// 
// 	Author		:	Boulanger Jean-Louis	(jl.boulanger@wanadoo.fr)
// 	File		:	Simple_Subst_Grammar.g
//	Date		:	Creation 03/02/2003
//	Descripton	:	A grmmar for the special substitution ":=" tools written in ANTLR
//
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//  February 3  2003		v 0.1 	
//		- Creation


//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
        import ABTOOLS.DEBUGGING.*;
        import ABTOOLS.ANTLR_TOOLS.*;
}

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/


class Simple_Subst_Grammar extends TreeParser;
options
{
	importVocab 	= PO_GSL;
    buildAST 	    = true;
    ASTLabelType 	= "MyNode";
 
    // Copied following options from java grammar.
    codeGenMakeSwitchThreshold = 3;
    codeGenBitsetTestThreshold = 4;

	k = 1;
}

// Indtroduce some behaviours....

{

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

}

// Grammar become HERE
// -------------------


traitement[MyNode var, MyNode expr] 	:
!
{
            MyNode tmp1 	= new MyNode();
            predicate(tmp1,var,expr);
            #traitement      = tmp1;
}
	;

/** 
 *  PREDICATE 
 **/

predicate [MyNode var, MyNode expr]	
    :
                BTRUE
			|	#(t51:B_NOT	    predicate[var,expr])
			|	#(t52:B_RAN	    predicate[var,expr])
			|	#(t53:B_DOM	    predicate[var,expr])
            |	#(t54:B_AND	    predicate[var,expr]	predicate[var,expr]	)	
            |   #(t2:"or"       predicate[var,expr] 	predicate[var,expr])
			|	#(t3:B_IMPLIES	predicate[var,expr]		predicate[var,expr])
			|	#(t4:B_EQUIV	predicate[var,expr] 	predicate[var,expr])
			|
			#(t5:B_MULT
		 		predicate[var,expr]
			 	predicate[var,expr]
			)
			|	
			#(t6:B_POWER
				predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t7:B_DIV
	  			predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t8:"mod"
	  			predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t9:UNARY_ADD
			 	predicate[var,expr]
			)
			|
			#(t10:UNARY_MINUS
			 	predicate[var,expr]
			)
			|
		 	#(t11:B_ADD 
	  			predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t12:B_MINUS
		 		predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t13:B_EQUAL
	 			predicate[var,expr]
			 	predicate[var,expr]
			)
			|
			#(t14:B_LESS
		 		predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t15:B_GREATER
	 			predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t16:B_NOTEQUAL
				predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t17:B_LESSTHANEQUAL
		 		predicate[var,expr]	
				predicate[var,expr]
			)
			|
			#(t18:B_GREATERTHANEQUAL
				predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t19:B_INSET
				predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t20:B_NOTINSET
				predicate[var,expr] 
				predicate[var,expr]
			)
			|
			#(t21:B_SUBSET
				predicate[var,expr] 
				predicate[var,expr]
			)
			|
			#(t22:B_NOTSUBSET
				predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t23:B_STRICTSUBSET
				predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t24:B_NOTSTRICTSBSET
				predicate[var,expr]
				predicate[var,expr])
			|
			#(t25:B_CONCATSEQ
				predicate[var,expr]
				predicate[var,expr])
			|
			#(t26:B_PREAPPSEQ			predicate[var,expr] 		predicate[var,expr])
			|	#(t27:B_APPSEQ			predicate[var,expr]		predicate[var,expr])
			|	#(t28:B_PREFIXSEQ		predicate[var,expr]		predicate[var,expr])
			|	#(t29:B_SUFFIXSEQ		predicate[var,expr]		predicate[var,expr])
			|	#(t30:B_RELATION		predicate[var,expr]		predicate[var,expr])
			|	#(t31:B_PARTIAL			predicate[var,expr] 	 	predicate[var,expr])
			|	#(t32:B_TOTAL			predicate[var,expr] 	 	predicate[var,expr])
			|	#(t33:B_PARTIAL_INJECT		predicate[var,expr] 		predicate[var,expr])
			|	#(t34:B_TOTAL_INJECT		predicate[var,expr] 		predicate[var,expr])
			|	#(t35:B_PARTIAL_SURJECT		predicate[var,expr] 		predicate[var,expr])
			|	#(t36:B_TOTAL_SURJECT		predicate[var,expr] 		predicate[var,expr])
			|	#(t37:B_BIJECTION 		predicate[var,expr] 		predicate[var,expr])
			|	#(t38:B_DOMAINRESTRICT		predicate[var,expr] 	 	predicate[var,expr])
			|	#(t39:B_RANGERESTRICT		predicate[var,expr] 		predicate[var,expr])
			|	#(B_DOMAINSUBSTRACT		predicate[var,expr] 		predicate[var,expr])
			|	#(B_RANGESUBSTRACT		predicate[var,expr] 		predicate[var,expr])
			|	#(B_OVERRIDEFORWARD		predicate[var,expr] 		predicate[var,expr])
			|	#(B_OVERRIDEBACKWARD 		predicate[var,expr]		predicate[var,expr])
			|	#(B_RELPROD 			predicate[var,expr] 		predicate[var,expr])
			|	#(B_UNION 			predicate[var,expr] 		predicate[var,expr])
			|	#(B_INTER 			predicate[var,expr] 		predicate[var,expr])
			|	#(B_MAPLET 			predicate[var,expr]		predicate[var,expr])
			|	#(LIST_VAR 			predicate[var,expr]		predicate[var,expr])
			|	basic_sets 
			|	cbasic_value[var,expr]
			|	#("bool"			predicate[var,expr]			)
			|	#(B_BRACKOPEN			listPredicate[var,expr]			)
			|	#(B_RANGE 			predicate[var,expr] 		predicate[var,expr])
			|	B_EMPTYSET 
			|	#(B_CURLYOPEN 			cvalue_set[var,expr] 			)
			|	B_SEQEMPTY
			|	is_record [var,expr]
			|	quantification[var,expr]
			|	q_lambda[var,expr]
		;

// Dans un ensemble il peut y avoir 
cvalue_set[MyNode var, MyNode expr]	:
		        #(t1:B_SUCH
			 		list_var[var,expr]
			 		predicate[var,expr]
				)
			|	#(t2:B_COMMA
			 		cvalue_set[var,expr]
			 		predicate[var,expr]
				)
			|	predicate[var,expr]
		;

cbasic_value[MyNode var, MyNode expr]	:
		        t1:B_ASTRING
			|	t2:B_NUMBER
			|	#(t3:B_TILDE 		predicate[var,expr])
			|	nameRenamedDecorated[var,expr]
            |	#(B_LPAREN 		    predicate[var,expr]		list_New_Predicate[var,expr]	)
            |	#(PARENT 		    pred_func_composition[var,expr]				)
            |	#(t4:B_QUOTEIDENT	predicate[var,expr]		    predicate[var,expr] 		)
            |	#(APPLY_TO		    predicate[var,expr] 		predicate[var,expr]		)
			|	t6:"TRUE"
			|	t7:"FALSE"
		;

pred_func_composition[MyNode var, MyNode expr]:
		        #(t1:B_SEMICOLON pred_func_composition[var,expr] 		predicate[var,expr])
			|	#(t2:B_PARALLEL  pred_func_composition[var,expr] 		predicate[var,expr])
			|	#(t3:B_COMMA	 pred_func_composition[var,expr] 		predicate[var,expr])
			|	predicate[var,expr]
		;

basic_sets	:
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

quantification[MyNode var, MyNode expr]	:
		        #(t70:B_FORALL 	list_var[var,expr] 			predicate[var,expr])
			|	#(t71:B_EXISTS 	list_var[var,expr] 		 	predicate[var,expr])
		;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda[MyNode var, MyNode expr]	:		
            #(t1:B_LAMBDA 
					q_operande[var,expr]
				)
			|	#(t2:"PI"

					q_operande[var,expr]
				)
			|	#(t3:"SIGMA"
					q_operande[var,expr]
				)
			|	#(t4:"UNION"
					 q_operande[var,expr]
				)
			|	#(t5:"INTER"
					 q_operande[var,expr]
				)
		;

q_operande[MyNode var, MyNode expr]	:	#(t1:B_SUCH
				list_var [var,expr]
				predicate[var,expr]
				predicate[var,expr]
			)
		;

// Dans le second cas il y a un blanc car pas de ( pour separer
list_var[MyNode var, MyNode expr]	:
            #(B_LPAREN
				list_identifier[var,expr]
			)
			|
				list_identifier[var,expr] 
		;

listTypedIdentifier[MyNode var, MyNode expr]: 	
            #(B_COMMA
				listTypedIdentifier[var,expr]	
{

}
			 	listTypedIdentifier[var,expr]
			)	
			|	typedIdentifier[var,expr]
		;

typedIdentifier[MyNode var, MyNode expr]	:
			#(B_INSET
				nameRenamed[var,expr]
				predicate[var,expr] 
			)	
			|	nameRenamed[var,expr]

		;

is_record[MyNode var, MyNode expr]	:
			#(t1:"rec"
				listrecord[var,expr]
			)
			|
			#(t01:"struct"
				listrecord [var,expr]
			)
		;

listrecord[MyNode var, MyNode expr]	:	#(tt:B_COMMA 
				listrecord[var,expr] 
				a_record[var,expr]
			)
			|	a_record[var,expr]
		;

a_record[MyNode var, MyNode expr]	:
	        #(B_SELECTOR 
				name:B_IDENTIFIER
				predicate[var,expr]
			)
			|	predicate[var,expr]
;

nameRenamed[MyNode var, MyNode expr]	:
	        n1:B_IDENTIFIER
			|
			#(tt:B_POINT 
				nameRenamed[var,expr]
				nameRenamed[var,expr]
			)
		;

nameRenamedDecorated[MyNode var, MyNode expr]:	
            #(tt:B_CPRED 
				nameRenamed [var,expr]
			)
			|
			nameRenamed[var,expr]
		;


listPredicate[MyNode var, MyNode expr]	:
	        #(tt:B_COMMA
		 		listPredicate[var,expr]
			 	predicate[var,expr]
			)
			|	predicate[var,expr]
;

list_New_Predicate[MyNode var, MyNode expr]:
	        #(tt:B_COMMA 
				list_New_Predicate[var,expr]
				new_predicate[var,expr]
			)
			|	new_predicate[var,expr]
		;

new_predicate[MyNode var, MyNode expr]	:
	        #(t1:B_SEMICOLON 
				new_predicate[var,expr]
				predicate[var,expr]
			)
			|
			#(t2:B_PARALLEL  
				new_predicate[var,expr]
				predicate[var,expr]
			)
			|
			predicate[var,expr]
		;

list_identifier[MyNode var, MyNode expr]	: 	
            #(tt:B_COMMA
		 		list_identifier[var,expr] 
				n1:B_IDENTIFIER 
			)
			|	n2:B_IDENTIFIER
		;
