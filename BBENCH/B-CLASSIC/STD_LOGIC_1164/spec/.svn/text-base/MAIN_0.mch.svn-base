/*
        Les bases de BHDL.
         - la bibliotheque STD_Logic_1164 modelisee en B (3 composants)
         - une machine d'entree (conversion)
         - une machine modelisant les proprietes de la logique.
           Preuve difficile pour l'AB.

*/

MACHINE
	MAIN_0


/*?
    -- logic state system  (unresolved)
    TYPE std_ulogic IS ( 'U',  -- Uninitialized
                         'X',  -- Forcing  Unknown
                         '0',  -- Forcing  0
                         '1',  -- Forcing  1
                         'Z',  -- High Impedance   
                         'W',  -- Weak     Unknown
                         'L',  -- Weak     0     
                         'H',  -- Weak     1   
                         '-'   -- Don't care
                       );
?*/

SETS
	STD_ULOGIC	= { 	UU		/* uninitialized   */
			  , 	XX		/* etat inconnu    */
			  , 	OO		/* 0 fort 	   */
			  , 	II		/* 1 fort	   */
			  , 	ZZ		/* Haute impedance */
			  , 	WW		/* Inconnu faible  */
			  , 	LL		/* 0 faible	   */
			  , 	HH 		/* 1 faible 	   */
			  , 	DD		/* Don't CARE 	   */
			  }

/* 
	UU 	sert a initialiser le systeme et permet de verifier 
		qu'a tout moment une sortie est bien le resultat d'un calcul
	XX	initialise mais il y a plusieurs sources contradictoire
		0, 1 ou Z
	OO	connecte a la masse
	II	connecte a l'alimentation
	LL, HH 	pas de valeurs particuliere, mais interprete respectivement comme O et 1  
*/

CONSTANTS
/* Les objets suivants sont tous des constantes car ils n'evoluent pas dans la specification VHDL */
	XMAP
, 	BIT 
, 	XOI 
, 	XOIZ 
, 	UXOI 
, 	UXOIZ 
,	STD_LOGIC 

/*?
    -- industry standard logic type

    SUBTYPE std_logic IS resolved std_ulogic;

    -- common subtypes

    SUBTYPE X01     IS resolved std_ulogic RANGE 'X' TO '1'; -- ('X','0','1') 
    SUBTYPE X01Z    IS resolved std_ulogic RANGE 'X' TO 'Z'; -- ('X','0','1','Z') 
    SUBTYPE UX01    IS resolved std_ulogic RANGE 'U' TO '1'; -- ('U','X','0','1') 
    SUBTYPE UX01Z   IS resolved std_ulogic RANGE 'U' TO 'Z'; -- ('U','X','0','1','Z') 
?*/

,  	AND_ULOGIC	/* TABLE OF THE FUNCTION  	*/
, 	RESOLVED 	/* TABLE OF THE FUNCTION RESOLVED 	*/
, 	AND_STD		/* TABLE OF THE FUNCTION AND 		*/
,	NAND_STD
, 	OR_STD		/* TABLE OF THE FUNCTION OR 		*/
,	NOR_STD
,  	XOR_STD 	/* TABLE OF THE FUNCTION XOR 		*/
,	XNOR_STD
,	NOT_STD  	/* TABLE OF THE FUNCTION NOT 		*/
, 	CVT_TO_BIT 	/* TABLE OF THE FUNCTION CVT_TO_XOI 	*/
,	CVT_TO_XOI 	/* TABLE OF THE FUNCTION CVT_TO_BIT 	*/
, 	CVT_TO_XOIZ 	/* TABLE OF THE FUNCTION CVT_TO_XOIZ 	*/
, 	CVT_TO_UXOI	/* TABLE OF THE FUNCTION CVT_TO_UXOI 	*/
, 	IS_X_VALUE	/* CONVERSION TABLE OF STD_ULOGIC TO BOOL*/


PROPERTIES
		BIT       		<:  STD_ULOGIC
&      	BIT       		=  {   OO  ,  II  }

&      	XMAP 			: STD_ULOGIC
&		XMAP 			: BIT
&      	XMAP       		= OO
 
&      	XOI        		<:  STD_ULOGIC
&      	XOI       		=  {   XX  ,  OO  ,  II  }

