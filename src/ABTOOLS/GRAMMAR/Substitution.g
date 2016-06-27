
// 
// 	Author		:	Boulanger Jean-Louis
//  	Email       	:   	jean.louis.boulanger@wanadoo.fr
// 	File		:	Substitution.g
//	Descripton	:	A Substitution tools written in ANTLR 2.7.0 and more
//
//	
//	Copyright 2001-2009 Boulanger Jean-Louis
//

// Releases
//	August 04 	2001	v 0.1 
//		- Creation
//		- First constructon ANY, VAR
//
//	December	2001	v 0.1.1
//		- Pretty Printing
//
//	May	  	    2002    v 0.1.2
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//  October     2003    v 0.1.3
//      - Pretty-Printing
//      - Add somes substitutions
//  April       2004    v 0.2
//      - Packaging
//  October     2004    v 1.2
//      - Debugging
//  November    2004
//      - Add PO and APO token 
//  August      2005
//      - Pretty printing

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
	import antlr.ASTFactory;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*;
}

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/


class Substitution extends TreeParser;
options
{
    importVocab 	= PO;
    exportVocab	    = PO_GSL;
    buildAST 	    = true;
    ASTLabelType 	= "MyNode";
 
    // Copied following options from java grammar.
    codeGenMakeSwitchThreshold = 3;
    codeGenBitsetTestThreshold = 4;

    k = 1;
}

// Indtroduce some behaviours....

{
// Package information
    protected String module ="Substitution.g";
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

// Create some basic node

	
	public MyNode Such ( MyNode pred, MyNode subst)
	{
		ASTFactory astFactory = new ASTFactory();
		astFactory.setASTNodeType("MyNode");
		return #([GSL_SUCH,"|"], #pred, #subst);
	}



}

// Grammar become HERE
// -------------------


// DOC:
// 	xx! : ...
// 	indique que l'arbre associee a la regle xx n'est pas produit automatiquement


buildAndEvalGoal [MyNode pp, MyNode ii] returns [MyNode res]
{
   res = null;
}
    :
{
System.out.println("BEGIN : buildAndEZvalGoal");

   MyNode newgoal = #(#[APO,"APO"], #([SUBST_TO,"[["], #pp,  #ii));
   res = agoal(newgoal);

System.out.println("END : buildAndEZvalGoal");
}
    ;

agoal returns [MyNode res]
{
   res = null;
}
    :
!			#(SUBST_TO ii:instruction pp:predicate
{
    if (ii == null)                       // cas du skip qui produit null
		res = #pp;
	else	                              // cas general
		res = #(SUBST_TO, #pp,  #ii);
}
			)
|
!           #(BTRUE
{
   res = ##;
}
            )
;

/** 
 *  Root Rule
 **/


traitement 	:
    #(PO 
        traitement
        traitement
    )
    | a_PO
;

a_PO :
    #(APO info)
;

info :
    #(B_IMPLIES 
        predicate 
        goal
    )
;

goal! :
        #(SUBST_TO ii:instruction pp:predicate
{
	if (ii == null)
		// cas du skip qui produit null
		#goal = #pp;
	else	// cas general
		#goal = #(SUBST_TO, #pp,  #ii);
}
        )
    |   #(t:BTRUE
{
    #goal = #t;
}
        )
;

instruction	:
	        #(t1:PARALLEL 	
				instruction
			 	instruction
			 )
			|
			#(t2:SEQUENTIAL	
				instruction
				instruction
			 )
			|
!			#("skip" 
//	MR 6.2
{
	#instruction = null;
}
			)
			|
!			#("BEGIN"		// C.12 Line 1
				i4:instruction
//	MR 6.1
{
	#instruction = #i4;
} 
			 )
			|
// 	BBOOK $4.7 C.12 Line 4
// 	MR 6.4 
!			#("PRE"
				p5:predicate
				i5:instruction
{
	#instruction = #([GSL_SUCH,"|"], p5, i5);
}
			)
			|
// 	MR 6.5
!			#("ASSERT" 
				p6:predicate
				i6:instruction
{
// A VERIFIER dans le BBOOK
	#instruction = #([B_AND,"&"], #p6, #([GSL_GUARD,"==>"],#p6,#i6));
}
				)
			|	
