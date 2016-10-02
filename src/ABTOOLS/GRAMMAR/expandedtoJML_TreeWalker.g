header 
{
	package ABTOOLS.GRAMMAR;
}
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
}class toJML_TreeWalker extends TreeParser;

options {
	importVocab= BPrime;
	buildAST= false;
	ASTLabelType= "MyNode";
	codeGenMakeSwitchThreshold= 3;
	codeGenBitsetTestThreshold= 4;
	k= 1;
}

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
composant :{
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

machine :{
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
:#(tt:"REFINEMENT"
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

implementation :#(tt:"IMPLEMENTATION"
	{
		index.Add();
	}
  paramName                       
  clauses
)
;

clauses :(
		clause
	)*
;

clause :refines 
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

paramName returns [String code = new String()]:{
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

listTypedIdentifier returns [Map<String, ABType> ids]{
	ids = null;
}
:{
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

typedIdentifier returns [Map<String, ABType> id]{
	id = null;
	String p = null, nr = null;
}
:#(B_INSET
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

constraints {
	String p1="";
}
://constraints of parameters, comment generated from contraints, add to new invariant clause
	#(tt:"CONSTRAINTS"
		p1=predicate
	){
		listJML.add("/*@public invariant");
		listJML.add(p1);
		listJML.add("*/\n");
	}
;

link :uses 
			|	includes
			|	sees
			|	extendeds
			|	promotes
			|	imports
;

extendeds :#(tt:"EXTENDS"
		listInstanciation
	)
;

uses :#(tt:"USES"
		listNames			
	)
;

includes :#(tt:"INCLUDES"
		listInstanciation
	)
;

sees :#(tt:"SEES"
		listNames
	)
;

listNames :#(B_COMMA 
		listNames
		nameRenamed
	)
  |	nameRenamed
;

listInstanciation :#(B_COMMA 
		listInstanciation
		paramRenameValuation
	)
	|	paramRenameValuation
;

paramRenameValuation :#(t3:B_LPAREN 
		paramRenameValuation
		list_New_Predicate
	)
	|	nameRenamed
;

imports :#(tt:"IMPORTS"
		listInstanciation
	)
;

promotes :#(tt:"PROMOTES"
		listNames
	)
;

refines :#(tt:"REFINES"				
		name:B_IDENTIFIER
	)
;

constants {
	Map<String, ABType> lti = null;
}
:#(t1:"CONSTANTS"
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
}
:#(tt:"SETS"
		set=sets_declaration
	){
		listJML.add(set);
	}
;

sets_declaration returns [String code]{
	code="";
	String s1="";
	String s2="";
}
:#(B_SEMICOLON  
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

set_declaration returns [String code]{
	String vs = "toJML valuation_set";
	code="";
}
:#(B_EQUAL 
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

valuation_set returns [String code = new String()]{
	String lc="";
	String lc2="";
}
:#(B_CURLYOPEN
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

list_couple returns [String s = new String()]:{
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

couple returns [String code = new String()]{	
	String pc="";
	String as1="";
	String as2=""; 
}
:#(t1:B_MAPLET 
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

parent_couple returns [String code]{
	code="";
	String asv1="";
	String asv2="";
}
:#(t1:B_MAPLET 
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

a_set_value returns [String code = new String()]:name:B_IDENTIFIER
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

values {
		String code="";
}
:#(tt:"VALUES"
		code=list_valuation
	){
		listJML.add(code);
	}
;

list_valuation returns [String code]{
	code="";
	String l="";
	String s="";
}
:#(tt:B_SEMICOLON 
		l=list_valuation 
		s=set_valuation
	){
		code=l+";\n"+s;
	}
  |	code=set_valuation
;

set_valuation returns [String code]{
	code="";
	String ns="";
}
:#(tt:B_EQUAL 
		name:B_IDENTIFIER
		ns=new_set_or_constant_valuation
	){
		code=#name.getText()+"="+ns;
	}
;

new_set_or_constant_valuation returns [String code]{
	code="";
}
:code=predicate
;

set_interval_value returns [String code]{
	code="";
	String idecl="";
}
:#(tt:B_EQUAL 
		a:B_IDENTIFIER
		idecl=interval_declaration
	){
		code=#a.getText()+"="+idecl;
	}
