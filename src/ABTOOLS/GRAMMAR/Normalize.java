// $ANTLR 2.7.6 (2005-12-22): "Normalize.g" -> "Normalize.java"$

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

// General packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;
	import antlr.ASTFactory;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*; 


public class Normalize extends antlr.TreeParser       implements NormalizeTokenTypes
 {

	String module = "Normalize.g";

//	Data Temporary
	MyNode 	T1, T2, T3, T4;
	MyNode 	save[] 			= 	new MyNode[15];

	int 	save_cmp 		= 	1;

//	Definition Name and parameter
	AST	def_name		= 	null;
	AST	def_param		= 	null;
	AST	def_body		= 	null;
	boolean def			= 	false;

//	Abstract World or NOT 
	boolean abstract_m 		= 	true;

// Instance d'un GSL
	GSL uu = new GSL();


//
    boolean modify = false ;

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

	public ListDefinition getListDefinition ()
	{
		return listdefinition;
	}

public Normalize() {
	tokenNames = _tokenNames;
}

/**
 *	La regle "composant" permet de definir le point d'entree pour realiser le typage.
 **/
	public final void composant(AST _t) throws RecognitionException {
		
		MyNode composant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode composant_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_MACHINE:
			{
				if ( inputState.guessing==0 ) {
					
					uu.setASTNodeType(MyNode.class.getName());
					
				}
				AST __t2 = _t;
				MyNode tmp1_AST = null;
				MyNode tmp1_AST_in = null;
				tmp1_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp1_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp1_AST);
				ASTPair __currentAST2 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_MACHINE);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					abstract_m = true ;
				}
				paramName(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				clauses(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST2;
				_t = __t2;
				_t = _t.getNextSibling();
				composant_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_REFINEMENT:
			{
				AST __t3 = _t;
				MyNode tmp2_AST = null;
				MyNode tmp2_AST_in = null;
				tmp2_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp2_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp2_AST);
				ASTPair __currentAST3 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_REFINEMENT);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					abstract_m = true ;
				}
				paramName(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				clauses(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST3;
				_t = __t3;
				_t = _t.getNextSibling();
				composant_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IMPLEMENTATION:
			{
				AST __t4 = _t;
				MyNode tmp3_AST = null;
				MyNode tmp3_AST_in = null;
				tmp3_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp3_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp3_AST);
				ASTPair __currentAST4 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_IMPLEMENTATION);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					abstract_m = false;
				}
				paramName(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				clauses(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST4;
				_t = __t4;
				_t = _t.getNextSibling();
				composant_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SYSTEM:
			{
				AST __t5 = _t;
				MyNode tmp4_AST = null;
				MyNode tmp4_AST_in = null;
				tmp4_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp4_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp4_AST);
				ASTPair __currentAST5 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SYSTEM);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					abstract_m = true ;
				}
				paramName(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				clauses(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST5;
				_t = __t5;
				_t = _t.getNextSibling();
				composant_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = composant_AST;
		_retTree = _t;
	}
	
/**
 * This rule is used for defined all Name with or without parameters
 **/
	public final void paramName(AST _t) throws RecognitionException {
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode paramName_AST = null;
		MyNode bb = null;
		MyNode bb_AST = null;
		MyNode n1 = null;
		MyNode n1_AST = null;
		MyNode l1_AST = null;
		MyNode l1 = null;
		MyNode n2 = null;
		MyNode n2_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t41 = _t;
				bb = _t==ASTNULL ? null :(MyNode)_t;
				MyNode bb_AST_in = null;
				bb_AST = (MyNode)astFactory.create(bb);
				astFactory.addASTChild(currentAST, bb_AST);
				ASTPair __currentAST41 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				n1 = (MyNode)_t;
				MyNode n1_AST_in = null;
				n1_AST = (MyNode)astFactory.create(n1);
				astFactory.addASTChild(currentAST, n1_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				l1 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				l1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
					String ss = n1_AST.getText();
					System.out.println("paramName =" + ss);
					
						bb_AST.memorizeOpname(n1_AST.getText());
					n1_AST.memorizeOpname(n1_AST.getText());
					
					//	For definition, we prepare the work
						def_name 	= (MyNode) astFactory.dupTree(n1_AST);
						def_param	= (MyNode) astFactory.dupTree(l1_AST);
					
				}
				currentAST = __currentAST41;
				_t = __t41;
				_t = _t.getNextSibling();
				paramName_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				n2 = (MyNode)_t;
				MyNode n2_AST_in = null;
				n2_AST = (MyNode)astFactory.create(n2);
				astFactory.addASTChild(currentAST, n2_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						n2_AST.memorizeOpname(n2_AST.getText());
					
					//	For definition, we prepare the work
						def_name 	= (MyNode) astFactory.dupTree(n2_AST);
						def_param	= null;
					
				}
				paramName_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = paramName_AST;
		_retTree = _t;
	}
	
	public final void clauses(AST _t) throws RecognitionException {
		
		MyNode clauses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode clauses_AST = null;
		
		try {      // for error handling
			{
			_loop8:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					clause(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
			clauses_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = clauses_AST;
		_retTree = _t;
	}
	
	public final void clause(AST _t) throws RecognitionException {
		
		MyNode clause_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode clause_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTRAINTS:
			{
				AST __t10 = _t;
				MyNode tmp5_AST = null;
				MyNode tmp5_AST_in = null;
				tmp5_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp5_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp5_AST);
				ASTPair __currentAST10 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CONSTRAINTS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST10;
				_t = __t10;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_EXTENDS:
			{
				AST __t11 = _t;
				MyNode tmp6_AST = null;
				MyNode tmp6_AST_in = null;
				tmp6_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp6_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp6_AST);
				ASTPair __currentAST11 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_EXTENDS);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST11;
				_t = __t11;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_USES:
			{
				AST __t12 = _t;
				MyNode tmp7_AST = null;
				MyNode tmp7_AST_in = null;
				tmp7_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp7_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST12 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_USES);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST12;
				_t = __t12;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INCLUDES:
			{
				AST __t13 = _t;
				MyNode tmp8_AST = null;
				MyNode tmp8_AST_in = null;
				tmp8_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp8_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST13 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INCLUDES);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST13;
				_t = __t13;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SEES:
			{
				AST __t14 = _t;
				MyNode tmp9_AST = null;
				MyNode tmp9_AST_in = null;
				tmp9_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp9_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp9_AST);
				ASTPair __currentAST14 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SEES);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST14;
				_t = __t14;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IMPORTS:
			{
				AST __t15 = _t;
				MyNode tmp10_AST = null;
				MyNode tmp10_AST_in = null;
				tmp10_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp10_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp10_AST);
				ASTPair __currentAST15 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_IMPORTS);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST15;
				_t = __t15;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PROMOTES:
			{
				AST __t16 = _t;
				MyNode tmp11_AST = null;
				MyNode tmp11_AST_in = null;
				tmp11_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp11_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp11_AST);
				ASTPair __currentAST16 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PROMOTES);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST16;
				_t = __t16;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_REFINES:
			{
				AST __t17 = _t;
				MyNode tmp12_AST = null;
				MyNode tmp12_AST_in = null;
				tmp12_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp12_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp12_AST);
				ASTPair __currentAST17 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_REFINES);
				_t = _t.getFirstChild();
				MyNode tmp13_AST = null;
				MyNode tmp13_AST_in = null;
				tmp13_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp13_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp13_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				currentAST = __currentAST17;
				_t = __t17;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CONSTANTS:
			{
				AST __t18 = _t;
				MyNode tmp14_AST = null;
				MyNode tmp14_AST_in = null;
				tmp14_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp14_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp14_AST);
				ASTPair __currentAST18 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST18;
				_t = __t18;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t19 = _t;
				MyNode tmp15_AST = null;
				MyNode tmp15_AST_in = null;
				tmp15_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp15_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST19 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST19;
				_t = __t19;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t20 = _t;
				MyNode tmp16_AST = null;
				MyNode tmp16_AST_in = null;
				tmp16_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp16_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST20 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST20;
				_t = __t20;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t21 = _t;
				MyNode tmp17_AST = null;
				MyNode tmp17_AST_in = null;
				tmp17_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp17_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp17_AST);
				ASTPair __currentAST21 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST21;
				_t = __t21;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t22 = _t;
				MyNode tmp18_AST = null;
				MyNode tmp18_AST_in = null;
				tmp18_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp18_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp18_AST);
				ASTPair __currentAST22 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST22;
				_t = __t22;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SETS:
			{
				AST __t23 = _t;
				MyNode tmp19_AST = null;
				MyNode tmp19_AST_in = null;
				tmp19_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp19_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp19_AST);
				ASTPair __currentAST23 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SETS);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST23;
				_t = __t23;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VALUES:
			{
				AST __t24 = _t;
				MyNode tmp20_AST = null;
				MyNode tmp20_AST_in = null;
				tmp20_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp20_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp20_AST);
				ASTPair __currentAST24 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VALUES);
				_t = _t.getFirstChild();
				list_valuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST24;
				_t = __t24;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PROPERTIES:
			{
				AST __t25 = _t;
				MyNode tmp21_AST = null;
				MyNode tmp21_AST_in = null;
				tmp21_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp21_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST25 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PROPERTIES);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST25;
				_t = __t25;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VARIABLES:
			{
				AST __t26 = _t;
				MyNode tmp22_AST = null;
				MyNode tmp22_AST_in = null;
				tmp22_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp22_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST26 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST26;
				_t = __t26;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t27 = _t;
				MyNode tmp23_AST = null;
				MyNode tmp23_AST_in = null;
				tmp23_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp23_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
				ASTPair __currentAST27 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST27;
				_t = __t27;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t28 = _t;
				MyNode tmp24_AST = null;
				MyNode tmp24_AST_in = null;
				tmp24_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp24_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST28 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST28;
				_t = __t28;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t29 = _t;
				MyNode tmp25_AST = null;
				MyNode tmp25_AST_in = null;
				tmp25_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp25_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST29 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST29;
				_t = __t29;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t30 = _t;
				MyNode tmp26_AST = null;
				MyNode tmp26_AST_in = null;
				tmp26_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp26_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST30 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST30;
				_t = __t30;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INVARIANT:
			{
				AST __t31 = _t;
				MyNode tmp27_AST = null;
				MyNode tmp27_AST_in = null;
				tmp27_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp27_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST31 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST31;
				_t = __t31;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_DEFINITIONS:
			{
				AST __t32 = _t;
				MyNode tmp28_AST = null;
				MyNode tmp28_AST_in = null;
				tmp28_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp28_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				ASTPair __currentAST32 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_DEFINITIONS);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					
					def = true;
					
				}
				list_def(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
					def = false;
					
				}
				currentAST = __currentAST32;
				_t = __t32;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INITIALISATION:
			{
				AST __t33 = _t;
				MyNode tmp29_AST = null;
				MyNode tmp29_AST_in = null;
				tmp29_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp29_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST33 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INITIALISATION);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST33;
				_t = __t33;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_OPERATIONS:
			{
				AST __t34 = _t;
				MyNode tmp30_AST = null;
				MyNode tmp30_AST_in = null;
				tmp30_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp30_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				ASTPair __currentAST34 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_OPERATIONS);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST34;
				_t = __t34;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ASSERTIONS:
			{
				AST __t35 = _t;
				MyNode tmp31_AST = null;
				MyNode tmp31_AST_in = null;
				tmp31_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp31_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp31_AST);
				ASTPair __currentAST35 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ASSERTIONS);
				_t = _t.getFirstChild();
				list_assertions(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST35;
				_t = __t35;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_EVENTS:
			{
				AST __t36 = _t;
				MyNode tmp32_AST = null;
				MyNode tmp32_AST_in = null;
				tmp32_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp32_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				ASTPair __currentAST36 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_EVENTS);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST36;
				_t = __t36;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_MODALITIES:
			{
				AST __t37 = _t;
				MyNode tmp33_AST = null;
				MyNode tmp33_AST_in = null;
				tmp33_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp33_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				ASTPair __currentAST37 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_MODALITIES);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST37;
				_t = __t37;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_DYNAMICS:
			{
				AST __t38 = _t;
				MyNode tmp34_AST = null;
				MyNode tmp34_AST_in = null;
				tmp34_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp34_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				ASTPair __currentAST38 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_DYNAMICS);
				_t = _t.getFirstChild();
				predicate_with_prime(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST38;
				_t = __t38;
				_t = _t.getNextSibling();
				clause_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = clause_AST;
		_retTree = _t;
	}
	
/** 
 * Clause Predicate
 **/
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		MyNode p1_AST = null;
		MyNode p1 = null;
		MyNode p2_AST = null;
		MyNode p2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_AND:
			{
				AST __t208 = _t;
				MyNode tmp35_AST = null;
				MyNode tmp35_AST_in = null;
				tmp35_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp35_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				ASTPair __currentAST208 = currentAST.copy();
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
				currentAST = __currentAST208;
				_t = __t208;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_or:
			{
				AST __t209 = _t;
				MyNode tmp36_AST = null;
				MyNode tmp36_AST_in = null;
				tmp36_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp36_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				ASTPair __currentAST209 = currentAST.copy();
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
				currentAST = __currentAST209;
				_t = __t209;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IMPLIES:
			{
				AST __t210 = _t;
				MyNode tmp37_AST = null;
				MyNode tmp37_AST_in = null;
				tmp37_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp37_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				ASTPair __currentAST210 = currentAST.copy();
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
				currentAST = __currentAST210;
				_t = __t210;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUIV:
			{
				AST __t211 = _t;
				MyNode tmp38_AST = null;
				MyNode tmp38_AST_in = null;
				tmp38_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp38_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				ASTPair __currentAST211 = currentAST.copy();
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
				currentAST = __currentAST211;
				_t = __t211;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MULT:
			{
				AST __t212 = _t;
				MyNode tmp39_AST = null;
				MyNode tmp39_AST_in = null;
				tmp39_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp39_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				ASTPair __currentAST212 = currentAST.copy();
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
				currentAST = __currentAST212;
				_t = __t212;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case PRODSET:
			{
				AST __t213 = _t;
				MyNode tmp40_AST = null;
				MyNode tmp40_AST_in = null;
				tmp40_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp40_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
				ASTPair __currentAST213 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST213;
				_t = __t213;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_POWER:
			{
				AST __t214 = _t;
				MyNode tmp41_AST = null;
				MyNode tmp41_AST_in = null;
				tmp41_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp41_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				ASTPair __currentAST214 = currentAST.copy();
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
				currentAST = __currentAST214;
				_t = __t214;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DIV:
			{
				AST __t215 = _t;
				MyNode tmp42_AST = null;
				MyNode tmp42_AST_in = null;
				tmp42_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp42_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				ASTPair __currentAST215 = currentAST.copy();
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
				currentAST = __currentAST215;
				_t = __t215;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_mod:
			{
				AST __t216 = _t;
				MyNode tmp43_AST = null;
				MyNode tmp43_AST_in = null;
				tmp43_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp43_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST216 = currentAST.copy();
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
				currentAST = __currentAST216;
				_t = __t216;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_ADD:
			{
				AST __t217 = _t;
				MyNode tmp44_AST = null;
				MyNode tmp44_AST_in = null;
				tmp44_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp44_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST217 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST217;
				_t = __t217;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_MINUS:
			{
				AST __t218 = _t;
				MyNode tmp45_AST = null;
				MyNode tmp45_AST_in = null;
				tmp45_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp45_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST218 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST218;
				_t = __t218;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ADD:
			{
				AST __t219 = _t;
				MyNode tmp46_AST = null;
				MyNode tmp46_AST_in = null;
				tmp46_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp46_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST219 = currentAST.copy();
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
				currentAST = __currentAST219;
				_t = __t219;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				AST __t220 = _t;
				MyNode tmp47_AST = null;
				MyNode tmp47_AST_in = null;
				tmp47_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp47_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST220 = currentAST.copy();
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
				currentAST = __currentAST220;
				_t = __t220;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EQUAL:
			{
				AST __t221 = _t;
				MyNode tmp48_AST = null;
				MyNode tmp48_AST_in = null;
				tmp48_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp48_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				ASTPair __currentAST221 = currentAST.copy();
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
				currentAST = __currentAST221;
				_t = __t221;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESS:
			{
				AST __t222 = _t;
				MyNode tmp49_AST = null;
				MyNode tmp49_AST_in = null;
				tmp49_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp49_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
				ASTPair __currentAST222 = currentAST.copy();
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
				currentAST = __currentAST222;
				_t = __t222;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATER:
			{
				AST __t223 = _t;
				MyNode tmp50_AST = null;
				MyNode tmp50_AST_in = null;
				tmp50_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp50_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp50_AST);
				ASTPair __currentAST223 = currentAST.copy();
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
				currentAST = __currentAST223;
				_t = __t223;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t224 = _t;
				MyNode tmp51_AST = null;
				MyNode tmp51_AST_in = null;
				tmp51_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp51_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp51_AST);
				ASTPair __currentAST224 = currentAST.copy();
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
				currentAST = __currentAST224;
				_t = __t224;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t225 = _t;
				MyNode tmp52_AST = null;
				MyNode tmp52_AST_in = null;
				tmp52_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp52_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp52_AST);
				ASTPair __currentAST225 = currentAST.copy();
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
				currentAST = __currentAST225;
				_t = __t225;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t226 = _t;
				MyNode tmp53_AST = null;
				MyNode tmp53_AST_in = null;
				tmp53_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp53_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp53_AST);
				ASTPair __currentAST226 = currentAST.copy();
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
				currentAST = __currentAST226;
				_t = __t226;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INSET:
			{
				AST __t227 = _t;
				MyNode tmp54_AST = null;
				MyNode tmp54_AST_in = null;
				tmp54_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp54_AST_in = (MyNode)_t;
				ASTPair __currentAST227 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				p1 = _t==ASTNULL ? null : (MyNode)_t;
				ml_var(_t);
				_t = _retTree;
				p1_AST = (MyNode)returnAST;
				p2 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p2_AST = (MyNode)returnAST;
				if ( inputState.guessing==0 ) {
					predicate_AST = (MyNode)currentAST.root;
					
					
					// Expansion d'un B_INSET
					System.out.println("Expansion INSET - LIST_VAR >>");
					
						MyNode a1 	= 	(MyNode) astFactory.dupTree(p1_AST) ;
						MyNode a2 	= 	(MyNode) astFactory.dupTree(p2_AST) ;
						MyNode t2 	= 	(MyNode)astFactory.make( (new ASTArray(3)).add(tmp54_AST).add(a1).add(a2));
					
						predicate_AST	=	inset(t2);
					System.out.println("Expansion INSET - LIST_VAR <<");
					
					currentAST.root = predicate_AST;
					currentAST.child = predicate_AST!=null &&predicate_AST.getFirstChild()!=null ?
						predicate_AST.getFirstChild() : predicate_AST;
					currentAST.advanceChildToEnd();
				}
				currentAST = __currentAST227;
				_t = __t227;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t228 = _t;
				MyNode tmp55_AST = null;
				MyNode tmp55_AST_in = null;
				tmp55_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp55_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp55_AST);
				ASTPair __currentAST228 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST228;
				_t = __t228;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOM:
			{
				AST __t229 = _t;
				MyNode tmp56_AST = null;
				MyNode tmp56_AST_in = null;
				tmp56_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp56_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp56_AST);
				ASTPair __currentAST229 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST229;
				_t = __t229;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MAX:
			{
				AST __t230 = _t;
				MyNode tmp57_AST = null;
				MyNode tmp57_AST_in = null;
				tmp57_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp57_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp57_AST);
				ASTPair __currentAST230 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MAX);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST230;
				_t = __t230;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MIN:
			{
				AST __t231 = _t;
				MyNode tmp58_AST = null;
				MyNode tmp58_AST_in = null;
				tmp58_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp58_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp58_AST);
				ASTPair __currentAST231 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MIN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST231;
				_t = __t231;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CARD:
			{
				AST __t232 = _t;
				MyNode tmp59_AST = null;
				MyNode tmp59_AST_in = null;
				tmp59_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp59_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp59_AST);
				ASTPair __currentAST232 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST232;
				_t = __t232;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RAN:
			{
				AST __t233 = _t;
				MyNode tmp60_AST = null;
				MyNode tmp60_AST_in = null;
				tmp60_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp60_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp60_AST);
				ASTPair __currentAST233 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST233;
				_t = __t233;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTINSET:
			{
				AST __t234 = _t;
				MyNode tmp61_AST = null;
				MyNode tmp61_AST_in = null;
				tmp61_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp61_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp61_AST);
				ASTPair __currentAST234 = currentAST.copy();
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
				currentAST = __currentAST234;
				_t = __t234;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUBSET:
			{
				AST __t235 = _t;
				MyNode tmp62_AST = null;
				MyNode tmp62_AST_in = null;
				tmp62_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp62_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp62_AST);
				ASTPair __currentAST235 = currentAST.copy();
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
				currentAST = __currentAST235;
				_t = __t235;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t236 = _t;
				MyNode tmp63_AST = null;
				MyNode tmp63_AST_in = null;
				tmp63_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp63_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp63_AST);
				ASTPair __currentAST236 = currentAST.copy();
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
				currentAST = __currentAST236;
				_t = __t236;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t237 = _t;
				MyNode tmp64_AST = null;
				MyNode tmp64_AST_in = null;
				tmp64_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp64_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp64_AST);
				ASTPair __currentAST237 = currentAST.copy();
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
				currentAST = __currentAST237;
				_t = __t237;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t238 = _t;
				MyNode tmp65_AST = null;
				MyNode tmp65_AST_in = null;
				tmp65_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp65_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp65_AST);
				ASTPair __currentAST238 = currentAST.copy();
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
				currentAST = __currentAST238;
				_t = __t238;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t239 = _t;
				MyNode tmp66_AST = null;
				MyNode tmp66_AST_in = null;
				tmp66_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp66_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp66_AST);
				ASTPair __currentAST239 = currentAST.copy();
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
				currentAST = __currentAST239;
				_t = __t239;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t240 = _t;
				MyNode tmp67_AST = null;
				MyNode tmp67_AST_in = null;
				tmp67_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp67_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp67_AST);
				ASTPair __currentAST240 = currentAST.copy();
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
				currentAST = __currentAST240;
				_t = __t240;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_APPSEQ:
			{
				AST __t241 = _t;
				MyNode tmp68_AST = null;
				MyNode tmp68_AST_in = null;
				tmp68_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp68_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp68_AST);
				ASTPair __currentAST241 = currentAST.copy();
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
				currentAST = __currentAST241;
				_t = __t241;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t242 = _t;
				MyNode tmp69_AST = null;
				MyNode tmp69_AST_in = null;
				tmp69_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp69_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp69_AST);
				ASTPair __currentAST242 = currentAST.copy();
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
				currentAST = __currentAST242;
				_t = __t242;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t243 = _t;
				MyNode tmp70_AST = null;
				MyNode tmp70_AST_in = null;
				tmp70_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp70_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp70_AST);
				ASTPair __currentAST243 = currentAST.copy();
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
				currentAST = __currentAST243;
				_t = __t243;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELATION:
			{
				AST __t244 = _t;
				MyNode tmp71_AST = null;
				MyNode tmp71_AST_in = null;
				tmp71_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp71_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp71_AST);
				ASTPair __currentAST244 = currentAST.copy();
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
				currentAST = __currentAST244;
				_t = __t244;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL:
			{
				AST __t245 = _t;
				MyNode tmp72_AST = null;
				MyNode tmp72_AST_in = null;
				tmp72_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp72_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp72_AST);
				ASTPair __currentAST245 = currentAST.copy();
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
				currentAST = __currentAST245;
				_t = __t245;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL:
			{
				AST __t246 = _t;
				MyNode tmp73_AST = null;
				MyNode tmp73_AST_in = null;
				tmp73_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp73_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp73_AST);
				ASTPair __currentAST246 = currentAST.copy();
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
				currentAST = __currentAST246;
				_t = __t246;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t247 = _t;
				MyNode tmp74_AST = null;
				MyNode tmp74_AST_in = null;
				tmp74_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp74_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp74_AST);
				ASTPair __currentAST247 = currentAST.copy();
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
				currentAST = __currentAST247;
				_t = __t247;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t248 = _t;
				MyNode tmp75_AST = null;
				MyNode tmp75_AST_in = null;
				tmp75_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp75_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp75_AST);
				ASTPair __currentAST248 = currentAST.copy();
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
				currentAST = __currentAST248;
				_t = __t248;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t249 = _t;
				MyNode tmp76_AST = null;
				MyNode tmp76_AST_in = null;
				tmp76_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp76_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp76_AST);
				ASTPair __currentAST249 = currentAST.copy();
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
				currentAST = __currentAST249;
				_t = __t249;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t250 = _t;
				MyNode tmp77_AST = null;
				MyNode tmp77_AST_in = null;
				tmp77_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp77_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp77_AST);
				ASTPair __currentAST250 = currentAST.copy();
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
				currentAST = __currentAST250;
				_t = __t250;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BIJECTION:
			{
				AST __t251 = _t;
				MyNode tmp78_AST = null;
				MyNode tmp78_AST_in = null;
				tmp78_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp78_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp78_AST);
				ASTPair __currentAST251 = currentAST.copy();
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
				currentAST = __currentAST251;
				_t = __t251;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t252 = _t;
				MyNode tmp79_AST = null;
				MyNode tmp79_AST_in = null;
				tmp79_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp79_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp79_AST);
				ASTPair __currentAST252 = currentAST.copy();
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
				currentAST = __currentAST252;
				_t = __t252;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t253 = _t;
				MyNode tmp80_AST = null;
				MyNode tmp80_AST_in = null;
				tmp80_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp80_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp80_AST);
				ASTPair __currentAST253 = currentAST.copy();
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
				currentAST = __currentAST253;
				_t = __t253;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t254 = _t;
				MyNode tmp81_AST = null;
				MyNode tmp81_AST_in = null;
				tmp81_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp81_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp81_AST);
				ASTPair __currentAST254 = currentAST.copy();
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
				currentAST = __currentAST254;
				_t = __t254;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t255 = _t;
				MyNode tmp82_AST = null;
				MyNode tmp82_AST_in = null;
				tmp82_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp82_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp82_AST);
				ASTPair __currentAST255 = currentAST.copy();
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
				currentAST = __currentAST255;
				_t = __t255;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t256 = _t;
				MyNode tmp83_AST = null;
				MyNode tmp83_AST_in = null;
				tmp83_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp83_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp83_AST);
				ASTPair __currentAST256 = currentAST.copy();
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
				currentAST = __currentAST256;
				_t = __t256;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t257 = _t;
				MyNode tmp84_AST = null;
				MyNode tmp84_AST_in = null;
				tmp84_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp84_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp84_AST);
				ASTPair __currentAST257 = currentAST.copy();
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
				currentAST = __currentAST257;
				_t = __t257;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELPROD:
			{
				AST __t258 = _t;
				MyNode tmp85_AST = null;
				MyNode tmp85_AST_in = null;
				tmp85_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp85_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp85_AST);
				ASTPair __currentAST258 = currentAST.copy();
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
				currentAST = __currentAST258;
				_t = __t258;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_UNION:
			{
				AST __t259 = _t;
				MyNode tmp86_AST = null;
				MyNode tmp86_AST_in = null;
				tmp86_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp86_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp86_AST);
				ASTPair __currentAST259 = currentAST.copy();
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
				currentAST = __currentAST259;
				_t = __t259;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_INTER:
			{
				AST __t260 = _t;
				MyNode tmp87_AST = null;
				MyNode tmp87_AST_in = null;
				tmp87_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp87_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp87_AST);
				ASTPair __currentAST260 = currentAST.copy();
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
				currentAST = __currentAST260;
				_t = __t260;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MAPLET:
			{
				AST __t261 = _t;
				MyNode tmp88_AST = null;
				MyNode tmp88_AST_in = null;
				tmp88_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp88_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp88_AST);
				ASTPair __currentAST261 = currentAST.copy();
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
				currentAST = __currentAST261;
				_t = __t261;
				_t = _t.getNextSibling();
				predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case LIST_VAR:
			{
				AST __t262 = _t;
				MyNode tmp89_AST = null;
				MyNode tmp89_AST_in = null;
				tmp89_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp89_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp89_AST);
				ASTPair __currentAST262 = currentAST.copy();
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
				currentAST = __currentAST262;
				_t = __t262;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_AST;
		_retTree = _t;
	}
	
	public final void listInstanciation(AST _t) throws RecognitionException {
		
		MyNode listInstanciation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listInstanciation_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t49 = _t;
				MyNode tmp90_AST = null;
				MyNode tmp90_AST_in = null;
				tmp90_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp90_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp90_AST);
				ASTPair __currentAST49 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listInstanciation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST49;
				_t = __t49;
				_t = _t.getNextSibling();
				listInstanciation_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_LPAREN)) {
				paramRenameValuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listInstanciation_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listInstanciation_AST;
		_retTree = _t;
	}
	
	public final void listNames(AST _t) throws RecognitionException {
		
		MyNode listNames_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listNames_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t47 = _t;
				MyNode tmp91_AST = null;
				MyNode tmp91_AST_in = null;
				tmp91_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp91_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp91_AST);
				ASTPair __currentAST47 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listNames(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST47;
				_t = __t47;
				_t = _t.getNextSibling();
				listNames_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listNames_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listNames_AST;
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
				AST __t43 = _t;
				MyNode tmp92_AST = null;
				MyNode tmp92_AST_in = null;
				tmp92_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp92_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp92_AST);
				ASTPair __currentAST43 = currentAST.copy();
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
				currentAST = __currentAST43;
				_t = __t43;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listTypedIdentifier_AST;
		_retTree = _t;
	}
	
	public final void sets_declaration(AST _t) throws RecognitionException {
		
		MyNode sets_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sets_declaration_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t53 = _t;
				MyNode tmp93_AST = null;
				MyNode tmp93_AST_in = null;
				tmp93_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp93_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp93_AST);
				ASTPair __currentAST53 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				sets_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST53;
				_t = __t53;
				_t = _t.getNextSibling();
				sets_declaration_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t54 = _t;
				MyNode tmp94_AST = null;
				MyNode tmp94_AST_in = null;
				tmp94_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp94_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp94_AST);
				ASTPair __currentAST54 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				sets_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST54;
				_t = __t54;
				_t = _t.getNextSibling();
				sets_declaration_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_EQUAL:
			{
				set_declaration(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				sets_declaration_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = sets_declaration_AST;
		_retTree = _t;
	}
	
	public final void list_valuation(AST _t) throws RecognitionException {
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_valuation_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t82 = _t;
				MyNode tmp95_AST = null;
				MyNode tmp95_AST_in = null;
				tmp95_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp95_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp95_AST);
				ASTPair __currentAST82 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_valuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_valuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST82;
				_t = __t82;
				_t = _t.getNextSibling();
				list_valuation_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_EQUAL)) {
				set_valuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_valuation_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_valuation_AST;
		_retTree = _t;
	}
	
	public final void list_def(AST _t) throws RecognitionException {
		
		MyNode list_def_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_def_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_DEF)) {
				AST __t90 = _t;
				MyNode tmp96_AST = null;
				MyNode tmp96_AST_in = null;
				tmp96_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp96_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp96_AST);
				ASTPair __currentAST90 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				list_def(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_def(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST90;
				_t = __t90;
				_t = _t.getNextSibling();
				list_def_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_DOUBLE_EQUAL)) {
				definition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_def_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_def_AST;
		_retTree = _t;
	}
	