//
			#("IF"
 				tp7:predicate
				tb7:branche_then[tp7]
				(tb8:branche_elsif[tp7])*
				(tb9:branche_else[tp7])?
{
// A VERIFIER dans le BBOOK
//	#instruction = #([B_AND,"&"], #p6, #([GSL_GUARD,"==>"],#p6,#i6));
}
			)
			|
//	MR 6.6
!			#(t8:"CHOICE"
				l8:list_or
{
	#instruction = #l8;
}
			)
			|
			#(t9:"CASE"
				tt9:predicate
				branche_either
				(branche_or)*
				(branche_else[tt9])?
			)
			|
//	MR 6.10
!			#(t10:"ANY" 
				l10:listTypedIdentifier 
				p10:predicate
				i10:instruction
{
	#instruction = #([GSL_FOR_SUCH, "@"],l10, #([GSL_GUARD,"==>"], p10,i10));
} 
			)
			|
//	MR 6.11
!			#(t11:"LET"
				l11:listTypedIdentifier 
				p11:list_equal 
				i11:instruction
{
	#instruction = #([GSL_FOR_SUCH, "@"],l11, #([GSL_GUARD,"==>"], p11,i11));
} 
			)
			|
			#(t12:"SELECT"
				tt12:predicate 
				branche_then[tt12] 
				(branche_when)*
				(branche_else[tt12])?
			)
			|
			#(t13:"WHILE"
				predicate
				instruction
				variant_or_no
			)
			|
!			#(tt:"VAR"			// C12 page 741 4eme tableau ligne 3
				l14:listTypedIdentifier
				i14:instruction
{
	#instruction = #([GSL_FOR_SUCH,"@"],l14,i14);
}
			)
			|	simple_affect
		;


list_or		:
// 	MR 6.6
!			#(t1:"OR"
				l1:list_or
				i1:instruction
{
	#list_or	= #([GSL_GUARD,"==>"],#l1,#i1);
}
    )
|	instruction
;

branche_when	:	
!    #("WHEN"
        p:predicate
        i:instruction
{
	#branche_when = #([GSL_GUARD,"==>"],#p,#i);
}
    )
;

branche_either	:
!    #("EITHER" 
        p:predicate 
        i:instruction 
{
	#branche_either = #([GSL_GUARD,"==>"],#p,#i);
}
    )
;

branche_or	:
!	#("OR"
        p:predicate
        i:instruction
{
	#branche_or = #([GSL_GUARD,"==>"],#p,#i);
}
    )
;

branche_then  [AST pr]	:
!   #("THEN"
        i:instruction
{
	#branche_then = #([GSL_GUARD,"==>"], pr, i);
}
    )
;

branche_else  [AST pr]	:
!			#(tt:"ELSE"
				i:instruction
{
	#branche_else = #([GSL_GUARD,"==>"], #([B_NOT,"NOT"],pr), i);
}
			)
		;

branche_elsif [AST pr]:
			#(t1:"ELSIF"
				p:predicate
				i:instruction
{
	#branche_elsif = #([GSL_GUARD,"==>"], #([B_AND,"&"],#([B_NOT,"NOT"],pr),p), i);
}
			)
		;


variant_or_no	:	
    t1:"VARIANT"
    predicate 
    t2:"INVARIANT" 
    predicate
|
    t3:"INVARIANT"
    predicate
    t4:"VARIANT"
    predicate 
;

list_equal	: 	
    #(B_AND 	
        list_equal
        an_equal
    )
|	an_equal	
;


an_equal	:
	#(B_EQUAL 	
        B_IDENTIFIER 
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
    )
|
    #(t2:APPLY_TO
        func_call 
        list_New_Predicate
    )
|
    #(t3:B_LPAREN
        func_call
        list_New_Predicate
    )
|
    #(t4:B_QUOTEIDENT
        func_call
        func_call
    )
|
    nameRenamed 
;

func_param	:
	list_New_Predicate
;

list_New_Predicate:	
    #(tt:B_COMMA 
        list_New_Predicate
        new_predicate
    )
|	new_predicate
;

new_predicate	:	
    #(t1:B_SEMICOLON 
        new_predicate
        predicate
    )
