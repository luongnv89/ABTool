//
// AUTHOR      : BOULANGER Jean-Louis
// DESCRIPTION : Ce fichier permet de tester le typage explicite
//               Il fait partie integrante de la distribution ABTOOLS
//               Il permet de tester l'extension de la methode B intitule BPRIME
// FILE        : typ_mch_param.mch
//


MACHINE 
 	typ_mch_param (AA:un_type)
 
 OPERATIONS  
 
 
 xx, aa <-- OP ( p1, p2 ) =
 xx := p1;
 
 xx, aa <-- OP1 ( p1, p2 ) =
 aa := p2

 END
 