/**
 * the Generalised Substitution Language
 **/
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode instruction_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t107 = _t;
				MyNode tmp97_AST = null;
				MyNode tmp97_AST_in = null;
				tmp97_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp97_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp97_AST);
				ASTPair __currentAST107 = currentAST.copy();
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
				currentAST = __currentAST107;
				_t = __t107;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case SEQUENTIAL:
			{
				AST __t108 = _t;
				MyNode tmp98_AST = null;
				MyNode tmp98_AST_in = null;
				tmp98_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp98_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp98_AST);
				ASTPair __currentAST108 = currentAST.copy();
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
				currentAST = __currentAST108;
				_t = __t108;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_skip:
			{
				MyNode tmp99_AST = null;
				MyNode tmp99_AST_in = null;
				tmp99_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp99_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp99_AST);
				match(_t,LITERAL_skip);
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t109 = _t;
				MyNode tmp100_AST = null;
				MyNode tmp100_AST_in = null;
				tmp100_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp100_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp100_AST);
				ASTPair __currentAST109 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST109;
				_t = __t109;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PRE:
			{
				AST __t110 = _t;
				MyNode tmp101_AST = null;
				MyNode tmp101_AST_in = null;
				tmp101_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp101_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp101_AST);
				ASTPair __currentAST110 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST110;
				_t = __t110;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t111 = _t;
				MyNode tmp102_AST = null;
				MyNode tmp102_AST_in = null;
				tmp102_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp102_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp102_AST);
				ASTPair __currentAST111 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST111;
				_t = __t111;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IF:
			{
				AST __t112 = _t;
				MyNode tmp103_AST = null;
				MyNode tmp103_AST_in = null;
				tmp103_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp103_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp103_AST);
				ASTPair __currentAST112 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				branche_then(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop114:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop114;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				currentAST = __currentAST112;
				_t = __t112;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t116 = _t;
				MyNode tmp104_AST = null;
				MyNode tmp104_AST_in = null;
				tmp104_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp104_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp104_AST);
				ASTPair __currentAST116 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST116;
				_t = __t116;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CASE:
			{
				AST __t117 = _t;
				MyNode tmp105_AST = null;
				MyNode tmp105_AST_in = null;
				tmp105_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp105_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp105_AST);
				ASTPair __currentAST117 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				branche_either(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop119:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop119;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				currentAST = __currentAST117;
				_t = __t117;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ANY:
			{
				AST __t121 = _t;
				MyNode tmp106_AST = null;
				MyNode tmp106_AST_in = null;
				tmp106_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp106_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp106_AST);
				ASTPair __currentAST121 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST121;
				_t = __t121;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_LET:
			{
				AST __t122 = _t;
				MyNode tmp107_AST = null;
				MyNode tmp107_AST_in = null;
				tmp107_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp107_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp107_AST);
				ASTPair __currentAST122 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST122;
				_t = __t122;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t123 = _t;
				MyNode tmp108_AST = null;
				MyNode tmp108_AST_in = null;
				tmp108_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp108_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp108_AST);
				ASTPair __currentAST123 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop125:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop125;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				currentAST = __currentAST123;
				_t = __t123;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t127 = _t;
				MyNode tmp109_AST = null;
				MyNode tmp109_AST_in = null;
				tmp109_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp109_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp109_AST);
				ASTPair __currentAST127 = currentAST.copy();
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
				currentAST = __currentAST127;
				_t = __t127;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VAR:
			{
				AST __t128 = _t;
				MyNode tmp110_AST = null;
				MyNode tmp110_AST_in = null;
				tmp110_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp110_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp110_AST);
				ASTPair __currentAST128 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST128;
				_t = __t128;
				_t = _t.getNextSibling();
				instruction_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_LPAREN:
			case B_QUOTEIDENT:
			case B_OUT:
			case B_BECOME_ELEM:
			case B_SIMPLESUBST:
			case INSET:
			case FUNC_CALL_PARAM:
			case A_FUNC_CALL:
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = instruction_AST;
		_retTree = _t;
	}
	
	public final void listOperation(AST _t) throws RecognitionException {
		
		MyNode listOperation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperation_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t99 = _t;
				MyNode tmp111_AST = null;
				MyNode tmp111_AST_in = null;
				tmp111_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp111_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp111_AST);
				ASTPair __currentAST99 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listOperation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST99;
				_t = __t99;
				_t = _t.getNextSibling();
				listOperation_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==OP_DEF)) {
				operation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listOperation_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listOperation_AST;
		_retTree = _t;
	}
	
	public final void list_assertions(AST _t) throws RecognitionException {
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_assertions_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t97 = _t;
				MyNode tmp112_AST = null;
				MyNode tmp112_AST_in = null;
				tmp112_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp112_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp112_AST);
				ASTPair __currentAST97 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_assertions(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_assertions(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST97;
				_t = __t97;
				_t = _t.getNextSibling();
				list_assertions_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_assertions_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_assertions_AST;
		_retTree = _t;
	}
	
	public final void predicate_with_prime(AST _t) throws RecognitionException {
		
		MyNode predicate_with_prime_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_with_prime_AST = null;
		
		try {      // for error handling
			MyNode tmp113_AST = null;
			MyNode tmp113_AST_in = null;
			tmp113_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp113_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp113_AST);
			match(_t,LITERAL_tutu);
			_t = _t.getNextSibling();
			predicate_with_prime_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_with_prime_AST;
		_retTree = _t;
	}
	
/** 
 * La regle "typedIdentifier" permet de prendre en compte l'extension 
 * de typage explicite de B'
 **/
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode typedIdentifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t45 = _t;
				MyNode tmp114_AST = null;
				MyNode tmp114_AST_in = null;
				tmp114_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp114_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp114_AST);
				ASTPair __currentAST45 = currentAST.copy();
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
				currentAST = __currentAST45;
				_t = __t45;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = typedIdentifier_AST;
		_retTree = _t;
	}
	
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		MyNode name = null;
		MyNode name_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				MyNode name_AST_in = null;
				name_AST = (MyNode)astFactory.create(name);
				astFactory.addASTChild(currentAST, name_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					nameRenamed_AST = (MyNode)currentAST.root;
					
						ADefinition ll = listdefinition.lookupName(name.getText());
						if (ll != null)
						{
							def_body 	= (MyNode) astFactory.dupTree(ll.getBody()) ;
							def_param	= (MyNode) astFactory.dupTree(ll.getParam()) ;
					
							nameRenamed_AST 	= (MyNode) astFactory.dupTree(ll.getBody());
						modify          = true;  
						}
						else
							nameRenamed_AST 	= name_AST;
					
					currentAST.root = nameRenamed_AST;
					currentAST.child = nameRenamed_AST!=null &&nameRenamed_AST.getFirstChild()!=null ?
						nameRenamed_AST.getFirstChild() : nameRenamed_AST;
					currentAST.advanceChildToEnd();
				}
				nameRenamed_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t164 = _t;
				MyNode tmp115_AST = null;
				MyNode tmp115_AST_in = null;
				tmp115_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp115_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp115_AST);
				ASTPair __currentAST164 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp116_AST = null;
				MyNode tmp116_AST_in = null;
				tmp116_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp116_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp116_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				currentAST = __currentAST164;
				_t = __t164;
				_t = _t.getNextSibling();
				nameRenamed_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamed_AST;
		_retTree = _t;
	}
	
	public final void paramRenameValuation(AST _t) throws RecognitionException {
		
		MyNode paramRenameValuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode paramRenameValuation_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t51 = _t;
				MyNode tmp117_AST = null;
				MyNode tmp117_AST_in = null;
				tmp117_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp117_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp117_AST);
				ASTPair __currentAST51 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				paramRenameValuation(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_New_Predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST51;
				_t = __t51;
				_t = _t.getNextSibling();
				paramRenameValuation_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				paramRenameValuation_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = paramRenameValuation_AST;
		_retTree = _t;
	}
	
	public final void list_New_Predicate(AST _t) throws RecognitionException {
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_New_Predicate_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t159 = _t;
				MyNode tmp118_AST = null;
				MyNode tmp118_AST_in = null;
				tmp118_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp118_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp118_AST);
				ASTPair __currentAST159 = currentAST.copy();
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
				currentAST = __currentAST159;
				_t = __t159;
				_t = _t.getNextSibling();
				list_New_Predicate_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_New_Predicate_AST;
		_retTree = _t;
	}
	
	public final void set_declaration(AST _t) throws RecognitionException {
		
		MyNode set_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_declaration_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_EQUAL)) {
				AST __t56 = _t;
				MyNode tmp119_AST = null;
				MyNode tmp119_AST_in = null;
				tmp119_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp119_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp119_AST);
				ASTPair __currentAST56 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				MyNode tmp120_AST = null;
				MyNode tmp120_AST_in = null;
				tmp120_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp120_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp120_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST56;
				_t = __t56;
				_t = _t.getNextSibling();
				set_declaration_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp121_AST = null;
				MyNode tmp121_AST_in = null;
				tmp121_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp121_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp121_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				set_declaration_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = set_declaration_AST;
		_retTree = _t;
	}
	
	public final void valuation_set(AST _t) throws RecognitionException {
		
		MyNode valuation_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode valuation_set_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t61 = _t;
				MyNode tmp122_AST = null;
				MyNode tmp122_AST_in = null;
				tmp122_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp122_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp122_AST);
				ASTPair __currentAST61 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				list_couple(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST61;
				_t = __t61;
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGE:
			{
				AST __t62 = _t;
				MyNode tmp123_AST = null;
				MyNode tmp123_AST_in = null;
				tmp123_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp123_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp123_AST);
				ASTPair __currentAST62 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST62;
				_t = __t62;
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MULT:
			{
				AST __t63 = _t;
				MyNode tmp124_AST = null;
				MyNode tmp124_AST_in = null;
				tmp124_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp124_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp124_AST);
				ASTPair __currentAST63 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST63;
				_t = __t63;
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ADD:
			{
				AST __t64 = _t;
				MyNode tmp125_AST = null;
				MyNode tmp125_AST_in = null;
				tmp125_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp125_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp125_AST);
				ASTPair __currentAST64 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST64;
				_t = __t64;
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				AST __t65 = _t;
				MyNode tmp126_AST = null;
				MyNode tmp126_AST_in = null;
				tmp126_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp126_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp126_AST);
				ASTPair __currentAST65 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				valuation_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST65;
				_t = __t65;
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				MyNode tmp127_AST = null;
				MyNode tmp127_AST_in = null;
				tmp127_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp127_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp127_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				valuation_set_AST = (MyNode)currentAST.root;
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
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = valuation_set_AST;
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
				AST __t58 = _t;
				MyNode tmp128_AST = null;
				MyNode tmp128_AST_in = null;
				tmp128_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp128_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp128_AST);
				ASTPair __currentAST58 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST58;
				_t = __t58;
				_t = _t.getNextSibling();
				is_record_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t59 = _t;
				MyNode tmp129_AST = null;
				MyNode tmp129_AST_in = null;
				tmp129_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp129_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp129_AST);
				ASTPair __currentAST59 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST59;
				_t = __t59;
				_t = _t.getNextSibling();
				is_record_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = is_record_AST;
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
				AST __t67 = _t;
				MyNode tmp130_AST = null;
				MyNode tmp130_AST_in = null;
				tmp130_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp130_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp130_AST);
				ASTPair __currentAST67 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listrecord(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST67;
				_t = __t67;
				_t = _t.getNextSibling();
				listrecord_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listrecord_AST;
		_retTree = _t;
	}
	
	public final void list_couple(AST _t) throws RecognitionException {
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_couple_AST = null;
		MyNode t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t69 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				MyNode t1_AST_in = null;
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				ASTPair __currentAST69 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_couple(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_couple(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST69;
				_t = __t69;
				_t = _t.getNextSibling();
				list_couple_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_4.member(_t.getType()))) {
				couple(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_couple_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_couple_AST;
		_retTree = _t;
	}
	
	public final void a_set_value(AST _t) throws RecognitionException {
		
		MyNode a_set_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_set_value_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_IDENTIFIER:
			{
				MyNode tmp131_AST = null;
				MyNode tmp131_AST_in = null;
				tmp131_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp131_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp131_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_MINUS:
			{
				AST __t77 = _t;
				MyNode tmp132_AST = null;
				MyNode tmp132_AST_in = null;
				tmp132_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp132_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp132_AST);
				ASTPair __currentAST77 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				MyNode tmp133_AST = null;
				MyNode tmp133_AST_in = null;
				tmp133_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp133_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp133_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				currentAST = __currentAST77;
				_t = __t77;
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				AST __t78 = _t;
				MyNode tmp134_AST = null;
				MyNode tmp134_AST_in = null;
				tmp134_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp134_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp134_AST);
				ASTPair __currentAST78 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				MyNode tmp135_AST = null;
				MyNode tmp135_AST_in = null;
				tmp135_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp135_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp135_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				currentAST = __currentAST78;
				_t = __t78;
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp136_AST = null;
				MyNode tmp136_AST_in = null;
				tmp136_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp136_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp136_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp137_AST = null;
				MyNode tmp137_AST_in = null;
				tmp137_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp137_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp137_AST);
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp138_AST = null;
				MyNode tmp138_AST_in = null;
				tmp138_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp138_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp138_AST);
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = a_set_value_AST;
		_retTree = _t;
	}
	
