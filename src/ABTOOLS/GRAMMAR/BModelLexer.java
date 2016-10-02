// $ANTLR 2.7.6 (2005-12-22): "expandedBModel.g" -> "BModelLexer.java"$

    package ABTOOLS.GRAMMAR;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;

public class BModelLexer extends antlr.CharScanner implements BModelTokenTypes, TokenStream
 {
String module = "BModel.g";

    ErrorMessage errors = new ErrorMessage();
    
	public void setErrors (ErrorMessage err)
	{
		errors = err;
	}

	public String getErrors ()
	{
		return errors.toString();
	}


	protected int 	  tokcolumn   		= 1 ;
	protected int 	  column 	    	= 1 ;
  	protected int 	  tokenNumber  		= 0;
  	protected boolean countingTokens 	= true;


	public void consume() 
	throws CharStreamException { 
	  try {      // for error handling

		if (inputState.guessing > 0)
		{
		 if (text.length() == 0)
		 {	// remenber token start column
			tokcolumn = column;
			System.err.println("Column "+tokcolumn); 
		 }

		 if (LA(1)=='\n') 
			{column = 1;}
		 else
			{column++ ;}
		}
		super.consume();
	   }
	   catch (CharStreamException ignore) 
	   {
	   }
	}

	protected Token makeToken(int t)
	{   
		if ( t != Token.SKIP && countingTokens) 
		{
        		tokenNumber++;
    		}
		MyToken tok = (MyToken) super.makeToken(t);
		tok.setColumn(tokcolumn);
    		tok.setTokenNumber(tokenNumber);
		return tok;
	}

	public Metrics metrics = null ;
	
	public Metrics getMetrics()
	{
		if (metrics != null)
		{
			return metrics;
		}
		else
		{
		// error .....
			return null;
		}
	}

		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
	
public BModelLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public BModelLexer(Reader in) {
	this(new CharBuffer(in));
}
public BModelLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public BModelLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("CHOICE", this), new Integer(161));
	literals.put(new ANTLRHashString("CASE", this), new Integer(163));
	literals.put(new ANTLRHashString("IMPLEMENTATION", this), new Integer(127));
	literals.put(new ANTLRHashString("NAT1", this), new Integer(92));
	literals.put(new ANTLRHashString("VARIABLES", this), new Integer(144));
	literals.put(new ANTLRHashString("END", this), new Integer(125));
	literals.put(new ANTLRHashString("VALUES", this), new Integer(142));
	literals.put(new ANTLRHashString("ASSERT", this), new Integer(158));
	literals.put(new ANTLRHashString("BE", this), new Integer(172));
	literals.put(new ANTLRHashString("STRING", this), new Integer(111));
	literals.put(new ANTLRHashString("ABSTRACT_VARIABLES", this), new Integer(145));
	literals.put(new ANTLRHashString("REFINEMENT", this), new Integer(126));
	literals.put(new ANTLRHashString("MODEL", this), new Integer(187));
	literals.put(new ANTLRHashString("THEN", this), new Integer(157));
	literals.put(new ANTLRHashString("PROMOTES", this), new Integer(134));
	literals.put(new ANTLRHashString("MACHINE", this), new Integer(124));
	literals.put(new ANTLRHashString("POST", this), new Integer(196));
	literals.put(new ANTLRHashString("NATURAL", this), new Integer(93));
	literals.put(new ANTLRHashString("VAR", this), new Integer(177));
	literals.put(new ANTLRHashString("not", this), new Integer(70));
	literals.put(new ANTLRHashString("DO", this), new Integer(176));
	literals.put(new ANTLRHashString("MAINTAIN", this), new Integer(192));
	literals.put(new ANTLRHashString("CONSTANTS", this), new Integer(136));
	literals.put(new ANTLRHashString("INT1", this), new Integer(87));
	literals.put(new ANTLRHashString("MODALITIES", this), new Integer(191));
	literals.put(new ANTLRHashString("UNION", this), new Integer(82));
	literals.put(new ANTLRHashString("WHEN", this), new Integer(170));
	literals.put(new ANTLRHashString("PRE", this), new Integer(156));
	literals.put(new ANTLRHashString("NAT", this), new Integer(91));
	literals.put(new ANTLRHashString("mod", this), new Integer(51));
	literals.put(new ANTLRHashString("ABSTRACT_CONSTANTS", this), new Integer(139));
	literals.put(new ANTLRHashString("ASSERTIONS", this), new Integer(151));
	literals.put(new ANTLRHashString("INTER", this), new Integer(83));
	literals.put(new ANTLRHashString("OF", this), new Integer(164));
	literals.put(new ANTLRHashString("SEES", this), new Integer(132));
	literals.put(new ANTLRHashString("INITIALISATION", this), new Integer(152));
	literals.put(new ANTLRHashString("OR", this), new Integer(162));
	literals.put(new ANTLRHashString("PROPERTIES", this), new Integer(143));
	literals.put(new ANTLRHashString("IF", this), new Integer(159));
	literals.put(new ANTLRHashString("FALSE", this), new Integer(64));
	literals.put(new ANTLRHashString("ANY", this), new Integer(167));
	literals.put(new ANTLRHashString("VARIANT", this), new Integer(174));
	literals.put(new ANTLRHashString("EITHER", this), new Integer(165));
	literals.put(new ANTLRHashString("INCLUDES", this), new Integer(131));
	literals.put(new ANTLRHashString("or", this), new Integer(9));
	literals.put(new ANTLRHashString("ESTABLISH", this), new Integer(194));
	literals.put(new ANTLRHashString("EXTENDS", this), new Integer(129));
	literals.put(new ANTLRHashString("min", this), new Integer(72));
	literals.put(new ANTLRHashString("rec", this), new Integer(65));
	literals.put(new ANTLRHashString("NATURAL1", this), new Integer(94));
	literals.put(new ANTLRHashString("REFINES", this), new Integer(135));
	literals.put(new ANTLRHashString("ELSIF", this), new Integer(160));
	literals.put(new ANTLRHashString("INT", this), new Integer(86));
	literals.put(new ANTLRHashString("PI", this), new Integer(80));
	literals.put(new ANTLRHashString("INTEGER1", this), new Integer(89));
	literals.put(new ANTLRHashString("BEGIN", this), new Integer(155));
	literals.put(new ANTLRHashString("dom", this), new Integer(71));
	literals.put(new ANTLRHashString("card", this), new Integer(74));
	literals.put(new ANTLRHashString("HIDDEN_VARIABLES", this), new Integer(148));
	literals.put(new ANTLRHashString("INVARIANT", this), new Integer(147));
	literals.put(new ANTLRHashString("EVENTS", this), new Integer(190));
	literals.put(new ANTLRHashString("SELECT", this), new Integer(169));
	literals.put(new ANTLRHashString("CONSTRAINTS", this), new Integer(128));
	literals.put(new ANTLRHashString("ran", this), new Integer(69));
	literals.put(new ANTLRHashString("SETS", this), new Integer(141));
	literals.put(new ANTLRHashString("CONCRETE_VARIABLES", this), new Integer(149));
	literals.put(new ANTLRHashString("WHILE", this), new Integer(175));
	literals.put(new ANTLRHashString("IN", this), new Integer(173));
	literals.put(new ANTLRHashString("BOOL", this), new Integer(90));
	literals.put(new ANTLRHashString("VISIBLE_VARIABLES", this), new Integer(146));
	literals.put(new ANTLRHashString("max", this), new Integer(73));
	literals.put(new ANTLRHashString("IMPORTS", this), new Integer(133));
	literals.put(new ANTLRHashString("SIGMA", this), new Integer(81));
	literals.put(new ANTLRHashString("LET", this), new Integer(171));
	literals.put(new ANTLRHashString("OPERATIONS", this), new Integer(153));
	literals.put(new ANTLRHashString("bool", this), new Integer(27));
	literals.put(new ANTLRHashString("WHERE", this), new Integer(168));
	literals.put(new ANTLRHashString("ELSE", this), new Integer(166));
	literals.put(new ANTLRHashString("USES", this), new Integer(130));
	literals.put(new ANTLRHashString("struct", this), new Integer(84));
	literals.put(new ANTLRHashString("OBSERVATIONS", this), new Integer(189));
	literals.put(new ANTLRHashString("DEFINITIONS", this), new Integer(150));
	literals.put(new ANTLRHashString("OBSERVABLES", this), new Integer(188));
	literals.put(new ANTLRHashString("HIDDEN_CONSTANTS", this), new Integer(140));
	literals.put(new ANTLRHashString("TRUE", this), new Integer(63));
	literals.put(new ANTLRHashString("UNTIL", this), new Integer(193));
	literals.put(new ANTLRHashString("skip", this), new Integer(154));
	literals.put(new ANTLRHashString("INTEGER", this), new Integer(88));
	literals.put(new ANTLRHashString("VISIBLE_CONSTANTS", this), new Integer(138));
	literals.put(new ANTLRHashString("CONCRETE_CONSTANTS", this), new Integer(137));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '\t':  case '\n':  case '\u000c':  case '\r':
				case ' ':
				{
					mWS(true);
					theRetToken=_returnToken;
					break;
				}
				case '(':
				{
					mB_LPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case ',':
				{
					mB_COMMA(true);
					theRetToken=_returnToken;
					break;
				}
				case ')':
				{
					mB_RPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case '&':
				{
					mB_AND(true);
					theRetToken=_returnToken;
					break;
				}
				case ';':
				{
					mB_SEMICOLON(true);
					theRetToken=_returnToken;
					break;
				}
				case '}':
				{
					mB_CURLYCLOSE(true);
					theRetToken=_returnToken;
					break;
				}
				case '~':
				{
					mB_TILDE(true);
					theRetToken=_returnToken;
					break;
				}
				case ']':
				{
					mB_BRACKCLOSE(true);
					theRetToken=_returnToken;
					break;
				}
				case '\'':
				{
					mB_QUOTEIDENT(true);
					theRetToken=_returnToken;
					break;
				}
				case '^':
				{
					mB_CONCATSEQ(true);
					theRetToken=_returnToken;
					break;
				}
				case '%':
				{
					mB_LAMBDA(true);
					theRetToken=_returnToken;
					break;
				}
				case '!':
				{
					mB_FORALL(true);
					theRetToken=_returnToken;
					break;
				}
				case '#':
				{
					mB_EXISTS(true);
					theRetToken=_returnToken;
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					mB_NUMBER(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':
				{
					mB_ASTRING(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='+') && (LA(2)=='-') && (LA(3)=='>') && (LA(4)=='>')) {
						mB_PARTIAL_SURJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='-') && (LA(3)=='>') && (LA(4)=='>')) {
						mB_TOTAL_SURJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='-') && (LA(3)=='>') && (LA(4)=='>')) {
						mB_BIJECTION(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='e') && (LA(3)=='f') && (true) && (true) && (true) && (true) && (true) && (true)) {
						mB_ref(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='-') && (LA(3)=='-')) {
						mB_OUT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (LA(2)=='=') && (LA(3)=='>')) {
						mB_garde(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='=') && (LA(3)=='>')) {
						mB_EQUIV(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='<') && (LA(3)==':')) {
						mB_NOTSUBSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='<') && (LA(3)==':')) {
						mB_STRICTSUBSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='<') && (LA(3)=='<')) {
						mB_NOTSTRICTSBSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (LA(2)=='-') && (LA(3)=='>') && (true)) {
						mB_PARTIAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='-') && (LA(3)=='>')) {
						mB_RELATION(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='-') && (LA(3)=='>') && (true)) {
						mB_TOTAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='-') && (LA(3)=='>') && (true)) {
						mB_TOTAL_INJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='<') && (LA(3)=='|')) {
						mB_DOMAINSUBSTRACT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='>') && (LA(3)=='>')) {
						mB_RANGESUBSTRACT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='[') && (LA(2)=='[')) {
						mSUBST_TO(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='*')) {
						mML_COMMENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='|')) {
						mB_PARALLEL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (LA(2)=='=') && (true)) {
						mB_DOUBLE_EQUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (LA(2)=='*')) {
						mB_POWER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)==':') && (LA(2)==':')) {
						mB_BECOME_ELEM(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)==':') && (LA(2)=='=')) {
						mB_SIMPLESUBST(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (LA(2)=='>')) {
						mB_IMPLIES(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)==':')) {
						mB_NOTINSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)==':')) {
						mB_SUBSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='{') && (LA(2)=='}')) {
						mB_EMPTYSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='=')) {
						mB_NOTEQUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='=') && (true)) {
						mB_LESSTHANEQUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='=')) {
						mB_GREATERTHANEQUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='+')) {
						mB_PARTIAL_INJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='-')) {
						mB_MAPLET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='|')) {
						mB_DOMAINRESTRICT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='>') && (true)) {
						mB_RANGERESTRICT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='+')) {
						mB_OVERRIDEFORWARD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (LA(2)=='>')) {
						mB_OVERRIDEBACKWARD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='<')) {
						mB_RELPROD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='>')) {
						mB_PREAPPSEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='-') && (true)) {
						mB_APPSEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='|')) {
						mB_PREFIXSEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='\\') && (LA(2)=='|')) {
						mB_SUFFIXSEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='.') && (LA(2)=='.')) {
						mB_RANGE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='\\') && (LA(2)=='/')) {
						mB_UNION(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='\\')) {
						mB_INTER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<'||LA(1)=='[') && (LA(2)=='>'||LA(2)==']')) {
						mB_SEQEMPTY(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='$') && (LA(2)=='0')) {
						mB_CPRED(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (true)) {
						mB_MINUS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (true)) {
						mB_ADD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (true)) {
						mB_MULT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (true)) {
						mB_DIV(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='$') && (true)) {
						mB_dollars(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)==':') && (true)) {
						mB_INSET(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='{') && (true)) {
						mB_CURLYOPEN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (true)) {
						mB_EQUAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (true)) {
						mB_LESS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (true)) {
						mB_GREATER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='[') && (true)) {
						mB_BRACKOPEN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='.') && (true)) {
						mB_POINT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (true)) {
						mB_SUCH(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true) && (true) && (true) && (true) && (true) && (true)) {
						mB_IDENTIFIER(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mB_ref(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_ref;
		int _saveIndex;
		
		match("ref");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSUBST_TO(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SUBST_TO;
		int _saveIndex;
		
		match("[[");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		case '\u000c':
		{
			match('\f');
			break;
		}
		case '\n':  case '\r':
		{
			{
			if ((LA(1)=='\r') && (LA(2)=='\n')) {
				match("\r\n");
			}
			else if ((LA(1)=='\r') && (true)) {
				match('\r');
			}
			else if ((LA(1)=='\n')) {
				match('\n');
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			}
			if ( inputState.guessing==0 ) {
				newline();
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
				_ttype = Token.SKIP;
			
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mML_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ML_COMMENT;
		int _saveIndex;
		
		if ((LA(1)=='/') && (LA(2)=='*') && (LA(3)=='?') && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && ((LA(5) >= '\u0003' && LA(5) <= '\uffff')) && ((LA(6) >= '\u0003' && LA(6) <= '\uffff')) && (true) && (true) && (true)) {
			match("/*?");
			{
			_loop583:
			do {
				boolean synPredMatched581 = false;
				if (((LA(1)=='/') && (LA(2)=='*') && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && ((LA(5) >= '\u0003' && LA(5) <= '\uffff')) && ((LA(6) >= '\u0003' && LA(6) <= '\uffff')) && ((LA(7) >= '\u0003' && LA(7) <= '\uffff')) && (true) && (true))) {
					int _m581 = mark();
					synPredMatched581 = true;
					inputState.guessing++;
					try {
						{
						match("/*");
						}
					}
					catch (RecognitionException pe) {
						synPredMatched581 = false;
					}
					rewind(_m581);
inputState.guessing--;
				}
				if ( synPredMatched581 ) {
					mML_COMMENT(false);
				}
				else if (((LA(1)=='?') && (LA(2)=='*') && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && ((LA(5) >= '\u0003' && LA(5) <= '\uffff')) && (true) && (true) && (true) && (true))&&(LA(3)!='/' )) {
					match("?*");
				}
				else if ((_tokenSet_1.member(LA(1))) && ((LA(2) >= '\u0003' && LA(2) <= '\uffff')) && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && (true) && (true) && (true) && (true) && (true)) {
					{
					match(_tokenSet_1);
					}
				}
				else if ((LA(1)=='\n'||LA(1)=='\r')) {
					mNEWLINE(false);
				}
				else {
					break _loop583;
				}
				
			} while (true);
			}
			match("?*/");
			if ( inputState.guessing==0 ) {
				_ttype = Token.SKIP; metrics.addLineComment();
			}
		}
		else if ((LA(1)=='/') && (LA(2)=='*') && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && (true) && (true) && (true) && (true) && (true)) {
			match("/*");
			{
			_loop578:
			do {
				boolean synPredMatched576 = false;
				if (((LA(1)=='/') && (LA(2)=='*') && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && ((LA(4) >= '\u0003' && LA(4) <= '\uffff')) && ((LA(5) >= '\u0003' && LA(5) <= '\uffff')) && ((LA(6) >= '\u0003' && LA(6) <= '\uffff')) && (true) && (true) && (true))) {
					int _m576 = mark();
					synPredMatched576 = true;
					inputState.guessing++;
					try {
						{
						match("/*");
						}
					}
					catch (RecognitionException pe) {
						synPredMatched576 = false;
					}
					rewind(_m576);
inputState.guessing--;
				}
				if ( synPredMatched576 ) {
					mML_COMMENT(false);
				}
				else if (((LA(1)=='*') && ((LA(2) >= '\u0003' && LA(2) <= '\uffff')) && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && (true) && (true) && (true) && (true) && (true) && (true))&&( LA(2)!='/' )) {
					match('*');
				}
				else if ((_tokenSet_2.member(LA(1))) && ((LA(2) >= '\u0003' && LA(2) <= '\uffff')) && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && (true) && (true) && (true) && (true) && (true) && (true)) {
					{
					match(_tokenSet_2);
					}
				}
				else if ((LA(1)=='\n'||LA(1)=='\r')) {
					mNEWLINE(false);
				}
				else {
					break _loop578;
				}
				
			} while (true);
			}
			match("*/");
			if ( inputState.guessing==0 ) {
				_ttype = Token.SKIP;
				metrics.addLineComment();
			}
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mNEWLINE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NEWLINE;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '\n':
		{
			match('\n');
			break;
		}
		case '\r':
		{
			{
			match('\r');
			{
			if ((LA(1)=='\n') && ((LA(2) >= '\u0003' && LA(2) <= '\uffff')) && ((LA(3) >= '\u0003' && LA(3) <= '\uffff')) && (true) && (true) && (true) && (true) && (true) && (true)) {
				match('\n');
			}
			else if (((LA(1) >= '\u0003' && LA(1) <= '\uffff')) && ((LA(2) >= '\u0003' && LA(2) <= '\uffff')) && (true) && (true) && (true) && (true) && (true) && (true) && (true)) {
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			}
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
					newline(); 
					metrics.addLine();
				
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_LPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_LPAREN;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_COMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RPAREN;
		int _saveIndex;
		
		match(')');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PARALLEL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PARALLEL;
		int _saveIndex;
		
		match("||");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_DOUBLE_EQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_DOUBLE_EQUAL;
		int _saveIndex;
		
		match("==");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_MINUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_MINUS;
		int _saveIndex;
		
		match('-');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_ADD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_ADD;
		int _saveIndex;
		
		match('+');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_POWER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_POWER;
		int _saveIndex;
		
		match("**");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_MULT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_MULT;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_DIV(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_DIV;
		int _saveIndex;
		
		match('/');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_AND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_AND;
		int _saveIndex;
		
		match('&');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_dollars(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_dollars;
		int _saveIndex;
		
		match('$');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_OUT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_OUT;
		int _saveIndex;
		
		match("<--");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_garde(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_garde;
		int _saveIndex;
		
		match("==>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SEMICOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SEMICOLON;
		int _saveIndex;
		
		match(';');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_BECOME_ELEM(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_BECOME_ELEM;
		int _saveIndex;
		
		match("::");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SIMPLESUBST(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SIMPLESUBST;
		int _saveIndex;
		
		match(":=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_IMPLIES(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_IMPLIES;
		int _saveIndex;
		
		match("=>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_EQUIV(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_EQUIV;
		int _saveIndex;
		
		match("<=>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_INSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_INSET;
		int _saveIndex;
		
		match(':');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_NOTINSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_NOTINSET;
		int _saveIndex;
		
		match("/:");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SUBSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SUBSET;
		int _saveIndex;
		
		match("<:");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_NOTSUBSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_NOTSUBSET;
		int _saveIndex;
		
		match("/<:");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_STRICTSUBSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_STRICTSUBSET;
		int _saveIndex;
		
		match("<<:");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_NOTSTRICTSBSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_NOTSTRICTSBSET;
		int _saveIndex;
		
		match("/<<:");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_EMPTYSET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_EMPTYSET;
		int _saveIndex;
		
		match("{}");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_CURLYOPEN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_CURLYOPEN;
		int _saveIndex;
		
		match('{');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_CURLYCLOSE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_CURLYCLOSE;
		int _saveIndex;
		
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_EQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_EQUAL;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_LESS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_LESS;
		int _saveIndex;
		
		match('<');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_GREATER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_GREATER;
		int _saveIndex;
		
		match('>');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_NOTEQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_NOTEQUAL;
		int _saveIndex;
		
		match("/=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_LESSTHANEQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_LESSTHANEQUAL;
		int _saveIndex;
		
		match("<=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_GREATERTHANEQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_GREATERTHANEQUAL;
		int _saveIndex;
		
		match(">=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PARTIAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PARTIAL;
		int _saveIndex;
		
		match("+->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RELATION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RELATION;
		int _saveIndex;
		
		match("<->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_TOTAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_TOTAL;
		int _saveIndex;
		
		match("-->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PARTIAL_INJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PARTIAL_INJECT;
		int _saveIndex;
		
		match(">+>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_TOTAL_INJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_TOTAL_INJECT;
		int _saveIndex;
		
		match(">->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PARTIAL_SURJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PARTIAL_SURJECT;
		int _saveIndex;
		
		match("+->>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_TOTAL_SURJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_TOTAL_SURJECT;
		int _saveIndex;
		
		match("-->>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_BIJECTION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_BIJECTION;
		int _saveIndex;
		
		match(">->>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_TILDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_TILDE;
		int _saveIndex;
		
		match('~');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_BRACKOPEN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_BRACKOPEN;
		int _saveIndex;
		
		match('[');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_BRACKCLOSE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_BRACKCLOSE;
		int _saveIndex;
		
		match(']');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_QUOTEIDENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_QUOTEIDENT;
		int _saveIndex;
		
		match("'");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_MAPLET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_MAPLET;
		int _saveIndex;
		
		match("|->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_DOMAINRESTRICT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_DOMAINRESTRICT;
		int _saveIndex;
		
		match("<|");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RANGERESTRICT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RANGERESTRICT;
		int _saveIndex;
		
		match("|>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_DOMAINSUBSTRACT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_DOMAINSUBSTRACT;
		int _saveIndex;
		
		match("<<|");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RANGESUBSTRACT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RANGESUBSTRACT;
		int _saveIndex;
		
		match("|>>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_OVERRIDEFORWARD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_OVERRIDEFORWARD;
		int _saveIndex;
		
		match("<+");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_OVERRIDEBACKWARD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_OVERRIDEBACKWARD;
		int _saveIndex;
		
		match("+>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RELPROD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RELPROD;
		int _saveIndex;
		
		match("><");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_CONCATSEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_CONCATSEQ;
		int _saveIndex;
		
		match('^');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PREAPPSEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PREAPPSEQ;
		int _saveIndex;
		
		match("->");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_APPSEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_APPSEQ;
		int _saveIndex;
		
		match("<-");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_PREFIXSEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_PREFIXSEQ;
		int _saveIndex;
		
		match("/|\\");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SUFFIXSEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SUFFIXSEQ;
		int _saveIndex;
		
		match("\\|/");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_RANGE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_RANGE;
		int _saveIndex;
		
		match("..");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_UNION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_UNION;
		int _saveIndex;
		
		match("\\/");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_INTER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_INTER;
		int _saveIndex;
		
		match("/\\");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_LAMBDA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_LAMBDA;
		int _saveIndex;
		
		match('%');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_FORALL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_FORALL;
		int _saveIndex;
		
		match('!');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_EXISTS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_EXISTS;
		int _saveIndex;
		
		match('#');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SEQEMPTY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SEQEMPTY;
		int _saveIndex;
		
		switch ( LA(1)) {
		case '<':
		{
			match("<>");
			break;
		}
		case '[':
		{
			match("[]");
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_POINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_POINT;
		int _saveIndex;
		
		match('.');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_SUCH(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_SUCH;
		int _saveIndex;
		
		match('|');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_CPRED(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_CPRED;
		int _saveIndex;
		
		match("$0");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mDIGIT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIGIT;
		int _saveIndex;
		
		matchRange('0','9');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_NUMBER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_NUMBER;
		int _saveIndex;
		
		{
		int _cnt660=0;
		_loop660:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mDIGIT(false);
			}
			else {
				if ( _cnt660>=1 ) { break _loop660; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt660++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mALPHA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ALPHA;
		int _saveIndex;
		
		switch ( LA(1)) {
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':
		{
			matchRange('A','Z');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_ASTRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_ASTRING;
		int _saveIndex;
		
		_saveIndex=text.length();
		match('"');
		text.setLength(_saveIndex);
		{
		_loop665:
		do {
			if ((_tokenSet_3.member(LA(1)))) {
				{
				match(_tokenSet_3);
				}
			}
			else {
				break _loop665;
			}
			
		} while (true);
		}
		_saveIndex=text.length();
		match('"');
		text.setLength(_saveIndex);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mVOCAB(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = VOCAB;
		int _saveIndex;
		
		matchRange('\3','\377');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mB_IDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = B_IDENTIFIER;
		int _saveIndex;
		
		{
		mALPHA(false);
		}
		{
		_loop670:
		do {
			switch ( LA(1)) {
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case 'a':  case 'b':
			case 'c':  case 'd':  case 'e':  case 'f':
			case 'g':  case 'h':  case 'i':  case 'j':
			case 'k':  case 'l':  case 'm':  case 'n':
			case 'o':  case 'p':  case 'q':  case 'r':
			case 's':  case 't':  case 'u':  case 'v':
			case 'w':  case 'x':  case 'y':  case 'z':
			{
				mALPHA(false);
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				mDIGIT(false);
				break;
			}
			case '_':
			{
				match('_');
				break;
			}
			default:
			{
				break _loop670;
			}
			}
		} while (true);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		data[1]=576460743847706622L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[2048];
		data[0]=9223372036854766584L;
		for (int i = 1; i<=1023; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		data[0]=-4398046520328L;
		for (int i = 1; i<=1023; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[2048];
		data[0]=-17179869192L;
		for (int i = 1; i<=1023; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