;

interval_declaration returns [String code]{
	code="";
	String p1="";
	String p2="";
}
:#(tt:B_RANGE 
		p1=predicate
		p2=predicate
	) {
		code="new org.jmlspecs.b2jml.util.Interval("+p1+","+p2+")";
	}
;

set_rename_value :#(tt:B_EQUAL 
		name1:B_IDENTIFIER
		name2:B_IDENTIFIER
	)
;

properties {
	String p1="";
}
://wrt the variables
	#(tt:"PROPERTIES"
		p1=predicate
	){
		listJML.add("/*@ public invariant");
		listJML.add(p1 + ";");
		listJML.add("*/");
	}
;

variables {
		Map<String, ABType> lti = null;
	}
:#(t0:"VARIABLES" 
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

invariant :{
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

definitions :#(tt:"DEFINITIONS"
		list_def
	)
;

list_def :#(tt:LIST_DEF
		list_def
		definition
	) 
	|	definition
;

definition :#(t1:B_DOUBLE_EQUAL 
				paramName
				formalText
			)
        |	t3:B_ASTRING
    ;

formalText :#(EXP_DEF   predicate)
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

list_assertions returns [String code]{
			code="";
			String l1="";
			String l2="";
}
:#(tt:B_SEMICOLON  
				l1=list_assertions
				l2=list_assertions
			){
				code=l1+"&&"+l2;
			}
        |	code=predicate
;

initialisation :{
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
}
:#(tt:"OPERATIONS"
				code=listOperation
			){
				listJML.add(code);
			}
;

listOperation returns [String code]{
			code="";
			String l1="";
			String l2="";
}
:#(tt:B_SEMICOLON
				l1=listOperation
				l2=operation
			){
				code=l1+"\n"+l2;
			}
        |	code=operation
;

operation returns [String code]{
	code="/*@ public normal_behavior\n";
	String l1="";
	String l2="";
	//returnVal = new ArrayList<String>();
	returnVal1 = new LinkedHashMap<String,String>();
	hasPRE = false;
}
:#(tt:OP_DEF
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

operationHeader returns [String code]{
	operParam = true;
	code="";
	String l1="";
	String l2="";
	Map<String,ABType> ident=new LinkedHashMap<String,ABType>();
}
:#(tt:B_OUT
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

instruction returns [String code]{
		code="instruction";
		String p=""; //predicate
		String ins=""; //instruction
		String ins2="";
		String ins3="";
		String ins4="";
		Map<String,ABType> ident=new LinkedHashMap<String,ABType>(); //identifiers		
	}
:#(t1:PARALLEL 	
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

list_or returns [String code]{
	code="";
	String ins="";
	String ins2="";
}
:#(t1:"OR"
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
}
:#(tt:"WHEN"
				p=predicate
				inst=instruction
			){
				stackOps.peek().add(p);
				stackOps.peek().add("(" + inst + ")");
			}
;

branche_either returns [String code=new String()]{
	String p="";
	String ins="";
}
:#(tt:"EITHER"
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

branche_or {
	String p="";
	String ins="";
}
:#(tt:"OR"
		p=predicate
		ins=instruction
	){
		stackOps.peek().add(p);
		stackOps.peek().add("(" + ins + ")");
	}
;

branche_then returns [String code]{
	code="";
	String ins="true";
}
:#(tt:"THEN"			
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
}
:#(tt:"ELSE"
		ins=instruction
	){
		stackOps.peek().add("(" + ins + ")");
	}
;

branche_elsif {
	String p="";
	String ins="";
}
:#(t1:"ELSIF"
		p=predicate
		ins=instruction
	){
		stackOps.peek().add(p);
		stackOps.peek().add("(" + ins + ")");
	}
;

variant_or_no :#(t1:"VARIANT"
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

list_equal returns [String code=new String()]{
	String le="";
	String ae="";
}
:#(tt:B_AND 	
		le=list_equal
		ae=an_equal
	){
		code=le+" && "+ae;
	}
 | code=an_equal	
;

an_equal returns [String code=""]{
	String p="";
}
:#(tt:B_EQUAL 	
		n1:B_IDENTIFIER
		p=predicate
	){
		code=n1.getText()+".equals("+p+")";
	}
