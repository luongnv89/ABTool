header 
{
    package ABTOOLS.GRAMMAR;
}
{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}class BEventParser extends Parser;

options {
	importVocab= B;
	exportVocab= BEventLexer;
	k=7;
	codeGenMakeSwitchThreshold= 2;
	codeGenBitsetTestThreshold= 3;
	buildAST= true;
	ASTLabelType= "MyNode";
}

{
	String module = "BEvent.g";

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
	
	public String path = "";
  
  public void setPath(String p){
     path = p;
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
	}

	public void RA1 (MyNode node)
	{
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
		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
	
	/* Searches the file 'name' with extension '.mch'
   *  if the file doesn't exist, search the file 'name' with 
   *  extension 'ref'. Returns the extension found.
   */
   public static String searchExtension(String path, String name){
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
composant[boolean loadLinked] :{
	// We memorise the global decision
	LoadLinked = loadLinked;
}
(	  		machine
		|	refinement
		|	/* Empty sources files are *not* allowed.  */
        {						
			System.err.println ( "ABParser Warning : Empty source file!" );
			errors.WSyntaxic   ( module, "The file is empty" );
		}
)
;

machine :"SYSTEM"^
				paramName
				(	constraints
				 |	sees					// Not all link see MR
				 |	sets
				 |	constants
				 |	aconstants
				 |	properties
				 |	variables
				 |	cvariables
				 |	invariant
				 |	assertions
				 |	initialisation_mch
				 |	events_mch
				 |	modalities
				)*
			"END"! 
			EOF!
		;

events_mch :"EVENTS"^ 
            listEventMch
	;

listEventMch :eventMch 
        (   B_SEMICOLON^  
            eventMch
        )*
    ;

eventMch :nameRenamed
				c:B_EQUAL^ 
{
	#c.setType(OP_DEF);
} 
				substitution_event_mch
	;

substitution_event_mch :substitution_mch
	;

modalities :"MODALITIES"^
            modality
	;

modality :"ANY"^ 
            listIdentifier 
        "WHERE"! 
            expression
        "THEN"!
            event_list
        (
            maintain
        |	establish
        )
        "END"!
		|
        "BEGIN"^
            event_list
        (
            maintain
        |	establish
        )
        "END"!
		|
        "SELECT"^
            predicate
        "THEN"!
            event_list
            establish
        "END"!			
	;

maintain :"MAINTAIN"^
				predicate
			"UNTIL"!
				predicate
			"VARIANT"!
				a_variant
	;

establish :"ESTABLISH"^
				expression		// ce n'est pas un predicat
	;

event_list :"ALL"
		|	listIdentifier 
	;

refinement :"REFINEMENT"^
					paramName 
					(	refines
					 |	constraints
					 |	sees				// Just SEES in MR
					 |	sets 
					 |	aconstants
					 |	constants
					 |	properties
					 |	variables
					 |	cvariables
					 |	invariant
					 |	variant				// NEW 
					 |	assertions
					 |	initialisation_ref
					 |	events_ref
					 |	modalities
					)*
				"END"!
				EOF!
;

events_ref :"EVENTS"^
            listEventRef
    ;

listEventRef :eventRef 
        (
                B_SEMICOLON^  
                eventRef
        )*
    ;

eventRef :an_event_ref
				c:B_EQUAL^ 
{
	#c.setType(OP_DEF);
} 
				substitution_event_ref
		;

an_event_ref :nameRenamed (B_ref^ listNameRenamed)?
		;

listNameRenamed :nameRenamed (B_COMMA^ nameRenamed)*
		;

substitution_event_ref :parallele_ref
	;

protected variant :"VARIANT"^ 	a_variant
	;

protected a_variant :predicate
	;

protected substitution_block_mch :rr:"BEGIN"^
					substitution_mch
				("POST"!
					predicate
{
    #rr.setType(B_BEGIN_POST);
}
				)?
				"END"!
		;

protected substitution_block_ref :rr:"BEGIN"^
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_BEGIN_POST);
}
				)?
				"END"!
		;

protected substitution_precondition_mch :rr:"PRE"^
					expression
				"THEN"!
					substitution_mch
				("POST"!
					predicate
{
#rr.setType(B_PRE_POST);
}
				)?
				"END"!
		;

protected substitution_precondition_ref :rr:"PRE"^
					expression
				"THEN"!
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_PRE_POST);
}
				)?
				"END"!
		;

protected substitution_selection_mch :rr:"SELECT"^ 
					expression
				"THEN"!
					substitution_mch
				("POST"!
					predicate
{
            #rr.setType(B_SELECT_POST);
}
				)?
				"END"!
		;

protected substitution_selection_ref :rr:"SELECT"^ 
					expression
				"THEN"!
					parallele_ref
				("POST"!
					predicate
{
    #rr.setType(B_SELECT_POST);
}
				)?
				"END"!
		;