|
    #(t2:B_PARALLEL  
        new_predicate
        predicate
    )
|
    predicate
;

nameRenamed	:
	B_IDENTIFIER
|   #(tt:B_POINT 
        nameRenamed
        nameRenamed
    )
;

nameRenamedDecorated:
	#(B_CPRED 
        nameRenamed 
    )
|
    nameRenamed
;

nameRenameDecoratedInverted:
    #(B_TILDE 
        nameRenamedDecorated 
    )
|	nameRenamedDecorated
;

list_identifier	: 	
    #(tt:B_COMMA
        list_identifier 
        n1:B_IDENTIFIER 
    )
|	B_IDENTIFIER
;

listPredicate	:	
    #(tt:B_COMMA
        listPredicate
        predicate
    )
|	predicate
;

a_func_call	:
//!	#(A_FUNC_CALL
//        aa:afc
//{
//	#a_func_call = #aa;
//}
//    )
//|
   afc
;

afc		:
	#(FUNC_CALL_PARAM
        afc
        listPredicate
    )
|
    #(t1:B_QUOTEIDENT
        afc
        afc
    )
|	nameRenamed
;

list_func_call	:	
    #(tt:B_COMMA
        list_func_call
        a_func_call
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
        listPredicate
    )
|
    #(t2:B_OUT	 	
        list_func_call
        func_call
    )
|
// 	MR 6.12
//	BJL : A FAIRE
// 		PB introduction de variable temporaire pour chaque variable de list_func_call
//		Voir doc technique .....
    #(t3:INSET
        list_func_call
        predicate
        
    )
|
!	#(t4:B_BECOME_ELEM
        list_func_call
        predicate
{
    // ForALL Y WHERE [Y:=list_func_call]predicate THEN list_func_call := Y END
    // @ YList. [YList := list_func_call]predicate => list_func_call := YLIst

//#simple_affcet = #([GSL_FOR_SUCH, "@"],l11, #([GSL_GUARD,"==>"], p11,i11));
}
    )
|	a_func_call
;



/** 
 *  PREDICATE 
 **/

predicate	:
	!		#(t:BTRUE
{
	#predicate = #t;
}
 			)
			|
			#(a1:B_NOT
				predicate
			)
			|
			#(a2:B_RAN
				predicate
			)
			|
			#(a3:B_DOM
				predicate
			)
			|
			#(t1:B_AND
				predicate
				predicate
			)	
			|	
			#(t2:"or"
 				predicate 
				predicate
			)
			|
			#(t3:B_IMPLIES
				predicate
				predicate
			)
			|
			#(t4:B_EQUIV
	 			predicate 
				predicate
			)
			|
			#(t5:B_MULT
		 		predicate
			 	predicate
			)
			|	
			#(t6:B_POWER
				predicate
			 	predicate
			)
			|
			#(t7:B_DIV
	  			predicate
			 	predicate
			)
			|
			#(t8:"mod"
	  			predicate
			 	predicate
			)
			|
			#(t9:UNARY_ADD
			 	predicate
			)
			|
			#(t10:UNARY_MINUS
			 	predicate
			)
			|
		 	#(t11:B_ADD 
	  			predicate
			 	predicate
			)
			|
			#(t12:B_MINUS
		 		predicate
			 	predicate
			)
			|
			#(t13:B_EQUAL
	 			predicate
			 	predicate
			)
			|
			#(t14:B_LESS
		 		predicate
				predicate
			)
			|
			#(t15:B_GREATER
	 			predicate
				predicate
			)
			|
			#(t16:B_NOTEQUAL
				predicate
				predicate
			)
			|
			#(t17:B_LESSTHANEQUAL
		 		predicate	
				predicate
			)
			|
			#(t18:B_GREATERTHANEQUAL
				predicate
				predicate
			)
			|
			#(t19:B_INSET
				predicate
				predicate
			)
			|
			#(t20:B_NOTINSET
				predicate 
				predicate
			)
			|
			#(t21:B_SUBSET
				predicate 
				predicate
			)
			|
			#(t22:B_NOTSUBSET
				predicate
				predicate
			)
			|
			#(t23:B_STRICTSUBSET
				predicate
				predicate
			)
			|
			#(t24:B_NOTSTRICTSBSET
				predicate
				predicate
			)
			|
			#(t25:B_CONCATSEQ
				predicate
				predicate
			)
			|
			#(t26:B_PREAPPSEQ
				predicate 
				predicate
			)
			|
			#(t27:B_APPSEQ
				predicate
				predicate
			)
			|
			#(t28:B_PREFIXSEQ
				predicate
				predicate
			)
			|	#(t29:B_SUFFIXSEQ		predicate		predicate)
			|	#(t30:B_RELATION		predicate		predicate)
			|	#(t31:B_PARTIAL			predicate 	 	predicate)
			|	#(t32:B_TOTAL			predicate 	 	predicate)
			|	#(t33:B_PARTIAL_INJECT		predicate 		predicate)
			|	#(t34:B_TOTAL_INJECT		predicate 		predicate)
			|	#(t35:B_PARTIAL_SURJECT		predicate 		predicate)
			|	#(t36:B_TOTAL_SURJECT		predicate 		predicate)
			|	#(t37:B_BIJECTION 		predicate 		predicate)
			|	#(t38:B_DOMAINRESTRICT		predicate 	 	predicate)
			|	#(t39:B_RANGERESTRICT		predicate 		predicate)
			|	#(t40:B_DOMAINSUBSTRACT		predicate 		predicate)
			|	#(t41:B_RANGESUBSTRACT		predicate 		predicate)
			|	#(t42:B_OVERRIDEFORWARD		predicate 		predicate)
			|	#(t43:B_OVERRIDEBACKWARD 	predicate		predicate)
			|	#(t44:B_RELPROD 		predicate 		predicate)
			|	#(t45:B_UNION 			predicate 		predicate)
			|	#(t46:B_INTER 			predicate 		predicate)
			|	#(t48:B_MAPLET 			predicate		predicate)
			|	#(t49:LIST_VAR 			predicate		predicate)
			|	cset_description
			;

