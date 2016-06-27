//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            ListDefinition.java
// Description     A particular class which defined Definition management
// Copyright	   2000-2010 Jean-Louis Boulanger

 
 
// April 2001      v 0.1
//       Creation
// February 2002   v 0.2
//       Add documentation
// April 2004      v 0.3
//       Add Package structuration
// July  2004      v 0.4
//       Stabilized
// March 2009	   v 0.5
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
 * @author <a href="mailto:jean.louis.boulanger@gmail;com">Jean-Louis Boulanger</a>
 **/
 
package ABTOOLS.ANTLR_TOOLS;

import java.util.Hashtable;
import java.util.Enumeration;

// ABTools library
import ABTOOLS.DEBUGGING.*;

public class ListDefinition
{

    /** 
     * table where all defined definition are mapped to B code
     **/
    private Hashtable table;
    private int       MAX_PROJECTABLE = 533;

    /** 
     * Debugging object
     **/
    private DEBUG     debugging;


    public ListDefinition()
    {
	table        = new Hashtable(MAX_PROJECTABLE);
	debugging    = new DEBUG();
    }

    public ListDefinition ( DEBUG debug)
    {
	table        = new Hashtable(MAX_PROJECTABLE);
	debugging    = debug;
    }

    public ADefinition Add_AM ( ADefinition def )
    {
	debugging.println("CLASS ListDefinition, OP add, PARAMETER name = " + def.getName());

	return (ADefinition) table.put(def.getName(),def);
    }


    /**
     * lookup a fully scoped name in the symbol table 
     **/
    public ADefinition lookupName(String name) 
    {
      	debugging.println("CLASS ListDefinition, OP lookupName, PARAMETER name = " + name);

	return (ADefinition) table.get(name);
    }


    /**
     * Generate a String
     **/
    public String toString() 
    {
	debugging.println("CLASS ListDefinition, OP toString");

	StringBuffer buff = new StringBuffer(300);

	Enumeration ke = table.keys();
	Enumeration ve = table.elements();
    
	while(ke.hasMoreElements()) 
	{
	    buff.append(    "key      "+ke.nextElement().toString() 
			  + " Element "+((ADefinition)ve.nextElement()).toString()
			  + "\n"
		       );
	}

	buff.append("\n");

	return buff.toString();
    }


} // end of class ListDefinition 
