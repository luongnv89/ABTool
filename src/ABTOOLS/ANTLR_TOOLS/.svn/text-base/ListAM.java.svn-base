//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            ListAM.java
// Description     
// Copyright	   2001-2010 Jean-Louis Boulanger
 
 
// April 2001      v 0.1
//       Creation
// April 2004      v 0.2
//       Packaging and Restructuring
// July  2004      v 0.3
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

import java.util.Hashtable;
import java.util.Enumeration;

import ABTOOLS.DEBUGGING.*;

public class ListAM
{

    /** 
     * table where all defined abstract machine are mapped to B code
     **/
    private Hashtable Table;
    private int       MAX_PROJECTABLE = 533;

    /** 
     * Debugging object
     **/
    private DEBUG     debugging;

    private Enumeration ke;
    private Enumeration ve;

    public ListAM()
    {
	Table = new Hashtable(MAX_PROJECTABLE);
	debugging    = new DEBUG();
    }

    public ListAM ( DEBUG debug)
    {
	Table = new Hashtable(MAX_PROJECTABLE);
	debugging    = debug;
    }




    public LinkedAM Add_AM ( LinkedAM AM )
    {
	debugging.println("CLASS Project, OP add, PARAMETER name = " + AM.getName());

	return (LinkedAM) Table.put(AM.getName(),AM);
    }



    /**
     * lookup a fully scoped name in the symbol table 
     **/
    public LinkedAM lookupName(String name) 
    {
      	debugging.println("CLASS Project, OP lookupName, PARAMETER name = " + name);

	return (LinkedAM) Table.get(name);
    }

    public String toString() 
    {
	debugging.println("CLASS ListAM, OP toString");

	StringBuffer buff = new StringBuffer(300);

	Enumeration ke = Table.keys();
	Enumeration ve = Table.elements();
    
	while(ke.hasMoreElements()) 
	{
	    buff.append(        
					"key      "+ ke.nextElement().toString() 
			      + " Element "+ ((LinkedAM) ve.nextElement()).toString()
			      + "\n"
		       );
	}

	buff.append("\n");

	return buff.toString();
    }

    public void init ()
    {
	ke = Table.keys();
	ve = Table.elements();
    }

    public boolean isNotEmpty ()
    {
	return ke.hasMoreElements();
    }

    public LinkedAM getAM ()
    {
	if (this.isNotEmpty())
	{
	    // Consommation d'un element
	    String tmp = ke.nextElement().toString();

	    return (LinkedAM) ve.nextElement();
	}
	else
	    return null;
    }


} // end of class ListAM 
