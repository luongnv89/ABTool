// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	BTyping.g
//	Date		:	Creation 03/06/2000
//	Descripton	:	Typing based on Tree Walker for Another B Parser written in ANTLR
//	
//	Copyright 2000-2011 Boulanger Jean-Louis
//

// Releases
//	June 		2000	v 0.0 		Creation
//						Add some new rule
//						Mise en conformite globale avec B.g et Treewalker.g
//						Add clause sets in implementation
//
//	July 		2000	v 0.1		Add Error message managing
//						Add negative number in a_set_value rule
//
//	September 	2000	v 0.2		Add Typing in many rules
//	
//	December  	2000  	v 0.3		Add Typing for SUCH rules
//
//	January	  	2001  	v 0.3.1		Add "Struct"
//
//	February  	2001  	v 0.3.2 	Correct some rules
//						Test with ANTLR 2.7.1
//
//	April     	2001  	v 0.3.3 	Add the possibility to manage all linked abstract machine
//
//     	May	  	2001  	v 0.3.4 	Add the control of promoted operation 
//
//	August		2001	v 0.3.5 	Add some Typing rules
//						We correct the conflict on B_TILDE
//						We add the typing verification of local variable of ANY
//
//	December	2001	v 0.3.6		We correct some typing rule
//						We correct the typing verification of declarated variables.
//
//	January		2002	v 0.3.7		We add the possibility to verify the parameter type in module param name
//
//	April		2002	v 0.3.8	
//		- We introduce the "not" operator
//		- We introduce semantics controls RA1  (see technical documentation)
//		- Add some debug trace
//
//				
//	May	  	2002	v 0.3.9
//		- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//
//	August		2002	v 0.4.0
//		- add a mechanism for verify the good used of the B clause.
//			CONSTANTS => PROPERTIES
//			....
//	September 	2002	v 0.4.1
//		- pretty printing 
//
//  October     2002    v 0.4.2
//      - pretty printing
//      - introduce the fact that in definition we don't generate typing error ....
//
//  March       2003    v 0.4.3
//      - pretty printing
//      - Correct a Bug 15 is a NATURAL not an INTEGER
//      - Correct a Bug in variable search fron another machine
//
//  May         2003    v 0.4.4
//      - Typage de MAXINT
//
//  July        2003    v 0.4.5
//      - Pretty printing
//      - Retry THEN in SELECT structure
//      - Correct a BUG in context management
//      - correct a BUG in list_func_call
//      - Correct a BUG in list_var_bis (introduce A_FUNC_CALL)
//
//  August      2003    v 0.4.6
//              - Modify rule "afc" for introduce "B_PARENT".
//              This case appear during normalization
//              - Add the local variable module
//              - Introduce SUBST_DEF and EXP_DEF Tolen for help treewalking.
//
// April        2004    v 0.5
//              - renaming
//              - Packaging and Restructuring
//
// September    2004    v 0.6 
//              - Pretty Printing
//              - enable errors management in SymbolTable
//
//                      v 1.3
//              - add some trace
//
// November     2004    v 1.4
//              - Add basic functor
// August       2005    v 1.6
//              - pretty printing 
//              - analysis for BUG number 20
//              - add a message error link to INVARIANT Clause
// May          2007
//              - Pretty print
//              - correct BUG for typing {1,2}




// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//  * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
//  **/

header 
{
    package ABTOOLS.GRAMMAR;
}


// Import the necessary classes 
//-----------------------------------------------------------------------------

{
// usefull packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*; 
    	import ABTOOLS.TYPING.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class BTyping extends TreeParser;
options
{
	importVocab 	= BPrime;					// The language is imported and not exported
        buildAST 	= false;					// We write, we don't build tree
        ASTLabelType 	= "MyNode";
 
        // Copied following options from java grammar.
        codeGenMakeSwitchThreshold = 3;
        codeGenBitsetTestThreshold = 4;

        k = 1;      							// On a besoin d'un seul token 
}

// Indtroduce some behaviours....
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

		#target.setBType(tmp);
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

		type.setLineNumber(#target.getLineNum());			// On reaffecte le numero de ligne

  		#target.setBType(type);
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

}

// Grammar become HERE
// -------------------

/**
 *	La regle "composant" permet de definir le point d'entree pour realiser le typage.
 **/

// Root Rule
composant 	:
                (
		 		machine
			|	refinement
			|	implementation
		)
{
	finalChecking();
}
		;


// THREE type of B composants
// simple name or with one or more parameters

machine         :
    #("MACHINE"
{
	// on active la memorisation des parametres pour typage en fin de constraints.
	param_module = true;
    machine      = true;
}
                paramName
{
	param_module = false;
}
        (	constraints
        |      	link
        |      	sets
        |      	constants
        |      	properties
        |      	variables
        |      	invariant
        |      	assertions
        |      	definitions
        |      	initialisation
        |      	operations
        )*
{
// Verification de la declaration des operations promotes
	if (promote != null)
	{
		list_var_bis(promote);
		ABType type = promote.getBType();
	}

	popScope(); 				// Celui la c'est celui associe au paramName 

    machine = false;
}

    )
;

refinement      :	
    #("REFINEMENT" 
{
	// on active la memorisation des parametres pour typage en fin de constraints.
	param_module = true;
}
 		paramName
{
	param_module = false;
}
        (
            	refines
        |      constraints
        |      link
        |      sets 
        |      constants
        |      properties
        |      variables
        |      invariant
        |      assertions
        |      definitions
        |      initialisation
        |      operations  
        )*
{
// Verification de la declaration des operations promotes
	if (promote != null)
	{
		list_var_bis(promote);
		ABType type = promote.getBType();
	}

	popScope(); 				// Celui la c'est celui associe a la clause paramName 
}
    )
;

implementation  :
    #("IMPLEMENTATION"
{
	// on active la memorisation des parametres pourtypage en fin de constraints.
	param_module = true;
}
		paramName
{
	param_module = false;
}
        (
                refines 
        |      	link
        |	sets
        |      	values
        |      	constants
        |      	properties
        |      	variables
        |      	invariant
        |      	assertions
        |      	definitions
        |      	initialisation
        |      	operations 
        )*
{
// Verification de la declaration des operations promotes
	if (promote != null)
	{
		list_var_bis(promote);
		ABType type = promote.getBType();
	}

	popScope(); 				// Celui la c'est celui associe au paramName 
}
    )
;


/**
 * This rule is used for defined all Name with or without parameters
 **/