;

func_call returns [String code]{
		code="";
		String f="";
		String l="";
}
:#(t1:B_TILDE
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

func_param :list_New_Predicate
;

list_identifier returns [String code]{
	code="";
	String l1="";
}
:#(tt:B_COMMA
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

a_func_call returns [String code]{
	code="";
}
:code=afc
;

afc returns [String code]{
	code="";
  String s1="";
  String s2="";
}
:#(FUNC_CALL_PARAM
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

list_func_call returns [String code]{
	code="";
	String l1="";
	String l2="";
}
:#(tt:B_COMMA
		l1=list_func_call
		l2=list_func_call
	){
		code=l1+","+l2;
	}
	|	code=a_func_call
;

simple_affect returns [String code]{
	code="";
	String l1="";
	String p="";
}
:#(t1:B_SIMPLESUBST 	
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

// inherited from grammar toJML_ExpressionTreeWalker
is_record :#(t1:"rec"
				listrecord
			)
			|
			#(t01:"struct"
				listrecord
			)
;

// inherited from grammar toJML_ExpressionTreeWalker
listrecord :#(tt:B_COMMA 
				listrecord
				a_record
			)
        |	a_record
;

// inherited from grammar toJML_ExpressionTreeWalker
a_record :#(B_SELECTOR 
				name:B_IDENTIFIER
				predicate
			)
        |	predicate			
;

// inherited from grammar toJML_ExpressionTreeWalker
list_New_Predicate returns [String code]{
			code="";
			String l1="";
			String l2="";
}
:#(tt:B_COMMA 
				l1=list_New_Predicate
				l2=new_predicate
			){
				code=l1+","+l2;
			}
        |	code=new_predicate
		;

// inherited from grammar toJML_ExpressionTreeWalker
new_predicate returns [String code]{
		code="";
		String l1="";
		String l2="";
}
:#(t1:B_SEMICOLON 
				l1=new_predicate
				l2=predicate
			){
				code=l1+";"+l2;	
			}
        |
			#(t2:B_PARALLEL  
				l1=new_predicate
				l2=predicate
			){
				code=l1+" && "+l2;
			}
	|	code=predicate
;

// inherited from grammar toJML_ExpressionTreeWalker
nameRenamed returns [String code]{
	code = "";
}
:n1:B_IDENTIFIER
	{
		code = n1.getText();
		// FLURB
	}
	| {
			String nr1 = "";
			String nr2 = "";
		}
		#(tt:B_POINT 
		nr1 = nameRenamed
		nr2 = nameRenamed
		)
		{
			code = nr1 + "." + nr2;
		}
;

// inherited from grammar toJML_ExpressionTreeWalker
nameRenamedDecorated returns [String code]{
	code = "";
	String s = "";
}
:#(tt:B_CPRED 
	s = nameRenamed
	{
		code = s;

	}
	)
	|	s = nameRenamed
		{
			// toJML begin
			code = s;
			// TODO: fix to use real type info
			/*
			s.put("vars", nr.get("vars"));
			s.put("types", nr.get("types"));
			*/
			// toJML end
		}
;

// inherited from grammar toJML_ExpressionTreeWalker
nameRenameDecoratedInverted returns [String code]{
	code = "";
	String s="";
}
:#(tt:B_TILDE 
					s=nameRenamedDecorated
				) {
					code = s+".inverse()";
				}
			|	code=nameRenamedDecorated
;

// inherited from grammar toJML_ExpressionTreeWalker
listPredicate returns [String code]{
code="";
String s1="";
String s2="";
}
:#(tt:ELEM_SET
		 		s1=listPredicate
			 	s2=predicate
			)
			{
			code=s1+","+s2;
			}
			|	code=predicate
;

// inherited from grammar toJML_ExpressionTreeWalker
predicate returns [String code]{
		String p1 = "";
		String p2 = "";
		code = "";
	}
