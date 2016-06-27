// 
// 	Author		:	Boulanger Jean-Louis
//  Email       :   jean.louis.boulanger@wanadoo.fr
// 	File		:	GSL.g
//	Descripton	:	A GSL tools written in ANTLR
//
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//	August 07 2001		v 0.1 	
//		- Creation
//
//	May	  2002		    v 0.1.1
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  February 2003       v 0.1.2
//      - Pretty-printing
//
//  March   2003        v 0.1.3
//      - Pretty Printing
//      - Simple_Subst is OK
//  April   2004        v 0.2
//      - Restructuring and Packaging
//  September 2004      v 1.1
//      - Packaging
//  October   2004      v 1.2
//      - Debugging
//  November  2004 
//      - Add PO and APO token



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
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/


class GSL extends TreeParser;
options
{
	importVocab 	= PO_GSL;
	buildAST 	= true;
	ASTLabelType 	= "MyNode";
 
    // Copied following options from java grammar.
    	codeGenMakeSwitchThreshold = 3;
    	codeGenBitsetTestThreshold = 4;

	k = 1;
}

// Indtroduce some behaviours....

{
// Package information
    	protected String module = "GSL.g";

// Somes variables
	boolean	aaffecter = false	;
	MyNode 	name			;
	MyNode 	transfert		;

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


traitement 	:
    #(PO
            traitement
            traitement
     )
    | apo
;

apo	:
    #(APO structure)
;

structure	:
!
    #(B_IMPLIES pr:predicate gg:goal
{
	#structure = #(B_IMPLIES ,#pr ,#gg);
}
    )
;

