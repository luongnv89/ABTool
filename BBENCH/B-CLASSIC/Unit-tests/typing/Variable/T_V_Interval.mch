/* 
   Description : Machine de test du typage
   Author : Boulanger jean-louis
*/

MACHINE
         T_V_Interval
	
VARIABLES
	ff
	
INVARIANT
	ff :  1  .. 10
	
INITIALISATION
	ff :: (  1  .. 10) 
	
END
