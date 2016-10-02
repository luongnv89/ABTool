// $ANTLR 2.7.6 (2005-12-22): "expandedBModel.g" -> "BModelParser.java"$

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

public class BModelParser extends antlr.LLkParser       implements BModelLexerTokenTypes
 {

	String module = "BModel.g";

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

protected BModelParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BModelParser(TokenBuffer tokenBuf) {
  this(tokenBuf,7);
}

protected BModelParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BModelParser(TokenStream lexer) {
  this(lexer,7);
}

public BModelParser(ParserSharedInputState state) {
  super(state,7);
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
			case LITERAL_MODEL:
			{
				model();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LITERAL_REFINEMENT:
			{
				refinement();
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
	
	public final void model() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode model_AST = null;
		
		try {      // for error handling
			MyNode tmp1_AST = null;
			tmp1_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp1_AST);
			match(LITERAL_MODEL);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5:
			do {
				switch ( LA(1)) {
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
				case LITERAL_INVARIANT:
				{
					invariant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_OBSERVABLES:
				{
					observables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_OBSERVATIONS:
				{
					observations();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ASSERTIONS:
				{
					assertions();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_MODALITIES:
				{
					modalities();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_INITIALISATION:
				{
					initialisation_mch();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_EVENTS:
				{
					events();
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
			model_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = model_AST;
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
			_loop8:
			do {
				switch ( LA(1)) {
				case LITERAL_REFINES:
				{
					refines();
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
				case LITERAL_INVARIANT:
				{
					invariant();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_OBSERVABLES:
				{
					observables();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_OBSERVATIONS:
				{
					observations();
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
				case LITERAL_MODALITIES:
				{
					modalities();
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
					events();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop8;
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
			case LITERAL_OPERATIONS:
			case LITERAL_VARIANT:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
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
	
	public final void sets() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode sets_AST = null;
		
		try {      // for error handling
			if ( inputState.guessing==0 ) {
				
					ChooseMetrics(mset);
				
			}
			MyNode tmp8_AST = null;
			tmp8_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp8_AST);
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
				recover(ex,_tokenSet_2);
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
				MyNode tmp9_AST = null;
				tmp9_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp9_AST);
				match(LITERAL_CONSTANTS);
				break;
			}
			case LITERAL_CONCRETE_CONSTANTS:
			{
				MyNode tmp10_AST = null;
				tmp10_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp10_AST);
				match(LITERAL_CONCRETE_CONSTANTS);
				break;
			}
			case LITERAL_VISIBLE_CONSTANTS:
			{
				MyNode tmp11_AST = null;
				tmp11_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp11_AST);
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
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = constants_AST;
	}
	
	public final void properties() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode properties_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp12_AST = null;
			tmp12_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp12_AST);
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
				recover(ex,_tokenSet_2);
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
				MyNode tmp13_AST = null;
				tmp13_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(LITERAL_VARIABLES);
				break;
			}
			case LITERAL_ABSTRACT_VARIABLES:
			{
				MyNode tmp14_AST = null;
				tmp14_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp14_AST);
				match(LITERAL_ABSTRACT_VARIABLES);
				break;
			}
			case LITERAL_VISIBLE_VARIABLES:
			{
				MyNode tmp15_AST = null;
				tmp15_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp15_AST);
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
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = variables_AST;
	}
	
	public final void invariant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode invariant_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp16_AST = null;
			tmp16_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp16_AST);
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
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = invariant_AST;
	}
	
	public final void observables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode observables_AST = null;
		
		try {      // for error handling
			MyNode tmp17_AST = null;
			tmp17_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp17_AST);
			match(LITERAL_OBSERVABLES);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			observables_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = observables_AST;
	}
	
	public final void observations() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode observations_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp18_AST = null;
			tmp18_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp18_AST);
			match(LITERAL_OBSERVATIONS);
			expression();
			t1_AST = (MyNode)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				
					previous_gop.putI(t1_AST);	// We memorize the node Invariant for GOP
				
			}
			observations_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = observations_AST;
	}
	
	public final void assertions() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assertions_AST = null;
		
		try {      // for error handling
			MyNode tmp19_AST = null;
			tmp19_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp19_AST);
			match(LITERAL_ASSERTIONS);
			list_assertions();
			astFactory.addASTChild(currentAST, returnAST);
			assertions_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = assertions_AST;
	}
	
	public final void modalities() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode modalities_AST = null;
		
		try {      // for error handling
			MyNode tmp20_AST = null;
			tmp20_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp20_AST);
			match(LITERAL_MODALITIES);
			modality();
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
	
	public final void initialisation_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode initialisation_mch_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp21_AST = null;
			tmp21_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp21_AST);
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
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_mch_AST;
	}
	
	public final void events() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode events_AST = null;
		
		try {      // for error handling
			MyNode tmp22_AST = null;
			tmp22_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp22_AST);
			match(LITERAL_EVENTS);
			listEvent();
			astFactory.addASTChild(currentAST, returnAST);
			events_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = events_AST;
	}
	
	public final void refines() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode refines_AST = null;
		Token  t1 = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp23_AST = null;
			tmp23_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp23_AST);
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
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = refines_AST;
	}
	
	protected final void variant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode variant_AST = null;
		
		try {      // for error handling
			MyNode tmp24_AST = null;
			tmp24_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp24_AST);
			match(LITERAL_VARIANT);
			a_variant();
			astFactory.addASTChild(currentAST, returnAST);
			variant_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = variant_AST;
	}
	
	public final void initialisation_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode initialisation_ref_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp25_AST = null;
			tmp25_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp25_AST);
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
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_ref_AST;
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
			_loop75:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp26_AST = null;
					tmp26_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp26_AST);
					match(B_COMMA);
					nameRenamed();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							AddMetrics();
						
					}
				}
				else {
					break _loop75;
				}
				
			} while (true);
			}
			listIdentifier_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = listIdentifier_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode expression_AST = null;
		
		try {      // for error handling
			logical_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop315:
			do {
				if ((LA(1)==B_IMPLIES)) {
					MyNode tmp27_AST = null;
					tmp27_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp27_AST);
					match(B_IMPLIES);
					logical_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop315;
				}
				
			} while (true);
			}
			expression_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = expression_AST;
	}
	
	public final void listEvent() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listEvent_AST = null;
		
		try {      // for error handling
			event();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop14:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp28_AST = null;
					tmp28_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp28_AST);
					match(B_SEMICOLON);
					event();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop14;
				}
				
			} while (true);
			}
			listEvent_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = listEvent_AST;
	}
	
	public final void event() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode event_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			c = LT(1);
			c_AST = (MyNode)astFactory.create(c);
			astFactory.makeASTRoot(currentAST, c_AST);
			match(B_EQUAL);
			if ( inputState.guessing==0 ) {
				
					c_AST.setType(OP_DEF);
				
			}
			substitution_event();
			astFactory.addASTChild(currentAST, returnAST);
			event_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = event_AST;
	}
	
	public final void nameRenamed() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode nameRenamed_AST = null;
		
		try {      // for error handling
			MyNode tmp29_AST = null;
			tmp29_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp29_AST);
			match(B_IDENTIFIER);
			{
			_loop411:
			do {
				if ((LA(1)==B_POINT)) {
					MyNode tmp30_AST = null;
					tmp30_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp30_AST);
					match(B_POINT);
					MyNode tmp31_AST = null;
					tmp31_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp31_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop411;
				}
				
			} while (true);
			}
			nameRenamed_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		returnAST = nameRenamed_AST;
	}
	
	public final void substitution_event() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_event_AST = null;
		
		try {      // for error handling
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			substitution_event_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_event_AST;
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
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_mch_AST;
	}
	
	public final void modality() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode modality_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_ANY:
			{
				MyNode tmp32_AST = null;
				tmp32_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp32_AST);
				match(LITERAL_ANY);
				listIdentifier();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_WHERE);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_THEN);
				event_list();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_MAINTAIN:
				{
					maintain();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ESTABLISH:
				{
					establish();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_END);
				modality_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BEGIN:
			{
				MyNode tmp36_AST = null;
				tmp36_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp36_AST);
				match(LITERAL_BEGIN);
				event_list();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_MAINTAIN:
				{
					maintain();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case LITERAL_ESTABLISH:
				{
					establish();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(LITERAL_END);
				modality_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_SELECT:
			{
				MyNode tmp38_AST = null;
				tmp38_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp38_AST);
				match(LITERAL_SELECT);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_THEN);
				event_list();
				astFactory.addASTChild(currentAST, returnAST);
				establish();
				astFactory.addASTChild(currentAST, returnAST);
				match(LITERAL_END);
				modality_AST = (MyNode)currentAST.root;
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
		returnAST = modality_AST;
	}
	
	public final void event_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode event_list_AST = null;
		Token  b = null;
		MyNode b_AST = null;
		
		try {      // for error handling
			MyNode tmp41_AST = null;
			tmp41_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp41_AST);
			match(B_IDENTIFIER);
			{
			_loop25:
			do {
				if ((LA(1)==LITERAL_OR)) {
					b = LT(1);
					b_AST = (MyNode)astFactory.create(b);
					astFactory.makeASTRoot(currentAST, b_AST);
					match(LITERAL_OR);
					MyNode tmp42_AST = null;
					tmp42_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp42_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop25;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
					b_AST.setType(B_OR_EVENT);
				
			}
			event_list_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			} else {
			  throw ex;
			}
		}
		returnAST = event_list_AST;
	}
	
	public final void maintain() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode maintain_AST = null;
		
		try {      // for error handling
			MyNode tmp43_AST = null;
			tmp43_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp43_AST);
			match(LITERAL_MAINTAIN);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_UNTIL);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_VARIANT);
			a_variant();
			astFactory.addASTChild(currentAST, returnAST);
			maintain_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = maintain_AST;
	}
	
	public final void establish() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode establish_AST = null;
		
		try {      // for error handling
			MyNode tmp46_AST = null;
			tmp46_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp46_AST);
			match(LITERAL_ESTABLISH);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			establish_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		returnAST = establish_AST;
	}
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predicate_AST = null;
		
		try {      // for error handling
			plogical_1();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop416:
			do {
				if ((LA(1)==B_IMPLIES)) {
					MyNode tmp47_AST = null;
					tmp47_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp47_AST);
					match(B_IMPLIES);
					plogical_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop416;
				}
				
			} while (true);
			}
			predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		returnAST = predicate_AST;
	}
	
	protected final void a_variant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode a_variant_AST = null;
		
		try {      // for error handling
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			a_variant_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = a_variant_AST;
	}
	
	public final void events_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode events_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp48_AST = null;
			tmp48_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp48_AST);
			match(LITERAL_EVENTS);
			listEventRef();
			astFactory.addASTChild(currentAST, returnAST);
			events_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = events_ref_AST;
	}
	
	public final void listEventRef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listEventRef_AST = null;
		
		try {      // for error handling
			eventRef();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop29:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp49_AST = null;
					tmp49_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp49_AST);
					match(B_SEMICOLON);
					eventRef();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
			listEventRef_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = listEventRef_AST;
	}
	
	public final void eventRef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode eventRef_AST = null;
		Token  c = null;
		MyNode c_AST = null;
		
		try {      // for error handling
			an_event_ref();
			astFactory.addASTChild(currentAST, returnAST);
			c = LT(1);
			c_AST = (MyNode)astFactory.create(c);
			astFactory.makeASTRoot(currentAST, c_AST);
			match(B_EQUAL);
			if ( inputState.guessing==0 ) {
				
					c_AST.setType(OP_DEF);
				
			}
			substitution_event_ref();
			astFactory.addASTChild(currentAST, returnAST);
			eventRef_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = eventRef_AST;
	}
	
	public final void an_event_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode an_event_ref_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case B_ref:
			{
				MyNode tmp50_AST = null;
				tmp50_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp50_AST);
				match(B_ref);
				listNameRenamed();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case B_EQUAL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			an_event_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = an_event_ref_AST;
	}
	
	public final void substitution_event_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_event_ref_AST = null;
		
		try {      // for error handling
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			substitution_event_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_event_ref_AST;
	}
	
	public final void listNameRenamed() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listNameRenamed_AST = null;
		
		try {      // for error handling
			nameRenamed();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop35:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp51_AST = null;
					tmp51_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp51_AST);
					match(B_COMMA);
					nameRenamed();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop35;
				}
				
			} while (true);
			}
			listNameRenamed_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		returnAST = listNameRenamed_AST;
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
			_loop204:
			do {
				if ((LA(1)==B_SEMICOLON||LA(1)==B_PARALLEL) && (_tokenSet_16.member(LA(2))) && (_tokenSet_17.member(LA(3))) && (_tokenSet_18.member(LA(4))) && (_tokenSet_19.member(LA(5))) && (_tokenSet_20.member(LA(6))) && (_tokenSet_20.member(LA(7)))) {
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
					break _loop204;
				}
				
			} while (true);
			}
			parallele_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_21);
			} else {
			  throw ex;
			}
		}
		returnAST = parallele_ref_AST;
	}
	
	protected final void substitution_block_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_block_mch_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_BEGIN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				MyNode tmp52_AST = null;
				tmp52_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp52_AST);
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_BEGIN_POST);
				}
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
			substitution_block_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_block_mch_AST;
	}
	
	protected final void substitution_block_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_block_ref_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_BEGIN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_BEGIN_POST);
				}
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
			substitution_block_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_block_ref_AST;
	}
	
	protected final void substitution_precondition_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_precondition_mch_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_PRE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_PRE_POST);
				}
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
			substitution_precondition_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_precondition_mch_AST;
	}
	
	protected final void substitution_precondition_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_precondition_ref_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_PRE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_PRE_POST);
				}
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
			substitution_precondition_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_precondition_ref_AST;
	}
	
	protected final void substitution_selection_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_selection_mch_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_SELECT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
					rr_AST.setType(B_SELECT_POST);
					
				}
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
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_selection_mch_AST;
	}
	
	protected final void substitution_selection_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_selection_ref_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_SELECT);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_SELECT_POST);
				}
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
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_selection_ref_AST;
	}
	
	protected final void substitution_unbounded_choice_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_unbounded_choice_mch_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_ANY);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_WHERE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_ANY_POST);
				}
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
			substitution_unbounded_choice_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_unbounded_choice_mch_AST;
	}
	
	protected final void substitution_unbounded_choice_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode substitution_unbounded_choice_ref_AST = null;
		Token  rr = null;
		MyNode rr_AST = null;
		
		try {      // for error handling
			rr = LT(1);
			rr_AST = (MyNode)astFactory.create(rr);
			astFactory.makeASTRoot(currentAST, rr_AST);
			match(LITERAL_ANY);
			listIdentifier();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_WHERE);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case LITERAL_POST:
			{
				match(LITERAL_POST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					rr_AST.setType(B_ANY_POST);
				}
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
			substitution_unbounded_choice_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = substitution_unbounded_choice_ref_AST;
	}
	
	protected final void predInvertedParamInvertedQuoted() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode predInvertedParamInvertedQuoted_AST = null;
		
		try {      // for error handling
			predInvertedParamInverted();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop57:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp76_AST = null;
					tmp76_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp76_AST);
					match(B_QUOTEIDENT);
					predInvertedParamInverted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop57;
				}
				
			} while (true);
			}
			predInvertedParamInvertedQuoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_24);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_25.member(LA(2))) && (_tokenSet_26.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				MyNode tmp77_AST = null;
				tmp77_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp77_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_25.member(LA(1))) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3))) && (_tokenSet_28.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_25);
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
			switch ( LA(1)) {
			case B_OR_EVENT:
			{
				MyNode tmp78_AST = null;
				tmp78_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp78_AST);
				match(B_OR_EVENT);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_ANY_POST:
			{
				MyNode tmp79_AST = null;
				tmp79_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp79_AST);
				match(B_ANY_POST);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_SELECT_POST:
			{
				MyNode tmp80_AST = null;
				tmp80_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp80_AST);
				match(B_SELECT_POST);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_PRE_POST:
			{
				MyNode tmp81_AST = null;
				tmp81_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp81_AST);
				match(B_PRE_POST);
				dummy_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BEGIN_POST:
			{
				MyNode tmp82_AST = null;
				tmp82_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp82_AST);
				match(B_BEGIN_POST);
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
	
	public final void machine() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode machine_AST = null;
		
		try {      // for error handling
			MyNode tmp83_AST = null;
			tmp83_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp83_AST);
			match(LITERAL_MACHINE);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop61:
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
				default:
				{
					break _loop61;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_OPERATIONS:
			{
				operations_mch();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_DEFINITIONS:
				{
					definitions_mch();
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
	
	public final void constraints() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode constraints_AST = null;
		MyNode t1_AST = null;
		
		try {      // for error handling
			MyNode tmp86_AST = null;
			tmp86_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp86_AST);
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
				recover(ex,_tokenSet_29);
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
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
		returnAST = mchlink_AST;
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
				MyNode tmp87_AST = null;
				tmp87_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp87_AST);
				match(LITERAL_ABSTRACT_CONSTANTS);
				break;
			}
			case LITERAL_HIDDEN_CONSTANTS:
			{
				MyNode tmp88_AST = null;
				tmp88_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp88_AST);
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
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
		returnAST = aconstants_AST;
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
				MyNode tmp89_AST = null;
				tmp89_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp89_AST);
				match(LITERAL_HIDDEN_VARIABLES);
				break;
			}
			case LITERAL_CONCRETE_VARIABLES:
			{
				MyNode tmp90_AST = null;
				tmp90_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp90_AST);
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
				recover(ex,_tokenSet_30);
			} else {
			  throw ex;
			}
		}
		returnAST = cvariables_AST;
	}
	
	public final void definitions_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp91_AST = null;
			tmp91_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp91_AST);
			match(LITERAL_DEFINITIONS);
			list_def_mch();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_mch_AST;
	}
	
	public final void operations_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operations_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp92_AST = null;
			tmp92_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp92_AST);
			match(LITERAL_OPERATIONS);
			listOperationMch();
			astFactory.addASTChild(currentAST, returnAST);
			operations_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = operations_mch_AST;
	}
	
	public final void implementation() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode implementation_AST = null;
		
		try {      // for error handling
			MyNode tmp93_AST = null;
			tmp93_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp93_AST);
			match(LITERAL_IMPLEMENTATION);
			paramName();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop66:
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
				default:
				{
					break _loop66;
				}
				}
			} while (true);
			}
			{
			switch ( LA(1)) {
			case LITERAL_OPERATIONS:
			{
				operations_imp();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LITERAL_DEFINITIONS:
				{
					definitions_imp();
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
				recover(ex,_tokenSet_32);
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
			MyNode tmp96_AST = null;
			tmp96_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp96_AST);
			match(LITERAL_VALUES);
			list_valuation();
			astFactory.addASTChild(currentAST, returnAST);
			values_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
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
			MyNode tmp97_AST = null;
			tmp97_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp97_AST);
			match(LITERAL_DEFINITIONS);
			list_def_imp();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
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
			MyNode tmp98_AST = null;
			tmp98_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp98_AST);
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
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		returnAST = initialisation_imp_AST;
	}
	
	public final void operations_imp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode operations_imp_AST = null;
		
		try {      // for error handling
			MyNode tmp99_AST = null;
			tmp99_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp99_AST);
			match(LITERAL_OPERATIONS);
			listOperationImp();
			astFactory.addASTChild(currentAST, returnAST);
			operations_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = operations_imp_AST;
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
				MyNode tmp100_AST = null;
				tmp100_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp100_AST);
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
	
	public final void uses() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode uses_AST = null;
		
		try {      // for error handling
			MyNode tmp102_AST = null;
			tmp102_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp102_AST);
			match(LITERAL_USES);
			listNames("USES");
			astFactory.addASTChild(currentAST, returnAST);
			uses_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_29);
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
			MyNode tmp103_AST = null;
			tmp103_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp103_AST);
			match(LITERAL_INCLUDES);
			listInstanciation("INCLUDES");
			astFactory.addASTChild(currentAST, returnAST);
			includes_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
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
			MyNode tmp104_AST = null;
			tmp104_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp104_AST);
			match(LITERAL_SEES);
			listNames("SEES");
			astFactory.addASTChild(currentAST, returnAST);
			sees_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
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
			MyNode tmp105_AST = null;
			tmp105_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp105_AST);
			match(LITERAL_EXTENDS);
			listInstanciation("EXTENDS");
			astFactory.addASTChild(currentAST, returnAST);
			extendeds_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
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
			MyNode tmp106_AST = null;
			tmp106_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp106_AST);
			match(LITERAL_PROMOTES);
			listNames("PROMOTES");
			astFactory.addASTChild(currentAST, returnAST);
			promotes_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
		returnAST = promotes_AST;
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
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = reflink_AST;
	}
	
	public final void imports() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode imports_AST = null;
		
		try {      // for error handling
			MyNode tmp107_AST = null;
			tmp107_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp107_AST);
			match(LITERAL_IMPORTS);
			listInstanciation("IMPORTS");
			astFactory.addASTChild(currentAST, returnAST);
			imports_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
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
			_loop96:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp108_AST = null;
					tmp108_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp108_AST);
					match(B_COMMA);
					paramRenameValuation(type);
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop96;
				}
				
			} while (true);
			}
			listInstanciation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
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
			_loop86:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp109_AST = null;
					tmp109_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp109_AST);
					match(B_COMMA);
					nameRenamedWithSave(type);
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop86;
				}
				
			} while (true);
			}
			listNames_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
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
			boolean synPredMatched89 = false;
			if (((LA(1)==B_IDENTIFIER) && (LA(2)==B_POINT))) {
				int _m89 = mark();
				synPredMatched89 = true;
				inputState.guessing++;
				try {
					{
					match(B_IDENTIFIER);
					match(B_POINT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched89 = false;
				}
				rewind(_m89);
inputState.guessing--;
			}
			if ( synPredMatched89 ) {
				MyNode tmp110_AST = null;
				tmp110_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp110_AST);
				match(B_IDENTIFIER);
				{
				MyNode tmp111_AST = null;
				tmp111_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp111_AST);
				match(B_POINT);
				nameRenamedWithSave(type);
				astFactory.addASTChild(currentAST, returnAST);
				}
				nameRenamedWithSave_AST = (MyNode)currentAST.root;
			}
			else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_35.member(LA(2)))) {
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
				recover(ex,_tokenSet_35);
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
			_loop93:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp112_AST = null;
					tmp112_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp112_AST);
					match(B_COMMA);
					paramName();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop93;
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
			_loop99:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp113_AST = null;
					tmp113_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp113_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop99;
				}
				
			} while (true);
			}
			paramRenameValuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
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
			_loop560:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp115_AST = null;
					tmp115_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp115_AST);
					match(B_COMMA);
					new_predicate();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop560;
				}
				
			} while (true);
			}
			list_New_Predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
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
			_loop110:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp116_AST = null;
					tmp116_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp116_AST);
					match(B_SEMICOLON);
					set_declaration();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop110;
				}
				
			} while (true);
			}
			sets_declaration_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
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
			MyNode tmp117_AST = null;
			tmp117_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp117_AST);
			match(B_IDENTIFIER);
			{
			switch ( LA(1)) {
			case B_EQUAL:
			{
				MyNode tmp118_AST = null;
				tmp118_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp118_AST);
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
			case LITERAL_OPERATIONS:
			case LITERAL_VARIANT:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
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
				recover(ex,_tokenSet_38);
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
			_loop116:
			do {
				if ((_tokenSet_39.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_MULT:
					{
						MyNode tmp119_AST = null;
						tmp119_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp119_AST);
						match(B_MULT);
						break;
					}
					case B_ADD:
					{
						MyNode tmp120_AST = null;
						tmp120_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp120_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp121_AST = null;
						tmp121_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp121_AST);
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
					break _loop116;
				}
				
			} while (true);
			}
			set_construction_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_38);
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
				MyNode tmp122_AST = null;
				tmp122_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp122_AST);
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
				if ((_tokenSet_40.member(LA(1))) && (_tokenSet_41.member(LA(2))) && (_tokenSet_42.member(LA(3))) && (_tokenSet_43.member(LA(4))) && (_tokenSet_44.member(LA(5))) && (_tokenSet_45.member(LA(6))) && (_tokenSet_46.member(LA(7)))) {
					basic_value();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case B_RANGE:
					{
						MyNode tmp124_AST = null;
						tmp124_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp124_AST);
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
					case LITERAL_OPERATIONS:
					case LITERAL_VARIANT:
					case LITERAL_OBSERVABLES:
					case LITERAL_OBSERVATIONS:
					case LITERAL_EVENTS:
					case LITERAL_MODALITIES:
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
				else if ((LA(1)==LITERAL_rec||LA(1)==LITERAL_struct) && (LA(2)==B_LPAREN) && (_tokenSet_47.member(LA(3))) && (_tokenSet_48.member(LA(4))) && (_tokenSet_49.member(LA(5))) && (_tokenSet_50.member(LA(6))) && (_tokenSet_43.member(LA(7)))) {
					is_record();
					astFactory.addASTChild(currentAST, returnAST);
					valuation_set_AST = (MyNode)currentAST.root;
				}
				else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_51.member(LA(2))) && (_tokenSet_52.member(LA(3))) && (_tokenSet_53.member(LA(4))) && (_tokenSet_54.member(LA(5))) && (_tokenSet_55.member(LA(6))) && (_tokenSet_46.member(LA(7)))) {
					MyNode tmp125_AST = null;
					tmp125_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp125_AST);
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
				recover(ex,_tokenSet_51);
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
			_loop121:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp126_AST = null;
					tmp126_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp126_AST);
					match(B_COMMA);
					couple_parent();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop121;
				}
				
			} while (true);
			}
			list_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_56);
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
				MyNode tmp127_AST = null;
				tmp127_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp127_AST);
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
				MyNode tmp128_AST = null;
				tmp128_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp128_AST);
				match(LITERAL_TRUE);
				basic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp129_AST = null;
				tmp129_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp129_AST);
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
				recover(ex,_tokenSet_57);
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
				recover(ex,_tokenSet_57);
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
				MyNode tmp130_AST = null;
				tmp130_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp130_AST);
				match(LITERAL_INT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 87:
			{
				MyNode tmp131_AST = null;
				tmp131_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp131_AST);
				match(87);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INTEGER:
			{
				MyNode tmp132_AST = null;
				tmp132_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp132_AST);
				match(LITERAL_INTEGER);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 89:
			{
				MyNode tmp133_AST = null;
				tmp133_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp133_AST);
				match(89);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_BOOL:
			{
				MyNode tmp134_AST = null;
				tmp134_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp134_AST);
				match(LITERAL_BOOL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NAT:
			{
				MyNode tmp135_AST = null;
				tmp135_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp135_AST);
				match(LITERAL_NAT);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 92:
			{
				MyNode tmp136_AST = null;
				tmp136_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp136_AST);
				match(92);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_NATURAL:
			{
				MyNode tmp137_AST = null;
				tmp137_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp137_AST);
				match(LITERAL_NATURAL);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case 94:
			{
				MyNode tmp138_AST = null;
				tmp138_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp138_AST);
				match(94);
				basic_sets_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_STRING:
			{
				MyNode tmp139_AST = null;
				tmp139_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp139_AST);
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
				recover(ex,_tokenSet_58);
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
				MyNode tmp140_AST = null;
				tmp140_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp140_AST);
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
				recover(ex,_tokenSet_59);
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
			_loop126:
			do {
				if ((LA(1)==B_COMMA||LA(1)==B_MAPLET)) {
					{
					switch ( LA(1)) {
					case B_MAPLET:
					{
						MyNode tmp142_AST = null;
						tmp142_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp142_AST);
						match(B_MAPLET);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp143_AST = null;
						tmp143_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp143_AST);
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
					break _loop126;
				}
				
			} while (true);
			}
			extended_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
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
			_loop129:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp144_AST = null;
					tmp144_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp144_AST);
					match(B_MAPLET);
					a_set_value();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop129;
				}
				
			} while (true);
			}
			couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_59);
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
				MyNode tmp145_AST = null;
				tmp145_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp145_AST);
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
				MyNode tmp146_AST = null;
				tmp146_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp146_AST);
				match(B_NUMBER);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case B_NUMBER:
			{
				MyNode tmp147_AST = null;
				tmp147_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp147_AST);
				match(B_NUMBER);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_TRUE:
			{
				MyNode tmp148_AST = null;
				tmp148_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp148_AST);
				match(LITERAL_TRUE);
				a_set_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp149_AST = null;
				tmp149_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp149_AST);
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
				recover(ex,_tokenSet_60);
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
			_loop134:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp150_AST = null;
					tmp150_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp150_AST);
					match(B_SEMICOLON);
					set_valuation();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop134;
				}
				
			} while (true);
			}
			list_valuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
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
			MyNode tmp151_AST = null;
			tmp151_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp151_AST);
			match(B_IDENTIFIER);
			MyNode tmp152_AST = null;
			tmp152_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp152_AST);
			match(B_EQUAL);
			new_set_or_constant_valuation();
			astFactory.addASTChild(currentAST, returnAST);
			set_valuation_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_61);
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
			if ((_tokenSet_62.member(LA(1))) && (_tokenSet_61.member(LA(2)))) {
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched138 = false;
				if (((LA(1)==B_CURLYOPEN) && (_tokenSet_63.member(LA(2))) && (_tokenSet_64.member(LA(3))) && (_tokenSet_65.member(LA(4))) && (_tokenSet_66.member(LA(5))) && (_tokenSet_67.member(LA(6))) && (_tokenSet_68.member(LA(7))))) {
					int _m138 = mark();
					synPredMatched138 = true;
					inputState.guessing++;
					try {
						{
						match(B_CURLYOPEN);
						list_couple();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched138 = false;
					}
					rewind(_m138);
inputState.guessing--;
				}
				if ( synPredMatched138 ) {
					MyNode tmp153_AST = null;
					tmp153_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp153_AST);
					match(B_CURLYOPEN);
					list_couple();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_CURLYCLOSE);
					new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
				}
				else {
					boolean synPredMatched140 = false;
					if (((_tokenSet_47.member(LA(1))) && (_tokenSet_69.member(LA(2))) && (_tokenSet_70.member(LA(3))) && (_tokenSet_71.member(LA(4))) && (_tokenSet_72.member(LA(5))) && (_tokenSet_73.member(LA(6))) && (_tokenSet_68.member(LA(7))))) {
						int _m140 = mark();
						synPredMatched140 = true;
						inputState.guessing++;
						try {
							{
							bases();
							match(B_MULT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched140 = false;
						}
						rewind(_m140);
inputState.guessing--;
					}
					if ( synPredMatched140 ) {
						bases();
						astFactory.addASTChild(currentAST, returnAST);
						MyNode tmp155_AST = null;
						tmp155_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp155_AST);
						match(B_MULT);
						bases();
						astFactory.addASTChild(currentAST, returnAST);
						new_set_or_constant_valuation_AST = (MyNode)currentAST.root;
					}
					else if ((_tokenSet_40.member(LA(1))) && (_tokenSet_74.member(LA(2))) && (_tokenSet_75.member(LA(3))) && (_tokenSet_73.member(LA(4))) && (_tokenSet_68.member(LA(5))) && (_tokenSet_76.member(LA(6))) && (_tokenSet_77.member(LA(7)))) {
						basic_value();
						astFactory.addASTChild(currentAST, returnAST);
						{
						switch ( LA(1)) {
						case B_RANGE:
						{
							MyNode tmp156_AST = null;
							tmp156_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp156_AST);
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
						case LITERAL_OPERATIONS:
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
						recover(ex,_tokenSet_61);
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
					MyNode tmp157_AST = null;
					tmp157_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp157_AST);
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
				case LITERAL_OPERATIONS:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_ELSE:
				case LITERAL_VARIANT:
				case LITERAL_OBSERVABLES:
				case LITERAL_OBSERVATIONS:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_POST:
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
				MyNode tmp158_AST = null;
				tmp158_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp158_AST);
				match(B_SEQEMPTY);
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				MyNode tmp159_AST = null;
				tmp159_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp159_AST);
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
				MyNode tmp162_AST = null;
				tmp162_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp162_AST);
				match(B_EMPTYSET);
				bases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				MyNode tmp163_AST = null;
				tmp163_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp163_AST);
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
				recover(ex,_tokenSet_78);
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
			MyNode tmp165_AST = null;
			tmp165_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp165_AST);
			match(B_IDENTIFIER);
			MyNode tmp166_AST = null;
			tmp166_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp166_AST);
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
			MyNode tmp167_AST = null;
			tmp167_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp167_AST);
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
			MyNode tmp168_AST = null;
			tmp168_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp168_AST);
			match(B_IDENTIFIER);
			MyNode tmp169_AST = null;
			tmp169_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp169_AST);
			match(B_EQUAL);
			MyNode tmp170_AST = null;
			tmp170_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp170_AST);
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
			_loop155:
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
					break _loop155;
				}
				
			} while (true);
			}
			list_def_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
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
				MyNode tmp172_AST = null;
				tmp172_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp172_AST);
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
				recover(ex,_tokenSet_79);
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
			if ((_tokenSet_80.member(LA(1))) && (_tokenSet_81.member(LA(2))) && (_tokenSet_82.member(LA(3))) && (_tokenSet_83.member(LA(4))) && (_tokenSet_84.member(LA(5))) && (_tokenSet_85.member(LA(6))) && (_tokenSet_86.member(LA(7)))) {
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
			else if ((_tokenSet_87.member(LA(1))) && (_tokenSet_88.member(LA(2))) && (_tokenSet_89.member(LA(3))) && (_tokenSet_85.member(LA(4))) && (_tokenSet_90.member(LA(5))) && (_tokenSet_86.member(LA(6))) && (_tokenSet_91.member(LA(7)))) {
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
				recover(ex,_tokenSet_79);
			} else {
			  throw ex;
			}
		}
		returnAST = formalText_mch_AST;
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
			_loop162:
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
					break _loop162;
				}
				
			} while (true);
			}
			list_def_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = list_def_ref_AST;
	}
	
	public final void definitions_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode definitions_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp174_AST = null;
			tmp174_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp174_AST);
			match(LITERAL_DEFINITIONS);
			list_def_ref();
			astFactory.addASTChild(currentAST, returnAST);
			definitions_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = definitions_ref_AST;
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
				MyNode tmp175_AST = null;
				tmp175_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp175_AST);
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
				recover(ex,_tokenSet_14);
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
			if ((_tokenSet_80.member(LA(1))) && (_tokenSet_92.member(LA(2))) && (_tokenSet_93.member(LA(3))) && (_tokenSet_94.member(LA(4))) && (_tokenSet_95.member(LA(5))) && (_tokenSet_96.member(LA(6))) && (_tokenSet_97.member(LA(7)))) {
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
			else if ((_tokenSet_16.member(LA(1))) && (_tokenSet_98.member(LA(2))) && (_tokenSet_99.member(LA(3))) && (_tokenSet_100.member(LA(4))) && (_tokenSet_101.member(LA(5))) && (_tokenSet_102.member(LA(6))) && (_tokenSet_102.member(LA(7)))) {
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
				recover(ex,_tokenSet_14);
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
				recover(ex,_tokenSet_14);
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
			_loop169:
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
					break _loop169;
				}
				
			} while (true);
			}
			list_def_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_103);
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
				MyNode tmp177_AST = null;
				tmp177_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp177_AST);
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
				recover(ex,_tokenSet_104);
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
			if ((_tokenSet_80.member(LA(1))) && (_tokenSet_105.member(LA(2))) && (_tokenSet_72.member(LA(3))) && (_tokenSet_73.member(LA(4))) && (_tokenSet_68.member(LA(5))) && (_tokenSet_76.member(LA(6))) && (_tokenSet_77.member(LA(7)))) {
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
			else if ((_tokenSet_106.member(LA(1))) && (_tokenSet_107.member(LA(2))) && (_tokenSet_108.member(LA(3))) && (_tokenSet_76.member(LA(4))) && (_tokenSet_109.member(LA(5))) && (_tokenSet_77.member(LA(6))) && (_tokenSet_77.member(LA(7)))) {
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
				recover(ex,_tokenSet_104);
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
				recover(ex,_tokenSet_104);
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
			_loop175:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp178_AST = null;
					tmp178_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp178_AST);
					match(B_SEMICOLON);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop175;
				}
				
			} while (true);
			}
			list_assertions_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = list_assertions_AST;
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
			_loop209:
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
					break _loop209;
				}
				
			} while (true);
			}
			sequential_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_110);
			} else {
			  throw ex;
			}
		}
		returnAST = sequential_AST;
	}
	
	public final void listOperationMch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationMch_AST = null;
		
		try {      // for error handling
			operationMch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop182:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp179_AST = null;
					tmp179_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp179_AST);
					match(B_SEMICOLON);
					operationMch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop182;
				}
				
			} while (true);
			}
			listOperationMch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationMch_AST;
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
				recover(ex,_tokenSet_111);
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
			else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_112.member(LA(2)))) {
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
				recover(ex,_tokenSet_15);
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
			MyNode tmp180_AST = null;
			tmp180_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp180_AST);
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
	
	public final void listOperationRef() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationRef_AST = null;
		
		try {      // for error handling
			operationRef();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop187:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp181_AST = null;
					tmp181_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp181_AST);
					match(B_SEMICOLON);
					operationRef();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop187;
				}
				
			} while (true);
			}
			listOperationRef_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationRef_AST;
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
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		returnAST = operationRef_AST;
	}
	
	public final void listOperationImp() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode listOperationImp_AST = null;
		
		try {      // for error handling
			operationImp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop192:
			do {
				if ((LA(1)==B_SEMICOLON)) {
					MyNode tmp182_AST = null;
					tmp182_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp182_AST);
					match(B_SEMICOLON);
					operationImp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop192;
				}
				
			} while (true);
			}
			listOperationImp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			} else {
			  throw ex;
			}
		}
		returnAST = listOperationImp_AST;
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
				recover(ex,_tokenSet_111);
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
			_loop198:
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
					break _loop198;
				}
				
			} while (true);
			}
			parallele_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
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
				recover(ex,_tokenSet_22);
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
			MyNode tmp183_AST = null;
			tmp183_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp183_AST);
			match(LITERAL_skip);
			identite_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_113);
			} else {
			  throw ex;
			}
		}
		returnAST = identite_AST;
	}
	
	public final void assert_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assert_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp184_AST = null;
			tmp184_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp184_AST);
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
				recover(ex,_tokenSet_22);
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
			MyNode tmp187_AST = null;
			tmp187_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp187_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop218:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop218;
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
				recover(ex,_tokenSet_22);
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
			MyNode tmp189_AST = null;
			tmp189_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp189_AST);
			match(LITERAL_CHOICE);
			list_or_mch();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			choice_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_22);
			} else {
			  throw ex;
			}
		}
		returnAST = choice_mch_AST;
	}
	
	public final void let_mch() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode let_mch_AST = null;
		
		try {      // for error handling
			MyNode tmp191_AST = null;
			tmp191_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp191_AST);
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
				recover(ex,_tokenSet_22);
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
			MyNode tmp195_AST = null;
			tmp195_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp195_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_mch();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop244:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop244;
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
				recover(ex,_tokenSet_22);
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
			boolean synPredMatched302 = false;
			if (((LA(1)==B_IDENTIFIER) && (_tokenSet_114.member(LA(2))) && (_tokenSet_115.member(LA(3))) && (_tokenSet_116.member(LA(4))) && (_tokenSet_117.member(LA(5))) && (_tokenSet_118.member(LA(6))) && (_tokenSet_119.member(LA(7))))) {
				int _m302 = mark();
				synPredMatched302 = true;
				inputState.guessing++;
				try {
					{
					list_func_call();
					match(B_SIMPLESUBST);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched302 = false;
				}
				rewind(_m302);
inputState.guessing--;
			}
			if ( synPredMatched302 ) {
				list_func_call();
				l1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp199_AST = null;
				tmp199_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp199_AST);
				match(B_SIMPLESUBST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						RU1(l1_AST);
					
				}
				simple_affect_ref_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched304 = false;
				if (((LA(1)==B_IDENTIFIER) && (_tokenSet_120.member(LA(2))) && (_tokenSet_115.member(LA(3))) && (_tokenSet_121.member(LA(4))) && (_tokenSet_122.member(LA(5))) && (_tokenSet_118.member(LA(6))) && (_tokenSet_119.member(LA(7))))) {
					int _m304 = mark();
					synPredMatched304 = true;
					inputState.guessing++;
					try {
						{
						list_func_call();
						match(B_OUT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched304 = false;
					}
					rewind(_m304);
inputState.guessing--;
				}
				if ( synPredMatched304 ) {
					list_func_call();
					l2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					MyNode tmp200_AST = null;
					tmp200_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp200_AST);
					match(B_OUT);
					func_call();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							RU1(l2_AST);
						
					}
					simple_affect_ref_AST = (MyNode)currentAST.root;
				}
				else {
					boolean synPredMatched306 = false;
					if (((LA(1)==B_IDENTIFIER) && (_tokenSet_123.member(LA(2))) && (_tokenSet_115.member(LA(3))) && (_tokenSet_124.member(LA(4))) && (_tokenSet_70.member(LA(5))) && (_tokenSet_125.member(LA(6))) && (_tokenSet_126.member(LA(7))))) {
						int _m306 = mark();
						synPredMatched306 = true;
						inputState.guessing++;
						try {
							{
							list_func_call();
							match(B_INSET);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched306 = false;
						}
						rewind(_m306);
inputState.guessing--;
					}
					if ( synPredMatched306 ) {
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
						boolean synPredMatched308 = false;
						if (((LA(1)==B_IDENTIFIER) && (_tokenSet_127.member(LA(2))) && (_tokenSet_80.member(LA(3))) && (_tokenSet_128.member(LA(4))) && (_tokenSet_129.member(LA(5))) && (_tokenSet_118.member(LA(6))) && (_tokenSet_119.member(LA(7))))) {
							int _m308 = mark();
							synPredMatched308 = true;
							inputState.guessing++;
							try {
								{
								list_func_call();
								match(B_BECOME_ELEM);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched308 = false;
							}
							rewind(_m308);
inputState.guessing--;
						}
						if ( synPredMatched308 ) {
							list_func_call();
							l4_AST = (MyNode)returnAST;
							astFactory.addASTChild(currentAST, returnAST);
							MyNode tmp203_AST = null;
							tmp203_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp203_AST);
							match(B_BECOME_ELEM);
							expression();
							astFactory.addASTChild(currentAST, returnAST);
							if ( inputState.guessing==0 ) {
								
									RU1(l4_AST);
								
							}
							simple_affect_ref_AST = (MyNode)currentAST.root;
						}
						else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_130.member(LA(2))) && (_tokenSet_131.member(LA(3))) && (_tokenSet_132.member(LA(4))) && (_tokenSet_119.member(LA(5))) && (_tokenSet_133.member(LA(6))) && (_tokenSet_134.member(LA(7)))) {
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
							recover(ex,_tokenSet_22);
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
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = subst_ref_AST;
	}
	
	public final void assert_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode assert_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp204_AST = null;
			tmp204_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp204_AST);
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
				recover(ex,_tokenSet_23);
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
			MyNode tmp207_AST = null;
			tmp207_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp207_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop224:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop224;
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
				recover(ex,_tokenSet_23);
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
			MyNode tmp209_AST = null;
			tmp209_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp209_AST);
			match(LITERAL_CHOICE);
			list_or_ref();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			choice_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		returnAST = choice_ref_AST;
	}
	
	public final void case_ref() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		MyNode case_ref_AST = null;
		
		try {      // for error handling
			MyNode tmp211_AST = null;
			tmp211_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp211_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_ref();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop251:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop251;
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
				recover(ex,_tokenSet_23);
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
			MyNode tmp215_AST = null;
			tmp215_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp215_AST);
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
				recover(ex,_tokenSet_23);
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
			MyNode tmp219_AST = null;
			tmp219_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp219_AST);
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
				recover(ex,_tokenSet_23);
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
				recover(ex,_tokenSet_135);
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
			MyNode tmp222_AST = null;
			tmp222_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp222_AST);
			match(LITERAL_BEGIN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_END);
			substitution_block_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_135);
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
			MyNode tmp224_AST = null;
			tmp224_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp224_AST);
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
				recover(ex,_tokenSet_135);
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
			MyNode tmp227_AST = null;
			tmp227_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp227_AST);
			match(LITERAL_IF);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop230:
			do {
				if ((LA(1)==LITERAL_ELSIF)) {
					elsif_branch_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop230;
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
				recover(ex,_tokenSet_135);
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
			MyNode tmp229_AST = null;
			tmp229_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp229_AST);
			match(LITERAL_CASE);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			match(LITERAL_OF);
			branche_either_imp();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop258:
			do {
				if ((LA(1)==LITERAL_OR)) {
					branche_or_imp();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop258;
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
				recover(ex,_tokenSet_135);
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
			MyNode tmp233_AST = null;
			tmp233_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp233_AST);
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
				recover(ex,_tokenSet_135);
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
			MyNode tmp236_AST = null;
			tmp236_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp236_AST);
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
				recover(ex,_tokenSet_135);
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
			boolean synPredMatched297 = false;
			if (((LA(1)==B_IDENTIFIER) && (_tokenSet_114.member(LA(2))) && (_tokenSet_115.member(LA(3))) && (_tokenSet_136.member(LA(4))) && (_tokenSet_137.member(LA(5))) && (_tokenSet_138.member(LA(6))) && (_tokenSet_139.member(LA(7))))) {
				int _m297 = mark();
				synPredMatched297 = true;
				inputState.guessing++;
				try {
					{
					list_func_call();
					match(B_SIMPLESUBST);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched297 = false;
				}
				rewind(_m297);
inputState.guessing--;
			}
			if ( synPredMatched297 ) {
				list_func_call();
				l1_AST = (MyNode)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp239_AST = null;
				tmp239_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp239_AST);
				match(B_SIMPLESUBST);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				if ( inputState.guessing==0 ) {
					
						RU1(l1_AST);
					
				}
				simple_affect_AST = (MyNode)currentAST.root;
			}
			else {
				boolean synPredMatched299 = false;
				if (((LA(1)==B_IDENTIFIER) && (_tokenSet_120.member(LA(2))) && (_tokenSet_115.member(LA(3))) && (_tokenSet_140.member(LA(4))) && (_tokenSet_141.member(LA(5))) && (_tokenSet_138.member(LA(6))) && (_tokenSet_139.member(LA(7))))) {
					int _m299 = mark();
					synPredMatched299 = true;
					inputState.guessing++;
					try {
						{
						list_func_call();
						match(B_OUT);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched299 = false;
					}
					rewind(_m299);
inputState.guessing--;
				}
				if ( synPredMatched299 ) {
					list_func_call();
					l2_AST = (MyNode)returnAST;
					astFactory.addASTChild(currentAST, returnAST);
					MyNode tmp240_AST = null;
					tmp240_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp240_AST);
					match(B_OUT);
					func_call();
					astFactory.addASTChild(currentAST, returnAST);
					if ( inputState.guessing==0 ) {
						
							RU1(l2_AST);
						
					}
					simple_affect_AST = (MyNode)currentAST.root;
				}
				else if ((LA(1)==B_IDENTIFIER) && (_tokenSet_142.member(LA(2))) && (_tokenSet_143.member(LA(3))) && (_tokenSet_144.member(LA(4))) && (_tokenSet_139.member(LA(5))) && (_tokenSet_145.member(LA(6))) && (_tokenSet_145.member(LA(7)))) {
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
					recover(ex,_tokenSet_135);
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
			MyNode tmp241_AST = null;
			tmp241_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp241_AST);
			match(LITERAL_THEN);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
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
			MyNode tmp242_AST = null;
			tmp242_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp242_AST);
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
				recover(ex,_tokenSet_146);
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
			MyNode tmp244_AST = null;
			tmp244_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp244_AST);
			match(LITERAL_ELSE);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp245_AST = null;
			tmp245_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp245_AST);
			match(LITERAL_THEN);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
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
			MyNode tmp246_AST = null;
			tmp246_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp246_AST);
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
				recover(ex,_tokenSet_146);
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
			MyNode tmp248_AST = null;
			tmp248_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp248_AST);
			match(LITERAL_ELSE);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp249_AST = null;
			tmp249_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp249_AST);
			match(LITERAL_THEN);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			then_branch_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_146);
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
			MyNode tmp250_AST = null;
			tmp250_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp250_AST);
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
				recover(ex,_tokenSet_146);
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
			MyNode tmp252_AST = null;
			tmp252_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp252_AST);
			match(LITERAL_ELSE);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			e_branch_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			_loop237:
			do {
				if ((LA(1)==LITERAL_OR)) {
					MyNode tmp253_AST = null;
					tmp253_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp253_AST);
					match(LITERAL_OR);
					substitution_mch();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop237;
				}
				
			} while (true);
			}
			list_or_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			_loop241:
			do {
				if ((LA(1)==LITERAL_OR)) {
					MyNode tmp254_AST = null;
					tmp254_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp254_AST);
					match(LITERAL_OR);
					parallele_ref();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop241;
				}
				
			} while (true);
			}
			list_or_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp255_AST = null;
			tmp255_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp255_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp257_AST = null;
			tmp257_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp257_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp259_AST = null;
			tmp259_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp259_AST);
			match(LITERAL_ELSE);
			substitution_mch();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_mch_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp260_AST = null;
			tmp260_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp260_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp262_AST = null;
			tmp262_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp262_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp264_AST = null;
			tmp264_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp264_AST);
			match(LITERAL_ELSE);
			parallele_ref();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_ref_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp265_AST = null;
			tmp265_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp265_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp267_AST = null;
			tmp267_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp267_AST);
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
				recover(ex,_tokenSet_147);
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
			MyNode tmp269_AST = null;
			tmp269_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp269_AST);
			match(LITERAL_ELSE);
			sequential();
			astFactory.addASTChild(currentAST, returnAST);
			branche_else_imp_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
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
			MyNode tmp270_AST = null;
			tmp270_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp270_AST);
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
				recover(ex,_tokenSet_0);
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
			MyNode tmp272_AST = null;
			tmp272_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp272_AST);
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
				recover(ex,_tokenSet_0);
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
			MyNode tmp274_AST = null;
			tmp274_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp274_AST);
			match(B_IDENTIFIER);
			{
			_loop554:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp275_AST = null;
					tmp275_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp275_AST);
					match(B_COMMA);
					MyNode tmp276_AST = null;
					tmp276_AST = (MyNode)astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp276_AST);
					match(B_IDENTIFIER);
				}
				else {
					break _loop554;
				}
				
			} while (true);
			}
			list_identifier_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_148);
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
			_loop274:
			do {
				if ((LA(1)==B_AND)) {
					MyNode tmp277_AST = null;
					tmp277_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp277_AST);
					match(B_AND);
					an_equal();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop274;
				}
				
			} while (true);
			}
			list_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_149);
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
				MyNode tmp278_AST = null;
				tmp278_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp278_AST);
				match(LITERAL_VARIANT);
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp279_AST = null;
				tmp279_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp279_AST);
				match(LITERAL_INVARIANT);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				variant_or_no_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_INVARIANT:
			{
				MyNode tmp280_AST = null;
				tmp280_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp280_AST);
				match(LITERAL_INVARIANT);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp281_AST = null;
				tmp281_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp281_AST);
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
				recover(ex,_tokenSet_12);
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
			MyNode tmp282_AST = null;
			tmp282_AST = (MyNode)astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp282_AST);
			match(B_IDENTIFIER);
			MyNode tmp283_AST = null;
			tmp283_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp283_AST);
			match(B_EQUAL);
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
			an_equal_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_150);
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
			case LITERAL_OPERATIONS:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_VARIANT:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_POST:
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
				recover(ex,_tokenSet_113);
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
				MyNode tmp285_AST = null;
				tmp285_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp285_AST);
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
			case LITERAL_OPERATIONS:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_VARIANT:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_POST:
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
				recover(ex,_tokenSet_151);
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
			_loop282:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp286_AST = null;
					tmp286_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp286_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop282;
				}
				
			} while (true);
			}
			nameRenameDecoratedInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_152);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_153.member(LA(2))) && (_tokenSet_154.member(LA(3))) && (_tokenSet_155.member(LA(4))) && (_tokenSet_156.member(LA(5))) && (_tokenSet_157.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				MyNode tmp288_AST = null;
				tmp288_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp288_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_153.member(LA(1))) && (_tokenSet_154.member(LA(2))) && (_tokenSet_155.member(LA(3))) && (_tokenSet_156.member(LA(4))) && (_tokenSet_157.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_153);
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
				MyNode tmp289_AST = null;
				tmp289_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp289_AST);
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
			case LITERAL_OPERATIONS:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_OF:
			case LITERAL_ELSE:
			case LITERAL_IN:
			case LITERAL_VARIANT:
			case LITERAL_DO:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_UNTIL:
			case LITERAL_POST:
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
				recover(ex,_tokenSet_158);
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
			MyNode tmp290_AST = null;
			tmp290_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp290_AST);
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
			_loop288:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp292_AST = null;
					tmp292_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp292_AST);
					match(B_QUOTEIDENT);
					a_func_call();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop288;
				}
				
			} while (true);
			}
			a_func_call_quoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_159);
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
			_loop291:
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
					break _loop291;
				}
				
			} while (true);
			}
			a_func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_160);
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
			_loop408:
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
					break _loop408;
				}
				
			} while (true);
			}
			listPredicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_161);
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
			_loop294:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp294_AST = null;
					tmp294_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp294_AST);
					match(B_COMMA);
					a_func_call_quoted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop294;
				}
				
			} while (true);
			}
			list_func_call_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_162);
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
			_loop319:
			do {
				if ((LA(1)==LITERAL_or||LA(1)==B_AND)) {
					{
					switch ( LA(1)) {
					case LITERAL_or:
					{
						MyNode tmp295_AST = null;
						tmp295_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp295_AST);
						match(LITERAL_or);
						break;
					}
					case B_AND:
					{
						MyNode tmp296_AST = null;
						tmp296_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp296_AST);
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
					break _loop319;
				}
				
			} while (true);
			}
			logical_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_163);
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
			_loop323:
			do {
				if ((LA(1)==B_EQUIV||LA(1)==B_EQUAL)) {
					{
					switch ( LA(1)) {
					case B_EQUIV:
					{
						MyNode tmp297_AST = null;
						tmp297_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp297_AST);
						match(B_EQUIV);
						break;
					}
					case B_EQUAL:
					{
						MyNode tmp298_AST = null;
						tmp298_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp298_AST);
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
					break _loop323;
				}
				
			} while (true);
			}
			logical_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_164);
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
			boolean synPredMatched328 = false;
			if (((_tokenSet_80.member(LA(1))) && (_tokenSet_165.member(LA(2))) && (_tokenSet_70.member(LA(3))) && (_tokenSet_166.member(LA(4))) && (_tokenSet_167.member(LA(5))) && (_tokenSet_168.member(LA(6))) && (_tokenSet_28.member(LA(7))))) {
				int _m328 = mark();
				synPredMatched328 = true;
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
					synPredMatched328 = false;
				}
				rewind(_m328);
