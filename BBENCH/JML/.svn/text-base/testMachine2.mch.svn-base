/* testMachine
 * Author: 
 * Creation date: 
 */
MACHINE
    testMachine2
    
VARIABLES
    vv,ww
    
INVARIANT
    vv : BOOL &
    ww : NAT
    
INITIALISATION
    vv :: BOOL ||
    ww := 34
    
OPERATIONS
    test_1 =
    BEGIN
        vv := bool(ww > 10)
    END;
    
    test_2 =
    BEGIN
        vv :: BOOL
    END;
    
    test_3 =
    BEGIN
        ANY zz WHERE
            zz : NAT
        THEN
            vv := bool(zz< 10)
        END
    END    
END
