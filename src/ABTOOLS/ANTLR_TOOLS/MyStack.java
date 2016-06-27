// 
// AUTHOR     : Boulanger Jean-Louis
// EMAIL      : jean.louis.boulanger@gmail;com
// DATE       : February 2002
// FILE       : MyStack.java
// Copyright	   2000-2009 Jean-Louis Boulanger

// DESCRIPTION: A Class for manage a Stack
//              Cette version permet d'integrer la couche debugging que nous avons
//              mis en place dans le cadre d'ABTOOLS
//

// RELEASES
//   February 2002 Creation                     V 0.1
//   April    2004 Packaging and Restructuring  V 0.2
//   July     2004 Stabilized                   V 0.3
//	 March	  2009								v 0.4
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
 * The ABtools MyStack.
 *
 * @author <a href="mailto:jean.louis.boulanger@gmail.com">Jean-Louis Boulanger</a>
 * 
 */

package ABTOOLS.ANTLR_TOOLS;

// Java Packages
import java.util.Stack;

// Our Packages
import ABTOOLS.DEBUGGING.*;


public class MyStack 
{

    /**
     * Debugging object
     **/
    private DEBUG     debugging = null ;
    private Stack     st = null;

    public MyStack ()
    {
	st = new Stack();
    }

    public MyStack (DEBUG debug)
    {
        debugging = debug;
	st        = new Stack();
    }

    public void put (Object oo)
    {
	debugging.println("CLASS Stack, OP put");

	st.push(oo);
    }

    public Object get ()
    {
	debugging.println("CLASS Stack, OP get");

	return st.pop();
    }

    public String toString ()
    {
	debugging.println("CLASS Stack, OP toString");

	return "";
    }
  
} // End of file MyStack.java
