/* 
   Description : Machine de test des appel de function
   Author : Boulanger jean-louis
   Copyright 2000-2010 Jean-Louis Boulanger
*/

MACHINE
	Set

VARIABLES
	a, b, c, d, e, f

INVARIANT
	a : {1, 2}
&	b : a /\ NAT
&	c <: a
&	d <<: a
&	e /<: a
&	f : a \/ b

INITIALISATION
a, b, c, d, e, f 
:(
       a : {1, 2}
&       b : a /\ NAT
&       c <: a
&       d <<: a
&       e /<: a
&       f : a \/ b
)

END
