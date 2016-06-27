/*
        Les bases de BHDL.
         - la bibliotheque STD_Logic_1164 modelisee en B (3 composants)
         - une machine d'entree (conversion)
         - une machine modelisant les proprietes de la logique.
           Preuve difficile pour l'AB.

*/

MACHINE
	PROPERTIES_0

SEES 
	MAIN_0

ASSERTIONS
          /* Loi d'identite */
           (!xx.(xx:STD_ULOGIC =>  AND_STD(xx,II )=xx)) &
           (!xx.(xx:STD_ULOGIC =>  OR_STD (xx,OO)=xx)) &

          /* Loi de nullite */
           (!xx.(xx:BIT 	=>  AND_STD(xx,OO)=OO)) &
           (!xx.(xx:BIT 	=>  OR_STD (xx,II )=II )) &
           (!xx.(xx:STD_ULOGIC 	=>  AND_STD(xx,UU)=UU)) &
           (!xx.(xx:STD_ULOGIC 	=>  OR_STD (xx,UU)=UU)) &

          /* Loi d'idempotence */
           (!xx.(xx:STD_ULOGIC =>  AND_STD(xx,xx)=xx)) &
           (!xx.(xx:STD_ULOGIC =>  OR_STD (xx,xx)=xx)) &

          /* Lois d'inversion */
           (!xx.(xx:BIT 	=>  AND_STD(xx,NOT_STD(xx))=OO)) &
           (!xx.(xx:BIT 	=>  OR_STD(xx,NOT_STD(xx))=II)) &

          /* Loi commutative */
           (!(xx,yy).( (xx:STD_ULOGIC  & yy:STD_ULOGIC) =>
                       AND_STD(xx,yy) = AND_STD(yy,xx) )) &
           (!(xx,yy).( (xx:STD_ULOGIC  & yy:STD_ULOGIC) =>
                       OR_STD(xx,yy) = OR_STD(yy,xx) )) &
          /* Loi associative */
           (!(xx,yy,zz).( (xx:STD_ULOGIC  & yy:STD_ULOGIC & zz:STD_ULOGIC) =>
                          AND_STD(AND_STD(xx,yy),zz) = AND_STD(xx,AND_STD(yy,zz)) )) &
           (!(xx,yy,zz).( (xx:STD_ULOGIC  & yy:STD_ULOGIC & zz:STD_ULOGIC) =>
                          OR_STD(OR_STD(xx,yy),zz) = OR_STD(xx,OR_STD(yy,zz)) )) &
          /* Loi distributive */
           (!(xx,yy,zz).( (xx:STD_ULOGIC  & yy:STD_ULOGIC & zz:STD_ULOGIC) =>
                          AND_STD(xx,OR_STD(yy,zz)) = AND_STD(OR_STD(xx,yy),OR_STD(xx,zz))  )) &
           (!(xx,yy,zz).( (xx:STD_ULOGIC  & yy:STD_ULOGIC & zz:STD_ULOGIC) =>
                          OR_STD(xx,AND_STD(yy,zz)) = OR_STD(AND_STD(xx,yy),AND_STD(xx,zz)) )) &
         /* Loi d'absorption */
           (!(xx,yy).( (xx:BIT  & yy:BIT) =>
                       AND_STD(xx,OR_STD(xx,yy)) = xx )) &
           (!(xx,yy).( (xx:BIT  & yy:BIT) =>
                       OR_STD(xx,AND_STD(xx,yy)) = xx )) &
         /* Loi de Morgan */
           (!(xx,yy).( (xx:STD_ULOGIC  & yy:STD_ULOGIC) =>
                       NOT_STD(AND_STD(xx,yy)) = OR_STD(NOT_STD(xx),NOT_STD(yy)) )) &
           (!(xx,yy).( (xx:STD_ULOGIC  & yy:STD_ULOGIC) =>
                       NOT_STD(OR_STD(xx,yy)) = AND_STD(NOT_STD(xx),NOT_STD(yy)) ))


END	/*? of file PROPERTIES_0.mch ?*/
