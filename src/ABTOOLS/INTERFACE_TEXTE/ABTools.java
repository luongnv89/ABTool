// 
// Author          : Boulanger Jean-Louis
// Email           : jean.louis.boulanger@gmail.com
//
// File            : ABTools.java
// Description     : Main class of this "Another B Tool"
//
//
//	
//	Copyright 1999-2010 Boulanger Jean-Louis
//


// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  

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

/**
 * @author <a href="mailto:jean.louis.boulanger@gmail.com">Jean-Louis Boulanger</a>
 **/


package ABTOOLS.INTERFACE_TEXTE;

// Java Paquetage
import java.io.*;

// ANTLR Paquetages for AST
import antlr.BaseAST;
import antlr.CommonAST;
import antlr.collections.AST;
import antlr.collections.impl.*;
import antlr.debug.misc.*;

import java.awt.event.*;
import antlr.*;

import antlr.DumpASTVisitor;

// Some personnal usefull paquetages
import ABTOOLS.DEBUGGING.*;
import ABTOOLS.PRINTING.*;
import ABTOOLS.TYPING.*;
import ABTOOLS.ANTLR_TOOLS.*;
import ABTOOLS.CODE_GENERATOR.*;
import ABTOOLS.GRAMMAR.*;

// Root Class
// ----------
public class ABTools {

    static String  module       = "ABTools.java";

    // PARAMETER of TOOLS
    static int     NB_MAX_FILE  = 10000;

    static boolean showTree     = false;
    static boolean decompile 	= false;
    static boolean Tex       	= false;
    static boolean Xml       	= false;
    static boolean symbolTable 	= false;

    static boolean bprime       = false;
    static boolean bsystem      = false;
    static boolean bevent       = false;
    static boolean expression   = false;
    static boolean gsl          = false;

    static boolean loadLinked   = false;
    static boolean normalize    = false;
    static boolean PO           = false;
    static boolean codegenerator= false;
    // toJML option
    static boolean toJML        = false;


    static File    current;

// Create Log File and the writer
    static File       log;
    static FileWriter logwriter;

// Create Out File and it's writer
    static File       out;
    static FileWriter outwriter;

// List File
    static String[] filetodo;

// Target directory, current directory by default
    static String   target=".";

// Debug request, No debug by default
    static boolean       debugging_grammar;
    static DEBUG         debugging = new DEBUG();

    static ErrorMessage  errors    = new ErrorMessage();

// Project Management
    static Project       project   = new Project(debugging);

// parser et lexer ....
    static BParser  parser;
    static BLexer   lexer;

