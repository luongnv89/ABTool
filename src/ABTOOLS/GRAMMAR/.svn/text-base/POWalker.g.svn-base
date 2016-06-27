
// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	POWalker.g
//	Descripton	:	PO Tree Walker for Another's B Parser written in ANTLR
//	
//	Copyright 2000-2009 Boulanger Jean-Louis
//

// Releases
//	November	2004    v 0.1 	
//      - Creation


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
    import ABTOOLS.PO.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class POWalker extends TreeParser;
options
{
	importVocab 	= PO_GSL;
    	buildAST 	= false;
    	ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
    codeGenMakeSwitchThreshold = 3;
    codeGenBitsetTestThreshold = 4;

	k = 1;
}

// Indtroduce some behaviours....

{
	String module = "POWalker.g";

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
	private static DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}

// Section for printing 
	
	public   StringBuffer    myBuffer;

    public String toString()
    {
		return myBuffer.toString();
	}

 	void printToString(String st)
	{
        debug.print(st);
	   	myBuffer.append(st);
 	}

	void initializeString()
	{
		myBuffer = new StringBuffer();
        	printToString("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n");
	}

	void finalizeString()
	{
		// au cas ou ....
	}


}

// Grammar become HERE
// -------------------

analyze_PO :
        #(PO 
            analyze_PO
            analyze_PO
        )
    |   analyze_APO
;

analyze_APO:
    #(APO 
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

a_record	:	
	#(B_SELECTOR 
		B_IDENTIFIER
		predicate
    	)
|	predicate			
;

listrecord	:
	#(B_COMMA 
		listrecord
        	a_record
    	)
|	a_record
;

is_record	:
    #("rec"
        listrecord
    )
|   #("struct"
        listrecord
    )
;

list_New_Predicate:	
    #(B_COMMA 
        list_New_Predicate
        new_predicate
    )
|	new_predicate
;

new_predicate	:
    #(B_SEMICOLON 
        new_predicate
        predicate
    )
|   #(B_PARALLEL  
        new_predicate
        predicate
    )
|   predicate
;

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
    #(B_TILDE 
        nameRenamedDecorated
    )
|	nameRenamedDecorated
;

list_identifier	:
    #(B_COMMA
        list_identifier
        n1:B_IDENTIFIER
    )
|	B_IDENTIFIER
;

listPredicate	:
    #(B_COMMA
        listPredicate
        predicate
    )
|	predicate
;

/** 
 *  PREDICATE 
 **/

predicate	:
            #(BTRUE
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

// Dans un ensemble il peut y avoir 
cvalue_set:
    #(t1:B_SUCH
        list_var
        predicate
    )
|	#(t2:B_COMMA
        cvalue_set
        predicate
    )
|	predicate
;

cbasic_value:
    t1:B_ASTRING
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

pred_func_composition:
    #(B_SEMICOLON
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

basic_sets	:
{
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

quantification:
    #(B_FORALL
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

q_lambda	:
    #(B_LAMBDA
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

q_operande	:
    #(B_SUCH
        list_var 
        predicate
        predicate
    )
;

list_var:
{
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

// END Of FILE POWalker.g
