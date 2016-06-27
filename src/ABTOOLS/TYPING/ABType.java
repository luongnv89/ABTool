//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : ABType.java
// Description    : This class introduced the basic behaviors for B TYPE.

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

import ABTOOLS.DEBUGGING.*;

public class ABType 
{
    // Line Number for Error message
    private int lineNumber;

    public void setLineNumber (int num)
    {
		lineNumber = num;
    }

    public int getLineNumber ()
    {
		return lineNumber;
    }

    // Activate or Desactivate Debug information 
    public boolean debugging             = false;

    public void Disable_Debugging ()
    {
		debugging = false;
    }
    
    public void Enable_Debugging ()
    {
		debugging = true;
    }
    
    public boolean Debug_Grammar ()
    {
		return debugging;
    }


    // La forme ASCII du type
    private String  textType;

    /** The function for test the type definition
     */
    public boolean IsDefined()
    {
		return  !(textType == "Not Defined");
    }

    /** Fonction de creation de base
     */
    public ABType()  
    {
		textType = "Not Defined";
    }

    public void setType (String tt)
    {
		textType = tt;
    }

    public String getType ()
    {
		return textType;
    }

    /** Possibilite de creer un nouvel ensemble
     */
    public ABType(String new_set)
    {
		textType = new_set;
    }

    /**
     *  Possibilite de creer un nouvel ensemble avec le Numero de ligne
     **/
    public ABType(String new_set, int num)
    {
		textType   = new_set;
		lineNumber = num;
    }

    /** 
     * Les informations utiles sont transformees en chaine de caractere
     **/
    public String toString ()
    {
		return textType;
    }

    public void typingVerification(ErrorMessage err)
    {
		if (this.IsDefined() == false)
           System.out.println(err.IdNotDefined(this.toString()));

    }


    /** 
     * Methode demandant la normalisation du sous arbre.
     * Dans le cas des types simples, elle n'a aucun effet
     **/

    public ABType normalize ()
    {
		return this.clone();
    }


    /** 
     * Fonction de Reduction
     **/

    public ABType reduce (ErrorMessage err)
    {
		return this.clone();
    }

    
    /**
     * Fonction d'inversion
     **/

    public ABType inverse (ErrorMessage err)
    {
		return this;
    }

    public ABType getMember ()
    {
		return this.clone();
    }
	
    public ABType getMember2 ()
    {
		return this.clone();
    }

    public ABType clone()
    {
		return new ABType(textType, lineNumber);
    }

} /* End of class ABType.java */
