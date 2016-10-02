// $ANTLR 2.7.6 (2005-12-22): "GSL.g" -> "GSL.java"$

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

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*;


/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/
public class GSL extends antlr.TreeParser       implements GSLTokenTypes
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
public GSL() {
	tokenNames = _tokenNames;
}

	public final void traitement(AST _t) throws RecognitionException {
		
		MyNode traitement_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode traitement_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==PO)) {
				AST __t2 = _t;
				MyNode tmp1_AST = null;
				MyNode tmp1_AST_in = null;
				tmp1_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp1_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp1_AST);
				ASTPair __currentAST2 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PO);
				_t = _t.getFirstChild();
				traitement(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				traitement(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST2;
				_t = __t2;
				_t = _t.getNextSibling();
				traitement_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==APO)) {
				apo(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				traitement_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = traitement_AST;
		_retTree = _t;
	}
	
	public final void apo(AST _t) throws RecognitionException {
		
		MyNode apo_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode apo_AST = null;
		
		try {      // for error handling
			AST __t4 = _t;
			MyNode tmp2_AST = null;
			MyNode tmp2_AST_in = null;
			tmp2_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp2_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp2_AST);
			ASTPair __currentAST4 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,APO);
			_t = _t.getFirstChild();
			structure(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST4;
			_t = __t4;
			_t = _t.getNextSibling();
			apo_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = apo_AST;
		_retTree = _t;
	}
	
	public final void structure(AST _t) throws RecognitionException {
		
		MyNode structure_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode structure_AST = null;
		MyNode pr_AST = null;
		MyNode pr = null;
		MyNode gg_AST = null;
		MyNode gg = null;
		
		try {      // for error handling
			AST __t6 = _t;
			MyNode tmp3_AST = null;
			MyNode tmp3_AST_in = null;
			tmp3_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp3_AST_in = (MyNode)_t;
			ASTPair __currentAST6 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_IMPLIES);
			_t = _t.getFirstChild();
			pr = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			pr_AST = (MyNode)returnAST;
			gg = _t==ASTNULL ? null : (MyNode)_t;
			goal(_t);
			_t = _retTree;
			gg_AST = (MyNode)returnAST;
			structure_AST = (MyNode)currentAST.root;
			
				structure_AST = (MyNode)astFactory.make( (new ASTArray(3)).add(tmp3_AST).add(pr_AST).add(gg_AST));
			
			currentAST.root = structure_AST;
			currentAST.child = structure_AST!=null &&structure_AST.getFirstChild()!=null ?
				structure_AST.getFirstChild() : structure_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST6;
			_t = __t6;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = structure_AST;
		_retTree = _t;
	}
	
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		MyNode t51 = null;
		MyNode t51_AST = null;
		MyNode t52 = null;
		MyNode t52_AST = null;
		MyNode t53 = null;
		MyNode t53_AST = null;
		MyNode t54 = null;
		MyNode t54_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		MyNode t5 = null;
		MyNode t5_AST = null;
		MyNode t6 = null;
		MyNode t6_AST = null;
		MyNode t7 = null;
		MyNode t7_AST = null;
		MyNode t8 = null;
		MyNode t8_AST = null;
		MyNode t9 = null;
		MyNode t9_AST = null;
		MyNode t10 = null;
		MyNode t10_AST = null;
		MyNode t11 = null;
		MyNode t11_AST = null;
		MyNode t12 = null;
		MyNode t12_AST = null;
		MyNode t13 = null;
		MyNode t13_AST = null;
		MyNode t14 = null;
		MyNode t14_AST = null;
		MyNode t15 = null;
		MyNode t15_AST = null;
		MyNode t16 = null;
		MyNode t16_AST = null;
		MyNode t17 = null;
		MyNode t17_AST = null;
		MyNode t18 = null;
		MyNode t18_AST = null;
		MyNode t19 = null;
		MyNode t19_AST = null;
		MyNode t20 = null;
		MyNode t20_AST = null;
		MyNode t21 = null;
		MyNode t21_AST = null;
		MyNode t22 = null;
		MyNode t22_AST = null;
		MyNode t23 = null;
		MyNode t23_AST = null;
		MyNode t24 = null;
		MyNode t24_AST = null;
		MyNode t25 = null;
		MyNode t25_AST = null;
		MyNode t26 = null;
		MyNode t26_AST = null;
		MyNode t27 = null;
		MyNode t27_AST = null;
		MyNode t28 = null;
		MyNode t28_AST = null;
		MyNode t29 = null;
		MyNode t29_AST = null;
		MyNode t30 = null;
		MyNode t30_AST = null;
		MyNode t31 = null;
		MyNode t31_AST = null;
		MyNode t32 = null;
		MyNode t32_AST = null;
		MyNode t33 = null;
		MyNode t33_AST = null;
		MyNode t34 = null;
		MyNode t34_AST = null;
		MyNode t35 = null;
		MyNode t35_AST = null;
		MyNode t36 = null;
		MyNode t36_AST = null;
		MyNode t37 = null;
		MyNode t37_AST = null;
		MyNode t38 = null;
		MyNode t38_AST = null;
		MyNode t39 = null;
		MyNode t39_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BTRUE:
			{
				MyNode tmp4_AST = null;
				MyNode tmp4_AST_in = null;
				tmp4_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp4_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp4_AST);
				match(_t,BTRUE);
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOT:
			{
				AST __t23 = _t;
				t51 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t51_AST_in = null;
				t51_AST = (MyNode)astFactory.create(t51);
				astFactory.addASTChild(currentAST, t51_AST);
				ASTPair __currentAST23 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST23;
				_t = __t23;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RAN:
			{
				AST __t24 = _t;
				t52 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t52_AST_in = null;
				t52_AST = (MyNode)astFactory.create(t52);
				astFactory.addASTChild(currentAST, t52_AST);
				ASTPair __currentAST24 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST24;
				_t = __t24;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOM:
			{
				AST __t25 = _t;
				t53 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t53_AST_in = null;
				t53_AST = (MyNode)astFactory.create(t53);
				astFactory.addASTChild(currentAST, t53_AST);
				ASTPair __currentAST25 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST25;
				_t = __t25;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_AND:
			{
				AST __t26 = _t;
				t54 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t54_AST_in = null;
				t54_AST = (MyNode)astFactory.create(t54);
				astFactory.addASTChild(currentAST, t54_AST);
				ASTPair __currentAST26 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST26;
				_t = __t26;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_or:
			{
				AST __t27 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST27 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST27;
				_t = __t27;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IMPLIES:
			{
				AST __t28 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST28 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST28;
				_t = __t28;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUIV:
			{
				AST __t29 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				astFactory.addASTChild(currentAST, t4_AST);
				ASTPair __currentAST29 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST29;
				_t = __t29;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MULT:
			{
				AST __t30 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t5_AST_in = null;
				t5_AST = (MyNode)astFactory.create(t5);
				astFactory.addASTChild(currentAST, t5_AST);
				ASTPair __currentAST30 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST30;
				_t = __t30;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_POWER:
			{
				AST __t31 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t6_AST_in = null;
				t6_AST = (MyNode)astFactory.create(t6);
				astFactory.addASTChild(currentAST, t6_AST);
				ASTPair __currentAST31 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST31;
				_t = __t31;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DIV:
			{
				AST __t32 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t7_AST_in = null;
				t7_AST = (MyNode)astFactory.create(t7);
				astFactory.addASTChild(currentAST, t7_AST);
				ASTPair __currentAST32 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST32;
				_t = __t32;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_mod:
			{
				AST __t33 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t8_AST_in = null;
				t8_AST = (MyNode)astFactory.create(t8);
				astFactory.addASTChild(currentAST, t8_AST);
				ASTPair __currentAST33 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST33;
				_t = __t33;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_ADD:
			{
				AST __t34 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t9_AST_in = null;
				t9_AST = (MyNode)astFactory.create(t9);
				astFactory.addASTChild(currentAST, t9_AST);
				ASTPair __currentAST34 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST34;
				_t = __t34;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_MINUS:
			{
				AST __t35 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t10_AST_in = null;
				t10_AST = (MyNode)astFactory.create(t10);
				astFactory.addASTChild(currentAST, t10_AST);
				ASTPair __currentAST35 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST35;
				_t = __t35;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ADD:
			{
				AST __t36 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t11_AST_in = null;
				t11_AST = (MyNode)astFactory.create(t11);
				astFactory.addASTChild(currentAST, t11_AST);
				ASTPair __currentAST36 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST36;
				_t = __t36;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				AST __t37 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t12_AST_in = null;
				t12_AST = (MyNode)astFactory.create(t12);
				astFactory.addASTChild(currentAST, t12_AST);
				ASTPair __currentAST37 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST37;
				_t = __t37;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUAL:
			{
				AST __t38 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t13_AST_in = null;
				t13_AST = (MyNode)astFactory.create(t13);
				astFactory.addASTChild(currentAST, t13_AST);
				ASTPair __currentAST38 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST38;
				_t = __t38;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESS:
			{
				AST __t39 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t14_AST_in = null;
				t14_AST = (MyNode)astFactory.create(t14);
				astFactory.addASTChild(currentAST, t14_AST);
				ASTPair __currentAST39 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST39;
				_t = __t39;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATER:
			{
				AST __t40 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t15_AST_in = null;
				t15_AST = (MyNode)astFactory.create(t15);
				astFactory.addASTChild(currentAST, t15_AST);
				ASTPair __currentAST40 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST40;
				_t = __t40;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t41 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t16_AST_in = null;
				t16_AST = (MyNode)astFactory.create(t16);
				astFactory.addASTChild(currentAST, t16_AST);
				ASTPair __currentAST41 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST41;
				_t = __t41;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t42 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t17_AST_in = null;
				t17_AST = (MyNode)astFactory.create(t17);
				astFactory.addASTChild(currentAST, t17_AST);
				ASTPair __currentAST42 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST42;
				_t = __t42;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t43 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t18_AST_in = null;
				t18_AST = (MyNode)astFactory.create(t18);
				astFactory.addASTChild(currentAST, t18_AST);
				ASTPair __currentAST43 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST43;
				_t = __t43;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INSET:
			{
				AST __t44 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t19_AST_in = null;
				t19_AST = (MyNode)astFactory.create(t19);
				astFactory.addASTChild(currentAST, t19_AST);
				ASTPair __currentAST44 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST44;
				_t = __t44;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTINSET:
			{
				AST __t45 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t20_AST_in = null;
				t20_AST = (MyNode)astFactory.create(t20);
				astFactory.addASTChild(currentAST, t20_AST);
				ASTPair __currentAST45 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST45;
				_t = __t45;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUBSET:
			{
				AST __t46 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t21_AST_in = null;
				t21_AST = (MyNode)astFactory.create(t21);
				astFactory.addASTChild(currentAST, t21_AST);
				ASTPair __currentAST46 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST46;
				_t = __t46;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t47 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t22_AST_in = null;
				t22_AST = (MyNode)astFactory.create(t22);
				astFactory.addASTChild(currentAST, t22_AST);
				ASTPair __currentAST47 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST47;
				_t = __t47;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t48 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t23_AST_in = null;
				t23_AST = (MyNode)astFactory.create(t23);
				astFactory.addASTChild(currentAST, t23_AST);
				ASTPair __currentAST48 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST48;
				_t = __t48;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t49 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t24_AST_in = null;
				t24_AST = (MyNode)astFactory.create(t24);
				astFactory.addASTChild(currentAST, t24_AST);
				ASTPair __currentAST49 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST49;
				_t = __t49;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t50 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t25_AST_in = null;
				t25_AST = (MyNode)astFactory.create(t25);
				astFactory.addASTChild(currentAST, t25_AST);
				ASTPair __currentAST50 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST50;
				_t = __t50;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t51 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t26_AST_in = null;
				t26_AST = (MyNode)astFactory.create(t26);
				astFactory.addASTChild(currentAST, t26_AST);
				ASTPair __currentAST51 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST51;
				_t = __t51;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_APPSEQ:
			{
				AST __t52 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t27_AST_in = null;
				t27_AST = (MyNode)astFactory.create(t27);
				astFactory.addASTChild(currentAST, t27_AST);
				ASTPair __currentAST52 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST52;
				_t = __t52;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t53 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t28_AST_in = null;
				t28_AST = (MyNode)astFactory.create(t28);
				astFactory.addASTChild(currentAST, t28_AST);
				ASTPair __currentAST53 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST53;
				_t = __t53;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t54 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t29_AST_in = null;
				t29_AST = (MyNode)astFactory.create(t29);
				astFactory.addASTChild(currentAST, t29_AST);
				ASTPair __currentAST54 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST54;
				_t = __t54;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELATION:
			{
				AST __t55 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t30_AST_in = null;
				t30_AST = (MyNode)astFactory.create(t30);
				astFactory.addASTChild(currentAST, t30_AST);
				ASTPair __currentAST55 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST55;
				_t = __t55;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL:
			{
				AST __t56 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t31_AST_in = null;
				t31_AST = (MyNode)astFactory.create(t31);
				astFactory.addASTChild(currentAST, t31_AST);
				ASTPair __currentAST56 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST56;
				_t = __t56;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL:
			{
				AST __t57 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t32_AST_in = null;
				t32_AST = (MyNode)astFactory.create(t32);
				astFactory.addASTChild(currentAST, t32_AST);
				ASTPair __currentAST57 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST57;
				_t = __t57;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t58 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t33_AST_in = null;
				t33_AST = (MyNode)astFactory.create(t33);
				astFactory.addASTChild(currentAST, t33_AST);
				ASTPair __currentAST58 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST58;
				_t = __t58;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t59 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t34_AST_in = null;
				t34_AST = (MyNode)astFactory.create(t34);
				astFactory.addASTChild(currentAST, t34_AST);
				ASTPair __currentAST59 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST59;
				_t = __t59;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t60 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t35_AST_in = null;
				t35_AST = (MyNode)astFactory.create(t35);
				astFactory.addASTChild(currentAST, t35_AST);
				ASTPair __currentAST60 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST60;
				_t = __t60;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t61 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t36_AST_in = null;
				t36_AST = (MyNode)astFactory.create(t36);
				astFactory.addASTChild(currentAST, t36_AST);
				ASTPair __currentAST61 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST61;
				_t = __t61;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BIJECTION:
			{
				AST __t62 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t37_AST_in = null;
				t37_AST = (MyNode)astFactory.create(t37);
				astFactory.addASTChild(currentAST, t37_AST);
				ASTPair __currentAST62 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST62;
				_t = __t62;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t63 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t38_AST_in = null;
				t38_AST = (MyNode)astFactory.create(t38);
				astFactory.addASTChild(currentAST, t38_AST);
				ASTPair __currentAST63 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST63;
				_t = __t63;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t64 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t39_AST_in = null;
				t39_AST = (MyNode)astFactory.create(t39);
				astFactory.addASTChild(currentAST, t39_AST);
				ASTPair __currentAST64 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST64;
				_t = __t64;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t65 = _t;
				MyNode tmp5_AST = null;
				MyNode tmp5_AST_in = null;
				tmp5_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp5_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp5_AST);
				ASTPair __currentAST65 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST65;
				_t = __t65;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t66 = _t;
				MyNode tmp6_AST = null;
				MyNode tmp6_AST_in = null;
				tmp6_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp6_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp6_AST);
				ASTPair __currentAST66 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST66;
				_t = __t66;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t67 = _t;
				MyNode tmp7_AST = null;
				MyNode tmp7_AST_in = null;
				tmp7_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp7_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST67 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST67;
				_t = __t67;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t68 = _t;
				MyNode tmp8_AST = null;
				MyNode tmp8_AST_in = null;
				tmp8_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp8_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST68 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST68;
				_t = __t68;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELPROD:
			{
				AST __t69 = _t;
				MyNode tmp9_AST = null;
				MyNode tmp9_AST_in = null;
				tmp9_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp9_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp9_AST);
				ASTPair __currentAST69 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST69;
				_t = __t69;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_UNION:
			{
				AST __t70 = _t;
				MyNode tmp10_AST = null;
				MyNode tmp10_AST_in = null;
				tmp10_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp10_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp10_AST);
				ASTPair __currentAST70 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST70;
				_t = __t70;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INTER:
			{
				AST __t71 = _t;
				MyNode tmp11_AST = null;
				MyNode tmp11_AST_in = null;
				tmp11_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp11_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp11_AST);
				ASTPair __currentAST71 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST71;
				_t = __t71;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MAPLET:
			{
				AST __t72 = _t;
				MyNode tmp12_AST = null;
				MyNode tmp12_AST_in = null;
				tmp12_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp12_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp12_AST);
				ASTPair __currentAST72 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST72;
				_t = __t72;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LIST_VAR:
			{
				AST __t73 = _t;
				MyNode tmp13_AST = null;
				MyNode tmp13_AST_in = null;
				tmp13_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp13_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp13_AST);
				ASTPair __currentAST73 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST73;
				_t = __t73;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case LITERAL_STRING:
			{
				basic_sets(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate_AST = (MyNode)currentAST.root;
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
				astFactory.addASTChild(currentAST, returnAST);
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_bool:
			{
				AST __t74 = _t;
				MyNode tmp14_AST = null;
				MyNode tmp14_AST_in = null;
				tmp14_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp14_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp14_AST);
				ASTPair __currentAST74 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST74;
				_t = __t74;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t75 = _t;
				MyNode tmp15_AST = null;
				MyNode tmp15_AST_in = null;
				tmp15_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp15_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST75 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST75;
				_t = __t75;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGE:
			{
				AST __t76 = _t;
				MyNode tmp16_AST = null;
				MyNode tmp16_AST_in = null;
				tmp16_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp16_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST76 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST76;
				_t = __t76;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp17_AST = null;
				MyNode tmp17_AST_in = null;
				tmp17_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp17_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp17_AST);
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t77 = _t;
				MyNode tmp18_AST = null;
				MyNode tmp18_AST_in = null;
				tmp18_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp18_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp18_AST);
				ASTPair __currentAST77 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST77;
				_t = __t77;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SEQEMPTY:
			{
				MyNode tmp19_AST = null;
				MyNode tmp19_AST_in = null;
				tmp19_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp19_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp19_AST);
				match(_t,B_SEQEMPTY);
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				quantification(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate_AST = (MyNode)currentAST.root;
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
				astFactory.addASTChild(currentAST, returnAST);
				predicate_AST = (MyNode)currentAST.root;
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
		returnAST = predicate_AST;
		_retTree = _t;
	}
	
	public final void goal(AST _t) throws RecognitionException {
		
		MyNode goal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode goal_AST = null;
		MyNode pr_AST = null;
		MyNode pr = null;
		MyNode t = null;
		MyNode t_AST = null;
		
			MyNode res	= null     ;
			aaffecter 	= false	;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==SUBST_TO)) {
				AST __t8 = _t;
				MyNode tmp20_AST = null;
				MyNode tmp20_AST_in = null;
				tmp20_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp20_AST_in = (MyNode)_t;
				ASTPair __currentAST8 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SUBST_TO);
				_t = _t.getFirstChild();
				
					System.out.println("GSL-goal : on debute ");
				
				pr = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				pr_AST = (MyNode)returnAST;
				
					System.out.println("GSL-goal :pr = " + pr_AST.toStringList());
				
				res=gsl(_t,pr_AST);
				_t = _retTree;
				goal_AST = (MyNode)currentAST.root;
				
					goal_AST = (MyNode) astFactory.dupTree(res);
					System.out.println("GSL-goal : on finit avec " + res.toStringList());
				
				currentAST.root = goal_AST;
				currentAST.child = goal_AST!=null &&goal_AST.getFirstChild()!=null ?
					goal_AST.getFirstChild() : goal_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST8;
				_t = __t8;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==BTRUE)) {
				AST __t9 = _t;
				t = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t_AST_in = null;
				t_AST = (MyNode)astFactory.create(t);
				astFactory.addASTChild(currentAST, t_AST);
				ASTPair __currentAST9 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BTRUE);
				_t = _t.getFirstChild();
				goal_AST = (MyNode)currentAST.root;
				
				goal_AST = t_AST;
				
				currentAST.root = goal_AST;
				currentAST.child = goal_AST!=null &&goal_AST.getFirstChild()!=null ?
					goal_AST.getFirstChild() : goal_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST9;
				_t = __t9;
				_t = _t.getNextSibling();
				goal_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = goal_AST;
		_retTree = _t;
	}
	
	public final MyNode  gsl(AST _t,
		MyNode pr
	) throws RecognitionException {
		MyNode result;
		
		MyNode gsl_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode gsl_AST = null;
		MyNode p1_AST = null;
		MyNode p1 = null;
		MyNode ll_AST = null;
		MyNode ll = null;
		MyNode p2_AST = null;
		MyNode p2 = null;
		MyNode lf_AST = null;
		MyNode lf = null;
		MyNode p5_AST = null;
		MyNode p5 = null;
		
			MyNode res,r1,r2 ;
			result = new MyNode();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case GSL_SUCH:
			{
				AST __t11 = _t;
				MyNode tmp21_AST = null;
				MyNode tmp21_AST_in = null;
				tmp21_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp21_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST11 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,GSL_SUCH);
				_t = _t.getFirstChild();
				p1 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				res=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
					result  = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add(p1_AST).add(res));
					System.out.println("gsl1 -- on a :" + result.toStringList());	
				
				currentAST = __currentAST11;
				_t = __t11;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case GSL_FOR_SUCH:
			{
				AST __t12 = _t;
				MyNode tmp22_AST = null;
				MyNode tmp22_AST_in = null;
				tmp22_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp22_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST12 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,GSL_FOR_SUCH);
				_t = _t.getFirstChild();
				ll = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				ll_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				res=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
					result  = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_FORALL,"!")).add(ll_AST).add(res));
					System.out.println("gsl2 -- on a :" + result.toStringList());
				
				currentAST = __currentAST12;
				_t = __t12;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case GSL_GUARD:
			{
				AST __t13 = _t;
				MyNode tmp23_AST = null;
				MyNode tmp23_AST_in = null;
				tmp23_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp23_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
				ASTPair __currentAST13 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,GSL_GUARD);
				_t = _t.getFirstChild();
				p2 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p2_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				res=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
					result  = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_IMPLIES,"=>")).add(p2_AST).add(res));
					System.out.println("gsl3 -- on a :" + result.toStringList());
				
				currentAST = __currentAST13;
				_t = __t13;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_skip:
			{
				AST __t14 = _t;
				MyNode tmp24_AST = null;
				MyNode tmp24_AST_in = null;
				tmp24_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp24_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST14 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				
					result	= (MyNode) astFactory.dupTree(pr);
					System.out.println("gsl4 -- on a :" + result.toStringList());
				
				currentAST = __currentAST14;
				_t = __t14;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SIMPLESUBST:
			{
				AST __t15 = _t;
				MyNode tmp25_AST = null;
				MyNode tmp25_AST_in = null;
				tmp25_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp25_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST15 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				lf = _t==ASTNULL ? null : (MyNode)_t;
				afc(_t);
				_t = _retTree;
				lf_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				p5 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p5_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				
					aaffecter   = true;
					System.out.println("debut gsl ':=' -- on a :" + pr.toStringList());
					name        = (MyNode) astFactory.dupTree(lf_AST); 
				transfert   = (MyNode) astFactory.dupTree(p5_AST); 
				MyNode tmp  = predicate_normalize(pr);
				result      = (MyNode) astFactory.dupTree(tmp);
				
				aaffecter   = false;
					System.out.println("fin gsl ':=' -- on a :" + tmp.toStringList());
				
				currentAST = __currentAST15;
				_t = __t15;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case SEQUENTIAL:
			{
				AST __t16 = _t;
				MyNode tmp26_AST = null;
				MyNode tmp26_AST_in = null;
				tmp26_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp26_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST16 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				r1=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				res=gsl(_t,r1);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
					result = (MyNode) astFactory.dupTree(pr);
					System.out.println("gsl ';' -- on a :" + result.toStringList());
				
				currentAST = __currentAST16;
				_t = __t16;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
				break;
			}
			case PARALLEL:
			{
				AST __t17 = _t;
				MyNode tmp27_AST = null;
				MyNode tmp27_AST_in = null;
				tmp27_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp27_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST17 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				r1=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				r2=gsl(_t,pr);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
					result = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add(r1).add(r2));
					System.out.println("gsl '||' -- on a :" + result.toStringList());
				
				currentAST = __currentAST17;
				_t = __t17;
				_t = _t.getNextSibling();
				gsl_AST = (MyNode)currentAST.root;
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
		returnAST = gsl_AST;
		_retTree = _t;
		return result;
	}
	
	public final void listTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listTypedIdentifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t106 = _t;
				MyNode tmp28_AST = null;
				MyNode tmp28_AST_in = null;
				tmp28_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp28_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				ASTPair __currentAST106 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				
				
				
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST106;
				_t = __t106;
				_t = _t.getNextSibling();
				listTypedIdentifier_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_INSET)) {
				typedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listTypedIdentifier_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = listTypedIdentifier_AST;
		_retTree = _t;
	}
	
	public final void afc(AST _t) throws RecognitionException {
		
		MyNode afc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode afc_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t19 = _t;
				MyNode tmp29_AST = null;
				MyNode tmp29_AST_in = null;
				tmp29_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp29_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST19 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST19;
				_t = __t19;
				_t = _t.getNextSibling();
				afc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t20 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST20 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				afc(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST20;
				_t = __t20;
				_t = _t.getNextSibling();
				afc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				afc_AST = (MyNode)currentAST.root;
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
		returnAST = afc_AST;
		_retTree = _t;
	}
	
	public final void listPredicate(AST _t) throws RecognitionException {
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listPredicate_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t121 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST121 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST121;
				_t = __t121;
				_t = _t.getNextSibling();
				listPredicate_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listPredicate_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = listPredicate_AST;
		_retTree = _t;
	}
	
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		MyNode n1 = null;
		MyNode n1_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		MyNode n2_AST = null;
		MyNode n2 = null;
		MyNode n3_AST = null;
		MyNode n3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				n1 = (MyNode)_t;
				MyNode n1_AST_in = null;
				n1_AST = (MyNode)astFactory.create(n1);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				nameRenamed_AST = (MyNode)currentAST.root;
				
				if (aaffecter == true)
				{
				if (n1.toString().compareTo(name.toString())==0)
				{
				nameRenamed_AST = (MyNode) astFactory.dupTree(transfert);
				}
				else
				{
				nameRenamed_AST =(MyNode) astFactory.dupTree(n1_AST);
				}
				}
				else
				{
				nameRenamed_AST =n1_AST;
				}
				
				currentAST.root = nameRenamed_AST;
				currentAST.child = nameRenamed_AST!=null &&nameRenamed_AST.getFirstChild()!=null ?
					nameRenamed_AST.getFirstChild() : nameRenamed_AST;
				currentAST.advanceChildToEnd();
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t117 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				ASTPair __currentAST117 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				n2 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				n2_AST = (MyNode)returnAST;
				n3 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				n3_AST = (MyNode)returnAST;
				nameRenamed_AST = (MyNode)currentAST.root;
				
				if (aaffecter== true)
				{
				if (tt.toString().compareTo(name.toString())==0)
				{
				nameRenamed_AST = (MyNode) astFactory.dupTree(transfert);
				}
				else
				{
				nameRenamed_AST =(MyNode)astFactory.make( (new ASTArray(3)).add(tt_AST).add(n2_AST).add(n3_AST));
				}
				}
				else
				{
				nameRenamed_AST =(MyNode)astFactory.make( (new ASTArray(3)).add(tt_AST).add(n2_AST).add(n3_AST));
				}
				
				currentAST.root = nameRenamed_AST;
				currentAST.child = nameRenamed_AST!=null &&nameRenamed_AST.getFirstChild()!=null ?
					nameRenamed_AST.getFirstChild() : nameRenamed_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST117;
				_t = __t117;
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
		returnAST = nameRenamed_AST;
		_retTree = _t;
	}
	
/** 
 *  PREDICATE 
 **/
	public final MyNode  predicate_normalize(AST _t) throws RecognitionException {
		MyNode pr;
		
		MyNode predicate_normalize_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_normalize_AST = null;
		MyNode pp_AST = null;
		MyNode pp = null;
		
		pr = null;
		
		
		try {      // for error handling
			pp = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			pp_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			
			pr = (MyNode) astFactory.dupTree(pp_AST);
			
			predicate_normalize_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = predicate_normalize_AST;
		_retTree = _t;
		return pr;
	}
	
	public final void basic_sets(AST _t) throws RecognitionException {
		
		MyNode basic_sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode basic_sets_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_INT:
			{
				MyNode tmp30_AST = null;
				MyNode tmp30_AST_in = null;
				tmp30_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp30_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp31_AST = null;
				MyNode tmp31_AST_in = null;
				tmp31_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp31_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp31_AST);
				match(_t,87);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp32_AST = null;
				MyNode tmp32_AST_in = null;
				tmp32_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp32_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp33_AST = null;
				MyNode tmp33_AST_in = null;
				tmp33_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp33_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				match(_t,89);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp34_AST = null;
				MyNode tmp34_AST_in = null;
				tmp34_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp34_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp35_AST = null;
				MyNode tmp35_AST_in = null;
				tmp35_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp35_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp36_AST = null;
				MyNode tmp36_AST_in = null;
				tmp36_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp36_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				match(_t,92);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp37_AST = null;
				MyNode tmp37_AST_in = null;
				tmp37_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp37_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp38_AST = null;
				MyNode tmp38_AST_in = null;
				tmp38_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp38_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				match(_t,94);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp39_AST = null;
				MyNode tmp39_AST_in = null;
				tmp39_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp39_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
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
		returnAST = basic_sets_AST;
		_retTree = _t;
	}
	
	public final void cbasic_value(AST _t) throws RecognitionException {
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode cbasic_value_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		MyNode p1_AST = null;
		MyNode p1 = null;
		MyNode p2_AST = null;
		MyNode p2 = null;
		MyNode t6 = null;
		MyNode t6_AST = null;
		MyNode t7 = null;
		MyNode t7_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				MyNode tmp40_AST = null;
				MyNode tmp40_AST_in = null;
				tmp40_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp40_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp41_AST = null;
				MyNode tmp41_AST_in = null;
				tmp41_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp41_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TILDE:
			{
				AST __t82 = _t;
				MyNode tmp42_AST = null;
				MyNode tmp42_AST_in = null;
				tmp42_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp42_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				ASTPair __currentAST82 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST82;
				_t = __t82;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			{
				nameRenamedDecorated(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LPAREN:
			{
				AST __t83 = _t;
				MyNode tmp43_AST = null;
				MyNode tmp43_AST_in = null;
				tmp43_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp43_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST83 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_New_Predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST83;
				_t = __t83;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case PARENT:
			{
				AST __t84 = _t;
				MyNode tmp44_AST = null;
				MyNode tmp44_AST_in = null;
				tmp44_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp44_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST84 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST84;
				_t = __t84;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t85 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				ASTPair __currentAST85 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				p1 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p1_AST = (MyNode)returnAST;
				p2 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p2_AST = (MyNode)returnAST;
				cbasic_value_AST = (MyNode)currentAST.root;
				
				if (aaffecter)
				{
				if (t4.toString().compareTo(name.toString())==0)
				{
				cbasic_value_AST = (MyNode) astFactory.dupTree(transfert);
				}
				else
				{
				cbasic_value_AST =(MyNode)astFactory.make( (new ASTArray(3)).add(t4_AST).add(p1_AST).add(p2_AST));
				}
				}
				else
				{
				cbasic_value_AST =(MyNode)astFactory.make( (new ASTArray(3)).add(t4_AST).add(p1_AST).add(p2_AST));
				}
				
				currentAST.root = cbasic_value_AST;
				currentAST.child = cbasic_value_AST!=null &&cbasic_value_AST.getFirstChild()!=null ?
					cbasic_value_AST.getFirstChild() : cbasic_value_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST85;
				_t = __t85;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t86 = _t;
				MyNode tmp45_AST = null;
				MyNode tmp45_AST_in = null;
				tmp45_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp45_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST86 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST86;
				_t = __t86;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				t6 = (MyNode)_t;
				MyNode t6_AST_in = null;
				t6_AST = (MyNode)astFactory.create(t6);
				astFactory.addASTChild(currentAST, t6_AST);
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				t7 = (MyNode)_t;
				MyNode t7_AST_in = null;
				t7_AST = (MyNode)astFactory.create(t7);
				astFactory.addASTChild(currentAST, t7_AST);
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
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
		returnAST = cbasic_value_AST;
		_retTree = _t;
	}
	
	public final void cvalue_set(AST _t) throws RecognitionException {
		
		MyNode cvalue_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode cvalue_set_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t79 = _t;
				MyNode tmp46_AST = null;
				MyNode tmp46_AST_in = null;
				tmp46_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp46_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST79 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST79;
				_t = __t79;
				_t = _t.getNextSibling();
				cvalue_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t80 = _t;
				MyNode tmp47_AST = null;
				MyNode tmp47_AST_in = null;
				tmp47_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp47_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST80 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST80;
				_t = __t80;
				_t = _t.getNextSibling();
				cvalue_set_AST = (MyNode)currentAST.root;
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
			case 87:
			case LITERAL_INTEGER:
			case 89:
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
				astFactory.addASTChild(currentAST, returnAST);
				cvalue_set_AST = (MyNode)currentAST.root;
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
		returnAST = cvalue_set_AST;
		_retTree = _t;
	}
	
	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode is_record_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t01 = null;
		MyNode t01_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t110 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST110 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST110;
				_t = __t110;
				_t = _t.getNextSibling();
				is_record_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t111 = _t;
				t01 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t01_AST_in = null;
				t01_AST = (MyNode)astFactory.create(t01);
				astFactory.addASTChild(currentAST, t01_AST);
				ASTPair __currentAST111 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST111;
				_t = __t111;
				_t = _t.getNextSibling();
				is_record_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = is_record_AST;
		_retTree = _t;
	}
	
	public final void quantification(AST _t) throws RecognitionException {
		
		MyNode quantification_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode quantification_AST = null;
		MyNode t70 = null;
		MyNode t70_AST = null;
		MyNode t71 = null;
		MyNode t71_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t93 = _t;
				t70 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t70_AST_in = null;
				t70_AST = (MyNode)astFactory.create(t70);
				astFactory.addASTChild(currentAST, t70_AST);
				ASTPair __currentAST93 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST93;
				_t = __t93;
				_t = _t.getNextSibling();
				quantification_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t94 = _t;
				t71 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t71_AST_in = null;
				t71_AST = (MyNode)astFactory.create(t71);
				astFactory.addASTChild(currentAST, t71_AST);
				ASTPair __currentAST94 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST94;
				_t = __t94;
				_t = _t.getNextSibling();
				quantification_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = quantification_AST;
		_retTree = _t;
	}
	
	public final void q_lambda(AST _t) throws RecognitionException {
		
		MyNode q_lambda_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_lambda_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		MyNode t5 = null;
		MyNode t5_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t96 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST96 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST96;
				_t = __t96;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PI:
			{
				AST __t97 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST97 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST97;
				_t = __t97;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t98 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST98 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST98;
				_t = __t98;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_UNION:
			{
				AST __t99 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				astFactory.addASTChild(currentAST, t4_AST);
				ASTPair __currentAST99 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST99;
				_t = __t99;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTER:
			{
				AST __t100 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t5_AST_in = null;
				t5_AST = (MyNode)astFactory.create(t5);
				astFactory.addASTChild(currentAST, t5_AST);
				ASTPair __currentAST100 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST100;
				_t = __t100;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
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
		returnAST = q_lambda_AST;
		_retTree = _t;
	}
	
	public final void list_var(AST _t) throws RecognitionException {
		
		MyNode list_var_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_var_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t104 = _t;
				MyNode tmp48_AST = null;
				MyNode tmp48_AST_in = null;
				tmp48_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp48_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				ASTPair __currentAST104 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST104;
				_t = __t104;
				_t = _t.getNextSibling();
				list_var_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_var_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_var_AST;
		_retTree = _t;
	}
	
	public final void nameRenamedDecorated(AST _t) throws RecognitionException {
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamedDecorated_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode n1_AST = null;
		MyNode n1 = null;
		MyNode t2_AST = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t119 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				ASTPair __currentAST119 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				n1 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				n1_AST = (MyNode)returnAST;
				nameRenamedDecorated_AST = (MyNode)currentAST.root;
				
				if (aaffecter== true)
				{ 
				if (t1.toString().compareTo(name.toString())==0)
				{
				nameRenamedDecorated_AST = (MyNode) astFactory.dupTree(transfert);
				}
				else
				{
				nameRenamedDecorated_AST =(MyNode)astFactory.make( (new ASTArray(2)).add(t1_AST).add(n1_AST));
				}
				}
				else
				{
				nameRenamedDecorated_AST =(MyNode)astFactory.make( (new ASTArray(2)).add(t1_AST).add(n1_AST));
				}
				
				currentAST.root = nameRenamedDecorated_AST;
				currentAST.child = nameRenamedDecorated_AST!=null &&nameRenamedDecorated_AST.getFirstChild()!=null ?
					nameRenamedDecorated_AST.getFirstChild() : nameRenamedDecorated_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST119;
				_t = __t119;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				t2 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				t2_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				nameRenamedDecorated_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = nameRenamedDecorated_AST;
		_retTree = _t;
	}
	
	public final void list_New_Predicate(AST _t) throws RecognitionException {
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_New_Predicate_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t123 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST123 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				new_predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST123;
				_t = __t123;
				_t = _t.getNextSibling();
				list_New_Predicate_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				new_predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_New_Predicate_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_New_Predicate_AST;
		_retTree = _t;
	}
	
	public final void pred_func_composition(AST _t) throws RecognitionException {
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pred_func_composition_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t88 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST88 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST88;
				_t = __t88;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t89 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST89 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST89;
				_t = __t89;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t90 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST90 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST90;
				_t = __t90;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
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
			case 87:
			case LITERAL_INTEGER:
			case 89:
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
				astFactory.addASTChild(currentAST, returnAST);
				pred_func_composition_AST = (MyNode)currentAST.root;
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
		returnAST = pred_func_composition_AST;
		_retTree = _t;
	}
	
	public final void q_operande(AST _t) throws RecognitionException {
		
		MyNode q_operande_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_operande_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			AST __t102 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			MyNode t1_AST_in = null;
			t1_AST = (MyNode)astFactory.create(t1);
			astFactory.addASTChild(currentAST, t1_AST);
			ASTPair __currentAST102 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST102;
			_t = __t102;
			_t = _t.getNextSibling();
			q_operande_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = q_operande_AST;
		_retTree = _t;
	}
	
	public final void list_identifier(AST _t) throws RecognitionException {
		
		MyNode list_identifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_identifier_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		MyNode n1 = null;
		MyNode n1_AST = null;
		MyNode n2 = null;
		MyNode n2_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t128 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST128 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				n1 = (MyNode)_t;
				MyNode n1_AST_in = null;
				n1_AST = (MyNode)astFactory.create(n1);
				astFactory.addASTChild(currentAST, n1_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				currentAST = __currentAST128;
				_t = __t128;
				_t = _t.getNextSibling();
				list_identifier_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				n2 = (MyNode)_t;
				MyNode n2_AST_in = null;
				n2_AST = (MyNode)astFactory.create(n2);
				astFactory.addASTChild(currentAST, n2_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				list_identifier_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_identifier_AST;
		_retTree = _t;
	}
	
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode typedIdentifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t108 = _t;
				MyNode tmp49_AST = null;
				MyNode tmp49_AST_in = null;
				tmp49_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp49_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
				ASTPair __currentAST108 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST108;
				_t = __t108;
				_t = _t.getNextSibling();
				typedIdentifier_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				typedIdentifier_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = typedIdentifier_AST;
		_retTree = _t;
	}
	
	public final void listrecord(AST _t) throws RecognitionException {
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listrecord_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t113 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST113 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_record(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST113;
				_t = __t113;
				_t = _t.getNextSibling();
				listrecord_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				a_record(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listrecord_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = listrecord_AST;
		_retTree = _t;
	}
	
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_record_AST = null;
		MyNode name = null;
		MyNode name_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t115 = _t;
				MyNode tmp50_AST = null;
				MyNode tmp50_AST_in = null;
				tmp50_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp50_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp50_AST);
				ASTPair __currentAST115 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				MyNode name_AST_in = null;
				name_AST = (MyNode)astFactory.create(name);
				astFactory.addASTChild(currentAST, name_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST115;
				_t = __t115;
				_t = _t.getNextSibling();
				a_record_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_record_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = a_record_AST;
		_retTree = _t;
	}
	
	public final void new_predicate(AST _t) throws RecognitionException {
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_predicate_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t125 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST125 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST125;
				_t = __t125;
				_t = _t.getNextSibling();
				new_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t126 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST126 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST126;
				_t = __t126;
				_t = _t.getNextSibling();
				new_predicate_AST = (MyNode)currentAST.root;
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
			case 87:
			case LITERAL_INTEGER:
			case 89:
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
				astFactory.addASTChild(currentAST, returnAST);
				new_predicate_AST = (MyNode)currentAST.root;
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
		returnAST = new_predicate_AST;
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
		long[] data = { -3530822108395339808L, 215083372240927L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -3530822108395339808L, 215083372247071L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -3530822108395339808L, 215220811194399L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	}
	
