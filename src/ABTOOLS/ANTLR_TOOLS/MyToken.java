//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            MyToken.java
// Description     A particular class which extends the CommonToken
// Copyright	   2000-2009 Jean-Louis Boulanger

// January 2000
//       Creation 
//       Introduction de l'information de numero de colonne qui n'est pas
//        gere en standard par ANTLR
// March 2000
//       Version 0.2
//       Introduction de l'information du nombre de token
//       Ajout d'operation de manipulation
// April 2004
//       Version 0.3
//       Restructuring and Packaging
// July  2004
//       Version 0.4
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
 * @author <a href="mailto:jean.louis.boulanger@gmail.com">Jean-Louis Boulanger</a>
 **/

package ABTOOLS.ANTLR_TOOLS;

public class MyToken extends antlr.CommonToken
{
    protected int    column;
    protected int    tokenNumber;
    protected String type;

    public MyToken ()
    {
	column = 0;
	tokenNumber = 0;
    }

    public int getColumn() 
    {
	return column;
    }

    public void setColumn(int c) 
    {
	column = c;
    }

    public String getNameType() 
    {
	return type;
    }

    public void setNameType(String tt) 
    {
	type = tt;
    }

    public int getTokenNumber() 
    { 
	return tokenNumber;
    }

    public void setTokenNumber(int i) 
    {
	tokenNumber = i;
    }

    public String toString() 
    {
        return "MyToken:" +"(" + hashCode() + ")" 
	                 + "[" + getType() + "] "
	                 + getText() 
	                 + " line:" + getLine() 
	    //                 + " source:" + source 
	    ;
    }

} // End of MyToken.java
