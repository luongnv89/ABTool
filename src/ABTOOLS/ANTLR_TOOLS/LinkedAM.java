//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            LinkedAM.java
// Description     A collection of Abstract Machine.
// Copyright	   2001-2010 Jean-Louis Boulanger
 
 
// April 2001      v 0.1
//       Creation
// April 2004      v 0.2
//       Packaging and Restructuring
// July  2004      v 0.3
//       Finalized Packaging
// March 2009	   v 0.4
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

package ABTOOLS.ANTLR_TOOLS;

import ABTOOLS.DEBUGGING.*;

public class LinkedAM
{
    /** 
     * Debugging object
     **/
    private DEBUG     debugging;

    /**
     * Data structure
     **/
    String AMname;
    String AMlink;
    
    String extension = "";
    String path = "";

    public LinkedAM()
    {
	debugging = new DEBUG();
    }

    public LinkedAM(String name, String link, String ext, String pathFile)
    {
	AMname    = name;
	AMlink    = link;
	debugging = new DEBUG();
	extension = ext;
	path = pathFile;
    }
    
    public LinkedAM(String name, String link, String ext, String pathFile, DEBUG debug)
    {
	AMname    = name;
	AMlink    = link;
	debugging = debug;
	extension = ext;
	path = pathFile;
    }

    // Access to internal Data

    public String getName ()
    {
	debugging.println("CLASS AbstractMachine, OP getName");

	return AMname;
    }

    public String getLink ()
    {
	debugging.println("CLASS AbstractMachine, OP getLink");

	return AMlink;
    }
    
    public String getExtension(){
    	return extension;
    }

    public String getPath(){
    	return path;
    }

    public boolean isLink (String link)
    {
	debugging.println("CLASS AbstractMachine, OP isLink, Param "+link);

	return (AMlink.compareTo(link)==0);
    }

    public String toString()
    {
	return "AM Name "+AMname+" AM link"+AMlink;
    }

} // end of class LinkedAM
