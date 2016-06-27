//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : ENUM.java
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
                 
public class ENUM extends UNARY
{
    /** Fonction de creation de base
     */
    public ENUM()  
    {
        super();
        setType("ENUM");      
    }
 
    public ENUM(ABType t1)  
    {
        super(t1);
        setType("ENUM");      
    }                        

    public ENUM(String t1)  
    {
        super(t1);
        setType("ENUM");      
    }
	
	public ABType normalize ()
    {
		super.normalize();
		setType("ENUM");
		return this;
    }
	
	public ABType clone ()
    {
		return new ENUM(member1.clone());
    }

} // End of ENUM.java
