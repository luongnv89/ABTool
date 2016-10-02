// $ANTLR 2.7.6 (2005-12-22): "BCodeGenerator.g" -> "BCodeGenerator.java"$

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

// Necessaire pour gerer la classe Date pour creer 
// des variables temporelles uniques
	import java.util.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    	import ABTOOLS.ANTLR_TOOLS.*;
    	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.PRINTING.*;
    	import ABTOOLS.CODE_GENERATOR.*;


public class BCodeGenerator extends antlr.TreeParser       implements BCodeGeneratorTokenTypes
 {

// Module description
    	String module = "BCodeGenerator";

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


// Manage the code Generator
	public		int ADA		= 10;
    	public      	int JAVA        = 11;

    	public      	int currenttarget = 0;

    	private static CodeGenerator cg;

 	public void setCodeGenerator ( CodeGenerator newcg)
 	{
 		cg = newcg;
 	}

	public void	selectTarget(int target )
	{
		currenttarget = target;

// Contrainte de conception
// JAVA n'est pas fortiche avec le switch, on ne peut pas mettre de chaine 
// de caractere dans le switch et le case doit etre valuee

		if (target == JAVA) 
			cg = new CG_JAVA();
		else if (target == ADA)
            		cg = new CG_ADA();
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

		if (currenttarget == JAVA)
			return ("JAVA");
		else if (currenttarget == ADA)
			return ("ADA");
		else
		{
			return (null);
		}
	}


	void InitializeModule()
	{
      
	}

	void FinalizeModule()
	{
	
	}
public BCodeGenerator() {
	tokenNames = _tokenNames;
}

/** 
 *  Root Rule
 **/
	public final void composant(AST _t) throws RecognitionException {
		
		MyNode composant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/**
 * THREE type of B composants simple name or with one or more parameters
 **/
	public final void machine(AST _t) throws RecognitionException {
		
		MyNode machine_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp = "To Be Defined";
		
		
		try {      // for error handling
			AST __t4 = _t;
			MyNode tmp1_AST_in = (MyNode)_t;
			match(_t,LITERAL_MACHINE);
			_t = _t.getFirstChild();
			tmp=pparamName(_t);
			_t = _retTree;
			
			System.err.println(errors.NotImplementation (tmp)); 
			
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
		
		String tmp = "To Be Defined";
		
		
		try {      // for error handling
			AST __t6 = _t;
			MyNode tmp2_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINEMENT);
			_t = _t.getFirstChild();
			tmp=pparamName(_t);
			_t = _retTree;
			
			System.err.println(errors.NotImplementation (tmp)); 
			
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
		
		String tmp = "To Be Defined";
		
		
		try {      // for error handling
			AST __t10 = _t;
			MyNode tmp3_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPLEMENTATION);
			_t = _t.getFirstChild();
			
				InitializeModule();
			
			tmp=pparamName(_t);
			_t = _retTree;
			
			System.out.println("Module a traiter :"+tmp);
				cg.BeginGen(tmp);
			
			
			clauses(_t);
			_t = _retTree;
			
			cg.EndGen();
				FinalizeModule();
			
			_t = __t10;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  pparamName(AST _t) throws RecognitionException {
		String currentName;
		
		MyNode pparamName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode name1 = null;
		
		currentName = "To Be Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t8 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					currentName = name.getText();
				
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t8;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					currentName = name1.getText();
				
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
		return currentName;
	}
	
	public final void listTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t18 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t18;
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
	
	public final void clauses(AST _t) throws RecognitionException {
		
		MyNode clauses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			{
			_loop13:
			do {
				if (_t==null) _t=ASTNULL;
				if (((_t.getType() >= LITERAL_EXTENDS && _t.getType() <= LITERAL_OPERATIONS))) {
					clause(_t);
					_t = _retTree;
				}
				else {
					break _loop13;
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
			AST __t43 = _t;
			MyNode tmp6_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINES);
			_t = _t.getFirstChild();
			
				;
			
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				;
			
			_t = __t43;
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
			AST __t51 = _t;
			MyNode tmp7_AST_in = (MyNode)_t;
			match(_t,LITERAL_SETS);
			_t = _t.getFirstChild();
			
				;
			
			sets_declaration(_t);
			_t = _retTree;
			
				;
			
			_t = __t51;
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
		MyNode tt = null;
		
		try {      // for error handling
			AST __t80 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_VALUES);
			_t = _t.getFirstChild();
			
				;
			
			list_valuation(_t);
			_t = _retTree;
			
				;
			
			_t = __t80;
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
				AST __t45 = _t;
				MyNode tmp8_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t45;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t46 = _t;
				MyNode tmp9_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t46;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t47 = _t;
				MyNode tmp10_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t47;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t48 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t48;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t49 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t49;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t93 = _t;
			MyNode tmp13_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROPERTIES);
			_t = _t.getFirstChild();
			tmp1=predicate(_t);
			_t = _retTree;
			_t = __t93;
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
				AST __t95 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t95;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t96 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t96;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t97 = _t;
				MyNode tmp16_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t97;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t98 = _t;
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t98;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t99 = _t;
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t99;
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
		
		String tmp;
		
		
		try {      // for error handling
			AST __t105 = _t;
			MyNode tmp19_AST_in = (MyNode)_t;
			match(_t,LITERAL_INVARIANT);
			_t = _t.getFirstChild();
			tmp=predicate(_t);
			_t = _retTree;
			_t = __t105;
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
			AST __t114 = _t;
			MyNode tmp20_AST_in = (MyNode)_t;
			match(_t,LITERAL_ASSERTIONS);
			_t = _t.getFirstChild();
			list_assertions(_t);
			_t = _retTree;
			_t = __t114;
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
			AST __t107 = _t;
			MyNode tmp21_AST_in = (MyNode)_t;
			match(_t,LITERAL_DEFINITIONS);
			_t = _t.getFirstChild();
			list_def(_t);
			_t = _retTree;
			_t = __t107;
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
			AST __t118 = _t;
			MyNode tmp22_AST_in = (MyNode)_t;
			match(_t,LITERAL_INITIALISATION);
			_t = _t.getFirstChild();
			
			cg.Constructor();
			cg.BeginOp();
			
			instruction(_t);
			_t = _retTree;
			
			cg.EndOp();
			
			_t = __t118;
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
			AST __t120 = _t;
			MyNode tmp23_AST_in = (MyNode)_t;
			match(_t,LITERAL_OPERATIONS);
			_t = _t.getFirstChild();
			listOperation(_t);
			_t = _retTree;
			_t = __t120;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/**
 *  For all Name with parameters
 **/
	public final void paramName(AST _t) throws RecognitionException {
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode name1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t16 = _t;
				MyNode tmp24_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t16;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
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
	
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t20 = _t;
				MyNode tmp25_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				tmp=predicate(_t);
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
	
/**
 * Ne pas utiliser de fonction d'impression pour le renommage ....
 **/
	public final void nameRenamed(AST _t) throws RecognitionException {
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp26_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t194 = _t;
				MyNode tmp27_AST_in = (MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				_t = __t194;
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
	
/** 
 *  PREDICATE 
 **/
	public final String  predicate(AST _t) throws RecognitionException {
		String st;
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode t8 = null;
		
		st = "";
		String tmp1 = "Not Defined";
		String tmp2 = "Not Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_AND:
			{
				AST __t219 = _t;
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.And(tmp1, tmp2);
				
				_t = __t219;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t220 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Or(tmp1, tmp2);
				
				_t = __t220;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t221 = _t;
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("Imply -- pred x => y"));
				
				_t = __t221;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t222 = _t;
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("Equivalent -- pred x <=> y"));
				
				_t = __t222;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t223 = _t;
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Mult(tmp1, tmp2);
				
				_t = __t223;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t224 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Power(tmp1, tmp2);
				
				_t = __t224;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t225 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Div(tmp1, tmp2);
				
				_t = __t225;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t226 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Mod(tmp1, tmp2);
				
				_t = __t226;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t227 = _t;
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				st=predicate(_t);
				_t = _retTree;
				_t = __t227;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t228 = _t;
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				
					st = cg.Minus("", tmp1);
				
				_t = __t228;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t229 = _t;
				MyNode tmp35_AST_in = (MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				
					st = cg.Plus(tmp1, tmp2);
				
				_t = __t229;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t230 = _t;
				MyNode tmp36_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Minus(tmp1, tmp2);
				
				_t = __t230;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t231 = _t;
				MyNode tmp37_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Equal(tmp1, tmp2);
				
				_t = __t231;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t232 = _t;
				MyNode tmp38_AST_in = (MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Less(tmp1, tmp2);
				
				_t = __t232;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t233 = _t;
				MyNode tmp39_AST_in = (MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.Greater(tmp1, tmp2);
				
				_t = __t233;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t234 = _t;
				MyNode tmp40_AST_in = (MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.NotEqual(tmp1, tmp2);
				
				_t = __t234;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t235 = _t;
				MyNode tmp41_AST_in = (MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.LessEqual(tmp1, tmp2);
				
				_t = __t235;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t236 = _t;
				MyNode tmp42_AST_in = (MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				
					st = cg.GreaterEqual(tmp1, tmp2);
				
				_t = __t236;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t237 = _t;
				MyNode tmp43_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t237;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t238 = _t;
				MyNode tmp44_AST_in = (MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t238;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t239 = _t;
				MyNode tmp45_AST_in = (MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t239;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t240 = _t;
				MyNode tmp46_AST_in = (MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t240;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t241 = _t;
				MyNode tmp47_AST_in = (MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t241;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t242 = _t;
				MyNode tmp48_AST_in = (MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t242;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t243 = _t;
				MyNode tmp49_AST_in = (MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t243;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t244 = _t;
				MyNode tmp50_AST_in = (MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t244;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t245 = _t;
				MyNode tmp51_AST_in = (MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t245;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t246 = _t;
				MyNode tmp52_AST_in = (MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t246;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t247 = _t;
				MyNode tmp53_AST_in = (MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t248 = _t;
				MyNode tmp54_AST_in = (MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t248;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t249 = _t;
				MyNode tmp55_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t249;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t250 = _t;
				MyNode tmp56_AST_in = (MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t250;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t251 = _t;
				MyNode tmp57_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t251;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t252 = _t;
				MyNode tmp58_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t252;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t253 = _t;
				MyNode tmp59_AST_in = (MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t253;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t254 = _t;
				MyNode tmp60_AST_in = (MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t254;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t255 = _t;
				MyNode tmp61_AST_in = (MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t255;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t256 = _t;
				MyNode tmp62_AST_in = (MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t256;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t257 = _t;
				MyNode tmp63_AST_in = (MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t257;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t258 = _t;
				MyNode tmp64_AST_in = (MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t258;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t259 = _t;
				MyNode tmp65_AST_in = (MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t259;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t260 = _t;
				MyNode tmp66_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t260;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t261 = _t;
				MyNode tmp67_AST_in = (MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t261;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t262 = _t;
				MyNode tmp68_AST_in = (MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t262;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t263 = _t;
				MyNode tmp69_AST_in = (MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t263;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t264 = _t;
				MyNode tmp70_AST_in = (MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t264;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t265 = _t;
				MyNode tmp71_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t265;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t266 = _t;
				MyNode tmp72_AST_in = (MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t266;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t267 = _t;
				MyNode tmp73_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t267;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t268 = _t;
				MyNode tmp74_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t269 = _t;
				MyNode tmp75_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t269;
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
				st=cset_description(_t);
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
		return st;
	}
	
	public final void constraints(AST _t) throws RecognitionException {
		
		MyNode constraints_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp;
		
		
		try {      // for error handling
			AST __t22 = _t;
			MyNode tmp76_AST_in = (MyNode)_t;
			match(_t,LITERAL_CONSTRAINTS);
			_t = _t.getFirstChild();
			tmp=predicate(_t);
			_t = _retTree;
			_t = __t22;
			_t = _t.getNextSibling();
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
			AST __t27 = _t;
			MyNode tmp77_AST_in = (MyNode)_t;
			match(_t,LITERAL_USES);
			_t = _t.getFirstChild();
			listNames(_t);
			_t = _retTree;
			_t = __t27;
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
			AST __t29 = _t;
			MyNode tmp78_AST_in = (MyNode)_t;
			match(_t,LITERAL_INCLUDES);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t29;
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
			AST __t31 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SEES);
			_t = _t.getFirstChild();
			listNames(_t);
			_t = _retTree;
			_t = __t31;
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
			AST __t25 = _t;
			MyNode tmp79_AST_in = (MyNode)_t;
			match(_t,LITERAL_EXTENDS);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t25;
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
			AST __t41 = _t;
			MyNode tmp80_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROMOTES);
			_t = _t.getFirstChild();
			
				;
			
			listNames(_t);
			_t = _retTree;
			
				;
			
			_t = __t41;
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
			AST __t39 = _t;
			MyNode tmp81_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPORTS);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t39;
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
				AST __t35 = _t;
				MyNode tmp82_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				paramRenameValuation(_t);
				_t = _retTree;
				_t = __t35;
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
				AST __t33 = _t;
				MyNode tmp83_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				_t = __t33;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t37 = _t;
				MyNode tmp84_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				paramRenameValuation(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
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
				AST __t189 = _t;
				MyNode tmp85_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_New_Predicate(_t);
				_t = _retTree;
				new_predicate(_t);
				_t = _retTree;
				_t = __t189;
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
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t53 = _t;
				MyNode tmp86_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				
					;
				
				sets_declaration(_t);
				_t = _retTree;
				_t = __t53;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t54 = _t;
				MyNode tmp87_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				
					;
				
				sets_declaration(_t);
				_t = _retTree;
				_t = __t54;
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
				AST __t56 = _t;
				MyNode tmp88_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				set = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					;
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t56;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
				;
				
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t61 = _t;
				MyNode tmp89_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				
					;
				
				list_couple(_t);
				_t = _retTree;
				
					;
				
				_t = __t61;
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
				AST __t62 = _t;
				MyNode tmp90_AST_in = (MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					;
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t62;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t63 = _t;
				MyNode tmp91_AST_in = (MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					;
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t63;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t64 = _t;
				MyNode tmp92_AST_in = (MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				
					;
				
				valuation_set(_t);
				_t = _retTree;
				_t = __t64;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			{
				MyNode tmp93_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					;
				
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
	
	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t58 = _t;
				MyNode tmp94_AST_in = (MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				
					;
				
				listrecord(_t);
				_t = _retTree;
				
					;
				
				_t = __t58;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t59 = _t;
				MyNode tmp95_AST_in = (MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				_t = __t59;
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
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t66 = _t;
				MyNode tmp96_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				
					;
				
				a_record(_t);
				_t = _retTree;
				_t = __t66;
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
	
	public final void list_couple(AST _t) throws RecognitionException {
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t68 = _t;
				MyNode tmp97_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_couple(_t);
				_t = _retTree;
				
					;
				
				couple(_t);
				_t = _retTree;
				_t = __t68;
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
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void basic_sets(AST _t) throws RecognitionException {
		
		MyNode basic_sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_INT:
			{
				MyNode tmp98_AST_in = (MyNode)_t;
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				break;
			}
			case 87:
			{
				MyNode tmp99_AST_in = (MyNode)_t;
				match(_t,87);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp100_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				break;
			}
			case 89:
			{
				MyNode tmp101_AST_in = (MyNode)_t;
				match(_t,89);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp102_AST_in = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp103_AST_in = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				break;
			}
			case 92:
			{
				MyNode tmp104_AST_in = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp105_AST_in = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				break;
			}
			case 94:
			{
				MyNode tmp106_AST_in = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp107_AST_in = (MyNode)_t;
				match(_t,LITERAL_STRING);
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
	
	public final void a_record(AST _t) throws RecognitionException {
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t78 = _t;
				MyNode tmp108_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t78;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				tmp1=predicate(_t);
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
	
	public final void couple(AST _t) throws RecognitionException {
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t70 = _t;
				MyNode tmp109_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					;
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t70;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t71 = _t;
				MyNode tmp110_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					;
				
				parent_couple(_t);
				_t = _retTree;
				
					;
				
				_t = __t71;
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
	
	public final void a_set_value(AST _t) throws RecognitionException {
		
		MyNode a_set_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
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
				MyNode tmp111_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					;
				
				break;
			}
			case B_MINUS:
			{
				AST __t76 = _t;
				mi = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				val1 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					;
				
				_t = __t76;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				val = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					;
				
				break;
			}
			case LITERAL_TRUE:
			{
				tr = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					;
				
				break;
			}
			case LITERAL_FALSE:
			{
				fa = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					;
				
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t73 = _t;
				MyNode tmp112_AST_in = (MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					;
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t73;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t74 = _t;
				MyNode tmp113_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a_set_value(_t);
				_t = _retTree;
				
					;
				
				a_set_value(_t);
				_t = _retTree;
				_t = __t74;
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
	
	public final void list_valuation(AST _t) throws RecognitionException {
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t82 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_valuation(_t);
				_t = _retTree;
				
					;
				
				set_valuation(_t);
				_t = _retTree;
				_t = __t82;
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
			AST __t84 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				;
			
			new_set_or_constant_valuation(_t);
			_t = _retTree;
			_t = __t84;
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
		
		String tmp1;
		
		
		try {      // for error handling
			tmp1=predicate(_t);
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
			AST __t87 = _t;
			MyNode tmp114_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp115_AST_in = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			interval_declaration(_t);
			_t = _retTree;
			_t = __t87;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t89 = _t;
			MyNode tmp116_AST_in = (MyNode)_t;
			match(_t,B_RANGE);
			_t = _t.getFirstChild();
			tmp1=predicate(_t);
			_t = _retTree;
			tmp1=predicate(_t);
			_t = _retTree;
			_t = __t89;
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
			AST __t91 = _t;
			MyNode tmp117_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp118_AST_in = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			MyNode tmp119_AST_in = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			_t = __t91;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void gTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode gTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name1 = null;
		MyNode name2 = null;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_COMMA:
			{
				AST __t101 = _t;
				MyNode tmp120_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				gTypedIdentifier(_t);
				_t = _retTree;
				gTypedIdentifier(_t);
				_t = _retTree;
				_t = __t101;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t102 = _t;
				MyNode tmp121_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				name1 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				
				cg.DecVar(name1.getText(),name1.getBType().toString());
				
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t102;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				{
				name2 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				
				cg.DecVar(name2.getText(),name2.getBType().toString());
				
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
	
	public final void list_def(AST _t) throws RecognitionException {
		
		MyNode list_def_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_DEF)) {
				AST __t109 = _t;
				MyNode tmp122_AST_in = (MyNode)_t;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				list_def(_t);
				_t = _retTree;
				definition(_t);
				_t = _retTree;
				_t = __t109;
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
	
/**
 *  Attention deux cas
 *     - Une definition
 *     - un acces a un fichier de definition
 **/
	public final void definition(AST _t) throws RecognitionException {
		
		MyNode definition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_DOUBLE_EQUAL)) {
				AST __t111 = _t;
				MyNode tmp123_AST_in = (MyNode)_t;
				match(_t,B_DOUBLE_EQUAL);
				_t = _t.getFirstChild();
				paramName(_t);
				_t = _retTree;
				formalText(_t);
				_t = _retTree;
				_t = __t111;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_ASTRING)) {
				MyNode tmp124_AST_in = (MyNode)_t;
				match(_t,B_ASTRING);
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
	
	public final void formalText(AST _t) throws RecognitionException {
		
		MyNode formalText_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
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
			{
				tmp1=predicate(_t);
				_t = _retTree;
				break;
			}
			case B_OUT:
			case B_BECOME_ELEM:
			case B_SIMPLESUBST:
			case LITERAL_skip:
			case LITERAL_BEGIN:
			case LITERAL_PRE:
			case LITERAL_ASSERT:
			case LITERAL_IF:
			case LITERAL_CHOICE:
			case LITERAL_CASE:
			case LITERAL_ANY:
			case LITERAL_SELECT:
			case LITERAL_LET:
			case LITERAL_WHILE:
			case LITERAL_VAR:
			case PARALLEL:
			case INSET:
			case SEQUENTIAL:
			case A_FUNC_CALL:
			{
				instruction(_t);
				_t = _retTree;
				break;
			}
			case OP_DEF:
			{
				operation(_t);
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
 * The Generalised Substitution Language
 **/
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1="";
		String tmp2= "";
		String tmp3= "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t136 = _t;
				MyNode tmp125_AST_in = (MyNode)_t;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("PARALLEL -- subst ||"));
				
				_t = __t136;
				_t = _t.getNextSibling();
				break;
			}
			case SEQUENTIAL:
			{
				AST __t137 = _t;
				MyNode tmp126_AST_in = (MyNode)_t;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				
				cg.Sequential();
				
				instruction(_t);
				_t = _retTree;
				_t = __t137;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_skip:
			{
				AST __t138 = _t;
				MyNode tmp127_AST_in = (MyNode)_t;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				
					cg.Comment(" Skip -- do nothing");
				
				_t = __t138;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t139 = _t;
				MyNode tmp128_AST_in = (MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				
					cg.BeginOp();
				
				instruction(_t);
				_t = _retTree;
				
				cg.EndOp();
				
				_t = __t139;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t140 = _t;
				MyNode tmp129_AST_in = (MyNode)_t;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("PRE -- subst p | I"));
				
				instruction(_t);
				_t = _retTree;
				_t = __t140;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t141 = _t;
				MyNode tmp130_AST_in = (MyNode)_t;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t141;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_IF:
			{
				AST __t142 = _t;
				MyNode tmp131_AST_in = (MyNode)_t;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				branche_then(_t);
				_t = _retTree;
				{
				_loop144:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
					}
					else {
						break _loop144;
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
				_t = __t142;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t146 = _t;
				MyNode tmp132_AST_in = (MyNode)_t;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("CHOICE -- subst I [] J"));
				
				_t = __t146;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CASE:
			{
				AST __t147 = _t;
				MyNode tmp133_AST_in = (MyNode)_t;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				
					tmp2 = "tmp"; 	//la variable debe comenzar por letra y no por numero
					Date fec = new Date(); 
					int  dat=fec.getDay();
					int hor=fec.getHours();
					int min=fec.getMinutes();
					int sec=fec.getSeconds();
				//formato de la variable: tmpXXXXXXXX
				
				//	tmp2= tmp2 +dat+hor+min+sec;
				
				//	tmp3.append(" "); //  aqui habra que escribir el tipo que devuelve predicate
				//	tmp3.append( tmp2+" = " +tmp1+";");
				
				
				
				branche_either(_t);
				_t = _retTree;
				{
				_loop149:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
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
				
				
				
				_t = __t147;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ANY:
			{
				AST __t151 = _t;
				MyNode tmp134_AST_in = (MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("CHOICE -- subst I [] J"));
				
				_t = __t151;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_LET:
			{
				AST __t152 = _t;
				MyNode tmp135_AST_in = (MyNode)_t;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				
					;
				
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				list_equal(_t);
				_t = _retTree;
				
					;
				
				instruction(_t);
				_t = _retTree;
				
					;
				
				_t = __t152;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t153 = _t;
				MyNode tmp136_AST_in = (MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				
					;
				
				tmp1=predicate(_t);
				_t = _retTree;
				
					;
				
				instruction(_t);
				_t = _retTree;
				
					;
				
				{
				_loop155:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
					}
					else {
						break _loop155;
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
				
					;
				
				_t = __t153;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t157 = _t;
				MyNode tmp137_AST_in = (MyNode)_t;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				
					;
				
				tmp1=predicate(_t);
				_t = _retTree;
				
					;
				
				instruction(_t);
				_t = _retTree;
				variant_or_no(_t);
				_t = _retTree;
				
					;
				
				_t = __t157;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VAR:
			{
				AST __t158 = _t;
				MyNode tmp138_AST_in = (MyNode)_t;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				
					;
				
				instruction(_t);
				_t = _retTree;
				_t = __t158;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			case B_BECOME_ELEM:
			case B_SIMPLESUBST:
			case INSET:
			case A_FUNC_CALL:
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
			AST __t124 = _t;
			MyNode tmp139_AST_in = (MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			operationHeader(_t);
			_t = _retTree;
			
			cg.BeginOp();
			
			instruction(_t);
			_t = _retTree;
			
			cg.EndOp();
			
			_t = __t124;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void list_assertions(AST _t) throws RecognitionException {
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t116 = _t;
				MyNode tmp140_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_assertions(_t);
				_t = _retTree;
				list_assertions(_t);
				_t = _retTree;
				_t = __t116;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				tmp1=predicate(_t);
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
				AST __t122 = _t;
				MyNode tmp141_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				operation(_t);
				_t = _retTree;
				_t = __t122;
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
		
		String pp = "To Be defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				AST __t126 = _t;
				MyNode tmp142_AST_in = (MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				pp=opNameParameter(_t);
				_t = _retTree;
				opparamName(_t,pp);
				_t = _retTree;
				_t = __t126;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_LPAREN)) {
				opparamName(_t,"");
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
	
	public final String  opNameParameter(AST _t) throws RecognitionException {
		String out;
		
		MyNode opNameParameter_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1, tmp2;
		out = "Not Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t131 = _t;
				MyNode tmp143_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp1=opParameters(_t);
				_t = _retTree;
				tmp2=opNameParameter(_t);
				_t = _retTree;
				
				out = cg.concatParam(tmp1, tmp2);
				
				_t = __t131;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_INSET)) {
				out=opParameters(_t);
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
		return out;
	}
	
	public final void opparamName(AST _t,
		String pOut
	) throws RecognitionException {
		
		MyNode opparamName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode name1 = null;
		
		String res  = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t128 = _t;
				MyNode tmp144_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				res=opNameParameter(_t);
				_t = _retTree;
				
				res = cg.constructOPHeader(name.getText(),res + pOut);
				cg.Constructor(res);
				
				_t = __t128;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				{
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
				res = cg.constructOPHeader(name1.getText(),"" + pOut);
				cg.Constructor(res);
				
				}
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
	
	public final String  opParameters(AST _t) throws RecognitionException {
		String out;
		
		MyNode opParameters_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode n1 = null;
		MyNode n2 = null;
		
		String tmp;
		out = "Not Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t133 = _t;
				MyNode tmp145_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				n1 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				tmp=predicate(_t);
				_t = _retTree;
				
				out = cg.constructDecVar(n1.getText(), n1.getBType().toString());
				
				_t = __t133;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				{
				n2 = _t==ASTNULL ? null : (MyNode)_t;
				nameRenamed(_t);
				_t = _retTree;
				
				out = cg.constructDecVar(n2.getText(), n2.getBType().toString());
				
				}
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
		return out;
	}
	
	public final void branche_then(AST _t) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t168 = _t;
			MyNode tmp146_AST_in = (MyNode)_t;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			instruction(_t);
			_t = _retTree;
			_t = __t168;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t172 = _t;
			MyNode tmp147_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			tmp1=predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			_t = __t172;
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
			AST __t170 = _t;
			MyNode tmp148_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			instruction(_t);
			_t = _retTree;
			_t = __t170;
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
				AST __t160 = _t;
				MyNode tmp149_AST_in = (MyNode)_t;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t160;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t164 = _t;
			MyNode tmp150_AST_in = (MyNode)_t;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			tmp1=predicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			_t = __t164;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t166 = _t;
			MyNode tmp151_AST_in = (MyNode)_t;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			tmp1=listPredicate(_t);
			_t = _retTree;
			instruction(_t);
			_t = _retTree;
			_t = __t166;
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
				AST __t179 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				list_equal(_t);
				_t = _retTree;
				
					;
				
				an_equal(_t);
				_t = _retTree;
				_t = __t179;
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
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t162 = _t;
			MyNode tmp152_AST_in = (MyNode)_t;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			
				;
			
			tmp1=predicate(_t);
			_t = _retTree;
			
				;
			
			instruction(_t);
			_t = _retTree;
			_t = __t162;
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
		
		String tmp1,tmp2;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				AST __t174 = _t;
				MyNode tmp153_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				AST __t175 = _t;
				MyNode tmp154_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t175;
				_t = _t.getNextSibling();
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t174;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t176 = _t;
				MyNode tmp155_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				AST __t177 = _t;
				MyNode tmp156_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t177;
				_t = _t.getNextSibling();
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t176;
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
		MyNode var = null;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t214 = _t;
				MyNode tmp157_AST_in = (MyNode)_t;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				var = _t==ASTNULL ? null : (MyNode)_t;
				afc_bis(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				
				cg.Affectation(var.getText(),tmp1);
				
				_t = __t214;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			{
				AST __t215 = _t;
				MyNode tmp158_AST_in = (MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				func_call(_t);
				_t = _retTree;
				_t = __t215;
				_t = _t.getNextSibling();
				break;
			}
			case INSET:
			{
				AST __t216 = _t;
				MyNode tmp159_AST_in = (MyNode)_t;
				match(_t,INSET);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("INSET -- subst x:(A)"));
				
				_t = __t216;
				_t = _t.getNextSibling();
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t217 = _t;
				MyNode tmp160_AST_in = (MyNode)_t;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("B_BECOME_ELEMENT -- subst x::A"));
				
				_t = __t217;
				_t = _t.getNextSibling();
				break;
			}
			case A_FUNC_CALL:
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
	
	public final String  listPredicate(AST _t) throws RecognitionException {
		String st;
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		st= "";
		String tmp1 = "";
		String tmp2 = "";
		
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t202 = _t;
				MyNode tmp161_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp2=listPredicate(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t202;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				tmp1=predicate(_t);
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
		return st;
	}
	
	public final void an_equal(AST _t) throws RecognitionException {
		
		MyNode an_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			AST __t181 = _t;
			MyNode tmp162_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			MyNode tmp163_AST_in = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			tmp1=predicate(_t);
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
	
	public final void func_call(AST _t) throws RecognitionException {
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t3 = null;
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t183 = _t;
				MyNode tmp164_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					;
				
				_t = __t183;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t184 = _t;
				MyNode tmp165_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					;
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					;
				
				_t = __t184;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t185 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					;
				
				list_New_Predicate(_t);
				_t = _retTree;
				
					;
				
				_t = __t185;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t186 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				
					;
				
				func_call(_t);
				_t = _retTree;
				_t = __t186;
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
	
	public final void new_predicate(AST _t) throws RecognitionException {
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t191 = _t;
				MyNode tmp166_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					;
				
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t191;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t192 = _t;
				MyNode tmp167_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				new_predicate(_t);
				_t = _retTree;
				
					;
				
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t192;
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
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			{
				tmp1=predicate(_t);
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
				AST __t196 = _t;
				MyNode tmp168_AST_in = (MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				nameRenamed(_t);
				_t = _retTree;
				_t = __t196;
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
				AST __t198 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				nameRenamedDecorated(_t);
				_t = _retTree;
				_t = __t198;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t200 = _t;
				MyNode tmp169_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				MyNode tmp170_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				_t = __t200;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				MyNode tmp171_AST_in = (MyNode)_t;
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
	
	public final void a_func_call(AST _t) throws RecognitionException {
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t204 = _t;
			MyNode tmp172_AST_in = (MyNode)_t;
			match(_t,A_FUNC_CALL);
			_t = _t.getFirstChild();
			afc(_t);
			_t = _retTree;
			_t = __t204;
			_t = _t.getNextSibling();
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
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t206 = _t;
				MyNode tmp173_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				tmp1=listPredicate(_t);
				_t = _retTree;
				_t = __t206;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t207 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				afc(_t);
				_t = _retTree;
				_t = __t207;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t209 = _t;
				MyNode tmp174_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list_func_call(_t);
				_t = _retTree;
				list_func_call(_t);
				_t = _retTree;
				_t = __t209;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==A_FUNC_CALL)) {
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
	
	public final void afc_bis(AST _t) throws RecognitionException {
		
		MyNode afc_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t211 = _t;
				MyNode tmp175_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				afc_bis(_t);
				_t = _retTree;
				tmp1=listPredicate(_t);
				_t = _retTree;
				
				System.err.println(errors.NotAllowedInImp("FUNC_CALL_PARAM"));
				
				_t = __t211;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t212 = _t;
				MyNode tmp176_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc_bis(_t);
				_t = _retTree;
				afc_bis(_t);
				_t = _retTree;
				_t = __t212;
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
	
	public final String  cset_description(AST _t) throws RecognitionException {
		String st;
		
		MyNode cset_description_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1,tmp2;
		st = "Not Defined";
		
		
		
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
				st=cbasic_value(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_bool:
			{
				AST __t271 = _t;
				MyNode tmp177_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t271;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t272 = _t;
				MyNode tmp178_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				tmp1=listPredicate(_t);
				_t = _retTree;
				_t = __t272;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t273 = _t;
				MyNode tmp179_AST_in = (MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t273;
				_t = _t.getNextSibling();
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp180_AST_in = (MyNode)_t;
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t274 = _t;
				MyNode tmp181_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				tmp1=cvalue_set(_t);
				_t = _retTree;
				_t = __t274;
				_t = _t.getNextSibling();
				break;
			}
			case B_SEQEMPTY:
			{
				MyNode tmp182_AST_in = (MyNode)_t;
				match(_t,B_SEQEMPTY);
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
		return st;
	}
	
	public final String  cbasic_value(AST _t) throws RecognitionException {
		String st;
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		
		String tmp1,tmp2;
		st = "Not Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				MyNode tmp183_AST_in = (MyNode)_t;
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				t2 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					st = t2.getText();
				
				break;
			}
			case B_TILDE:
			{
				AST __t279 = _t;
				MyNode tmp184_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t279;
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
				AST __t280 = _t;
				MyNode tmp185_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
				_t = __t280;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t281 = _t;
				MyNode tmp186_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				_t = __t281;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t282 = _t;
				MyNode tmp187_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t282;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t283 = _t;
				MyNode tmp188_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				tmp1=predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t283;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp189_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
				st = cg.genereTrue();
				
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp190_AST_in = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
				st = cg.genereFalse();
				
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
		return st;
	}
	
	public final String  cvalue_set(AST _t) throws RecognitionException {
		String st;
		
		MyNode cvalue_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1, tmp2;
		st = "Not Defined";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t276 = _t;
				MyNode tmp191_AST_in = (MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t276;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t277 = _t;
				MyNode tmp192_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp2=cvalue_set(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t277;
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
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			{
				tmp1=predicate(_t);
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
		return st;
	}
	
	public final void quantification(AST _t) throws RecognitionException {
		
		MyNode quantification_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t290 = _t;
				MyNode tmp193_AST_in = (MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t290;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t291 = _t;
				MyNode tmp194_AST_in = (MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				list_var(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t291;
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
		MyNode t4 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t293 = _t;
				MyNode tmp195_AST_in = (MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				
					;
				
				q_operande(_t);
				_t = _retTree;
				_t = __t293;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t294 = _t;
				MyNode tmp196_AST_in = (MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				
					;
				
				q_operande(_t);
				_t = _retTree;
				_t = __t294;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t295 = _t;
				MyNode tmp197_AST_in = (MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				
					;
				
				q_operande(_t);
				_t = _retTree;
				_t = __t295;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t296 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				
					;
				
				q_operande(_t);
				_t = _retTree;
				_t = __t296;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t297 = _t;
				MyNode tmp198_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				
					;
				
				q_operande(_t);
				_t = _retTree;
				_t = __t297;
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
				AST __t301 = _t;
				MyNode tmp199_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				
					;
				
				list_identifier(_t);
				_t = _retTree;
				
					;
				
				_t = __t301;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				
					;
				
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
		
		String tmp1;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t285 = _t;
				MyNode tmp200_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t285;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t286 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t286;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t287 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				pred_func_composition(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				_t = __t287;
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
			case B_NOT:
			case B_DOM:
			case B_RAN:
			case B_NOTINSET:
			case LITERAL_STRING:
			{
				tmp1=predicate(_t);
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
		
		String tmp1,tmp2;
		
		
		try {      // for error handling
			AST __t299 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			
				;
			
			tmp1=predicate(_t);
			_t = _retTree;
			
				;
			
			tmp2=predicate(_t);
			_t = _retTree;
			
				;
			
			_t = __t299;
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
		"A_FUNC_CALL"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -3530822108395339808L, 215083372247071L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -3530822108395339808L, 215220811194399L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -9214224099843244000L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -3530822108395339808L, 215083372240927L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 0L, 117093590311632896L, 600819929301647360L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	}
	