    //
    static ASTterme previousGOP;

// code generation
    static String        code = "JAVA";    // by default always JAVA
    static ABTOOLS.CODE_GENERATOR.CodeGenerator cg;

// option without file to manage
	static boolean optionwithoutfile = false;


	
// Main Method 
    public static void main(String[] args) 
    {

// Use a try/catch block for parser exceptions
      try 
      {

	  debugging.setErrors(errors);

	  filetodo = new String[NB_MAX_FILE];

	  log =  new File("ABTools.log") ;

	  logwriter = new FileWriter(log);

//	  logwriter.write("ABParser \n");

// if we have at least one command-line argument
	  if (args.length > 0 ) 
	  {
	    System.out.println("Begin the Treatement...");             // A little message to screen..

// The number of file or directory to parse
	    int j = 0;

	    // for each args of the command line
	    for(int i=0; i< args.length; i++) 
	    {
	     if ( args[i].equals("-lisence") )                  // A switch to request tree printing
	     {
			 System.out.println("Le produit <<ABTools>> est fourni sans AUCUNE GARANTIE et sous license LGPL superieur a 2.0 ");
			 optionwithoutfile = true;
	     }
	     else
	     if ( args[i].equals("-showtree") )                  // A switch to request tree printing
	     {
	      showTree = true;
	     }
	     else
	     if ( args[i].equals("-bprime") )                    // A switch to request bprime parsing
	     {
	      bprime = true;
	     }
	     else
// 2001 september
	     if ( args[i].equals("-bsystem") )                   // A switch to request b system parsing
	     {
	      bsystem = true;
	     }
	     else
// 2002 May
	     if ( args[i].equals("-bevent") )                   // A switch to request b event parsing
	     {
	      bevent = true;
	     }
	     else
// 2001 september
	     if ( args[i].equals("-normalize") )                 // A switch to request the AST normalization
	     {
	      normalize = true;
	     }
	     else
	     if ( args[i].equals("-target") )                    // A switch to set the target directory
	     {
	      target = args[++i];
	     }
	     else
	     if ( args[i].equals("-tex") )                       // A switch to request tex printing
	     {
	      Tex = true;
	     }
	     else
	     if ( args[i].equals("-xml") )                       // A switch to request xml printing of tree
	     {
	      Xml = true;
	     }
	     else
	     if ( args[i].equals("-debug") )                     // A switch to request debug information printing
	     {
	      debugging_grammar = true;
	      debugging.Enable();
	     }
	     else
	     if ( args[i].equals("-info") )
	     {
			 info.info();
			 optionwithoutfile = true;
	     }
	     else
	     if ( args[i].equals("-help") )
	     {
			 info.help();
			 optionwithoutfile = true;
	     }
	     else
	     if ( args[i].equals("-decompile") )
	     {
	      decompile = true;
	     }
	     else
	     if ( args[i].equals("-symbolTable"))
	     {
	      symbolTable = true;
	     }
	     else
	     if ( args[i].equals("-codeGenerator"))
	     {
		 // La generation de code implique une connaissance des types
		 // de chaque objet et un lien sur les composants lies
	      codegenerator = true;

	      symbolTable   = true;    // Typage des objets
	      loadLinked    = true;    // analyse des composants dependants
	      normalize     = true;    // expansion et normalisation du code
	     }
	     else 
         if ( args[i].equals("-toJML") )
         {
          toJML 		= true;
          symbolTable	= true;
          loadLinked	= true;
         }	    	 
	     else
	     if ( args[i].equals("-loadLinked"))
	     {
	      loadLinked = true;
	     }
	     else
	     if ( args[i].equals("-PO"))
	     {
	      PO = true;
	     } 
	     else
	     if ( args[i].equals("-Expression"))
	     {
	      expression = true;
	     } 
	     else
	     if ( args[i].equals("-GSL"))
	     {
	      gsl = true;
	     }
	     else
// It's a file or a directory
	     if (j <NB_MAX_FILE)
		 filetodo[j++] = args[i];
	     else
	     {
		 System.out.println( errors.Internal(module,"NB_MAX_FILE is too little :" + NB_MAX_FILE ));
	     }
	    }

// for each directory/file specified on the command line
	    if (j > 0)
	    {
	     for(int i=0; i<j; i++)
	     {
		 doFile(new File(filetodo[i]));                                     // a file or directory .... parse it
	     }
	    }
	    else
		{
			if (optionwithoutfile == false)
			{
				System.out.println(errors.Internal(module,"No file to parse in command line"));  // A little message to screen..
				info.usage();
			}
		}
// Fermeture du fichier de log
	   logwriter.close();

// Petit message indiquant la fin du travail
	   System.out.println("End of Treatement..."); 

// Test April 2001
	   System.out.println(project.toString());


// Afin de fermer proprement la session graphique
	   if (!showTree)
	       System.exit(0);
	 }
	  else
	 {
// Write to screen the commande line and usage 
	     info.usage();
	 }
    }
	catch(Exception e) 
   {
	   System.err.println(errors.Application("exception: "+e));
	   //e.fillInStackTrace(System.err);    // so we can get stack trace
	   e.printStackTrace(System.err);
	   // Il y avait printStackTrace() 
	   // fillIn est plus precis ....mais ca marche pas

   }
}


// This method decides what action to take based on the type of
// file we are looking at
	public static void doFile(File f)
	throws Exception 
        {
	    try 
	    {
// If this is a directory, walk each file/dir in that directory
		if (f.isDirectory()) 
	        {
		    System.out.print("  Parsing all files of directory: ");
		    System.out.println("   "+f.getAbsolutePath());

		    String files[] = f.list();

		    for(int i=0 ; i < files.length; i++)
			doFile(new File(f, files[i]));
		}
// otherwise, if this is a B file, parse it!
		else if ( f.isFile() )
		{
		    if ( (f.getName().length()>4) &&
		         (f.getName().substring(f.getName().length()-4).equals(".mch") ||
			      f.getName().substring(f.getName().length()-4).equals(".ref") ||
			      f.getName().substring(f.getName().length()-4).equals(".imp") ||
			     (f.getName().substring(f.getName().length()-4).equals(".sys") && (bsystem || bevent)) ||
			     (f.getName().substring(f.getName().length()-4).equals(".exp") && (expression)      )
			    )
		       )
	        {
			// test si le repertoire "target" existe sinon "EXIT()"

		    String targBack = target;
		    if (toJML && target.equals(".") && f.getParent() != null) {
		    	target = f.getParent();
		    }
			File tmp = new File(target);

			if (tmp.isDirectory()) 
			{   // Si le repertoire existe
			    // nothing
			}
			else
			{
			    // sortie car PB .....
			    System.out.print( errors.Application ("The <<"+target+">> directory does not exist \n"));
			    System.exit(1);    
			}
			
			// test si le repertoire ERRORS existe sinon creation
			tmp = new File(target+"/ERRORS");
			
			if (tmp.isDirectory()) 
			{   // Si le repertoire existe
			    // nothing
			}
			else
			{
			    // create this directory
			    tmp.mkdir();    
			}

			// July 2000
			// Ajout de la creation du fichier d'erreur et de son ouverture
			
			// Create Error File and it's writer
			// July 2001, il faut qu'il soit locale ...
			File       error         = new File(target+"/ERRORS/"+f.getName()+".err") ;
			FileWriter errorwriter   = new FileWriter(error);
			errorwriter.write("Error for the file : "+f.getAbsolutePath()+"\n\n");


			System.out.print("  Parsing the file : ");        // A little message to screen..
			System.out.println("   "+f.getAbsolutePath());
			
			System.err.print("  Parsing the file : ");        // A little message to Err OUT..
			System.err.println("   "+f.getAbsolutePath());

			parseFile(f.getName(), f.getParent(), new FileInputStream(f), errorwriter);
			
			// July 2000
			// Ajout de la fermeture du fichier d'erreur
			
			errorwriter.close();	   
			
			current = f;
			target = targBack;
		    }
		    else 
		    {
			// print somes adverticements to screen
			System.err.println(errors.Application (" File must be have an extension of type .mch, .ref or .imp"));
			System.err.println(errors.Application (" Next file was rejected : "+f.getAbsolutePath() ));
		    }
		}
		else
		{
		    // print somes adverticements to screen
		    System.err.println(errors.FileNotFound(f.getParent()));

   // BJL
   // Si fichier inexistant prevenir la liste des composants du projet

		    AbstractMachine am = new AbstractMachine(
							     (MyNode) null, 
							     (ListAM) null, 
							     f.getName(), 
							     f.getParent(), 
							     debugging, 
							     (ASTterme)null
							     );
		 // Introduit l'abstract machine dans le project
		    project.Add_AM(am);

		}
	    }
	    catch (FileNotFoundException fnf) 
	    {
		System.err.println(errors.Application ("Cannot find file : " +f));
	    }
	    
	}

// Here's where we do the real work...
	public static void parseFile(String f, String rep, InputStream s, FileWriter errorwriter)
	throws Exception 
        {
	    String[] tokenNames;

	 try 
	 {
	     AST ast;

	     // Add September 2001, Many grammar in one tool ....
	     if (expression)
	     {
		 ExpressionLexer lexer = new ExpressionLexer(s);
		 lexer.setTokenObjectClass("ABTOOLS.ANTLR_TOOLS.MyToken");
	     
		 // Add July 2000, information file 
		 lexer.setFilename(f);

		 // Create a parser that reads from the scanner
		 ExpressionParser parser = new ExpressionParser(lexer);

		 // Add July 2000
		 parser.setFilename(f);
		 

		 // Add May 2001
		 parser.setDebug(debugging);
		 parser.setErrors(errors);

		 System.out.println("\n Compatibility = Expression");
		 System.out.println("Activing the parsing ");
	     
		 // Pour mettre en place notre definition des noeuds....
		 parser.setASTNodeType(MyNode.class.getName());

		 MyNode.setTokenVocabulary("BExpressionTokenTypes");

		 // start parsing at the compilationUnit rule
		
		 parser.analyse_expression();

		 // do something with the tree
		 ast = (AST)parser.getAST();

		 tokenNames = parser.getTokenNames();
		 System.out.println("End the parsing \n \n");
	     }
	     else
	     if (bsystem)
	     {
		 BSystemLexer lexer = new BSystemLexer(s);
		 lexer.setTokenObjectClass("ABTOOLS.ANTLR_TOOLS.MyToken");
	     
		 // Add July 2000, information file 
		 lexer.setFilename(f);

		 // Create a parser that reads from the scanner
		 BSystemParser parser = new BSystemParser(lexer);

		 // Add July 2000
		 parser.setFilename(f);
		 parser.setPath(rep);

		 // Add May 2001
		 parser.setDebug(debugging);
		 parser.setErrors(errors);

		 System.out.println("\n Compatibility = B SYSTEM");
		 System.out.println("Activing the parsing ");
	     
		 // Pour mettre en place notre definition des noeuds....
		 parser.setASTNodeType(MyNode.class.getName());

		 MyNode.setTokenVocabulary("BSystemTokenTypes");

		 // start parsing at the compilationUnit rule
		 // option loadLinked indicate if the parser load all linked abstract machine
		 parser.composant(loadLinked);

// April 2001
		 if (loadLinked)
		 {
		     // recuperation de la liste des machines liees ...
		     ListAM listam = parser.getListAM();
		     listam.init();

		     while (listam.isNotEmpty())
		     {
			 System.out.println("on parse un fichier complementaire ...");

			 LinkedAM        tmp = listam.getAM();
			 AbstractMachine am_refined  = project.lookupName(tmp.getName()+tmp.getExtension());
			 
			 if (am_refined == null)        // null => pas dans la table ....
			 {
			     // Si elle est liee c'est une machine ..... 
			     // sauf pour le refinement ... a voir plus tard.
				 doFile(new File(tmp.getPath(), tmp.getName()+tmp.getExtension()));
			 }
		     }
		 }


		 System.out.println("End the parsing \n \n");

		 // do something with the tree
		 ast = (AST)parser.getAST();

// April 2001 Modify in JULY 2001
		 // Create an Abstract Machine field
		 AbstractMachine am = new AbstractMachine((MyNode) ast, 
							  parser.getListAM(), 
							  f, 
							  rep, 
							  debugging, 
							  parser.getPreviousGOP());

		 // Introduit l'abstract machine dans le project
		 project.Add_AM(am);

		 //		 doTreeAction(f, ast);
		 tokenNames = parser.getTokenNames();

		 previousGOP = parser.getPreviousGOP();

	     }
	     else
	     // Add March 2001, Many grammar in one tool ....
	     if (bprime)
	     {
		 BPrimeLexer lexer = new BPrimeLexer(s);
		 lexer.setTokenObjectClass("ABTOOLS.ANTLR_TOOLS.MyToken");
	     
		 // Add July 2000, information file 
		 lexer.setFilename(f);

		 // Create a parser that reads from the scanner
		 BPrimeParser parser = new BPrimeParser(lexer);

		 // Add July 2000
		 parser.setFilename(f);
		 
		 parser.setPath(rep);

		 // Add May 2001
		 parser.setDebug(debugging);
		 parser.setErrors(errors);

		 System.out.println("\n Compatibility = BPRIME");
		 System.out.println("Activing the parsing ");
	     
		 // Pour mettre en place notre definition des noeuds....
		 parser.setASTNodeType(MyNode.class.getName());

		 MyNode.setTokenVocabulary("BPrimeTokenTypes");

		 // start parsing at the compilationUnit rule
		 // option loadLinked indicate if the parser load all linked abstract machine
		 parser.composant(loadLinked);

// April 2001
		 if (loadLinked)
		 {
		     // recuperation de la liste des machines liees ...
		     ListAM listam = parser.getListAM();
		     listam.init();
		     while (listam.isNotEmpty())
		     {
			 System.out.println("on parse un fichier complementaire ...");

			 LinkedAM tmp = listam.getAM();
			 AbstractMachine am_refined  = project.lookupName(tmp.getName()+tmp.getExtension());
			 
			 if (am_refined == null)        // null => pas dans la table ....
			 {
			     // Si elle est liee c'est une machine ..... 
			     // sauf pour le refinement ... a voir plus tard.
				 doFile(new File(tmp.getPath(), tmp.getName()+tmp.getExtension()));
			 }
		     }
		 }


		 System.out.println("End the parsing \n \n");

		 // do something with the tree
		 ast = (AST)parser.getAST();

// April 2001 Modify in JULY 2001
		 // Create an Abstract Machine field
		 AbstractMachine am = new AbstractMachine((MyNode) ast, 
							  parser.getListAM(), 
							  f, 
							  rep, 
							  debugging, 
							  parser.getPreviousGOP());

		 // Introduit l'abstract machine dans le project
		 project.Add_AM(am);

		 //		 doTreeAction(f, ast);
		 tokenNames = parser.getTokenNames();

		 previousGOP = parser.getPreviousGOP();

	     }
	     else
	     // Add May 2002, Add the B event parser ...
	     if (bevent)
	     {
		 BEventLexer lexer = new BEventLexer(s);
		 lexer.setTokenObjectClass("ABTOOLS.ANTLR_TOOLS.MyToken");
	     
		 // Add July 2000, information file 
		 lexer.setFilename(f);

		 // Create a parser that reads from the scanner
		 BEventParser parser = new BEventParser(lexer);

		 // Add July 2000
		 parser.setFilename(f);
		 
		 parser.setPath(rep);

		 // Add May 2001
		 parser.setDebug(debugging);
		 parser.setErrors(errors);

		 System.out.println("\n Compatibility = BEvent");
		 System.out.println("Activing the parsing ");
	     
		 // Pour mettre en place notre definition des noeuds....
		 parser.setASTNodeType(MyNode.class.getName());

		 MyNode.setTokenVocabulary("BEventTokenTypes");

		 // start parsing at the compilationUnit rule
		 // option loadLinked indicate if the parser load all linked abstract machine
		 parser.composant(loadLinked);

		 if (loadLinked)
		 {
		     // recuperation de la liste des machines liees ...
		     ListAM listam = parser.getListAM();
		     listam.init();
		     while (listam.isNotEmpty())
		     {
			 System.out.println("on parse un fichier complementaire ...");

			 LinkedAM tmp = listam.getAM();
			 AbstractMachine am_refined  = project.lookupName(tmp.getName()+tmp.getExtension());
			 
			 if (am_refined == null)        // null => pas dans la table ....
			 {
			     // Si elle est liee c'est une machine ..... 
			     // sauf pour le refinement ... a voir plus tard.
				 doFile(new File(tmp.getPath(), tmp.getName()+tmp.getExtension()));
			 }
		     }
		 }

		 System.out.println("End the parsing \n \n");

		 // do something with the tree
		 ast = (AST)parser.getAST();

		 // Create an Abstract Machine field
		 AbstractMachine am = new AbstractMachine( (MyNode) ast, 
							   parser.getListAM(), 
							   f, 
							   rep, 
							   debugging, 
							   parser.getPreviousGOP());

		 // Introduit l'abstract machine dans le project
		 project.Add_AM(am);		 

		 //		 doTreeAction(f, ast);
		 tokenNames = parser.getTokenNames();

		 previousGOP = parser.getPreviousGOP();

	     }
	     else  // B CLASSIC
	     {
	
		 // Create a scanner that reads from the input stream passed to us
		 lexer = new BLexer(s);
		 lexer.setTokenObjectClass("ABTOOLS.ANTLR_TOOLS.MyToken");
			 
		 // METRICS management
		 Metrics metrics = new Metrics();
		 lexer.setMetrics(metrics);
	     
		 // Add July 2000, information file 
		 lexer.setFilename(f);

		 // Create a parser that reads from the scanner
		 BParser parser = new BParser(lexer);

		 // Add July 2000
		 parser.setFilename(f);
		 
		 parser.setPath(rep);
		 
		 // Add May 2001
		 parser.setDebug(debugging);
		 parser.setErrors(errors);
		 parser.setMetrics(metrics);

		 System.out.println("\n Compatibility = B CLASSIC \n");
		 System.out.println("Activing the parsing ");
	     
		 // Pour mettre en place notre definition des noeuds....
		 parser.setASTNodeType(MyNode.class.getName());

		 MyNode.setTokenVocabulary("BTokenTypes");              
	     
		 // start parsing at the compilationUnit rule
		 // option loadLinked indicate if the parser load all linked abstract machine
		 parser.composant(loadLinked);
		 
		 

// April 2001
		 if (loadLinked)
		 { 
		     // recuperation de la liste des machines liees ...
		     ListAM listam = parser.getListAM();
		     listam.init();
		     while (listam.isNotEmpty())
		     {
			 System.out.println("on parse un fichier complementaire ...");
			  
			 LinkedAM        tmp = listam.getAM();
			 
			 AbstractMachine am_refined  = project.lookupName(tmp.getName()+tmp.getExtension());
			 
			 if (am_refined == null)        // null => pas dans la table ....
			 {
			     // Si elle est liee c'est une machine ..... 
			     // sauf pour le refinement ... a voir plus tard.
				 doFile(new File(tmp.getPath(), tmp.getName()+tmp.getExtension()));
			 }
		    }
		 }

		 System.out.println("End the parsing \n \n");

		 // do something with the tree
		 ast = (AST)parser.getAST();

// April 2001 Modify in JULY 2001
// Create an Abstract Machine field
		 AbstractMachine am = new AbstractMachine( 
												  (MyNode) ast, 
												  parser.getListAM(), 
												  f, 
												  rep, 
												  debugging, 
												  parser.getPreviousGOP(),
												  metrics
												  );
			 
	     System.out.println("BEGIN METRIC\n");
			metrics.Print();
	     System.out.println("END METRIC\n");

		 // Introduit l'abstract machine dans le project
		 project.Add_AM(am);

		 //		 doTreeAction(f, ast, parser.getTokenNames());
		 tokenNames = parser.getTokenNames();

		 previousGOP = parser.getPreviousGOP();
	     }

// September 2001
	     if (normalize && (expression != true) && (gsl != true) && (ast !=null) )
	     {
		 System.out.println("Begin of Normalization \n \n");
		 
		 Normalize Bnormalize = new Normalize();
		 Bnormalize.setASTNodeType(MyNode.class.getName());
		 Bnormalize.setFileName(f);
		 Bnormalize.setErrors(errors);
		 ListDefinition ldef = new ListDefinition();
		 Bnormalize.setListDefinition(ldef);
		 
		 Bnormalize.composant(ast);
		 
		 ldef = Bnormalize.getListDefinition();
		 
		 if (ldef == null)
		 {
		     System.out.println("Definition Table : No definition");
		 }
		 else
		 {
		     System.out.println("Definition Table");
		     System.out.println(ldef.toString());
		 }

		 errorwriter.write(Bnormalize.getErrors());

		 ast = (AST)Bnormalize.getAST();

		 System.out.println("End of Normalization \n \n");
	     }



// Symbol Table and typing

	     if ( symbolTable && (expression != true) && (gsl != true) && (ast != null))
	     {
		 System.out.println("Activing the typing ");

		 BTyping Btyping = new BTyping();
		 

		 //DEBUG debug = new DEBUG();
		 //debug.Enable_Debugging();
		 //Btyping.setDebug(debug);

		 if (loadLinked)
		 {
		     Btyping.setFileName(f);
		     Btyping.setProject(project);
		 }

		 // Add MAY 2001
		 Btyping.setErrors(errors);
		 Btyping.setASTNodeType(MyNode.class.getName());
		 Btyping.composant(ast);

		 // Recuperation de la table et mise a jour du projet
		 AbstractMachine am = project.lookupName(f);
		 am.AddSymbolTable(Btyping.getSymbolTable());

		 errorwriter.write(Btyping.getErrors());
		 
		 System.out.println("\n list of variable  :\n"+Btyping.ListVariable());
		 
		 
		 // Recuperation Memoire
		 System.gc();

		 System.out.println("Desactiving the typing.\n");
	     }
	     else if (symbolTable && (ast == null))
	     {
		 System.out.println("\n list of variable  : No AST");
	     }
	     else if (!symbolTable)
	     {
		 System.out.println("\n list of variable  : Not active");
	     }

	     doTreeAction(f, ast, tokenNames);

// XML
	     if ( Xml && (ast != null))  // Si on a une demande de generation du Xml il est fait avant la fin.
	     {
		 // Cette section vise a construire l'arbre XML 
		 // a partir de l'AST avec les moyens d'ANTLR
		 System.out.println("\n XML generation  : see file "+f+".xml");
		 BaseAST asts =(BaseAST)ast;
		 doXmlAction(f+".tex",asts);
	     }

// TEX
	     if ( Tex && (ast != null))  // Si on a une demande de generation du TEX il est fait avant la fin.
	     {
		 // Cette setion vise a construire l'arbre TEX
		 // a partir de l'AST avec les moyens d'ANTLR

		 System.out.println("\n TEX generation  : see file "+f+".tex");
		 BaseAST asts =(BaseAST)ast;
		 doTexAction(f+".tex",asts);
	     }

// PO
	     if (PO && (ast != null)) // si on demande la generation des POs
	     {
		 System.out.println("\n BEGIN PO generation \n");
		 DoPO(f,previousGOP );
		 System.out.println("\n END PO generation \n");
	     }

// June 2003
// Code Generator
	     if (codegenerator && (ast != null)) // si on demande la generation du code
	     {
		 System.out.println("\n BEGIN Code generation \n");
		 BaseAST asts =(BaseAST)ast;
		 doCodeGeneration(f,asts);
		 System.out.println("\n END Code  generation \n");
	     }



// Le travail est finit on ferme ce fichier
	     s.close();                                              

// Garbage collection hint
	     System.gc();              

	 }

	 catch (Exception e) 
	 {
	     System.err.println(errors.Application("Exception: "+e));
	     e.printStackTrace();                                    // so we can get stack trace		
	 }
	}


// Print the Tree to screen
	public static void doTreeAction(String f, AST ast, String[] tokenNames) 
	throws Exception 
        {
	 try 
	 {

	  if ( ast == null )
	  {
	      // Add news to log file
	      logwriter.write("File : "+f + " is not correctly parsed \n");

	      System.out.println(errors.Application("Some trouble : null AST"));
	      return;
	  }
	  else
 // Ajout 2004 
	      // -- traitement expression
	  if (expression)
	  {
	      System.out.println("Begin : Tree in Lisp Form "); 
	      System.out.println(ast.toStringList());
	      System.out.println("End   : Tree in Lisp Form"); 
	  }
	  else
	      // -- traitement du gsl
	  if (gsl)
	  {
	      System.out.println("Begin : Tree in Lisp Form "); 
	      System.out.println(ast.toStringList());
	      System.out.println("End   : Tree in Lisp Form"); 
	  }
// Fin Ajout 2004
	  else
	  {
	      // Add news to log file
	      logwriter.write("File : "+f + " is correctly parsed \n");
 
	      if ( decompile ) 
	      {
		      System.out.println("BEGIN : Activing tree decompiling "); 

		      System.out.println("Begin : Tree in Lisp Form "); 
		      System.out.println(ast.toStringList());
		      System.out.println("End   : Tree in Lisp Form"); 

		      System.out.println("\n");

// Tree Walking
		      if (bevent)                           // B SYSTEM
		      {
			  EventTreeWalker tparse = new EventTreeWalker();

			  tparse.setDebug(debugging);
			  tparse.setErrors(errors);

			  tparse.selectTarget(tparse.ASCII);
			  tparse.composant(ast);

			  System.out.println("  Successful walk of result AST for "+f);

			  System.out.println("Begin : Tree in ASCII Form"); 
			  System.out.println(tparse.toString());
			  System.out.println("End : Tree in ASCII Form"); 

// Print in a file with same name but in "depot" dirctory
// The "depot" is defined in target variable

			  out       = new File(target+"/"+f) ;
			  outwriter = new FileWriter(out);

			  outwriter.write(tparse.toString());
			  //            outwriter.write(ast.toStringList());

			  outwriter.close();
		      }
		      else
		      if (bsystem)                           // B SYSTEM
		      {
			  SystemTreeWalker tparse = new SystemTreeWalker();

			  tparse.setDebug(debugging);
			  tparse.setErrors(errors);

			  tparse.selectTarget(tparse.ASCII);
			  tparse.composant(ast);

			  System.out.println("  Successful walk of result AST for "+f);

			  System.out.println("Begin : Tree in ASCII Form"); 
			  System.out.println(tparse.toString());
			  System.out.println("End : Tree in ASCII Form"); 

// Print in a file with same name but in "depot" dirctory
// The "depot" is defined in target variable

			  out       = new File(target+"/"+f) ;
			  outwriter = new FileWriter(out);

			  outwriter.write(tparse.toString());
			  //            outwriter.write(ast.toStringList());

			  outwriter.close();
		      }
		      else                                 // B CLASSIC
		      {
			  TreeWalker tparse = new TreeWalker();

			  tparse.setDebug(debugging);
			  tparse.setErrors(errors);

			  tparse.selectTarget(tparse.ASCII);
			  tparse.composant(ast);

			  System.out.println("  Successful walk of result AST for "+f);

			  System.out.println("Begin : Tree in ASCII Form"); 
			  System.out.println(tparse.toString());
			  System.out.println("End : Tree in ASCII Form"); 

// Print in a file with same name but in "depot" dirctory
// The "depot" is defined in target variable

			  out       = new File(target+"/"+f) ;
			  outwriter = new FileWriter(out);

			  outwriter.write(tparse.toString());

// Write AST in comment
			  outwriter.write("\n /* Begin AST Tree \n");
			  outwriter.write(ast.toStringList());
			  outwriter.write("\n    End AST Tree */");

			  outwriter.close();
		      }

// Recuperation Memoire
		      System.gc();
		      System.out.println("END : Activing tree decompiling "); 

	      }

	      if ( showTree )
	      {
		      System.out.println("   Activing tree showing ");      

// Print the resulting tree out in LISP notation
		      System.out.println(ast.toStringTree());   

// Make a windows with tree
// 		      ((CommonAST)ast).setVerboseStringConversion(true, tokenNames);
// 		      ASTFactory factory = new ASTFactory();
// 		      AST r = factory.create(0,"AST ROOT");
// 		      r.setFirstChild(ast);
// 		      final ASTFrame frame = new ASTFrame("Java AST", r);
// 		      frame.setVisible(true);
// 		      frame.addWindowListener(
// 					      new WindowAdapter() 
// 		      {
// 		            public void windowClosing (WindowEvent e) 
// 			    {
// 				frame.setVisible(false); // hide the Frame
// 				frame.dispose();
// 				//				System.exit(0);
//                             }
// 		      });
	      }
          // toJML option
          if (toJML)
          {
              System.out.println("*** toJML: initialiazing...");
             
              System.out.println("*** toJML: creating tree walker...");
              
              toJML_TreeWalker tparse = new toJML_TreeWalker();
              
              
              tparse.setDebug(debugging);
              tparse.setErrors(errors);

              tparse.composant(ast);
              tparse.toFile(target);
              /*
                      System.out.println("  Successful walk of result AST for "+f);

                      System.out.println("Begin : Tree in ASCII Form"); 
                      System.out.println(tparse.toString());
                      System.out.println("End : Tree in ASCII Form");
               */

              // Print in a file with same name but in "depot" dirctory
              // The "depot" is defined in target variable

//                    out       = new File(target+"/"+f) ;
//                    outwriter = new FileWriter(out);

//            outwriter.write(tparse.toString());

              // Write AST in comment
//                    outwriter.write("\n /* Begin AST Tree \n");
//                    outwriter.write(ast.toStringList());
//                    outwriter.write("\n    End AST Tree */");

//                    outwriter.close();


              // Recuperation Memoire
              //                        System.gc();
              System.out.println("*** ...toJML: finalizing tree walker");
              System.out.println("*** ...toJML: finalizing");
          }      
	  }
	 }
	 catch (Exception e) 
	 {
		  System.err.println(errors.Internal(module,"\n Exception: "+e));
		  e.printStackTrace();                          // so we can get stack trace		
	 }
	}


