/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	If_0

VARIABLES
	AA

INVARIANT
	AA : NAT

INITIALISATION
	AA :( AA: NAT)

OPERATIONS

OP(BB) =
PRE
	AA, BB : NAT*NAT
THEN
	IF (AA=BB)
	THEN AA:=1
	ELSIF (AA/= BB)
	THEN AA:= 2
	ELSIF (AA/= AA)	
	THEN AA:= 3
	ELSE AA:= 4
	END
END

END
