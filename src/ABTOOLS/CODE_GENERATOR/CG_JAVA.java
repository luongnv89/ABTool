//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@gmail.com
// Project        : Is part of the project ABTOOLS 
//
// File           : CG_JAVA.java
// Description    : Generic class for Code generator from B to JAVA
// Copyright	   2004-2009 Jean-Louis Boulanger


// September 2004  v 0.1
//   - Creation
// August 2007     v 0.2
//   - Pretty printing
//	 - completion
// March 2009	   v 0.3
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

import java.io.*;

import ABTOOLS.DEBUGGING.*;

public class CG_JAVA extends CodeGenerator
{

// Module
    String module = "CG_JAVA.java";

    public CG_JAVA()
    {
		this.setExt("java");
    }

    public void BeginGen ( String module)
    {
		current = module;
		open();

		if (debug.Debug()== true)
		{
		}
		else
		{
		}

		writeln("//");
		writeln("// Class " + current + " generated from " + current + ".imp");
		writeln("//");
		writeln("");

		writeln("public class "+current);
		writeln("{\n");
    }

 // Generate DecVar
    public void DecVar (String svar, String stype)
    {
	if      (stype.compareTo("INT")==0)
	    write("int "+svar+";\n");
	else if (stype.compareTo("INT1")==0)
	    write("int "+svar+";\n");
	else if (stype.compareTo("INTEGER")==0)
	    write("int "+svar+";\n");
	else if (stype.compareTo("BOOL")==0)
	    write("boolean "+svar+";\n");
	else 
	    System.err.print(errors.Internal (module,"OP DecVar - Can't generate "+stype+" in JAVA"));
    }

 // Generate DecVar
    public String constructDecVar (String svar, String stype)
    {
	if      (stype.compareTo("INT")==0)
	    return ("int "+svar);
	else if (stype.compareTo("INT1")==0)
	    return ("int "+svar);
	else if (stype.compareTo("INTEGER")==0)
	    return ("int "+svar);
	else if (stype.compareTo("BOOL")==0)
	    return ("boolean "+svar);
	else 
	    return(errors.Internal (module,"OP constructDecVAR - Can't generate "+stype+" in JAVA"));
    }

 // generate the header
    public void Header ()
    {

    }

// Operation treatment
    public void Constructor()
    {
		write("\n /** \n * Constructeur \n **/\n\n");
		write("public "+current+"()\n");
    }

    public void Constructor(String name)
    {
		write("\n /** \n * Operation \n **/\n\n");
		write("public void "+name+"\n");
    }

    public void BeginOp()
    {
		write("{ \n");
    }

    public void EndOp()
    {
		write("} \n\n");
    }

// Comment
    public void Comment (String msg)
    {
		write("// "+msg+"\n");
    }

    /**
     * Code instruction generation
     **/
// Sequential
    public void Sequential ()
    {
		write ("; \n");
    }

 // Affectation
    public void Affectation (String var, String exp)
    {
		write(var + " = " + exp +"; \n"); 
    }

 // Condition : trasalte a B predicate to a JAVA Predicate
	public String Condition (String condition)
	{
		return("false");
	}
 
 // Generate IF structure without else
    public void IF (String condition, String b_then)
    {
		write("\n IF"+ Condition(condition)+" \n");
		write(     b_then+ "\n");
		write("\n ENDIF \n");
    }

// Generate IF structure with else
    public void IF (String condition, String b_then, String b_else)
    {
		write("\n IF"+ Condition(condition)+" \n");
		write(     b_then+ "\n");
		write(    "else \n");
		write(     b_else+"\n");
		write("\n ENDIF \n");
    }

    // Finalize the generation process
    public void EndGen()
    {
		writeln("} // End Of class "+current);
		close();
    }

}

// End of Class CG_JAVA