    // Operation permettant de generer du XML a partir de l'arbre syntaxique

	public static void doXmlAction(String f,BaseAST ast) 
	throws Exception 
        {
	 try 
	 {
	 // Initialement introduit en Novembre 2004
	 // repris en Janvier 2008
	 
	     // XML serialize the tree, 
 	     System.out.println("   Begin : Activing XML generation ");      

// V1 on utilise ANTLR	   
//	     Writer w = new OutputStreamWriter(System.out);  
//	     ast.xmlSerialize(w);
//	     w.write("\n");
//	     w.flush();

// V2 XMLSerialize.serialize(ast);

	     out       = new File(target+"/"+f) ;
	     outwriter = new FileWriter(out);

	     BXML b2xml = new BXML();
	     b2xml.setDebug(debugging);
	     b2xml.setErrors(errors);
	     b2xml.setASTNodeType(MyNode.class.getName());

	     b2xml.composant(ast);

	     outwriter.write(b2xml.toString());
	     outwriter.close();

System.out.println("End : XML generation ");
	 }
	 catch (Exception e) 
	 {
	   System.err.println(errors.Internal(module,"Exception: "+e));
	   e.printStackTrace();                         // so we can get stack trace 
	 }          
        }

    // Operation permettant de generer du TEX a partir de l'arbre syntaxique

