/*
        Les bases de BHDL.
         - la bibliotheque STD_Logic_1164 modelisee en B (3 composants)
         - une machine d'entree (conversion)
         - une machine modelisant les proprietes de la logique.
           Preuve difficile pour l'AB.

*/

MACHINE
        VECTOR_0

DEFINITIONS
        APPLY(op,in1,in2,out,out_type)  ==
           IF   in1'vector_size = in2'vector_size
           THEN
                ANY
                        vv
                WHERE
                        vv : STD_LOGIC_VECTOR
                &      !xx.( (xx : 1 .. max({in1'vector_size,in2'vector_size}) )
                             => ( (vv'vector)(xx) = op( (in1'vector)(xx), (in2'vector)(xx) ) 
                                & ( (vv'vector)(xx) ) : out_type )
                            )
                THEN
                        out := vv
                END
           ELSE
/* Ajout BJL .. Importan de typer dans tous les cas ... */
                out :( out : STD_LOGIC_VECTOR)
           END

;       APPLY_VECTOR(op,in,out,out_type)        == 
                ANY
                        vv
                WHERE
                        vv : STD_LOGIC_VECTOR
                &       !xx.( (xx : 1 .. max({in'vector_size}) )
                              => ( (vv'vector)(xx) = op( (in'vector)(xx) ) 
                            & ( (vv'vector)(xx) ) : out_type )
                        )
                THEN
                         out := vv
                END
	
;       MAX_VECTOR         ==      1000

SEES
        MAIN_0

CONSTANTS
        STD_LOGIC_VECTOR
,       STD_ULOGIC_VECTOR
,       BIT_VECTOR
,       Max_Element

PROPERTIES
        Max_Element             :       NAT1

/*?
        On construit l'ensemble de tous les vecteurs de STD_LOGIC
?*/

&       STD_LOGIC_VECTOR        =
        struct (
                vector          :       1.. Max_Element --> STD_LOGIC,
                vector_size     :       NAT1
                )
&       card(STD_LOGIC_VECTOR)  <       MAX_VECTOR
&       !xx.((xx:STD_LOGIC_VECTOR) => xx'vector_size < Max_Element)

/*      Idem pour STD_ULOGIC */
&       STD_ULOGIC_VECTOR       =
        struct (
                vector          :       1.. Max_Element --> STD_ULOGIC,
                vector_size     :       NAT1
                )
&       card(STD_ULOGIC_VECTOR) <       MAX_VECTOR
&       !xx.((xx:STD_ULOGIC_VECTOR) => xx'vector_size < Max_Element)

/*      Idem pour BIT */
&       BIT_VECTOR      =
        struct (
                vector          :       1.. Max_Element --> BIT,
                vector_size     :       NAT1
                )
&       card(BIT_VECTOR)        <       MAX_VECTOR
&       !xx.((xx : BIT_VECTOR) => xx'vector_size < Max_Element)

OPERATIONS

out <-- Resolved_Vector(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR 
&		in2	: STD_ULOGIC_VECTOR
THEN
        APPLY(RESOLVED,in1,in2,out,STD_ULOGIC)
END;

out  <-- And_Ulogic(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR
&       in2 : STD_ULOGIC_VECTOR
THEN
        APPLY(AND_STD,in1,in2,out,STD_ULOGIC)
END;

out  <-- And_Logic(in1,in2)=
PRE
        in1 : STD_LOGIC_VECTOR
&       in2 : STD_LOGIC_VECTOR
THEN
        APPLY(AND_STD,in1,in2,out,STD_LOGIC)
END;

out  <-- Nand_Ulogic(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR
&       in2 : STD_ULOGIC_VECTOR
THEN
        APPLY(NAND_STD,in1,in2,out,STD_ULOGIC)
END;

out  <-- Nand_Logic(in1,in2)=
PRE
        in1 : STD_LOGIC_VECTOR
&       in2 : STD_LOGIC_VECTOR
THEN
        APPLY(NAND_STD,in1,in2,out,STD_LOGIC)
END;

out  <-- Or_Ulogic(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR
&       in2 : STD_ULOGIC_VECTOR
THEN
        APPLY(OR_STD,in1,in2,out,STD_ULOGIC)
END;

out  <-- Or_Logic(in1,in2)=
PRE
        in1 : STD_LOGIC_VECTOR
&       in2 : STD_LOGIC_VECTOR
THEN
        APPLY(OR_STD,in1,in2,out,STD_LOGIC)
END;

out  <-- Xor_Ulogic(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR
&       in2 : STD_ULOGIC_VECTOR
THEN
        APPLY(XOR_STD,in1,in2,out,STD_ULOGIC)
END;

out  <-- Xor_Logic(in1,in2)=
PRE
        in1 : STD_LOGIC_VECTOR
&       in2 : STD_LOGIC_VECTOR
THEN
        APPLY(XOR_STD,in1,in2,out,STD_LOGIC)
END;

out  <-- Xnor_Ulogic(in1,in2)=
PRE
        in1 : STD_ULOGIC_VECTOR
&       in2 : STD_ULOGIC_VECTOR
THEN
        APPLY(XNOR_STD,in1,in2,out,STD_ULOGIC)
END;

out  <-- Xnor_Logic(in1,in2)=
PRE
        in1 : STD_LOGIC_VECTOR
&       in2 : STD_LOGIC_VECTOR
THEN
        APPLY(XNOR_STD,in1,in2,out,STD_LOGIC)
END;


out  <-- Not_Ulogic(in)=
PRE
        in : STD_ULOGIC_VECTOR
THEN
        APPLY_VECTOR(NOT_STD,in,out,STD_ULOGIC)
END;

out  <-- Not_logic(in)=
PRE
        in : STD_LOGIC_VECTOR
THEN
        APPLY_VECTOR(NOT_STD,in,out,STD_LOGIC)
END;


out  <-- To_Bit_Vector_From_Ulogic(in)=
PRE
        in : STD_ULOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_BIT,in,out,BIT)
END;


out  <-- To_Bit_Vector_From_Logic(in)=
PRE
        in : STD_LOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_BIT,in,out,BIT)
END;


out  <-- To_StdUlogicVector_From_Bit(in)=
PRE
        in : BIT_VECTOR
THEN

      ANY
                vv
      WHERE
                vv : STD_ULOGIC_VECTOR
        &       !xx.( ( xx : 1 ..in'vector_size )
                     => ( (vv'vector)(xx) = ( (in'vector)(xx) ) ) 
                     )
                THEN
                         out := vv
                END

END;

out  <-- To_StdLogicVector_From_Bit(in)=
PRE
        in : BIT_VECTOR
THEN

      ANY
                vv
      WHERE
                vv : STD_LOGIC_VECTOR
        &       !xx.( ( xx : 1 ..in'vector_size )
                      => ( (vv'vector)(xx) = ( (in'vector)(xx) ) )
                     )
                THEN
                         out := vv
                END

END;

out  <-- To_XOI_Vector_From_Ulogic(in)=
PRE
        in : STD_ULOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_XOI,in,out,XOI)
END;

out  <-- To_XOI_Vector_From_Logic(in)=
PRE
        in : STD_LOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_XOI,in,out,XOI)
END;

/*
out  <-- To_XOI_Ulogic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
         ANY
                vv
      WHERE
                vv : STD_ULOGIC_VECTOR
        &       !xx.( ( xx : 1 ..in'vector_size )
                     => ( (vv'vector)(xx) = ( (in'vector)(xx) ) )
                     )

              THEN
                         out := vv
                END

END;
*/

out  <-- To_UXOI_Logic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
      ANY
                vv
      WHERE
                vv : STD_LOGIC_VECTOR
        &       !xx.( (xx : 1 ..in'vector_size )
                     =>( (vv'vector)(xx) = ( (in'vector)(xx) ) ) 
                     )
      THEN
         out := vv
      END
END;

out  <-- To_UXOI_Ulogic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
         ANY
                vv
      WHERE
                vv : STD_ULOGIC_VECTOR
        &       !xx.( (xx : 1 ..in'vector_size )
                     => ( (vv'vector)(xx) = ( (in'vector)(xx) ) )
                     ) 
                THEN
                         out := vv
                END

END;


out  <-- To_XOI_Logic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
         ANY
                vv
         WHERE
                vv : STD_LOGIC_VECTOR
        &       !xx.(( xx : 1 ..in'vector_size )
                => ( (vv'vector)(xx) = ( (in'vector)(xx) )
                      )  )

           THEN
                         out := vv
                END

END;

out  <-- To_XOIZ_Vector_From_Ulogic(in)=
PRE
        in : STD_ULOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_XOIZ,in,out,XOIZ)
END;


out  <-- To_UXOI_Vector_From_Logic(in)=
PRE
        in : STD_LOGIC_VECTOR
THEN
        APPLY_VECTOR(CVT_TO_XOIZ,in,out,UXOI)
END;

/*
out  <-- To_UXOI_Ulogic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
         ANY
                vv
      WHERE
                vv : STD_ULOGIC_VECTOR
        &       !xx.( ( xx : 1 ..in'vector_size )
                => ( (vv'vector)(xx) = ( (in'vector)(xx) )
                        ) )
                THEN
                         out := vv
                END

END;


out  <-- To_UXOI_Logic_Vector_From_BIT(in)=
PRE
        in : BIT_VECTOR
THEN
         ANY
                vv
      WHERE
                vv : STD_LOGIC_VECTOR
        &       !xx.( ( xx : 1 ..in'vector_size )
                => ( (vv'vector)(xx) = ( (in'vector)(xx) )
                        ) )
                THEN
                         out := vv
                END

END;


out  <-- Is_X_VECTOR_Ulogic(in)=
PRE
        in : STD_ULOGIC_VECTOR
THEN
   ANY
                        vv
                WHERE
                        vv : BOOL
                &       vv = bool( !xx.( (xx : 1 .. max({in'vector_size}) )
                        => ( IS_X_VALUE( (in'vector)(xx) ) = TRUE ) )
                        )
                THEN
                         out := vv
                END
END;


out  <-- Is_X_VECTOR_Ulogic(in) =
PRE
        in : STD_LOGIC_VECTOR
THEN
                ANY
                        vv
                WHERE
                        vv : BOOL
                &       vv = bool( !xx.( (xx : 1 .. max({in'vector_size}) )
                        => ( IS_X_VALUE( (in'vector)(xx) ) = TRUE ) )
                        )
                THEN
                         out := vv
                END


END;

*/


/*? Operation d'impression:
Il est important de prevoir une operation d'impression pour les 
tests du module
?*/

Print_Vector (bb) =
PRE
        bb : STD_LOGIC_VECTOR
THEN
        skip
END

END     /*? of file VECTOR_0.mch ?*/
