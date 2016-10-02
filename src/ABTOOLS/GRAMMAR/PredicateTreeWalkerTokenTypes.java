// $ANTLR 2.7.6 (2005-12-22): "PredicateTreeWalker.g" -> "PredicateTreeWalker.java"$

    package ABTOOLS.GRAMMAR;

public interface PredicateTreeWalkerTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int B_COMMA = 4;
	int B_IDENTIFIER = 5;
	int B_POINT = 6;
	int B_CPRED = 7;
	int B_IMPLIES = 8;
	int LITERAL_or = 9;
	int B_AND = 10;
	int B_EQUIV = 11;
	int B_EQUAL = 12;
	int B_SUBSET = 13;
	int B_NOTSUBSET = 14;
	int B_STRICTSUBSET = 15;
	int B_NOTSTRICTSBSET = 16;
	int B_LESS = 17;
	int B_GREATER = 18;
	int B_NOTEQUAL = 19;
	int B_LESSTHANEQUAL = 20;
	int B_GREATERTHANEQUAL = 21;
	int B_CONCATSEQ = 22;
	int B_PREAPPSEQ = 23;
	int B_APPSEQ = 24;
	int B_PREFIXSEQ = 25;
	int B_SUFFIXSEQ = 26;
	int LITERAL_bool = 27;
	int B_LPAREN = 28;
	int B_RPAREN = 29;
	int B_RELATION = 30;
	int B_PARTIAL = 31;
	int B_TOTAL = 32;
	int B_PARTIAL_INJECT = 33;
	int B_TOTAL_INJECT = 34;
	int B_PARTIAL_SURJECT = 35;
	int B_TOTAL_SURJECT = 36;
	int B_BIJECTION = 37;
	int B_DOMAINRESTRICT = 38;
	int B_RANGERESTRICT = 39;
	int B_DOMAINSUBSTRACT = 40;
	int B_RANGESUBSTRACT = 41;
	int B_OVERRIDEFORWARD = 42;
	int B_OVERRIDEBACKWARD = 43;
	int B_RELPROD = 44;
	int B_UNION = 45;
	int B_INTER = 46;
	int B_MAPLET = 47;
	int B_MULT = 48;
	int B_POWER = 49;
	int B_DIV = 50;
	int LITERAL_mod = 51;
	int B_ADD = 52;
	int B_MINUS = 53;
	int B_SEQEMPTY = 54;
	int B_BRACKOPEN = 55;
	int B_BRACKCLOSE = 56;
	int B_RANGE = 57;
	int B_EMPTYSET = 58;
	int B_CURLYOPEN = 59;
	int B_CURLYCLOSE = 60;
	int B_SUCH = 61;
	int B_ASTRING = 62;
	int LITERAL_TRUE = 63;
	int LITERAL_FALSE = 64;
	int LITERAL_rec = 65;
	int B_TILDE = 66;
	int B_NUMBER = 67;
	int B_QUOTEIDENT = 68;
	int LITERAL_ran = 69;
	int LITERAL_not = 70;
	int LITERAL_dom = 71;
	int LITERAL_min = 72;
	int LITERAL_max = 73;
	int LITERAL_card = 74;
	int B_SEMICOLON = 75;
	int B_PARALLEL = 76;
	int B_FORALL = 77;
	int B_EXISTS = 78;
	int B_LAMBDA = 79;
	int LITERAL_PI = 80;
	int LITERAL_SIGMA = 81;
	int LITERAL_UNION = 82;
	int LITERAL_INTER = 83;
	int LITERAL_struct = 84;
	int B_INSET = 85;
	int LITERAL_INT = 86;
	// "INT1" = 87
	int LITERAL_INTEGER = 88;
	// "INTEGER1" = 89
	int LITERAL_BOOL = 90;
	int LITERAL_NAT = 91;
	// "NAT1" = 92
	int LITERAL_NATURAL = 93;
	// "NATURAL1" = 94
	int UNARY_ADD = 95;
	int UNARY_MINUS = 96;
	int ELEM_SET = 97;
	int LIST_VAR = 98;
	int APPLY_TO = 99;
	int PARENT = 100;
	int B_SELECTOR = 101;
	int PRODSET = 102;
	int B_NOT = 103;
	int B_DOM = 104;
	int B_RAN = 105;
	int B_MIN = 106;
	int B_MAX = 107;
	int B_CARD = 108;
	int B_MOD = 109;
	int B_NOTINSET = 110;
	int LITERAL_STRING = 111;
	int WS = 112;
	int ML_COMMENT = 113;
	int NEWLINE = 114;
	int B_DOUBLE_EQUAL = 115;
	int B_dollars = 116;
	int B_OUT = 117;
	int B_garde = 118;
	int B_BECOME_ELEM = 119;
	int B_SIMPLESUBST = 120;
	int DIGIT = 121;
	int ALPHA = 122;
	int VOCAB = 123;
	int LITERAL_MACHINE = 124;
	int LITERAL_END = 125;
	int LITERAL_REFINEMENT = 126;
	int LITERAL_IMPLEMENTATION = 127;
	int LITERAL_CONSTRAINTS = 128;
	int LITERAL_EXTENDS = 129;
	int LITERAL_USES = 130;
	int LITERAL_INCLUDES = 131;
	int LITERAL_SEES = 132;
	int LITERAL_IMPORTS = 133;
	int LITERAL_PROMOTES = 134;
	int LITERAL_REFINES = 135;
	int LITERAL_CONSTANTS = 136;
	int LITERAL_CONCRETE_CONSTANTS = 137;
	int LITERAL_VISIBLE_CONSTANTS = 138;
	int LITERAL_ABSTRACT_CONSTANTS = 139;
	int LITERAL_HIDDEN_CONSTANTS = 140;
	int LITERAL_SETS = 141;
	int LITERAL_VALUES = 142;
	int LITERAL_PROPERTIES = 143;
	int LITERAL_VARIABLES = 144;
	int LITERAL_ABSTRACT_VARIABLES = 145;
	int LITERAL_VISIBLE_VARIABLES = 146;
	int LITERAL_INVARIANT = 147;
	int LITERAL_HIDDEN_VARIABLES = 148;
	int LITERAL_CONCRETE_VARIABLES = 149;
	int LITERAL_DEFINITIONS = 150;
	int LITERAL_ASSERTIONS = 151;
	int LITERAL_INITIALISATION = 152;
	int LITERAL_OPERATIONS = 153;
	int LITERAL_skip = 154;
	int LITERAL_BEGIN = 155;
	int LITERAL_PRE = 156;
	int LITERAL_THEN = 157;
	int LITERAL_ASSERT = 158;
	int LITERAL_IF = 159;
	int LITERAL_ELSIF = 160;
	int LITERAL_CHOICE = 161;
	int LITERAL_OR = 162;
	int LITERAL_CASE = 163;
	int LITERAL_OF = 164;
	int LITERAL_EITHER = 165;
	int LITERAL_ELSE = 166;
	int LITERAL_ANY = 167;
	int LITERAL_WHERE = 168;
	int LITERAL_SELECT = 169;
	int LITERAL_WHEN = 170;
	int LITERAL_LET = 171;
	int LITERAL_BE = 172;
	int LITERAL_IN = 173;
	int LITERAL_VARIANT = 174;
	int LITERAL_WHILE = 175;
	int LITERAL_DO = 176;
	int LITERAL_VAR = 177;
	int PARALLEL = 178;
	int OP_DEF = 179;
	int INSET = 180;
	int FUNC_CALL_PARAM = 181;
	int SEQUENTIAL = 182;
	int EXP_DEF = 183;
	int LIST_DEF = 184;
	int SUBST_DEF = 185;
	int SUBST_TO = 186;
	int BTRUE = 187;
}