	public static void doTexAction(String f, BaseAST ast) 
	throws Exception 
        {
	 try 
	 {
	     // TEX serialize the tree, 
 	     System.out.println("   Begin : Activing TEX generation ");      
   
	     // Tree Walking
	     TreeWalker tparse = new TreeWalker();
	     //	     tparse.selectTarget(tparse.TEX);
	     tparse.setDebug(debugging);
	     tparse.setErrors(errors);

	     tparse.selectTargetTex();

// Ajout Septembre 2004
	     if (expression)
	     {
		 tparse.predicate(ast);
	     }
	     else
	     if (gsl)
	     {
		 // To be defined
	     }
	     else
	     {
		 tparse.composant(ast);
	     }
// Ajout septembre 2004

	     out       = new File(target+"/"+f) ;
	     outwriter = new FileWriter(out);

	     // Description de l'entete du fichier TEX

	     outwriter.write(" \\documentclass[11pt]{article} \n"+ 
                             " \\usepackage[T1]{fontenc}      \n"+ 
                             " \\usepackage{amsfonts}         \n"+ 
                             " \\usepackage{amssymb}          \n"+ 
                             " \\usepackage{B1}               \n"+ 
                             " \\begin{document}%             \n"+ 
                             " \\begin{amn}%                  \n");

	     outwriter.write(tparse.toString());

	     // Description de la fin du fichier TEX
	     outwriter.write("                 \n"+
			     "\\end{amn}%      \n"+ 
                             "\\end{document}% \n");

	     outwriter.close();

	     System.out.println("   End: Activing TEX generation ");      
	 }
	 catch (Exception e) 
	 {
	   System.err.println(errors.Internal(module,"Exception: "+e));
	   e.printStackTrace();                         // so we can get stack trace 
	 }          
        }

// begin June 2003
// Operation permettant de generer du code a partir de l'arbre syntaxique