paramName	:
#(tt:B_LPAREN 
	name:B_IDENTIFIER
{
	pushScope(#name.getText());
}
	ll:listTypedIdentifier
{
	ABType tmp = new FUNC_DEF(new ABType(), #ll.getBType());
 	tmp.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
	#name.setBType(tmp);

	if (param_module)
    {
		param_machine = ll;
    }

	popScope();
 	symbolTable.add(#name.getText(),(MyNode)#name);
	pushScope(#name.getText());
}
				)	
|	name1:B_IDENTIFIER
{
	pushScope(#name1.getText());

 	ABType type = new FUNC_DEF(new CONSTANT(), new ABType());
 	type.setLineNumber(#name1.getLineNum());				// On reaffecte le numero de ligne
	#name1.setBType(type);

 	popScope();
	symbolTable.add(#name1.getText(),(MyNode)#name1);
	pushScope(#name1.getText());
}
;

paramNameOP	:		#(tt:B_LPAREN 
					name:B_IDENTIFIER
{
	pushScope(#name.getText());
}
					ll:listTypedIdentifier
{
	param_in = #ll;

	ABType tmp = new FUNC_DEF(new ABType(), #ll.getBType());
 	tmp.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
	#name.setBType(tmp);

	popScope();
 	symbolTable.add(#name.getText(),(MyNode)#name);
	pushScope(#name.getText());
}
				)	
			|
				name1:B_IDENTIFIER
{
	pushScope(#name1.getText());

 	ABType type = new FUNC_DEF(new CONSTANT(), new ABType());
 	type.setLineNumber(#name1.getLineNum());				// On reaffecte le numero de ligne
	#name1.setBType(type);

 	popScope();
	symbolTable.add(#name1.getText(),(MyNode)#name1);
	pushScope(#name1.getText());
}
		;

listTypedIdentifier: 		#(t1:B_COMMA
					t2:listTypedIdentifier
			 	 	t3:listTypedIdentifier 
{
	// C'est bien un produit cartesien de type ...
    
  	ABType type = new PROD(#t2.getBType(),#t3.getBType());
  	
   	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
				)	
			|	typedIdentifier
		;

/** 
 * La regle "typedIdentifier" permet de prendre en compte l'extension 
 * de typage explicite de B'
 */

typedIdentifier	
{
 	String newCurrent="";
 	ABType newType=new ABType(); 
}
			:	#(t1:B_INSET
					newCurrent 	= name:nameRenamed
				 	newType 	= predicate
{
	// ATTENTION
	// Question : que fait-on comme verification sur newCurrent ??
	// Reponse  : Aucune car AddTypeToId verifie l'unicite avant ajout a la table.

        newType.setLineNumber(#t1.getLineNum());			// On reaffecte le numero de ligne
        #name.setBType(newType);
        #t1.setBType(newType);						// Il faut typer les deux noeuds pour la decompilation
        addIdInCurrentScope(#name,newType);

}
				 )	
			|
					newCurrent 	= name1:nameRenamed
{
 	newType = new ABType();

    if (machine == true)
    {

    }
    else
    {
System.out.println("\n II -- on cherche un Id "+#name1.getText()+" context "+symbolTable.currentScopeWithoutComponent());
      newType = searchId(name1, newType);
System.out.println("on a trouver Id "+#name1.getText()+" "+newType.toString()+"\n");
System.out.println("-- II \n");
    }

	newType.setLineNumber(#name1.getLineNum());			// On reaffecte le numero de ligne
 	#name1.setBType(newType);
	addIdInCurrentScope(#name1,newType); 

    if (machine == true)
    {
     
    }
}
		;

constraints	:
    #(c1:"CONSTRAINTS"
{
 	ABType type=new ABType(); 
}
        type = tt:predicate
{
	typeControleTreatement (tt, type);

// ATTENTION
// QUESTION : On reaffecte le type mais est ce necessaire ??
	type.setLineNumber(#c1.getLineNum());			// On reaffecte le numero de ligne
	#tt.setBType(type);
	#c1.setBType(type);


// Verification du typages des parametres d'E/S
	if (param_machine != null)
	{
		list_var_bis(param_machine);
		ABType ltype = param_machine.getBType();
	}
}
    )
;

link		:
    	uses 
|	includes
|	sees
|	extendeds
|	promotes
|	imports
;


extendeds	:
    #("EXTENDS"
        listInstanciation
    )
;

uses	:
    #("USES"						
        listNames						
    )
;

includes:
    #("INCLUDES"						
        listInstanciation					
    )
;

sees:
    #("SEES"						
        listNames						
    )
;

listNames	:
    #(B_COMMA
        listNames
        listNames
    )
|
{
 	String newCurrent="";
}
        newCurrent=nameRenamed
;

listInstanciation:
    #(B_COMMA 
            listInstanciation
            listInstanciation
     )
    |	paramRenameValuation
;


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

// ATTENTION
// Mettre en place la notion de PROJET

paramRenameValuation 
{
	ABType type = new ABType();
}
	:		#(t1:B_LPAREN 
					pp:paramRenameValuation
				  	type = list_New_Predicate
{
	ABType tmp = pp.getBType();
	type 	 = new PROD(tmp, type);

// Il faut etudier comment faire :
// en effet on a F (a) (b) (c) qui se traduite en [ ( [ ( [ ( F, a] b] c ]
// pas facilement typable 

//	if (typeControle(new EQUAL(tmp, type)))
//		type = new BOOL();

 	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
				)
			|
{
 	String newCurrent = "";
}
				newCurrent	= name1:nameRenamed
{
// ATTENTION
// Voir si le type final lors de parametrisation est correct 
// BJL : rien a faire sur cette partie 


 	type.setLineNumber(#name1.getLineNum());			// On reaffecte le numero de ligne
  	#name1.setBType(type);
}
;

imports	:
    #("IMPORTS"
            listInstanciation
     )
;

promotes:
    	#("PROMOTES"							
        	lv:listNames							
{
	promote = #lv;
}
    	)
;


/**
 * A FAIRE:
 * ========
 * Pour realiser un typage complet, on a besoin d'avoir acces a la "notion de projet"
 * pour ceci afin de verifier que 
 *	- la machine existe et que c'est bien une machine, ou un raffinement suivant le cas
 *	- le raffinement de la machine est correct au niveau parametre
 *
 */
refines	:
    #("REFINES"
        name:B_IDENTIFIER
{
	// Pour l'instant c'est un FUNC_DEF on pourrait imaginer mettre un MCH_DEF
	addId(name,new FUNC_DEF());
}
    )
;

constants	:
    #("CONSTANTS"
        lv1:listTypedIdentifier
{
	constant = #lv1;
	constant_valide	= true;
}
    )
|	#("CONCRETE_CONSTANTS"
        lv2:listTypedIdentifier
{
	concrete_constant = #lv2;
	constant_valide	= true;
}
    )
|	#("VISIBLE_CONSTANTS"
        lv3:listTypedIdentifier
{
	visible_constant = #lv3;
	constant_valide	= true;
}
    )
|	#("ABSTRACT_CONSTANTS"
        lv4:listTypedIdentifier
{
	abstract_constant = #lv4;
	constant_valide	= true;
}
    )
|	#("HIDDEN_CONSTANTS"
        lv5:listTypedIdentifier
{
	hidden_constant = #lv5;
	constant_valide	= true;
}
    )
;

sets		:
    #("SETS"							
        sets_declaration
    )
;


// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:
                #(B_SEMICOLON  sets_declaration   sets_declaration)
| 		#(B_COMMA      sets_declaration   sets_declaration)
|		set_declaration	
;

set_declaration :
// Definition d'un ensemble ID = ....
		#(t1:B_EQUAL
			set:B_IDENTIFIER
{
	ABType type = new ABType();
}
			type = valuation_set[set]
{
//	type = new SET(type.reduce(errors));
	type.setLineNumber(#t1.getLineNum());
	#t1.setBType(type);
	addId(set,type);
System.out.println("1.On a mis dans la table "+type.toString());
}
		)
|
// Definition d'un ID
		name:B_IDENTIFIER
{
	ABType type = new GIVENSET(new ABType(#name.getText()));
	type.setLineNumber(#name.getLineNum());
	#name.setBType(type);
	addId(name,type);
System.out.println("2.On a mis dans la table "+type.toString());
}
;

is_record returns [ABType type]
{
            type  	= new ABType();
	ABType 	tmp1  	= new ABType();
}
:		#(t1:"rec"  
{
	pushScope("STRUCT");
}
		tmp1 = listrecord[false]
{
  	type = new STRUCT (tmp1);

 	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
 	#t1.setBType(type);

	popScope();
}
				)
|	#(t2:"struct"  
{
	pushScope("STRUCT");
}
		tmp1 = listrecord[true]
{
  	type = new STRUCT (tmp1);

 	type.setLineNumber(#t2.getLineNum());				// On reaffecte le numero de ligne
 	#t2.setBType(type);

	popScope();
}
		)
;

valuation_set[AST set] returns [ABType type]
{
            	type  = new ABType();
	ABType 	tmp1  = new ABType();
	ABType 	tmp2  = new ABType();
}
		:		
		#(t1:B_CURLYOPEN
			tmp1 = list_couple[set]
{
// Pour analyse des erreurs de compatibilite dans la declaration du type
  	type = tmp1.reduce(errors);

	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
		)
|		#(t100:B_RANGE
			tmp1 = a_set_value[set]
			tmp2 = a_set_value[set]
{
// Pour analyse des erreurs de compatibilite dans la declaration du type
  	type = compatibilityReduceType (#t100, tmp1, tmp2);

	type.setLineNumber(#t100.getLineNum());				// On reaffecte le numero de ligne
  	#t100.setBType(type);
}
		)
	|	type = is_record
	|	#(t3:B_MULT 
			tmp1 = valuation_set[set]
			tmp2 = valuation_set[set]
{
// 	type = compatibilityReduceType (#t3, tmp1, tmp2);
	type = new PROD(tmp1, tmp2);
	type.setLineNumber(#t3.getLineNum());				// On reaffecte le numero de ligne
 	#t3.setBType(type);
}
		)
	|	#(t4:B_ADD 
			tmp1 = valuation_set[set]
		 	tmp2 = valuation_set[set]
{
 	type = compatibilityReduceType (#t4, tmp1, tmp2);
	type.setLineNumber(#t4.getLineNum());				// On reaffecte le numero de ligne
 	#t4.setBType(type);
}
		)
	|	#(t5:B_MINUS 
			tmp1 = valuation_set[set]
			tmp2 = valuation_set[set]
{
 	type = compatibilityReduceType (#t5, tmp1, tmp2);
	type.setLineNumber(#t5.getLineNum());				// On reaffecte le numero de ligne
 	#t5.setBType(type);
}
		)
	|	t6:B_IDENTIFIER
{
	type = searchId(t6,type);
	type.setLineNumber(#t6.getLineNum());				// On reaffecte le numero de ligne
 	#t6.setBType(type);
}
	|	type = basic_sets
;

listrecord[boolean bb] returns [ABType type]
{
	type = new ABType();
	ABType tmp1, tmp2;
}
:
	#(t1:B_COMMA 
		tmp1 = listrecord[bb]
		tmp2 = listrecord[bb]
{
  	type = new LIST(tmp1, tmp2); 
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
	)
|	type = aa:a_record[bb]
{
	type.setLineNumber(#aa.getLineNum());				// On reaffecte le numero de ligne
  	#aa.setBType(type);
}
;

list_couple[AST set] returns [ABType type]
{
	      	type = new ABType();
	 ABType list = new ABType();
	 ABType temp = new ABType();
}
:
 	#(t1:B_COMMA 
		list = list_couple[set]  
		temp = list_couple[set]
{
  	type = new LIST(list, temp); 
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
	)
|	type = cc:couple[set]
{
 	type.setLineNumber(#cc.getLineNum());				// On reaffecte le numero de ligne
 	#cc.setBType(type);
}
;

couple[AST set]	returns [ABType type]
{type = new ABType();}
		:
	type = xx:a_maplet[set]
{ 
 	type.setLineNumber(#xx.getLineNum());				// On reaffecte le numero de ligne
 	#xx.setBType(type);
}
|	#(tt:B_LPAREN 
		type = parent_couple[set]
{ 
	type.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
  	#tt.setBType(type);
}
	)
|	type = aa:a_set_value[set]
{
	type.setLineNumber(#aa.getLineNum());				// On reaffecte le numero de ligne
  	#aa.setBType(type);
}
;

parent_couple[AST set] returns [ABType type]
{
	type = new ABType();
}
		:
				type = aa:a_maplet[set]
{
	type.setLineNumber(#aa.getLineNum());				// On reaffecte le numero de ligne
  	#aa.setBType(type);
}
			|	type = bb:a_comma[set]
{
	type.setLineNumber(#bb.getLineNum());				// On reaffecte le numero de ligne
  	#bb.setBType(type);
}
;

a_comma[AST set] returns [ABType type] 
{
	type = new ABType();
 	ABType t1, t2;
}
		:
				#(tt:B_COMMA 
					t1 = a_set_value_
					t2 = a_set_value_
{
  	type = new PROD(t1,t2); 
	type.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
  	#tt.setBType(type);
}
				)
;

a_maplet[AST set] returns [ABType type] 
{
	type = new ABType();
 	ABType t1, t2;
}
		:
				#(tt:B_MAPLET 
					t1 = a_set_value_
					t2 = a_set_value_
{
  	type = new PROD(t1,t2); 
	type.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
  	#tt.setBType(type);
}
				)
;

a_set_value[AST mset] returns [ABType type]
{
	type = new ABType();
}
		:
				name:B_IDENTIFIER
{
	type= new ENUM(#mset.toString());
	addId(name,type);
	type.setLineNumber(#name.getLineNum());				// On reaffecte le numero de ligne
	#name.setBType(type);
}
			|	#(t1:UNARY_MINUS	
					val:B_NUMBER
{
	type = new INT();
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
	#t1.setBType(type);
	#val.setBType(type);
}
				)
			|	val1:B_NUMBER
{
	type = new NAT();
	type.setLineNumber(#val1.getLineNum());				// On reaffecte le numero de ligne
	#val1.setBType(type);
	// Inutile de les mettres dans la table des symboles ...
}
			|	tr:"TRUE"
{
	type = new BOOL();
	type.setLineNumber(#tr.getLineNum());				// On reaffecte le numero de ligne
	#tr.setBType(type);
}
			|	fa:"FALSE"
{
	type = new BOOL();
	type.setLineNumber(#fa.getLineNum());				// On reaffecte le numero de ligne
	#fa.setBType(type);
}
		;

a_set_value_ returns [ABType type]
{ 
	type = new ENUM();						// On est dans la famille des types ENUMERE
}
			:
				name:B_IDENTIFIER
{
	type.setLineNumber(#name.getLineNum());				// On reaffecte le numero de ligne
	addId(name,type);
	#name.setBType(type);
}
			|	#(t1:UNARY_MINUS
					val:B_NUMBER
{
	type = new INT();
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne

	#t1.setBType(type);
	#val.setBType(type);
}
				)
			|
				val1:B_NUMBER
{
// on a un NUMBER => un NAT
  	type = new NAT();
	type.setLineNumber(#val1.getLineNum());				// On reaffecte le numero de ligne

  	#val1.setBType(type);
	// Inutile de les mettres dans la table des symboles ...
}
			|	tr:"TRUE"
{
	type = new BOOL();
	type.setLineNumber(#tr.getLineNum());				// On reaffecte le numero de ligne

	#tr.setBType(type);
}
			|	fa:"FALSE"
{
	type = new BOOL();
	type.setLineNumber(#fa.getLineNum());				// On reaffecte le numero de ligne

	#fa.setBType(type);
}
;

/**
 *	La regle "a_record" permet de definir le type de chaque champs
 **/
a_record[boolean b] returns [ABType type]	
{
	type= new ABType();
}
		:		#(tt:B_SELECTOR
					name:B_IDENTIFIER 
					type = predicate
{
	if (b == true) 						// New ID  pas de branche ELSE
		if (typant == true)				// Il y a des phases non typantes  ex: INIT
			addId(name,type);

	type.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
	#tt.setBType(type);
}
				)
			|	type = predicate
;

values	:
    #("VALUES"	
        list_valuation	
    )
;

list_valuation	:	
    #(B_SEMICOLON 
        list_valuation 	
        list_valuation	
    )
|	set_valuation
;

set_valuation	:
    #(aa:B_EQUAL 
        name:B_IDENTIFIER
{
 	ABType newType= new ABType();
}
        newType = new_set_or_constant_valuation[name]
{
	addTypeToId(name,newType);
	newType.setLineNumber(#aa.getLineNum());				// On reaffecte le numero de ligne
	#aa.setBType(newType);
}
    )
;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

// En fait en faisant simple c'est un "predicate"
new_set_or_constant_valuation[AST name] returns [ABType newType]
{
  	newType = new ABType();
}
		:
//				newType = basic_sets
//			|
//				#(t1:B_CURLYOPEN
//					newType = list_couple[name] 
//{
//  	newType = new SET(newType.reduce(errors));
//  	#t1.setBType(newType);
//}
//				)
//			|	newType = interval_declaration
//			|	newType = cbasic_value
// Pour les tableaux
//			|	#(t2:B_MULT 
//{
// 	ABType newType1 = new ABType();
// 	ABType newType2 = new ABType();
//}
//					newType1 = predicate
//					newType2 = predicate
//{
//	// C'est bien un PRODUIT car tt = XX * XX
//
//  	newType = new PROD(newType1, newType2);
//  	#t2.setBType(newType);
//}
//				)
			newType = predicate
;

set_interval_value:
    #(tt:B_EQUAL 
        a:B_IDENTIFIER 	
{
 	ABType newType = new ABType();
 	pushScope(#a.getText());
}
        newType =interval_declaration
{
	newType.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
  	#tt.setBType(newType);
	addId(a,newType);
 	popScope();
}
    )
;

interval_declaration returns [ABType newType]
{ 
	newType = new ABType();
}
		:		
    #(t1:B_RANGE
{
  	ABType newType1= new ABType();
  	ABType newType2= new ABType();
} 
        newType1 = predicate
        newType2 = predicate
{
  	newType = new RANGE(newType1,newType2);
  	#t1.setBType(newType);
}
    )
;

properties	:		
	#("PROPERTIES"
{
 	ABType type 	= new ABType();
 	ABType type1	= new ABType();
	typant 		= true;				// Debut de phase typante
} 
		type1 = tt:predicate
{
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
}
				)
		;

variables	:
{
        variable_valide	= true;
}
			(	#("VARIABLES" 
					lv1:listTypedIdentifier 
{
		variable = #lv1;
}
				)
			|	#("ABSTRACT_VARIABLES"
				 	lv2:listTypedIdentifier 
{
		abstract_variable = #lv2;
}				)
			|	#("VISIBLE_VARIABLES"
					lv3:listTypedIdentifier
{
		visible_variable = #lv3;
}
				)
			|	#("CONCRETE_VARIABLES"
					lv4:listTypedIdentifier
{
		concrete_variable = #lv4;
}
				)
			|	#("HIDDEN_VARIABLES"
					lv5:listTypedIdentifier	
{
		hidden_variable = #lv5;
}
				)
			)
		;

invariant	:		
#(b1:"INVARIANT"
{ 
	ABType type	    	= new ABType();
	ABType tmpType		= new ABType();
        typant     		= true;					// Debut de phase typante
} 
	type = tt:predicate
{

// ATTENTION
// QUESTION : On reaffecte le type mais est ce necessaire ??
	type.setLineNumber(#b1.getLineNum());			// On reaffecte le numero de ligne
	#tt.setBType(type);
	#b1.setBType(type);

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
}
)
;



definitions	:		#("DEFINITIONS" 
{
    errors.Disabling();
}
					list_def
				)
{
    errors.Enabling();
}
		;

list_def	:
		        #(LIST_DEF
					list_def
					list_def
				)
			|	definition
		;

/**
 * Attention deux cas
 *	- Une definition
 *	- un acces a un fichier de definition
 **/
definition	:
                #(B_DOUBLE_EQUAL
                        name:paramName
                        ft:formalText
{
	popScope(); 						// Celui la c'est celui associe au paramName 

// Pas de type de retours pour les definitions .....
}
				)
			|	B_ASTRING
		;

/**
 **	A FAIRE
 **	On se trouve dans le corps d'une DEFINITION,
 **	il va falloir introduire l'inhibition des messages d'erreurs 
 **	ou les transforme en WARNING
 **/

formalText	:
{ 
	ABType type= new ABType();
} 
        #(EXP_DEF    type = predicate)
    |	#(SUBST_DEF         instruction)
//			|	operation
    ;

assertions	:		
            #("ASSERTIONS"	
                list_assertions
            )
    ;

list_assertions	:
		    #(B_SEMICOLON  
                list_assertions
                list_assertions
            )
			|
{
 	ABType type= new ABType();
}
            type = predicate
{
	// Ici il faut verifier ce qu'on fait avec le type
	// Normalement RIEN juste verifier qu'il n'y a pas d'erreur
}
    ;

/**
 **	L'initialisation ne doit pas introduire de VARIABLE 
 **	sauf si on utilise l'instruction VAR .. IN ... END 
 **	=> pas de push/pop
 **/

initialisation	:
	#("INITIALISATION"
        	instruction
        )
;

operations	:
		    #("OPERATIONS"
                listOperation
            )
    ;

listOperation	:
            #(B_SEMICOLON   listOperation   listOperation   )
        |	                    operation
    ;

operation	:		
            #(OP_DEF
                def_operation	
            )
    ;

def_operation
{
	ABType type = null;
}		:
				(
					#(tt:B_OUT
{	// mise en place du context
	pushScope(#tt.getOpname());
}
						ll:listTypedIdentifier
{
	param_out = #ll;
	popScope();
}
						paramNameOP
					)
					instruction
{
	// Verification du typage de tous les parametres de sortie de l'operation
	type 		= TypedDeclaratedVariable(param_out);
	param_out 	= null ;

	// Verification du typage de tous les parametres d'entree de l'operation
	type 		= TypedDeclaratedVariable(param_in);
	param_in 	= null ;

    popScope();
}
				)
					|
				(
					paramNameOP
					instruction
{
	// Verification du typage de tous les parametres d'entree de l'operation
	type 		= TypedDeclaratedVariable(param_in);
	param_in 	= null ;

    popScope();
}
				)
;


/**
 * the Generalised Substitution Language
 **/

instruction
{
	ABType newType	= new ABType();
	ABType type	    = new ABType();
}
		:		#(PARALLEL	    instruction	instruction	)
			|	#(SEQUENTIAL	instruction	instruction	)
			|	"skip" 
			|	#("BEGIN"	instruction 	)
			|	#("PRE"
					{System.out.println("Debut de Pre");}
					newType = predicate
					instruction
					{System.out.println("Fin de Pre");}
				)
			|	#("ASSERT"	
					newType = predicate
				 	instruction
				)
			|	#("IF" 	
					newType = predicate
					branche_then
					(branche_elsif)*
					(branche_else)?
				)
			|	#("CHOICE"
				 	list_or 
				)
			|	#("CASE"	

					newType = predicate
					branche_either
					(branche_or)*
					(branche_else)?
				)
			|	#(sany:"ANY" 
{
	pushScope("ANY");
}
					lv1:listTypedIdentifier
{
	local_variable = #lv1;	 	// Memorize the list of local VARIABLE for verification of typing
}
					newType = predicate
{
	type		= TypedDeclaratedVariable(local_variable);
	local_variable 	= null ;
	type.setLineNumber(#sany.getLineNum());				// On reaffecte le numero de ligne
 	#sany.setBType(type);
}
					instruction
{
 	popScope();
}
				)
			|	#(let:"LET"
{
	pushScope("LET");
}
					lv2:listTypedIdentifier
{
	local_variable = #lv2;	 	// Memorize the list of local VARIABLE for verification of typing
}
					list_equal
{
	type		= TypedDeclaratedVariable(local_variable);
	local_variable 	= null ;
	type.setLineNumber(#let.getLineNum());				// On reaffecte le numero de ligne
 	#let.setBType(type);
}
				 	instruction
{
 	popScope();
}
				)
			|	#("SELECT" 	
					newType = predicate
					instruction
					(branche_when)*
					(branche_else)?
				)
			|	#("WHILE"
					newType = predicate
					instruction
					variant_or_no
				)
			|	#(var:"VAR"
{
	pushScope("VAR");
}
					lv3:listTypedIdentifier
{
	local_variable = #lv3;
	int ln = #var.getLineNum();
}
					instruction
{
	type		= TypedDeclaratedVariable(local_variable);
	local_variable 	= null ;
	type.setLineNumber(ln);				// On reaffecte le numero de ligne
 	#var.setBType(type);

 	popScope();
}
				)
			|	simple_affect
		;


