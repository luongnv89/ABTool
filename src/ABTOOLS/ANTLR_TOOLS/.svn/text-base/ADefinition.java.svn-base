//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            ADefinition.java
// Description     Manage one definition (name, param, body)
// Copyright	   2000-2010 Jean-Louis Boulanger
 
 
// September 2001      v 0.1
//       Creation
// February  2002      v 0.2
//       Add some documentation
//       Add parameter for Definition
// April     2003      v 0.3
//       Correct a print bug
//       Add some documentation
// April     2004      v 0.4
//       Packaging and Restructuring
// July      2004      v 0.5
//       Stabilized
// March 2009	   v 0.4
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

package ABTOOLS.ANTLR_TOOLS;

// Our Packages
import ABTOOLS.DEBUGGING.*;

// ANTLR Packages for AST
import antlr.BaseAST;
import antlr.CommonAST;
import antlr.collections.AST;

public class ADefinition
{
    /** 
     * Debugging object
     **/

    private DEBUG     debugging;

    /**
     * Data structure
     **/

    String def;
    AST    parametre = null;
    AST    body      = null;


    /**
     * Create methods
     **/

    public ADefinition()
    {
	debugging = new DEBUG();
    }

    public ADefinition(String name, AST abody)
    {
	def       = name;
	body      = abody;
	debugging = new DEBUG();
    }

    public ADefinition(String name, AST param, AST abody)
    {
	def       = name;
	parametre = param;
	body      = abody;
	debugging = new DEBUG();
    }

    public ADefinition(String name, AST abody, DEBUG debug)
    {
	def       = name;
	body      = abody;
	debugging = debug;
    }

    public ADefinition(String name, AST param, AST abody, DEBUG debug)
    {
	def       = name;
	parametre = param;
	body      = abody;
	debugging = debug;
    }


    /**
     * Access to internal Data
     **/

    public String getName ()
    {
	debugging.println("CLASS ADefinition, OP getName");

	return def;
    }

    public AST getBody ()
    {
	debugging.println("CLASS ADefinition, OP getBody");

	return body;
    }

    public AST getParam ()
    {
	debugging.println("CLASS ADefinition, OP getParam");

	return parametre;
    }

    /**
     * We generate a String
     **/

    public String toString()
    {
	debugging.println("CLASS ADefinition, OP toString");

	if (parametre == null)
	    return "AM Name : " + def + " body : " + body.toStringList();
	else
	    return "AM Name : " + def + " param : " + parametre.toString() + " body : " + body.toStringList();
	    
    }

} // end of class ADefinition
