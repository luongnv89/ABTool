//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : UNARY.java
// Description    : 
//
// Copyright 2000-2010 Jean-louis Boulanger


// June 2000
//       Creation
// April 2004
//       Packaging and Restructuring
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
                 
public class UNARY extends ABType

{
    ABType member1;

    /** Fonction de creation de base
     */
    public UNARY()  
    {
		super("UNARY");
		member1 = new ABType();
    }

    public UNARY( ABType t1)  
    {
		super("UNARY");
		member1 = t1;
    }

    public UNARY( String new_set)  
    {
		super("UNARY");
		member1 = new ABType(new_set);
    }

    /** Les informations utiles sont transformees en chaine de caractere
     */
    public String toString ()
    {
        return getType()+"("+member1.toString()+")";
    }

    /** Methode demandant la normalisation du sous arbre.
	Dans le cas des types simples, elle n'a aucun effet
    */
    public ABType normalize ()
    {
		member1 = (member1.clone()).normalize();
		return this;
    }

    /** Va chercher le member
     */
    public ABType getMember ()
    {
		return member1.clone();
    }

    public ABType clone ()
    {
       return new UNARY(member1.clone());
    }

}   // End of File UNARY.java
