REFINEMENT VARTEST
  VARIABLES x, y, z
  INVARIANT x : NAT & y : NAT & z : INT
  
  OPERATIONS
  
   test1 =
      VAR x, b IN
       x := 1 || y := 2 || b := TRUE
     END;

  
   test2 =
    SELECT x < 3 THEN
       x := 3
    WHEN x > 5 THEN
     VAR x, b, z IN
       x := 1 || y := 2 || b := TRUE || z := 3
     END 
    ELSE
      z := 7
    END
  END
