/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/


MACHINE
	any_0

VARIABLES
	aa, bb

INVARIANT
	aa <: NAT
&	bb <: NAT

INITIALISATION
	aa := {1,2,3}
||	bb := {1,2,3}

OPERATIONS

yy <-- OP =
	ANY
		xx
	WHERE
		xx : aa- bb
	THEN
		yy := xx
	END

END