protected substitution_unbounded_choice_mch :rr:"ANY"^ 
					listIdentifier 
				"WHERE"! 
					expression 
				"THEN"!  
					substitution_mch
				("POST"!
					predicate
                       {#rr.setType(B_ANY_POST);}
				)?
				"END"!
		;

protected substitution_unbounded_choice_ref :rr:"ANY"^ 
					listIdentifier 
				"WHERE"! 
					expression
				"THEN"!  
					parallele_ref
				("POST"!
					predicate
                       {#rr.setType(B_ANY_POST);}
				)?
				"END"!
		;

protected predInvertedParamInvertedQuoted ://				(predInvertedParamInverted B_QUOTEIDENT^ predInvertedParamInverted)
//				=>
				predInvertedParamInverted 
				(
					B_QUOTEIDENT^ predInvertedParamInverted
				)*
//				|
//				predInvertedParamInverted rr:B_QUOTEIDENT^ {#rr.setType(B_PREC);}
		;

dummy :B_PREC
    |       B_ANY_POST
    |       B_SELECT_POST
    |       B_PRE_POST
    |       B_BEGIN_POST
;

// inherited from grammar BParser
implementation :"IMPLEMENTATION"^
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

// inherited from grammar BParser
paramName :aa:B_IDENTIFIER 
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

// inherited from grammar BParser
paramRename :nameRenamed
{
    	AddMetrics();
}
    (
        B_LPAREN^
            listIdentifier
        B_RPAREN!
    )?
;

// inherited from grammar BParser
listIdentifier :nameRenamed
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

// inherited from grammar BParser
constraints :"CONSTRAINTS"^	
        t1:expression
{
	previous_gop.putC(#t1);
}
;

// inherited from grammar BParser
mchlink :uses
    |	includes
    |	sees
    |	extendeds
    |	promotes
;

// inherited from grammar BParser
reflink :// pas de clause USES dans les REFINEMENTS d'apres Manuel de REF B
// a verifier
//				uses
//			|
        includes
    |	sees
    |	extendeds
    |	promotes
;

// inherited from grammar BParser
implink :sees
    |	imports
    |	extendeds
    |	promotes
;

// inherited from grammar BParser
extendeds :"EXTENDS"^
        listInstanciation["EXTENDS"]
;

// inherited from grammar BParser
uses :"USES"^
        listNames["USES"]
;

// inherited from grammar BParser
includes :"INCLUDES"^ 	listInstanciation ["INCLUDES"]
;

// inherited from grammar BParser
sees :"SEES"^ 	listNames["SEES"]
;

// inherited from grammar BParser
listNames[String type] :nameRenamedWithSave[type] 
        (
            B_COMMA^ 
            nameRenamedWithSave[type]
        )*
;

// inherited from grammar BParser
nameRenamedWithSave[String type] :(B_IDENTIFIER B_POINT) => B_IDENTIFIER (B_POINT^ nameRenamedWithSave[type] )
    |
        t1:B_IDENTIFIER
{
		add_AM((MyNode)#t1,type);
}
    ;

// inherited from grammar BParser
listParamNames :paramName 	
        (
            B_COMMA^ 
            paramName
        )*
    ;

// inherited from grammar BParser
listInstanciation[String type] :paramRenameValuation[type] 
        (
            B_COMMA^
            paramRenameValuation[type]
        )*	
    ;

// inherited from grammar BParser
paramRenameValuation[String type] :nameRenamedWithSave[type] 
        (
            B_LPAREN^ 
            list_New_Predicate 
            B_RPAREN!
        )* 
    ;

// inherited from grammar BParser
imports :"IMPORTS"^
            listInstanciation ["IMPORTS"]
    ;

// inherited from grammar BParser
promotes :"PROMOTES"^
            listNames["PROMOTES"]
    ;

// inherited from grammar BParser
refines :"REFINES"^ 
            t1:B_IDENTIFIER
{
		add_AM(#t1,"REFINES");
}
    ;

// inherited from grammar BParser
constants :{
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

// inherited from grammar BParser
aconstants :{
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

// inherited from grammar BParser
sets :{
	ChooseMetrics(mset);
}
        "SETS"^ sets_declaration
{
	ChooseMetrics(unknown);
}
;

// inherited from grammar BParser
sets_declaration :set_declaration 
        ( 
            B_SEMICOLON^ 
            set_declaration
        )*
;

// inherited from grammar BParser
set_declaration :{
	AddMetrics();
}
        B_IDENTIFIER
        (
            B_EQUAL^ 
            set_construction
        )?
;

// inherited from grammar BParser
set_construction :valuation_set
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

// inherited from grammar BParser
valuation_set :B_CURLYOPEN^ list_couple B_CURLYCLOSE!
    |	basic_value (B_RANGE^ 	basic_value)?		// pour gerer les interval 1..10+1
    |	is_record
    |	basic_sets
    |	B_IDENTIFIER
    ;

// inherited from grammar BParser
list_couple :couple_parent	
        (
            B_COMMA^
            couple_parent 
        )*
    ;

// inherited from grammar BParser
couple_parent :B_LPAREN^ extended_couple B_RPAREN!
    |	couple
    ;

// inherited from grammar BParser
extended_couple :a_set_value 
        ( 
            (
                B_MAPLET^ 
            | 	B_COMMA^
            ) 
            a_set_value
        )*
    ;

// inherited from grammar BParser
couple :a_set_value 
        (
            B_MAPLET^ 
            a_set_value
        )*
    ;

// inherited from grammar BParser
a_set_value :B_IDENTIFIER
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

// inherited from grammar BParser
values :"VALUES"^ 
            list_valuation
;

// inherited from grammar BParser
list_valuation :set_valuation 
        (
            B_SEMICOLON^ 
            set_valuation
        )*
    ;

// inherited from grammar BParser
set_valuation :B_IDENTIFIER B_EQUAL^ 
            new_set_or_constant_valuation
    ;

// inherited from grammar BParser
new_set_or_constant_valuation :basic_sets
    |	(B_CURLYOPEN^ list_couple) => B_CURLYOPEN^ list_couple B_CURLYCLOSE!

// J'ai ajoute le fait qu'on puisse construire un tableau
    |	(bases B_MULT ) => bases B_MULT^ bases  // ATTENTION : tableaux a plusieurs dimensions ? A VOIR
    |	basic_value ( B_RANGE^ basic_value )?
;

// inherited from grammar BParser
set_interval_value :B_IDENTIFIER B_EQUAL^ interval_declaration
;

// inherited from grammar BParser
interval_declaration :basic_value B_RANGE^ basic_value
;

// inherited from grammar BParser
set_rename_value :B_IDENTIFIER B_EQUAL^ B_IDENTIFIER
;

// inherited from grammar BParser
properties :"PROPERTIES"^ t1:expression
{
	previous_gop.putP(#t1);
}
;

// inherited from grammar BParser
variables :{
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

// inherited from grammar BParser
invariant :"INVARIANT"^ 
            t1:expression
{
	previous_gop.putI(#t1);	// We memorize the node Invariant for GOP
}
;

// inherited from grammar BParser
cvariables :{
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

// inherited from grammar BParser
definitions_mch_bis :"DEFINITIONS"! 
            list_def_mch
;

// inherited from grammar BParser
definitions_mch :"DEFINITIONS"^ 
            list_def_mch
    ;

// inherited from grammar BParser
list_def_mch :definition_mch 
        (
            c:B_SEMICOLON^
// declarations are ambiguous with "a;b"
{
	#c.setType(LIST_DEF);
} 
            definition_mch
        )*
;

// inherited from grammar BParser
definition_mch :paramName B_DOUBLE_EQUAL^ formalText_mch
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

// inherited from grammar BParser
formalText_mch :expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);
} 
    |	substitution_mch
{
	## = #( #[SUBST_DEF, "SUBST_DEF"], ##);	
} 
//			|	operationMch
;

// inherited from grammar BParser
definitions_ref_bis :"DEFINITIONS"! 
            	list_def_ref
;

// inherited from grammar BParser
definitions_ref :"DEFINITIONS"^
	        list_def_ref
;

// inherited from grammar BParser
list_def_ref :definition_ref 
        (
            c:B_SEMICOLON^  {#c.setType(LIST_DEF);} 
            definition_ref
        )*
;

// inherited from grammar BParser
definition_ref :paramName B_DOUBLE_EQUAL^ formalText_ref
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

// inherited from grammar BParser
formalText_ref :expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);	
} 
    |	substitution_ref
{
   ## = #( #[SUBST_DEF, "SUBST_DEF"], ##);	   
} 
//			|	operationRef
;

// inherited from grammar BParser
definitions_imp_bis :"DEFINITIONS"! 
            list_def_imp
;

// inherited from grammar BParser
definitions_imp :"DEFINITIONS"^ 
            list_def_imp
;

// inherited from grammar BParser
list_def_imp :definition_imp 
        (
            c:B_SEMICOLON^  
{
	#c.setType(LIST_DEF);
} 
            definition_imp
        )*
;

// inherited from grammar BParser
definition_imp :paramName B_DOUBLE_EQUAL^ formalText_imp
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

// inherited from grammar BParser
formalText_imp :expression
{
	## = #( #[EXP_DEF, "EXP_DEF"], ##);	
} 
    |   substitution_imp
{
    ## = #( #[SUBST_DEF, "SUBST_DEF"], ##);
} 
//			|	operationImp
;

// inherited from grammar BParser
assertions :"ASSERTIONS"^ 
        list_assertions
;

// inherited from grammar BParser
list_assertions :expression 
        (
            B_SEMICOLON^ 
            expression
        )*
;

// inherited from grammar BParser
initialisation_mch :"INITIALISATION"^ 
        t1:substitution_mch
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

// inherited from grammar BParser
initialisation_ref :"INITIALISATION"^ 
        t1:parallele_ref
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

// inherited from grammar BParser
initialisation_imp :"INITIALISATION"^ 
            t1:sequential
{
	previous_gop.putINIT(#t1);	// We memorize the node Invariant for GOP
}
    ;

// inherited from grammar BParser
operations_mch :"OPERATIONS"^

            listOperationMch
    ;

// inherited from grammar BParser
listOperationMch :operationMch 
        (
            B_SEMICOLON^  
            operationMch
        )*				
    ;

// inherited from grammar BParser
operationMch :operationHeader
            c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_mch
    ;

// inherited from grammar BParser
operations_ref :"OPERATIONS"^ 
            listOperationRef
    ;

// inherited from grammar BParser
listOperationRef :operationRef 
        (
            B_SEMICOLON^ 
            operationRef
        )*
    ;

// inherited from grammar BParser
operationRef :operationHeader 
        c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_ref
		;

// inherited from grammar BParser
operations_imp :"OPERATIONS"^ 
            listOperationImp
    ;

// inherited from grammar BParser
listOperationImp :operationImp 
        (
            B_SEMICOLON^ 
            operationImp
        )*
    ;

// inherited from grammar BParser
operationImp :operationHeader 
        c:B_EQUAL^ 
// declarations are ambiguous with "a=b"
{
	#c.setType(OP_DEF);
} 
        substitution_imp
    ;

// inherited from grammar BParser
operationHeader :{
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

// inherited from grammar BParser
substitution_mch :parallele_mch
    ;

// inherited from grammar BParser
parallele_mch :subst_mch 
        (	c:B_PARALLEL^ 
// declarations are ambiguous with "a || b" in predicate
{
	#c.setType(PARALLEL);
} 
            subst_mch
        )*
;

// inherited from grammar BParser
subst_mch :identite
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

// inherited from grammar BParser
substitution_ref :subst_ref
;

// inherited from grammar BParser
parallele_ref :subst_ref 
    (
        (	c:B_PARALLEL^ 	{#c.setType(PARALLEL  );}  // declarations are ambiguous with "a||b" in predicate
        |	d:B_SEMICOLON^	{#d.setType(SEQUENTIAL);}  // declarations are ambiguous with "a;b" in predicate
        ) 
        subst_ref
    )*
;

// inherited from grammar BParser
subst_ref :identite
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

// inherited from grammar BParser
substitution_imp :subst_imp
;

// inherited from grammar BParser
sequential :subst_imp 
    (
        d:B_SEMICOLON^ 
// declarations are ambiguous with "a;b" in predicate
{
    #d.setType(SEQUENTIAL);
}
        subst_imp
    )*
;

// inherited from grammar BParser
subst_imp :identite
			|	substitution_block_imp
			|	assert_imp
			|	anif_imp
			|	case_imp
			|	var_imp
			|	while_loop
			|	simple_affect
		;

// inherited from grammar BParser
identite :"skip"^
;

// inherited from grammar BParser
substitution_block_imp :"BEGIN"^
        sequential
    "END"!
;

// inherited from grammar BParser
assert_mch :"ASSERT"^ 
        expression
    "THEN"! 
        substitution_mch 
    "END"!
;

// inherited from grammar BParser
assert_ref :"ASSERT"^ 
        expression
    "THEN"! 
        parallele_ref 
    "END"!
;

// inherited from grammar BParser
assert_imp :"ASSERT"^ 
        expression
    "THEN"! 
        sequential 
    "END"!
;

// inherited from grammar BParser
anif_mch :"IF"^ 
        expression
        then_branch_mch 
        (elsif_branch_mch)*
        (e_branch_mch)?
    "END"!
;

// inherited from grammar BParser
then_branch_mch :"THEN"^ 
        substitution_mch 
;

// inherited from grammar BParser
elsif_branch_mch :"ELSIF"^
        expression 
    "THEN"!
        substitution_mch
;

// inherited from grammar BParser
anif_ref :"IF"^ expression
        then_branch_ref
        (elsif_branch_ref)*
        (e_branch_ref)?
    "END"!
;

// inherited from grammar BParser
then_branch_ref :"THEN"^
        parallele_ref
;

// inherited from grammar BParser
elsif_branch_ref :"ELSIF"^ 
        expression  
    "THEN"! 
        parallele_ref
;

// inherited from grammar BParser
anif_imp :"IF"^ expression
        then_branch_imp
        (elsif_branch_imp)*
        (e_branch_imp)?
    "END"!
;

// inherited from grammar BParser
then_branch_imp :"THEN"^ 
        sequential 
;

// inherited from grammar BParser
elsif_branch_imp :"ELSIF"^ 
        expression 
    "THEN"! 
        sequential 
;

// inherited from grammar BParser
choice_mch :"CHOICE"^ 
        list_or_mch 
    "END"!
;

// inherited from grammar BParser
list_or_mch :substitution_mch  
    (
        "OR"^ 
        substitution_mch
    )*
;

// inherited from grammar BParser
choice_ref :"CHOICE"^ 
        list_or_ref
    "END"!
;

// inherited from grammar BParser
list_or_ref :parallele_ref 
    (
        "OR"^ 
        parallele_ref
    )*
;

// inherited from grammar BParser
case_mch :"CASE"^	predicate "OF"!
        branche_either_mch
        (branche_or_mch)*
        (branche_else_mch)?
        "END"!
    "END"!
;

// inherited from grammar BParser
branche_either_mch :"EITHER"^
 	    predicate
 	"THEN"!
        substitution_mch 
;

// inherited from grammar BParser
branche_or_mch :"OR"^
        predicate
 	"THEN"!
        substitution_mch
;

// inherited from grammar BParser
branche_else_mch :"ELSE"^
        substitution_mch
;

// inherited from grammar BParser
case_ref :"CASE"^	
        predicate 
    "OF"!
        branche_either_ref
        (branche_or_ref)*
        (branche_else_ref)?
        "END"!
    "END"!
;

// inherited from grammar BParser
branche_either_ref :"EITHER"^
 	    predicate
 	"THEN"!
	    parallele_ref
;

// inherited from grammar BParser
branche_or_ref :"OR"^
        predicate
 	"THEN"!
        parallele_ref
;

// inherited from grammar BParser
branche_else_ref :"ELSE"^
        parallele_ref
;

// inherited from grammar BParser
case_imp :"CASE"^	
        predicate 
    "OF"!
        branche_either_imp
        (branche_or_imp)*
        (branche_else_imp)?
        "END"!
    "END"!
;

// inherited from grammar BParser
branche_either_imp :"EITHER"^
 	    predicate
 	"THEN"!
	    sequential
;

// inherited from grammar BParser
branche_or_imp :"OR"^
        predicate
 	"THEN"!
        sequential
;

// inherited from grammar BParser
branche_else_imp :"ELSE"^
        sequential
;

// inherited from grammar BParser
when_branch_mch :"WHEN"^ 
        expression 
    "THEN"! 
        substitution_mch
;

// inherited from grammar BParser
e_branch_mch :"ELSE"^ 
        substitution_mch
;

// inherited from grammar BParser
when_branch_ref :"WHEN"^ 
        expression 
    "THEN"! 
        parallele_ref
;

// inherited from grammar BParser
e_branch_ref :"ELSE"^ 
        parallele_ref
;

// inherited from grammar BParser
e_branch_imp :"ELSE"^ 
        sequential
;

// inherited from grammar BParser
let_ref :"LET"^
        list_identifier 
    "BE"!
        list_equal 
    "IN"!
        parallele_ref
    "END"!
;

// inherited from grammar BParser
let_mch :"LET"^
        list_identifier 
    "BE"!
        list_equal 
    "IN"!
        substitution_mch
    "END"!
;

// inherited from grammar BParser
variant_or_no :"VARIANT"^ 	    
        predicate 	
    "INVARIANT"^
 	    expression
|	"INVARIANT"^
 	    expression
 	"VARIANT"^
  	    predicate 
;

// inherited from grammar BParser
while_loop :"WHILE"^	
        predicate
    "DO"!
        sequential
        variant_or_no
    "END"!
;

// inherited from grammar BParser
list_equal :an_equal 
    (
        B_AND^ 
        an_equal
    )*
;

// inherited from grammar BParser
an_equal :B_IDENTIFIER
        B_EQUAL^ 
    predicate
		;

// inherited from grammar BParser
func_call :nameRenameDecoratedInvertedParamInverted 
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

// inherited from grammar BParser
nameRenameDecoratedInvertedParamInverted :nameRenameDecoratedInvertedParam 
    (
        B_TILDE^
    )?
;

// inherited from grammar BParser
nameRenameDecoratedInvertedParam :nameRenameDecoratedInverted 
    (
        B_LPAREN^ 
            list_New_Predicate 
        B_RPAREN!
    )* 
;

// inherited from grammar BParser
nameRenameDecoratedInverted :nameRenamedDecorated 
    (
        B_TILDE^
    )?
;

// inherited from grammar BParser
applyto :B_BRACKOPEN^ 
        predicate 
    B_BRACKCLOSE!
;

// inherited from grammar BParser
a_func_call_quoted :a_func_call 
    (
        B_QUOTEIDENT^ 
        a_func_call
    )*		// D'apres STERIA ce serait un identificateur
//{
// 	## = #( #[A_FUNC_CALL, "a_func_call"], ##);
//}
    ;

// inherited from grammar BParser
a_func_call :nameRenamed 
	(	c:B_LPAREN^ 
// parse the formal parameter declarations.
{
	#c.setType(FUNC_CALL_PARAM);
} 
		listPredicate 
		B_RPAREN! 
	)*
;

// inherited from grammar BParser
list_func_call :a_func_call_quoted 
	(
        	B_COMMA^ 
        	a_func_call_quoted
   	 )*
;

// inherited from grammar BParser
simple_affect :(list_func_call B_SIMPLESUBST) 	=> l1:list_func_call 	B_SIMPLESUBST^ 	predicate
{
	RU1(#l1);
}
    |	(list_func_call B_OUT) 		=> l2:list_func_call	B_OUT^	 	func_call
{
	RU1(#l2);
}
    |	a_func_call_quoted
    ;

// inherited from grammar BParser
simple_affect_ref :(list_func_call B_SIMPLESUBST) 	=> l1:list_func_call 	B_SIMPLESUBST^ 			predicate 
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

// inherited from grammar BParser
var_ref :"VAR"^
            listIdentifier
        "IN"!
            parallele_ref
        "END"!
;

// inherited from grammar BParser
var_imp :"VAR"^
            listIdentifier
        "IN"!
            sequential
        "END"!
;

// inherited from grammar BParser
analyse_expression :expression
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty expression !" );
            errors.WSyntaxic   ( module, "Expression is empty" );
        }
    ;

// inherited from grammar BParser
analyse_predicate :predicate
    |	 /* Empty source files are *not* allowed.  */
        {
            System.err.println ( "Warning : Empty predicate !" );
            errors.WSyntaxic   ( module, "Predicat is empty" );
        }
    ;

// inherited from grammar BParser
expression 
options {
	defaultErrorHandler= true;
}
:logical_1 	
        (	B_IMPLIES^
        	logical_1
        )*
;

// inherited from grammar BParser
logical_1 :logical_2
  	 (
  	 	(	"or"^ 
  	 	| 	B_AND^
  	 	)
  	 	 logical_2
  	 )*
;

// inherited from grammar BParser
logical_2 :subset_description
	( 
                ( 	B_EQUIV^ 
		|	B_EQUAL^  
		)   
		subset_description
	)*
;

// inherited from grammar BParser
subset_description :// May 2007
// BJL : probleme entre x,y : X et x,y
		(extended_pair
			(
				( 	B_SUBSET
				|	B_NOTSUBSET
				|	B_STRICTSUBSET
				|	B_NOTSTRICTSBSET
				|	B_INSET			// Il faut pouvoir prendre en compte f(xx) : T
				)
				arithmetic_3
			)
		) =>
		( extended_pair
			(
				( 	B_SUBSET^
				|	B_NOTSUBSET^
				|	B_STRICTSUBSET^
				|	B_NOTSTRICTSBSET^
				|	B_INSET^	// Il faut pouvoir prendre en compte f(xx) : T
				)
				arithmetic_3
			)
		)
	|	arithmetic_3
;

// inherited from grammar BParser
extended_pair :arithmetic_3
		( 
			a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}	
			arithmetic_3
		)*
;

// inherited from grammar BParser
arithmetic_3 :sequence_description
			( 
				(	B_NOTINSET^
				|	B_LESS^
				|	B_GREATER^
				|	B_NOTEQUAL^
				|	B_LESSTHANEQUAL^
				|	B_GREATERTHANEQUAL^
				)
				sequence_description
			)*
;

// inherited from grammar BParser
sequence_description :set_description 
			(
				(	B_CONCATSEQ^
				|	B_PREAPPSEQ^
				|	B_APPSEQ^
				|	B_PREFIXSEQ^
				|	B_SUFFIXSEQ^
				)
			 	set_description
			)*
;

// inherited from grammar BParser
set_description :"bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
	|	functional_set
;

// inherited from grammar BParser
functional_set :functional_const_set 
			( 
				(
					B_RELATION^
				|	B_PARTIAL^
				|	B_TOTAL^
				|	B_PARTIAL_INJECT^
				|	B_TOTAL_INJECT^
				|	B_PARTIAL_SURJECT^
				|	B_TOTAL_SURJECT^
				|	B_BIJECTION^
				)
				functional_const_set
			)*
;

// inherited from grammar BParser
functional_const_set :basic_constructors 
    (	
        (	B_DOMAINRESTRICT^
        |	B_RANGERESTRICT^
        |	B_DOMAINSUBSTRACT^
        |	B_RANGESUBSTRACT^
        |	B_OVERRIDEFORWARD^
        |	B_OVERRIDEBACKWARD^
        |	B_RELPROD^
        )
        basic_constructors
    )*
;

// inherited from grammar BParser
basic_constructors :new_couple 
    (
        (	B_UNION^
        |	B_INTER^
        )
        new_couple
    )*
;

// inherited from grammar BParser
new_couple :arithmetic_0
    (
        B_MAPLET^
        arithmetic_0
    )*
;

// inherited from grammar BParser
arithmetic_0 :(basic_sets B_MULT) => 
(    basic_sets
    ( 
 	a:B_MULT^
{#a.setType(PRODSET);}
        basic_sets
    )*
)
|	
    arithmetic_1 	
    ( 
        (
            B_POWER^ 
        |   B_MULT^
        )
        arithmetic_1
    )*	
;

// inherited from grammar BParser
arithmetic_1 :arithmetic_2 	
    ( 
        (
            B_DIV^
        |   "mod"^
        )
        arithmetic_2
    )*
;

// inherited from grammar BParser
arithmetic_2 :bases
    ( 
        (
            B_ADD^   
        |   B_MINUS^
        )
        bases
    )*
;

// inherited from grammar BParser
bases :basic_sets
			|	basic_value 				(B_RANGE^ 	arithmetic_0)?
			|	B_SEQEMPTY
			|	B_BRACKOPEN^ 				listPredicate 	B_BRACKCLOSE!
			|	c:B_LESS^ {#c.setType(B_BRACKOPEN);}	listPredicate 	B_GREATER!
			|	B_EMPTYSET
			|	B_CURLYOPEN^ 				value_set 	B_CURLYCLOSE!
			|	quantification
			|	q_lambda
		;

// inherited from grammar BParser
value_set :(alist_var B_SUCH) 	=> 	alist_var 	(B_SUCH^  expression)
// May 2007 - BJL 
    |	            		 	predicate 	(B_COMMA^ predicate)*
//	|				listPredicate
	;

// inherited from grammar BParser
basic_value :a:B_ADD^   {#a.setType(UNARY_ADD);} 	unary_basic_value_inverted
			|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	unary_basic_value_inverted
			|						unary_basic_value_inverted
			|	B_ASTRING
			|	is_record
			|	"TRUE"^ 
			|	"FALSE"^
;

// inherited from grammar BParser
unary_basic_value_inverted :unary_basic_value (B_TILDE^)?
;

// inherited from grammar BParser
unary_basic_value :expInvertedParamInvertedQuoted 
    (	c:B_BRACKOPEN^
{
	#c.setType(APPLY_TO);
} 
        predicate 
        B_BRACKCLOSE!
    )?
|	B_NUMBER
;

// inherited from grammar BParser
expInvertedParamInvertedQuoted :expInvertedParamInverted 
    (
        B_QUOTEIDENT^ expInvertedParamInverted
    )*
    // Attention on en fait peut etre beaucoup
    //			( ....)' (.....)
;

// inherited from grammar BParser
expInvertedParamInverted :expInvertedParam (B_TILDE^)?
;

// inherited from grammar BParser
expInvertedParam :expParentInverted 
    (
        B_LPAREN^ 
        list_New_Predicate 
        B_RPAREN!
    )*
;

// inherited from grammar BParser
expParentInverted :(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	expression_parent (B_TILDE^)?
;

// inherited from grammar BParser
expression_parent :c:B_LPAREN^ 
{
	#c.setType(PARENT);
}
        expression_func_composition
        B_RPAREN!
    |	nameRenamedDecorated 
;

// inherited from grammar BParser
expression_func_composition :expression 
        ( 
            (
                B_SEMICOLON^ 
            | 	B_PARALLEL^
            |	B_COMMA^
            ) 
            expression
        )* 
;

// inherited from grammar BParser
basic_sets :"INT"
    |	"INT1"
    |	"INTEGER"
    |	"INTEGER1"
    |	"BOOL"
    |	"NAT"
    |	"NAT1"
    |	"NATURAL"
    |	"NATURAL1"
    |	"STRING"
    ;

// inherited from grammar BParser
listPredicate :predicate 
        ( 
{
System.out.println("ListPredicate >>");
}
            b:B_COMMA^  {#b.setType(ELEM_SET);} 
            predicate
{
System.out.println("ListPredicate <<");
}
        )*
;

// inherited from grammar BParser
nameRenamed :B_IDENTIFIER 
	(
		B_POINT^ 
		B_IDENTIFIER
	)*
;

// inherited from grammar BParser
nameRenamedDecorated :nameRenamed 
	(
		B_CPRED^
	)?
;

// inherited from grammar BParser
predicate :plogical_1 		
	( 	B_IMPLIES^
		plogical_1
	)*
;

// inherited from grammar BParser
protected plogical_1 :plogical_2 
	( 
		(	"or"^  
		| 	B_AND^ 
		)
		plogical_2
	)*
;

// inherited from grammar BParser
protected plogical_2 :psubset_description
	( 
		( 	B_EQUIV^ 
		|	B_EQUAL^  
		) 
		psubset_description
	)*
;

// inherited from grammar BParser
protected psubset_description :// August 2010
// BJL : probleme entre x,y : X et x,y
	(pextended_pair
		(
			(
			 	B_SUBSET
			|	B_NOTSUBSET
			|	B_STRICTSUBSET
			|	B_NOTSTRICTSBSET
			
			)
			parithmetic_3
		)
	) =>
	pextended_pair
	(
		( 
			B_SUBSET^
		|	B_NOTSUBSET^
		|	B_STRICTSUBSET^
		|	B_NOTSTRICTSBSET^
		)
		parithmetic_3
	)*
	|	parithmetic_3
;

// inherited from grammar BParser
protected pextended_pair :parithmetic_3	
	( 
		a:B_COMMA^ 
{
	#a.setType(LIST_VAR);
}
		parithmetic_3 
	)*
;

// inherited from grammar BParser
protected parithmetic_3 :psequence_description
	(
		(
			B_LESS^
		|	B_GREATER^
		|	B_NOTEQUAL^
		|	B_LESSTHANEQUAL^
		|	B_GREATERTHANEQUAL^
		) 
		psequence_description
	)*
;

// inherited from grammar BParser
protected psequence_description :pset_description 
	(
		(	B_CONCATSEQ^
		|	B_PREAPPSEQ^
		|	B_APPSEQ^
		|	B_PREFIXSEQ^
		|	B_SUFFIXSEQ^
		) 
		pset_description
	)*
;

// inherited from grammar BParser
protected pset_description :"bool"^ B_LPAREN! expression B_RPAREN!
							//	Afin de traiter le cas particulier de bool(xx:INT) qui 
							//	n'est pas permis pour f(x+1)
|	pfunctional_set
		;

// inherited from grammar BParser
protected pfunctional_set :pfunctional_const_set 
	(
		(	B_RELATION^
		|	B_PARTIAL^
		|	B_TOTAL^
		|	B_PARTIAL_INJECT^
		|	B_TOTAL_INJECT^
		|	B_PARTIAL_SURJECT^
		|	B_TOTAL_SURJECT^
		|	B_BIJECTION^
		)
		pfunctional_const_set
	)*
;

// inherited from grammar BParser
protected pfunctional_const_set :pset_constructors     
	(
		(	B_DOMAINRESTRICT^
		|	B_RANGERESTRICT^
		|	B_DOMAINSUBSTRACT^
		|	B_RANGESUBSTRACT^
		|	B_OVERRIDEFORWARD^
		|	B_OVERRIDEBACKWARD^
		|	B_RELPROD^
	) 
	pset_constructors
)*
;

// inherited from grammar BParser
protected pset_constructors :ppaire 
	(
		(	B_UNION^
		|	B_INTER^
		)
		ppaire
	)*
;

// inherited from grammar BParser
protected ppaire :parithmetic_0
	( 
		B_MAPLET^ 
		parithmetic_0
	)*
;

// inherited from grammar BParser
protected parithmetic_0 :(basic_sets B_MULT) => 
(    basic_sets
    ( 
 	a:B_MULT^
{#a.setType(PRODSET);}
        basic_sets
    )*
)
|
	parithmetic_1 	
	( 
		( 	B_POWER^ 
		|	B_MULT^
		)
		parithmetic_1
	)*	
;

// inherited from grammar BParser
protected parithmetic_1 :parithmetic_2 
	( 
		(	B_DIV^ 
		| 	"mod"^
		)
		parithmetic_2
	)*
;

// inherited from grammar BParser
protected parithmetic_2 :pbases 	
	( 
		(	B_ADD^ 
		| 	B_MINUS^
		)
		pbases
	)*
;

// inherited from grammar BParser
protected pbases :B_SEQEMPTY
|	B_BRACKOPEN^ 					listPredicate B_BRACKCLOSE!
|	c:B_LESS^       {#c.setType(B_BRACKOPEN);}	listPredicate B_GREATER!
|	basic_sets
|	pbasic_value (B_RANGE^ parithmetic_0)?		// Il y avait predicate
|	B_EMPTYSET
|	B_CURLYOPEN^ pvalue_set B_CURLYCLOSE!
|	quantification
|	q_lambda
;

// inherited from grammar BParser
protected pvalue_set :(alist_var B_SUCH) 	=> alist_var 	 B_SUCH^  expression
|				   predicate 	(B_COMMA^ predicate)*
;

// inherited from grammar BParser
protected pbasic_value :a:B_ADD^   {#a.setType(UNARY_ADD);} 	punary_basic_value_inverted
|	b:B_MINUS^ {#b.setType(UNARY_MINUS);} 	punary_basic_value_inverted
|						punary_basic_value_inverted
|	B_ASTRING
|	is_rec
|	"TRUE"^ 
|	"FALSE"^
;

// inherited from grammar BParser
is_rec :"rec"^
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;

// inherited from grammar BParser
protected punary_basic_value_inverted :punary_basic_value 
	(	B_TILDE^
	)?
;

// inherited from grammar BParser
protected punary_basic_value :predInvertedParamInvertedQuoted 
	(	c:B_BRACKOPEN^ 
{
	#c.setType(APPLY_TO);
}  
		predicate 
		B_BRACKCLOSE!
	)?
|	B_NUMBER
;

// inherited from grammar BParser
protected predInvertedParamInverted :predInvertedParam (B_TILDE^)?
;

// inherited from grammar BParser
protected predInvertedParam :predParentInverted 
	(	B_LPAREN^ 
		list_New_Predicate 
		B_RPAREN!
	)*
;

// inherited from grammar BParser
protected predParentInverted :(
                    r:"ran"^  {#r.setType(B_RAN);}
		|   n:"not"^  {#n.setType(B_NOT);}
		|   d:"dom"^  {#d.setType(B_DOM);}
                |   e:"min"^  {#e.setType(B_MIN);} 
                |   f:"max"^  {#f.setType(B_MAX);} 
                |   g:"card"^ {#g.setType(B_CARD);} 
	)?
	pred_parent (B_TILDE^)?
;

// inherited from grammar BParser
pred_parent :c:B_LPAREN^ 
{#c.setType(PARENT);}
	pred_func_composition
	B_RPAREN!
|	nameRenamedDecorated 
;

// inherited from grammar BParser
pred_func_composition :predicate 
	( 
		(	B_SEMICOLON^ 
		| 	B_PARALLEL^
// Il y avait ca juste pour les predicats si pb il faudra verifier pour expression
		|	B_COMMA^
		) 
		predicate
	)* 
;

// inherited from grammar BParser
quantification :(	B_FORALL^ 
	| 	B_EXISTS^
	)
		(
			(B_LPAREN B_LPAREN B_IDENTIFIER B_COMMA) =>
				(B_LPAREN! q_quantification B_RPAREN!)
		|	(B_LPAREN B_IDENTIFIER B_COMMA) =>
				(q_quantification)
		|	(B_LPAREN B_IDENTIFIER B_POINT) =>
				(B_LPAREN! q_quantification B_RPAREN!)
		|	(B_LPAREN B_LPAREN B_IDENTIFIER B_RPAREN) =>
				(B_LPAREN! q_quantification B_RPAREN!)	
		|	(B_LPAREN B_IDENTIFIER B_RPAREN) =>
				(q_quantification)	
		|	(B_IDENTIFIER) =>
				(q_quantification)
		)
;

// inherited from grammar BParser
q_quantification :alist_var 
        B_POINT!
        B_LPAREN! 
            expression 
        B_RPAREN!
;

// inherited from grammar BParser
q_lambda :(	B_LAMBDA^
        |	"PI"^
        |	"SIGMA"^
        |	"UNION"^
        |	"INTER"^
        )	
        (
        	(B_LPAREN B_LPAREN B_IDENTIFIER B_COMMA)  =>	(B_LPAREN! q_operande B_RPAREN!)
        |	(B_LPAREN B_IDENTIFIER B_COMMA)           =>	(q_operande)
        |	(B_LPAREN B_IDENTIFIER B_POINT)           =>	(B_LPAREN! q_operande B_RPAREN!)
        |	(B_LPAREN B_LPAREN B_IDENTIFIER B_RPAREN) =>	(B_LPAREN! q_operande B_RPAREN!)	
        |	(B_LPAREN B_IDENTIFIER B_RPAREN)          =>	(q_operande)	
        |	(B_IDENTIFIER)                            =>	(q_operande)
        )
;

// inherited from grammar BParser
q_operande :alist_var
        B_POINT!
        B_LPAREN!
            expression
        B_SUCH^
            expression
        B_RPAREN!
;

// inherited from grammar BParser
is_struct :"struct"^ 
            B_LPAREN! 
                listRecord 
            B_RPAREN!
;

// inherited from grammar BParser
is_record :is_rec
    |	is_struct
;

// inherited from grammar BParser
listRecord :a_record 
        (   
            B_COMMA^ 
            a_record
        )*
;

// inherited from grammar BParser
list_identifier :B_IDENTIFIER  
        ( 
            B_COMMA^ 
            B_IDENTIFIER 
        )*
;

// inherited from grammar BParser
alist_var :(B_LPAREN) =>	
		B_LPAREN^   	list_identifier B_RPAREN!
|				list_identifier 
;

// inherited from grammar BParser
list_New_Predicate :new_predicate
        (
            B_COMMA^ 
            new_predicate
        )*
;

// inherited from grammar BParser
new_predicate :expression
        (
            (	B_SEMICOLON^
            |	B_PARALLEL^
            ) 
            predicate
        )* 
;

// inherited from grammar BParser
a_record :(B_IDENTIFIER B_INSET^) => B_IDENTIFIER a:B_INSET^ {#a.setType(B_SELECTOR);} pfunctional_set
|	pfunctional_set
|	is_struct
;

{
	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}class BEventLexer extends Lexer;

options {
	importVocab=   BEventLexer   ;
	exportVocab=   BEvent	;
	caseSensitive=	true	;
	caseSensitiveLiterals=	true	;
	testLiterals=	true	;
	k=	9	    ;
	charVocabulary=   '\u0003' .. '\uFFFF';
	codeGenBitsetTestThreshold=   20;
}

{
   	String module = "(Lexer part) BEvent.g";

    	ErrorMessage errors = new ErrorMessage();
    
	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}


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
B_ref :"ref"           ;

// inherited from grammar BLexer
SUBST_TO :"[["
;

// inherited from grammar BLexer
WS 
options {
	paraphrase= "white space";
}
:(	' '
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

// inherited from grammar BLexer
ML_COMMENT 
options {
	paraphrase= "a comment";
}
:"/*"
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

// inherited from grammar BLexer
protected NEWLINE :(
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

// inherited from grammar BLexer
B_LPAREN :'('			;

// inherited from grammar BLexer
B_COMMA :','			;

// inherited from grammar BLexer
B_RPAREN :')'			;

// inherited from grammar BLexer
B_PARALLEL :"||" 			;

// inherited from grammar BLexer
B_DOUBLE_EQUAL :"==" 			;

// inherited from grammar BLexer
B_MINUS :'-' 			;

// inherited from grammar BLexer
B_ADD :'+' 			;

// inherited from grammar BLexer
B_POWER :"**" 			;

// inherited from grammar BLexer
B_MULT :'*' 			;

// inherited from grammar BLexer
B_DIV :'/' 			;

// inherited from grammar BLexer
B_AND :'&' 			;

// inherited from grammar BLexer
B_dollars :'$' 			;

// inherited from grammar BLexer
B_OUT :"<--" 		;

// inherited from grammar BLexer
B_garde :"==>" 		;

// inherited from grammar BLexer
B_SEMICOLON :';' 			;

// inherited from grammar BLexer
B_BECOME_ELEM :"::"  		;

// inherited from grammar BLexer
B_SIMPLESUBST :":=" 			;

// inherited from grammar BLexer
B_IMPLIES :"=>" 			;

// inherited from grammar BLexer
B_EQUIV :"<=>" 		;

// inherited from grammar BLexer
B_INSET :':' 			;

// inherited from grammar BLexer
B_NOTINSET :"/:" 			;

// inherited from grammar BLexer
B_SUBSET :"<:" 			;

// inherited from grammar BLexer
B_NOTSUBSET :"/<:" 		;

// inherited from grammar BLexer
B_STRICTSUBSET :"<<:" 		;

// inherited from grammar BLexer
B_NOTSTRICTSBSET :"/<<:" 		;

// inherited from grammar BLexer
B_EMPTYSET :"{}" 			;

// inherited from grammar BLexer
B_CURLYOPEN :'{' 			;

// inherited from grammar BLexer
B_CURLYCLOSE :'}' 			;

// inherited from grammar BLexer
B_EQUAL :'=' 			;

// inherited from grammar BLexer
B_LESS :'<' 			;

// inherited from grammar BLexer
B_GREATER :'>' 			;

// inherited from grammar BLexer
B_NOTEQUAL :"/=" 			;

// inherited from grammar BLexer
B_LESSTHANEQUAL :"<=" 			;

// inherited from grammar BLexer
B_GREATERTHANEQUAL :">=" 			;

// inherited from grammar BLexer
B_PARTIAL :"+->" 		;

// inherited from grammar BLexer
B_RELATION :"<->" 		;

// inherited from grammar BLexer
B_TOTAL :"-->" 		;

// inherited from grammar BLexer
B_PARTIAL_INJECT :">+>" 		;

// inherited from grammar BLexer
B_TOTAL_INJECT :">->" 		;

// inherited from grammar BLexer
B_PARTIAL_SURJECT :"+->>" 		;

// inherited from grammar BLexer
B_TOTAL_SURJECT :"-->>" 		;

// inherited from grammar BLexer
B_BIJECTION :">->>" 		;

// inherited from grammar BLexer
B_TILDE :'~' 			;

// inherited from grammar BLexer
B_BRACKOPEN :'[' 			;

// inherited from grammar BLexer
B_BRACKCLOSE :']' 			;

// inherited from grammar BLexer
B_QUOTEIDENT :"'" 			;

// inherited from grammar BLexer
B_MAPLET :"|->" 		;

// inherited from grammar BLexer
B_DOMAINRESTRICT :"<|" 			;

// inherited from grammar BLexer
B_RANGERESTRICT :"|>" 			;

// inherited from grammar BLexer
B_DOMAINSUBSTRACT :"<<|" 			;

// inherited from grammar BLexer
B_RANGESUBSTRACT :"|>>" 		;

// inherited from grammar BLexer
B_OVERRIDEFORWARD :"<+" 			;

// inherited from grammar BLexer
B_OVERRIDEBACKWARD :"+>" 			;

// inherited from grammar BLexer
B_RELPROD :"><"  		;

// inherited from grammar BLexer
B_CONCATSEQ :'^' 			;

// inherited from grammar BLexer
B_PREAPPSEQ :"->" 			;

// inherited from grammar BLexer
B_APPSEQ :"<-" 			;

// inherited from grammar BLexer
B_PREFIXSEQ :"/|\\" 		;

// inherited from grammar BLexer
B_SUFFIXSEQ :"\\|/" 		;

// inherited from grammar BLexer
B_RANGE :".." 			;

// inherited from grammar BLexer
B_UNION :"\\/" 		;

// inherited from grammar BLexer
B_INTER :"/\\" 		;

// inherited from grammar BLexer
B_LAMBDA :'%' 			;

// inherited from grammar BLexer
B_FORALL :'!' 			;

// inherited from grammar BLexer
B_EXISTS :'#' 			;

// inherited from grammar BLexer
B_SEQEMPTY :"<>" | "[]" 		;

// inherited from grammar BLexer
B_POINT :'.' 			;

// inherited from grammar BLexer
B_SUCH :'|' 			;

// inherited from grammar BLexer
B_CPRED :"$0"			;

// inherited from grammar BLexer
protected DIGIT :'0' .. '9'
;

// inherited from grammar BLexer
B_NUMBER 
options {
	paraphrase= "a number";
}
:(DIGIT)+
;

// inherited from grammar BLexer
protected ALPHA :'a' .. 'z' 
    | 'A' .. 'Z'
;

// inherited from grammar BLexer
B_ASTRING 
options {
	paraphrase= "a string literal";
}
:'"'! ( ~('"') )* '"'!	;

// inherited from grammar BLexer
protected VOCAB 
options {
	paraphrase= "an escape sequence";
}
:'\3'..'\377'
;

// inherited from grammar BLexer
B_IDENTIFIER 
options {
	testLiterals= true;
	paraphrase= "an identifer";
}
:(ALPHA) (ALPHA | DIGIT |'_')*			
    ;