&      	XOIZ       		<:  STD_ULOGIC
&      	XOIZ      		=  {   XX  ,  OO  ,  II  ,  ZZ  }

&      	UXOI       		<:  STD_ULOGIC
&      	UXOI      		=  {   UU  ,  XX  ,  OO  ,  II  }

&      	UXOIZ      		<:  STD_ULOGIC
&      	UXOIZ     		=  {   UU  ,  XX  ,  OO  ,  II  ,  ZZ  }

&      	STD_LOGIC  		<:  STD_ULOGIC

/*
	On fait ici un essai, il n'es pas facile de dire 
	qu'elle est la meilleur forme pour la preuve
	a voir sur la duree
&       STD_LOGIC 		=  {UU , XX , OO , II , ZZ , WW , LL , HH } 
*/

&      	STD_LOGIC 		= STD_ULOGIC - { DD}


/* OPERATEUR LOGIC DE BASE */
/* ======================= */

&	AND_ULOGIC 	: STD_ULOGIC * STD_ULOGIC 	--> STD_LOGIC
& 	RESOLVED 	: STD_ULOGIC * STD_ULOGIC 	--> STD_LOGIC

& 	AND_STD 	: STD_ULOGIC * STD_ULOGIC 	--> UXOI
&	NAND_STD	: STD_ULOGIC * STD_ULOGIC 	--> UXOI
& 	OR_STD 		: STD_ULOGIC * STD_ULOGIC 	--> UXOI
&	NOR_STD		: STD_ULOGIC * STD_ULOGIC 	--> UXOI
& 	XOR_STD 	: STD_ULOGIC * STD_ULOGIC 	--> UXOI
&	XNOR_STD	: STD_ULOGIC * STD_ULOGIC 	--> UXOI
& 	NOT_STD 	: STD_ULOGIC  				--> UXOI

/* OPERATEUR DE CONVERSION */
/* ======================= */

&  	CVT_TO_XOI 		: STD_ULOGIC --> XOI
&  	CVT_TO_BIT 		: STD_ULOGIC --> BIT
&  	CVT_TO_XOIZ 	: STD_ULOGIC --> XOIZ
&  	CVT_TO_UXOI 	: STD_ULOGIC --> UXOI

/* RETOUR AU BOOLEEN */
/* ================= */

& 	IS_X_VALUE 	: STD_ULOGIC --> BOOL


/* Et on ajoute les tables de valuation */
/* ==================================== */

&  RESOLVED = { (UU,UU) |-> UU
              , (UU,XX) |-> UU
              , (UU,OO) |-> UU
              , (UU,II) |-> UU
              , (UU,ZZ) |-> UU
              , (UU,WW) |-> UU
              , (UU,LL) |-> UU
              , (UU,HH) |-> UU
              , (UU,DD) |-> UU

              , (XX,UU) |-> UU
              , (XX,XX) |-> XX
              , (XX,OO) |-> XX
              , (XX,II) |-> XX
              , (XX,ZZ) |-> XX
              , (XX,WW) |-> XX
              , (XX,LL) |-> XX
              , (XX,HH) |-> XX
              , (XX,DD) |-> XX

              , (OO,UU) |-> UU
              , (OO,XX) |-> XX
              , (OO,OO) |-> OO
              , (OO,II) |-> XX
              , (OO,ZZ) |-> OO
              , (OO,WW) |-> OO
              , (OO,LL) |-> OO
              , (OO,HH) |-> OO
              , (OO,DD) |-> XX

              , (II,UU) |-> UU
              , (II,XX) |-> XX
              , (II,OO) |-> XX
              , (II,II) |-> II
              , (II,ZZ) |-> II
              , (II,WW) |-> II
              , (II,LL) |-> II
              , (II,HH) |-> II
              , (II,DD) |-> XX

              , (ZZ,UU) |-> UU
              , (ZZ,XX) |-> XX
              , (ZZ,OO) |-> OO
              , (ZZ,II) |-> XX
              , (ZZ,ZZ) |-> ZZ
              , (ZZ,WW) |-> WW
              , (ZZ,LL) |-> LL
              , (ZZ,HH) |-> HH
              , (ZZ,DD) |-> XX

              , (WW,UU) |-> UU
              , (WW,XX) |-> XX
              , (WW,OO) |-> OO
              , (WW,II) |-> II
              , (WW,ZZ) |-> WW
              , (WW,WW) |-> WW
              , (WW,LL) |-> WW
              , (WW,HH) |-> WW
              , (WW,DD) |-> XX

              , (LL,UU) |-> UU
              , (LL,XX) |-> XX
              , (LL,OO) |-> OO
              , (LL,II) |-> II
              , (LL,ZZ) |-> LL
              , (LL,WW) |-> WW
              , (LL,LL) |-> LL
              , (LL,HH) |-> WW
              , (LL,DD) |-> XX

              , (HH,UU) |-> UU
              , (HH,XX) |-> XX
              , (HH,OO) |-> OO
              , (HH,II) |-> II
              , (HH,ZZ) |-> HH
              , (HH,WW) |-> WW
              , (HH,LL) |-> WW
              , (HH,HH) |-> HH
              , (HH,DD) |-> XX

              , (DD,UU) |-> UU
              , (DD,XX) |-> XX
              , (DD,OO) |-> XX
              , (DD,II) |-> XX
              , (DD,ZZ) |-> XX
              , (DD,WW) |-> XX
              , (DD,LL) |-> XX
              , (DD,HH) |-> XX
              , (DD,DD) |-> XX
              }