goal
{
	MyNode res	= null     ;
	aaffecter 	= false	;
}		:
!	#(SUBST_TO 
{
	System.out.println("GSL-goal : on debute ");
}
        	pr:predicate 
{
	System.out.println("GSL-goal :pr = " + #pr.toStringList());
}
        	res=gsl[#pr]
{ 
	#goal = (MyNode) astFactory.dupTree(#res);
	System.out.println("GSL-goal : on finit avec " + #res.toStringList());
}
	)
    |
        #(t:BTRUE
{
    #goal = #t;
}
        )
;

gsl [MyNode pr]  returns [MyNode result]
{
	MyNode res,r1,r2 ;
	#result = new MyNode();
}
		:
	#(GSL_SUCH	
		p1:predicate
		res= gsl[pr]
{
	#result  = #(#[B_AND,"&"],     #p1, #res);
	System.out.println("gsl1 -- on a :" + #result.toStringList());	
}
	)
	|
	#(GSL_FOR_SUCH	
		ll:listTypedIdentifier
		res=gsl[pr]
{
	#result  = #(#[B_FORALL,"!"],  #ll, #res);
	System.out.println("gsl2 -- on a :" + #result.toStringList());
}
	)
	|
	#(GSL_GUARD	
		p2:predicate
		res=gsl[pr]
{
	#result  = #(#[B_IMPLIES,"=>"],#p2, #res);
	System.out.println("gsl3 -- on a :" + #result.toStringList());
}
	)
	|
	#("skip"
{
	#result	= (MyNode) astFactory.dupTree(#pr);
	System.out.println("gsl4 -- on a :" + #result.toStringList());
}
	)
	|
	#(B_SIMPLESUBST	
		lf:afc	
		p5:predicate
{
	aaffecter   = true;
	System.out.println("debut gsl ':=' -- on a :" + #pr.toStringList());
	#name        = (MyNode) astFactory.dupTree(#lf); 
        #transfert   = (MyNode) astFactory.dupTree(#p5); 
        MyNode tmp  = predicate_normalize(#pr);
        #result      = (MyNode) astFactory.dupTree(#tmp);

        aaffecter   = false;
	System.out.println("fin gsl ':=' -- on a :" + #tmp.toStringList());
}
	)
	|
	#(SEQUENTIAL	
		r1=gsl[pr]	
		res=gsl[r1]
{
	#result = (MyNode) astFactory.dupTree(pr);
	System.out.println("gsl ';' -- on a :" + #result.toStringList());
}
	)
	|
	#(PARALLEL	    
		r1=gsl[pr]
		r2=gsl[pr]
{
	#result = #(#[B_AND,"&"],     #r1, #r2);
	System.out.println("gsl '||' -- on a :" + #result.toStringList());
}
	)
;

afc 	: 	
        #(FUNC_CALL_PARAM	afc 	listPredicate	)
    |	#(t1:B_QUOTEIDENT	afc	    afc             )
    |                        	nameRenamed
;

/** 
 *  PREDICATE 
 **/
predicate_normalize returns [MyNode pr] 
{
    pr = null;
}:
    pp:predicate
{
            #pr = (MyNode) astFactory.dupTree(#pp);
}
    ;

predicate	:
                BTRUE
			|	#(t51:B_NOT	             predicate              )
			|	#(t52:B_RAN	             predicate              )
			|	#(t53:B_DOM	             predicate              )
			|	#(t54:B_AND	             predicate	predicate	)	
            		|   	#(t2:"or"                    predicate 	predicate   )
			|	#(t3:B_IMPLIES		     predicate	predicate	)
			|	#(t4:B_EQUIV		     predicate 	predicate	)
			|	#(t5:B_MULT		     predicate	predicate	)
			|	#(t6:B_POWER		     predicate	predicate	)
			|	#(t7:B_DIV                   predicate	predicate	)
			|	#(t8:"mod"	 	     predicate 	predicate	)
			|	#(t9:UNARY_ADD 	             predicate       		)
			|	#(t10:UNARY_MINUS 	     predicate    			)
			| 	#(t11:B_ADD 		     predicate	predicate	)
			|	#(t12:B_MINUS 		     predicate	predicate	)
			|	#(t13:B_EQUAL	             predicate	predicate	)
			|	#(t14:B_LESS	             predicate	predicate	)
			|	#(t15:B_GREATER	 	     predicate	predicate	)
			|	#(t16:B_NOTEQUAL	     predicate	predicate	)
			|	#(t17:B_LESSTHANEQUAL	     predicate	predicate	)
			|	#(t18:B_GREATERTHANEQUAL     predicate	predicate	)
			|	#(t19:B_INSET		     predicate	predicate	)
			|	#(t20:B_NOTINSET	     predicate 	predicate	)
			|	#(t21:B_SUBSET		     predicate 	predicate	)
			|	#(t22:B_NOTSUBSET	     predicate	predicate	)
			|	#(t23:B_STRICTSUBSET	     predicate	predicate	)
			|	#(t24:B_NOTSTRICTSBSET	     predicate	predicate   )
			|	#(t25:B_CONCATSEQ	     predicate	predicate   )
			|	#(t26:B_PREAPPSEQ	     predicate 	predicate   )
			|	#(t27:B_APPSEQ		     predicate	predicate   )
			|	#(t28:B_PREFIXSEQ	     predicate	predicate   )
			|	#(t29:B_SUFFIXSEQ	     predicate	predicate   )
			|	#(t30:B_RELATION	     predicate	predicate   )
			|	#(t31:B_PARTIAL			 predicate 	predicate   )
			|	#(t32:B_TOTAL			 predicate 	predicate   )
			|	#(t33:B_PARTIAL_INJECT	     predicate 	predicate   )
			|	#(t34:B_TOTAL_INJECT	     predicate 	predicate   )
			|	#(t35:B_PARTIAL_SURJECT	     predicate 	predicate   )
			|	#(t36:B_TOTAL_SURJECT	 predicate 	predicate   )
			|	#(t37:B_BIJECTION 		 predicate 	predicate   )
			|	#(t38:B_DOMAINRESTRICT	 predicate 	predicate   )
			|	#(t39:B_RANGERESTRICT	 predicate 	predicate   )
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
			|	#(B_COMMA	cvalue_set	predicate	)
			|	predicate
		;

cbasic_value	:
                B_ASTRING
			|	B_NUMBER
			|	#(B_TILDE 		       predicate                                    )
			|	                       nameRenamedDecorated
			|	#(B_LPAREN	           predicate		        list_New_Predicate	)
			|	#(PARENT               pred_func_composition                        )
			|
!       	    #(t4:B_QUOTEIDENT	p1:predicate		        p2:predicate
{
    if (aaffecter)
    {
        if (t4.toString().compareTo(name.toString())==0)
        {
            #cbasic_value = (MyNode) astFactory.dupTree(#transfert);
        }
        else
        {
            #cbasic_value =#(#t4,#p1,#p2);
        }
    }
    else
    {
        #cbasic_value =#(#t4,#p1,#p2);
    }
}
                )
			|	#(APPLY_TO      predicate 		predicate		)
			|	t6:"TRUE"
			|	t7:"FALSE"
		;

pred_func_composition:
                #(t1:B_SEMICOLON pred_func_composition 		predicate)
			|	#(t2:B_PARALLEL  pred_func_composition 		predicate)
			|	#(t3:B_COMMA	 pred_func_composition 		predicate)
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
                #(t70:B_FORALL 	list_var 			predicate)
			|	#(t71:B_EXISTS 	list_var 		 	predicate)
		;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda	:
                #(t1:B_LAMBDA 
					q_operande
				)
			|	#(t2:"PI"

					q_operande
				)
			|	#(t3:"SIGMA"
					q_operande
				)
			|	#(t4:"UNION"
					 q_operande
				)
			|	#(t5:"INTER"
					 q_operande
				)
		;