list_or		:		#("OR"
			  		list_or
				 	instruction
				)
			|	instruction
		;

branche_when	:		#("WHEN"		
{ 
	ABType type= new ABType();
} 
					type = tt:predicate
{
	typeControleTreatement (tt, type);
}
					instruction
				)
		;
branche_either	:		#("EITHER" 
{
	ABType type= new ABType();
} 
					type = tt:predicate
{
	typeControleTreatement (tt, type);
}
					instruction 
				)
		;

branche_or	:		#("OR"		
{ 
	ABType type= new ABType();
} 
					type = tt:predicate
{
	typeControleTreatement (tt, type);
}
					instruction
				)
		;

branche_then	:		#("THEN"				
					instruction
				)
		;

branche_else	:		#("ELSE"
					instruction
				)
		;

branche_elsif	:		#("ELSIF"				
{ 
	ABType type= new ABType();
} 
					type = tt:predicate
{
	typeControleTreatement (tt, type);
}
					instruction
				)
		;

variant_or_no
{ 
	ABType type= new ABType();
} 
		:		#("VARIANT"

					#("INVARIANT"
						type = t2:predicate
{
	typeControleTreatement (#t2, type);
}
                    )
                    type = t1:predicate
{
	typeControleTreatement (#t1, type);
}
				)
			|
			 	#("INVARIANT"

					#("VARIANT"
						type = t4:predicate
{
	typeControleTreatement (#t4, type);
}
					)
					type = t3:predicate
{
	typeControleTreatement (#t3, type);
}
				)
