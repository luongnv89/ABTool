
// 
// 	Author			:	Danni Yu, Juan Pablo Garcia Cifuentes
//								based on ExpressionTreeWalker.g made by Boulanger Jean-Louis 
//	EMAIL				:	S.DanniYu@gmail.com, JuanPabloGarciaCifuentes@gmail.com
// 	File				:	toJML_ExpressionTreeWalker.g
//	Descripton	:	Expression Tree Walker in order to generate JML code
//

// Releases
//  July 2010 v 1.0
//					- first version
//  May 2011 v 1.1

//
// This file is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.  
//

// /**
//	* @author <a href="mailto:S.DanniYu@gmail.com">Danni Yu</a>
//  * @author <a href="mailto:JuanPabloGarciaCifuentes@gmail.com">Juan Pablo Garcia Cifuentes</a>
//  **/

header 
{
    package ABTOOLS.GRAMMAR;
}
 
// Import the necessary classes 
//-----------------------------------------------------------------------------

{
// ANTLR packages
	import antlr.debug.misc.*;
	import antlr.DumpASTVisitor;
	// ABTools packages
	import ABTOOLS.DEBUGGING.*;
	import ABTOOLS.ANTLR_TOOLS.*;
	import ABTOOLS.PRINTING.*;
	// usefull packages
	import java.io.*;
	import java.util.*;
}

// Define a Tree Walker
//-----------------------------------------------------------------------------

class toJML_ExpressionTreeWalker extends TreeParser;


// Grammar become HERE
// -------------------

// (01/2001)
//---Nov 6-----------------------
is_record	:
			#(t1:"rec"
				listrecord
			)
			|
			#(t01:"struct"
				listrecord
			)
;

listrecord	:
            #(tt:B_COMMA 
				listrecord
				a_record
			)
        |	a_record
;

a_record	:	
            #(B_SELECTOR 
				name:B_IDENTIFIER
				predicate
			)
        |	predicate			
;

list_New_Predicate returns [String code] {
			code="";
			String l1="";
			String l2="";
}:	
        #(tt:B_COMMA 
				l1=list_New_Predicate
				l2=new_predicate
			){
				code=l1+","+l2;
			}
        |	code=new_predicate
		;

new_predicate	returns [String code] {
		code="";
		String l1="";
		String l2="";
}:
	    #(t1:B_SEMICOLON 
				l1=new_predicate
				l2=predicate
			){
				code=l1+";"+l2;	
			}
        |
			#(t2:B_PARALLEL  
				l1=new_predicate
				l2=predicate
			){
				code=l1+" && "+l2;
			}
	|	code=predicate
;

/**
 * Ne pas utiliser de fonction d'impression pour le renommage ....
 **/

nameRenamed	returns [String code]
{
	code = "";
} 
:	
	n1:B_IDENTIFIER
	{
		code = n1.getText();
		// FLURB
	}
	| {
			String nr1 = "";
			String nr2 = "";
		}
		#(tt:B_POINT 
		nr1 = nameRenamed
		nr2 = nameRenamed
		)
		{
			code = nr1 + "." + nr2;
		}
;

nameRenamedDecorated returns [String code] 
{
	code = "";
	String s = "";
} 
:	
	#(tt:B_CPRED 
	s = nameRenamed
	{
		code = s;

	}
	)
	|	s = nameRenamed
		{
			// toJML begin
			code = s;
			// TODO: fix to use real type info
			/*
			s.put("vars", nr.get("vars"));
			s.put("types", nr.get("types"));
			*/
			// toJML end
		}
;

nameRenameDecoratedInverted returns [String code]{
	code = "";
	String s="";
}:	
        #(tt:B_TILDE 
					s=nameRenamedDecorated
				) {
					code = s+".inverse()";
				}
			|	code=nameRenamedDecorated
;

list_identifier	returns [String code] {
	code="";
	String l1="";
}:
 	      #(tt:B_COMMA
		 		l1=list_identifier
				n1:B_IDENTIFIER
			){
				code=l1+","+n1.getText();
			}
			|	n2:B_IDENTIFIER {
				code=n2.getText();		
			}
    ;

listPredicate returns [String code]	
{
code="";
String s1="";
String s2="";
}:
      #(tt:ELEM_SET
		 		s1=listPredicate
			 	s2=predicate
			)
			{
			code=s1+","+s2;
			}
			|	code=predicate
