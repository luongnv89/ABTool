MACHINE BOUND
  SETS X
  VARIABLES y
  INVARIANT y : X 
  
  OPERATIONS
  test = PRE !x. ((x : X) => (x = y)) THEN skip END

END