//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            Project.java
// Description     A particular class which defined My project management
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

// JAVA Package
import java.util.Hashtable;
import java.util.Enumeration;

// Our Packages
import ABTOOLS.DEBUGGING.*;
//import AbstractMachine;

public class Project
{

    /** 
     * table where all defined abstract machine are mapped to B code
     **/
    private Hashtable projectTable;
    private int       MAX_PROJECTABLE = 533;

    /** 
     * Debugging object
     **/
    private DEBUG     debugging;


    public Project()
    {
	projectTable = new Hashtable(MAX_PROJECTABLE);
	debugging    = new DEBUG();
    }

    public Project ( DEBUG debug)
    {
	projectTable = new Hashtable(MAX_PROJECTABLE);
	debugging    = debug;
    }


    public AbstractMachine Add_AM ( AbstractMachine AM )
    {
	debugging.println("CLASS Project, OP add, PARAMETER name = " + AM.getName());
	return (AbstractMachine) projectTable.put(AM.getName(),AM);
    }



    /**
     * lookup a fully scoped name in the symbol table 
     **/
    public AbstractMachine lookupName(String name) 
    {
      	debugging.println("CLASS Project, OP lookupName, PARAMETER name = " + name);
      	
      	return (AbstractMachine) projectTable.get(name);
    }

    public String toString() 
    {
	debugging.println("CLASS Project, OP toString");

	StringBuffer buff = new StringBuffer(300);

	Enumeration ke = projectTable.keys();
	Enumeration ve = projectTable.elements();
    
	while(ke.hasMoreElements()) 
	{
	    buff.append(    "key      "+ke.nextElement().toString() 
			  + " Element "+((AbstractMachine)ve.nextElement()).toString()
			  + "\n"
		       );
	}

	buff.append("\n");

	return buff.toString();
    }


} // end of class Project 
