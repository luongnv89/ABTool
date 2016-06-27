//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : PROD.java
// Description    : 
//
// Copyright 2000-2010 Jean-louis Boulanger


// June 2000
//     	Creation
// December 2001
//	Modify the reduce operation	
// April 2004
//      Packaging and restructuring
// April 2007
//      Bug correction in REDUCE function
// May 2007
//      Add Cloning
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

public class PROD extends INFIXE 
{

	// TAW 2/22/2011
	private boolean isFunction = false;
	
    /** 
     * Fonction de creation de base
     **/

    public PROD()  
    {
		setType("PROD");
    }

    public PROD( ABType t1,ABType t2)  
    {
		super(t1,t2);
		setType("PROD");
    }
    
    public PROD(ABType t1,ABType t2, boolean isFun) {
    	this(t1, t2);
    	isFunction = isFun;
    }
    
    public boolean isFunction() {return isFunction;}


    public ABType reduce(ErrorMessage err)
    {
	
			System.out.println("PROD t1 = "+member1.toString());
			System.out.println("PROD member.member t1 = "+(member1.clone()).getMember().toString());
		
			System.out.println("POW t2 = "+member2.toString());
			System.out.println("PROD member.member t2 = "+(member2.clone()).getMember().toString());

	//BJL December 2001
		Class c1 = member1.getClass();
		Class c2 = member2.getClass();
 
	// la regle de reduction est
	// P(T) * P(R) = P(T*R)

        if (c1.isInstance(new POW()) & c2.isInstance(new POW()) )
        {
			return (new POW( new PROD( (member1.clone()).getMember(),(member2.clone()).getMember()) ));
        }
        else
        {
// BJL April 2007
// si T * R avec T et R different ca n'implique pas une ERREUR .. pas de réduction possible
//		String msg = err.TypeIncompatible(member1.toString()+"(line"+member1.getLineNumber()+")",
//		                                  member2.toString()+"(line"+member2.getLineNumber()+")");
//        	return (new Typing_ERROR(msg));
		return (this);
        }            
    }


    // BJL August 2001
    public ABType inverse (ErrorMessage err)
    {
		return new PROD((member2.clone()).inverse(err), (member1.clone()).inverse(err));
    }


    public ABType clone ()
    {
       return new PROD(member1.clone(), member2.clone(), isFunction);
    }

    public void typingVerification(ErrorMessage err)
    {
		Class c1 = member1.getClass();
		Class c2 = member2.getClass();


		if (c1.isInstance(new PROD()))
		{
			member1.typingVerification(err);
		}
		else if (c1.isInstance(new ABType()))
		{
			if (!(member1.IsDefined() == true))
            {
				System.out.println(err.IdNotDefined(member1.toString()));
            }
		}

		if (c2.isInstance(new PROD()))
		{
			member2.typingVerification(err);
		}
		else if (c2.isInstance(new ABType()))
		{
			if (!(member2.IsDefined() == true))
				System.out.println(err.IdNotDefined(member2.toString()));
		}
    }

} /* End of Class PROD */
