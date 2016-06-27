//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            PO.java
// Description     A particular class which extends the CommonAST
// Copyright	   2000-2009 Jean-Louis Boulanger

// March 2000      v 0.0   
//       Creation
//
// April 2000      v 0.1     
//       Introduction de l'information de numero de colonne qui n'est pas
//       gere en standard par ANTLR
//       Introduction de l'information du nombre de token
//       Ajout d'operation de manipulation
//
// August 2000     v 0.2
//       Add Xmlserialisation for node
//
// December 2000   v 0.3
//       Add a field for initialisation memorisation
//
// April 2001      v 0.4
//       Add a particular field for memorize the operation name 
// April 2004      v 0.5
//       Restructuring and Packaging
// July  2004      v 0.6
//       Stabilized
// March 2009	   v 0.7
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

// Antlr Packages
import antlr.collections.AST;
import antlr.CommonAST;
import antlr.Token;

// Java Paquetages
import java.io.*;        
import java.lang.reflect.*;
import java.util.Hashtable;
import java.util.Enumeration;

// Our Packages
import ABTOOLS.DEBUGGING.*;

/** 
  Class TNode is an implementation of the AST interface
  and adds many useful features:
*/


public class PO extends MyNode 
{

    // Basic data
    protected String  comment = "";

    /**
     *  Get the COMMENT for this node 
     **/

    public String getComment() 
    {
	return comment; 
    }
  
    /**
     * Set the token type for this node 
     **/
    public void setComment(String cc) 
    { 
	comment = cc; 
    }



} // End of File PO.java
