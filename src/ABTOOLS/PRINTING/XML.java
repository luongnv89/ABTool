// 
// 	Author		:	Boulanger Jean-Louis	
//	EMAIL		:	jean.louis.boulanger@wanadoo.fr
// 	File		:	XML.java
//	Descripton	:	
//	Version		:	0.1
//	
//	Copyright 2000-2009 Boulanger Jean-Louis
//

// Releases
//	March 	2001	v0.1 	Creation
//

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/
package ABTOOLS.PRINTING;

import java.io.*;
import java.util.*;

public class XML
{

    public static final String[] colors = { "#f0f0f0", "#e0e0e0" };
    public static final String SINGLE_COLUMN = "***";
    static PrintWriter out;

    public static void status() throws IOException 
    {
        //PrintWriter out = new PrintWriter(response.getWriter());
        header(out);
	// que fait-on ......
        footer(out);
    }


    public static void print(String title, String message) throws IOException 
    {
	//        PrintWriter out = new PrintWriter(response.getWriter());
        header(out, 80);
        out.println("<h3 align=\"center\">" + title + "</h3>");
        if (message != null) out.println("<blockquote><pre>" + message + "</pre></blockquote>");
        footer(out);
    }

    public static void header(PrintWriter out) 
    {
        header(out, 90);
    }

    public static void header(PrintWriter out, int width) 
    {
        out.println(
            "<html>" +
            " <head>" +
            "  <meta name=\"GENERATOR\" content=\"" +
	    // introduire le numero de version ABTools.version() + 
	               "\">" +
            " </head>" +
            " <body>" +
            " <p><br></p>" +
            " <center>" +
            "  <table border=\"0\" width=\"" + width + "%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">" +
            "  <tr>" +
            "   <td width=\"100%\"><table border=\"0\" width=\"100%\" cellpadding=\"4\">" +
            "    <tr>" +
            "     <td width=\"100%\" bgcolor=\"#c0c0c0\"><p align=\"right\"><font color=\"red\"><big><big>" + 
	         //Cocoon.version() + 
	           "</big></big></font></td>" +
            "    </tr>" +
            "    <tr>" +
            "      <td width=\"100%\" bgcolor=\"#f0f0f0\">" +
            "       <p align=\"center\"><br></p>");
    }

    public static void footer(PrintWriter out) 
    {
        out.println(
            "      </td>" +
            "     </tr>" +
            "     <tr>" +
            "      <td width=\"100%\" bgcolor=\"#FFFFFF\">" +
            "       <strong>Warning</strong>: this page has been dynamically generated." +
            "      </td>" +
            "     </tr>" +
            "    </table>" +
            "   </td>" +
            "  </tr>" +
            " </table>" +
            " </center>" +
            " <p align=\"center\">" +
            "   <font size=\"-1\">" +
            "   Copyright (c) " + 
	    // YEAR + 
            " <a href=\"http://xml.apache.org\">The Apache XML Project</a>.<br>" +
            "   All rights reserved. " +
            "  </font>" +
            " </p>" +
            " </body>" +
            " </html>");
		out.close();
    }




}
