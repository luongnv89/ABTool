//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : NAT.java
// Description    : 
//

// Copyright 2000-2010 Jean-louis Boulanger

// June 2000
//       Creation
// March 2003
//       Add Normalize and Reduce specifics operations
// April 2004
//       Add Package structuration
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
                 
public class NAT extends NATURAL
{
    /** Fonction de creation de base
     */
    public NAT()  
    {
	setType("NAT");
    }

    /** 
     * Methode demandant la normalisation du sous arbre.
     * Dans le cas des types simples, elle n'a aucun effet
     **/

    public ABType normalize ()
    {
	return new POW(new INTEGER());
    }

    public ABType clone ()
    {
       return new NAT();
    }

    /** 
     * Fonction de Reduction
     **/

    public ABType reduce (ErrorMessage err)
    {
	return new NATURAL();
    }

    // All NAT are NATURAL

} // End Of File NAT.java
