//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@wanadoo.fr
//
// File           : OutASCII.java
// Description    : Cette classe permet de definir un aspect graphique pour une 
//                  impression ASCII
//
// Copyright 2000-2009 Jean-louis Boulanger


// June  2000
//       Creation
// April 2004
//       Packaging and Restructuring
// July  2004
//       Stabilized

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/

package ABTOOLS.PRINTING;
                 
public class OutASCII extends OUT
{

    /** 
     *  Differentes fonctions permettant de faire une sortie 
     *	coherente dans un fichier
     **/

    public String Clause ( String txt)
    {
	return ("\n" + txt );
    }

    public String KeyWord ( String txt)
    {
	return (txt);
    }

    public String Separator ( String txt)
    {
        return (" "+txt+ " ");
    }   

} // End of OutASCII.java
