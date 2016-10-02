// $ANTLR 2.7.6 (2005-12-22): "Substitution.g" -> "Substitution.java"$

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
	import antlr.ASTFactory;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*;


/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/
public class Substitution extends antlr.TreeParser       implements PO_GSLTokenTypes
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
		return (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_SUCH,"|")).add(pred).add(subst));
	}



public Substitution() {
	tokenNames = _tokenNames;
}

	public final MyNode  buildAndEvalGoal(AST _t,
		MyNode pp, MyNode ii
	) throws RecognitionException {
		MyNode res;
		
		MyNode buildAndEvalGoal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode buildAndEvalGoal_AST = null;
		
		res = null;
		
		
		try {      // for error handling
			
			System.out.println("BEGIN : buildAndEZvalGoal");
			
			MyNode newgoal = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(APO,"APO")).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(SUBST_TO,"[[")).add(pp).add(ii))));
			res = agoal(newgoal);
			
			System.out.println("END : buildAndEZvalGoal");
			
			buildAndEvalGoal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = buildAndEvalGoal_AST;
		_retTree = _t;
		return res;
	}
	
	public final MyNode  agoal(AST _t) throws RecognitionException {
		MyNode res;
		
		MyNode agoal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode agoal_AST = null;
		MyNode ii_AST = null;
		MyNode ii = null;
		MyNode pp_AST = null;
		MyNode pp = null;
		
		res = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==SUBST_TO)) {
				AST __t3 = _t;
				MyNode tmp1_AST = null;
				MyNode tmp1_AST_in = null;
				tmp1_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp1_AST_in = (MyNode)_t;
				ASTPair __currentAST3 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SUBST_TO);
				_t = _t.getFirstChild();
				ii = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				ii_AST = (MyNode)returnAST;
				pp = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				pp_AST = (MyNode)returnAST;
				
				if (ii == null)                       // cas du skip qui produit null
						res = pp_AST;
					else	                              // cas general
						res = (MyNode)astFactory.make( (new ASTArray(3)).add(tmp1_AST).add(pp_AST).add(ii_AST));
				
				currentAST = __currentAST3;
				_t = __t3;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==BTRUE)) {
				AST __t4 = _t;
				MyNode tmp2_AST = null;
				MyNode tmp2_AST_in = null;
				tmp2_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp2_AST_in = (MyNode)_t;
				ASTPair __currentAST4 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BTRUE);
				_t = _t.getFirstChild();
				agoal_AST = (MyNode)currentAST.root;
				
				res = agoal_AST;
				
				currentAST = __currentAST4;
				_t = __t4;
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
		returnAST = agoal_AST;
		_retTree = _t;
		return res;
	}
	
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode instruction_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode i4_AST = null;
		MyNode i4 = null;
		MyNode p5_AST = null;
		MyNode p5 = null;
		MyNode i5_AST = null;
		MyNode i5 = null;
		MyNode p6_AST = null;
		MyNode p6 = null;
		MyNode i6_AST = null;
		MyNode i6 = null;
		MyNode tp7_AST = null;
		MyNode tp7 = null;
		MyNode tb7_AST = null;
		MyNode tb7 = null;
		MyNode tb8_AST = null;
		MyNode tb8 = null;
		MyNode tb9_AST = null;
		MyNode tb9 = null;
		MyNode t8 = null;
		MyNode t8_AST = null;
		MyNode l8_AST = null;
		MyNode l8 = null;
		MyNode t9 = null;
		MyNode t9_AST = null;
		MyNode tt9_AST = null;
		MyNode tt9 = null;
		MyNode t10 = null;
		MyNode t10_AST = null;
		MyNode l10_AST = null;
		MyNode l10 = null;
		MyNode p10_AST = null;
		MyNode p10 = null;
		MyNode i10_AST = null;
		MyNode i10 = null;
		MyNode t11 = null;
		MyNode t11_AST = null;
		MyNode l11_AST = null;
		MyNode l11 = null;
		MyNode p11_AST = null;
		MyNode p11 = null;
		MyNode i11_AST = null;
		MyNode i11 = null;
		MyNode t12 = null;
		MyNode t12_AST = null;
		MyNode tt12_AST = null;
		MyNode tt12 = null;
		MyNode t13 = null;
		MyNode t13_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		MyNode l14_AST = null;
		MyNode l14 = null;
		MyNode i14_AST = null;
		MyNode i14 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t15 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST15 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST15;
				_t = __t15;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case SEQUENTIAL:
			{
				AST __t16 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST16 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST16;
				_t = __t16;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_skip:
			{
				AST __t17 = _t;
				MyNode tmp3_AST_in = null;
				ASTPair __currentAST17 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = null;
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST17;
				_t = __t17;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t18 = _t;
				MyNode tmp4_AST_in = null;
				ASTPair __currentAST18 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				i4 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i4_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = i4_AST;
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST18;
				_t = __t18;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t19 = _t;
				MyNode tmp5_AST_in = null;
				ASTPair __currentAST19 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				p5 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p5_AST = (MyNode)returnAST;
				i5 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i5_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_SUCH,"|")).add(p5_AST).add(i5_AST));
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST19;
				_t = __t19;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t20 = _t;
				MyNode tmp6_AST_in = null;
				ASTPair __currentAST20 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				p6 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p6_AST = (MyNode)returnAST;
				i6 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i6_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
				// A VERIFIER dans le BBOOK
					instruction_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add(p6_AST).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p6_AST).add(i6_AST))));
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST20;
				_t = __t20;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_IF:
			{
				AST __t21 = _t;
				MyNode tmp7_AST = null;
				MyNode tmp7_AST_in = null;
				tmp7_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp7_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST21 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				tp7 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				tp7_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				tb7 = _t==ASTNULL ? null : (MyNode)_t;
				branche_then(_t,tp7);
				_t = _retTree;
				tb7_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop23:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						tb8 = _t==ASTNULL ? null : (MyNode)_t;
						branche_elsif(_t,tp7);
						_t = _retTree;
						tb8_AST = (MyNode)returnAST;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop23;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					tb9 = _t==ASTNULL ? null : (MyNode)_t;
					branche_else(_t,tp7);
					_t = _retTree;
					tb9_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
				// A VERIFIER dans le BBOOK
				//	#instruction = #([B_AND,"&"], #p6, #([GSL_GUARD,"==>"],#p6,#i6));
				
				currentAST = __currentAST21;
				_t = __t21;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t25 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t8_AST_in = null;
				t8_AST = (MyNode)astFactory.create(t8);
				ASTPair __currentAST25 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				l8 = _t==ASTNULL ? null : (MyNode)_t;
				list_or(_t);
				_t = _retTree;
				l8_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = l8_AST;
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST25;
				_t = __t25;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CASE:
			{
				AST __t26 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t9_AST_in = null;
				t9_AST = (MyNode)astFactory.create(t9);
				astFactory.addASTChild(currentAST, t9_AST);
				ASTPair __currentAST26 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				tt9 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				tt9_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				branche_either(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop28:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop28;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t,tt9);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				currentAST = __currentAST26;
				_t = __t26;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ANY:
			{
				AST __t30 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t10_AST_in = null;
				t10_AST = (MyNode)astFactory.create(t10);
				ASTPair __currentAST30 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				l10 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				l10_AST = (MyNode)returnAST;
				p10 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p10_AST = (MyNode)returnAST;
				i10 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i10_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_FOR_SUCH,"@")).add(l10_AST).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p10_AST).add(i10_AST))));
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST30;
				_t = __t30;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_LET:
			{
				AST __t31 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t11_AST_in = null;
				t11_AST = (MyNode)astFactory.create(t11);
				ASTPair __currentAST31 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				l11 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				l11_AST = (MyNode)returnAST;
				p11 = _t==ASTNULL ? null : (MyNode)_t;
				list_equal(_t);
				_t = _retTree;
				p11_AST = (MyNode)returnAST;
				i11 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i11_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_FOR_SUCH,"@")).add(l11_AST).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p11_AST).add(i11_AST))));
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST31;
				_t = __t31;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t32 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t12_AST_in = null;
				t12_AST = (MyNode)astFactory.create(t12);
				astFactory.addASTChild(currentAST, t12_AST);
				ASTPair __currentAST32 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				tt12 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				tt12_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				branche_then(_t,tt12);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop34:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop34;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t,tt12);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				currentAST = __currentAST32;
				_t = __t32;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t36 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t13_AST_in = null;
				t13_AST = (MyNode)astFactory.create(t13);
				astFactory.addASTChild(currentAST, t13_AST);
				ASTPair __currentAST36 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST36;
				_t = __t36;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VAR:
			{
				AST __t37 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				ASTPair __currentAST37 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				l14 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				l14_AST = (MyNode)returnAST;
				i14 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i14_AST = (MyNode)returnAST;
				instruction_AST = (MyNode)currentAST.root;
				
					instruction_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_FOR_SUCH,"@")).add(l14_AST).add(i14_AST));
				
				currentAST.root = instruction_AST;
				currentAST.child = instruction_AST!=null &&instruction_AST.getFirstChild()!=null ?
					instruction_AST.getFirstChild() : instruction_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST37;
				_t = __t37;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_QUOTEIDENT:
			case B_OUT:
			case B_BECOME_ELEM:
			case B_SIMPLESUBST:
			case INSET:
			case FUNC_CALL_PARAM:
			{
				simple_affect(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction_AST = (MyNode)currentAST.root;
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
		returnAST = instruction_AST;
		_retTree = _t;
	}
	
/** 
 *  PREDICATE 
 **/
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		MyNode t = null;
		MyNode t_AST = null;
		MyNode a1 = null;
		MyNode a1_AST = null;
		MyNode a2 = null;
		MyNode a2_AST = null;
		MyNode a3 = null;
		MyNode a3_AST = null;
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
		MyNode t40 = null;
		MyNode t40_AST = null;
		MyNode t41 = null;
		MyNode t41_AST = null;
		MyNode t42 = null;
		MyNode t42_AST = null;
		MyNode t43 = null;
		MyNode t43_AST = null;
		MyNode t44 = null;
		MyNode t44_AST = null;
		MyNode t45 = null;
		MyNode t45_AST = null;
		MyNode t46 = null;
		MyNode t46_AST = null;
		MyNode t48 = null;
		MyNode t48_AST = null;
		MyNode t49 = null;
		MyNode t49_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BTRUE:
			{
				AST __t90 = _t;
				t = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t_AST_in = null;
				t_AST = (MyNode)astFactory.create(t);
				ASTPair __currentAST90 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BTRUE);
				_t = _t.getFirstChild();
				predicate_AST = (MyNode)currentAST.root;
				
					predicate_AST = t_AST;
				
				currentAST.root = predicate_AST;
				currentAST.child = predicate_AST!=null &&predicate_AST.getFirstChild()!=null ?
					predicate_AST.getFirstChild() : predicate_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST90;
				_t = __t90;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t91 = _t;
				a1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode a1_AST_in = null;
				a1_AST = (MyNode)astFactory.create(a1);
				astFactory.addASTChild(currentAST, a1_AST);
				ASTPair __currentAST91 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST91;
				_t = __t91;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RAN:
			{
				AST __t92 = _t;
				a2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode a2_AST_in = null;
				a2_AST = (MyNode)astFactory.create(a2);
				astFactory.addASTChild(currentAST, a2_AST);
				ASTPair __currentAST92 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST92;
				_t = __t92;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOM:
			{
				AST __t93 = _t;
				a3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode a3_AST_in = null;
				a3_AST = (MyNode)astFactory.create(a3);
				astFactory.addASTChild(currentAST, a3_AST);
				ASTPair __currentAST93 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST93;
				_t = __t93;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_AND:
			{
				AST __t94 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST94 = currentAST.copy();
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
				currentAST = __currentAST94;
				_t = __t94;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_or:
			{
				AST __t95 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST95 = currentAST.copy();
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
				currentAST = __currentAST95;
				_t = __t95;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IMPLIES:
			{
				AST __t96 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST96 = currentAST.copy();
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
				currentAST = __currentAST96;
				_t = __t96;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUIV:
			{
				AST __t97 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				astFactory.addASTChild(currentAST, t4_AST);
				ASTPair __currentAST97 = currentAST.copy();
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
				currentAST = __currentAST97;
				_t = __t97;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MULT:
			{
				AST __t98 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t5_AST_in = null;
				t5_AST = (MyNode)astFactory.create(t5);
				astFactory.addASTChild(currentAST, t5_AST);
				ASTPair __currentAST98 = currentAST.copy();
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
				currentAST = __currentAST98;
				_t = __t98;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_POWER:
			{
				AST __t99 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t6_AST_in = null;
				t6_AST = (MyNode)astFactory.create(t6);
				astFactory.addASTChild(currentAST, t6_AST);
				ASTPair __currentAST99 = currentAST.copy();
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
				currentAST = __currentAST99;
				_t = __t99;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DIV:
			{
				AST __t100 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t7_AST_in = null;
				t7_AST = (MyNode)astFactory.create(t7);
				astFactory.addASTChild(currentAST, t7_AST);
				ASTPair __currentAST100 = currentAST.copy();
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
				currentAST = __currentAST100;
				_t = __t100;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_mod:
			{
				AST __t101 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t8_AST_in = null;
				t8_AST = (MyNode)astFactory.create(t8);
				astFactory.addASTChild(currentAST, t8_AST);
				ASTPair __currentAST101 = currentAST.copy();
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
				currentAST = __currentAST101;
				_t = __t101;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_ADD:
			{
				AST __t102 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t9_AST_in = null;
				t9_AST = (MyNode)astFactory.create(t9);
				astFactory.addASTChild(currentAST, t9_AST);
				ASTPair __currentAST102 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST102;
				_t = __t102;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_MINUS:
			{
				AST __t103 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t10_AST_in = null;
				t10_AST = (MyNode)astFactory.create(t10);
				astFactory.addASTChild(currentAST, t10_AST);
				ASTPair __currentAST103 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST103;
				_t = __t103;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ADD:
			{
				AST __t104 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t11_AST_in = null;
				t11_AST = (MyNode)astFactory.create(t11);
				astFactory.addASTChild(currentAST, t11_AST);
				ASTPair __currentAST104 = currentAST.copy();
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
				currentAST = __currentAST104;
				_t = __t104;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				AST __t105 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t12_AST_in = null;
				t12_AST = (MyNode)astFactory.create(t12);
				astFactory.addASTChild(currentAST, t12_AST);
				ASTPair __currentAST105 = currentAST.copy();
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
				currentAST = __currentAST105;
				_t = __t105;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUAL:
			{
				AST __t106 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t13_AST_in = null;
				t13_AST = (MyNode)astFactory.create(t13);
				astFactory.addASTChild(currentAST, t13_AST);
				ASTPair __currentAST106 = currentAST.copy();
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
				currentAST = __currentAST106;
				_t = __t106;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESS:
			{
				AST __t107 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t14_AST_in = null;
				t14_AST = (MyNode)astFactory.create(t14);
				astFactory.addASTChild(currentAST, t14_AST);
				ASTPair __currentAST107 = currentAST.copy();
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
				currentAST = __currentAST107;
				_t = __t107;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATER:
			{
				AST __t108 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t15_AST_in = null;
				t15_AST = (MyNode)astFactory.create(t15);
				astFactory.addASTChild(currentAST, t15_AST);
				ASTPair __currentAST108 = currentAST.copy();
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
				currentAST = __currentAST108;
				_t = __t108;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t109 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t16_AST_in = null;
				t16_AST = (MyNode)astFactory.create(t16);
				astFactory.addASTChild(currentAST, t16_AST);
				ASTPair __currentAST109 = currentAST.copy();
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
				currentAST = __currentAST109;
				_t = __t109;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t110 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t17_AST_in = null;
				t17_AST = (MyNode)astFactory.create(t17);
				astFactory.addASTChild(currentAST, t17_AST);
				ASTPair __currentAST110 = currentAST.copy();
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
				currentAST = __currentAST110;
				_t = __t110;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t111 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t18_AST_in = null;
				t18_AST = (MyNode)astFactory.create(t18);
				astFactory.addASTChild(currentAST, t18_AST);
				ASTPair __currentAST111 = currentAST.copy();
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
				currentAST = __currentAST111;
				_t = __t111;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INSET:
			{
				AST __t112 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t19_AST_in = null;
				t19_AST = (MyNode)astFactory.create(t19);
				astFactory.addASTChild(currentAST, t19_AST);
				ASTPair __currentAST112 = currentAST.copy();
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
				currentAST = __currentAST112;
				_t = __t112;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTINSET:
			{
				AST __t113 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t20_AST_in = null;
				t20_AST = (MyNode)astFactory.create(t20);
				astFactory.addASTChild(currentAST, t20_AST);
				ASTPair __currentAST113 = currentAST.copy();
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
				currentAST = __currentAST113;
				_t = __t113;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUBSET:
			{
				AST __t114 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t21_AST_in = null;
				t21_AST = (MyNode)astFactory.create(t21);
				astFactory.addASTChild(currentAST, t21_AST);
				ASTPair __currentAST114 = currentAST.copy();
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
				currentAST = __currentAST114;
				_t = __t114;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t115 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t22_AST_in = null;
				t22_AST = (MyNode)astFactory.create(t22);
				astFactory.addASTChild(currentAST, t22_AST);
				ASTPair __currentAST115 = currentAST.copy();
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
				currentAST = __currentAST115;
				_t = __t115;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t116 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t23_AST_in = null;
				t23_AST = (MyNode)astFactory.create(t23);
				astFactory.addASTChild(currentAST, t23_AST);
				ASTPair __currentAST116 = currentAST.copy();
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
				currentAST = __currentAST116;
				_t = __t116;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t117 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t24_AST_in = null;
				t24_AST = (MyNode)astFactory.create(t24);
				astFactory.addASTChild(currentAST, t24_AST);
				ASTPair __currentAST117 = currentAST.copy();
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
				currentAST = __currentAST117;
				_t = __t117;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t118 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t25_AST_in = null;
				t25_AST = (MyNode)astFactory.create(t25);
				astFactory.addASTChild(currentAST, t25_AST);
				ASTPair __currentAST118 = currentAST.copy();
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
				currentAST = __currentAST118;
				_t = __t118;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t119 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t26_AST_in = null;
				t26_AST = (MyNode)astFactory.create(t26);
				astFactory.addASTChild(currentAST, t26_AST);
				ASTPair __currentAST119 = currentAST.copy();
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
				currentAST = __currentAST119;
				_t = __t119;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_APPSEQ:
			{
				AST __t120 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t27_AST_in = null;
				t27_AST = (MyNode)astFactory.create(t27);
				astFactory.addASTChild(currentAST, t27_AST);
				ASTPair __currentAST120 = currentAST.copy();
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
				currentAST = __currentAST120;
				_t = __t120;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t121 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t28_AST_in = null;
				t28_AST = (MyNode)astFactory.create(t28);
				astFactory.addASTChild(currentAST, t28_AST);
				ASTPair __currentAST121 = currentAST.copy();
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
				currentAST = __currentAST121;
				_t = __t121;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t122 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t29_AST_in = null;
				t29_AST = (MyNode)astFactory.create(t29);
				astFactory.addASTChild(currentAST, t29_AST);
				ASTPair __currentAST122 = currentAST.copy();
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
				currentAST = __currentAST122;
				_t = __t122;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELATION:
			{
				AST __t123 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t30_AST_in = null;
				t30_AST = (MyNode)astFactory.create(t30);
				astFactory.addASTChild(currentAST, t30_AST);
				ASTPair __currentAST123 = currentAST.copy();
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
				currentAST = __currentAST123;
				_t = __t123;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL:
			{
				AST __t124 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t31_AST_in = null;
				t31_AST = (MyNode)astFactory.create(t31);
				astFactory.addASTChild(currentAST, t31_AST);
				ASTPair __currentAST124 = currentAST.copy();
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
				currentAST = __currentAST124;
				_t = __t124;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL:
			{
				AST __t125 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t32_AST_in = null;
				t32_AST = (MyNode)astFactory.create(t32);
				astFactory.addASTChild(currentAST, t32_AST);
				ASTPair __currentAST125 = currentAST.copy();
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
				currentAST = __currentAST125;
				_t = __t125;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t126 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t33_AST_in = null;
				t33_AST = (MyNode)astFactory.create(t33);
				astFactory.addASTChild(currentAST, t33_AST);
				ASTPair __currentAST126 = currentAST.copy();
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
				currentAST = __currentAST126;
				_t = __t126;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t127 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t34_AST_in = null;
				t34_AST = (MyNode)astFactory.create(t34);
				astFactory.addASTChild(currentAST, t34_AST);
				ASTPair __currentAST127 = currentAST.copy();
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
				currentAST = __currentAST127;
				_t = __t127;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t128 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t35_AST_in = null;
				t35_AST = (MyNode)astFactory.create(t35);
				astFactory.addASTChild(currentAST, t35_AST);
				ASTPair __currentAST128 = currentAST.copy();
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
				currentAST = __currentAST128;
				_t = __t128;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t129 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t36_AST_in = null;
				t36_AST = (MyNode)astFactory.create(t36);
				astFactory.addASTChild(currentAST, t36_AST);
				ASTPair __currentAST129 = currentAST.copy();
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
				currentAST = __currentAST129;
				_t = __t129;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BIJECTION:
			{
				AST __t130 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t37_AST_in = null;
				t37_AST = (MyNode)astFactory.create(t37);
				astFactory.addASTChild(currentAST, t37_AST);
				ASTPair __currentAST130 = currentAST.copy();
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
				currentAST = __currentAST130;
				_t = __t130;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t131 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t38_AST_in = null;
				t38_AST = (MyNode)astFactory.create(t38);
				astFactory.addASTChild(currentAST, t38_AST);
				ASTPair __currentAST131 = currentAST.copy();
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
				currentAST = __currentAST131;
				_t = __t131;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t132 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t39_AST_in = null;
				t39_AST = (MyNode)astFactory.create(t39);
				astFactory.addASTChild(currentAST, t39_AST);
				ASTPair __currentAST132 = currentAST.copy();
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
				currentAST = __currentAST132;
				_t = __t132;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t133 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t40_AST_in = null;
				t40_AST = (MyNode)astFactory.create(t40);
				astFactory.addASTChild(currentAST, t40_AST);
				ASTPair __currentAST133 = currentAST.copy();
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
				currentAST = __currentAST133;
				_t = __t133;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t134 = _t;
				t41 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t41_AST_in = null;
				t41_AST = (MyNode)astFactory.create(t41);
				astFactory.addASTChild(currentAST, t41_AST);
				ASTPair __currentAST134 = currentAST.copy();
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
				currentAST = __currentAST134;
				_t = __t134;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t135 = _t;
				t42 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t42_AST_in = null;
				t42_AST = (MyNode)astFactory.create(t42);
				astFactory.addASTChild(currentAST, t42_AST);
				ASTPair __currentAST135 = currentAST.copy();
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
				currentAST = __currentAST135;
				_t = __t135;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t136 = _t;
				t43 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t43_AST_in = null;
				t43_AST = (MyNode)astFactory.create(t43);
				astFactory.addASTChild(currentAST, t43_AST);
				ASTPair __currentAST136 = currentAST.copy();
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
				currentAST = __currentAST136;
				_t = __t136;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELPROD:
			{
				AST __t137 = _t;
				t44 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t44_AST_in = null;
				t44_AST = (MyNode)astFactory.create(t44);
				astFactory.addASTChild(currentAST, t44_AST);
				ASTPair __currentAST137 = currentAST.copy();
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
				currentAST = __currentAST137;
				_t = __t137;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_UNION:
			{
				AST __t138 = _t;
				t45 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t45_AST_in = null;
				t45_AST = (MyNode)astFactory.create(t45);
				astFactory.addASTChild(currentAST, t45_AST);
				ASTPair __currentAST138 = currentAST.copy();
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
				currentAST = __currentAST138;
				_t = __t138;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INTER:
			{
				AST __t139 = _t;
				t46 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t46_AST_in = null;
				t46_AST = (MyNode)astFactory.create(t46);
				astFactory.addASTChild(currentAST, t46_AST);
				ASTPair __currentAST139 = currentAST.copy();
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
				currentAST = __currentAST139;
				_t = __t139;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MAPLET:
			{
				AST __t140 = _t;
				t48 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t48_AST_in = null;
				t48_AST = (MyNode)astFactory.create(t48);
				astFactory.addASTChild(currentAST, t48_AST);
				ASTPair __currentAST140 = currentAST.copy();
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
				currentAST = __currentAST140;
				_t = __t140;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LIST_VAR:
			{
				AST __t141 = _t;
				t49 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t49_AST_in = null;
				t49_AST = (MyNode)astFactory.create(t49);
				astFactory.addASTChild(currentAST, t49_AST);
				ASTPair __currentAST141 = currentAST.copy();
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
				currentAST = __currentAST141;
				_t = __t141;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			case LITERAL_bool:
			case B_LPAREN:
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
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case APPLY_TO:
			case PARENT:
			case LITERAL_STRING:
			{
				cset_description(_t);
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
	
/** 
 *  Root Rule
 **/
	public final void traitement(AST _t) throws RecognitionException {
		
		MyNode traitement_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode traitement_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==PO)) {
				AST __t6 = _t;
				MyNode tmp8_AST = null;
				MyNode tmp8_AST_in = null;
				tmp8_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp8_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST6 = currentAST.copy();
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
				currentAST = __currentAST6;
				_t = __t6;
				_t = _t.getNextSibling();
				traitement_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==APO)) {
				a_PO(_t);
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
	
	public final void a_PO(AST _t) throws RecognitionException {
		
		MyNode a_PO_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_PO_AST = null;
		
		try {      // for error handling
			AST __t8 = _t;
			MyNode tmp9_AST = null;
			MyNode tmp9_AST_in = null;
			tmp9_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp9_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp9_AST);
			ASTPair __currentAST8 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,APO);
			_t = _t.getFirstChild();
			info(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST8;
			_t = __t8;
			_t = _t.getNextSibling();
			a_PO_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = a_PO_AST;
		_retTree = _t;
	}
	
	public final void info(AST _t) throws RecognitionException {
		
		MyNode info_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode info_AST = null;
		
		try {      // for error handling
			AST __t10 = _t;
			MyNode tmp10_AST = null;
			MyNode tmp10_AST_in = null;
			tmp10_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp10_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp10_AST);
			ASTPair __currentAST10 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_IMPLIES);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			goal(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST10;
			_t = __t10;
			_t = _t.getNextSibling();
			info_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = info_AST;
		_retTree = _t;
	}
	
	public final void goal(AST _t) throws RecognitionException {
		
		MyNode goal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode goal_AST = null;
		MyNode ii_AST = null;
		MyNode ii = null;
		MyNode pp_AST = null;
		MyNode pp = null;
		MyNode t = null;
		MyNode t_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==SUBST_TO)) {
				AST __t12 = _t;
				MyNode tmp11_AST = null;
				MyNode tmp11_AST_in = null;
				tmp11_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp11_AST_in = (MyNode)_t;
				ASTPair __currentAST12 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SUBST_TO);
				_t = _t.getFirstChild();
				ii = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				ii_AST = (MyNode)returnAST;
				pp = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				pp_AST = (MyNode)returnAST;
				goal_AST = (MyNode)currentAST.root;
				
					if (ii == null)
						// cas du skip qui produit null
						goal_AST = pp_AST;
					else	// cas general
						goal_AST = (MyNode)astFactory.make( (new ASTArray(3)).add(tmp11_AST).add(pp_AST).add(ii_AST));
				
				currentAST.root = goal_AST;
				currentAST.child = goal_AST!=null &&goal_AST.getFirstChild()!=null ?
					goal_AST.getFirstChild() : goal_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST12;
				_t = __t12;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==BTRUE)) {
				AST __t13 = _t;
				t = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t_AST_in = null;
				t_AST = (MyNode)astFactory.create(t);
				ASTPair __currentAST13 = currentAST.copy();
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
				currentAST = __currentAST13;
				_t = __t13;
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
		returnAST = goal_AST;
		_retTree = _t;
	}
	
	public final void branche_then(AST _t,
		AST pr
	) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_then_AST = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t47 = _t;
			MyNode tmp12_AST_in = null;
			ASTPair __currentAST47 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			branche_then_AST = (MyNode)currentAST.root;
			
				branche_then_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(pr).add(i_AST));
			
			currentAST.root = branche_then_AST;
			currentAST.child = branche_then_AST!=null &&branche_then_AST.getFirstChild()!=null ?
				branche_then_AST.getFirstChild() : branche_then_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST47;
			_t = __t47;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_then_AST;
		_retTree = _t;
	}
	
	public final void branche_elsif(AST _t,
		AST pr
	) throws RecognitionException {
		
		MyNode branche_elsif_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_elsif_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode p_AST = null;
		MyNode p = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t51 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			MyNode t1_AST_in = null;
			t1_AST = (MyNode)astFactory.create(t1);
			astFactory.addASTChild(currentAST, t1_AST);
			ASTPair __currentAST51 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			p = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			p_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			branche_elsif_AST = (MyNode)currentAST.root;
			
				branche_elsif_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add((MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(B_NOT,"NOT")).add(pr))).add(p_AST))).add(i_AST));
			
			currentAST.root = branche_elsif_AST;
			currentAST.child = branche_elsif_AST!=null &&branche_elsif_AST.getFirstChild()!=null ?
				branche_elsif_AST.getFirstChild() : branche_elsif_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST51;
			_t = __t51;
			_t = _t.getNextSibling();
			branche_elsif_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_elsif_AST;
		_retTree = _t;
	}
	
	public final void branche_else(AST _t,
		AST pr
	) throws RecognitionException {
		
		MyNode branche_else_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_else_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t49 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			MyNode tt_AST_in = null;
			tt_AST = (MyNode)astFactory.create(tt);
			ASTPair __currentAST49 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			branche_else_AST = (MyNode)currentAST.root;
			
				branche_else_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add((MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(B_NOT,"NOT")).add(pr))).add(i_AST));
			
			currentAST.root = branche_else_AST;
			currentAST.child = branche_else_AST!=null &&branche_else_AST.getFirstChild()!=null ?
				branche_else_AST.getFirstChild() : branche_else_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST49;
			_t = __t49;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_else_AST;
		_retTree = _t;
	}
	
	public final void list_or(AST _t) throws RecognitionException {
		
		MyNode list_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_or_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode l1_AST = null;
		MyNode l1 = null;
		MyNode i1_AST = null;
		MyNode i1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t39 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				ASTPair __currentAST39 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				l1 = _t==ASTNULL ? null : (MyNode)_t;
				list_or(_t);
				_t = _retTree;
				l1_AST = (MyNode)returnAST;
				i1 = _t==ASTNULL ? null : (MyNode)_t;
				instruction(_t);
				_t = _retTree;
				i1_AST = (MyNode)returnAST;
				list_or_AST = (MyNode)currentAST.root;
				
					list_or_AST	= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(l1_AST).add(i1_AST));
				
				currentAST.root = list_or_AST;
				currentAST.child = list_or_AST!=null &&list_or_AST.getFirstChild()!=null ?
					list_or_AST.getFirstChild() : list_or_AST;
				currentAST.advanceChildToEnd();
				currentAST = __currentAST39;
				_t = __t39;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_or_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_or_AST;
		_retTree = _t;
	}
	
	public final void branche_either(AST _t) throws RecognitionException {
		
		MyNode branche_either_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_either_AST = null;
		MyNode p_AST = null;
		MyNode p = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t43 = _t;
			MyNode tmp13_AST_in = null;
			ASTPair __currentAST43 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			p = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			p_AST = (MyNode)returnAST;
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			branche_either_AST = (MyNode)currentAST.root;
			
				branche_either_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p_AST).add(i_AST));
			
			currentAST.root = branche_either_AST;
			currentAST.child = branche_either_AST!=null &&branche_either_AST.getFirstChild()!=null ?
				branche_either_AST.getFirstChild() : branche_either_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST43;
			_t = __t43;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_either_AST;
		_retTree = _t;
	}
	
	public final void branche_or(AST _t) throws RecognitionException {
		
		MyNode branche_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_or_AST = null;
		MyNode p_AST = null;
		MyNode p = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t45 = _t;
			MyNode tmp14_AST_in = null;
			ASTPair __currentAST45 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			p = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			p_AST = (MyNode)returnAST;
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			branche_or_AST = (MyNode)currentAST.root;
			
				branche_or_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p_AST).add(i_AST));
			
			currentAST.root = branche_or_AST;
			currentAST.child = branche_or_AST!=null &&branche_or_AST.getFirstChild()!=null ?
				branche_or_AST.getFirstChild() : branche_or_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST45;
			_t = __t45;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_or_AST;
		_retTree = _t;
	}
	
	public final void listTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listTypedIdentifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t175 = _t;
				MyNode tmp15_AST = null;
				MyNode tmp15_AST_in = null;
				tmp15_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp15_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST175 = currentAST.copy();
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
				currentAST = __currentAST175;
				_t = __t175;
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
	
	public final void list_equal(AST _t) throws RecognitionException {
		
		MyNode list_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_equal_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_AND)) {
				AST __t54 = _t;
				MyNode tmp16_AST = null;
				MyNode tmp16_AST_in = null;
				tmp16_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp16_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST54 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				list_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				an_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST54;
				_t = __t54;
				_t = _t.getNextSibling();
				list_equal_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_EQUAL)) {
				an_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_equal_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_equal_AST;
		_retTree = _t;
	}
	
	public final void branche_when(AST _t) throws RecognitionException {
		
		MyNode branche_when_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_when_AST = null;
		MyNode p_AST = null;
		MyNode p = null;
		MyNode i_AST = null;
		MyNode i = null;
		
		try {      // for error handling
			AST __t41 = _t;
			MyNode tmp17_AST_in = null;
			ASTPair __currentAST41 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			p = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			p_AST = (MyNode)returnAST;
			i = _t==ASTNULL ? null : (MyNode)_t;
			instruction(_t);
			_t = _retTree;
			i_AST = (MyNode)returnAST;
			branche_when_AST = (MyNode)currentAST.root;
			
				branche_when_AST = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(GSL_GUARD,"==>")).add(p_AST).add(i_AST));
			
			currentAST.root = branche_when_AST;
			currentAST.child = branche_when_AST!=null &&branche_when_AST.getFirstChild()!=null ?
				branche_when_AST.getFirstChild() : branche_when_AST;
			currentAST.advanceChildToEnd();
			currentAST = __currentAST41;
			_t = __t41;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = branche_when_AST;
		_retTree = _t;
	}
	
	public final void variant_or_no(AST _t) throws RecognitionException {
		
		MyNode variant_or_no_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variant_or_no_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				t1 = (MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				match(_t,LITERAL_VARIANT);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				t2 = (MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				match(_t,LITERAL_INVARIANT);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				t3 = (MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				match(_t,LITERAL_INVARIANT);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				t4 = (MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				astFactory.addASTChild(currentAST, t4_AST);
				match(_t,LITERAL_VARIANT);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = variant_or_no_AST;
		_retTree = _t;
	}
	
	public final void simple_affect(AST _t) throws RecognitionException {
		
		MyNode simple_affect_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode simple_affect_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t85 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST85 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST85;
				_t = __t85;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OUT:
			{
				AST __t86 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST86 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST86;
				_t = __t86;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case INSET:
			{
				AST __t87 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST87 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,INSET);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST87;
				_t = __t87;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t88 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				ASTPair __currentAST88 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
				// ForALL Y WHERE [Y:=list_func_call]predicate THEN list_func_call := Y END
				// @ YList. [YList := list_func_call]predicate => list_func_call := YLIst
				
				//#simple_affcet = #([GSL_FOR_SUCH, "@"],l11, #([GSL_GUARD,"==>"], p11,i11));
				
				currentAST = __currentAST88;
				_t = __t88;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_QUOTEIDENT:
			case FUNC_CALL_PARAM:
			{
				a_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				simple_affect_AST = (MyNode)currentAST.root;
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
		returnAST = simple_affect_AST;
		_retTree = _t;
	}
	
	public final void an_equal(AST _t) throws RecognitionException {
		
		MyNode an_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode an_equal_AST = null;
		
		try {      // for error handling
			AST __t56 = _t;
			MyNode tmp18_AST = null;
			MyNode tmp18_AST_in = null;
			tmp18_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp18_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp18_AST);
			ASTPair __currentAST56 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp19_AST = null;
			MyNode tmp19_AST_in = null;
			tmp19_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp19_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp19_AST);
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST56;
			_t = __t56;
			_t = _t.getNextSibling();
			an_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = an_equal_AST;
		_retTree = _t;
	}
	
	public final void func_call(AST _t) throws RecognitionException {
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode func_call_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		MyNode t2 = null;
		MyNode t2_AST = null;
		MyNode t3 = null;
		MyNode t3_AST = null;
		MyNode t4 = null;
		MyNode t4_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t58 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST58 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST58;
				_t = __t58;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case APPLY_TO:
			{
				AST __t59 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST59 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_New_Predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST59;
				_t = __t59;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LPAREN:
			{
				AST __t60 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t3_AST_in = null;
				t3_AST = (MyNode)astFactory.create(t3);
				astFactory.addASTChild(currentAST, t3_AST);
				ASTPair __currentAST60 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_New_Predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST60;
				_t = __t60;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t61 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t4_AST_in = null;
				t4_AST = (MyNode)astFactory.create(t4);
				astFactory.addASTChild(currentAST, t4_AST);
				ASTPair __currentAST61 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST61;
				_t = __t61;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				func_call_AST = (MyNode)currentAST.root;
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
		returnAST = func_call_AST;
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
				AST __t64 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST64 = currentAST.copy();
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
				currentAST = __currentAST64;
				_t = __t64;
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
	
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp20_AST = null;
				MyNode tmp20_AST_in = null;
				tmp20_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp20_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp20_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				nameRenamed_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t69 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST69 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST69;
				_t = __t69;
				_t = _t.getNextSibling();
				nameRenamed_AST = (MyNode)currentAST.root;
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
	
	public final void func_param(AST _t) throws RecognitionException {
		
		MyNode func_param_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode func_param_AST = null;
		
		try {      // for error handling
			list_New_Predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			func_param_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = func_param_AST;
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
				AST __t66 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST66 = currentAST.copy();
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
				currentAST = __currentAST66;
				_t = __t66;
				_t = _t.getNextSibling();
				new_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t67 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t2_AST_in = null;
				t2_AST = (MyNode)astFactory.create(t2);
				astFactory.addASTChild(currentAST, t2_AST);
				ASTPair __currentAST67 = currentAST.copy();
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
				currentAST = __currentAST67;
				_t = __t67;
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
	
	public final void nameRenamedDecorated(AST _t) throws RecognitionException {
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamedDecorated_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t71 = _t;
				MyNode tmp21_AST = null;
				MyNode tmp21_AST_in = null;
				tmp21_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp21_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST71 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST71;
				_t = __t71;
				_t = _t.getNextSibling();
				nameRenamedDecorated_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
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
	
	public final void nameRenameDecoratedInverted(AST _t) throws RecognitionException {
		
		MyNode nameRenameDecoratedInverted_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenameDecoratedInverted_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_TILDE)) {
				AST __t73 = _t;
				MyNode tmp22_AST = null;
				MyNode tmp22_AST_in = null;
				tmp22_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp22_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST73 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST73;
				_t = __t73;
				_t = _t.getNextSibling();
				nameRenameDecoratedInverted_AST = (MyNode)currentAST.root;
			}
			else if (((_t.getType() >= B_IDENTIFIER && _t.getType() <= B_CPRED))) {
				nameRenamedDecorated(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				nameRenameDecoratedInverted_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = nameRenameDecoratedInverted_AST;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t75 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST75 = currentAST.copy();
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
				currentAST = __currentAST75;
				_t = __t75;
				_t = _t.getNextSibling();
				list_identifier_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp23_AST = null;
				MyNode tmp23_AST_in = null;
				tmp23_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp23_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
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
				AST __t77 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST77 = currentAST.copy();
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
				currentAST = __currentAST77;
				_t = __t77;
				_t = _t.getNextSibling();
				listPredicate_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
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
	
	public final void a_func_call(AST _t) throws RecognitionException {
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_func_call_AST = null;
		
		try {      // for error handling
			afc(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			a_func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = a_func_call_AST;
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
				AST __t80 = _t;
				MyNode tmp24_AST = null;
				MyNode tmp24_AST_in = null;
				tmp24_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp24_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST80 = currentAST.copy();
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
				currentAST = __currentAST80;
				_t = __t80;
				_t = _t.getNextSibling();
				afc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t81 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST81 = currentAST.copy();
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
				currentAST = __currentAST81;
				_t = __t81;
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
	
	public final void list_func_call(AST _t) throws RecognitionException {
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_func_call_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t83 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST83 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST83;
				_t = __t83;
				_t = _t.getNextSibling();
				list_func_call_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				a_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_func_call_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		returnAST = list_func_call_AST;
		_retTree = _t;
	}
	
	public final void cset_description(AST _t) throws RecognitionException {
		
		MyNode cset_description_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode cset_description_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
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
				cset_description_AST = (MyNode)currentAST.root;
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
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_bool:
			{
				AST __t143 = _t;
				MyNode tmp25_AST = null;
				MyNode tmp25_AST_in = null;
				tmp25_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp25_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST143 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST143;
				_t = __t143;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t144 = _t;
				MyNode tmp26_AST = null;
				MyNode tmp26_AST_in = null;
				tmp26_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp26_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST144 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST144;
				_t = __t144;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGE:
			{
				AST __t145 = _t;
				MyNode tmp27_AST = null;
				MyNode tmp27_AST_in = null;
				tmp27_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp27_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST145 = currentAST.copy();
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
				currentAST = __currentAST145;
				_t = __t145;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp28_AST = null;
				MyNode tmp28_AST_in = null;
				tmp28_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp28_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t146 = _t;
				MyNode tmp29_AST = null;
				MyNode tmp29_AST_in = null;
				tmp29_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp29_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST146 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST146;
				_t = __t146;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SEQEMPTY:
			{
				MyNode tmp30_AST = null;
				MyNode tmp30_AST_in = null;
				tmp30_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp30_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				match(_t,B_SEQEMPTY);
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				quantification(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				cset_description_AST = (MyNode)currentAST.root;
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
				cset_description_AST = (MyNode)currentAST.root;
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
		returnAST = cset_description_AST;
		_retTree = _t;
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
				MyNode tmp31_AST = null;
				MyNode tmp31_AST_in = null;
				tmp31_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp31_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp31_AST);
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp32_AST = null;
				MyNode tmp32_AST_in = null;
				tmp32_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp32_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				match(_t,87);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp33_AST = null;
				MyNode tmp33_AST_in = null;
				tmp33_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp33_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp34_AST = null;
				MyNode tmp34_AST_in = null;
				tmp34_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp34_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				match(_t,89);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp35_AST = null;
				MyNode tmp35_AST_in = null;
				tmp35_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp35_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp36_AST = null;
				MyNode tmp36_AST_in = null;
				tmp36_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp36_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp37_AST = null;
				MyNode tmp37_AST_in = null;
				tmp37_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp37_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				match(_t,92);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp38_AST = null;
				MyNode tmp38_AST_in = null;
				tmp38_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp38_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp39_AST = null;
				MyNode tmp39_AST_in = null;
				tmp39_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp39_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				match(_t,94);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp40_AST = null;
				MyNode tmp40_AST_in = null;
				tmp40_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp40_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				MyNode tmp41_AST = null;
				MyNode tmp41_AST_in = null;
				tmp41_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp41_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp42_AST = null;
				MyNode tmp42_AST_in = null;
				tmp42_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp42_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TILDE:
			{
				AST __t151 = _t;
				MyNode tmp43_AST = null;
				MyNode tmp43_AST_in = null;
				tmp43_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp43_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST151 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST151;
				_t = __t151;
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
				AST __t152 = _t;
				MyNode tmp44_AST = null;
				MyNode tmp44_AST_in = null;
				tmp44_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp44_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST152 = currentAST.copy();
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
				currentAST = __currentAST152;
				_t = __t152;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case PARENT:
			{
				AST __t153 = _t;
				MyNode tmp45_AST = null;
				MyNode tmp45_AST_in = null;
				tmp45_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp45_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST153 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST153;
				_t = __t153;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t154 = _t;
				MyNode tmp46_AST = null;
				MyNode tmp46_AST_in = null;
				tmp46_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp46_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST154 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST154;
				_t = __t154;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case APPLY_TO:
			{
				AST __t155 = _t;
				MyNode tmp47_AST = null;
				MyNode tmp47_AST_in = null;
				tmp47_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp47_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST155 = currentAST.copy();
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
				currentAST = __currentAST155;
				_t = __t155;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp48_AST = null;
				MyNode tmp48_AST_in = null;
				tmp48_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp48_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp49_AST = null;
				MyNode tmp49_AST_in = null;
				tmp49_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp49_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
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
				AST __t148 = _t;
				MyNode tmp50_AST = null;
				MyNode tmp50_AST_in = null;
				tmp50_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp50_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp50_AST);
				ASTPair __currentAST148 = currentAST.copy();
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
				currentAST = __currentAST148;
				_t = __t148;
				_t = _t.getNextSibling();
				cvalue_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t149 = _t;
				MyNode tmp51_AST = null;
				MyNode tmp51_AST_in = null;
				tmp51_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp51_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp51_AST);
				ASTPair __currentAST149 = currentAST.copy();
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
				currentAST = __currentAST149;
				_t = __t149;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t179 = _t;
				MyNode tmp52_AST = null;
				MyNode tmp52_AST_in = null;
				tmp52_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp52_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp52_AST);
				ASTPair __currentAST179 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST179;
				_t = __t179;
				_t = _t.getNextSibling();
				is_record_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t180 = _t;
				MyNode tmp53_AST = null;
				MyNode tmp53_AST_in = null;
				tmp53_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp53_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp53_AST);
				ASTPair __currentAST180 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST180;
				_t = __t180;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t162 = _t;
				MyNode tmp54_AST = null;
				MyNode tmp54_AST_in = null;
				tmp54_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp54_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp54_AST);
				ASTPair __currentAST162 = currentAST.copy();
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
				currentAST = __currentAST162;
				_t = __t162;
				_t = _t.getNextSibling();
				quantification_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t163 = _t;
				MyNode tmp55_AST = null;
				MyNode tmp55_AST_in = null;
				tmp55_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp55_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp55_AST);
				ASTPair __currentAST163 = currentAST.copy();
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
				currentAST = __currentAST163;
				_t = __t163;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t165 = _t;
				MyNode tmp56_AST = null;
				MyNode tmp56_AST_in = null;
				tmp56_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp56_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp56_AST);
				ASTPair __currentAST165 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST165;
				_t = __t165;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PI:
			{
				AST __t166 = _t;
				MyNode tmp57_AST = null;
				MyNode tmp57_AST_in = null;
				tmp57_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp57_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp57_AST);
				ASTPair __currentAST166 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST166;
				_t = __t166;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t167 = _t;
				MyNode tmp58_AST = null;
				MyNode tmp58_AST_in = null;
				tmp58_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp58_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp58_AST);
				ASTPair __currentAST167 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST167;
				_t = __t167;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_UNION:
			{
				AST __t168 = _t;
				MyNode tmp59_AST = null;
				MyNode tmp59_AST_in = null;
				tmp59_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp59_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp59_AST);
				ASTPair __currentAST168 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST168;
				_t = __t168;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTER:
			{
				AST __t169 = _t;
				MyNode tmp60_AST = null;
				MyNode tmp60_AST_in = null;
				tmp60_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp60_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp60_AST);
				ASTPair __currentAST169 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST169;
				_t = __t169;
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
				AST __t173 = _t;
				MyNode tmp61_AST = null;
				MyNode tmp61_AST_in = null;
				tmp61_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp61_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp61_AST);
				ASTPair __currentAST173 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST173;
				_t = __t173;
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
	
	public final void pred_func_composition(AST _t) throws RecognitionException {
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pred_func_composition_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t157 = _t;
				MyNode tmp62_AST = null;
				MyNode tmp62_AST_in = null;
				tmp62_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp62_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp62_AST);
				ASTPair __currentAST157 = currentAST.copy();
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
				currentAST = __currentAST157;
				_t = __t157;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t158 = _t;
				MyNode tmp63_AST = null;
				MyNode tmp63_AST_in = null;
				tmp63_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp63_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp63_AST);
				ASTPair __currentAST158 = currentAST.copy();
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
				currentAST = __currentAST158;
				_t = __t158;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t159 = _t;
				MyNode tmp64_AST = null;
				MyNode tmp64_AST_in = null;
				tmp64_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp64_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp64_AST);
				ASTPair __currentAST159 = currentAST.copy();
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
				currentAST = __currentAST159;
				_t = __t159;
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
			AST __t171 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			MyNode t1_AST_in = null;
			t1_AST = (MyNode)astFactory.create(t1);
			astFactory.addASTChild(currentAST, t1_AST);
			ASTPair __currentAST171 = currentAST.copy();
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
			currentAST = __currentAST171;
			_t = __t171;
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
	
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode typedIdentifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t177 = _t;
				MyNode tmp65_AST = null;
				MyNode tmp65_AST_in = null;
				tmp65_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp65_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp65_AST);
				ASTPair __currentAST177 = currentAST.copy();
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
				currentAST = __currentAST177;
				_t = __t177;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t182 = _t;
				MyNode tmp66_AST = null;
				MyNode tmp66_AST_in = null;
				tmp66_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp66_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp66_AST);
				ASTPair __currentAST182 = currentAST.copy();
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
				currentAST = __currentAST182;
				_t = __t182;
				_t = _t.getNextSibling();
				listrecord_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_4.member(_t.getType()))) {
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t184 = _t;
				MyNode tmp67_AST = null;
				MyNode tmp67_AST_in = null;
				tmp67_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp67_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp67_AST);
				ASTPair __currentAST184 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				MyNode tmp68_AST = null;
				MyNode tmp68_AST_in = null;
				tmp68_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp68_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp68_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST184;
				_t = __t184;
				_t = _t.getNextSibling();
				a_record_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
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
	
	public final void dummy(AST _t) throws RecognitionException {
		
		MyNode dummy_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode dummy_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case GSL_SUCH:
			{
				MyNode tmp69_AST = null;
				MyNode tmp69_AST_in = null;
				tmp69_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp69_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp69_AST);
				match(_t,GSL_SUCH);
				_t = _t.getNextSibling();
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case GSL_FOR_SUCH:
			{
				MyNode tmp70_AST = null;
				MyNode tmp70_AST_in = null;
				tmp70_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp70_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp70_AST);
				match(_t,GSL_FOR_SUCH);
				_t = _t.getNextSibling();
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case GSL_GUARD:
			{
				MyNode tmp71_AST = null;
				MyNode tmp71_AST_in = null;
				tmp71_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp71_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp71_AST);
				match(_t,GSL_GUARD);
				_t = _t.getNextSibling();
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case GSL_BOUNDED:
			{
				MyNode tmp72_AST = null;
				MyNode tmp72_AST_in = null;
				tmp72_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp72_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp72_AST);
				match(_t,GSL_BOUNDED);
				_t = _t.getNextSibling();
				dummy_AST = (MyNode)currentAST.root;
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
		returnAST = dummy_AST;
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
		long[] data = { 96L, 117093590311632912L, 33366376252964864L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -3530822108395339808L, 215083372247071L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -3530822108395339808L, 215083372240927L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 96L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -3530822108395339808L, 215220811194399L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	}
	