/**
 * Les Types de base
 **/
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
				MyNode tmp139_AST = null;
				MyNode tmp139_AST_in = null;
				tmp139_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp139_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp139_AST);
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp140_AST = null;
				MyNode tmp140_AST_in = null;
				tmp140_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp140_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp140_AST);
				match(_t,87);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp141_AST = null;
				MyNode tmp141_AST_in = null;
				tmp141_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp141_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp141_AST);
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp142_AST = null;
				MyNode tmp142_AST_in = null;
				tmp142_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp142_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp142_AST);
				match(_t,89);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp143_AST = null;
				MyNode tmp143_AST_in = null;
				tmp143_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp143_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp143_AST);
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp144_AST = null;
				MyNode tmp144_AST_in = null;
				tmp144_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp144_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp144_AST);
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp145_AST = null;
				MyNode tmp145_AST_in = null;
				tmp145_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp145_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp145_AST);
				match(_t,92);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp146_AST = null;
				MyNode tmp146_AST_in = null;
				tmp146_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp146_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp146_AST);
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp147_AST = null;
				MyNode tmp147_AST_in = null;
				tmp147_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp147_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp147_AST);
				match(_t,94);
				_t = _t.getNextSibling();
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp148_AST = null;
				MyNode tmp148_AST_in = null;
				tmp148_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp148_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp148_AST);
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = basic_sets_AST;
		_retTree = _t;
	}
	
