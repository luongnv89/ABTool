
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jl.boulanger@wanadoo.fr
// 	File		:	PredicateTreeWalker.g
//	Date		:	Creation 10/04/2003
//	Descripton	:	Predicate Tree Walker for Another's B Parser written in ANTLR
//	
//	Copyright 2003-2009 Boulanger Jean-Louis
//

// Releases
//	April 	2003	v0.1
//      - Creation

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

// our packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.PRINTING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class PredicateTreeWalker extends TreeParser;
options
{
	importVocab 	= B;
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
}

// Grammar become HERE
// -------------------

/** 
 *  Root Rule
 **/

predicate	:
                BTRUE
			|	#(B_NOT	         	     predicate              )
			|	#(B_RAN	                 predicate              )
			|	#(B_DOM	                 predicate              )
			|	#(B_AND	                 predicate	predicate	)	
            		|   	#("or"                   predicate 	predicate   )
			|	#(B_IMPLIES	    	     predicate	predicate	)
			|	#(B_EQUIV		         predicate 	predicate	)
			|	#(B_MULT		         predicate	predicate	)
			|	#(PRODSET		         predicate	predicate	)
			|	#(B_POWER		         predicate	predicate	)
			|	#(B_DIV                  predicate	predicate	)
			|	#("mod"	 		         predicate 	predicate	)
			|	#(UNARY_ADD 	         predicate       		)
			|	#(UNARY_MINUS 	         predicate    			)
			| 	#(B_ADD 		         predicate	predicate	)
			|	#(B_MINUS 		         predicate	predicate	)
			|	#(B_EQUAL	             predicate	predicate	)
			|	#(B_LESS	             predicate	predicate	)
			|	#(B_GREATER	 	         predicate	predicate	)
			|	#(B_NOTEQUAL	         predicate	predicate	)
			|	#(B_LESSTHANEQUAL	     predicate	predicate	)
			|	#(B_GREATERTHANEQUAL     predicate	predicate	)
			|	#(B_INSET			     predicate	predicate	)
			|	#(B_NOTINSET		     predicate 	predicate	)
			|	#(B_SUBSET		         predicate 	predicate	)
			|	#(B_NOTSUBSET		     predicate	predicate	)
			|	#(B_STRICTSUBSET	     predicate	predicate	)
			|	#(B_NOTSTRICTSBSET	     predicate	predicate   )
			|	#(B_CONCATSEQ		     predicate	predicate   )
			|	#(B_PREAPPSEQ		     predicate 	predicate   )
			|	#(B_APPSEQ			 	 predicate	predicate   )
			|	#(B_PREFIXSEQ		 	 predicate	predicate   )
			|	#(B_SUFFIXSEQ		 	 predicate	predicate   )
			|	#(B_RELATION		 	 predicate	predicate   )
			|	#(B_PARTIAL			 	 predicate 	predicate   )
			|	#(B_TOTAL			 	 predicate 	predicate   )
			|	#(B_PARTIAL_INJECT	 	 predicate 	predicate   )
			|	#(B_TOTAL_INJECT		 predicate 	predicate   )
			|	#(B_PARTIAL_SURJECT	 	 predicate 	predicate   )
			|	#(B_TOTAL_SURJECT	 	 predicate 	predicate   )
			|	#(B_BIJECTION 		 	 predicate 	predicate   )
			|	#(B_DOMAINRESTRICT	 	 predicate 	predicate   )
			|	#(B_RANGERESTRICT	 	 predicate 	predicate   )
			|	#(B_DOMAINSUBSTRACT		 predicate 	predicate   )
			|	#(B_RANGESUBSTRACT		 predicate 	predicate   )
			|	#(B_OVERRIDEFORWARD		 predicate 	predicate   )
			|	#(B_OVERRIDEBACKWARD 	 predicate	predicate   )
			|	#(B_RELPROD 			 predicate 	predicate   )
			|	#(B_UNION 			     predicate 	predicate   )
			|	#(B_INTER 			     predicate 	predicate   )
			|	#(B_MAPLET 			     predicate	predicate   )
			|	#(LIST_VAR 			     predicate	predicate   )
			|	basic_sets 
			|	cbasic_value
			|	#("bool"			     predicate			    )
			|	#(B_BRACKOPEN			 listPredicate			)
			|	#(B_RANGE 			     predicate 	predicate   )
			|	B_EMPTYSET 
			|	#(B_CURLYOPEN 			 cvalue_set 			)
			|	B_SEQEMPTY
			|	is_record 
			|	quantification
			|	q_lambda
		;

// Dans un ensemble il peut y avoir 
cvalue_set	:
        #(B_SUCH	list_var	predicate	)
|	#(B_COMMA	predicate	predicate	)
;

cbasic_value	:
                B_ASTRING
			|	B_NUMBER
			|	#(B_TILDE 		       predicate                                    )
			|	                       nameRenamedDecorated
			|	#(B_LPAREN	           predicate		        list_New_Predicate	)
			|	#(PARENT               pred_func_composition                        )
			|   #(B_QUOTEIDENT	       predicate		        predicate           )
			|	#(APPLY_TO             predicate 		        predicate		    )
			|	"TRUE"
			|	"FALSE"
		;

pred_func_composition:
        #(B_SEMICOLON 	pred_func_composition 	predicate)
|	#(B_PARALLEL  	pred_func_composition 	predicate)
|	#(B_COMMA	pred_func_composition 	predicate)
|	predicate
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

quantification	:
                #(B_FORALL 	list_var 			predicate)
			|	#(B_EXISTS 	list_var 		 	predicate)
		;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda	:
                #(B_LAMBDA 	 q_operande)
			|	#("PI"	     q_operande)
			|	#("SIGMA"	 q_operande)
			|	#("UNION"	 q_operande)
			|	#("INTER"	 q_operande)
		;

q_operande	:
	        #(B_SUCH	list_var 	predicate	predicate)
;

// Dans le second cas il y a un blanc car pas de ( pour separer
list_var	:
	        #(B_LPAREN	list_identifier)
    |       	        list_identifier 
;

listTypedIdentifier: 	
            #(B_COMMA	listTypedIdentifier		listTypedIdentifier)	
        |	                typedIdentifier
;

typedIdentifier	:
                #(B_INSET	nameRenamed	predicate )	
            |	            nameRenamed
;

is_record	:
                    #("rec"	    listrecord )
			|		#("struct"	listrecord )
;

listrecord	:
        #(B_COMMA	  listrecord	a_record	)
    |	                            a_record
;

a_record	:	
        #(B_SELECTOR	name:B_IDENTIFIER	predicate	)
    |	                                    predicate
;

nameRenamed	:
                B_IDENTIFIER
        |       #(B_POINT 	nameRenamed	nameRenamed)
		;

nameRenamedDecorated:
	        #(B_CPRED 	nameRenamed )
    |	                nameRenamed
		;


listPredicate	:	
	#(ELEM_SET	  listPredicate 	predicate	)
|	predicate
;

list_New_Predicate:	
        #(B_COMMA	  list_New_Predicate	new_predicate	)
|	                        new_predicate
;

new_predicate	:	
            	#(B_SEMICOLON	    	new_predicate	predicate)
|		#(B_PARALLEL		new_predicate	predicate)
|	                                    		predicate
;

list_identifier	: 	
            #(B_COMMA	    list_identifier 	B_IDENTIFIER )
        |	                                    B_IDENTIFIER
;



// END Of FILE PredicateTreeWalker.g
















