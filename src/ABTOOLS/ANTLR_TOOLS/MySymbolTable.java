//
// Author          Boulanger Jean-Louis
//                 jean.louis.boulanger@gmail.com
//
// File            MySymbolTable.java
// Description     A particular class which defined My Symbol table
// Copyright	   2000-2009 Jean-Louis Boulanger
 
 
// March 2000      v 0.0
//       Creation 
//
// December 2000   v 0.1
//       Add a field for memorize the initialisation of variable
//
// April    2002   v 0.2
//       Pretty-print the class
//
// April    2004   v 0.3
//       Add Package structuration
//
// July     2004   v 0.4
//       Stabilized
//
// September 2004  v 1.1
//       Add informations and service for variable implementation
//       Add ErrorMessages treatment
// September 2004  v 1.2
//       Add an operation currentScopeWithoutComponent
// August    2005  v 1.3
//       Correct the lookup operation (see BUG number 20).
//       Research a Name always in scopedName.
// March 2009	   v 1.4
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

// Java Packages
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

// our Packages
import ABTOOLS.DEBUGGING.*;

public class MySymbolTable 
{

    // Activate or Desactivate Debug information 
    private DEBUG         debugging ;
    private ErrorMessage  errors;

    private int MAX_SCOPE     = 10;
    private int MAX_STRING    = 100;

    /** 
     * holds list of scopes 
     **/
    private Vector scopeStack;


    /** 
     * table where all defined names are mapped to symbol 
     **/
    private Hashtable symbolTable;


    public MySymbolTable()  
    {
	scopeStack  = new Vector(MAX_SCOPE);
	symbolTable = new Hashtable(533);
	debugging   = new DEBUG();
    }

    public MySymbolTable(DEBUG debug)  
    {
	scopeStack  = new Vector(MAX_SCOPE);
	symbolTable = new Hashtable(533);
	debugging   = debug;
    }


    public void setDebug (DEBUG newdebug)
    {
	debugging = newdebug;
    }

    public void setErrors (ErrorMessage newerrors)
    {
	errors = newerrors;
    }

    /** 
     * push a new scope onto the scope stack.
     **/
    public void pushScope(String s) 
    {
	debugging.println("CLASS MySymbolTable, OP pushScope, PARAMETER s =" + s);

	scopeStack.addElement(s);
    }

    /** 
     * pop the last scope off the scope stack.
     **/
    public void popScope() 
    {
	debugging.println("CLASS MySymbolTable, OP popScope");

	int size = scopeStack.size();
	if(size > 0)
	    scopeStack.removeElementAt(size - 1);
    }


    /** 
     * return the current scope as a string 
     **/
    public String currentScopeAsString() 
    {
	debugging.println("CLASS MySymbolTable, OP currentScopeAsString");

	StringBuffer buf = new StringBuffer(MAX_STRING);
	boolean first    = true;
	Enumeration e    = scopeStack.elements();

	while(e.hasMoreElements()) 
	{
	    if (first) 
		first = false;
	    else
		buf.append("::");

	    buf.append(e.nextElement().toString());
	}
      return buf.toString();
    }


    /**
     * given a name for a type, append it with the current scope.
     **/
    public String addCurrentScopeToName(String name) 
    {
	debugging.println("CLASS MySymbolTable, OP addCurrentScopeToName, PARAMETER name = "+ name);

	String currScope = currentScopeAsString();
	return addScopeToName(currScope, name);
    }

    /** given a name for a type, append it with the given scope.
     */
    public String addScopeToName(String scope, String name) 
    {
	debugging.println("CLASS MySymbolTable, OP addScopeToName, PARAMETER scope = "+ scope +" and name = "+ name);

	if(scope == null || scope.length() > 0)
	    return scope + "::" + name;
	else
	    return name;
    }


    /**
     *  remove one level of scope from name 
     **/
    public String currentScopeWithoutComponent() 
    {
	debugging.println("CLASS MySymbolTable, OP currentScopeWithoutComponent");

	StringBuffer buf = new StringBuffer(MAX_STRING);
	boolean first    = true;
	Enumeration e    = scopeStack.elements();
	e.nextElement();

	while(e.hasMoreElements()) 
	{
	    if (first) 
		first = false;
	    else
		buf.append("::");

	    buf.append(e.nextElement().toString());
	}

	return buf.toString();
    }

    /**
     *  remove one level of scope from name 
     **/
    public String removeOneLevelScope(String scopeName) 
    {
	debugging.println("CLASS MySymbolTable, OP removeOneLevelScope, PARAMETER scopedName = "+ scopeName);

	int index = scopeName.lastIndexOf("::");

	if (index > 0) 
        {
	    return scopeName.substring(0,index);
	}

	if (scopeName.length() > 0) 
	{
	    return "";
	}

	return null;
    }

  
    /** 
     * add a node to the table with it's key as the current scope and the name 
     **/
    public MyNode add(String name, MyNode node) 
    {
	debugging.println("CLASS MySymbolTable, OP add, PARAMETER name = " + name + " and a node ");

	return (MyNode) symbolTable.put(addCurrentScopeToName(name),node);
    }


    /**
     * lookup a fully scoped name in the symbol table 
     **/
    public MyNode lookupScopedName(String scopedName) 
    {
	debugging.println("CLASS MySymbolTable, OP lookupScopedName, PARAMETER scopedName = " + scopedName);

	return (MyNode) symbolTable.get(scopedName);
    }


    /**
     * lookup an unscoped name in the table by prepending the current scope.
     *	-- if not found, pop scopes and look again
     **/
    public MyNode lookupNameInCurrentScope(String name) 
    {
	debugging.println("CLASS MySymbolTable, OP lookupNameInCurrentScope, PARAMETER name = " + name);

	String scope       = currentScopeAsString();
	String scopedName  = addScopeToName(scope, name);
	MyNode tnode       = (MyNode) symbolTable.get(scopedName);

	debugging.println( "\n"+ this.toString() );
 
	while (tnode == null && scope != null) 
	{
	    scopedName = addScopeToName(scope, name);

	    debugging.println("lookup trying " + scopedName);

	    tnode = (MyNode) symbolTable.get(scopedName);
	    scope = removeOneLevelScope(scope);
	};

	return tnode;
    }



    /**
     * convert this table to a string 
     **/
    public String toString() 
    {
	debugging.println("CLASS MySymbolTable, OP toString");

	StringBuffer buff = new StringBuffer(300);

	if ( currentScopeAsString()== "")
	{
	    buff.append(  "MySymbolTable   \n");
	    buff.append(  "CurrentScope   :  " + currentScopeAsString() + "\n");
	    buff.append(  "DefinedSymbols :\n");
	}

	Enumeration ke = symbolTable.keys();
	Enumeration ve = symbolTable.elements();
    
	while(ke.hasMoreElements()) 
	{
	    buff.append(    "key      "+ke.nextElement().toString() 
			  + " Element "+((MyNode)ve.nextElement()).toStringDecorated()
			  + "\n"

		       );
	}

	buff.append("\n");

	return buff.toString();
    }



} // End of class MySymbolTable