&  AND_STD  = { (UU,UU) |-> UU
              , (UU,XX) |-> UU
              , (UU,OO) |-> OO
              , (UU,II) |-> UU
              , (UU,ZZ) |-> UU
              , (UU,WW) |-> UU
              , (UU,LL) |-> OO
              , (UU,HH) |-> UU
              , (UU,DD) |-> UU

              , (XX,UU) |-> UU
              , (XX,XX) |-> XX
              , (XX,OO) |-> OO
              , (XX,II) |-> XX
              , (XX,ZZ) |-> XX
              , (XX,WW) |-> XX
              , (XX,LL) |-> OO
              , (XX,HH) |-> XX
              , (XX,DD) |-> XX

              , (OO,UU) |-> OO
              , (OO,XX) |-> OO
              , (OO,OO) |-> OO
              , (OO,II) |-> OO
              , (OO,ZZ) |-> OO
              , (OO,WW) |-> OO
              , (OO,LL) |-> OO
              , (OO,HH) |-> OO
              , (OO,DD) |-> OO

              , (II,UU) |-> UU
              , (II,XX) |-> XX
              , (II,OO) |-> OO
              , (II,II) |-> II
              , (II,ZZ) |-> XX
              , (II,WW) |-> XX
              , (II,LL) |-> OO
              , (II,HH) |-> II
              , (II,DD) |-> XX

              , (ZZ,UU) |-> UU
              , (ZZ,XX) |-> XX
              , (ZZ,OO) |-> OO
              , (ZZ,II) |-> XX
              , (ZZ,ZZ) |-> XX
              , (ZZ,WW) |-> XX
              , (ZZ,LL) |-> OO
              , (ZZ,HH) |-> XX
              , (ZZ,DD) |-> XX

              , (WW,UU) |-> UU
              , (WW,XX) |-> XX
              , (WW,OO) |-> OO
              , (WW,II) |-> XX
              , (WW,ZZ) |-> XX
              , (WW,WW) |-> XX
              , (WW,LL) |-> OO
              , (WW,HH) |-> XX
              , (WW,DD) |-> XX

              , (LL,UU) |-> OO
              , (LL,XX) |-> OO
              , (LL,OO) |-> OO
              , (LL,II) |-> OO
              , (LL,ZZ) |-> OO
              , (LL,WW) |-> OO
              , (LL,LL) |-> OO
              , (LL,HH) |-> OO
              , (LL,DD) |-> OO

              , (HH,UU) |-> UU
              , (HH,XX) |-> XX
              , (HH,OO) |-> OO
              , (HH,II) |-> II
              , (HH,ZZ) |-> XX
              , (HH,WW) |-> XX
              , (HH,LL) |-> OO
              , (HH,HH) |-> II
              , (HH,DD) |-> XX

              , (DD,UU) |-> UU
              , (DD,XX) |-> XX
              , (DD,OO) |-> OO
              , (DD,II) |-> XX
              , (DD,ZZ) |-> XX
              , (DD,WW) |-> XX
              , (DD,LL) |-> OO
              , (DD,HH) |-> XX
              , (DD,DD) |-> XX
              }