/**
 *	La regle "a_record" permet de definir le type de chaque champs
 **/
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_record_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t80 = _t;
				MyNode tmp149_AST = null;
				MyNode tmp149_AST_in = null;
				tmp149_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp149_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp149_AST);
				ASTPair __currentAST80 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				MyNode tmp150_AST = null;
				MyNode tmp150_AST_in = null;
				tmp150_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp150_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp150_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST80;
				_t = __t80;
				_t = _t.getNextSibling();
				a_record_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = a_record_AST;
		_retTree = _t;
	}
	
	public final void couple(AST _t) throws RecognitionException {
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode couple_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t71 = _t;
				MyNode tmp151_AST = null;
				MyNode tmp151_AST_in = null;
				tmp151_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp151_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp151_AST);
				ASTPair __currentAST71 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST71;
				_t = __t71;
				_t = _t.getNextSibling();
				couple_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LPAREN:
			{
				AST __t72 = _t;
				MyNode tmp152_AST = null;
				MyNode tmp152_AST_in = null;
				tmp152_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp152_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp152_AST);
				ASTPair __currentAST72 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				parent_couple(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST72;
				_t = __t72;
				_t = _t.getNextSibling();
				couple_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			case UNARY_MINUS:
			{
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				couple_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = couple_AST;
		_retTree = _t;
	}
	
	public final void parent_couple(AST _t) throws RecognitionException {
		
		MyNode parent_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parent_couple_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t74 = _t;
				MyNode tmp153_AST = null;
				MyNode tmp153_AST_in = null;
				tmp153_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp153_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp153_AST);
				ASTPair __currentAST74 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST74;
				_t = __t74;
				_t = _t.getNextSibling();
				parent_couple_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_MAPLET)) {
				AST __t75 = _t;
				MyNode tmp154_AST = null;
				MyNode tmp154_AST_in = null;
				tmp154_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp154_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp154_AST);
				ASTPair __currentAST75 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_set_value(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST75;
				_t = __t75;
				_t = _t.getNextSibling();
				parent_couple_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = parent_couple_AST;
		_retTree = _t;
	}
	
	public final void set_valuation(AST _t) throws RecognitionException {
		
		MyNode set_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_valuation_AST = null;
		
		try {      // for error handling
			AST __t84 = _t;
			MyNode tmp155_AST = null;
			MyNode tmp155_AST_in = null;
			tmp155_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp155_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp155_AST);
			ASTPair __currentAST84 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp156_AST = null;
			MyNode tmp156_AST_in = null;
			tmp156_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp156_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp156_AST);
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST84;
			_t = __t84;
			_t = _t.getNextSibling();
			set_valuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = set_valuation_AST;
		_retTree = _t;
	}
	
	public final void set_interval_value(AST _t) throws RecognitionException {
		
		MyNode set_interval_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_interval_value_AST = null;
		
		try {      // for error handling
			AST __t86 = _t;
			MyNode tmp157_AST = null;
			MyNode tmp157_AST_in = null;
			tmp157_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp157_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp157_AST);
			ASTPair __currentAST86 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp158_AST = null;
			MyNode tmp158_AST_in = null;
			tmp158_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp158_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp158_AST);
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			interval_declaration(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST86;
			_t = __t86;
			_t = _t.getNextSibling();
			set_interval_value_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = set_interval_value_AST;
		_retTree = _t;
	}
	
	public final void interval_declaration(AST _t) throws RecognitionException {
		
		MyNode interval_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode interval_declaration_AST = null;
		
		try {      // for error handling
			AST __t88 = _t;
			MyNode tmp159_AST = null;
			MyNode tmp159_AST_in = null;
			tmp159_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp159_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp159_AST);
			ASTPair __currentAST88 = currentAST.copy();
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
			currentAST = __currentAST88;
			_t = __t88;
			_t = _t.getNextSibling();
			interval_declaration_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = interval_declaration_AST;
		_retTree = _t;
	}
	
