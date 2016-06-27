
// 
// 	Author		:	Boulanger Jean-Louis
//  	Email       	:   	jean.louis.boulanger@gmail.com
// 	File		:	B.g
//	Date		:	Creation 11/10/1999
//	Descripton	:	Another B Parser written in ANTLR 2.7.0 and more
//
//	
//	Copyright 1999-2010 Boulanger Jean-Louis
//

// This file is part of ABTOOLS.

//    ABTOOLS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU LESSER General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.

//    ABTOOLS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU LESSER General Public License for more details.

//    You should have received a copy of the GNU LESSER General Public License
//    along with Foobar.  If not, see <http://www.gnu.org/licenses/>. 

// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.



header 
{
    package ABTOOLS.GRAMMAR;
}


/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

// Import the necessary classes 
//-----------------------------------------------------------------------------

{
// Usefull packages
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
        import ABTOOLS.ANTLR_TOOLS.*;
}


// Define a Parser, calling it BParser
//-----------------------------------------------------------------------------
class BParser extends ExpressionParser;
options 
{
    importVocab                 =   Expression;
    exportVocab                 =   BLexer;		// Call its vocabulary "B" 

    //tokenVocabulary=B;	    			// Call its vocabulary "B" 
    //vtpLanguage = "exp";

    k                           =   2 ;		// k tokens lookahead


    codeGenMakeSwitchThreshold  =   2;      // Some optimizations
    codeGenBitsetTestThreshold  =   3;

    //defaultErrorHandler = false;          // Don't generate parser error handlers

    buildAST 	                = true;
    ASTLabelType 	        = "MyNode";
} 


// Indtroduce some behaviours....

{
	protected String module = "(parser part) B.g";

// Error Management
	ErrorMessage errors = new ErrorMessage();
	
	public String path = "";
	
	public void setPath(String p){
	   path = p;
	}
	
	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}

