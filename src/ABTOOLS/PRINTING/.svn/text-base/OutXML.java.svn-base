//
// Author         : Boulanger Jean-Louis
// Email          : jean.louis.boulanger@wanadoo.fr
//
// File           : OutXML.java
// Description    : Permet d'imprimer un AST sous la forme d'un fichier XML
//


// RELEASES
// January 2003    v 0.1
//       Creation
// July    2004    v 0.2
//       Packaging and stabilized
            
package ABTOOLS.PRINTING;
                 
public class OutXML extends OUT
{

    /** Differentes fonctions permettant de faire une sortie 
	coherente dans un fichier XML
    */

    public String Clause ( String txt)
    {
	// On a un enorme serie de if un par clause

	// Notes de conception:
	// 1. il faut doubler le \ pour eviter l'interpretation
	// 2. on a utiliser le if au lieu du switch suite au restriction (pas de string et pas d'expressions)

	String out;

	if (txt.compareTo("MACHINE")==0) 
	    out = "\\BMACHINE \n";
	else
	    if (txt.compareTo("END") ==0)
	    out = "\\BEND \n";
	else    
	    if (txt.compareTo("REFINEMENT" )==0)
	    out = "\\BREFINEMENT \n";
	else    
	    if (txt.compareTo("IMPLEMENTATION")==0)
	    out = "\\BIMPLEMENTATION \n";
	else    
	    if (txt.compareTo("CONSTRAINTS")==0)
	    out = "\\BCONSTRAINTS \n";
	else    
	    if (txt.compareTo("EXTENDS")==0)
	    out = "\\BEXTENDS \n";
	else    
	    if (txt.compareTo("USES")==0)
	    out = "\\BUSES \n";
	else    
	    if (txt.compareTo("INCLUDES")==0)
	    out = "\\BINCLUDES \n";
	else    
	    if (txt.compareTo("SEES")==0)
	    out = "\\BSEES \n";
	else    
	    if (txt.compareTo("IMPORTS")==0)
	    out = "\\BIMPORTS \n";
	else    
	    if (txt.compareTo("PROMOTES")==0)
	    out = "\\BPOMOTES \n";
	else    
	    if (txt.compareTo("REFINES") ==0)
	    out = "\\BREFINES \n";
	else    
	    if (txt.compareTo("CONSTANTS")==0)
	    out = "\\BCONSTANTS \n";
	else    
	    if (txt.compareTo("CONCRETE_CONSTANTS") ==0)
	    out = "\\BCONCRETECONSTANTS \n";
	else    
	    if (txt.compareTo("VISIBLE_CONSTANTS") ==0)
	    out = "\\BCONSTRAINTS \n";
	else    
	    if (txt.compareTo("ABSTRACT_CONSTANTS")==0)
	    out = "\\BABSTRACTCONSTANTS \n";
	else    
	    if (txt.compareTo("HIDDEN_CONSTANTS")==0)
	    out = "\\BHIDDENCONSTANTS \n";
	else    
	    if (txt.compareTo("SETS") ==0)
	    out = "\\BSETS \n"; 
	else
	    if (txt.compareTo("PROPERTIES") ==0)
	    out = "\\BPROPERTIES \n "; 
	else
	    if (txt.compareTo("VARIABLES") ==0)
	    out = "\\BVARIABLES \n "; 
	else
	    if (txt.compareTo("ABSTRACT_VARIABLES") ==0) 
	    out = "\\ABSTRACTVARIABLES \n "; 
	else
	    if (txt.compareTo("VISIBLE_VARIABLES") ==0)
	    out = "\\VISIBLEVARIABLES \n "; 
	else
	    if (txt.compareTo("INVARIANT") ==0)
	    out = "\\BINVARIANT \n "; 
	else
	    if (txt.compareTo("HIDDEN_VARIABLES") ==0)
	    out = "\\BHIDDENVARIABLES \n"; 
	else
	    if (txt.compareTo("CONCRETE_VARIABLES") ==0)
	    out = "\\BCONCRETEVARIABLES \n"; 
	else
	    if (txt.compareTo("DEFINITIONS") ==0)
	    out = "\\BDEFIITIONS \n "; 
	else
	    if (txt.compareTo("ASSERTIONS") ==0)
	    out = "\\BASSERTIONS \n "; 
	else
	    if (txt.compareTo("INITIALISATION") ==0)
	    out = "\\BINITIALISATION \n "; 
	else
	    if (txt.compareTo("OPERATIONS") ==0)
	    out = "\\BOPERATIONS \n ";
	else
	    if (txt.compareTo("VALUES") ==0)
	    out = "\\BVALUES \n ";
        else    
	{
	    System.out.println("INTERNAL ERROR: Token "+txt+" is not a clause ..");
	    out = "\n";
	}

	return (out);
    }

