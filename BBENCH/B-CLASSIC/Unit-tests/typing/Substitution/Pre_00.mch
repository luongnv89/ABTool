/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	Pre_00

OPERATIONS

xx,yy,zz <-- OP (aa,bb) =
	PRE
		aa: INT
	&	bb : INT
	THEN
		xx,yy,zz := aa, bb, +bb
	END
END
