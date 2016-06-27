//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : ErrorMessage.java
// Description    : Cette classe a pour fonction de gerer et de generer 
//                  les messages d'erreurs dit "Classique"
// Copyright      : 2000-2009 Jean-Louis Boulanger


// July   2000  v 0.1
//       Creation 
// April  2001 v 0.1.1
//       Add new ERRORS "INTERNAL ERROR"
// August 2001 v 0.1.2
//       Add new ERRORS "Is not Inversible"
// September 2002 v 0.1.3
//       Add new ERRORS "Clause X is needing"
// October   2003 v 0.1.4
//       Add Errors Inhibition
// July      2003 v 0.1.5
//       Add Errors B file not found
// April     2004 v 0.2
//       Packaging and Restructuring
// July      2004 v 0.3
//       Stabilized
// September 2004 v 0.4
//       Add a Application warning ..
//       Add CodeGenerator Errors and Warning
// March 2009	   v 0.5
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

package ABTOOLS.DEBUGGING;

public class ErrorMessage 
{


    // Variable for Managing Error Messages
    static String[] currentMessageError  ;
    static int      NB_MAX_ERRORS = 2000 ; 
    static int      currentError         ;

	static String	my_empty = ""		 ;
	
    // enable or not
    static boolean  enable = true;



    /** 
     * Fonction de creation de base
     **/

    public ErrorMessage()  
    {
		currentMessageError = new String[NB_MAX_ERRORS];
		currentError        = 0;
    }


    // Enabling or not
    // ===============

    public void Enabling()
    {
		enable = true;
    }

    public void Disabling()
    {
		enable = false;
    }

    // Add error or warning
    //

    public String Add (String msg)
    {
		if (enable)
		{
			currentMessageError[currentError] = msg ;
			return currentMessageError[currentError++];
		}
		else
			return my_empty;
    }

    // WARNING
    // =======
   // AN INTERNAL WARNING CAN NOT DISABLE ...
    public String Warning (String module,String msg)
    {
	    currentMessageError[currentError] = "WARNING : Module " + module + " msg :" + msg ;
	    return currentMessageError[currentError++];
    }

    // SYNTAXIC
    // ========

    public String WSyntaxic (String module,String msg)
    {
 	return Add("Syntaxic WARNING : Module " + module + " msg :" + msg );
    }

    // APPLICATION ERROR
    // =================
    
    // AN INTERNAL CAN NOT DISABLE ...
    public String Internal (String module,String msg)
    {
	currentMessageError[currentError] = "Internal ERROR : Module " + module + " msg :" + msg ;
	return currentMessageError[currentError++];
    }

    // PROJECT ERROR
    // =============

    public String FileNotFound (String id)
    {
	return Add("Project ERROR : The B file <" + id +"> is not found");
    }

    public String FileEmpty (String id)
    {
	return Add("Project ERROR : The B file <" + id +"> is empty");
    }

    // APPLICATION WARNING
    // ===================
    public String Application (String msg)
    {
	    currentMessageError[currentError] = "WARNING msg :" + msg ;
	    return currentMessageError[currentError++];
    }

    // SEMANTIC ERROR
    // ==============

    public String IdMultipleDefined (String id)
    {
	return Add("Semantic ERROR : The identificater <" + id +"> is always defined");
    }

    public String IdNotDefined (String id)
    {
	return Add("Semantic ERROR : The identificater <" + id +"> is not defined");
    }

    public String ListIncompleted (int ligne)
    {
	return Add("Semantic ERROR : The number of terme in affectation is not correct in line :"+ligne);
    }

    public String IsNeeding (String id)
    {
	return Add("Semantic ERROR : Clause :"+id+" is needing.");
    }

    // TYPING ERROR
    // ============

    public String TypeIncompatible (String id1, String id2)
    {
	return Add("Typing   ERROR : The type <" + id1 + "> is not compatible with the type " + id2);
    }

    public String TypeIncompatible (String id1, String id2, String msg)
    {
	return Add("Typing   ERROR : The type <" + id1 + "> is not compatible with the type " + id2 +" "+msg);
    }

    public String IsNotFunction (String id1)
    {
	return Add("Typing   ERROR : The type <" + id1 + "> is not inversible");
    }

    // CodeGenerator ERROR and WARNING
    // ===============================

    public String NotImplementation (String id1)
    {
	return Add("CodeGenerator WARNING : The B component <" + id1 + "> is not an implementation.");
    }


    public String NotAllowedInImp (String code)
    {
	return Add("CodeGenerator ERROR : " + code + " is not allowed in an implementation.");
    }


    /** 
     * Convert this table to a string 
     **/

    public String toString() 
    {
		StringBuffer buff = new StringBuffer(300);
		int          j    = currentError - 1;

		while(j >= 0) 
		{
			buff.append(currentMessageError[j--]+"\n");
		}

		return buff.toString();
    }

} // End of file ErrorMessage.java
