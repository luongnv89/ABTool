//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            ASTterme.java
// Description     A particular class which destructure the basic AST
// Copyright	   2000-2009 Jean-Louis Boulanger


 
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


 
// July 2001      v 0.1
//       Creation
// April 2004     v 0.2
//       Packaging and Restructuring
// July  2004     v 0.3
//       Stabilized
// March 2009	   v 0.4
//		 Change directory name and mail adress


package ABTOOLS.ANTLR_TOOLS;

// Java Packages
import java.util.Hashtable;
import java.util.Enumeration;

//Our Package
import ABTOOLS.DEBUGGING.*;

public class ASTterme
{
    /** 
     * Debugging object
     **/
    private DEBUG     debugging;


    /**
     * Data structure
     **/
    MyNode        Cnode=null;           // C
    MyNode        Pnode=null;           // P
    MyNode        Inode=null;           // I

    MyNode        INITnode = null;

    /** 
     * table where all defined operation are mapped
     **/
    private Hashtable   ListOP;
    private int         MAX_PROJECTABLE = 533;
    private Enumeration ke;                   // ke nd ve are link to the hashtable, it permit hashtable walking
    private Enumeration ve;




    public ASTterme()
    {
	ListOP    = new Hashtable(MAX_PROJECTABLE);
	debugging = new DEBUG();
    }

    public ASTterme(DEBUG debug)
    {
	ListOP    = new Hashtable(MAX_PROJECTABLE);
	debugging = debug;
    }

// Access to internal Data
    public void putC (MyNode node )
    {
	debugging.println("CLASS ASTterme, OP putC");
	Cnode        = node;
    }

    public MyNode getC ()
    {
	debugging.println("CLASS ASTterme, OP getC");
	return Cnode;
    }

    public void putP (MyNode node )
    {
	debugging.println("CLASS ASTterme, OP putP");
	Pnode        = node;
    }

    public MyNode getP ()
    {
	debugging.println("CLASS ASTterme, OP getP");
	return Pnode;
    }

    public void putI (MyNode node )
    {
	debugging.println("CLASS ASTterme, OP putI");
	Inode        = node;
    }

    public MyNode getI ()
    {
	debugging.println("CLASS ASTterme, OP getI");
	return Inode;
    }

    public void putINIT (MyNode node )
    {
	debugging.println("CLASS ASTterme, OP putINIT");
	INITnode        = node;
    }

    public MyNode getINIT ()
    {
	debugging.println("CLASS ASTterme, OP getINIT");
	return INITnode;
    }

    public void putAnOp (MyNode node )
    {
	debugging.println("CLASS ASTterme, OP putAnOp");
	ListOP.put(node.getText(),node);
    }

    public void initGetOP ()
    {
	debugging.println("CLASS ASTterme, OP initGetOP");

	ke = ListOP.keys();
	ve = ListOP.elements();
    }

    public boolean listOpIsNotEmpty ()
    {
	debugging.println("CLASS ASTterme, OP listOpIsNotEmpty");
	return ke.hasMoreElements();
    }

    public MyNode getAnOp ()
    {
	debugging.println("CLASS ASTterme, OP getAnOp");
	// Consommation d'un element
	String tmp = ke.nextElement().toString();

	return (MyNode) ve.nextElement();
    }
 

    public String toString() 
    {
	debugging.println("CLASS ASTterme, OP toString");

	return "CLASS ASTterme, OP toString is Not Implemented";
    }

} // end of class ASTterme
