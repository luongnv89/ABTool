//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
// File           : INFIXE.java
// Description    : 
//

// Copyright 2000-2010 Jean-louis Boulanger


// June 2000
//       Creation
// July 2000      v 0.1
//       Add Member operations
// April 2004     v 0.2
//       Packaging and restructuring
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
                 
public class INFIXE extends ABType

{
    ABType member1,member2;

    /** Fonction de creation de base
     */
    public INFIXE()  
    {
		member1 = new ABType();
		member2 = new ABType();
    }

    public INFIXE( ABType t1,ABType t2)  
    {
		member1 = t1;
		member2 = t2;
    }

    public ABType getMember ()
    {
		return member1.clone();
    }


    public ABType getMember2 ()
    {
		return member2.clone();
    }

    public void putMember (ABType t1)
    {
		member1 = t1;
    }

    public void putMember2 (ABType t2)
    {
		member2 = t2;
    }

    /** Les informations utiles sont transformees en chaine de caractere
     */
    public String toString ()
    {
        return getType()+"("+member1.toString()+","+member2.toString()+")";
    }

    /** Methode demandant la normalisation du sous arbre.
	Dans le cas des types simples, elle n'a aucun effet
    */
    public ABType normalize ()
    {
		member1 = (member1.clone()).normalize();
		member2 = (member2.clone()).normalize();
		return this;
    }

    public ABType reduce (ErrorMessage err)
    {
		member1 = (member1.clone()).reduce(err);
		member2 = (member2.clone()).reduce(err);
		return this;
    }

    public ABType clone ()
	{
		return new INFIXE (member1.clone(), member2.clone());
	}
	
}   // End of File INFIXE.java
