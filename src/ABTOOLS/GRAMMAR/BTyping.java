// $ANTLR 2.7.6 (2005-12-22): "BTyping.g" -> "BTyping.java"$

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

// Our Packages
    	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*; 
    	import ABTOOLS.TYPING.*;


public class BTyping extends antlr.TreeParser       implements BTypingTokenTypes
 {

	String module = "Typing.g";

// Variables liees aux nombres d'objet
// ====================================

	static int nb_exist = 0; 


// Quelques variables pour memoriser des arbres a revalider
// =========================================================

	public 	MyNode 	promote;
	private boolean param_module;

	public 	MyNode 	param_machine;
	public 	MyNode 	variable;			    	// Liste des variables, une fois l'invariant type, on peut
	public 	MyNode 	abstract_variable;			// re-evaluer la liste des variables pour verifier qu'elles 
	public 	MyNode 	visible_variable;			// sont toutes typees.
	public 	MyNode 	hidden_variable;
	public 	MyNode 	concrete_variable;
	public 	MyNode 	local_variable;
	public 	MyNode 	param_variable;
	public 	MyNode 	param_out;
	public 	MyNode 	param_in;
	public 	MyNode 	constant;			    	// Liste des constantes, une fois les properties analysees
	public 	MyNode 	abstract_constant;			// re-evaluer la liste des constantes pour verifier qu'elles 
	public 	MyNode 	visible_constant;			// sont toutes typees.
	public 	MyNode 	hidden_constant;
	public 	MyNode 	concrete_constant;

	private boolean param_machine_valide		= false;
    	private boolean set_valide                  	= false;
	private boolean variable_valide			= false;
	private boolean concrete_variable_valide	= false;
	private boolean local_variable_valide		= false;
	private	boolean	constant_valide			= false;

    	private boolean machine   = false;

	private boolean typant    = false;

// access to symbol table
    public MySymbolTable symbolTable = new MySymbolTable();

// Error Messages
// ==============

	public ErrorMessage errors = new ErrorMessage();

	public void setErrors (ErrorMessage err)
	{
		errors = err;
        	symbolTable.setErrors(err);
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
		symbolTable.setDebug(newdebug);
	}

// Project Management
// ==================
	Project currentProject = null;

	public void setProject (Project project)
	{
		currentProject = project;
	}


// Name of current file
// ====================
	String filename;

	public void setFileName (String name)
	{
		filename = name;
	}

// Gestion de la table des symboles
// ================================


// source for names to unnamed scopes
    	protected int unnamedScopeCounter = 0;

    	public String getAScopeName() 
    	{
        	return "" + (unnamedScopeCounter++);
    	}

    	public void pushScope(String scopeName) 
    	{
        	symbolTable.pushScope(scopeName);
    	}

    	public void popScope() 
    	{
        	symbolTable.popScope();
    	}

// For printing the Symbol Table ... 
	public String ListVariable ()
	{
		return symbolTable.toString();
	}

// for external access to the symbolTable
	public MySymbolTable getSymbolTable ()
	{
		return symbolTable;
	}

// Passage d'un node a une chaine nom + num line 
	public String nodeToString (MyNode name)
	{
		return name.getText()+"(line "+name.getLineNum()+")" ;
	}

// A special function for verify identifier unicity 
	public boolean idVerify(String name)
	{
		debug.println("CLASS Typing, OP idVerify, PARAMETER s =" + name);

		MyNode tmp = symbolTable.lookupNameInCurrentScope(name);
		return (tmp == null);	
	}

// Ajout d'un Identificateur dans la table le cas echeant 
// et possibilite d'emission d'un erreur
	public void addId (MyNode name, ABType type)
	{
		debug.println("CLASS Typing, OP addId, PARAMETER name =" + name+" type ="+type.toString());

		boolean ve = idVerify(name.getText());

		if (ve == false)
			System.out.println(errors.IdMultipleDefined(name.getText()+"(line "+name.getLineNum()+")")); 
		else
		{
			name.setBType((type.clone()).reduce(errors));
			symbolTable.add(name.getText(),(MyNode)name);
		}
	}

// Ajout d'un Identificateur dans la table le cas echeant 
// et possibilite d'emission d'un erreur
	public void addIdInCurrentScope (MyNode name, ABType type)
	{
		debug.println("CLASS Typing, OP addIdInCurrentScope, PARAMETER name =" + name+" type ="+type.toString());

		String  nn = symbolTable.addCurrentScopeToName(name.getText());
		boolean ve = symbolTable.lookupScopedName(nn) == null;

		if (ve == false)
			System.out.println(errors.IdMultipleDefined(name.getText()+"(line "+name.getLineNum()+")")); 
		else
		{
			name.setBType((type.clone()).reduce(errors));
			symbolTable.add(name.getText(),(MyNode)name);
		}
	}

// Complete le type d'un identificateur => il doit exister ...
	public void addTypeToId (MyNode name, ABType type)
	{
 		debug.println("CLASS Typing, OP addTypeToId, PARAMETER name =" + name.getText()+" type ="+type.toString());

 		boolean ve = idVerify(name.getText());

		// Si il existe
		if (ve == false)
		{
			// Recherche si il existe dans la table des symboles
 			MyNode current = symbolTable.lookupNameInCurrentScope(name.getText());

			// Si il existe et qu'il n'est pas type on le complete
			if (current.getBType().toString().compareTo((new ABType()).toString())== 0)
			{
				// ensemble non value, on surcharge le type 
				// et ce qu'il y a dans la table des symboles

	 			name.setBType(type.clone());
	 			symbolTable.add(name.getText(),(MyNode)name);
			}
			else	// Si il existe dans la table et qu'il est type
			{	
				// Pour verifier la compatibilite on genere le pred =(t1,t2) 
				// et on le reduit

System.out.println("addTypeToId "+current.toString() +" T1 "+current.getBType().toString()+ " T2 :" +type.toString());

				ABType tmp  = new EQUAL((current.getBType()).clone(), type.clone());

				// 
				if (!(typeControle(tmp)))
				{
					// On laisse pour l'instant un message d'erreur ....
// Ancien message
// System.out.println(errors.IdMultipleDefined(nodeToString(name)+"(line "+name.getLineNum()+")")); 

					System.out.println(errors.TypeIncompatible(	current.getBType().toString(),
											type.toString(),
											"(line "+name.getLineNum()+")" 
										)
							);
				}
			}
		}
		else
		{
 			name.setBType(type.clone());
			symbolTable.add(name.getText(),(MyNode)name);
		}
	}

    /** @return all machines either included or refined by this machine (transitively) */
    private java.util.ArrayList<AbstractMachine> getAllLinkedHelper(String fileName) {
        java.util.ArrayList<AbstractMachine> result = new java.util.ArrayList<AbstractMachine>();
        ListAM lam = currentProject.lookupName(fileName).getLinked();
        if (lam != null)  {
            lam.init();
            while (lam.isNotEmpty()) {
            	String currentname = "";
            	String extension = "";
            	LinkedAM  tmp = lam.getAM();
            	currentname = tmp.getName();
            	extension = tmp.getExtension();
            	AbstractMachine aml = currentProject.lookupName(currentname+extension);
           		if (!result.contains(aml)) {
                    result.add(aml);
            	}
            	java.util.ArrayList<AbstractMachine> linked = getAllLinkedHelper(currentname+extension);
            	for (AbstractMachine am : linked) {
               		if (!result.contains(am)) {
                    	result.add(am);
                	}
            	}
        	}
    	}
        return result;
    }
    
    private java.util.HashMap<String, java.util.ArrayList<AbstractMachine>> linked = 
      new java.util.HashMap<String, java.util.ArrayList<AbstractMachine>>();

    /** @return all machines either included or refined by this machine (transitively) */
    public java.util.ArrayList<AbstractMachine> getAllLinked(String fileName) {
    	java.util.ArrayList<AbstractMachine> links = linked.get(fileName);
    	if (links == null) {
    		links = getAllLinkedHelper(fileName);
    		linked.put(fileName, links);
    	}
    	return links;
	}

// Search an IDENTIFICATEUR, and generate an error if not found
public ABType searchId (MyNode name, ABType type)
{
    debug.println("CLASS Typing, OP searchId, PARAMETER name =" + name.getText()+" type ="+type.toString());
    
    boolean ve =idVerify(name.getText());
    
    // Si il existe
    if (ve == false)
  {
      debug.println("CLASS Typing, OP searchId, on a trouve l'id" + name);
      
      // Recherche si il existe dans la table des symboles
      MyNode current = symbolTable.lookupNameInCurrentScope(name.getText());
      
      System.out.println("SearchId : A pour type :"+current.getBType().toString());
      
      debug.println("CLASS Typing, OP searchId, son type est  =" +current.toString());
      
      // Si il existe et qu'il n'est pas type on le complete
      if (current.getBType().toString().compareTo((new ABType()).toString())== 0)
    {
        debug.println("CLASS Typing, OP searchId, on lui donne le type  =" +type.toString());
        
        // ensemble non value, on surcharge le type 
        // et ce qu'il y a dans la table des symboles
        name.setBType(type.clone());
        symbolTable.add(name.getText(),(MyNode)name);
        
        // le type est celui d'entree
        return type;
    }
      else
    { // Si il existe et qu'il est type on recupere le type
        ABType tmp = current.getBType();
        
        // Il faut verifier la compatibilite des types
        
        System.out.println("SearchId : A pour type :"+tmp.toString());
        
        // Le type de retour est celui de la table
        return tmp;
    }
  }
    else if (currentProject != null) // on recherche dans le projet
  {
      
      System.out.println("Projet existe");
      
      boolean trouve  = false;
      ABType  current = new ABType();
      
      java.util.Iterator<AbstractMachine> lam = getAllLinked(filename).iterator();
      while (lam.hasNext() && !trouve) {
      	 AbstractMachine aml = lam.next();
      	  String currentname = aml.getName();
          String extension = "";
          int pos = currentname.lastIndexOf('.');
          if (pos > -1) {
          	extension = currentname.substring(pos + 1, currentname.length());
          	currentname = currentname.substring(0, pos);
          }
          System.out.println("on regarde la machine : "+ currentname);
      	  
          
          if (aml != null)
        {
            
            System.out.println("on a trouve la machine : " + currentname+extension);
           
            // TAW 8/6/2011
            // temporary patch so that lookups occur more than one refinement back
           // lam = currentProject.lookupName(currentname+extension).getLinked();
            //lam.init();
            
            // Pour deboggage ...
            //DEBUG db = new DEBUG();
            //db.Enable_Debugging();
            //aml.setDebug(db);
            //MySymbolTable st = aml.getSymbolTable();
            //st.setDebug(db);
            //MyNode node = st.lookupScopedName(currentname+"::"+name.getText());
            
            MySymbolTable sm =aml.getSymbolTable();
            
            // July 2003
            // BJL
            // Il peut y avoir des machines absentes du projet
            if (sm != null)
          {
              MyNode node = sm.lookupScopedName(currentname+"::"+name.getText());
              
              if (node != null)
            {
                System.out.println(" la variable est trouvee " + currentname+"::"+name.getText());
                
                trouve  = true;
                current = node.getBType();
            }
              
              node = sm.lookupScopedName(currentname+"::"+ symbolTable.currentScopeWithoutComponent()+"::"+name.getText());
              
              if (node != null)
            {
                System.out.println(" la variable est trouvee " + currentname+"::"+name.getText());
                
                trouve  = true;
                current = node.getBType();
            }
          }
            //else
            //{
            //trouve  = false;
            //current = new ABType();
            //System.out.println(errors.IdNotDefined(nodeToString(name)));
            //}
        }
          
          // April 2002
          // Clause REFINE => MACHINE ou REFINEMENT
          // A VERIFIER.
          else
        {
            aml = currentProject.lookupName(currentname+extension);
            if (aml != null)
          {
              MySymbolTable sm =aml.getSymbolTable();
              
              // July 2003
              // BJL
              // Il peut y avoir des machines absentes du projet
              if (sm != null)
            {
                MyNode node = sm.lookupScopedName(currentname+"::"+name.getText());
                
                if (node != null)
              {
                  trouve  = true;
                  current = node.getBType();
              }
            }
          }
            //else
            //{
            //trouve  = false;
            //current = new ABType();
            //System.out.println(errors.IdNotDefined(nodeToString(name)));
            //}
        }
    }
      
      // ATTENTION
      // Faut-il verifier la compatibilite 
      
      return current;
  }
    else  // Il n'est pas defini il faut generer une erreur....
  {
      System.out.println(errors.IdNotDefined(nodeToString(name)));
      
      // On retourne un type de base
      return new ABType();
  }
}

// Typing verification of type
	protected boolean typeControle(ABType type)
	{
		return (type.reduce(errors).toString().compareTo((new BOOL()).toString())== 0);
	}

// ABType Controle and traitement
	protected void typeControleTreatement (MyNode name, ABType type)
	{
		debug.println("CLASS Typing, OP typeControleTreatement, PARAMETER name =" + name+" type ="+type.toString());

		// Normalement il faut verifier le type de retour afin de verifier
		// que tout c'est bien passe ...

		ABType tmp = new EQUAL(name.getBType(), type.clone());

		if (typeControle(tmp))
			name.setBType(type);
	}

// Transfert du type

/**
 * Permet de recopier les informations utiles d'un Noeud "source" vers un Noeud "target"
 **/
	protected void transfertType(MyNode source, MyNode target)
	{
		ABType tmp = (source.getBType()).clone();
		tmp.setLineNumber(target.getLineNum());				// On reaffecte le numero de ligne

		target.setBType(tmp);
	}



// Verification de la compatibilite et reduction

	protected ABType compatibilityReduceType (MyNode target, ABType tmp1, ABType tmp2)
	{
		ABType type;

  		ABType tmpType = new EQUAL (tmp1.clone(),tmp2.clone());

System.out.println("compatibility "+target.toString() +" t2:"+tmpType.toString());

		if (typeControle( tmpType ) )
			type = tmp1.reduce(errors);				        // Ils sont compatibles d'ou on en reduits 1 
		else
		{
			type = tmpType.reduce(errors);				    // On renvoie le message d'erreur.
			System.out.println(errors.TypeIncompatible(tmp1.toString(),tmp2.toString(),"(line "+target.getLineNum()+")"));
		}

		type.setLineNumber(target.getLineNum());			// On reaffecte le numero de ligne

  		target.setBType(type);
		return (type);
	}


// December 2001
/**
 * Verfication que toutes les variables appartenant a une liste sont typees
 **/
	protected ABType TypedDeclaratedVariable (MyNode list)
	{
		ABType type = null;

System.out.println("Begin TypedDeclaratedVariable");

		if (list != null)
		{
			// Verify the typing of all local variable
			try 
			{
				list_var_bis(list);
				type 	= (list.getBType()).clone();
				list 	= null;

				// Verification du typage de toutes les variables
				type.typingVerification(errors);
			}
            		catch(Exception e)
       			{
                		String StrTmp = errors.Internal   ( module, "PB in TypedDeclaratedVariable, exception:"+e );
    				System.out.println(StrTmp);			// Impression a l'ecran de l'erreur
                		type = new Typing_ERROR(StrTmp);		// On a un PB,on construit le type adequat
            		}
		}
		else
		{
// September 2002
// Add the case or list is null => not used => JOKER
			type = new JOKER();
		}
System.out.println("End TypedDeclaratedVariable");

		return type;
	}


// April 2002 
// Accessibilite des variables
	public void RA1 (MyNode node)
	{
// All variable are accessible ....

	}


// September 2002
// Final checking, verify that VARIABLE is associate to INVARIANT and ....
	protected void finalChecking ()
	{

		if ( param_machine_valide 	== true)
		{
			System.out.println(errors.IsNeeding("PRE"));
		}			
		if ( variable_valide 		== true)
		{
			System.out.println(errors.IsNeeding("INVARIANT"));
		}
		if ( concrete_variable_valide 	== true)
		{
			System.out.println(errors.IsNeeding("INVARIANT"));
		}
		if ( local_variable_valide 	== true)
		{
		}
		if ( constant_valide	 	== true)
		{
			System.out.println(errors.IsNeeding("PROPERTIES"));
		}
	        if (set_valide == true)
	        {
			System.out.println(errors.IsNeeding("PROPERTIES"));
	        }

	}

	public ABType unwrap(ABType type) {
		if (type instanceof UNARY) {
			return type.getMember();
		} else {
			return type;
		}
	}

public BTyping() {
	tokenNames = _tokenNames;
}

/**
 *	La regle "composant" permet de definir le point d'entree pour realiser le typage.
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
			
				finalChecking();
			
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
			
				// on active la memorisation des parametres pour typage en fin de constraints.
				param_module = true;
			machine      = true;
			
			paramName(_t);
			_t = _retTree;
			
				param_module = false;
			
			{
			_loop6:
			do {
				if (_t==null) _t=ASTNULL;
				switch ( _t.getType()) {
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
					break _loop6;
				}
				}
			} while (true);
			}
			
			// Verification de la declaration des operations promotes
				if (promote != null)
				{
					list_var_bis(promote);
					ABType type = promote.getBType();
				}
			
				popScope(); 				// Celui la c'est celui associe au paramName 
			
			machine = false;
			
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
			AST __t8 = _t;
			MyNode tmp2_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINEMENT);
			_t = _t.getFirstChild();
			
				// on active la memorisation des parametres pour typage en fin de constraints.
				param_module = true;
			
			paramName(_t);
			_t = _retTree;
			
				param_module = false;
			
			{
			_loop10:
			do {
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
					break _loop10;
				}
				}
			} while (true);
			}
			
			// Verification de la declaration des operations promotes
				if (promote != null)
				{
					list_var_bis(promote);
					ABType type = promote.getBType();
				}
			
				popScope(); 				// Celui la c'est celui associe a la clause paramName 
			
			_t = __t8;
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
			AST __t12 = _t;
			MyNode tmp3_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPLEMENTATION);
			_t = _t.getFirstChild();
			
				// on active la memorisation des parametres pourtypage en fin de constraints.
				param_module = true;
			
			paramName(_t);
			_t = _retTree;
			
				param_module = false;
			
			{
			_loop14:
			do {
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
					break _loop14;
				}
				}
			} while (true);
			}
			
			// Verification de la declaration des operations promotes
				if (promote != null)
				{
					list_var_bis(promote);
					ABType type = promote.getBType();
				}
			
				popScope(); 				// Celui la c'est celui associe au paramName 
			
			_t = __t12;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/**
 * This rule is used for defined all Name with or without parameters
 **/
	public final void paramName(AST _t) throws RecognitionException {
		
		MyNode paramName_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		MyNode ll = null;
		MyNode name1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t16 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					pushScope(name.getText());
				
				ll = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					ABType tmp = new FUNC_DEF(new ABType(), ll.getBType());
					tmp.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
					name.setBType(tmp);
				
					if (param_module)
				{
						param_machine = ll;
				}
				
					popScope();
					symbolTable.add(name.getText(),(MyNode)name);
					pushScope(name.getText());
				
				_t = __t16;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					pushScope(name1.getText());
				
					ABType type = new FUNC_DEF(new CONSTANT(), new ABType());
					type.setLineNumber(name1.getLineNum());				// On reaffecte le numero de ligne
					name1.setBType(type);
				
					popScope();
					symbolTable.add(name1.getText(),(MyNode)name1);
					pushScope(name1.getText());
				
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
	
	public final void constraints(AST _t) throws RecognitionException {
		
		MyNode constraints_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode c1 = null;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t24 = _t;
			c1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_CONSTRAINTS);
			_t = _t.getFirstChild();
			
				ABType type=new ABType(); 
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
			// ATTENTION
			// QUESTION : On reaffecte le type mais est ce necessaire ??
				type.setLineNumber(c1.getLineNum());			// On reaffecte le numero de ligne
				tt.setBType(type);
				c1.setBType(type);
			
			
			// Verification du typages des parametres d'E/S
				if (param_machine != null)
				{
					list_var_bis(param_machine);
					ABType ltype = param_machine.getBType();
				}
			
			_t = __t24;
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
			AST __t53 = _t;
			MyNode tmp4_AST_in = (MyNode)_t;
			match(_t,LITERAL_SETS);
			_t = _t.getFirstChild();
			sets_declaration(_t);
			_t = _retTree;
			_t = __t53;
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
		MyNode lv1 = null;
		MyNode lv2 = null;
		MyNode lv3 = null;
		MyNode lv4 = null;
		MyNode lv5 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_CONSTANTS:
			{
				AST __t47 = _t;
				MyNode tmp5_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONSTANTS);
				_t = _t.getFirstChild();
				lv1 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					constant = lv1;
					constant_valide	= true;
				
				_t = __t47;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				AST __t48 = _t;
				MyNode tmp6_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_CONSTANTS);
				_t = _t.getFirstChild();
				lv2 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					concrete_constant = lv2;
					constant_valide	= true;
				
				_t = __t48;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				AST __t49 = _t;
				MyNode tmp7_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_CONSTANTS);
				_t = _t.getFirstChild();
				lv3 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					visible_constant = lv3;
					constant_valide	= true;
				
				_t = __t49;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				AST __t50 = _t;
				MyNode tmp8_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_CONSTANTS);
				_t = _t.getFirstChild();
				lv4 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					abstract_constant = lv4;
					constant_valide	= true;
				
				_t = __t50;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				AST __t51 = _t;
				MyNode tmp9_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_CONSTANTS);
				_t = _t.getFirstChild();
				lv5 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					hidden_constant = lv5;
					constant_valide	= true;
				
				_t = __t51;
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
			AST __t97 = _t;
			MyNode tmp10_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROPERTIES);
			_t = _t.getFirstChild();
			
				ABType type 	= new ABType();
				ABType type1	= new ABType();
				typant 		= true;				// Debut de phase typante
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type1=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
			// Verification de la declaration des constantes
			
				if (constant != null)
				{
					list_var_bis(constant);
					type = constant.getBType();
					constant_valide	= false;
				}
				if (abstract_constant != null)
				{
					list_var_bis(abstract_constant);
					type = abstract_constant.getBType();
					constant_valide	= false;
				}
				if (visible_constant != null)
				{
					list_var_bis(visible_constant);
					type = visible_constant.getBType();
					constant_valide	= false;
				}
				if (hidden_constant != null)
				{
					list_var_bis(hidden_constant);
					type = hidden_constant.getBType();
					constant_valide	= false;
				}
				if (concrete_constant != null)
				{
					list_var_bis(concrete_constant);
					type = concrete_constant.getBType();
					constant_valide	= false;
				}
			
				typant = false;						// Fin de phase Typante
			
			_t = __t97;
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
		MyNode lv1 = null;
		MyNode lv2 = null;
		MyNode lv3 = null;
		MyNode lv4 = null;
		MyNode lv5 = null;
		
		try {      // for error handling
			
			variable_valide	= true;
			
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_VARIABLES:
			{
				AST __t100 = _t;
				MyNode tmp11_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIABLES);
				_t = _t.getFirstChild();
				lv1 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
						variable = lv1;
				
				_t = __t100;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				AST __t101 = _t;
				MyNode tmp12_AST_in = (MyNode)_t;
				match(_t,LITERAL_ABSTRACT_VARIABLES);
				_t = _t.getFirstChild();
				lv2 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
						abstract_variable = lv2;
				
				_t = __t101;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				AST __t102 = _t;
				MyNode tmp13_AST_in = (MyNode)_t;
				match(_t,LITERAL_VISIBLE_VARIABLES);
				_t = _t.getFirstChild();
				lv3 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
						visible_variable = lv3;
				
				_t = __t102;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				AST __t103 = _t;
				MyNode tmp14_AST_in = (MyNode)_t;
				match(_t,LITERAL_CONCRETE_VARIABLES);
				_t = _t.getFirstChild();
				lv4 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
						concrete_variable = lv4;
				
				_t = __t103;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_HIDDEN_VARIABLES:
			{
				AST __t104 = _t;
				MyNode tmp15_AST_in = (MyNode)_t;
				match(_t,LITERAL_HIDDEN_VARIABLES);
				_t = _t.getFirstChild();
				lv5 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
						hidden_variable = lv5;
				
				_t = __t104;
				_t = _t.getNextSibling();
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
	
	public final void invariant(AST _t) throws RecognitionException {
		
		MyNode invariant_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode b1 = null;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t106 = _t;
			b1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,LITERAL_INVARIANT);
			_t = _t.getFirstChild();
			
				ABType type	    	= new ABType();
				ABType tmpType		= new ABType();
			typant     		= true;					// Debut de phase typante
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
			
			// ATTENTION
			// QUESTION : On reaffecte le type mais est ce necessaire ??
				type.setLineNumber(b1.getLineNum());			// On reaffecte le numero de ligne
				tt.setBType(type);
				b1.setBType(type);
			
			// Une fois l'invariant declare, toutes les variables sont typees
			
			// Verification de la declaration des variables
				tmpType 	= TypedDeclaratedVariable(variable);
				tmpType 	= TypedDeclaratedVariable(abstract_variable);
				tmpType 	= TypedDeclaratedVariable(visible_variable);
				tmpType 	= TypedDeclaratedVariable(hidden_variable);
				tmpType 	= TypedDeclaratedVariable(concrete_variable);
			
				typant = false;						// Fin de phase typante
			
			if (type != null)
			{
			variable_valide = false;
			}
			
			_t = __t106;
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
			AST __t117 = _t;
			MyNode tmp16_AST_in = (MyNode)_t;
			match(_t,LITERAL_ASSERTIONS);
			_t = _t.getFirstChild();
			list_assertions(_t);
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
	
	public final void definitions(AST _t) throws RecognitionException {
		
		MyNode definitions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t108 = _t;
			MyNode tmp17_AST_in = (MyNode)_t;
			match(_t,LITERAL_DEFINITIONS);
			_t = _t.getFirstChild();
			
			errors.Disabling();
			
			list_def(_t);
			_t = _retTree;
			_t = __t108;
			_t = _t.getNextSibling();
			
			errors.Enabling();
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/**
 **	L'initialisation ne doit pas introduire de VARIABLE 
 **	sauf si on utilise l'instruction VAR .. IN ... END 
 **	=> pas de push/pop
 **/
	public final void initialisation(AST _t) throws RecognitionException {
		
		MyNode initialisation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t121 = _t;
			MyNode tmp18_AST_in = (MyNode)_t;
			match(_t,LITERAL_INITIALISATION);
			_t = _t.getFirstChild();
			instruction(_t);
			_t = _retTree;
			_t = __t121;
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
			AST __t123 = _t;
			MyNode tmp19_AST_in = (MyNode)_t;
			match(_t,LITERAL_OPERATIONS);
			_t = _t.getFirstChild();
			listOperation(_t);
			_t = _retTree;
			_t = __t123;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
/**
 * A FAIRE:
 * ========
 * Pour realiser un typage complet, on a besoin d'avoir acces a la "notion de projet"
 * pour ceci afin de verifier que 
 *	- la machine existe et que c'est bien une machine, ou un raffinement suivant le cas
 *	- le raffinement de la machine est correct au niveau parametre
 *
 */
	public final void refines(AST _t) throws RecognitionException {
		
		MyNode refines_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		
		try {      // for error handling
			AST __t45 = _t;
			MyNode tmp20_AST_in = (MyNode)_t;
			match(_t,LITERAL_REFINES);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				// Pour l'instant c'est un FUNC_DEF on pourrait imaginer mettre un MCH_DEF
				addId(name,new FUNC_DEF());
			
			_t = __t45;
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
			AST __t86 = _t;
			MyNode tmp21_AST_in = (MyNode)_t;
			match(_t,LITERAL_VALUES);
			_t = _t.getFirstChild();
			list_valuation(_t);
			_t = _retTree;
			_t = __t86;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void listTypedIdentifier(AST _t) throws RecognitionException {
		
		MyNode listTypedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t20 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				t2 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				t3 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					// C'est bien un produit cartesien de type ...
				
					ABType type = new PROD(t2.getBType(),t3.getBType());
					
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
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
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void paramNameOP(AST _t) throws RecognitionException {
		
		MyNode paramNameOP_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		MyNode ll = null;
		MyNode name1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t18 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					pushScope(name.getText());
				
				ll = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					param_in = ll;
				
					ABType tmp = new FUNC_DEF(new ABType(), ll.getBType());
					tmp.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
					name.setBType(tmp);
				
					popScope();
					symbolTable.add(name.getText(),(MyNode)name);
					pushScope(name.getText());
				
				_t = __t18;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name1 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					pushScope(name1.getText());
				
					ABType type = new FUNC_DEF(new CONSTANT(), new ABType());
					type.setLineNumber(name1.getLineNum());				// On reaffecte le numero de ligne
					name1.setBType(type);
				
					popScope();
					symbolTable.add(name1.getText(),(MyNode)name1);
					pushScope(name1.getText());
				
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
 * La regle "typedIdentifier" permet de prendre en compte l'extension 
 * de typage explicite de B'
 */
	public final void typedIdentifier(AST _t) throws RecognitionException {
		
		MyNode typedIdentifier_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode name = null;
		MyNode name1 = null;
		
			String newCurrent="";
			ABType newType=new ABType(); 
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_INSET)) {
				AST __t22 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				name = _t==ASTNULL ? null : (MyNode)_t;
				newCurrent=nameRenamed(_t);
				_t = _retTree;
				newType=predicate(_t);
				_t = _retTree;
				
					// ATTENTION
					// Question : que fait-on comme verification sur newCurrent ??
					// Reponse  : Aucune car AddTypeToId verifie l'unicite avant ajout a la table.
				
				newType.setLineNumber(t1.getLineNum());			// On reaffecte le numero de ligne
				name.setBType(newType);
				t1.setBType(newType);						// Il faut typer les deux noeuds pour la decompilation
				addIdInCurrentScope(name,newType);
				
				
				_t = __t22;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				name1 = _t==ASTNULL ? null : (MyNode)_t;
				newCurrent=nameRenamed(_t);
				_t = _retTree;
				
					newType = new ABType();
				
				if (machine == true)
				{
				
				}
				else
				{
				System.out.println("\n II -- on cherche un Id "+name1.getText()+" context "+symbolTable.currentScopeWithoutComponent());
				newType = searchId(name1, newType);
				System.out.println("on a trouver Id "+name1.getText()+" "+newType.toString()+"\n");
				System.out.println("-- II \n");
				}
				
					newType.setLineNumber(name1.getLineNum());			// On reaffecte le numero de ligne
					name1.setBType(newType);
					addIdInCurrentScope(name1,newType); 
				
				if (machine == true)
				{
				
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
	
/**
 *  Ne pas tenter de mettre l'identificateur dans la table des symboles
 * dans cette clause 
 **/
	public final String  nameRenamed(AST _t) throws RecognitionException {
		String currentName;
		
		MyNode nameRenamed_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode v = null;
		MyNode w = null;
		
			currentName = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				AST __t190 = _t;
				name = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getFirstChild();
				
					currentName = name.getText();
													// On retrourne un ID.
					ABType type = new ID();
					type.setLineNumber(name.getLineNum());				// On reaffecte le numero de ligne
					name.setBType(type);
				
				_t = __t190;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t191 = _t;
				v = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				currentName=nameRenamed(_t);
				_t = _retTree;
				w = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
													// on calcule le nom complet et 
													// on l'affecte au noeud principal
					currentName = currentName +v.getText()+w.getText();
				//	#v.setText(currentName);
				
													// On retrourne un ID.
					ABType type = new ID();
					type.setLineNumber(v.getLineNum());				// On reaffecte le numero de ligne
					v.setBType(type);
				
				_t = __t191;
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
		return currentName;
	}
	
/** 
 * Clause Predicate
 * Il faut verifier la compatibilite entre les deux types
 **/
	public final ABType  predicate(AST _t) throws RecognitionException {
		ABType theType;
		
		MyNode predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode a2 = null;
		MyNode a1 = null;
		MyNode a0 = null;
		MyNode t0 = null;
		MyNode o0 = null;
		MyNode o1 = null;
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
		MyNode t131 = null;
		MyNode p131 = null;
		MyNode t14 = null;
		MyNode t15 = null;
		MyNode t16 = null;
		MyNode t17 = null;
		MyNode t18 = null;
		MyNode t19 = null;
		MyNode p191 = null;
		MyNode p192 = null;
		MyNode t20 = null;
		MyNode t21 = null;
		MyNode p211 = null;
		MyNode p212 = null;
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
		MyNode t50 = null;
		
			theType 		= new ABType();
			ABType newType  	= new ABType();
			ABType newType1 	= new ABType();
			ABType tmpType 		= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CARD:
			{
				AST __t214 = _t;
				a2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CARD);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (a2, new SET(), newType1);
				
					theType.setLineNumber(a2.getLineNum());				// On reaffecte le numero de ligne
					a2.setBType(theType);	
				
				_t = __t214;
				_t = _t.getNextSibling();
				break;
			}
			case B_MIN:
			{
				AST __t215 = _t;
				a1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MIN);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (a1, new SET(), newType1);
				
					theType.setLineNumber(a1.getLineNum());				// On reaffecte le numero de ligne
					a1.setBType(theType);	
				
				_t = __t215;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAX:
			{
				AST __t216 = _t;
				a0 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAX);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (a0, new SET(), newType1);
				
					theType.setLineNumber(a0.getLineNum());				// On reaffecte le numero de ligne
					a0.setBType(theType);	
				
				_t = __t216;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOT:
			{
				AST __t217 = _t;
				t0 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOT);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t0, new BOOL(), newType1);
				
					theType.setLineNumber(t0.getLineNum());				// On reaffecte le numero de ligne
					t0.setBType(theType);	
				
				_t = __t217;
				_t = _t.getNextSibling();
				break;
			}
			case B_RAN:
			{
				AST __t218 = _t;
				o0 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RAN);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
				
				// BJL - May 2007
				// B_RAN prend le second membre d'un produit
				
					if (newType1.getType().compareTo("PROD")==0)
					{
						theType = newType1.getMember();
					}
					else
					{
						String msg = errors.TypeIncompatible( "ran(f) where f is not a function ",
						                                      newType1.toString(),
						                                      "(line"+newType1.getLineNumber() + ")");
						theType    = new Typing_ERROR(msg);
					}
				
					theType.setLineNumber(o0.getLineNum());				// On reaffecte le numero de ligne
					o0.setBType(theType);	
				
				_t = __t218;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOM:
			{
				AST __t219 = _t;
				o1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOM);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
				
				// BJL - April 2007
				// B_DOM prend le premier membre d'un produit
				
					if (newType1.getType().compareTo("PROD")==0)
					{
						theType = newType1.getMember2();
					}
					else
					{
						String msg = errors.TypeIncompatible( "dom(f) where f is not a function ",
						                                      newType1.toString(),
						                                      "(line"+newType1.getLineNumber() + ")");
						theType    = new Typing_ERROR(msg);
					}
				
					theType.setLineNumber(o1.getLineNum());				// On reaffecte le numero de ligne
					o1.setBType(theType);	
				
				_t = __t219;
				_t = _t.getNextSibling();
				break;
			}
			case B_AND:
			{
				AST __t220 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t1, newType, newType1);
				
					theType.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(theType);	
				
				_t = __t220;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_or:
			{
				AST __t221 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_or);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t2, newType, newType1);
				
					theType.setLineNumber(t2.getLineNum());				// On reaffecte le numero de ligne
					t2.setBType(theType);	
				
				_t = __t221;
				_t = _t.getNextSibling();
				break;
			}
			case B_IMPLIES:
			{
				AST __t222 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IMPLIES);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t3, newType, newType1);
				
					theType.setLineNumber(t3.getLineNum());				// On reaffecte le numero de ligne
					t3.setBType(theType);	
				
				_t = __t222;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUIV:
			{
				AST __t223 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUIV);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t4, newType, newType1);
				
					theType.setLineNumber(t4.getLineNum());				// On reaffecte le numero de ligne
					t4.setBType(theType);	
				
				_t = __t223;
				_t = _t.getNextSibling();
				break;
			}
			case B_MULT:
			{
				AST __t224 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				// 	theType = compatibilityReduceType (#t5, newType, newType1);
					theType = new PROD(newType, newType1);
				
					theType.setLineNumber(t5.getLineNum());				// On reaffecte le numero de ligne
					t5.setBType(theType);	
				
				_t = __t224;
				_t = _t.getNextSibling();
				break;
			}
			case PRODSET:
			{
				AST __t225 = _t;
				t51 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PRODSET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType, newType1);
				
					theType.setLineNumber(t51.getLineNum());				// On reaffecte le numero de ligne
					t51.setBType(theType);	
				
				_t = __t225;
				_t = _t.getNextSibling();
				break;
			}
			case B_POWER:
			{
				AST __t226 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_POWER);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t6, newType, newType1);
				
					theType.setLineNumber(t6.getLineNum());				// On reaffecte le numero de ligne
					t6.setBType(theType);	
				
				_t = __t226;
				_t = _t.getNextSibling();
				break;
			}
			case B_DIV:
			{
				AST __t227 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DIV);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t7, newType, newType1);
				
					theType.setLineNumber(t7.getLineNum());				// On reaffecte le numero de ligne
					t7.setBType(theType);	
				
				_t = __t227;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_mod:
			{
				AST __t228 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_mod);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t8, newType, newType1);
				
					theType.setLineNumber(t8.getLineNum());				// On reaffecte le numero de ligne
					t8.setBType(theType);	
				
				_t = __t228;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_ADD:
			{
				AST __t229 = _t;
				t9 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_ADD);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				
					// Pas d'evolution du type car A et +A sont du meme type.
					// Mais il faut que ce soit un INTEGER.
					theType = compatibilityReduceType (t9, newType, new INTEGER());
				
					theType.setLineNumber(t9.getLineNum());				// On reaffecte le numero de ligne
					t9.setBType(theType);	
				
				_t = __t229;
				_t = _t.getNextSibling();
				break;
			}
			case UNARY_MINUS:
			{
				AST __t230 = _t;
				t10 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				
					// Par contre pour -A, il y a des verifications de type a faire,
					// avant de construire le nouveau type
				
					// Il faut que l'on est un ENTIER 
					theType = compatibilityReduceType (t10, new INTEGER(), newType);
				
					theType.setLineNumber(t10.getLineNum());				// On reaffecte le numero de ligne
					t10.setBType(theType);	
				
				_t = __t230;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t231 = _t;
				t11 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t11, newType, newType1);
				
					theType.setLineNumber(t11.getLineNum());				// On reaffecte le numero de ligne
					t11.setBType(theType);	
				
				_t = __t231;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t232 = _t;
				t12 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = compatibilityReduceType (t12, newType, newType1);
				
					theType.setLineNumber(t12.getLineNum());				// On reaffecte le numero de ligne
					t12.setBType(theType);	
				
				_t = __t232;
				_t = _t.getNextSibling();
				break;
			}
			case B_EQUAL:
			{
				AST __t233 = _t;
				t131 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				{
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==B_IDENTIFIER)) {
					{
					p131 = (MyNode)_t;
					match(_t,B_IDENTIFIER);
					_t = _t.getNextSibling();
					newType1=predicate(_t);
					_t = _retTree;
					
						addTypeToId(p131,newType1);
						theType = new BOOL();
					
						theType.setLineNumber(t131.getLineNum());				// On reaffecte le numero de ligne
						t131.setBType(theType);	
					
					}
				}
				else if ((_tokenSet_0.member(_t.getType()))) {
					{
					newType=predicate(_t);
					_t = _retTree;
					newType1=predicate(_t);
					_t = _retTree;
					
						tmpType = new EQUAL (newType,newType1);
					
						theType = tmpType.reduce(errors);					    // On reduit et si compatible on a BOOL.
						theType.setLineNumber(t131.getLineNum());				// On reaffecte le numero de ligne
						t131.setBType(theType);
					
					}
				}
				else {
					throw new NoViableAltException(_t);
				}
				
				}
				_t = __t233;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESS:
			{
				AST __t237 = _t;
				t14 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESS);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					// Il faut que les deux types soit compatible si OUI => BOOL
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					    // On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t14.getLineNum());				// On reaffecte le numero de ligne
					t14.setBType(theType);	
				
				_t = __t237;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATER:
			{
				AST __t238 = _t;
				t15 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATER);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					// Il faut que les deux types soit compatible si OUI => BOOL
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t15.getLineNum());				// On reaffecte le numero de ligne
					t15.setBType(theType);	
				
				_t = __t238;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTEQUAL:
			{
				AST __t239 = _t;
				t16 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTEQUAL);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					// Il faut que les deux types soit compatible si OUI => BOOL
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t16.getLineNum());				// On reaffecte le numero de ligne
					t16.setBType(theType);	
				
				_t = __t239;
				_t = _t.getNextSibling();
				break;
			}
			case B_LESSTHANEQUAL:
			{
				AST __t240 = _t;
				t17 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LESSTHANEQUAL);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					// Il faut que les deux types soit compatible si OUI => BOOL
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t17.getLineNum());				// On reaffecte le numero de ligne
					t17.setBType(theType);	
				
				_t = __t240;
				_t = _t.getNextSibling();
				break;
			}
			case B_GREATERTHANEQUAL:
			{
				AST __t241 = _t;
				t18 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_GREATERTHANEQUAL);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				System.out.println("debut GE");
				
				System.out.println("t1"+newType.toString()+" t2 "+newType1.toString());
				
				
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t18.getLineNum());				// On reaffecte le numero de ligne
					t18.setBType(theType);
				
				System.out.println("fin GE");
				
				_t = __t241;
				_t = _t.getNextSibling();
				break;
			}
			case B_INSET:
			{
				AST __t242 = _t;
				t19 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				p191 = _t==ASTNULL ? null : (MyNode)_t;
				newType=predicate(_t);
				_t = _retTree;
				p192 = _t==ASTNULL ? null : (MyNode)_t;
				newType1=predicate(_t);
				_t = _retTree;
				
				
				System.out.println("INSET : t1="+p191.getBType().toString()+" t2="+p192.getBType().toString());
				System.out.println(" " + p191.getBType().toString().compareTo(newType.toString()));
				System.out.println("INSET : t1="+p191.getBType().toString()+" nt="+newType.toString());
				System.out.println("INSET : t2="+p192.getBType().toString()+" nt="+newType1.toString());
				
				
					// ATTENTION:
					//		soit ID    : xx
					//		soit ID,ID : xx*yy
				
					// Introduire un algorithme qui si newType est un ID verifie qu'il est ou non dans la table
					// et ensuite ajoute ou non un type
					// si il y avait un type on doit etre compatible .... sinon erreur ...
				
					// TAW 6/21/2011
					if (newType1 instanceof GIVENSET)  {
						theType = new BOOL();
						if (newType.IsDefined()) {
							if (!newType.toString().equals(unwrap(newType1).toString())) {
							System.out.println("ERROR: invalid type!");
							}
						} else {
				//			p191.setBType(unwrap(newType1));
							addTypeToId(p191,unwrap(newType1));
						}
					} else
					// END TAW
					if (p191.getBType().toString().compareTo(newType.toString())== 0)
					{
						addTypeToId(p191,newType1);
						theType = new BOOL();
					}
					else
					{
						tmpType = new EQUAL (newType,newType1);
						theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
					}
				
					theType.setLineNumber(t19.getLineNum());
					t19.setBType(theType);
				
				_t = __t242;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTINSET:
			{
				AST __t243 = _t;
				t20 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTINSET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t20.getLineNum());				// On reaffecte le numero de ligne
					t20.setBType(theType);
				
				_t = __t243;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUBSET:
			{
				AST __t244 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUBSET);
				_t = _t.getFirstChild();
				p211 = _t==ASTNULL ? null : (MyNode)_t;
				newType=predicate(_t);
				_t = _retTree;
				p212 = _t==ASTNULL ? null : (MyNode)_t;
				newType1=predicate(_t);
				_t = _retTree;
				
					if (p211.getBType().toString().compareTo(newType.toString())== 0)
					{
						addTypeToId(p211,newType1);
						theType = new BOOL();
					}
					else
					{
						tmpType = new EQUAL (newType,newType1);
						theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
					}
				
					theType.setLineNumber(t21.getLineNum());				// On reaffecte le numero de ligne
					t21.setBType(theType);	
				
				_t = __t244;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSUBSET:
			{
				AST __t245 = _t;
				t22 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSUBSET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t22.getLineNum());				// On reaffecte le numero de ligne
					t22.setBType(theType);
				
				_t = __t245;
				_t = _t.getNextSibling();
				break;
			}
			case B_STRICTSUBSET:
			{
				AST __t246 = _t;
				t23 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_STRICTSUBSET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t23.getLineNum());				// On reaffecte le numero de ligne
					t23.setBType(theType);
				
				_t = __t246;
				_t = _t.getNextSibling();
				break;
			}
			case B_NOTSTRICTSBSET:
			{
				AST __t247 = _t;
				t24 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NOTSTRICTSBSET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.
				
					theType.setLineNumber(t24.getLineNum());				// On reaffecte le numero de ligne
					t24.setBType(theType);
				
				_t = __t247;
				_t = _t.getNextSibling();
				break;
			}
			case B_CONCATSEQ:
			{
				AST __t248 = _t;
				t25 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CONCATSEQ);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
				
					theType.setLineNumber(t25.getLineNum());				// On reaffecte le numero de ligne
					t25.setBType(theType);
				
				_t = __t248;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREAPPSEQ:
			{
				AST __t249 = _t;
				t26 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREAPPSEQ);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t26.getLineNum());				// On reaffecte le numero de ligne
					t26.setBType(theType);
				
				_t = __t249;
				_t = _t.getNextSibling();
				break;
			}
			case B_APPSEQ:
			{
				AST __t250 = _t;
				t27 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_APPSEQ);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t27.getLineNum());				// On reaffecte le numero de ligne
					t27.setBType(theType);
				
				_t = __t250;
				_t = _t.getNextSibling();
				break;
			}
			case B_PREFIXSEQ:
			{
				AST __t251 = _t;
				t28 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PREFIXSEQ);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t28.getLineNum());				// On reaffecte le numero de ligne
					t28.setBType(theType);
				
				_t = __t251;
				_t = _t.getNextSibling();
				break;
			}
			case B_SUFFIXSEQ:
			{
				AST __t252 = _t;
				t29 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUFFIXSEQ);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t29.getLineNum());				// On reaffecte le numero de ligne
					t29.setBType(theType);
				
				_t = __t252;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELATION:
			{
				AST __t253 = _t;
				t30 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELATION);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType,newType1);					// Creation d'un nouveau type
				
					theType.setLineNumber(t30.getLineNum());				// On reaffecte le numero de ligne
					t30.setBType(theType);
				
				_t = __t253;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL:
			{
				AST __t254 = _t;
				t31 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t31.getLineNum());				// On reaffecte le numero de ligne
					t31.setBType(theType);
				
				_t = __t254;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL:
			{
				AST __t255 = _t;
				t32 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t32.getLineNum());				// On reaffecte le numero de ligne
					t32.setBType(theType);
				
				_t = __t255;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_INJECT:
			{
				AST __t256 = _t;
				t33 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_INJECT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t33.getLineNum());				// On reaffecte le numero de ligne
					t33.setBType(theType);
				
				_t = __t256;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_INJECT:
			{
				AST __t257 = _t;
				t34 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_INJECT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t34.getLineNum());				// On reaffecte le numero de ligne
					t34.setBType(theType);
				
				_t = __t257;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARTIAL_SURJECT:
			{
				AST __t258 = _t;
				t35 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_PARTIAL_SURJECT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t35.getLineNum());				// On reaffecte le numero de ligne
					t35.setBType(theType);
				
				_t = __t258;
				_t = _t.getNextSibling();
				break;
			}
			case B_TOTAL_SURJECT:
			{
				AST __t259 = _t;
				t36 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TOTAL_SURJECT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t36.getLineNum());				// On reaffecte le numero de ligne
					t36.setBType(theType);
				
				_t = __t259;
				_t = _t.getNextSibling();
				break;
			}
			case B_BIJECTION:
			{
				AST __t260 = _t;
				t37 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BIJECTION);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type
				
					theType.setLineNumber(t37.getLineNum());				// On reaffecte le numero de ligne
					t37.setBType(theType);
				
				_t = __t260;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINRESTRICT:
			{
				AST __t261 = _t;
				t38 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINRESTRICT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				theType = newType;
				
				_t = __t261;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGERESTRICT:
			{
				AST __t262 = _t;
				t39 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGERESTRICT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				theType = newType;
				
				_t = __t262;
				_t = _t.getNextSibling();
				break;
			}
			case B_DOMAINSUBSTRACT:
			{
				AST __t263 = _t;
				t40 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_DOMAINSUBSTRACT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				theType = newType;
				
				_t = __t263;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGESUBSTRACT:
			{
				AST __t264 = _t;
				t41 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGESUBSTRACT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
				theType = newType;
				
				_t = __t264;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEFORWARD:
			{
				AST __t265 = _t;
				t42 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEFORWARD);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = newType;
					theType.setLineNumber(t42.getLineNum());				// On reaffecte le numero de ligne
				
				_t = __t265;
				_t = _t.getNextSibling();
				break;
			}
			case B_OVERRIDEBACKWARD:
			{
				AST __t266 = _t;
				t43 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OVERRIDEBACKWARD);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = newType;
					theType.setLineNumber(t43.getLineNum());				// On reaffecte le numero de ligne
				
				_t = __t266;
				_t = _t.getNextSibling();
				break;
			}
			case B_RELPROD:
			{
				AST __t267 = _t;
				t44 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RELPROD);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = newType;
					theType.setLineNumber(t44.getLineNum());				// On reaffecte le numero de ligne
				
				_t = __t267;
				_t = _t.getNextSibling();
				break;
			}
			case B_UNION:
			{
				AST __t268 = _t;
				t45 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_UNION);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t45.getLineNum());				// On reaffecte le numero de ligne
					t45.setBType(theType);
				
				_t = __t268;
				_t = _t.getNextSibling();
				break;
			}
			case B_INTER:
			{
				AST __t269 = _t;
				t46 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INTER);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					tmpType = new EQUAL (newType,newType1);
				
					if (typeControle( tmpType ) )
						theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
					else
						theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.
				
					theType.setLineNumber(t46.getLineNum());
				
				_t = __t269;
				_t = _t.getNextSibling();
				break;
			}
			case B_MAPLET:
			{
				AST __t270 = _t;
				t48 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MAPLET);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType, newType1);
					theType.setLineNumber(t48.getLineNum());				// On reaffecte le numero de ligne
					t48.setBType(theType);
				
				_t = __t270;
				_t = _t.getNextSibling();
				break;
			}
			case LIST_VAR:
			{
				AST __t271 = _t;
				t49 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LIST_VAR);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new PROD(newType, newType1);
					theType.setLineNumber(t49.getLineNum());				// On reaffecte le numero de ligne
					t49.setBType(theType);
				
				_t = __t271;
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
				t50 = _t==ASTNULL ? null : (MyNode)_t;
				theType=cset_description(_t);
				_t = _retTree;
				
					t50.setBType(theType);
				
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
		return theType;
	}
	
	public final void uses(AST _t) throws RecognitionException {
		
		MyNode uses_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t29 = _t;
			MyNode tmp22_AST_in = (MyNode)_t;
			match(_t,LITERAL_USES);
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
	
	public final void includes(AST _t) throws RecognitionException {
		
		MyNode includes_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t31 = _t;
			MyNode tmp23_AST_in = (MyNode)_t;
			match(_t,LITERAL_INCLUDES);
			_t = _t.getFirstChild();
			listInstanciation(_t);
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
	
	public final void sees(AST _t) throws RecognitionException {
		
		MyNode sees_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t33 = _t;
			MyNode tmp24_AST_in = (MyNode)_t;
			match(_t,LITERAL_SEES);
			_t = _t.getFirstChild();
			listNames(_t);
			_t = _retTree;
			_t = __t33;
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
			AST __t27 = _t;
			MyNode tmp25_AST_in = (MyNode)_t;
			match(_t,LITERAL_EXTENDS);
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
	
	public final void promotes(AST _t) throws RecognitionException {
		
		MyNode promotes_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode lv = null;
		
		try {      // for error handling
			AST __t43 = _t;
			MyNode tmp26_AST_in = (MyNode)_t;
			match(_t,LITERAL_PROMOTES);
			_t = _t.getFirstChild();
			lv = _t==ASTNULL ? null : (MyNode)_t;
			listNames(_t);
			_t = _retTree;
			
				promote = lv;
			
			_t = __t43;
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
			AST __t41 = _t;
			MyNode tmp27_AST_in = (MyNode)_t;
			match(_t,LITERAL_IMPORTS);
			_t = _t.getFirstChild();
			listInstanciation(_t);
			_t = _retTree;
			_t = __t41;
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
				AST __t37 = _t;
				MyNode tmp28_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listInstanciation(_t);
				_t = _retTree;
				listInstanciation(_t);
				_t = _retTree;
				_t = __t37;
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
				AST __t35 = _t;
				MyNode tmp29_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				listNames(_t);
				_t = _retTree;
				listNames(_t);
				_t = _retTree;
				_t = __t35;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				
					String newCurrent="";
				
				newCurrent=nameRenamed(_t);
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
 * Cette regle permet de verifier le typage lors des instanciations
 * on doit type des objets de la forme ff(pred1, pred2, pred3) (pred4)
 *
 * A FAIRE:
 * ========
 * Pour realiser un typage complet, on a besoin d'avoir acces a la "notion de projet"
 * pour ceci afin de verifier que 
 *	- la machine existe et que c'est bien une machine
 *	- l'instanciation de la machine est correct
 *
 **/
	public final void paramRenameValuation(AST _t) throws RecognitionException {
		
		MyNode paramRenameValuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode pp = null;
		MyNode name1 = null;
		
			ABType type = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t39 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				pp = _t==ASTNULL ? null : (MyNode)_t;
				paramRenameValuation(_t);
				_t = _retTree;
				type=list_New_Predicate(_t);
				_t = _retTree;
				
					ABType tmp = pp.getBType();
					type 	 = new PROD(tmp, type);
				
				// Il faut etudier comment faire :
				// en effet on a F (a) (b) (c) qui se traduite en [ ( [ ( [ ( F, a] b] c ]
				// pas facilement typable 
				
				//	if (typeControle(new EQUAL(tmp, type)))
				//		type = new BOOL();
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
				_t = __t39;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				
					String newCurrent = "";
				
				name1 = _t==ASTNULL ? null : (MyNode)_t;
				newCurrent=nameRenamed(_t);
				_t = _retTree;
				
				// ATTENTION
				// Voir si le type final lors de parametrisation est correct 
				// BJL : rien a faire sur cette partie 
				
				
					type.setLineNumber(name1.getLineNum());			// On reaffecte le numero de ligne
					name1.setBType(type);
				
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
	
	public final ABType  list_New_Predicate(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode list_New_Predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
				type 	= new ABType();
			ABType 	tmp1 	= new ABType();
			ABType 	tmp2 	= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t185 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp1=list_New_Predicate(_t);
				_t = _retTree;
				tmp2=new_predicate(_t);
				_t = _retTree;
				
					type = new PROD(tmp1, tmp2);
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
				_t = __t185;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_1.member(_t.getType()))) {
				type=new_predicate(_t);
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
		return type;
	}
	
	public final void sets_declaration(AST _t) throws RecognitionException {
		
		MyNode sets_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t55 = _t;
				MyNode tmp30_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				sets_declaration(_t);
				_t = _retTree;
				_t = __t55;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t56 = _t;
				MyNode tmp31_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				sets_declaration(_t);
				_t = _retTree;
				sets_declaration(_t);
				_t = _retTree;
				_t = __t56;
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
		MyNode t1 = null;
		MyNode set = null;
		MyNode name = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_EQUAL)) {
				AST __t58 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EQUAL);
				_t = _t.getFirstChild();
				set = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					ABType type = new ABType();
				
				type=valuation_set(_t,set);
				_t = _retTree;
				
				//	type = new SET(type.reduce(errors));
					type.setLineNumber(t1.getLineNum());
					t1.setBType(type);
					addId(set,type);
				System.out.println("1.On a mis dans la table "+type.toString());
				
				_t = __t58;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					ABType type = new GIVENSET(new ABType(name.getText()));
					type.setLineNumber(name.getLineNum());
					name.setBType(type);
					addId(name,type);
				System.out.println("2.On a mis dans la table "+type.toString());
				
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
	
	public final ABType  valuation_set(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode valuation_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t100 = null;
		MyNode t3 = null;
		MyNode t4 = null;
		MyNode t5 = null;
		MyNode t6 = null;
		
			type  = new ABType();
			ABType 	tmp1  = new ABType();
			ABType 	tmp2  = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_CURLYOPEN:
			{
				AST __t63 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				tmp1=list_couple(_t,set);
				_t = _retTree;
				
				// Pour analyse des erreurs de compatibilite dans la declaration du type
					type = tmp1.reduce(errors);
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
				_t = __t63;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t64 = _t;
				t100 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				tmp1=a_set_value(_t,set);
				_t = _retTree;
				tmp2=a_set_value(_t,set);
				_t = _retTree;
				
				// Pour analyse des erreurs de compatibilite dans la declaration du type
					type = compatibilityReduceType (t100, tmp1, tmp2);
				
					type.setLineNumber(t100.getLineNum());				// On reaffecte le numero de ligne
					t100.setBType(type);
				
				_t = __t64;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				type=is_record(_t);
				_t = _retTree;
				break;
			}
			case B_MULT:
			{
				AST __t65 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MULT);
				_t = _t.getFirstChild();
				tmp1=valuation_set(_t,set);
				_t = _retTree;
				tmp2=valuation_set(_t,set);
				_t = _retTree;
				
				// 	type = compatibilityReduceType (#t3, tmp1, tmp2);
					type = new PROD(tmp1, tmp2);
					type.setLineNumber(t3.getLineNum());				// On reaffecte le numero de ligne
					t3.setBType(type);
				
				_t = __t65;
				_t = _t.getNextSibling();
				break;
			}
			case B_ADD:
			{
				AST __t66 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ADD);
				_t = _t.getFirstChild();
				tmp1=valuation_set(_t,set);
				_t = _retTree;
				tmp2=valuation_set(_t,set);
				_t = _retTree;
				
					type = compatibilityReduceType (t4, tmp1, tmp2);
					type.setLineNumber(t4.getLineNum());				// On reaffecte le numero de ligne
					t4.setBType(type);
				
				_t = __t66;
				_t = _t.getNextSibling();
				break;
			}
			case B_MINUS:
			{
				AST __t67 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_MINUS);
				_t = _t.getFirstChild();
				tmp1=valuation_set(_t,set);
				_t = _retTree;
				tmp2=valuation_set(_t,set);
				_t = _retTree;
				
					type = compatibilityReduceType (t5, tmp1, tmp2);
					type.setLineNumber(t5.getLineNum());				// On reaffecte le numero de ligne
					t5.setBType(type);
				
				_t = __t67;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			{
				t6 = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					type = searchId(t6,type);
					type.setLineNumber(t6.getLineNum());				// On reaffecte le numero de ligne
					t6.setBType(type);
				
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
				type=basic_sets(_t);
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
		return type;
	}
	
	public final ABType  is_record(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode is_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode t2 = null;
		
		type  	= new ABType();
			ABType 	tmp1  	= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_rec)) {
				AST __t60 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_rec);
				_t = _t.getFirstChild();
				
					pushScope("STRUCT");
				
				tmp1=listrecord(_t,false);
				_t = _retTree;
				
					type = new STRUCT (tmp1);
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
					popScope();
				
				_t = __t60;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_struct)) {
				AST __t61 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_struct);
				_t = _t.getFirstChild();
				
					pushScope("STRUCT");
				
				tmp1=listrecord(_t,true);
				_t = _retTree;
				
					type = new STRUCT (tmp1);
				
					type.setLineNumber(t2.getLineNum());				// On reaffecte le numero de ligne
					t2.setBType(type);
				
					popScope();
				
				_t = __t61;
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
		return type;
	}
	
	public final ABType  listrecord(AST _t,
		boolean bb
	) throws RecognitionException {
		ABType type;
		
		MyNode listrecord_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode aa = null;
		
			type = new ABType();
			ABType tmp1, tmp2;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t69 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp1=listrecord(_t,bb);
				_t = _retTree;
				tmp2=listrecord(_t,bb);
				_t = _retTree;
				
					type = new LIST(tmp1, tmp2); 
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
				_t = __t69;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_2.member(_t.getType()))) {
				aa = _t==ASTNULL ? null : (MyNode)_t;
				type=a_record(_t,bb);
				_t = _retTree;
				
					type.setLineNumber(aa.getLineNum());				// On reaffecte le numero de ligne
					aa.setBType(type);
				
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
		return type;
	}
	
	public final ABType  list_couple(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode list_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode cc = null;
		
			      	type = new ABType();
			 ABType list = new ABType();
			 ABType temp = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t71 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				list=list_couple(_t,set);
				_t = _retTree;
				temp=list_couple(_t,set);
				_t = _retTree;
				
					type = new LIST(list, temp); 
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
				_t = __t71;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_3.member(_t.getType()))) {
				cc = _t==ASTNULL ? null : (MyNode)_t;
				type=couple(_t,set);
				_t = _retTree;
				
					type.setLineNumber(cc.getLineNum());				// On reaffecte le numero de ligne
					cc.setBType(type);
				
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
		return type;
	}
	
	public final ABType  a_set_value(AST _t,
		AST mset
	) throws RecognitionException {
		ABType type;
		
		MyNode a_set_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode t1 = null;
		MyNode val = null;
		MyNode val1 = null;
		MyNode tr = null;
		MyNode fa = null;
		
			type = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_IDENTIFIER:
			{
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					type= new ENUM(mset.toString());
					addId(name,type);
					type.setLineNumber(name.getLineNum());				// On reaffecte le numero de ligne
					name.setBType(type);
				
				break;
			}
			case UNARY_MINUS:
			{
				AST __t80 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				val = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					type = new INT();
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
					val.setBType(type);
				
				_t = __t80;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				val1 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					type = new NAT();
					type.setLineNumber(val1.getLineNum());				// On reaffecte le numero de ligne
					val1.setBType(type);
					// Inutile de les mettres dans la table des symboles ...
				
				break;
			}
			case LITERAL_TRUE:
			{
				tr = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(tr.getLineNum());				// On reaffecte le numero de ligne
					tr.setBType(type);
				
				break;
			}
			case LITERAL_FALSE:
			{
				fa = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(fa.getLineNum());				// On reaffecte le numero de ligne
					fa.setBType(type);
				
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
		return type;
	}
	
/**
 * Les Types de base
 **/
	public final ABType  basic_sets(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode basic_sets_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			type = new ABType();
		
		
		try {      // for error handling
			{
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case LITERAL_INT:
			{
				MyNode tmp32_AST_in = (MyNode)_t;
				match(_t,LITERAL_INT);
				_t = _t.getNextSibling();
				
					type = new INT();
				
				break;
			}
			case 87:
			{
				MyNode tmp33_AST_in = (MyNode)_t;
				match(_t,87);
				_t = _t.getNextSibling();
				
					type = new INT1();
				
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp34_AST_in = (MyNode)_t;
				match(_t,LITERAL_INTEGER);
				_t = _t.getNextSibling();
				
					type = new INTEGER(); 
				
				break;
			}
			case 89:
			{
				MyNode tmp35_AST_in = (MyNode)_t;
				match(_t,89);
				_t = _t.getNextSibling();
				
					type = new INTEGER1();
				
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp36_AST_in = (MyNode)_t;
				match(_t,LITERAL_BOOL);
				_t = _t.getNextSibling();
				
					type = new BOOL();    
				
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp37_AST_in = (MyNode)_t;
				match(_t,LITERAL_NAT);
				_t = _t.getNextSibling();
				
					type = new NAT();     
				
				break;
			}
			case 92:
			{
				MyNode tmp38_AST_in = (MyNode)_t;
				match(_t,92);
				_t = _t.getNextSibling();
				
					type = new NAT1();    
				
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp39_AST_in = (MyNode)_t;
				match(_t,LITERAL_NATURAL);
				_t = _t.getNextSibling();
				
					type = new NATURAL(); 
				
				break;
			}
			case 94:
			{
				MyNode tmp40_AST_in = (MyNode)_t;
				match(_t,94);
				_t = _t.getNextSibling();
				
					type = new NATURAL1();
				
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp41_AST_in = (MyNode)_t;
				match(_t,LITERAL_STRING);
				_t = _t.getNextSibling();
				
					type = new STRING();
				
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
		return type;
	}
	
/**
 *	La regle "a_record" permet de definir le type de chaque champs
 **/
	public final ABType  a_record(AST _t,
		boolean b
	) throws RecognitionException {
		ABType type;
		
		MyNode a_record_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode name = null;
		
			type= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SELECTOR)) {
				AST __t84 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SELECTOR);
				_t = _t.getFirstChild();
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				type=predicate(_t);
				_t = _retTree;
				
					if (b == true) 						// New ID  pas de branche ELSE
						if (typant == true)				// Il y a des phases non typantes  ex: INIT
							addId(name,type);
				
					type.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
					tt.setBType(type);
				
				_t = __t84;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				type=predicate(_t);
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
		return type;
	}
	
	public final ABType  couple(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode xx = null;
		MyNode tt = null;
		MyNode aa = null;
		type = new ABType();
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_MAPLET:
			{
				xx = _t==ASTNULL ? null : (MyNode)_t;
				type=a_maplet(_t,set);
				_t = _retTree;
				
					type.setLineNumber(xx.getLineNum());				// On reaffecte le numero de ligne
					xx.setBType(type);
				
				break;
			}
			case B_LPAREN:
			{
				AST __t73 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				type=parent_couple(_t,set);
				_t = _retTree;
				
					type.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
					tt.setBType(type);
				
				_t = __t73;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			case UNARY_MINUS:
			{
				aa = _t==ASTNULL ? null : (MyNode)_t;
				type=a_set_value(_t,set);
				_t = _retTree;
				
					type.setLineNumber(aa.getLineNum());				// On reaffecte le numero de ligne
					aa.setBType(type);
				
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
		return type;
	}
	
	public final ABType  a_maplet(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode a_maplet_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			type = new ABType();
			ABType t1, t2;
		
		
		try {      // for error handling
			AST __t78 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_MAPLET);
			_t = _t.getFirstChild();
			t1=a_set_value_(_t);
			_t = _retTree;
			t2=a_set_value_(_t);
			_t = _retTree;
			
				type = new PROD(t1,t2); 
				type.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
				tt.setBType(type);
			
			_t = __t78;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final ABType  parent_couple(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode parent_couple_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode aa = null;
		MyNode bb = null;
		
			type = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_MAPLET)) {
				aa = _t==ASTNULL ? null : (MyNode)_t;
				type=a_maplet(_t,set);
				_t = _retTree;
				
					type.setLineNumber(aa.getLineNum());				// On reaffecte le numero de ligne
					aa.setBType(type);
				
			}
			else if ((_t.getType()==B_COMMA)) {
				bb = _t==ASTNULL ? null : (MyNode)_t;
				type=a_comma(_t,set);
				_t = _retTree;
				
					type.setLineNumber(bb.getLineNum());				// On reaffecte le numero de ligne
					bb.setBType(type);
				
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
		return type;
	}
	
	public final ABType  a_comma(AST _t,
		AST set
	) throws RecognitionException {
		ABType type;
		
		MyNode a_comma_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
			type = new ABType();
			ABType t1, t2;
		
		
		try {      // for error handling
			AST __t76 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_COMMA);
			_t = _t.getFirstChild();
			t1=a_set_value_(_t);
			_t = _retTree;
			t2=a_set_value_(_t);
			_t = _retTree;
			
				type = new PROD(t1,t2); 
				type.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
				tt.setBType(type);
			
			_t = __t76;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final ABType  a_set_value_(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode a_set_value__AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode t1 = null;
		MyNode val = null;
		MyNode val1 = null;
		MyNode tr = null;
		MyNode fa = null;
		
			type = new ENUM();						// On est dans la famille des types ENUMERE
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_IDENTIFIER:
			{
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					type.setLineNumber(name.getLineNum());				// On reaffecte le numero de ligne
					addId(name,type);
					name.setBType(type);
				
				break;
			}
			case UNARY_MINUS:
			{
				AST __t82 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,UNARY_MINUS);
				_t = _t.getFirstChild();
				val = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
					type = new INT();
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
				
					t1.setBType(type);
					val.setBType(type);
				
				_t = __t82;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				val1 = (MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getNextSibling();
				
				// on a un NUMBER => un NAT
					type = new NAT();
					type.setLineNumber(val1.getLineNum());				// On reaffecte le numero de ligne
				
					val1.setBType(type);
					// Inutile de les mettres dans la table des symboles ...
				
				break;
			}
			case LITERAL_TRUE:
			{
				tr = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(tr.getLineNum());				// On reaffecte le numero de ligne
				
					tr.setBType(type);
				
				break;
			}
			case LITERAL_FALSE:
			{
				fa = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(fa.getLineNum());				// On reaffecte le numero de ligne
				
					fa.setBType(type);
				
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
		return type;
	}
	
	public final void list_valuation(AST _t) throws RecognitionException {
		
		MyNode list_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t88 = _t;
				MyNode tmp42_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_valuation(_t);
				_t = _retTree;
				list_valuation(_t);
				_t = _retTree;
				_t = __t88;
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
		MyNode aa = null;
		MyNode name = null;
		
		try {      // for error handling
			AST __t90 = _t;
			aa = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				ABType newType= new ABType();
			
			newType=new_set_or_constant_valuation(_t,name);
			_t = _retTree;
			
				addTypeToId(name,newType);
				newType.setLineNumber(aa.getLineNum());				// On reaffecte le numero de ligne
				aa.setBType(newType);
			
			_t = __t90;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final ABType  new_set_or_constant_valuation(AST _t,
		AST name
	) throws RecognitionException {
		ABType newType;
		
		MyNode new_set_or_constant_valuation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			newType = new ABType();
		
		
		try {      // for error handling
			newType=predicate(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return newType;
	}
	
	public final void set_interval_value(AST _t) throws RecognitionException {
		
		MyNode set_interval_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode a = null;
		
		try {      // for error handling
			AST __t93 = _t;
			tt = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			a = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				ABType newType = new ABType();
				pushScope(a.getText());
			
			newType=interval_declaration(_t);
			_t = _retTree;
			
				newType.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
				tt.setBType(newType);
				addId(a,newType);
				popScope();
			
			_t = __t93;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final ABType  interval_declaration(AST _t) throws RecognitionException {
		ABType newType;
		
		MyNode interval_declaration_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
			newType = new ABType();
		
		
		try {      // for error handling
			AST __t95 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_RANGE);
			_t = _t.getFirstChild();
			
				ABType newType1= new ABType();
				ABType newType2= new ABType();
			
			newType1=predicate(_t);
			_t = _retTree;
			newType2=predicate(_t);
			_t = _retTree;
			
				newType = new RANGE(newType1,newType2);
				t1.setBType(newType);
			
			_t = __t95;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return newType;
	}
	
	public final void list_def(AST _t) throws RecognitionException {
		
		MyNode list_def_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LIST_DEF)) {
				AST __t110 = _t;
				MyNode tmp43_AST_in = (MyNode)_t;
				match(_t,LIST_DEF);
				_t = _t.getFirstChild();
				list_def(_t);
				_t = _retTree;
				list_def(_t);
				_t = _retTree;
				_t = __t110;
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
 * Attention deux cas
 *	- Une definition
 *	- un acces a un fichier de definition
 **/
	public final void definition(AST _t) throws RecognitionException {
		
		MyNode definition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		MyNode ft = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_DOUBLE_EQUAL)) {
				AST __t112 = _t;
				MyNode tmp44_AST_in = (MyNode)_t;
				match(_t,B_DOUBLE_EQUAL);
				_t = _t.getFirstChild();
				name = _t==ASTNULL ? null : (MyNode)_t;
				paramName(_t);
				_t = _retTree;
				ft = _t==ASTNULL ? null : (MyNode)_t;
				formalText(_t);
				_t = _retTree;
				
					popScope(); 						// Celui la c'est celui associe au paramName 
				
				// Pas de type de retours pour les definitions .....
				
				_t = __t112;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_ASTRING)) {
				MyNode tmp45_AST_in = (MyNode)_t;
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
	
/**
 **	A FAIRE
 **	On se trouve dans le corps d'une DEFINITION,
 **	il va falloir introduire l'inhibition des messages d'erreurs 
 **	ou les transforme en WARNING
 **/
	public final void formalText(AST _t) throws RecognitionException {
		
		MyNode formalText_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==EXP_DEF)) {
				
					ABType type= new ABType();
				
				AST __t114 = _t;
				MyNode tmp46_AST_in = (MyNode)_t;
				match(_t,EXP_DEF);
				_t = _t.getFirstChild();
				type=predicate(_t);
				_t = _retTree;
				_t = __t114;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==SUBST_DEF)) {
				AST __t115 = _t;
				MyNode tmp47_AST_in = (MyNode)_t;
				match(_t,SUBST_DEF);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				_t = __t115;
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
 * the Generalised Substitution Language
 **/
	public final void instruction(AST _t) throws RecognitionException {
		
		MyNode instruction_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode sany = null;
		MyNode lv1 = null;
		MyNode let = null;
		MyNode lv2 = null;
		MyNode var = null;
		MyNode lv3 = null;
		
			ABType newType	= new ABType();
			ABType type	    = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PARALLEL:
			{
				AST __t133 = _t;
				MyNode tmp48_AST_in = (MyNode)_t;
				match(_t,PARALLEL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t133;
				_t = _t.getNextSibling();
				break;
			}
			case SEQUENTIAL:
			{
				AST __t134 = _t;
				MyNode tmp49_AST_in = (MyNode)_t;
				match(_t,SEQUENTIAL);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t134;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_skip:
			{
				MyNode tmp50_AST_in = (MyNode)_t;
				match(_t,LITERAL_skip);
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_BEGIN:
			{
				AST __t135 = _t;
				MyNode tmp51_AST_in = (MyNode)_t;
				match(_t,LITERAL_BEGIN);
				_t = _t.getFirstChild();
				instruction(_t);
				_t = _retTree;
				_t = __t135;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PRE:
			{
				AST __t136 = _t;
				MyNode tmp52_AST_in = (MyNode)_t;
				match(_t,LITERAL_PRE);
				_t = _t.getFirstChild();
				System.out.println("Debut de Pre");
				newType=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				System.out.println("Fin de Pre");
				_t = __t136;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ASSERT:
			{
				AST __t137 = _t;
				MyNode tmp53_AST_in = (MyNode)_t;
				match(_t,LITERAL_ASSERT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t137;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_IF:
			{
				AST __t138 = _t;
				MyNode tmp54_AST_in = (MyNode)_t;
				match(_t,LITERAL_IF);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				branche_then(_t);
				_t = _retTree;
				{
				_loop140:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_ELSIF)) {
						branche_elsif(_t);
						_t = _retTree;
					}
					else {
						break _loop140;
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
				_t = __t138;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CHOICE:
			{
				AST __t142 = _t;
				MyNode tmp55_AST_in = (MyNode)_t;
				match(_t,LITERAL_CHOICE);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				_t = __t142;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_CASE:
			{
				AST __t143 = _t;
				MyNode tmp56_AST_in = (MyNode)_t;
				match(_t,LITERAL_CASE);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				branche_either(_t);
				_t = _retTree;
				{
				_loop145:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_OR)) {
						branche_or(_t);
						_t = _retTree;
					}
					else {
						break _loop145;
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
				_t = __t143;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_ANY:
			{
				AST __t147 = _t;
				sany = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_ANY);
				_t = _t.getFirstChild();
				
					pushScope("ANY");
				
				lv1 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					local_variable = lv1;	 	// Memorize the list of local VARIABLE for verification of typing
				
				newType=predicate(_t);
				_t = _retTree;
				
					type		= TypedDeclaratedVariable(local_variable);
					local_variable 	= null ;
					type.setLineNumber(sany.getLineNum());				// On reaffecte le numero de ligne
					sany.setBType(type);
				
				instruction(_t);
				_t = _retTree;
				
					popScope();
				
				_t = __t147;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_LET:
			{
				AST __t148 = _t;
				let = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_LET);
				_t = _t.getFirstChild();
				
					pushScope("LET");
				
				lv2 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					local_variable = lv2;	 	// Memorize the list of local VARIABLE for verification of typing
				
				list_equal(_t);
				_t = _retTree;
				
					type		= TypedDeclaratedVariable(local_variable);
					local_variable 	= null ;
					type.setLineNumber(let.getLineNum());				// On reaffecte le numero de ligne
					let.setBType(type);
				
				instruction(_t);
				_t = _retTree;
				
					popScope();
				
				_t = __t148;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SELECT:
			{
				AST __t149 = _t;
				MyNode tmp57_AST_in = (MyNode)_t;
				match(_t,LITERAL_SELECT);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				{
				_loop151:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==LITERAL_WHEN)) {
						branche_when(_t);
						_t = _retTree;
					}
					else {
						break _loop151;
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
				_t = __t149;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_WHILE:
			{
				AST __t153 = _t;
				MyNode tmp58_AST_in = (MyNode)_t;
				match(_t,LITERAL_WHILE);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				variant_or_no(_t);
				_t = _retTree;
				_t = __t153;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_VAR:
			{
				AST __t154 = _t;
				var = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_VAR);
				_t = _t.getFirstChild();
				
					pushScope("VAR");
				
				lv3 = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					local_variable = lv3;
					int ln = var.getLineNum();
				
				instruction(_t);
				_t = _retTree;
				
					type		= TypedDeclaratedVariable(local_variable);
					local_variable 	= null ;
					type.setLineNumber(ln);				// On reaffecte le numero de ligne
					var.setBType(type);
				
					popScope();
				
				_t = __t154;
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
	
	public final void list_assertions(AST _t) throws RecognitionException {
		
		MyNode list_assertions_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_SEMICOLON)) {
				AST __t119 = _t;
				MyNode tmp59_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				list_assertions(_t);
				_t = _retTree;
				list_assertions(_t);
				_t = _retTree;
				_t = __t119;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_0.member(_t.getType()))) {
				
					ABType type= new ABType();
				
				type=predicate(_t);
				_t = _retTree;
				
					// Ici il faut verifier ce qu'on fait avec le type
					// Normalement RIEN juste verifier qu'il n'y a pas d'erreur
				
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
				AST __t125 = _t;
				MyNode tmp60_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				listOperation(_t);
				_t = _retTree;
				listOperation(_t);
				_t = _retTree;
				_t = __t125;
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
		
		try {      // for error handling
			AST __t127 = _t;
			MyNode tmp61_AST_in = (MyNode)_t;
			match(_t,OP_DEF);
			_t = _t.getFirstChild();
			def_operation(_t);
			_t = _retTree;
			_t = __t127;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void def_operation(AST _t) throws RecognitionException {
		
		MyNode def_operation_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode ll = null;
		
			ABType type = null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_OUT)) {
				{
				AST __t130 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
					// mise en place du context
					pushScope(tt.getOpname());
				
				ll = _t==ASTNULL ? null : (MyNode)_t;
				listTypedIdentifier(_t);
				_t = _retTree;
				
					param_out = ll;
					popScope();
				
				paramNameOP(_t);
				_t = _retTree;
				_t = __t130;
				_t = _t.getNextSibling();
				instruction(_t);
				_t = _retTree;
				
					// Verification du typage de tous les parametres de sortie de l'operation
					type 		= TypedDeclaratedVariable(param_out);
					param_out 	= null ;
				
					// Verification du typage de tous les parametres d'entree de l'operation
					type 		= TypedDeclaratedVariable(param_in);
					param_in 	= null ;
				
				popScope();
				
				}
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_LPAREN)) {
				{
				paramNameOP(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				
					// Verification du typage de tous les parametres d'entree de l'operation
					type 		= TypedDeclaratedVariable(param_in);
					param_in 	= null ;
				
				popScope();
				
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
	
	public final void branche_then(AST _t) throws RecognitionException {
		
		MyNode branche_then_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t164 = _t;
			MyNode tmp62_AST_in = (MyNode)_t;
			match(_t,LITERAL_THEN);
			_t = _t.getFirstChild();
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
	
	public final void branche_elsif(AST _t) throws RecognitionException {
		
		MyNode branche_elsif_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		
		try {      // for error handling
			AST __t168 = _t;
			MyNode tmp63_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSIF);
			_t = _t.getFirstChild();
			
				ABType type= new ABType();
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
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
	
	public final void branche_else(AST _t) throws RecognitionException {
		
		MyNode branche_else_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			AST __t166 = _t;
			MyNode tmp64_AST_in = (MyNode)_t;
			match(_t,LITERAL_ELSE);
			_t = _t.getFirstChild();
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
	
	public final void list_or(AST _t) throws RecognitionException {
		
		MyNode list_or_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_OR)) {
				AST __t156 = _t;
				MyNode tmp65_AST_in = (MyNode)_t;
				match(_t,LITERAL_OR);
				_t = _t.getFirstChild();
				list_or(_t);
				_t = _retTree;
				instruction(_t);
				_t = _retTree;
				_t = __t156;
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
		MyNode tt = null;
		
		try {      // for error handling
			AST __t160 = _t;
			MyNode tmp66_AST_in = (MyNode)_t;
			match(_t,LITERAL_EITHER);
			_t = _t.getFirstChild();
			
				ABType type= new ABType();
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
			instruction(_t);
			_t = _retTree;
			_t = __t160;
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
			AST __t162 = _t;
			MyNode tmp67_AST_in = (MyNode)_t;
			match(_t,LITERAL_OR);
			_t = _t.getFirstChild();
			
				ABType type= new ABType();
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
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
	
	public final void list_equal(AST _t) throws RecognitionException {
		
		MyNode list_equal_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_AND)) {
				AST __t175 = _t;
				MyNode tmp68_AST_in = (MyNode)_t;
				match(_t,B_AND);
				_t = _t.getFirstChild();
				list_equal(_t);
				_t = _retTree;
				list_equal(_t);
				_t = _retTree;
				_t = __t175;
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
			AST __t158 = _t;
			MyNode tmp69_AST_in = (MyNode)_t;
			match(_t,LITERAL_WHEN);
			_t = _t.getFirstChild();
			
				ABType type= new ABType();
			
			tt = _t==ASTNULL ? null : (MyNode)_t;
			type=predicate(_t);
			_t = _retTree;
			
				typeControleTreatement (tt, type);
			
			instruction(_t);
			_t = _retTree;
			_t = __t158;
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
		MyNode t2 = null;
		MyNode t1 = null;
		MyNode t4 = null;
		MyNode t3 = null;
		
			ABType type= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==LITERAL_VARIANT)) {
				AST __t170 = _t;
				MyNode tmp70_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				AST __t171 = _t;
				MyNode tmp71_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				t2 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					typeControleTreatement (t2, type);
				
				_t = __t171;
				_t = _t.getNextSibling();
				t1 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					typeControleTreatement (t1, type);
				
				_t = __t170;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==LITERAL_INVARIANT)) {
				AST __t172 = _t;
				MyNode tmp72_AST_in = (MyNode)_t;
				match(_t,LITERAL_INVARIANT);
				_t = _t.getFirstChild();
				AST __t173 = _t;
				MyNode tmp73_AST_in = (MyNode)_t;
				match(_t,LITERAL_VARIANT);
				_t = _t.getFirstChild();
				t4 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					typeControleTreatement (t4, type);
				
				_t = __t173;
				_t = _t.getNextSibling();
				t3 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					typeControleTreatement (t3, type);
				
				_t = __t172;
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
		MyNode sa1 = null;
		MyNode lf1 = null;
		MyNode lp1 = null;
		MyNode sa2 = null;
		MyNode lf2 = null;
		MyNode fc2 = null;
		MyNode si = null;
		MyNode lf3 = null;
		MyNode sa4 = null;
		MyNode lf4 = null;
		
			ABType type	= new ABType();
			ABType tmp1	= new ABType();
			ABType tmp	= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SIMPLESUBST:
			{
				AST __t209 = _t;
				sa1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SIMPLESUBST);
				_t = _t.getFirstChild();
				lf1 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				
					RA1(lf1);
				
				lp1 = _t==ASTNULL ? null : (MyNode)_t;
				tmp=predicate(_t);
				_t = _retTree;
				
				
				// A VERIFIER
				// Il y a un PB car si on a x,y,z := a,b,c
				// il faut scinder les verifications ...........
				
				// Il y a bien le cas ou on complete le type ==> dans les VAR.
				
					type = lp1.getBType();
				
					type.setLineNumber(sa1.getLineNum());		// On reaffecte le numero de ligne
					sa1.setBType(type);
				
					addTypeToId (lf1, type);
				
				
				_t = __t209;
				_t = _t.getNextSibling();
				break;
			}
			case B_OUT:
			{
				AST __t210 = _t;
				sa2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_OUT);
				_t = _t.getFirstChild();
				lf2 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				fc2 = _t==ASTNULL ? null : (MyNode)_t;
				func_call(_t);
				_t = _retTree;
				
					type = fc2.getBType();
					type.setLineNumber(sa2.getLineNum());		// On reaffecte le numero de ligne
					sa2.setBType(type);
				
					addTypeToId (lf2, type);
				
				_t = __t210;
				_t = _t.getNextSibling();
				break;
			}
			case INSET:
			{
				AST __t211 = _t;
				si = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,INSET);
				_t = _t.getFirstChild();
				
					System.out.println("debut => INSET");
				
				lf3 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				
					RA1(lf3);
					local_variable = lf3;	 	// Memorize the list of local VARIABLE for verification of typing
				
				type=predicate(_t);
				_t = _retTree;
				
				
				// BJL : April 2007 
				// In x,y :( P), P is a predicate and is type is BOOL
				// x and y must be defined and typed.
				// PB : normally X and Y must be dedefined in P. How verified this ????
				
				
					tmp1   		= TypedDeclaratedVariable(local_variable);
					local_variable 	= null ;
					tmp 		= new BOOL();
					
					if (typeControle(new EQUAL(type, tmp)))
					{
						type = new BOOL();
						type.setLineNumber(si.getLineNum());		// On reaffecte le numero de ligne
					si.setBType(type);
					 }
					else
					{
				
				// BJL
				// Introduire une incompatibilite de type 
				
					}
				
				
					System.out.println("Fin => INSET");
				
				_t = __t211;
				_t = _t.getNextSibling();
				break;
			}
			case B_BECOME_ELEM:
			{
				AST __t212 = _t;
				sa4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BECOME_ELEM);
				_t = _t.getFirstChild();
				
					System.out.println("debut => BECOME");
				
				lf4 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				
					RA1(lf4);
					local_variable = lf4;	 	// Memorize the list of local VARIABLE for verification of typing
				
				type=predicate(_t);
				_t = _retTree;
				
				
					tmp1	    	= TypedDeclaratedVariable(local_variable);
					local_variable 	= null ;
				
					if (typeControle(new EQUAL(tmp1, type)))
					{
						type = new BOOL();
						type.setLineNumber(sa4.getLineNum());		// On reaffecte le numero de ligne
					sa4.setBType(type);
					}
					else
					{
				
				// BJL
				// Introduire une incompatibilite de type 
				
					}
				
				System.out.println("fin => BECOME");
				
				_t = __t212;
				_t = _t.getNextSibling();
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
		MyNode name = null;
		
		try {      // for error handling
			AST __t177 = _t;
			MyNode tmp74_AST_in = (MyNode)_t;
			match(_t,B_EQUAL);
			_t = _t.getFirstChild();
			name = (MyNode)_t;
			match(_t,B_IDENTIFIER);
			_t = _t.getNextSibling();
			
				ABType type= new ABType();
			
			type=predicate(_t);
			_t = _retTree;
			
				addId(name,type);
			
			_t = __t177;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
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
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode name = null;
		MyNode t4 = null;
		
			ABType type = new ABType();
			ABType tmp1 = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_TILDE:
			{
				AST __t179 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				_t = __t179;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t180 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				type=list_New_Predicate(_t);
				_t = _retTree;
				_t = __t180;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t181 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				name = _t==ASTNULL ? null : (MyNode)_t;
				func_call(_t);
				_t = _retTree;
				tmp1=list_New_Predicate(_t);
				_t = _retTree;
				
					if ((name.toString()).compareTo("POW") == 0)
					{
						type = new POW(tmp1);
					}
					// TAW 6/24/2011
					else if (name.getBType() instanceof INFIXE) {
						if (unwrap(name.getBType().getMember()).toString().equals(tmp1.toString())) {
						type = unwrap(name.getBType().getMember2());
						type.setLineNumber(t3.getLineNum());
						t3.setBType(type);
					} else {
						System.out.println("ERROR: relation of type " + name.getBType() + 
						" can not be applied to argument of type " + tmp1 + ", line " +
						t2.getLineNum()+ ".");
					}
					}
					// END TAW
					else
					{    // Autre cas
					}
				
					type.setLineNumber(t3.getLineNum());				// On reaffecte le numero de ligne
					t3.setBType(type);
				
				_t = __t181;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t182 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				func_call(_t);
				_t = _retTree;
				func_call(_t);
				_t = _retTree;
				_t = __t182;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				
					String newCurrent="";
				
				newCurrent=nameRenamed(_t);
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
	
	public final ABType  func_param(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode func_param_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			type = new ABType();
		
		
		try {      // for error handling
			type=list_New_Predicate(_t);
			_t = _retTree;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
		return type;
	}
	
	public final ABType  new_predicate(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode new_predicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			type = new ABType();
			ABType tmp1 = new ABType();
			ABType tmp2 = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t187 = _t;
				MyNode tmp75_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				tmp1=new_predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t187;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t188 = _t;
				MyNode tmp76_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				tmp1=new_predicate(_t);
				_t = _retTree;
				tmp2=predicate(_t);
				_t = _retTree;
				_t = __t188;
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
				type=predicate(_t);
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
		return type;
	}
	
	public final String  nameRenamedDecorated(AST _t) throws RecognitionException {
		String currentName;
		
		MyNode nameRenamedDecorated_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode name = null;
		
			currentName = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_CPRED)) {
				AST __t193 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CPRED);
				_t = _t.getFirstChild();
				name = _t==ASTNULL ? null : (MyNode)_t;
				currentName=nameRenamed(_t);
				_t = _retTree;
				
					// Etant donne qu'on parle de la valeur precedente d'un ID il faut qu'il soit deja definit
				
					ABType tmp = searchId(name,new ABType());	// On recupere le type 
					tmp.setLineNumber(t1.getLineNum());		// On reaffecte le numero de ligne
					name.setBType(tmp);				// et on le re-affecte
					t1.setBType(tmp);				// et on le re-affecte
				
				_t = __t193;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER||_t.getType()==B_POINT)) {
				currentName=nameRenamed(_t);
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
		return currentName;
	}
	
	public final String  nameRenamedDecoratedInverted(AST _t) throws RecognitionException {
		String currentName;
		
		MyNode nameRenamedDecoratedInverted_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
			currentName = "";
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_TILDE)) {
				AST __t195 = _t;
				MyNode tmp77_AST_in = (MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				currentName=nameRenamedDecorated(_t);
				_t = _retTree;
				_t = __t195;
				_t = _t.getNextSibling();
			}
			else if (((_t.getType() >= B_IDENTIFIER && _t.getType() <= B_CPRED))) {
				currentName=nameRenamedDecorated(_t);
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
		return currentName;
	}
	
	public final void listPredicate(AST _t) throws RecognitionException {
		
		MyNode listPredicate_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode lp1 = null;
		MyNode lp2 = null;
		MyNode t2 = null;
		MyNode lp3 = null;
		MyNode lp4 = null;
		MyNode pe = null;
		
			ABType type = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_COMMA:
			{
				AST __t197 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				lp1 = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				lp2 = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				
					type = new PROD(lp1.getBType(),lp2.getBType());
					type.setLineNumber(tt.getLineNum());
					tt.setBType(type);
				
				_t = __t197;
				_t = _t.getNextSibling();
				break;
			}
			case ELEM_SET:
			{
				AST __t198 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				lp3 = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				lp4 = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				
					type = new PROD(lp3.getBType(),lp4.getBType());
					type.setLineNumber(t2.getLineNum());
					t2.setBType(type);
				
				_t = __t198;
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
				pe = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					type.setLineNumber(pe.getLineNum());
					pe.setBType(type);
				
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
	
	public final void a_func_call(AST _t) throws RecognitionException {
		
		MyNode a_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode a1 = null;
		MyNode a2 = null;
		
			MyNode name;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==A_FUNC_CALL)) {
				AST __t200 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,A_FUNC_CALL);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				name=afc(_t);
				_t = _retTree;
				
				
					ABType tmp = a1.getBType();
				
				// March 2003
				// Cette ID existe 
				//    si oui est-ce le bon type 
				//    sinon on le cre avec ce type.
					ABType tmp1= searchId(name, tmp);
				
				// F e1* ... * en --> s1 * .. * sn a pour type e1* ...* en * s1* ... *sn
				// et on a F( p1, ... ,pm) a pour type P1* ...*Pn et le resultat est un sous interval du type precedent
				
				// PB afec les F(xx,yy) qui retourne des couples, triplets ou autre ...
				
					tmp.setLineNumber(a1.getLineNum());					// On reaffecte le numero de ligne
					tt.setBType(tmp);
				
				//    #tt.setText(#a1.getText()); 
				tt.memorizeOpname(a1.getOpname());
				
				
				_t = __t200;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_5.member(_t.getType()))) {
				{
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				name=afc(_t);
				_t = _retTree;
				
				
					ABType tmp = a2.getBType();
				
				// March 2003
				// Cette ID existe 
				//    si oui est-ce le bon type 
				//    sinon on le cre avec ce type.
					ABType tmp1= searchId(name, tmp);
				
				// F e1* ... * en --> s1 * .. * sn a pour type e1* ...* en * s1* ... *sn
				// et on a F( p1, ... ,pm) a pour type P1* ...*Pn et le resultat est un sous interval du type precedent
				
				// PB afec les F(xx,yy) qui retourne des couples, triplets ou autre ...
				
					tmp.setLineNumber(a2.getLineNum());					// On reaffecte le numero de ligne
					a2.setBType(tmp);
				
				//    #tt.setText(#a1.getText()); 
				a2.memorizeOpname(a2.getOpname());
				
				
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
	
	public final MyNode  afc(AST _t) throws RecognitionException {
		MyNode cNode;
		
		MyNode afc_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode a1 = null;
		MyNode lp = null;
		MyNode t2 = null;
		MyNode a2 = null;
		MyNode lp2 = null;
		MyNode t3 = null;
		MyNode a3 = null;
		MyNode name = null;
		
			String currentName = "";
			MyNode tmp;
			cNode = new MyNode();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case FUNC_CALL_PARAM:
			{
				AST __t203 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,FUNC_CALL_PARAM);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				cNode=afc(_t);
				_t = _retTree;
				lp = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				
				ABType tmp1 = a1.getBType();					        // on a un f(x)...(y)
					ABType tmp2 = lp.getBType();					        // on ajoute un (zz)
				
					ABType tmptype  = new PROD(tmp1,tmp2);				// c'est pourquoi on fait le product
					tmptype.setLineNumber(t1.getLineNum());			// On reaffecte le numero de ligne
					t1.setBType(tmptype);
				
				//    #t1.setText(#a1.getText());
				
				t1.memorizeOpname(a1.getOpname());
				
				_t = __t203;
				_t = _t.getNextSibling();
				break;
			}
			case B_LPAREN:
			{
				AST __t204 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				cNode=afc(_t);
				_t = _retTree;
				lp2 = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				
				ABType tmp1 = a2.getBType();					        // on a un f(x)...(y)
					ABType tmp2 = lp2.getBType();					    // on ajoute un (zz)
				
					ABType tmptype  = new PROD(tmp1,tmp2);				// c'est pourquoi on fait le product
					tmptype.setLineNumber(t2.getLineNum());			// On reaffecte le numero de ligne
				
					t2.setBType(tmptype);
				
				//    #t2.setText(#a2.getText());
				
				t2.memorizeOpname(a2.getOpname());
				
				_t = __t204;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t205 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				a3 = _t==ASTNULL ? null : (MyNode)_t;
				cNode=afc(_t);
				_t = _retTree;
				tmp=afc(_t);
				_t = _retTree;
				
				//   #t3.setText(#a3.getText());
				
				t3.memorizeOpname(a3.getOpname());
				
				_t = __t205;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				name = _t==ASTNULL ? null : (MyNode)_t;
				currentName=nameRenamed(_t);
				_t = _retTree;
				
					cNode = name;
				
					ABType tmptype = searchId(name,new ABType());			// On recupere le type 
					tmptype.setLineNumber(name.getLineNum());			// On reaffecte le numero de ligne
					name.setBType(tmptype);
				
				name.memorizeOpname(name.getText());
				
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
		return cNode;
	}
	
	public final void list_func_call(AST _t) throws RecognitionException {
		
		MyNode list_func_call_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode lf1 = null;
		MyNode lf2 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t207 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				lf1 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				lf2 = _t==ASTNULL ? null : (MyNode)_t;
				list_func_call(_t);
				_t = _retTree;
				
					// On construit le produit cartesien ...
					ABType type = new PROD(lf1.getBType(),lf2.getBType());
					type.setLineNumber(tt.getLineNum());
				
					tt.setBType(type);
				
				_t = __t207;
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
	
	public final ABType  cset_description(AST _t) throws RecognitionException {
		ABType theType;
		
		MyNode cset_description_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode bb = null;
		MyNode t4 = null;
		MyNode ll = null;
		MyNode t5 = null;
		MyNode t6 = null;
		MyNode t7 = null;
		MyNode q12 = null;
		MyNode t8 = null;
		
			theType 		= new ABType();
			ABType newType  	= new ABType();
			ABType newType1 	= new ABType();
		
		
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
				theType=basic_sets(_t);
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
				theType=cbasic_value(_t);
				_t = _retTree;
				break;
			}
			case LITERAL_bool:
			{
				AST __t273 = _t;
				bb = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_bool);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				
					// Dans un predicat de la forme bool( P ), ....
				
					theType	= new EQUAL( new BOOL(),newType);
					theType.reduce(errors);
					theType.setLineNumber(bb.getLineNum());				// On reaffecte le numero de ligne
					bb.setBType(theType);
				
				_t = __t273;
				_t = _t.getNextSibling();
				break;
			}
			case B_BRACKOPEN:
			{
				AST __t274 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_BRACKOPEN);
				_t = _t.getFirstChild();
				ll = _t==ASTNULL ? null : (MyNode)_t;
				listPredicate(_t);
				_t = _retTree;
				
					theType = ll.getBType();
					theType.setLineNumber(t4.getLineNum());				// On reaffecte le numero de ligne
					t4.setBType(theType);
				
				_t = __t274;
				_t = _t.getNextSibling();
				break;
			}
			case B_RANGE:
			{
				AST __t275 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_RANGE);
				_t = _t.getFirstChild();
				newType=predicate(_t);
				_t = _retTree;
				newType1=predicate(_t);
				_t = _retTree;
				
					theType = new RANGE(newType, newType1);
					theType.setLineNumber(t5.getLineNum());				// On reaffecte le numero de ligne
					t5.setBType(theType);
				
				_t = __t275;
				_t = _t.getNextSibling();
				break;
			}
			case B_EMPTYSET:
			{
				AST __t276 = _t;
				t6 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EMPTYSET);
				_t = _t.getFirstChild();
				
					theType = new JOKER();
					theType.setLineNumber(t6.getLineNum());				// On reaffecte le numero de ligne
					t6.setBType(theType);
				
				_t = __t276;
				_t = _t.getNextSibling();
				break;
			}
			case B_CURLYOPEN:
			{
				AST __t277 = _t;
				t7 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_CURLYOPEN);
				_t = _t.getFirstChild();
				q12 = _t==ASTNULL ? null : (MyNode)_t;
				newType=cvalue_set(_t);
				_t = _retTree;
				
				System.out.println("CURLYOPEN : t1="+q12.getBType().toString()+" nt="+newType.toString());
				
				//	theType = new SET(newType.reduce(errors));  
					theType = newType.reduce(errors);
					theType.setLineNumber(t7.getLineNum());				// On reaffecte le numero de ligne
					t7.setBType(theType);
				
				_t = __t277;
				_t = _t.getNextSibling();
				break;
			}
			case B_SEQEMPTY:
			{
				AST __t278 = _t;
				t8 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SEQEMPTY);
				_t = _t.getFirstChild();
				
					theType = new EMPTYSEQ();
					theType.setLineNumber(t8.getLineNum());				// On reaffecte le numero de ligne
					t8.setBType(theType);
				
				_t = __t278;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				theType=is_record(_t);
				_t = _retTree;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				theType=quantification(_t);
				_t = _retTree;
				break;
			}
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			{
				theType=q_lambda(_t);
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
		return theType;
	}
	
	public final ABType  cbasic_value(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode cbasic_value_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode as = null;
		MyNode nb = null;
		MyNode t1 = null;
		MyNode t2 = null;
		MyNode t3 = null;
		MyNode ll = null;
		MyNode t4 = null;
		MyNode tt = null;
		MyNode ff = null;
		
			     type 		= new ABType();
			ABType newType1	= new ABType();
			ABType newType2	= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_ASTRING:
			{
				AST __t284 = _t;
				as = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_ASTRING);
				_t = _t.getFirstChild();
				
					type = new STRING();
					type.setLineNumber(as.getLineNum());				// On reaffecte le numero de ligne
					as.setBType(type);
				
				_t = __t284;
				_t = _t.getNextSibling();
				break;
			}
			case B_NUMBER:
			{
				AST __t285 = _t;
				nb = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_NUMBER);
				_t = _t.getFirstChild();
				
					type = new NAT();
					type.setLineNumber(nb.getLineNum());				// On reaffecte le numero de ligne
					nb.setBType(type);
				
				_t = __t285;
				_t = _t.getNextSibling();
				break;
			}
			case B_TILDE:
			{
				AST __t286 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_TILDE);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				
					type = newType1.inverse(errors);				// On inverse le type ....
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);	
				
				_t = __t286;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			case B_CPRED:
			{
				
					String cc="";
				
				t2 = _t==ASTNULL ? null : (MyNode)_t;
				cc=nameRenamedDecorated(_t);
				_t = _retTree;
				
					if ((t2.toString()).compareTo("MAXINT") == 0)
					{
						type = new NAT();					// MAXINT est un INTEGER ....
					}
					else if ((t2.toString()).compareTo("POW") == 0)
					{
						type = new ABType();					// on a pas besoin de typer ....
					}
					else
					{    // Autre cas
				// UN ID
				
				System.out.println("On cherche cc= "+cc+" t2= "+t2.toString());
				
						type = searchId(t2, new ABType());
					}
				System.out.println("Attention on a le type "+ type.toString());
				
					type.setLineNumber(t2.getLineNum());				// On reaffecte le numero de ligne
					t2.setBType(type);
				
				break;
			}
			case B_LPAREN:
			{
				AST __t287 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				ll = _t==ASTNULL ? null : (MyNode)_t;
				newType1=predicate(_t);
				_t = _retTree;
				type=list_New_Predicate(_t);
				_t = _retTree;
				
				
				System.out.println("LPARENT");
				
					String name = ll.toString();
					if (name.compareTo("POW") == 0)
					{
						type = new POW(type);	// No specific typing
					}
					// TAW - 6/18/2011
					else if (name.equalsIgnoreCase("set")) 
					{
						type = new SET(type);
					} else if (name.equalsIgnoreCase("seq")) 
					{
						type = new SEQ(type);
					} else if (newType1 instanceof SEQ && type instanceof INTEGER) 
					{
						type = type.getMember();
					} else if (type instanceof SEQ) 
					{
						if (name.equalsIgnoreCase("size") || name.equalsIgnoreCase("card")) 
						{
							type = new NAT();
						} else if (	name.equalsIgnoreCase("first") 
							   || 	name.equalsIgnoreCase("last")
					     		   || 	name.equalsIgnoreCase("conc")) 
					     	{
						type = type.getMember();
						}
					} else if (type instanceof SET) 
					{
						if (	name.equalsIgnoreCase("size") || name.equalsIgnoreCase("card")) 
						{
							type = new NAT();
						} else if (name.equalsIgnoreCase("union") || name.equalsIgnoreCase("inter")) 
						{
							type = type.getMember();
						}
					} else if (newType1 instanceof INFIXE) {
							if (unwrap(newType1.getMember()).toString().equals(type.toString())) {
								type = unwrap(newType1.getMember2());
								type.setLineNumber(t3.getLineNum());
								t3.setBType(type);
							} else {
								System.out.println("ERROR: relation of type " + newType1 + 
								" can not be applied to argument of type " + type + ", line " +
								t3.getLineNum()+ ".");
							}
					}
				// END TAW
					else 
					{    // Other case
				
					}
				
					type.setLineNumber(t3.getLineNum());				// On reaffecte le numero de ligne
					t3.setBType(type);
				
				_t = __t287;
				_t = _t.getNextSibling();
				break;
			}
			case PARENT:
			{
				AST __t288 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,PARENT);
				_t = _t.getFirstChild();
				
				System.out.println("on a un PARENT");
				
				type=pred_func_composition(_t);
				_t = _retTree;
				
					type.setLineNumber(t4.getLineNum());				// On reaffecte le numero de ligne
					t4.setBType(type);
				
				_t = __t288;
				_t = _t.getNextSibling();
				break;
			}
			case B_QUOTEIDENT:
			{
				AST __t289 = _t;
				MyNode tmp78_AST_in = (MyNode)_t;
				match(_t,B_QUOTEIDENT);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				newType2=predicate(_t);
				_t = _retTree;
				_t = __t289;
				_t = _t.getNextSibling();
				break;
			}
			case APPLY_TO:
			{
				AST __t290 = _t;
				MyNode tmp79_AST_in = (MyNode)_t;
				match(_t,APPLY_TO);
				_t = _t.getFirstChild();
				newType1=predicate(_t);
				_t = _retTree;
				newType2=predicate(_t);
				_t = _retTree;
				_t = __t290;
				_t = _t.getNextSibling();
				
					if (unwrap(newType1.getMember()).toString().equals(newType2.toString())) {
						type = unwrap(newType1.getMember2());
						type.setLineNumber(tmp79_AST_in.getLineNum());
						tmp79_AST_in.setBType(type);
					} else {
						System.out.println("ERROR: relation of type " + newType1 + 
						" can not be applied to argument of type " + newType2 + ", line " +
						tmp79_AST_in.getLineNum()+ ".");
					}
				
				break;
			}
			case LITERAL_TRUE:
			{
				tt = (MyNode)_t;
				match(_t,LITERAL_TRUE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(tt.getLineNum());				// On reaffecte le numero de ligne
					tt.setBType(type);
				
				break;
			}
			case LITERAL_FALSE:
			{
				ff = (MyNode)_t;
				match(_t,LITERAL_FALSE);
				_t = _t.getNextSibling();
				
					type = new BOOL();
					type.setLineNumber(ff.getLineNum());				// On reaffecte le numero de ligne
					ff.setBType(type);
				
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
		return type;
	}
	
	public final ABType  cvalue_set(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode cvalue_set_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode lv = null;
		MyNode t2 = null;
		MyNode t21 = null;
		
				type 	= new ABType();
			ABType 	tmp1	= new ABType();
			ABType 	tmp2	= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SUCH:
			{
				AST __t280 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_SUCH);
				_t = _t.getFirstChild();
				
					pushScope("SUCH");
				
				lv = _t==ASTNULL ? null : (MyNode)_t;
				list_var(_t);
				_t = _retTree;
				tmp1=predicate(_t);
				_t = _retTree;
				
					compatibilityReduceType (t1, tmp1, new BOOL());
				
				// Q:
				//  Peux t on re-evaluer list_var pour verificaton du typage ?? 
				//  si oui faut le faire ....
				
					list_var_bis(lv);
				
					type = lv.getBType();
				
				// Verification des objets non types
					type.typingVerification(errors);
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
					popScope();
				
				
				_t = __t280;
				_t = _t.getNextSibling();
				break;
			}
			case ELEM_SET:
			{
				AST __t281 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,ELEM_SET);
				_t = _t.getFirstChild();
				tmp1=cvalue_set(_t);
				_t = _retTree;
				tmp2=cvalue_set(_t);
				_t = _retTree;
				
				System.out.println("ELEM_SET : t1="+tmp1.toString()+" t2="+tmp2.toString());
				
					type = new LIST(tmp1, tmp2);
					type.setLineNumber(t2.getLineNum());				// On reaffecte le numero de ligne
					t2.setBType(type);
				
				_t = __t281;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t282 = _t;
				t21 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				tmp1=cvalue_set(_t);
				_t = _retTree;
				tmp2=cvalue_set(_t);
				_t = _retTree;
				
				System.out.println("B_COMMA : t1="+tmp1.toString()+" t2="+tmp2.toString());
				
					type = new LIST(tmp1, tmp2);
					type.setLineNumber(t21.getLineNum());				// On reaffecte le numero de ligne
					t21.setBType(type);
				
				_t = __t282;
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
				type=predicate(_t);
				_t = _retTree;
				
				System.out.println("cvalue_set - predicate = "+type.toString());
				
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
		return type;
	}
	
	public final ABType  quantification(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode quantification_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode lv1 = null;
		MyNode p1 = null;
		MyNode t2 = null;
		MyNode lv2 = null;
		MyNode p2 = null;
		
			type= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_FORALL)) {
				AST __t298 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_FORALL);
				_t = _t.getFirstChild();
				
					pushScope("FORALL");
				
				lv1 = _t==ASTNULL ? null : (MyNode)_t;
				list_var(_t);
				_t = _retTree;
				p1 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
					compatibilityReduceType (t1, type, new BOOL());
				
				// Q:
				//  Peux t on re-evaluer list_var pour verificaton du typage ?? 
				//  si oui faut le faire ....
				//  OUI : C'est fait ...
				
					ABType tmpType = TypedDeclaratedVariable(lv1);
				
					type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
					t1.setBType(type);
				
					popScope();
				
				_t = __t298;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_EXISTS)) {
				AST __t299 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_EXISTS);
				_t = _t.getFirstChild();
				
					pushScope("EXISTS"+nb_exist++);
				
				lv2 = _t==ASTNULL ? null : (MyNode)_t;
				list_var(_t);
				_t = _retTree;
				p2 = _t==ASTNULL ? null : (MyNode)_t;
				type=predicate(_t);
				_t = _retTree;
				
				// System.out.println("Typage : EXIST : Begin");
				// System.out.println("predicate = "+ type.toString());
				
					compatibilityReduceType (t2, type, new BOOL());
				
				// Q:
				//  Peux t on re-evaluer list_var pour verificaton du typage ?? 
				//  si oui faut le faire ....
				//  OUI : C'est fait ...
				
					ABType tmpType = TypedDeclaratedVariable(lv2);
					
					type.setLineNumber(t2.getLineNum());				// On reaffecte le numero de ligne
					t2.setBType(type);
				
				// System.out.println("result = "+ #t2.getBType().toString());
				
					popScope();
				
				// System.out.println("Typage : EXIST : End");
				
				_t = __t299;
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
		return type;
	}
	
	public final ABType  q_lambda(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode q_lambda_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode q1 = null;
		MyNode t2 = null;
		MyNode q2 = null;
		MyNode t3 = null;
		MyNode q3 = null;
		MyNode t4 = null;
		MyNode q4 = null;
		MyNode t5 = null;
		MyNode q5 = null;
		
			type= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_LAMBDA:
			{
				AST __t301 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LAMBDA);
				_t = _t.getFirstChild();
				q1 = _t==ASTNULL ? null : (MyNode)_t;
				q_operande(_t);
				_t = _retTree;
				
				System.out.println("debut Lambda");
				
					transfertType(q1,t1);
				type = t1.getBType();
				
				System.out.println("fin Lambda");
				
				
				_t = __t301;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_PI:
			{
				AST __t302 = _t;
				t2 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_PI);
				_t = _t.getFirstChild();
				q2 = _t==ASTNULL ? null : (MyNode)_t;
				q_operande(_t);
				_t = _retTree;
				
					transfertType(q2,t2);
				type = t2.getBType();
				
				_t = __t302;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_SIGMA:
			{
				AST __t303 = _t;
				t3 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_SIGMA);
				_t = _t.getFirstChild();
				q3 = _t==ASTNULL ? null : (MyNode)_t;
				q_operande(_t);
				_t = _retTree;
				
					transfertType(q3,t3);
				type = t3.getBType();
				
				_t = __t303;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_UNION:
			{
				AST __t304 = _t;
				t4 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_UNION);
				_t = _t.getFirstChild();
				q4 = _t==ASTNULL ? null : (MyNode)_t;
				q_operande(_t);
				_t = _retTree;
				
					transfertType(q4,t4);
				type = t4.getBType();
				
				_t = __t304;
				_t = _t.getNextSibling();
				break;
			}
			case LITERAL_INTER:
			{
				AST __t305 = _t;
				t5 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,LITERAL_INTER);
				_t = _t.getFirstChild();
				q5 = _t==ASTNULL ? null : (MyNode)_t;
				q_operande(_t);
				_t = _retTree;
				
					transfertType(q5,t5);
				type = t4.getBType();
				
				_t = __t305;
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
		return type;
	}
	
/**
 * Rien a faire car on a une liste de variable
 * Faut quand meme ajouter les variables a l'environnement .....
 *
 * Prevoire l'extension B' liste_typed_identifier
 */
	public final void list_var(AST _t) throws RecognitionException {
		
		MyNode list_var_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t309 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier(_t);
				_t = _retTree;
				_t = __t309;
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
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final ABType  pred_func_composition(AST _t) throws RecognitionException {
		ABType type;
		
		MyNode pred_func_composition_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		
				type 	= new ABType();
			ABType 	newType	= new ABType();
			ABType 	newType1= new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_SEMICOLON:
			{
				AST __t292 = _t;
				MyNode tmp80_AST_in = (MyNode)_t;
				match(_t,B_SEMICOLON);
				_t = _t.getFirstChild();
				newType=pred_func_composition(_t);
				_t = _retTree;
				newType1=pred_func_composition(_t);
				_t = _retTree;
				_t = __t292;
				_t = _t.getNextSibling();
				break;
			}
			case B_PARALLEL:
			{
				AST __t293 = _t;
				MyNode tmp81_AST_in = (MyNode)_t;
				match(_t,B_PARALLEL);
				_t = _t.getFirstChild();
				newType=pred_func_composition(_t);
				_t = _retTree;
				newType1=pred_func_composition(_t);
				_t = _retTree;
				_t = __t293;
				_t = _t.getNextSibling();
				break;
			}
			case B_COMMA:
			{
				AST __t294 = _t;
				MyNode tmp82_AST_in = (MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				newType=pred_func_composition(_t);
				_t = _retTree;
				newType1=pred_func_composition(_t);
				_t = _retTree;
				_t = __t294;
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
				type=predicate(_t);
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
		return type;
	}
	
	public final void q_operande(AST _t) throws RecognitionException {
		
		MyNode q_operande_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		MyNode lv1 = null;
		
			ABType type  = new ABType();
			ABType newType1 = new ABType();
		
		
		try {      // for error handling
			AST __t307 = _t;
			t1 = _t==ASTNULL ? null :(MyNode)_t;
			match(_t,B_SUCH);
			_t = _t.getFirstChild();
			
				pushScope("SUCH");
			
			lv1 = _t==ASTNULL ? null : (MyNode)_t;
			list_var(_t);
			_t = _retTree;
			type=predicate(_t);
			_t = _retTree;
			
				compatibilityReduceType (t1, type, new BOOL());
			
			// Q:
			//  Peux t on re-evaluer list_var pour verificaton du typage ?? 
			//  si oui faut le faire ....
				list_var_bis(lv1);
			
				type = lv1.getBType();
				type.setLineNumber(t1.getLineNum());				// On reaffecte le numero de ligne
			
			
			newType1=predicate(_t);
			_t = _retTree;
			
				t1.setBType(type);
			
				popScope();
			
			_t = __t307;
			_t = _t.getNextSibling();
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
		MyNode a1 = null;
		MyNode a2 = null;
		MyNode name = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_COMMA)) {
				AST __t311 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				list_identifier(_t);
				_t = _retTree;
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				list_identifier(_t);
				_t = _retTree;
				
					ABType tmp1 = a1.getBType();
					ABType tmp2 = a2.getBType();
					ABType type = new PROD(tmp1, tmp2);
					type.setLineNumber(tt.getLineNum());
					tt.setBType(type);
				
				_t = __t311;
				_t = _t.getNextSibling();
			}
			else if ((_t.getType()==B_IDENTIFIER)) {
				AST __t312 = _t;
				name = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getFirstChild();
				
					ABType tmp = new ABType();
					addId(name,tmp);
					tmp.setLineNumber(name.getLineNum());			// On reaffecte le numero de ligne
					name.setBType(tmp);
				
				_t = __t312;
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
 * Cette seconde version a pour but de recuperer le typage
 * en fait, le premier tour permet de declarer les variables
 * le second de recuperer les informations de typage et de decelees 
 * les variables non typees et non utilisees
 **/
	public final void list_var_bis(AST _t) throws RecognitionException {
		
		MyNode list_var_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode t1 = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_LPAREN)) {
				AST __t314 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_LPAREN);
				_t = _t.getFirstChild();
				list_identifier_bis(_t);
				_t = _retTree;
				_t = __t314;
				_t = _t.getNextSibling();
			}
			else if ((_tokenSet_7.member(_t.getType()))) {
				list_identifier_bis(_t);
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
	
	public final void list_identifier_bis(AST _t) throws RecognitionException {
		
		MyNode list_identifier_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode tt = null;
		MyNode a1 = null;
		MyNode a2 = null;
		MyNode t1 = null;
		MyNode name1 = null;
		MyNode n1 = null;
		MyNode n2 = null;
		
			ABType newType = new ABType();
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case B_COMMA:
			{
				AST __t316 = _t;
				tt = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_COMMA);
				_t = _t.getFirstChild();
				a1 = _t==ASTNULL ? null : (MyNode)_t;
				list_identifier_bis(_t);
				_t = _retTree;
				a2 = _t==ASTNULL ? null : (MyNode)_t;
				list_identifier_bis(_t);
				_t = _retTree;
				
					ABType tmp1 = a1.getBType();
					ABType tmp2 = a2.getBType();
					ABType type = new PROD(tmp1, tmp2);
				
					tt.setBType(type);
				
				_t = __t316;
				_t = _t.getNextSibling();
				break;
			}
			case B_IDENTIFIER:
			case B_POINT:
			{
				id_bis(_t);
				_t = _retTree;
				break;
			}
			case B_INSET:
			{
				AST __t317 = _t;
				t1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,B_INSET);
				_t = _t.getFirstChild();
				name1 = _t==ASTNULL ? null : (MyNode)_t;
				id_bis(_t);
				_t = _retTree;
				newType=predicate(_t);
				_t = _retTree;
				
				
				//  BJL a voir
				//	compatibilityReduceType (#name1, newType, new BOOL());
				
					t1.setBType(name1.getBType());				   // Transfert de type
				
				_t = __t317;
				_t = _t.getNextSibling();
				break;
			}
			case A_FUNC_CALL:
			{
				AST __t318 = _t;
				n1 = _t==ASTNULL ? null :(MyNode)_t;
				match(_t,A_FUNC_CALL);
				_t = _t.getFirstChild();
				n2 = _t==ASTNULL ? null : (MyNode)_t;
				id_bis(_t);
				_t = _retTree;
				
				n1.setBType(n2.getBType());                  // Transfert de type
				
				_t = __t318;
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
	
	public final void id_bis(AST _t) throws RecognitionException {
		
		MyNode id_bis_AST_in = (_t == ASTNULL) ? null : (MyNode)_t;
		MyNode name = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			if ((_t.getType()==B_IDENTIFIER)) {
				name = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				
					ABType tmp = new ABType();
					tmp      = searchId(name,tmp);
				
					if (tmp.IsDefined() != true)
					{
				
						String StrTmp = errors.IdNotDefined(name.getText()+"(line "+name.getLineNum()+")");
						System.err.println(StrTmp);				// Impression a l'ecran de l'erreur
					tmp = new Typing_ERROR(StrTmp);		    		// On a un PB,on construit le type adequat
				
					}
				
					tmp.setLineNumber(name.getLineNum());				// On reaffecte le numero de ligne
					name.setBType(tmp);
				
				
			}
			else if ((_t.getType()==B_POINT)) {
				AST __t320 = _t;
				MyNode tmp83_AST_in = (MyNode)_t;
				match(_t,B_POINT);
				_t = _t.getFirstChild();
				MyNode tmp84_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
				MyNode tmp85_AST_in = (MyNode)_t;
				match(_t,B_IDENTIFIER);
				_t = _t.getNextSibling();
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
		"A_FUNC_CALL"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { -3530822108395339808L, 246144575725599L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { -3530822108395339808L, 246144575731743L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -3530822108395339808L, 246282014679071L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -9223231299097984992L, 4294967305L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 268435552L, 117093590311632912L, 4645052394680352768L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 268435552L, 16L, 9007199254740992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 268435552L, 16L, 4620693217682128896L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 112L, 2097152L, 4611686018427387904L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	}
	
