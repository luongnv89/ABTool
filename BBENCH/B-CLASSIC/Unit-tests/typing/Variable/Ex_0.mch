/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
	Ex_0

VARIABLES
	ff, xx, yy, zz

INVARIANT
	xx, yy , zz : NAT*NAT*NAT
&	ff : NAT*NAT*NAT --> NAT
&	ff(xx,yy,zz)= xx+yy+zz

INITIALISATION
ff, xx, yy, zz 
	:(
		xx, yy , zz : NAT*NAT*NAT
	&	ff : NAT*NAT*NAT --> NAT
	&	ff(xx,yy,zz)= xx+yy+zz
	)

END