/**
 * Attention un cas, one case
 *	- Une definition
 * Le cas lie a l'acces a un fichier de definition a ete regle dans la phase de parsing
 **/
	public final void definition(AST _t) throws RecognitionException {
		
		MyNode definition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definition_AST = null;
		MyNode body_AST = null;
		MyNode body = null;
		
		try {      // for error handling
			AST __t92 = _t;
			MyNode tmp160_AST = null;
			MyNode tmp160_AST_in = null;
			tmp160_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp160_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp160_AST);
			ASTPair __currentAST92 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_DOUBLE_EQUAL);
			_t = _t.getFirstChild();
			paramName(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			body = _t==ASTNULL ? null : (MyNode)_t;
			formalText(_t);
			_t = _retTree;
			body_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ADefinition adef = new ADefinition(def_name.getText(), def_param, (MyNode) astFactory.dupTree(body_AST.getFirstChild()));
					listdefinition.Add_AM ( adef );
				
			}
			currentAST = __currentAST92;
			_t = __t92;
			_t = _t.getNextSibling();
			definition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = definition_AST;
		_retTree = _t;
	}
	
	public final void formalText(AST _t) throws RecognitionException {
		
		MyNode formalText_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode formalText_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==EXP_DEF)) {
				AST __t94 = _t;
				MyNode tmp161_AST = null;
				MyNode tmp161_AST_in = null;
				tmp161_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp161_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp161_AST);
				ASTPair __currentAST94 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,EXP_DEF);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST94;
				_t = __t94;
				_t = _t.getNextSibling();
				formalText_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==SUBST_DEF)) {
				AST __t95 = _t;
				MyNode tmp162_AST = null;
				MyNode tmp162_AST_in = null;
				tmp162_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp162_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp162_AST);
				ASTPair __currentAST95 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SUBST_DEF);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST95;
				_t = __t95;
				_t = _t.getNextSibling();
				formalText_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = formalText_AST;
		_retTree = _t;
	}
	
	public final void operation(AST _t) throws RecognitionException {
		
		MyNode operation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operation_AST = null;
		MyNode tt = null;
		MyNode tt_AST = null;
		MyNode pp_AST = null;
		MyNode pp = null;
		
		try {      // for error handling
			AST __t101 = _t;
			MyNode tmp163_AST = null;
			MyNode tmp163_AST_in = null;
			tmp163_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp163_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp163_AST);
			ASTPair __currentAST101 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				{
				AST __t104 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				MyNode tt_AST_in = null;
				tt_AST = (MyNode)astFactory.create(tt);
				astFactory.addASTChild(currentAST, tt_AST);
				ASTPair __currentAST104 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				pp = _t==ASTNULL ? null : (MyNode)_t;
				paramName(_t);
				_t = _retTree;
				pp_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						// Memorization pour introduction dans la table des symboles
						// ce n'est pas simple car les parametres de sortie sont introduit 
						// avant le nom de l'operation
					
						tt_AST.memorizeOpname( pp_AST.getOpname() ); 
					
				}
				currentAST = __currentAST104;
				_t = __t104;
				_t = _t.getNextSibling();
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_LPAREN)) {
				{
				paramName(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				}
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			currentAST = __currentAST101;
			_t = __t101;
			_t = _t.getNextSibling();
			operation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = operation_AST;
		_retTree = _t;
	}
	
	public final void branche_then(AST _t) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_then_AST = null;
		
		try {      // for error handling
			AST __t138 = _t;
			MyNode tmp164_AST = null;
			MyNode tmp164_AST_in = null;
			tmp164_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp164_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp164_AST);
			ASTPair __currentAST138 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST138;
			_t = __t138;
			_t = _t.getNextSibling();
			branche_then_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_then_AST;
		_retTree = _t;
	}
	
	public final void branche_elsif(AST _t) throws RecognitionException {
		
		MyNode branche_elsif_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_elsif_AST = null;
		
		try {      // for error handling
			AST __t142 = _t;
			MyNode tmp165_AST = null;
			MyNode tmp165_AST_in = null;
			tmp165_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp165_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp165_AST);
			ASTPair __currentAST142 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST142;
			_t = __t142;
			_t = _t.getNextSibling();
			branche_elsif_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_elsif_AST;
		_retTree = _t;
	}
	
	public final void branche_else(AST _t) throws RecognitionException {
		
		MyNode branche_else_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_else_AST = null;
		
		try {      // for error handling
			AST __t140 = _t;
			MyNode tmp166_AST = null;
			MyNode tmp166_AST_in = null;
			tmp166_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp166_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp166_AST);
			ASTPair __currentAST140 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST140;
			_t = __t140;
			_t = _t.getNextSibling();
			branche_else_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_else_AST;
		_retTree = _t;
	}
	
	public final void list_or(AST _t) throws RecognitionException {
		
		MyNode list_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_or_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t130 = _t;
				MyNode tmp167_AST = null;
				MyNode tmp167_AST_in = null;
				tmp167_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp167_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp167_AST);
				ASTPair __currentAST130 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				instruction(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST130;
				_t = __t130;
				_t = _t.getNextSibling();
				list_or_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_or_AST;
		_retTree = _t;
	}
	
	public final void branche_either(AST _t) throws RecognitionException {
		
		MyNode branche_either_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_either_AST = null;
		
		try {      // for error handling
			AST __t134 = _t;
			MyNode tmp168_AST = null;
			MyNode tmp168_AST_in = null;
			tmp168_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp168_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp168_AST);
			ASTPair __currentAST134 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST134;
			_t = __t134;
			_t = _t.getNextSibling();
			branche_either_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_either_AST;
		_retTree = _t;
	}
	
	public final void branche_or(AST _t) throws RecognitionException {
		
		MyNode branche_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_or_AST = null;
		
		try {      // for error handling
			AST __t136 = _t;
			MyNode tmp169_AST = null;
			MyNode tmp169_AST_in = null;
			tmp169_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp169_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp169_AST);
			ASTPair __currentAST136 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST136;
			_t = __t136;
			_t = _t.getNextSibling();
			branche_or_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_or_AST;
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
				AST __t149 = _t;
				MyNode tmp170_AST = null;
				MyNode tmp170_AST_in = null;
				tmp170_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp170_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp170_AST);
				ASTPair __currentAST149 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				list_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_equal(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST149;
				_t = __t149;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_equal_AST;
		_retTree = _t;
	}
	
	public final void branche_when(AST _t) throws RecognitionException {
		
		MyNode branche_when_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_when_AST = null;
		
		try {      // for error handling
			AST __t132 = _t;
			MyNode tmp171_AST = null;
			MyNode tmp171_AST_in = null;
			tmp171_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp171_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp171_AST);
			ASTPair __currentAST132 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			instruction(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST132;
			_t = __t132;
			_t = _t.getNextSibling();
			branche_when_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = branche_when_AST;
		_retTree = _t;
	}
	
	public final void variant_or_no(AST _t) throws RecognitionException {
		
		MyNode variant_or_no_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variant_or_no_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				AST __t144 = _t;
				MyNode tmp172_AST = null;
				MyNode tmp172_AST_in = null;
				tmp172_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp172_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp172_AST);
				ASTPair __currentAST144 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				AST __t145 = _t;
				MyNode tmp173_AST = null;
				MyNode tmp173_AST_in = null;
				tmp173_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp173_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp173_AST);
				ASTPair __currentAST145 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST145;
				_t = __t145;
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST144;
				_t = __t144;
				_t = _t.getNextSibling();
				variant_or_no_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t146 = _t;
				MyNode tmp174_AST = null;
				MyNode tmp174_AST_in = null;
				tmp174_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp174_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp174_AST);
				ASTPair __currentAST146 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				AST __t147 = _t;
				MyNode tmp175_AST = null;
				MyNode tmp175_AST_in = null;
				tmp175_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp175_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp175_AST);
				ASTPair __currentAST147 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST147;
				_t = __t147;
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST146;
				_t = __t146;
				_t = _t.getNextSibling();
				variant_or_no_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = variant_or_no_AST;
		_retTree = _t;
	}
	
/**
 * Cinq cas 
 *	soit 	a,b,c := f(), g(), h()
 *	soit	a,b,c <-- f(x)
 *	soit	a,b,c :( P )
 *	soit	a,b,c :: P
 *	soit	P.R (xx,yy)	ou sans parametre	P.R
 **/
	public final void simple_affect(AST _t) throws RecognitionException {
		
		MyNode simple_affect_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode simple_affect_AST = null;
		MyNode lf_AST = null;
		MyNode lf = null;
		MyNode lp_AST = null;
		MyNode lp = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t178 = _t;
				MyNode tmp176_AST = null;
				MyNode tmp176_AST_in = null;
				tmp176_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp176_AST_in = (MyNode)_t;
				ASTPair __currentAST178 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				if ( inputState.guessing==0 ) {
					
					System.out.println("SIMPLE_AFFECT >>");
					
				}
				lf = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				lf_AST = (MyNode)returnAST;
				lp = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				lp_AST = (MyNode)returnAST;
				if ( inputState.guessing==0 ) {
					simple_affect_AST = (MyNode)currentAST.root;
					
						MyNode a1 	= 	(MyNode) astFactory.dupTree(lf_AST) ;
						MyNode a2 	= 	(MyNode) astFactory.dupTree(lp_AST) ;
						MyNode temp 	= 	(MyNode)astFactory.make( (new ASTArray(3)).add(tmp176_AST).add(a1).add(a2));
					
						simple_affect_AST	=	affectation(temp);
					System.out.println("SIMPLE_AFFECT <<");
					
					currentAST.root = simple_affect_AST;
					currentAST.child = simple_affect_AST!=null &&simple_affect_AST.getFirstChild()!=null ?
						simple_affect_AST.getFirstChild() : simple_affect_AST;
					currentAST.advanceChildToEnd();
				}
				currentAST = __currentAST178;
				_t = __t178;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			{
				AST __t179 = _t;
				MyNode tmp177_AST = null;
				MyNode tmp177_AST_in = null;
				tmp177_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp177_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp177_AST);
				ASTPair __currentAST179 = currentAST.copy();
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
				currentAST = __currentAST179;
				_t = __t179;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case INSET:
			{
				AST __t180 = _t;
				MyNode tmp178_AST = null;
				MyNode tmp178_AST_in = null;
				tmp178_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp178_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp178_AST);
				ASTPair __currentAST180 = currentAST.copy();
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
				currentAST = __currentAST180;
				_t = __t180;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t181 = _t;
				MyNode tmp179_AST = null;
				MyNode tmp179_AST_in = null;
				tmp179_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp179_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp179_AST);
				ASTPair __currentAST181 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST181;
				_t = __t181;
				_t = _t.getNextSibling();
				simple_affect_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_LPAREN:
			case B_QUOTEIDENT:
			case FUNC_CALL_PARAM:
			case A_FUNC_CALL:
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
			AST __t151 = _t;
			MyNode tmp180_AST = null;
			MyNode tmp180_AST_in = null;
			tmp180_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp180_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp180_AST);
			ASTPair __currentAST151 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp181_AST = null;
			MyNode tmp181_AST_in = null;
			tmp181_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp181_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp181_AST);
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			predicate(_t);
			_t = _retTree;
			astFactory.addASTChild(currentAST, returnAST);
			currentAST = __currentAST151;
			_t = __t151;
			_t = _t.getNextSibling();
			an_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = an_equal_AST;
		_retTree = _t;
	}
	