;

a_func_call	returns [String code] {
	code="";
}:
   code= afc
;

//changed nov 6th-----------
afc	returns [String code] {
	code="";
	String s1="";
	String s2="";
}:
	    #(FUNC_CALL_PARAM
			 	s1=afc
			 	s2=listPredicate
		 	){
		 		code=s1+".("+s2+")";
		 	}
        |
			#(t1:B_QUOTEIDENT //access to a record field, manual p51
				afc
				afc
			)
        |
      #(B_LPAREN
			 	l1=afc
       	l2=listPredicate
      ){
      	code="("+l1+")."+l2;
      }
        |	code=nameRenamed
;

list_func_call	returns [String code]{
	code="";
	String l1="";
	String l2="";
}:
	        #(tt:B_COMMA
		  		l1=list_func_call
			 		l2=list_func_call
			){
			code=l1+","+l2;
			}
        |	code=a_func_call
;
//---Nov 6th------------

/** 
 *  PREDICATE 
 **/

predicate returns [String code] 
	{
		String p1 = "";
		String p2 = "";
		code = "";
	}
:
	#(t1:B_AND			
	p1 = predicate
	p2 = predicate
	)	
	{
		code = p1 + "\n && " + p2;
	}
	|	#(t2:"or"
		p1=predicate	
		p2=predicate
		)
	{
		code = p1 + "\n || " + p2;
	}
	| #(t3:B_IMPLIES
		p1=predicate
		p2=predicate
		)
	{
		code = "(" + p1 + ")" + "\n ==> (" + p2 + ")";
	}
	| #(t4:B_EQUIV
	 	p1=predicate
		p2=predicate
		)
		{
		code = "(" + p1 + ")" + "\n <==> (" + p2 + ")";
		}
	| #(t5:B_MULT
		p1 = pred1:predicate
		p2 = pred2:predicate
		)
		{
			//if p1 and p2 are integers, do arithmetic multiplication
			if (pred1.getBType() instanceof ABTOOLS.TYPING.INTEGER &&
			    pred2.getBType() instanceof ABTOOLS.TYPING.INTEGER) {
			    code = p1+"*"+p2;
			} else {
				// else make cartesian product
				code = "org.jmlspecs.b2jml.util.ModelUtils.cartesian("+p1+", "+p2+")";
			}
		}
			|	
			#(t6:B_POWER
				p1=predicate
			 	p2=predicate
			)
			{
			code="Math.pow("+p1+","+p2+")";		
			}
			|
			#(t7:B_DIV
	  	  p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"/"+p2;		
			}
			|
			#(t8:"mod"
	  		p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"%"+p2;		
			}
			|
			#(t9:UNARY_ADD
			 	p1=predicate
			)
			{
				code="+"+p1;
			}
			|
			#(t10:UNARY_MINUS
			 	p2=predicate
			)
			{
				code="-"+p1;
			}
			|
		 	#(t11:B_ADD 
	  		p1=predicate
			 	p2=predicate
			)
			{
			code=p1+"+"+p2;		
			} 
			|
			#(t12:B_MINUS
		 		p1=predMinus1:predicate
			 	p2=predMinus2:predicate
			)
			{
			if (predMinus1.getBType() instanceof ABTOOLS.TYPING.INTEGER &&
			    predMinus2.getBType() instanceof ABTOOLS.TYPING.INTEGER) {
				code = p1+"-"+p2;
			} else {
	        	ABType type1 = predMinus1.getBType();
	        	ABType type2 = predMinus2.getBType();
	        	if (type1 instanceof INFIXE && !(type2 instanceof INFIXE)) {
	        		if (toJML_TreeWalker.isFunction(type1)) {
	        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p2 + ")";
	        		} else {
	        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
	        		}
	        	} else if (type2 instanceof INFIXE && !(type1 instanceof INFIXE)) {
	        		if (toJML_TreeWalker.isFunction(type2)) {
	        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p1 + ")";
	        		} else {
	        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
	        		}
	        	}
				code = p1+".difference("+p2+")";
			}
			}
			|
			#(t13:B_EQUAL
	 			p1=predEqual1:predicate
			 	p2=predEqual2:predicate
			)
			{
			if (((predEqual1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
					(predEqual2.getBType() instanceof ABTOOLS.TYPING.INTEGER)) ||
					((predEqual1.getBType() instanceof ABTOOLS.TYPING.BOOL) ||
					(predEqual2.getBType() instanceof ABTOOLS.TYPING.BOOL)))
			{
				code=p1+"=="+p2;
			}
			else
			{
				code=p1+".equals("+p2+")";
			}
			}
			|
			#(t14:B_LESS
		 		p1=predLess1:predicate
				p2=predLess2:predicate
			)
			{
			if ((predLess1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
					(predLess2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+"<"+p2;
			}
			else
			{
				code=p1+".compareTo("+p2+")<0";
			}
			}
			|
			#(t15:B_GREATER
	 			p1=predGreater1:predicate
				p2=predGreater2:predicate
			)
			{
			if ((predGreater1.getBType() instanceof ABTOOLS.TYPING.INTEGER)||
					(predGreater2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+">"+p2;
			}
			else
			{
				code=p1+".compareTo("+p2+")>0";
			}
			}
			|
			#(t16:B_NOTEQUAL
				p1=predNEqual1:predicate
				p2=predNEqual2:predicate
			)
			{
			if (((predNEqual1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
					(predNEqual2.getBType() instanceof ABTOOLS.TYPING.INTEGER)) ||
					((predNEqual1.getBType() instanceof ABTOOLS.TYPING.BOOL) ||
					(predNEqual2.getBType() instanceof ABTOOLS.TYPING.BOOL)))
			{
				code=p1+"!="+p2;
			}
			else
			{
				code="!"+p1+".equals("+p2+")";
			}
			}
			|
			#(t17:B_LESSTHANEQUAL
		 		p1=predLTE1:predicate
				p2=predLTE2:predicate
			)
			{
			if ((predLTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER) ||
					(predLTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+"<="+p2;
			}
			else
			{
				code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")<0";
			}
			}
			|
			#(t18:B_GREATERTHANEQUAL
				p1=predGTE1:predicate
				p2=predGTE2:predicate
			)
			{
			if ((predGTE1.getBType() instanceof ABTOOLS.TYPING.INTEGER)&&
					(predGTE2.getBType() instanceof ABTOOLS.TYPING.INTEGER))
			{
				code=p1+">="+p2;
			}
			else
			{
				code=p1+".equals()"+p2+"||"+p1+".compareTo("+p2+")>0";
			}
			}
		|
		#(t19:B_INSET
		p1 = predicate
		p2 = predicate
		)
		{
			code = p2 + ".has(" + p1 + ")";
		}
			|
			#(t20:B_NOTINSET
				p1=predicate
				p2=predicate
			)
			{
				code = "!"+p2 + ".has(" + p1 + ")";
			}
	| #(t21:B_SUBSET
		p1 = pl1:predicate
		p2 = pl2:predicate
		)
		{
		    if (pl1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = p1 + ".isSubset(" + p2 + ")";
		}
	|
			#(t22:B_NOTSUBSET
				p1=pl1a:predicate
				p2=pl2a:predicate
			)
			{
		    if (pl1a.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2a.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = "!"+p1 + ".isSubset(" + p2 + ")";
			}
			|
			#(t23:B_STRICTSUBSET
				p1=pl1b:predicate 	
				p2=pl2b:predicate
			)
			{
		    if (pl1b.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2b.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = p1 + ".isProperSubset(" + p2 + ")";
			}
			|
			#(t24:B_NOTSTRICTSBSET
				p1=pl1c:predicate 	
				p2=pl2c:predicate
			)
			{
		    if (pl1c.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
		    if (pl2c.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
			code = "(!"+p1 + ".isProperSubset(" + p2 + ")";
			}
			|
			#(t25:B_CONCATSEQ
				p1=predicate 	
				p2=predicate
			)
			{
				code=p1+".concat("+p2+")";
			}
			|
			#(t26:B_PREAPPSEQ
				p1=predicate 	
				p2=predicate
			)
			{
				code = p2+".insertBeforeIndex(0,"+p1+")";
			}
			|
			#(t27:B_APPSEQ
				p1=predicate
				p2=predicate
			)
			{
				code=p1+".insertAfterIndex("+p1+".int_length()-1,"+p2+")";
			}
			|
			#(t28:B_PREFIXSEQ
				p1=predicate
				p2=predicate
			)
			{
				code=p1+".prefix("+p2+")";
			}
			|
			#(t29:B_SUFFIXSEQ
		 		p1=predicate
				p2=predicate
			)
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.suffixseq("+p1+","+p2+")";	
			}
		|	#(t30:B_RELATION		
		p1 = predr1:predicate
		p2 = predr2:predicate
		)
		{
/*			code="(new org.jmlspecs.b2jml.util.Relation("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Relation<" + 
				   toJML_TreeWalker.typeToString(predr1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predr2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
		}
			|	
      #(t31:B_PARTIAL
         p1=predp1:predicate
         p2=predp2:predicate
       )
       {
/*         code="(new org.jmlspecs.b2jml.util.Partial("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Partial<" + 
				   toJML_TreeWalker.typeToString(predp1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predp2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
       }
	|	#(t32:B_TOTAL
		p1 = predt1:predicate
		p2 = predt2:predicate
		)
		{
/*			code="(new org.jmlspecs.b2jml.util.Total("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Total<" + 
				   toJML_TreeWalker.typeToString(predt1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predt2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
		}
	|	
     #(t33:B_PARTIAL_INJECT
		 p1=predpi1:predicate
   		 p2=predpi2:predicate
    	)
      {
/* 	   	code="(new org.jmlspecs.b2jml.util.PartialInject("+p1+","+p2+"))";*/
			code = "(new org.jmlspecs.b2jml.util.PartialInject<" + 
				   toJML_TreeWalker.typeToString(predpi1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predpi2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
      }
			|	
            #(t34:B_TOTAL_INJECT
		      p1=predti1:predicate
              p2=predti2:predicate
            )
            {
 /*           	code="(new org.jmlspecs.b2jml.util.TotalInject("+p1+","+p2+"))"; */
 			code = "(new org.jmlspecs.b2jml.util.TotalInject<" + 
				   toJML_TreeWalker.typeToString(predti1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predti2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
            }
			|	
      #(t35:B_PARTIAL_SURJECT
		  p1=predps1:predicate
      	  p2=predps2:predicate
      ){
 /*     code="(new org.jmlspecs.b2jml.util.PartialSurject("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.PartialSurject<" + 
				   toJML_TreeWalker.typeToString(predps1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predps2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
      }
			|
	        #(t36:B_TOTAL_SURJECT
		    p1=predts1:predicate
            p2=predts2:predicate
            ){
/*            	code="(new org.jmlspecs.b2jml.util.TotalSurject("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.TotalSurject<" + 
				   toJML_TreeWalker.typeToString(predts1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predts2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";

            }
			|
	        #(t37:B_BIJECTION
 		        p1=predb1:predicate
            	p2=predb2:predicate
            ){
/*            	code="(new org.jmlspecs.b2jml.util.Bijection("+p1+","+p2+"))"; */
			code = "(new org.jmlspecs.b2jml.util.Bijection<" + 
				   toJML_TreeWalker.typeToString(predb1.getBType(), false, true) + ", " +
				   toJML_TreeWalker.typeToString(predb2.getBType(), false, true) + ">(" +
				   p1 + ", " + p2 + "))";
            }
			|
	        #(t38:B_DOMAINRESTRICT
		        	p1=predicate
              p2=predicate
            )
            {
							code=p2+".restrictDomainTo("+p2+".domain().intersection("+p1+"))";
            }
						|
	        	#(t39:B_RANGERESTRICT
		        	p1=predicate
              p2=predicate
            )
            {
							code=p1+".restrictRangeTo("+p1+".range().intersection("+p2+"))";          
            }
					|
	        #(t40:B_DOMAINSUBSTRACT
		        	p1=ds1:predicate
              p2=ds2:predicate
            )
            {
            	if (ds1.getBType() instanceof INFIXE) p1 = "JMLEqualsSet.convertFrom(" + p1 + ")";
            	if (!(ds2.getBType() instanceof INFIXE)) p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
            	code="org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction("+p1+","+p2+")";
            }
            
			|
	        #(t41:B_RANGESUBSTRACT
	        	p1=rs1:predicate 
	        	p2=rs2:predicate
	        )
	        {
            	if (rs2.getBType() instanceof INFIXE) p2 = "JMLEqualsSet.convertFrom(" + p2 + ")";
            	if (!(rs1.getBType() instanceof INFIXE)) p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
	        	code="org.jmlspecs.b2jml.util.ModelUtils.range_subtraction("+p1+","+p2+")";  
	        }
			|
	        #(t42:B_OVERRIDEFORWARD
	        	p1=predicate
	        	p2=predicate
	        )
	        {
	        	code="org.jmlspecs.b2jml.util.ModelUtils.overrideforward("+p1+","+p2+")";  	        
	        }
					|
	        #(t43:B_OVERRIDEBACKWARD
	         	p1=predicate
	         	p2=predicate
	         ) //not in B ref manual
					|
	        #(t44:B_RELPROD
	        	p1=predicate
	        	p2=predicate
	        )
	        {
	        	code="org.jmlspecs.b2jml.util.ModelUtils.relprod("+p1+","+p2+")";  
	        }
			|
	        #(t45:B_UNION
	        	p1=pu1:predicate
	        	p2=pu2:predicate
	        )
	        {
	        	ABType type1 = pu1.getBType();
	        	ABType type2 = pu2.getBType();
	        	if (type1 instanceof INFIXE && !(type2 instanceof INFIXE)) {
	        		if (toJML_TreeWalker.isFunction(type1)) {
	        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p2 + ")";
	        		} else {
	        			p2 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p2 + ")";
	        		}
	        	} else if (type2 instanceof INFIXE && !(type1 instanceof INFIXE)) {
	        		if (toJML_TreeWalker.isFunction(type2)) {
	        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toMap(" + p1 + ")";
	        		} else {
	        			p1 = "org.jmlspecs.b2jml.util.ModelUtils.toRel(" + p1 + ")";
	        		}
	        	}
	        	code=p1+".union("+p2+")";
	        }
			|
	        #(t46:B_INTER
			 			p1=predicate
					 	p2=predicate
				)
				{
					code=p1+".intersection("+p2+")";
				}
			|	#(t48:B_MAPLET
						p1=predicate
						p2=predicate
				)
				{
					code="org.jmlspecs.b2jml.util.ModelUtils.maplet("+p1+","+p2+")";  	   
				}
			|	#(t49:LIST_VAR
			 			p1=predicate
						p2=predicate
				) 
				{
					code=p1+","+p2;
				}
			|	#(B_NOT
					p1=predicate
			)
			{
				code="!"+p1;
			}
			|	#(B_RAN
					p1=predicate 
			)
			{
				code=p1+".range()";
			}
			|	#(B_DOM
					p1=predicate 
			)
			{
				code=p1+".domain()";
			}
			|	#(B_MIN
				p1=predicate
			)
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.min("+p1+")";
			}
			|	#(B_MAX
					p1=predicate
			)			
			{
				code="org.jmlspecs.b2jml.util.ModelUtils.max("+p1+")";
			}
			|	#(B_CARD
					p1=predicate
			)
			{
				code=p1+".int_size()";
			}
			|	{
				String cd = "";
				}
				cd = cs:cset_description
		{
			// toJML begin
			// TODO: fix to use types rather than HashMap
			code = cd;
			/*
			String type = cd.get("types");
			if (type.length()>=5 && type.substring(0,5).compareTo("PROD(")==0)
			{
				listPredicate.add("PROD");
			}
			else
			{
				if (type.length()>=5 && type.substring(0,5).compareTo("ENUM(")==0)
				{
					listPredicate.add("ENUM");
				}
				else
				{
					if (type.length()>=4 && type.substring(0,4).compareTo("MULT")==0)
					{
						listPredicate.add("MULT");
					}
					else
					{
						listPredicate.add("BASIC");
					}
				}
			}
			listPredicate.add(cd.get("vars"));
			*/
			// toJML end
		}
