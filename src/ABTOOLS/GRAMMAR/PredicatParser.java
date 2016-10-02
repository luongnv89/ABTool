// $ANTLR 2.7.6 (2005-12-22): "Predicat.g" -> "PredicatParser.java"$

    package ABTOOLS.GRAMMAR;
    
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;

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

	import java.io.*;
	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
	//import ABTOOLS.DEBUGGING.*;
	//import ABTOOLS.ANTLR_TOOLS.MyToken;

//	import MyNode;
//	import ASTterme;

/**
 * @author <a href="mailto:jl.boulanger@wanadoo.fr">Jean-Louis Boulanger</a>
 **/
public class PredicatParser extends antlr.LLkParser       implements PredicatLexerTokenTypes
 {

	String module = "predicat.g";

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


protected PredicatParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public PredicatParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected PredicatParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public PredicatParser(TokenStream lexer) {
  this(lexer,1);
}

public PredicatParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void analyse_predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode analyse_predicate_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			case B_LESS:
			case LITERAL_bool:
			case B_LPAREN:
			case B_ADD:
			case B_MINUS:
			case B_SEQEMPTY:
			case B_BRACKOPEN:
			case B_EMPTYSET:
			case B_CURLYOPEN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_NUMBER:
			case LITERAL_ran:
			case LITERAL_not:
			case LITERAL_dom:
			case LITERAL_min:
			case LITERAL_max:
			case LITERAL_card:
			case B_FORALL:
			case B_EXISTS:
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			{
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				analyse_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case EOF:
			{
				if ( inputState.guessing==0 ) {
					
						System.err.println ( "Warning : Empty predicat !" );
						errors.WSyntaxic   ( module, "The file is empty" );
					
				}
				analyse_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = analyse_predicate_AST;
	}
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		
		try {      // for error handling
			plogical_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop13:
			do {
				if ((LA(1)==B_IMPLIES)) {
					MyNode tmp1_AST = null;
					tmp1_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp1_AST);
					match(B_IMPLIES);
					plogical_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop13;
				}
				
			} while (true);
			}
			predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_AST;
	}
	
	public final void listPredicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listPredicate_AST = null;
		Token  b = null;
		MyNode b_AST = null;
		
		try {      // for error handling
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop4:
			do {
				if ((LA(1)==B_COMMA)) {
					if ( inputState.guessing==0 ) {
						
						System.out.println("ListPredicate >>");
						
					}
					b = LT(1);
					b_AST = (MyNode)astFactory.create(b);
					astFactory.makeASTRoot(currentAST, b_AST);
					match(B_COMMA);
					if ( inputState.guessing==0 ) {
						b_AST.setType(ELEM_SET);
					}
					predicate();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
						System.out.println("ListPredicate <<");
						
					}
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			listPredicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = listPredicate_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_AST = null;
		
		try {      // for error handling
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			expression_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_AST;
	}
	
	public final void nameRenamed() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		
		try {      // for error handling
			MyNode tmp2_AST = null;
			tmp2_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp2_AST);
			match(B_IDENTIFIER);
			{
			_loop8:
			do {
				if ((LA(1)==B_POINT)) {
					MyNode tmp3_AST = null;
					tmp3_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp3_AST);
					match(B_POINT);
					MyNode tmp4_AST = null;
					tmp4_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp4_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
			nameRenamed_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamed_AST;
	}
	
	public final void nameRenamedDecorated() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamedDecorated_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case B_CPRED:
			{
				MyNode tmp5_AST = null;
				tmp5_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp5_AST);
				match(B_CPRED);
				break;
			}
			case EOF:
			case B_COMMA:
			case B_IMPLIES:
			case LITERAL_or:
			case B_AND:
			case B_EQUIV:
			case B_EQUAL:
			case B_SUBSET:
			case B_NOTSUBSET:
			case B_STRICTSUBSET:
			case B_NOTSTRICTSBSET:
			case B_LESS:
			case B_GREATER:
			case B_NOTEQUAL:
			case B_LESSTHANEQUAL:
			case B_GREATERTHANEQUAL:
			case B_CONCATSEQ:
			case B_PREAPPSEQ:
			case B_APPSEQ:
			case B_PREFIXSEQ:
			case B_SUFFIXSEQ:
			case B_LPAREN:
			case B_RPAREN:
			case B_RELATION:
			case B_PARTIAL:
			case B_TOTAL:
			case B_PARTIAL_INJECT:
			case B_TOTAL_INJECT:
			case B_PARTIAL_SURJECT:
			case B_TOTAL_SURJECT:
			case B_BIJECTION:
			case B_DOMAINRESTRICT:
			case B_RANGERESTRICT:
			case B_DOMAINSUBSTRACT:
			case B_RANGESUBSTRACT:
			case B_OVERRIDEFORWARD:
			case B_OVERRIDEBACKWARD:
			case B_RELPROD:
			case B_UNION:
			case B_INTER:
			case B_MAPLET:
			case B_MULT:
			case B_POWER:
			case B_DIV:
			case LITERAL_mod:
			case B_ADD:
			case B_MINUS:
			case B_BRACKOPEN:
			case B_BRACKCLOSE:
			case B_RANGE:
			case B_CURLYCLOSE:
			case B_SUCH:
			case B_TILDE:
			case B_QUOTEIDENT:
			case B_SEMICOLON:
			case B_PARALLEL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			nameRenamedDecorated_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamedDecorated_AST;
	}
	
	protected final void plogical_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode plogical_1_AST = null;
		
		try {      // for error handling
			plogical_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop17:
			do {
				if ((LA(1)==LITERAL_or||LA(1)==B_AND)) {
					{
					switch ( LA(1)) {
					case LITERAL_or:
					{
						MyNode tmp6_AST = null;
						tmp6_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp6_AST);
						match(LITERAL_or);
						break;
					}
					case B_AND:
					{
						MyNode tmp7_AST = null;
						tmp7_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp7_AST);
						match(B_AND);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					plogical_2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop17;
				}
				
			} while (true);
			}
			plogical_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = plogical_1_AST;
	}
	
	protected final void plogical_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode plogical_2_AST = null;
		
		try {      // for error handling
			psubset_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop21:
			do {
				if ((LA(1)==B_EQUIV||LA(1)==B_EQUAL)) {
					{
					switch ( LA(1)) {
					case B_EQUIV:
					{
						MyNode tmp8_AST = null;
						tmp8_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp8_AST);
						match(B_EQUIV);
						break;
					}
					case B_EQUAL:
					{
						MyNode tmp9_AST = null;
						tmp9_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp9_AST);
						match(B_EQUAL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					psubset_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop21;
				}
				
			} while (true);
			}
			plogical_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = plogical_2_AST;
	}
	
	protected final void psubset_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode psubset_description_AST = null;
		
		try {      // for error handling
			boolean synPredMatched26 = false;
			if (((_tokenSet_8.member(LA(1))))) {
				int _m26 = mark();
				synPredMatched26 = true;
				inputState.guessing++;
				try {
					{
					pextended_pair();
					{
					{
					switch ( LA(1)) {
					case B_SUBSET:
					{
						match(B_SUBSET);
						break;
					}
					case B_NOTSUBSET:
					{
						match(B_NOTSUBSET);
						break;
					}
					case B_STRICTSUBSET:
					{
						match(B_STRICTSUBSET);
						break;
					}
					case B_NOTSTRICTSBSET:
					{
						match(B_NOTSTRICTSBSET);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					parithmetic_3();
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched26 = false;
				}
				rewind(_m26);
inputState.guessing--;
			}
			if ( synPredMatched26 ) {
				pextended_pair();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop29:
				do {
					if (((LA(1) >= B_SUBSET && LA(1) <= B_NOTSTRICTSBSET))) {
						{
						switch ( LA(1)) {
						case B_SUBSET:
						{
							MyNode tmp10_AST = null;
							tmp10_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp10_AST);
							match(B_SUBSET);
							break;
						}
						case B_NOTSUBSET:
						{
							MyNode tmp11_AST = null;
							tmp11_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp11_AST);
							match(B_NOTSUBSET);
							break;
						}
						case B_STRICTSUBSET:
						{
							MyNode tmp12_AST = null;
							tmp12_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp12_AST);
							match(B_STRICTSUBSET);
							break;
						}
						case B_NOTSTRICTSBSET:
						{
							MyNode tmp13_AST = null;
							tmp13_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp13_AST);
							match(B_NOTSTRICTSBSET);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						parithmetic_3();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop29;
					}
					
				} while (true);
				}
				psubset_description_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_8.member(LA(1)))) {
				parithmetic_3();
				astFactory.addASTChild(currentAST, returnAST);
				psubset_description_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = psubset_description_AST;
	}
	
	protected final void pextended_pair() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pextended_pair_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			parithmetic_3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop32:
			do {
				if ((LA(1)==B_COMMA)) {
					a = LT(1);
					a_AST = (MyNode)astFactory.create(a);
					astFactory.makeASTRoot(currentAST, a_AST);
					match(B_COMMA);
					if ( inputState.guessing==0 ) {
						
							a_AST.setType(LIST_VAR);
						
					}
					parithmetic_3();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop32;
				}
				
			} while (true);
			}
			pextended_pair_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = pextended_pair_AST;
	}
	
	protected final void parithmetic_3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parithmetic_3_AST = null;
		
		try {      // for error handling
			psequence_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop36:
			do {
				if (((LA(1) >= B_LESS && LA(1) <= B_GREATERTHANEQUAL))) {
					{
					switch ( LA(1)) {
					case B_LESS:
					{
						MyNode tmp14_AST = null;
						tmp14_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp14_AST);
						match(B_LESS);
						break;
					}
					case B_GREATER:
					{
						MyNode tmp15_AST = null;
						tmp15_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp15_AST);
						match(B_GREATER);
						break;
					}
					case B_NOTEQUAL:
					{
						MyNode tmp16_AST = null;
						tmp16_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp16_AST);
						match(B_NOTEQUAL);
						break;
					}
					case B_LESSTHANEQUAL:
					{
						MyNode tmp17_AST = null;
						tmp17_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp17_AST);
						match(B_LESSTHANEQUAL);
						break;
					}
					case B_GREATERTHANEQUAL:
					{
						MyNode tmp18_AST = null;
						tmp18_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp18_AST);
						match(B_GREATERTHANEQUAL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					psequence_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop36;
				}
				
			} while (true);
			}
			parithmetic_3_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = parithmetic_3_AST;
	}
	
	protected final void psequence_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode psequence_description_AST = null;
		
		try {      // for error handling
			pset_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop40:
			do {
				if (((LA(1) >= B_CONCATSEQ && LA(1) <= B_SUFFIXSEQ))) {
					{
					switch ( LA(1)) {
					case B_CONCATSEQ:
					{
						MyNode tmp19_AST = null;
						tmp19_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp19_AST);
						match(B_CONCATSEQ);
						break;
					}
					case B_PREAPPSEQ:
					{
						MyNode tmp20_AST = null;
						tmp20_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp20_AST);
						match(B_PREAPPSEQ);
						break;
					}
					case B_APPSEQ:
					{
						MyNode tmp21_AST = null;
						tmp21_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp21_AST);
						match(B_APPSEQ);
						break;
					}
					case B_PREFIXSEQ:
					{
						MyNode tmp22_AST = null;
						tmp22_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp22_AST);
						match(B_PREFIXSEQ);
						break;
					}
					case B_SUFFIXSEQ:
					{
						MyNode tmp23_AST = null;
						tmp23_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp23_AST);
						match(B_SUFFIXSEQ);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					pset_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop40;
				}
				
			} while (true);
			}
			psequence_description_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = psequence_description_AST;
	}
	
	protected final void pset_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pset_description_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_bool:
			{
				MyNode tmp24_AST = null;
				tmp24_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp24_AST);
				match(LITERAL_bool);
				match(B_LPAREN);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				pset_description_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_LESS:
			case B_LPAREN:
			case B_ADD:
			case B_MINUS:
			case B_SEQEMPTY:
			case B_BRACKOPEN:
			case B_EMPTYSET:
			case B_CURLYOPEN:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_NUMBER:
			case LITERAL_ran:
			case LITERAL_not:
			case LITERAL_dom:
			case LITERAL_min:
			case LITERAL_max:
			case LITERAL_card:
			case B_FORALL:
			case B_EXISTS:
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			{
				pfunctional_set();
				astFactory.addASTChild(currentAST, returnAST);
				pset_description_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = pset_description_AST;
	}
	
	protected final void pfunctional_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pfunctional_set_AST = null;
		
		try {      // for error handling
			pfunctional_const_set();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop45:
			do {
				if (((LA(1) >= B_RELATION && LA(1) <= B_BIJECTION))) {
					{
					switch ( LA(1)) {
					case B_RELATION:
					{
						MyNode tmp27_AST = null;
						tmp27_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp27_AST);
						match(B_RELATION);
						break;
					}
					case B_PARTIAL:
					{
						MyNode tmp28_AST = null;
						tmp28_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp28_AST);
						match(B_PARTIAL);
						break;
					}
					case B_TOTAL:
					{
						MyNode tmp29_AST = null;
						tmp29_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp29_AST);
						match(B_TOTAL);
						break;
					}
					case B_PARTIAL_INJECT:
					{
						MyNode tmp30_AST = null;
						tmp30_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp30_AST);
						match(B_PARTIAL_INJECT);
						break;
					}
					case B_TOTAL_INJECT:
					{
						MyNode tmp31_AST = null;
						tmp31_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp31_AST);
						match(B_TOTAL_INJECT);
						break;
					}
					case B_PARTIAL_SURJECT:
					{
						MyNode tmp32_AST = null;
						tmp32_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp32_AST);
						match(B_PARTIAL_SURJECT);
						break;
					}
					case B_TOTAL_SURJECT:
					{
						MyNode tmp33_AST = null;
						tmp33_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp33_AST);
						match(B_TOTAL_SURJECT);
						break;
					}
					case B_BIJECTION:
					{
						MyNode tmp34_AST = null;
						tmp34_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp34_AST);
						match(B_BIJECTION);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					pfunctional_const_set();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop45;
				}
				
			} while (true);
			}
			pfunctional_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = pfunctional_set_AST;
	}
	
	protected final void pfunctional_const_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pfunctional_const_set_AST = null;
		
		try {      // for error handling
			pset_constructors();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop49:
			do {
				if (((LA(1) >= B_DOMAINRESTRICT && LA(1) <= B_RELPROD))) {
					{
					switch ( LA(1)) {
					case B_DOMAINRESTRICT:
					{
						MyNode tmp35_AST = null;
						tmp35_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp35_AST);
						match(B_DOMAINRESTRICT);
						break;
					}
					case B_RANGERESTRICT:
					{
						MyNode tmp36_AST = null;
						tmp36_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp36_AST);
						match(B_RANGERESTRICT);
						break;
					}
					case B_DOMAINSUBSTRACT:
					{
						MyNode tmp37_AST = null;
						tmp37_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp37_AST);
						match(B_DOMAINSUBSTRACT);
						break;
					}
					case B_RANGESUBSTRACT:
					{
						MyNode tmp38_AST = null;
						tmp38_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp38_AST);
						match(B_RANGESUBSTRACT);
						break;
					}
					case B_OVERRIDEFORWARD:
					{
						MyNode tmp39_AST = null;
						tmp39_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp39_AST);
						match(B_OVERRIDEFORWARD);
						break;
					}
					case B_OVERRIDEBACKWARD:
					{
						MyNode tmp40_AST = null;
						tmp40_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp40_AST);
						match(B_OVERRIDEBACKWARD);
						break;
					}
					case B_RELPROD:
					{
						MyNode tmp41_AST = null;
						tmp41_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp41_AST);
						match(B_RELPROD);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					pset_constructors();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop49;
				}
				
			} while (true);
			}
			pfunctional_const_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = pfunctional_const_set_AST;
	}
	
	protected final void pset_constructors() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pset_constructors_AST = null;
		
		try {      // for error handling
			ppaire();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop53:
			do {
				if ((LA(1)==B_UNION||LA(1)==B_INTER)) {
					{
					switch ( LA(1)) {
					case B_UNION:
					{
						MyNode tmp42_AST = null;
						tmp42_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp42_AST);
						match(B_UNION);
						break;
					}
					case B_INTER:
					{
						MyNode tmp43_AST = null;
						tmp43_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp43_AST);
						match(B_INTER);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					ppaire();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop53;
				}
				
			} while (true);
			}
			pset_constructors_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = pset_constructors_AST;
	}
	
	protected final void ppaire() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode ppaire_AST = null;
		
		try {      // for error handling
			parithmetic_0();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop56:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp44_AST = null;
					tmp44_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp44_AST);
					match(B_MAPLET);
					parithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop56;
				}
				
			} while (true);
			}
			ppaire_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = ppaire_AST;
	}
	
	protected final void parithmetic_0() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parithmetic_0_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			boolean synPredMatched59 = false;
			if ((((LA(1) >= LITERAL_INT && LA(1) <= 94)))) {
				int _m59 = mark();
				synPredMatched59 = true;
				inputState.guessing++;
				try {
					{
					basic_sets();
					match(B_MULT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched59 = false;
				}
				rewind(_m59);
inputState.guessing--;
			}
			if ( synPredMatched59 ) {
				{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop62:
				do {
					if ((LA(1)==B_MULT)) {
						a = LT(1);
						a_AST = (MyNode)astFactory.create(a);
						astFactory.makeASTRoot(currentAST, a_AST);
						match(B_MULT);
						if ( inputState.guessing==0 ) {
							a_AST.setType(PRODSET);
						}
						basic_sets();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop62;
					}
					
				} while (true);
				}
				}
				parithmetic_0_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_16.member(LA(1)))) {
				parithmetic_1();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop65:
				do {
					if ((LA(1)==B_MULT||LA(1)==B_POWER)) {
						{
						switch ( LA(1)) {
						case B_POWER:
						{
							MyNode tmp45_AST = null;
							tmp45_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp45_AST);
							match(B_POWER);
							break;
						}
						case B_MULT:
						{
							MyNode tmp46_AST = null;
							tmp46_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp46_AST);
							match(B_MULT);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						parithmetic_1();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop65;
					}
					
				} while (true);
				}
				parithmetic_0_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = parithmetic_0_AST;
	}
	
	public final void basic_sets() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode basic_sets_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_INT:
			{
				MyNode tmp47_AST = null;
				tmp47_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp47_AST);
				match(LITERAL_INT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp48_AST = null;
				tmp48_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp48_AST);
				match(87);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp49_AST = null;
				tmp49_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp49_AST);
				match(LITERAL_INTEGER);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp50_AST = null;
				tmp50_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp50_AST);
				match(89);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp51_AST = null;
				tmp51_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp51_AST);
				match(LITERAL_BOOL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp52_AST = null;
				tmp52_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp52_AST);
				match(LITERAL_NAT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp53_AST = null;
				tmp53_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp53_AST);
				match(92);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp54_AST = null;
				tmp54_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp54_AST);
				match(LITERAL_NATURAL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp55_AST = null;
				tmp55_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp55_AST);
				match(94);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_sets_AST;
	}
	
	protected final void parithmetic_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parithmetic_1_AST = null;
		
		try {      // for error handling
			parithmetic_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop69:
			do {
				if ((LA(1)==B_DIV||LA(1)==LITERAL_mod)) {
					{
					switch ( LA(1)) {
					case B_DIV:
					{
						MyNode tmp56_AST = null;
						tmp56_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp56_AST);
						match(B_DIV);
						break;
					}
					case LITERAL_mod:
					{
						MyNode tmp57_AST = null;
						tmp57_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp57_AST);
						match(LITERAL_mod);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					parithmetic_2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop69;
				}
				
			} while (true);
			}
			parithmetic_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = parithmetic_1_AST;
	}
	
	protected final void parithmetic_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parithmetic_2_AST = null;
		
		try {      // for error handling
			pbases();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop73:
			do {
				if ((LA(1)==B_ADD||LA(1)==B_MINUS)) {
					{
					switch ( LA(1)) {
					case B_ADD:
					{
						MyNode tmp58_AST = null;
						tmp58_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp58_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp59_AST = null;
						tmp59_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp59_AST);
						match(B_MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					pbases();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop73;
				}
				
			} while (true);
			}
			parithmetic_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = parithmetic_2_AST;
	}
	
	protected final void pbases() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pbases_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_SEQEMPTY:
			{
				MyNode tmp60_AST = null;
				tmp60_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp60_AST);
				match(B_SEQEMPTY);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				MyNode tmp61_AST = null;
				tmp61_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp61_AST);
				match(B_BRACKOPEN);
				listPredicate();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_BRACKCLOSE);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LESS:
			{
				c = LT(1);
				c_AST = (MyNode)astFactory.create(c);
				astFactory.makeASTRoot(currentAST, c_AST);
				match(B_LESS);
				if ( inputState.guessing==0 ) {
					c_AST.setType(B_BRACKOPEN);
				}
				listPredicate();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_GREATER);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_LPAREN:
			case B_ADD:
			case B_MINUS:
			case B_ASTRING:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case LITERAL_rec:
			case B_NUMBER:
			case LITERAL_ran:
			case LITERAL_not:
			case LITERAL_dom:
			case LITERAL_min:
			case LITERAL_max:
			case LITERAL_card:
			{
				pbasic_value();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case B_RANGE:
				{
					MyNode tmp64_AST = null;
					tmp64_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp64_AST);
					match(B_RANGE);
					parithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case EOF:
				case B_COMMA:
				case B_IMPLIES:
				case LITERAL_or:
				case B_AND:
				case B_EQUIV:
				case B_EQUAL:
				case B_SUBSET:
				case B_NOTSUBSET:
				case B_STRICTSUBSET:
				case B_NOTSTRICTSBSET:
				case B_LESS:
				case B_GREATER:
				case B_NOTEQUAL:
				case B_LESSTHANEQUAL:
				case B_GREATERTHANEQUAL:
				case B_CONCATSEQ:
				case B_PREAPPSEQ:
				case B_APPSEQ:
				case B_PREFIXSEQ:
				case B_SUFFIXSEQ:
				case B_RPAREN:
				case B_RELATION:
				case B_PARTIAL:
				case B_TOTAL:
				case B_PARTIAL_INJECT:
				case B_TOTAL_INJECT:
				case B_PARTIAL_SURJECT:
				case B_TOTAL_SURJECT:
				case B_BIJECTION:
				case B_DOMAINRESTRICT:
				case B_RANGERESTRICT:
				case B_DOMAINSUBSTRACT:
				case B_RANGESUBSTRACT:
				case B_OVERRIDEFORWARD:
				case B_OVERRIDEBACKWARD:
				case B_RELPROD:
				case B_UNION:
				case B_INTER:
				case B_MAPLET:
				case B_MULT:
				case B_POWER:
				case B_DIV:
				case LITERAL_mod:
				case B_ADD:
				case B_MINUS:
				case B_BRACKCLOSE:
				case B_CURLYCLOSE:
				case B_SUCH:
				case B_SEMICOLON:
				case B_PARALLEL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp65_AST = null;
				tmp65_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp65_AST);
				match(B_EMPTYSET);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				MyNode tmp66_AST = null;
				tmp66_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp66_AST);
				match(B_CURLYOPEN);
				pvalue_set();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_CURLYCLOSE);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				quantification();
				astFactory.addASTChild(currentAST, returnAST);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_LAMBDA:
			case LITERAL_PI:
			case LITERAL_SIGMA:
			case LITERAL_UNION:
			case LITERAL_INTER:
			{
				q_lambda();
				astFactory.addASTChild(currentAST, returnAST);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = pbases_AST;
	}
	
	protected final void pbasic_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pbasic_value_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		Token  b = null;
		MyNode b_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_ADD:
			{
				a = LT(1);
				a_AST = (MyNode)astFactory.create(a);
				astFactory.makeASTRoot(currentAST, a_AST);
				match(B_ADD);
				if ( inputState.guessing==0 ) {
					a_AST.setType(UNARY_ADD);
				}
				punary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MINUS:
			{
				b = LT(1);
				b_AST = (MyNode)astFactory.create(b);
				astFactory.makeASTRoot(currentAST, b_AST);
				match(B_MINUS);
				if ( inputState.guessing==0 ) {
					b_AST.setType(UNARY_MINUS);
				}
				punary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_LPAREN:
			case B_NUMBER:
			case LITERAL_ran:
			case LITERAL_not:
			case LITERAL_dom:
			case LITERAL_min:
			case LITERAL_max:
			case LITERAL_card:
			{
				punary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ASTRING:
			{
				MyNode tmp68_AST = null;
				tmp68_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp68_AST);
				match(B_ASTRING);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_rec:
			{
				is_rec();
				astFactory.addASTChild(currentAST, returnAST);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp69_AST = null;
				tmp69_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp69_AST);
				match(LITERAL_TRUE);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp70_AST = null;
				tmp70_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp70_AST);
				match(LITERAL_FALSE);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
		returnAST = pbasic_value_AST;
	}
	
	protected final void pvalue_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pvalue_set_AST = null;
		
		try {      // for error handling
			boolean synPredMatched78 = false;
			if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
				int _m78 = mark();
				synPredMatched78 = true;
				inputState.guessing++;
				try {
					{
					alist_var();
					match(B_SUCH);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched78 = false;
				}
				rewind(_m78);
inputState.guessing--;
			}
			if ( synPredMatched78 ) {
				alist_var();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp71_AST = null;
				tmp71_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp71_AST);
				match(B_SUCH);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				pvalue_set_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_8.member(LA(1)))) {
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop80:
				do {
					if ((LA(1)==B_COMMA)) {
						MyNode tmp72_AST = null;
						tmp72_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp72_AST);
						match(B_COMMA);
						predicate();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop80;
					}
					
				} while (true);
				}
				pvalue_set_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
		returnAST = pvalue_set_AST;
	}
	
	public final void quantification() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode quantification_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case B_FORALL:
			{
				MyNode tmp73_AST = null;
				tmp73_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp73_AST);
				match(B_FORALL);
				break;
			}
			case B_EXISTS:
			{
				MyNode tmp74_AST = null;
				tmp74_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp74_AST);
				match(B_EXISTS);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			boolean synPredMatched107 = false;
			if (((LA(1)==B_LPAREN))) {
				int _m107 = mark();
				synPredMatched107 = true;
				inputState.guessing++;
				try {
					{
					match(B_LPAREN);
					match(B_LPAREN);
					match(B_IDENTIFIER);
					match(B_COMMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched107 = false;
				}
				rewind(_m107);
inputState.guessing--;
			}
			if ( synPredMatched107 ) {
				{
				match(B_LPAREN);
				q_quantification();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched110 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
					int _m110 = mark();
					synPredMatched110 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched110 = false;
					}
					rewind(_m110);
inputState.guessing--;
				}
				if ( synPredMatched110 ) {
					{
					q_quantification();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched113 = false;
					if (((LA(1)==B_LPAREN))) {
						int _m113 = mark();
						synPredMatched113 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched113 = false;
						}
						rewind(_m113);
inputState.guessing--;
					}
					if ( synPredMatched113 ) {
						{
						match(B_LPAREN);
						q_quantification();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched116 = false;
						if (((LA(1)==B_LPAREN))) {
							int _m116 = mark();
							synPredMatched116 = true;
							inputState.guessing++;
							try {
								{
								match(B_LPAREN);
								match(B_LPAREN);
								match(B_IDENTIFIER);
								match(B_RPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched116 = false;
							}
							rewind(_m116);
inputState.guessing--;
						}
						if ( synPredMatched116 ) {
							{
							match(B_LPAREN);
							q_quantification();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched119 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
								int _m119 = mark();
								synPredMatched119 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched119 = false;
								}
								rewind(_m119);
inputState.guessing--;
							}
							if ( synPredMatched119 ) {
								{
								q_quantification();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched122 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
									int _m122 = mark();
									synPredMatched122 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched122 = false;
									}
									rewind(_m122);
inputState.guessing--;
								}
								if ( synPredMatched122 ) {
									{
									q_quantification();
									astFactory.addASTChild(currentAST, returnAST);
									}
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}
								}
								quantification_AST = (MyNode)currentAST.root;
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_17);
								} else {
								  throw ex;
								}
							}
							returnAST = quantification_AST;
						}
						
	public final void q_lambda() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_lambda_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case B_LAMBDA:
			{
				MyNode tmp81_AST = null;
				tmp81_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp81_AST);
				match(B_LAMBDA);
				break;
			}
			case LITERAL_PI:
			{
				MyNode tmp82_AST = null;
				tmp82_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp82_AST);
				match(LITERAL_PI);
				break;
			}
			case LITERAL_SIGMA:
			{
				MyNode tmp83_AST = null;
				tmp83_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp83_AST);
				match(LITERAL_SIGMA);
				break;
			}
			case LITERAL_UNION:
			{
				MyNode tmp84_AST = null;
				tmp84_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp84_AST);
				match(LITERAL_UNION);
				break;
			}
			case LITERAL_INTER:
			{
				MyNode tmp85_AST = null;
				tmp85_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp85_AST);
				match(LITERAL_INTER);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			boolean synPredMatched129 = false;
			if (((LA(1)==B_LPAREN))) {
				int _m129 = mark();
				synPredMatched129 = true;
				inputState.guessing++;
				try {
					{
					match(B_LPAREN);
					match(B_LPAREN);
					match(B_IDENTIFIER);
					match(B_COMMA);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched129 = false;
				}
				rewind(_m129);
inputState.guessing--;
			}
			if ( synPredMatched129 ) {
				{
				match(B_LPAREN);
				q_operande();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched132 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
					int _m132 = mark();
					synPredMatched132 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched132 = false;
					}
					rewind(_m132);
inputState.guessing--;
				}
				if ( synPredMatched132 ) {
					{
					q_operande();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched135 = false;
					if (((LA(1)==B_LPAREN))) {
						int _m135 = mark();
						synPredMatched135 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched135 = false;
						}
						rewind(_m135);
inputState.guessing--;
					}
					if ( synPredMatched135 ) {
						{
						match(B_LPAREN);
						q_operande();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched138 = false;
						if (((LA(1)==B_LPAREN))) {
							int _m138 = mark();
							synPredMatched138 = true;
							inputState.guessing++;
							try {
								{
								match(B_LPAREN);
								match(B_LPAREN);
								match(B_IDENTIFIER);
								match(B_RPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched138 = false;
							}
							rewind(_m138);
inputState.guessing--;
						}
						if ( synPredMatched138 ) {
							{
							match(B_LPAREN);
							q_operande();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched141 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
								int _m141 = mark();
								synPredMatched141 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched141 = false;
								}
								rewind(_m141);
inputState.guessing--;
							}
							if ( synPredMatched141 ) {
								{
								q_operande();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched144 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN))) {
									int _m144 = mark();
									synPredMatched144 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched144 = false;
									}
									rewind(_m144);
inputState.guessing--;
								}
								if ( synPredMatched144 ) {
									{
									q_operande();
									astFactory.addASTChild(currentAST, returnAST);
									}
								}
								else {
									throw new NoViableAltException(LT(1), getFilename());
								}
								}}}}}
								}
								q_lambda_AST = (MyNode)currentAST.root;
							}
							catch (RecognitionException ex) {
								if (inputState.guessing==0) {
									reportError(ex);
									recover(ex,_tokenSet_17);
								} else {
								  throw ex;
								}
							}
							returnAST = q_lambda_AST;
						}
						
	public final void alist_var() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode alist_var_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_LPAREN:
			{
				MyNode tmp92_AST = null;
				tmp92_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp92_AST);
				match(B_LPAREN);
				list_identifier();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				alist_var_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				list_identifier();
				astFactory.addASTChild(currentAST, returnAST);
				alist_var_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_20);
			} else {
			  throw ex;
			}
		}
		returnAST = alist_var_AST;
	}
	
	protected final void punary_basic_value_inverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode punary_basic_value_inverted_AST = null;
		
		try {      // for error handling
			punary_basic_value();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case B_TILDE:
			{
				MyNode tmp94_AST = null;
				tmp94_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp94_AST);
				match(B_TILDE);
				break;
			}
			case EOF:
			case B_COMMA:
			case B_IMPLIES:
			case LITERAL_or:
			case B_AND:
			case B_EQUIV:
			case B_EQUAL:
			case B_SUBSET:
			case B_NOTSUBSET:
			case B_STRICTSUBSET:
			case B_NOTSTRICTSBSET:
			case B_LESS:
			case B_GREATER:
			case B_NOTEQUAL:
			case B_LESSTHANEQUAL:
			case B_GREATERTHANEQUAL:
			case B_CONCATSEQ:
			case B_PREAPPSEQ:
			case B_APPSEQ:
			case B_PREFIXSEQ:
			case B_SUFFIXSEQ:
			case B_RPAREN:
			case B_RELATION:
			case B_PARTIAL:
			case B_TOTAL:
			case B_PARTIAL_INJECT:
			case B_TOTAL_INJECT:
			case B_PARTIAL_SURJECT:
			case B_TOTAL_SURJECT:
			case B_BIJECTION:
			case B_DOMAINRESTRICT:
			case B_RANGERESTRICT:
			case B_DOMAINSUBSTRACT:
			case B_RANGESUBSTRACT:
			case B_OVERRIDEFORWARD:
			case B_OVERRIDEBACKWARD:
			case B_RELPROD:
			case B_UNION:
			case B_INTER:
			case B_MAPLET:
			case B_MULT:
			case B_POWER:
			case B_DIV:
			case LITERAL_mod:
			case B_ADD:
			case B_MINUS:
			case B_BRACKCLOSE:
			case B_RANGE:
			case B_CURLYCLOSE:
			case B_SUCH:
			case B_SEMICOLON:
			case B_PARALLEL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			punary_basic_value_inverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
		returnAST = punary_basic_value_inverted_AST;
	}
	
	public final void is_rec() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode is_rec_AST = null;
		
		try {      // for error handling
			MyNode tmp95_AST = null;
			tmp95_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp95_AST);
			match(LITERAL_rec);
			match(B_LPAREN);
			listRecord();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			is_rec_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
		returnAST = is_rec_AST;
	}
	
	public final void listRecord() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listRecord_AST = null;
		
		try {      // for error handling
			a_record();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop151:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp98_AST = null;
					tmp98_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp98_AST);
					match(B_COMMA);
					a_record();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop151;
				}
				
			} while (true);
			}
			listRecord_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = listRecord_AST;
	}
	
	protected final void punary_basic_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode punary_basic_value_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			case B_LPAREN:
			case LITERAL_ran:
			case LITERAL_not:
			case LITERAL_dom:
			case LITERAL_min:
			case LITERAL_max:
			case LITERAL_card:
			{
				predInvertedParamInvertedQuoted();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case B_BRACKOPEN:
				{
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_BRACKOPEN);
					if ( inputState.guessing==0 ) {
						
							c_AST.setType(APPLY_TO);
						
					}
					predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_BRACKCLOSE);
					break;
				}
				case EOF:
				case B_COMMA:
				case B_IMPLIES:
				case LITERAL_or:
				case B_AND:
				case B_EQUIV:
				case B_EQUAL:
				case B_SUBSET:
				case B_NOTSUBSET:
				case B_STRICTSUBSET:
				case B_NOTSTRICTSBSET:
				case B_LESS:
				case B_GREATER:
				case B_NOTEQUAL:
				case B_LESSTHANEQUAL:
				case B_GREATERTHANEQUAL:
				case B_CONCATSEQ:
				case B_PREAPPSEQ:
				case B_APPSEQ:
				case B_PREFIXSEQ:
				case B_SUFFIXSEQ:
				case B_RPAREN:
				case B_RELATION:
				case B_PARTIAL:
				case B_TOTAL:
				case B_PARTIAL_INJECT:
				case B_TOTAL_INJECT:
				case B_PARTIAL_SURJECT:
				case B_TOTAL_SURJECT:
				case B_BIJECTION:
				case B_DOMAINRESTRICT:
				case B_RANGERESTRICT:
				case B_DOMAINSUBSTRACT:
				case B_RANGESUBSTRACT:
				case B_OVERRIDEFORWARD:
				case B_OVERRIDEBACKWARD:
				case B_RELPROD:
				case B_UNION:
				case B_INTER:
				case B_MAPLET:
				case B_MULT:
				case B_POWER:
				case B_DIV:
				case LITERAL_mod:
				case B_ADD:
				case B_MINUS:
				case B_BRACKCLOSE:
				case B_RANGE:
				case B_CURLYCLOSE:
				case B_SUCH:
				case B_TILDE:
				case B_SEMICOLON:
				case B_PARALLEL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				punary_basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp100_AST = null;
				tmp100_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp100_AST);
				match(B_NUMBER);
				punary_basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = punary_basic_value_AST;
	}
	
	protected final void predInvertedParamInvertedQuoted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParamInvertedQuoted_AST = null;
		
		try {      // for error handling
			predInvertedParamInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop89:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp101_AST = null;
					tmp101_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp101_AST);
					match(B_QUOTEIDENT);
					predInvertedParamInverted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop89;
				}
				
			} while (true);
			}
			predInvertedParamInvertedQuoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = predInvertedParamInvertedQuoted_AST;
	}
	
	protected final void predInvertedParamInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParamInverted_AST = null;
		
		try {      // for error handling
			predInvertedParam();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==B_TILDE)) {
				MyNode tmp102_AST = null;
				tmp102_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp102_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_24.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			predInvertedParamInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_24);
			} else {
			  throw ex;
			}
		}
		returnAST = predInvertedParamInverted_AST;
	}
	
	protected final void predInvertedParam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParam_AST = null;
		
		try {      // for error handling
			predParentInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop94:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp103_AST = null;
					tmp103_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp103_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop94;
				}
				
			} while (true);
			}
			predInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_24);
			} else {
			  throw ex;
			}
		}
		returnAST = predInvertedParam_AST;
	}
	
	protected final void predParentInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predParentInverted_AST = null;
		Token  r = null;
		MyNode r_AST = null;
		Token  n = null;
		MyNode n_AST = null;
		Token  d = null;
		MyNode d_AST = null;
		Token  e = null;
		MyNode e_AST = null;
		Token  f = null;
		MyNode f_AST = null;
		Token  g = null;
		MyNode g_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_ran:
			{
				r = LT(1);
				r_AST = (MyNode)astFactory.create(r);
				astFactory.makeASTRoot(currentAST, r_AST);
				match(LITERAL_ran);
				if ( inputState.guessing==0 ) {
					r_AST.setType(B_RAN);
				}
				break;
			}
			case LITERAL_not:
			{
				n = LT(1);
				n_AST = (MyNode)astFactory.create(n);
				astFactory.makeASTRoot(currentAST, n_AST);
				match(LITERAL_not);
				if ( inputState.guessing==0 ) {
					n_AST.setType(B_NOT);
				}
				break;
			}
			case LITERAL_dom:
			{
				d = LT(1);
				d_AST = (MyNode)astFactory.create(d);
				astFactory.makeASTRoot(currentAST, d_AST);
				match(LITERAL_dom);
				if ( inputState.guessing==0 ) {
					d_AST.setType(B_DOM);
				}
				break;
			}
			case LITERAL_min:
			{
				e = LT(1);
				e_AST = (MyNode)astFactory.create(e);
				astFactory.makeASTRoot(currentAST, e_AST);
				match(LITERAL_min);
				if ( inputState.guessing==0 ) {
					e_AST.setType(B_MIN);
				}
				break;
			}
			case LITERAL_max:
			{
				f = LT(1);
				f_AST = (MyNode)astFactory.create(f);
				astFactory.makeASTRoot(currentAST, f_AST);
				match(LITERAL_max);
				if ( inputState.guessing==0 ) {
					f_AST.setType(B_MAX);
				}
				break;
			}
			case LITERAL_card:
			{
				g = LT(1);
				g_AST = (MyNode)astFactory.create(g);
				astFactory.makeASTRoot(currentAST, g_AST);
				match(LITERAL_card);
				if ( inputState.guessing==0 ) {
					g_AST.setType(B_CARD);
				}
				break;
			}
			case B_IDENTIFIER:
			case B_LPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			pred_parent();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==B_TILDE)) {
				MyNode tmp105_AST = null;
				tmp105_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp105_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			predParentInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = predParentInverted_AST;
	}
	
	public final void list_New_Predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_New_Predicate_AST = null;
		
		try {      // for error handling
			new_predicate();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop160:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp106_AST = null;
					tmp106_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp106_AST);
					match(B_COMMA);
					new_predicate();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop160;
				}
				
			} while (true);
			}
			list_New_Predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = list_New_Predicate_AST;
	}
	
	public final void pred_parent() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pred_parent_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_LPAREN:
			{
				c = LT(1);
				c_AST = (MyNode)astFactory.create(c);
				astFactory.makeASTRoot(currentAST, c_AST);
				match(B_LPAREN);
				if ( inputState.guessing==0 ) {
					c_AST.setType(PARENT);
				}
				pred_func_composition();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				pred_parent_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				nameRenamedDecorated();
				astFactory.addASTChild(currentAST, returnAST);
				pred_parent_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = pred_parent_AST;
	}
	
	public final void pred_func_composition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode pred_func_composition_AST = null;
		
		try {      // for error handling
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop102:
			do {
				if ((_tokenSet_25.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp108_AST = null;
						tmp108_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp108_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp109_AST = null;
						tmp109_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp109_AST);
						match(B_PARALLEL);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp110_AST = null;
						tmp110_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp110_AST);
						match(B_COMMA);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					predicate();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop102;
				}
				
			} while (true);
			}
			pred_func_composition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = pred_func_composition_AST;
	}
	
	public final void q_quantification() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_quantification_AST = null;
		
		try {      // for error handling
			alist_var();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_POINT);
			match(B_LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			q_quantification_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = q_quantification_AST;
	}
	
	public final void q_operande() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode q_operande_AST = null;
		
		try {      // for error handling
			alist_var();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_POINT);
			match(B_LPAREN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			MyNode tmp116_AST = null;
			tmp116_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp116_AST);
			match(B_SUCH);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			q_operande_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = q_operande_AST;
	}
	
