// $ANTLR 2.7.6 (2005-12-22): "expandedBSystem.g" -> "BSystemParser.java"$

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

	import java.io.*;

	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;

// Our Packages
    import ABTOOLS.DEBUGGING.*;
    import ABTOOLS.ANTLR_TOOLS.*;

public class BSystemParser extends antlr.LLkParser       implements BSystemTokenTypes
 {

	String module = "BSystem.g";



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
	
	public String path = "";
  
  public void setPath(String p){
     path = p;
  }

// Debug enable or disabled
	protected DEBUG debug = new DEBUG();

	public void setDebug (DEBUG newdebug)
	{
		debug = newdebug;
	}


// Switch de lecture des machine abstraites liees
	protected boolean LoadLinked = false;

	public ListAM listAM = new ListAM();

	public ListAM getListAM()
	{
		return listAM;
	}

	public void add_AM (MyNode t1, String type)
	{
		if ( type.compareTo("PROMOTES") != 0 )
		{
		  String ext = searchExtension(path, t1.getText());
      listAM.Add_AM(new LinkedAM(t1.getText(), type, ext, path));
      //listAM.Add_AM(new LinkedAM(#t1.getText(), type)); 
		}
	}


// July 2001
// We prepare the PO generation by memorization of principals terms

	public ASTterme previous_gop = new ASTterme(debug);

	public ASTterme getPreviousGOP()
	{
		return previous_gop;
	}

// April 2002
// Semantic Control
	public void RU1 (MyNode node)
	{
	}

	public void RA1 (MyNode node)
	{
	}

// January 2010
// Metrics management

	public Metrics metrics = null;
	
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

// permet de gerer le type de mesure en cours
	protected int metricstype = 0;
	
	protected int unknown    	= 0;
	protected int mvariables 	= 1;
	protected int mconstants 	= 2;
	protected int maconstants 	= 3;
	protected int mset 		= 4;
	protected int mservices  	= 5;
	protected int mcvariables 	= 6;
		
	protected void ChooseMetrics (int mtype)
	{
		metricstype = mtype;
	}
	
	protected void AddMetrics()
	{
		if (metricstype == mvariables)
		{
			metrics.addVarGlobal();
		}
		else if (metricstype == mconstants)
		{
			metrics.addConstant();
		}
		else if (metricstype == maconstants)
		{
			metrics.addAConstant();
		}
		else if (metricstype == mservices)
		{
			metrics.addService();
		}
		else if (metricstype == mset)
		{
			metrics.addSet();
		}
		else if (metricstype == mcvariables)
		{
			metrics.addCVarGlobal();
		}
		else
		{
		}
	}
		
	public void setMetrics (Metrics m)
	{
		metrics = m;
	}
	
	/* Searches the file 'name' with extension '.mch'
   *  if the file doesn't exist, search the file 'name' with 
   *  extension 'ref'. Returns the extension found.
   */
   public static String searchExtension(String path, String name){
   String extension = "";
   File file = new File(path,name+".mch");
   if (file.isFile()){
    extension = ".mch";
   }
   else{
    File file2 = new File(path,name+".ref");
    if (file2.isFile()){
     extension = ".ref";
    }
   }     
   return extension;
   }

protected BSystemParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BSystemParser(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected BSystemParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BSystemParser(TokenStream lexer) {
  this(lexer,3);
}

public BSystemParser(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void composant(
		boolean loadLinked
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode composant_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					// We memorise the global decision
					LoadLinked = loadLinked;
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_SYSTEM:
			{
				machine();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_REFINEMENT:
			{
				refinement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_IMPLEMENTATION:
			{
				implementation();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			{
				if ( inputState.guessing==0 ) {
											
								System.err.println ( "ABParser Warning : Empty source file!" );
								errors.WSyntaxic   ( module, "The file is empty" );
							
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			composant_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = composant_AST;
	}
	
	public final void machine() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode machine_AST = null;
		
		try {      // for error handling
			MyNode tmp1_AST = null;
			tmp1_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp1_AST);
			match(LITERAL_SYSTEM);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5:
			do {
				switch ( LA(1)) {
				case LITERAL_CONSTRAINTS:
				{
					constraints();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EXTENDS:
				case LITERAL_USES:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_PROMOTES:
				{
					mchlink();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_SETS:
				{
					sets();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				{
					constants();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				{
					aconstants();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_PROPERTIES:
				{
					properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				{
					variables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				{
					cvariables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INVARIANT:
				{
					invariant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_DYNAMICS:
				{
					dynamics();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ASSERTIONS:
				{
					assertions();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_DEFINITIONS:
				{
					definitions_mch();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INITIALISATION:
				{
					initialisation_mch();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_MODALITIES:
				{
					modalities();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EVENTS:
				{
					events_mch();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop5;
				}
				}
			} while (true);
			}
			match(LITERAL_END);
			match(Token.EOF_TYPE);
			machine_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = machine_AST;
	}
	
	public final void refinement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode refinement_AST = null;
		
		try {      // for error handling
			MyNode tmp4_AST = null;
			tmp4_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp4_AST);
			match(LITERAL_REFINEMENT);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop15:
			do {
				switch ( LA(1)) {
				case LITERAL_REFINES:
				{
					refines();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_CONSTRAINTS:
				{
					constraints();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EXTENDS:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_PROMOTES:
				{
					reflink();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_SETS:
				{
					sets();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				{
					aconstants();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				{
					constants();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_PROPERTIES:
				{
					properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				{
					variables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				{
					cvariables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INVARIANT:
				{
					invariant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_VARIANT:
				{
					variant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ASSERTIONS:
				{
					assertions();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_DEFINITIONS:
				{
					definitions_ref();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INITIALISATION:
				{
					initialisation_ref();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EVENTS:
				{
					events_ref();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop15;
				}
				}
			} while (true);
			}
			match(LITERAL_END);
			match(Token.EOF_TYPE);
			refinement_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = refinement_AST;
	}
	
	public final void implementation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode implementation_AST = null;
		
		try {      // for error handling
			MyNode tmp7_AST = null;
			tmp7_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp7_AST);
			match(LITERAL_IMPLEMENTATION);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop20:
			do {
				switch ( LA(1)) {
				case LITERAL_REFINES:
				{
					refines();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EXTENDS:
				case LITERAL_SEES:
				case LITERAL_IMPORTS:
				case LITERAL_PROMOTES:
				{
					implink();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_VALUES:
				{
					values();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_SETS:
				{
					sets();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				{
					constants();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_PROPERTIES:
				{
					properties();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				{
					variables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				{
					cvariables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INVARIANT:
				{
					invariant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ASSERTIONS:
				{
					assertions();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_DEFINITIONS:
				{
					definitions_imp();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INITIALISATION:
				{
					initialisation_imp();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EVENTS:
				{
					events_imp();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop20;
				}
				}
			} while (true);
			}
			match(LITERAL_END);
			match(Token.EOF_TYPE);
			implementation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = implementation_AST;
	}
	
	public final void paramName() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode paramName_AST = null;
		Token  aa = null;
		MyNode aa_AST = null;
		Token  bb = null;
		MyNode bb_AST = null;
		
		try {      // for error handling
			aa = LT(1);
			aa_AST = (MyNode)astFactory.create(aa);
			astFactory.addASTChild(currentAST, aa_AST);
			match(B_IDENTIFIER);
			if ( inputState.guessing==0 ) {
				
					aa_AST.memorizeOpname(aa_AST.getText());
				
			}
			{
			switch ( LA(1)) {
			case B_LPAREN:
			{
				bb = LT(1);
				bb_AST = (MyNode)astFactory.create(bb);
				astFactory.makeASTRoot(currentAST, bb_AST);
				match(B_LPAREN);
				listIdentifier();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						bb_AST.memorizeOpname(aa_AST.getText());
					
				}
				match(B_RPAREN);
				break;
			}
			case EOF:
			case B_COMMA:
			case B_EQUAL:
			case B_DOUBLE_EQUAL:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_VARIANT:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_DYNAMICS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			paramName_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = paramName_AST;
	}
	
	public final void constraints() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode constraints_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp11_AST = null;
			tmp11_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp11_AST);
			match(LITERAL_CONSTRAINTS);
			expression();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putC(t1_AST);
				
			}
			constraints_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = constraints_AST;
	}
	
	public final void mchlink() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode mchlink_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_USES:
			{
				uses();
				astFactory.addASTChild(currentAST, returnAST);
				mchlink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INCLUDES:
			{
				includes();
				astFactory.addASTChild(currentAST, returnAST);
				mchlink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SEES:
			{
				sees();
				astFactory.addASTChild(currentAST, returnAST);
				mchlink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_EXTENDS:
			{
				extendeds();
				astFactory.addASTChild(currentAST, returnAST);
				mchlink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PROMOTES:
			{
				promotes();
				astFactory.addASTChild(currentAST, returnAST);
				mchlink_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = mchlink_AST;
	}
	
	public final void sets() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sets_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(mset);
				
			}
			MyNode tmp12_AST = null;
			tmp12_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp12_AST);
			match(LITERAL_SETS);
			sets_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(unknown);
				
			}
			sets_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sets_AST;
	}
	
	public final void constants() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode constants_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(mconstants);
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_CONSTANTS:
			{
				MyNode tmp13_AST = null;
				tmp13_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(LITERAL_CONSTANTS);
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				MyNode tmp14_AST = null;
				tmp14_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp14_AST);
				match(LITERAL_CONCRETE_CONSTANTS);
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				MyNode tmp15_AST = null;
				tmp15_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp15_AST);
				match(LITERAL_VISIBLE_CONSTANTS);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(unknown);
				
			}
			constants_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = constants_AST;
	}
	
	public final void aconstants() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode aconstants_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(maconstants);
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_ABSTRACT_CONSTANTS:
			{
				MyNode tmp16_AST = null;
				tmp16_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp16_AST);
				match(LITERAL_ABSTRACT_CONSTANTS);
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				MyNode tmp17_AST = null;
				tmp17_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp17_AST);
				match(LITERAL_HIDDEN_CONSTANTS);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(unknown);
				
			}
			aconstants_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = aconstants_AST;
	}
	
	public final void properties() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode properties_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp18_AST = null;
			tmp18_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp18_AST);
			match(LITERAL_PROPERTIES);
			expression();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putP(t1_AST);
				
			}
			properties_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = properties_AST;
	}
	
	public final void variables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variables_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(mvariables);
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_VARIABLES:
			{
				MyNode tmp19_AST = null;
				tmp19_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp19_AST);
				match(LITERAL_VARIABLES);
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				MyNode tmp20_AST = null;
				tmp20_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp20_AST);
				match(LITERAL_ABSTRACT_VARIABLES);
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				MyNode tmp21_AST = null;
				tmp21_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp21_AST);
				match(LITERAL_VISIBLE_VARIABLES);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(unknown);
				
			}
			variables_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = variables_AST;
	}
	
	public final void cvariables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode cvariables_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(mcvariables);
				
			}
			{
			switch ( LA(1)) {
			case LITERAL_HIDDEN_VARIABLES:
			{
				MyNode tmp22_AST = null;
				tmp22_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp22_AST);
				match(LITERAL_HIDDEN_VARIABLES);
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				MyNode tmp23_AST = null;
				tmp23_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp23_AST);
				match(LITERAL_CONCRETE_VARIABLES);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(unknown);
				
			}
			cvariables_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = cvariables_AST;
	}
	
	public final void invariant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode invariant_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp24_AST = null;
			tmp24_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp24_AST);
			match(LITERAL_INVARIANT);
			expression();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putI(t1_AST);	// We memorize the node Invariant for GOP
				
			}
			invariant_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = invariant_AST;
	}
	
	public final void dynamics() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode dynamics_AST = null;
		
		try {      // for error handling
			MyNode tmp25_AST = null;
			tmp25_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp25_AST);
			match(LITERAL_DYNAMICS);
			predicate_with_prime();
			astFactory.addASTChild(currentAST, returnAST);
			dynamics_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = dynamics_AST;
	}
	
	public final void assertions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assertions_AST = null;
		
		try {      // for error handling
			MyNode tmp26_AST = null;
			tmp26_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp26_AST);
			match(LITERAL_ASSERTIONS);
			list_assertions();
			astFactory.addASTChild(currentAST, returnAST);
			assertions_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = assertions_AST;
	}
	
	public final void definitions_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp27_AST = null;
			tmp27_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp27_AST);
			match(LITERAL_DEFINITIONS);
			list_def_mch();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_mch_AST;
	}
	
	public final void initialisation_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode initialisation_mch_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp28_AST = null;
			tmp28_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp28_AST);
			match(LITERAL_INITIALISATION);
			substitution_mch();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putINIT(t1_AST);	// We memorize the node Invariant for GOP
				
			}
			initialisation_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_mch_AST;
	}
	
	public final void modalities() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode modalities_AST = null;
		
		try {      // for error handling
			MyNode tmp29_AST = null;
			tmp29_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp29_AST);
			match(LITERAL_MODALITIES);
			new_select();
			astFactory.addASTChild(currentAST, returnAST);
			modalities_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = modalities_AST;
	}
	
	public final void events_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode events_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp30_AST = null;
			tmp30_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp30_AST);
			match(LITERAL_EVENTS);
			listOperationMch();
			astFactory.addASTChild(currentAST, returnAST);
			events_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = events_mch_AST;
	}
	
	public final void listOperationMch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationMch_AST = null;
		
		try {      // for error handling
			operationMch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop140:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp31_AST = null;
					tmp31_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp31_AST);
					match(B_SEMICOLON);
					operationMch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop140;
				}
				
			} while (true);
			}
			listOperationMch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationMch_AST;
	}
	
	public final void new_select() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_select_AST = null;
		
		try {      // for error handling
			MyNode tmp32_AST = null;
			tmp32_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp32_AST);
			match(LITERAL_SELECT);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_LEADSTO);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_WHILE);
			event_list();
			astFactory.addASTChild(currentAST, returnAST);
			MyNode tmp35_AST = null;
			tmp35_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp35_AST);
			match(LITERAL_INVARIANT);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			MyNode tmp36_AST = null;
			tmp36_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp36_AST);
			match(LITERAL_VARIANT);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			MyNode tmp37_AST = null;
			tmp37_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp37_AST);
			match(LITERAL_END);
			new_select_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = new_select_AST;
	}
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		
		try {      // for error handling
			plogical_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop388:
			do {
				if ((LA(1)==B_IMPLIES)) {
					MyNode tmp38_AST = null;
					tmp38_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp38_AST);
					match(B_IMPLIES);
					plogical_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop388;
				}
				
			} while (true);
			}
			predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_AST;
	}
	
	public final void event_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode event_list_AST = null;
		
		try {      // for error handling
			MyNode tmp39_AST = null;
			tmp39_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp39_AST);
			match(B_IDENTIFIER);
			{
			_loop11:
			do {
				if ((LA(1)==LITERAL_OR)) {
					MyNode tmp40_AST = null;
					tmp40_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp40_AST);
					match(LITERAL_OR);
					MyNode tmp41_AST = null;
					tmp41_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp41_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
			event_list_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = event_list_AST;
	}
	
	public final void predicate_with_prime() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_with_prime_AST = null;
		
		try {      // for error handling
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			predicate_with_prime_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_with_prime_AST;
	}
	
	public final void refines() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode refines_AST = null;
		Token  t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp42_AST = null;
			tmp42_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp42_AST);
			match(LITERAL_REFINES);
			t1 = LT(1);
			t1_AST = (MyNode)astFactory.create(t1);
			astFactory.addASTChild(currentAST, t1_AST);
			match(B_IDENTIFIER);
			if ( inputState.guessing==0 ) {
				
						add_AM(t1_AST,"REFINES");
				
			}
			refines_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = refines_AST;
	}
	
	public final void reflink() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode reflink_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_INCLUDES:
			{
				includes();
				astFactory.addASTChild(currentAST, returnAST);
				reflink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SEES:
			{
				sees();
				astFactory.addASTChild(currentAST, returnAST);
				reflink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_EXTENDS:
			{
				extendeds();
				astFactory.addASTChild(currentAST, returnAST);
				reflink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PROMOTES:
			{
				promotes();
				astFactory.addASTChild(currentAST, returnAST);
				reflink_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = reflink_AST;
	}
	
	public final void variant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variant_AST = null;
		
		try {      // for error handling
			MyNode tmp43_AST = null;
			tmp43_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp43_AST);
			match(LITERAL_VARIANT);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			variant_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = variant_AST;
	}
	
	public final void definitions_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp44_AST = null;
			tmp44_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp44_AST);
			match(LITERAL_DEFINITIONS);
			list_def_ref();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_ref_AST;
	}
	
	public final void initialisation_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode initialisation_ref_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp45_AST = null;
			tmp45_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp45_AST);
			match(LITERAL_INITIALISATION);
			parallele_ref();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putINIT(t1_AST);	// We memorize the node Invariant for GOP
				
			}
			initialisation_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_ref_AST;
	}
	
	public final void events_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode events_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp46_AST = null;
			tmp46_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp46_AST);
			match(LITERAL_EVENTS);
			listOperationRef();
			astFactory.addASTChild(currentAST, returnAST);
			events_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = events_ref_AST;
	}
	
	public final void listOperationRef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationRef_AST = null;
		
		try {      // for error handling
			operationRef();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop145:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp47_AST = null;
					tmp47_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp47_AST);
					match(B_SEMICOLON);
					operationRef();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop145;
				}
				
			} while (true);
			}
			listOperationRef_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationRef_AST;
	}
	
	public final void implink() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode implink_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_SEES:
			{
				sees();
				astFactory.addASTChild(currentAST, returnAST);
				implink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IMPORTS:
			{
				imports();
				astFactory.addASTChild(currentAST, returnAST);
				implink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_EXTENDS:
			{
				extendeds();
				astFactory.addASTChild(currentAST, returnAST);
				implink_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PROMOTES:
			{
				promotes();
				astFactory.addASTChild(currentAST, returnAST);
				implink_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = implink_AST;
	}
	
	public final void values() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode values_AST = null;
		
		try {      // for error handling
			MyNode tmp48_AST = null;
			tmp48_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp48_AST);
			match(LITERAL_VALUES);
			list_valuation();
			astFactory.addASTChild(currentAST, returnAST);
			values_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = values_AST;
	}
	
	public final void definitions_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp49_AST = null;
			tmp49_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp49_AST);
			match(LITERAL_DEFINITIONS);
			list_def_imp();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_imp_AST;
	}
	
	public final void initialisation_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode initialisation_imp_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp50_AST = null;
			tmp50_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp50_AST);
			match(LITERAL_INITIALISATION);
			sequential();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putINIT(t1_AST);	// We memorize the node Invariant for GOP
				
			}
			initialisation_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_imp_AST;
	}
	
	public final void events_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode events_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp51_AST = null;
			tmp51_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp51_AST);
			match(LITERAL_EVENTS);
			listOperationImp();
			astFactory.addASTChild(currentAST, returnAST);
			events_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = events_imp_AST;
	}
	
	public final void listOperationImp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationImp_AST = null;
		
		try {      // for error handling
			operationImp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop150:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp52_AST = null;
					tmp52_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp52_AST);
					match(B_SEMICOLON);
					operationImp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop150;
				}
				
			} while (true);
			}
			listOperationImp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationImp_AST;
	}
	
	protected final void predInvertedParamInvertedQuoted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParamInvertedQuoted_AST = null;
		
		try {      // for error handling
			predInvertedParamInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop25:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp53_AST = null;
					tmp53_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp53_AST);
					match(B_QUOTEIDENT);
					predInvertedParamInverted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop25;
				}
				
			} while (true);
			}
			predInvertedParamInvertedQuoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_14.member(LA(2))) && (_tokenSet_15.member(LA(3)))) {
				MyNode tmp54_AST = null;
				tmp54_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp54_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_14.member(LA(1))) && (_tokenSet_15.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
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
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = predInvertedParamInverted_AST;
	}
	
	public final void dummy() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode dummy_AST = null;
		
		try {      // for error handling
			MyNode tmp55_AST = null;
			tmp55_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp55_AST);
			match(B_PREC);
			dummy_AST = (MyNode)currentAST.root;
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
	
	public final void listIdentifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listIdentifier_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					AddMetrics();
				
			}
			{
			_loop33:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp56_AST = null;
					tmp56_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp56_AST);
					match(B_COMMA);
					nameRenamed();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							AddMetrics();
						
					}
				}
				else {
					break _loop33;
				}
				
			} while (true);
			}
			listIdentifier_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			} else {
			  throw ex;
			}
		}
		returnAST = listIdentifier_AST;
	}
	
	public final void paramRename() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode paramRename_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					AddMetrics();
				
			}
			{
			switch ( LA(1)) {
			case B_LPAREN:
			{
				MyNode tmp57_AST = null;
				tmp57_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp57_AST);
				match(B_LPAREN);
				listIdentifier();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				break;
			}
			case EOF:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			paramRename_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = paramRename_AST;
	}
	
	public final void nameRenamed() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		
		try {      // for error handling
			MyNode tmp59_AST = null;
			tmp59_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp59_AST);
			match(B_IDENTIFIER);
			{
			_loop383:
			do {
				if ((LA(1)==B_POINT)) {
					MyNode tmp60_AST = null;
					tmp60_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp60_AST);
					match(B_POINT);
					MyNode tmp61_AST = null;
					tmp61_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp61_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop383;
				}
				
			} while (true);
			}
			nameRenamed_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamed_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_AST = null;
		
		try {      // for error handling
			logical_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop287:
			do {
				if ((LA(1)==B_IMPLIES)) {
					MyNode tmp62_AST = null;
					tmp62_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp62_AST);
					match(B_IMPLIES);
					logical_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop287;
				}
				
			} while (true);
			}
			expression_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_AST;
	}
	
	public final void uses() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode uses_AST = null;
		
		try {      // for error handling
			MyNode tmp63_AST = null;
			tmp63_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp63_AST);
			match(LITERAL_USES);
			listNames("USES");
			astFactory.addASTChild(currentAST, returnAST);
			uses_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = uses_AST;
	}
	
	public final void includes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode includes_AST = null;
		
		try {      // for error handling
			MyNode tmp64_AST = null;
			tmp64_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp64_AST);
			match(LITERAL_INCLUDES);
			listInstanciation("INCLUDES");
			astFactory.addASTChild(currentAST, returnAST);
			includes_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = includes_AST;
	}
	
	public final void sees() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sees_AST = null;
		
		try {      // for error handling
			MyNode tmp65_AST = null;
			tmp65_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp65_AST);
			match(LITERAL_SEES);
			listNames("SEES");
			astFactory.addASTChild(currentAST, returnAST);
			sees_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sees_AST;
	}
	
	public final void extendeds() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode extendeds_AST = null;
		
		try {      // for error handling
			MyNode tmp66_AST = null;
			tmp66_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp66_AST);
			match(LITERAL_EXTENDS);
			listInstanciation("EXTENDS");
			astFactory.addASTChild(currentAST, returnAST);
			extendeds_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = extendeds_AST;
	}
	
	public final void promotes() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode promotes_AST = null;
		
		try {      // for error handling
			MyNode tmp67_AST = null;
			tmp67_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp67_AST);
			match(LITERAL_PROMOTES);
			listNames("PROMOTES");
			astFactory.addASTChild(currentAST, returnAST);
			promotes_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = promotes_AST;
	}
	
	public final void imports() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode imports_AST = null;
		
		try {      // for error handling
			MyNode tmp68_AST = null;
			tmp68_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp68_AST);
			match(LITERAL_IMPORTS);
			listInstanciation("IMPORTS");
			astFactory.addASTChild(currentAST, returnAST);
			imports_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = imports_AST;
	}
	
	public final void listInstanciation(
		String type
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listInstanciation_AST = null;
		
		try {      // for error handling
			paramRenameValuation(type);
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop54:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp69_AST = null;
					tmp69_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp69_AST);
					match(B_COMMA);
					paramRenameValuation(type);
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop54;
				}
				
			} while (true);
			}
			listInstanciation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = listInstanciation_AST;
	}
	
	public final void listNames(
		String type
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listNames_AST = null;
		
		try {      // for error handling
			nameRenamedWithSave(type);
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop44:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp70_AST = null;
					tmp70_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp70_AST);
					match(B_COMMA);
					nameRenamedWithSave(type);
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop44;
				}
				
			} while (true);
			}
			listNames_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = listNames_AST;
	}
	
	public final void nameRenamedWithSave(
		String type
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamedWithSave_AST = null;
		Token  t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			boolean synPredMatched47 = false;
			if (((LA(1)==B_IDENTIFIER) && (LA(2)==B_POINT))) {
				int _m47 = mark();
				synPredMatched47 = true;
				inputState.guessing++;
				try {
					{
					match(B_IDENTIFIER);
					match(B_POINT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched47 = false;
				}
				rewind(_m47);
inputState.guessing--;
			}
			if ( synPredMatched47 ) {
				MyNode tmp71_AST = null;
				tmp71_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp71_AST);
				match(B_IDENTIFIER);
				{
				MyNode tmp72_AST = null;
				tmp72_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp72_AST);
				match(B_POINT);
				nameRenamedWithSave(type);
				astFactory.addASTChild(currentAST, returnAST);
				}
				nameRenamedWithSave_AST = (MyNode)currentAST.root;
			}
			else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_20.member(LA(2)))) {
				t1 = LT(1);
				t1_AST = (MyNode)astFactory.create(t1);
				astFactory.addASTChild(currentAST, t1_AST);
				match(B_IDENTIFIER);
				if ( inputState.guessing==0 ) {
					
							add_AM((MyNode)t1_AST,type);
					
				}
				nameRenamedWithSave_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
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
		returnAST = nameRenamedWithSave_AST;
	}
	
	public final void listParamNames() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listParamNames_AST = null;
		
		try {      // for error handling
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop51:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp73_AST = null;
					tmp73_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp73_AST);
					match(B_COMMA);
					paramName();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop51;
				}
				
			} while (true);
			}
			listParamNames_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = listParamNames_AST;
	}
	
	public final void paramRenameValuation(
		String type
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode paramRenameValuation_AST = null;
		
		try {      // for error handling
			nameRenamedWithSave(type);
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop57:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp74_AST = null;
					tmp74_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp74_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop57;
				}
				
			} while (true);
			}
			paramRenameValuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = paramRenameValuation_AST;
	}
	
	public final void list_New_Predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_New_Predicate_AST = null;
		
		try {      // for error handling
			new_predicate();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop532:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp76_AST = null;
					tmp76_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp76_AST);
					match(B_COMMA);
					new_predicate();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop532;
				}
				
			} while (true);
			}
			list_New_Predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = list_New_Predicate_AST;
	}
	
	public final void sets_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sets_declaration_AST = null;
		
		try {      // for error handling
			set_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop68:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp77_AST = null;
					tmp77_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp77_AST);
					match(B_SEMICOLON);
					set_declaration();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop68;
				}
				
			} while (true);
			}
			sets_declaration_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = sets_declaration_AST;
	}
	
	public final void set_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_declaration_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					AddMetrics();
				
			}
			MyNode tmp78_AST = null;
			tmp78_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp78_AST);
			match(B_IDENTIFIER);
			{
			switch ( LA(1)) {
			case B_EQUAL:
			{
				MyNode tmp79_AST = null;
				tmp79_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp79_AST);
				match(B_EQUAL);
				set_construction();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case B_SEMICOLON:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_VARIANT:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_DYNAMICS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			set_declaration_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = set_declaration_AST;
	}
	
	public final void set_construction() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_construction_AST = null;
		
		try {      // for error handling
			valuation_set();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop74:
			do {
				if ((_tokenSet_24.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_MULT:
					{
						MyNode tmp80_AST = null;
						tmp80_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp80_AST);
						match(B_MULT);
						break;
					}
					case B_ADD:
					{
						MyNode tmp81_AST = null;
						tmp81_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp81_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp82_AST = null;
						tmp82_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp82_AST);
						match(B_MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					valuation_set();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop74;
				}
				
			} while (true);
			}
			set_construction_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = set_construction_AST;
	}
	
	public final void valuation_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode valuation_set_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_CURLYOPEN:
			{
				MyNode tmp83_AST = null;
				tmp83_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp83_AST);
				match(B_CURLYOPEN);
				list_couple();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_CURLYCLOSE);
				valuation_set_AST = (MyNode)currentAST.root;
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
			case LITERAL_STRING:
			{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				valuation_set_AST = (MyNode)currentAST.root;
				break;
			}
			default:
				if ((_tokenSet_25.member(LA(1))) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3)))) {
					basic_value();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case B_RANGE:
					{
						MyNode tmp85_AST = null;
						tmp85_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp85_AST);
						match(B_RANGE);
						basic_value();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case B_MULT:
					case B_ADD:
					case B_MINUS:
					case B_SEMICOLON:
					case LITERAL_END:
					case LITERAL_CONSTRAINTS:
					case LITERAL_EXTENDS:
					case LITERAL_USES:
					case LITERAL_INCLUDES:
					case LITERAL_SEES:
					case LITERAL_IMPORTS:
					case LITERAL_PROMOTES:
					case LITERAL_REFINES:
					case LITERAL_CONSTANTS:
					case LITERAL_CONCRETE_CONSTANTS:
					case LITERAL_VISIBLE_CONSTANTS:
					case LITERAL_ABSTRACT_CONSTANTS:
					case LITERAL_HIDDEN_CONSTANTS:
					case LITERAL_SETS:
					case LITERAL_VALUES:
					case LITERAL_PROPERTIES:
					case LITERAL_VARIABLES:
					case LITERAL_ABSTRACT_VARIABLES:
					case LITERAL_VISIBLE_VARIABLES:
					case LITERAL_INVARIANT:
					case LITERAL_HIDDEN_VARIABLES:
					case LITERAL_CONCRETE_VARIABLES:
					case LITERAL_DEFINITIONS:
					case LITERAL_ASSERTIONS:
					case LITERAL_INITIALISATION:
					case LITERAL_VARIANT:
					case LITERAL_EVENTS:
					case LITERAL_MODALITIES:
					case LITERAL_DYNAMICS:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					valuation_set_AST = (MyNode)currentAST.root;
				}
				else if ((LA(1)==LITERAL_rec||LA(1)==LITERAL_struct) && (LA(2)==B_LPAREN) && (_tokenSet_28.member(LA(3)))) {
					is_record();
					astFactory.addASTChild(currentAST, returnAST);
					valuation_set_AST = (MyNode)currentAST.root;
				}
				else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_29.member(LA(2))) && (_tokenSet_30.member(LA(3)))) {
					MyNode tmp86_AST = null;
					tmp86_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp86_AST);
					match(B_IDENTIFIER);
					valuation_set_AST = (MyNode)currentAST.root;
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
		returnAST = valuation_set_AST;
	}
	
	public final void list_couple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_couple_AST = null;
		
		try {      // for error handling
			couple_parent();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop79:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp87_AST = null;
					tmp87_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp87_AST);
					match(B_COMMA);
					couple_parent();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop79;
				}
				
			} while (true);
			}
			list_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = list_couple_AST;
	}
	
	public final void basic_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode basic_value_AST = null;
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
				unary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				basic_value_AST = (MyNode)currentAST.root;
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
				unary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				basic_value_AST = (MyNode)currentAST.root;
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
				unary_basic_value_inverted();
				astFactory.addASTChild(currentAST, returnAST);
				basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ASTRING:
			{
				MyNode tmp88_AST = null;
				tmp88_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp88_AST);
				match(B_ASTRING);
				basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_rec:
			case LITERAL_struct:
			{
				is_record();
				astFactory.addASTChild(currentAST, returnAST);
				basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp89_AST = null;
				tmp89_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp89_AST);
				match(LITERAL_TRUE);
				basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp90_AST = null;
				tmp90_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp90_AST);
				match(LITERAL_FALSE);
				basic_value_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_value_AST;
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		returnAST = is_record_AST;
	}
	
	public final void basic_sets() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode basic_sets_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_INT:
			{
				MyNode tmp91_AST = null;
				tmp91_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp91_AST);
				match(LITERAL_INT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp92_AST = null;
				tmp92_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp92_AST);
				match(87);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp93_AST = null;
				tmp93_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp93_AST);
				match(LITERAL_INTEGER);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp94_AST = null;
				tmp94_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp94_AST);
				match(89);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp95_AST = null;
				tmp95_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp95_AST);
				match(LITERAL_BOOL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp96_AST = null;
				tmp96_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp96_AST);
				match(LITERAL_NAT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp97_AST = null;
				tmp97_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp97_AST);
				match(92);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp98_AST = null;
				tmp98_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp98_AST);
				match(LITERAL_NATURAL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp99_AST = null;
				tmp99_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp99_AST);
				match(94);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp100_AST = null;
				tmp100_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp100_AST);
				match(LITERAL_STRING);
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
				recover(ex,_tokenSet_33);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_sets_AST;
	}
	
	public final void couple_parent() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode couple_parent_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_LPAREN:
			{
				MyNode tmp101_AST = null;
				tmp101_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp101_AST);
				match(B_LPAREN);
				extended_couple();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				couple_parent_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			case B_MINUS:
			case LITERAL_TRUE:
			case LITERAL_FALSE:
			case B_NUMBER:
			{
				couple();
				astFactory.addASTChild(currentAST, returnAST);
				couple_parent_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
		returnAST = couple_parent_AST;
	}
	
	public final void extended_couple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode extended_couple_AST = null;
		
		try {      // for error handling
			a_set_value();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop84:
			do {
				if ((LA(1)==B_COMMA||LA(1)==B_MAPLET)) {
					{
					switch ( LA(1)) {
					case B_MAPLET:
					{
						MyNode tmp103_AST = null;
						tmp103_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp103_AST);
						match(B_MAPLET);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp104_AST = null;
						tmp104_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp104_AST);
						match(B_COMMA);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					a_set_value();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop84;
				}
				
			} while (true);
			}
			extended_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = extended_couple_AST;
	}
	
	public final void couple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode couple_AST = null;
		
		try {      // for error handling
			a_set_value();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop87:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp105_AST = null;
					tmp105_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp105_AST);
					match(B_MAPLET);
					a_set_value();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop87;
				}
				
			} while (true);
			}
			couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
		returnAST = couple_AST;
	}
	
	public final void a_set_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_set_value_AST = null;
		Token  b = null;
		MyNode b_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			{
				MyNode tmp106_AST = null;
				tmp106_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp106_AST);
				match(B_IDENTIFIER);
				a_set_value_AST = (MyNode)currentAST.root;
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
				MyNode tmp107_AST = null;
				tmp107_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp107_AST);
				match(B_NUMBER);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp108_AST = null;
				tmp108_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp108_AST);
				match(B_NUMBER);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp109_AST = null;
				tmp109_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp109_AST);
				match(LITERAL_TRUE);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp110_AST = null;
				tmp110_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp110_AST);
				match(LITERAL_FALSE);
				a_set_value_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
		returnAST = a_set_value_AST;
	}
	
	public final void list_valuation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_valuation_AST = null;
		
		try {      // for error handling
			set_valuation();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop92:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp111_AST = null;
					tmp111_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp111_AST);
					match(B_SEMICOLON);
					set_valuation();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop92;
				}
				
			} while (true);
			}
			list_valuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = list_valuation_AST;
	}
	
	public final void set_valuation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_valuation_AST = null;
		
		try {      // for error handling
			MyNode tmp112_AST = null;
			tmp112_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp112_AST);
			match(B_IDENTIFIER);
			MyNode tmp113_AST = null;
			tmp113_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp113_AST);
			match(B_EQUAL);
			new_set_or_constant_valuation();
			astFactory.addASTChild(currentAST, returnAST);
			set_valuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		returnAST = set_valuation_AST;
	}
	
	public final void new_set_or_constant_valuation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_set_or_constant_valuation_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_37.member(LA(1))) && (_tokenSet_36.member(LA(2)))) {
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched96 = false;
				if (((LA(1)==B_CURLYOPEN) && (_tokenSet_38.member(LA(2))) && (_tokenSet_39.member(LA(3))))) {
					int _m96 = mark();
					synPredMatched96 = true;
					inputState.guessing++;
					try {
						{
						match(B_CURLYOPEN);
						list_couple();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched96 = false;
					}
					rewind(_m96);
inputState.guessing--;
				}
				if ( synPredMatched96 ) {
					MyNode tmp114_AST = null;
					tmp114_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp114_AST);
					match(B_CURLYOPEN);
					list_couple();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_CURLYCLOSE);
					new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
				}
				else {
					boolean synPredMatched98 = false;
					if (((_tokenSet_28.member(LA(1))) && (_tokenSet_40.member(LA(2))) && (_tokenSet_41.member(LA(3))))) {
						int _m98 = mark();
						synPredMatched98 = true;
						inputState.guessing++;
						try {
							{
							bases();
							match(B_MULT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched98 = false;
						}
						rewind(_m98);
inputState.guessing--;
					}
					if ( synPredMatched98 ) {
						bases();
						astFactory.addASTChild(currentAST, returnAST);
						MyNode tmp116_AST = null;
						tmp116_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp116_AST);
						match(B_MULT);
						bases();
						astFactory.addASTChild(currentAST, returnAST);
						new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
					}
					else if ((_tokenSet_25.member(LA(1))) && (_tokenSet_42.member(LA(2))) && (_tokenSet_43.member(LA(3)))) {
						basic_value();
						astFactory.addASTChild(currentAST, returnAST);
						{
						switch ( LA(1)) {
						case B_RANGE:
						{
							MyNode tmp117_AST = null;
							tmp117_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp117_AST);
							match(B_RANGE);
							basic_value();
							astFactory.addASTChild(currentAST, returnAST);
							break;
						}
						case B_SEMICOLON:
						case LITERAL_END:
						case LITERAL_EXTENDS:
						case LITERAL_SEES:
						case LITERAL_IMPORTS:
						case LITERAL_PROMOTES:
						case LITERAL_REFINES:
						case LITERAL_CONSTANTS:
						case LITERAL_CONCRETE_CONSTANTS:
						case LITERAL_VISIBLE_CONSTANTS:
						case LITERAL_SETS:
						case LITERAL_VALUES:
						case LITERAL_PROPERTIES:
						case LITERAL_VARIABLES:
						case LITERAL_ABSTRACT_VARIABLES:
						case LITERAL_VISIBLE_VARIABLES:
						case LITERAL_INVARIANT:
						case LITERAL_HIDDEN_VARIABLES:
						case LITERAL_CONCRETE_VARIABLES:
						case LITERAL_DEFINITIONS:
						case LITERAL_ASSERTIONS:
						case LITERAL_INITIALISATION:
						case LITERAL_EVENTS:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					}}
				}
				catch (RecognitionException ex) {
					if (inputState.guessing==0) {
						reportError(ex);
						recover(ex,_tokenSet_36);
					} else {
					  throw ex;
					}
				}
				returnAST = new_set_or_constant_valuation_AST;
			}
			
	public final void bases() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode bases_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case LITERAL_STRING:
			{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				bases_AST = (MyNode)currentAST.root;
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
			case LITERAL_struct:
			{
				basic_value();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case B_RANGE:
				{
					MyNode tmp118_AST = null;
					tmp118_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp118_AST);
					match(B_RANGE);
					arithmetic_0();
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
				case B_INSET:
				case B_NOTINSET:
				case LITERAL_END:
				case LITERAL_CONSTRAINTS:
				case LITERAL_EXTENDS:
				case LITERAL_USES:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_IMPORTS:
				case LITERAL_PROMOTES:
				case LITERAL_REFINES:
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				case LITERAL_SETS:
				case LITERAL_VALUES:
				case LITERAL_PROPERTIES:
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				case LITERAL_INVARIANT:
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				case LITERAL_DEFINITIONS:
				case LITERAL_ASSERTIONS:
				case LITERAL_INITIALISATION:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_ELSE:
				case LITERAL_WHEN:
				case LITERAL_VARIANT:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_DYNAMICS:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SEQEMPTY:
			{
				MyNode tmp119_AST = null;
				tmp119_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp119_AST);
				match(B_SEQEMPTY);
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				MyNode tmp120_AST = null;
				tmp120_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp120_AST);
				match(B_BRACKOPEN);
				listPredicate();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_BRACKCLOSE);
				bases_AST = (MyNode)currentAST.root;
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
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_EMPTYSET:
			{
				MyNode tmp123_AST = null;
				tmp123_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp123_AST);
				match(B_EMPTYSET);
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				MyNode tmp124_AST = null;
				tmp124_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp124_AST);
				match(B_CURLYOPEN);
				value_set();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_CURLYCLOSE);
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_FORALL:
			case B_EXISTS:
			{
				quantification();
				astFactory.addASTChild(currentAST, returnAST);
				bases_AST = (MyNode)currentAST.root;
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
				bases_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
		returnAST = bases_AST;
	}
	
	public final void set_interval_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_interval_value_AST = null;
		
		try {      // for error handling
			MyNode tmp126_AST = null;
			tmp126_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp126_AST);
			match(B_IDENTIFIER);
			MyNode tmp127_AST = null;
			tmp127_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp127_AST);
			match(B_EQUAL);
			interval_declaration();
			astFactory.addASTChild(currentAST, returnAST);
			set_interval_value_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = set_interval_value_AST;
	}
	
	public final void interval_declaration() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode interval_declaration_AST = null;
		
		try {      // for error handling
			basic_value();
			astFactory.addASTChild(currentAST, returnAST);
			MyNode tmp128_AST = null;
			tmp128_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp128_AST);
			match(B_RANGE);
			basic_value();
			astFactory.addASTChild(currentAST, returnAST);
			interval_declaration_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = interval_declaration_AST;
	}
	
	public final void set_rename_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_rename_value_AST = null;
		
		try {      // for error handling
			MyNode tmp129_AST = null;
			tmp129_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp129_AST);
			match(B_IDENTIFIER);
			MyNode tmp130_AST = null;
			tmp130_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp130_AST);
			match(B_EQUAL);
			MyNode tmp131_AST = null;
			tmp131_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp131_AST);
			match(B_IDENTIFIER);
			set_rename_value_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = set_rename_value_AST;
	}
	
	public final void definitions_mch_bis() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_mch_bis_AST = null;
		
		try {      // for error handling
			match(LITERAL_DEFINITIONS);
			list_def_mch();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_mch_bis_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_mch_bis_AST;
	}
	
	public final void list_def_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_def_mch_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			definition_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop113:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_SEMICOLON);
					if ( inputState.guessing==0 ) {
						
							c_AST.setType(LIST_DEF);
						
					}
					definition_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop113;
				}
				
			} while (true);
			}
			list_def_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = list_def_mch_AST;
	}
	
	public final void definition_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definition_mch_AST = null;
		Token  f = null;
		MyNode f_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			{
				paramName();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp133_AST = null;
				tmp133_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp133_AST);
				match(B_DOUBLE_EQUAL);
				formalText_mch();
				astFactory.addASTChild(currentAST, returnAST);
				definition_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ASTRING:
			{
				f = LT(1);
				f_AST = (MyNode)astFactory.create(f);
				match(B_ASTRING);
				if ( inputState.guessing==0 ) {
					definition_mch_AST = (MyNode)currentAST.root;
					
						// create lexer to handle include
						String name = f.getText();
					
								try {
									BLexer lexer = new BLexer(new FileInputStream(new File(name)));
					
									// Pour mettre en place notre definition des noeuds....
									lexer.setTokenObjectClass("MyToken");
					
									BParser parser = new BParser(lexer);
					
									// Pour mettre en place notre definition des noeuds....
								      	parser.setASTNodeType(MyNode.class.getName());
								        MyNode.setTokenVocabulary("BTokenTypes");
					
									// On appel la lecture d'un fichier de definition de type MACHINE
									parser.definitions_mch_bis();
					
									// On recupere l'arbre syntaxique 
									AST aa = parser.getAST();
					
									// On contruit le nouvel arbre syntaxique
									definition_mch_AST = (MyNode) aa;
					
								}                                   
								catch (FileNotFoundException fnf) 
								{
							      	System.err.println("Exception : cannot find file "+name);
									definition_mch_AST = (MyNode) f_AST ;
					}
					
					currentAST.root = definition_mch_AST;
					currentAST.child = definition_mch_AST!=null &&definition_mch_AST.getFirstChild()!=null ?
						definition_mch_AST.getFirstChild() : definition_mch_AST;
					currentAST.advanceChildToEnd();
				}
				definition_mch_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
		returnAST = definition_mch_AST;
	}
	
	public final void formalText_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode formalText_mch_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_46.member(LA(1))) && (_tokenSet_47.member(LA(2))) && (_tokenSet_48.member(LA(3)))) {
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_mch_AST = (MyNode)currentAST.root;
					
						formalText_mch_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(EXP_DEF,"EXP_DEF")).add(formalText_mch_AST));
					
					currentAST.root = formalText_mch_AST;
					currentAST.child = formalText_mch_AST!=null &&formalText_mch_AST.getFirstChild()!=null ?
						formalText_mch_AST.getFirstChild() : formalText_mch_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_mch_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_49.member(LA(1))) && (_tokenSet_50.member(LA(2))) && (_tokenSet_51.member(LA(3)))) {
				substitution_mch();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_mch_AST = (MyNode)currentAST.root;
					
						formalText_mch_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(SUBST_DEF,"SUBST_DEF")).add(formalText_mch_AST));	
					
					currentAST.root = formalText_mch_AST;
					currentAST.child = formalText_mch_AST!=null &&formalText_mch_AST.getFirstChild()!=null ?
						formalText_mch_AST.getFirstChild() : formalText_mch_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_mch_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
		returnAST = formalText_mch_AST;
	}
	
	public final void substitution_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_mch_AST = null;
		
		try {      // for error handling
			parallele_mch();
			astFactory.addASTChild(currentAST, returnAST);
			substitution_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_mch_AST;
	}
	
	public final void definitions_ref_bis() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_ref_bis_AST = null;
		
		try {      // for error handling
			match(LITERAL_DEFINITIONS);
			list_def_ref();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_ref_bis_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_ref_bis_AST;
	}
	
	public final void list_def_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_def_ref_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			definition_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop120:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_SEMICOLON);
					if ( inputState.guessing==0 ) {
						c_AST.setType(LIST_DEF);
					}
					definition_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop120;
				}
				
			} while (true);
			}
			list_def_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = list_def_ref_AST;
	}
	
	public final void definition_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definition_ref_AST = null;
		Token  f = null;
		MyNode f_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			{
				paramName();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp135_AST = null;
				tmp135_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp135_AST);
				match(B_DOUBLE_EQUAL);
				formalText_ref();
				astFactory.addASTChild(currentAST, returnAST);
				definition_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ASTRING:
			{
				f = LT(1);
				f_AST = (MyNode)astFactory.create(f);
				match(B_ASTRING);
				if ( inputState.guessing==0 ) {
					definition_ref_AST = (MyNode)currentAST.root;
					
						// create lexer to handle include
						String name = f.getText();
					
								try {
									BLexer lexer = new BLexer(new FileInputStream(new File(name)));
					
									// Pour mettre en place notre definition des noeuds....
									lexer.setTokenObjectClass("MyToken");
					
									BParser parser = new BParser(lexer);
					
									// Pour mettre en place notre definition des noeuds....
								      	parser.setASTNodeType(MyNode.class.getName());
								        MyNode.setTokenVocabulary("BTokenTypes");
					
									// On appel la lecture d'un fichier de definition de type MACHINE
									parser.definitions_ref_bis();
					
									// On recupere l'arbre syntaxique 
									AST aa = parser.getAST();
					
									// On contruit le nouvel arbre syntaxique
									definition_ref_AST = (MyNode) aa;
					
								}                                   
								catch (FileNotFoundException fnf) 
								{
					System.err.println("Exception : cannot find file "+name);
									definition_ref_AST = (MyNode) f_AST ;
					}
					
					currentAST.root = definition_ref_AST;
					currentAST.child = definition_ref_AST!=null &&definition_ref_AST.getFirstChild()!=null ?
						definition_ref_AST.getFirstChild() : definition_ref_AST;
					currentAST.advanceChildToEnd();
				}
				definition_ref_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
		returnAST = definition_ref_AST;
	}
	
	public final void formalText_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode formalText_ref_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_46.member(LA(1))) && (_tokenSet_54.member(LA(2))) && (_tokenSet_55.member(LA(3)))) {
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_ref_AST = (MyNode)currentAST.root;
					
						formalText_ref_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(EXP_DEF,"EXP_DEF")).add(formalText_ref_AST));	
					
					currentAST.root = formalText_ref_AST;
					currentAST.child = formalText_ref_AST!=null &&formalText_ref_AST.getFirstChild()!=null ?
						formalText_ref_AST.getFirstChild() : formalText_ref_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_ref_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_56.member(LA(1))) && (_tokenSet_57.member(LA(2))) && (_tokenSet_58.member(LA(3)))) {
				substitution_ref();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_ref_AST = (MyNode)currentAST.root;
					
					formalText_ref_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(SUBST_DEF,"SUBST_DEF")).add(formalText_ref_AST));	   
					
					currentAST.root = formalText_ref_AST;
					currentAST.child = formalText_ref_AST!=null &&formalText_ref_AST.getFirstChild()!=null ?
						formalText_ref_AST.getFirstChild() : formalText_ref_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_ref_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
		returnAST = formalText_ref_AST;
	}
	
	public final void substitution_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_ref_AST = null;
		
		try {      // for error handling
			subst_ref();
			astFactory.addASTChild(currentAST, returnAST);
			substitution_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_ref_AST;
	}
	
	public final void definitions_imp_bis() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_imp_bis_AST = null;
		
		try {      // for error handling
			match(LITERAL_DEFINITIONS);
			list_def_imp();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_imp_bis_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_imp_bis_AST;
	}
	
	public final void list_def_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_def_imp_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			definition_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop127:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_SEMICOLON);
					if ( inputState.guessing==0 ) {
						
							c_AST.setType(LIST_DEF);
						
					}
					definition_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop127;
				}
				
			} while (true);
			}
			list_def_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = list_def_imp_AST;
	}
	
	public final void definition_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definition_imp_AST = null;
		Token  f = null;
		MyNode f_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case B_IDENTIFIER:
			{
				paramName();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp137_AST = null;
				tmp137_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp137_AST);
				match(B_DOUBLE_EQUAL);
				formalText_imp();
				astFactory.addASTChild(currentAST, returnAST);
				definition_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ASTRING:
			{
				f = LT(1);
				f_AST = (MyNode)astFactory.create(f);
				match(B_ASTRING);
				if ( inputState.guessing==0 ) {
					definition_imp_AST = (MyNode)currentAST.root;
					
						// create lexer to handle include
						String name = f.getText();
					
								try {
									BLexer lexer = new BLexer(new FileInputStream(new File(name)));
					
									// Pour mettre en place notre definition des noeuds....
									lexer.setTokenObjectClass("MyToken");
					
									BParser parser = new BParser(lexer);
					
									// Pour mettre en place notre definition des noeuds....
								      	parser.setASTNodeType(MyNode.class.getName());
								        MyNode.setTokenVocabulary("BTokenTypes");
					
									// On appel la lecture d'un fichier de definition de type MACHINE
									parser.definitions_imp_bis();
					
									// On recupere l'arbre syntaxique 
									AST aa = parser.getAST();
					
									// On contruit le nouvel arbre syntaxique
									definition_imp_AST = (MyNode) aa;
					
								}                                   
								catch (FileNotFoundException fnf) 
								{
					System.err.println("cannot find file "+name);
					definition_imp_AST = (MyNode) f_AST ;
					}       
					
					currentAST.root = definition_imp_AST;
					currentAST.child = definition_imp_AST!=null &&definition_imp_AST.getFirstChild()!=null ?
						definition_imp_AST.getFirstChild() : definition_imp_AST;
					currentAST.advanceChildToEnd();
				}
				definition_imp_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_59);
			} else {
			  throw ex;
			}
		}
		returnAST = definition_imp_AST;
	}
	
	public final void formalText_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode formalText_imp_AST = null;
		
		try {      // for error handling
			if ((_tokenSet_46.member(LA(1))) && (_tokenSet_60.member(LA(2))) && (_tokenSet_61.member(LA(3)))) {
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_imp_AST = (MyNode)currentAST.root;
					
						formalText_imp_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(EXP_DEF,"EXP_DEF")).add(formalText_imp_AST));	
					
					currentAST.root = formalText_imp_AST;
					currentAST.child = formalText_imp_AST!=null &&formalText_imp_AST.getFirstChild()!=null ?
						formalText_imp_AST.getFirstChild() : formalText_imp_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_imp_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_62.member(LA(1))) && (_tokenSet_63.member(LA(2))) && (_tokenSet_64.member(LA(3)))) {
				substitution_imp();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					formalText_imp_AST = (MyNode)currentAST.root;
					
					formalText_imp_AST = (MyNode)astFactory.make( (new ASTArray(2)).add((MyNode)astFactory.create(SUBST_DEF,"SUBST_DEF")).add(formalText_imp_AST));
					
					currentAST.root = formalText_imp_AST;
					currentAST.child = formalText_imp_AST!=null &&formalText_imp_AST.getFirstChild()!=null ?
						formalText_imp_AST.getFirstChild() : formalText_imp_AST;
					currentAST.advanceChildToEnd();
				}
				formalText_imp_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_59);
			} else {
			  throw ex;
			}
		}
		returnAST = formalText_imp_AST;
	}
	
	public final void substitution_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_imp_AST = null;
		
		try {      // for error handling
			subst_imp();
			astFactory.addASTChild(currentAST, returnAST);
			substitution_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_59);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_imp_AST;
	}
	
	public final void list_assertions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_assertions_AST = null;
		
		try {      // for error handling
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop133:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp138_AST = null;
					tmp138_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp138_AST);
					match(B_SEMICOLON);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop133;
				}
				
			} while (true);
			}
			list_assertions_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = list_assertions_AST;
	}
	
	public final void parallele_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parallele_ref_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		Token  d = null;
		MyNode d_AST = null;
		
		try {      // for error handling
			subst_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop162:
			do {
				if ((LA(1)==B_SEMICOLON||LA(1)==B_PARALLEL)) {
					{
					switch ( LA(1)) {
					case B_PARALLEL:
					{
						c = LT(1);
						c_AST = (MyNode)astFactory.create(c);
						astFactory.makeASTRoot(currentAST, c_AST);
						match(B_PARALLEL);
						if ( inputState.guessing==0 ) {
							c_AST.setType(PARALLEL  );
						}
						break;
					}
					case B_SEMICOLON:
					{
						d = LT(1);
						d_AST = (MyNode)astFactory.create(d);
						astFactory.makeASTRoot(currentAST, d_AST);
						match(B_SEMICOLON);
						if ( inputState.guessing==0 ) {
							d_AST.setType(SEQUENTIAL);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					subst_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop162;
				}
				
			} while (true);
			}
			parallele_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_65);
			} else {
			  throw ex;
			}
		}
		returnAST = parallele_ref_AST;
	}
	
	public final void sequential() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sequential_AST = null;
		Token  d = null;
		MyNode d_AST = null;
		
		try {      // for error handling
			subst_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop167:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					d = LT(1);
					d_AST = (MyNode)astFactory.create(d);
					astFactory.makeASTRoot(currentAST, d_AST);
					match(B_SEMICOLON);
					if ( inputState.guessing==0 ) {
						
						d_AST.setType(SEQUENTIAL);
						
					}
					subst_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop167;
				}
				
			} while (true);
			}
			sequential_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_66);
			} else {
			  throw ex;
			}
		}
		returnAST = sequential_AST;
	}
	
	public final void operations_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operations_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp139_AST = null;
			tmp139_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp139_AST);
			match(LITERAL_OPERATIONS);
			listOperationMch();
			astFactory.addASTChild(currentAST, returnAST);
			operations_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = operations_mch_AST;
	}
	
	public final void operationMch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operationMch_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			operationHeader();
			astFactory.addASTChild(currentAST, returnAST);
			c = LT(1);
			c_AST = (MyNode)astFactory.create(c);
			astFactory.makeASTRoot(currentAST, c_AST);
			match(B_EQUAL);
			if ( inputState.guessing==0 ) {
				
					c_AST.setType(OP_DEF);
				
			}
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			operationMch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
		returnAST = operationMch_AST;
	}
	
	public final void operationHeader() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operationHeader_AST = null;
		Token  bb = null;
		MyNode bb_AST = null;
		MyNode pp_AST = null;
		
		try {      // for error handling
			if ((LA(1)==B_IDENTIFIER) && (LA(2)==B_EQUAL||LA(2)==B_LPAREN)) {
				if ( inputState.guessing==0 ) {
					
						ChooseMetrics(mservices);
					
				}
				paramName();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						ChooseMetrics(unknown);
					
				}
				operationHeader_AST = (MyNode)currentAST.root;
			}
			else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_67.member(LA(2)))) {
				listIdentifier();
				astFactory.addASTChild(currentAST, returnAST);
				bb = LT(1);
				bb_AST = (MyNode)astFactory.create(bb);
				astFactory.makeASTRoot(currentAST, bb_AST);
				match(B_OUT);
				if ( inputState.guessing==0 ) {
					
						ChooseMetrics(mservices);
					
				}
				paramName();
				pp_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						// Memorization pour introduction dans la table des symboles
						// ce n'est pas simple car les parametres de sortie sont introduit 
						// avant le nom de l'operation
						bb_AST.memorizeOpname( pp_AST.getOpname() );
						ChooseMetrics(unknown);
					
				}
				operationHeader_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_68);
			} else {
			  throw ex;
			}
		}
		returnAST = operationHeader_AST;
	}
	
	public final void operations_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operations_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp140_AST = null;
			tmp140_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp140_AST);
			match(LITERAL_OPERATIONS);
			listOperationRef();
			astFactory.addASTChild(currentAST, returnAST);
			operations_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = operations_ref_AST;
	}
	
	public final void operationRef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operationRef_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			operationHeader();
			astFactory.addASTChild(currentAST, returnAST);
			c = LT(1);
			c_AST = (MyNode)astFactory.create(c);
			astFactory.makeASTRoot(currentAST, c_AST);
			match(B_EQUAL);
			if ( inputState.guessing==0 ) {
				
					c_AST.setType(OP_DEF);
				
			}
			substitution_ref();
			astFactory.addASTChild(currentAST, returnAST);
			operationRef_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
		returnAST = operationRef_AST;
	}
	
	public final void operations_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operations_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp141_AST = null;
			tmp141_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp141_AST);
			match(LITERAL_OPERATIONS);
			listOperationImp();
			astFactory.addASTChild(currentAST, returnAST);
			operations_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = operations_imp_AST;
	}
	
	public final void operationImp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operationImp_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			operationHeader();
			astFactory.addASTChild(currentAST, returnAST);
			c = LT(1);
			c_AST = (MyNode)astFactory.create(c);
			astFactory.makeASTRoot(currentAST, c_AST);
			match(B_EQUAL);
			if ( inputState.guessing==0 ) {
				
					c_AST.setType(OP_DEF);
				
			}
			substitution_imp();
			astFactory.addASTChild(currentAST, returnAST);
			operationImp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_59);
			} else {
			  throw ex;
			}
		}
		returnAST = operationImp_AST;
	}
	
	public final void parallele_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parallele_mch_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			subst_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop156:
			do {
				if ((LA(1)==B_PARALLEL)) {
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_PARALLEL);
					if ( inputState.guessing==0 ) {
						
							c_AST.setType(PARALLEL);
						
					}
					subst_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop156;
				}
				
			} while (true);
			}
			parallele_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
		returnAST = parallele_mch_AST;
	}
	
	public final void subst_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode subst_mch_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_skip:
			{
				identite();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BEGIN:
			{
				substitution_block_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PRE:
			{
				substitution_precondition_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ASSERT:
			{
				assert_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IF:
			{
				anif_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CHOICE:
			{
				choice_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ANY:
			{
				substitution_unbounded_choice_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SELECT:
			{
				substitution_selection_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_LET:
			{
				let_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CASE:
			{
				case_mch();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				simple_affect_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_mch_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = subst_mch_AST;
	}
	
	public final void identite() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode identite_AST = null;
		
		try {      // for error handling
			MyNode tmp142_AST = null;
			tmp142_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp142_AST);
			match(LITERAL_skip);
			identite_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_70);
			} else {
			  throw ex;
			}
		}
		returnAST = identite_AST;
	}
	
	public final void substitution_block_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_block_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp143_AST = null;
			tmp143_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp143_AST);
			match(LITERAL_BEGIN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_block_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_block_mch_AST;
	}
	
	public final void substitution_precondition_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_precondition_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp145_AST = null;
			tmp145_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp145_AST);
			match(LITERAL_PRE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_precondition_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_precondition_mch_AST;
	}
	
	public final void assert_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assert_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp148_AST = null;
			tmp148_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp148_AST);
			match(LITERAL_ASSERT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			assert_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = assert_mch_AST;
	}
	
	public final void anif_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode anif_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp151_AST = null;
			tmp151_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp151_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop180:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop180;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				e_branch_mch();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			anif_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = anif_mch_AST;
	}
	
	public final void choice_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode choice_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp153_AST = null;
			tmp153_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp153_AST);
			match(LITERAL_CHOICE);
			list_or_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			choice_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = choice_mch_AST;
	}
	
	public final void substitution_unbounded_choice_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_unbounded_choice_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp155_AST = null;
			tmp155_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp155_AST);
			match(LITERAL_ANY);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_WHERE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_unbounded_choice_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_unbounded_choice_mch_AST;
	}
	
	public final void substitution_selection_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_selection_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp159_AST = null;
			tmp159_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp159_AST);
			match(LITERAL_SELECT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop229:
			do {
				if ((LA(1)==LITERAL_WHEN)) {
					when_branch_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop229;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				e_branch_mch();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			substitution_selection_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_selection_mch_AST;
	}
	
	public final void let_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode let_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp162_AST = null;
			tmp162_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp162_AST);
			match(LITERAL_LET);
			list_identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_BE);
			list_equal();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_IN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			let_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = let_mch_AST;
	}
	
	public final void case_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode case_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp166_AST = null;
			tmp166_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp166_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop206:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop206;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				branche_else_mch();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			match(LITERAL_END);
			case_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_69);
			} else {
			  throw ex;
			}
		}
		returnAST = case_mch_AST;
	}
	
	public final void simple_affect_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode simple_affect_ref_AST = null;
		MyNode l1_AST = null;
		MyNode l2_AST = null;
		MyNode l3_AST = null;
		Token  d = null;
		MyNode d_AST = null;
		MyNode l4_AST = null;
		
		try {      // for error handling
			boolean synPredMatched274 = false;
			if (((LA(1)==B_IDENTIFIER) && (_tokenSet_71.member(LA(2))) && (_tokenSet_72.member(LA(3))))) {
				int _m274 = mark();
				synPredMatched274 = true;
				inputState.guessing++;
				try {
					{
					list_func_call();
					match(B_SIMPLESUBST);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched274 = false;
				}
				rewind(_m274);
inputState.guessing--;
			}
			if ( synPredMatched274 ) {
				list_func_call();
				l1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp170_AST = null;
				tmp170_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp170_AST);
				match(B_SIMPLESUBST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						RU1(l1_AST);
					
				}
				simple_affect_ref_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched276 = false;
				if (((LA(1)==B_IDENTIFIER) && (_tokenSet_73.member(LA(2))) && (_tokenSet_72.member(LA(3))))) {
					int _m276 = mark();
					synPredMatched276 = true;
					inputState.guessing++;
					try {
						{
						list_func_call();
						match(B_OUT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched276 = false;
					}
					rewind(_m276);
inputState.guessing--;
				}
				if ( synPredMatched276 ) {
					list_func_call();
					l2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					MyNode tmp171_AST = null;
					tmp171_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp171_AST);
					match(B_OUT);
					func_call();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							RU1(l2_AST);
						
					}
					simple_affect_ref_AST = (MyNode)currentAST.root;
				}
				else {
					boolean synPredMatched278 = false;
					if (((LA(1)==B_IDENTIFIER) && (_tokenSet_74.member(LA(2))) && (_tokenSet_72.member(LA(3))))) {
						int _m278 = mark();
						synPredMatched278 = true;
						inputState.guessing++;
						try {
							{
							list_func_call();
							match(B_INSET);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched278 = false;
						}
						rewind(_m278);
inputState.guessing--;
					}
					if ( synPredMatched278 ) {
						list_func_call();
						l3_AST = (MyNode)returnAST;
						astFactory.addASTChild(currentAST, returnAST);
						d = LT(1);
						d_AST = (MyNode)astFactory.create(d);
						astFactory.makeASTRoot(currentAST, d_AST);
						match(B_INSET);
						if ( inputState.guessing==0 ) {
							d_AST.setType(INSET);
						}
						match(B_LPAREN);
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						if ( inputState.guessing==0 ) {
							
								RU1(l3_AST);
							
						}
						simple_affect_ref_AST = (MyNode)currentAST.root;
					}
					else {
						boolean synPredMatched280 = false;
						if (((LA(1)==B_IDENTIFIER) && (_tokenSet_75.member(LA(2))) && (_tokenSet_46.member(LA(3))))) {
							int _m280 = mark();
							synPredMatched280 = true;
							inputState.guessing++;
							try {
								{
								list_func_call();
								match(B_BECOME_ELEM);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched280 = false;
							}
							rewind(_m280);
inputState.guessing--;
						}
						if ( synPredMatched280 ) {
							list_func_call();
							l4_AST = (MyNode)returnAST;
							astFactory.addASTChild(currentAST, returnAST);
							MyNode tmp174_AST = null;
							tmp174_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp174_AST);
							match(B_BECOME_ELEM);
							expression();
							astFactory.addASTChild(currentAST, returnAST);
							if ( inputState.guessing==0 ) {
								
									RU1(l4_AST);
								
							}
							simple_affect_ref_AST = (MyNode)currentAST.root;
						}
						else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_76.member(LA(2))) && (_tokenSet_77.member(LA(3)))) {
							a_func_call_quoted();
							astFactory.addASTChild(currentAST, returnAST);
							simple_affect_ref_AST = (MyNode)currentAST.root;
						}
						else {
							throw new NoViableAltException(LT(1), getFilename());
						}
						}}}
					}
					catch (RecognitionException ex) {
						if (inputState.guessing==0) {
							reportError(ex);
							recover(ex,_tokenSet_78);
						} else {
						  throw ex;
						}
					}
					returnAST = simple_affect_ref_AST;
				}
				
	public final void subst_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode subst_ref_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_skip:
			{
				identite();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BEGIN:
			{
				substitution_block_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_PRE:
			{
				substitution_precondition_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ASSERT:
			{
				assert_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IF:
			{
				anif_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CHOICE:
			{
				choice_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ANY:
			{
				substitution_unbounded_choice_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SELECT:
			{
				substitution_selection_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CASE:
			{
				case_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_LET:
			{
				let_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				simple_affect_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VAR:
			{
				var_ref();
				astFactory.addASTChild(currentAST, returnAST);
				subst_ref_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = subst_ref_AST;
	}
	
	public final void substitution_block_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_block_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp175_AST = null;
			tmp175_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp175_AST);
			match(LITERAL_BEGIN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_block_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_block_ref_AST;
	}
	
	public final void substitution_precondition_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_precondition_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp177_AST = null;
			tmp177_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp177_AST);
			match(LITERAL_PRE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_precondition_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_precondition_ref_AST;
	}
	
	public final void assert_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assert_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp180_AST = null;
			tmp180_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp180_AST);
			match(LITERAL_ASSERT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			assert_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = assert_ref_AST;
	}
	
	public final void anif_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode anif_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp183_AST = null;
			tmp183_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp183_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop186:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop186;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				e_branch_ref();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			anif_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = anif_ref_AST;
	}
	
	public final void choice_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode choice_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp185_AST = null;
			tmp185_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp185_AST);
			match(LITERAL_CHOICE);
			list_or_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			choice_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = choice_ref_AST;
	}
	
	public final void substitution_unbounded_choice_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_unbounded_choice_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp187_AST = null;
			tmp187_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp187_AST);
			match(LITERAL_ANY);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_WHERE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_unbounded_choice_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_unbounded_choice_ref_AST;
	}
	
	public final void substitution_selection_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_selection_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp191_AST = null;
			tmp191_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp191_AST);
			match(LITERAL_SELECT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop235:
			do {
				if ((LA(1)==LITERAL_WHEN)) {
					when_branch_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop235;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				e_branch_ref();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			substitution_selection_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_selection_ref_AST;
	}
	
	public final void case_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode case_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp194_AST = null;
			tmp194_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp194_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop213:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop213;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				branche_else_ref();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			match(LITERAL_END);
			case_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = case_ref_AST;
	}
	
	public final void let_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode let_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp198_AST = null;
			tmp198_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp198_AST);
			match(LITERAL_LET);
			list_identifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_BE);
			list_equal();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_IN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			let_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = let_ref_AST;
	}
	
	public final void var_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode var_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp202_AST = null;
			tmp202_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp202_AST);
			match(LITERAL_VAR);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_IN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			var_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = var_ref_AST;
	}
	
	public final void subst_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode subst_imp_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_skip:
			{
				identite();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BEGIN:
			{
				substitution_block_imp();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_ASSERT:
			{
				assert_imp();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_IF:
			{
				anif_imp();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_CASE:
			{
				case_imp();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_VAR:
			{
				var_imp();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_WHILE:
			{
				while_loop();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				simple_affect();
				astFactory.addASTChild(currentAST, returnAST);
				subst_imp_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = subst_imp_AST;
	}
	
	public final void substitution_block_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_block_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp205_AST = null;
			tmp205_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp205_AST);
			match(LITERAL_BEGIN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_block_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_block_imp_AST;
	}
	
	public final void assert_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assert_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp207_AST = null;
			tmp207_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp207_AST);
			match(LITERAL_ASSERT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			assert_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = assert_imp_AST;
	}
	
	public final void anif_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode anif_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp210_AST = null;
			tmp210_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp210_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop192:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop192;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				e_branch_imp();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			anif_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = anif_imp_AST;
	}
	
	public final void case_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode case_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp212_AST = null;
			tmp212_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp212_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop220:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop220;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_ELSE:
			{
				branche_else_imp();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_END);
			match(LITERAL_END);
			case_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = case_imp_AST;
	}
	
	public final void var_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode var_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp216_AST = null;
			tmp216_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp216_AST);
			match(LITERAL_VAR);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_IN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			var_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = var_imp_AST;
	}
	
	public final void while_loop() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode while_loop_AST = null;
		
		try {      // for error handling
			MyNode tmp219_AST = null;
			tmp219_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp219_AST);
			match(LITERAL_WHILE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_DO);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			variant_or_no();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			while_loop_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_80);
			} else {
			  throw ex;
			}
		}
		returnAST = while_loop_AST;
	}
	
	public final void simple_affect() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode simple_affect_AST = null;
		MyNode l1_AST = null;
		MyNode l2_AST = null;
		
		try {      // for error handling
			boolean synPredMatched269 = false;
			if (((LA(1)==B_IDENTIFIER) && (_tokenSet_71.member(LA(2))) && (_tokenSet_72.member(LA(3))))) {
				int _m269 = mark();
				synPredMatched269 = true;
				inputState.guessing++;
				try {
					{
					list_func_call();
					match(B_SIMPLESUBST);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched269 = false;
				}
				rewind(_m269);
inputState.guessing--;
			}
			if ( synPredMatched269 ) {
				list_func_call();
				l1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp222_AST = null;
				tmp222_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp222_AST);
				match(B_SIMPLESUBST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						RU1(l1_AST);
					
				}
				simple_affect_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched271 = false;
				if (((LA(1)==B_IDENTIFIER) && (_tokenSet_73.member(LA(2))) && (_tokenSet_72.member(LA(3))))) {
					int _m271 = mark();
					synPredMatched271 = true;
					inputState.guessing++;
					try {
						{
						list_func_call();
						match(B_OUT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched271 = false;
					}
					rewind(_m271);
inputState.guessing--;
				}
				if ( synPredMatched271 ) {
					list_func_call();
					l2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					MyNode tmp223_AST = null;
					tmp223_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp223_AST);
					match(B_OUT);
					func_call();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							RU1(l2_AST);
						
					}
					simple_affect_AST = (MyNode)currentAST.root;
				}
				else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_81.member(LA(2))) && (_tokenSet_82.member(LA(3)))) {
					a_func_call_quoted();
					astFactory.addASTChild(currentAST, returnAST);
					simple_affect_AST = (MyNode)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_80);
				} else {
				  throw ex;
				}
			}
			returnAST = simple_affect_AST;
		}
		
	public final void then_branch_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode then_branch_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp224_AST = null;
			tmp224_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp224_AST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = then_branch_mch_AST;
	}
	
	public final void elsif_branch_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode elsif_branch_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp225_AST = null;
			tmp225_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp225_AST);
			match(LITERAL_ELSIF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			elsif_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = elsif_branch_mch_AST;
	}
	
	public final void e_branch_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode e_branch_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp227_AST = null;
			tmp227_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp227_AST);
			match(LITERAL_ELSE);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = e_branch_mch_AST;
	}
	
	public final void then_branch_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode then_branch_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp228_AST = null;
			tmp228_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp228_AST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = then_branch_ref_AST;
	}
	
	public final void elsif_branch_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode elsif_branch_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp229_AST = null;
			tmp229_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp229_AST);
			match(LITERAL_ELSIF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			elsif_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = elsif_branch_ref_AST;
	}
	
	public final void e_branch_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode e_branch_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp231_AST = null;
			tmp231_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp231_AST);
			match(LITERAL_ELSE);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = e_branch_ref_AST;
	}
	
	public final void then_branch_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode then_branch_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp232_AST = null;
			tmp232_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp232_AST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = then_branch_imp_AST;
	}
	
	public final void elsif_branch_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode elsif_branch_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp233_AST = null;
			tmp233_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp233_AST);
			match(LITERAL_ELSIF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			elsif_branch_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_83);
			} else {
			  throw ex;
			}
		}
		returnAST = elsif_branch_imp_AST;
	}
	
	public final void e_branch_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode e_branch_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp235_AST = null;
			tmp235_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp235_AST);
			match(LITERAL_ELSE);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = e_branch_imp_AST;
	}
	
	public final void list_or_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_or_mch_AST = null;
		
		try {      // for error handling
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop199:
			do {
				if ((LA(1)==LITERAL_OR)) {
					MyNode tmp236_AST = null;
					tmp236_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp236_AST);
					match(LITERAL_OR);
					substitution_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop199;
				}
				
			} while (true);
			}
			list_or_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = list_or_mch_AST;
	}
	
	public final void list_or_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_or_ref_AST = null;
		
		try {      // for error handling
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop203:
			do {
				if ((LA(1)==LITERAL_OR)) {
					MyNode tmp237_AST = null;
					tmp237_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp237_AST);
					match(LITERAL_OR);
					parallele_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop203;
				}
				
			} while (true);
			}
			list_or_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = list_or_ref_AST;
	}
	
	public final void branche_either_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_either_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp238_AST = null;
			tmp238_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp238_AST);
			match(LITERAL_EITHER);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			branche_either_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_either_mch_AST;
	}
	
	public final void branche_or_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_or_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp240_AST = null;
			tmp240_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp240_AST);
			match(LITERAL_OR);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			branche_or_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_or_mch_AST;
	}
	
	public final void branche_else_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_else_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp242_AST = null;
			tmp242_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp242_AST);
			match(LITERAL_ELSE);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_else_mch_AST;
	}
	
	public final void branche_either_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_either_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp243_AST = null;
			tmp243_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp243_AST);
			match(LITERAL_EITHER);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			branche_either_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_either_ref_AST;
	}
	
	public final void branche_or_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_or_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp245_AST = null;
			tmp245_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp245_AST);
			match(LITERAL_OR);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			branche_or_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_or_ref_AST;
	}
	
	public final void branche_else_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_else_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp247_AST = null;
			tmp247_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp247_AST);
			match(LITERAL_ELSE);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_else_ref_AST;
	}
	
	public final void branche_either_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_either_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp248_AST = null;
			tmp248_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp248_AST);
			match(LITERAL_EITHER);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			branche_either_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_either_imp_AST;
	}
	
	public final void branche_or_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_or_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp250_AST = null;
			tmp250_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp250_AST);
			match(LITERAL_OR);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			branche_or_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_85);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_or_imp_AST;
	}
	
	public final void branche_else_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode branche_else_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp252_AST = null;
			tmp252_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp252_AST);
			match(LITERAL_ELSE);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = branche_else_imp_AST;
	}
	
	public final void when_branch_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode when_branch_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp253_AST = null;
			tmp253_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp253_AST);
			match(LITERAL_WHEN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			when_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = when_branch_mch_AST;
	}
	
	public final void when_branch_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode when_branch_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp255_AST = null;
			tmp255_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp255_AST);
			match(LITERAL_WHEN);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			when_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_86);
			} else {
			  throw ex;
			}
		}
		returnAST = when_branch_ref_AST;
	}
	
	public final void list_identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_identifier_AST = null;
		
		try {      // for error handling
			MyNode tmp257_AST = null;
			tmp257_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp257_AST);
			match(B_IDENTIFIER);
			{
			_loop526:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp258_AST = null;
					tmp258_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp258_AST);
					match(B_COMMA);
					MyNode tmp259_AST = null;
					tmp259_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp259_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop526;
				}
				
			} while (true);
			}
			list_identifier_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_87);
			} else {
			  throw ex;
			}
		}
		returnAST = list_identifier_AST;
	}
	
	public final void list_equal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_equal_AST = null;
		
		try {      // for error handling
			an_equal();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop246:
			do {
				if ((LA(1)==B_AND)) {
					MyNode tmp260_AST = null;
					tmp260_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp260_AST);
					match(B_AND);
					an_equal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop246;
				}
				
			} while (true);
			}
			list_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_88);
			} else {
			  throw ex;
			}
		}
		returnAST = list_equal_AST;
	}
	
	public final void variant_or_no() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variant_or_no_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_VARIANT:
			{
				MyNode tmp261_AST = null;
				tmp261_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp261_AST);
				match(LITERAL_VARIANT);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp262_AST = null;
				tmp262_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp262_AST);
				match(LITERAL_INVARIANT);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INVARIANT:
			{
				MyNode tmp263_AST = null;
				tmp263_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp263_AST);
				match(LITERAL_INVARIANT);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp264_AST = null;
				tmp264_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp264_AST);
				match(LITERAL_VARIANT);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_84);
			} else {
			  throw ex;
			}
		}
		returnAST = variant_or_no_AST;
	}
	
	public final void an_equal() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode an_equal_AST = null;
		
		try {      // for error handling
			MyNode tmp265_AST = null;
			tmp265_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp265_AST);
			match(B_IDENTIFIER);
			MyNode tmp266_AST = null;
			tmp266_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp266_AST);
			match(B_EQUAL);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			an_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_89);
			} else {
			  throw ex;
			}
		}
		returnAST = an_equal_AST;
	}
	
	public final void func_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode func_call_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			nameRenameDecoratedInvertedParamInverted();
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
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_BRACKCLOSE);
				break;
			}
			case EOF:
			case B_SEMICOLON:
			case B_PARALLEL:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_WHEN:
			case LITERAL_VARIANT:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_DYNAMICS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_70);
			} else {
			  throw ex;
			}
		}
		returnAST = func_call_AST;
	}
	
	public final void nameRenameDecoratedInvertedParamInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenameDecoratedInvertedParamInverted_AST = null;
		
		try {      // for error handling
			nameRenameDecoratedInvertedParam();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case B_TILDE:
			{
				MyNode tmp268_AST = null;
				tmp268_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp268_AST);
				match(B_TILDE);
				break;
			}
			case EOF:
			case B_BRACKOPEN:
			case B_SEMICOLON:
			case B_PARALLEL:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_WHEN:
			case LITERAL_VARIANT:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_DYNAMICS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			nameRenameDecoratedInvertedParamInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_90);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenameDecoratedInvertedParamInverted_AST;
	}
	
	public final void nameRenameDecoratedInvertedParam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenameDecoratedInvertedParam_AST = null;
		
		try {      // for error handling
			nameRenameDecoratedInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop254:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp269_AST = null;
					tmp269_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp269_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop254;
				}
				
			} while (true);
			}
			nameRenameDecoratedInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_91);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenameDecoratedInvertedParam_AST;
	}
	
	public final void nameRenameDecoratedInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenameDecoratedInverted_AST = null;
		
		try {      // for error handling
			nameRenamedDecorated();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==B_TILDE) && (_tokenSet_92.member(LA(2))) && (_tokenSet_93.member(LA(3)))) {
				MyNode tmp271_AST = null;
				tmp271_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp271_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_92.member(LA(1))) && (_tokenSet_93.member(LA(2))) && (_tokenSet_94.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			nameRenameDecoratedInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_92);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenameDecoratedInverted_AST;
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
				MyNode tmp272_AST = null;
				tmp272_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp272_AST);
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
			case B_INSET:
			case B_NOTINSET:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_OF:
			case LITERAL_ELSE:
			case LITERAL_WHEN:
			case LITERAL_IN:
			case LITERAL_VARIANT:
			case LITERAL_WHILE:
			case LITERAL_DO:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case B_LEADSTO:
			case LITERAL_DYNAMICS:
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
				recover(ex,_tokenSet_95);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamedDecorated_AST;
	}
	
	public final void applyto() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode applyto_AST = null;
		
		try {      // for error handling
			MyNode tmp273_AST = null;
			tmp273_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp273_AST);
			match(B_BRACKOPEN);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_BRACKCLOSE);
			applyto_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = applyto_AST;
	}
	
	public final void a_func_call_quoted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_func_call_quoted_AST = null;
		
		try {      // for error handling
			a_func_call();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop260:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp275_AST = null;
					tmp275_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp275_AST);
					match(B_QUOTEIDENT);
					a_func_call();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop260;
				}
				
			} while (true);
			}
			a_func_call_quoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_96);
			} else {
			  throw ex;
			}
		}
		returnAST = a_func_call_quoted_AST;
	}
	
	public final void a_func_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_func_call_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop263:
			do {
				if ((LA(1)==B_LPAREN)) {
					c = LT(1);
					c_AST = (MyNode)astFactory.create(c);
					astFactory.makeASTRoot(currentAST, c_AST);
					match(B_LPAREN);
					if ( inputState.guessing==0 ) {
						
							c_AST.setType(FUNC_CALL_PARAM);
						
					}
					listPredicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop263;
				}
				
			} while (true);
			}
			a_func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_97);
			} else {
			  throw ex;
			}
		}
		returnAST = a_func_call_AST;
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
			_loop380:
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
					break _loop380;
				}
				
			} while (true);
			}
			listPredicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_98);
			} else {
			  throw ex;
			}
		}
		returnAST = listPredicate_AST;
	}
	
	public final void list_func_call() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode list_func_call_AST = null;
		
		try {      // for error handling
			a_func_call_quoted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop266:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp277_AST = null;
					tmp277_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp277_AST);
					match(B_COMMA);
					a_func_call_quoted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop266;
				}
				
			} while (true);
			}
			list_func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_99);
			} else {
			  throw ex;
			}
		}
		returnAST = list_func_call_AST;
	}
	
	public final void analyse_expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode analyse_expression_AST = null;
		
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
			case LITERAL_struct:
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case LITERAL_STRING:
			{
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				analyse_expression_AST = (MyNode)currentAST.root;
				break;
			}
			case EOF:
			{
				if ( inputState.guessing==0 ) {
					
					System.err.println ( "Warning : Empty expression !" );
					errors.WSyntaxic   ( module, "Expression is empty" );
					
				}
				analyse_expression_AST = (MyNode)currentAST.root;
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
		returnAST = analyse_expression_AST;
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
			case LITERAL_STRING:
			{
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				analyse_predicate_AST = (MyNode)currentAST.root;
				break;
			}
			case EOF:
			{
				if ( inputState.guessing==0 ) {
					
					System.err.println ( "Warning : Empty predicate !" );
					errors.WSyntaxic   ( module, "Predicat is empty" );
					
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
	
	public final void logical_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode logical_1_AST = null;
		
		try {      // for error handling
			logical_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop291:
			do {
				if ((LA(1)==LITERAL_or||LA(1)==B_AND)) {
					{
					switch ( LA(1)) {
					case LITERAL_or:
					{
						MyNode tmp278_AST = null;
						tmp278_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp278_AST);
						match(LITERAL_or);
						break;
					}
					case B_AND:
					{
						MyNode tmp279_AST = null;
						tmp279_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp279_AST);
						match(B_AND);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					logical_2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop291;
				}
				
			} while (true);
			}
			logical_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_100);
			} else {
			  throw ex;
			}
		}
		returnAST = logical_1_AST;
	}
	
	public final void logical_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode logical_2_AST = null;
		
		try {      // for error handling
			subset_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop295:
			do {
				if ((LA(1)==B_EQUIV||LA(1)==B_EQUAL)) {
					{
					switch ( LA(1)) {
					case B_EQUIV:
					{
						MyNode tmp280_AST = null;
						tmp280_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp280_AST);
						match(B_EQUIV);
						break;
					}
					case B_EQUAL:
					{
						MyNode tmp281_AST = null;
						tmp281_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp281_AST);
						match(B_EQUAL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					subset_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop295;
				}
				
			} while (true);
			}
			logical_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_101);
			} else {
			  throw ex;
			}
		}
		returnAST = logical_2_AST;
	}
	
	public final void subset_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode subset_description_AST = null;
		
		try {      // for error handling
			boolean synPredMatched300 = false;
			if (((_tokenSet_46.member(LA(1))) && (_tokenSet_102.member(LA(2))) && (_tokenSet_41.member(LA(3))))) {
				int _m300 = mark();
				synPredMatched300 = true;
				inputState.guessing++;
				try {
					{
					extended_pair();
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
					case B_INSET:
					{
						match(B_INSET);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					arithmetic_3();
					}
					}
				}
				catch (RecognitionException pe) {
					synPredMatched300 = false;
				}
				rewind(_m300);
inputState.guessing--;
			}
			if ( synPredMatched300 ) {
				{
				extended_pair();
				astFactory.addASTChild(currentAST, returnAST);
				{
				{
				switch ( LA(1)) {
				case B_SUBSET:
				{
					MyNode tmp282_AST = null;
					tmp282_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp282_AST);
					match(B_SUBSET);
					break;
				}
				case B_NOTSUBSET:
				{
					MyNode tmp283_AST = null;
					tmp283_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp283_AST);
					match(B_NOTSUBSET);
					break;
				}
				case B_STRICTSUBSET:
				{
					MyNode tmp284_AST = null;
					tmp284_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp284_AST);
					match(B_STRICTSUBSET);
					break;
				}
				case B_NOTSTRICTSBSET:
				{
					MyNode tmp285_AST = null;
					tmp285_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp285_AST);
					match(B_NOTSTRICTSBSET);
					break;
				}
				case B_INSET:
				{
					MyNode tmp286_AST = null;
					tmp286_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp286_AST);
					match(B_INSET);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				arithmetic_3();
				astFactory.addASTChild(currentAST, returnAST);
				}
				}
				subset_description_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_46.member(LA(1))) && (_tokenSet_103.member(LA(2))) && (_tokenSet_104.member(LA(3)))) {
				arithmetic_3();
				astFactory.addASTChild(currentAST, returnAST);
				subset_description_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_105);
			} else {
			  throw ex;
			}
		}
		returnAST = subset_description_AST;
	}
	
	public final void extended_pair() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode extended_pair_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			arithmetic_3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop306:
			do {
				if ((LA(1)==B_COMMA)) {
					a = LT(1);
					a_AST = (MyNode)astFactory.create(a);
					astFactory.makeASTRoot(currentAST, a_AST);
					match(B_COMMA);
					if ( inputState.guessing==0 ) {
						
							a_AST.setType(LIST_VAR);
						
					}
					arithmetic_3();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop306;
				}
				
			} while (true);
			}
			extended_pair_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_106);
			} else {
			  throw ex;
			}
		}
		returnAST = extended_pair_AST;
	}
	
	public final void arithmetic_3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode arithmetic_3_AST = null;
		
		try {      // for error handling
			sequence_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop310:
			do {
				if ((_tokenSet_107.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_NOTINSET:
					{
						MyNode tmp287_AST = null;
						tmp287_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp287_AST);
						match(B_NOTINSET);
						break;
					}
					case B_LESS:
					{
						MyNode tmp288_AST = null;
						tmp288_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp288_AST);
						match(B_LESS);
						break;
					}
					case B_GREATER:
					{
						MyNode tmp289_AST = null;
						tmp289_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp289_AST);
						match(B_GREATER);
						break;
					}
					case B_NOTEQUAL:
					{
						MyNode tmp290_AST = null;
						tmp290_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp290_AST);
						match(B_NOTEQUAL);
						break;
					}
					case B_LESSTHANEQUAL:
					{
						MyNode tmp291_AST = null;
						tmp291_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp291_AST);
						match(B_LESSTHANEQUAL);
						break;
					}
					case B_GREATERTHANEQUAL:
					{
						MyNode tmp292_AST = null;
						tmp292_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp292_AST);
						match(B_GREATERTHANEQUAL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					sequence_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop310;
				}
				
			} while (true);
			}
			arithmetic_3_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_108);
			} else {
			  throw ex;
			}
		}
		returnAST = arithmetic_3_AST;
	}
	
	public final void sequence_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sequence_description_AST = null;
		
		try {      // for error handling
			set_description();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop314:
			do {
				if (((LA(1) >= B_CONCATSEQ && LA(1) <= B_SUFFIXSEQ))) {
					{
					switch ( LA(1)) {
					case B_CONCATSEQ:
					{
						MyNode tmp293_AST = null;
						tmp293_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp293_AST);
						match(B_CONCATSEQ);
						break;
					}
					case B_PREAPPSEQ:
					{
						MyNode tmp294_AST = null;
						tmp294_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp294_AST);
						match(B_PREAPPSEQ);
						break;
					}
					case B_APPSEQ:
					{
						MyNode tmp295_AST = null;
						tmp295_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp295_AST);
						match(B_APPSEQ);
						break;
					}
					case B_PREFIXSEQ:
					{
						MyNode tmp296_AST = null;
						tmp296_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp296_AST);
						match(B_PREFIXSEQ);
						break;
					}
					case B_SUFFIXSEQ:
					{
						MyNode tmp297_AST = null;
						tmp297_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp297_AST);
						match(B_SUFFIXSEQ);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					set_description();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop314;
				}
				
			} while (true);
			}
			sequence_description_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_109);
			} else {
			  throw ex;
			}
		}
		returnAST = sequence_description_AST;
	}
	
	public final void set_description() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode set_description_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_bool:
			{
				MyNode tmp298_AST = null;
				tmp298_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp298_AST);
				match(LITERAL_bool);
				match(B_LPAREN);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				set_description_AST = (MyNode)currentAST.root;
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
			case LITERAL_struct:
			case LITERAL_INT:
			case 87:
			case LITERAL_INTEGER:
			case 89:
			case LITERAL_BOOL:
			case LITERAL_NAT:
			case 92:
			case LITERAL_NATURAL:
			case 94:
			case LITERAL_STRING:
			{
				functional_set();
				astFactory.addASTChild(currentAST, returnAST);
				set_description_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_110);
			} else {
			  throw ex;
			}
		}
		returnAST = set_description_AST;
	}
	
	public final void functional_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode functional_set_AST = null;
		
		try {      // for error handling
			functional_const_set();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop319:
			do {
				if (((LA(1) >= B_RELATION && LA(1) <= B_BIJECTION))) {
					{
					switch ( LA(1)) {
					case B_RELATION:
					{
						MyNode tmp301_AST = null;
						tmp301_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp301_AST);
						match(B_RELATION);
						break;
					}
					case B_PARTIAL:
					{
						MyNode tmp302_AST = null;
						tmp302_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp302_AST);
						match(B_PARTIAL);
						break;
					}
					case B_TOTAL:
					{
						MyNode tmp303_AST = null;
						tmp303_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp303_AST);
						match(B_TOTAL);
						break;
					}
					case B_PARTIAL_INJECT:
					{
						MyNode tmp304_AST = null;
						tmp304_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp304_AST);
						match(B_PARTIAL_INJECT);
						break;
					}
					case B_TOTAL_INJECT:
					{
						MyNode tmp305_AST = null;
						tmp305_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp305_AST);
						match(B_TOTAL_INJECT);
						break;
					}
					case B_PARTIAL_SURJECT:
					{
						MyNode tmp306_AST = null;
						tmp306_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp306_AST);
						match(B_PARTIAL_SURJECT);
						break;
					}
					case B_TOTAL_SURJECT:
					{
						MyNode tmp307_AST = null;
						tmp307_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp307_AST);
						match(B_TOTAL_SURJECT);
						break;
					}
					case B_BIJECTION:
					{
						MyNode tmp308_AST = null;
						tmp308_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp308_AST);
						match(B_BIJECTION);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					functional_const_set();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop319;
				}
				
			} while (true);
			}
			functional_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_110);
			} else {
			  throw ex;
			}
		}
		returnAST = functional_set_AST;
	}
	
	public final void functional_const_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode functional_const_set_AST = null;
		
		try {      // for error handling
			basic_constructors();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop323:
			do {
				if (((LA(1) >= B_DOMAINRESTRICT && LA(1) <= B_RELPROD))) {
					{
					switch ( LA(1)) {
					case B_DOMAINRESTRICT:
					{
						MyNode tmp309_AST = null;
						tmp309_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp309_AST);
						match(B_DOMAINRESTRICT);
						break;
					}
					case B_RANGERESTRICT:
					{
						MyNode tmp310_AST = null;
						tmp310_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp310_AST);
						match(B_RANGERESTRICT);
						break;
					}
					case B_DOMAINSUBSTRACT:
					{
						MyNode tmp311_AST = null;
						tmp311_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp311_AST);
						match(B_DOMAINSUBSTRACT);
						break;
					}
					case B_RANGESUBSTRACT:
					{
						MyNode tmp312_AST = null;
						tmp312_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp312_AST);
						match(B_RANGESUBSTRACT);
						break;
					}
					case B_OVERRIDEFORWARD:
					{
						MyNode tmp313_AST = null;
						tmp313_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp313_AST);
						match(B_OVERRIDEFORWARD);
						break;
					}
					case B_OVERRIDEBACKWARD:
					{
						MyNode tmp314_AST = null;
						tmp314_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp314_AST);
						match(B_OVERRIDEBACKWARD);
						break;
					}
					case B_RELPROD:
					{
						MyNode tmp315_AST = null;
						tmp315_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp315_AST);
						match(B_RELPROD);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					basic_constructors();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop323;
				}
				
			} while (true);
			}
			functional_const_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_111);
			} else {
			  throw ex;
			}
		}
		returnAST = functional_const_set_AST;
	}
	
	public final void basic_constructors() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode basic_constructors_AST = null;
		
		try {      // for error handling
			new_couple();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop327:
			do {
				if ((LA(1)==B_UNION||LA(1)==B_INTER)) {
					{
					switch ( LA(1)) {
					case B_UNION:
					{
						MyNode tmp316_AST = null;
						tmp316_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp316_AST);
						match(B_UNION);
						break;
					}
					case B_INTER:
					{
						MyNode tmp317_AST = null;
						tmp317_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp317_AST);
						match(B_INTER);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					new_couple();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop327;
				}
				
			} while (true);
			}
			basic_constructors_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_112);
			} else {
			  throw ex;
			}
		}
		returnAST = basic_constructors_AST;
	}
	
	public final void new_couple() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_couple_AST = null;
		
		try {      // for error handling
			arithmetic_0();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop330:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp318_AST = null;
					tmp318_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp318_AST);
					match(B_MAPLET);
					arithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop330;
				}
				
			} while (true);
			}
			new_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_113);
			} else {
			  throw ex;
			}
		}
		returnAST = new_couple_AST;
	}
	
	public final void arithmetic_0() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode arithmetic_0_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			boolean synPredMatched333 = false;
			if (((_tokenSet_37.member(LA(1))) && (_tokenSet_44.member(LA(2))) && (_tokenSet_114.member(LA(3))))) {
				int _m333 = mark();
				synPredMatched333 = true;
				inputState.guessing++;
				try {
					{
					basic_sets();
					match(B_MULT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched333 = false;
				}
				rewind(_m333);
inputState.guessing--;
			}
			if ( synPredMatched333 ) {
				{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop336:
				do {
					if ((LA(1)==B_MULT) && (_tokenSet_37.member(LA(2))) && (_tokenSet_44.member(LA(3)))) {
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
						break _loop336;
					}
					
				} while (true);
				}
				}
				arithmetic_0_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_28.member(LA(1))) && (_tokenSet_115.member(LA(2))) && (_tokenSet_104.member(LA(3)))) {
				arithmetic_1();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop339:
				do {
					if ((LA(1)==B_MULT||LA(1)==B_POWER) && (_tokenSet_28.member(LA(2))) && (_tokenSet_115.member(LA(3)))) {
						{
						switch ( LA(1)) {
						case B_POWER:
						{
							MyNode tmp319_AST = null;
							tmp319_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp319_AST);
							match(B_POWER);
							break;
						}
						case B_MULT:
						{
							MyNode tmp320_AST = null;
							tmp320_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp320_AST);
							match(B_MULT);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						arithmetic_1();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop339;
					}
					
				} while (true);
				}
				arithmetic_0_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
		returnAST = arithmetic_0_AST;
	}
	
	public final void arithmetic_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode arithmetic_1_AST = null;
		
		try {      // for error handling
			arithmetic_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop343:
			do {
				if ((LA(1)==B_DIV||LA(1)==LITERAL_mod) && (_tokenSet_28.member(LA(2))) && (_tokenSet_115.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case B_DIV:
					{
						MyNode tmp321_AST = null;
						tmp321_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp321_AST);
						match(B_DIV);
						break;
					}
					case LITERAL_mod:
					{
						MyNode tmp322_AST = null;
						tmp322_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp322_AST);
						match(LITERAL_mod);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					arithmetic_2();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop343;
				}
				
			} while (true);
			}
			arithmetic_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
		returnAST = arithmetic_1_AST;
	}
	
	public final void arithmetic_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode arithmetic_2_AST = null;
		
		try {      // for error handling
			bases();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop347:
			do {
				if ((LA(1)==B_ADD||LA(1)==B_MINUS) && (_tokenSet_28.member(LA(2))) && (_tokenSet_115.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case B_ADD:
					{
						MyNode tmp323_AST = null;
						tmp323_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp323_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp324_AST = null;
						tmp324_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp324_AST);
						match(B_MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					bases();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop347;
				}
				
			} while (true);
			}
			arithmetic_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
		returnAST = arithmetic_2_AST;
	}
	
	public final void value_set() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode value_set_AST = null;
		
		try {      // for error handling
			boolean synPredMatched352 = false;
			if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && (_tokenSet_116.member(LA(2))) && (_tokenSet_117.member(LA(3))))) {
				int _m352 = mark();
				synPredMatched352 = true;
				inputState.guessing++;
				try {
					{
					alist_var();
					match(B_SUCH);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched352 = false;
				}
				rewind(_m352);
inputState.guessing--;
			}
			if ( synPredMatched352 ) {
				alist_var();
				astFactory.addASTChild(currentAST, returnAST);
				{
				MyNode tmp325_AST = null;
				tmp325_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp325_AST);
				match(B_SUCH);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				value_set_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_118.member(LA(2))) && (_tokenSet_115.member(LA(3)))) {
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop355:
				do {
					if ((LA(1)==B_COMMA)) {
						MyNode tmp326_AST = null;
						tmp326_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp326_AST);
						match(B_COMMA);
						predicate();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop355;
					}
					
				} while (true);
				}
				value_set_AST = (MyNode)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = value_set_AST;
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
				MyNode tmp327_AST = null;
				tmp327_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp327_AST);
				match(B_FORALL);
				break;
			}
			case B_EXISTS:
			{
				MyNode tmp328_AST = null;
				tmp328_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp328_AST);
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
			boolean synPredMatched479 = false;
			if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
				int _m479 = mark();
				synPredMatched479 = true;
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
					synPredMatched479 = false;
				}
				rewind(_m479);