/**
 * afin de prendre en compte 
 *	soit f (x,y,z)
 *	soit f (x) [z]
 *	soit f~(x) [z]
 *	soit f (x)~
 **/
	public final void func_call(AST _t) throws RecognitionException {
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode func_call_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t153 = _t;
				MyNode tmp182_AST = null;
				MyNode tmp182_AST_in = null;
				tmp182_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp182_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp182_AST);
				ASTPair __currentAST153 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST153;
				_t = __t153;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case APPLY_TO:
			{
				AST __t154 = _t;
				MyNode tmp183_AST = null;
				MyNode tmp183_AST_in = null;
				tmp183_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp183_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp183_AST);
				ASTPair __currentAST154 = currentAST.copy();
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
				currentAST = __currentAST154;
				_t = __t154;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LPAREN:
			{
				AST __t155 = _t;
				MyNode tmp184_AST = null;
				MyNode tmp184_AST_in = null;
				tmp184_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp184_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp184_AST);
				ASTPair __currentAST155 = currentAST.copy();
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
				currentAST = __currentAST155;
				_t = __t155;
				_t = _t.getNextSibling();
				func_call_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t156 = _t;
				MyNode tmp185_AST = null;
				MyNode tmp185_AST_in = null;
				tmp185_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp185_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp185_AST);
				ASTPair __currentAST156 = currentAST.copy();
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
				currentAST = __currentAST156;
				_t = __t156;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = func_call_AST;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = func_param_AST;
		_retTree = _t;
	}
	
	public final void new_predicate(AST _t) throws RecognitionException {
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_predicate_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t161 = _t;
				MyNode tmp186_AST = null;
				MyNode tmp186_AST_in = null;
				tmp186_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp186_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp186_AST);
				ASTPair __currentAST161 = currentAST.copy();
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
				currentAST = __currentAST161;
				_t = __t161;
				_t = _t.getNextSibling();
				new_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t162 = _t;
				MyNode tmp187_AST = null;
				MyNode tmp187_AST_in = null;
				tmp187_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp187_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp187_AST);
				ASTPair __currentAST162 = currentAST.copy();
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
				currentAST = __currentAST162;
				_t = __t162;
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
			case PRODSET:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
				AST __t166 = _t;
				MyNode tmp188_AST = null;
				MyNode tmp188_AST_in = null;
				tmp188_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp188_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp188_AST);
				ASTPair __currentAST166 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST166;
				_t = __t166;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamedDecorated_AST;
		_retTree = _t;
	}
	
	public final void listPredicate(AST _t) throws RecognitionException {
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listPredicate_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==ELEM_SET)) {
				AST __t168 = _t;
				MyNode tmp189_AST = null;
				MyNode tmp189_AST_in = null;
				tmp189_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp189_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp189_AST);
				ASTPair __currentAST168 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST168;
				_t = __t168;
				_t = _t.getNextSibling();
				listPredicate_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = listPredicate_AST;
		_retTree = _t;
	}
	
	public final void a_func_call(AST _t) throws RecognitionException {
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_func_call_AST = null;
		MyNode a_AST = null;
		MyNode a = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==A_FUNC_CALL)) {
				AST __t170 = _t;
				MyNode tmp190_AST = null;
				MyNode tmp190_AST_in = null;
				tmp190_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp190_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp190_AST);
				ASTPair __currentAST170 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,A_FUNC_CALL);
				_t = _t.getFirstChild();
				a = _t==ASTNULL ? null : (MyNode)_t;
				afc(_t);
				_t = _retTree;
				a_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
					//             if (modify = true)
					//             {
					//                 ## = #a;
					//                 modify = false;
					//             }
					
				}
				currentAST = __currentAST170;
				_t = __t170;
				_t = _t.getNextSibling();
				a_func_call_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_6.member(_t.getType()))) {
				afc(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				a_func_call_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = a_func_call_AST;
		_retTree = _t;
	}
	
	public final void afc(AST _t) throws RecognitionException {
		
		MyNode afc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode afc_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t172 = _t;
				MyNode tmp191_AST = null;
				MyNode tmp191_AST_in = null;
				tmp191_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp191_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp191_AST);
				ASTPair __currentAST172 = currentAST.copy();
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
				currentAST = __currentAST172;
				_t = __t172;
				_t = _t.getNextSibling();
				afc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LPAREN:
			{
				AST __t173 = _t;
				MyNode tmp192_AST = null;
				MyNode tmp192_AST_in = null;
				tmp192_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp192_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp192_AST);
				ASTPair __currentAST173 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST173;
				_t = __t173;
				_t = _t.getNextSibling();
				afc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t174 = _t;
				MyNode tmp193_AST = null;
				MyNode tmp193_AST_in = null;
				tmp193_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp193_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp193_AST);
				ASTPair __currentAST174 = currentAST.copy();
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
				currentAST = __currentAST174;
				_t = __t174;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = afc_AST;
		_retTree = _t;
	}
	
	public final void list_func_call(AST _t) throws RecognitionException {
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_func_call_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t176 = _t;
				MyNode tmp194_AST = null;
				MyNode tmp194_AST_in = null;
				tmp194_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp194_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp194_AST);
				ASTPair __currentAST176 = currentAST.copy();
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
				currentAST = __currentAST176;
				_t = __t176;
				_t = _t.getNextSibling();
				list_func_call_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_7.member(_t.getType()))) {
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_func_call_AST;
		_retTree = _t;
	}
	
	public final MyNode  affectation(AST _t) throws RecognitionException {
		MyNode res;
		
		MyNode affectation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode affectation_AST = null;
		MyNode lf_AST = null;
		MyNode lf = null;
		MyNode lp_AST = null;
		MyNode lp = null;
		
			res = null;
		
		
		try {      // for error handling
			AST __t183 = _t;
			MyNode tmp195_AST = null;
			MyNode tmp195_AST_in = null;
			tmp195_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp195_AST_in = (MyNode)_t;
			ASTPair __currentAST183 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_SIMPLESUBST);
			_t = _t.getFirstChild();
			lf = _t==ASTNULL ? null : (MyNode)_t;
			list_func_call(_t);
			_t = _retTree;
			lf_AST = (MyNode)returnAST;
			lp = _t==ASTNULL ? null : (MyNode)_t;
			predicate(_t);
			_t = _retTree;
			lp_AST = (MyNode)returnAST;
			if ( inputState.guessing==0 ) {
				
				System.out.println("AFFECTATION >>");
				
					getAPred(lp_AST);
					getAFunc(lf_AST);
				
				System.out.println("list variable  : "+ lf_AST.toStringList());
				System.out.println("list predicate : "+ lp_AST.toStringList());
				
				System.out.println("v1 : "+ T2.toStringList());
				System.out.println("p1 : "+ T4.toStringList());
				
					MyNode a1 	    = (MyNode) astFactory.dupTree(T2) ;
					MyNode a2 	    = (MyNode) astFactory.dupTree(T4) ;
					MyNode temp 	= (MyNode)astFactory.make( (new ASTArray(3)).add(tmp195_AST).add(a1).add(a2));
					save[save_cmp]	= (MyNode) astFactory.dupTree(temp) ;
				
				System.out.println("Il y a "+save_cmp+" et on a Temp ="+temp.toStringList());
				
					if (T1 == null && T3 == null)
					{
						res = (MyNode) astFactory.dupTree(save[save_cmp]);
					}
					else if (T1 != null && T3 != null)
					{
						MyNode a3 	    = (MyNode) astFactory.dupTree(T1) ;
						MyNode a4 	    = (MyNode) astFactory.dupTree(T3) ;
						MyNode tmp  	= (MyNode)astFactory.make( (new ASTArray(3)).add(tmp195_AST).add(a3).add(a4));
				
						save_cmp 	    = save_cmp+1;
						MyNode tmp1 	= affectation(tmp);
						save_cmp 	    = save_cmp-1;
				
						if (abstract_m == true)
							res 		= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(PARALLEL,"||")).add(tmp1).add(save[save_cmp]));
						else
							res 		= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(SEQUENTIAL,";")).add(tmp1).add(save[save_cmp]));
					}
					else
					{ 
						res = (MyNode) astFactory.dupTree(save[save_cmp]);
						System.out.println(errors.ListIncompleted(lf.getLineNum()));
					}
				
				System.out.println("AFFECTATION <<");
				
			}
			currentAST = __currentAST183;
			_t = __t183;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = affectation_AST;
		_retTree = _t;
		return res;
	}
	
	public final void getAFunc(AST _t) throws RecognitionException {
		
		MyNode getAFunc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode getAFunc_AST = null;
		MyNode a1_AST = null;
		MyNode a1 = null;
		MyNode r1_AST = null;
		MyNode r1 = null;
		MyNode a10_AST = null;
		MyNode a10 = null;
		MyNode r10_AST = null;
		MyNode r10 = null;
		MyNode a11_AST = null;
		MyNode a11 = null;
		MyNode r11_AST = null;
		MyNode r11 = null;
		MyNode a2_AST = null;
		MyNode a2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case ELEM_SET:
			{
				AST __t185 = _t;
				MyNode tmp196_AST = null;
				MyNode tmp196_AST_in = null;
				tmp196_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp196_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp196_AST);
				ASTPair __currentAST185 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				a1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r1 = _t==ASTNULL ? null : (MyNode)_t;
				a_func_call(_t);
				_t = _retTree;
				r1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = (MyNode) astFactory.dupTree(a1_AST);
						T2 = (MyNode) astFactory.dupTree(r1_AST);
					
				}
				currentAST = __currentAST185;
				_t = __t185;
				_t = _t.getNextSibling();
				getAFunc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t186 = _t;
				MyNode tmp197_AST = null;
				MyNode tmp197_AST_in = null;
				tmp197_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp197_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp197_AST);
				ASTPair __currentAST186 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a10 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				a10_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r10 = _t==ASTNULL ? null : (MyNode)_t;
				a_func_call(_t);
				_t = _retTree;
				r10_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = (MyNode) astFactory.dupTree(a10_AST);
						T2 = (MyNode) astFactory.dupTree(r10_AST);
					
				}
				currentAST = __currentAST186;
				_t = __t186;
				_t = _t.getNextSibling();
				getAFunc_AST = (MyNode)currentAST.root;
				break;
			}
			case LIST_VAR:
			{
				AST __t187 = _t;
				MyNode tmp198_AST = null;
				MyNode tmp198_AST_in = null;
				tmp198_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp198_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp198_AST);
				ASTPair __currentAST187 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				a11 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				a11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r11 = _t==ASTNULL ? null : (MyNode)_t;
				a_func_call(_t);
				_t = _retTree;
				r11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = (MyNode) astFactory.dupTree(a11_AST);
						T2 = (MyNode) astFactory.dupTree(r11_AST);
					
				}
				currentAST = __currentAST187;
				_t = __t187;
				_t = _t.getNextSibling();
				getAFunc_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_LPAREN:
			case B_QUOTEIDENT:
			case FUNC_CALL_PARAM:
			case A_FUNC_CALL:
			{
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				a_func_call(_t);
				_t = _retTree;
				a2_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = null;
						T2 = (MyNode) astFactory.dupTree(a2_AST);
					
				}
				getAFunc_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = getAFunc_AST;
		_retTree = _t;
	}
	
	public final void getAPred(AST _t) throws RecognitionException {
		
		MyNode getAPred_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode getAPred_AST = null;
		MyNode p1_AST = null;
		MyNode p1 = null;
		MyNode r1_AST = null;
		MyNode r1 = null;
		MyNode p11_AST = null;
		MyNode p11 = null;
		MyNode r11_AST = null;
		MyNode r11 = null;
		MyNode p3_AST = null;
		MyNode p3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_VAR)) {
				AST __t189 = _t;
				MyNode tmp199_AST = null;
				MyNode tmp199_AST_in = null;
				tmp199_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp199_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp199_AST);
				ASTPair __currentAST189 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				p1 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r1 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				r1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T3 = (MyNode) astFactory.dupTree(p1_AST);
						T4 = (MyNode) astFactory.dupTree(r1_AST);
					
				}
				currentAST = __currentAST189;
				_t = __t189;
				_t = _t.getNextSibling();
				getAPred_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_COMMA)) {
				AST __t190 = _t;
				MyNode tmp200_AST = null;
				MyNode tmp200_AST_in = null;
				tmp200_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp200_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp200_AST);
				ASTPair __currentAST190 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				p11 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r11 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				r11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T3 = (MyNode) astFactory.dupTree(p11_AST);
						T4 = (MyNode) astFactory.dupTree(r11_AST);
					
				}
				currentAST = __currentAST190;
				_t = __t190;
				_t = _t.getNextSibling();
				getAPred_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				p3 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				p3_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T3 = null;
						T4 = (MyNode) astFactory.dupTree(p3_AST);
					
				}
				getAPred_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = getAPred_AST;
		_retTree = _t;
	}
	
	public final void getATerm(AST _t) throws RecognitionException {
		
		MyNode getATerm_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode getATerm_AST = null;
		MyNode a1_AST = null;
		MyNode a1 = null;
		MyNode r1_AST = null;
		MyNode r1 = null;
		MyNode a11_AST = null;
		MyNode a11 = null;
		MyNode r11_AST = null;
		MyNode r11 = null;
		MyNode a2_AST = null;
		MyNode a2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_MULT)) {
				AST __t192 = _t;
				MyNode tmp201_AST = null;
				MyNode tmp201_AST_in = null;
				tmp201_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp201_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp201_AST);
				ASTPair __currentAST192 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				expression_typage(_t);
				_t = _retTree;
				a1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r1 = _t==ASTNULL ? null : (MyNode)_t;
				cset_description(_t);
				_t = _retTree;
				r1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = (MyNode) astFactory.dupTree(a1_AST);
						T2 = (MyNode) astFactory.dupTree(r1_AST);
					
				}
				currentAST = __currentAST192;
				_t = __t192;
				_t = _t.getNextSibling();
				getATerm_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==PRODSET)) {
				AST __t193 = _t;
				MyNode tmp202_AST = null;
				MyNode tmp202_AST_in = null;
				tmp202_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp202_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp202_AST);
				ASTPair __currentAST193 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				a11 = _t==ASTNULL ? null : (MyNode)_t;
				expression_typage(_t);
				_t = _retTree;
				a11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				r11 = _t==ASTNULL ? null : (MyNode)_t;
				cset_description(_t);
				_t = _retTree;
				r11_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = (MyNode) astFactory.dupTree(a11_AST);
						T2 = (MyNode) astFactory.dupTree(r11_AST);
					
				}
				currentAST = __currentAST193;
				_t = __t193;
				_t = _t.getNextSibling();
				getATerm_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_8.member(_t.getType()))) {
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				expression_typage(_t);
				_t = _retTree;
				a2_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						T1 = null;
						T2 = (MyNode) astFactory.dupTree(a2_AST);
					
				}
				getATerm_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = getATerm_AST;
		_retTree = _t;
	}
	
	public final void expression_typage(AST _t) throws RecognitionException {
		
		MyNode expression_typage_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_typage_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MULT:
			{
				AST __t197 = _t;
				MyNode tmp203_AST = null;
				MyNode tmp203_AST_in = null;
				tmp203_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp203_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp203_AST);
				ASTPair __currentAST197 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				expression_typage(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				cset_description(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST197;
				_t = __t197;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case PRODSET:
			{
				AST __t198 = _t;
				MyNode tmp204_AST = null;
				MyNode tmp204_AST_in = null;
				tmp204_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp204_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp204_AST);
				ASTPair __currentAST198 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				expression_typage(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				expression_typage(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST198;
				_t = __t198;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RELATION:
			{
				AST __t199 = _t;
				MyNode tmp205_AST = null;
				MyNode tmp205_AST_in = null;
				tmp205_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp205_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp205_AST);
				ASTPair __currentAST199 = currentAST.copy();
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
				currentAST = __currentAST199;
				_t = __t199;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL:
			{
				AST __t200 = _t;
				MyNode tmp206_AST = null;
				MyNode tmp206_AST_in = null;
				tmp206_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp206_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp206_AST);
				ASTPair __currentAST200 = currentAST.copy();
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
				currentAST = __currentAST200;
				_t = __t200;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL:
			{
				AST __t201 = _t;
				MyNode tmp207_AST = null;
				MyNode tmp207_AST_in = null;
				tmp207_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp207_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp207_AST);
				ASTPair __currentAST201 = currentAST.copy();
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
				currentAST = __currentAST201;
				_t = __t201;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t202 = _t;
				MyNode tmp208_AST = null;
				MyNode tmp208_AST_in = null;
				tmp208_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp208_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp208_AST);
				ASTPair __currentAST202 = currentAST.copy();
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
				currentAST = __currentAST202;
				_t = __t202;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t203 = _t;
				MyNode tmp209_AST = null;
				MyNode tmp209_AST_in = null;
				tmp209_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp209_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp209_AST);
				ASTPair __currentAST203 = currentAST.copy();
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
				currentAST = __currentAST203;
				_t = __t203;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t204 = _t;
				MyNode tmp210_AST = null;
				MyNode tmp210_AST_in = null;
				tmp210_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp210_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp210_AST);
				ASTPair __currentAST204 = currentAST.copy();
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
				currentAST = __currentAST204;
				_t = __t204;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t205 = _t;
				MyNode tmp211_AST = null;
				MyNode tmp211_AST_in = null;
				tmp211_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp211_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp211_AST);
				ASTPair __currentAST205 = currentAST.copy();
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
				currentAST = __currentAST205;
				_t = __t205;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BIJECTION:
			{
				AST __t206 = _t;
				MyNode tmp212_AST = null;
				MyNode tmp212_AST_in = null;
				tmp212_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp212_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp212_AST);
				ASTPair __currentAST206 = currentAST.copy();
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
				currentAST = __currentAST206;
				_t = __t206;
				_t = _t.getNextSibling();
				expression_typage_AST = (MyNode)currentAST.root;
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
				expression_typage_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = expression_typage_AST;
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
				AST __t266 = _t;
				MyNode tmp213_AST = null;
				MyNode tmp213_AST_in = null;
				tmp213_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp213_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp213_AST);
				ASTPair __currentAST266 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST266;
				_t = __t266;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t267 = _t;
				MyNode tmp214_AST = null;
				MyNode tmp214_AST_in = null;
				tmp214_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp214_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp214_AST);
				ASTPair __currentAST267 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST267;
				_t = __t267;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RANGE:
			{
				AST __t268 = _t;
				MyNode tmp215_AST = null;
				MyNode tmp215_AST_in = null;
				tmp215_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp215_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp215_AST);
				ASTPair __currentAST268 = currentAST.copy();
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
				currentAST = __currentAST268;
				_t = __t268;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp216_AST = null;
				MyNode tmp216_AST_in = null;
				tmp216_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp216_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp216_AST);
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t269 = _t;
				MyNode tmp217_AST = null;
				MyNode tmp217_AST_in = null;
				tmp217_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp217_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp217_AST);
				ASTPair __currentAST269 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST269;
				_t = __t269;
				_t = _t.getNextSibling();
				cset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SEQEMPTY:
			{
				MyNode tmp218_AST = null;
				MyNode tmp218_AST_in = null;
				tmp218_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp218_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp218_AST);
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = cset_description_AST;
		_retTree = _t;
	}
	
	public final MyNode  inset(AST _t) throws RecognitionException {
		MyNode res;
		
		MyNode inset_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode inset_AST = null;
		MyNode lf_AST = null;
		MyNode lf = null;
		MyNode lp_AST = null;
		MyNode lp = null;
		
			res = null;
		
		
		try {      // for error handling
			AST __t195 = _t;
			MyNode tmp219_AST = null;
			MyNode tmp219_AST_in = null;
			tmp219_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp219_AST_in = (MyNode)_t;
			ASTPair __currentAST195 = currentAST.copy();
			currentAST.root = currentAST.child;
			currentAST.child = null;
			match(_t,B_INSET);
			_t = _t.getFirstChild();
			lf = _t==ASTNULL ? null : (MyNode)_t;
			ml_var(_t);
			_t = _retTree;
			lf_AST = (MyNode)returnAST;
			lp = _t==ASTNULL ? null : (MyNode)_t;
			expression_typage(_t);
			_t = _retTree;
			lp_AST = (MyNode)returnAST;
			if ( inputState.guessing==0 ) {
				
				System.out.println("INSET >>");
				
					getAPred(lf_AST);
				
				System.out.println("list variable: "+ lf_AST.toStringList());
				//System.out.println("v1 : "+ #T3.toStringList());
				System.out.println("v2 : "+ T4.toStringList());
				
					MyNode a1 	= (MyNode) astFactory.dupTree(T4) ;
				
					getATerm(lp_AST);
				
				System.out.println("list predicat: "+lp_AST.toStringList());
				//System.out.println("p1 : "+ #T1.toStringList());
				System.out.println("p2 : "+ T2.toStringList());
				
					MyNode a2 	= (MyNode) astFactory.dupTree(T2) ;
				
					MyNode temp 	= (MyNode)astFactory.make( (new ASTArray(3)).add(tmp219_AST).add(a1).add(a2));
				
				System.out.println("Il y a "+save_cmp+"et on a Temp ="+temp.toStringList());
				
					save[save_cmp]	= (MyNode) astFactory.dupTree(temp) ;
				
					if (T1 == null && T3 == null)
					{
						res = (MyNode) astFactory.dupTree(save[save_cmp]);
					}
					else if (T1 != null && T3 != null)
					{
						MyNode a3 	    = (MyNode) astFactory.dupTree(T3) ;
						MyNode a4 	    = (MyNode) astFactory.dupTree(T1) ;
						MyNode tmp  	= (MyNode)astFactory.make( (new ASTArray(3)).add(tmp219_AST).add(a3).add(a4));
				
						save_cmp 	    = save_cmp+1;
						MyNode tmp1 	= inset(tmp);
						save_cmp 	    = save_cmp-1;
						res 		    = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add(tmp1).add(save[save_cmp]));
					}
					else
					{ 
						res = (MyNode) astFactory.dupTree(save[save_cmp]);
						System.out.println(errors.ListIncompleted(lf.getLineNum()));
					}
				
				System.out.println("INSET <<");
				
			}
			currentAST = __currentAST195;
			_t = __t195;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = inset_AST;
		_retTree = _t;
		return res;
	}
	
	public final void ml_var(AST _t) throws RecognitionException {
		
		MyNode ml_var_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode ml_var_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_VAR)) {
				AST __t264 = _t;
				MyNode tmp220_AST = null;
				MyNode tmp220_AST_in = null;
				tmp220_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp220_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp220_AST);
				ASTPair __currentAST264 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				ml_var(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				nameRenamedDecorated(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST264;
				_t = __t264;
				_t = _t.getNextSibling();
				ml_var_AST = (MyNode)currentAST.root;
			}
			else if (((_t.getType() >= B_IDENTIFIER && _t.getType() <= B_CPRED))) {
				nameRenamedDecorated(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				ml_var_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = ml_var_AST;
		_retTree = _t;
	}
	
	public final void cbasic_value(AST _t) throws RecognitionException {
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode cbasic_value_AST = null;
		MyNode pp_AST = null;
		MyNode pp = null;
		MyNode lp_AST = null;
		MyNode lp = null;
		
			boolean afaire = false;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				MyNode tmp221_AST = null;
				MyNode tmp221_AST_in = null;
				tmp221_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp221_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp221_AST);
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp222_AST = null;
				MyNode tmp222_AST_in = null;
				tmp222_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp222_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp222_AST);
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_TILDE:
			{
				AST __t274 = _t;
				MyNode tmp223_AST = null;
				MyNode tmp223_AST_in = null;
				tmp223_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp223_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp223_AST);
				ASTPair __currentAST274 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST274;
				_t = __t274;
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
				AST __t275 = _t;
				MyNode tmp224_AST = null;
				MyNode tmp224_AST_in = null;
				tmp224_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp224_AST_in = (MyNode)_t;
				ASTPair __currentAST275 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				pp = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				pp_AST = (MyNode)returnAST;
				lp = _t==ASTNULL ? null : (MyNode)_t;
				list_New_Predicate(_t);
				_t = _retTree;
				lp_AST = (MyNode)returnAST;
				if ( inputState.guessing==0 ) {
					cbasic_value_AST = (MyNode)currentAST.root;
					
							String      st = pp_AST.toString();
							ADefinition ll = listdefinition.lookupName(st);
					
							if (ll != null)
					{
								afaire = true;
					}
							else 
							{
								afaire = false;
							}
					//		if ((def == false) & (afaire == true))
					
							if ((afaire == true))
							{
					MyNode a1           = (MyNode) astFactory.dupTree(ll.getParam());
								MyNode a2           = (MyNode) astFactory.dupTree(lp_AST) ;
								MyNode temp 		= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_SIMPLESUBST,":=")).add(a1).add(a2));
					
								MyNode result 		= affectation(temp);
					
					MyNode body         = (MyNode) astFactory.dupTree(ll.getBody());
					MyNode tmp          = (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(SUBST_TO,"[[")).add(body).add(result));
					
					uu.goal(tmp);
					MyNode finalResult = (MyNode) uu.getAST();
					
					cbasic_value_AST		= finalResult;
							}
							else
					{
								cbasic_value_AST 		= (MyNode)astFactory.make( (new ASTArray(3)).add(tmp224_AST).add(pp_AST).add(lp_AST));
					}
					
					currentAST.root = cbasic_value_AST;
					currentAST.child = cbasic_value_AST!=null &&cbasic_value_AST.getFirstChild()!=null ?
						cbasic_value_AST.getFirstChild() : cbasic_value_AST;
					currentAST.advanceChildToEnd();
				}
				currentAST = __currentAST275;
				_t = __t275;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t276 = _t;
				MyNode tmp225_AST = null;
				MyNode tmp225_AST_in = null;
				tmp225_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp225_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp225_AST);
				ASTPair __currentAST276 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST276;
				_t = __t276;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t277 = _t;
				MyNode tmp226_AST = null;
				MyNode tmp226_AST_in = null;
				tmp226_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp226_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp226_AST);
				ASTPair __currentAST277 = currentAST.copy();
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
				currentAST = __currentAST277;
				_t = __t277;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case APPLY_TO:
			{
				AST __t278 = _t;
				MyNode tmp227_AST = null;
				MyNode tmp227_AST_in = null;
				tmp227_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp227_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp227_AST);
				ASTPair __currentAST278 = currentAST.copy();
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
				currentAST = __currentAST278;
				_t = __t278;
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp228_AST = null;
				MyNode tmp228_AST_in = null;
				tmp228_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp228_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp228_AST);
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				cbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp229_AST = null;
				MyNode tmp229_AST_in = null;
				tmp229_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp229_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp229_AST);
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
				AST __t271 = _t;
				MyNode tmp230_AST = null;
				MyNode tmp230_AST_in = null;
				tmp230_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp230_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp230_AST);
				ASTPair __currentAST271 = currentAST.copy();
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
				currentAST = __currentAST271;
				_t = __t271;
				_t = _t.getNextSibling();
				cvalue_set_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t272 = _t;
				MyNode tmp231_AST = null;
				MyNode tmp231_AST_in = null;
				tmp231_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp231_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp231_AST);
				ASTPair __currentAST272 = currentAST.copy();
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
				currentAST = __currentAST272;
				_t = __t272;
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
			case PRODSET:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = cvalue_set_AST;
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
				AST __t285 = _t;
				MyNode tmp232_AST = null;
				MyNode tmp232_AST_in = null;
				tmp232_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp232_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp232_AST);
				ASTPair __currentAST285 = currentAST.copy();
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
				currentAST = __currentAST285;
				_t = __t285;
				_t = _t.getNextSibling();
				quantification_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t286 = _t;
				MyNode tmp233_AST = null;
				MyNode tmp233_AST_in = null;
				tmp233_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp233_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp233_AST);
				ASTPair __currentAST286 = currentAST.copy();
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
				currentAST = __currentAST286;
				_t = __t286;
				_t = _t.getNextSibling();
				quantification_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
				AST __t288 = _t;
				MyNode tmp234_AST = null;
				MyNode tmp234_AST_in = null;
				tmp234_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp234_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp234_AST);
				ASTPair __currentAST288 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST288;
				_t = __t288;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PI:
			{
				AST __t289 = _t;
				MyNode tmp235_AST = null;
				MyNode tmp235_AST_in = null;
				tmp235_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp235_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp235_AST);
				ASTPair __currentAST289 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST289;
				_t = __t289;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t290 = _t;
				MyNode tmp236_AST = null;
				MyNode tmp236_AST_in = null;
				tmp236_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp236_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp236_AST);
				ASTPair __currentAST290 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST290;
				_t = __t290;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_UNION:
			{
				AST __t291 = _t;
				MyNode tmp237_AST = null;
				MyNode tmp237_AST_in = null;
				tmp237_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp237_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp237_AST);
				ASTPair __currentAST291 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST291;
				_t = __t291;
				_t = _t.getNextSibling();
				q_lambda_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTER:
			{
				AST __t292 = _t;
				MyNode tmp238_AST = null;
				MyNode tmp238_AST_in = null;
				tmp238_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp238_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp238_AST);
				ASTPair __currentAST292 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST292;
				_t = __t292;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
				AST __t296 = _t;
				MyNode tmp239_AST = null;
				MyNode tmp239_AST_in = null;
				tmp239_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp239_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp239_AST);
				ASTPair __currentAST296 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST296;
				_t = __t296;
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
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
				AST __t280 = _t;
				MyNode tmp240_AST = null;
				MyNode tmp240_AST_in = null;
				tmp240_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp240_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp240_AST);
				ASTPair __currentAST280 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST280;
				_t = __t280;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PARALLEL:
			{
				AST __t281 = _t;
				MyNode tmp241_AST = null;
				MyNode tmp241_AST_in = null;
				tmp241_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp241_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp241_AST);
				ASTPair __currentAST281 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST281;
				_t = __t281;
				_t = _t.getNextSibling();
				pred_func_composition_AST = (MyNode)currentAST.root;
				break;
			}
			case B_COMMA:
			{
				AST __t282 = _t;
				MyNode tmp242_AST = null;
				MyNode tmp242_AST_in = null;
				tmp242_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp242_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp242_AST);
				ASTPair __currentAST282 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				pred_func_composition(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST282;
				_t = __t282;
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
			case PRODSET:
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
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
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = pred_func_composition_AST;
		_retTree = _t;
	}
	
	public final void q_operande(AST _t) throws RecognitionException {
		
		MyNode q_operande_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_operande_AST = null;
		
		try {      // for error handling
			AST __t294 = _t;
			MyNode tmp243_AST = null;
			MyNode tmp243_AST_in = null;
			tmp243_AST = (MyNode)astFactory.create((MyNode)_t);
			tmp243_AST_in = (MyNode)_t;
			astFactory.addASTChild(currentAST, tmp243_AST);
			ASTPair __currentAST294 = currentAST.copy();
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
			currentAST = __currentAST294;
			_t = __t294;
			_t = _t.getNextSibling();
			q_operande_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = q_operande_AST;
		_retTree = _t;
	}
	
	public final void list_identifier(AST _t) throws RecognitionException {
		
		MyNode list_identifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_identifier_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t298 = _t;
				MyNode tmp244_AST = null;
				MyNode tmp244_AST_in = null;
				tmp244_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp244_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp244_AST);
				ASTPair __currentAST298 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_identifier(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST298;
				_t = __t298;
				_t = _t.getNextSibling();
				list_identifier_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp245_AST = null;
				MyNode tmp245_AST_in = null;
				tmp245_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp245_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp245_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				list_identifier_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_identifier_AST;
		_retTree = _t;
	}
	
	public final void list_var_bis(AST _t) throws RecognitionException {
		
		MyNode list_var_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_var_bis_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t300 = _t;
				MyNode tmp246_AST = null;
				MyNode tmp246_AST_in = null;
				tmp246_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp246_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp246_AST);
				ASTPair __currentAST300 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier_bis(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST300;
				_t = __t300;
				_t = _t.getNextSibling();
				list_var_bis_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				list_identifier_bis(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_var_bis_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_var_bis_AST;
		_retTree = _t;
	}
	
	public final void list_identifier_bis(AST _t) throws RecognitionException {
		
		MyNode list_identifier_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_identifier_bis_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t302 = _t;
				MyNode tmp247_AST = null;
				MyNode tmp247_AST_in = null;
				tmp247_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp247_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp247_AST);
				ASTPair __currentAST302 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier_bis(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				list_identifier_bis(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST302;
				_t = __t302;
				_t = _t.getNextSibling();
				list_identifier_bis_AST = (MyNode)currentAST.root;
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp248_AST = null;
				MyNode tmp248_AST_in = null;
				tmp248_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp248_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp248_AST);
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				list_identifier_bis_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = list_identifier_bis_AST;
		_retTree = _t;
	}
	
	public final MyNode  normalize(AST _t) throws RecognitionException {
		MyNode res;
		
		MyNode normalize_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode normalize_AST = null;
		MyNode pp1_AST = null;
		MyNode pp1 = null;
		MyNode lf_AST = null;
		MyNode lf = null;
		MyNode lp_AST = null;
		MyNode lp = null;
		MyNode pp2_AST = null;
		MyNode pp2 = null;
		MyNode lf1_AST = null;
		MyNode lf1 = null;
		MyNode lf2_AST = null;
		MyNode lf2 = null;
		MyNode pp3_AST = null;
		MyNode pp3 = null;
		MyNode lf3_AST = null;
		MyNode lf3 = null;
		MyNode lf4_AST = null;
		MyNode lf4 = null;
		
			res = null;
		
		
		try {      // for error handling
			boolean synPredMatched305 = false;
			if (_t==null) _t=ASTNULL;
			if (((_t.getType()==B_LPAREN))) {
				AST __t305 = _t;
				synPredMatched305 = true;
				inputState.guessing++;
				try {
					{
					match(_t,B_LPAREN);
					_t = _t.getNextSibling();
					predicate(_t);
					_t = _retTree;
					match(_t,B_SIMPLESUBST);
					_t = _t.getNextSibling();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched305 = false;
				}
				_t = __t305;
inputState.guessing--;
			}
			if ( synPredMatched305 ) {
				AST __t306 = _t;
				MyNode tmp249_AST = null;
				MyNode tmp249_AST_in = null;
				tmp249_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp249_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp249_AST);
				ASTPair __currentAST306 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				pp1 = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				pp1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				AST __t307 = _t;
				MyNode tmp250_AST = null;
				MyNode tmp250_AST_in = null;
				tmp250_AST = (MyNode)astFactory.create((MyNode)_t);
				tmp250_AST_in = (MyNode)_t;
				astFactory.addASTChild(currentAST, tmp250_AST);
				ASTPair __currentAST307 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				lf = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				lf_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				lp = _t==ASTNULL ? null : (MyNode)_t;
				predicate(_t);
				_t = _retTree;
				lp_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST307;
				_t = __t307;
				_t = _t.getNextSibling();
				currentAST = __currentAST306;
				_t = __t306;
				_t = _t.getNextSibling();
				normalize_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched309 = false;
				if (_t==null) _t=ASTNULL;
				if (((_t.getType()==B_LPAREN))) {
					AST __t309 = _t;
					synPredMatched309 = true;
					inputState.guessing++;
					try {
						{
						match(_t,B_LPAREN);
						_t = _t.getNextSibling();
						predicate(_t);
						_t = _retTree;
						match(_t,PARALLEL);
						_t = _t.getNextSibling();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched309 = false;
					}
					_t = __t309;
inputState.guessing--;
				}
				if ( synPredMatched309 ) {
					AST __t310 = _t;
					MyNode tmp251_AST = null;
					MyNode tmp251_AST_in = null;
					tmp251_AST = (MyNode)astFactory.create((MyNode)_t);
					tmp251_AST_in = (MyNode)_t;
					astFactory.addASTChild(currentAST, tmp251_AST);
					ASTPair __currentAST310 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,B_LPAREN);
					_t = _t.getFirstChild();
					pp2 = _t==ASTNULL ? null : (MyNode)_t;
					predicate(_t);
					_t = _retTree;
					pp2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					AST __t311 = _t;
					MyNode tmp252_AST = null;
					MyNode tmp252_AST_in = null;
					tmp252_AST = (MyNode)astFactory.create((MyNode)_t);
					tmp252_AST_in = (MyNode)_t;
					astFactory.addASTChild(currentAST, tmp252_AST);
					ASTPair __currentAST311 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,PARALLEL);
					_t = _t.getFirstChild();
					lf1 = _t==ASTNULL ? null : (MyNode)_t;
					instruction(_t);
					_t = _retTree;
					lf1_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					lf2 = _t==ASTNULL ? null : (MyNode)_t;
					instruction(_t);
					_t = _retTree;
					lf2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					currentAST = __currentAST311;
					_t = __t311;
					_t = _t.getNextSibling();
					currentAST = __currentAST310;
					_t = __t310;
					_t = _t.getNextSibling();
					normalize_AST = (MyNode)currentAST.root;
				}
				else {
					boolean synPredMatched313 = false;
					if (_t==null) _t=ASTNULL;
					if (((_t.getType()==B_LPAREN))) {
						AST __t313 = _t;
						synPredMatched313 = true;
						inputState.guessing++;
						try {
							{
							match(_t,B_LPAREN);
							_t = _t.getNextSibling();
							predicate(_t);
							_t = _retTree;
							match(_t,SEQUENTIAL);
							_t = _t.getNextSibling();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched313 = false;
						}
						_t = __t313;
inputState.guessing--;
					}
					if ( synPredMatched313 ) {
						AST __t314 = _t;
						MyNode tmp253_AST = null;
						MyNode tmp253_AST_in = null;
						tmp253_AST = (MyNode)astFactory.create((MyNode)_t);
						tmp253_AST_in = (MyNode)_t;
						astFactory.addASTChild(currentAST, tmp253_AST);
						ASTPair __currentAST314 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,B_LPAREN);
						_t = _t.getFirstChild();
						pp3 = _t==ASTNULL ? null : (MyNode)_t;
						predicate(_t);
						_t = _retTree;
						pp3_AST = (MyNode)returnAST;
						astFactory.addASTChild(currentAST, returnAST);
						AST __t315 = _t;
						MyNode tmp254_AST = null;
						MyNode tmp254_AST_in = null;
						tmp254_AST = (MyNode)astFactory.create((MyNode)_t);
						tmp254_AST_in = (MyNode)_t;
						astFactory.addASTChild(currentAST, tmp254_AST);
						ASTPair __currentAST315 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,SEQUENTIAL);
						_t = _t.getFirstChild();
						lf3 = _t==ASTNULL ? null : (MyNode)_t;
						instruction(_t);
						_t = _retTree;
						lf3_AST = (MyNode)returnAST;
						astFactory.addASTChild(currentAST, returnAST);
						lf4 = _t==ASTNULL ? null : (MyNode)_t;
						instruction(_t);
						_t = _retTree;
						lf4_AST = (MyNode)returnAST;
						astFactory.addASTChild(currentAST, returnAST);
						currentAST = __currentAST315;
						_t = __t315;
						_t = _t.getNextSibling();
						currentAST = __currentAST314;
						_t = __t314;
						_t = _t.getNextSibling();
						normalize_AST = (MyNode)currentAST.root;
					}
					else {
						throw new NoViableAltException(_t);
					}
					}}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						reportError(ex);
						if (_t!=null) {_t = _t.getNextSibling();}
					} else {
					  throw ex;
					}
				}
				returnAST = normalize_AST;
				_retTree = _t;
				return res;
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
				"\"SYSTEM\"",
				"\"EVENTS\"",
				"\"MODALITIES\"",
				"B_LEADSTO",
				"\"DYNAMICS\"",
				"B_PREC",
				"\"tutu\"",
				"A_FUNC_CALL"
			};
			
			private static final long[] mk_tokenSet_0() {
				long[] data = { 0L, 0L, -5764607522967126017L, 0L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
			private static final long[] mk_tokenSet_1() {
				long[] data = { -3530822108395339808L, 246144575725599L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
			private static final long[] mk_tokenSet_2() {
				long[] data = { -3530822108395339808L, 246144575731743L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
			private static final long[] mk_tokenSet_3() {
				long[] data = { -3530822108395339808L, 246282014679071L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
			private static final long[] mk_tokenSet_4() {
				long[] data = { -9214224099843244000L, 4294967305L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
			private static final long[] mk_tokenSet_5() {
				long[] data = new long[8];
				data[0]=268435552L;
				data[1]=117093590311632912L;
				data[2]=33366376252964864L;
				data[3]=4L;
				return data;
			}
			public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
			private static final long[] mk_tokenSet_6() {
				long[] data = { 268435552L, 16L, 9007199254740992L, 0L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
			private static final long[] mk_tokenSet_7() {
				long[] data = new long[8];
				data[0]=268435552L;
				data[1]=16L;
				data[2]=9007199254740992L;
				data[3]=4L;
				return data;
			}
			public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
			private static final long[] mk_tokenSet_8() {
				long[] data = { -3548554757184421664L, 141117590855711L, 0L, 0L};
				return data;
			}
			public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
			}
			
