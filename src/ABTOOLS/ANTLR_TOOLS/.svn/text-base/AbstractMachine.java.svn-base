//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            AbstractMachine.java
// Description     A particular class which defined an Abstract Machine
// Copyright	   2000-2010 Jean-Louis Boulanger

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

public class AbstractMachine
{
    /** 
     * Debugging object
     **/
    private DEBUG     debugging;


    /**
     * Data structure
     **/
    MyNode        AMnode;
    String        AMname;
    String        AMfile;
    ListAM        AMlistAM;
    MySymbolTable AMsymbolTable;
    ASTterme      AMGOP;
	Metrics		  AMmetrics = new Metrics();
	
	public AbstractMachine()
    {
	debugging = new DEBUG();
    }

	public AbstractMachine(MyNode node, ListAM lam, MySymbolTable symboltable, String name, String file, Metrics metrics)
    {
		AMnode        = node;
		AMname        = name;
		AMfile        = file;
		AMlistAM      = lam;
		AMsymbolTable = symboltable;
		debugging     = new DEBUG();
		AMGOP         = null;
		AMmetrics	  = metrics;
    }
	
    public AbstractMachine(MyNode node, ListAM lam, MySymbolTable symboltable, String name, String file)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = symboltable;
	debugging     = new DEBUG();
	AMGOP         = null;
    }

    public AbstractMachine(MyNode node, ListAM lam, String name, String file)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = null;
	AMGOP         = null;
	debugging     = new DEBUG();
    }

    public AbstractMachine(MyNode node, ListAM lam, MySymbolTable symboltable, String name, String file, DEBUG debug)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = symboltable;
	debugging     = debug;
	AMGOP         = null;
    }

    public AbstractMachine(MyNode node, ListAM lam, String name, String file, DEBUG debug)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = null;
	AMGOP         = null;
	debugging     = debug;
    }

    public AbstractMachine(MyNode node, ListAM lam, String name, String file, DEBUG debug, ASTterme gop)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = null;
	AMGOP         = gop;
	debugging     = debug;
    }

    public AbstractMachine(MyNode node, ListAM lam, MySymbolTable symboltable, String name, String file, DEBUG debug, ASTterme gop)
    {
	AMnode        = node;
	AMname        = name;
	AMfile        = file;
	AMlistAM      = lam;
	AMsymbolTable = symboltable;
	AMGOP         = gop;
	debugging     = debug;
    }

	public AbstractMachine(MyNode node, ListAM lam, String name, String file, DEBUG debug, ASTterme gop,Metrics metrics)
    {
		AMnode        = node;
		AMname        = name;
		AMfile        = file;
		AMlistAM      = lam;
		AMGOP         = gop;
		debugging     = debug;
		AMmetrics     = metrics;
    }
	
	public AbstractMachine(MyNode node, ListAM lam, MySymbolTable symboltable, String name, String file, DEBUG debug, ASTterme gop, Metrics metrics)
    {
		AMnode        = node;
		AMname        = name;
		AMfile        = file;
		AMlistAM      = lam;
		AMsymbolTable = symboltable;
		AMGOP         = gop;
		debugging     = debug;
		AMmetrics     = metrics;
    }
	
    public void setDebug (DEBUG newdebug)
    {
	debugging = newdebug;
    }


// Add some DATA

    public void AddPreviousGop(ASTterme gop)
    {
	debugging.println("CLASS AbstractMachine, OP AddPreviousGop");
	AMGOP         = gop;
    }

    public void AddSymbolTable (MySymbolTable symboltable)
    {
	debugging.println("CLASS AbstractMachine, OP AddSymbolTable");
	AMsymbolTable = symboltable;
    }

    public void AddMetrics (Metrics metrics)
    {
		debugging.println("CLASS AbstractMachine, OP AddMetrics");
		AMmetrics = metrics;
    }
	
// Access to internal Data

    public String getName ()
    {
	debugging.println("CLASS AbstractMachine, OP getName");

	return AMname;
    }

    public MyNode getNode ()
    {
	debugging.println("CLASS AbstractMachine, OP getNode");

	return AMnode;
    }

    public String getFile ()
    {
	debugging.println("CLASS AbstractMachine, OP getFile");

	return AMfile;
    }

    public ListAM getLinked ()
    {
	debugging.println("CLASS AbstractMachine, OP getLinked");

	return AMlistAM;
    }
	
	public Metrics getMetrics ()
    {
		debugging.println("CLASS AbstractMachine, OP getMetrics");
		
		return AMmetrics;
    }

    public MySymbolTable getSymbolTable ()
    {
	debugging.println("CLASS AbstractMachine, OP getSymbolTable");

	return AMsymbolTable;
    }

    public boolean idVerify(String name)
    {
	MyNode tmp = AMsymbolTable.lookupNameInCurrentScope(name);

	return (tmp == null);	
    }

    public String toString() 
    {
	debugging.println("CLASS AbstractMachine, OP toString");

	return "AM Name : "+AMname+"| AM File : "+AMfile+"|";
    }

} // end of class AbstractMachine
