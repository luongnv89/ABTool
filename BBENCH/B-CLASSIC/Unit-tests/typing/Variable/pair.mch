/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	pair

VARIABLES
	xx, yy, ff

INVARIANT
	(xx,yy) 	: INT*INT
&	ff 		: INT -->INT
&	(ff(xx),yy) 	: INT*INT

OPERATIONS

OP =
ANY
	xx,yy,ff
WHERE
	(xx,yy) 	: INT*INT
&	ff 		: INT -->INT
&	(ff(xx),yy) 	: INT*INT
THEN
	skip
END
END