inputState.guessing--;
			}
			if ( synPredMatched479 ) {
				{
				match(B_LPAREN);
				q_quantification();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched482 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
					int _m482 = mark();
					synPredMatched482 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched482 = false;
					}
					rewind(_m482);
inputState.guessing--;
				}
				if ( synPredMatched482 ) {
					{
					q_quantification();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched485 = false;
					if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
						int _m485 = mark();
						synPredMatched485 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched485 = false;
						}
						rewind(_m485);
inputState.guessing--;
					}
					if ( synPredMatched485 ) {
						{
						match(B_LPAREN);
						q_quantification();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched488 = false;
						if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
							int _m488 = mark();
							synPredMatched488 = true;
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
								synPredMatched488 = false;
							}
							rewind(_m488);
inputState.guessing--;
						}
						if ( synPredMatched488 ) {
							{
							match(B_LPAREN);
							q_quantification();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched491 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
								int _m491 = mark();
								synPredMatched491 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched491 = false;
								}
								rewind(_m491);
inputState.guessing--;
							}
							if ( synPredMatched491 ) {
								{
								q_quantification();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched494 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
									int _m494 = mark();
									synPredMatched494 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched494 = false;
									}
									rewind(_m494);
inputState.guessing--;
								}
								if ( synPredMatched494 ) {
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
									recover(ex,_tokenSet_33);
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
				MyNode tmp335_AST = null;
				tmp335_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp335_AST);
				match(B_LAMBDA);
				break;
			}
			case LITERAL_PI:
			{
				MyNode tmp336_AST = null;
				tmp336_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp336_AST);
				match(LITERAL_PI);
				break;
			}
			case LITERAL_SIGMA:
			{
				MyNode tmp337_AST = null;
				tmp337_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp337_AST);
				match(LITERAL_SIGMA);
				break;
			}
			case LITERAL_UNION:
			{
				MyNode tmp338_AST = null;
				tmp338_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp338_AST);
				match(LITERAL_UNION);
				break;
			}
			case LITERAL_INTER:
			{
				MyNode tmp339_AST = null;
				tmp339_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp339_AST);
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
			boolean synPredMatched501 = false;
			if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
				int _m501 = mark();
				synPredMatched501 = true;
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
					synPredMatched501 = false;
				}
				rewind(_m501);
