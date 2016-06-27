// 
// 	Author			:	Danni Yu, Juan Pablo Garcia Cifuentes
//								based on TreeWalker.g made by Boulanger Jean-Louis 
//	EMAIL				:	S.DanniYu@gmail.com, JuanPabloGarciaCifuentes@gmail.com
// 	File				:	toJML_TreeWalker.g
//	Descripton	:	Tree Walker for Another's B Parser in order to generate JML code
//	
//

// Releases
//  July 2010 v 1.0
//					- first version
//  May 2011 v.1.1

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//  * @author <a href="mailto:S.DanniYu@gmail.com">Danni Yu</a>
//  * @author <a href="mailto:JuanPabloGarciaCifuentes@gmail.com">Juan Pablo Garcia Cifuentes</a>
//  **/

header 
{
	package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------
{
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
}


// Define a Tree Walker
//-----------------------------------------------------------------------------

class toJML_TreeWalker extends toJML_ExpressionTreeWalker;
options
{
	importVocab = BPrime;
	buildAST = false;
	ASTLabelType = "MyNode";
	// Copied following options from java grammar.
	codeGenMakeSwitchThreshold = 3;
	codeGenBitsetTestThreshold = 4;
	k = 1;
}

// Introduce some behaviours....
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

}

// Grammar become HERE
// -------------------

/** 
 *  Root Rule
 **/
composant :
	{
		index.init();
	}
	(
		machine
		|	refinement
		|	implementation
	)
	{
	
	}
;

/**
 * THREE type of B composants simple name or with one or more parameters
 **/

machine	:	
	{
		String pn = "toJML paramName";
		assign.clear();
		//just to prevent assignable stmt outside operations from having an empty stack
	}
	#(tt:"MACHINE"
		pn = paramName
	{
		fileName = pn + ".java";
		listJML.add("import org.jmlspecs.models.*;"); 
		listJML.add("public abstract class " + #pn + " {");
	}
		clauses
	{
		listJML.add("}"); // close class declaration
	}
	)
;

refinement {
	String pn= "";
	assign.clear(); 
}
:	
	
	#(tt:"REFINEMENT"
	{
		index.Add();
	}
  pn=paramName 
  {
  	fileName = pn + ".java";
		listJML.add("import org.jmlspecs.models.*;"); 
		listJML.add("public abstract class " + #pn + " {");
  }
  clauses
  {
  listJML.add("}");
  }
  )
;

implementation :
	#(tt:"IMPLEMENTATION"
	{
		index.Add();
	}
  paramName                       
  clauses
)
;


clauses	:
	(
		clause
	)*
;

clause	:
	refines 
	| constraints
	| link
	| sets
	| values
	| constants
	| properties
	| variables
	| invariant
	| assertions
	| definitions
	| initialisation
	| operations  
;


/**
 *  For all Name with parameters
 **/
paramName returns [String code = new String()]	:	
	{
		Map<String, ABType> lti = null;
	}
	#(B_LPAREN 
		name:B_IDENTIFIER 		 
		lti = listTypedIdentifier
	{
		code=#name.getText()+"(";
		for (String s:lti.keySet()){
			code+=toJML_TreeWalker.typeToString(lti.get(s))+" "+s+", ";
		}
		code=code.substring(0,code.length()-2);
		code+=")";
	}
	)			
	| name1:B_IDENTIFIER
	{
	if (operParam) 
		code = #name1.getText()+"()";
	else 
		code =#name1.getText();
	}
;

listTypedIdentifier returns [Map<String, ABType> ids]	
{
	ids = null;
}
:
	{
		Map<String, ABType> lti1 = null;
		Map<String, ABType> lti2 = null;
	}
	#(B_COMMA
	lti1 = listTypedIdentifier	
	lti2 = listTypedIdentifier
	)	
	{
		ids = (Map<String, ABType>) new LinkedHashMap(lti1);
		ids.putAll(lti2);
	}
	|
	{
		Map<String, ABType> ti = null;
	}	
	ti = typedIdentifier
	{
		ids = ti;
	}
;

typedIdentifier	returns [Map<String, ABType> id] 
{
	id = null;
	String p = null, nr = null;
}
:
	#(B_INSET
		nr = nn1:nameRenamed
		p = pred:predicate 
	)	
	{
	  id = new LinkedHashMap<String, ABType>();
		id.put(nr, nn1.getBType());
	}
	|	
		nr = name:nameRenamed
	{
		id = new LinkedHashMap<String, ABType>();
		id.put(nr, name.getBType());
	}
