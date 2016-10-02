// $ANTLR 2.7.6 (2005-12-22): "expandedBXML.g" -> "BXML.java"$

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

	import ABTOOLS.ANTLR_TOOLS.*;
	import ABTOOLS.PRINTING.*;
	import ABTOOLS.DEBUGGING.*;


public class BXML extends antlr.TreeParser       implements BXMLTokenTypes
 {

// module
	private String module ="BXML.g";

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
	
	public	StringBuffer myBuffer;

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

        	System.out.println(myBuffer.toString());
		// au cas ou ....
	}

	void printToString(String st)
	{
        	debug.print(st);
        	myBuffer.append(st);
 	}

	void printToStringln(String st)
	{
        	debug.println(st);
        	myBuffer.append(st+"\n");
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
	 }
 	}

public BXML() {
	tokenNames = _tokenNames;
}

	public final void composant(AST _t) throws RecognitionException {
		
		MyNode composant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				initializeString();
				printToStringln("<AMN>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_MACHINE:
			{
				machine(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_REFINEMENT:
			{
				refinement(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_IMPLEMENTATION:
			{
				implementation(_t);
				_t = _retTree;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			
				printToStringln("</AMN>");
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
		
		try {      // for error handling
			AST __t4 = _t;
			MyNode tmp1_AST_in = (MyNode)_t;
			match(_t,LITERAL_MACHINE);
			_t = _t.getFirstChild();
			
				printToStringln("<MACHINE>");
			
			head_component(_t);
			_t = _retTree;
			clauses(_t);
			_t = _retTree;
			
				printToString("</MACHINE>");
			
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
		
		try {      // for error handling
			AST __t6 = _t;
			MyNode tmp2_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINEMENT);
			_t = _t.getFirstChild();
			
				
				printToStringln("<REFINEMENT>");
			
			head_component(_t);
			_t = _retTree;
			clauses(_t);
			_t = _retTree;
			
				printToString("</REFINEMENT>");
			
			_t = __t6;
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
		
		try {      // for error handling
			AST __t8 = _t;
			MyNode tmp3_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPLEMENTATION);
			_t = _t.getFirstChild();
			
				printToStringln("<IMPLEMENTATION>");
			
			head_component(_t);
			_t = _retTree;
			clauses(_t);
			_t = _retTree;
			
				printToString("</IMPLEMENTATION>");
			
			_t = __t8;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void head_component(AST _t) throws RecognitionException {
		
		MyNode head_component_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<HEAD>");
			
			paramName(_t);
			_t = _retTree;
			
				printToString("</HEAD>");
			
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
			
				printToString("<clauses>");
			
			{
			_loop15:
			do {
				if (_t==null) _t=ASTNULL;
				if (((_t.getType() >= LITERAL_CONSTRAINTS && _t.getType() <= LITERAL_OPERATIONS))) {
					clause(_t);
					_t = _retTree;
				}
				else {
					break _loop15;
				}
				
			} while (true);
			}
			
				printToString("</clauses>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void paramName(AST _t) throws RecognitionException {
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<paramName>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t12 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString("<B_LPAREN>");
				
				paramName(_t);
				_t = _retTree;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToString("</B_LPARENT>");
				
				_t = __t12;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				an_id(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				printToString("</paramName>");
			
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
			
				printToString("<listTypedIdentifier>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t20 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<ListIdent>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToString("</ListIdent>");
				
				_t = __t20;
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
			
				printToString("</listTypedIdentifier>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void an_id(AST _t) throws RecognitionException {
		
		MyNode an_id_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<Identifier>");
			
			MyNode tmp6_AST_in = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				printToString("</Identifier>");
			
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
			
				printToString("<clause>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTRAINTS:
			{
				constraints(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_REFINES:
			{
				refines(_t);
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
			case LITERAL_VALUES:
			{
				values(_t);
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
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			
				printToString("<clause>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void constraints(AST _t) throws RecognitionException {
		
		MyNode constraints_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t25 = _t;
			MyNode tmp7_AST_in = (MyNode)_t;
			match(_t,LITERAL_CONSTRAINTS);
			_t = _t.getFirstChild();
			
				printToStringln("<Constraints>");
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("</Constraints>");
			
			_t = __t25;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void refines(AST _t) throws RecognitionException {
		
		MyNode refines_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		
		try {      // for error handling
			AST __t48 = _t;
			MyNode tmp8_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINES);
			_t = _t.getFirstChild();
			
				printToStringln("<Refines>");
			
			name = _t==ASTNULL ? null : (MyNode)_t;
			an_id(_t);
			_t = _retTree;
			
				printToStringln(name);
				printToStringln("</Refines>");
			
			_t = __t48;
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
		
		try {      // for error handling
			AST __t56 = _t;
			MyNode tmp9_AST_in = (MyNode)_t;
			match(_t,LITERAL_SETS);
			_t = _t.getFirstChild();
			
				printToStringln("<Sets>");
			
			sets_declaration(_t);
			_t = _retTree;
			
				printToStringln("</Sets>");
			
			_t = __t56;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void values(AST _t) throws RecognitionException {
		
		MyNode values_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t94 = _t;
			MyNode tmp10_AST_in = (MyNode)_t;
			match(_t,LITERAL_VALUES);
			_t = _t.getFirstChild();
			
				printToStringln("<Values>");
			
			list_valuation(_t);
			_t = _retTree;
			
				printToStringln("</Values>");
			
			_t = __t94;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTANTS:
			{
				AST __t50 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteConstants>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteConstants>");
				
				_t = __t50;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t51 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteConstants>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteConstants>");
				
				_t = __t51;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t52 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln("<AbstractConstants>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</AbstractConstants>");
				
				_t = __t52;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t53 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln("<AbstractConstants>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</AbstractConstants>");
				
				_t = __t53;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t54 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteConstants>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteConstants>");
				
				_t = __t54;
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
		
		try {      // for error handling
			AST __t107 = _t;
			MyNode tmp16_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROPERTIES);
			_t = _t.getFirstChild();
			
				printToStringln("<Properties>");
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("</Properties>");
			
			_t = __t107;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_VARIABLES:
			{
				AST __t109 = _t;
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteVariables>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteVariables>");
				
				_t = __t109;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t110 = _t;
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln("<AbstractVariables>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</AbstractVariables>");
				
				_t = __t110;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t111 = _t;
				MyNode tmp19_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln("<AbstractVariables>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</AbstractVariables>");
				
				_t = __t111;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t112 = _t;
				MyNode tmp20_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteVariables>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteVariables>");
				
				_t = __t112;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t113 = _t;
				MyNode tmp21_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				
					printToStringln("<ConcreteVariables>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</ConcreteVariables>");
				
				_t = __t113;
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
		
		try {      // for error handling
			AST __t115 = _t;
			MyNode tmp22_AST_in = (MyNode)_t;
			match(_t,LITERAL_INVARIANT);
			_t = _t.getFirstChild();
			
				printToStringln("<Invariant>");
			
			
			
			predicate(_t);
			_t = _retTree;
			
				printToStringln("</Invariant>");
			
			_t = __t115;
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
		
		try {      // for error handling
			AST __t126 = _t;
			MyNode tmp23_AST_in = (MyNode)_t;
			match(_t,LITERAL_ASSERTIONS);
			_t = _t.getFirstChild();
			
				printToStringln("<Assertions>");
			
			list_assertions(_t);
			_t = _retTree;
			
				printToStringln("</Assertions>");
			
			_t = __t126;
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
		
		try {      // for error handling
			AST __t117 = _t;
			MyNode tmp24_AST_in = (MyNode)_t;
			match(_t,LITERAL_DEFINITIONS);
			_t = _t.getFirstChild();
			
				printToStringln("<Definitions>");
			
			list_def(_t);
			_t = _retTree;
			
				printToStringln("</Definitions>");
			
			_t = __t117;
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
		
		try {      // for error handling
			AST __t130 = _t;
			MyNode tmp25_AST_in = (MyNode)_t;
			match(_t,LITERAL_INITIALISATION);
			_t = _t.getFirstChild();
			
				printToStringln("<Initialisation>");
			
			instruction(_t);
			_t = _retTree;
			
				printToStringln("</Initialisation>");
			
			_t = __t130;
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
		
		try {      // for error handling
			AST __t132 = _t;
			MyNode tmp26_AST_in = (MyNode)_t;
			match(_t,LITERAL_OPERATIONS);
			_t = _t.getFirstChild();
			
				printToStringln("<Operations>");
			
			System.out.println(myBuffer.toString());
			
			listOperation(_t);
			_t = _retTree;
			
				printToStringln("</Operations>");
			
			_t = __t132;
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
		
		try {      // for error handling
			
				printToString("<typedIdentifier>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t23 = _t;
				MyNode tmp27_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				
					printToString("<B_INSET>");
				
				nameRenamed(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</B_INSET>");
				
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
			
				printToString("</typedIdentifier>");
			
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
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t215 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				_t = __t215;
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
	
	public final void predicate(AST _t) throws RecognitionException {
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case BTRUE:
			{
				AST __t225 = _t;
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,BTRUE);
				_t = _t.getFirstChild();
				
				printToString("<BTRUE>");
				
				_t = __t225;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t226 = _t;
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				
					printToString("<Neg>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Neg>");
				
				_t = __t226;
				_t = _t.getNextSibling();
				break;
			}
			case B_AND:
			{
				AST __t227 = _t;
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				
					printToString("<And>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</And");
				
				_t = __t227;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t228 = _t;
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				
					printToString("<Or>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Or>");
				
				_t = __t228;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t229 = _t;
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				
					printToString("<Implies>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Implies>");
				
				_t = __t229;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t230 = _t;
				MyNode tmp35_AST_in = (MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				
					printToString("<Equiv>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Equiv>");
				
				_t = __t230;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t231 = _t;
				MyNode tmp36_AST_in = (MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				
					printToString("<Mul>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Mul>");
				
				_t = __t231;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t232 = _t;
				MyNode tmp37_AST_in = (MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				
					printToString("<Puissance>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Puissance>");
				
				_t = __t232;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t233 = _t;
				MyNode tmp38_AST_in = (MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				
					printToString("<Div>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Div>");
				
				_t = __t233;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t234 = _t;
				MyNode tmp39_AST_in = (MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				
					printToString("<Modulo>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Modulo>");
				
				_t = __t234;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t235 = _t;
				MyNode tmp40_AST_in = (MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				
					printToString("<Plus>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Plus>");
				
				_t = __t235;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t236 = _t;
				MyNode tmp41_AST_in = (MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<UMinus>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</UMinus>");
				
				_t = __t236;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t237 = _t;
				MyNode tmp42_AST_in = (MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				
					printToString("<Plus>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Plus>");
				
				_t = __t237;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t238 = _t;
				MyNode tmp43_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<Minus>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Minus>");
				
				_t = __t238;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t239 = _t;
				MyNode tmp44_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				
					printToString("<Equal>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Equal>");
				
				_t = __t239;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t240 = _t;
				MyNode tmp45_AST_in = (MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				
					printToString("<Less>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Less>");
				
				_t = __t240;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t241 = _t;
				MyNode tmp46_AST_in = (MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				
					printToString("<Greater>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Greater>");
				
				_t = __t241;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t242 = _t;
				MyNode tmp47_AST_in = (MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<NotEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotEqual>");
				
				_t = __t242;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t243 = _t;
				MyNode tmp48_AST_in = (MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<LessEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</LessEqual>");
				
				_t = __t243;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t244 = _t;
				MyNode tmp49_AST_in = (MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				
					printToString("<GreaterEqual>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</GreaterEqual>");
				
				_t = __t244;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t245 = _t;
				MyNode tmp50_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				
					printToString("<In>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</In>");
				
				_t = __t245;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t246 = _t;
				MyNode tmp51_AST_in = (MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				
					printToString("<NotIn>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotIn>");
				
				_t = __t246;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t247 = _t;
				MyNode tmp52_AST_in = (MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				
					printToString("<SubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SubSet>");
				
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t248 = _t;
				MyNode tmp53_AST_in = (MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				
					printToString("<NotSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotSubSet>");
				
				_t = __t248;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t249 = _t;
				MyNode tmp54_AST_in = (MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				
					printToString("<StrictSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</StrictSubSet>");
				
				_t = __t249;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t250 = _t;
				MyNode tmp55_AST_in = (MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				
					printToString("<NotStrictSubSet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</NotStrictSubSet>");
				
				_t = __t250;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t251 = _t;
				MyNode tmp56_AST_in = (MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				
					printToString("<ConcatSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</ConcatSeq>");
				
				_t = __t251;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t252 = _t;
				MyNode tmp57_AST_in = (MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				
					printToString("<>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t252;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t253 = _t;
				MyNode tmp58_AST_in = (MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t253;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t254 = _t;
				MyNode tmp59_AST_in = (MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				
					printToString("<PrefixSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PrefixSeq>");
				
				_t = __t254;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t255 = _t;
				MyNode tmp60_AST_in = (MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				
					printToString("<SuffixSeq>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SuffixSeq>");
				
				_t = __t255;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t256 = _t;
				MyNode tmp61_AST_in = (MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t256;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t257 = _t;
				MyNode tmp62_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				
					printToString("<PartialFunc>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialFunc>");
				
				_t = __t257;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t258 = _t;
				MyNode tmp63_AST_in = (MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				
					printToString("<TotalFunc>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalFunc>");
				
				_t = __t258;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t259 = _t;
				MyNode tmp64_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				
					printToString("<PartialInj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialInj>");
				
				_t = __t259;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t260 = _t;
				MyNode tmp65_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				
					printToString("<TotalInj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalInj>");
				
				_t = __t260;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t261 = _t;
				MyNode tmp66_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				
					printToString("<PartialSurj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</PartialSurj>");
				
				_t = __t261;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t262 = _t;
				MyNode tmp67_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				
					printToString("<TotalSurj>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalSurj>");
				
				_t = __t262;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t263 = _t;
				MyNode tmp68_AST_in = (MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				
					printToString("<TotalBij>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</TotalBij>");
				
				_t = __t263;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t264 = _t;
				MyNode tmp69_AST_in = (MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				
					printToString("<DomRestrict>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</DomRestrict>");
				
				_t = __t264;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t265 = _t;
				MyNode tmp70_AST_in = (MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				
					printToString("<RanRestrict>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RanRestrict>");
				
				_t = __t265;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t266 = _t;
				MyNode tmp71_AST_in = (MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				
					printToString("<DomSubstract>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</DomSubstract>");
				
				_t = __t266;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t267 = _t;
				MyNode tmp72_AST_in = (MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				
					printToString("<RanSubstract>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RanSubstract>");
				
				_t = __t267;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t268 = _t;
				MyNode tmp73_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				
					printToString("<OverRideFwd>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</OverRideFwd>");
				
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t269 = _t;
				MyNode tmp74_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				
					printToString("<OverRideBck>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</OverRideBck>");
				
				_t = __t269;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t270 = _t;
				MyNode tmp75_AST_in = (MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				
					printToString("<RelProd>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RelProd>");
				
				_t = __t270;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t271 = _t;
				MyNode tmp76_AST_in = (MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				
					printToString("<UnionSets>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</UnionSets>");
				
				_t = __t271;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t272 = _t;
				MyNode tmp77_AST_in = (MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				
					printToString("<InterSets>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</InterSets>");
				
				_t = __t272;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t273 = _t;
				MyNode tmp78_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				
					printToString("<Maplet>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</Maplet>");
				
				_t = __t273;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t274 = _t;
				MyNode tmp79_AST_in = (MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t274;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t275 = _t;
				MyNode tmp80_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				
					printToString("<Ran>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Ran>");
				
				_t = __t275;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t276 = _t;
				MyNode tmp81_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				
					printToString("<Dom>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</Dom>");
				
				_t = __t276;
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
				AST __t277 = _t;
				MyNode tmp82_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				
					printToString("<BoolEvaluation>");
				
				predicate(_t);
				_t = _retTree;
				
					printToString("</BoolEvaluation>");
				
				_t = __t277;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t278 = _t;
				MyNode tmp83_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				_t = __t278;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t279 = _t;
				MyNode tmp84_AST_in = (MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				
					printToString("<SetRange>");
				
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</SetRange>");
				
				_t = __t279;
				_t = _t.getNextSibling();
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t280 = _t;
				MyNode tmp85_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				_t = __t280;
				_t = _t.getNextSibling();
				break;
			}
			case B_SEQEMPTY:
			{
				{
				
					printToString("<SeqEmpty>");
				
				MyNode tmp86_AST_in = (MyNode)_t;
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
	
	public final void uses(AST _t) throws RecognitionException {
		
		MyNode uses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t30 = _t;
			MyNode tmp87_AST_in = (MyNode)_t;
			match(_t,LITERAL_USES);
			_t = _t.getFirstChild();
			
				printToStringln("<Uses>");
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("</Uses>");
			
			_t = __t30;
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
		
		try {      // for error handling
			AST __t32 = _t;
			MyNode tmp88_AST_in = (MyNode)_t;
			match(_t,LITERAL_INCLUDES);
			_t = _t.getFirstChild();
			
				printToStringln("<Includes>");
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("</Includes>");
			
			_t = __t32;
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
		
		try {      // for error handling
			AST __t34 = _t;
			MyNode tmp89_AST_in = (MyNode)_t;
			match(_t,LITERAL_SEES);
			_t = _t.getFirstChild();
			
				printToStringln("<Sees>");
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("</Sees>");
			
			_t = __t34;
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
		
		try {      // for error handling
			AST __t28 = _t;
			MyNode tmp90_AST_in = (MyNode)_t;
			match(_t,LITERAL_EXTENDS);
			_t = _t.getFirstChild();
			
				printToStringln("<Extends>");
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("</Extends>");
			
			_t = __t28;
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
		
		try {      // for error handling
			AST __t46 = _t;
			MyNode tmp91_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROMOTES);
			_t = _t.getFirstChild();
			
				printToStringln("<Promotes>");
			
			listNames(_t);
			_t = _retTree;
			
				printToStringln("</Promotes>");
			
			_t = __t46;
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
		
		try {      // for error handling
			AST __t44 = _t;
			MyNode tmp92_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPORTS);
			_t = _t.getFirstChild();
			
				printToStringln("<Imports>");
			
			listInstanciation(_t);
			_t = _retTree;
			
				printToStringln("</Imports>");
			
			_t = __t44;
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
			
				printToString("<listInstanciation>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t40 = _t;
				MyNode tmp93_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<B_COMMA>");
				
				listInstanciation(_t);
				_t = _retTree;
				paramRenameValuation(_t);
				_t = _retTree;
				
					printToString("</B_COMMA>");
				
				_t = __t40;
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
			
				printToString("<listInstanciation>");
			
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
			
				printToString("<listNames>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t37 = _t;
				MyNode tmp94_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<B_COMMA>");
				
				listNames(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				
					printToString("</B_COMMA>");
				
				_t = __t37;
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
			
				printToString("</listNames>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void paramRenameValuation(AST _t) throws RecognitionException {
		
		MyNode paramRenameValuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				
					printToStringln("<Declaration>");
				
				AST __t42 = _t;
				MyNode tmp95_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString("<B_PARENT>");
				
				paramRenameValuation(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString("</B_PARENT>");
				
				_t = __t42;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				nameRenamed(_t);
				_t = _retTree;
				
					printToStringln("</Declaration>");
				
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
				AST __t210 = _t;
				MyNode tmp96_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				new_predicate(_t);
				_t = _retTree;
				_t = __t210;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
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
			
				printToStringln("<sets_declaration>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t59 = _t;
				MyNode tmp97_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToStringln("<B_SEMICOLON>");
				
				sets_declaration(_t);
				_t = _retTree;
				sets_declaration(_t);
				_t = _retTree;
				
					printToStringln("</B_SEMICOLON>");
				
				_t = __t59;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t60 = _t;
				MyNode tmp98_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToStringln("<B_COMMA>");
				
				sets_declaration(_t);
				_t = _retTree;
				sets_declaration(_t);
				_t = _retTree;
				
					printToStringln("</B_COMMA>");
				
				_t = __t60;
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
			
				printToStringln("</sets_declaration>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void set_declaration(AST _t) throws RecognitionException {
		
		MyNode set_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToStringln("<set_declaration>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_EQUAL)) {
				AST __t63 = _t;
				MyNode tmp99_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				
					printToStringln("<B_EQUAL>");
				
				an_id(_t);
				_t = _retTree;
				valuation_set(_t);
				_t = _retTree;
				
					printToStringln("</B_EQUAL>");
				
				_t = __t63;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				an_id(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				printToStringln("<set_declaration>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void valuation_set(AST _t) throws RecognitionException {
		
		MyNode valuation_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<valuation_set>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t69 = _t;
				MyNode tmp100_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				
					printToString("<B_CURLYOPEN>");
				
				list_couple(_t);
				_t = _retTree;
				
					printToString("</B_CURLYOPEN>");
				
				_t = __t69;
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
				AST __t70 = _t;
				MyNode tmp101_AST_in = (MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				
					printToString("<B_MULT>");
				
				valuation_set(_t);
				_t = _retTree;
				valuation_set(_t);
				_t = _retTree;
				
					printToString("</B_MULT>");
				
				_t = __t70;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t71 = _t;
				MyNode tmp102_AST_in = (MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				
					printToString("<B_ADD>");
				
				valuation_set(_t);
				_t = _retTree;
				valuation_set(_t);
				_t = _retTree;
				
					printToString("</B_ADD>");
				
				_t = __t71;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t72 = _t;
				MyNode tmp103_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<B_MINUS>");
				
				valuation_set(_t);
				_t = _retTree;
				valuation_set(_t);
				_t = _retTree;
				
					printToString("</B_MINUS>");
				
				_t = __t72;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			{
				an_id(_t);
				_t = _retTree;
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
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			
				printToString("</valuation_set>");
			
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
				AST __t65 = _t;
				MyNode tmp104_AST_in = (MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				
					printToStringln("<rec>");
				
				listrecord(_t);
				_t = _retTree;
				
					printToStringln("</rec>");
				
				_t = __t65;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t66 = _t;
				MyNode tmp105_AST_in = (MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				
					printToStringln("<struct>");
				
				listrecord(_t);
				_t = _retTree;
				
					printToString("</struc>");
				
				_t = __t66;
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
		
		try {      // for error handling
			
				printToString("<listrecord>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t75 = _t;
				MyNode tmp106_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<B_COMMA>");
				
				listrecord(_t);
				_t = _retTree;
				a_record(_t);
				_t = _retTree;
				
					printToString("</B_COMMA>");
				
				_t = __t75;
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
			
				printToString("</listrecord>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_couple(AST _t) throws RecognitionException {
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<list_couple>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t78 = _t;
				MyNode tmp107_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<B_COMMA>");
				
				list_couple(_t);
				_t = _retTree;
				couple(_t);
				_t = _retTree;
				
					printToString("</B_COMMA>");
				
				_t = __t78;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				couple(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				printToString("</list_couple>");
			
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
				MyNode tmp108_AST_in = (MyNode)_t;
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				printToString("<INT>");
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp109_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				printToString("<INTEGER>");
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp110_AST_in = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				printToString("<BOOL>");
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp111_AST_in = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				printToString("<NAT>");
				break;
			}
			case 92:
			{
				MyNode tmp112_AST_in = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				printToString("<NAT1>");
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp113_AST_in = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				printToString("<NATURAL>");
				break;
			}
			case 94:
			{
				MyNode tmp114_AST_in = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				printToString("<NATURAL1>");
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp115_AST_in = (MyNode)_t;
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				printToString("<STRING>");
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp116_AST_in = (MyNode)_t;
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
	
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<a_record>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t92 = _t;
				MyNode tmp117_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				
					printToString("<B_SELECTOR>");
				
				an_id(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</B_SELECTOR>");
				
				_t = __t92;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				predicate(_t);
				_t = _retTree;
			}
			else {
				throw new NoViableAltException(_t);
			}
			
			}
			
				printToString("</a_record>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void couple(AST _t) throws RecognitionException {
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<couple>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t81 = _t;
				MyNode tmp118_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				
					printToString("<B_MAPLET>");
				
				a_set_value(_t);
				_t = _retTree;
				a_set_value(_t);
				_t = _retTree;
				
					printToString("</B_MAPLET>");
				
				_t = __t81;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t82 = _t;
				MyNode tmp119_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					printToString("<B_LPARENT>");
				
				parent_couple(_t);
				_t = _retTree;
				
					printToString("</B_LPARENT>");
				
				_t = __t82;
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
			
				printToString("</couple>");
			
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
		
		try {      // for error handling
			
				printToString("<a_set_value>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_IDENTIFIER:
			{
				name = _t==ASTNULL ? null : (MyNode)_t;
				an_id(_t);
				_t = _retTree;
				
					printToString(name);
				
				break;
			}
			case B_MINUS:
			{
				AST __t89 = _t;
				MyNode tmp120_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				
					printToString("<B_MINUS>");
				
				MyNode tmp121_AST_in = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					printToString("</B_MINUS>");
				
				_t = __t89;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp122_AST_in = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp123_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp124_AST_in = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
			}
			
				printToString("</a_set_value>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void parent_couple(AST _t) throws RecognitionException {
		
		MyNode parent_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToString("<parent_couple>");
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t85 = _t;
				MyNode tmp125_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				
					printToString("<B_MAPLET>");
				
				a_set_value(_t);
				_t = _retTree;
				a_set_value(_t);
				_t = _retTree;
				
					printToString("</B_MAPLET>");
				
				_t = __t85;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t86 = _t;
				MyNode tmp126_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
					printToString("<B_COMMA>");
				
				a_set_value(_t);
				_t = _retTree;
				a_set_value(_t);
				_t = _retTree;
				
					printToString("</B_COMMA>");
				
				_t = __t86;
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
			
				printToString("</parent_couple>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_valuation(AST _t) throws RecognitionException {
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				
					printToStringln("<list_valuation>");
				
				AST __t96 = _t;
				MyNode tmp127_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToStringln("<B_SEMICOLON>");
				
				list_valuation(_t);
				_t = _retTree;
				set_valuation(_t);
				_t = _retTree;
				
					printToStringln("</B_SEMICOLON>");
				
				_t = __t96;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EQUAL)) {
				set_valuation(_t);
				_t = _retTree;
				
					printToStringln("</list_valuation>");
				
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
		
		try {      // for error handling
			AST __t98 = _t;
			MyNode tmp128_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			
				printToStringln("<B_EQUAL>");
			
			an_id(_t);
			_t = _retTree;
			new_set_or_constant_valuation(_t);
			_t = _retTree;
			
				printToStringln("</B_EQUAL>");
			
			_t = __t98;
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
		
		try {      // for error handling
			AST __t101 = _t;
			MyNode tmp129_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			
				printToStringln("<B_EQUAL>");
			
			an_id(_t);
			_t = _retTree;
			interval_declaration(_t);
			_t = _retTree;
			
				printToStringln("</B_EQUAL>");
			
			_t = __t101;
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
		
		try {      // for error handling
			AST __t103 = _t;
			MyNode tmp130_AST_in = (MyNode)_t;
			match(_t,B_RANGE);
			_t = _t.getFirstChild();
			
				printToStringln("<B_RANGE>");
			
			predicate(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			
				printToStringln("</B_RANGE>");
			
			_t = __t103;
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
		
		try {      // for error handling
			AST __t105 = _t;
			MyNode tmp131_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			
				printToStringln("<B_EQUAL>");
			
			an_id(_t);
			_t = _retTree;
			an_id(_t);
			_t = _retTree;
			
				printToStringln("</B_EQUAL>");
			
			_t = __t105;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_DEF)) {
				AST __t119 = _t;
				MyNode tmp132_AST_in = (MyNode)_t;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				
					printToStringln("<LIST_DEF>");
				
				list_def(_t);
				_t = _retTree;
				definition(_t);
				_t = _retTree;
				
					printToStringln("</LIST_DEF>");
				
				_t = __t119;
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
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_DOUBLE_EQUAL)) {
				AST __t121 = _t;
				MyNode tmp133_AST_in = (MyNode)_t;
				match(_t,B_DOUBLE_EQUAL);
				_t = _t.getFirstChild();
				
					printToString("<Definition>");
					printToString("<Header>");
				
				paramName(_t);
				_t = _retTree;
				
					printToString("</Header>");
				
				formalText(_t);
				_t = _retTree;
				
					printToString("</Definition>");
				
				_t = __t121;
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
			switch ( _t.getType()) {
			case EXP_DEF:
			{
				
					printToString("<EXP_DEF>");
				
				AST __t123 = _t;
				MyNode tmp134_AST_in = (MyNode)_t;
				match(_t,EXP_DEF);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				_t = __t123;
				_t = _t.getNextSibling();
				
					printToString("</EXP_DEF>");
				
				break;
			}
			case SUBST_DEF:
			{
				
					printToString("<SUBST_DEF>");
				
				AST __t124 = _t;
				MyNode tmp135_AST_in = (MyNode)_t;
				match(_t,SUBST_DEF);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				_t = __t124;
				_t = _t.getNextSibling();
				
					printToString("</SUBST_DEF>");
				
				break;
			}
			case OP_DEF:
			{
				
					printToString("<OP_DEF>");
				
				operation(_t);
				_t = _retTree;
				
					printToString("</OP_DEF>");
				
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
	
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t141 = _t;
				MyNode tmp136_AST_in = (MyNode)_t;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				
					printToStringln("<Parallel>");
				
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					printToStringln("</Parallel>");
				
				_t = __t141;
				_t = _t.getNextSibling();
				break;
			}
			case SEQUENTIAL:
			{
				AST __t142 = _t;
				MyNode tmp137_AST_in = (MyNode)_t;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				
					printToStringln("<Sequence>");
				
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					printToStringln("</Sequence>");
				
				_t = __t142;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_skip:
			{
				AST __t143 = _t;
				MyNode tmp138_AST_in = (MyNode)_t;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				
					printToString("<Skip> </Skip>");
				
				_t = __t143;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t144 = _t;
				MyNode tmp139_AST_in = (MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				
					printToStringln("<Block>");
				
				instruction(_t);
				_t = _retTree;
				
					printToString("</Block>");
				
				_t = __t144;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t145 = _t;
				MyNode tmp140_AST_in = (MyNode)_t;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				
					printToStringln("<Pre>");
				
				predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					printToStringln("</Pre>");
				
				_t = __t145;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t146 = _t;
				MyNode tmp141_AST_in = (MyNode)_t;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				
				printToStringln("<Assert>");
				
				predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
				printToString("</Assert>");
				
				_t = __t146;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_IF:
			{
				AST __t147 = _t;
				MyNode tmp142_AST_in = (MyNode)_t;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				
				printToStringln("<If>");
				
				predicate(_t);
				_t = _retTree;
				branche_then(_t);
				_t = _retTree;
				{
				_loop149:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
					}
					else {
						break _loop149;
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
				
					printToString("</If>");
				
				_t = __t147;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t151 = _t;
				MyNode tmp143_AST_in = (MyNode)_t;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				
					printToStringln("<Choice>");
				
				list_or(_t);
				_t = _retTree;
				
					printToString("</Choice>");
				
				_t = __t151;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CASE:
			{
				AST __t152 = _t;
				MyNode tmp144_AST_in = (MyNode)_t;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				
				printToStringln("<Case>");
				
				predicate(_t);
				_t = _retTree;
				branche_either(_t);
				_t = _retTree;
				{
				_loop154:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
					}
					else {
						break _loop154;
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
				
				printToStringln("</Case>");
				
				_t = __t152;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ANY:
			{
				AST __t156 = _t;
				MyNode tmp145_AST_in = (MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				
					printToStringln("<Any>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					printToStringln("</Any>");
				
				_t = __t156;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_LET:
			{
				AST __t157 = _t;
				MyNode tmp146_AST_in = (MyNode)_t;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				
				printToStringln("<Let>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				list_equal(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					printToStringln("</Let>");
				
				_t = __t157;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t158 = _t;
				MyNode tmp147_AST_in = (MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				
				printToStringln("<Select>");
				
				predicate(_t);
				_t = _retTree;
				branche_then(_t);
				_t = _retTree;
				{
				_loop160:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
					}
					else {
						break _loop160;
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
				
				printToStringln("</Select>");
				
				_t = __t158;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t162 = _t;
				MyNode tmp148_AST_in = (MyNode)_t;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				
				printToStringln("<While>");
				
				predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				variant_or_no(_t);
				_t = _retTree;
				
				printToStringln("</While>");
				
				_t = __t162;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VAR:
			{
				AST __t163 = _t;
				MyNode tmp149_AST_in = (MyNode)_t;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				
				printToStringln("<Var>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
				printToStringln("</Var>");
				
				_t = __t163;
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
	
	public final void operation(AST _t) throws RecognitionException {
		
		MyNode operation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToStringln("<Operation>");
			
			AST __t136 = _t;
			MyNode tmp150_AST_in = (MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			operationHeader(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			_t = __t136;
			_t = _t.getNextSibling();
			
				printToStringln("</Operation>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_assertions(AST _t) throws RecognitionException {
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t128 = _t;
				MyNode tmp151_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToString("<B_SEMICOLON>");
				
				list_assertions(_t);
				_t = _retTree;
				list_assertions(_t);
				_t = _retTree;
				
					printToString("</B_SEMICOLON>");
				
				_t = __t128;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t134 = _t;
				MyNode tmp152_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToString("<B_SEMICOLON>");
				
				listOperation(_t);
				_t = _retTree;
				operation(_t);
				_t = _retTree;
				
					printToString("</B_SEMICOLON>");
				
				_t = __t134;
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
	
	public final void operationHeader(AST _t) throws RecognitionException {
		
		MyNode operationHeader_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
				printToStringln("<Header>");
			
			{
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				AST __t139 = _t;
				MyNode tmp153_AST_in = (MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				
					printToStringln("<Results>");
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					printToStringln("</Results>");
				
				paramName(_t);
				_t = _retTree;
				_t = __t139;
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
			
				printToStringln("</Header>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void branche_then(AST _t) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t173 = _t;
			MyNode tmp154_AST_in = (MyNode)_t;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			
			printToStringln("<Then>");
			
			instruction(_t);
			_t = _retTree;
			
			printToStringln("</Then>");
			
			_t = __t173;
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
		
		try {      // for error handling
			AST __t177 = _t;
			MyNode tmp155_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			
			printToStringln("<Elsif>");
			
			predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			
			printToStringln("</Elsif>");
			
			_t = __t177;
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
		
		try {      // for error handling
			AST __t175 = _t;
			MyNode tmp156_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			
			printToStringln("<Else>");
			
			instruction(_t);
			_t = _retTree;
			
			printToStringln("</Else>");
			
			_t = __t175;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t165 = _t;
				MyNode tmp157_AST_in = (MyNode)_t;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				
				printToStringln("<Or>");
				
				list_or(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
				printToStringln("</Or>");
				
				_t = __t165;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_4.member(_t.getType()))) {
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
		
		try {      // for error handling
			AST __t169 = _t;
			MyNode tmp158_AST_in = (MyNode)_t;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			
			printToStringln("<Either>");
			
			predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			
			printToStringln("</Either>");
			
			_t = __t169;
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
			AST __t171 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			_t = __t171;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_AND)) {
				AST __t184 = _t;
				MyNode tmp159_AST_in = (MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				
				printToStringln("<B_AND>");
				
				list_equal(_t);
				_t = _retTree;
				an_equal(_t);
				_t = _retTree;
				
				printToStringln("<B_AND>");
				
				_t = __t184;
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
		
		try {      // for error handling
			AST __t167 = _t;
			MyNode tmp160_AST_in = (MyNode)_t;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			
			printToStringln("<When>");
			
			predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			
			printToStringln("</When>");
			
			_t = __t167;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				AST __t179 = _t;
				MyNode tmp161_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				
				printToStringln("<Variant>");
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</Variant>");
				
				AST __t180 = _t;
				MyNode tmp162_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				
				printToStringln("<Invariant>");
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</Invariant>");
				
				_t = __t180;
				_t = _t.getNextSibling();
				_t = __t179;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t181 = _t;
				MyNode tmp163_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				
				printToStringln("<Invariant>");
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</Invariant>");
				
				AST __t182 = _t;
				MyNode tmp164_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				
				printToStringln("<Variant>");
				
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</Variant>");
				
				_t = __t182;
				_t = _t.getNextSibling();
				_t = __t181;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t200 = _t;
				MyNode tmp165_AST_in = (MyNode)_t;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				
				printToStringln("<B_SIMPLESUBST>");
				
				list_func_call(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</B_SIMPLESUBST>");
				
				_t = __t200;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			{
				AST __t201 = _t;
				MyNode tmp166_AST_in = (MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				
				printToStringln("<B_OUT>");
				
				list_func_call(_t);
				_t = _retTree;
				func_call(_t);
				_t = _retTree;
				
				printToStringln("</B_OUT>");
				
				_t = __t201;
				_t = _t.getNextSibling();
				break;
			}
			case INSET:
			{
				AST __t202 = _t;
				MyNode tmp167_AST_in = (MyNode)_t;
				match(_t,INSET);
				_t = _t.getFirstChild();
				
				printToStringln("<INSET>");
				
				list_func_call(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</INSET>");
				
				_t = __t202;
				_t = _t.getNextSibling();
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t203 = _t;
				MyNode tmp168_AST_in = (MyNode)_t;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				
				printToStringln("<B_BECOME_ELEM>");
				
				list_func_call(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
				printToStringln("</B_BECOME_ELEM>");
				
				_t = __t203;
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
		
		try {      // for error handling
			
			printToStringln("<B_EQUAL>");
			
			AST __t186 = _t;
			MyNode tmp169_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			an_id(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			_t = __t186;
			_t = _t.getNextSibling();
			
			printToStringln("</B_EQUAL>");
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void func_call(AST _t) throws RecognitionException {
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t188 = _t;
				MyNode tmp170_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				
				printToStringln("<B_TILDE>");
				
				func_call(_t);
				_t = _retTree;
				
				printToStringln("</B_TILDE>");
				
				_t = __t188;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t189 = _t;
				MyNode tmp171_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				
				printToStringln("<APPLY_TO>");
				
				func_call(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
				
				printToStringln("</APPLY_TO>");
				
				_t = __t189;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t190 = _t;
				MyNode tmp172_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
				printToStringln("<B_LPAREN>");
				
				func_call(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
				
				printToStringln("</B_LPAREN>");
				
				_t = __t190;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t191 = _t;
				MyNode tmp173_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				
				printToStringln("<B_QUOTEIDENT>");
				
				func_call(_t);
				_t = _retTree;
				func_call(_t);
				_t = _retTree;
				
				printToStringln("<B_QUOTEIDENT>");
				
				_t = __t191;
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
				AST __t195 = _t;
				MyNode tmp174_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				
				printToStringln("<FUNC_CALL_PARAM>");
				
				afc(_t);
				_t = _retTree;
				listPredicate(_t);
				_t = _retTree;
				
				printToStringln("</FUNC_CALL_PARAM>");
				
				_t = __t195;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t196 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				
				printToStringln("<B_QUOTEIDENT>");
				
				afc(_t);
				_t = _retTree;
				afc(_t);
				_t = _retTree;
				
				printToStringln("</B_QUOTEIDENT>");
				
				_t = __t196;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t223 = _t;
				MyNode tmp175_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listPredicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t223;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
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
				AST __t198 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				
				printToStringln("<B_COMMA>");
				
				list_func_call(_t);
				_t = _retTree;
				a_func_call(_t);
				_t = _retTree;
				
				printToStringln("</B_COMMA>");
				
				_t = __t198;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
	
	public final void analyze_PO(AST _t) throws RecognitionException {
		
		MyNode analyze_PO_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==PO)) {
				AST __t206 = _t;
				MyNode tmp176_AST_in = (MyNode)_t;
				match(_t,PO);
				_t = _t.getFirstChild();
				analyze_PO(_t);
				_t = _retTree;
				analyze_PO(_t);
				_t = _retTree;
				_t = __t206;
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
			AST __t208 = _t;
			MyNode tmp177_AST_in = (MyNode)_t;
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
			
			_t = __t208;
			_t = _t.getNextSibling();
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
				AST __t212 = _t;
				MyNode tmp178_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t212;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t213 = _t;
				MyNode tmp179_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t213;
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
	
	public final void nameRenamedDecorated(AST _t) throws RecognitionException {
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t217 = _t;
				MyNode tmp180_AST_in = (MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				_t = __t217;
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
				AST __t219 = _t;
				MyNode tmp181_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				_t = __t219;
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
				AST __t221 = _t;
				MyNode tmp182_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				_t = __t221;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp183_AST_in = (MyNode)_t;
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
				AST __t286 = _t;
				MyNode tmp184_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				
				printToString("<Tilde>");
				
				predicate(_t);
				_t = _retTree;
				
				printToString("</Tilde>");
				
				_t = __t286;
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
				AST __t287 = _t;
				MyNode tmp185_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				
					printToString("<PredParen>");
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					printToString("</PredParen>");
				
				_t = __t287;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t288 = _t;
				MyNode tmp186_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				
					printToString("<PredParen>");
				
				pred_func_composition(_t);
				_t = _retTree;
				
					printToString("</PredParen>");
				
				_t = __t288;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t289 = _t;
				MyNode tmp187_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t289;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t290 = _t;
				MyNode tmp188_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t290;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				{
				
					printToString("<TRUE>");
				
				MyNode tmp189_AST_in = (MyNode)_t;
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
				
				MyNode tmp190_AST_in = (MyNode)_t;
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
				AST __t283 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t283;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t284 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				cvalue_set(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t284;
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
				AST __t300 = _t;
				MyNode tmp191_AST_in = (MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				printToString("<ForAll>");
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				printToString("</ForAll>");
				_t = __t300;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t301 = _t;
				MyNode tmp192_AST_in = (MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				printToString("<Exists>");
				list_var(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				printToString("</Exists>");
				_t = __t301;
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
				AST __t303 = _t;
				MyNode tmp193_AST_in = (MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				
					printToString("<Lambda>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</Lambda>");
				
				_t = __t303;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t304 = _t;
				MyNode tmp194_AST_in = (MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				
					printToString("<PI>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</PI>");
				
				_t = __t304;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t305 = _t;
				MyNode tmp195_AST_in = (MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				
					printToString("<SIGMA>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</SIGMA>");
				
				_t = __t305;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t306 = _t;
				MyNode tmp196_AST_in = (MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				
					printToString("<UnionQ>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</UnionQ>");
				
				_t = __t306;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t307 = _t;
				MyNode tmp197_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				
					printToString("<InterQ>");
				
				q_operande(_t);
				_t = _retTree;
				
					printToString("</InterQ>");
				
				_t = __t307;
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
				AST __t312 = _t;
				MyNode tmp198_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				_t = __t312;
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
				AST __t294 = _t;
				MyNode tmp199_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				
					printToString("<RelSeqComp>");
				
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</RelSeqComp>");
				
				_t = __t294;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t295 = _t;
				MyNode tmp200_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				
					printToString("<ParallelComp>");
				
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				
					printToString("</ParallelComp>");
				
				_t = __t295;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t296 = _t;
				MyNode tmp201_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t296;
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
			AST __t309 = _t;
			MyNode tmp202_AST_in = (MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			_t = __t309;
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
		"B_LEADSTO",
		"\"DYNAMICS\"",
		"B_PREC",
		"PO",
		"APO",
		"BTRUE"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[8];
		data[0]=-3530822108395339808L;
		data[1]=215083330304031L;
		data[3]=8L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[8];
		data[0]=-3530822108395339808L;
		data[1]=215220769251359L;
		data[3]=8L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -9214224099843244000L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[8];
		data[0]=-3530822108395339808L;
		data[1]=215083330297887L;
		data[3]=8L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 96L, 117093590311632912L, 33366376252964864L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 96L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	}
	
