//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : LIST.java
// Description    : 
//
// Copyright 2000-2010 Jean-louis Boulanger


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
                 
public class LIST extends INFIXE 

{
    ABType joker = new JOKER();	// On cree une fois pour toute le joker car il est souvent utilise.

    /** Fonction de creation de base
     */
    public LIST()  
    {
		setType(",");
    }

    public LIST(ABType t1, ABType t2)  
    {
		super(t1,t2);
		setType(",");
    }

    /** Fonction de Reduction
	    On cherche a calculer le type réduit  
	     (, x, x) doit donner x 
	     (, x (,x x)) doit donner x aussi
	     (, (, x, x) x) doit donner x
     */

    public ABType reduce(ErrorMessage err)
    {
	// Le cas echeant on tente la reduction
		ABType tmp1 = (member1.clone()).reduce(err);
		ABType tmp2 = (member2.clone()).reduce(err);

		if (debugging == true)
		{
			System.out.println("C1  "+tmp1.toString());
			System.out.println("C2  "+tmp2.toString());
		}
		
	// Le compare fonctionne mieux que le simple == entre deux chaines
	// surprenant mais c'est comme ca

	// Plusieurs cas, car il y a la notion de joker, qui est un type compatible avec
	// tous les autres.

		if      ( tmp1.toString().compareTo(joker.toString())==0 )
			return(member2);
		else if ( tmp2.toString().compareTo(joker.toString())==0 ) 
			return(member1);
		else if ( tmp1.toString().compareTo(tmp2.toString() )==0 )
			return(tmp1);
		else
		{
			String msg = err.TypeIncompatible(tmp1.toString(), tmp2.toString());
			return (new Typing_ERROR(msg));
		}
	}

	/** Nécessitez de cloner pour construire de nouvelle instance du merme type
	 */
	public ABType clone ()
    {
		return new LIST (member1.clone(), member2.clone());
    }
	
	
} // End Of File LIST.java