;

constraints 	{
	String p1="";
}:
	//constraints of parameters, comment generated from contraints, add to new invariant clause
	#(tt:"CONSTRAINTS"
		p1=predicate
	){
		listJML.add("/*@public invariant");
		listJML.add(p1);
		listJML.add("*/\n");
	}
;

link	:	uses 
			|	includes
			|	sees
			|	extendeds
			|	promotes
			|	imports
;

extendeds	:	
	#(tt:"EXTENDS"
		listInstanciation
	)
;

uses :
	#(tt:"USES"
		listNames			
	)
;

includes :	
	#(tt:"INCLUDES"
		listInstanciation
	)
;

sees :
	#(tt:"SEES"
		listNames
	)
;

listNames	:	
	#(B_COMMA 
		listNames
		nameRenamed
	)
  |	nameRenamed
;

listInstanciation :	
	#(B_COMMA 
		listInstanciation
		paramRenameValuation
	)
	|	paramRenameValuation
;

paramRenameValuation :	
	#(t3:B_LPAREN 
		paramRenameValuation
		list_New_Predicate
	)
	|	nameRenamed
;

imports	:	
	#(tt:"IMPORTS"
		listInstanciation
	)
;

promotes :	
	#(tt:"PROMOTES"
		listNames
	)
;

refines	:	
	#(tt:"REFINES"				
		name:B_IDENTIFIER
	)
;

constants 
{
	Map<String, ABType> lti = null;
}	
:	
	#(t1:"CONSTANTS"
		lti=listTypedIdentifier		
	){
		for (String name : lti.keySet()) {
			String decl = "  //@ public static final ghost ";
			decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
			decl += " " + name + ";";
			listJML.add(decl);
		}
	}
  |
	#(t2:"CONCRETE_CONSTANTS"
		listTypedIdentifier
	) //implementation 
  |
	#(t3:"VISIBLE_CONSTANTS"
		listTypedIdentifier
	) //public 
	|
	#(t4:"ABSTRACT_CONSTANTS"
		lti = listTypedIdentifier
	) // for abstract machine
	{
		for (String name : lti.keySet()) {
			String decl = "  //@ public static final ghost ";
			decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
			decl += " " + name + ";";
			listJML.add(decl);
		}
	}
  |
	#(t5:"HIDDEN_CONSTANTS"
		listTypedIdentifier
	) //private constant
;

sets {
	String set="";
}:	
	#(tt:"SETS"
		set=sets_declaration
	){
		listJML.add(set);
	}
;

// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration returns [String code] {
	code="";
	String s1="";
	String s2="";
}:	

	#(B_SEMICOLON  
		s1=sets_declaration
		s2=sets_declaration
	)	{
		code=s1+"\n"+s2;
	}
	|
	#(B_COMMA
		s1=sets_declaration 
		s2=sets_declaration
	){
		code=s1+","+s2;
	}
	|	code=set_declaration		
;


// 12/2001 ajout impression du type de chaque ensemble
//
set_declaration returns [String code]
{
	String vs = "toJML valuation_set";
	code="";
}:
	#(B_EQUAL 
		set:B_IDENTIFIER
		vs = valuation_set
	)
	{
		if (#set.getBType() instanceof ENUM){
			String stype=toJML_TreeWalker.typeToString(#set.getBType());
			code="  //@ public static final ghost JMLEqualsSet<"+stype+"> " + #set.getText() 
									+ "= JMLEqualsSet.convertFrom(new "+ stype+ "[] "+vs+");"; 
		} else {
			code="  //@public final ghost JMLEqualsSet<Integer> "+#set.getText()+" = "+vs+ ";";
		}
	}
	|	
		name:B_IDENTIFIER
	{
//		code="  //@ public model JMLEqualsSet<Integer> " + #name.getText()+";";
		code="  //@ public final ghost " + toJML_TreeWalker.typeToString(#name.getBType()) + " " + #name.getText()+";";
	}
;



valuation_set	returns [String code = new String()] 	
{
	String lc="";
	String lc2="";
}:
	#(B_CURLYOPEN
		lc = list_couple
	)
	{
		code = "{" + lc + "}";
	}
	| is_record
	| 
	#(t2:B_MULT 
		valuation_set
		valuation_set
	) 
	| 
	#(t3:B_ADD 
		lc=valuation_set
	){
		code="+"+lc;
	}
	| 
	#(t4:B_MINUS 
		lc=valuation_set
		lc2=valuation_set
	){
		code=lc+".difference("+lc2+")";
	}
	|	
	t5:B_IDENTIFIER
	{
		code=#t5.getText();
	}
	|	code=basic_sets