;

list_equal	:
                #(B_AND
				 	list_equal
		 		 	list_equal
				)
			|	an_equal	
;

an_equal	:
                #(B_EQUAL 
					name:B_IDENTIFIER
{ 
	ABType type= new ABType();
} 
					type = predicate
{
	addId(#name,type);
}
				)
;

/**
 * afin de prendre en compte 
 *	soit f (x,y,z)
 *	soit f (x) [z]
 *	soit f~(x) [z]
 *	soit f (x)~
 **/
func_call
{
 	ABType type = new ABType();
	ABType tmp1 = new ABType();
}
		: 		#(t1:B_TILDE
			 		func_call
 				)
			|	#(t2:APPLY_TO
					func_call
					type = list_New_Predicate
				)
			| 	#(t3:B_LPAREN
			 		name:func_call 
					tmp1 = list_New_Predicate
{
	if ((name.toString()).compareTo("POW") == 0)
	{
		type = new POW(tmp1);
	}
	// TAW 6/24/2011
	else if (name.getBType() instanceof INFIXE) {
		if (unwrap(name.getBType().getMember()).toString().equals(tmp1.toString())) {
		type = unwrap(name.getBType().getMember2());
		type.setLineNumber(#t3.getLineNum());
		#t3.setBType(type);
	} else {
		System.out.println("ERROR: relation of type " + name.getBType() + 
		" can not be applied to argument of type " + tmp1 + ", line " +
		#t2.getLineNum()+ ".");
	}
	}
	// END TAW
	else
	{    // Autre cas
	}

	type.setLineNumber(#t3.getLineNum());				// On reaffecte le numero de ligne
  	#t3.setBType(type);
}
				)
			|	#(t4:B_QUOTEIDENT
					func_call
				 	func_call
				)
			|
{ 
	String newCurrent="";
}
				newCurrent=nameRenamed
		;

func_param returns [ABType type]
{
	type = new ABType();
}
		:		type = list_New_Predicate
		;

list_New_Predicate returns [ABType type]
{
		type 	= new ABType();
	ABType 	tmp1 	= new ABType();
	ABType 	tmp2 	= new ABType();
}
		:
				#(t1:B_COMMA 
					tmp1 = list_New_Predicate  
					tmp2 = new_predicate
{
	type = new PROD(tmp1, tmp2);

	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
  	#t1.setBType(type);
}
				)
			|	type = new_predicate
		;

new_predicate	returns [ABType type]
{
	type = new ABType();
	ABType tmp1 = new ABType();
	ABType tmp2 = new ABType();
} 
		:
// A FAIRE g;f et g||f pas si simple a calculer

				#(B_SEMICOLON 
					tmp1 = new_predicate
					tmp2 = predicate
	 			)
			|	#(B_PARALLEL
					tmp1 = new_predicate
					tmp2 = predicate
				)
			|
					type=predicate
		;


/**
 *  Ne pas tenter de mettre l'identificateur dans la table des symboles
 * dans cette clause 
 **/

nameRenamed returns [String currentName]
{
	currentName = "";
}
		:		#(name:B_IDENTIFIER 
{ 
	currentName = #name.getText();
									// On retrourne un ID.
	ABType type = new ID();
	type.setLineNumber(#name.getLineNum());				// On reaffecte le numero de ligne
 	#name.setBType(type);
}
				)
			|	#(v:B_POINT 
					currentName	=	nameRenamed
					w:B_IDENTIFIER 	
{ 
									// on calcule le nom complet et 
									// on l'affecte au noeud principal
 	currentName = currentName +#v.getText()+#w.getText();
//	#v.setText(currentName);

									// On retrourne un ID.
	ABType type = new ID();
	type.setLineNumber(#v.getLineNum());				// On reaffecte le numero de ligne
 	#v.setBType(type);
}
				)
		;