inputState.guessing--;
			}
			if ( synPredMatched328 ) {
				{
				extended_pair();
				astFactory.addASTChild(currentAST, returnAST);
				{
				{
				switch ( LA(1)) {
				case B_SUBSET:
				{
					MyNode tmp299_AST = null;
					tmp299_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp299_AST);
					match(B_SUBSET);
					break;
				}
				case B_NOTSUBSET:
				{
					MyNode tmp300_AST = null;
					tmp300_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp300_AST);
					match(B_NOTSUBSET);
					break;
				}
				case B_STRICTSUBSET:
				{
					MyNode tmp301_AST = null;
					tmp301_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp301_AST);
					match(B_STRICTSUBSET);
					break;
				}
				case B_NOTSTRICTSBSET:
				{
					MyNode tmp302_AST = null;
					tmp302_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp302_AST);
					match(B_NOTSTRICTSBSET);
					break;
				}
				case B_INSET:
				{
					MyNode tmp303_AST = null;
					tmp303_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp303_AST);
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
			else if ((_tokenSet_80.member(LA(1))) && (_tokenSet_169.member(LA(2))) && (_tokenSet_167.member(LA(3))) && (_tokenSet_168.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_170);
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
			_loop334:
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
					break _loop334;
				}
				
			} while (true);
			}
			extended_pair_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_171);
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
			_loop338:
			do {
				if ((_tokenSet_172.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_NOTINSET:
					{
						MyNode tmp304_AST = null;
						tmp304_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp304_AST);
						match(B_NOTINSET);
						break;
					}
					case B_LESS:
					{
						MyNode tmp305_AST = null;
						tmp305_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp305_AST);
						match(B_LESS);
						break;
					}
					case B_GREATER:
					{
						MyNode tmp306_AST = null;
						tmp306_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp306_AST);
						match(B_GREATER);
						break;
					}
					case B_NOTEQUAL:
					{
						MyNode tmp307_AST = null;
						tmp307_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp307_AST);
						match(B_NOTEQUAL);
						break;
					}
					case B_LESSTHANEQUAL:
					{
						MyNode tmp308_AST = null;
						tmp308_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp308_AST);
						match(B_LESSTHANEQUAL);
						break;
					}
					case B_GREATERTHANEQUAL:
					{
						MyNode tmp309_AST = null;
						tmp309_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp309_AST);
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
					break _loop338;
				}
				
			} while (true);
			}
			arithmetic_3_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_173);
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
			_loop342:
			do {
				if (((LA(1) >= B_CONCATSEQ && LA(1) <= B_SUFFIXSEQ))) {
					{
					switch ( LA(1)) {
					case B_CONCATSEQ:
					{
						MyNode tmp310_AST = null;
						tmp310_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp310_AST);
						match(B_CONCATSEQ);
						break;
					}
					case B_PREAPPSEQ:
					{
						MyNode tmp311_AST = null;
						tmp311_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp311_AST);
						match(B_PREAPPSEQ);
						break;
					}
					case B_APPSEQ:
					{
						MyNode tmp312_AST = null;
						tmp312_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp312_AST);
						match(B_APPSEQ);
						break;
					}
					case B_PREFIXSEQ:
					{
						MyNode tmp313_AST = null;
						tmp313_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp313_AST);
						match(B_PREFIXSEQ);
						break;
					}
					case B_SUFFIXSEQ:
					{
						MyNode tmp314_AST = null;
						tmp314_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp314_AST);
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
					break _loop342;
				}
				
			} while (true);
			}
			sequence_description_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_174);
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
				MyNode tmp315_AST = null;
				tmp315_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp315_AST);
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
				recover(ex,_tokenSet_175);
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
			_loop347:
			do {
				if (((LA(1) >= B_RELATION && LA(1) <= B_BIJECTION))) {
					{
					switch ( LA(1)) {
					case B_RELATION:
					{
						MyNode tmp318_AST = null;
						tmp318_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp318_AST);
						match(B_RELATION);
						break;
					}
					case B_PARTIAL:
					{
						MyNode tmp319_AST = null;
						tmp319_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp319_AST);
						match(B_PARTIAL);
						break;
					}
					case B_TOTAL:
					{
						MyNode tmp320_AST = null;
						tmp320_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp320_AST);
						match(B_TOTAL);
						break;
					}
					case B_PARTIAL_INJECT:
					{
						MyNode tmp321_AST = null;
						tmp321_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp321_AST);
						match(B_PARTIAL_INJECT);
						break;
					}
					case B_TOTAL_INJECT:
					{
						MyNode tmp322_AST = null;
						tmp322_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp322_AST);
						match(B_TOTAL_INJECT);
						break;
					}
					case B_PARTIAL_SURJECT:
					{
						MyNode tmp323_AST = null;
						tmp323_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp323_AST);
						match(B_PARTIAL_SURJECT);
						break;
					}
					case B_TOTAL_SURJECT:
					{
						MyNode tmp324_AST = null;
						tmp324_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp324_AST);
						match(B_TOTAL_SURJECT);
						break;
					}
					case B_BIJECTION:
					{
						MyNode tmp325_AST = null;
						tmp325_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp325_AST);
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
					break _loop347;
				}
				
			} while (true);
			}
			functional_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_175);
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
			_loop351:
			do {
				if (((LA(1) >= B_DOMAINRESTRICT && LA(1) <= B_RELPROD))) {
					{
					switch ( LA(1)) {
					case B_DOMAINRESTRICT:
					{
						MyNode tmp326_AST = null;
						tmp326_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp326_AST);
						match(B_DOMAINRESTRICT);
						break;
					}
					case B_RANGERESTRICT:
					{
						MyNode tmp327_AST = null;
						tmp327_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp327_AST);
						match(B_RANGERESTRICT);
						break;
					}
					case B_DOMAINSUBSTRACT:
					{
						MyNode tmp328_AST = null;
						tmp328_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp328_AST);
						match(B_DOMAINSUBSTRACT);
						break;
					}
					case B_RANGESUBSTRACT:
					{
						MyNode tmp329_AST = null;
						tmp329_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp329_AST);
						match(B_RANGESUBSTRACT);
						break;
					}
					case B_OVERRIDEFORWARD:
					{
						MyNode tmp330_AST = null;
						tmp330_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp330_AST);
						match(B_OVERRIDEFORWARD);
						break;
					}
					case B_OVERRIDEBACKWARD:
					{
						MyNode tmp331_AST = null;
						tmp331_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp331_AST);
						match(B_OVERRIDEBACKWARD);
						break;
					}
					case B_RELPROD:
					{
						MyNode tmp332_AST = null;
						tmp332_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp332_AST);
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
					break _loop351;
				}
				
			} while (true);
			}
			functional_const_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_176);
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
			_loop355:
			do {
				if ((LA(1)==B_UNION||LA(1)==B_INTER)) {
					{
					switch ( LA(1)) {
					case B_UNION:
					{
						MyNode tmp333_AST = null;
						tmp333_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp333_AST);
						match(B_UNION);
						break;
					}
					case B_INTER:
					{
						MyNode tmp334_AST = null;
						tmp334_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp334_AST);
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
					break _loop355;
				}
				
			} while (true);
			}
			basic_constructors_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_177);
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
			_loop358:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp335_AST = null;
					tmp335_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp335_AST);
					match(B_MAPLET);
					arithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop358;
				}
				
			} while (true);
			}
			new_couple_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_178);
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
			boolean synPredMatched361 = false;
			if (((_tokenSet_62.member(LA(1))) && (_tokenSet_78.member(LA(2))) && (_tokenSet_179.member(LA(3))) && (_tokenSet_168.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7))))) {
				int _m361 = mark();
				synPredMatched361 = true;
				inputState.guessing++;
				try {
					{
					basic_sets();
					match(B_MULT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched361 = false;
				}
				rewind(_m361);
inputState.guessing--;
			}
			if ( synPredMatched361 ) {
				{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop364:
				do {
					if ((LA(1)==B_MULT) && (_tokenSet_62.member(LA(2))) && (_tokenSet_78.member(LA(3))) && (_tokenSet_179.member(LA(4))) && (_tokenSet_168.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
						break _loop364;
					}
					
				} while (true);
				}
				}
				arithmetic_0_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_47.member(LA(1))) && (_tokenSet_166.member(LA(2))) && (_tokenSet_167.member(LA(3))) && (_tokenSet_168.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				arithmetic_1();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop367:
				do {
					if ((LA(1)==B_MULT||LA(1)==B_POWER) && (_tokenSet_47.member(LA(2))) && (_tokenSet_166.member(LA(3))) && (_tokenSet_167.member(LA(4))) && (_tokenSet_168.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
						{
						switch ( LA(1)) {
						case B_POWER:
						{
							MyNode tmp336_AST = null;
							tmp336_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp336_AST);
							match(B_POWER);
							break;
						}
						case B_MULT:
						{
							MyNode tmp337_AST = null;
							tmp337_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp337_AST);
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
						break _loop367;
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
				recover(ex,_tokenSet_78);
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
			_loop371:
			do {
				if ((LA(1)==B_DIV||LA(1)==LITERAL_mod) && (_tokenSet_47.member(LA(2))) && (_tokenSet_166.member(LA(3))) && (_tokenSet_167.member(LA(4))) && (_tokenSet_168.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case B_DIV:
					{
						MyNode tmp338_AST = null;
						tmp338_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp338_AST);
						match(B_DIV);
						break;
					}
					case LITERAL_mod:
					{
						MyNode tmp339_AST = null;
						tmp339_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp339_AST);
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
					break _loop371;
				}
				
			} while (true);
			}
			arithmetic_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_78);
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
			_loop375:
			do {
				if ((LA(1)==B_ADD||LA(1)==B_MINUS) && (_tokenSet_47.member(LA(2))) && (_tokenSet_166.member(LA(3))) && (_tokenSet_167.member(LA(4))) && (_tokenSet_168.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case B_ADD:
					{
						MyNode tmp340_AST = null;
						tmp340_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp340_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp341_AST = null;
						tmp341_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp341_AST);
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
					break _loop375;
				}
				
			} while (true);
			}
			arithmetic_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_78);
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
			boolean synPredMatched380 = false;
			if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && (_tokenSet_180.member(LA(2))) && (_tokenSet_181.member(LA(3))) && (_tokenSet_182.member(LA(4))) && (_tokenSet_166.member(LA(5))) && (_tokenSet_167.member(LA(6))) && (_tokenSet_168.member(LA(7))))) {
				int _m380 = mark();
				synPredMatched380 = true;
				inputState.guessing++;
				try {
					{
					alist_var();
					match(B_SUCH);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched380 = false;
				}
				rewind(_m380);
inputState.guessing--;
			}
			if ( synPredMatched380 ) {
				alist_var();
				astFactory.addASTChild(currentAST, returnAST);
				{
				MyNode tmp342_AST = null;
				tmp342_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp342_AST);
				match(B_SUCH);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				value_set_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_115.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_166.member(LA(3))) && (_tokenSet_167.member(LA(4))) && (_tokenSet_168.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop383:
				do {
					if ((LA(1)==B_COMMA)) {
						MyNode tmp343_AST = null;
						tmp343_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp343_AST);
						match(B_COMMA);
						predicate();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop383;
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
				recover(ex,_tokenSet_56);
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
				MyNode tmp344_AST = null;
				tmp344_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp344_AST);
				match(B_FORALL);
				break;
			}
			case B_EXISTS:
			{
				MyNode tmp345_AST = null;
				tmp345_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp345_AST);
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
			boolean synPredMatched507 = false;
			if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_186.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
				int _m507 = mark();
				synPredMatched507 = true;
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
					synPredMatched507 = false;
				}
				rewind(_m507);
inputState.guessing--;
			}
			if ( synPredMatched507 ) {
				{
				match(B_LPAREN);
				q_quantification();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched510 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_186.member(LA(5))) && (_tokenSet_187.member(LA(6))) && (_tokenSet_188.member(LA(7))))) {
					int _m510 = mark();
					synPredMatched510 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
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
					q_quantification();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched513 = false;
					if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_186.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
						int _m513 = mark();
						synPredMatched513 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
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
						match(B_LPAREN);
						q_quantification();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched516 = false;
						if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_186.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
							int _m516 = mark();
							synPredMatched516 = true;
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
								synPredMatched516 = false;
							}
							rewind(_m516);
