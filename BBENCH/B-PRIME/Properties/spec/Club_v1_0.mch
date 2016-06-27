/*
	FILE		:	Club_v1_0.mch
	AUTHOR		:	Boulanger Jean-Louis
	DESCRIPTION	:	An example from Bforum.
				BBENCH is part of ABTOOLS project

 	Copyright 2009-2010 Boulanger Jean-Louis

 It's B-PRIME because we have a link between amachine formal parameter and a SETS in PROPERTIES. it's not authoraized by B-Classic.
The Atelier B does not accept this kind of machine
*/

/*
RELEASES
	v 0.1 	march 2009
		- Creation from B Forum
*/

MACHINE
	Club_v1_0(capacity)

CONSTRAINTS
	capacity : NATURAL1 
& 	capacity <= 4096

SETS
	report = {yes, no}
; 	NAME

CONSTANTS
	total

PROPERTIES
	card(NAME) > capacity 
&	total: NATURAL1 
&	total > 4096

VARIABLES
	member
, 	waiting

INVARIANT
	member <: NAME 
&	waiting <: NAME 
&	member /\ waiting = {} 
&	card(member) <= 4096 
&	card(waiting) <= total

INITIALISATION
	member, waiting :(
		member <: NAME 
	&	waiting <: NAME 
	&	member /\ waiting = {} 
	&	card(member) <= 4096 
	&	card(waiting) <= total
	)

END	/* of file Club_0.mch */
