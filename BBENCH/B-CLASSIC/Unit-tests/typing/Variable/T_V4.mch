/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	T_V4
	
VARIABLES
	V1 /* : NATURAL */
	 , V2 /* : NAT */
	 , V3 /* : INT */
	 , V4 /* : INTEGER */
	
INVARIANT
	V1 : NATURAL
	 & V2 : NAT
	 & V3 : INT
	 & V4 : INTEGER
	
INITIALISATION
	V1 , V2 , V3 , V4 :=  1  ,  1  ,  1  ,  1 
	
END
 /* Begin AST Tree 
 ( MACHINE T_V4 ( VARIABLES ( , ( , ( , V1 V2 ) V3 ) V4 ) ) ( INVARIANT ( & ( & ( & ( : V1 NATURAL ) ( : V2 NAT ) ) ( : V3 INT ) ) ( : V4 INTEGER ) ) ) ( INITIALISATION ( := ( , ( , ( , V1 V2 ) V3 ) V4 ) ( , ( , ( , 1 1 ) 1 ) 1 ) ) ) )
    End AST Tree */