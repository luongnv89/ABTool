/* Jean-Louis Boulanger */
/* Incompatibilité de type entre BOOL et BOOLE */

MACHINE
	T_V3
	
VARIABLES
	V1 
	
INVARIANT
	V1 : BOOL
	
INITIALISATION
	V1 :( V1 : BOOLE ) 
END
