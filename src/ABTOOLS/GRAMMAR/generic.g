
// 
// 	Author		:	Boulanger Jean-Louis
//      Email           :   	jean.louis.boulanger@wanadoo.fr
// 	File		:	generic.g
//	Descripton	:	A PO Generator written in ANTLR
//
//	
//	Copyright 2001-2009s Boulanger Jean-Louis
//

// Releases
//  July 07 2001	v 0.1 	
//	- Creation
//  May	  2002		v 0.1.1
//	- Modify for use JAVA 2 (1.4) and ANTLR 2.7.2a
//  July  2004          v 1.1
//      - Packaging 
//  October 2004        v 1.2
//      - Pretty Printing
//      - Finalizing PO
//  September 2007
//      - Pretty Printing

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

header 
{
    package ABTOOLS.GRAMMAR;
}

// Import the necessary classes 
//-----------------------------------------------------------------------------
{
	import antlr.ASTFactory;
	import antlr.collections.ASTEnumeration;

// our packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;
}

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/


class generic extends Parser;
options 
{
	buildAST        = true;
    	importVocab     = B;
	exportVocab	= PO;
    	ASTLabelType    = "MyNode";
}

{
// Module
    protected String module = "generic.g";

// Error Management
	ErrorMessage errors = new ErrorMessage();

	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}

// Debug enable or disabled
	protected DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}

// The main tree
	protected       ASTterme previous_gop;


// Somes generals sub-PO
	public static 	MyNode 	A	    =	null;
	public static	MyNode 	B	    =	null;
	public static 	MyNode 	context	=	null;


// Somes generals informations
	public static   MyNode I;
	public static   MyNode C;
	public static   MyNode P;
	public static   MyNode INIT;

// The Proof obligations
	public static   MyNode POinit 	=   null;
    public static   MyNode POop     =   null;

// Basics Operations

	private void generateContext()
	{
// ATTENTION
// define A 
// define B
		A = null ;
        	B = null ;

// Valeur par defaut
		MyNode tmp =  #([BTRUE,"BTRUE"]);

//System.out.println(#C.toString());

		if (C != null & P != null)
			tmp	= #([B_AND,"&"], astFactory.dupTree(#C), astFactory.dupTree(#P) );
        	else if (C != null)
            		tmp = (MyNode) astFactory.dupTree(#C) ;
		else if (P != null)
			tmp	= (MyNode) astFactory.dupTree(#P);

		context = tmp;
	}

	public void setPreviousGOP(ASTterme ast)
	{
		ASTFactory astFactory = new ASTFactory();
		astFactory.setASTNodeType(MyNode.class.getName());

        	if (ast == null)
            		System.err.println(errors.Internal(module,"in OP setPreviousGOP: AST is null"));
        	else
        	{
// Memorized
            		previous_gop 	= ast;

// Prepared the INVARIANT
            		I= (MyNode) ast.getI();
            		if (I != null)
                		I.setComment("INVARIANT");

// Prepared the C
            		C= (MyNode) ast.getC();
            		if (C != null)
                		C.setComment("CONSTRAINTS");

// Prepared the P
            		P= (MyNode) ast.getP();
            		if (P!= null)
                		P.setComment("CONSTRAINTS");

// Prepared the INIT
            		INIT= (MyNode) ast.getINIT();
            		if (INIT != null)
                		INIT.setComment("INITIALISATION");

            		generateContext();
        	}
	}


	private void  poINITmch()
	{
		MyNode tmp1 = null;

		if (I != null)
			tmp1	= (MyNode) astFactory.dupTree(#I) ;
		else
			tmp1	= #([BTRUE,"BTRUE"]) ;

		if (INIT != null)
			tmp1 	= #([SUBST_TO,"[["], #INIT, #tmp1);
		else
			tmp1	= #([BTRUE,"BTRUE"]) ;

		if (context != null)
			POinit = #([APO,"APO"],#( [B_IMPLIES,"=>"], astFactory.dupTree(#context), #tmp1));
		else
			POinit = #([APO,"APO"],#tmp1);

// La PO de base:
//			POinit	= #([B_IMPLIES,"=>"]
//					, 	#([B_AND, "&"  ] , #C   , #P)
//					, 	#([APPLY_TO,"["] , #INIT, #I) 
//				       ); 
	}

    private void poOPmch()
    {
		MyNode tmp1 = null;

    }

    private void poOPref()
    {
		MyNode tmp1 = null;

    }

	public  void generate()
	{
		ASTFactory astFactory = new ASTFactory();
		astFactory.setASTNodeType("MyNode");

System.out.println("Debut du processus de generation des POs");

		generateContext();
		poINITmch();
        	poOPmch();

System.out.println("Fin du processus de generation des POs");
	}

// Fournit les PO
	public MyNode getPO()
	{
        if (POop != null)
		    return (MyNode) #([PO,"PO"], POinit, POop);
        else
		    return (MyNode) POinit;
	}
}

// Somes rules
rule :
	"bouffon"
;

// Somes imaginaries tokens for manipulate PO
dummy	:
        	PO
	|	APO
	|	BTRUE
;


// End of file generic.g