:#(t1:B_AND			
	p1 = predicate
	p2 = predicate
	)	
	{
		code = p1 + "\n && " + p2;
	}
	|	#(t2:"or"
		p1=predicate	
		p2=predicate
		)
	{
		code = p1 + "\n || " + p2;
	}
	| #(t3:B_IMPLIES
		p1=predicate
		p2=predicate
		)
	{
		code = "(" + p1 + ")" + "\n ==> (" + p2 + ")";
	}
	| #(t4:B_EQUIV
	 	p1=predicate
		p2=predicate
		)
		{
		code = "(" + p1 + ")" + "\n <==> (" + p2 + ")";
		}
	| #(t5:B_MULT
		p1 = pred1:predicate
		p2 = pred2:predicate
		)
		{
			//if p1 and p2 are integers, do arithmetic multiplication
			if (pred1.getBType() instanceof ABTOOLS.TYPING.INTEGER &&
			    pred2.getBType() instanceof ABTOOLS.TYPING.INTEGER) {
			    code = p1+"*"+p2;
			} else {
				// else make cartesian product
				code = "org.jmlspecs.b2jml.util.ModelUtils.cartesian("+p1+", "+p2+")";
			}
		}
			|	
			#(t6:B_POWER
				p1=predicate
			 	p2=predicate
			)
			{
			code="Math.pow("+p1+","+p2+")";		
			}
			|
			#(t7:B_DIV
	  	  p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"/"+p2;		
			}
			|
			#(t8:"mod"
	  		p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"%"+p2;		
			}
			|
			#(t9:UNARY_ADD
			 	p1=predicate
			)
			{
				code="+"+p1;
			}
			|
			#(t10:UNARY_MINUS
			 	p2=predicate
			)
			{
				code="-"+p1;
			}
			|
		 	#(t11:B_ADD 
	  		p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"+"+p2;		
			} 
			|
			#(t12:B_MINUS
		 		p1=predMinus1:predicate
			 	p2=predMinus2:predicate
			)
			{
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
			}
			|
			#(t13:B_EQUAL
	 			p1=predEqual1:predicate
			 	p2=predEqual2:predicate
			)
			{
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
			}
			|
			#(t14:B_LESS
		 		p1=predLess1:predicate
				p2=predLess2:predicate
			)
			{
			if ((predLess1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
					(predLess2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+"<"+p2;
			}
			else
			{
				code=p1+".compareTo("+p2+")<0";
			}
			}
			|
			#(t15:B_GREATER
	 			p1=predGreater1:predicate
				p2=predGreater2:predicate
			)
			{
			if ((predGreater1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
					(predGreater2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+">"+p2;
			}
			else
			{
				code=p1+".compareTo("+p2+")>0";
			}
			}
			|
			#(t16:B_NOTEQUAL
				p1=predNEqual1:predicate
				p2=predNEqual2:predicate
			)
			{
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
			}
			|
			#(t17:B_LESSTHANEQUAL
		 		p1=predLTE1:predicate
				p2=predLTE2:predicate
			)
			{
			if ((predLTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
					(predLTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+"<="+p2;
			}
			else
			{
				code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")<0";
			}
			}
			|
			#(t18:B_GREATERTHANEQUAL
				p1=predGTE1:predicate
				p2=predGTE2:predicate
			)
			{
			if ((predGTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER)&&
					(predGTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+">="+p2;
			}
			else
			{
				code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")>0";
			}
			}
		|
		#(t19:B_INSET
		p1 = predicate
		p2 = predicate
		)
		{
			code = p2 + ".has(" + p1 + ")";
		}
			|
			#(t20:B_NOTINSET
				p1=predicate
				p2=predicate
			)
			{
				code = "!"+p2 + ".has(" + p1 + ")";
			}
	| #(t21:B_SUBSET
		p1 = pl1:predicate
		p2 = pl2:predicate
		)
		{
		    if (pl1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = p1 + ".isSubset(" + p2 + ")";
		}
	|
			#(t22:B_NOTSUBSET
				p1=pl1a:predicate
				p2=pl2a:predicate
			)
			{
		    if (pl1a.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2a.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = "!"+p1 + ".isSubset(" + p2 + ")";
			}
			|
			#(t23:B_STRICTSUBSET
				p1=pl1b:predicate 	
				p2=pl2b:predicate
			)
			{
		    if (pl1b.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2b.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = p1 + ".isProperSubset(" + p2 + ")";
			}
			|
			#(t24:B_NOTSTRICTSBSET
				p1=pl1c:predicate 	
				p2=pl2c:predicate
			)
			{
		    if (pl1c.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2c.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = "(!"+p1 + ".isProperSubset(" + p2 + ")";
			}
			|
			#(t25:B_CONCATSEQ
				p1=predicate 	
				p2=predicate
			)
			{
				code=p1+".concat("+p2+")";
			}
			|
			#(t26:B_PREAPPSEQ
				p1=predicate 	
				p2=predicate
			)
			{
				code = p2+".insertBeforeIndex(0,"+p1+")";
			}
			|
			#(t27:B_APPSEQ
				p1=predicate
				p2=predicate
			)
			{
				code=p1+".insertAfterIndex("+p1+".int_length()-1,"+p2+")";
			}
			|
			#(t28:B_PREFIXSEQ
				p1=predicate
				p2=predicate
			)
			{
				code=p1+".prefix("+p2+")";
			}
			|
			#(t29:B_SUFFIXSEQ
		 		p1=predicate
				p2=predicate
			)
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.suffixseq("+p1+","+p2+")";	
			}
		|	#(t30:B_RELATION		
		p1 = predr1:predicate
		p2 = predr2:predicate
		)
		{
/*			code="(new org.jmlspecs.b2jml.util.Relation("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Relation<" + 
				   toJML_TreeWalker.typeToString(predr1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predr2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
		}
			|	
      #(t31:B_PARTIAL
         p1=predp1:predicate
         p2=predp2:predicate
       )
       {
/*         code="(new org.jmlspecs.b2jml.util.Partial("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Partial<" + 
				   toJML_TreeWalker.typeToString(predp1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predp2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
       }
	|	#(t32:B_TOTAL
		p1 = predt1:predicate
		p2 = predt2:predicate
		)
		{
/*			code="(new org.jmlspecs.b2jml.util.Total("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Total<" + 
				   toJML_TreeWalker.typeToString(predt1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predt2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
		}
	|	
     #(t33:B_PARTIAL_INJECT
		 p1=predpi1:predicate
   		 p2=predpi2:predicate
    	)
      {
/* 	   	code="(new org.jmlspecs.b2jml.util.PartialInject("+p1+","+p2+"))";*/
			code = "(new org.jmlspecs.b2jml.util.PartialInject<" + 
				   toJML_TreeWalker.typeToString(predpi1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predpi2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
      }
			|	
            #(t34:B_TOTAL_INJECT
		      p1=predti1:predicate
              p2=predti2:predicate
            )
            {
 /*           	code="(new org.jmlspecs.b2jml.util.TotalInject("+p1+","+p2+"))"; */
 			code = "(new org.jmlspecs.b2jml.util.TotalInject<" + 
				   toJML_TreeWalker.typeToString(predti1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predti2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
            }
			|	
      #(t35:B_PARTIAL_SURJECT
		  p1=predps1:predicate
      	  p2=predps2:predicate
      ){
 /*     code="(new org.jmlspecs.b2jml.util.PartialSurject("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.PartialSurject<" + 
				   toJML_TreeWalker.typeToString(predps1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predps2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
      }
			|
	        #(t36:B_TOTAL_SURJECT
		    p1=predts1:predicate
            p2=predts2:predicate
            ){
/*            	code="(new org.jmlspecs.b2jml.util.TotalSurject("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.TotalSurject<" + 
				   toJML_TreeWalker.typeToString(predts1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predts2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";

            }
			|
	        #(t37:B_BIJECTION
 		        p1=predb1:predicate
            	p2=predb2:predicate
            ){
/*            	code="(new org.jmlspecs.b2jml.util.Bijection("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Bijection<" + 
				   toJML_TreeWalker.typeToString(predb1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predb2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
            }
			|
	        #(t38:B_DOMAINRESTRICT
		        	p1=predicate
              p2=predicate
            )
            {
							code=p2+".restrictDomainTo("+p2+".domain().intersection("+p1+"))";
            }
						|
	        	#(t39:B_RANGERESTRICT
		        	p1=predicate
              p2=predicate
            )
            {
							code=p1+".restrictRangeTo("+p1+".range().intersection("+p2+"))";          
            }
					|
	        #(t40:B_DOMAINSUBSTRACT
		        	p1=ds1:predicate
              p2=ds2:predicate
            )
            {
            	if (ds1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
            	if (!(ds2.getBType() instanceof INFIXE)) p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
            	code="org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction("+p1+","+p2+")";
            }
            
			|
	        #(t41:B_RANGESUBSTRACT
	        	p1=rs1:predicate 
	        	p2=rs2:predicate
	        )
	        {
            	if (rs2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
            	if (!(rs1.getBType() instanceof INFIXE)) p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
	        	code="org.jmlspecs.b2jml.util.ModelUtils.range_subtraction("+p1+","+p2+")";  
	        }
			|
	        #(t42:B_OVERRIDEFORWARD
	        	p1=predicate
	        	p2=predicate
	        )
	        {
	        	code="org.jmlspecs.b2jml.util.ModelUtils.overrideforward("+p1+","+p2+")";  	        
	        }
					|
	        #(t43:B_OVERRIDEBACKWARD
	         	p1=predicate
	         	p2=predicate
	         ) //not in B ref manual
					|
	        #(t44:B_RELPROD
	        	p1=predicate
	        	p2=predicate
	        )
	        {
	        	code="org.jmlspecs.b2jml.util.ModelUtils.relprod("+p1+","+p2+")";  
	        }
			|
	        #(t45:B_UNION
	        	p1=pu1:predicate
	        	p2=pu2:predicate
	        )
	        {
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
	        }
			|
	        #(t46:B_INTER
			 			p1=predicate
					 	p2=predicate
				)
				{
					code=p1+".intersection("+p2+")";
				}
			|	#(t48:B_MAPLET
						p1=predicate
						p2=predicate
				)
				{
					code="org.jmlspecs.b2jml.util.ModelUtils.maplet("+p1+","+p2+")";  	   
				}
			|	#(t49:LIST_VAR
			 			p1=predicate
						p2=predicate
				) 
				{
					code=p1+","+p2;
				}
			|	#(B_NOT
					p1=predicate
			)
			{
				code="!"+p1;
			}
			|	#(B_RAN
					p1=predicate 
			)
			{
				code=p1+".range()";
			}
			|	#(B_DOM
					p1=predicate 
			)
			{
				code=p1+".domain()";
			}
			|	#(B_MIN
				p1=predicate
			)
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.min("+p1+")";
			}
			|	#(B_MAX
					p1=predicate
			)			
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.max("+p1+")";
			}
			|	#(B_CARD
					p1=predicate
			)
			{
				code=p1+".int_size()";
			}
			|	{
				String cd = "";
				}
				cd = cs:cset_description
		{
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
		}
;

// inherited from grammar toJML_ExpressionTreeWalker
cset_description returns [String code]{
	code = "";
	String p="";
	String s1;
	String s2;
}
:code=basic_sets 
	|	{
			String cv = "";
		}
		cv = cbasic_value
		{
			// toJML begin
			code = cv;
			// toJML end
		}
	|	#("bool"
		p=predicate
		){
		code=p;
		}
	| #(B_BRACKOPEN
		p=listPredicate
		){
		code=p;
		}
	| #(t2:B_RANGE
		s1=predicate
		s2=predicate
		) {
			code = "new org.jmlspecs.b2jml.util.Interval("+s1+","+s2+")";
		} // p1..p2 interval
	|	t3:B_EMPTYSET
		{
		code="JMLEqualsSet.EMPTY";
		}
	|	#(t4:B_CURLYOPEN
		p=pnode1: cvalue_set
		) {
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
		}
	| t1:B_SEQEMPTY {
		code=t1.getText();
	}
	|	is_record 
	|	code=quantification 
	|	q_lambda
;

// inherited from grammar toJML_ExpressionTreeWalker
cvalue_set returns [String code]{
		code="";
		String l1="";
		String p1="";
}
:#(t1:B_SUCH
			 		l1=list_var
			 		p1=predicate
				){
					code=l1+";"+p1;
				}
			|	#(t2:ELEM_SET
			 		l1=cvalue_set
			 		p1=predicate
				){
					code=l1+","+p1;
				}
			|	code=predicate
		;

// inherited from grammar toJML_ExpressionTreeWalker
cbasic_value returns [String code]{
	code = "";
	String p = "";
	String p1 = "";
}
:t1:B_ASTRING
	{
		code = "\""+t1+"\"";
	}
	|	t2:B_NUMBER
		{
			code = t2.getText();
		}
	|	#(B_TILDE
		p = predicate
		{
			code = p+".inverse()";
		}
		)
	|	
		p = nr:nameRenamedDecorated
		{
			// toJML begin
			code = p;
			if (nr.getBType() instanceof ENUM && enumConstants.contains(p)) {
				code = "\"" + p + "\"";
			}
			// toJML end
		}
	|	#(B_LPAREN
		p = pl0: predicate
		p1 = pl1: predicate
		{
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
		}
		)
	|	
		#(PARENT
		p = pred_func_composition
		)
		{
			code = p;
		}
	|	#(B_QUOTEIDENT 
		predicate
		predicate 		
		)
	|	#(APPLY_TO
		p = pl1d:predicate
		p1 = pl2d:predicate
		){
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
		}
	|	"TRUE"
		{
			code = "true";
		}
	|	"FALSE"
		{
			code = "false";
		}
	| BTRUE { code = "true";}
;

// inherited from grammar toJML_ExpressionTreeWalker
pred_func_composition returns [String code]{
		code = "";
		String p = "";
		String p2="";
	}
:#(B_SEMICOLON 
	p=pred_func_composition
	p2=predicate
	){
		if (5>3) throw new IllegalStateException("Sequential statement not supported");	
	}
	|	#(t2:B_PARALLEL  
		p=pred_func_composition
		p2=predicate
		){
		code=p+" && " +p2;
		}
	|	#(t3:B_COMMA	 
		p=pred_func_composition
		p2=predicate
		){
		code=p+","+p2;
		}
	|	p = predicate
		{
			code=p;
			
			// toJML begin
			// TODO: fix to use real type information
			/*
			listPredicate.add(p.get(0));
			listPredicate.add(p.get(1));
			*/
			// toJML end
		}