inputState.guessing--;
						}
						if ( synPredMatched516 ) {
							{
							match(B_LPAREN);
							q_quantification();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched519 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_186.member(LA(5))) && (_tokenSet_187.member(LA(6))) && (_tokenSet_188.member(LA(7))))) {
								int _m519 = mark();
								synPredMatched519 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched519 = false;
								}
								rewind(_m519);
inputState.guessing--;
							}
							if ( synPredMatched519 ) {
								{
								q_quantification();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched522 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_186.member(LA(5))) && (_tokenSet_187.member(LA(6))) && (_tokenSet_188.member(LA(7))))) {
									int _m522 = mark();
									synPredMatched522 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched522 = false;
									}
									rewind(_m522);
inputState.guessing--;
								}
								if ( synPredMatched522 ) {
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
									recover(ex,_tokenSet_58);
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
				MyNode tmp352_AST = null;
				tmp352_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp352_AST);
				match(B_LAMBDA);
				break;
			}
			case LITERAL_PI:
			{
				MyNode tmp353_AST = null;
				tmp353_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp353_AST);
				match(LITERAL_PI);
				break;
			}
			case LITERAL_SIGMA:
			{
				MyNode tmp354_AST = null;
				tmp354_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp354_AST);
				match(LITERAL_SIGMA);
				break;
			}
			case LITERAL_UNION:
			{
				MyNode tmp355_AST = null;
				tmp355_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp355_AST);
				match(LITERAL_UNION);
				break;
			}
			case LITERAL_INTER:
			{
				MyNode tmp356_AST = null;
				tmp356_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp356_AST);
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
			boolean synPredMatched529 = false;
			if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_189.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
				int _m529 = mark();
				synPredMatched529 = true;
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
					synPredMatched529 = false;
				}
				rewind(_m529);