nameRenamedDecorated returns [String currentName]
{
	currentName = "";
}
		:		#(t1:B_CPRED 
					currentName = name:nameRenamed
{
	// Etant donne qu'on parle de la valeur precedente d'un ID il faut qu'il soit deja definit

	ABType tmp = searchId(name,new ABType());	// On recupere le type 
	tmp.setLineNumber(#t1.getLineNum());		// On reaffecte le numero de ligne
	#name.setBType(tmp);				// et on le re-affecte
	#t1.setBType(tmp);				// et on le re-affecte
}
				)
			|
// On fait rien
				currentName = nameRenamed
		;

// BJL
// A VIRER
// N'est plus utilise pour l'instant 
nameRenamedDecoratedInverted returns [String currentName]
{
	currentName = "";
}
		:		#(B_TILDE 
					currentName = nameRenamedDecorated
				)
			|	currentName = nameRenamedDecorated
		;



listPredicate	
{ 
	ABType type = new ABType();
} 
		:
// cette regle est inutile ......
				#(tt:B_COMMA 
					lp1:listPredicate 
					lp2:listPredicate
{
	type = new PROD(#lp1.getBType(),#lp2.getBType());
	type.setLineNumber(#tt.getLineNum());
	#tt.setBType(type);
}
				)
			|
				#(t2:ELEM_SET 
					lp3:listPredicate 
					lp4:listPredicate
{
	type = new PROD(#lp3.getBType(),#lp4.getBType());
	type.setLineNumber(#t2.getLineNum());
	#t2.setBType(type);
}
				)
			|
				type = pe:predicate
{
	type.setLineNumber(#pe.getLineNum());
	#pe.setBType(type);
}
		;

a_func_call
{
	MyNode name;
}
		:
            #(tt:A_FUNC_CALL
					name = a1:afc
{

	ABType tmp = #a1.getBType();

// March 2003
// Cette ID existe 
//    si oui est-ce le bon type 
//    sinon on le cre avec ce type.
	ABType tmp1= searchId(name, #tmp);

// F e1* ... * en --> s1 * .. * sn a pour type e1* ...* en * s1* ... *sn
// et on a F( p1, ... ,pm) a pour type P1* ...*Pn et le resultat est un sous interval du type precedent

// PB afec les F(xx,yy) qui retourne des couples, triplets ou autre ...

	tmp.setLineNumber(#a1.getLineNum());					// On reaffecte le numero de ligne
	#tt.setBType(tmp);

//    #tt.setText(#a1.getText()); 
    #tt.memorizeOpname(#a1.getOpname());

}
				)	
|
                (
					name = a2:afc
{

	ABType tmp = #a2.getBType();

// March 2003
// Cette ID existe 
//    si oui est-ce le bon type 
//    sinon on le cre avec ce type.
	ABType tmp1= searchId(name, #tmp);

// F e1* ... * en --> s1 * .. * sn a pour type e1* ...* en * s1* ... *sn
// et on a F( p1, ... ,pm) a pour type P1* ...*Pn et le resultat est un sous interval du type precedent

// PB afec les F(xx,yy) qui retourne des couples, triplets ou autre ...

	tmp.setLineNumber(#a2.getLineNum());					// On reaffecte le numero de ligne
	#a2.setBType(tmp);

//    #tt.setText(#a1.getText()); 
    #a2.memorizeOpname(#a2.getOpname());

}
    )
;

afc returns [MyNode cNode]
{
	String currentName = "";
	MyNode tmp;
	cNode = new MyNode();
}
		:
				#(t1:FUNC_CALL_PARAM
					cNode = a1:afc
				  	lp:listPredicate
{
    ABType tmp1 = #a1.getBType();					        // on a un f(x)...(y)
	ABType tmp2 = #lp.getBType();					        // on ajoute un (zz)

	ABType tmptype  = new PROD(tmp1,tmp2);				// c'est pourquoi on fait le product
	tmptype.setLineNumber(#t1.getLineNum());			// On reaffecte le numero de ligne
	#t1.setBType(tmptype);

//    #t1.setText(#a1.getText());

    #t1.memorizeOpname(#a1.getOpname());
}
				)
            |
                #(t2:B_LPAREN
                    cNode = a2:afc
                    lp2:listPredicate
{
    ABType tmp1 = #a2.getBType();					        // on a un f(x)...(y)
	ABType tmp2 = #lp2.getBType();					    // on ajoute un (zz)

	ABType tmptype  = new PROD(tmp1,tmp2);				// c'est pourquoi on fait le product
	tmptype.setLineNumber(#t2.getLineNum());			// On reaffecte le numero de ligne

	#t2.setBType(tmptype);

//    #t2.setText(#a2.getText());

    #t2.memorizeOpname(#a2.getOpname());
}

                )
			|
				#(t3:B_QUOTEIDENT
					cNode = a3:afc
				 	tmp   = afc
// TODO
// BJL prevoire de voir ce qui sera fait pour le typage en cas d'appel du style a'a:= B

{ 
//   #t3.setText(#a3.getText());

    #t3.memorizeOpname(#a3.getOpname());
}
			 	)
			|
				currentName = name:nameRenamed
{
	cNode = name;

	ABType tmptype = searchId(name,new ABType());			// On recupere le type 
	tmptype.setLineNumber(#name.getLineNum());			// On reaffecte le numero de ligne
	#name.setBType(tmptype);

    #name.memorizeOpname(#name.getText());
}
		;

list_func_call	:
				#(tt:B_COMMA  
					lf1:list_func_call
					lf2:list_func_call
{
	// On construit le produit cartesien ...
	ABType type = new PROD(#lf1.getBType(),#lf2.getBType());
	type.setLineNumber(#tt.getLineNum());

	#tt.setBType(type);
}
				)
			|	a_func_call
;

// Quatre cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a,b,c :( P )
//	soit	a,b,c :: P
//	soit	P.R (xx,yy)
//	soit 	P.R

simple_affect	
{ 
	ABType type	= new ABType();
	ABType tmp1	= new ABType();
	ABType tmp	= new ABType();
} 
		:		#(sa1:B_SIMPLESUBST
					lf1:list_func_call
{
	RA1(#lf1);
}
					tmp = lp1:predicate
{

// A VERIFIER
// Il y a un PB car si on a x,y,z := a,b,c
// il faut scinder les verifications ...........

// Il y a bien le cas ou on complete le type ==> dans les VAR.

	type = #lp1.getBType();

	type.setLineNumber(#sa1.getLineNum());		// On reaffecte le numero de ligne
	#sa1.setBType(type);

	addTypeToId (#lf1, type);

}
				)
			|	#(sa2:B_OUT	 	
					lf2:list_func_call
					fc2:func_call
{
	type = #fc2.getBType();
	type.setLineNumber(#sa2.getLineNum());		// On reaffecte le numero de ligne
	#sa2.setBType(type);

	addTypeToId (#lf2, type);
}
				)
			|	#(si:INSET
{
	System.out.println("debut => INSET");
}
					lf3:list_func_call
{
	RA1(#lf3);
	local_variable = #lf3;	 	// Memorize the list of local VARIABLE for verification of typing
}
					type = predicate
{

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
		type.setLineNumber(#si.getLineNum());		// On reaffecte le numero de ligne
        	#si.setBType(type);
   	 }
    	else
    	{

// BJL
// Introduire une incompatibilite de type 

   	}
}
{
	System.out.println("Fin => INSET");
}
				)
			|	#(sa4:B_BECOME_ELEM
{
	System.out.println("debut => BECOME");
}
				 	lf4:list_func_call
{
	RA1(#lf4);
	local_variable = #lf4;	 	// Memorize the list of local VARIABLE for verification of typing
}
					type = predicate
{

	tmp1	    	= TypedDeclaratedVariable(local_variable);
	local_variable 	= null ;

	if (typeControle(new EQUAL(tmp1, type)))
	{
		type = new BOOL();
		type.setLineNumber(#sa4.getLineNum());		// On reaffecte le numero de ligne
        	#sa4.setBType(type);
    	}
    	else
    	{

// BJL
// Introduire une incompatibilite de type 

    	}

System.out.println("fin => BECOME");
}
				)
			|	a_func_call
		;



/** 
 * Clause Predicate
 * Il faut verifier la compatibilite entre les deux types
 **/

// PREDICATE 
predicate 	returns [ABType theType]
{
 	theType 		= new ABType();
 	ABType newType  	= new ABType();
 	ABType newType1 	= new ABType();
 	ABType tmpType 		= new ABType();
}
		:
    #(a2:B_CARD 
            newType1 = predicate
{ 
 	theType = compatibilityReduceType (#a2, new SET(), newType1);

	theType.setLineNumber(#a2.getLineNum());				// On reaffecte le numero de ligne
	#a2.setBType(theType);	
}
    )
|   #(a1:B_MIN 
            newType1 = predicate
{ 
 	theType = compatibilityReduceType (#a1, new SET(), newType1);

	theType.setLineNumber(#a1.getLineNum());				// On reaffecte le numero de ligne
	#a1.setBType(theType);	
}
    )
|   #(a0:B_MAX 
            newType1 = predicate
{ 
 	theType = compatibilityReduceType (#a0, new SET(), newType1);

	theType.setLineNumber(#a0.getLineNum());				// On reaffecte le numero de ligne
	#a0.setBType(theType);	
}
    )
|    #(t0:B_NOT 
            newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t0, new BOOL(), newType1);

	theType.setLineNumber(#t0.getLineNum());				// On reaffecte le numero de ligne
	#t0.setBType(theType);	
}
    )
|    #(o0:B_RAN 
		newType1 = predicate
{ 

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

	theType.setLineNumber(#o0.getLineNum());				// On reaffecte le numero de ligne
	#o0.setBType(theType);	
}
    )
|	#(o1:B_DOM 
        	newType1 = predicate
{ 

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

	theType.setLineNumber(#o1.getLineNum());				// On reaffecte le numero de ligne
	#o1.setBType(theType);	
}
    )
|	#(t1:B_AND
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t1, newType, newType1);

	theType.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
	#t1.setBType(theType);	
}
    )	
|	#(t2:"or" 
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t2, newType, newType1);

	theType.setLineNumber(#t2.getLineNum());				// On reaffecte le numero de ligne
	#t2.setBType(theType);	
}
				)
|	#(t3:B_IMPLIES	
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t3, newType, newType1);

	theType.setLineNumber(#t3.getLineNum());				// On reaffecte le numero de ligne
	#t3.setBType(theType);	
}
				)
|	#(t4:B_EQUIV
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t4, newType, newType1);

	theType.setLineNumber(#t4.getLineNum());				// On reaffecte le numero de ligne
	#t4.setBType(theType);	
}
    )
|	#(t5:B_MULT
        newType  = predicate
        newType1 = predicate
{ 
// 	theType = compatibilityReduceType (#t5, newType, newType1);
 	theType = new PROD(newType, newType1);

	theType.setLineNumber(#t5.getLineNum());				// On reaffecte le numero de ligne
	#t5.setBType(theType);	
}
	)
|	#(t51:PRODSET
        newType  = predicate
        newType1 = predicate
{ 
 	theType = new PROD(newType, newType1);

	theType.setLineNumber(#t51.getLineNum());				// On reaffecte le numero de ligne
	#t51.setBType(theType);	
}
	)
|	#(t6:B_POWER 
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t6, newType, newType1);

	theType.setLineNumber(#t6.getLineNum());				// On reaffecte le numero de ligne
	#t6.setBType(theType);	
}
    )
|	#(t7:B_DIV
        newType  = predicate
        newType1 = predicate
{
 	theType = compatibilityReduceType (#t7, newType, newType1);

	theType.setLineNumber(#t7.getLineNum());				// On reaffecte le numero de ligne
	#t7.setBType(theType);	
}
    )
|	#(t8:"mod"
        newType = predicate
        newType1 = predicate
{
 	theType = compatibilityReduceType (#t8, newType, newType1);

	theType.setLineNumber(#t8.getLineNum());				// On reaffecte le numero de ligne
	#t8.setBType(theType);	
}
    )
|	#(t9:UNARY_ADD
        newType = predicate
{
	// Pas d'evolution du type car A et +A sont du meme type.
	// Mais il faut que ce soit un INTEGER.
 	theType = compatibilityReduceType (#t9, newType, new INTEGER());

	theType.setLineNumber(#t9.getLineNum());				// On reaffecte le numero de ligne
	#t9.setBType(theType);	
}
    )
|	#(t10:UNARY_MINUS
        newType = predicate
{
	// Par contre pour -A, il y a des verifications de type a faire,
	// avant de construire le nouveau type

	// Il faut que l'on est un ENTIER 
 	theType = compatibilityReduceType (#t10, new INTEGER(), newType);

	theType.setLineNumber(#t10.getLineNum());				// On reaffecte le numero de ligne
	#t10.setBType(theType);	
}
    )
| 	#(t11:B_ADD
        newType  = predicate
        newType1 = predicate
{ 
 	theType = compatibilityReduceType (#t11, newType, newType1);

	theType.setLineNumber(#t11.getLineNum());				// On reaffecte le numero de ligne
	#t11.setBType(theType);	
}
    )
|	#(t12:B_MINUS
        newType  = predicate
        newType1 = predicate
{ 
	theType = compatibilityReduceType (#t12, newType, newType1);

	theType.setLineNumber(#t12.getLineNum());				// On reaffecte le numero de ligne
	#t12.setBType(theType);	
}
    )
|	#(t131:B_EQUAL
        
        (	
            (	p131:B_IDENTIFIER
                newType1 = predicate
{
	addTypeToId(p131,newType1);
	theType = new BOOL();

	theType.setLineNumber(#t131.getLineNum());				// On reaffecte le numero de ligne
	#t131.setBType(theType);	
}
            )
        |
            (						 
                newType  = predicate
                newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					    // On reduit et si compatible on a BOOL.
	theType.setLineNumber(#t131.getLineNum());				// On reaffecte le numero de ligne
	#t131.setBType(theType);
}
            )
        )
)
|	#(t14:B_LESS
        newType  = predicate
        newType1 = predicate
{
	// Il faut que les deux types soit compatible si OUI => BOOL
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					    // On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t14.getLineNum());				// On reaffecte le numero de ligne
	#t14.setBType(theType);	
}
    )
|	#(t15:B_GREATER
        newType = predicate
        newType1 = predicate
{
	// Il faut que les deux types soit compatible si OUI => BOOL
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t15.getLineNum());				// On reaffecte le numero de ligne
	#t15.setBType(theType);	
}
    )
|	#(t16:B_NOTEQUAL
        newType = predicate				 
        newType1 = predicate
{
	// Il faut que les deux types soit compatible si OUI => BOOL
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t16.getLineNum());				// On reaffecte le numero de ligne
	#t16.setBType(theType);	
}
    )
|	#(t17:B_LESSTHANEQUAL
        newType  = predicate
        newType1 = predicate
{
	// Il faut que les deux types soit compatible si OUI => BOOL
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t17.getLineNum());				// On reaffecte le numero de ligne
	#t17.setBType(theType);	
}
    )