;

// inherited from grammar toJML_ExpressionTreeWalker
basic_sets returns [String code=new String()]:t1:"INT"			    {code = "org.jmlspecs.b2jml.util.B_Types.B_Int"; }
	|	t2:"INT1"		    {code = "org.jmlspecs.b2jml.util.B_Types.B_Int1"; }
	|	t3:"INTEGER"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Integer"; }
	|	t4:"INTEGER1"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Integer1"; }
	|	t5:"BOOL"   		{code = "org.jmlspecs.b2jml.util.B_Types.B_Boolean";}
	|	t6:"NAT"    		{code = "org.jmlspecs.b2jml.util.B_Types.B_Nat"; }
	|	t7:"NAT1"	    	{code = "org.jmlspecs.b2jml.util.B_Types.B_Nat1"; }
	|	t8:"NATURAL"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Natural"; }
	|	t9:"NATURAL1"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Natural1"; }
	|	t10:"STRING"		{code = "org.jmlspecs.b2jml.util.B_Types.B_String";}
;

// inherited from grammar toJML_ExpressionTreeWalker
quantification returns [String code]{
	code="";
	String s1="";
	String s2="";
}
:#(t70:B_FORALL
			s1=forall1: list_var
			s2=predicate
		){
			code="("+back+"forall "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
		}
		|	#(t71:B_EXISTS
			s1=exists1:list_var
			s2=predicate
		){
			code="("+back+"exists "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
		}
		;

// inherited from grammar toJML_ExpressionTreeWalker
q_lambda :#(t1:B_LAMBDA
			q_operande
				)
			|	#(t2:"PI"
					q_operande
				)
			|	#(t3:"SIGMA"
					q_operande
				)
			|	#(t4:"UNION"
					 q_operande
				)
			|	#(t5:"INTER"
					 q_operande
				)
		;

// inherited from grammar toJML_ExpressionTreeWalker
q_operande :#(t1:B_SUCH
				list_var 
				predicate
				predicate
			)
		;

// inherited from grammar toJML_ExpressionTreeWalker
list_var returns [String code]{
	code="";
	String s1="";
}
:#(B_LPAREN
				s1=list_identifier
			){
				code="("+s1+")";
			}
			|
			code=list_identifier
;