inputState.guessing--;
			}
			if ( synPredMatched529 ) {
				{
				match(B_LPAREN);
				q_operande();
				astFactory.addASTChild(currentAST, returnAST);
				match(B_RPAREN);
				}
			}
			else {
				boolean synPredMatched532 = false;
				if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_189.member(LA(5))) && (_tokenSet_70.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
					int _m532 = mark();
					synPredMatched532 = true;
					inputState.guessing++;
					try {
						{
						match(B_LPAREN);
						match(B_IDENTIFIER);
						match(B_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched532 = false;
					}
					rewind(_m532);
inputState.guessing--;
				}
				if ( synPredMatched532 ) {
					{
					q_operande();
					astFactory.addASTChild(currentAST, returnAST);
					}
				}
				else {
					boolean synPredMatched535 = false;
					if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_189.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
						int _m535 = mark();
						synPredMatched535 = true;
						inputState.guessing++;
						try {
							{
							match(B_LPAREN);
							match(B_IDENTIFIER);
							match(B_POINT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched535 = false;
						}
						rewind(_m535);
inputState.guessing--;
					}
					if ( synPredMatched535 ) {
						{
						match(B_LPAREN);
						q_operande();
						astFactory.addASTChild(currentAST, returnAST);
						match(B_RPAREN);
						}
					}
					else {
						boolean synPredMatched538 = false;
						if (((LA(1)==B_LPAREN) && (LA(2)==B_IDENTIFIER||LA(2)==B_LPAREN) && ((LA(3) >= B_COMMA && LA(3) <= B_POINT)) && (_tokenSet_184.member(LA(4))) && (_tokenSet_185.member(LA(5))) && (_tokenSet_189.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
							int _m538 = mark();
							synPredMatched538 = true;
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
								synPredMatched538 = false;
							}
							rewind(_m538);
inputState.guessing--;
						}
						if ( synPredMatched538 ) {
							{
							match(B_LPAREN);
							q_operande();
							astFactory.addASTChild(currentAST, returnAST);
							match(B_RPAREN);
							}
						}
						else {
							boolean synPredMatched541 = false;
							if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_189.member(LA(5))) && (_tokenSet_70.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
								int _m541 = mark();
								synPredMatched541 = true;
								inputState.guessing++;
								try {
									{
									match(B_LPAREN);
									match(B_IDENTIFIER);
									match(B_RPAREN);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched541 = false;
								}
								rewind(_m541);
inputState.guessing--;
							}
							if ( synPredMatched541 ) {
								{
								q_operande();
								astFactory.addASTChild(currentAST, returnAST);
								}
							}
							else {
								boolean synPredMatched544 = false;
								if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && ((LA(2) >= B_COMMA && LA(2) <= B_POINT)) && (_tokenSet_184.member(LA(3))) && (_tokenSet_185.member(LA(4))) && (_tokenSet_189.member(LA(5))) && (_tokenSet_70.member(LA(6))) && (_tokenSet_70.member(LA(7))))) {
									int _m544 = mark();
									synPredMatched544 = true;
									inputState.guessing++;
									try {
										{
										match(B_IDENTIFIER);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched544 = false;
									}
									rewind(_m544);
inputState.guessing--;
								}
								if ( synPredMatched544 ) {
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
									recover(ex,_tokenSet_58);
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
				MyNode tmp363_AST = null;
				tmp363_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp363_AST);
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
				recover(ex,_tokenSet_190);
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
				MyNode tmp365_AST = null;
				tmp365_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp365_AST);
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
			case LITERAL_OPERATIONS:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_ELSE:
			case LITERAL_VARIANT:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_POST:
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
				recover(ex,_tokenSet_57);
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
				case LITERAL_OPERATIONS:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_ELSE:
				case LITERAL_VARIANT:
				case LITERAL_OBSERVABLES:
				case LITERAL_OBSERVATIONS:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_POST:
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
				MyNode tmp367_AST = null;
				tmp367_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp367_AST);
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
				recover(ex,_tokenSet_191);
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
			_loop391:
			do {
				if ((LA(1)==B_QUOTEIDENT)) {
					MyNode tmp368_AST = null;
					tmp368_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp368_AST);
					match(B_QUOTEIDENT);
					expInvertedParamInverted();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop391;
				}
				
			} while (true);
			}
			expInvertedParamInvertedQuoted_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_192);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_193.member(LA(2))) && (_tokenSet_179.member(LA(3))) && (_tokenSet_168.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				MyNode tmp369_AST = null;
				tmp369_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp369_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_193.member(LA(1))) && (_tokenSet_179.member(LA(2))) && (_tokenSet_168.member(LA(3))) && (_tokenSet_28.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_193);
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
			_loop396:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp370_AST = null;
					tmp370_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp370_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop396;
				}
				
			} while (true);
			}
			expInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_193);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_194.member(LA(2))) && (_tokenSet_179.member(LA(3))) && (_tokenSet_168.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				MyNode tmp372_AST = null;
				tmp372_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp372_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_194.member(LA(1))) && (_tokenSet_179.member(LA(2))) && (_tokenSet_168.member(LA(3))) && (_tokenSet_28.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_194);
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
				recover(ex,_tokenSet_194);
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
			_loop404:
			do {
				if ((_tokenSet_195.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp374_AST = null;
						tmp374_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp374_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp375_AST = null;
						tmp375_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp375_AST);
						match(B_PARALLEL);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp376_AST = null;
						tmp376_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp376_AST);
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
					break _loop404;
				}
				
			} while (true);
			}
			expression_func_composition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
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
			_loop420:
			do {
				if ((LA(1)==LITERAL_or||LA(1)==B_AND) && (_tokenSet_115.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case LITERAL_or:
					{
						MyNode tmp377_AST = null;
						tmp377_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp377_AST);
						match(LITERAL_or);
						break;
					}
					case B_AND:
					{
						MyNode tmp378_AST = null;
						tmp378_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp378_AST);
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
					break _loop420;
				}
				
			} while (true);
			}
			plogical_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_197);
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
			_loop424:
			do {
				if ((LA(1)==B_EQUIV||LA(1)==B_EQUAL)) {
					{
					switch ( LA(1)) {
					case B_EQUIV:
					{
						MyNode tmp379_AST = null;
						tmp379_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp379_AST);
						match(B_EQUIV);
						break;
					}
					case B_EQUAL:
					{
						MyNode tmp380_AST = null;
						tmp380_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp380_AST);
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
					break _loop424;
				}
				
			} while (true);
			}
			plogical_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_198);
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
			boolean synPredMatched429 = false;
			if (((_tokenSet_115.member(LA(1))) && (_tokenSet_196.member(LA(2))) && (_tokenSet_188.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7))))) {
				int _m429 = mark();
				synPredMatched429 = true;
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
					synPredMatched429 = false;
				}
				rewind(_m429);
