//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : DEBUG.java
// Description    : Management of debug and debug information (to screen, to file, ...)
// Copyright	  : 2000-2009 Jean-Louis Boulanger
 
//
// RELASES
//
// December  2000  v 0.1       Creation ofthis new file. 
// April     2001  v 0.2       Add the notion of out file for debug.
//                             and Print, Open and Close method
// April     2004  v 0.3       Restructuring and Packaging
// July      2004  v 0.4       Stabilized
// September 2004  v 0.5
//                            - Change name of Enable/Disable method
//                            - Add toString 
//                            - Pretty Printing
//                            - Introduce module variable and errors management
// March 2009	   v 0.6
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

// Java Paquetage
import java.io.*;


public class DEBUG
{

// Module
    String module = "DEBUG.java";

// DEBUG or not ...
    protected boolean debugging = false;

// where I print debug information ...
    protected int     type      = 0;

// Create Out File and it's writer
    static File       out;
    static FileWriter outwriter;

// Error Management
    ErrorMessage errors;
  
    public DEBUG()
    {
		debugging = false;
    }

    public DEBUG(ErrorMessage err)
    {
		debugging = false;
		errors    = err;
    }

    public void setErrors(ErrorMessage err)
    {
		errors    = err;
    }

// Debug enable or disabled
    public void Disable()
    {
		debugging = false;
    }
    
    public void Enable()
    {
		debugging = true;
    }
    
    public void EnabletoFile()
    {
		debugging = true;
		type = 1;
    }

    public boolean Debug ()
    {
		return debugging;
    }

// File Management
    public void open (String target, String name)
    {
	 try 
	 {
	     type      = 1;
	     out       = new File(target+"/"+name) ;
	     outwriter = new FileWriter(out); 
	 }
	 catch (Exception e) 
	 {
	     System.err.println(errors.Internal(module,"Exception: "+e));
	     e.printStackTrace();              // so we can get stack trace 
	     type = 2;
	 }
    }

    public void print (String debugTrace)
    {
	if (debugging)
	{
	    if (type == 0)   // Print on cureent screen
	    {
			System.out.println(debugTrace);
	    }
	    else             // Print in file 
	    {
		try 
		{
		    outwriter.write(debugTrace);
		}
		catch (Exception e) 
		{
		    System.err.println(errors.Internal(module,"Exception: "+e));
		    e.printStackTrace();              // so we can get stack trace 
		}
	    }
	}
    }

    public void println (String debugTrace)
    {
	if (debugging)
	{
	    if (type == 0)   // Print on cureent screen
	    {
			System.out.println(debugTrace+"\n");
	    }
	    else             // Print in file 
	    {
		try 
		{
		    outwriter.write(debugTrace);
		}
		catch (Exception e) 
		{
		    System.err.println(errors.Internal(module,"Exception: "+e));
		    e.printStackTrace();              // so we can get stack trace 
		}
	    }
	}
    }

    public void close ()
    {
	try 
	{
	    outwriter.close();
	}
	catch (Exception e) 
	{
	    System.err.println(errors.Internal(module,"Exception: "+e));
	    e.printStackTrace();              // so we can get stack trace 
	}
    }

}	// End of file "DEBUG.java"
