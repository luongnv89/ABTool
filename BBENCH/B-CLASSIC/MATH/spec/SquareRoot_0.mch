/*

Auhor   :       BOULANGER Jean-Louis    
Date    :       AVRIL 1999
Descr   :       
File    :       SquareRoot_0.mch

*/

MACHINE
        SquareRoot_0

DEFINITIONS
	square (x)	==	x*x

OPERATIONS

sqrt <-- SquareRoot (xx) =
PRE
	xx : NAT
THEN
	ANY
		yy
	WHERE
		yy : NAT
	&	square(yy) <= xx
	&	yy <= square(xx+1)
	THEN
		sqrt := yy
	END
END



END /* of file SquareRoot_0 */