&    OR_STD = { (UU,UU) |-> UU
              , (UU,XX) |-> UU
              , (UU,OO) |-> UU
              , (UU,II) |-> II
              , (UU,ZZ) |-> UU
              , (UU,WW) |-> UU
              , (UU,LL) |-> UU
              , (UU,HH) |-> II
              , (UU,DD) |-> UU

              , (XX,UU) |-> UU
              , (XX,XX) |-> XX
              , (XX,OO) |-> XX
              , (XX,II) |-> II
              , (XX,ZZ) |-> XX
              , (XX,WW) |-> XX
              , (XX,LL) |-> XX
              , (XX,HH) |-> II
              , (XX,DD) |-> XX

              , (OO,UU) |-> UU
              , (OO,XX) |-> XX
              , (OO,OO) |-> OO
              , (OO,II) |-> II
              , (OO,ZZ) |-> XX
              , (OO,WW) |-> XX
              , (OO,LL) |-> OO
              , (OO,HH) |-> II
              , (OO,DD) |-> XX

              , (II,UU) |-> II
              , (II,XX) |-> II
              , (II,OO) |-> II
              , (II,II) |-> II
              , (II,ZZ) |-> II
              , (II,WW) |-> II
              , (II,LL) |-> II
              , (II,HH) |-> II
              , (II,DD) |-> II

              , (ZZ,UU) |-> UU
              , (ZZ,XX) |-> XX
              , (ZZ,OO) |-> XX
              , (ZZ,II) |-> II
              , (ZZ,ZZ) |-> XX
              , (ZZ,WW) |-> XX
              , (ZZ,LL) |-> XX
              , (ZZ,HH) |-> II
              , (ZZ,DD) |-> XX

              , (WW,UU) |-> UU
              , (WW,XX) |-> XX
              , (WW,OO) |-> XX
              , (WW,II) |-> II
              , (WW,ZZ) |-> XX
              , (WW,WW) |-> XX
              , (WW,LL) |-> XX
              , (WW,HH) |-> II
              , (WW,DD) |-> XX

              , (LL,UU) |-> UU
              , (LL,XX) |-> XX
              , (LL,OO) |-> OO
              , (LL,II) |-> II
              , (LL,ZZ) |-> XX
              , (LL,WW) |-> XX
              , (LL,LL) |-> OO
              , (LL,HH) |-> II
              , (LL,DD) |-> XX

              , (HH,UU) |-> II
              , (HH,XX) |-> II
              , (HH,OO) |-> II
              , (HH,II) |-> II
              , (HH,ZZ) |-> II
              , (HH,WW) |-> II
              , (HH,LL) |-> II
              , (HH,HH) |-> II
              , (HH,DD) |-> II

              , (DD,UU) |-> UU
              , (DD,XX) |-> XX
              , (DD,OO) |-> XX
              , (DD,II) |-> II
              , (DD,ZZ) |-> XX
              , (DD,WW) |-> XX
              , (DD,LL) |-> XX
              , (DD,HH) |-> II
              , (DD,DD) |-> XX
             }

