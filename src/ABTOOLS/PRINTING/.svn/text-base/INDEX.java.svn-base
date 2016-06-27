//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@wanadoo.fr
//
// File           : INDEX.java
// Description    : Cette classe permet de gerer les tabulations pour une meilleur
//                  impression
// Copyright	2001-2009 Jean-louis Boulanger


// January 2001  v 0.1
//       Creation
// May     2001  v 0.2
//       Add the getOne method
// August  2001  v 0.3
//	 Add the "init" method
// April   2004  v 0.4
//       Packaging and Restructuring
// July    2004  v 0.5
//       Stabilized

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

/**
 * @author <a href="mailto:jean.louis.boulanger@gmail.com">Jean-Louis Boulanger</a>
 **/

package ABTOOLS.PRINTING;
       
public class INDEX 
{

    /** 
     *   Differentes fonctions permettant de gerer les tabulations
     **/

    int tabulation ;

    public INDEX ()
    {
	tabulation = 0;
    }

    public void init()
    {
	tabulation = 0;
    }

    public void Add ()
    {
	tabulation = tabulation +1;
    }

    public void Retract ()
    {
	tabulation = tabulation -1;
    }

    public String toString()
    {
	int    tmp     = tabulation;
	String tmp_str = "";

	while (tmp > 0)
        {
	    tmp_str = tmp_str +"\t";
	    tmp     = tmp -1;
	}

	return tmp_str;
    }

    public String getOne()
    {
	return "\t";
    }

}   // End of class INDEX