|	#(t18:B_GREATERTHANEQUAL
        newType  = predicate 										 
        newType1 = predicate
{
System.out.println("debut GE");

System.out.println("t1"+newType.toString()+" t2 "+newType1.toString());


	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t18.getLineNum());				// On reaffecte le numero de ligne
	#t18.setBType(theType);

System.out.println("fin GE");
}
    )
|	#(t19:B_INSET
        newType  = p191:predicate
        newType1 = p192:predicate
{ 

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

	theType.setLineNumber(#t19.getLineNum());
	#t19.setBType(theType);
}
    )
|	#(t20:B_NOTINSET
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t20.getLineNum());				// On reaffecte le numero de ligne
	#t20.setBType(theType);
}
    )
|	#(t21:B_SUBSET
        newType  = p211:predicate
        newType1 = p212:predicate
{
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

	theType.setLineNumber(#t21.getLineNum());				// On reaffecte le numero de ligne
	#t21.setBType(theType);	
}
    )
|	#(t22:B_NOTSUBSET
        newType = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t22.getLineNum());				// On reaffecte le numero de ligne
	#t22.setBType(theType);
}
    )
|	#(t23:B_STRICTSUBSET
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t23.getLineNum());				// On reaffecte le numero de ligne
	#t23.setBType(theType);
}
    )
|	#(t24:B_NOTSTRICTSBSET
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	theType = tmpType.reduce(errors);					// On reduit et si compatible on a BOOL.

	theType.setLineNumber(#t24.getLineNum());				// On reaffecte le numero de ligne
	#t24.setBType(theType);
}
    )
|	#(t25:B_CONCATSEQ
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.


	theType.setLineNumber(#t25.getLineNum());				// On reaffecte le numero de ligne
	#t25.setBType(theType);
}
    )
|	#(t26:B_PREAPPSEQ
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t26.getLineNum());				// On reaffecte le numero de ligne
	#t26.setBType(theType);
}
    )
|	#(t27:B_APPSEQ
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t27.getLineNum());				// On reaffecte le numero de ligne
	#t27.setBType(theType);
}
    )
|	#(t28:B_PREFIXSEQ
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t28.getLineNum());				// On reaffecte le numero de ligne
	#t28.setBType(theType);
}
    )
|	#(t29:B_SUFFIXSEQ		 
        newType  = predicate			 
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t29.getLineNum());				// On reaffecte le numero de ligne
	#t29.setBType(theType);
}
    )
|	#(t30:B_RELATION
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType,newType1);					// Creation d'un nouveau type

	theType.setLineNumber(#t30.getLineNum());				// On reaffecte le numero de ligne
	#t30.setBType(theType);
}
    )
|	#(t31:B_PARTIAL
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t31.getLineNum());				// On reaffecte le numero de ligne
	#t31.setBType(theType);
}
    )