& XOR_STD   = { (UU,UU) |-> UU
              , (UU,XX) |-> UU
              , (UU,OO) |-> UU
              , (UU,II) |-> UU
              , (UU,ZZ) |-> UU
              , (UU,WW) |-> UU
              , (UU,LL) |-> UU
              , (UU,HH) |-> UU
              , (UU,DD) |-> UU

              , (XX,UU) |-> UU
              , (XX,XX) |-> XX
              , (XX,OO) |-> XX
              , (XX,II) |-> XX
              , (XX,ZZ) |-> XX
              , (XX,WW) |-> XX
              , (XX,LL) |-> XX
              , (XX,HH) |-> XX
              , (XX,DD) |-> XX

              , (OO,UU) |-> UU
              , (OO,XX) |-> XX
              , (OO,OO) |-> OO
              , (OO,II) |-> II
              , (OO,ZZ) |-> XX
              , (OO,WW) |-> XX
              , (OO,LL) |-> OO
              , (OO,HH) |-> II
              , (OO,DD) |-> XX

              , (II,UU) |-> UU
              , (II,XX) |-> XX
              , (II,OO) |-> II
              , (II,II) |-> OO
              , (II,ZZ) |-> XX
              , (II,WW) |-> XX
              , (II,LL) |-> II
              , (II,HH) |-> OO
              , (II,DD) |-> XX

              , (ZZ,UU) |-> UU
              , (ZZ,XX) |-> XX
              , (ZZ,OO) |-> XX
              , (ZZ,II) |-> II
              , (ZZ,ZZ) |-> XX
              , (ZZ,WW) |-> XX
              , (ZZ,LL) |-> XX
              , (ZZ,HH) |-> II
              , (ZZ,DD) |-> XX

              , (WW,UU) |-> UU
              , (WW,XX) |-> XX
              , (WW,OO) |-> XX
              , (WW,II) |-> II
              , (WW,ZZ) |-> XX
              , (WW,WW) |-> XX
              , (WW,LL) |-> XX
              , (WW,HH) |-> II
              , (WW,DD) |-> XX

              , (LL,UU) |-> UU
              , (LL,XX) |-> XX
              , (LL,OO) |-> OO
              , (LL,II) |-> II
              , (LL,ZZ) |-> XX
              , (LL,WW) |-> XX
              , (LL,LL) |-> OO
              , (LL,HH) |-> II
              , (LL,DD) |-> XX

              , (HH,UU) |-> II
              , (HH,XX) |-> II
              , (HH,OO) |-> II
              , (HH,II) |-> II
              , (HH,ZZ) |-> II
              , (HH,WW) |-> II
              , (HH,LL) |-> II
              , (HH,HH) |-> II
              , (HH,DD) |-> II

              , (DD,UU) |-> UU
              , (DD,XX) |-> XX
              , (DD,OO) |-> XX
              , (DD,II) |-> II
              , (DD,ZZ) |-> XX
              , (DD,WW) |-> XX
              , (DD,LL) |-> XX
              , (DD,HH) |-> II
              , (DD,DD) |-> XX
              }

&  NOT_STD  = {  UU     |-> XX
               , XX     |-> XX
               , OO     |-> II
               , II     |-> OO
               , ZZ     |-> XX
               , WW     |-> XX
               , LL     |-> II
               , HH     |-> OO
               , DD     |-> XX
               }

& CVT_TO_BIT ={  UU     |-> XMAP
               , XX     |-> XMAP
               , OO     |-> OO
               , II     |-> II
               , ZZ     |-> XMAP
               , WW     |-> XMAP
               , LL     |-> OO
               , HH     |-> II
               , DD     |-> XMAP
               }

& CVT_TO_XOI ={  UU     |-> XX
               , XX     |-> XX
               , OO     |-> OO
               , II     |-> II
               , ZZ     |-> XX
               , WW     |-> XX
               , LL     |-> OO
               , HH     |-> II
               , DD     |-> XX
               }

& CVT_TO_XOIZ={  UU     |-> XX
               , XX     |-> XX
               , OO     |-> OO
               , II     |-> II
               , ZZ     |-> ZZ
               , WW     |-> XX
               , LL     |-> OO
               , HH     |-> II
               , DD     |-> XX
               }

& CVT_TO_UXOI={  UU     |-> UU
               , XX     |-> XX
               , OO     |-> OO
               , II     |-> II
               , ZZ     |-> XX
               , WW     |-> XX
               , LL     |-> OO
               , HH     |-> II
               , DD     |-> XX
               }

& IS_X_VALUE= {  UU     |-> TRUE
               , XX     |-> TRUE
               , OO     |-> FALSE
               , II     |-> FALSE
               , ZZ     |-> TRUE
               , WW     |-> TRUE
               , LL     |-> FALSE
               , HH     |-> FALSE
               , DD     |-> TRUE
               }

/* Il y a aussi des fonctions construites a partir des autres */
&	NAND_STD	=  (AND_STD;NOT_STD)
&	NOR_STD		=  (OR_STD;NOT_STD)
&	XNOR_STD	=  (XOR_STD;NOT_STD)


OPERATIONS

/*
    FUNCTION "and"  ( l : std_ulogic; r : std_ulogic ) RETURN UX01;
    FUNCTION "nand" ( l : std_ulogic; r : std_ulogic ) RETURN UX01;
    FUNCTION "or"   ( l : std_ulogic; r : std_ulogic ) RETURN UX01;
    FUNCTION "nor"  ( l : std_ulogic; r : std_ulogic ) RETURN UX01;
    FUNCTION "xor"  ( l : std_ulogic; r : std_ulogic ) RETURN UX01;
--  function "xnor" ( l : std_ulogic; r : std_ulogic ) return ux01;
    function xnor   ( l : std_ulogic; r : std_ulogic ) return ux01;
    FUNCTION "not"  ( l : std_ulogic                 ) RETURN UX01;
*/

