//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            Metrics.java
// Description     

// Copyright	   2010 Jean-Louis Boulanger


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


package ABTOOLS.ANTLR_TOOLS;

import ABTOOLS.DEBUGGING.*;

// Java Paquetage
import java.io.*;        


public class Metrics 
{

// Activate or Desactivate Debug information 
    private DEBUG         debugging;

// Basic data
    protected int		nbLine			=	0;
	protected int		nbLineComment	=	0;
	protected int		nbLineInst		=	0;
	protected int		nbVarGlobal		=	0;
	protected int		nbCVarGlobal	=	0;
	protected int		nbConstant		=	0;
	protected int		nbAConstant		=	0;
	protected int		nbSet			=	0;
	protected int		nbService		=	0;

// Debugging and error management
    public void setDebug (DEBUG newdebug)
    {
	debugging = newdebug;
    }

// initialize
	public void reinit ()
    {
		nbLine			=	0;
		nbLineComment	=	0;
		nbLineInst		=	0;
		nbVarGlobal		=	0;
		nbCVarGlobal	=	0;
		nbConstant		=	0;
		nbAConstant		=	0;
		nbSet			=   0;
		nbService		=	0;
    }
	
// data Management
	public void addLine()
	{
		nbLine = nbLine +1;
	}

	public void addLineComment()
	{
		nbLineComment = nbLineComment +1;
	}
	
	public void addLineInst()
	{
		nbLineInst = nbLineInst +1;
	}
	
	public void addVarGlobal()
	{
		nbVarGlobal = nbVarGlobal +1;
	}

	public void addCVarGlobal()
	{
		nbCVarGlobal = nbCVarGlobal +1;
	}
	
	public void addAConstant()
	{
		nbAConstant = nbAConstant +1;
	}
	
	public void addConstant()
	{
		nbConstant = nbConstant +1;
	}
	
	public void addSet()
	{
		nbSet = nbSet +1;
	}

	public void addService()
	{
		nbService = nbService +1;
	}
	

	public void Print ()
	{
		System.out.println("BEGIN METRIC\n");
		System.out.println("+-------------------------------------------\n");
		System.out.println("Line               : " + nbLine + " Comment= " +nbLineComment + " Intsruction= " + nbLineInst+ "\n");
		System.out.println("Concrete CONSTANTS : " + nbConstant + "ABSTRACT CONSTANTS=" +nbAConstant +"\n");
		System.out.println("Nb VAR G           : " + nbVarGlobal + "  Nb Concrete VAR G :"+ nbCVarGlobal +"\n");
		System.out.println("Nb Set             : " + nbSet + "\n");
		System.out.println("Nb Service         : " + nbService + "\n");

		System.out.println("+-------------------------------------------\n");
		System.out.println("END METRIC\n");
		
	}
	
} // End of File Metrics.java
