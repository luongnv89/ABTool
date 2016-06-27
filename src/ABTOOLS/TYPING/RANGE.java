//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : RANGE.java
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
                 
public class RANGE extends INFIXE

{

    /** Fonction de creation de base
     */
    public RANGE()  
    {
		setType("RANGE");
    }

    public RANGE( ABType t1,ABType t2)  
    {
		super(t1,t2);
		setType("RANGE");
    }

    /** Fonction de Reduction
     */
 
    public ABType reduce (ErrorMessage err)
    {
        return (new LIST(this.member1.clone(),this.member2.clone())).reduce(err) ;
    } 

    public ABType clone ()
    {
       return new RANGE( member1.clone(), member2.clone());
    }

} // End Of File RANGE.java
