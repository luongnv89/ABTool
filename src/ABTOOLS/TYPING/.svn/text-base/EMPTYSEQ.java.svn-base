//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : EMPTYSEQ.java
// Description    : 


// Copyright 2000-2011 Jean-louis Boulanger


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

import ABTOOLS.DEBUGGING.*;
                 
public class EMPTYSEQ extends ABType
{
    /** Fonction de creation de base
     */
    public EMPTYSEQ()  
    {
	setType("EMPTYSEQ");
    }


   /** Methode demandant la normalisation du sous arbre.
        Dans le cas des types simples, elle n'a aucun effet
    */

    // Le type de [] est P(T*Z) avec T et Z qcq
    // dans notre cas on met T=Z={}

    public ABType normalize ()
    {
	/** Le type de [] est P(T*Z) avec T et Z qcq
	    dans notre cas on met T=Z={}
	*/

	ABType empty = new EMPTYSET();

        return new POW ( new PROD(empty,empty) );
    }



}
