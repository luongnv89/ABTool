/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	Choice1

VARIABLES
	xx, yy , zz, aa

INVARIANT
	xx, yy, zz, aa : BOOL*BOOL*NAT*BOOL

INITIALISATION
	xx, yy, zz, aa := TRUE,TRUE,3,TRUE

OPERATIONS

op(bb)=
PRE
	bb : BOOL
THEN
	CHOICE  	yy := aa
		  OR   	xx := bb
		  OR	zz := 3
		  OR	aa :=  bool (bb = TRUE)
	END
END

END