inputState.guessing--;
			}
			if ( synPredMatched429 ) {
				pextended_pair();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop432:
				do {
					if (((LA(1) >= B_SUBSET && LA(1) <= B_NOTSTRICTSBSET))) {
						{
						switch ( LA(1)) {
						case B_SUBSET:
						{
							MyNode tmp381_AST = null;
							tmp381_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp381_AST);
							match(B_SUBSET);
							break;
						}
						case B_NOTSUBSET:
						{
							MyNode tmp382_AST = null;
							tmp382_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp382_AST);
							match(B_NOTSUBSET);
							break;
						}
						case B_STRICTSUBSET:
						{
							MyNode tmp383_AST = null;
							tmp383_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp383_AST);
							match(B_STRICTSUBSET);
							break;
						}
						case B_NOTSTRICTSBSET:
						{
							MyNode tmp384_AST = null;
							tmp384_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp384_AST);
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
						break _loop432;
					}
					
				} while (true);
				}
				psubset_description_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_115.member(LA(1))) && (_tokenSet_199.member(LA(2))) && (_tokenSet_188.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_200);
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
			_loop435:
			do {
				if ((LA(1)==B_COMMA) && (_tokenSet_115.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
					break _loop435;
				}
				
			} while (true);
			}
			pextended_pair_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_201);
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
			_loop439:
			do {
				if (((LA(1) >= B_LESS && LA(1) <= B_GREATERTHANEQUAL)) && (_tokenSet_115.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case B_LESS:
					{
						MyNode tmp385_AST = null;
						tmp385_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp385_AST);
						match(B_LESS);
						break;
					}
					case B_GREATER:
					{
						MyNode tmp386_AST = null;
						tmp386_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp386_AST);
						match(B_GREATER);
						break;
					}
					case B_NOTEQUAL:
					{
						MyNode tmp387_AST = null;
						tmp387_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp387_AST);
						match(B_NOTEQUAL);
						break;
					}
					case B_LESSTHANEQUAL:
					{
						MyNode tmp388_AST = null;
						tmp388_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp388_AST);
						match(B_LESSTHANEQUAL);
						break;
					}
					case B_GREATERTHANEQUAL:
					{
						MyNode tmp389_AST = null;
						tmp389_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp389_AST);
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
					break _loop439;
				}
				
			} while (true);
			}
			parithmetic_3_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_201);
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
			_loop443:
			do {
				if (((LA(1) >= B_CONCATSEQ && LA(1) <= B_SUFFIXSEQ))) {
					{
					switch ( LA(1)) {
					case B_CONCATSEQ:
					{
						MyNode tmp390_AST = null;
						tmp390_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp390_AST);
						match(B_CONCATSEQ);
						break;
					}
					case B_PREAPPSEQ:
					{
						MyNode tmp391_AST = null;
						tmp391_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp391_AST);
						match(B_PREAPPSEQ);
						break;
					}
					case B_APPSEQ:
					{
						MyNode tmp392_AST = null;
						tmp392_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp392_AST);
						match(B_APPSEQ);
						break;
					}
					case B_PREFIXSEQ:
					{
						MyNode tmp393_AST = null;
						tmp393_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp393_AST);
						match(B_PREFIXSEQ);
						break;
					}
					case B_SUFFIXSEQ:
					{
						MyNode tmp394_AST = null;
						tmp394_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp394_AST);
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
					break _loop443;
				}
				
			} while (true);
			}
			psequence_description_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_202);
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
				MyNode tmp395_AST = null;
				tmp395_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp395_AST);
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
				recover(ex,_tokenSet_203);
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
			_loop448:
			do {
				if (((LA(1) >= B_RELATION && LA(1) <= B_BIJECTION))) {
					{
					switch ( LA(1)) {
					case B_RELATION:
					{
						MyNode tmp398_AST = null;
						tmp398_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp398_AST);
						match(B_RELATION);
						break;
					}
					case B_PARTIAL:
					{
						MyNode tmp399_AST = null;
						tmp399_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp399_AST);
						match(B_PARTIAL);
						break;
					}
					case B_TOTAL:
					{
						MyNode tmp400_AST = null;
						tmp400_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp400_AST);
						match(B_TOTAL);
						break;
					}
					case B_PARTIAL_INJECT:
					{
						MyNode tmp401_AST = null;
						tmp401_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp401_AST);
						match(B_PARTIAL_INJECT);
						break;
					}
					case B_TOTAL_INJECT:
					{
						MyNode tmp402_AST = null;
						tmp402_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp402_AST);
						match(B_TOTAL_INJECT);
						break;
					}
					case B_PARTIAL_SURJECT:
					{
						MyNode tmp403_AST = null;
						tmp403_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp403_AST);
						match(B_PARTIAL_SURJECT);
						break;
					}
					case B_TOTAL_SURJECT:
					{
						MyNode tmp404_AST = null;
						tmp404_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp404_AST);
						match(B_TOTAL_SURJECT);
						break;
					}
					case B_BIJECTION:
					{
						MyNode tmp405_AST = null;
						tmp405_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp405_AST);
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
					break _loop448;
				}
				
			} while (true);
			}
			pfunctional_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_203);
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
			_loop452:
			do {
				if (((LA(1) >= B_DOMAINRESTRICT && LA(1) <= B_RELPROD))) {
					{
					switch ( LA(1)) {
					case B_DOMAINRESTRICT:
					{
						MyNode tmp406_AST = null;
						tmp406_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp406_AST);
						match(B_DOMAINRESTRICT);
						break;
					}
					case B_RANGERESTRICT:
					{
						MyNode tmp407_AST = null;
						tmp407_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp407_AST);
						match(B_RANGERESTRICT);
						break;
					}
					case B_DOMAINSUBSTRACT:
					{
						MyNode tmp408_AST = null;
						tmp408_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp408_AST);
						match(B_DOMAINSUBSTRACT);
						break;
					}
					case B_RANGESUBSTRACT:
					{
						MyNode tmp409_AST = null;
						tmp409_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp409_AST);
						match(B_RANGESUBSTRACT);
						break;
					}
					case B_OVERRIDEFORWARD:
					{
						MyNode tmp410_AST = null;
						tmp410_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp410_AST);
						match(B_OVERRIDEFORWARD);
						break;
					}
					case B_OVERRIDEBACKWARD:
					{
						MyNode tmp411_AST = null;
						tmp411_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp411_AST);
						match(B_OVERRIDEBACKWARD);
						break;
					}
					case B_RELPROD:
					{
						MyNode tmp412_AST = null;
						tmp412_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp412_AST);
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
					break _loop452;
				}
				
			} while (true);
			}
			pfunctional_const_set_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_204);
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
			_loop456:
			do {
				if ((LA(1)==B_UNION||LA(1)==B_INTER)) {
					{
					switch ( LA(1)) {
					case B_UNION:
					{
						MyNode tmp413_AST = null;
						tmp413_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp413_AST);
						match(B_UNION);
						break;
					}
					case B_INTER:
					{
						MyNode tmp414_AST = null;
						tmp414_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp414_AST);
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
					break _loop456;
				}
				
			} while (true);
			}
			pset_constructors_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_205);
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
			_loop459:
			do {
				if ((LA(1)==B_MAPLET)) {
					MyNode tmp415_AST = null;
					tmp415_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp415_AST);
					match(B_MAPLET);
					parithmetic_0();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop459;
				}
				
			} while (true);
			}
			ppaire_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_206);
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
			boolean synPredMatched462 = false;
			if (((_tokenSet_62.member(LA(1))) && (_tokenSet_207.member(LA(2))) && (_tokenSet_26.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7))))) {
				int _m462 = mark();
				synPredMatched462 = true;
				inputState.guessing++;
				try {
					{
					basic_sets();
					match(B_MULT);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched462 = false;
				}
				rewind(_m462);
inputState.guessing--;
			}
			if ( synPredMatched462 ) {
				{
				basic_sets();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop465:
				do {
					if ((LA(1)==B_MULT) && (_tokenSet_62.member(LA(2))) && (_tokenSet_207.member(LA(3))) && (_tokenSet_26.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
						break _loop465;
					}
					
				} while (true);
				}
				}
				parithmetic_0_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_208.member(LA(1))) && (_tokenSet_196.member(LA(2))) && (_tokenSet_188.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				parithmetic_1();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop468:
				do {
					if ((LA(1)==B_MULT||LA(1)==B_POWER) && (_tokenSet_208.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
						{
						switch ( LA(1)) {
						case B_POWER:
						{
							MyNode tmp416_AST = null;
							tmp416_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp416_AST);
							match(B_POWER);
							break;
						}
						case B_MULT:
						{
							MyNode tmp417_AST = null;
							tmp417_AST = (MyNode)astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp417_AST);
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
						break _loop468;
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
				recover(ex,_tokenSet_207);
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
			_loop472:
			do {
				if ((LA(1)==B_DIV||LA(1)==LITERAL_mod) && (_tokenSet_208.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case B_DIV:
					{
						MyNode tmp418_AST = null;
						tmp418_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp418_AST);
						match(B_DIV);
						break;
					}
					case LITERAL_mod:
					{
						MyNode tmp419_AST = null;
						tmp419_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp419_AST);
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
					break _loop472;
				}
				
			} while (true);
			}
			parithmetic_1_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_207);
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
			_loop476:
			do {
				if ((LA(1)==B_ADD||LA(1)==B_MINUS) && (_tokenSet_208.member(LA(2))) && (_tokenSet_196.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
					{
					switch ( LA(1)) {
					case B_ADD:
					{
						MyNode tmp420_AST = null;
						tmp420_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp420_AST);
						match(B_ADD);
						break;
					}
					case B_MINUS:
					{
						MyNode tmp421_AST = null;
						tmp421_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp421_AST);
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
					break _loop476;
				}
				
			} while (true);
			}
			parithmetic_2_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_207);
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
				MyNode tmp422_AST = null;
				tmp422_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp422_AST);
				match(B_SEQEMPTY);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_BRACKOPEN:
			{
				MyNode tmp423_AST = null;
				tmp423_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp423_AST);
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
					MyNode tmp426_AST = null;
					tmp426_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp426_AST);
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
				case LITERAL_OPERATIONS:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_OF:
				case LITERAL_ELSE:
				case LITERAL_IN:
				case LITERAL_VARIANT:
				case LITERAL_DO:
				case LITERAL_OBSERVABLES:
				case LITERAL_OBSERVATIONS:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_UNTIL:
				case LITERAL_POST:
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
				MyNode tmp427_AST = null;
				tmp427_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp427_AST);
				match(B_EMPTYSET);
				pbases_AST = (MyNode)currentAST.root;
				break;
			}
			case B_CURLYOPEN:
			{
				MyNode tmp428_AST = null;
				tmp428_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp428_AST);
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
				recover(ex,_tokenSet_207);
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
				MyNode tmp430_AST = null;
				tmp430_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp430_AST);
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
				MyNode tmp431_AST = null;
				tmp431_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp431_AST);
				match(LITERAL_TRUE);
				pbasic_value_AST = (MyNode)currentAST.root;
				break;
			}
			case LITERAL_FALSE:
			{
				MyNode tmp432_AST = null;
				tmp432_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp432_AST);
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
				recover(ex,_tokenSet_209);
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
			boolean synPredMatched481 = false;
			if (((LA(1)==B_IDENTIFIER||LA(1)==B_LPAREN) && (_tokenSet_180.member(LA(2))) && (_tokenSet_181.member(LA(3))) && (_tokenSet_182.member(LA(4))) && (_tokenSet_187.member(LA(5))) && (_tokenSet_188.member(LA(6))) && (_tokenSet_27.member(LA(7))))) {
				int _m481 = mark();
				synPredMatched481 = true;
				inputState.guessing++;
				try {
					{
					alist_var();
					match(B_SUCH);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched481 = false;
				}
				rewind(_m481);
inputState.guessing--;
			}
			if ( synPredMatched481 ) {
				alist_var();
				astFactory.addASTChild(currentAST, returnAST);
				MyNode tmp433_AST = null;
				tmp433_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp433_AST);
				match(B_SUCH);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				pvalue_set_AST = (MyNode)currentAST.root;
			}
			else if ((_tokenSet_115.member(LA(1))) && (_tokenSet_183.member(LA(2))) && (_tokenSet_210.member(LA(3))) && (_tokenSet_188.member(LA(4))) && (_tokenSet_27.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				predicate();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop483:
				do {
					if ((LA(1)==B_COMMA)) {
						MyNode tmp434_AST = null;
						tmp434_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp434_AST);
						match(B_COMMA);
						predicate();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop483;
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
				recover(ex,_tokenSet_56);
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
				MyNode tmp435_AST = null;
				tmp435_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp435_AST);
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
			case LITERAL_OPERATIONS:
			case LITERAL_THEN:
			case LITERAL_ELSIF:
			case LITERAL_OR:
			case LITERAL_OF:
			case LITERAL_ELSE:
			case LITERAL_IN:
			case LITERAL_VARIANT:
			case LITERAL_DO:
			case LITERAL_OBSERVABLES:
			case LITERAL_OBSERVATIONS:
			case LITERAL_EVENTS:
			case LITERAL_MODALITIES:
			case LITERAL_UNTIL:
			case LITERAL_POST:
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
				recover(ex,_tokenSet_209);
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
			MyNode tmp436_AST = null;
			tmp436_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp436_AST);
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
				recover(ex,_tokenSet_211);
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
			_loop551:
			do {
				if ((LA(1)==B_COMMA)) {
					MyNode tmp439_AST = null;
					tmp439_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp439_AST);
					match(B_COMMA);
					a_record();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop551;
				}
				
			} while (true);
			}
			listRecord_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
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
				case LITERAL_OPERATIONS:
				case LITERAL_THEN:
				case LITERAL_ELSIF:
				case LITERAL_OR:
				case LITERAL_OF:
				case LITERAL_ELSE:
				case LITERAL_IN:
				case LITERAL_VARIANT:
				case LITERAL_DO:
				case LITERAL_OBSERVABLES:
				case LITERAL_OBSERVATIONS:
				case LITERAL_EVENTS:
				case LITERAL_MODALITIES:
				case LITERAL_UNTIL:
				case LITERAL_POST:
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
				MyNode tmp441_AST = null;
				tmp441_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp441_AST);
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
				recover(ex,_tokenSet_212);
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
			_loop494:
			do {
				if ((LA(1)==B_LPAREN)) {
					MyNode tmp442_AST = null;
					tmp442_AST = (MyNode)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp442_AST);
					match(B_LPAREN);
					list_New_Predicate();
					astFactory.addASTChild(currentAST, returnAST);
					match(B_RPAREN);
				}
				else {
					break _loop494;
				}
				
			} while (true);
			}
			predInvertedParam_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_25);
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
			if ((LA(1)==B_TILDE) && (_tokenSet_213.member(LA(2))) && (_tokenSet_26.member(LA(3))) && (_tokenSet_27.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
				MyNode tmp444_AST = null;
				tmp444_AST = (MyNode)astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp444_AST);
				match(B_TILDE);
			}
			else if ((_tokenSet_213.member(LA(1))) && (_tokenSet_26.member(LA(2))) && (_tokenSet_27.member(LA(3))) && (_tokenSet_28.member(LA(4))) && (_tokenSet_28.member(LA(5))) && (_tokenSet_28.member(LA(6))) && (_tokenSet_28.member(LA(7)))) {
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
				recover(ex,_tokenSet_213);
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
				recover(ex,_tokenSet_213);
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
			_loop502:
			do {
				if ((_tokenSet_195.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp446_AST = null;
						tmp446_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp446_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp447_AST = null;
						tmp447_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp447_AST);
						match(B_PARALLEL);
						break;
					}
					case B_COMMA:
					{
						MyNode tmp448_AST = null;
						tmp448_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp448_AST);
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
					break _loop502;
				}
				
			} while (true);
			}
			pred_func_composition_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_37);
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
				recover(ex,_tokenSet_58);
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
			MyNode tmp454_AST = null;
			tmp454_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp454_AST);
			match(B_SUCH);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			match(B_RPAREN);
			q_operande_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_58);
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
			MyNode tmp456_AST = null;
			tmp456_AST = (MyNode)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp456_AST);
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
				recover(ex,_tokenSet_57);
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
			boolean synPredMatched567 = false;
			if (((LA(1)==B_IDENTIFIER) && (LA(2)==B_INSET))) {
				int _m567 = mark();
				synPredMatched567 = true;
				inputState.guessing++;
				try {
					{
					match(B_IDENTIFIER);
					match(B_INSET);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched567 = false;
				}
				rewind(_m567);
inputState.guessing--;
			}
			if ( synPredMatched567 ) {
				MyNode tmp459_AST = null;
				tmp459_AST = (MyNode)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp459_AST);
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
			else if ((_tokenSet_208.member(LA(1))) && (_tokenSet_214.member(LA(2)))) {
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
				recover(ex,_tokenSet_215);
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
			_loop564:
			do {
				if ((LA(1)==B_SEMICOLON||LA(1)==B_PARALLEL)) {
					{
					switch ( LA(1)) {
					case B_SEMICOLON:
					{
						MyNode tmp460_AST = null;
						tmp460_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp460_AST);
						match(B_SEMICOLON);
						break;
					}
					case B_PARALLEL:
					{
						MyNode tmp461_AST = null;
						tmp461_AST = (MyNode)astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp461_AST);
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
					break _loop564;
				}
				
			} while (true);
			}
			new_predicate_AST = (MyNode)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_215);
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
		"\"MODEL\"",
		"\"OBSERVABLES\"",
		"\"OBSERVATIONS\"",
		"\"EVENTS\"",
		"\"MODALITIES\"",
		"\"MAINTAIN\"",
		"\"UNTIL\"",
		"\"ESTABLISH\"",
		"B_ref",
		"\"POST\"",
		"B_OR_EVENT",
		"B_ANY_POST",
		"B_SELECT_POST",
		"B_PRE_POST",
		"B_BEGIN_POST"
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
		long[] data = { 4114L, 2308094809027379200L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 0L, 2305843009213693952L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 0L, 2305843009213693952L, -1152851135836477568L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 0L, 2305843009213693952L, -1152921504539754657L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 0L, 2305843009213693952L, -1152851135795566606L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 536870912L, 2314850208468434944L, -1152814851911843841L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[8];
		data[0]=3530822108395339794L;
		data[1]=2305843009213700096L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 0L, 2305843009213696000L, -1152851135836477568L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[8];
		data[0]=3728980491328552850L;
		data[1]=2423006968271607828L;
		data[2]=-1152533011326042113L;
		data[3]=26L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[8];
		data[0]=2L;
		data[1]=2305843009213696000L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = new long[8];
		data[3]=5L;
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 0L, 2305843009213693952L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = new long[8];
		data[0]=1224979099181909010L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 2L, 2048L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 4096L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 32L, 0L, 574541466173440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = new long[8];
		data[0]=-3679440895158910862L;
		data[1]=2423077339161165819L;
		data[2]=-1152276298017560704L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = new long[8];
		data[0]=-3530822108395339790L;
		data[1]=2423147707905343487L;
		data[2]=-1152222352691452032L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2423147707905343487L;
		data[2]=-1152222215252498560L;
		data[3]=24L;
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1152222215252498560L;
		data[3]=29L;
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = new long[8];
		data[0]=2L;
		data[1]=2305843009213696000L;
		data[2]=-1152850839483734144L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = new long[8];
		data[0]=2L;
		data[1]=2305843009213700096L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = new long[8];
		data[0]=2L;
		data[1]=2305843009213700096L;
		data[2]=-1152850839483734144L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = new long[8];
		data[0]=1423137481846423314L;
		data[1]=2305843009213700100L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = new long[8];
		data[0]=1423137481846423314L;
		data[1]=2305843009213700116L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = new long[8];
		data[0]=-206L;
		data[1]=2423147707905343487L;
		data[2]=-1151818694444187649L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151818694444187649L;
		data[3]=30L;
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800002746515457L;
		data[3]=31L;
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = { 0L, 2305843009213693952L, 67092319L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = { 0L, 2305843009213693952L, 67108863L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = { 0L, 2305843009213693952L, 4194304L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = { 0L, 2305843009213693952L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = { 2L, 2305843009213693952L, 67092319L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = { 2L, 2305843009213693952L, 67108863L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = { 268435474L, 2305843009213693952L, 67108863L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = { 18L, 2305843009213693952L, 67108863L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = { 536870912L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = { 0L, 2305843009213696000L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = { 13792273858822144L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = { -4598175219276840928L, 1050603L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = { -3535044232106344224L, 2305983748847431679L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = { -3530822107858468878L, 2306054117593710591L, -1152135856841031681L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = { -14L, 2425399507719028735L, -1152135856841031681L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800419358343169L;
		data[3]=21L;
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800281919389697L;
		data[3]=21L;
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800002746515457L;
		data[3]=23L;
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = { -3679440895293128672L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = { -3530822107992555280L, 140739634784255L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = { -16L, 2305983748847435775L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = { -14L, 2306054117593710591L, -1152135856841031681L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = { 13792273858822144L, 2305843009213696000L, -1152851135795560449L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = { -3679440895158910942L, 140739633735659L, 715278954528768L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = { -3530822108395339790L, 2425399507719028735L, -1152135856841031681L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2423147707905343487L;
		data[2]=-1151800419358343169L;
		data[3]=21L;
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800281919389697L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = { 1152921504606846976L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	private static final long[] mk_tokenSet_57() {
		long[] data = new long[8];
		data[0]=3692951694041153298L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_57 = new BitSet(mk_tokenSet_57());
	private static final long[] mk_tokenSet_58() {
		long[] data = new long[8];
		data[0]=3548836505965297426L;
		data[1]=2305913377959974912L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_58 = new BitSet(mk_tokenSet_58());
	private static final long[] mk_tokenSet_59() {
		long[] data = { 1152921504606846992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_59 = new BitSet(mk_tokenSet_59());
	private static final long[] mk_tokenSet_60() {
		long[] data = { 1153062242632073232L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_60 = new BitSet(mk_tokenSet_60());
	private static final long[] mk_tokenSet_61() {
		long[] data = { 0L, 2305843009213696000L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_61 = new BitSet(mk_tokenSet_61());
	private static final long[] mk_tokenSet_62() {
		long[] data = { 0L, 140739631644672L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_62 = new BitSet(mk_tokenSet_62());
	private static final long[] mk_tokenSet_63() {
		long[] data = { -9214364837331599328L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_63 = new BitSet(mk_tokenSet_63());
	private static final long[] mk_tokenSet_64() {
		long[] data = { -8061302595504832464L, 9L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_64 = new BitSet(mk_tokenSet_64());
	private static final long[] mk_tokenSet_65() {
		long[] data = { -8061302594699526096L, 2305843009213696009L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_65 = new BitSet(mk_tokenSet_65());
	private static final long[] mk_tokenSet_66() {
		long[] data = { -2526378652526837710L, 2305983748847431659L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_66 = new BitSet(mk_tokenSet_66());
	private static final long[] mk_tokenSet_67() {
		long[] data = { -2377900603251621902L, 2389370710700060671L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_67 = new BitSet(mk_tokenSet_67());
	private static final long[] mk_tokenSet_68() {
		long[] data = { -14L, 2389370710700064767L, 1020453896316914L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_68 = new BitSet(mk_tokenSet_68());
	private static final long[] mk_tokenSet_69() {
		long[] data = { -3535044232106344224L, 140739633735679L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_69 = new BitSet(mk_tokenSet_69());
	private static final long[] mk_tokenSet_70() {
		long[] data = { -16L, 211108380016639L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_70 = new BitSet(mk_tokenSet_70());
	private static final long[] mk_tokenSet_71() {
		long[] data = { -16L, 2306054117593710591L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_71 = new BitSet(mk_tokenSet_71());
	private static final long[] mk_tokenSet_72() {
		long[] data = { -14L, 2306054117593710591L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_72 = new BitSet(mk_tokenSet_72());
	private static final long[] mk_tokenSet_73() {
		long[] data = { -14L, 2389370710700064767L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_73 = new BitSet(mk_tokenSet_73());
	private static final long[] mk_tokenSet_74() {
		long[] data = { -3535325707083054880L, 2305983748847431679L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_74 = new BitSet(mk_tokenSet_74());
	private static final long[] mk_tokenSet_75() {
		long[] data = { -3530822107858468878L, 2306054117593710591L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_75 = new BitSet(mk_tokenSet_75());
	private static final long[] mk_tokenSet_76() {
		long[] data = { -14L, 2389370710700064767L, 1020591335270386L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_76 = new BitSet(mk_tokenSet_76());
	private static final long[] mk_tokenSet_77() {
		long[] data = { -14L, 2389370710700064767L, 1091239252322290L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_77 = new BitSet(mk_tokenSet_77());
	private static final long[] mk_tokenSet_78() {
		long[] data = new long[8];
		data[0]=3548836505965297426L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_78 = new BitSet(mk_tokenSet_78());
	private static final long[] mk_tokenSet_79() {
		long[] data = { 2L, 2305843009213696000L, 67092319L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_79 = new BitSet(mk_tokenSet_79());
	private static final long[] mk_tokenSet_80() {
		long[] data = { -3679440895158910944L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_80 = new BitSet(mk_tokenSet_80());
	private static final long[] mk_tokenSet_81() {
		long[] data = { -3530822108395339790L, 2306054117593706495L, 67092319L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_81 = new BitSet(mk_tokenSet_81());
	private static final long[] mk_tokenSet_82() {
		long[] data = { -14L, 2306054117593710591L, 11591579844447L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_82 = new BitSet(mk_tokenSet_82());
	private static final long[] mk_tokenSet_83() {
		long[] data = { -14L, 2425399507719028735L, 11591579844447L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_83 = new BitSet(mk_tokenSet_83());
	private static final long[] mk_tokenSet_84() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=30369713733471L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_84 = new BitSet(mk_tokenSet_84());
	private static final long[] mk_tokenSet_85() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=30507152686943L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_85 = new BitSet(mk_tokenSet_85());
	private static final long[] mk_tokenSet_86() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=30786325561183L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_86 = new BitSet(mk_tokenSet_86());
	private static final long[] mk_tokenSet_87() {
		long[] data = { 32L, 0L, 11591512752128L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_87 = new BitSet(mk_tokenSet_87());
	private static final long[] mk_tokenSet_88() {
		long[] data = { -3679440895158910862L, 2423077339161165819L, 11591579844447L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_88 = new BitSet(mk_tokenSet_88());
	private static final long[] mk_tokenSet_89() {
		long[] data = new long[8];
		data[0]=-3530822108395339790L;
		data[1]=2423147707905341439L;
		data[2]=30369646641152L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_89 = new BitSet(mk_tokenSet_89());
	private static final long[] mk_tokenSet_90() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2423147707905343487L;
		data[2]=30786325561183L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_90 = new BitSet(mk_tokenSet_90());
	private static final long[] mk_tokenSet_91() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=65970697650015L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_91 = new BitSet(mk_tokenSet_91());
	private static final long[] mk_tokenSet_92() {
		long[] data = { -3530822108395339790L, 211108380012543L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_92 = new BitSet(mk_tokenSet_92());
	private static final long[] mk_tokenSet_93() {
		long[] data = { -14L, 211108380016639L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_93 = new BitSet(mk_tokenSet_93());
	private static final long[] mk_tokenSet_94() {
		long[] data = { -14L, 2462908193701887L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_94 = new BitSet(mk_tokenSet_94());
	private static final long[] mk_tokenSet_95() {
		long[] data = { -14L, 2462908193701887L, 574541466173440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_95 = new BitSet(mk_tokenSet_95());
	private static final long[] mk_tokenSet_96() {
		long[] data = { -14L, 119556498505334783L, 574541466173440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_96 = new BitSet(mk_tokenSet_96());
	private static final long[] mk_tokenSet_97() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=628503972151296L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_97 = new BitSet(mk_tokenSet_97());
	private static final long[] mk_tokenSet_98() {
		long[] data = { -3679440895158910862L, 117234329947467771L, 574541466173440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_98 = new BitSet(mk_tokenSet_98());
	private static final long[] mk_tokenSet_99() {
		long[] data = new long[8];
		data[0]=-3530822108395339790L;
		data[1]=2423147707905343487L;
		data[2]=628503972151296L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_99 = new BitSet(mk_tokenSet_99());
	private static final long[] mk_tokenSet_100() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=628641411104768L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_100 = new BitSet(mk_tokenSet_100());
	private static final long[] mk_tokenSet_101() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2423147707905343487L;
		data[2]=628920583979008L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_101 = new BitSet(mk_tokenSet_101());
	private static final long[] mk_tokenSet_102() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=628920583979008L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_102 = new BitSet(mk_tokenSet_102());
	private static final long[] mk_tokenSet_103() {
		long[] data = { 2L, 2305843009213693952L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_103 = new BitSet(mk_tokenSet_103());
	private static final long[] mk_tokenSet_104() {
		long[] data = { 2L, 2305843009213696000L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_104 = new BitSet(mk_tokenSet_104());
	private static final long[] mk_tokenSet_105() {
		long[] data = { -3530822108395339790L, 2306054117593706495L, 67102706L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_105 = new BitSet(mk_tokenSet_105());
	private static final long[] mk_tokenSet_106() {
		long[] data = { 32L, 0L, 703725224067072L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_106 = new BitSet(mk_tokenSet_106());
	private static final long[] mk_tokenSet_107() {
		long[] data = { -3679440895158910862L, 2387048542140100603L, 703725291169778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_107 = new BitSet(mk_tokenSet_107());
	private static final long[] mk_tokenSet_108() {
		long[] data = { -3530822108395339790L, 2387118910886375423L, 1020453829214208L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_108 = new BitSet(mk_tokenSet_108());
	private static final long[] mk_tokenSet_109() {
		long[] data = { -14L, 2387118910886379519L, 1091239252322290L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_109 = new BitSet(mk_tokenSet_109());
	private static final long[] mk_tokenSet_110() {
		long[] data = { 0L, 2305843009213693952L, 70665164023794L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_110 = new BitSet(mk_tokenSet_110());
	private static final long[] mk_tokenSet_111() {
		long[] data = { 0L, 2305843009213696000L, 4194304L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_111 = new BitSet(mk_tokenSet_111());
	private static final long[] mk_tokenSet_112() {
		long[] data = { 80L, 9007199254740992L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_112 = new BitSet(mk_tokenSet_112());
	private static final long[] mk_tokenSet_113() {
		long[] data = new long[8];
		data[0]=2L;
		data[1]=2305843009213700096L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_113 = new BitSet(mk_tokenSet_113());
	private static final long[] mk_tokenSet_114() {
		long[] data = { 268435536L, 72057594037927952L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_114 = new BitSet(mk_tokenSet_114());
	private static final long[] mk_tokenSet_115() {
		long[] data = { -3679440895158910944L, 140739632687083L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_115 = new BitSet(mk_tokenSet_115());
	private static final long[] mk_tokenSet_116() {
		long[] data = new long[8];
		data[0]=-3530822107858468878L;
		data[1]=2378041342884315135L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_116 = new BitSet(mk_tokenSet_116());
	private static final long[] mk_tokenSet_117() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2378041342885363711L;
		data[2]=-1152276297976660001L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_117 = new BitSet(mk_tokenSet_117());
	private static final long[] mk_tokenSet_118() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1152276297439789089L;
		data[3]=24L;
		return data;
	}
	public static final BitSet _tokenSet_118 = new BitSet(mk_tokenSet_118());
	private static final long[] mk_tokenSet_119() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1152222352650551329L;
		data[3]=29L;
		return data;
	}
	public static final BitSet _tokenSet_119 = new BitSet(mk_tokenSet_119());
	private static final long[] mk_tokenSet_120() {
		long[] data = { 268435536L, 9007199254741008L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_120 = new BitSet(mk_tokenSet_120());
	private static final long[] mk_tokenSet_121() {
		long[] data = new long[8];
		data[0]=-3530822107858468878L;
		data[1]=2314990948101128191L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_121 = new BitSet(mk_tokenSet_121());
	private static final long[] mk_tokenSet_122() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2314990948102176767L;
		data[2]=-1152276297976660001L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_122 = new BitSet(mk_tokenSet_122());
	private static final long[] mk_tokenSet_123() {
		long[] data = { 268435536L, 2097168L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_123 = new BitSet(mk_tokenSet_123());
	private static final long[] mk_tokenSet_124() {
		long[] data = { -3530822107858468880L, 140739635832831L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_124 = new BitSet(mk_tokenSet_124());
	private static final long[] mk_tokenSet_125() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2306054117593710591L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_125 = new BitSet(mk_tokenSet_125());
	private static final long[] mk_tokenSet_126() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2306054117593710591L;
		data[2]=-1152276297976660001L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_126 = new BitSet(mk_tokenSet_126());
	private static final long[] mk_tokenSet_127() {
		long[] data = { 268435536L, 36028797018963984L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_127 = new BitSet(mk_tokenSet_127());
	private static final long[] mk_tokenSet_128() {
		long[] data = new long[8];
		data[0]=-3530822107858468878L;
		data[1]=2342082914612674559L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_128 = new BitSet(mk_tokenSet_128());
	private static final long[] mk_tokenSet_129() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2342082914612674559L;
		data[2]=-1152276297976660001L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_129 = new BitSet(mk_tokenSet_129());
	private static final long[] mk_tokenSet_130() {
		long[] data = new long[8];
		data[0]=268435522L;
		data[1]=2305843009213700112L;
		data[2]=-1152850839442833441L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_130 = new BitSet(mk_tokenSet_130());
	private static final long[] mk_tokenSet_131() {
		long[] data = new long[8];
		data[0]=-3679440895158910942L;
		data[1]=2305983748847435755L;
		data[2]=-1152276297976660001L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_131 = new BitSet(mk_tokenSet_131());
	private static final long[] mk_tokenSet_132() {
		long[] data = new long[8];
		data[0]=-3530822107858468878L;
		data[1]=2425399507719028735L;
		data[2]=-1152276297439789089L;
		data[3]=24L;
		return data;
	}
	public static final BitSet _tokenSet_132 = new BitSet(mk_tokenSet_132());
	private static final long[] mk_tokenSet_133() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1152222215211597857L;
		data[3]=29L;
		return data;
	}
	public static final BitSet _tokenSet_133 = new BitSet(mk_tokenSet_133());
	private static final long[] mk_tokenSet_134() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1152222215211597857L;
		data[3]=31L;
		return data;
	}
	public static final BitSet _tokenSet_134 = new BitSet(mk_tokenSet_134());
	private static final long[] mk_tokenSet_135() {
		long[] data = { 2L, 2305843009213696000L, 70665164023794L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_135 = new BitSet(mk_tokenSet_135());
	private static final long[] mk_tokenSet_136() {
		long[] data = { -3530822107858468878L, 2378041342884311039L, 70665164023794L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_136 = new BitSet(mk_tokenSet_136());
	private static final long[] mk_tokenSet_137() {
		long[] data = { -14L, 2378041342885363711L, 774390388090866L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_137 = new BitSet(mk_tokenSet_137());
	private static final long[] mk_tokenSet_138() {
		long[] data = { -14L, 2389370710700064767L, 774390924961778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_138 = new BitSet(mk_tokenSet_138());
	private static final long[] mk_tokenSet_139() {
		long[] data = { -14L, 2389370710700064767L, 1091118993238002L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_139 = new BitSet(mk_tokenSet_139());
	private static final long[] mk_tokenSet_140() {
		long[] data = { -3530822107858468878L, 2314990948101124095L, 70665164023794L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_140 = new BitSet(mk_tokenSet_140());
	private static final long[] mk_tokenSet_141() {
		long[] data = { -14L, 2314990948102176767L, 774390388090866L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_141 = new BitSet(mk_tokenSet_141());
	private static final long[] mk_tokenSet_142() {
		long[] data = { 268435522L, 2305843009213696016L, 70665164023794L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_142 = new BitSet(mk_tokenSet_142());
	private static final long[] mk_tokenSet_143() {
		long[] data = { -3679440895158910942L, 2305983748847431659L, 774390388090866L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_143 = new BitSet(mk_tokenSet_143());
	private static final long[] mk_tokenSet_144() {
		long[] data = { -3530822107858468878L, 2389370710700060671L, 774390924961778L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_144 = new BitSet(mk_tokenSet_144());
	private static final long[] mk_tokenSet_145() {
		long[] data = { -14L, 2389370710700064767L, 1091256432191474L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_145 = new BitSet(mk_tokenSet_145());
	private static final long[] mk_tokenSet_146() {
		long[] data = { 0L, 2305843009213693952L, 279172874240L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_146 = new BitSet(mk_tokenSet_146());
	private static final long[] mk_tokenSet_147() {
		long[] data = { 0L, 2305843009213693952L, 292057776128L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_147 = new BitSet(mk_tokenSet_147());
	private static final long[] mk_tokenSet_148() {
		long[] data = { 2305843009750564928L, 0L, 17592186044416L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_148 = new BitSet(mk_tokenSet_148());
	private static final long[] mk_tokenSet_149() {
		long[] data = { 0L, 0L, 35184372088832L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_149 = new BitSet(mk_tokenSet_149());
	private static final long[] mk_tokenSet_150() {
		long[] data = { 1024L, 0L, 35184372088832L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_150 = new BitSet(mk_tokenSet_150());
	private static final long[] mk_tokenSet_151() {
		long[] data = new long[8];
		data[0]=36028797018963970L;
		data[1]=2305843009213700096L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_151 = new BitSet(mk_tokenSet_151());
	private static final long[] mk_tokenSet_152() {
		long[] data = new long[8];
		data[0]=36028797018963970L;
		data[1]=2305843009213700100L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_152 = new BitSet(mk_tokenSet_152());
	private static final long[] mk_tokenSet_153() {
		long[] data = new long[8];
		data[0]=36028797287399426L;
		data[1]=2305843009213700100L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_153 = new BitSet(mk_tokenSet_153());
	private static final long[] mk_tokenSet_154() {
		long[] data = new long[8];
		data[0]=-3679440895158910942L;
		data[1]=2305983748847435755L;
		data[2]=-1152135560488288257L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_154 = new BitSet(mk_tokenSet_154());
	private static final long[] mk_tokenSet_155() {
		long[] data = new long[8];
		data[0]=-3458764513820540942L;
		data[1]=2425399507719028735L;
		data[2]=-1152135559951417345L;
		data[3]=24L;
		return data;
	}
	public static final BitSet _tokenSet_155 = new BitSet(mk_tokenSet_155());
	private static final long[] mk_tokenSet_156() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800140185468929L;
		data[3]=29L;
		return data;
	}
	public static final BitSet _tokenSet_156 = new BitSet(mk_tokenSet_156());
	private static final long[] mk_tokenSet_157() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151800002746515457L;
		data[3]=29L;
		return data;
	}
	public static final BitSet _tokenSet_157 = new BitSet(mk_tokenSet_157());
	private static final long[] mk_tokenSet_158() {
		long[] data = new long[8];
		data[0]=3728980491328552722L;
		data[1]=2305913377959974932L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_158 = new BitSet(mk_tokenSet_158());
	private static final long[] mk_tokenSet_159() {
		long[] data = new long[8];
		data[0]=18L;
		data[1]=2422936599527430144L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_159 = new BitSet(mk_tokenSet_159());
	private static final long[] mk_tokenSet_160() {
		long[] data = new long[8];
		data[0]=18L;
		data[1]=2422936599527430160L;
		data[2]=-1152850839442817025L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_160 = new BitSet(mk_tokenSet_160());
	private static final long[] mk_tokenSet_161() {
		long[] data = { 72057594575060992L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_161 = new BitSet(mk_tokenSet_161());
	private static final long[] mk_tokenSet_162() {
		long[] data = { 0L, 117093590313730048L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_162 = new BitSet(mk_tokenSet_162());
	private static final long[] mk_tokenSet_163() {
		long[] data = new long[8];
		data[0]=3530822108395340050L;
		data[1]=2305843009213700096L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_163 = new BitSet(mk_tokenSet_163());
	private static final long[] mk_tokenSet_164() {
		long[] data = new long[8];
		data[0]=3530822108395341586L;
		data[1]=2305843009213700096L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_164 = new BitSet(mk_tokenSet_164());
	private static final long[] mk_tokenSet_165() {
		long[] data = { -3530822108395347728L, 211108380010495L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_165 = new BitSet(mk_tokenSet_165());
	private static final long[] mk_tokenSet_166() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2306054117593710591L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_166 = new BitSet(mk_tokenSet_166());
	private static final long[] mk_tokenSet_167() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2306054117593710591L;
		data[2]=-1151818831883141121L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_167 = new BitSet(mk_tokenSet_167());
	private static final long[] mk_tokenSet_168() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2425399507719028735L;
		data[2]=-1151818694444187649L;
		data[3]=31L;
		return data;
	}
	public static final BitSet _tokenSet_168 = new BitSet(mk_tokenSet_168());
	private static final long[] mk_tokenSet_169() {
		long[] data = new long[8];
		data[0]=-122894L;
		data[1]=2306054117591613439L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_169 = new BitSet(mk_tokenSet_169());
	private static final long[] mk_tokenSet_170() {
		long[] data = new long[8];
		data[0]=3530822108395347730L;
		data[1]=2305843009213700096L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_170 = new BitSet(mk_tokenSet_170());
	private static final long[] mk_tokenSet_171() {
		long[] data = { 122880L, 2097152L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_171 = new BitSet(mk_tokenSet_171());
	private static final long[] mk_tokenSet_172() {
		long[] data = { 4063232L, 70368744177664L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_172 = new BitSet(mk_tokenSet_172());
	private static final long[] mk_tokenSet_173() {
		long[] data = new long[8];
		data[0]=3530822108395470610L;
		data[1]=2305843009215797248L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_173 = new BitSet(mk_tokenSet_173());
	private static final long[] mk_tokenSet_174() {
		long[] data = new long[8];
		data[0]=3530822108399533842L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_174 = new BitSet(mk_tokenSet_174());
	private static final long[] mk_tokenSet_175() {
		long[] data = new long[8];
		data[0]=3530822108529557266L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_175 = new BitSet(mk_tokenSet_175());
	private static final long[] mk_tokenSet_176() {
		long[] data = new long[8];
		data[0]=3530822382333722386L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_176 = new BitSet(mk_tokenSet_176());
	private static final long[] mk_tokenSet_177() {
		long[] data = new long[8];
		data[0]=3530857291827904274L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_177 = new BitSet(mk_tokenSet_177());
	private static final long[] mk_tokenSet_178() {
		long[] data = new long[8];
		data[0]=3530962844944170770L;
		data[1]=2305913377959974912L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_178 = new BitSet(mk_tokenSet_178());
	private static final long[] mk_tokenSet_179() {
		long[] data = new long[8];
		data[0]=-206L;
		data[1]=2306054117593710591L;
		data[2]=-1151818831883141121L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_179 = new BitSet(mk_tokenSet_179());
	private static final long[] mk_tokenSet_180() {
		long[] data = { 2305843009213694000L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_180 = new BitSet(mk_tokenSet_180());
	private static final long[] mk_tokenSet_181() {
		long[] data = { -3679440894622040016L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_181 = new BitSet(mk_tokenSet_181());
	private static final long[] mk_tokenSet_182() {
		long[] data = { -72057594574798864L, 211108380010495L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_182 = new BitSet(mk_tokenSet_182());
	private static final long[] mk_tokenSet_183() {
		long[] data = { -2377900603788492816L, 140739632687103L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_183 = new BitSet(mk_tokenSet_183());
	private static final long[] mk_tokenSet_184() {
		long[] data = { 805306416L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_184 = new BitSet(mk_tokenSet_184());
	private static final long[] mk_tokenSet_185() {
		long[] data = { -3679440895158910864L, 140739633735659L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_185 = new BitSet(mk_tokenSet_185());
	private static final long[] mk_tokenSet_186() {
		long[] data = { -3530822107858468880L, 211108380010495L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_186 = new BitSet(mk_tokenSet_186());
	private static final long[] mk_tokenSet_187() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2306054117593710591L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_187 = new BitSet(mk_tokenSet_187());
	private static final long[] mk_tokenSet_188() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2423147707905343487L;
		data[2]=-1151818694444187649L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_188 = new BitSet(mk_tokenSet_188());
	private static final long[] mk_tokenSet_189() {
		long[] data = { -1224979098644774928L, 211108380010495L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_189 = new BitSet(mk_tokenSet_189());
	private static final long[] mk_tokenSet_190() {
		long[] data = { 2305843009213694016L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_190 = new BitSet(mk_tokenSet_190());
	private static final long[] mk_tokenSet_191() {
		long[] data = new long[8];
		data[0]=3692951694041153298L;
		data[1]=2305913377959974916L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_191 = new BitSet(mk_tokenSet_191());
	private static final long[] mk_tokenSet_192() {
		long[] data = new long[8];
		data[0]=3728980491060117266L;
		data[1]=2305913377959974916L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_192 = new BitSet(mk_tokenSet_192());
	private static final long[] mk_tokenSet_193() {
		long[] data = new long[8];
		data[0]=3728980491060117266L;
		data[1]=2305913377959974932L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_193 = new BitSet(mk_tokenSet_193());
	private static final long[] mk_tokenSet_194() {
		long[] data = new long[8];
		data[0]=3728980491328552722L;
		data[1]=2305913377959974932L;
		data[2]=-1152850838905946113L;
		data[3]=16L;
		return data;
	}
	public static final BitSet _tokenSet_194 = new BitSet(mk_tokenSet_194());
	private static final long[] mk_tokenSet_195() {
		long[] data = { 16L, 6144L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_195 = new BitSet(mk_tokenSet_195());
	private static final long[] mk_tokenSet_196() {
		long[] data = new long[8];
		data[0]=-2305843009213693966L;
		data[1]=2305983748846387199L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_196 = new BitSet(mk_tokenSet_196());
	private static final long[] mk_tokenSet_197() {
		long[] data = new long[8];
		data[0]=1224979099181909266L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_197 = new BitSet(mk_tokenSet_197());
	private static final long[] mk_tokenSet_198() {
		long[] data = new long[8];
		data[0]=1224979099181909778L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_198 = new BitSet(mk_tokenSet_198());
	private static final long[] mk_tokenSet_199() {
		long[] data = new long[8];
		data[0]=-2305843009213816846L;
		data[1]=2305983748846387199L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_199 = new BitSet(mk_tokenSet_199());
	private static final long[] mk_tokenSet_200() {
		long[] data = new long[8];
		data[0]=1224979099181915922L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_200 = new BitSet(mk_tokenSet_200());
	private static final long[] mk_tokenSet_201() {
		long[] data = new long[8];
		data[0]=1224979099182038802L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_201 = new BitSet(mk_tokenSet_201());
	private static final long[] mk_tokenSet_202() {
		long[] data = new long[8];
		data[0]=1224979099185839890L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_202 = new BitSet(mk_tokenSet_202());
	private static final long[] mk_tokenSet_203() {
		long[] data = new long[8];
		data[0]=1224979099315863314L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_203 = new BitSet(mk_tokenSet_203());
	private static final long[] mk_tokenSet_204() {
		long[] data = new long[8];
		data[0]=1224979373120028434L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_204 = new BitSet(mk_tokenSet_204());
	private static final long[] mk_tokenSet_205() {
		long[] data = new long[8];
		data[0]=1225014282614210322L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_205 = new BitSet(mk_tokenSet_205());
	private static final long[] mk_tokenSet_206() {
		long[] data = new long[8];
		data[0]=1225119835730476818L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_206 = new BitSet(mk_tokenSet_206());
	private static final long[] mk_tokenSet_207() {
		long[] data = new long[8];
		data[0]=1242993496751603474L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_207 = new BitSet(mk_tokenSet_207());
	private static final long[] mk_tokenSet_208() {
		long[] data = { -3679440895293128672L, 140739632687083L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_208 = new BitSet(mk_tokenSet_208());
	private static final long[] mk_tokenSet_209() {
		long[] data = new long[8];
		data[0]=1387108684827459346L;
		data[1]=2305843009213700096L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_209 = new BitSet(mk_tokenSet_209());
	private static final long[] mk_tokenSet_210() {
		long[] data = new long[8];
		data[0]=-14L;
		data[1]=2305983748847435775L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_210 = new BitSet(mk_tokenSet_210());
	private static final long[] mk_tokenSet_211() {
		long[] data = new long[8];
		data[0]=3692951694041153298L;
		data[1]=2305913377959974912L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_211 = new BitSet(mk_tokenSet_211());
	private static final long[] mk_tokenSet_212() {
		long[] data = new long[8];
		data[0]=1387108684827459346L;
		data[1]=2305843009213700100L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_212 = new BitSet(mk_tokenSet_212());
	private static final long[] mk_tokenSet_213() {
		long[] data = new long[8];
		data[0]=1423137482114858770L;
		data[1]=2305843009213700116L;
		data[2]=-1152534110837669889L;
		data[3]=18L;
		return data;
	}
	public static final BitSet _tokenSet_213 = new BitSet(mk_tokenSet_213());
	private static final long[] mk_tokenSet_214() {
		long[] data = { -3530822107992555280L, 140739632687103L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_214 = new BitSet(mk_tokenSet_214());
	private static final long[] mk_tokenSet_215() {
		long[] data = { 536870928L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_215 = new BitSet(mk_tokenSet_215());
	
	}