q_operande	:
	        #(t1:B_SUCH
				list_var 
				predicate
				predicate
			)
		;

// Dans le second cas il y a un blanc car pas de ( pour separer
list_var	:
	        #(B_LPAREN
				list_identifier
			)
			|
				list_identifier 
		;

listTypedIdentifier: 	#(B_COMMA
				listTypedIdentifier	
{

}
			 	listTypedIdentifier
			)	
			|	typedIdentifier
		;

typedIdentifier	:
			#(B_INSET
				nameRenamed
				predicate 
			)	
			|	nameRenamed

		;

is_record	:
			#(t1:"rec"
				listrecord
			)
			|
			#(t01:"struct"
				listrecord 
			)
		;

listrecord	:
	        #(tt:B_COMMA	listrecord	a_record	)
    |	                                a_record
;

a_record	:	
        #(B_SELECTOR	name:B_IDENTIFIER	predicate	)
    |	                                    predicate
;

nameRenamed	:	
!           n1:B_IDENTIFIER
{
    if (aaffecter == true)
    {
        if (n1.toString().compareTo(#name.toString())==0)
        {
            #nameRenamed = (MyNode) astFactory.dupTree(#transfert);
        }
        else
        {
            #nameRenamed =(MyNode) astFactory.dupTree(#n1);
        }
    }
    else
    {
        #nameRenamed =#n1;
    }
}
			|
!			#(tt:B_POINT 
				n2:nameRenamed
				n3:nameRenamed
{
    if (aaffecter== true)
    {
        if (tt.toString().compareTo(#name.toString())==0)
        {
            #nameRenamed = (MyNode) astFactory.dupTree(#transfert);
        }
        else
        {
            #nameRenamed =#(#tt,#n2,#n3);
        }
    }
    else
    {
        #nameRenamed =#(#tt,#n2,#n3);
    }
}
			)
		;

nameRenamedDecorated:
!	        #(t1:B_CPRED 
				n1:nameRenamed 
{
    if (aaffecter== true)
    { 
        if (t1.toString().compareTo(#name.toString())==0)
        {
            #nameRenamedDecorated = (MyNode) astFactory.dupTree(#transfert);
        }
        else
        {
            #nameRenamedDecorated =#(#t1,#n1);
        }
    }
    else
    {
        #nameRenamedDecorated =#(#t1,#n1);
    }
}
			)
			|
			t2:nameRenamed
		;


listPredicate	:	
            #(tt:B_COMMA	listPredicate 	predicate	)
		|	                                predicate
;

list_New_Predicate:	
            #(tt:B_COMMA	list_New_Predicate	new_predicate	)
		|	                                    new_predicate
;

new_predicate	:	
            #(t1:B_SEMICOLON	new_predicate	predicate)
		|	#(t2:B_PARALLEL		new_predicate	predicate)
		|	                                    predicate
;

list_identifier	: 	
            #(tt:B_COMMA	list_identifier 	n1:B_IDENTIFIER )
        |	                                    n2:B_IDENTIFIER
;

// End of file gsl.g
