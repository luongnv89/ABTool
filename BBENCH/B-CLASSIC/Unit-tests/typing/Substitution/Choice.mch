/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	Choice

VARIABLES
	xx, yy , zz, aa

INVARIANT
	xx, yy, zz, aa : NAT*NAT*NAT*NAT

INITIALISATION
	xx, yy, zz, aa := 1,2,3,4

OPERATIONS

op=
	CHOICE  	yy := 1
		  OR   	xx := 2
		  OR	zz := 3
		  OR	aa := 4
	END

END
