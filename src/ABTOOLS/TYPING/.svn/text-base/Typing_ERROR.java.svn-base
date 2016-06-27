//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : Typing_ERROR.java
// Description    : A Bad type is introduced and generate an error.
//                  This error is propagate ....
// Copyright 2000-2010 Jean-louis Boulanger


// June 2000
//       Creation
// April 2004
//       Packaging and Restructuring
// July  2004
//       Finalizing
// May 2007
//       Add cloning
// March	2009	   v 0.5
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


package ABTOOLS.TYPING;

import  ABTOOLS.DEBUGGING.*;

public class Typing_ERROR extends ABType
{

    String Message;

    /** 
     * Fonction de creation de base
     **/

    public Typing_ERROR()  
    {
		super("Typing ERROR");
    }

    public Typing_ERROR(String error)  
    {
		super("Typing ERROR");
		Message = error;
    }

    /** 
     * Les informations utiles sont transformees en chaine de caractere
     * We specalize the inherited method
     **/

    public String toString ()
    {
		return getType() + "("+Message+")";
    }

	public ABType clone()
	{
		return this;
	}
	
} /* End of class Typing_ERROR */