;


list_couple returns [String s = new String()]	:
{
	String lc = "toJML list_couple";
	String c = "toJML couple";
} 	
	#(tt:B_COMMA 
		lc = list_couple
		c = couple 
	)
	{
		s = lc + ", " + c;
	}
|
		{
			String c = "toJML couple";
		}
		c = couple
		{
			s = c;
		}
;

couple returns [String code = new String()]  
{	
	String pc="";
	String as1="";
	String as2=""; 
}:
	
	#(t1:B_MAPLET 
		as1=a_set_value
		as2=a_set_value
	){
		code="new JMLEqualsEqualsPair("+as1+","+as2+")";
	}
	| 
	#(t2:B_LPAREN
		pc=parent_couple
	){
			code=pc;
	}
	|	{
			String asv = "toJML a_set_value";
		}
		asv = a_set_value
		{
			code = asv;
		}
;

parent_couple	returns [String code] {
	code="";
	String asv1="";
	String asv2="";
}:
	#(t1:B_MAPLET 
		asv1=a_set_value
		asv2=a_set_value
	){
		code="new JMLEqualsEqualsPair("+asv1+","+asv2+")";				
	}
	|
	#(t2:B_COMMA  
		asv1=a_set_value
		asv2=a_set_value
	){
		code=asv1+","+asv2;
	}
	|	code=a_set_value
;

a_set_value	returns [String code = new String()] :
	name:B_IDENTIFIER
	{
		code = "\""+#name.getText()+"\"";
		enumConstants.add(#name.getText());
	}
	|	#(mi:B_MINUS 	
		val1:B_NUMBER
	) {
		code="-"+#val1.getText();
	}
	|	val:B_NUMBER {
		code=#val.getText();
	}
	|	tr:"TRUE" {
		code="true";
	}
	|	fa:"FALSE" {
		code="false";
	}
;


values		{
		String code="";
}:	
	#(tt:"VALUES"
		code=list_valuation
	){
		listJML.add(code);
	}
;

list_valuation returns [String code] {
	code="";
	String l="";
	String s="";
}:
	#(tt:B_SEMICOLON 
		l=list_valuation 
		s=set_valuation
	){
		code=l+";\n"+s;
	}
  |	code=set_valuation
;

set_valuation	returns [String code] {
	code="";
	String ns="";
}:	
	#(tt:B_EQUAL 
		name:B_IDENTIFIER
		ns=new_set_or_constant_valuation
	){
		code=#name.getText()+"="+ns;
	}
;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

new_set_or_constant_valuation returns [String code] {
	code="";
}:
	code=predicate
;

set_interval_value returns [String code] {
	code="";
	String idecl="";
}:	
	#(tt:B_EQUAL 
		a:B_IDENTIFIER
		idecl=interval_declaration
	){
		code=#a.getText()+"="+idecl;
	}
;

interval_declaration returns [String code] {
	code="";
	String p1="";
	String p2="";
}:
	#(tt:B_RANGE 
		p1=predicate
		p2=predicate
	) {
		code="new org.jmlspecs.b2jml.util.Interval("+p1+","+p2+")";
	}
;

set_rename_value:
	#(tt:B_EQUAL 
		name1:B_IDENTIFIER
		name2:B_IDENTIFIER
	)
;

properties	{
	String p1="";
}:
//wrt the variables
	#(tt:"PROPERTIES"
		p1=predicate
	){
		listJML.add("/*@ public invariant");
		listJML.add(p1 + ";");
		listJML.add("*/");
	}
;

variables
	{
		Map<String, ABType> lti = null;
	}	
