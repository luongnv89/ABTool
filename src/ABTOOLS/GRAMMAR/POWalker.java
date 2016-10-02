// $ANTLR 2.7.6 (2005-12-22): "POWalker.g" -> "POWalker.java"$

    package ABTOOLS.GRAMMAR;

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

// usefull packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// our packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
    import ABTOOLS.PRINTING.*;
    import ABTOOLS.PO.*;


public class POWalker extends antlr.TreeParser       implements POWalkerTokenTypes
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


public POWalker() {
	tokenNames = _tokenNames;
}

	public final void analyze_PO(AST _t) throws RecognitionException {
		
		MyNode analyze_PO_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==PO)) {
				AST __t2 = _t;
				MyNode tmp1_AST_in = (MyNode)_t;
				match(_t,PO);
				_t = _t.getFirstChild();
				analyze_PO(_t);
				_t = _retTree;
				analyze_PO(_t);
				_t = _retTree;
				_t = __t2;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==APO)) {
				analyze_APO(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void analyze_APO(AST _t) throws RecognitionException {
		
		MyNode analyze_APO_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t4 = _t;
			MyNode tmp2_AST_in = (MyNode)_t;
			match(_t,APO);
			_t = _t.getFirstChild();
			
			initializeString();
			
			printToString("<ProofObligation>");
			
			
			printToString("<Predicate>");
			
			predicate(_t);
			_t = _retTree;
			
			printToString("</Predicate>");
			
			
			printToString("</ProofObligation>");
			
			finalizeString();
			
			_t = __t4;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/** 
 *  PREDICATE 
 **/
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BTRUE:
			{
				AST __t28 = _t;
				MyNode tmp3_AST_in = (MyNode)_t;
				match(_t,BTRUE);
				_t = _t.getFirstChild();
				
				printToString("<BTRUE>");
				
				_t = __t28;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t29 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				
					printToString("<Neg>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Neg>");
				
				_t = __t29;
				_t = _t.getNextSibling();
				break;
			}
			case B_AND:
			{
				AST __t30 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				
					printToString("<And>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</And");
				
				_t = __t30;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t31 = _t;
				MyNode tmp6_AST_in = (MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				
					printToString("<Or>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Or>");
				
				_t = __t31;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t32 = _t;
				MyNode tmp7_AST_in = (MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				
					printToString("<Implies>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Implies>");
				
				_t = __t32;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t33 = _t;
				MyNode tmp8_AST_in = (MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				
					printToString("<Equiv>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Equiv>");
				
				_t = __t33;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t34 = _t;
				MyNode tmp9_AST_in = (MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				
					printToString("<Mul>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Mul>");
				
				_t = __t34;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t35 = _t;
				MyNode tmp10_AST_in = (MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				
					printToString("<Puissance>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Puissance>");
				
				_t = __t35;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t36 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				
					printToString("<Div>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Div>");
				
				_t = __t36;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t37 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				
					printToString("<Modulo>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Modulo>");
				
				_t = __t37;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t38 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				
					printToString("<Plus>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Plus>");
				
				_t = __t38;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t39 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<UMinus>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</UMinus>");
				
				_t = __t39;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t40 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				
					printToString("<Plus>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Plus>");
				
				_t = __t40;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t41 = _t;
				MyNode tmp16_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<Minus>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Minus>");
				
				_t = __t41;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t42 = _t;
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				
					printToString("<Equal>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Equal>");
				
				_t = __t42;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t43 = _t;
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				
					printToString("<Less>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Less>");
				
				_t = __t43;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t44 = _t;
				MyNode tmp19_AST_in = (MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				
					printToString("<Greater>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Greater>");
				
				_t = __t44;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t45 = _t;
				MyNode tmp20_AST_in = (MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<NotEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotEqual>");
				
				_t = __t45;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t46 = _t;
				MyNode tmp21_AST_in = (MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<LessEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</LessEqual>");
				
				_t = __t46;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t47 = _t;
				MyNode tmp22_AST_in = (MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<GreaterEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</GreaterEqual>");
				
				_t = __t47;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t48 = _t;
				MyNode tmp23_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				
					printToString("<In>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</In>");
				
				_t = __t48;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t49 = _t;
				MyNode tmp24_AST_in = (MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				
					printToString("<NotIn>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotIn>");
				
				_t = __t49;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t50 = _t;
				MyNode tmp25_AST_in = (MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				
					printToString("<SubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SubSet>");
				
				_t = __t50;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t51 = _t;
				MyNode tmp26_AST_in = (MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				
					printToString("<NotSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotSubSet>");
				
				_t = __t51;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t52 = _t;
				MyNode tmp27_AST_in = (MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				
					printToString("<StrictSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</StrictSubSet>");
				
				_t = __t52;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t53 = _t;
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				
					printToString("<NotStrictSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotStrictSubSet>");
				
				_t = __t53;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t54 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				
					printToString("<ConcatSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</ConcatSeq>");
				
				_t = __t54;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t55 = _t;
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				
					printToString("<>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t55;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t56 = _t;
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t56;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t57 = _t;
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				
					printToString("<PrefixSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PrefixSeq>");
				
				_t = __t57;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t58 = _t;
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				
					printToString("<SuffixSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SuffixSeq>");
				
				_t = __t58;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t59 = _t;
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t59;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t60 = _t;
				MyNode tmp35_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				
					printToString("<PartialFunc>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialFunc>");
				
				_t = __t60;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t61 = _t;
				MyNode tmp36_AST_in = (MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				
					printToString("<TotalFunc>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalFunc>");
				
				_t = __t61;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t62 = _t;
				MyNode tmp37_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				
					printToString("<PartialInj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialInj>");
				
				_t = __t62;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t63 = _t;
				MyNode tmp38_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				
					printToString("<TotalInj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalInj>");
				
				_t = __t63;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t64 = _t;
				MyNode tmp39_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				
					printToString("<PartialSurj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialSurj>");
				
				_t = __t64;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t65 = _t;
				MyNode tmp40_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				
					printToString("<TotalSurj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalSurj>");
				
				_t = __t65;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t66 = _t;
				MyNode tmp41_AST_in = (MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				
					printToString("<TotalBij>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalBij>");
				
				_t = __t66;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t67 = _t;
				MyNode tmp42_AST_in = (MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				
					printToString("<DomRestrict>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</DomRestrict>");
				
				_t = __t67;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t68 = _t;
				MyNode tmp43_AST_in = (MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				
					printToString("<RanRestrict>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RanRestrict>");
				
				_t = __t68;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t69 = _t;
				MyNode tmp44_AST_in = (MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				
					printToString("<DomSubstract>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</DomSubstract>");
				
				_t = __t69;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t70 = _t;
				MyNode tmp45_AST_in = (MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				
					printToString("<RanSubstract>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RanSubstract>");
				
				_t = __t70;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t71 = _t;
				MyNode tmp46_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				
					printToString("<OverRideFwd>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</OverRideFwd>");
				
				_t = __t71;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t72 = _t;
				MyNode tmp47_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				
					printToString("<OverRideBck>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</OverRideBck>");
				
				_t = __t72;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t73 = _t;
				MyNode tmp48_AST_in = (MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				
					printToString("<RelProd>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RelProd>");
				
				_t = __t73;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t74 = _t;
				MyNode tmp49_AST_in = (MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				
					printToString("<UnionSets>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</UnionSets>");
				
				_t = __t74;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t75 = _t;
				MyNode tmp50_AST_in = (MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				
					printToString("<InterSets>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</InterSets>");
				
				_t = __t75;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t76 = _t;
				MyNode tmp51_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				
					printToString("<Maplet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Maplet>");
				
				_t = __t76;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t77 = _t;
				MyNode tmp52_AST_in = (MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t77;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t78 = _t;
				MyNode tmp53_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				
					printToString("<Ran>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Ran>");
				
				_t = __t78;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t79 = _t;
				MyNode tmp54_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				
					printToString("<Dom>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Dom>");
				
				_t = __t79;
				_t = _t.getNextSibling();
				break;
			}
			case B_EMPTYSET:
			case LITERAL_INT:
			case LITERAL_INTEGER:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case LITERAL_STRING:
			{
				basic_sets(_t);
				_t = _retTree;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			case B_LPAREN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_TILDE:
			case B_NUMBER:
			case B_QUOTEIDENT:
			case APPLY_TO:
			case PARENT:
			{
				cbasic_value(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_bool:
			{
				AST __t80 = _t;
				MyNode tmp55_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				
					printToString("<BoolEvaluation>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</BoolEvaluation>");
				
				_t = __t80;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t81 = _t;
				MyNode tmp56_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				_t = __t81;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t82 = _t;
				MyNode tmp57_AST_in = (MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				
					printToString("<SetRange>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SetRange>");
				
				_t = __t82;
				_t = _t.getNextSibling();
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t83 = _t;
				MyNode tmp58_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				_t = __t83;
				_t = _t.getNextSibling();
				break;
			}
			case B_SEQEMPTY:
			{
				{
				
					printToString("<SeqEmpty>");
				
				MyNode tmp59_AST_in = (MyNode)_t;
				match(_t,B_SEQEMPTY);
				_t = _t.getNextSibling();
				
					printToString("</SeqEmpty>");
				
				}
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record(_t);
				_t = _retTree;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				quantification(_t);
				_t = _retTree;
				break;
			}
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			{
				q_lambda(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t6 = _t;
				MyNode tmp60_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				MyNode tmp61_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				_t = __t6;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void listrecord(AST _t) throws RecognitionException {
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t8 = _t;
				MyNode tmp62_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				a_record(_t);
				_t = _retTree;
				_t = __t8;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				a_record(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t10 = _t;
				MyNode tmp63_AST_in = (MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				_t = __t10;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t11 = _t;
				MyNode tmp64_AST_in = (MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				_t = __t11;
				_t = _t.getNextSibling();
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_New_Predicate(AST _t) throws RecognitionException {
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t13 = _t;
				MyNode tmp65_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				new_predicate(_t);
				_t = _retTree;
				_t = __t13;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				new_predicate(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void new_predicate(AST _t) throws RecognitionException {
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t15 = _t;
				MyNode tmp66_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t15;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t16 = _t;
				MyNode tmp67_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t16;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			case B_IMPLIES:
			case LITERAL_or:
			case B_AND:
			case B_EQUIV:
			case B_EQUAL:
			case B_SUBSET:
			case B_NOTSUBSET:
			case B_STRICTSUBSET:
			case B_NOTSTRICTSBSET:
			case B_LESS:
			case B_GREATER:
			case B_NOTEQUAL:
			case B_LESSTHANEQUAL:
			case B_GREATERTHANEQUAL:
			case B_CONCATSEQ:
			case B_PREAPPSEQ:
			case B_APPSEQ:
			case B_PREFIXSEQ:
			case B_SUFFIXSEQ:
			case LITERAL_bool:
			case B_LPAREN:
			case B_RELATION:
			case B_PARTIAL:
			case B_TOTAL:
			case B_PARTIAL_INJECT:
			case B_TOTAL_INJECT:
			case B_PARTIAL_SURJECT:
			case B_TOTAL_SURJECT:
			case B_BIJECTION:
			case B_DOMAINRESTRICT:
			case B_RANGERESTRICT:
			case B_DOMAINSUBSTRACT:
			case B_RANGESUBSTRACT:
			case B_OVERRIDEFORWARD:
			case B_OVERRIDEBACKWARD:
			case B_RELPROD:
			case B_UNION:
			case B_INTER:
			case B_MAPLET:
			case B_MULT:
			case B_POWER:
			case B_DIV:
			case LITERAL_mod:
			case B_ADD:
			case B_MINUS:
			case B_SEQEMPTY:
			case B_BRACKOPEN:
			case B_RANGE:
			case B_EMPTYSET:
			case B_CURLYOPEN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_TILDE:
			case B_NUMBER:
			case B_QUOTEIDENT:
			case B_FORALL:
			case B_EXISTS:
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			case LITERAL_struct:
			case B_INSET:
			case LITERAL_INT:
			case LITERAL_INTEGER:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case UNARY_ADD:
			case UNARY_MINUS:
			case LIST_VAR:
			case APPLY_TO:
			case PARENT:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				predicate(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp68_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t18 = _t;
				MyNode tmp69_AST_in = (MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				_t = __t18;
				_t = _t.getNextSibling();
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void nameRenamedDecorated(AST _t) throws RecognitionException {
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t20 = _t;
				MyNode tmp70_AST_in = (MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				_t = __t20;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void nameRenameDecoratedInverted(AST _t) throws RecognitionException {
		
		MyNode nameRenameDecoratedInverted_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_TILDE)) {
				AST __t22 = _t;
				MyNode tmp71_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				_t = __t22;
				_t = _t.getNextSibling();
			}
			else if (((_t.getType() >= B_IDENTIFIER && _t.getType() <= B_CPRED))) {
				nameRenamedDecorated(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_identifier(AST _t) throws RecognitionException {
		
		MyNode list_identifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode n1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t24 = _t;
				MyNode tmp72_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				_t = __t24;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp73_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void listPredicate(AST _t) throws RecognitionException {
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t26 = _t;
				MyNode tmp74_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t26;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void basic_sets(AST _t) throws RecognitionException {
		
		MyNode basic_sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
			printToString("<SetPredefined>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_INT:
			{
				MyNode tmp75_AST_in = (MyNode)_t;
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				printToString("<INT>");
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp76_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				printToString("<INTEGER>");
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp77_AST_in = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				printToString("<BOOL>");
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp78_AST_in = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				printToString("<NAT>");
				break;
			}
			case 92:
			{
				MyNode tmp79_AST_in = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				printToString("<NAT1>");
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp80_AST_in = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				printToString("<NATURAL>");
				break;
			}
			case 94:
			{
				MyNode tmp81_AST_in = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				printToString("<NATURAL1>");
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp82_AST_in = (MyNode)_t;
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				printToString("<STRING>");
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp83_AST_in = (MyNode)_t;
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				printToString("<SetEmpty>");
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			
			printToString("</SetPredefined>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void cbasic_value(AST _t) throws RecognitionException {
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				t1 = (MyNode)_t;
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				
				printToString("<STRING>");
					printToString(t1.getText());
				printToString("</STRING>");
				
				break;
			}
			case B_NUMBER:
			{
				t2 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
				printToString("<Number>");
					printToString(t2.getText());
				printToString("</Number>");
				
				break;
			}
			case B_TILDE:
			{
				AST __t89 = _t;
				MyNode tmp84_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				
				printToString("<Tilde>");
				
				predicate(_t);
				_t = _retTree;
				
				printToString("</Tilde>");
				
				_t = __t89;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			{
				nameRenamedDecorated(_t);
				_t = _retTree;
				break;
			}
			case B_LPAREN:
			{
				AST __t90 = _t;
				MyNode tmp85_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString("<PredParen>");
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString("</PredParen>");
				
				_t = __t90;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t91 = _t;
				MyNode tmp86_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				
					printToString("<PredParen>");
				
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString("</PredParen>");
				
				_t = __t91;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t92 = _t;
				MyNode tmp87_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t92;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t93 = _t;
				MyNode tmp88_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t93;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				{
				
					printToString("<TRUE>");
				
				MyNode tmp89_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					printToString("</TRUE>");
				
				}
				break;
			}
			case LITERAL_FALSE:
			{
				{
				
					printToString("<FALSE>");
				
				MyNode tmp90_AST_in = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					printToString("</FALSE>");
				
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void cvalue_set(AST _t) throws RecognitionException {
		
		MyNode cvalue_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t86 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t86;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t87 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t87;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			case B_IMPLIES:
			case LITERAL_or:
			case B_AND:
			case B_EQUIV:
			case B_EQUAL:
			case B_SUBSET:
			case B_NOTSUBSET:
			case B_STRICTSUBSET:
			case B_NOTSTRICTSBSET:
			case B_LESS:
			case B_GREATER:
			case B_NOTEQUAL:
			case B_LESSTHANEQUAL:
			case B_GREATERTHANEQUAL:
			case B_CONCATSEQ:
			case B_PREAPPSEQ:
			case B_APPSEQ:
			case B_PREFIXSEQ:
			case B_SUFFIXSEQ:
			case LITERAL_bool:
			case B_LPAREN:
			case B_RELATION:
			case B_PARTIAL:
			case B_TOTAL:
			case B_PARTIAL_INJECT:
			case B_TOTAL_INJECT:
			case B_PARTIAL_SURJECT:
			case B_TOTAL_SURJECT:
			case B_BIJECTION:
			case B_DOMAINRESTRICT:
			case B_RANGERESTRICT:
			case B_DOMAINSUBSTRACT:
			case B_RANGESUBSTRACT:
			case B_OVERRIDEFORWARD:
			case B_OVERRIDEBACKWARD:
			case B_RELPROD:
			case B_UNION:
			case B_INTER:
			case B_MAPLET:
			case B_MULT:
			case B_POWER:
			case B_DIV:
			case LITERAL_mod:
			case B_ADD:
			case B_MINUS:
			case B_SEQEMPTY:
			case B_BRACKOPEN:
			case B_RANGE:
			case B_EMPTYSET:
			case B_CURLYOPEN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_TILDE:
			case B_NUMBER:
			case B_QUOTEIDENT:
			case B_FORALL:
			case B_EXISTS:
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			case LITERAL_struct:
			case B_INSET:
			case LITERAL_INT:
			case LITERAL_INTEGER:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case UNARY_ADD:
			case UNARY_MINUS:
			case LIST_VAR:
			case APPLY_TO:
			case PARENT:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				predicate(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void quantification(AST _t) throws RecognitionException {
		
		MyNode quantification_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t103 = _t;
				MyNode tmp91_AST_in = (MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				printToString("<ForAll>");
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				printToString("</ForAll>");
				_t = __t103;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t104 = _t;
				MyNode tmp92_AST_in = (MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				printToString("<Exists>");
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				printToString("</Exists>");
				_t = __t104;
				_t = _t.getNextSibling();
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void q_lambda(AST _t) throws RecognitionException {
		
		MyNode q_lambda_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t106 = _t;
				MyNode tmp93_AST_in = (MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				
					printToString("<Lambda>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</Lambda>");
				
				_t = __t106;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t107 = _t;
				MyNode tmp94_AST_in = (MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				
					printToString("<PI>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</PI>");
				
				_t = __t107;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t108 = _t;
				MyNode tmp95_AST_in = (MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				
					printToString("<SIGMA>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</SIGMA>");
				
				_t = __t108;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t109 = _t;
				MyNode tmp96_AST_in = (MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				
					printToString("<UnionQ>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</UnionQ>");
				
				_t = __t109;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t110 = _t;
				MyNode tmp97_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				
					printToString("<InterQ>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</InterQ>");
				
				_t = __t110;
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_var(AST _t) throws RecognitionException {
		
		MyNode list_var_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<idList>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t115 = _t;
				MyNode tmp98_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				_t = __t115;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				list_identifier(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				printToString("</idList>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void pred_func_composition(AST _t) throws RecognitionException {
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t97 = _t;
				MyNode tmp99_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToString("<RelSeqComp>");
				
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RelSeqComp>");
				
				_t = __t97;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t98 = _t;
				MyNode tmp100_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				
					printToString("<ParallelComp>");
				
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</ParallelComp>");
				
				_t = __t98;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t99 = _t;
				MyNode tmp101_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t99;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			case B_IMPLIES:
			case LITERAL_or:
			case B_AND:
			case B_EQUIV:
			case B_EQUAL:
			case B_SUBSET:
			case B_NOTSUBSET:
			case B_STRICTSUBSET:
			case B_NOTSTRICTSBSET:
			case B_LESS:
			case B_GREATER:
			case B_NOTEQUAL:
			case B_LESSTHANEQUAL:
			case B_GREATERTHANEQUAL:
			case B_CONCATSEQ:
			case B_PREAPPSEQ:
			case B_APPSEQ:
			case B_PREFIXSEQ:
			case B_SUFFIXSEQ:
			case LITERAL_bool:
			case B_LPAREN:
			case B_RELATION:
			case B_PARTIAL:
			case B_TOTAL:
			case B_PARTIAL_INJECT:
			case B_TOTAL_INJECT:
			case B_PARTIAL_SURJECT:
			case B_TOTAL_SURJECT:
			case B_BIJECTION:
			case B_DOMAINRESTRICT:
			case B_RANGERESTRICT:
			case B_DOMAINSUBSTRACT:
			case B_RANGESUBSTRACT:
			case B_OVERRIDEFORWARD:
			case B_OVERRIDEBACKWARD:
			case B_RELPROD:
			case B_UNION:
			case B_INTER:
			case B_MAPLET:
			case B_MULT:
			case B_POWER:
			case B_DIV:
			case LITERAL_mod:
			case B_ADD:
			case B_MINUS:
			case B_SEQEMPTY:
			case B_BRACKOPEN:
			case B_RANGE:
			case B_EMPTYSET:
			case B_CURLYOPEN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_TILDE:
			case B_NUMBER:
			case B_QUOTEIDENT:
			case B_FORALL:
			case B_EXISTS:
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			case LITERAL_struct:
			case B_INSET:
			case LITERAL_INT:
			case LITERAL_INTEGER:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case UNARY_ADD:
			case UNARY_MINUS:
			case LIST_VAR:
			case APPLY_TO:
			case PARENT:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				predicate(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void q_operande(AST _t) throws RecognitionException {
		
		MyNode q_operande_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t112 = _t;
			MyNode tmp102_AST_in = (MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			_t = __t112;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"B_COMMA",
		"an identifer",
		"B_POINT",
		"B_CPRED",
		"B_IMPLIES",
		"\"or\"",
		"B_AND",
		"B_EQUIV",
		"B_EQUAL",
		"B_SUBSET",
		"B_NOTSUBSET",
		"B_STRICTSUBSET",
		"B_NOTSTRICTSBSET",
		"B_LESS",
		"B_GREATER",
		"B_NOTEQUAL",
		"B_LESSTHANEQUAL",
		"B_GREATERTHANEQUAL",
		"B_CONCATSEQ",
		"B_PREAPPSEQ",
		"B_APPSEQ",
		"B_PREFIXSEQ",
		"B_SUFFIXSEQ",
		"\"bool\"",
		"B_LPAREN",
		"B_RPAREN",
		"B_RELATION",
		"B_PARTIAL",
		"B_TOTAL",
		"B_PARTIAL_INJECT",
		"B_TOTAL_INJECT",
		"B_PARTIAL_SURJECT",
		"B_TOTAL_SURJECT",
		"B_BIJECTION",
		"B_DOMAINRESTRICT",
		"B_RANGERESTRICT",
		"B_DOMAINSUBSTRACT",
		"B_RANGESUBSTRACT",
		"B_OVERRIDEFORWARD",
		"B_OVERRIDEBACKWARD",
		"B_RELPROD",
		"B_UNION",
		"B_INTER",
		"B_MAPLET",
		"B_MULT",
		"B_POWER",
		"B_DIV",
		"\"mod\"",
		"B_ADD",
		"B_MINUS",
		"B_SEQEMPTY",
		"B_BRACKOPEN",
		"B_BRACKCLOSE",
		"B_RANGE",
		"B_EMPTYSET",
		"B_CURLYOPEN",
		"B_CURLYCLOSE",
		"B_SUCH",
		"a string literal",
		"\"TRUE\"",
		"\"FALSE\"",
		"\"rec\"",
		"B_TILDE",
		"a number",
		"B_QUOTEIDENT",
		"\"ran\"",
		"\"not\"",
		"\"dom\"",
		"\"min\"",
		"\"max\"",
		"\"card\"",
		"B_SEMICOLON",
		"B_PARALLEL",
		"B_FORALL",
		"B_EXISTS",
		"B_LAMBDA",
		"\"PI\"",
		"\"SIGMA\"",
		"\"UNION\"",
		"\"INTER\"",
		"\"struct\"",
		"B_INSET",
		"\"INT\"",
		"\"INT1\"",
		"\"INTEGER\"",
		"\"INTEGER1\"",
		"\"BOOL\"",
		"\"NAT\"",
		"\"NAT1\"",
		"\"NATURAL\"",
		"\"NATURAL1\"",
		"UNARY_ADD",
		"UNARY_MINUS",
		"ELEM_SET",
		"LIST_VAR",
		"APPLY_TO",
		"PARENT",
		"B_SELECTOR",
		"PRODSET",
		"B_NOT",
		"B_DOM",
		"B_RAN",
		"B_MIN",
		"B_MAX",
		"B_CARD",
		"B_MOD",
		"B_NOTINSET",
		"\"STRING\"",
		"white space",
		"a comment",
		"NEWLINE",
		"B_DOUBLE_EQUAL",
		"B_dollars",
		"B_OUT",
		"B_garde",
		"B_BECOME_ELEM",
		"B_SIMPLESUBST",
		"DIGIT",
		"ALPHA",
		"an escape sequence",
		"\"MACHINE\"",
		"\"END\"",
		"\"REFINEMENT\"",
		"\"IMPLEMENTATION\"",
		"\"CONSTRAINTS\"",
		"\"EXTENDS\"",
		"\"USES\"",
		"\"INCLUDES\"",
		"\"SEES\"",
		"\"IMPORTS\"",
		"\"PROMOTES\"",
		"\"REFINES\"",
		"\"CONSTANTS\"",
		"\"CONCRETE_CONSTANTS\"",
		"\"VISIBLE_CONSTANTS\"",
		"\"ABSTRACT_CONSTANTS\"",
		"\"HIDDEN_CONSTANTS\"",
		"\"SETS\"",
		"\"VALUES\"",
		"\"PROPERTIES\"",
		"\"VARIABLES\"",
		"\"ABSTRACT_VARIABLES\"",
		"\"VISIBLE_VARIABLES\"",
		"\"INVARIANT\"",
		"\"HIDDEN_VARIABLES\"",
		"\"CONCRETE_VARIABLES\"",
		"\"DEFINITIONS\"",
		"\"ASSERTIONS\"",
		"\"INITIALISATION\"",
		"\"OPERATIONS\"",
		"\"skip\"",
		"\"BEGIN\"",
		"\"PRE\"",
		"\"THEN\"",
		"\"ASSERT\"",
		"\"IF\"",
		"\"ELSIF\"",
		"\"CHOICE\"",
		"\"OR\"",
		"\"CASE\"",
		"\"OF\"",
		"\"EITHER\"",
		"\"ELSE\"",
		"\"ANY\"",
		"\"WHERE\"",
		"\"SELECT\"",
		"\"WHEN\"",
		"\"LET\"",
		"\"BE\"",
		"\"IN\"",
		"\"VARIANT\"",
		"\"WHILE\"",
		"\"DO\"",
		"\"VAR\"",
		"PARALLEL",
		"OP_DEF",
		"INSET",
		"FUNC_CALL_PARAM",
		"SEQUENTIAL",
		"EXP_DEF",
		"LIST_DEF",
		"SUBST_DEF",
		"SUBST_TO",
		"\"bouffon\"",
		"PO",
		"APO",
		"BTRUE",
		"GSL_SUCH",
		"GSL_FOR_SUCH",
		"GSL_GUARD",
		"GSL_BOUNDED"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -3530822108395339808L, 215083330297887L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -3530822108395339808L, 215220769251359L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -3530822108395339808L, 215083330304031L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	}
	
