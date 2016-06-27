// 
// Author          : Boulanger Jean-Louis
// Email           : jean.louis.boulanger@gmail.com
//
// File            : Predicate.java
// Description     : Main class of this "Another B Tool" part that manage predicate
//
//	
//	Copyright 1999-2009 Boulanger Jean-Louis


// February  2004     Version 0.1
//    Creation
// March 2009	   v 0.2
//		 Change directory name and mail adress

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

package PREDICATE_SUBSTITUTION;

// Java Paquetage
import java.io.*;

// ANTLR Paquetages for AST
import antlr.collections.AST;
import antlr.collections.impl.*;
import antlr.debug.misc.*;
import antlr.*;

// Java graphic librarie
import java.awt.event.*;

// Some personnal usefull paquetages
import ABTOOLS.DEBUGGING.*;
import ABTOOLS.PRINTING.*;
import ABTOOLS.TYPING.*;
import ABTOOLS.ANTLR_TOOLS.*;
import ABTOOLS.CODE_GENERATOR.*;
import ABTOOLS.GRAMMAR.*;

class Predicate 
{

    public static void main(String[] args) 
    {
	try
	{
	    BLexer  lexer  = new BLexer(new BufferedInputStream(new FileInputStream(args[0])));
	    lexer.setTokenObjectClass("MyToken");

	    BParser parser = new BParser(lexer);
	    parser.setASTNodeType(MyNode.class.getName());

	    MyNode.setTokenVocabulary("BTokenTypes");

	    try 
	    {
			parser.predicate();
			//parser.substitution_mch();
	    } 
	    catch (ANTLRException ex) 
	    {
			System.err.println(ex.getMessage());
	    }
                        
	    CommonAST ast = (CommonAST)parser.getAST();
	
	    if (ast == null)
			System.err.println("Cannot create AST");
	    else
			System.out.println(ast.toStringList());
	} 
	catch (FileNotFoundException ex) 
	{
	    System.err.println(ex.toString());
	}
    }

}