:
	#(t0:"VARIABLES" 
		lti = listTypedIdentifier
	)
	{
		for (String name : lti.keySet()) {
			String decl = "  //@ public model ";
			decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
			decl += " " + name+";";
			listJML.add(decl);
		}
		listJML.add("\n");
	}
	| #(t1:"ABSTRACT_VARIABLES" 
		lti = listTypedIdentifier
		)
	{
		for (String name : lti.keySet()) {
			String decl = "  //@ public model ";
			decl += toJML_TreeWalker.typeToString((ABType) lti.get(name));
			decl += " " + name+";";
			listJML.add(decl);
		}
		listJML.add("\n");
	}
	| #(t2:"VISIBLE_VARIABLES"
		listTypedIdentifier
		)
	| #(t4:"CONCRETE_VARIABLES"
		listTypedIdentifier
		)
	| #(t5:"HIDDEN_VARIABLES"
		listTypedIdentifier
		)
;

invariant	:
	{
		inInvariant = true;
		listJML.add("/*@ public invariant");
		String t = "";
	}
	#(tt:"INVARIANT"
	t = predicate
	)
	{
		inInvariant = false;
		listJML.add(t);
		listJML.add(";*/\n");
	}
;

definitions	:
	#(tt:"DEFINITIONS"
		list_def
	)
;

list_def	:
	#(tt:LIST_DEF
		list_def
		definition
	) 
	|	definition
;

/**
 *  Attention deux cas
 *     - Une definition
 *     - un acces a un fichier de definition
 **/

definition	:
	        #(t1:B_DOUBLE_EQUAL 
				paramName
				formalText
			)
        |	t3:B_ASTRING
    ;

formalText	:
    #(EXP_DEF   predicate)
    |	#(SUBST_DEF instruction)
//			|	operation
    ;

assertions :{
		String p="";
		listJML.add("/*@ public invariant_redundantly ");		
}
     #(tt:"ASSERTIONS"
					p=list_assertions
			){
			listJML.add(p);
			listJML.add(";*/\n");
			}
;

list_assertions	returns [String code] {
			code="";
			String l1="";
			String l2="";
}:
	        #(tt:B_SEMICOLON  
				l1=list_assertions
				l2=list_assertions
			){
				code=l1+"&&"+l2;
			}
        |	code=predicate
;


initialisation : 
		{
		listJML.add("/*@ public initially ");
		String in="";
		inInitially=true;
		}
		#(tt:"INITIALISATION"
	    in=instruction
		) 
		{
		inInitially=false;
		listJML.add(in);
		listJML.add(";*/\n");
		}		
;

operations returns [String code]{
			code="";
}:
	    #(tt:"OPERATIONS"
				code=listOperation
			){
				listJML.add(code);
			}
;

listOperation	returns [String code] {
			code="";
			String l1="";
			String l2="";
}:
	    #(tt:B_SEMICOLON
				l1=listOperation
				l2=operation
			){
				code=l1+"\n"+l2;
			}
        |	code=operation
;