    public String KeyWord ( String txt)
    {
	// On a un enorme serie de if pour chaque keyword

	// Notes de conception:
	// 1. il faut doubler le \ pour eviter l'interpretation
	// 2. on a utiliser le if au lieu du switch suite au restriction (pas de string et pas d'expressions)

	String out;

	if (txt.compareTo("rec")==0) 
	    out = "\\BREC "; else
	if (txt.compareTo("TRUE")==0) 
	    out = "\\BTRUE "; else
	if (txt.compareTo("FALSE")==0) 
	    out = "\\BFALSE "; else
	if (txt.compareTo("skip")==0) 
	    out = "\\BSKIP "; else
	if (txt.compareTo("BEGIN")==0) 
	    out = "\\BBEGIN "; else
	if (txt.compareTo("END")==0) 
	    out = "\\BEND "; else
	if (txt.compareTo("PRE")==0) 
	    out = "\\BPRE "; else
	if (txt.compareTo("THEN")==0) 
	    out = "\\BTHEN "; else
	if (txt.compareTo("ASSERT")==0) 
	    out = "\\BELSE "; else
	if (txt.compareTo("IF")==0)
	    out = "\\BIF "; else
	if (txt.compareTo("ELSIF")==0) 
	    out = "\\BELSIF "; else
	if (txt.compareTo("CHOICE")==0) 
	    out = "\\BCHOICE "; else
	if (txt.compareTo("OR")==0) 
	    out = "\\BOR "; else
	if (txt.compareTo("CASE")==0) 
	    out = "\\BCASE "; else
	if (txt.compareTo("OF")==0) 
	    out = "\\BOF "; else
	if (txt.compareTo("EITHER")==0) 
	    out = "\\BEITHER "; else
	if (txt.compareTo("ELSE")==0) 
	    out = "\\BELSE "; else
	if (txt.compareTo("ANY") == 0) 
	    out = "\\BANY "; else
	if (txt.compareTo("WHERE")==0) 
	    out = "\\BWHERE "; else
	if (txt.compareTo("SELECT")==0) 
	    out = "\\BSELECT "; else
	if (txt.compareTo("WHEN")==0) 
	    out = "\\BWHEN "; else
	if (txt.compareTo("LET")==0) 
	    out = "\\BLET "; else
	if (txt.compareTo("BE")==0) 
	    out = "\\BBE "; else
	if (txt.compareTo("IN")==0)
	    out = "\\BIN "; else
	if (txt.compareTo("VARIANT")==0) 
	    out = "\\BVARIANT "; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "\\BINVARIANT "; else
	if (txt.compareTo("WHILE")==0) 
	    out = "\\BWHILE "; else
	if (txt.compareTo("DO")==0) 
	    out = "\\BDO "; else
	if (txt.compareTo("VAR")==0) 
	    out = "\\BVAR "; else
	if (txt.compareTo("mod")==0) 
	    out = "\\BMOD "; else
	if (txt.compareTo("bool")==0) 
	    out = " "; else
	if (txt.compareTo("INT")==0) 
	    out = "\\BINT "; else
//	if (txt.compareTo("INT1")==0) out = "\\BINT1 "; else
	if (txt.compareTo("INTEGER")==0) 
	    out = "\\BINTEGER "; else
//	if (txt.compareTo("INTEGER1")==0) out = " "; else
	if (txt.compareTo("BOOL")==0) 
	    out = "\\BBOOL "; else
	if (txt.compareTo("NAT")==0) 
	    out = "\\BNAT "; else
	if (txt.compareTo("NAT1")==0) 
	    out = "\\BNATONE "; else
	if (txt.compareTo("NATURAL")==0) 
	    out = "\\BNATURAL "; else
	if (txt.compareTo("NATURAL1")==0) 
	    out = "\\BNATURALONE ";
        else    
	{
	    System.out.println("INTERNAL ERROR: Token "+txt+" is not a keyword ..");
	    out = "\n";
	}

	return (out);
    }