inputState.guessing--;
			}
			if ( synPredMatched501 ) {
				{
				match(B_LPAREN);
				q_operande();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched504 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
					int _m504 = mark();
					synPredMatched504 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched504 = false;
					}
					rewind(_m504);
inputState.guessing--;
				}
				if ( synPredMatched504 ) {
					{
					q_operande();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched507 = false;
					if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
						int _m507 = mark();
						synPredMatched507 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched507 = false;
						}
						rewind(_m507);
inputState.guessing--;
					}
					if ( synPredMatched507 ) {
						{
						match(B_LPAREN);
						q_operande();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched510 = false;
						if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)))) {
							int _m510 = mark();
							synPredMatched510 = true;
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
								synPredMatched510 = false;
							}
							rewind(_m510);
inputState.guessing--;
						}
						if ( synPredMatched510 ) {
							{
							match(B_LPAREN);
							q_operande();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched513 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
								int _m513 = mark();
								synPredMatched513 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched513 = false;
								}
								rewind(_m513);
inputState.guessing--;
							}
							if ( synPredMatched513 ) {
								{
								q_operande();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched516 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_119.member(LA(3))))) {
									int _m516 = mark();
									synPredMatched516 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched516 = false;
									}
									rewind(_m516);
inputState.guessing--;
								}
								if ( synPredMatched516 ) {
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
									recover(ex,_tokenSet_33);
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
				MyNode tmp346_AST = null;
				tmp346_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp346_AST);
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
				recover(ex,_tokenSet_120);
			} else {
			  throw ex;
			}
		}
		returnAST = alist_var_AST;
	}
	
	public final void unary_basic_value_inverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode unary_basic_value_inverted_AST = null;
		
		try {      // for error handling
			unary_basic_value();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case B_TILDE:
			{
				MyNode tmp348_AST = null;
				tmp348_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp348_AST);
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
			case B_INSET:
			case B_NOTINSET:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_WHEN:
			case LITERAL_VARIANT:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_DYNAMICS:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			unary_basic_value_inverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		returnAST = unary_basic_value_inverted_AST;
	}
	
	public final void unary_basic_value() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode unary_basic_value_AST = null;
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
				expInvertedParamInvertedQuoted();
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
				case B_INSET:
				case B_NOTINSET:
				case LITERAL_END:
				case LITERAL_CONSTRAINTS:
				case LITERAL_EXTENDS:
				case LITERAL_USES:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_IMPORTS:
				case LITERAL_PROMOTES:
				case LITERAL_REFINES:
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				case LITERAL_SETS:
				case LITERAL_VALUES:
				case LITERAL_PROPERTIES:
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				case LITERAL_INVARIANT:
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				case LITERAL_DEFINITIONS:
				case LITERAL_ASSERTIONS:
				case LITERAL_INITIALISATION:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_ELSE:
				case LITERAL_WHEN:
				case LITERAL_VARIANT:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_DYNAMICS:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				unary_basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp350_AST = null;
				tmp350_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp350_AST);
				match(B_NUMBER);
				unary_basic_value_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_121);
			} else {
			  throw ex;
			}
		}
		returnAST = unary_basic_value_AST;
	}
	
	public final void expInvertedParamInvertedQuoted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expInvertedParamInvertedQuoted_AST = null;
		
		try {      // for error handling
			expInvertedParamInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop363:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp351_AST = null;
					tmp351_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp351_AST);
					match(B_QUOTEIDENT);
					expInvertedParamInverted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop363;
				}
				
			} while (true);
			}
			expInvertedParamInvertedQuoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_122);
			} else {
			  throw ex;
			}
		}
		returnAST = expInvertedParamInvertedQuoted_AST;
	}
	
	public final void expInvertedParamInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expInvertedParamInverted_AST = null;
		
		try {      // for error handling
			expInvertedParam();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==B_TILDE) && (_tokenSet_123.member(LA(2))) && (_tokenSet_114.member(LA(3)))) {
				MyNode tmp352_AST = null;
				tmp352_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp352_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_123.member(LA(1))) && (_tokenSet_114.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expInvertedParamInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_123);
			} else {
			  throw ex;
			}
		}
		returnAST = expInvertedParamInverted_AST;
	}
	
	public final void expInvertedParam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expInvertedParam_AST = null;
		
		try {      // for error handling
			expParentInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop368:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp353_AST = null;
					tmp353_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp353_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop368;
				}
				
			} while (true);
			}
			expInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_123);
			} else {
			  throw ex;
			}
		}
		returnAST = expInvertedParam_AST;
	}
	
	public final void expParentInverted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expParentInverted_AST = null;
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
			expression_parent();
			astFactory.addASTChild(currentAST, returnAST);
			{
			if ((LA(1)==B_TILDE) && (_tokenSet_124.member(LA(2))) && (_tokenSet_114.member(LA(3)))) {
				MyNode tmp355_AST = null;
				tmp355_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp355_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_124.member(LA(1))) && (_tokenSet_114.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			expParentInverted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_124);
			} else {
			  throw ex;
			}
		}
		returnAST = expParentInverted_AST;
	}
	
	public final void expression_parent() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_parent_AST = null;
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
				expression_func_composition();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				expression_parent_AST = (MyNode)currentAST.root;
				break;
			}
			case B_IDENTIFIER:
			{
				nameRenamedDecorated();
				astFactory.addASTChild(currentAST, returnAST);
				expression_parent_AST = (MyNode)currentAST.root;
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
				recover(ex,_tokenSet_124);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_parent_AST;
	}
	
	public final void expression_func_composition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_func_composition_AST = null;
		
		try {      // for error handling
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop376:
			do {
				if ((_tokenSet_125.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp357_AST = null;
						tmp357_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp357_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp358_AST = null;
						tmp358_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp358_AST);
						match(B_PARALLEL);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp359_AST = null;
						tmp359_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp359_AST);
						match(B_COMMA);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop376;
				}
				
			} while (true);
			}
			expression_func_composition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_func_composition_AST;
	}
	
	protected final void plogical_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode plogical_1_AST = null;
		
		try {      // for error handling
			plogical_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop392:
			do {
				if ((LA(1)==LITERAL_or||LA(1)==B_AND) && (_tokenSet_72.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case LITERAL_or:
					{
						MyNode tmp360_AST = null;
						tmp360_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp360_AST);
						match(LITERAL_or);
						break;
					}
					case B_AND:
					{
						MyNode tmp361_AST = null;
						tmp361_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp361_AST);
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
					break _loop392;
				}
				
			} while (true);
			}
			plogical_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_127);
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
			_loop396:
			do {
				if ((LA(1)==B_EQUIV||LA(1)==B_EQUAL)) {
					{
					switch ( LA(1)) {
					case B_EQUIV:
					{
						MyNode tmp362_AST = null;
						tmp362_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp362_AST);
						match(B_EQUIV);
						break;
					}
					case B_EQUAL:
					{
						MyNode tmp363_AST = null;
						tmp363_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp363_AST);
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
					break _loop396;
				}
				
			} while (true);
			}
			plogical_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_128);
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
			boolean synPredMatched401 = false;
			if (((_tokenSet_72.member(LA(1))) && (_tokenSet_126.member(LA(2))) && (_tokenSet_129.member(LA(3))))) {
				int _m401 = mark();
				synPredMatched401 = true;
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
					synPredMatched401 = false;
				}
				rewind(_m401);
