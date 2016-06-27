/*
        Les bases de BHDL.
         - la bibliotheque STD_Logic_1164 modelisee en B (3 composants)
         - une machine d'entree (conversion)
         - une machine modelisant les proprietes de la logique.
           Preuve difficile pour l'AB.

*/

MACHINE
	ENTRIE_0

SEES
	MAIN_0

ABSTRACT_CONSTANTS
        bin_to_dec
,       bool_to_bit
,       bit_to_dec
,       bit_to_bool

PROPERTIES
        bool_to_bit     : BOOL  --> BIT
&       bool_to_bit     = {     (TRUE |-> II),
                                (FALSE|-> OO)
                           }

&       bin_to_dec      : BOOL  -->     0..1
&       bin_to_dec      = {     (TRUE |-> 1),
                                (FALSE|-> 0)
                           }

&       bit_to_dec      : BIT   -->     0..1
&       bit_to_dec      = {     (OO |-> 0),
                                (II   |-> 1)
                           }
&       bit_to_bool     : BIT   -->     BOOL
&       bit_to_bool     = {     (OO |-> FALSE),
                                (II   |-> TRUE)
                           }

END
