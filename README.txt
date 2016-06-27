//
//      File            : README.txt
//      Author          : Boulanger Jean-Louis
//      Email           : jean.louis.boulanger@gmail.com
//
//	
//	Copyright 1999-2009 Boulanger Jean-Louis
//
// RELEASES
//      April 2001       Creation
//	May   2001	 Add some paragraph.
//	May   2008       change some things ..

What is it?
-----------  


Why?
----  

The Latest Version
------------------ 

See the file VERSION.txt for the current version.

This directory tree holds ABTools, a set of tools for the B method:
 - parser for B source programs.  
 - typechecker
 - ...

ABTools itself is written in Java, so it will run on any platform with a 
Java Virtual Machine (JVM).  However, it will only work with JDK 1.2 or later.  
ABTools produces an abstract syntax tree from a B source file, and gives 
the ability to query expressions for their types, and expressions, 
statements, and methods for the checked exceptions they throw.

ABTools uses ANTLR to create the lexer, parser, and abstract syntax tree
walkers that is uses.  See http://www.antlr.org/ for more information on
ANTLR.

Installation :
--------------

The file `INSTALL.txt' in this directory gives information on building and
installing ABTools.

Documentation
-------------
 
Documentation is available in HTML format, in the Documentation/ directory.
For building see Documentation/index.html.
For installing see Documentation/index.html.


BUG !
-----

Reports of bugs in ABTools should be sent to the ABTools maintainer.  
The maintainer is currently Jean-Louis Boulanger <jean.louis.boulanger@gmail.com>.  

See the file `BUGS.txt' in this directory for a list of known bugs in the current
version.


WHERE take it ?
---------------

The ABTools home page is http://www.chez.com/abtools/.  Updates,
bugfixes, and further documentation will appear there from time to
time.

The ABTools CVS site is now hosted by the project BRILLANT on Savannah

Home : http://savannah.gnu.org/projects/brillant/

CVS : http://savannah.gnu.org/cgi-bin/viewcvs/brillant/abtools/

Attention : Passage a GNA ...


Licensing
---------
 
This software is licensed under the terms you may find in the file
named "LICENSE.txt" in this directory. 
 



More to come
  
Thanks for using ABTOOLS.
 

----------------------------------------------------------------
Please send all your feedback to jean.louis.boulanger@gmail.com

// End of file README.txt