operation	returns [String code] {
	code="/*@ public normal_behavior\n";
	String l1="";
	String l2="";
	//returnVal = new ArrayList<String>();
	returnVal1 = new LinkedHashMap<String,String>();
	hasPRE = false;
}:
	#(tt:OP_DEF
		l1=operationHeader
	{
		index.Add();
	}
		l2=instruction
	{
		index.Retract();
	}
	) {
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
;

operationHeader	returns [String code] {
	operParam = true;
	code="";
	String l1="";
	String l2="";
	Map<String,ABType> ident=new LinkedHashMap<String,ABType>();
}:

	#(tt:B_OUT
		ident=listTypedIdentifier
		l2=paramName 
// au cas ou			listTypedIdentifier
	) {
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
    |	code=paramName {
//    		code="void "+ code; 
    		operParam = false;
    	}
;

/**
 * The Generalised Substitution Language
 **/

instruction	returns [String code]
	{
		code="instruction";
		String p=""; //predicate
		String ins=""; //instruction
		String ins2="";
		String ins3="";
		String ins4="";
		Map<String,ABType> ident=new LinkedHashMap<String,ABType>(); //identifiers		
	}:
	#(t1:PARALLEL 	
		ins=instruction 			
		ins2=instruction
	){
		code=ins+"&&\n"+ins2;
	}
  |
	#(t2:SEQUENTIAL	
		instruction
		instruction
	) {
		if (6>3) 
			throw new IllegalStateException("Sequential statement not supported");
	} //error -unreachable statement "break"
  |
	#(t3:"skip"
	{
	}
	) {code="true";}
  |
	#(t4:"BEGIN"
		code=instruction
	)
  |
	#(t40:B_BEGIN_POST
  	instruction
    predicate
	) |
	#(t5:"PRE"
		p=predicate
		ins=instruction
	){
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
	}
  |
	#(t6:"ASSERT" 				
		p=predicate
		ins=instruction
	){
		code="/*@ assert "+p+"&&\n"+ins+";/*@";
	}
  |	
	#(t7:"IF"
 		p=predicate
		ins=branche_then 
		{
			ArrayList<String> listInstrIf=new ArrayList<String>(); 
			listInstrIf.add(p);
			listInstrIf.add(ins);
			stackOps.push(listInstrIf);
		}
		(branche_elsif)* 
		(branche_else)?
	) {
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
	}
  |
  #(t8:"CHOICE"
		ins=list_or
	){
 		code=ins;
	}
  |
	#(t9:"CASE"
		p=predicate 
		ins=branche_either
	{
		ArrayList<String> listInstrCas=new ArrayList<String>(); 
		listInstrCas.add(p);
		stackOps.add(listInstrCas);
		ArrayList<String> temp = new ArrayList<String>();
	}
		(branche_or)*
		(branche_else)?
	){
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
	}
  |
	#("ANY" 
	{
		index.Retract();
	}
		ident=listTypedIdentifier 
		p=predicate
		ins=instruction
	){
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
	}
	|
	#(t11:"LET"
		ident=listTypedIdentifier 
		p=list_equal 
		ins=instruction
	) {
		String var="";
		ABType type=new ABType();
		for (String s:ident.keySet()){
			var=s;
			type=ident.get(s);
		}
		code="("+back+"exists "+toJML_TreeWalker.typeToString(type)+" "+var+"; "+back+"old("+p+") && "+ins+")"; 
	
		//code=ins+"&&"+ins3;
  }
  |
	#(t12:"SELECT"
		p=predicate 
		ins=instruction
	{
		ArrayList<String> listInstrSel=new ArrayList<String>(); 
		listInstrSel.add(p);
		listInstrSel.add("(" + ins + ")");
		stackOps.add(listInstrSel);
		index.Retract();
	}
		(branche_when)*
		(branche_else)?
	){		
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
		}
    |
		#(t13:"WHILE"
			predicate
			instruction
			variant_or_no
		) {
				if (5>3) throw new IllegalStateException("while statement not supported");
		}
    |
		#(tt:"VAR"
			ident=listTypedIdentifier
		    {
				HashMap<String, Boolean> occurs = new HashMap<String, Boolean>();
				for (String s:ident.keySet()){
					occurs.put(s, assign.contains(s));
				}
		    }
			ins=instruction
		){
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
			
	      }
     	|	code=simple_affect
;

list_or returns [String code] {
	code="";
	String ins="";
	String ins2="";
} :
    #(t1:"OR"
     ins=list_or                         
     ins2=instruction
     ) {
     code="(("+ins+")&& !("+ins2+"))||\n (("+ins2+")&&!("+ins+"))";
    }
    | code=instruction
;


branche_when {
	String p="";
	String inst="";
}:
	   #(tt:"WHEN"
				p=predicate
				inst=instruction
			){
				stackOps.peek().add(p);
				stackOps.peek().add("(" + inst + ")");
			}
;

branche_either	returns [String code=new String()]{
	String p="";
	String ins="";
}
:
	#(tt:"EITHER"
		p=pn: predicate 
		ins=instruction 
	){
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
;

branche_or	{
	String p="";
	String ins="";
}
:
	#(tt:"OR"
		p=predicate
		ins=instruction
	){
		stackOps.peek().add(p);
		stackOps.peek().add("(" + ins + ")");
	}
;

branche_then returns [String code] {
	code="";
	String ins="true";
}:	
	#(tt:"THEN"			
{
	index.Add();
}
	ins=instruction
{
	index.Retract();
}
	){
		code=ins;
	}
;

branche_else {
	String ins="true";
}:
	#(tt:"ELSE"
		ins=instruction
	){
		stackOps.peek().add("(" + ins + ")");
	}
;