    public String Separator ( String txt)
    {
	// On a un enorme serie de if un pour chaque separator

	// Notes de conception:
	// 1. il faut doubler le \ pour eviter l'interpretation
	// 2. on a utiliser le if au lieu du switch suite au restriction (pas de string et pas d'expressions)

	String out;

	if (txt.compareTo("(") ==0 ) 
	    out = "("; 
	else
	if (txt.compareTo(")")==0) 
	    out = ")"; 
	else
	if (txt.compareTo(",")==0) 
	    out = " \\BCOMMA "; 
	else
	if (txt.compareTo(":")==0) 
	    out = " \\BIN "; 
	else
	if (txt.compareTo(";")==0) 
	    out = " \\BSEMICOLON ";
	else
	if (txt.compareTo("=")==0) 
	    out = " \\BEQUAL "; 
	else
	if (txt.compareTo("*")==0) 
	    out = " \\BPROD "; 
	else
	if (txt.compareTo("+")==0) 
	    out = " \\BADD "; 
	else
	if (txt.compareTo("-")==0) 
	    out = " \\BMINUS "; 
	else
	if (txt.compareTo("{")==0) 
	    out = "{ "; 
	else
	if (txt.compareTo("}")==0) 
	    out = "}"; 
	else
	if (txt.compareTo("|->")==0) 
	    out = "\\BMAPLET"; 
	else
	if (txt.compareTo("..")==0) 
	    out = "\\BRANGE"; 
	else
	if (txt.compareTo("==")==0) 
	    out = "\\BFUNDEF"; 
	else
	if (txt.compareTo("<--")==0) 
	    out = "\\BSUBSTOPERCALL"; 
	else
	if (txt.compareTo("||")==0) 
	    out = "\\BPARALLELSUBS"; 
	else
	if (txt.compareTo("&")==0) 
	    out = "\\BAND"; 
	else
	if (txt.compareTo("[")==0) 
	    out = "\\BRACKOPEN"; 
	else
	if (txt.compareTo("]")==0) 
	    out = "\\BRACKCLOSE"; else
	if (txt.compareTo("~")==0) 
	    out = "\\BTILDE"; 
	else
	if (txt.compareTo(".")==0) 
	    out = "\\BPOINT"; 
	else
	if (txt.compareTo("$0")==0) 
	    out = "\\BPRED"; 
	else
	if (txt.compareTo("'")==0) 
	    out = "'"; 
	else
	if (txt.compareTo(":=")==0) 
	    out = "\\BSIMPLESUBST"; 
	else
	if (txt.compareTo(":(")==0) 
	    out = "\\BBECOMEELEM"; 
	else
	if (txt.compareTo("or") == 0)
	    out = " or "; 
	else
	if (txt.compareTo("=>")==0) 
	    out = "IMPLIES"; 
	else 
	if (txt.compareTo("<=>")==0) 
	    out = "EQUIV"; 
	else
	if (txt.compareTo("**")==0) 
	    out = "\\BPOWER"; 
	else
	if (txt.compareTo("div")==0) 
	    out = "\\BDIV"; 
	else
	if (txt.compareTo("mod")==0) 
	    out = "mod";
	else
	if (txt.compareTo("<")==0) 
	    out = "\\BLESS"; 
	else
	if (txt.compareTo(">")==0) 
	    out = "\\BGREATER"; 
	else
	if (txt.compareTo("!=")==0) 
	    out = "\\BNOTEQUAL"; 
	else
	if (txt.compareTo("<=")==0) 
	    out = "\\BLESSTHANEQUAL"; 
	else
	if (txt.compareTo(">=")==0) 
	    out = "\\BGREATERTHANEQUAL"; 
	else
	if (txt.compareTo("<--")==0) 
	    out = "\\BCONCATSEQ"; 
	else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "PREAPPSEQ"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "APPSEQ"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "PREFIXSEQ"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "SUFFIXSEQ"; 
	else
	if (txt.compareTo("bool")==0) 
	    out = "bool";
	else
	if (txt.compareTo("<->")==0) 
	    out = "RELATION"; else
	if (txt.compareTo("+->")==0) 
	    out = "PARTIAL"; else
	if (txt.compareTo("-->")==0) 
	    out = "TOTAL"; else
	if (txt.compareTo(">+->")==0) 
	    out = "PARTIALINJECT"; else
	if (txt.compareTo(">->")==0) 
	    out = "TOTALINJECT"; else
	if (txt.compareTo("+->>")==0) 
	    out = "PARTIALSURJECT"; else
	if (txt.compareTo("-->>")==0) 
	    out = "TOTALSURJECT"; else
	if (txt.compareTo(">->>")==0) 
	    out = "BIJECTION"; else
	if (txt.compareTo("<|")==0) 
	    out = "DOMAINRESTRICT"; else
	if (txt.compareTo("<+")==0) 
	    out = "RANGERESTRICT"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "DOMAINSUBSTRACT"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "RANGESUBSTRACT"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "OVERRIDEFORWARD"; else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "OVERRIDEBACKWARD"; 
	else
	if (txt.compareTo("INVARIANT")==0) 
	    out = "RELPROD";
	else
	if (txt.compareTo("\\/")==0) 
	    out = "\\BUNION"; 
	else
	if (txt.compareTo("/\\")==0) 
	    out = "\\BINTER"; 
	else
	if (txt.compareTo("[]")==0) 
	    out = "\\BSEQEMPTY"; 
	else
	if (txt.compareTo("{}")==0) 
	    out = "\\BEMPTYSET"; 
	else
	if (txt.compareTo("!:")==0) 
	    out = "\\BNOTINSET"; 
	else
	if (txt.compareTo("<:")==0) 
	    out = "SUBSET"; 
	else
	if (txt.compareTo("!<:")==0) 
	    out = "NOTSUBSET"; 
	else
	if (txt.compareTo("<")==0) 
	    out = "STRICTSUBSET"; 
	else
	if (txt.compareTo("!<")==0) 
	    out = "NOTSTRICTSBSET"; 
	else
	if (txt.compareTo("!")==0) 
	    out = "\\BFORALL"; 
	else
	if (txt.compareTo("#")==0) 
	    out = "\\BEXISTS";
	else
	if (txt.compareTo("%")==0) 
	    out = "\\BLAMBDA"; 
	else
	if (txt.compareTo("PI")== 0)
            out = "\\BPI"; 
	else
	if (txt.compareTo("SIGMA") == 0) 
            out = "\\BSIGMA"; 
	else
	if (txt.compareTo("UNION") == 0) 
            out = "\\BbigUNION"; 
	else
	if (txt.compareTo("INTER") == 0) 
            out = "\\BbigINTER"; 
	else
	    /*
SEQUENTIAL
PARALLEL
LISTDEF
OPDEF
INSET
FUNCCALLPARAM
AFUNCCALL
APPLYTO
	    */
	if (txt.compareTo("$0")==0) 
	    out = "\\BDOLLARS" ; 
	else
	if (txt.compareTo("|")==0) 
	    out = "\\BSUCH";
        else    
	{
	    System.out.println("INTERNAL ERROR: Token "+txt+" is not a keyword ..");
	    out = "\n";
	}

	return (" "+out+" ");
    }

} // end of file OutXML.java
