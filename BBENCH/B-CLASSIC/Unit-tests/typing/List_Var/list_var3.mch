/*	AUTHOR		: 	Boulanger Jean-Louis
	FILE		:	list_var3.mch
	
*/

MACHINE
	list_var3

VARIABLES
	xx, yy, zz

INVARIANT
	xx,yy,zz : INT*POW(INT)*INT

INITIALISATION
	xx,yy :( xx,yy,zz : INT*POW(INT)*INT )

END