inputState.guessing--;
			}
			if ( synPredMatched401 ) {
				pextended_pair();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop404:
				do {
					if (((LA(1) >= B_SUBSET && LA(1) <= B_NOTSTRICTSBSET))) {
						{
						switch ( LA(1)) {
						case B_SUBSET:
						{
							MyNode tmp364_AST = null;
							tmp364_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp364_AST);
							match(B_SUBSET);
							break;
						}
						case B_NOTSUBSET:
						{
							MyNode tmp365_AST = null;
							tmp365_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp365_AST);
							match(B_NOTSUBSET);
							break;
						}
						case B_STRICTSUBSET:
						{
							MyNode tmp366_AST = null;
							tmp366_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp366_AST);
							match(B_STRICTSUBSET);
							break;
						}
						case B_NOTSTRICTSBSET:
						{
							MyNode tmp367_AST = null;
							tmp367_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp367_AST);
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
						break _loop404;
					}
					
				} while (true);
				}
				psubset_description_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_130.member(LA(2))) && (_tokenSet_129.member(LA(3)))) {
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
				recover(ex,_tokenSet_131);
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
			_loop407:
			do {
				if ((LA(1)==B_COMMA) && (_tokenSet_72.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
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
					break _loop407;
				}
				
			} while (true);
			}
			pextended_pair_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_132);
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
			_loop411:
			do {
				if (((LA(1) >= B_LESS && LA(1) <= B_GREATERTHANEQUAL)) && (_tokenSet_72.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case B_LESS:
					{
						MyNode tmp368_AST = null;
						tmp368_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp368_AST);
						match(B_LESS);
						break;
					}
					case B_GREATER:
					{
						MyNode tmp369_AST = null;
						tmp369_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp369_AST);
						match(B_GREATER);
						break;
					}
					case B_NOTEQUAL:
					{
						MyNode tmp370_AST = null;
						tmp370_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp370_AST);
						match(B_NOTEQUAL);
						break;
					}
					case B_LESSTHANEQUAL:
					{
						MyNode tmp371_AST = null;
						tmp371_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp371_AST);
						match(B_LESSTHANEQUAL);
						break;
					}
					case B_GREATERTHANEQUAL:
					{
						MyNode tmp372_AST = null;
						tmp372_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp372_AST);
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
					break _loop411;
				}
				
			} while (true);
			}
			parithmetic_3_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_132);
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
			_loop415:
			do {
				if (((LA(1) >= B_CONCATSEQ && LA(1) <= B_SUFFIXSEQ))) {
					{
					switch ( LA(1)) {
					case B_CONCATSEQ:
					{
						MyNode tmp373_AST = null;
						tmp373_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp373_AST);
						match(B_CONCATSEQ);
						break;
					}
					case B_PREAPPSEQ:
					{
						MyNode tmp374_AST = null;
						tmp374_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp374_AST);
						match(B_PREAPPSEQ);
						break;
					}
					case B_APPSEQ:
					{
						MyNode tmp375_AST = null;
						tmp375_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp375_AST);
						match(B_APPSEQ);
						break;
					}
					case B_PREFIXSEQ:
					{
						MyNode tmp376_AST = null;
						tmp376_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp376_AST);
						match(B_PREFIXSEQ);
						break;
					}
					case B_SUFFIXSEQ:
					{
						MyNode tmp377_AST = null;
						tmp377_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp377_AST);
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
					break _loop415;
				}
				
			} while (true);
			}
			psequence_description_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_133);
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
				MyNode tmp378_AST = null;
				tmp378_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp378_AST);
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
			case LITERAL_STRING:
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
				recover(ex,_tokenSet_134);
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
			_loop420:
			do {
				if (((LA(1) >= B_RELATION && LA(1) <= B_BIJECTION))) {
					{
					switch ( LA(1)) {
					case B_RELATION:
					{
						MyNode tmp381_AST = null;
						tmp381_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp381_AST);
						match(B_RELATION);
						break;
					}
					case B_PARTIAL:
					{
						MyNode tmp382_AST = null;
						tmp382_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp382_AST);
						match(B_PARTIAL);
						break;
					}
					case B_TOTAL:
					{
						MyNode tmp383_AST = null;
						tmp383_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp383_AST);
						match(B_TOTAL);
						break;
					}
					case B_PARTIAL_INJECT:
					{
						MyNode tmp384_AST = null;
						tmp384_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp384_AST);
						match(B_PARTIAL_INJECT);
						break;
					}
					case B_TOTAL_INJECT:
					{
						MyNode tmp385_AST = null;
						tmp385_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp385_AST);
						match(B_TOTAL_INJECT);
						break;
					}
					case B_PARTIAL_SURJECT:
					{
						MyNode tmp386_AST = null;
						tmp386_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp386_AST);
						match(B_PARTIAL_SURJECT);
						break;
					}
					case B_TOTAL_SURJECT:
					{
						MyNode tmp387_AST = null;
						tmp387_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp387_AST);
						match(B_TOTAL_SURJECT);
						break;
					}
					case B_BIJECTION:
					{
						MyNode tmp388_AST = null;
						tmp388_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp388_AST);
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
					break _loop420;
				}
				
			} while (true);
			}
			pfunctional_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_134);
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
			_loop424:
			do {
				if (((LA(1) >= B_DOMAINRESTRICT && LA(1) <= B_RELPROD))) {
					{
					switch ( LA(1)) {
					case B_DOMAINRESTRICT:
					{
						MyNode tmp389_AST = null;
						tmp389_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp389_AST);
						match(B_DOMAINRESTRICT);
						break;
					}
					case B_RANGERESTRICT:
					{
						MyNode tmp390_AST = null;
						tmp390_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp390_AST);
						match(B_RANGERESTRICT);
						break;
					}
					case B_DOMAINSUBSTRACT:
					{
						MyNode tmp391_AST = null;
						tmp391_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp391_AST);
						match(B_DOMAINSUBSTRACT);
						break;
					}
					case B_RANGESUBSTRACT:
					{
						MyNode tmp392_AST = null;
						tmp392_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp392_AST);
						match(B_RANGESUBSTRACT);
						break;
					}
					case B_OVERRIDEFORWARD:
					{
						MyNode tmp393_AST = null;
						tmp393_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp393_AST);
						match(B_OVERRIDEFORWARD);
						break;
					}
					case B_OVERRIDEBACKWARD:
					{
						MyNode tmp394_AST = null;
						tmp394_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp394_AST);
						match(B_OVERRIDEBACKWARD);
						break;
					}
					case B_RELPROD:
					{
						MyNode tmp395_AST = null;
						tmp395_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp395_AST);
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
					break _loop424;
				}
				
			} while (true);
			}
			pfunctional_const_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_135);
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
			_loop428:
			do {
				if ((LA(1)==B_UNION||LA(1)==B_INTER)) {
					{
					switch ( LA(1)) {
					case B_UNION:
					{
						MyNode tmp396_AST = null;
						tmp396_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp396_AST);
						match(B_UNION);
						break;
					}
					case B_INTER:
					{
						MyNode tmp397_AST = null;
						tmp397_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp397_AST);
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
					break _loop428;
				}
				
			} while (true);
			}
			pset_constructors_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_136);
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
			_loop431:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp398_AST = null;
					tmp398_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp398_AST);
					match(B_MAPLET);
					parithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop431;
				}
				
			} while (true);
			}
			ppaire_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_137);
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
			boolean synPredMatched434 = false;
			if (((_tokenSet_37.member(LA(1))) && (_tokenSet_138.member(LA(2))) && (_tokenSet_15.member(LA(3))))) {
				int _m434 = mark();
				synPredMatched434 = true;
				inputState.guessing++;
				try {
					{
					basic_sets();
					match(B_MULT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched434 = false;
				}
				rewind(_m434);
inputState.guessing--;
			}
			if ( synPredMatched434 ) {
				{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop437:
				do {
					if ((LA(1)==B_MULT) && (_tokenSet_37.member(LA(2))) && (_tokenSet_138.member(LA(3)))) {
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
						break _loop437;
					}
					
				} while (true);
				}
				}
				parithmetic_0_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_139.member(LA(1))) && (_tokenSet_126.member(LA(2))) && (_tokenSet_129.member(LA(3)))) {
				parithmetic_1();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop440:
				do {
					if ((LA(1)==B_MULT||LA(1)==B_POWER) && (_tokenSet_139.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
						{
						switch ( LA(1)) {
						case B_POWER:
						{
							MyNode tmp399_AST = null;
							tmp399_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp399_AST);
							match(B_POWER);
							break;
						}
						case B_MULT:
						{
							MyNode tmp400_AST = null;
							tmp400_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp400_AST);
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
						break _loop440;
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
				recover(ex,_tokenSet_138);
			} else {
			  throw ex;
			}
		}
		returnAST = parithmetic_0_AST;
	}
	
	protected final void parithmetic_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode parithmetic_1_AST = null;
		
		try {      // for error handling
			parithmetic_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop444:
			do {
				if ((LA(1)==B_DIV||LA(1)==LITERAL_mod) && (_tokenSet_139.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case B_DIV:
					{
						MyNode tmp401_AST = null;
						tmp401_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp401_AST);
						match(B_DIV);
						break;
					}
					case LITERAL_mod:
					{
						MyNode tmp402_AST = null;
						tmp402_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp402_AST);
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
					break _loop444;
				}
				
			} while (true);
			}
			parithmetic_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_138);
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
			_loop448:
			do {
				if ((LA(1)==B_ADD||LA(1)==B_MINUS) && (_tokenSet_139.member(LA(2))) && (_tokenSet_126.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case B_ADD:
					{
						MyNode tmp403_AST = null;
						tmp403_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp403_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp404_AST = null;
						tmp404_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp404_AST);
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
					break _loop448;
				}
				
			} while (true);
			}
			parithmetic_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_138);
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
				MyNode tmp405_AST = null;
				tmp405_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp405_AST);
				match(B_SEQEMPTY);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				MyNode tmp406_AST = null;
				tmp406_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp406_AST);
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
			case LITERAL_STRING:
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
					MyNode tmp409_AST = null;
					tmp409_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp409_AST);
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
				case B_SEMICOLON:
				case B_PARALLEL:
				case LITERAL_END:
				case LITERAL_CONSTRAINTS:
				case LITERAL_EXTENDS:
				case LITERAL_USES:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_IMPORTS:
				case LITERAL_PROMOTES:
				case LITERAL_REFINES:
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				case LITERAL_SETS:
				case LITERAL_VALUES:
				case LITERAL_PROPERTIES:
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				case LITERAL_INVARIANT:
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				case LITERAL_DEFINITIONS:
				case LITERAL_ASSERTIONS:
				case LITERAL_INITIALISATION:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_OF:
				case LITERAL_ELSE:
				case LITERAL_WHEN:
				case LITERAL_IN:
				case LITERAL_VARIANT:
				case LITERAL_WHILE:
				case LITERAL_DO:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case B_LEADSTO:
				case LITERAL_DYNAMICS:
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
				MyNode tmp410_AST = null;
				tmp410_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp410_AST);
				match(B_EMPTYSET);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				MyNode tmp411_AST = null;
				tmp411_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp411_AST);
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
				recover(ex,_tokenSet_138);
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
				MyNode tmp413_AST = null;
				tmp413_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp413_AST);
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
				MyNode tmp414_AST = null;
				tmp414_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp414_AST);
				match(LITERAL_TRUE);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp415_AST = null;
				tmp415_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp415_AST);
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
				recover(ex,_tokenSet_140);
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
			boolean synPredMatched453 = false;
			if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && (_tokenSet_116.member(LA(2))) && (_tokenSet_117.member(LA(3))))) {
				int _m453 = mark();
				synPredMatched453 = true;
				inputState.guessing++;
				try {
					{
					alist_var();
					match(B_SUCH);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched453 = false;
				}
				rewind(_m453);
inputState.guessing--;
			}
			if ( synPredMatched453 ) {
				alist_var();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp416_AST = null;
				tmp416_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp416_AST);
				match(B_SUCH);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				pvalue_set_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_72.member(LA(1))) && (_tokenSet_118.member(LA(2))) && (_tokenSet_141.member(LA(3)))) {
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop455:
				do {
					if ((LA(1)==B_COMMA)) {
						MyNode tmp417_AST = null;
						tmp417_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp417_AST);
						match(B_COMMA);
						predicate();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop455;
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
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = pvalue_set_AST;
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
				MyNode tmp418_AST = null;
				tmp418_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp418_AST);
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
			case B_SEMICOLON:
			case B_PARALLEL:
			case LITERAL_END:
			case LITERAL_CONSTRAINTS:
			case LITERAL_EXTENDS:
			case LITERAL_USES:
			case LITERAL_INCLUDES:
			case LITERAL_SEES:
			case LITERAL_IMPORTS:
			case LITERAL_PROMOTES:
			case LITERAL_REFINES:
			case LITERAL_CONSTANTS:
			case LITERAL_CONCRETE_CONSTANTS:
			case LITERAL_VISIBLE_CONSTANTS:
			case LITERAL_ABSTRACT_CONSTANTS:
			case LITERAL_HIDDEN_CONSTANTS:
			case LITERAL_SETS:
			case LITERAL_VALUES:
			case LITERAL_PROPERTIES:
			case LITERAL_VARIABLES:
			case LITERAL_ABSTRACT_VARIABLES:
			case LITERAL_VISIBLE_VARIABLES:
			case LITERAL_INVARIANT:
			case LITERAL_HIDDEN_VARIABLES:
			case LITERAL_CONCRETE_VARIABLES:
			case LITERAL_DEFINITIONS:
			case LITERAL_ASSERTIONS:
			case LITERAL_INITIALISATION:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_OF:
			case LITERAL_ELSE:
			case LITERAL_WHEN:
			case LITERAL_IN:
			case LITERAL_VARIANT:
			case LITERAL_WHILE:
			case LITERAL_DO:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case B_LEADSTO:
			case LITERAL_DYNAMICS:
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
				recover(ex,_tokenSet_140);
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
			MyNode tmp419_AST = null;
			tmp419_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp419_AST);
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
				recover(ex,_tokenSet_142);
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
			_loop523:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp422_AST = null;
					tmp422_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp422_AST);
					match(B_COMMA);
					a_record();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop523;
				}
				
			} while (true);
			}
			listRecord_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
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
				case B_TILDE:
				case B_SEMICOLON:
				case B_PARALLEL:
				case LITERAL_END:
				case LITERAL_CONSTRAINTS:
				case LITERAL_EXTENDS:
				case LITERAL_USES:
				case LITERAL_INCLUDES:
				case LITERAL_SEES:
				case LITERAL_IMPORTS:
				case LITERAL_PROMOTES:
				case LITERAL_REFINES:
				case LITERAL_CONSTANTS:
				case LITERAL_CONCRETE_CONSTANTS:
				case LITERAL_VISIBLE_CONSTANTS:
				case LITERAL_ABSTRACT_CONSTANTS:
				case LITERAL_HIDDEN_CONSTANTS:
				case LITERAL_SETS:
				case LITERAL_VALUES:
				case LITERAL_PROPERTIES:
				case LITERAL_VARIABLES:
				case LITERAL_ABSTRACT_VARIABLES:
				case LITERAL_VISIBLE_VARIABLES:
				case LITERAL_INVARIANT:
				case LITERAL_HIDDEN_VARIABLES:
				case LITERAL_CONCRETE_VARIABLES:
				case LITERAL_DEFINITIONS:
				case LITERAL_ASSERTIONS:
				case LITERAL_INITIALISATION:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_OF:
				case LITERAL_ELSE:
				case LITERAL_WHEN:
				case LITERAL_IN:
				case LITERAL_VARIANT:
				case LITERAL_WHILE:
				case LITERAL_DO:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case B_LEADSTO:
				case LITERAL_DYNAMICS:
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
				MyNode tmp424_AST = null;
				tmp424_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp424_AST);
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
				recover(ex,_tokenSet_143);
			} else {
			  throw ex;
			}
		}
		returnAST = punary_basic_value_AST;
	}
	
	protected final void predInvertedParam() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParam_AST = null;
		
		try {      // for error handling
			predParentInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop466:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp425_AST = null;
					tmp425_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp425_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop466;
				}
				
			} while (true);
			}
			predInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_144.member(LA(2))) && (_tokenSet_15.member(LA(3)))) {
				MyNode tmp427_AST = null;
				tmp427_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp427_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_144.member(LA(1))) && (_tokenSet_15.member(LA(2))) && (_tokenSet_16.member(LA(3)))) {
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
				recover(ex,_tokenSet_144);
			} else {
			  throw ex;
			}
		}
		returnAST = predParentInverted_AST;
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
				recover(ex,_tokenSet_144);
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
			_loop474:
			do {
				if ((_tokenSet_125.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp429_AST = null;
						tmp429_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp429_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp430_AST = null;
						tmp430_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp430_AST);
						match(B_PARALLEL);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp431_AST = null;
						tmp431_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp431_AST);
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
					break _loop474;
				}
				
			} while (true);
			}
			pred_func_composition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
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
				recover(ex,_tokenSet_33);
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
			MyNode tmp437_AST = null;
			tmp437_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp437_AST);
			match(B_SUCH);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			q_operande_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
			} else {
			  throw ex;
			}
		}
		returnAST = q_operande_AST;
	}
	
	public final void is_struct() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode is_struct_AST = null;
		
		try {      // for error handling
			MyNode tmp439_AST = null;
			tmp439_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp439_AST);
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		returnAST = is_struct_AST;
	}
	
	public final void a_record() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_record_AST = null;
		Token  a = null;
		MyNode a_AST = null;
		
		try {      // for error handling
			boolean synPredMatched539 = false;
			if (((LA(1)==B_IDENTIFIER) && (LA(2)==B_INSET))) {
				int _m539 = mark();
				synPredMatched539 = true;
				inputState.guessing++;
				try {
					{
					match(B_IDENTIFIER);
					match(B_INSET);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched539 = false;
				}
				rewind(_m539);
inputState.guessing--;
			}
			if ( synPredMatched539 ) {
				MyNode tmp442_AST = null;
				tmp442_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp442_AST);
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
			else if ((_tokenSet_139.member(LA(1))) && (_tokenSet_145.member(LA(2)))) {
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
				recover(ex,_tokenSet_146);
			} else {
			  throw ex;
			}
		}
		returnAST = a_record_AST;
	}
	
	public final void new_predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode new_predicate_AST = null;
		
		try {      // for error handling
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop536:
			do {
				if ((LA(1)==B_SEMICOLON||LA(1)==B_PARALLEL)) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp443_AST = null;
						tmp443_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp443_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp444_AST = null;
						tmp444_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp444_AST);
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
					break _loop536;
				}
				
			} while (true);
			}
			new_predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
			} else {
			  throw ex;
			}
		}
		returnAST = new_predicate_AST;
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
		"\"SYSTEM\"",
		"\"EVENTS\"",
		"\"MODALITIES\"",
		"B_LEADSTO",
		"\"DYNAMICS\"",
		"B_PREC"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 4114L, 2308094809027379200L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 0L, 2305843009213693952L, -5764537154256519201L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 0L, 2305843009213693952L, -5764607523000696993L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 0L, 2305843009213693952L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 2L, 2305843009213693952L, -5764607523000696993L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 1224979099181909010L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 0L, 0L, 524288L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 0L, 2305843009213693952L, 1152991873384579067L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 0L, 2305843009213693952L, 1152991873384562651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 2L, 2305843009213693952L, 1152991873384562651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 0L, 2305843009213693952L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 2L, 2305843009213693952L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 1423137481846423314L, 2305843009213700100L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 1423137481846423314L, 2305843009213700116L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { -206L, 2423147707905343487L, -1151814296431230977L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { -14L, 2425399507719028735L, -1151814296431230977L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 536870912L, 2314850208468434944L, -5764500870372786177L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 3728980491328552850L, 2423006968271607828L, -1152387875824730113L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 3530822108395339794L, 2305843009213700096L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { 268435472L, 2305843009213693952L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { 16L, 2305843009213693952L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { 536870912L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { 0L, 2305843009213696000L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { 13792273858822144L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { -4598175219276840928L, 1050603L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = { -3535044232106344224L, 2305983748847431679L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = { -3530822107858468878L, 2306054117593710591L, -5763821875301974017L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = { -3679440895293128672L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = { 13792273858822144L, 2305843009213696000L, -5764537154256502785L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = { -3679440895158910942L, 140739633735659L, 715278954528768L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = { 1152921504606846976L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = { 3692951694041153298L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = { 3548836505965297426L, 2305913377959974912L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = { 1152921504606846992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = { 1153062242632073232L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = { 0L, 2305843009213696000L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = { 0L, 140739631644672L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = { -9214364837331599328L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = { -8061302595504832464L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = { -3535044232106344224L, 140739633735679L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = { -16L, 211108380016639L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = { -3535325707083054880L, 2305983748847431679L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = { -3530822107858468878L, 2306054117593710591L, 1153625229864462322L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = { 3548836505965297426L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = { 2L, 2305843009213696000L, -5764607523000696993L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = { -3679440895158910944L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = { -3530822108395339790L, 2306054117593706495L, -5764607523000696993L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = { -14L, 2306054117593710591L, -5764595931487944865L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = { 32L, 0L, 11591512752128L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = { -3679440895158910862L, 2423077339161165819L, -5764595931487944865L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = { -3530822108395339790L, 2423147707905341439L, 30369646641152L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = { 2L, 2305843009213696000L, -5764602828601442465L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = { 2L, 2305843009213696000L, 1152991873384562651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = { -3530822108395339790L, 2306054117593706495L, 1152991873384562651L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = { -14L, 2306054117593710591L, 1153566414850736091L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = { 32L, 0L, 574541466173440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = { -3679440895158910862L, 2423077339161161723L, 1153566414850736091L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	private static final long[] mk_tokenSet_58() {
		long[] data = { -3530822108395339790L, 2423147707905343487L, 628503972151296L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
	private static final long[] mk_tokenSet_59() {
		long[] data = { 2L, 2305843009213696000L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
	private static final long[] mk_tokenSet_60() {
		long[] data = { -3530822108395339790L, 2306054117593706495L, 1152921504640395250L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
	private static final long[] mk_tokenSet_61() {
		long[] data = { -14L, 2306054117593710591L, 1153625229864462322L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
	private static final long[] mk_tokenSet_62() {
		long[] data = { 32L, 0L, 703725224067072L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
	private static final long[] mk_tokenSet_63() {
		long[] data = { -3679440895158910862L, 2387048542140100603L, 1153625229864462322L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
	private static final long[] mk_tokenSet_64() {
		long[] data = { -3530822108395339790L, 2387118910886375423L, 1020453829214208L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());
	private static final long[] mk_tokenSet_65() {
		long[] data = { 0L, 2305843009213693952L, 1152996567783817179L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());
	private static final long[] mk_tokenSet_66() {
		long[] data = { 0L, 2305843009213693952L, 1152992169737316338L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());
	private static final long[] mk_tokenSet_67() {
		long[] data = { 80L, 9007199254740992L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());
	private static final long[] mk_tokenSet_68() {
		long[] data = { 4096L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());
	private static final long[] mk_tokenSet_69() {
		long[] data = { 2L, 2305843009213700096L, -5764602828601442465L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());
	private static final long[] mk_tokenSet_70() {
		long[] data = { 2L, 2305843009213700096L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());
	private static final long[] mk_tokenSet_71() {
		long[] data = { 268435536L, 72057594037927952L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_71 = new BitSet(mk_tokenSet_71());
	private static final long[] mk_tokenSet_72() {
		long[] data = { -3679440895158910944L, 140739632687083L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_72 = new BitSet(mk_tokenSet_72());
	private static final long[] mk_tokenSet_73() {
		long[] data = { 268435536L, 9007199254741008L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_73 = new BitSet(mk_tokenSet_73());
	private static final long[] mk_tokenSet_74() {
		long[] data = { 268435536L, 2097168L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_74 = new BitSet(mk_tokenSet_74());
	private static final long[] mk_tokenSet_75() {
		long[] data = { 268435536L, 36028797018963984L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_75 = new BitSet(mk_tokenSet_75());
	private static final long[] mk_tokenSet_76() {
		long[] data = { 268435522L, 2305843009213700112L, -5764532459857264673L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_76 = new BitSet(mk_tokenSet_76());
	private static final long[] mk_tokenSet_77() {
		long[] data = { -3679440895158910942L, 2305983748847435755L, -5763957918391091233L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_77 = new BitSet(mk_tokenSet_77());
	private static final long[] mk_tokenSet_78() {
		long[] data = { 2L, 2305843009213700096L, -5764532459857264673L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_78 = new BitSet(mk_tokenSet_78());
	private static final long[] mk_tokenSet_79() {
		long[] data = { 2L, 2305843009213700096L, 1152996567783817179L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_79 = new BitSet(mk_tokenSet_79());
	private static final long[] mk_tokenSet_80() {
		long[] data = { 2L, 2305843009213696000L, 1152992169737316338L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_80 = new BitSet(mk_tokenSet_80());
	private static final long[] mk_tokenSet_81() {
		long[] data = { 268435522L, 2305843009213696016L, 1152992169737316338L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_81 = new BitSet(mk_tokenSet_81());
	private static final long[] mk_tokenSet_82() {
		long[] data = { -3679440895158910942L, 2305983748847431659L, 1153695894961383410L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_82 = new BitSet(mk_tokenSet_82());
	private static final long[] mk_tokenSet_83() {
		long[] data = { 0L, 2305843009213693952L, 279172874240L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_83 = new BitSet(mk_tokenSet_83());
	private static final long[] mk_tokenSet_84() {
		long[] data = { 0L, 2305843009213693952L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_84 = new BitSet(mk_tokenSet_84());
	private static final long[] mk_tokenSet_85() {
		long[] data = { 0L, 2305843009213693952L, 292057776128L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_85 = new BitSet(mk_tokenSet_85());
	private static final long[] mk_tokenSet_86() {
		long[] data = { 0L, 2305843009213693952L, 4672924418048L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_86 = new BitSet(mk_tokenSet_86());
	private static final long[] mk_tokenSet_87() {
		long[] data = { 2305843009750564928L, 0L, 17592186044416L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_87 = new BitSet(mk_tokenSet_87());
	private static final long[] mk_tokenSet_88() {
		long[] data = { 0L, 0L, 35184372088832L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_88 = new BitSet(mk_tokenSet_88());
	private static final long[] mk_tokenSet_89() {
		long[] data = { 1024L, 0L, 35184372088832L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_89 = new BitSet(mk_tokenSet_89());
	private static final long[] mk_tokenSet_90() {
		long[] data = { 36028797018963970L, 2305843009213700096L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_90 = new BitSet(mk_tokenSet_90());
	private static final long[] mk_tokenSet_91() {
		long[] data = { 36028797018963970L, 2305843009213700100L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_91 = new BitSet(mk_tokenSet_91());
	private static final long[] mk_tokenSet_92() {
		long[] data = { 36028797287399426L, 2305843009213700100L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_92 = new BitSet(mk_tokenSet_92());
	private static final long[] mk_tokenSet_93() {
		long[] data = { -3679440895158910942L, 2305983748847435755L, -5763817180902719489L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_93 = new BitSet(mk_tokenSet_93());
	private static final long[] mk_tokenSet_94() {
		long[] data = { -3458764513820540942L, 2425399507719028735L, -5763817180365848577L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_94 = new BitSet(mk_tokenSet_94());
	private static final long[] mk_tokenSet_95() {
		long[] data = { 3728980491328552722L, 2305913377959974932L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_95 = new BitSet(mk_tokenSet_95());
	private static final long[] mk_tokenSet_96() {
		long[] data = { 18L, 2422936599527430144L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_96 = new BitSet(mk_tokenSet_96());
	private static final long[] mk_tokenSet_97() {
		long[] data = { 18L, 2422936599527430160L, -5764532459857248257L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_97 = new BitSet(mk_tokenSet_97());
	private static final long[] mk_tokenSet_98() {
		long[] data = { 72057594575060992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_98 = new BitSet(mk_tokenSet_98());
	private static final long[] mk_tokenSet_99() {
		long[] data = { 0L, 117093590313730048L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_99 = new BitSet(mk_tokenSet_99());
	private static final long[] mk_tokenSet_100() {
		long[] data = { 3530822108395340050L, 2305843009213700096L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_100 = new BitSet(mk_tokenSet_100());
	private static final long[] mk_tokenSet_101() {
		long[] data = { 3530822108395341586L, 2305843009213700096L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_101 = new BitSet(mk_tokenSet_101());
	private static final long[] mk_tokenSet_102() {
		long[] data = { -3530822108395347728L, 211108380010495L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_102 = new BitSet(mk_tokenSet_102());
	private static final long[] mk_tokenSet_103() {
		long[] data = { -122894L, 2306054117591613439L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_103 = new BitSet(mk_tokenSet_103());
	private static final long[] mk_tokenSet_104() {
		long[] data = { -14L, 2306054117593710591L, -1151814433870184449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_104 = new BitSet(mk_tokenSet_104());
	private static final long[] mk_tokenSet_105() {
		long[] data = { 3530822108395347730L, 2305843009213700096L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_105 = new BitSet(mk_tokenSet_105());
	private static final long[] mk_tokenSet_106() {
		long[] data = { 122880L, 2097152L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_106 = new BitSet(mk_tokenSet_106());
	private static final long[] mk_tokenSet_107() {
		long[] data = { 4063232L, 70368744177664L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_107 = new BitSet(mk_tokenSet_107());
	private static final long[] mk_tokenSet_108() {
		long[] data = { 3530822108395470610L, 2305843009215797248L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_108 = new BitSet(mk_tokenSet_108());
	private static final long[] mk_tokenSet_109() {
		long[] data = { 3530822108399533842L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_109 = new BitSet(mk_tokenSet_109());
	private static final long[] mk_tokenSet_110() {
		long[] data = { 3530822108529557266L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_110 = new BitSet(mk_tokenSet_110());
	private static final long[] mk_tokenSet_111() {
		long[] data = { 3530822382333722386L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_111 = new BitSet(mk_tokenSet_111());
	private static final long[] mk_tokenSet_112() {
		long[] data = { 3530857291827904274L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_112 = new BitSet(mk_tokenSet_112());
	private static final long[] mk_tokenSet_113() {
		long[] data = { 3530962844944170770L, 2305913377959974912L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_113 = new BitSet(mk_tokenSet_113());
	private static final long[] mk_tokenSet_114() {
		long[] data = { -206L, 2306054117593710591L, -1151814433870184449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_114 = new BitSet(mk_tokenSet_114());
	private static final long[] mk_tokenSet_115() {
		long[] data = { -14L, 2306054117593710591L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_115 = new BitSet(mk_tokenSet_115());
	private static final long[] mk_tokenSet_116() {
		long[] data = { 2305843009213694000L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_116 = new BitSet(mk_tokenSet_116());
	private static final long[] mk_tokenSet_117() {
		long[] data = { -3679440894622040016L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_117 = new BitSet(mk_tokenSet_117());
	private static final long[] mk_tokenSet_118() {
		long[] data = { -2377900603788492816L, 140739632687103L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_118 = new BitSet(mk_tokenSet_118());
	private static final long[] mk_tokenSet_119() {
		long[] data = { 805306416L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_119 = new BitSet(mk_tokenSet_119());
	private static final long[] mk_tokenSet_120() {
		long[] data = { 2305843009213694016L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_120 = new BitSet(mk_tokenSet_120());
	private static final long[] mk_tokenSet_121() {
		long[] data = { 3692951694041153298L, 2305913377959974916L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_121 = new BitSet(mk_tokenSet_121());
	private static final long[] mk_tokenSet_122() {
		long[] data = { 3728980491060117266L, 2305913377959974916L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_122 = new BitSet(mk_tokenSet_122());
	private static final long[] mk_tokenSet_123() {
		long[] data = { 3728980491060117266L, 2305913377959974932L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_123 = new BitSet(mk_tokenSet_123());
	private static final long[] mk_tokenSet_124() {
		long[] data = { 3728980491328552722L, 2305913377959974932L, -5764532459320377345L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_124 = new BitSet(mk_tokenSet_124());
	private static final long[] mk_tokenSet_125() {
		long[] data = { 16L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_125 = new BitSet(mk_tokenSet_125());
	private static final long[] mk_tokenSet_126() {
		long[] data = { -2305843009213693966L, 2305983748846387199L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_126 = new BitSet(mk_tokenSet_126());
	private static final long[] mk_tokenSet_127() {
		long[] data = { 1224979099181909266L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_127 = new BitSet(mk_tokenSet_127());
	private static final long[] mk_tokenSet_128() {
		long[] data = { 1224979099181909778L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_128 = new BitSet(mk_tokenSet_128());
	private static final long[] mk_tokenSet_129() {
		long[] data = { -14L, 2423147707905343487L, -1151814296431230977L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_129 = new BitSet(mk_tokenSet_129());
	private static final long[] mk_tokenSet_130() {
		long[] data = { -2305843009213816846L, 2305983748846387199L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_130 = new BitSet(mk_tokenSet_130());
	private static final long[] mk_tokenSet_131() {
		long[] data = { 1224979099181915922L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_131 = new BitSet(mk_tokenSet_131());
	private static final long[] mk_tokenSet_132() {
		long[] data = { 1224979099182038802L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_132 = new BitSet(mk_tokenSet_132());
	private static final long[] mk_tokenSet_133() {
		long[] data = { 1224979099185839890L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_133 = new BitSet(mk_tokenSet_133());
	private static final long[] mk_tokenSet_134() {
		long[] data = { 1224979099315863314L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_134 = new BitSet(mk_tokenSet_134());
	private static final long[] mk_tokenSet_135() {
		long[] data = { 1224979373120028434L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_135 = new BitSet(mk_tokenSet_135());
	private static final long[] mk_tokenSet_136() {
		long[] data = { 1225014282614210322L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_136 = new BitSet(mk_tokenSet_136());
	private static final long[] mk_tokenSet_137() {
		long[] data = { 1225119835730476818L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_137 = new BitSet(mk_tokenSet_137());
	private static final long[] mk_tokenSet_138() {
		long[] data = { 1242993496751603474L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_138 = new BitSet(mk_tokenSet_138());
	private static final long[] mk_tokenSet_139() {
		long[] data = { -3679440895293128672L, 140739632687083L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_139 = new BitSet(mk_tokenSet_139());
	private static final long[] mk_tokenSet_140() {
		long[] data = { 1387108684827459346L, 2305843009213700096L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_140 = new BitSet(mk_tokenSet_140());
	private static final long[] mk_tokenSet_141() {
		long[] data = { -14L, 2305983748847435775L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_141 = new BitSet(mk_tokenSet_141());
	private static final long[] mk_tokenSet_142() {
		long[] data = { 3692951694041153298L, 2305913377959974912L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_142 = new BitSet(mk_tokenSet_142());
	private static final long[] mk_tokenSet_143() {
		long[] data = { 1387108684827459346L, 2305843009213700100L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_143 = new BitSet(mk_tokenSet_143());
	private static final long[] mk_tokenSet_144() {
		long[] data = { 1423137482114858770L, 2305843009213700116L, -1152388975336357889L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_144 = new BitSet(mk_tokenSet_144());
	private static final long[] mk_tokenSet_145() {
		long[] data = { -3530822107992555280L, 140739632687103L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_145 = new BitSet(mk_tokenSet_145());
	private static final long[] mk_tokenSet_146() {
		long[] data = { 536870928L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_146 = new BitSet(mk_tokenSet_146());
	
	}