;



cset_description returns [String code] 
{
	code = "";
	String p="";
	String s1;
	String s2;
}  
:
	code=basic_sets 
	|	{
			String cv = "";
		}
		cv = cbasic_value
		{
			// toJML begin
			code = cv;
			// toJML end
		}
	|	#("bool"
		p=predicate
		){
		code=p;
		}
	| #(B_BRACKOPEN
		p=listPredicate
		){
		code=p;
		}
	| #(t2:B_RANGE
		s1=predicate
		s2=predicate
		) {
			code = "new org.jmlspecs.b2jml.util.Interval("+s1+","+s2+")";
		} // p1..p2 interval
	|	t3:B_EMPTYSET
		{
		code="JMLEqualsSet.EMPTY";
		}
	|	#(t4:B_CURLYOPEN
		p=pnode1: cvalue_set
		) {
		String tys = toJML_TreeWalker.typeToString(pnode1.getBType());
		if (!tys.equals("Type_Error")) {
			String set = "toSet";
			if (pnode1.getBType() instanceof INFIXE) {
				set = "toRel";
				t4.setBType(pnode1.getBType());
			}
			code = "org.jmlspecs.b2jml.util.ModelUtils." + set + "(" + p + ")";
			// code="JMLEqualsSet.convertFrom(new "+toJML_TreeWalker.typeToString(pnode1.getBType(), false, false)+"[] {"+p+"})";
		} else {
			String typeStrin = pnode1.getBType().toString();
			if (typeStrin.contains("STRING") || typeStrin.contains("ENUM") ) 
				code="JMLEqualsSet.convertFrom(new String[] {"+p+"})"; 
			else if (typeStrin.contains("BOOL"))
			  code="JMLEqualsSet.convertFrom(new boolean[] {"+p+"})";
			else if (typeStrin.contains("INFIXE") || typeStrin.contains("EQUAL") ||
							typeStrin.contains("LIST") || typeStrin.contains("PROD") ||
							typeStrin.contains("RANGE"))
				code="JMLEqualsSet.convertFrom(new JMLEqualsToEqualsRelation[] {"+p+"})";
		  else if(typeStrin.contains("UNARY") || typeStrin.contains("SET") ||
		  				typeStrin.contains("POW"))
		    code="JMLEqualsSet.convertFrom(new JMLEqualsSet[] {"+p+"})";
		  else 
			  code="JMLEqualsSet.convertFrom(new Integer[] {"+p+"})";
		}
		}
	| t1:B_SEQEMPTY {
		code=t1.getText();
	}
	|	is_record 
	|	code=quantification 
	|	q_lambda