branche_elsif	{
	String p="";
	String ins="";
}:	
	#(t1:"ELSIF"
		p=predicate
		ins=instruction
	){
		stackOps.peek().add(p);
		stackOps.peek().add("(" + ins + ")");
	}
;


variant_or_no	:	
      #(t1:"VARIANT"
				#(t2:"INVARIANT"
					predicate
				)
				predicate 
			)
        |
			#(t3:"INVARIANT"
				#(t4:"VARIANT"
					predicate 
				)
				predicate
			)
;

list_equal returns [String code=new String()]	{
	String le="";
	String ae="";
}:

	#(tt:B_AND 	
		le=list_equal
		ae=an_equal
	){
		code=le+" && "+ae;
	}
 | code=an_equal	
;


an_equal	returns [String code=""]{
	String p="";
}:	
	#(tt:B_EQUAL 	
		n1:B_IDENTIFIER
		p=predicate
	){
		code=n1.getText()+".equals("+p+")";
	}
;

// afin de prendre en compte 
//	soit f (x,y,z)
//	soit f (x) [z]
//	soit f~(x) [z]
//	soit f (x)~

func_call	returns [String code] {
		code="";
		String f="";
		String l="";
}:
 	   #(t1:B_TILDE
		 		f=func_call
 			){
 				code=f+".inverse()";
 			}
       |
			#(t2:APPLY_TO
				f=func_call
				l=pl:list_New_Predicate
			){
			//if f is relation, element image, if function, apply
				code=f+".apply("+l+")";
			}
        |
		 	#(t3:B_LPAREN
		 		f=func_call
				l=list_New_Predicate
			){
				code=f+"("+l+")";
			}
        |
			#(t4:B_QUOTEIDENT
				func_call 			
				func_call
			)
      | code=nameRenamed 
;

func_param	:	
        list_New_Predicate
;

list_identifier	returns [String code]{
	code="";
	String l1="";
}:
 	   #(tt:B_COMMA
		 		l1=list_identifier
				n1:B_IDENTIFIER
			){
				sqType.push(n1.getBType());
				code=l1+","+n1.getText();
			}
			|	n2:B_IDENTIFIER {
				sqType.push(n2.getBType());
				code=n2.getText();
			}
    ;


a_func_call	returns [String code]{
	code="";
}: 
    code=afc
;

afc returns [String code]	{
	code="";
  String s1="";
  String s2="";
}:    
	#(FUNC_CALL_PARAM
		s1=afc
		s2=listPredicate
	){
		code=s1+".("+s2+")";
	}
  |
	#(t1:B_QUOTEIDENT
		afc
		afc
	)
	|
  #(B_LPAREN
		s1=afc
		s2=listPredicate
	){
		code="("+s1+")."+s2;
	}
	| code=nameRenamed
;

list_func_call	returns [String code] {
	code="";
	String l1="";
	String l2="";
}:
	#(tt:B_COMMA
		l1=list_func_call
		l2=list_func_call
	){
		code=l1+","+l2;
	}
	|	code=a_func_call
;

// Quatre cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a,b,c :( P )
//	soit	a,b,c :: P
//	soit	P.R (xx,yy)
//	soit 	P.R

//simple substitution

simple_affect	returns [String code]
{
	code="";
	String l1="";
	String p="";
}:
	#(t1:B_SIMPLESUBST 	
				l1=left:list_func_call
				p=pnode: predicate
		){
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
			}			
      |
			#(t2:B_OUT	 	
				l1=list_func_call
				p=func_call
			){
				code=l1+"=="+p;
				if (!assign.contains(l1))
					assign.add(l1);
			}
      |
			#(INSET
				l1=list_func_call
				p=predicate
			) {
				code=p+".has("+l1+")";
			}
        |
			#(t4:B_BECOME_ELEM
			 	l1=t:list_func_call
				p=predicate
			){
			 ABType Type = t.getBType();
			 String JMLType = toJML_TreeWalker.typeToString(Type);
			 code="("+back+"exists "+JMLType+" "+l1+"';"+back+"old("+p+".has("+l1+"')); "+l1+ " == "+back+"old("+l1+"'))";
			 //code=p+".has("+l1+")";
			 if (!inInitially){
				 if (!assign.contains(l1))
	                assign.add(l1);
              }
			}
      | code=a_func_call
		;