|	#(t32:B_TOTAL
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t32.getLineNum());				// On reaffecte le numero de ligne
	#t32.setBType(theType);
}
    )
|	#(t33:B_PARTIAL_INJECT
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t33.getLineNum());				// On reaffecte le numero de ligne
	#t33.setBType(theType);
}
    )
|	#(t34:B_TOTAL_INJECT
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t34.getLineNum());				// On reaffecte le numero de ligne
	#t34.setBType(theType);
}
    )
|	#(t35:B_PARTIAL_SURJECT	
        newType  = predicate
        newType1 = predicate
{
	tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t35.getLineNum());				// On reaffecte le numero de ligne
	#t35.setBType(theType);
}
    )
|	#(t36:B_TOTAL_SURJECT
        newType  = predicate
        newType1 = predicate
{
	tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t36.getLineNum());				// On reaffecte le numero de ligne
	#t36.setBType(theType);
}
    )
|	#(t37:B_BIJECTION
        newType  = predicate
        newType1 = predicate
{
	tmpType = new PROD(newType,newType1, true);					// Creation d'un nouveau type

	theType.setLineNumber(#t37.getLineNum());				// On reaffecte le numero de ligne
	#t37.setBType(theType);
}
    )
|	#(t38:B_DOMAINRESTRICT
        newType  = predicate
        newType1 = predicate
{
theType = newType;
}
    )
|	#(t39:B_RANGERESTRICT	 
        newType  = predicate	 
        newType1 = predicate
{
theType = newType;
}
    )
|	#(t40:B_DOMAINSUBSTRACT
        newType  = predicate
        newType1 = predicate
{
theType = newType;
}
    )
|	#(t41:B_RANGESUBSTRACT
        newType  = predicate
        newType1 = predicate
{
theType = newType;
}
    )
|	#(t42:B_OVERRIDEFORWARD
        newType  = predicate			 
        newType1 = predicate
{
	theType = newType;
	theType.setLineNumber(#t42.getLineNum());				// On reaffecte le numero de ligne
}
    )
|	#(t43:B_OVERRIDEBACKWARD			 
        newType  = predicate
        newType1 = predicate
{
	theType = newType;
	theType.setLineNumber(#t43.getLineNum());				// On reaffecte le numero de ligne
}
    )
|	#(t44:B_RELPROD
        newType  = predicate
        newType1 = predicate
{
	theType = newType;
	theType.setLineNumber(#t44.getLineNum());				// On reaffecte le numero de ligne
}
    )
|	#(t45:B_UNION
        newType  = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t45.getLineNum());				// On reaffecte le numero de ligne
	#t45.setBType(theType);
}
    )
|	#(t46:B_INTER
        newType = predicate
        newType1 = predicate
{
	tmpType = new EQUAL (newType,newType1);

	if (typeControle( tmpType ) )
		theType = newType.reduce(errors);				// Ils sont compatibles d'ou on en reduits 1 
	else
		theType = tmpType.reduce(errors);				// On renvoie le message d'erreur.

	theType.setLineNumber(#t46.getLineNum());
}
    )
|	#(t48:B_MAPLET 
        newType  = predicate
        newType1 = predicate
{
	theType = new PROD(newType, newType1);
	theType.setLineNumber(#t48.getLineNum());				// On reaffecte le numero de ligne
	#t48.setBType(theType);
}
    )
|	#(t49:LIST_VAR
        newType  = predicate										 
        newType1 = predicate
{
	theType = new PROD(newType, newType1);
	theType.setLineNumber(#t49.getLineNum());				// On reaffecte le numero de ligne
	#t49.setBType(theType);
}
    )
|	theType = t50:cset_description
{
	#t50.setBType(theType);
}
;

cset_description returns [ABType theType]
{
	theType 		= new ABType();
	ABType newType  	= new ABType();
	ABType newType1 	= new ABType();
}
			:
				theType = basic_sets
			|
				theType = cbasic_value
			|
				#(bb:"bool"
					newType = predicate

{
	// Dans un predicat de la forme bool( P ), ....

	theType	= new EQUAL( new BOOL(),newType);
	theType.reduce(errors);
	theType.setLineNumber(#bb.getLineNum());				// On reaffecte le numero de ligne
	#bb.setBType(theType);
}
 				)
			|
				#(t4:B_BRACKOPEN
					ll:listPredicate 
{
 	theType = ll.getBType();
 	theType.setLineNumber(#t4.getLineNum());				// On reaffecte le numero de ligne
	#t4.setBType(theType);
}				)
			|
				#(t5:B_RANGE
					newType  = predicate
					newType1 = predicate
{
 	theType = new RANGE(newType, newType1);
 	theType.setLineNumber(#t5.getLineNum());				// On reaffecte le numero de ligne
	#t5.setBType(theType);
}
				)
			|
				#(t6:B_EMPTYSET 
{
 	theType = new JOKER();
 	theType.setLineNumber(#t6.getLineNum());				// On reaffecte le numero de ligne
	#t6.setBType(theType);
}
				)
			|
				#(t7:B_CURLYOPEN
					newType = q12:cvalue_set
{
System.out.println("CURLYOPEN : t1="+q12.getBType().toString()+" nt="+newType.toString());

 //	theType = new SET(newType.reduce(errors));  
	theType = newType.reduce(errors);
	theType.setLineNumber(#t7.getLineNum());				// On reaffecte le numero de ligne
 	#t7.setBType(theType);
}
				)
			|
				#(t8:B_SEQEMPTY
{
 	theType = new EMPTYSEQ();
 	theType.setLineNumber(#t8.getLineNum());				// On reaffecte le numero de ligne
	#t8.setBType(theType);
}
				)
			|	theType = is_record
			|	theType = quantification
			|	theType = q_lambda
		;

// Dans un ensemble il peut y avoir 
cvalue_set returns [ABType type]
{
		type 	= new ABType();
	ABType 	tmp1	= new ABType();
	ABType 	tmp2	= new ABType();
}
		:

// Le cas du B_SUCH qui a la forme
//                   l_var | P
				#(t1:B_SUCH
{
	pushScope("SUCH");
}
				 	lv:list_var
					tmp1 = predicate
{
	compatibilityReduceType (#t1, tmp1, new BOOL());

 // Q:
 //  Peux t on re-evaluer list_var pour verificaton du typage ?? 
 //  si oui faut le faire ....

	list_var_bis(lv);

	type = lv.getBType();

// Verification des objets non types
	type.typingVerification(errors);

	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
 	#t1.setBType(type);

	popScope();

}	
				)
|		#(t2:ELEM_SET 	
			tmp1 = cvalue_set
			tmp2 = cvalue_set
{
System.out.println("ELEM_SET : t1="+tmp1.toString()+" t2="+tmp2.toString());

	type = new LIST(tmp1, tmp2);
	type.setLineNumber(#t2.getLineNum());				// On reaffecte le numero de ligne
 	#t2.setBType(type);
}
				)
|
// (B_COMMA cvalue_set) => 
 	#(t21:B_COMMA 	
			tmp1 = cvalue_set
			tmp2 = cvalue_set
{
System.out.println("B_COMMA : t1="+tmp1.toString()+" t2="+tmp2.toString());

	type = new LIST(tmp1, tmp2);
	type.setLineNumber(#t21.getLineNum());				// On reaffecte le numero de ligne
 	#t21.setBType(type);
}
		)
// BJL :
// Sera tres peu utiliser car dans predicat il y a LIST_VAR qui est 
// prioritaire.
//			|	#(t3:LIST_VAR
//					tmp1 = cvalue_set
//					tmp2 = predicate
//{
//	type = new LIST(tmp1, tmp2);
//	type.setLineNumber(#t3.getLineNum());				// On reaffecte le numero de ligne
// 	#t3.setBType(type);
//}
//				)
|	type = predicate
{
System.out.println("cvalue_set - predicate = "+type.toString());
}
		;

