/*
        Les bases de BHDL.
         - la bibliotheque STD_Logic_1164 modelisee en B (3 composants)
         - une machine d'entree (conversion)
         - une machine modelisant les proprietes de la logique.
           Preuve difficile pour l'AB.

*/

MACHINE
	SIGNAL_0

SETS
	SIGNAL

SEES
	MAIN_0


ABSTRACT_CONSTANTS
	SIGNAL_ULOGIC
,	SIGNAL_LOGIC
,	SIGNAL_nil
,	F_SIGNAL

PROPERTIES
	SIGNAL_ULOGIC = struct (
					value 	: STD_ULOGIC
 				,	next	: SIGNAL
				)
&	card(SIGNAL_ULOGIC) <1000

&	SIGNAL_LOGIC = struct (
					value 	: STD_ULOGIC		/* valeur d'un temps t */
 				,	next	: SIGNAL			/* pointeur sur les valeurs t-1 ... t-n */
				)
&	card(SIGNAL_LOGIC) <1000

&	SIGNAL_nil	: SIGNAL

&	F_SIGNAL	: SIGNAL --> SIGNAL_ULOGIC						/* Fonction de dereference */
&	F_SIGNAL(SIGNAL_nil)= rec(value :DD, next:SIGNAL_nil)		/* Valeur particuliere pour NIL */

VARIABLES
	signal

INVARIANT
	signal <: SIGNAL
&	SIGNAL_nil : signal

INITIALISATION
	signal := {SIGNAL_nil}

OPERATIONS

out <-- Create_Signal =
PRE
	SIGNAL - signal /= {}
THEN
	ANY
		tmp
	WHERE
		tmp : SIGNAL
	&	tmp /: signal
	THEN
		out := tmp
	||	signal := signal \/ { tmp}
	END
END;
	
/* construire les operations de manipulation d'un signal */


Add_Signal(in)=
PRE
	in : SIGNAL
THEN
	skip
END;


/*
    -- edge detection
	FUNCTION rising_edge  (SIGNAL s : std_ulogic) RETURN BOOLEAN;
    	FUNCTION falling_edge (SIGNAL s : std_ulogic) RETURN BOOLEAN;
*/

out <-- rising_edge (in) =
PRE
	in : SIGNAL_ULOGIC
THEN
	out :(
			out : BOOL
		& 	out = bool(		(in'value = II) & ( (F_SIGNAL(in'next))'value = OO) 
			)
	)
END;

out <-- falling_edge (in) =
PRE
	in : SIGNAL_ULOGIC
THEN
	out :(
			out : BOOL
		& 	out = bool(		(in'value = OO) 
			    		& ( (F_SIGNAL(in'next))'value = II) 
					)
		)
END;




/*? Operation d'impression:
	Il est important de prevoire une operation d'impression pour les tests du module
?*/

Print_Signal (bb) =
PRE
	bb : SIGNAL_ULOGIC
THEN
	skip
END

END /* of file Signal_0.mch */
