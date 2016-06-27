//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
//
// File           : CodeGenerator.java
// Description    : Generic class for Code generator from B to X
//


// September 2004  v 0.1
//   - Creation
// March 2009	   v 0.2
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

package ABTOOLS.CODE_GENERATOR;

// Java Package
import java.io.*;

// our package
import ABTOOLS.DEBUGGING.*;

public class CodeGenerator
{

// Module
    String module = "CodeGenerator.java";

// Create Out File and it's writer
    static File       out;
    static FileWriter outwriter;

// name of current B component
    String         current = "ToBeDefined";

// extension du to specific generation
    static String  extension ="ToBeDefined";

    ErrorMessage   errors;
    DEBUG          debug;

// Manage Errors
    public void setErrors ( ErrorMessage err)
    {
	errors = err;
    }

    public String getErrors ()
    {
	return errors.toString();
    }

// Manage debugging
    public void setDebug ( DEBUG newdebug)
    {
	debug = newdebug;
    }

    public void EnableDebug ()
    {
	debug.Enable();
    }

    public void DisableDebug ()
    {
	debug.Disable();
    }

// Initiate the generation process

    static String path = "To Be Defined";

    public void setPath(String newpath)
    {
	path = newpath;
    }

    public void setExt(String newext)
    {
	extension = newext;
    }

    public void setModule(String newmodule)
    {
	current = newmodule;
    }

    public void BeginGen ( String module)
    {
	current = module;
    }

    public void DecVar (String svar, String stype)
    {
    }

    public String constructDecVar (String svar, String stype)
    {
	return (stype + " " + svar);
    }

    public String concatParam (String p1, String p2)
    {
	return (p1 + "," + p2);
    }

// Operation treatment
    public void Constructor()
    {
    }

    public void Constructor(String name)
    {
    }

    public void BeginOp()
    {
    }

    public void EndOp()
    {
    }

    public String constructOPHeader(String name, String param)
    {
	return ( name + "(" + param +")");
    }

// generate the header
    public void Header ()
    {
    }

// Comment
    public void Comment (String msg)
    {
    }


// Data
    public String genereTrue ()
    {
	return ("true");
    }
	
    public String genereFalse ()
    {
	return ("false");
    }

    /**
     * Code instruction generation
     **/

// Affectation
    public void Affectation (String var, String exp)
    {
    }

// Sequential
    public void Sequential ()
    {
    }

    // Generate IF structure without else
    public void IF (String condition, String b_then)
    {
    }

    // Generate IF structure with else
    public void IF (String condition, String b_then, String b_else)
    {
    }

    // Finalize the generation process
    public void EndGen()
    {
    }


    public String GiveCompleteName ()
    {
	return(path + "/" + current + "." + extension);
    }

// Predicate
    public String And (String p1, String p2)
    {
	return(p1 + "&&" + p2);
    }
    public String Or (String p1, String p2)
    {
	return(p1 + "||" + p2);
    }
    public String Mult (String p1, String p2)
    {
	return(p1 + "*" + p2);
    }
    public String Plus (String p1, String p2)
    {
	return(p1 + "+" + p2);
    }
    public String Minus (String p1, String p2)
    {
	return(p1 + "-" + p2);
    }
    public String Power (String p1, String p2)
    {
	return(p1 + "^" + p2);
    }
    public String Div (String p1, String p2)
    {
	return(p1 + "/" + p2);
    }
    public String Mod (String p1, String p2)
    {
	return(p1 + "%" + p2);
    }
    public String Equal (String p1, String p2)
    {
	return(p1 + "==" + p2);
    }
    public String NotEqual (String p1, String p2)
    {
	return(p1 + "!=" + p2);
    }
    public String Less (String p1, String p2)
    {
	return(p1 + "<" + p2);
    }
    public String LessEqual (String p1, String p2)
    {
	return(p1 + "<=" + p2);
    }
    public String Greater (String p1, String p2)
    {
	return(p1 + ">" + p2);
    }
    public String GreaterEqual (String p1, String p2)
    {
	return(p1 + ">=" + p2);
    }

// Manage File
    public void open ()
    {
	try 
	{
	    out       = new File(GiveCompleteName()) ;
	    outwriter = new FileWriter(out); 
	}
	catch (Exception e) 
	{
	    System.err.println(errors.Internal(module,"Exception: "+e));
	    e.printStackTrace();              // so we can get stack trace 
	}
    }

    public void write (String str)
    {
	try 
	{
	    outwriter.write(str);
	}
	catch (Exception e) 
	{
	    System.err.println(errors.Internal(module,"Exception: "+e));
	    e.printStackTrace();              // so we can get stack trace 
	}
    }

    public void writeln (String str)
    {
	try 
	{
	    outwriter.write(str+"\n");
	}
	catch (Exception e) 
	{
	    System.err.println(errors.Internal(module,"Exception: "+e));
	    e.printStackTrace();              // so we can get stack trace 
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

}

// End of Class CodeGenerator
