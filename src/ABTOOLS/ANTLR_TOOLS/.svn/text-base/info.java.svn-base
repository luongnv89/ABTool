// 
// AUTHOR     : Boulanger Jean-Louis
// EMAIL      : jean.louis.boulanger@gmail.com
// DATE       : January 2010
// FILE       : info.java
// Copyright	   2000-2010 Jean-Louis Boulanger
//
// DESCRIPTION: An information class
//


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
 * The ABtools strings.
 *
 * @author <a href="mailto:jean.louis.boulanger@gmail.com">Jean-Louis Boulanger</a>
 *
 **/


// RELEASES
//   January 2000
//    Creation V 0.0
//
//   February 2000
//    First diffusion v 0.1
//
//   April 2000
//    V 0.2
//    Add some new informations and help
//    Change information on -showtree switch 
//
//   May 2000
//    v 0.2.1
//    Add The symbolTable switch
//
//   September 2000
//    v 0.2.2
//    Add The Latex switch
//
//   January 2001
//    v 0.2.3
//    Add The "bprime" switch
//    Add the getVersion method
//
//   February 2001
//    v 0.2.4
//    Add the new Default class
//
//   April 2001
//    v 0.2.5
//    Add help for "-loadLinked" switch
//
//   August 2001
//    v 0.2.6
//    Add help for "-license" and "-PO" switch
//
//   September 2001
//    v 0.2.7
//    Add help for "-normalise" and "-bsystem" switch
//
//   May 2002
//    v 0.2.8
//    Add help for "-bevent" switch
//
//   August  2003
//    v 0.2.9
//     - Add help for "-codeGenerator" switch
//     - modify ANTLR version
//
//   July    2004
//    v 0.3
//     - restructuring
//
//   September 2004
//    v 1.1
//     - modify impact of codeGeneration
//	March 2009	   v 1.2
//		 Change directory name and mail adress
//  October 2009
//       Add copyright and pretty-print

package ABTOOLS.ANTLR_TOOLS;

public class info implements Defaults 
{

    public String getVersion ()
    {
	 return version;
    }

    public static void info () 
    {
     System.out.println("ABTools is a - Another B Tools - produced by "+antlr_version);
     System.out.println("Author "+author+", Version "+version+ ", Copyright "+year);
    }

    public static void help () 
    {
     System.out.println("Help");
     System.out.println(" -bprime                : Use the B' compatibilities for parsing components      ");
     System.out.println(" -bsystem               : Use the B System compatibilities for parsing components");
     System.out.println(" -bevent                : Use the B Event  compatibilities for parsing components");
     System.out.println(" -debug                 : Request debug information                              ");
     System.out.println(" -decompile             : Decompile the AST to B                                 ");
     System.out.println(" -help                  : Show this help                                         ");
     System.out.println(" -info                  : Print some general information on this tools     ");
     System.out.println(" -license               : Rappel sur la license                            ");
     System.out.println(" -loadLinked            : Load all abstract machine which are linked       ");
     System.out.println(" -normalize             : Normalyze AST between treatment");
     System.out.println(" -PO                    : Generate all PO for current AMN                  ");
     System.out.println(" -showtree              : Request a AWT Windows for visualize the Tree     ");
     System.out.println(" -symbolTable           : Show the Symbol Table                            ");
     System.out.println(" -target directory      : Target for the output                            ");
     System.out.println(" -tex                   : Generate TEX source in <target> directory        ");
     System.out.println(" -xml                   : Generate xml source                              ");
     System.out.println(" -codeGenerator         : Generate JAVA code this switch imply -symbolTable, -normalize and -loadLinked");
    }

    public static void usage () 
    {
	     System.out.println( 
				 "Usage: java ABTools"  +
				 "["                    +
				 "-bprime | "            +
				 "-bsystem | "           +
				 "-bevent | "            +
				 "-debug | "            + 
				 "-decompile | "        +
				 "-help | "             +
				 "-info | "             +
				 "-license | "           +
				 "-loadLinked | "        +
				 "-normalize | "         +
				 "-PO | "                +
				 "-showtree | "         +
				 "-symbolTable | "      +
				 "-target <directory> | "+
				 "-tex | "               +
				 "-xml | "               +
				 "-codeGenerator "      +
				 "] "                   + 
				 "<directory or file name>");
    }

} // End of class info