;

// Dans un ensemble il peut y avoir 
cvalue_set returns [String code] {
		code="";
		String l1="";
		String p1="";
}:		
     #(t1:B_SUCH
			 		l1=list_var
			 		p1=predicate
				){
					code=l1+";"+p1;
				}
			|	#(t2:ELEM_SET
			 		l1=cvalue_set
			 		p1=predicate
				){
					code=l1+","+p1;
				}
			|	code=predicate
		;

cbasic_value returns [String code] 
{
	code = "";
	String p = "";
	String p1 = "";
} 
:
	t1:B_ASTRING
	{
		code = "\""+t1+"\"";
	}
	|	t2:B_NUMBER
		{
			code = t2.getText();
		}
	|	#(B_TILDE
		p = predicate
		{
			code = p+".inverse()";
		}
		)
	|	
		p = nr:nameRenamedDecorated
		{
			// toJML begin
			code = p;
			if (nr.getBType() instanceof ENUM && enumConstants.contains(p)) {
				code = "\"" + p + "\"";
			}
			// toJML end
		}
	|	#(B_LPAREN
		p = pl0: predicate
		p1 = pl1: predicate
		{
		    String name = p.toString();
		    if (name.equalsIgnoreCase("set")) {
		        code = "new org.jmlspecs.b2jml.util.Set<" +
		              toJML_TreeWalker.typeToString(pl1.getBType()) + ">()";
		    } else if (name.equalsIgnoreCase("seq")) {
		        code = "new org.jmlspecs.b2jml.util.Seq<" +
		              toJML_TreeWalker.typeToString(pl1.getBType()) + ">()";
		    } else if (pl0.getBType() instanceof SEQ && pl1.getBType() instanceof INTEGER) {
		    	code = p + ".itemAt(" + p1 + " - 1)";
		    } else if (pl1.getBType() instanceof SEQ && toJML_TreeWalker.seqOps.get(name) != null) {
		        if (name.equals("conc")) {
			        code = toJML_TreeWalker.seqOps.get(name) + "(" + p1 + ")";
		        } else {
			        code = p1 + "." + toJML_TreeWalker.seqOps.get(name) + "()";
		        }
			} else if (pl1.getBType() instanceof SET && toJML_TreeWalker.setOps.get(name) !=  null) {
				if (name.equals("inter") || name.equals("union")) {
			        code = toJML_TreeWalker.setOps.get(name) + "(" + p1 + ")";
				} else {
			        code = p1 + "." + toJML_TreeWalker.setOps.get(name) + "()";
				}	        
		    } else {
				code = p + ".apply("+p1+")";
			}
		}
		)
	|	
		#(PARENT
		p = pred_func_composition
		)
		{
			code = p;
		}
	|	#(B_QUOTEIDENT 
		predicate
		predicate 		
		)
	|	#(APPLY_TO
		p = pl1d:predicate
		p1 = pl2d:predicate
		){
			if (toJML_TreeWalker.isFunction(pl1d.getBType())) {
				code = p+".apply("+p1+")";
			} else if (pl1d.getBType() instanceof INFIXE) {
				String app = ".elementImage(";
				if (!pl2d.getBType().toString().equals(pl1d.getBType().getMember().toString())) {
					app = ".image(";
				}
				code = p + app + p1 + ")";
				System.out.println("HERE!");
				System.out.println(code);
				System.out.println(pl1d.getBType().getMember());
				System.out.println(pl1d.getBType().getMember().getClass());
				System.out.println(pl2d.getBType());
				System.out.println(pl2d.getBType().getClass());
			} else {
				code = p + "(" + p1 + ")";
			}
		}
	|	"TRUE"
		{
			code = "true";
		}
	|	"FALSE"
		{
			code = "false";
		}
	| BTRUE { code = "true";}