	public static void doCodeGeneration(String f, BaseAST ast) 
	throws Exception 
        {
	 try 
	 {
 	     System.out.println("   Begin : Activing Code Generation ");      
   
	     // Tree Walking
	     BCodeGenerator tparse = new BCodeGenerator();

// Code generation selection
	     if (code.equals("JAVA"))
	     {
		 System.out.println("   Select JAVA Generator ");
		 cg = new CG_JAVA();
	     }
	     else if (code.equals("ADA"))
	     {
		 System.out.println("   Select ADA Generator ");
		 cg = new CG_ADA();
	     }
	     else
	     {
		 System.err.println(errors.Internal(module,"Selected Code Generator does not exist :"+code));
		 cg = new CG_JAVA();
	     }

	     cg.setPath(target);
	     cg.setErrors(errors);
	     cg.setDebug(debugging);

 	     tparse.setCodeGenerator(cg);

// Manage errors and debugging
	     tparse.setDebug(debugging);
	     tparse.setErrors(errors);

// Begin Ajout Septembre 2004
	     if (expression)
	     {
		 System.err.println(errors.Application("No code generation for Expression"));
	     }
	     else
	     if (gsl)
	     {
		 System.err.println(errors.Application("No code generation for GSL"));
	     }
	     else
	     {
		 cg.setPath(".");
		 tparse.composant(ast);
	     }
// End Ajout septembre 2004

	     System.out.println("   End: Activing Code generation ");      
	 }
	 catch (Exception e) 
	 {
	   System.err.println(errors.Internal(module,"Exception: "+e));
	   e.printStackTrace();                         // so we can get stack trace 
	 }          
        }
// End June 2003


