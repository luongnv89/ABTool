/*
	FILE		:	Club_0.mch
	AUTHOR		:	Boulanger Jean-Louis
	DESCRIPTION	:	An example from Bforum.
				BBENCH is part of ABTOOLS project

 	Copyright 2009-2010 Boulanger Jean-Louis

*/

/*
RELEASES
	v 0.1 	march 2009
		- Creation from B Forum
*/

MACHINE
	Club_0(capacity)

CONSTRAINTS
	capacity : NATURAL1 
& 	capacity <= 4096

SETS
	report = {yes, no}
; 	NAME

END	/* of file Club_0.mch */