// Debug enable or disabled
	protected DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}
	
	// Switch de lecture des machine abstraites liees
	protected boolean LoadLinked = false;

	public ListAM listAM = new ListAM();

	public ListAM getListAM()
	{
		return listAM;
	}


	public void add_AM (MyNode t1, String type)
	{
		if ( type.compareTo("PROMOTES") != 0 )
		{
		  String ext = searchExtension(path, #t1.getText());
		  listAM.Add_AM(new LinkedAM(#t1.getText(), type, ext, path));
		  //listAM.Add_AM(new LinkedAM(#t1.getText(), type)); 
		}
	}


// July 2001
// We prepare the PO generation by memorization of principals terms

	public ASTterme previous_gop = new ASTterme(debug);

	public ASTterme getPreviousGOP()
	{
		return previous_gop;
	}


// April 2002
// Semantic Control
	public void RU1 (MyNode node)
	{
// Unicity of each variable in the list node
	}


// January 2010
// Metrics management

	public Metrics metrics = null;
	
	public Metrics getMetrics()
	{
		if (metrics != null)
		{
			return metrics;
		}
		else
		{
		// error .....
			return null;
		}
	}

	public void setMetrics (Metrics m)
	{
		metrics = m;
	}

// permet de gerer le type de mesure en cours
	protected int metricstype = 0;
	
	protected int unknown    	= 0;
	protected int mvariables 	= 1;
	protected int mconstants 	= 2;
	protected int maconstants 	= 3;
	protected int mset 		= 4;
	protected int mservices  	= 5;
	protected int mcvariables 	= 6;
		
	protected void ChooseMetrics (int mtype)
	{
		metricstype = mtype;
	}
	
	protected void AddMetrics()
	{
		if (metricstype == mvariables)
		{
			metrics.addVarGlobal();
		}
		else if (metricstype == mconstants)
		{
			metrics.addConstant();
		}
		else if (metricstype == maconstants)
		{
			metrics.addAConstant();
		}
		else if (metricstype == mservices)
		{
			metrics.addService();
		}
		else if (metricstype == mset)
		{
			metrics.addSet();
		}
		else if (metricstype == mcvariables)
		{
			metrics.addCVarGlobal();
		}
		else
		{
		}
	}
		
  /* Searches the file 'name' with extension '.mch'
   *  if the file doesn't exist, search the file 'name' with 
   *  extension 'ref'. Returns the extension found.
   */
   public String searchExtension(String path, String name){
   String extension = "";
   File file = new File(path,name+".mch");
   if (file.isFile()){
    extension = ".mch";
   }
   else{
    File file2 = new File(path,name+".ref");
    if (file2.isFile()){
     extension = ".ref";
    }
   }     
   return extension;
   }
	
}	

// Grammar begin  HERE
// -------------------

/**
 *	La regle de base permettant de realiser le parsing
 **/	

// Root Rule
// Le booleen LoadLinked permet d'indiquer qu'on est authorise 
// ou non a lire les machines abstraites liees a cette machine.
composant [boolean loadLinked] 	:
{
	// We memorise the global decision
	LoadLinked = loadLinked;
}
		  		machine
			|	refinement
			|	implementation       
			|	/* Empty source files are *not* allowed.  */
{
            System.err.println ( "ABParser Warning : Empty source file!" );
            errors.WSyntaxic   ( module, "The file is empty" );
}
    ;

/**
 * THREE type of B composants
 * simple name or with one or more parameters
 **/

// On a ajoute une seconde decalaration de la clause DEFINITION
// car il existe des exemples ou elle se trouve en fin de fichier

machine		:
        "MACHINE"^ 
        paramName
        (	constraints
        |	mchlink
        |	sets
        |	constants
        |	aconstants
        |	properties
        |	variables
        |	cvariables
        |	invariant
        |	assertions
        |	definitions_mch
        |	initialisation_mch
        )*
        (	operations_mch  (definitions_mch )? )?
        "END"! 
        EOF!
;

refinement	:
        "REFINEMENT"^
        paramName 
        (	refines
        |	constraints
        |	reflink
        |	sets 
        |	aconstants
        |	constants
        |	properties
        |	variables
        |	cvariables
        |	invariant
        |	assertions
        |	definitions_ref
        |	initialisation_ref
        )*
        (	operations_ref  (definitions_ref )? )?
        "END"!
        EOF!
;

implementation	:
        "IMPLEMENTATION"^
        paramName
        (	refines 
        |	implink
        |	values
        |	sets			// Et oui il y a des ensembles
        |	constants
        |	properties
        |	variables
        |	cvariables
        |	invariant
        |	assertions
        |	definitions_imp
        |	initialisation_imp
        )*
        (	operations_imp  (definitions_imp )? )?
        "END"!
        EOF! 	
;

// For all Name with parameters

paramName	:
        aa:B_IDENTIFIER 
{ 
	#aa.memorizeOpname(#aa.getText());
}
        (
            bb:B_LPAREN^
                listIdentifier
{ 
	#bb.memorizeOpname(#aa.getText());
}
            B_RPAREN!
        )?
;

paramRename	:
    nameRenamed
{
    	AddMetrics();
}
    (
        B_LPAREN^
            listIdentifier
        B_RPAREN!
    )?
;
	
listIdentifier	:
    nameRenamed
{
    	AddMetrics();
}
    ( 
        B_COMMA^ 
        nameRenamed
{
    	AddMetrics();
}
    )*
;

constraints	:
    "CONSTRAINTS"^	
        t1:expression
{
	previous_gop.putC(#t1);
}
;

mchlink		:
        uses
    |	includes
    |	sees
    |	extendeds
    |	promotes
;

reflink		:
// pas de clause USES dans les REFINEMENTS d'apres Manuel de REF B
// a verifier
//				uses
//			|
        includes
    |	sees
    |	extendeds
    |	promotes
;

implink	:
        sees
    |	imports
    |	extendeds
    |	promotes
;

extendeds :
        "EXTENDS"^
        listInstanciation["EXTENDS"]
;

uses :
        "USES"^
        listNames["USES"]
;

includes :
        "INCLUDES"^ 	listInstanciation ["INCLUDES"]
;

sees :
        "SEES"^ 	listNames["SEES"]
;


/**
 * We control the clause name for verify if it's an access 
 **/
listNames[String type]	:
        nameRenamedWithSave[type] 
        (
            B_COMMA^ 
            nameRenamedWithSave[type]
        )*
;

// Dans cette regle, nous introduisons la gestion de la table des machines

nameRenamedWithSave[String type]	:
        (B_IDENTIFIER B_POINT) => B_IDENTIFIER (B_POINT^ nameRenamedWithSave[type] )
    |
        t1:B_IDENTIFIER
{
		add_AM((MyNode)#t1,type);
}
    ;

listParamNames	:
        paramName 	
        (
            B_COMMA^ 
            paramName
        )*
    ;

listInstanciation [String type]:
        paramRenameValuation[type] 
        (
            B_COMMA^
            paramRenameValuation[type]
        )*	
    ;

paramRenameValuation[String type] :
        nameRenamedWithSave[type] 
        (
            B_LPAREN^ 
            list_New_Predicate 
            B_RPAREN!
        )* 
    ;


imports		:
        "IMPORTS"^
            listInstanciation ["IMPORTS"]
    ;


promotes	:
        "PROMOTES"^
            listNames["PROMOTES"]
    ;


refines		:
        "REFINES"^ 
            t1:B_IDENTIFIER
{
		add_AM(#t1,"REFINES");
}
    ;

// He oui il y a trois valeurs pour la clause CONSTANTS
constants	:
{
	ChooseMetrics(mconstants);
}
	(
            	"CONSTANTS"^
        | 	"CONCRETE_CONSTANTS"^
        |	"VISIBLE_CONSTANTS"^
        )
            listIdentifier
{
	ChooseMetrics(unknown);
}
    ;

aconstants	:
{
	ChooseMetrics(maconstants);
}
	(
            "ABSTRACT_CONSTANTS"^
        | 	"HIDDEN_CONSTANTS"^
        )
            listIdentifier
{
	ChooseMetrics(unknown);
}
    ;

sets:
{
	ChooseMetrics(mset);
}
        "SETS"^ sets_declaration
{
	ChooseMetrics(unknown);
}
;

// Extension B', on autorise soit le caractere ; ou le caractere , 
// cela permet de rendre uniforme la notation B

sets_declaration:
        set_declaration 
        ( 
            B_SEMICOLON^ 
            set_declaration
        )*
;

set_declaration :
{
	AddMetrics();
}
        B_IDENTIFIER
        (
            B_EQUAL^ 
            set_construction
        )?
;

set_construction:
        valuation_set
// Etrange a verifier si necessaire ????
        ( 
            (	
                B_MULT^ 
            | 	B_ADD^ 
            | 	B_MINUS^
            )
            valuation_set 
        )*
    ;

valuation_set	:
        B_CURLYOPEN^ list_couple B_CURLYCLOSE!
    |	basic_value (B_RANGE^ 	basic_value)?		// pour gerer les interval 1..10+1
    |	is_record
    |	basic_sets
    |	B_IDENTIFIER
    ;

list_couple	:
        couple_parent	
        (
            B_COMMA^
            couple_parent 
        )*
    ;

couple_parent	:
        B_LPAREN^ extended_couple B_RPAREN!
    |	couple
    ;

extended_couple :
        a_set_value 
        ( 
            (
                B_MAPLET^ 
            | 	B_COMMA^
            ) 
            a_set_value
        )*
    ;

couple	:
        a_set_value 
        (
            B_MAPLET^ 
            a_set_value
        )*
    ;

a_set_value	:
        B_IDENTIFIER
    |	b:B_MINUS^ 
        // declarations are ambiguous with "- a"
{
	#b.setType(UNARY_MINUS);
}
        B_NUMBER		// Il faut penser au -x
    |	B_NUMBER
    |	"TRUE"^
    |	"FALSE"^ 
;

values	:
        "VALUES"^ 
            list_valuation
;

list_valuation	:
		set_valuation 
        (
            B_SEMICOLON^ 
            set_valuation
        )*
    ;


set_valuation	:
		B_IDENTIFIER B_EQUAL^ 
            new_set_or_constant_valuation
    ;

// On prend en compte le fait qu'on ait 
//	un ensemble de base
//	un ensemble de valeur
//	un intervalle avec ou sans parenthese		=> (1..5) est surement du B'

new_set_or_constant_valuation	:
        basic_sets
    |	(B_CURLYOPEN^ list_couple) => B_CURLYOPEN^ list_couple B_CURLYCLOSE!

// J'ai ajoute le fait qu'on puisse construire un tableau
    |	(bases B_MULT ) => bases B_MULT^ bases  // ATTENTION : tableaux a plusieurs dimensions ? A VOIR
    |	basic_value ( B_RANGE^ basic_value )?
;

set_interval_value:
        B_IDENTIFIER B_EQUAL^ interval_declaration
;

interval_declaration:
        basic_value B_RANGE^ basic_value
;

set_rename_value:
        B_IDENTIFIER B_EQUAL^ B_IDENTIFIER
;

properties	:
        "PROPERTIES"^ t1:expression
{
	previous_gop.putP(#t1);
}
;

/** 
 * Declaration of a variable.
 **/
variables	:
{
	ChooseMetrics(mvariables);
}
        (
            	"VARIABLES"^
        |	"ABSTRACT_VARIABLES"^
        |	"VISIBLE_VARIABLES"^
        ) 
        listIdentifier
{
	ChooseMetrics(unknown);
}
;

invariant	:
        "INVARIANT"^ 
            t1:expression
{
	previous_gop.putI(#t1);	// We memorize the node Invariant for GOP
}
;

cvariables	:
{
	ChooseMetrics(mcvariables);
}
        (
            	"HIDDEN_VARIABLES"^
        |	"CONCRETE_VARIABLES"^
        )
        listIdentifier
{
	ChooseMetrics(unknown);
}
;

// Cette regle complementaire est utilisee pour la lectures des fichiers 
// annexes de definitions introduit par STERIA dans l'atelier B
// On ne guarde pas le mot cles DEFINITION car on est deja dans la clause DEFINITION


definitions_mch_bis:
		"DEFINITIONS"! 
            list_def_mch
;

definitions_mch	:
		"DEFINITIONS"^ 
            list_def_mch
    ;

list_def_mch	:
		definition_mch 
        (
            c:B_SEMICOLON^
// declarations are ambiguous with "a;b"
{
	#c.setType(LIST_DEF);
} 
            definition_mch
        )*
;

// Attention deux cas
//	- Une definition
//	- un acces a un fichier de definition
//	deux cas:
//		-- " too "	pris en charge 
//		-- < too >	a faire

definition_mch	:
    paramName B_DOUBLE_EQUAL^ formalText_mch
|	f:B_ASTRING!
    {
                	// create lexer to handle include
                	String name = f.getText();

			try {
				BLexer lexer = new BLexer(new FileInputStream(new File(name)));

				// Pour mettre en place notre definition des noeuds....
				lexer.setTokenObjectClass("MyToken");

				BParser parser = new BParser(lexer);

				// Pour mettre en place notre definition des noeuds....
			      	parser.setASTNodeType(MyNode.class.getName());
			        MyNode.setTokenVocabulary("BTokenTypes");
 
				// On appel la lecture d'un fichier de definition de type MACHINE
				parser.definitions_mch_bis();

				// On recupere l'arbre syntaxique 
				AST aa = parser.getAST();

				// On contruit le nouvel arbre syntaxique
				#definition_mch = (MyNode) #aa;

			}                                   
 			catch (FileNotFoundException fnf) 
			{
        		      	System.err.println("Exception : cannot find file "+name);
				#definition_mch = (MyNode) #f ;
            }
    }
;

// L'ordre suivant est preferable car TOTO == f(x) peut etre soit un predicate soit un func_call (substitution)
// et si on a TOTO = f(x)~ avec en premier la reconnaissance de substitution on a une erreur de parsing 

formalText_mch	:
        expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);
} 
    |	substitution_mch
{
	## = #( #[SUBST_DEF, "SUBST_DEF"], ##);	
} 
//			|	operationMch
;



// Cette regle complementaire est utilisee pour a lectures des fichiers 
// annexes de definitions introduit par STERIA dans l'atelier B
// On ne guarde pas le mot cles DEFINITION car on est deja dans la clause DEFINITION

definitions_ref_bis:
		"DEFINITIONS"! 
            	list_def_ref
;

definitions_ref	:
		"DEFINITIONS"^
	        list_def_ref
;

list_def_ref	:
        definition_ref 
        (
            c:B_SEMICOLON^  {#c.setType(LIST_DEF);} 
            definition_ref
        )*
;

// Attention deux cas
//	- Une definition
//	- un acces a un fichier de definition

definition_ref	:
        paramName B_DOUBLE_EQUAL^ formalText_ref
    |	f:B_ASTRING!
    {
                	// create lexer to handle include
                	String name = f.getText();

			try {
				BLexer lexer = new BLexer(new FileInputStream(new File(name)));

				// Pour mettre en place notre definition des noeuds....
				lexer.setTokenObjectClass("MyToken");

				BParser parser = new BParser(lexer);

				// Pour mettre en place notre definition des noeuds....
			      	parser.setASTNodeType(MyNode.class.getName());
			        MyNode.setTokenVocabulary("BTokenTypes");
 
				// On appel la lecture d'un fichier de definition de type MACHINE
				parser.definitions_ref_bis();

				// On recupere l'arbre syntaxique 
				AST aa = parser.getAST();

				// On contruit le nouvel arbre syntaxique
				#definition_ref = (MyNode) #aa;

			}                                   
 			catch (FileNotFoundException fnf) 
			{
                System.err.println("Exception : cannot find file "+name);
				#definition_ref = (MyNode) #f ;
            }
    }
;


formalText_ref	:
        expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);	
} 
    |	substitution_ref
{
   ## = #( #[SUBST_DEF, "SUBST_DEF"], ##);	   
} 
//			|	operationRef
;

// Cette regle complementaire est utilisee pour a lectures des fichiers 
// annexes de definitions introduit par STERIA dans l'atelier B
// On ne guarde pas le mot cles DEFINITION car on est deja dans la clause DEFINITION

definitions_imp_bis:
		"DEFINITIONS"! 
            list_def_imp
;

definitions_imp	:
        "DEFINITIONS"^ 
            list_def_imp
;

list_def_imp	:
        definition_imp 
        (
            c:B_SEMICOLON^  
{
	#c.setType(LIST_DEF);
} 
            definition_imp
        )*
;

// Attention deux cas
//	- Une definition
//	- un acces a un fichier de definition

definition_imp	:
    paramName B_DOUBLE_EQUAL^ formalText_imp
|	f:B_ASTRING!
    {
                	// create lexer to handle include
                	String name = f.getText();

			try {
				BLexer lexer = new BLexer(new FileInputStream(new File(name)));

				// Pour mettre en place notre definition des noeuds....
				lexer.setTokenObjectClass("MyToken");

				BParser parser = new BParser(lexer);

				// Pour mettre en place notre definition des noeuds....
			      	parser.setASTNodeType(MyNode.class.getName());
			        MyNode.setTokenVocabulary("BTokenTypes");
 
				// On appel la lecture d'un fichier de definition de type MACHINE
				parser.definitions_imp_bis();

				// On recupere l'arbre syntaxique 
				AST aa = parser.getAST();

				// On contruit le nouvel arbre syntaxique
				#definition_imp = (MyNode) #aa;

			}                                   
 			catch (FileNotFoundException fnf) 
			{
                System.err.println("cannot find file "+name);
                #definition_imp = (MyNode) #f ;
            }       
    }
;

formalText_imp	:
        expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);	
} 
    |   substitution_imp
{
    ## = #( #[SUBST_DEF, "SUBST_DEF"], ##);
} 
//			|	operationImp
;

assertions	:
        "ASSERTIONS"^ 
        list_assertions
;

list_assertions	:
	expression 
        (
            B_SEMICOLON^ 
            expression
        )*
;

initialisation_mch:
        "INITIALISATION"^ 
        t1:substitution_mch
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

initialisation_ref:
	"INITIALISATION"^ 
        t1:parallele_ref
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

initialisation_imp:
	"INITIALISATION"^ 
            t1:sequential
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

operations_mch	:
		"OPERATIONS"^

            listOperationMch
    ;

listOperationMch:
		operationMch 
        (
            B_SEMICOLON^  
            operationMch
        )*				
    ;

operationMch	:
        operationHeader
            c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_mch
    ;

operations_ref	:
	"OPERATIONS"^ 
            listOperationRef
    ;

listOperationRef:
	operationRef 
        (
            B_SEMICOLON^ 
            operationRef
        )*
    ;

operationRef	:
	operationHeader 
        c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_ref
		;

operations_imp	:
		"OPERATIONS"^ 
            listOperationImp
    ;

listOperationImp:
		operationImp 
        (
            B_SEMICOLON^ 
            operationImp
        )*
    ;

operationImp	:
		operationHeader 
        c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_imp
    ;

operationHeader :
{
	ChooseMetrics(mservices);
}
        paramName
{
	ChooseMetrics(unknown);
}
    |	listIdentifier bb:B_OUT^
{
	ChooseMetrics(mservices);
} 
    		pp:paramName 
{
	// Memorization pour introduction dans la table des symboles
	// ce n'est pas simple car les parametres de sortie sont introduit 
	// avant le nom de l'operation
 	#bb.memorizeOpname( #pp.getOpname() );
 	ChooseMetrics(unknown);
}
    ;

// The Generalised Substitution Language

substitution_mch:
		parallele_mch
    ;

parallele_mch	:
        subst_mch 
        (	c:B_PARALLEL^ 
// declarations are ambiguous with "a || b" in predicate
{
	#c.setType(PARALLEL);
} 
            subst_mch
        )*
;


subst_mch	:
    	identite
|	substitution_block_mch
|	substitution_precondition_mch
|	assert_mch
|	anif_mch
|	choice_mch
|	substitution_unbounded_choice_mch
|	substitution_selection_mch
|	let_mch
|	case_mch
|	simple_affect_ref
;

// the Generalised Substitution Language

substitution_ref:
    subst_ref
;

parallele_ref	:
    subst_ref 
    (
        (	c:B_PARALLEL^ 	{#c.setType(PARALLEL  );}  // declarations are ambiguous with "a||b" in predicate
        |	d:B_SEMICOLON^	{#d.setType(SEQUENTIAL);}  // declarations are ambiguous with "a;b" in predicate
        ) 
        subst_ref
    )*
;


subst_ref	:
    identite
|	substitution_block_ref
|	substitution_precondition_ref
|	assert_ref
|	anif_ref
|	choice_ref
|	substitution_unbounded_choice_ref
|	substitution_selection_ref
|	case_ref
|	let_ref
|	simple_affect_ref
|	var_ref
;

substitution_imp:
    subst_imp
;


sequential	:
    subst_imp 
    (
        d:B_SEMICOLON^ 
// declarations are ambiguous with "a;b" in predicate
{
    #d.setType(SEQUENTIAL);
}
        subst_imp
    )*
;	


subst_imp	:
                identite
			|	substitution_block_imp
			|	assert_imp
			|	anif_imp
			|	case_imp
			|	var_imp
			|	while_loop
			|	simple_affect
		;


identite	:
    "skip"^
;


substitution_block_mch	:
    "BEGIN"^
        substitution_mch
    "END"!
;


substitution_block_ref	:
    "BEGIN"^
        parallele_ref
    "END"!
;


substitution_block_imp	:
    "BEGIN"^
        sequential
    "END"!
;


substitution_precondition_mch	:
    "PRE"^
        expression
    "THEN"!
        substitution_mch
    "END"!
;


substitution_precondition_ref	:
    "PRE"^
        expression
    "THEN"!
        parallele_ref
    "END"!
;


assert_mch	:
    "ASSERT"^ 
        expression
    "THEN"! 
        substitution_mch 
    "END"!
;


assert_ref	:
    "ASSERT"^ 
        expression
    "THEN"! 
        parallele_ref 
    "END"!
;


assert_imp	:
	"ASSERT"^ 
        expression
    "THEN"! 
        sequential 
    "END"!
;


anif_mch	:
	"IF"^ 
        expression
        then_branch_mch 
        (elsif_branch_mch)*
        (e_branch_mch)?
    "END"!
;


then_branch_mch	:
    "THEN"^ 
        substitution_mch 
;


elsif_branch_mch:
    "ELSIF"^
        expression 
    "THEN"!
        substitution_mch
;


anif_ref	:
	"IF"^ expression
        then_branch_ref
        (elsif_branch_ref)*
        (e_branch_ref)?
    "END"!
;


then_branch_ref	:
    "THEN"^
        parallele_ref
;


elsif_branch_ref:
    "ELSIF"^ 
        expression  
    "THEN"! 
        parallele_ref
;


anif_imp	:
    "IF"^ expression
        then_branch_imp
        (elsif_branch_imp)*
        (e_branch_imp)?
    "END"!
;


then_branch_imp	:
    "THEN"^ 
        sequential 
;


elsif_branch_imp:
    "ELSIF"^ 
        expression 
    "THEN"! 
        sequential 
;


choice_mch	:
    "CHOICE"^ 
        list_or_mch 
    "END"!
;


list_or_mch	:
    substitution_mch  
    (
        "OR"^ 
        substitution_mch
    )*
;


choice_ref	:
    "CHOICE"^ 
        list_or_ref
    "END"!
;


list_or_ref	:
    parallele_ref 
    (
        "OR"^ 
        parallele_ref
    )*
;


case_mch	:
    "CASE"^	predicate "OF"!
        branche_either_mch
        (branche_or_mch)*
        (branche_else_mch)?
        "END"!
    "END"!
;

branche_either_mch:
    "EITHER"^
 	    predicate
 	"THEN"!
        substitution_mch 
;

branche_or_mch	:
    "OR"^
        predicate
 	"THEN"!
        substitution_mch
;

branche_else_mch:
    "ELSE"^
        substitution_mch
;


case_ref	:
    "CASE"^	
        predicate 
    "OF"!
        branche_either_ref
        (branche_or_ref)*
        (branche_else_ref)?
        "END"!
    "END"!
;


branche_either_ref:
    "EITHER"^
 	    predicate
 	"THEN"!
	    parallele_ref
;


branche_or_ref	:
    "OR"^
        predicate
 	"THEN"!
        parallele_ref
;


branche_else_ref:
    "ELSE"^
        parallele_ref
;


case_imp	:
    "CASE"^	
        predicate 
    "OF"!
        branche_either_imp
        (branche_or_imp)*
        (branche_else_imp)?
        "END"!
    "END"!
;


branche_either_imp:
    "EITHER"^
 	    predicate
 	"THEN"!
	    sequential
;


branche_or_imp	:
    "OR"^
        predicate
 	"THEN"!
        sequential
;


branche_else_imp:
    "ELSE"^
        sequential
;


substitution_unbounded_choice_mch		:
    "ANY"^ 
        listIdentifier 
    "WHERE"! 
        expression 
    "THEN"!  
        substitution_mch
    "END"!
;


substitution_unbounded_choice_ref:
    "ANY"^ 
        listIdentifier 
    "WHERE"! 
        expression
    "THEN"!  
        parallele_ref
    "END"!
;


substitution_selection_mch:	
    "SELECT"^ 
        expression
    "THEN"!
        substitution_mch
        (when_branch_mch )*
        (e_branch_mch)?
    "END"!
;


when_branch_mch:
    "WHEN"^ 
        expression 
    "THEN"! 
        substitution_mch
;


e_branch_mch	:
    "ELSE"^ 
        substitution_mch
;


substitution_selection_ref:	
    "SELECT"^ 
        expression
    "THEN"!
        parallele_ref
        (when_branch_ref)*
        (e_branch_ref)?
    "END"!
;

when_branch_ref	:
    "WHEN"^ 
        expression 
    "THEN"! 
        parallele_ref
;

e_branch_ref	:
    "ELSE"^ 
        parallele_ref
;

e_branch_imp	:
    "ELSE"^ 
        sequential
;

let_ref		:
    "LET"^
        list_identifier 
    "BE"!
        list_equal 
    "IN"!
        parallele_ref
    "END"!
;

let_mch		:
    "LET"^
        list_identifier 
    "BE"!
        list_equal 
    "IN"!
        substitution_mch
    "END"!
;

variant_or_no	:
    "VARIANT"^ 	    
        predicate 	
    "INVARIANT"^
 	    expression
|	"INVARIANT"^
 	    expression
 	"VARIANT"^
  	    predicate 
;

while_loop	:
    "WHILE"^	
        predicate
    "DO"!
        sequential
        variant_or_no
    "END"!
;

list_equal	:
    an_equal 
    (
        B_AND^ 
        an_equal
    )*
;

an_equal	:
    B_IDENTIFIER
        B_EQUAL^ 
    predicate
		;

// afin de prendre en compte 
//	soit f (x,y,z)
//	soit f(x) [z]
//	soit f(x)~
//	soit f(x)~[z]
//	soit f~(xx)
func_call	: 		
    nameRenameDecoratedInvertedParamInverted 
    (
        c:B_BRACKOPEN^ 
// declarations are ambiguous with "[b]" in sequence definition
{
	#c.setType(APPLY_TO);
} 
        expression
        B_BRACKCLOSE!
    )?
;

nameRenameDecoratedInvertedParamInverted:
    nameRenameDecoratedInvertedParam 
    (
        B_TILDE^
    )?
;

nameRenameDecoratedInvertedParam: 
    nameRenameDecoratedInverted 
    (
        B_LPAREN^ 
            list_New_Predicate 
        B_RPAREN!
    )* 
;

nameRenameDecoratedInverted:
	nameRenamedDecorated 
    (
        B_TILDE^
    )?
;

// il peut y avoir d'autre parametre 
// parse the formal parameter declarations.
applyto		:
    B_BRACKOPEN^ 
        predicate 
    B_BRACKCLOSE!
;

a_func_call_quoted:
    a_func_call 
    (
        B_QUOTEIDENT^ 
        a_func_call
    )*		// D'apres STERIA ce serait un identificateur
//{
// 	## = #( #[A_FUNC_CALL, "a_func_call"], ##);
//}
    ;

a_func_call:
	nameRenamed 
	(	c:B_LPAREN^ 
// parse the formal parameter declarations.
{
	#c.setType(FUNC_CALL_PARAM);
} 
		listPredicate 
		B_RPAREN! 
	)*
;

list_func_call	:
	a_func_call_quoted 
	(
        	B_COMMA^ 
        	a_func_call_quoted
   	 )*
;

// Deux cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a'b
// il y avait list predicate et comme predicate contient l'operateur , ce n'est plus la peine ...
simple_affect	:		
        (list_func_call B_SIMPLESUBST) 	=> l1:list_func_call 	B_SIMPLESUBST^ 	predicate
{
	RU1(#l1);
}
    |	(list_func_call B_OUT) 		=> l2:list_func_call	B_OUT^	 	func_call
{
	RU1(#l2);
}
    |	a_func_call_quoted
    ;

// Quatre cas 
//	soit 	a,b,c := f(), g(), h()
//	soit	a,b,c <-- f(x)
//	soit	a,b,c :( P )
//	soit	a,b,c :: P
//	soit	a'b'c
simple_affect_ref:
		(list_func_call B_SIMPLESUBST) 	=> l1:list_func_call 	B_SIMPLESUBST^ 			predicate 
{
	RU1(#l1);
}
	|	(list_func_call B_OUT) 		=> l2:list_func_call	B_OUT^	 			func_call
{
	RU1(#l2);
}
	|	(list_func_call B_INSET) 	=> l3:list_func_call	d:B_INSET^ {#d.setType(INSET);}	B_LPAREN! expression  B_RPAREN!
{
	RU1(#l3);
}
//	|	(list_func_call B_BECOME) 	=> list_func_call	B_BECOME			expression  B_RPAREN!
	|	(list_func_call B_BECOME_ELEM)	=> l4:list_func_call	B_BECOME_ELEM^ 			expression
{
	RU1(#l4);
}
	|	a_func_call_quoted
;

// extension B'
// 	VAR x,y,z IN ...
//	VAR x:P, y:Q, z:R IN ...
var_ref	:
	"VAR"^
            listIdentifier
        "IN"!
            parallele_ref
        "END"!
;

var_imp	:
	"VAR"^
            listIdentifier
        "IN"!
            sequential
        "END"!
;


// Somes imaginaries tokens for conflict
dummy :
    |   PARALLEL
    |   OP_DEF
    |   INSET
    |   FUNC_CALL_PARAM
    |   SEQUENTIAL
    |   EXP_DEF
    |   LIST_DEF
    |   SUBST_DEF
    ;

// My B lexer
//--------------


// Import the necessary classes 
//-----------------------------------------------------------------------------

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	import ABTOOLS.DEBUGGING.*;
    	import ABTOOLS.ANTLR_TOOLS.*;
}

class BLexer extends ExpressionLexer;
options 
{
    importVocab                 =   BLexer;
    exportVocab		            =   B	   ;            

	caseSensitive		        =	true	;		    // In B, the case is signifiant
	caseSensitiveLiterals	    =	true	;		    // In B, the case is signifiant

    //charVocabulary            = '\0' .. '\u00FF';
	charVocabulary              =   '\u0003' .. '\uFFFF';
	codeGenBitsetTestThreshold  =   20;

	testLiterals	    	    =	true	;		    // automatically test for literals
    k			                =	10	;               // k characters of lookahead
}

// Introduce some behaviours....

{
 	String module = "(lexer part) B.g";

	protected int 	  tokcolumn   		= 1 ;
	protected int 	  column 	    	= 1 ;
  	protected int 	  tokenNumber  		= 0;
  	protected boolean countingTokens 	= true;


	public void consume() 
	throws CharStreamException { 
	  try {      // for error handling

		if (inputState.guessing > 0)
		{
		 if (text.length() == 0)
		 {	// remenber token start column
			tokcolumn = column;
			System.err.println("Column "+tokcolumn); 
		 }

		 if (LA(1)=='\n') 
			{column = 1;}
		 else
			{column++ ;}
		}
		super.consume();
	   }
	   catch (CharStreamException ignore) 
	   {
	   }
	}

	protected Token makeToken(int t)
	{   
		if ( t != Token.SKIP && countingTokens) 
		{
            		tokenNumber++;
        	}
		MyToken tok = (MyToken) super.makeToken(t);
		tok.setColumn(tokcolumn);
        	tok.setTokenNumber(tokenNumber);
		return tok;
	}
	
	public Metrics metrics = null ;
	
	public Metrics getMetrics()
	{
		if (metrics != null)
		{
			return metrics;
		}
		else
		{
		// error .....
			return null;
		}
	}

		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
	
}

//B_MACHINE 		: "MACHINE"		;
//B_REFINEMENT		: "REFINEMENT"		;
//B_IMPLEMENTATION	: "IMPLEMENTATION"	;
//B_BEGIN		: "BEGIN"		;
//B_END			: "END" 		;



//B_CONSTRAINTS		: "CONSTRAINTS"	;

//B_EXTEND		: "EXTENDS"		;
//B_USES		    : "USES"		;
//B_INCLUDES		: "INCLUDES"	;
//B_PROMOTES		: "PROMOTES"	;
//B_SEES		    : "SEES"		;
//B_IMPORTS		    : "IMPORTS"		;

//B_REFINES	    	: "REFINES"		;

//B_CONSTANTS		: "CONSTANTS"		| "CONCRETE_CONSTANTS"	| "VISIBLE_CONSTANTS" ;
//B_ACONSTANTS		: "ABSTRACT_CONSTANTS"	| "HIDDEN_ONSTANTS"	;
//B_SETS		    : "SETS"		;
//B_VALUES	    	: "VALUES"		;

//B_PROPERTIES		: "PROPERTIES"		;
//B_VARIABLES		: "VARIABLES"		
//                  | "ABSTRACT_VARIABLES"	
//                  | "VISIBLE_VARIABLES";

//B_CVARIABLES		: "CONCRETE_VARIABLES"	| "HIDDEN_VARIABLES"	;
//B_INVARIANT		: "INVARIANT"		;
//B_VARIANT		    : "VARIANT"	    	;
//B_INITIALISATION	: "INITIALISATION"	;
//B_ASSERTIONS		: "ASSERTIONS"		;
//B_DEFINITIONS		: "DEFINITIONS"		;
//B_OPERATIONS		: "OPERATIONS"		;

//B_SKIP		    : "skip"		;
//B_PRE			    : "PRE"			;
//B_ASSERT		    : "ASSERT"		;
//B_THEN		    : "THEN"		;
//B_IF			    : "IF"			;
//B_ELSE		    : "ELSE"		;
//B_ELSIF	    	: "ELSIF"		;
//B_CHOICE		    : "CHOICE"		;
//B_OR			    : "OR"			;
//B_ANY			    : "ANY"			;
//B_WHERE		    : "WHERE"		;
//B_SELECT		    : "SELECT"		;
//B_WHEN		    : "WHEN"		;
//B_LET			    : "LET"			;
//B_BE			    : "BE"			;
//B_IN			    : "IN"			;
//B_VAR			    : "VAR"			;


// B_INT		    : "INT"			;
// B_INT1		    : "INT1"		;
// B_INTEGER		: "INTEGER"		;
// B_INTEGER1		: "INTEGER1"	;
// B_BOOL		    : "BOOL"		;
// B_NAT		    : "NAT"			;
// B_NAT1		    : "NAT1"		;
// B_NATURAL		: "NATURAL"		;
// B_NATURAL1		: "NATURAL1"	;

//B_CHAR		    : "CHAR"		;
//B_STRING		    : "STRING"		;

//B_FBOOL		    : "bool"		;

// February 2003
// En plus
SUBST_TO        : "[["
;

WS
options {
  	paraphrase = "white space";
}
    :	
        (	' '
		|	'\t'
		|	'\f'
		// handle newlines
		|	(	"\r\n"  // Evil DOS
			|	'\r'    // Macintosh
			|	'\n'    // Unix (the right way)
			)
			{ newline(); }
		)
{ 
	$setType(Token.SKIP);
}		 			// avant a virer des que possible { _ttype = Token.SKIP; }
	;


// multiple-line comments
ML_COMMENT
options 
{
  	paraphrase = "a comment";
}        :
	"/*"
        (	
/*
	'\r' '\n' can be matched in one alternative or by matching
    '\r' in one iteration and '\n' in another.  I am trying to
	handle any flavor of newline that comes in, but the language
	that allows both "\r\n" and "\r" and "\n" to all be valid
	newline is ambiguous.  Consequently, the resulting grammar
	must be ambiguous.  I'm shutting this warning off.
*/
            options 
            {
                generateAmbigWarnings=false;
    	    }
		   :
		        { LA(2)!='/' }? '*'
		   |	("/*") => ML_COMMENT
           	   |    NEWLINE
		   |	~('*'|'\n'|'\r')
	)*
        "*/" {$setType(Token.SKIP);
              metrics.addLineComment();}
|
	"/*?"
        (
/*
	'\r' '\n' can be matched in one alternative or by matching
	'\r' in one iteration and '\n' in another.  I am trying to
	handle any flavor of newline that comes in, but the language
	that allows both "\r\n" and "\r" and "\n" to all be valid
	newline is ambiguous.  Consequently, the resulting grammar
	must be ambiguous.  I'm shutting this warning off.
*/
            options 
            {
                generateAmbigWarnings=false;
    	    }
            :
            	{LA(3)!='/' }? "?*"
           	|    ("/*") => ML_COMMENT
		|	NEWLINE
		|	~('?'|'\n'|'\r')  
	)*
        "?*/" {$setType(Token.SKIP); metrics.addLineComment();}
;

protected
NEWLINE:
	(
		'\n' |
		(
			'\r' ('\n')?
		)
	)
	{ 
		newline(); 
		metrics.addLine();
	}
	;

// End of file B.g
