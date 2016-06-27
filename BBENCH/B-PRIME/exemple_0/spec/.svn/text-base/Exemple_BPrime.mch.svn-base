//
// AUTHOR      : BOULANGER Jean-Louis
// DESCRIPTION : Ce fichier permet de donner un exemple d'utilisation de BPRIME
//               Il fait partie integrante de la distribution ABTOOLS
//               Il permet de tester l'extension de la methode B intitule BPRIME
// FILE        : Exemple_BPrime.mch
//

// Copyright boulanger jean-louis 2001-2003

/* Exemple de machine decrite sous BPRIME */

MACHINE        Exemple_BPrime
 
VARIABLES      aa : NAT , bb : NAT  // Typage explicite des variables
 
INVARIANT      aa < bb              // L'invariant ne contient que les proprietes 

OPERATIONS
               r1 : NAT, r2 : NAT <-- EX ( p1 : NAT, p2 : NAT) =
               PRE
                   p1 < p2
               THEN
                   ANY
                      a1 : NAT, a2 : NAT
                   WHERE
                      a1 < a2
                   &  a1 < p1
                   &  a2 < p2
                   THEN
                      r1, r2 := a1, a2
                   END
               END
END
//
// Date   : Septembre 2001
//
