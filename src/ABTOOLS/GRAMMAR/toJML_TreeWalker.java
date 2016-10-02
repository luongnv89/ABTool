// $ANTLR 2.7.6 (2005-12-22): "expandedtoJML_TreeWalker.g" -> "toJML_TreeWalker.java"$

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

	// ANTLR packages
//	import antlr.debug.misc.*;
//	import antlr.DumpASTVisitor;
	// ABTools packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*;
	import ABTOOLS.PRINTING.*;
	import ABTOOLS.TYPING.*;
	// usefull packages
	import java.io.*;
	import java.util.*;


public class toJML_TreeWalker extends antlr.TreeParser       implements toJML_TreeWalkerTokenTypes
 {


	String module = "toJML_TreeWalker.g";
	
	String p_inset = null;
	
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
 	
 	// processing invariant?
	boolean inInvariant = false;
	boolean inInitially = false;
	boolean hasPRE;
	Stack<ABType> sqType = new Stack<ABType>();
	boolean operParam = false;
	
	public static char back = 92;
	public static HashMap<String, String> setOps = new HashMap<String, String>();
	public static HashMap<String, String> seqOps = new HashMap<String, String>();
	static {
		seqOps.put("size", "int_size");
		seqOps.put("card", "int_size");
		seqOps.put("first", "first");
		seqOps.put("last", "last");
		seqOps.put("front", "header");
		seqOps.put("tail", "trailer");
		seqOps.put("rev", "reverse");
		seqOps.put("conc", "org.jmlspecs.b2jml.util.ModUtils.concat");

		setOps.put("size", "int_size");
		setOps.put("card", "int_size");
		setOps.put("union", "org.jmlspecs.b2jml.util.ModUtils.union");	
		setOps.put("inter", "org.jmlspecs.b2jml.util.ModUtils.inter");			
	}
	
	public static boolean isFunction(ABType type) {
		if (type instanceof PROD) {
			return ((PROD) type).isFunction();
		} else {
			return false;
		}
	}
	
	public static boolean isCollection(ABType type) {
		return type instanceof EMPTYSET || type instanceof EMPTYSEQ
		    || type instanceof ID || type instanceof INFIXE
		    || type instanceof UNARY;
	}
	
	private ArrayList<String> enumConstants = new ArrayList<String>();
	
	 // this is a recursive method that converts B types to corresponding JML types
	 // if there is no JML type for the specific B type, match to a broader JML type 
	 // e.g. JMLEqualsSet for EMPTYSET in B, and specify the typing rule in invariant section
	public static String typeToString(ABType type, boolean firstCall, boolean useGeneric) {
	 
	  if (type instanceof BOOL) return "boolean";
	  if (type instanceof EMPTYSEQ) return "JMLEqualsSequence";
	  if (type instanceof EMPTYSET) return "JMLEqualsSet";
	  if (type instanceof INTEGER) return "Integer";
	  if (type instanceof STRING) return "String";
	  //java enum
	  if (type instanceof ENUM) return "String";
	  if (type instanceof CONSTANT) {
	  	if (useGeneric) {
	  		return "JMLEqualsSet<"+typeToString(type.getMember(), false, useGeneric)+">";
	  	} else {
	  		return "JMLEqualsSet";
	  	}
	  }
	  if (type instanceof EQUAL) {
	  	if (useGeneric) {
	  		return "JMLEqualsToEqualsRelation<"+typeToString(type.getMember(), false, useGeneric)+">";
	  	} else {
	  		return "JMLEqualsToEqualsRelation";
	  	}
	  }
	  if (type instanceof ID) {
	  	if (useGeneric) {
		  String s=typeToString(type.getMember(), false, useGeneric);
		  return "JMLEqualsToEqualsRelation<"+s+","+s+">";
		} else {
			return "JMLEqualsToEqualsRelation";
		} 
	  }

		if (type instanceof RANGE) {
			if (useGeneric) {
				return "JMLEqualsSet<"+typeToString(type.getMember(), false, useGeneric)+">";
			} else {
				return "JMLEqualsSet";
			}
		}
		
		if (type instanceof LIST || type instanceof INFIXE) {
			String rel = "Relation";
			if (toJML_TreeWalker.isFunction(type)) {
				rel = "Map";
			}
			if (useGeneric) {
				if (firstCall) {
					return "JMLEqualsToEquals" + rel + "<"+typeToString(type.getMember(), false, useGeneric)+
									","+typeToString(type.getMember2(), false, useGeneric)+">";
				} else {
					return "JMLEqualsEqualsPair<" +typeToString(type.getMember(), false, useGeneric) +
					       ", " + typeToString(type.getMember2(), false, useGeneric) + ">";
				}
			} else {
				if (firstCall) {
					return "JMLEqualsToEquals" + rel;
				} else {
					return "JMLEqualsEqualsPair";
				}
			}
		}
		if (type instanceof POW) {
			if (useGeneric) {
				return "JMLEqualsSet<JMLEqualsSet<"+typeToString(type.getMember(), false, useGeneric)+">>";
			} else {
				return "JMLEqualsSet";
			}
		}		
		if (type instanceof SEQ) {
			if (useGeneric) {
				return "JMLEqualsSequence<"+typeToString(type.getMember(), false, useGeneric)+">";
			} else {
				return "JMLEqualsSequence";
			}															
		}
		if (type instanceof GIVENSET) {
			if (firstCall) {
				if (useGeneric) {
					return "JMLEqualsSet<"+typeToString(type.getMember(), false, useGeneric)+">";
				} else {
					return "JMLEqualsSet";
				}
			} else {
				return typeToString(type.getMember(), false, useGeneric);
			}															
		}
		if (type instanceof UNARY) {
			if (useGeneric) {
				return "JMLEqualsSet<"+typeToString(type.getMember(), false, useGeneric)+">";
			} else {
				return "JMLEqualsSet";
			}
		}
		if (!(type instanceof Typing_ERROR)) {
		  return "Integer";
		}
		return "[Type_Error " + type + " of " + type.getClass() + "]"; //change later
	}
	
	public static String typeToString(ABType type) {
		return typeToString(type, true, true);
	}
	
	// List of Strings
	ArrayList<String> listJML = new ArrayList<String>();
	//JML fileName
	String fileName = new String();
	
	Stack<ArrayList<String>> stackOps = new Stack<ArrayList<String>>();
	ArrayList<String> assign = new ArrayList<String>();	
	//ArrayList<String> returnVal = new ArrayList<String>(); //output parameters
	Map<String,String> returnVal1 = new LinkedHashMap<String,String>();
	ArrayList<String> outStmt = new ArrayList<String>(); //output ensures stmt

	//toFile: generates a .java file with the code in list l.
	public void toFile(String dir)
	{
		try {
				FileWriter file = new FileWriter(dir + File.separator + fileName);
				PrintWriter pw = new PrintWriter(file);
				for(String s : listJML){
					System.out.println(s);
					pw.println(s);
				}
				file.close();
				if (dir.equals(".")) {
					System.out.println("Output in: "+System.getProperty("user.dir"));
				} else {
					System.out.println("Output in: " + dir);
				}
			} catch (Exception e) {
		            e.printStackTrace();
		}
	}

public toJML_TreeWalker() {
	tokenNames = _tokenNames;
}

	public final void composant(AST _t) throws RecognitionException {
		
		MyNode composant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			
					index.init();
				
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
	
	public final void machine(AST _t) throws RecognitionException {
		
		MyNode machine_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			
					String pn = "toJML paramName";
					assign.clear();
					//just to prevent assignable stmt outside operations from having an empty stack
				
			AST __t4 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_MACHINE);
			_t = _t.getFirstChild();
			pn=paramName(_t);
			_t = _retTree;
			
					fileName = pn + ".java";
					listJML.add("import org.jmlspecs.models.*;"); 
					listJML.add("public abstract class " + pn + " {");
				
			clauses(_t);
			_t = _retTree;
			
					listJML.add("}"); // close class declaration
				
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
		
			String pn= "";
			assign.clear(); 
		
		
		try {      // for error handling
			AST __t6 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_REFINEMENT);
			_t = _t.getFirstChild();
			
					index.Add();
				
			pn=paramName(_t);
			_t = _retTree;
			
				fileName = pn + ".java";
					listJML.add("import org.jmlspecs.models.*;"); 
					listJML.add("public abstract class " + pn + " {");
			
			clauses(_t);
			_t = _retTree;
			
			listJML.add("}");
			
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
		MyNode tt = null;
		
		try {      // for error handling
			AST __t8 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_IMPLEMENTATION);
			_t = _t.getFirstChild();
			
					index.Add();
				
			paramName(_t);
			_t = _retTree;
			clauses(_t);
			_t = _retTree;
			_t = __t8;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  paramName(AST _t) throws RecognitionException {
		String code = new String();
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode name1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				
						Map<String, ABType> lti = null;
					
				AST __t14 = _t;
				MyNode tmp1_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				lti=listTypedIdentifier(_t);
				_t = _retTree;
				
						code=name.getText()+"(";
						for (String s:lti.keySet()){
							code+=toJML_TreeWalker.typeToString(lti.get(s))+" "+s+", ";
						}
						code=code.substring(0,code.length()-2);
						code+=")";
					
				_t = __t14;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					if (operParam) 
						code = name1.getText()+"()";
					else 
						code =name1.getText();
					
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
		return code;
	}
	
	public final void clauses(AST _t) throws RecognitionException {
		
		MyNode clauses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			{
			_loop11:
			do {
				if (_t==null) _t=ASTNULL;
				if (((_t.getType() >= LITERAL_CONSTRAINTS && _t.getType() <= LITERAL_OPERATIONS))) {
					clause(_t);
					_t = _retTree;
				}
				else {
					break _loop11;
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
		MyNode tt = null;
		MyNode name = null;
		
		try {      // for error handling
			AST __t41 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_REFINES);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			_t = __t41;
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
		
			String p1="";
		
		
		try {      // for error handling
			AST __t20 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_CONSTRAINTS);
			_t = _t.getFirstChild();
			p1=predicate(_t);
			_t = _retTree;
			_t = __t20;
			_t = _t.getNextSibling();
			
					listJML.add("/*@public invariant");
					listJML.add(p1);
					listJML.add("*/\n");
				
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
		
			String set="";
		
		
		try {      // for error handling
			AST __t49 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SETS);
			_t = _t.getFirstChild();
			set=sets_declaration(_t);
			_t = _retTree;
			_t = __t49;
			_t = _t.getNextSibling();
			
					listJML.add(set);
				
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
		
				String code="";
		
		
		try {      // for error handling
			AST __t71 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_VALUES);
			_t = _t.getFirstChild();
			code=list_valuation(_t);
			_t = _retTree;
			_t = __t71;
			_t = _t.getNextSibling();
			
					listJML.add(code);
				
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
		
			Map<String, ABType> lti = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTANTS:
			{
				AST __t43 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				lti=listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t43;
				_t = _t.getNextSibling();
				
						for (String name : lti.keySet()) {
							String decl = "  //@ public static final ghost ";
							decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
							decl += " " + name + ";";
							listJML.add(decl);
						}
					
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t44 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t44;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t45 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t45;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t46 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				lti=listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t46;
				_t = _t.getNextSibling();
				
						for (String name : lti.keySet()) {
							String decl = "  //@ public static final ghost ";
							decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
							decl += " " + name + ";";
							listJML.add(decl);
						}
					
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t47 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t47;
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
		
			String p1="";
		
		
		try {      // for error handling
			AST __t84 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_PROPERTIES);
			_t = _t.getFirstChild();
			p1=predicate(_t);
			_t = _retTree;
			_t = __t84;
			_t = _t.getNextSibling();
			
					listJML.add("/*@ public invariant");
					listJML.add(p1 + ";");
					listJML.add("*/");
				
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
		
				Map<String, ABType> lti = null;
			
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_VARIABLES:
			{
				AST __t86 = _t;
				t0 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				lti=listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t86;
				_t = _t.getNextSibling();
				
						for (String name : lti.keySet()) {
							String decl = "  //@ public model ";
							decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
							decl += " " + name+";";
							listJML.add(decl);
						}
						listJML.add("\n");
					
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t87 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				lti=listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t87;
				_t = _t.getNextSibling();
				
						for (String name : lti.keySet()) {
							String decl = "  //@ public model ";
							decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
							decl += " " + name+";";
							listJML.add(decl);
						}
						listJML.add("\n");
					
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t88 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t88;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t89 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t89;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t90 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t90;
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
			
					inInvariant = true;
					listJML.add("/*@ public invariant");
					String t = "";
				
			AST __t92 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INVARIANT);
			_t = _t.getFirstChild();
			t=predicate(_t);
			_t = _retTree;
			_t = __t92;
			_t = _t.getNextSibling();
			
					inInvariant = false;
					listJML.add(t);
					listJML.add(";*/\n");
				
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
			
					String p="";
					listJML.add("/*@ public invariant_redundantly ");		
			
			AST __t103 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ASSERTIONS);
			_t = _t.getFirstChild();
			p=list_assertions(_t);
			_t = _retTree;
			_t = __t103;
			_t = _t.getNextSibling();
			
						listJML.add(p);
						listJML.add(";*/\n");
						
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
			AST __t94 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_DEFINITIONS);
			_t = _t.getFirstChild();
			list_def(_t);
			_t = _retTree;
			_t = __t94;
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
			
					listJML.add("/*@ public initially ");
					String in="";
					inInitially=true;
					
			AST __t107 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INITIALISATION);
			_t = _t.getFirstChild();
			in=instruction(_t);
			_t = _retTree;
			_t = __t107;
			_t = _t.getNextSibling();
			
					inInitially=false;
					listJML.add(in);
					listJML.add(";*/\n");
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  operations(AST _t) throws RecognitionException {
		String code;
		
		MyNode operations_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
					code="";
		
		
		try {      // for error handling
			AST __t109 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_OPERATIONS);
			_t = _t.getFirstChild();
			code=listOperation(_t);
			_t = _retTree;
			_t = __t109;
			_t = _t.getNextSibling();
			
							listJML.add(code);
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final Map<String, ABType>  listTypedIdentifier(AST _t) throws RecognitionException {
		Map<String, ABType> ids;
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			ids = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				
						Map<String, ABType> lti1 = null;
						Map<String, ABType> lti2 = null;
					
				AST __t16 = _t;
				MyNode tmp2_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				lti1=listTypedIdentifier(_t);
				_t = _retTree;
				lti2=listTypedIdentifier(_t);
				_t = _retTree;
				_t = __t16;
				_t = _t.getNextSibling();
				
						ids = (Map<String, ABType>) new LinkedHashMap(lti1);
						ids.putAll(lti2);
					
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT||_t.getType()==B_INSET)) {
				
						Map<String, ABType> ti = null;
					
				ti=typedIdentifier(_t);
				_t = _retTree;
				
						ids = ti;
					
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
		return ids;
	}
	
	public final Map<String, ABType>  typedIdentifier(AST _t) throws RecognitionException {
		Map<String, ABType> id;
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode nn1 = null;
		MyNode pred = null;
		MyNode name = null;
		
			id = null;
			String p = null, nr = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t18 = _t;
				MyNode tmp3_AST_in = (MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				nn1 = _t==ASTNULL ? null : (MyNode)_t;
				nr=nameRenamed(_t);
				_t = _retTree;
				pred = _t==ASTNULL ? null : (MyNode)_t;
				p=predicate(_t);
				_t = _retTree;
				_t = __t18;
				_t = _t.getNextSibling();
				
					  id = new LinkedHashMap<String, ABType>();
						id.put(nr, nn1.getBType());
					
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				name = _t==ASTNULL ? null : (MyNode)_t;
				nr=nameRenamed(_t);
				_t = _retTree;
				
						id = new LinkedHashMap<String, ABType>();
						id.put(nr, name.getBType());
					
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
		return id;
	}
	
	public final String  nameRenamed(AST _t) throws RecognitionException {
		String code;
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode n1 = null;
		MyNode tt = null;
		
			code = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
						code = n1.getText();
						// FLURB
					
			}
			else if ((_t.getType()==B_POINT)) {
				
							String nr1 = "";
							String nr2 = "";
						
				AST __t197 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				nr1=nameRenamed(_t);
				_t = _retTree;
				nr2=nameRenamed(_t);
				_t = _retTree;
				_t = __t197;
				_t = _t.getNextSibling();
				
							code = nr1 + "." + nr2;
						
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
		return code;
	}
	
	public final String  predicate(AST _t) throws RecognitionException {
		String code;
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		MyNode pred1 = null;
		MyNode pred2 = null;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode t8 = null;
		MyNode t9 = null;
		MyNode t10 = null;
		MyNode t11 = null;
		MyNode t12 = null;
		MyNode predMinus1 = null;
		MyNode predMinus2 = null;
		MyNode t13 = null;
		MyNode predEqual1 = null;
		MyNode predEqual2 = null;
		MyNode t14 = null;
		MyNode predLess1 = null;
		MyNode predLess2 = null;
		MyNode t15 = null;
		MyNode predGreater1 = null;
		MyNode predGreater2 = null;
		MyNode t16 = null;
		MyNode predNEqual1 = null;
		MyNode predNEqual2 = null;
		MyNode t17 = null;
		MyNode predLTE1 = null;
		MyNode predLTE2 = null;
		MyNode t18 = null;
		MyNode predGTE1 = null;
		MyNode predGTE2 = null;
		MyNode t19 = null;
		MyNode t20 = null;
		MyNode t21 = null;
		MyNode pl1 = null;
		MyNode pl2 = null;
		MyNode t22 = null;
		MyNode pl1a = null;
		MyNode pl2a = null;
		MyNode t23 = null;
		MyNode pl1b = null;
		MyNode pl2b = null;
		MyNode t24 = null;
		MyNode pl1c = null;
		MyNode pl2c = null;
		MyNode t25 = null;
		MyNode t26 = null;
		MyNode t27 = null;
		MyNode t28 = null;
		MyNode t29 = null;
		MyNode t30 = null;
		MyNode predr1 = null;
		MyNode predr2 = null;
		MyNode t31 = null;
		MyNode predp1 = null;
		MyNode predp2 = null;
		MyNode t32 = null;
		MyNode predt1 = null;
		MyNode predt2 = null;
		MyNode t33 = null;
		MyNode predpi1 = null;
		MyNode predpi2 = null;
		MyNode t34 = null;
		MyNode predti1 = null;
		MyNode predti2 = null;
		MyNode t35 = null;
		MyNode predps1 = null;
		MyNode predps2 = null;
		MyNode t36 = null;
		MyNode predts1 = null;
		MyNode predts2 = null;
		MyNode t37 = null;
		MyNode predb1 = null;
		MyNode predb2 = null;
		MyNode t38 = null;
		MyNode t39 = null;
		MyNode t40 = null;
		MyNode ds1 = null;
		MyNode ds2 = null;
		MyNode t41 = null;
		MyNode rs1 = null;
		MyNode rs2 = null;
		MyNode t42 = null;
		MyNode t43 = null;
		MyNode t44 = null;
		MyNode t45 = null;
		MyNode pu1 = null;
		MyNode pu2 = null;
		MyNode t46 = null;
		MyNode t48 = null;
		MyNode t49 = null;
		MyNode cs = null;
		
				String p1 = "";
				String p2 = "";
				code = "";
			
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_AND:
			{
				AST __t205 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t205;
				_t = _t.getNextSibling();
				
						code = p1 + "\n && " + p2;
					
				break;
			}
			case LITERAL_or:
			{
				AST __t206 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t206;
				_t = _t.getNextSibling();
				
						code = p1 + "\n || " + p2;
					
				break;
			}
			case B_IMPLIES:
			{
				AST __t207 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t207;
				_t = _t.getNextSibling();
				
						code = "(" + p1 + ")" + "\n ==> (" + p2 + ")";
					
				break;
			}
			case B_EQUIV:
			{
				AST __t208 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t208;
				_t = _t.getNextSibling();
				
						code = "(" + p1 + ")" + "\n <==> (" + p2 + ")";
						
				break;
			}
			case B_MULT:
			{
				AST __t209 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				pred1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pred2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t209;
				_t = _t.getNextSibling();
				
							//if p1 and p2 are integers, do arithmetic multiplication
							if (pred1.getBType() instanceof ABTOOLS.TYPING.INTEGER &&
							    pred2.getBType() instanceof ABTOOLS.TYPING.INTEGER) {
							    code = p1+"*"+p2;
							} else {
								// else make cartesian product
								code = "org.jmlspecs.b2jml.util.ModelUtils.cartesian("+p1+", "+p2+")";
							}
						
				break;
			}
			case B_POWER:
			{
				AST __t210 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t210;
				_t = _t.getNextSibling();
				
							code="Math.pow("+p1+","+p2+")";		
							
				break;
			}
			case B_DIV:
			{
				AST __t211 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t211;
				_t = _t.getNextSibling();
				
							code=p1+"/"+p2;		
							
				break;
			}
			case LITERAL_mod:
			{
				AST __t212 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t212;
				_t = _t.getNextSibling();
				
							code=p1+"%"+p2;		
							
				break;
			}
			case UNARY_ADD:
			{
				AST __t213 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t213;
				_t = _t.getNextSibling();
				
								code="+"+p1;
							
				break;
			}
			case UNARY_MINUS:
			{
				AST __t214 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				p2=predicate(_t);
				_t = _retTree;
				_t = __t214;
				_t = _t.getNextSibling();
				
								code="-"+p1;
							
				break;
			}
			case B_ADD:
			{
				AST __t215 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t215;
				_t = _t.getNextSibling();
				
							code=p1+"+"+p2;		
							
				break;
			}
			case B_MINUS:
			{
				AST __t216 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				predMinus1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predMinus2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t216;
				_t = _t.getNextSibling();
				
							if (predMinus1.getBType() instanceof ABTOOLS.TYPING.INTEGER &&
							    predMinus2.getBType() instanceof ABTOOLS.TYPING.INTEGER) {
								code = p1+"-"+p2;
							} else {
					        	ABType type1 = predMinus1.getBType();
					        	ABType type2 = predMinus2.getBType();
					        	if (type1 instanceof INFIXE && !(type2 instanceof INFIXE)) {
					        		if (toJML_TreeWalker.isFunction(type1)) {
					        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p2 + ")";
					        		} else {
					        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
					        		}
					        	} else if (type2 instanceof INFIXE && !(type1 instanceof INFIXE)) {
					        		if (toJML_TreeWalker.isFunction(type2)) {
					        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p1 + ")";
					        		} else {
					        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
					        		}
					        	}
								code = p1+".difference("+p2+")";
							}
							
				break;
			}
			case B_EQUAL:
			{
				AST __t217 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				predEqual1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predEqual2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t217;
				_t = _t.getNextSibling();
				
							if (((predEqual1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
									(predEqual2.getBType() instanceof ABTOOLS.TYPING.INTEGER)) ||
									((predEqual1.getBType() instanceof ABTOOLS.TYPING.BOOL) ||
									(predEqual2.getBType() instanceof ABTOOLS.TYPING.BOOL)))
							{
								code=p1+"=="+p2;
							}
							else
							{
								code=p1+".equals("+p2+")";
							}
							
				break;
			}
			case B_LESS:
			{
				AST __t218 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				predLess1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predLess2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t218;
				_t = _t.getNextSibling();
				
							if ((predLess1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
									(predLess2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
							{
								code=p1+"<"+p2;
							}
							else
							{
								code=p1+".compareTo("+p2+")<0";
							}
							
				break;
			}
			case B_GREATER:
			{
				AST __t219 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				predGreater1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predGreater2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t219;
				_t = _t.getNextSibling();
				
							if ((predGreater1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
									(predGreater2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
							{
								code=p1+">"+p2;
							}
							else
							{
								code=p1+".compareTo("+p2+")>0";
							}
							
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t220 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				predNEqual1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predNEqual2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t220;
				_t = _t.getNextSibling();
				
							if (((predNEqual1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
									(predNEqual2.getBType() instanceof ABTOOLS.TYPING.INTEGER)) ||
									((predNEqual1.getBType() instanceof ABTOOLS.TYPING.BOOL) ||
									(predNEqual2.getBType() instanceof ABTOOLS.TYPING.BOOL)))
							{
								code=p1+"!="+p2;
							}
							else
							{
								code="!"+p1+".equals("+p2+")";
							}
							
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t221 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				predLTE1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predLTE2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t221;
				_t = _t.getNextSibling();
				
							if ((predLTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
									(predLTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
							{
								code=p1+"<="+p2;
							}
							else
							{
								code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")<0";
							}
							
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t222 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				predGTE1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predGTE2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t222;
				_t = _t.getNextSibling();
				
							if ((predGTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER)&&
									(predGTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
							{
								code=p1+">="+p2;
							}
							else
							{
								code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")>0";
							}
							
				break;
			}
			case B_INSET:
			{
				AST __t223 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t223;
				_t = _t.getNextSibling();
				
							code = p2 + ".has(" + p1 + ")";
						
				break;
			}
			case B_NOTINSET:
			{
				AST __t224 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t224;
				_t = _t.getNextSibling();
				
								code = "!"+p2 + ".has(" + p1 + ")";
							
				break;
			}
			case B_SUBSET:
			{
				AST __t225 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				pl1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pl2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t225;
				_t = _t.getNextSibling();
				
						    if (pl1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
						    if (pl2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
							code = p1 + ".isSubset(" + p2 + ")";
						
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t226 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				pl1a = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pl2a = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t226;
				_t = _t.getNextSibling();
				
						    if (pl1a.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
						    if (pl2a.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
							code = "!"+p1 + ".isSubset(" + p2 + ")";
							
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t227 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				pl1b = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pl2b = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t227;
				_t = _t.getNextSibling();
				
						    if (pl1b.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
						    if (pl2b.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
							code = p1 + ".isProperSubset(" + p2 + ")";
							
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t228 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				pl1c = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pl2c = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t228;
				_t = _t.getNextSibling();
				
						    if (pl1c.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
						    if (pl2c.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
							code = "(!"+p1 + ".isProperSubset(" + p2 + ")";
							
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t229 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t229;
				_t = _t.getNextSibling();
				
								code=p1+".concat("+p2+")";
							
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t230 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t230;
				_t = _t.getNextSibling();
				
								code = p2+".insertBeforeIndex(0,"+p1+")";
							
				break;
			}
			case B_APPSEQ:
			{
				AST __t231 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t231;
				_t = _t.getNextSibling();
				
								code=p1+".insertAfterIndex("+p1+".int_length()-1,"+p2+")";
							
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t232 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t232;
				_t = _t.getNextSibling();
				
								code=p1+".prefix("+p2+")";
							
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t233 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t233;
				_t = _t.getNextSibling();
				
								code="org.jmlspecs.b2jml.util.ModelUtils.suffixseq("+p1+","+p2+")";	
							
				break;
			}
			case B_RELATION:
			{
				AST __t234 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				predr1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predr2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t234;
				_t = _t.getNextSibling();
				
				/*			code="(new org.jmlspecs.b2jml.util.Relation("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.Relation<" + 
								   toJML_TreeWalker.typeToString(predr1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predr2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
						
				break;
			}
			case B_PARTIAL:
			{
				AST __t235 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				predp1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predp2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t235;
				_t = _t.getNextSibling();
				
				/*         code="(new org.jmlspecs.b2jml.util.Partial("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.Partial<" + 
								   toJML_TreeWalker.typeToString(predp1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predp2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				break;
			}
			case B_TOTAL:
			{
				AST __t236 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				predt1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predt2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t236;
				_t = _t.getNextSibling();
				
				/*			code="(new org.jmlspecs.b2jml.util.Total("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.Total<" + 
								   toJML_TreeWalker.typeToString(predt1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predt2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
						
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t237 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				predpi1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predpi2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t237;
				_t = _t.getNextSibling();
				
				/* 	   	code="(new org.jmlspecs.b2jml.util.PartialInject("+p1+","+p2+"))";*/
							code = "(new org.jmlspecs.b2jml.util.PartialInject<" + 
								   toJML_TreeWalker.typeToString(predpi1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predpi2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t238 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				predti1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predti2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t238;
				_t = _t.getNextSibling();
				
				/*           	code="(new org.jmlspecs.b2jml.util.TotalInject("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.TotalInject<" + 
								   toJML_TreeWalker.typeToString(predti1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predti2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t239 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				predps1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predps2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t239;
				_t = _t.getNextSibling();
				
				/*     code="(new org.jmlspecs.b2jml.util.PartialSurject("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.PartialSurject<" + 
								   toJML_TreeWalker.typeToString(predps1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predps2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t240 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				predts1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predts2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t240;
				_t = _t.getNextSibling();
				
				/*            	code="(new org.jmlspecs.b2jml.util.TotalSurject("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.TotalSurject<" + 
								   toJML_TreeWalker.typeToString(predts1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predts2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				
				break;
			}
			case B_BIJECTION:
			{
				AST __t241 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				predb1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				predb2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t241;
				_t = _t.getNextSibling();
				
				/*            	code="(new org.jmlspecs.b2jml.util.Bijection("+p1+","+p2+"))"; */
							code = "(new org.jmlspecs.b2jml.util.Bijection<" + 
								   toJML_TreeWalker.typeToString(predb1.getBType(), false, true) + ", " +
								   toJML_TreeWalker.typeToString(predb2.getBType(), false, true) + ">(" +
								   p1 + ", " + p2 + "))";
				
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t242 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t242;
				_t = _t.getNextSibling();
				
											code=p2+".restrictDomainTo("+p2+".domain().intersection("+p1+"))";
				
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t243 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t243;
				_t = _t.getNextSibling();
				
											code=p1+".restrictRangeTo("+p1+".range().intersection("+p2+"))";          
				
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t244 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				ds1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				ds2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t244;
				_t = _t.getNextSibling();
				
					if (ds1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
					if (!(ds2.getBType() instanceof INFIXE)) p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
					code="org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction("+p1+","+p2+")";
				
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t245 = _t;
				t41 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				rs1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				rs2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t245;
				_t = _t.getNextSibling();
				
					if (rs2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
					if (!(rs1.getBType() instanceof INFIXE)) p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
					        	code="org.jmlspecs.b2jml.util.ModelUtils.range_subtraction("+p1+","+p2+")";  
					
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t246 = _t;
				t42 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t246;
				_t = _t.getNextSibling();
				
					        	code="org.jmlspecs.b2jml.util.ModelUtils.overrideforward("+p1+","+p2+")";  	        
					
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t247 = _t;
				t43 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t248 = _t;
				t44 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t248;
				_t = _t.getNextSibling();
				
					        	code="org.jmlspecs.b2jml.util.ModelUtils.relprod("+p1+","+p2+")";  
					
				break;
			}
			case B_UNION:
			{
				AST __t249 = _t;
				t45 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				pu1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				pu2 = _t==ASTNULL ? null : (MyNode)_t;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t249;
				_t = _t.getNextSibling();
				
					        	ABType type1 = pu1.getBType();
					        	ABType type2 = pu2.getBType();
					        	if (type1 instanceof INFIXE && !(type2 instanceof INFIXE)) {
					        		if (toJML_TreeWalker.isFunction(type1)) {
					        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p2 + ")";
					        		} else {
					        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
					        		}
					        	} else if (type2 instanceof INFIXE && !(type1 instanceof INFIXE)) {
					        		if (toJML_TreeWalker.isFunction(type2)) {
					        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p1 + ")";
					        		} else {
					        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
					        		}
					        	}
					        	code=p1+".union("+p2+")";
					
				break;
			}
			case B_INTER:
			{
				AST __t250 = _t;
				t46 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t250;
				_t = _t.getNextSibling();
				
									code=p1+".intersection("+p2+")";
								
				break;
			}
			case B_MAPLET:
			{
				AST __t251 = _t;
				t48 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t251;
				_t = _t.getNextSibling();
				
									code="org.jmlspecs.b2jml.util.ModelUtils.maplet("+p1+","+p2+")";  	   
								
				break;
			}
			case LIST_VAR:
			{
				AST __t252 = _t;
				t49 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t252;
				_t = _t.getNextSibling();
				
									code=p1+","+p2;
								
				break;
			}
			case B_NOT:
			{
				AST __t253 = _t;
				MyNode tmp4_AST_in = (MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t253;
				_t = _t.getNextSibling();
				
								code="!"+p1;
							
				break;
			}
			case B_RAN:
			{
				AST __t254 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t254;
				_t = _t.getNextSibling();
				
								code=p1+".range()";
							
				break;
			}
			case B_DOM:
			{
				AST __t255 = _t;
				MyNode tmp6_AST_in = (MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t255;
				_t = _t.getNextSibling();
				
								code=p1+".domain()";
							
				break;
			}
			case B_MIN:
			{
				AST __t256 = _t;
				MyNode tmp7_AST_in = (MyNode)_t;
				match(_t,B_MIN);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t256;
				_t = _t.getNextSibling();
				
								code="org.jmlspecs.b2jml.util.ModelUtils.min("+p1+")";
							
				break;
			}
			case B_MAX:
			{
				AST __t257 = _t;
				MyNode tmp8_AST_in = (MyNode)_t;
				match(_t,B_MAX);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t257;
				_t = _t.getNextSibling();
				
								code="org.jmlspecs.b2jml.util.ModelUtils.max("+p1+")";
							
				break;
			}
			case B_CARD:
			{
				AST __t258 = _t;
				MyNode tmp9_AST_in = (MyNode)_t;
				match(_t,B_CARD);
				_t = _t.getFirstChild();
				p1=predicate(_t);
				_t = _retTree;
				_t = __t258;
				_t = _t.getNextSibling();
				
								code=p1+".int_size()";
							
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
			case BTRUE:
			{
				
								String cd = "";
								
				cs = _t==ASTNULL ? null : (MyNode)_t;
				cd=cset_description(_t);
				_t = _retTree;
				
							// toJML begin
							// TODO: fix to use types rather than HashMap
							code = cd;
							/*
							String type = cd.get("types");
							if (type.length()>=5 && type.substring(0,5).compareTo("PROD(")==0)
							{
								listPredicate.add("PROD");
							}
							else
							{
								if (type.length()>=5 && type.substring(0,5).compareTo("ENUM(")==0)
								{
									listPredicate.add("ENUM");
								}
								else
								{
									if (type.length()>=4 && type.substring(0,4).compareTo("MULT")==0)
									{
										listPredicate.add("MULT");
									}
									else
									{
										listPredicate.add("BASIC");
									}
								}
							}
							listPredicate.add(cd.get("vars"));
							*/
							// toJML end
						
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
		return code;
	}
	
	public final void uses(AST _t) throws RecognitionException {
		
		MyNode uses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t25 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_USES);
			_t = _t.getFirstChild();
			listNames(_t);
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
	
	public final void includes(AST _t) throws RecognitionException {
		
		MyNode includes_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t27 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INCLUDES);
			_t = _t.getFirstChild();
			listInstanciation(_t);
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
	
	public final void sees(AST _t) throws RecognitionException {
		
		MyNode sees_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t29 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_SEES);
			_t = _t.getFirstChild();
			listNames(_t);
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
	
	public final void extendeds(AST _t) throws RecognitionException {
		
		MyNode extendeds_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t23 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_EXTENDS);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t23;
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
			AST __t39 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_PROMOTES);
			_t = _t.getFirstChild();
			listNames(_t);
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
	
	public final void imports(AST _t) throws RecognitionException {
		
		MyNode imports_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t37 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_IMPORTS);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t37;
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
				AST __t33 = _t;
				MyNode tmp10_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				paramRenameValuation(_t);
				_t = _retTree;
				_t = __t33;
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
				AST __t31 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				nameRenamed(_t);
				_t = _retTree;
				_t = __t31;
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
				AST __t35 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				paramRenameValuation(_t);
				_t = _retTree;
				list_New_Predicate(_t);
				_t = _retTree;
				_t = __t35;
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
	
	public final String  list_New_Predicate(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
					code="";
					String l1="";
					String l2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t192 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				l1=list_New_Predicate(_t);
				_t = _retTree;
				l2=new_predicate(_t);
				_t = _retTree;
				_t = __t192;
				_t = _t.getNextSibling();
				
								code=l1+","+l2;
							
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				code=new_predicate(_t);
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
		return code;
	}
	
	public final String  sets_declaration(AST _t) throws RecognitionException {
		String code;
		
		MyNode sets_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			code="";
			String s1="";
			String s2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t51 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				s1=sets_declaration(_t);
				_t = _retTree;
				s2=sets_declaration(_t);
				_t = _retTree;
				_t = __t51;
				_t = _t.getNextSibling();
				
						code=s1+"\n"+s2;
					
				break;
			}
			case B_COMMA:
			{
				AST __t52 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				s1=sets_declaration(_t);
				_t = _retTree;
				s2=sets_declaration(_t);
				_t = _retTree;
				_t = __t52;
				_t = _t.getNextSibling();
				
						code=s1+","+s2;
					
				break;
			}
			case B_IDENTIFIER:
			case B_EQUAL:
			{
				code=set_declaration(_t);
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
		return code;
	}
	
	public final String  set_declaration(AST _t) throws RecognitionException {
		String code;
		
		MyNode set_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode set = null;
		MyNode name = null;
		
			String vs = "toJML valuation_set";
			code="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_EQUAL)) {
				AST __t54 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				set = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				vs=valuation_set(_t);
				_t = _retTree;
				_t = __t54;
				_t = _t.getNextSibling();
				
						if (set.getBType() instanceof ENUM){
							String stype=toJML_TreeWalker.typeToString(set.getBType());
							code="  //@ public static final ghost JMLEqualsSet<"+stype+"> " + set.getText() 
													+ "= JMLEqualsSet.convertFrom(new "+ stype+ "[] "+vs+");"; 
						} else {
							code="  //@public final ghost JMLEqualsSet<Integer> "+set.getText()+" = "+vs+ ";";
						}
					
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
				//		code="  //@ public model JMLEqualsSet<Integer> " + #name.getText()+";";
						code="  //@ public final ghost " + toJML_TreeWalker.typeToString(name.getBType()) + " " + name.getText()+";";
					
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
		return code;
	}
	
	public final String  valuation_set(AST _t) throws RecognitionException {
		String code = new String();
		
		MyNode valuation_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		
			String lc="";
			String lc2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t56 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				lc=list_couple(_t);
				_t = _retTree;
				_t = __t56;
				_t = _t.getNextSibling();
				
						code = "{" + lc + "}";
					
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
				AST __t57 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				valuation_set(_t);
				_t = _retTree;
				valuation_set(_t);
				_t = _retTree;
				_t = __t57;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t58 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				lc=valuation_set(_t);
				_t = _retTree;
				_t = __t58;
				_t = _t.getNextSibling();
				
						code="+"+lc;
					
				break;
			}
			case B_MINUS:
			{
				AST __t59 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				lc=valuation_set(_t);
				_t = _retTree;
				lc2=valuation_set(_t);
				_t = _retTree;
				_t = __t59;
				_t = _t.getNextSibling();
				
						code=lc+".difference("+lc2+")";
					
				break;
			}
			case B_IDENTIFIER:
			{
				t5 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
						code=t5.getText();
					
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
				code=basic_sets(_t);
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
		return code;
	}
	
	public final String  list_couple(AST _t) throws RecognitionException {
		String s = new String();
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				
					String lc = "toJML list_couple";
					String c = "toJML couple";
				
				AST __t61 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				lc=list_couple(_t);
				_t = _retTree;
				c=couple(_t);
				_t = _retTree;
				_t = __t61;
				_t = _t.getNextSibling();
				
						s = lc + ", " + c;
					
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				
							String c = "toJML couple";
						
				c=couple(_t);
				_t = _retTree;
				
							s = c;
						
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
		return s;
	}
	
	public final void is_record(AST _t) throws RecognitionException {
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t01 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t185 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				_t = __t185;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t186 = _t;
				t01 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				_t = __t186;
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
	
	public final String  basic_sets(AST _t) throws RecognitionException {
		String code=new String();
		
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
				code = "org.jmlspecs.b2jml.util.B_Types.B_Int";
				break;
			}
			case 87:
			{
				t2 = (MyNode)_t;
				match(_t,87);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Int1";
				break;
			}
			case LITERAL_INTEGER:
			{
				t3 = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Integer";
				break;
			}
			case 89:
			{
				t4 = (MyNode)_t;
				match(_t,89);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Integer1";
				break;
			}
			case LITERAL_BOOL:
			{
				t5 = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Boolean";
				break;
			}
			case LITERAL_NAT:
			{
				t6 = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Nat";
				break;
			}
			case 92:
			{
				t7 = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Nat1";
				break;
			}
			case LITERAL_NATURAL:
			{
				t8 = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Natural";
				break;
			}
			case 94:
			{
				t9 = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_Natural1";
				break;
			}
			case LITERAL_STRING:
			{
				t10 = (MyNode)_t;
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				code = "org.jmlspecs.b2jml.util.B_Types.B_String";
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
		return code;
	}
	
	public final String  couple(AST _t) throws RecognitionException {
		String code = new String();
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
			
			String pc="";
			String as1="";
			String as2=""; 
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t63 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				as1=a_set_value(_t);
				_t = _retTree;
				as2=a_set_value(_t);
				_t = _retTree;
				_t = __t63;
				_t = _t.getNextSibling();
				
						code="new JMLEqualsEqualsPair("+as1+","+as2+")";
					
				break;
			}
			case B_LPAREN:
			{
				AST __t64 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				pc=parent_couple(_t);
				_t = _retTree;
				_t = __t64;
				_t = _t.getNextSibling();
				
							code=pc;
					
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			{
				
							String asv = "toJML a_set_value";
						
				asv=a_set_value(_t);
				_t = _retTree;
				
							code = asv;
						
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
		return code;
	}
	
	public final String  a_set_value(AST _t) throws RecognitionException {
		String code = new String();
		
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
				
						code = "\""+name.getText()+"\"";
						enumConstants.add(name.getText());
					
				break;
			}
			case B_MINUS:
			{
				AST __t69 = _t;
				mi = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				val1 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				_t = __t69;
				_t = _t.getNextSibling();
				
						code="-"+val1.getText();
					
				break;
			}
			case B_NUMBER:
			{
				val = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
						code=val.getText();
					
				break;
			}
			case LITERAL_TRUE:
			{
				tr = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
						code="true";
					
				break;
			}
			case LITERAL_FALSE:
			{
				fa = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
						code="false";
					
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
		return code;
	}
	
	public final String  parent_couple(AST _t) throws RecognitionException {
		String code;
		
		MyNode parent_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
			code="";
			String asv1="";
			String asv2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				AST __t66 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				asv1=a_set_value(_t);
				_t = _retTree;
				asv2=a_set_value(_t);
				_t = _retTree;
				_t = __t66;
				_t = _t.getNextSibling();
				
						code="new JMLEqualsEqualsPair("+asv1+","+asv2+")";				
					
				break;
			}
			case B_COMMA:
			{
				AST __t67 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				asv1=a_set_value(_t);
				_t = _retTree;
				asv2=a_set_value(_t);
				_t = _retTree;
				_t = __t67;
				_t = _t.getNextSibling();
				
						code=asv1+","+asv2;
					
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			{
				code=a_set_value(_t);
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
		return code;
	}
	
	public final String  list_valuation(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code="";
			String l="";
			String s="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t73 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				l=list_valuation(_t);
				_t = _retTree;
				s=set_valuation(_t);
				_t = _retTree;
				_t = __t73;
				_t = _t.getNextSibling();
				
						code=l+";\n"+s;
					
			}
			else if ((_t.getType()==B_EQUAL)) {
				code=set_valuation(_t);
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
		return code;
	}
	
	public final String  set_valuation(AST _t) throws RecognitionException {
		String code;
		
		MyNode set_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		
			code="";
			String ns="";
		
		
		try {      // for error handling
			AST __t75 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			ns=new_set_or_constant_valuation(_t);
			_t = _retTree;
			_t = __t75;
			_t = _t.getNextSibling();
			
					code=name.getText()+"="+ns;
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  new_set_or_constant_valuation(AST _t) throws RecognitionException {
		String code;
		
		MyNode new_set_or_constant_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			code="";
		
		
		try {      // for error handling
			code=predicate(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  set_interval_value(AST _t) throws RecognitionException {
		String code;
		
		MyNode set_interval_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode a = null;
		
			code="";
			String idecl="";
		
		
		try {      // for error handling
			AST __t78 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			a = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			idecl=interval_declaration(_t);
			_t = _retTree;
			_t = __t78;
			_t = _t.getNextSibling();
			
					code=a.getText()+"="+idecl;
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  interval_declaration(AST _t) throws RecognitionException {
		String code;
		
		MyNode interval_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code="";
			String p1="";
			String p2="";
		
		
		try {      // for error handling
			AST __t80 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_RANGE);
			_t = _t.getFirstChild();
			p1=predicate(_t);
			_t = _retTree;
			p2=predicate(_t);
			_t = _retTree;
			_t = __t80;
			_t = _t.getNextSibling();
			
					code="new org.jmlspecs.b2jml.util.Interval("+p1+","+p2+")";
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final void set_rename_value(AST _t) throws RecognitionException {
		
		MyNode set_rename_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name1 = null;
		MyNode name2 = null;
		
		try {      // for error handling
			AST __t82 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name1 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			name2 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			_t = __t82;
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
				AST __t96 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				list_def(_t);
				_t = _retTree;
				definition(_t);
				_t = _retTree;
				_t = __t96;
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
				AST __t98 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOUBLE_EQUAL);
				_t = _t.getFirstChild();
				paramName(_t);
				_t = _retTree;
				formalText(_t);
				_t = _retTree;
				_t = __t98;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_ASTRING)) {
				t3 = (MyNode)_t;
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
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==EXP_DEF)) {
				AST __t100 = _t;
				MyNode tmp16_AST_in = (MyNode)_t;
				match(_t,EXP_DEF);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				_t = __t100;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==SUBST_DEF)) {
				AST __t101 = _t;
				MyNode tmp17_AST_in = (MyNode)_t;
				match(_t,SUBST_DEF);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				_t = __t101;
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
	
	public final String  instruction(AST _t) throws RecognitionException {
		String code;
		
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
		
				code="instruction";
				String p=""; //predicate
				String ins=""; //instruction
				String ins2="";
				String ins3="";
				String ins4="";
				Map<String,ABType> ident=new LinkedHashMap<String,ABType>(); //identifiers		
			
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t117 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				ins=instruction(_t);
				_t = _retTree;
				ins2=instruction(_t);
				_t = _retTree;
				_t = __t117;
				_t = _t.getNextSibling();
				
						code=ins+"&&\n"+ins2;
					
				break;
			}
			case SEQUENTIAL:
			{
				AST __t118 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t118;
				_t = _t.getNextSibling();
				
						if (6>3) 
							throw new IllegalStateException("Sequential statement not supported");
					
				break;
			}
			case LITERAL_skip:
			{
				AST __t119 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_skip);
				_t = _t.getFirstChild();
				
					
				_t = __t119;
				_t = _t.getNextSibling();
				code="true";
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t120 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				code=instruction(_t);
				_t = _retTree;
				_t = __t120;
				_t = _t.getNextSibling();
				break;
			}
			case B_BEGIN_POST:
			{
				AST __t121 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BEGIN_POST);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t121;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t122 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				ins=instruction(_t);
				_t = _retTree;
				_t = __t122;
				_t = _t.getNextSibling();
				
					  hasPRE = true;
						code="/*@ public normal_behavior\n   requires "+p+";\n";
						for (String st1:returnVal1.keySet()){
						  assign.remove(st1);
						}
						if (assign.isEmpty()) code+="   assignable "+back+"nothing;";
						else {
							code+="   assignable ";
							for (String st2:assign) {
								code+=st2+",";
							}
							code=code.substring(0,code.length()-1)+";"; 	
						}
						code += "\n   ensures ";
						//if (returnVal.size()>1){
						//existential quantifiers
						Object[] keys = returnVal1.keySet().toArray();
						for (int i = 0; i < keys.length; i++) {
							code += "("+back+"exists "+returnVal1.get(keys[i])+" "+keys[i]+"; ";
						}
						code += ins;
						if (keys.length == 1) {
							code += "\n   && " + back + "result == " + keys[0] + ")";
						} else {
							for (int i = 0; i < keys.length; i++) {
								code += "\n   &&"+back+"result["+i+"] == "+keys[i];
							}
							for (int i = 0; i < keys.length; i++) {
								code += ")";
							}
						}
						/*
						String retStr = "";
						String retStr2 = "";
							for (int i=0;i< returnVal1.size();i++) {
							  String key = (String) keys[i];
								//code += "\n&& "+back+"result.get("+i+").equals("+returnVal.get(i)+")";
								//code += "\n&& "+back+"exist "+returnVal1.get(key)+" "+key+"; result.get("+i+")=="+key;	
								retStr += "("+back+"exists "+returnVal1.get(key)+" "+key+"; ";
								retStr2 += "\n&&"+back+"result.get("+i+")=="+key;
							}
							code = code+"\n   ensures "+retStr+"\n"+ins+retStr2;
							if (returnVal1.size()>0)
								code = code+" && "+back+"result.int_size()=="+returnVal1.size();
							for (int i=0; i<returnVal1.size();i++){
								code+=")";
							}
							*/
						//}
						code += ";\n";
						code += "also public exceptional_behavior\n   requires !(" + p + 
						");\n   assignable " + back + "nothing;\n   signals (Exception) true;";
					
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t123 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				ins=instruction(_t);
				_t = _retTree;
				_t = __t123;
				_t = _t.getNextSibling();
				
						code="/*@ assert "+p+"&&\n"+ins+";/*@";
					
				break;
			}
			case LITERAL_IF:
			{
				AST __t124 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				ins=branche_then(_t);
				_t = _retTree;
				
							ArrayList<String> listInstrIf=new ArrayList<String>(); 
							listInstrIf.add(p);
							listInstrIf.add(ins);
							stackOps.push(listInstrIf);
						
				{
				_loop126:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
					}
					else {
						break _loop126;
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
				_t = __t124;
				_t = _t.getNextSibling();
				
						ArrayList<String> listIf=stackOps.pop();
						String ifStmt=new String();
						for (int i=0;i<listIf.size()-1;i++){
							if (i%2==0) {
								ifStmt+="("+back+"old("+listIf.get(i)+") ? ";
							} else {
								ifStmt+=listIf.get(i) + ":";
							}
						}
						if (listIf.size() % 2==1){
							ifStmt+=listIf.get(listIf.size()-1);
						}	else {
							ifStmt+=listIf.get(listIf.size()-1)+": true";
						}	
						for (int j=0;j<listIf.size()%2;j++){
							ifStmt+=")";
						}
						code=ifStmt;
					
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t128 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				ins=list_or(_t);
				_t = _retTree;
				_t = __t128;
				_t = _t.getNextSibling();
				
						code=ins;
					
				break;
			}
			case LITERAL_CASE:
			{
				AST __t129 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				ins=branche_either(_t);
				_t = _retTree;
				
						ArrayList<String> listInstrCas=new ArrayList<String>(); 
						listInstrCas.add(p);
						stackOps.add(listInstrCas);
						ArrayList<String> temp = new ArrayList<String>();
					
				{
				_loop131:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
					}
					else {
						break _loop131;
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
				_t = __t129;
				_t = _t.getNextSibling();
				
						ArrayList<String> listCas=stackOps.pop();
						String casStmt=ins;
						if (listCas.size()>1){
							casStmt+=" && \n";
						}		
						String casDeflt=new String();
						String v=listCas.get(0);
						for (int i=1;i<listCas.size()-1;i++){
							if (i%2==1) {
								casStmt+="("+v+"=="+back+"old("+listCas.get(i)+") ==> ";
								casDeflt+=v+"!="+back+"old("+listCas.get(i)+") && ";
							} else {
									casStmt+=listCas.get(i)+") && \n";
							}
						} 
						if (listCas.size() % 2==0){
							casStmt+="("+casDeflt.substring(0,casDeflt.length()-3)+"==>"+ listCas.get(listCas.size()-1)+")";
						} else {
							casStmt+=listCas.get(listCas.size()-1)+")";
						}
						code=casStmt;
					
				break;
			}
			case LITERAL_ANY:
			{
				AST __t133 = _t;
				MyNode tmp18_AST_in = (MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				
						index.Retract();
					
				ident=listTypedIdentifier(_t);
				_t = _retTree;
				p=predicate(_t);
				_t = _retTree;
				ins=instruction(_t);
				_t = _retTree;
				_t = __t133;
				_t = _t.getNextSibling();
				
						String var;
						ABType type;
						int count = 0;
						code = "";
						for (String s:ident.keySet()){
							var=s;
							type=ident.get(s);
							count++;
							code += "("+back+"exists "+toJML_TreeWalker.typeToString(type)+" "+var + ";";
						}
						code+= back+"old("+p+"); "+ins;
						for (int i = 0; i < count; i++) {
							code += ")";
						} 
					
				break;
			}
			case LITERAL_LET:
			{
				AST __t134 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				ident=listTypedIdentifier(_t);
				_t = _retTree;
				p=list_equal(_t);
				_t = _retTree;
				ins=instruction(_t);
				_t = _retTree;
				_t = __t134;
				_t = _t.getNextSibling();
				
						String var="";
						ABType type=new ABType();
						for (String s:ident.keySet()){
							var=s;
							type=ident.get(s);
						}
						code="("+back+"exists "+toJML_TreeWalker.typeToString(type)+" "+var+"; "+back+"old("+p+") && "+ins+")"; 
					
						//code=ins+"&&"+ins3;
				
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t135 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				ins=instruction(_t);
				_t = _retTree;
				
						ArrayList<String> listInstrSel=new ArrayList<String>(); 
						listInstrSel.add(p);
						listInstrSel.add("(" + ins + ")");
						stackOps.add(listInstrSel);
						index.Retract();
					
				{
				_loop137:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
					}
					else {
						break _loop137;
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
				_t = __t135;
				_t = _t.getNextSibling();
						
						ArrayList<String> listSel=stackOps.pop();
						String selStmt=new String();
						String selCond=new String();
						for (int i=0;i<listSel.size()-1;i++){
							if (i%2==0) {
								selStmt+="("+back+"old("+listSel.get(i)+") ==> ";
								selCond+="!"+back+"old("+listSel.get(i)+") && ";
							} else {
								selStmt+=listSel.get(i)+") && \n";
							}
						} 
						if (listSel.size() % 2 ==1){
							selStmt+="(("+selCond.substring(0,selCond.length()-4)+") ==> "+listSel.get(listSel.size()-1)+")";
						} else {
							selStmt+=listSel.get(listSel.size()-1)+")";
						}
						code=selStmt;	
						
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t139 = _t;
				t13 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				variant_or_no(_t);
				_t = _retTree;
				_t = __t139;
				_t = _t.getNextSibling();
				
								if (5>3) throw new IllegalStateException("while statement not supported");
						
				break;
			}
			case LITERAL_VAR:
			{
				AST __t140 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				ident=listTypedIdentifier(_t);
				_t = _retTree;
				
								HashMap<String, Boolean> occurs = new HashMap<String, Boolean>();
								for (String s:ident.keySet()){
									occurs.put(s, assign.contains(s));
								}
						
				ins=instruction(_t);
				_t = _retTree;
				_t = __t140;
				_t = _t.getNextSibling();
				
							code="";
							String var="";
							ABType type;
							for (String s:ident.keySet()){
								code += "("+back+"exists ";
								type=ident.get(s);
								code+=toJML_TreeWalker.typeToString(type);
								code+=" "+s+"; ";
							}
							code += "\n   " + ins; 
							for (String s : ident.keySet()) {
								if (!occurs.get(s)) {
									assign.remove(s);
								}
								code += ")";
							}
							
					
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
				code=simple_affect(_t);
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
		return code;
	}
	
	public final String  list_assertions(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
					code="";
					String l1="";
					String l2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t105 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				l1=list_assertions(_t);
				_t = _retTree;
				l2=list_assertions(_t);
				_t = _retTree;
				_t = __t105;
				_t = _t.getNextSibling();
				
								code=l1+"&&"+l2;
							
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				code=predicate(_t);
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
		return code;
	}
	
	public final String  listOperation(AST _t) throws RecognitionException {
		String code;
		
		MyNode listOperation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
					code="";
					String l1="";
					String l2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t111 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				l1=listOperation(_t);
				_t = _retTree;
				l2=operation(_t);
				_t = _retTree;
				_t = __t111;
				_t = _t.getNextSibling();
				
								code=l1+"\n"+l2;
							
			}
			else if ((_t.getType()==OP_DEF)) {
				code=operation(_t);
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
		return code;
	}
	
	public final String  operation(AST _t) throws RecognitionException {
		String code;
		
		MyNode operation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code="/*@ public normal_behavior\n";
			String l1="";
			String l2="";
			//returnVal = new ArrayList<String>();
			returnVal1 = new LinkedHashMap<String,String>();
			hasPRE = false;
		
		
		try {      // for error handling
			AST __t113 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			l1=operationHeader(_t);
			_t = _retTree;
			
					index.Add();
				
			l2=instruction(_t);
			_t = _retTree;
			
					index.Retract();
				
			_t = __t113;
			_t = _t.getNextSibling();
			
					if (returnVal1.size() == 0) {
						l1 = "void " + l1;
					} else if (returnVal1.size() == 1) {
						l1 = returnVal1.get(returnVal1.keySet().iterator().next()) + " " + l1;
					} else {
						l1 = "Object [] " + l1;
					}
					if (!hasPRE){
						for (String st1:returnVal1.keySet()){
						   assign.remove(st1);
						}
						
						code+="  requires true;\n";
					
						if (assign.isEmpty()) code+="  assignable "+back+"nothing;";
						else {
							code+="  assignable ";
							for (String st2:assign) {
								code+=st2+",";
							}
							code=code.substring(0,code.length()-1)+";"; 
						} 	
						code += "\n   ensures ";
						if (returnVal1.size() == 0) {
							code += l2;
						} else {
							Object [] keys = returnVal1.keySet().toArray();
							for (int i = 0; i < keys.length; i++) {
								code += " ("+back+"exists "+returnVal1.get(keys[i])+" "+keys[i]+"; ";
							}
							code += "\n" + l2;
							if (keys.length == 1) {
								code += "\n   && " + back + "result == " + keys[0] + ")";
							} else {
								for (int i = 0; i < keys.length; i++) {
									code += "\n   &&"+back+"result["+i+"] == "+keys[i];
								}
								for (int i = 0; i < keys.length; i++) {
									code += ")";
								}
							}
							/*
						String retStr = "";
						String retStr2 = "";
						for (int i=0;i< returnVal1.size();i++) {
						  String key = (String) keys[i];
							//code += "\n&& "+back+"result.get("+i+").equals("+returnVal.get(i)+")";
							//code += "\n&& "+back+"exist "+returnVal1.get(key)+" "+key+"; result.get("+i+")=="+key;	
							retStr += "("+back+"exists "+returnVal1.get(key)+" "+key+"; ";
							retStr2 += "\n&&"+back+"result.get("+i+")=="+key;
						}
						code = code+"\nensures "+retStr+"\n"+l2+retStr2;
						if (returnVal1.size()>1)
							code = code+" && "+back+"result.length =="+returnVal1.size();
						for (int i=0; i<returnVal1.size();i++){
							code+=")";
							
						} */
						}
						code += "; */ \n"+ "public abstract " + l1 + ";\n";
					} else{ 
						code="\n"+l2+"*/ \n"+ "public abstract " + l1 + ";\n"; // l1 & l2 reverse
					}
					assign.clear();
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  operationHeader(AST _t) throws RecognitionException {
		String code;
		
		MyNode operationHeader_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			operParam = true;
			code="";
			String l1="";
			String l2="";
			Map<String,ABType> ident=new LinkedHashMap<String,ABType>();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				AST __t115 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				ident=listTypedIdentifier(_t);
				_t = _retTree;
				l2=paramName(_t);
				_t = _retTree;
				_t = __t115;
				_t = _t.getNextSibling();
				
							code = l2;
							for (String temp:ident.keySet()) {
								returnVal1.put(temp, "");
							}
					/*
							if (ident.keySet().size()==1) {
								String name="";
								for (String temp:ident.keySet())
									name=temp;
								//returnVal.add(name);
								returnVal1.put(name,"");
								code=l2;
							} else {
							  for (String temp:ident.keySet())
								  //returnVal.add(temp);
								  returnVal1.put(temp, "");
								code=l2;
							}
						*/
						
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_LPAREN)) {
				code=paramName(_t);
				_t = _retTree;
				
				//    		code="void "+ code; 
						operParam = false;
					
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
		return code;
	}
	
	public final String  branche_then(AST _t) throws RecognitionException {
		String code;
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code="";
			String ins="true";
		
		
		try {      // for error handling
			AST __t150 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
			
				index.Add();
			
			ins=instruction(_t);
			_t = _retTree;
			
				index.Retract();
			
			_t = __t150;
			_t = _t.getNextSibling();
			
					code=ins;
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final void branche_elsif(AST _t) throws RecognitionException {
		
		MyNode branche_elsif_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
			String p="";
			String ins="";
		
		
		try {      // for error handling
			AST __t154 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			p=predicate(_t);
			_t = _retTree;
			ins=instruction(_t);
			_t = _retTree;
			_t = __t154;
			_t = _t.getNextSibling();
			
					stackOps.peek().add(p);
					stackOps.peek().add("(" + ins + ")");
				
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
		
			String ins="true";
		
		
		try {      // for error handling
			AST __t152 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
			ins=instruction(_t);
			_t = _retTree;
			_t = __t152;
			_t = _t.getNextSibling();
			
					stackOps.peek().add("(" + ins + ")");
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  list_or(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
			code="";
			String ins="";
			String ins2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t142 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				ins=list_or(_t);
				_t = _retTree;
				ins2=instruction(_t);
				_t = _retTree;
				_t = __t142;
				_t = _t.getNextSibling();
				
				code="(("+ins+")&& !("+ins2+"))||\n (("+ins2+")&&!("+ins+"))";
				
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				code=instruction(_t);
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
		return code;
	}
	
	public final String  branche_either(AST _t) throws RecognitionException {
		String code=new String();
		
		MyNode branche_either_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode pn = null;
		
			String p="";
			String ins="";
		
		
		try {      // for error handling
			AST __t146 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			pn = _t==ASTNULL ? null : (MyNode)_t;
			p=predicate(_t);
			_t = _retTree;
			ins=instruction(_t);
			_t = _retTree;
			_t = __t146;
			_t = _t.getNextSibling();
			
					String p1=stackOps.peek().get(0);
					//code = "(("+c+"old("+p+")=="+p1+")==>"+ins+")";
					String t = toJML_TreeWalker.typeToString(pn.getBType());
					if (t.equals("Type_Error")) {
						String typeStrin = pn.getBType().toString();			
									if (typeStrin.contains("STRING") || typeStrin.contains("ENUM") ) 
							code = "JMLEqualsSet.convertFrom(new String[] "+p+")==>"+ins+")";
						else if (typeStrin.contains("BOOL"))
							code = "JMLEqualsSet.convertFrom(new Boolean[] "+p+")==>"+ins+")";
						else if (typeStrin.contains("INFIXE") || typeStrin.contains("EQUAL") ||
										typeStrin.contains("LIST") || typeStrin.contains("PROD") ||
										typeStrin.contains("RANGE"))
							code = "JMLEqualsSet.convertFrom(new JMLEqualsToEqualsRelation[] "+p+")==>"+ins+")";
					  else if(typeStrin.contains("UNARY") || typeStrin.contains("SET") ||
					  				typeStrin.contains("POW"))
					 		code = "JMLEqualsSet.convertFrom(new JMLEqualsSet[] "+p+")==>"+ins+")";
					  else 
							code = "JMLEqualsSet.convertFrom(new Integer[] "+p+")==>"+ins+")";
						
					} else { 
						code = "JMLEqualsSet.singleton("+p+").has("+p1+")==>"+ins+")";
					}
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final void branche_or(AST _t) throws RecognitionException {
		
		MyNode branche_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			String p="";
			String ins="";
		
		
		try {      // for error handling
			AST __t148 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			p=predicate(_t);
			_t = _retTree;
			ins=instruction(_t);
			_t = _retTree;
			_t = __t148;
			_t = _t.getNextSibling();
			
					stackOps.peek().add(p);
					stackOps.peek().add("(" + ins + ")");
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final String  list_equal(AST _t) throws RecognitionException {
		String code=new String();
		
		MyNode list_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			String le="";
			String ae="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_AND)) {
				AST __t161 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				le=list_equal(_t);
				_t = _retTree;
				ae=an_equal(_t);
				_t = _retTree;
				_t = __t161;
				_t = _t.getNextSibling();
				
						code=le+" && "+ae;
					
			}
			else if ((_t.getType()==B_EQUAL)) {
				code=an_equal(_t);
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
		return code;
	}
	
	public final void branche_when(AST _t) throws RecognitionException {
		
		MyNode branche_when_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			String p="";
			String inst="";
		
		
		try {      // for error handling
			AST __t144 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			p=predicate(_t);
			_t = _retTree;
			inst=instruction(_t);
			_t = _retTree;
			_t = __t144;
			_t = _t.getNextSibling();
			
							stackOps.peek().add(p);
							stackOps.peek().add("(" + inst + ")");
						
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
				AST __t156 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				AST __t157 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				_t = __t157;
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				_t = __t156;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t158 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				AST __t159 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				_t = __t159;
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				_t = __t158;
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
	
	public final String  simple_affect(AST _t) throws RecognitionException {
		String code;
		
		MyNode simple_affect_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode left = null;
		MyNode pnode = null;
		MyNode t2 = null;
		MyNode t4 = null;
		MyNode t = null;
		
			code="";
			String l1="";
			String p="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t180 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				left = _t==ASTNULL ? null : (MyNode)_t;
				l1=list_func_call(_t);
				_t = _retTree;
				pnode = _t==ASTNULL ? null : (MyNode)_t;
				p=predicate(_t);
				_t = _retTree;
				_t = __t180;
				_t = _t.getNextSibling();
				
						 if (!inInitially) {
								p = back + "old(" + p + ")";
							}
							ABType leftType = left.getBType();
							if (toJML_TreeWalker.isCollection(leftType)) {
								if (pnode.toString().equals("{}") || pnode.toString().equals("[]")) {
									code = l1 + ".isEmpty()";
								} else {
									code = l1 + ".equals(" + p + ")";
								}
							} else {
								code=l1+"=="+p;
							}
							if (!inInitially) {		
								if (!assign.contains(l1))
									assign.add(l1);
								if (returnVal1.keySet().contains(l1)) {
									returnVal1.put(l1, toJML_TreeWalker.typeToString(pnode.getBType()));
								}
							/*		
								  Object[] keys = returnVal1.keySet().toArray();
									for (int i=0;i<returnVal1.size();i++){
									  String key = (String) keys[i];
										if (l1.contains(key)){
										  String tp = toJML_TreeWalker.typeToString(pnode.getBType());
				//						  returnVal1.put(key,tp);
										  //code+="\n&&\\result.get("+i+") instanceof "+tp;
										  //outStmt.add("\\result.get("+i+") instanceof "+tp);
										}
									}
									*/
								}
							
				break;
			}
			case B_OUT:
			{
				AST __t181 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				l1=list_func_call(_t);
				_t = _retTree;
				p=func_call(_t);
				_t = _retTree;
				_t = __t181;
				_t = _t.getNextSibling();
				
								code=l1+"=="+p;
								if (!assign.contains(l1))
									assign.add(l1);
							
				break;
			}
			case INSET:
			{
				AST __t182 = _t;
				MyNode tmp19_AST_in = (MyNode)_t;
				match(_t,INSET);
				_t = _t.getFirstChild();
				l1=list_func_call(_t);
				_t = _retTree;
				p=predicate(_t);
				_t = _retTree;
				_t = __t182;
				_t = _t.getNextSibling();
				
								code=p+".has("+l1+")";
							
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t183 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				t = _t==ASTNULL ? null : (MyNode)_t;
				l1=list_func_call(_t);
				_t = _retTree;
				p=predicate(_t);
				_t = _retTree;
				_t = __t183;
				_t = _t.getNextSibling();
				
							 ABType Type = t.getBType();
							 String JMLType = toJML_TreeWalker.typeToString(Type);
							 code="("+back+"exists "+JMLType+" "+l1+"';"+back+"old("+p+".has("+l1+"')); "+l1+ " == "+back+"old("+l1+"'))";
							 //code=p+".has("+l1+")";
							 if (!inInitially){
								 if (!assign.contains(l1))
					                assign.add(l1);
				}
							
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_LPAREN:
			case B_QUOTEIDENT:
			case FUNC_CALL_PARAM:
			{
				code=a_func_call(_t);
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
		return code;
	}
	
	public final String  an_equal(AST _t) throws RecognitionException {
		String code="";
		
		MyNode an_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode n1 = null;
		
			String p="";
		
		
		try {      // for error handling
			AST __t163 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			n1 = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			p=predicate(_t);
			_t = _retTree;
			_t = __t163;
			_t = _t.getNextSibling();
			
					code=n1.getText()+".equals("+p+")";
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  func_call(AST _t) throws RecognitionException {
		String code;
		
		MyNode func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode pl = null;
		MyNode t3 = null;
		MyNode t4 = null;
		
				code="";
				String f="";
				String l="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t165 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				f=func_call(_t);
				_t = _retTree;
				_t = __t165;
				_t = _t.getNextSibling();
				
								code=f+".inverse()";
							
				break;
			}
			case APPLY_TO:
			{
				AST __t166 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				f=func_call(_t);
				_t = _retTree;
				pl = _t==ASTNULL ? null : (MyNode)_t;
				l=list_New_Predicate(_t);
				_t = _retTree;
				_t = __t166;
				_t = _t.getNextSibling();
				
							//if f is relation, element image, if function, apply
								code=f+".apply("+l+")";
							
				break;
			}
			case B_LPAREN:
			{
				AST __t167 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				f=func_call(_t);
				_t = _retTree;
				l=list_New_Predicate(_t);
				_t = _retTree;
				_t = __t167;
				_t = _t.getNextSibling();
				
								code=f+"("+l+")";
							
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t168 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				func_call(_t);
				_t = _retTree;
				_t = __t168;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				code=nameRenamed(_t);
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
		return code;
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
	
	public final String  list_identifier(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_identifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode n1 = null;
		MyNode n2 = null;
		
			code="";
			String l1="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t171 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				l1=list_identifier(_t);
				_t = _retTree;
				n1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				_t = __t171;
				_t = _t.getNextSibling();
				
								sqType.push(n1.getBType());
								code=l1+","+n1.getText();
							
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				n2 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
								sqType.push(n2.getBType());
								code=n2.getText();
							
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
		return code;
	}
	
	public final String  a_func_call(AST _t) throws RecognitionException {
		String code;
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			code="";
		
		
		try {      // for error handling
			code=afc(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return code;
	}
	
	public final String  afc(AST _t) throws RecognitionException {
		String code;
		
		MyNode afc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
			code="";
		String s1="";
		String s2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t174 = _t;
				MyNode tmp20_AST_in = (MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				s1=afc(_t);
				_t = _retTree;
				s2=listPredicate(_t);
				_t = _retTree;
				_t = __t174;
				_t = _t.getNextSibling();
				
						code=s1+".("+s2+")";
					
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t175 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				afc(_t);
				_t = _retTree;
				afc(_t);
				_t = _retTree;
				_t = __t175;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t176 = _t;
				MyNode tmp21_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				s1=afc(_t);
				_t = _retTree;
				s2=listPredicate(_t);
				_t = _retTree;
				_t = __t176;
				_t = _t.getNextSibling();
				
						code="("+s1+")."+s2;
					
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				code=nameRenamed(_t);
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
		return code;
	}
	
	public final String  listPredicate(AST _t) throws RecognitionException {
		String code;
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		code="";
		String s1="";
		String s2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==ELEM_SET)) {
				AST __t203 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				s1=listPredicate(_t);
				_t = _retTree;
				s2=predicate(_t);
				_t = _retTree;
				_t = __t203;
				_t = _t.getNextSibling();
				
							code=s1+","+s2;
							
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				code=predicate(_t);
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
		return code;
	}
	
	public final String  list_func_call(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code="";
			String l1="";
			String l2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t178 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				l1=list_func_call(_t);
				_t = _retTree;
				l2=list_func_call(_t);
				_t = _retTree;
				_t = __t178;
				_t = _t.getNextSibling();
				
						code=l1+","+l2;
					
			}
			else if ((_tokenSet_4.member(_t.getType()))) {
				code=a_func_call(_t);
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
		return code;
	}
	
	public final void listrecord(AST _t) throws RecognitionException {
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t188 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listrecord(_t);
				_t = _retTree;
				a_record(_t);
				_t = _retTree;
				_t = __t188;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
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
				AST __t190 = _t;
				MyNode tmp22_AST_in = (MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				predicate(_t);
				_t = _retTree;
				_t = __t190;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
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
	
	public final String  new_predicate(AST _t) throws RecognitionException {
		String code;
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
				code="";
				String l1="";
				String l2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t194 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				l1=new_predicate(_t);
				_t = _retTree;
				l2=predicate(_t);
				_t = _retTree;
				_t = __t194;
				_t = _t.getNextSibling();
				
								code=l1+";"+l2;	
							
				break;
			}
			case B_PARALLEL:
			{
				AST __t195 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				l1=new_predicate(_t);
				_t = _retTree;
				l2=predicate(_t);
				_t = _retTree;
				_t = __t195;
				_t = _t.getNextSibling();
				
								code=l1+" && "+l2;
							
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
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				code=predicate(_t);
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
		return code;
	}
	
	public final String  nameRenamedDecorated(AST _t) throws RecognitionException {
		String code;
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code = "";
			String s = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t199 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				s=nameRenamed(_t);
				_t = _retTree;
				
						code = s;
				
					
				_t = __t199;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				s=nameRenamed(_t);
				_t = _retTree;
				
							// toJML begin
							code = s;
							// TODO: fix to use real type info
							/*
							s.put("vars", nr.get("vars"));
							s.put("types", nr.get("types"));
							*/
							// toJML end
						
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
		return code;
	}
	
	public final String  nameRenameDecoratedInverted(AST _t) throws RecognitionException {
		String code;
		
		MyNode nameRenameDecoratedInverted_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			code = "";
			String s="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_TILDE)) {
				AST __t201 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				s=nameRenamedDecorated(_t);
				_t = _retTree;
				_t = __t201;
				_t = _t.getNextSibling();
				
									code = s+".inverse()";
								
			}
			else if (((_t.getType() >= B_IDENTIFIER && _t.getType() <= B_CPRED))) {
				code=nameRenamedDecorated(_t);
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
		return code;
	}
	
	public final String  cset_description(AST _t) throws RecognitionException {
		String code;
		
		MyNode cset_description_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode pnode1 = null;
		MyNode t1 = null;
		
			code = "";
			String p="";
			String s1;
			String s2;
		
		
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
				code=basic_sets(_t);
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
			case BTRUE:
			{
				
							String cv = "";
						
				cv=cbasic_value(_t);
				_t = _retTree;
				
							// toJML begin
							code = cv;
							// toJML end
						
				break;
			}
			case LITERAL_bool:
			{
				AST __t260 = _t;
				MyNode tmp23_AST_in = (MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				_t = __t260;
				_t = _t.getNextSibling();
				
						code=p;
						
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t261 = _t;
				MyNode tmp24_AST_in = (MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				p=listPredicate(_t);
				_t = _retTree;
				_t = __t261;
				_t = _t.getNextSibling();
				
						code=p;
						
				break;
			}
			case B_RANGE:
			{
				AST __t262 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				s1=predicate(_t);
				_t = _retTree;
				s2=predicate(_t);
				_t = _retTree;
				_t = __t262;
				_t = _t.getNextSibling();
				
							code = "new org.jmlspecs.b2jml.util.Interval("+s1+","+s2+")";
						
				break;
			}
			case B_EMPTYSET:
			{
				t3 = (MyNode)_t;
				match(_t,B_EMPTYSET);
				_t = _t.getNextSibling();
				
						code="JMLEqualsSet.EMPTY";
						
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t263 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				pnode1 = _t==ASTNULL ? null : (MyNode)_t;
				p=cvalue_set(_t);
				_t = _retTree;
				_t = __t263;
				_t = _t.getNextSibling();
				
						String tys = toJML_TreeWalker.typeToString(pnode1.getBType());
						if (!tys.equals("Type_Error")) {
							String set = "toSet";
							if (pnode1.getBType() instanceof INFIXE) {
								set = "toRel";
								t4.setBType(pnode1.getBType());
							}
							code = "org.jmlspecs.b2jml.util.ModelUtils." + set + "(" + p + ")";
							// code="JMLEqualsSet.convertFrom(new "+toJML_TreeWalker.typeToString(pnode1.getBType(), false, false)+"[] {"+p+"})";
						} else {
							String typeStrin = pnode1.getBType().toString();
							if (typeStrin.contains("STRING") || typeStrin.contains("ENUM") ) 
								code="JMLEqualsSet.convertFrom(new String[] {"+p+"})"; 
							else if (typeStrin.contains("BOOL"))
							  code="JMLEqualsSet.convertFrom(new boolean[] {"+p+"})";
							else if (typeStrin.contains("INFIXE") || typeStrin.contains("EQUAL") ||
											typeStrin.contains("LIST") || typeStrin.contains("PROD") ||
											typeStrin.contains("RANGE"))
								code="JMLEqualsSet.convertFrom(new JMLEqualsToEqualsRelation[] {"+p+"})";
						  else if(typeStrin.contains("UNARY") || typeStrin.contains("SET") ||
						  				typeStrin.contains("POW"))
						    code="JMLEqualsSet.convertFrom(new JMLEqualsSet[] {"+p+"})";
						  else 
							  code="JMLEqualsSet.convertFrom(new Integer[] {"+p+"})";
						}
						
				break;
			}
			case B_SEQEMPTY:
			{
				t1 = (MyNode)_t;
				match(_t,B_SEQEMPTY);
				_t = _t.getNextSibling();
				
						code=t1.getText();
					
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
				code=quantification(_t);
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
		return code;
	}
	
	public final String  cbasic_value(AST _t) throws RecognitionException {
		String code;
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode nr = null;
		MyNode pl0 = null;
		MyNode pl1 = null;
		MyNode pl1d = null;
		MyNode pl2d = null;
		
			code = "";
			String p = "";
			String p1 = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				t1 = (MyNode)_t;
				match(_t,B_ASTRING);
				_t = _t.getNextSibling();
				
						code = "\""+t1+"\"";
					
				break;
			}
			case B_NUMBER:
			{
				t2 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
							code = t2.getText();
						
				break;
			}
			case B_TILDE:
			{
				AST __t268 = _t;
				MyNode tmp25_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				p=predicate(_t);
				_t = _retTree;
				
							code = p+".inverse()";
						
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			{
				nr = _t==ASTNULL ? null : (MyNode)_t;
				p=nameRenamedDecorated(_t);
				_t = _retTree;
				
							// toJML begin
							code = p;
							if (nr.getBType() instanceof ENUM && enumConstants.contains(p)) {
								code = "\"" + p + "\"";
							}
							// toJML end
						
				break;
			}
			case B_LPAREN:
			{
				AST __t269 = _t;
				MyNode tmp26_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				pl0 = _t==ASTNULL ? null : (MyNode)_t;
				p=predicate(_t);
				_t = _retTree;
				pl1 = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				
						    String name = p.toString();
						    if (name.equalsIgnoreCase("set")) {
						        code = "new org.jmlspecs.b2jml.util.Set<" +
						              toJML_TreeWalker.typeToString(pl1.getBType()) + ">()";
						    } else if (name.equalsIgnoreCase("seq")) {
						        code = "new org.jmlspecs.b2jml.util.Seq<" +
						              toJML_TreeWalker.typeToString(pl1.getBType()) + ">()";
						    } else if (pl0.getBType() instanceof SEQ && pl1.getBType() instanceof INTEGER) {
						    	code = p + ".itemAt(" + p1 + " - 1)";
						    } else if (pl1.getBType() instanceof SEQ && toJML_TreeWalker.seqOps.get(name) != null) {
						        if (name.equals("conc")) {
							        code = toJML_TreeWalker.seqOps.get(name) + "(" + p1 + ")";
						        } else {
							        code = p1 + "." + toJML_TreeWalker.seqOps.get(name) + "()";
						        }
							} else if (pl1.getBType() instanceof SET && toJML_TreeWalker.setOps.get(name) !=  null) {
								if (name.equals("inter") || name.equals("union")) {
							        code = toJML_TreeWalker.setOps.get(name) + "(" + p1 + ")";
								} else {
							        code = p1 + "." + toJML_TreeWalker.setOps.get(name) + "()";
								}	        
						    } else {
								code = p + ".apply("+p1+")";
							}
						
				_t = __t269;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t270 = _t;
				MyNode tmp27_AST_in = (MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				p=pred_func_composition(_t);
				_t = _retTree;
				_t = __t270;
				_t = _t.getNextSibling();
				
							code = p;
						
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t271 = _t;
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				predicate(_t);
				_t = _retTree;
				predicate(_t);
				_t = _retTree;
				_t = __t271;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t272 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				pl1d = _t==ASTNULL ? null : (MyNode)_t;
				p=predicate(_t);
				_t = _retTree;
				pl2d = _t==ASTNULL ? null : (MyNode)_t;
				p1=predicate(_t);
				_t = _retTree;
				_t = __t272;
				_t = _t.getNextSibling();
				
							if (toJML_TreeWalker.isFunction(pl1d.getBType())) {
								code = p+".apply("+p1+")";
							} else if (pl1d.getBType() instanceof INFIXE) {
								String app = ".elementImage(";
								if (!pl2d.getBType().toString().equals(pl1d.getBType().getMember().toString())) {
									app = ".image(";
								}
								code = p + app + p1 + ")";
								System.out.println("HERE!");
								System.out.println(code);
								System.out.println(pl1d.getBType().getMember());
								System.out.println(pl1d.getBType().getMember().getClass());
								System.out.println(pl2d.getBType());
								System.out.println(pl2d.getBType().getClass());
							} else {
								code = p + "(" + p1 + ")";
							}
						
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
							code = "true";
						
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
							code = "false";
						
				break;
			}
			case BTRUE:
			{
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,BTRUE);
				_t = _t.getNextSibling();
				code = "true";
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
		return code;
	}
	
	public final String  cvalue_set(AST _t) throws RecognitionException {
		String code;
		
		MyNode cvalue_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
				code="";
				String l1="";
				String p1="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t265 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				l1=list_var(_t);
				_t = _retTree;
				p1=predicate(_t);
				_t = _retTree;
				_t = __t265;
				_t = _t.getNextSibling();
				
									code=l1+";"+p1;
								
				break;
			}
			case ELEM_SET:
			{
				AST __t266 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				l1=cvalue_set(_t);
				_t = _retTree;
				p1=predicate(_t);
				_t = _retTree;
				_t = __t266;
				_t = _t.getNextSibling();
				
									code=l1+","+p1;
								
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
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				code=predicate(_t);
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
		return code;
	}
	
	public final String  quantification(AST _t) throws RecognitionException {
		String code;
		
		MyNode quantification_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t70 = null;
		MyNode forall1 = null;
		MyNode t71 = null;
		MyNode exists1 = null;
		
			code="";
			String s1="";
			String s2="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t279 = _t;
				t70 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				forall1 = _t==ASTNULL ? null : (MyNode)_t;
				s1=list_var(_t);
				_t = _retTree;
				s2=predicate(_t);
				_t = _retTree;
				_t = __t279;
				_t = _t.getNextSibling();
				
							code="("+back+"forall "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
						
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t280 = _t;
				t71 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				exists1 = _t==ASTNULL ? null : (MyNode)_t;
				s1=list_var(_t);
				_t = _retTree;
				s2=predicate(_t);
				_t = _retTree;
				_t = __t280;
				_t = _t.getNextSibling();
				
							code="("+back+"exists "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
						
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
		return code;
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
				AST __t282 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				_t = __t282;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t283 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				_t = __t283;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t284 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				_t = __t284;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t285 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				_t = __t285;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t286 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				q_operande(_t);
				_t = _retTree;
				_t = __t286;
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
	
	public final String  list_var(AST _t) throws RecognitionException {
		String code;
		
		MyNode list_var_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			code="";
			String s1="";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t290 = _t;
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				s1=list_identifier(_t);
				_t = _retTree;
				_t = __t290;
				_t = _t.getNextSibling();
				
								code="("+s1+")";
							
			}
			else if ((_t.getType()==B_COMMA||_t.getType()==B_IDENTIFIER)) {
				code=list_identifier(_t);
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
		return code;
	}
	
	public final String  pred_func_composition(AST _t) throws RecognitionException {
		String code;
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t2 = null;
		MyNode t3 = null;
		
				code = "";
				String p = "";
				String p2="";
			
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t274 = _t;
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				p=pred_func_composition(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t274;
				_t = _t.getNextSibling();
				
						if (5>3) throw new IllegalStateException("Sequential statement not supported");	
					
				break;
			}
			case B_PARALLEL:
			{
				AST __t275 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				p=pred_func_composition(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t275;
				_t = _t.getNextSibling();
				
						code=p+" && " +p2;
						
				break;
			}
			case B_COMMA:
			{
				AST __t276 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				p=pred_func_composition(_t);
				_t = _retTree;
				p2=predicate(_t);
				_t = _retTree;
				_t = __t276;
				_t = _t.getNextSibling();
				
						code=p+","+p2;
						
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
			case B_MIN:
			case B_MAX:
			case B_CARD:
			case B_NOTINSET:
			case LITERAL_STRING:
			case BTRUE:
			{
				p=predicate(_t);
				_t = _retTree;
				
							code=p;
							
							// toJML begin
							// TODO: fix to use real type information
							/*
							listPredicate.add(p.get(0));
							listPredicate.add(p.get(1));
							*/
							// toJML end
						
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
		return code;
	}
	
	public final void q_operande(AST _t) throws RecognitionException {
		
		MyNode q_operande_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			AST __t288 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			list_var(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			predicate(_t);
			_t = _retTree;
			_t = __t288;
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
		"a comment",
		"BTRUE"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -3530822108395339808L, 245869697824799L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -9214224099843244000L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -3530822108395339808L, 245869697818655L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 268435552L, 117093590311632912L, 1186287880859811840L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 268435552L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { -3530822108395339808L, 246007136772127L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	}
	
