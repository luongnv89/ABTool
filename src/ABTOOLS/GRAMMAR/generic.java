// $ANTLR 2.7.6 (2005-12-22): "generic.g" -> "generic.java"$

    package ABTOOLS.GRAMMAR;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

	import antlr.ASTFactory;
	import antlr.collections.ASTEnumeration;

// our packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;

/**
 * @author <a href="mailto:jean.louis.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/
public class generic extends antlr.LLkParser       implements POTokenTypes
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
		MyNode tmp =  (MyNode)astFactory.make( (new ASTArray(1)).add((MyNode)astFactory.create(BTRUE,"BTRUE")));

//System.out.println(#C.toString());

		if (C != null & P != null)
			tmp	= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_AND,"&")).add(astFactory.dupTree(C)).add(astFactory.dupTree(P)));
        	else if (C != null)
            		tmp = (MyNode) astFactory.dupTree(C) ;
		else if (P != null)
			tmp	= (MyNode) astFactory.dupTree(P);

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
			tmp1	= (MyNode) astFactory.dupTree(I) ;
		else
			tmp1	= (MyNode)astFactory.make( (new ASTArray(1)).add((MyNode)astFactory.create(BTRUE,"BTRUE"))) ;

		if (INIT != null)
			tmp1 	= (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(SUBST_TO,"[[")).add(INIT).add(tmp1));
		else
			tmp1	= (MyNode)astFactory.make( (new ASTArray(1)).add((MyNode)astFactory.create(BTRUE,"BTRUE"))) ;

		if (context != null)
			POinit = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(APO,"APO")).add((MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(B_IMPLIES,"=>")).add(astFactory.dupTree(context)).add(tmp1))));
		else
			POinit = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(APO,"APO")).add(tmp1));

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
		    return (MyNode) (MyNode)astFactory.make( (new ASTArray(3)).add((MyNode)astFactory.create(PO,"PO")).add(POinit).add(POop));
        else
		    return (MyNode) POinit;
	}

protected generic(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public generic(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected generic(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public generic(TokenStream lexer) {
  this(lexer,1);
}

public generic(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void rule() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode rule_AST = null;
		
		try {      // for error handling
			MyNode tmp1_AST = null;
			tmp1_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1_AST);
			match(LITERAL_bouffon);
			rule_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = rule_AST;
	}
	
	public final void dummy() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode dummy_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case PO:
			{
				MyNode tmp2_AST = null;
				tmp2_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp2_AST);
				match(PO);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case APO:
			{
				MyNode tmp3_AST = null;
				tmp3_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp3_AST);
				match(APO);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case BTRUE:
			{
				MyNode tmp4_AST = null;
				tmp4_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp4_AST);
				match(BTRUE);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = dummy_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"B_COMMA",
		"an identifer",
		"B_POINT",
		"B_CPRED",
		"B_IMPLIES",
		"\"or\"",
		"B_AND",
		"B_EQUIV",
		"B_EQUAL",
		"B_SUBSET",
		"B_NOTSUBSET",
		"B_STRICTSUBSET",
		"B_NOTSTRICTSBSET",
		"B_LESS",
		"B_GREATER",
		"B_NOTEQUAL",
		"B_LESSTHANEQUAL",
		"B_GREATERTHANEQUAL",
		"B_CONCATSEQ",
		"B_PREAPPSEQ",
		"B_APPSEQ",
		"B_PREFIXSEQ",
		"B_SUFFIXSEQ",
		"\"bool\"",
		"B_LPAREN",
		"B_RPAREN",
		"B_RELATION",
		"B_PARTIAL",
		"B_TOTAL",
		"B_PARTIAL_INJECT",
		"B_TOTAL_INJECT",
		"B_PARTIAL_SURJECT",
		"B_TOTAL_SURJECT",
		"B_BIJECTION",
		"B_DOMAINRESTRICT",
		"B_RANGERESTRICT",
		"B_DOMAINSUBSTRACT",
		"B_RANGESUBSTRACT",
		"B_OVERRIDEFORWARD",
		"B_OVERRIDEBACKWARD",
		"B_RELPROD",
		"B_UNION",
		"B_INTER",
		"B_MAPLET",
		"B_MULT",
		"B_POWER",
		"B_DIV",
		"\"mod\"",
		"B_ADD",
		"B_MINUS",
		"B_SEQEMPTY",
		"B_BRACKOPEN",
		"B_BRACKCLOSE",
		"B_RANGE",
		"B_EMPTYSET",
		"B_CURLYOPEN",
		"B_CURLYCLOSE",
		"B_SUCH",
		"a string literal",
		"\"TRUE\"",
		"\"FALSE\"",
		"\"rec\"",
		"B_TILDE",
		"a number",
		"B_QUOTEIDENT",
		"\"ran\"",
		"\"not\"",
		"\"dom\"",
		"\"min\"",
		"\"max\"",
		"\"card\"",
		"B_SEMICOLON",
		"B_PARALLEL",
		"B_FORALL",
		"B_EXISTS",
		"B_LAMBDA",
		"\"PI\"",
		"\"SIGMA\"",
		"\"UNION\"",
		"\"INTER\"",
		"\"struct\"",
		"B_INSET",
		"\"INT\"",
		"\"INT1\"",
		"\"INTEGER\"",
		"\"INTEGER1\"",
		"\"BOOL\"",
		"\"NAT\"",
		"\"NAT1\"",
		"\"NATURAL\"",
		"\"NATURAL1\"",
		"UNARY_ADD",
		"UNARY_MINUS",
		"ELEM_SET",
		"LIST_VAR",
		"APPLY_TO",
		"PARENT",
		"B_SELECTOR",
		"PRODSET",
		"B_NOT",
		"B_DOM",
		"B_RAN",
		"B_MIN",
		"B_MAX",
		"B_CARD",
		"B_MOD",
		"B_NOTINSET",
		"\"STRING\"",
		"white space",
		"a comment",
		"NEWLINE",
		"B_DOUBLE_EQUAL",
		"B_dollars",
		"B_OUT",
		"B_garde",
		"B_BECOME_ELEM",
		"B_SIMPLESUBST",
		"DIGIT",
		"ALPHA",
		"an escape sequence",
		"\"MACHINE\"",
		"\"END\"",
		"\"REFINEMENT\"",
		"\"IMPLEMENTATION\"",
		"\"CONSTRAINTS\"",
		"\"EXTENDS\"",
		"\"USES\"",
		"\"INCLUDES\"",
		"\"SEES\"",
		"\"IMPORTS\"",
		"\"PROMOTES\"",
		"\"REFINES\"",
		"\"CONSTANTS\"",
		"\"CONCRETE_CONSTANTS\"",
		"\"VISIBLE_CONSTANTS\"",
		"\"ABSTRACT_CONSTANTS\"",
		"\"HIDDEN_CONSTANTS\"",
		"\"SETS\"",
		"\"VALUES\"",
		"\"PROPERTIES\"",
		"\"VARIABLES\"",
		"\"ABSTRACT_VARIABLES\"",
		"\"VISIBLE_VARIABLES\"",
		"\"INVARIANT\"",
		"\"HIDDEN_VARIABLES\"",
		"\"CONCRETE_VARIABLES\"",
		"\"DEFINITIONS\"",
		"\"ASSERTIONS\"",
		"\"INITIALISATION\"",
		"\"OPERATIONS\"",
		"\"skip\"",
		"\"BEGIN\"",
		"\"PRE\"",
		"\"THEN\"",
		"\"ASSERT\"",
		"\"IF\"",
		"\"ELSIF\"",
		"\"CHOICE\"",
		"\"OR\"",
		"\"CASE\"",
		"\"OF\"",
		"\"EITHER\"",
		"\"ELSE\"",
		"\"ANY\"",
		"\"WHERE\"",
		"\"SELECT\"",
		"\"WHEN\"",
		"\"LET\"",
		"\"BE\"",
		"\"IN\"",
		"\"VARIANT\"",
		"\"WHILE\"",
		"\"DO\"",
		"\"VAR\"",
		"PARALLEL",
		"OP_DEF",
		"INSET",
		"FUNC_CALL_PARAM",
		"SEQUENTIAL",
		"EXP_DEF",
		"LIST_DEF",
		"SUBST_DEF",
		"SUBST_TO",
		"\"bouffon\"",
		"PO",
		"APO",
		"BTRUE"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	
	}
