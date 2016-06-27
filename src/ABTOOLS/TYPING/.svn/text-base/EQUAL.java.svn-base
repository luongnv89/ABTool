//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : EQUAL.java
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

public class EQUAL extends INFIXE 

{
    ABType joker = new JOKER();

    /** 
     * Fonction de creation de base
     **/
    public EQUAL()
    {
	setType("=");
    }

    public EQUAL(ABType t1, ABType t2)  
    {
	super(t1,t2);
	setType("=");
    }

    /** 
     * Fonction de Reduction
     **/

    public ABType reduce(ErrorMessage err)
    {

	String tmp = this.toString();
	System.out.println("this : "+tmp);

                 	System.out.println("Debut EQUAL.reduce ");
	/** Le cas echeant on tente 
	 **      1. la normalisation,
	 **      2. la reduction
	 **/
			System.out.println("t1 = "+member1.toString());
			System.out.println("t2 = "+member2.toString());

	String tmp0 = this.toString();
	System.out.println("this : "+tmp0);

	// 1. la normalisation
System.out.println("Normalzation");

	ABType tmp1 = (member1.clone()).normalize();
	ABType tmp2 = (member2.clone()).normalize();

			System.out.println("t1 n = "+tmp1.toString());
			System.out.println("t2 n = "+tmp2.toString());

	tmp0 = this.toString();
	System.out.println("this : "+tmp0);

	// 2. La reduction
	System.out.println("Reduction");

	tmp1 = tmp1.reduce(err);
	tmp2 = tmp2.reduce(err);

			System.out.println("t1 r = "+tmp1.toString());
			System.out.println("t2 r = "+tmp2.toString());

	tmp0 = this.toString();
	System.out.println("this : "+tmp0);

	// Le compare fonctionne mieux que le simple == entre deux chaines
	// surprenant mais c'est comme ca

	if      ( tmp1.toString().compareTo(joker.toString())==0 )
		return(new BOOL());
	else if ( tmp2.toString().compareTo(joker.toString())==0 )
		return(new BOOL());
	else if ( tmp1.toString().compareTo( tmp2.toString())==0 )
		return(new BOOL());
	else
	{

       		Class c1 = tmp1.getClass();
        	Class c2 = tmp2.getClass();
 
        	if (c1.isInstance(new Typing_ERROR()) || c2.isInstance(new Typing_ERROR()) )
		{
			// Dans le cas ou il y a deja une erreur on la propage sans plus ...
			return (new Typing_ERROR());
		}
		else
// Begin May 2002
		    //Add analysis for POW(x) = POW(y) we analyse x = y.
		if (c1.isInstance(new POW()) && c2.isInstance(new POW()))
		{
		    		    System.out.println("M1:"+((tmp1.clone()).getMember()).toString());
		    		    System.out.println("M2:"+((tmp2.clone()).getMember()).toString());

		    ABType tmp3 = new EQUAL((tmp1.clone()).getMember(),(tmp2.clone()).getMember());

		    //		    System.out.println("R1:"+tmp3.toString());
		    //		    ABType tmp4 = tmp3.reduce(err);
		    //		    System.out.println("R2:"+tmp4.toString());

		    // Begin May 2003

		    System.out.println("this :"+ tmp.toString());
		    System.out.println("tmp3 :"+ tmp3.toString());
		    
		    // If without POW it's the same (this) we have a loop and stop it by imcopatibilities
		    if (tmp3.toString().compareTo(tmp.toString())==0)
		    {
			String msg = err.TypeIncompatible(	tmp1.toString()+"(line"+tmp1.getLineNumber()+")", 
								tmp2.toString()+"(line"+tmp2.getLineNumber()+")");
			return (new Typing_ERROR(msg));
		    }
		    else
		    {
		        return tmp3.reduce(err);
		    }

		    // End May 2003
		}
// End May 2002
		else
        	{
			String msg = err.TypeIncompatible(	tmp1.toString()+"(line"+tmp1.getLineNumber()+")", 
								tmp2.toString()+"(line"+tmp2.getLineNumber()+")");
			return (new Typing_ERROR(msg));
		}
   	}
    }

} // End of File EQUAL.java
