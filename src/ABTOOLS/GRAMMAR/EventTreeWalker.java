// $ANTLR 2.7.6 (2005-12-22): "expandedEventTreeWalker.g" -> "EventTreeWalker.java"$

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

 /**
  * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
  **/

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// our packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.PRINTING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;


public class EventTreeWalker extends antlr.TreeParser       implements EventTreeWalkerTokenTypes
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


// Section for selecting the target ASCII/XML/HTML or 
// more interesting ADA/C

	public 		int ASCII 		= 0;
	public 		int XML			= 1;
	public		int HTML		= 2;
	public		int TEX			= 3;

	public		int ADA			= 10;
	public		int C			= 11;

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
			errors.Internal		(	"EventTreeWalker.g",
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
	     errors.Internal("EventTreeWalker.g", " Treewalker PB it's a null node");
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
	     errors.Internal("EventTreeWalker.g", " Treewalker PB it's a null node");
	 }
	 else 
	 {
      	     myBuffer.append(node.getText()+"\n");
	     myBuffer.append(index.toString());
	 }
 	}

public EventTreeWalker() {
	tokenNames = _tokenNames;
}

	public final void composant(AST _t) throws RecognitionException {
		
		MyNode composant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				initializeString();
				index.init();
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_SYSTEM)) {
				machine(_t);
				_t = _retTree;
			}
			else if ((_t.getType()==LITERAL_REFINEMENT)) {
				refinement(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				finalizeString();
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void machine(AST _t) throws RecognitionException {
		
		MyNode machine_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t4 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SYSTEM);
			_t = _t.getFirstChild();
			
				index.Add();
				printToStringln(out.Clause(tt.getText()));
			
			paramName(_t);
			_t = _retTree;
			
				printToStringln("");
			
			clauses(_t);
			_t = _retTree;
			
				printToString(out.Clause("END"));
			
			_t = __t4;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void refinement(AST _t) throws RecognitionException {
		
		MyNode refinement_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t6 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_REFINEMENT);
			_t = _t.getFirstChild();
			
				index.Add();
				printToStringln(out.Clause(tt.getText()));
			
			paramName(_t);
			_t = _retTree;
			
				printToStringln("");
			
			clauses(_t);
			_t = _retTree;
			
				printToString(out.Clause("END"));
			
			_t = __t6;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void paramName(AST _t) throws RecognitionException {
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode name1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t50 = _t;
				MyNode tmp1_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name);
					printToString(out.Separator("("));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t50;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name1);
				
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
	
	public final void clauses(AST _t) throws RecognitionException {
		
		MyNode clauses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			{
			_loop9:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_tokenSet_0.member(_t.getType()))) {
					clause(_t);
					_t = _retTree;
				}
				else {
					break _loop9;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void clause(AST _t) throws RecognitionException {
		
		MyNode clause_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_REFINES:
			{
				refines(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_CONSTRAINTS:
			{
				constraints(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			{
				link(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_SETS:
			{
				sets(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			{
				constants(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_PROPERTIES:
			{
				properties(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			{
				variables(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_INVARIANT:
			{
				invariant(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_VARIANT:
			{
				variant(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_ASSERTIONS:
			{
				assertions(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_DEFINITIONS:
			{
				definitions(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_INITIALISATION:
			{
				initialisation(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_OPERATIONS:
			{
				operations(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_EVENTS:
			{
				events(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_MODALITIES:
			{
				modalities(_t);
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
	
	public final void refines(AST _t) throws RecognitionException {
		
		MyNode refines_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		
		try {      // for error handling
			AST __t77 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_REFINES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToStringln(name);
			
			_t = __t77;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void constraints(AST _t) throws RecognitionException {
		
		MyNode constraints_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t56 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_CONSTRAINTS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t56;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void link(AST _t) throws RecognitionException {
		
		MyNode link_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_USES:
			{
				uses(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_INCLUDES:
			{
				includes(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_SEES:
			{
				sees(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_EXTENDS:
			{
				extendeds(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_PROMOTES:
			{
				promotes(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_IMPORTS:
			{
				imports(_t);
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
	
	public final void sets(AST _t) throws RecognitionException {
		
		MyNode sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t85 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SETS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			sets_declaration(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t85;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void constants(AST _t) throws RecognitionException {
		
		MyNode constants_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTANTS:
			{
				AST __t79 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t1.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t79;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t80 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t2.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t80;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t81 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t3.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t81;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t82 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t4.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t82;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t83 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t5.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t83;
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
	
	public final void properties(AST _t) throws RecognitionException {
		
		MyNode properties_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t121 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_PROPERTIES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t121;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variables(AST _t) throws RecognitionException {
		
		MyNode variables_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t0 = null;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_VARIABLES:
			{
				AST __t123 = _t;
				t0 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t0.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t123;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t124 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t1.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t124;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t125 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t2.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t125;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t126 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t4.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t126;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t127 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t5.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
				
				_t = __t127;
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
	
	public final void invariant(AST _t) throws RecognitionException {
		
		MyNode invariant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t129 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INVARIANT);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t129;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variant(AST _t) throws RecognitionException {
		
		MyNode variant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t12 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_VARIANT);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			a_variant(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t12;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void assertions(AST _t) throws RecognitionException {
		
		MyNode assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t140 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ASSERTIONS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			list_assertions(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t140;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void definitions(AST _t) throws RecognitionException {
		
		MyNode definitions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t131 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_DEFINITIONS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			list_def(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t131;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void initialisation(AST _t) throws RecognitionException {
		
		MyNode initialisation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t144 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INITIALISATION);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			instruction(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t144;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void operations(AST _t) throws RecognitionException {
		
		MyNode operations_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t146 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_OPERATIONS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listOperation(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t146;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void events(AST _t) throws RecognitionException {
		
		MyNode events_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t15 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_EVENTS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listEvent(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t15;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void modalities(AST _t) throws RecognitionException {
		
		MyNode modalities_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t26 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_MODALITIES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
				index.Add();
			
			modality(_t);
			_t = _retTree;
			
				index.Retract();
				printToStringln("");
			
			_t = __t26;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void a_variant(AST _t) throws RecognitionException {
		
		MyNode a_variant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			predicate(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
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
				AST __t242 = _t;
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
				_t = __t242;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t243 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t243;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t244 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t244;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t245 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t4.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t245;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t246 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t5.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t246;
				_t = _t.getNextSibling();
				break;
			}
			case PRODSET:
			{
				AST __t247 = _t;
				t51 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t51.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t248 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t6.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t248;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t249 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t7.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t249;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t250 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t8.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t250;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t251 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t9.getText()) );
				
				predicate(_t);
				_t = _retTree;
				_t = __t251;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t252 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t10.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t252;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t253 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t11.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t253;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t254 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t12.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t254;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t255 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t13.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t255;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t256 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t14.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t256;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t257 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t15.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t257;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t258 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t16.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t258;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t259 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t17.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t259;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t260 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t18.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t260;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t261 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t19.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t261;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t262 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t20.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t262;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t263 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t21.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t263;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t264 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t22.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t264;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t265 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t23.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t265;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t266 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t24.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t266;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t267 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t25.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t267;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t268 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t26.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t269 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t27.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t269;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t270 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t28.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t270;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t271 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t29.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t271;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t272 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t30.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t272;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t273 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t31.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t273;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t274 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t32.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t274;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t275 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t33.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t275;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t276 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t34.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t276;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t277 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t35.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t277;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t278 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t36.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t278;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t279 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t37.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t279;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t280 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t38.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t280;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t281 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t39.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t281;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t282 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
				printToString(out.Separator(t40.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t282;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t283 = _t;
				t41 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t41.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t283;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t284 = _t;
				t42 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t42.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t284;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t285 = _t;
				t43 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t43.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t285;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t286 = _t;
				t44 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t44.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t286;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t287 = _t;
				t45 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
							printToString(out.Separator(t45.getText()));
				predicate(_t);
				_t = _retTree;
				_t = __t287;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t288 = _t;
				t46 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t46.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t288;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t289 = _t;
				t48 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t48.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t289;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t290 = _t;
				t49 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t49.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t290;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t291 = _t;
				MyNode tmp2_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("not"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t291;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t292 = _t;
				MyNode tmp3_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("ran"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t292;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t293 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("dom"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t293;
				_t = _t.getNextSibling();
				break;
			}
			case B_MIN:
			{
				AST __t294 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_MIN);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("min"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t294;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAX:
			{
				AST __t295 = _t;
				MyNode tmp6_AST_in = (MyNode)_t;
				match(_t,B_MAX);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("max"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t295;
				_t = _t.getNextSibling();
				break;
			}
			case B_CARD:
			{
				AST __t296 = _t;
				MyNode tmp7_AST_in = (MyNode)_t;
				match(_t,B_CARD);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("card"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t296;
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
	
	public final void listEvent(AST _t) throws RecognitionException {
		
		MyNode listEvent_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t17 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				listEvent(_t);
				_t = _retTree;
				
					index.Retract();
					printToStringln("");
					printToString(out.Separator(tt.getText())+index.getOne());
					index.Add();
				
				event(_t);
				_t = _retTree;
				_t = __t17;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==OP_DEF)) {
				event(_t);
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
	
	public final void event(AST _t) throws RecognitionException {
		
		MyNode event_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t19 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			eventHeader(_t);
			_t = _retTree;
			
				printToStringln(out.Separator(tt.getText()));
				index.Add();
			
			substitution_event(_t);
			_t = _retTree;
			
				index.Retract();
				printToStringln("");
			
			_t = __t19;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void eventHeader(AST _t) throws RecognitionException {
		
		MyNode eventHeader_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_ref)) {
				AST __t21 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ref);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				listNameRenamed(_t);
				_t = _retTree;
				_t = __t21;
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
	
	public final void substitution_event(AST _t) throws RecognitionException {
		
		MyNode substitution_event_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_PRE_POST:
			{
				substitution_precondition_postcondition(_t);
				_t = _retTree;
				break;
			}
			case B_SELECT_POST:
			{
				substitution_selection_postcondition(_t);
				_t = _retTree;
				break;
			}
			case B_ANY_POST:
			{
				substitution_unbounded_choice_postcondition(_t);
				_t = _retTree;
				break;
			}
			default:
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==B_BEGIN_POST)) {
					substitution_block_postcondition(_t);
					_t = _retTree;
				}
				else if ((_tokenSet_1.member(_t.getType()))) {
					instruction(_t);
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
				AST __t234 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(tt.getText());
				
				nameRenamed(_t);
				_t = _retTree;
				_t = __t234;
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
	
	public final void listNameRenamed(AST _t) throws RecognitionException {
		
		MyNode listNameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t23 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNameRenamed(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				listNameRenamed(_t);
				_t = _retTree;
				_t = __t23;
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
	
	public final void substitution_block_postcondition(AST _t) throws RecognitionException {
		
		MyNode substitution_block_postcondition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t40 = _t;
			MyNode tmp8_AST_in = (MyNode)_t;
			match(_t,B_BEGIN_POST);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause("BEGIN"));
			
			substitution_event(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("POST"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("END"));
			
			_t = __t40;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	protected final void substitution_precondition_postcondition(AST _t) throws RecognitionException {
		
		MyNode substitution_precondition_postcondition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t42 = _t;
			MyNode tmp9_AST_in = (MyNode)_t;
			match(_t,B_PRE_POST);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause("PRE"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("THEN"));
			
			substitution_event(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("POST"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("END"));
			
			_t = __t42;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	protected final void substitution_selection_postcondition(AST _t) throws RecognitionException {
		
		MyNode substitution_selection_postcondition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t44 = _t;
			MyNode tmp10_AST_in = (MyNode)_t;
			match(_t,B_SELECT_POST);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause("SELECT"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("THEN"));
			
			substitution_event(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("POST"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("END"));
			
			_t = __t44;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	protected final void substitution_unbounded_choice_postcondition(AST _t) throws RecognitionException {
		
		MyNode substitution_unbounded_choice_postcondition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t46 = _t;
			MyNode tmp11_AST_in = (MyNode)_t;
			match(_t,B_ANY_POST);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause("ANY"));
			
			listTypedIdentifier(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("WHERE"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("THEN"));
			
			substitution_event(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("POST"));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln(out.Clause("END"));
			
			_t = __t46;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t40 = null;
		MyNode t5 = null;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode t8 = null;
		MyNode t9 = null;
		MyNode t11 = null;
		MyNode t12 = null;
		MyNode t13 = null;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t154 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(t1.getText()));
				
				instruction(_t);
				_t = _retTree;
				_t = __t154;
				_t = _t.getNextSibling();
				break;
			}
			case SEQUENTIAL:
			{
				AST __t155 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(t2.getText()));
				
				instruction(_t);
				_t = _retTree;
				_t = __t155;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_skip:
			{
				AST __t156 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t3.getText()));
				
				_t = __t156;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t157 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				
				//	index.Add();
					printToStringln(out.KeyWord(t4.getText()));
				
				instruction(_t);
				_t = _retTree;
				
				//	index.Retract();
					printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t157;
				_t = _t.getNextSibling();
				break;
			}
			case B_BEGIN_POST:
			{
				AST __t158 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BEGIN_POST);
				_t = _t.getFirstChild();
				
				//	index.Add();
					printToStringln(out.KeyWord(t40.getText()));
				
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.KeyWord("POST"));
				
				predicate(_t);
				_t = _retTree;
				
				//	index.Retract();
					printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t158;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t159 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				
					index.Add();
					printToStringln(out.KeyWord(t5.getText()));
					index.Retract();
				
				predicate(_t);
				_t = _retTree;
				
					printToStringln();
				//	index.Add();
					printToStringln(out.KeyWord("THEN"));
				//	index.Retract();
				
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("END"));
				
				_t = __t159;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t160 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				
				printToStringln(out.KeyWord(t6.getText()));
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("");
					printToStringln(out.KeyWord("THEN"));
				
				instruction(_t);
				_t = _retTree;
				
				printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t160;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_IF:
			{
				AST __t161 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				
				printToStringln(out.KeyWord(t7.getText()));
				
				predicate(_t);
				_t = _retTree;
				branche_then(_t);
				_t = _retTree;
				{
				_loop163:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
					}
					else {
						break _loop163;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
				printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t161;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t165 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				
					printToStringln(out.KeyWord(t8.getText()));
					index.Retract();
				
				list_or(_t);
				_t = _retTree;
				
					printToStringln();
					printToString(out.KeyWord("END"));
				
				_t = __t165;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CASE:
			{
				AST __t166 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				
				printToStringln(out.KeyWord(t9.getText()));
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("");
					printToStringln(out.KeyWord("OF"));
				
				branche_either(_t);
				_t = _retTree;
				{
				_loop168:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
					}
					else {
						break _loop168;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
				printToStringln("");
					printToStringln(out.KeyWord("END"));
				printToStringln("");
					printToStringln(out.KeyWord("END"));
				
				_t = __t166;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ANY:
			{
				AST __t170 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				
					printToStringln(out.KeyWord("ANY"));
					index.Retract();
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln();
					index.Add();
					printToStringln(out.KeyWord("WHERE"));
					index.Retract();
				
				predicate(_t);
				_t = _retTree;
				
					printToStringln();
					index.Add();
					printToStringln(out.KeyWord("THEN"));
					index.Retract();
				instruction(_t);
				_t = _retTree;
				
					printToStringln();
					printToStringln(out.KeyWord("END"));
				
				_t = __t170;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_LET:
			{
				AST __t171 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				
					printToStringln(out.KeyWord(t11.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("BE"));
				
				list_equal(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("IN"));
				
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("END"));
				
				_t = __t171;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t172 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				
					printToStringln(out.KeyWord(t12.getText()));
				
				predicate(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("THEN"));
					index.Add();
				
				instruction(_t);
				_t = _retTree;
				
					index.Retract();
				
				{
				_loop174:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
					}
					else {
						break _loop174;
					}
					
				} while (true);
				}
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ELSE)) {
					branche_else(_t);
					_t = _retTree;
				}
				else if ((_t.getType()==3)) {
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
					printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t172;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t176 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				
					printToStringln(out.KeyWord(t13.getText()));
				
				predicate(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("DO"));
				
				instruction(_t);
				_t = _retTree;
				variant_or_no(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t176;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VAR:
			{
				AST __t177 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				
					printToStringln("");
					printToStringln(out.KeyWord(tt.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.KeyWord("IN"));
				
				instruction(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.KeyWord("END"));
				
				_t = __t177;
				_t = _t.getNextSibling();
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
			{
				simple_affect(_t);
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
	
	public final void modality(AST _t) throws RecognitionException {
		
		MyNode modality_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_ANY:
			{
				AST __t28 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t1.getText()));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				event_list(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ESTABLISH)) {
					establish(_t);
					_t = _retTree;
				}
				else if ((_t.getType()==LITERAL_MAINTAIN)) {
					maintain(_t);
					_t = _retTree;
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
					printToStringln("");
					printToStringln(out.Clause("END"));
				
				_t = __t28;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t30 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t2.getText()));
				
				event_list(_t);
				_t = _retTree;
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LITERAL_ESTABLISH)) {
					establish(_t);
					_t = _retTree;
				}
				else if ((_t.getType()==LITERAL_MAINTAIN)) {
					maintain(_t);
					_t = _retTree;
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				
					printToStringln("");
					printToStringln(out.Clause("END"));
				
				_t = __t30;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t32 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				
					printToStringln(out.Clause("THEN"));
				
				event_list(_t);
				_t = _retTree;
				establish(_t);
				_t = _retTree;
				
					printToStringln("");
					printToStringln(out.Clause("END"));
				
				_t = __t32;
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
	
	public final void listTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t52 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(","));
				
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t52;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_INSET)) {
				typedIdentifier(_t);
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
	
	public final void event_list(AST _t) throws RecognitionException {
		
		MyNode event_list_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_ALL)) {
				AST __t38 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ALL);
				_t = _t.getFirstChild();
				
					printToStringln(out.Clause(tt.getText()));
				
				_t = __t38;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				listTypedIdentifier(_t);
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
	
	public final void establish(AST _t) throws RecognitionException {
		
		MyNode establish_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t34 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ESTABLISH);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t34;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void maintain(AST _t) throws RecognitionException {
		
		MyNode maintain_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t36 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_MAINTAIN);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			a_variant(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t36;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void implementation(AST _t) throws RecognitionException {
		
		MyNode implementation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t48 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_IMPLEMENTATION);
			_t = _t.getFirstChild();
			
				index.Add();
				printToStringln(out.Clause(tt.getText()));
			
			paramName(_t);
			_t = _retTree;
			
				printToStringln("");
			
			clauses(_t);
			_t = _retTree;
			
				printToString(out.Clause("END"));
			
			_t = __t48;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode nn = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t54 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(out.Separator(":"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t54;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nn = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(" /* : "+nn.getBType().toString()+" */");
				
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
	
	public final void uses(AST _t) throws RecognitionException {
		
		MyNode uses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t61 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_USES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t61;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void includes(AST _t) throws RecognitionException {
		
		MyNode includes_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t63 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INCLUDES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t63;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void sees(AST _t) throws RecognitionException {
		
		MyNode sees_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t65 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SEES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t65;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void extendeds(AST _t) throws RecognitionException {
		
		MyNode extendeds_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t59 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_EXTENDS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t59;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void promotes(AST _t) throws RecognitionException {
		
		MyNode promotes_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t75 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_PROMOTES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t75;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void imports(AST _t) throws RecognitionException {
		
		MyNode imports_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t73 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_IMPORTS);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t73;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void listInstanciation(AST _t) throws RecognitionException {
		
		MyNode listInstanciation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t69 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				
					printToStringln(out.Separator(""));
					index.Retract();
					printToString(out.Separator(","));
					index.Add();
				
				paramRenameValuation(_t);
				_t = _retTree;
				_t = __t69;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_LPAREN)) {
				paramRenameValuation(_t);
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
	
	public final void listNames(AST _t) throws RecognitionException {
		
		MyNode listNames_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t67 = _t;
				MyNode tmp16_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				
					index.Retract();
					printToStringln("");
					printToString(out.Separator(","));
					index.Add();
				
				nameRenamed(_t);
				_t = _retTree;
				_t = __t67;
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
	
	public final void paramRenameValuation(AST _t) throws RecognitionException {
		
		MyNode paramRenameValuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t71 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				paramRenameValuation(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t71;
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
	
	public final void list_New_Predicate(AST _t) throws RecognitionException {
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t229 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				new_predicate(_t);
				_t = _retTree;
				_t = __t229;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
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
	
	public final void sets_declaration(AST _t) throws RecognitionException {
		
		MyNode sets_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t87 = _t;
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				
					printToString("\n"+out.Separator(";"));
				
				sets_declaration(_t);
				_t = _retTree;
				_t = __t87;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t88 = _t;
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				
					printToString("\n"+out.Separator(","));
				
				sets_declaration(_t);
				_t = _retTree;
				_t = __t88;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_EQUAL:
			{
				set_declaration(_t);
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
	
	public final void set_declaration(AST _t) throws RecognitionException {
		
		MyNode set_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode set = null;
		MyNode name = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_EQUAL)) {
				AST __t90 = _t;
				MyNode tmp19_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				set = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(set);
					printToString(" /* : "+set.getBType().toString()+" */");
					printToString(out.Separator("="));
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t90;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name);
					printToString(" /* : "+name.getBType().toString()+" */");
				
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
	
	public final void valuation_set(AST _t) throws RecognitionException {
		
		MyNode valuation_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t100 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t92 = _t;
				MyNode tmp20_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("{"));
				
				list_couple(_t);
				_t = _retTree;
				
					printToString(out.Separator("}"));
				
				_t = __t92;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t93 = _t;
				t100 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					printToString(out.KeyWord(t100.getText()));
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t93;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record(_t);
				_t = _retTree;
				break;
			}
			case B_MULT:
			{
				AST __t94 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					printToString(out.KeyWord(t2.getText()));
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t94;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t95 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					printToString(out.KeyWord(t3.getText()));
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t95;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t96 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					printToString(out.KeyWord(t4.getText()));
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t96;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			{
				t5 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(t5);
				
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
	
	public final void list_couple(AST _t) throws RecognitionException {
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t98 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_couple(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				couple(_t);
				_t = _retTree;
				_t = __t98;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_4.member(_t.getType()))) {
				couple(_t);
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
	
	public final void a_set_value(AST _t) throws RecognitionException {
		
		MyNode a_set_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode mi = null;
		MyNode val1 = null;
		MyNode val = null;
		MyNode tr = null;
		MyNode fa = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_IDENTIFIER:
			{
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name);
					printToString(" /* : "+name.getBType().toString()+" */");
				
				break;
			}
			case B_MINUS:
			{
				AST __t106 = _t;
				mi = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				val1 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					printToString(out.Separator(mi.getText()));
					printToString(val1);
				
				_t = __t106;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				val = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					printToString(val);
				
				break;
			}
			case LITERAL_TRUE:
			{
				tr = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					printToString(out.KeyWord(tr.getText()));
				
				break;
			}
			case LITERAL_FALSE:
			{
				fa = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					printToString(out.KeyWord(fa.getText()));
				
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
	
	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t01 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t222 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t1.getText()));
					printToString(out.Separator("("));
				
				listrecord(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t222;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t223 = _t;
				t01 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t01.getText()));
					printToString(out.Separator("("));
				
				listrecord(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t223;
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
	
	public final void couple(AST _t) throws RecognitionException {
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t100 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t100;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t101 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t2.getText()));
				
				parent_couple(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t101;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			{
				a_set_value(_t);
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
	
	public final void parent_couple(AST _t) throws RecognitionException {
		
		MyNode parent_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t103 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t103;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t104 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t104;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			{
				a_set_value(_t);
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
	
	public final void values(AST _t) throws RecognitionException {
		
		MyNode values_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t108 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_VALUES);
			_t = _t.getFirstChild();
			
				printToStringln(out.Clause(tt.getText()));
			
			list_valuation(_t);
			_t = _retTree;
			
				printToStringln("");
			
			_t = __t108;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_valuation(AST _t) throws RecognitionException {
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t110 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_valuation(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(tt.getText()));
				
				set_valuation(_t);
				_t = _retTree;
				_t = __t110;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EQUAL)) {
				set_valuation(_t);
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
	
	public final void set_valuation(AST _t) throws RecognitionException {
		
		MyNode set_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		
		try {      // for error handling
			AST __t112 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToString(name);
				printToString(out.Separator(tt.getText()));
			
			new_set_or_constant_valuation(_t);
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
	
	public final void new_set_or_constant_valuation(AST _t) throws RecognitionException {
		
		MyNode new_set_or_constant_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			predicate(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void set_interval_value(AST _t) throws RecognitionException {
		
		MyNode set_interval_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode a = null;
		
		try {      // for error handling
			AST __t115 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			a = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToString(a);
				printToString(" /* : "+a.getBType().toString()+" */");
				printToString(out.Separator(tt.getText()));
			
			interval_declaration(_t);
			_t = _retTree;
			_t = __t115;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void interval_declaration(AST _t) throws RecognitionException {
		
		MyNode interval_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t117 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_RANGE);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			
				printToString(out.Separator(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			_t = __t117;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void set_rename_value(AST _t) throws RecognitionException {
		
		MyNode set_rename_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name1 = null;
		MyNode name2 = null;
		
		try {      // for error handling
			AST __t119 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name1 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToStringln(name1);
				printToString(out.Separator(tt.getText()));
			
			name2 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToStringln(name2);
			
			_t = __t119;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_def(AST _t) throws RecognitionException {
		
		MyNode list_def_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_DEF)) {
				AST __t133 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				list_def(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(tt.getText()));
				
				definition(_t);
				_t = _retTree;
				_t = __t133;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_ASTRING||_t.getType()==B_DOUBLE_EQUAL)) {
				definition(_t);
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
	
	public final void definition(AST _t) throws RecognitionException {
		
		MyNode definition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_DOUBLE_EQUAL)) {
				AST __t135 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOUBLE_EQUAL);
				_t = _t.getFirstChild();
				paramName(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				formalText(_t);
				_t = _retTree;
				_t = __t135;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_ASTRING)) {
				t3 = (MyNode)_t;
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				
					printToStringln("\""+t3+"\"");
				
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
	
	public final void formalText(AST _t) throws RecognitionException {
		
		MyNode formalText_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==EXP_DEF)) {
				AST __t137 = _t;
				MyNode tmp21_AST_in = (MyNode)_t;
				match(_t,EXP_DEF);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				_t = __t137;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==SUBST_DEF)) {
				AST __t138 = _t;
				MyNode tmp22_AST_in = (MyNode)_t;
				match(_t,SUBST_DEF);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				_t = __t138;
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
	
	public final void list_assertions(AST _t) throws RecognitionException {
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t142 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_assertions(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator(tt.getText()));
				
				list_assertions(_t);
				_t = _retTree;
				_t = __t142;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
	
	public final void listOperation(AST _t) throws RecognitionException {
		
		MyNode listOperation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t148 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				
					index.Retract();
					printToStringln("");
					printToString(out.Separator(tt.getText())+index.getOne());
					index.Add();
				
				operation(_t);
				_t = _retTree;
				_t = __t148;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==OP_DEF)) {
				operation(_t);
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
	
	public final void operation(AST _t) throws RecognitionException {
		
		MyNode operation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t150 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			operationHeader(_t);
			_t = _retTree;
			
				printToStringln(out.Separator(tt.getText()));
				index.Add();
			
			instruction(_t);
			_t = _retTree;
			
				index.Retract();
				printToStringln("");
			
			_t = __t150;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void operationHeader(AST _t) throws RecognitionException {
		
		MyNode operationHeader_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				AST __t152 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				paramName(_t);
				_t = _retTree;
				_t = __t152;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_LPAREN)) {
				paramName(_t);
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
	
	public final void branche_then(AST _t) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t187 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(tt.getText()));
				index.Add();
			
			instruction(_t);
			_t = _retTree;
			
				index.Retract();
			
			_t = __t187;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void branche_elsif(AST _t) throws RecognitionException {
		
		MyNode branche_elsif_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			AST __t191 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(t1.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
				printToStringln(out.KeyWord("THEN"));
			
			instruction(_t);
			_t = _retTree;
			_t = __t191;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void branche_else(AST _t) throws RecognitionException {
		
		MyNode branche_else_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t189 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(tt.getText()));
			
			instruction(_t);
			_t = _retTree;
			_t = __t189;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_or(AST _t) throws RecognitionException {
		
		MyNode list_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t179 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				
					printToStringln();
					index.Add();
					printToStringln(out.KeyWord(t1.getText()));
					index.Retract();
				
				instruction(_t);
				_t = _retTree;
				_t = __t179;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				instruction(_t);
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
	
	public final void branche_either(AST _t) throws RecognitionException {
		
		MyNode branche_either_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t183 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
				printToStringln(out.KeyWord("THEN"));
			
			instruction(_t);
			_t = _retTree;
			_t = __t183;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void branche_or(AST _t) throws RecognitionException {
		
		MyNode branche_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t185 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
				printToStringln(out.KeyWord("THEN"));
			
			instruction(_t);
			_t = _retTree;
			_t = __t185;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_equal(AST _t) throws RecognitionException {
		
		MyNode list_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_AND)) {
				AST __t198 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				list_equal(_t);
				_t = _retTree;
				
					printToStringln("");
					printToString(out.Separator("&"));
				
				an_equal(_t);
				_t = _retTree;
				_t = __t198;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EQUAL)) {
				an_equal(_t);
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
	
	public final void branche_when(AST _t) throws RecognitionException {
		
		MyNode branche_when_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t181 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			
				printToStringln("");
				printToStringln(out.KeyWord(tt.getText()));
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("");
				printToStringln(out.KeyWord("THEN"));
			
			instruction(_t);
			_t = _retTree;
			_t = __t181;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void variant_or_no(AST _t) throws RecognitionException {
		
		MyNode variant_or_no_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				AST __t193 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				AST __t194 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				
					printToStringln("");
					printToStringln(out.KeyWord(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t194;
				_t = _t.getNextSibling();
				
					printToStringln("");
					printToStringln(out.KeyWord(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t193;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t195 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				AST __t196 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				
					printToStringln("");
					printToStringln(out.KeyWord(t4.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t196;
				_t = _t.getNextSibling();
				
					printToStringln("");
					printToStringln(out.KeyWord(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t195;
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
	
	public final void simple_affect(AST _t) throws RecognitionException {
		
		MyNode simple_affect_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t217 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t217;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			{
				AST __t218 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				func_call(_t);
				_t = _retTree;
				_t = __t218;
				_t = _t.getNextSibling();
				break;
			}
			case INSET:
			{
				AST __t219 = _t;
				MyNode tmp23_AST_in = (MyNode)_t;
				match(_t,INSET);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(":("));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t219;
				_t = _t.getNextSibling();
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t220 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(t4.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t220;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_LPAREN:
			case B_QUOTEIDENT:
			case FUNC_CALL_PARAM:
			{
				a_func_call(_t);
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
	
	public final void an_equal(AST _t) throws RecognitionException {
		
		MyNode an_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode n1 = null;
		
		try {      // for error handling
			AST __t200 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			n1 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToString(n1);
				printToString(out.Separator("="));
			
			predicate(_t);
			_t = _retTree;
			_t = __t200;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void func_call(AST _t) throws RecognitionException {
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t202 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator("~"));
				
				_t = __t202;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t203 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator("["));
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("]"));
				
				_t = __t203;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t204 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t204;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t205 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					printToString("'");
				
				func_call(_t);
				_t = _retTree;
				_t = __t205;
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
	
	public final void func_param(AST _t) throws RecognitionException {
		
		MyNode func_param_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			list_New_Predicate(_t);
			_t = _retTree;
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
				AST __t208 = _t;
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
				
				_t = __t208;
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
				AST __t211 = _t;
				MyNode tmp24_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t211;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t212 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				afc(_t);
				_t = _retTree;
				_t = __t212;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t213 = _t;
				MyNode tmp25_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t213;
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
	
	public final void listPredicate(AST _t) throws RecognitionException {
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==ELEM_SET)) {
				AST __t240 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t240;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
	
	public final void list_func_call(AST _t) throws RecognitionException {
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t215 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				list_func_call(_t);
				_t = _retTree;
				_t = __t215;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_6.member(_t.getType()))) {
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
	
	public final void listrecord(AST _t) throws RecognitionException {
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t225 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				
					printToString("\n"+out.Separator(","));
				
				a_record(_t);
				_t = _retTree;
				_t = __t225;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_7.member(_t.getType()))) {
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
				AST __t227 = _t;
				MyNode tmp26_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					printToString(name);
					printToString(out.Separator(":"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t227;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
	
	public final void new_predicate(AST _t) throws RecognitionException {
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t231 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t231;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t232 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t232;
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
	
	public final void nameRenamedDecorated(AST _t) throws RecognitionException {
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t236 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				_t = __t236;
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
				AST __t238 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				
					printToString(out.Separator(tt.getText()));
				
				_t = __t238;
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
				AST __t298 = _t;
				MyNode tmp27_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord("bool"));
					printToString(out.Separator("("));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t298;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t299 = _t;
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("["));
				
				listPredicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("]"));
				
				_t = __t299;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t300 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t300;
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
				AST __t301 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("{"));
				
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator("}"));
				
				_t = __t301;
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
				AST __t308 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("~"));
				
				_t = __t308;
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
				AST __t309 = _t;
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("("));
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t309;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t310 = _t;
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("("));
				
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t310;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t311 = _t;
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("'"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t311;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t312 = _t;
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("["));
				
				predicate(_t);
				_t = _retTree;
				
					printToString(out.Separator("]"));
				
				_t = __t312;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					printToString(out.Separator("TRUE"));
				
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp35_AST_in = (MyNode)_t;
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
				AST __t303 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				
					printToString(out.Separator(t1.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t303;
				_t = _t.getNextSibling();
				break;
			}
			case ELEM_SET:
			{
				AST __t304 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				cvalue_set(_t);
				_t = _retTree;
				_t = __t304;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t305 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t305;
				_t = _t.getNextSibling();
				break;
			}
			default:
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==LIST_VAR)) {
					AST __t306 = _t;
					t4 = _t==ASTNULL ? null :(MyNode)_t;
					match(_t,LIST_VAR);
					_t = _t.getFirstChild();
					cvalue_set(_t);
					_t = _retTree;
					
						printToString(out.Separator(t4.getText()));
					
					predicate(_t);
					_t = _retTree;
					_t = __t306;
					_t = _t.getNextSibling();
				}
				else if ((_tokenSet_5.member(_t.getType()))) {
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
				AST __t319 = _t;
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
				
				_t = __t319;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t320 = _t;
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
				
				_t = __t320;
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
				AST __t322 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				
					printToString(out.Separator(t1.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t322;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t323 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t2.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t323;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t324 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t3.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t324;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t325 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t4.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t325;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t326 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				
					printToString(out.KeyWord(t5.getText()));
				
				q_operande(_t);
				_t = _retTree;
				_t = __t326;
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
				AST __t330 = _t;
				MyNode tmp36_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString(out.Separator("("));
				
				list_identifier(_t);
				_t = _retTree;
				
					printToString(out.Separator(")"));
				
				_t = __t330;
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
				AST __t314 = _t;
				MyNode tmp37_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(";"));
				
				predicate(_t);
				_t = _retTree;
				_t = __t314;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t315 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(t2.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t315;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t316 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString(out.Separator(t3.getText()));
				
				predicate(_t);
				_t = _retTree;
				_t = __t316;
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
			AST __t328 = _t;
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
			
			_t = __t328;
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
		"\"SYSTEM\"",
		"\"EVENTS\"",
		"\"MODALITIES\"",
		"\"MAINTAIN\"",
		"\"UNTIL\"",
		"\"ESTABLISH\"",
		"\"ALL\"",
		"B_ref",
		"\"POST\"",
		"B_PREC",
		"B_ANY_POST",
		"B_SELECT_POST",
		"B_PRE_POST",
		"B_BEGIN_POST",
		"\"ref\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 0L, 0L, 3458834882631811071L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[8];
		data[0]=268435552L;
		data[1]=117093590311632912L;
		data[2]=33366376252964864L;
		data[3]=256L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 112L, 2097152L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -3530822108395339808L, 246144575731743L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -9214224099843244000L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { -3530822108395339808L, 246144575725599L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 268435552L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { -3530822108395339808L, 246282014679071L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	}
	
