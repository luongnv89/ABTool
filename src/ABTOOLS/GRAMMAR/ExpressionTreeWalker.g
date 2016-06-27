
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	ExpressionTreeWalker.g
//	Descripton	:	Tree Walker for Another's B Parser written in ANTLR
//	
//	Copyright 2004-2011 Boulanger Jean-Louis
//

// Releases
//  November  2004  v 1.0
//      - Creation
//  May 2007 
//      - pretty printing


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

class ExpressionTreeWalker extends TreeParser;
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
	String module = "ExpressionTreeWalker.g";

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

// (01/2001)
is_record	:
			#(t1:"rec"
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

listrecord	:
            #(tt:B_COMMA 
				listrecord
{
	printToString("\n"+out.Separator(","));
}
				a_record
			)
        |	a_record
;

a_record	:	
            #(B_SELECTOR 
				name:B_IDENTIFIER
{
	printToString(name);
	printToString(out.Separator(":"));
}
				predicate
			)
        |	predicate			
;

list_New_Predicate:	
            #(tt:B_COMMA 
				list_New_Predicate
{
	printToString(out.Separator(tt.getText()));
}
				new_predicate
			)
        |	new_predicate
		;

new_predicate	:
	        #(t1:B_SEMICOLON 
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

/**
 * Ne pas utiliser de fonction d'impression pour le renommage ....
 **/

nameRenamed	:	
            n1:B_IDENTIFIER
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

nameRenamedDecorated:	
            #(tt:B_CPRED 
				nameRenamed
{
	printToString(out.Separator(tt.getText()));
}
			)
			|
			nameRenamed
;

nameRenameDecoratedInverted:	
                #(tt:B_TILDE 
					nameRenamedDecorated
{
	printToString(out.Separator(tt.getText()));
}
				)
			|	nameRenamedDecorated
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

listPredicate	:
            #(tt:ELEM_SET
		 		listPredicate
{
	printToString(out.Separator(tt.getText()));
}
			 	predicate
			)
			|	predicate
;

a_func_call	:
    afc
;

afc	:
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


/** 
 *  PREDICATE 
 **/

predicate	:
	        #(t1:B_AND			
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



cset_description :
				basic_sets 
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

// Dans un ensemble il peut y avoir 
cvalue_set	:		#(t1:B_SUCH
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

cbasic_value	:
                t1:B_ASTRING
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

pred_func_composition:
		        #(B_SEMICOLON 
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

basic_sets	:	
	t1:"INT"			    {printToString(out.KeyWord(t1.getText()));}
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

quantification	:
                #(t70:B_FORALL
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

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda	:
                #(t1:B_LAMBDA
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

q_operande	:
	        #(t1:B_SUCH
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

// Dans le second cas il y a un blanc car pas de ( pour separer
list_var	:
	        #(B_LPAREN
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




// END Of FILE ExpressionTreeWalker.g
















