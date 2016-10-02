// $ANTLR 2.7.6 (2005-12-22): "ExpressionTreeWalker.g" -> "ExpressionTreeWalker.java"$

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


public class ExpressionTreeWalker extends antlr.TreeParser       implements ExpressionTreeWalkerTokenTypes
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

public ExpressionTreeWalker() {
	tokenNames = _tokenNames;
}

	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t01 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t2 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t1.getText()));
					printToString(out.Separator("("));
				
				listrecord(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t2;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t3 = _t;
				t01 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t01.getText()));
					printToString(out.Separator("("));
				
				listrecord(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t3;
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
	
	public final void listrecord(AST _t) throws RecognitionException {
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t5 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				
					printToString("\n"+out.Separator(","));
				
				a_record(_t);
				_t = _retTree;
				_t = __t5;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
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
	
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t7 = _t;
				MyNode tmp1_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name);
					printToString(out.Separator(":"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t7;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
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
	
/** 
 *  PREDICATE 
 **/
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		MyNode t51 = null;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode t8 = null;
		MyNode t9 = null;
		MyNode t10 = null;
		MyNode t11 = null;
		MyNode t12 = null;
		MyNode t13 = null;
		MyNode t14 = null;
		MyNode t15 = null;
		MyNode t16 = null;
		MyNode t17 = null;
		MyNode t18 = null;
		MyNode t19 = null;
		MyNode t20 = null;
		MyNode t21 = null;
		MyNode t22 = null;
		MyNode t23 = null;
		MyNode t24 = null;
		MyNode t25 = null;
		MyNode t26 = null;
		MyNode t27 = null;
		MyNode t28 = null;
		MyNode t29 = null;
		MyNode t30 = null;
		MyNode t31 = null;
		MyNode t32 = null;
		MyNode t33 = null;
		MyNode t34 = null;
		MyNode t35 = null;
		MyNode t36 = null;
		MyNode t37 = null;
		MyNode t38 = null;
		MyNode t39 = null;
		MyNode t40 = null;
		MyNode t41 = null;
		MyNode t42 = null;
		MyNode t43 = null;
		MyNode t44 = null;
		MyNode t45 = null;
		MyNode t46 = null;
		MyNode t48 = null;
		MyNode t49 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_AND:
			{
				AST __t31 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				
				//	index.Add();
				
				predicate(_t);
				_t = _retTree;
				
				//	index.Retract();
					printToStringln("");	
				//	index.Add();
					printToString(out.Separator(t1.getText()));
				
				
				predicate(_t);
				_t = _retTree;
				_t = __t31;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t32 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t32;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t33 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t33;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t34 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t4.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t34;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t35 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t5.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t35;
				_t = _t.getNextSibling();
				break;
			}
			case PRODSET:
			{
				AST __t36 = _t;
				t51 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t51.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t36;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t37 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t6.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t37;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t38 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t7.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t38;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t39 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t8.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t39;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t40 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t9.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t40;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t41 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t10.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t41;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t42 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t11.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t42;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t43 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t12.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t43;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t44 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t13.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t44;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t45 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t14.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t45;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t46 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t15.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t46;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t47 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t16.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t47;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t48 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t17.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t48;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t49 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t18.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t49;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t50 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t19.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t50;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t51 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t20.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t51;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t52 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t21.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t52;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t53 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t22.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t53;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t54 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t23.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t54;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t55 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t24.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t55;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t56 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t25.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t56;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t57 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t26.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t57;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t58 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t27.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t58;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t59 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t28.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t59;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t60 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t29.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t60;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t61 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t30.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t61;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t62 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t31.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t62;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t63 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t32.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t63;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t64 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t33.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t64;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t65 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t34.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t65;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t66 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t35.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t66;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t67 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t36.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t67;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t68 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t37.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t68;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t69 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t38.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t69;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t70 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t39.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t70;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t71 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t40.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t71;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t72 = _t;
				t41 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t41.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t72;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t73 = _t;
				t42 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t42.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t73;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t74 = _t;
				t43 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t43.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t74;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t75 = _t;
				t44 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t44.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t75;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t76 = _t;
				t45 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t45.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t76;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t77 = _t;
				t46 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t46.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t77;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t78 = _t;
				t48 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t48.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t78;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t79 = _t;
				t49 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t49.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t79;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t80 = _t;
				MyNode tmp2_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("not"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t80;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t81 = _t;
				MyNode tmp3_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("ran"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t81;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t82 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("dom"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t82;
				_t = _t.getNextSibling();
				break;
			}
			case B_MIN:
			{
				AST __t83 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_MIN);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("min"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t83;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAX:
			{
				AST __t84 = _t;
				MyNode tmp6_AST_in = (MyNode)_t;
				match(_t,B_MAX);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("max"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t84;
				_t = _t.getNextSibling();
				break;
			}
			case B_CARD:
			{
				AST __t85 = _t;
				MyNode tmp7_AST_in = (MyNode)_t;
				match(_t,B_CARD);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("card"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t85;
				_t = _t.getNextSibling();
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
	
	public final void list_New_Predicate(AST _t) throws RecognitionException {
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t9 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				new_predicate(_t);
				_t = _retTree;
				_t = __t9;
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
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t11 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t11;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t12 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t12;
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
	
/**
 * Ne pas utiliser de fonction d'impression pour le renommage ....
 **/
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode n1 = null;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(n1);
				
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t14 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(tt.getText());
				
				nameRenamed(_t);
				_t = _retTree;
				_t = __t14;
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
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t16 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				_t = __t16;
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
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_TILDE)) {
				AST __t18 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				_t = __t18;
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
		MyNode tt = null;
		MyNode n1 = null;
		MyNode n2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t20 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(n1);
				
				_t = __t20;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				n2 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(n2);
				
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
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==ELEM_SET)) {
				AST __t22 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t22;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
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
	
	public final void a_func_call(AST _t) throws RecognitionException {
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			afc(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void afc(AST _t) throws RecognitionException {
		
		MyNode afc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t25 = _t;
				MyNode tmp8_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t25;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t26 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				afc(_t);
				_t = _retTree;
				_t = __t26;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t27 = _t;
				MyNode tmp9_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t27;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				nameRenamed(_t);
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
	
	public final void list_func_call(AST _t) throws RecognitionException {
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t29 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				list_func_call(_t);
				_t = _retTree;
				_t = __t29;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				a_func_call(_t);
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
	
	public final void cset_description(AST _t) throws RecognitionException {
		
		MyNode cset_description_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t1 = null;
		
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
				AST __t87 = _t;
				MyNode tmp10_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("bool"));
					printToString(out.Separator("("));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t87;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t88 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("["));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("]"));
				
				_t = __t88;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t89 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t89;
				_t = _t.getNextSibling();
				break;
			}
			case B_EMPTYSET:
			{
				t3 = (MyNode)_t;
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				
					printToString(out.Separator(t3.getText()));
				
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t90 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("{"));
				
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator("}"));
				
				_t = __t90;
				_t = _t.getNextSibling();
				break;
			}
			case B_SEQEMPTY:
			{
				t1 = (MyNode)_t;
				match(_t,B_SEQEMPTY);
				_t = _t.getNextSibling();
				
					printToString(out.Separator(t1.getText()));
				
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
	
	public final void basic_sets(AST _t) throws RecognitionException {
		
		MyNode basic_sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode t8 = null;
		MyNode t9 = null;
		MyNode t10 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_INT:
			{
				t1 = (MyNode)_t;
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t1.getText()));
				break;
			}
			case 87:
			{
				t2 = (MyNode)_t;
				match(_t,87);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t2.getText()));
				break;
			}
			case LITERAL_INTEGER:
			{
				t3 = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t3.getText()));
				break;
			}
			case 89:
			{
				t4 = (MyNode)_t;
				match(_t,89);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t4.getText()));
				break;
			}
			case LITERAL_BOOL:
			{
				t5 = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t5.getText()));
				break;
			}
			case LITERAL_NAT:
			{
				t6 = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t6.getText()));
				break;
			}
			case 92:
			{
				t7 = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t7.getText()));
				break;
			}
			case LITERAL_NATURAL:
			{
				t8 = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t8.getText()));
				break;
			}
			case 94:
			{
				t9 = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t9.getText()));
				break;
			}
			case LITERAL_STRING:
			{
				t10 = (MyNode)_t;
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				printToString(out.KeyWord(t10.getText()));
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
				
					printToString("\""+t1+"\"");
				
				break;
			}
			case B_NUMBER:
			{
				t2 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					printToString(out.Separator(t2.getText()));
				
				break;
			}
			case B_TILDE:
			{
				AST __t97 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("~"));
				
				_t = __t97;
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
				AST __t98 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t98;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t99 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("("));
				
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t99;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t100 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("'"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t100;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t101 = _t;
				MyNode tmp16_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("["));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("]"));
				
				_t = __t101;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					printToString(out.Separator("TRUE"));
				
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					printToString(out.Separator("FALSE"));
				
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
		MyNode t3 = null;
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t92 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t92;
				_t = _t.getNextSibling();
				break;
			}
			case ELEM_SET:
			{
				AST __t93 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				cvalue_set(_t);
				_t = _retTree;
				_t = __t93;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t94 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t94;
				_t = _t.getNextSibling();
				break;
			}
			default:
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LIST_VAR)) {
					AST __t95 = _t;
					t4 = _t==ASTNULL ? null :(MyNode)_t;
					match(_t,LIST_VAR);
					_t = _t.getFirstChild();
					cvalue_set(_t);
					_t = _retTree;
					
						printToString(out.Separator(t4.getText()));
					
					predicate(_t);
					_t = _retTree;
					_t = __t95;
					_t = _t.getNextSibling();
				}
				else if ((_tokenSet_1.member(_t.getType()))) {
					predicate(_t);
					_t = _retTree;
				}
			else {
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
		MyNode t70 = null;
		MyNode t71 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t108 = _t;
				t70 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t70.getText()));
				
				list_var(_t);
				_t = _retTree;
				
					printToString(out.Separator(".("));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t108;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t109 = _t;
				t71 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t71.getText()));
				
				list_var(_t);
				_t = _retTree;
				
					printToString(out.Separator(".("));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t109;
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
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t111 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t1.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t111;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t112 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t2.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t112;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t113 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t3.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t113;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t114 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t4.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t114;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t115 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t5.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t115;
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
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t119 = _t;
				MyNode tmp19_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("("));
				
				list_identifier(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t119;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				
					printToString(out.Separator(" "));
				
				list_identifier(_t);
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
	
	public final void pred_func_composition(AST _t) throws RecognitionException {
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t103 = _t;
				MyNode tmp20_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(";"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t103;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t104 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t104;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t105 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t105;
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
		MyNode t1 = null;
		
		try {      // for error handling
			AST __t117 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			
				printToString(out.Separator(".("));
			
			predicate(_t);
			_t = _retTree;
			
				printToString(out.Separator(t1.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToString(out.Separator(")"));
			
			_t = __t117;
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
		"\"POST\"",
		"B_BEGIN_POST",
		"a comment"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -3530822108395339808L, 246282014679071L, 0L, 0L};
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
		long[] data = { 268435552L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	}
	