cset_description :
    basic_sets 
|	cbasic_value
|	#("bool"	predicate			)
|	#(B_BRACKOPEN	listPredicate			)
|	#(B_RANGE 	predicate 	predicate	)
|	B_EMPTYSET 
|	#(B_CURLYOPEN 	cvalue_set			)
|	B_SEQEMPTY
|	is_record 
|	quantification
|	q_lambda
;

// Dans un ensemble il peut y avoir 
cvalue_set	:
    #(B_SUCH
        list_var
        predicate
    )
|	#(B_COMMA
        cvalue_set
        predicate
    )
|	predicate
;

cbasic_value	:
    B_ASTRING
    
|	B_NUMBER

|	#(B_TILDE
 		predicate
    )
|	nameRenamedDecorated
|	#(B_LPAREN
        predicate
        list_New_Predicate
    )
|	#(PARENT 
        pred_func_composition
    )
|	#(B_QUOTEIDENT 
        predicate
        predicate 		
    )
|	#(APPLY_TO
		predicate
        predicate
    )
|	"TRUE"
|	"FALSE"
;

pred_func_composition:
    #(B_SEMICOLON 
        pred_func_composition
        predicate
    )
|	#(B_PARALLEL  
        pred_func_composition 		 
        predicate
    )
|	#(B_COMMA	 
        pred_func_composition 		 
        predicate
    )
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
    #(B_FORALL 				
        list_var 		 
        predicate 		 
    )
|	#(B_EXISTS 				
        list_var 		 
        predicate 		
    )
;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda	:
    #(B_LAMBDA 
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
|   list_identifier 
;

listTypedIdentifier: 	
    #(B_COMMA
        listTypedIdentifier
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
    #("rec"
        listrecord
    )
|
    #("struct"
        listrecord 
    )
;

listrecord	:	
    #(B_COMMA 
        listrecord 
        a_record
    )
|	a_record
;

a_record:
    #(B_SELECTOR 
        B_IDENTIFIER
        predicate
    )
|	predicate			
;

// Somes imaginaries tokens for manipulate PO
dummy:
    	GSL_SUCH
|	GSL_FOR_SUCH
|	GSL_GUARD
|	GSL_BOUNDED
;

// END Of FILE Substitution.g

