cbasic_value returns [ABType type]
{
 	     type 		= new ABType();
 	ABType newType1	= new ABType();
 	ABType newType2	= new ABType();
} 
		:		#(as:B_ASTRING	
{
 	type = new STRING();
	type.setLineNumber(#as.getLineNum());				// On reaffecte le numero de ligne
	#as.setBType(type);
}
				)
			|
				#(nb:B_NUMBER
{
 	type = new NAT();
	type.setLineNumber(#nb.getLineNum());				// On reaffecte le numero de ligne
	#nb.setBType(type);
}
				)
			|
				#(t1:B_TILDE
					newType1 = predicate
{
	type = newType1.inverse(errors);				// On inverse le type ....
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
	#t1.setBType(type);	
}
				)
			|
{ 
	String cc="";
}
				cc = t2:nameRenamedDecorated
{
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

	type.setLineNumber(#t2.getLineNum());				// On reaffecte le numero de ligne
	#t2.setBType(type);
}
|
	#(t3:B_LPAREN
		newType1 =  ll:predicate
		type  	 =  list_New_Predicate
{

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
				type.setLineNumber(#t3.getLineNum());
				#t3.setBType(type);
			} else {
				System.out.println("ERROR: relation of type " + newType1 + 
				" can not be applied to argument of type " + type + ", line " +
				#t3.getLineNum()+ ".");
			}
    	}
    // END TAW
    	else 
	{    // Other case

	}

	type.setLineNumber(#t3.getLineNum());				// On reaffecte le numero de ligne
	#t3.setBType(type);
}
				)
			|
				#(t4:PARENT 
{
    System.out.println("on a un PARENT");
}
	// Rien a faire car le type de (P) est identique au type de P

					type = pred_func_composition 		
{
	type.setLineNumber(#t4.getLineNum());				// On reaffecte le numero de ligne
	#t4.setBType(type);
}
				)
			|
				#(B_QUOTEIDENT 
					newType1 = predicate

// Le second predicat doit etre un des attributs du record precedent.
// A faire ....
 					newType2 = predicate
		 		)
			|
				#(APPLY_TO
					newType1 = predicate
 					newType2 = predicate
		 		)
	// TAW 6/24/2011
{
	if (unwrap(newType1.getMember()).toString().equals(newType2.toString())) {
		type = unwrap(newType1.getMember2());
		type.setLineNumber(#APPLY_TO.getLineNum());
		#APPLY_TO.setBType(type);
	} else {
		System.out.println("ERROR: relation of type " + newType1 + 
		" can not be applied to argument of type " + newType2 + ", line " +
		#APPLY_TO.getLineNum()+ ".");
	}
}
	// END TAW
			|	tt:"TRUE"
{
  	type = new BOOL();
	type.setLineNumber(#tt.getLineNum());				// On reaffecte le numero de ligne
  	#tt.setBType(type);
}
			|	ff:"FALSE"
{
  	type = new BOOL();
 	type.setLineNumber(#ff.getLineNum());				// On reaffecte le numero de ligne
  	#ff.setBType(type);
}
		;

pred_func_composition returns [ABType type]
{
		type 	= new ABType();
 	ABType 	newType	= new ABType();
 	ABType 	newType1= new ABType();
}
		:		#(B_SEMICOLON 
					newType  = pred_func_composition
					newType1 = pred_func_composition
				)
			|	#(B_PARALLEL
					newType  = pred_func_composition
					newType1 = pred_func_composition
				)
			|	#(B_COMMA
					newType  = pred_func_composition
					newType1 = pred_func_composition
				)
			|	type = predicate
		;

/**
 * Les Types de base
 **/
basic_sets returns [ABType type]
{
 	type = new ABType();
}
		:
			(
				"INT"
{
	type = new INT();
}
			|	"INT1"		
{
	type = new INT1();
}
			|	"INTEGER"	
{
	type = new INTEGER(); 
}
			|	"INTEGER1"	
{
	type = new INTEGER1();
}	
			|	"BOOL"		
{
	type = new BOOL();    
}
			|	"NAT"		
{
	type = new NAT();     
}
			|	"NAT1"		
{
	type = new NAT1();    
}
			|	"NATURAL"	
{
	type = new NATURAL(); 
}
			|	"NATURAL1"	
{
	type = new NATURAL1();
}
			|	"STRING"
{
	type = new STRING();
}
			)
		;

quantification returns [ABType type]
{
 	type= new ABType();
} 
		:		#(t1:B_FORALL
{
 	pushScope("FORALL");
}
				 	lv1:list_var
					type = p1:predicate
{
	compatibilityReduceType (#t1, type, new BOOL());

 // Q:
 //  Peux t on re-evaluer list_var pour verificaton du typage ?? 
 //  si oui faut le faire ....
 //  OUI : C'est fait ...

	ABType tmpType = TypedDeclaratedVariable(lv1);

	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne
 	#t1.setBType(type);

 	popScope();
}
  				)
			|	#(t2:B_EXISTS
{
 	pushScope("EXISTS"+nb_exist++);
}
					lv2:list_var
					type = p2:predicate
{
// System.out.println("Typage : EXIST : Begin");
// System.out.println("predicate = "+ type.toString());

	compatibilityReduceType (#t2, type, new BOOL());

 // Q:
 //  Peux t on re-evaluer list_var pour verificaton du typage ?? 
 //  si oui faut le faire ....
 //  OUI : C'est fait ...

	ABType tmpType = TypedDeclaratedVariable(lv2);
	
	type.setLineNumber(#t2.getLineNum());				// On reaffecte le numero de ligne
 	#t2.setBType(type);

// System.out.println("result = "+ #t2.getBType().toString());

 	popScope();

// System.out.println("Typage : EXIST : End");
}
				)
		;



q_lambda returns [ABType type]	
{
 	type= new ABType();
} 
:
		        #(t1:B_LAMBDA 	
					q1:q_operande
{
System.out.println("debut Lambda");

	transfertType(q1,t1);
    type = t1.getBType();

System.out.println("fin Lambda");

}
				)
			|	#(t2:"PI"
					q2:q_operande
{
	transfertType(q2,t2);
    type = #t2.getBType();
}
				)
			|	#(t3:"SIGMA"
				 	q3:q_operande
{
	transfertType(q3,t3);
    type = t3.getBType();
}
				)
			|	#(t4:"UNION"
				 	q4:q_operande
{
	transfertType(q4,t4);
    type = t4.getBType();
}
				)
			|	#(t5:"INTER"
				 	q5:q_operande
{
	transfertType(q5,t5);
    type = t4.getBType();
}
				)
;

q_operande
{
 	ABType type  = new ABType();
 	ABType newType1 = new ABType();
}
		:		#(t1:B_SUCH
// Un ensemble de variable
{
	pushScope("SUCH");
}
					lv1:list_var
// Le typage des variables est realisee et newType permet d'avoir un BILAN
// si on a BOOL tout va bien sinon on aura un type ERREUR.
					type  = predicate
{
	compatibilityReduceType (#t1, type, new BOOL());

 // Q:
 //  Peux t on re-evaluer list_var pour verificaton du typage ?? 
 //  si oui faut le faire ....
	list_var_bis(lv1);

	type = lv1.getBType();
	type.setLineNumber(#t1.getLineNum());				// On reaffecte le numero de ligne

}

// Ce predicat determine l'action et le retour d'un type, BOOL indique que 
// tout va bien sinon on a un type ERREUR
			newType1 = predicate

{
 	#t1.setBType(type);

 	popScope();
}
		)
;


/**
 * Rien a faire car on a une liste de variable
 * Faut quand meme ajouter les variables a l'environnement .....
 *
 * Prevoire l'extension B' liste_typed_identifier
 */

list_var	:
                #(t1:B_LPAREN 
			list_identifier  
		)
	|	list_identifier 
		;

list_identifier	:
                #(tt:B_COMMA
					a1:list_identifier 
					a2:list_identifier 
{
	ABType tmp1 = #a1.getBType();
	ABType tmp2 = #a2.getBType();
	ABType type = new PROD(tmp1, tmp2);
	type.setLineNumber(#tt.getLineNum());
 	#tt.setBType(type);
}
				)	
|		#(name:B_IDENTIFIER 
{
	ABType tmp = new ABType();
	addId(#name,tmp);
	tmp.setLineNumber(#name.getLineNum());			// On reaffecte le numero de ligne
 	#name.setBType(tmp);
}
		)
;


/**
 * Cette seconde version a pour but de recuperer le typage
 * en fait, le premier tour permet de declarer les variables
 * le second de recuperer les informations de typage et de decelees 
 * les variables non typees et non utilisees
 **/

list_var_bis		:	
            #(t1:B_LPAREN
                list_identifier_bis  
            )
        |	list_identifier_bis
		;

list_identifier_bis	
{
	ABType newType = new ABType();
}
		:
		 #(tt:B_COMMA
			a1:list_identifier_bis
			a2:list_identifier_bis
{
	ABType tmp1 = #a1.getBType();
	ABType tmp2 = #a2.getBType();
	ABType type = new PROD(tmp1, tmp2);

 	#tt.setBType(type);
}
				)	
|	id_bis
|	#(t1:B_INSET
		name1	: id_bis
		newType = predicate
{

//  BJL a voir
//	compatibilityReduceType (#name1, newType, new BOOL());

  	#t1.setBType(#name1.getBType());				   // Transfert de type
}
	)
|	#(n1:A_FUNC_CALL
	 	n2:id_bis
{
        #n1.setBType(#n2.getBType());                  // Transfert de type
}
         )

;

id_bis    :
        name:B_IDENTIFIER
{
	ABType tmp = new ABType();
    	tmp      = searchId(#name,tmp);

	if (tmp.IsDefined() != true)
	{

		String StrTmp = errors.IdNotDefined(#name.getText()+"(line "+name.getLineNum()+")");
		System.err.println(StrTmp);				// Impression a l'ecran de l'erreur
        	tmp = new Typing_ERROR(StrTmp);		    		// On a un PB,on construit le type adequat

	}

	tmp.setLineNumber(#name.getLineNum());				// On reaffecte le numero de ligne
	#name.setBType(tmp);

}
    |	#(B_POINT
// BJL 
// A voir .... 
            B_IDENTIFIER 
            B_IDENTIFIER
        )
;

// End of file BTyping.g
