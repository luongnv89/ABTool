//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : FUNC_DEF.java
// Description    : 
//
// Copyright 2000-2009 Jean-louis Boulanger


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

public class FUNC_DEF extends INFIXE
{

    /** Fonction de creation de base
     */
    public FUNC_DEF()  
    {
	super();
	setType("FUNC_DEF");
    }

    public FUNC_DEF( ABType t1, ABType t2)
    {
        super(t1,t2);
        setType("FUNC_DEF");
    }


    
} // End of file FUNC_DEF.java
