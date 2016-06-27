/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	T_V_Fonct_2
	
CONSTANTS
	nn /* : NATURAL */
	
PROPERTIES
	nn : NATURAL
	
VARIABLES
	ff /* : PROD(RANGE(NAT,NATURAL),NATURAL) */
	
INVARIANT
	ff :  1  .. nn --> NATURAL
	
INITIALISATION
	ff ::  (  1  .. nn --> NATURAL ) 
	
END
 /* Begin AST Tree 
 ( MACHINE T_V_Fonct_2 ( CONSTANTS n ) ( PROPERTIES ( : n NATURAL ) ) ( VARIABLES f ) ( INVARIANT ( : f ( --> ( .. 1 n ) NATURAL ) ) ) ( INITIALISATION ( :: f ( ( ( --> ( .. 1 n ) NATURAL ) ) ) ) )
    End AST Tree */