/**
 * Is_rec 	is applicable for no typing predicate
 * Is_record    is applicable for    typing predicate (expression)
 **/
	public final void is_struct() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode is_struct_AST = null;
		
		try {      // for error handling
			MyNode tmp118_AST = null;
			tmp118_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp118_AST);
			match(LITERAL_struct);
			match(B_LPAREN);
			listRecord();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			is_struct_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_26);
			} else {
			  throw ex;
			}
		}
		returnAST = is_struct_AST;
	}
	
	public final void is_record() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode is_record_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_rec:
			{
				is_rec();
				astFactory.addASTChild(currentAST, returnAST);
				is_record_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_struct:
			{
				is_struct();
				astFactory.addASTChild(currentAST, returnAST);
				is_record_AST = (MyNode)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = is_record_AST;
	}
	
/**
 *
 * Attention a ne pas mettre n'importe quoi ....
 *	- ne pas oublie que ca peut etre un autre record (01/2001)
 *
 * On change le : en SELECTOR pour retirer les conflits dans les treewalker
 **/
	public final void a_record() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_record_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			boolean synPredMatched167 = false;
			if (((LA(1)==B_IDENTIFIER))) {
				int _m167 = mark();
				synPredMatched167 = true;
				inputState.guessing++;
				try {
					{
					match(B_IDENTIFIER);
					match(B_INSET);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched167 = false;
				}
				rewind(_m167);
inputState.guessing--;
			}
			if ( synPredMatched167 ) {
				MyNode tmp121_AST = null;
				tmp121_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp121_AST);
				match(B_IDENTIFIER);
				a = LT(1);
				a_AST = (MyNode)astFactory.create(a);
				astFactory.makeASTRoot(currentAST, a_AST);
				match(B_INSET);
				if ( inputState.guessing==0 ) {
					a_AST.setType(B_SELECTOR);
				}
				pfunctional_set();
				astFactory.addASTChild(currentAST, returnAST);
				a_record_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_16.member(LA(1)))) {
				pfunctional_set();
				astFactory.addASTChild(currentAST, returnAST);
				a_record_AST = (MyNode)currentAST.root;
			}
			else if ((LA(1)==LITERAL_struct)) {
				is_struct();
				astFactory.addASTChild(currentAST, returnAST);
				a_record_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			} else {
			  throw ex;
			}
		}
		returnAST = a_record_AST;
	}
	
	public final void list_identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_identifier_AST = null;
		
		try {      // for error handling
			MyNode tmp122_AST = null;
			tmp122_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp122_AST);
			match(B_IDENTIFIER);
			{
			_loop154:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp123_AST = null;
					tmp123_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp123_AST);
					match(B_COMMA);
					MyNode tmp124_AST = null;
					tmp124_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp124_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop154;
				}
				
			} while (true);
			}
			list_identifier_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		returnAST = list_identifier_AST;
	}
	
	public final void new_predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_predicate_AST = null;
		
		try {      // for error handling
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop164:
			do {
				if ((LA(1)==B_SEMICOLON||LA(1)==B_PARALLEL)) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp125_AST = null;
						tmp125_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp125_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp126_AST = null;
						tmp126_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp126_AST);
						match(B_PARALLEL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					predicate();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop164;
				}
				
			} while (true);
			}
			new_predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			} else {
			  throw ex;
			}
		}
		returnAST = new_predicate_AST;
	}
	
	public final void dummy() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode dummy_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case UNARY_ADD:
			{
				MyNode tmp127_AST = null;
				tmp127_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp127_AST);
				match(UNARY_ADD);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case UNARY_MINUS:
			{
				MyNode tmp128_AST = null;
				tmp128_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp128_AST);
				match(UNARY_MINUS);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case ELEM_SET:
			{
				MyNode tmp129_AST = null;
				tmp129_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp129_AST);
				match(ELEM_SET);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case LIST_VAR:
			{
				MyNode tmp130_AST = null;
				tmp130_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp130_AST);
				match(LIST_VAR);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case APPLY_TO:
			{
				MyNode tmp131_AST = null;
				tmp131_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp131_AST);
				match(APPLY_TO);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case PARENT:
			{
				MyNode tmp132_AST = null;
				tmp132_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp132_AST);
				match(PARENT);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SELECTOR:
			{
				MyNode tmp133_AST = null;
				tmp133_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp133_AST);
				match(B_SELECTOR);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case PRODSET:
			{
				MyNode tmp134_AST = null;
				tmp134_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp134_AST);
				match(PRODSET);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NOT:
			{
				MyNode tmp135_AST = null;
				tmp135_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp135_AST);
				match(B_NOT);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_DOM:
			{
				MyNode tmp136_AST = null;
				tmp136_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp136_AST);
				match(B_DOM);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_RAN:
			{
				MyNode tmp137_AST = null;
				tmp137_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp137_AST);
				match(B_RAN);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MIN:
			{
				MyNode tmp138_AST = null;
				tmp138_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp138_AST);
				match(B_MIN);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MAX:
			{
				MyNode tmp139_AST = null;
				tmp139_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp139_AST);
				match(B_MAX);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CARD:
			{
				MyNode tmp140_AST = null;
				tmp140_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp140_AST);
				match(B_CARD);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_MOD:
			{
				MyNode tmp141_AST = null;
				tmp141_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp141_AST);
				match(B_MOD);
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
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = dummy_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"B_COMMA",
		"B_IDENTIFIER",
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
		"B_ASTRING",
		"\"TRUE\"",
		"\"FALSE\"",
		"\"rec\"",
		"B_TILDE",
		"B_NUMBER",
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
		"B_MOD"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 3530822108395601938L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 72057594038190080L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 3458764514357411856L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 3728980491328552850L, 6164L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 3728980491328552722L, 6164L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 3530822108395602194L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 3530822108395603730L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { -3679440895158910944L, 2144331755L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 3530822108395609874L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 3530822108395732754L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 3530822108399533842L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 3530822108529557266L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 3530822382333722386L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 3530857291827904274L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 3530962844944170770L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { -3679440895293128672L, 2144331755L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 3548836505965297426L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 3692951694041153298L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 1152921504606846976L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { 2305843009213694016L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { 536870912L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { 3692951694041153298L, 6148L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { 3728980491060117266L, 6148L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { 3728980491060117266L, 6164L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { 16L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = { 536870930L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = { 536870928L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = { 2305843009750564928L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	
	}