out <-- Resolved(in1,in2)=
PRE
      	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
	out:=RESOLVED(in1,in2)
END;

out  <-- And(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
   	out := AND_STD(in1,in2)
END;

out  <-- Nand(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
   	out :=  NAND_STD(in1,in2)
END;

out <-- Or(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
 	out := OR_STD(in1,in2)
END;

out <-- Nor(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
 	out := NOR_STD(in1,in2)
END;

out <-- Xor(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
 	out := XOR_STD(in1,in2)
END;

out <-- Xnor(in1,in2)=
PRE
       	(in1,in2)      :  STD_ULOGIC * STD_ULOGIC
THEN
 	out :=  XNOR_STD(in1,in2)
END;

out <-- Not(in1)=
PRE
       	in1      :  STD_ULOGIC
THEN
 	out := NOT_STD(in1)
END;


/* OPERATION de CONVERSION */
/* ======================= */

out <-- From_STD_ULOGIC_To_BIT(in1)=
PRE
       	in1:STD_ULOGIC
THEN
   	out := CVT_TO_BIT(in1)
END;

/*
    FUNCTION To_StdULogic       ( b : BIT               ) RETURN std_ulogic;
*/

out <-- From_BIT_To_StdULOGIC(in1)=
PRE
       	in1:BIT
THEN
	/* Pour forcer le typage,   il faut passer par un ANY afin de 
	   definir explicitement le type de la variable de sortie out 
	   sinon son type sera BIT et non pas STD_ULOGIC
	*/
	ANY 
		temp 
	WHERE
		temp : STD_ULOGIC
	&	temp = in1
	THEN
	   	out := temp
	END
END;

/*
    FUNCTION To_X01  ( b : BIT               ) RETURN  X01;
*/

out <-- From_BIT_To_XOI(in1)=
PRE
       	in1:BIT
THEN
   	out := CVT_TO_XOI(in1)
END;

/*
    FUNCTION To_X01Z ( b : BIT               ) RETURN  X01Z; 
*/

out <-- From_BIT_To_XOIZ(in1)=
PRE
       	in1:BIT
THEN
   	out := CVT_TO_XOIZ(in1)
END;

/*
    FUNCTION To_UX01  ( b : BIT               ) RETURN  UX01;
*/

out <-- From_BIT_To_UXOI(in1)=
PRE
       	in1:BIT
THEN
   	out := CVT_TO_UXOI(in1)
END;

/*
    FUNCTION To_X01  ( s : std_ulogic        ) RETURN  X01;
*/

out <-- From_STD_ULOGIC_To_XOI(in1)=
PRE
       	in1:STD_ULOGIC
THEN
   	out := CVT_TO_XOI(in1)
END;

/*
    FUNCTION To_X01Z ( s : std_ulogic        ) RETURN  X01Z;
*/

out <-- From_STD_ULOGIC_To_XOIZ(in1)=
PRE
       	in1:STD_ULOGIC
THEN
   	out := CVT_TO_XOIZ(in1)
END;

/*
  FUNCTION To_UX01  ( s : std_ulogic        ) RETURN  UX01;
*/

out <-- From_STD_ULOGIC_To_UXOI(in1)=
PRE
       	in1:STD_ULOGIC
THEN
   	out := CVT_TO_UXOI(in1)
END;


/*? Passage d'un STD_ULOGIC au BOOLEEN ?*/
/*  ==================================  */

/*
    FUNCTION Is_X ( s : std_ulogic        ) RETURN  BOOLEAN;
*/

out <-- IS_X(in1)=
PRE
       in1:STD_ULOGIC
THEN
   out := IS_X_VALUE(in1)
END;



/*? 	Operation d'impression:
	Il est important de prevoire une operation d'impression 
	pour les tests du module
?*/

Print (bb) =
PRE
	bb : STD_ULOGIC
THEN
	skip
END




END	/*? of file B_STD_LOGIC_1164_0.mch ?*/