;


//-------changed nov 17------

pred_func_composition returns [String code] 
	{
		code = "";
		String p = "";
		String p2="";
	}		
:
	#(B_SEMICOLON 
	p=pred_func_composition
	p2=predicate
	){
		if (5>3) throw new IllegalStateException("Sequential statement not supported");	
	}
	|	#(t2:B_PARALLEL  
		p=pred_func_composition
		p2=predicate
		){
		code=p+" && " +p2;
		}
	|	#(t3:B_COMMA	 
		p=pred_func_composition
		p2=predicate
		){
		code=p+","+p2;
		}
	|	p = predicate
		{
			code=p;
			
			// toJML begin
			// TODO: fix to use real type information
			/*
			listPredicate.add(p.get(0));
			listPredicate.add(p.get(1));
			*/
			// toJML end
		}
;

basic_sets	returns [String code=new String()]:	
	t1:"INT"			    {code = "org.jmlspecs.b2jml.util.B_Types.B_Int"; }
	|	t2:"INT1"		    {code = "org.jmlspecs.b2jml.util.B_Types.B_Int1"; }
	|	t3:"INTEGER"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Integer"; }
	|	t4:"INTEGER1"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Integer1"; }
	|	t5:"BOOL"   		{code = "org.jmlspecs.b2jml.util.B_Types.B_Boolean";}
	|	t6:"NAT"    		{code = "org.jmlspecs.b2jml.util.B_Types.B_Nat"; }
	|	t7:"NAT1"	    	{code = "org.jmlspecs.b2jml.util.B_Types.B_Nat1"; }
	|	t8:"NATURAL"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Natural"; }
	|	t9:"NATURAL1"		{code = "org.jmlspecs.b2jml.util.B_Types.B_Natural1"; }
	|	t10:"STRING"		{code = "org.jmlspecs.b2jml.util.B_Types.B_String";}
;

quantification returns [String code]{
	code="";
	String s1="";
	String s2="";
}:
		#(t70:B_FORALL
			s1=forall1: list_var
			s2=predicate
		){
			code="("+back+"forall "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
		}
		|	#(t71:B_EXISTS
			s1=exists1:list_var
			s2=predicate
		){
			code="("+back+"exists "+toJML_TreeWalker.typeToString(sqType.pop())+" "+s1+";"+s2+")";
		}
		;

// Attention il peut y avoir des parentheses
// Mais on les gardes pas

q_lambda :
        #(t1:B_LAMBDA
			q_operande
				)
			|	#(t2:"PI"
					q_operande
				)
			|	#(t3:"SIGMA"
					q_operande
				)
			|	#(t4:"UNION"
					 q_operande
				)
			|	#(t5:"INTER"
					 q_operande
				)
		;

q_operande	:
	    #(t1:B_SUCH
				list_var 
				predicate
				predicate
			)
		;

// Dans le second cas il y a un blanc car pas de ( pour separer
list_var returns [String code] {
	code="";
	String s1="";
}	:
	    #(B_LPAREN
				s1=list_identifier
			){
				code="("+s1+")";
			}
			|
			code=list_identifier
;