    public static void DoPO(String f ,ASTterme terme)
    throws Exception 
    {
	try 
	{    
	    // TEST JULY 2001
	    generic gg = new generic(lexer);
	    gg.setASTNodeType(MyNode.class.getName());
	    gg.setFilename(f);
	    gg.setErrors(errors);
	    gg.setDebug(debugging);

	    System.out.println("GOP initialize");

	    gg.setPreviousGOP(terme);
	    gg.generate();
		 
	    System.out.println("PO was generated");

	    MyNode aa = gg.getPO();
	    if (aa != null)
	    {
		System.out.println("Demande affichage  des POs :");
		System.out.println(" sous forme de list :");
		System.out.println(aa.toStringList());

		System.out.println("GOP Reduction");
		Substitution tt = new Substitution();
		tt.setASTNodeType(MyNode.class.getName());
	    
		tt.traitement(aa);
		AST bb = tt.getAST();
		System.out.println(" After GOP Reduction: "+bb.toStringList());
	
		System.out.println("GSL Reduction");

		GSL uu = new GSL();
		uu.setASTNodeType(MyNode.class.getName());

		uu.traitement(bb);
		AST cc = uu.getAST();
		System.out.println("After treatment :"+ cc.toStringList());
	
		System.out.println(" sous forme XML :");
		POWalker pow = new POWalker();
		pow.setASTNodeType(MyNode.class.getName());
		pow.analyze_PO(cc);
		System.out.println(pow.toString());

		System.out.println("Fin affichage");
	    }
	    else
		System.out.println("No PO");
	

	    System.out.println("GOP finished");
	}
	catch (Exception e) 
	{
	    System.err.println(errors.Internal(module,"\n Exception: "+e));
	    e.printStackTrace();                          // so we can get stack trace		
	}
    }
} // End of Class ABTools
