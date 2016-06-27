/*
 File name : Mnot;mch
 Description : Exemple de machine, encapsulant l'opérateur not
 Author : JL Boulanger
*/
MACHINE
	Mnot
	
OPERATIONS
	bb <-- mon_not ( yy )  = 
	PRE
		yy : BOOL
	THEN
		bb := not (yy) 
	END
		
	
	
END
