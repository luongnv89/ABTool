
/*?
	Author	:	BOULANGER Jean-Louis
	Date	:	JUILLET 96
	Descr	:	Quelques operations mathematiques connues
	File	: 	Math_0.mch

?*/

/*
	September 1999	Add the Sign Operation.
*/

MACHINE
	Math_0

OPERATIONS

xx <-- Max (aa,bb) =
	PRE
		aa : NAT
	&	bb : NAT
	THEN
		xx := max({aa,bb})
	END;

xx <-- Min (aa,bb) =
	PRE
		aa : NAT
	&	bb : NAT
	THEN
		xx := min({aa,bb})
	END;

xx <-- Abs (zz) =
	PRE 
		zz : INT
	THEN
		xx :: NAT
	END;

qq,rr <-- Euclidean_div(aa,bb) =
	PRE
		aa : NAT 
	& 	bb : NAT1
	THEN
	qq,rr :	(	qq : NAT & rr : NAT 
		&	0 <= rr
		&	rr <= aa
		&	0 <= qq
		&	qq <= aa
		&	rr < bb 
		&	aa = qq * bb + rr
		)
	END;

xx,yy <-- Swap (aa,bb) =
	PRE
		aa : NAT
	&	bb : NAT
	THEN	
		xx,yy := bb,aa
	END;

ss <-- Sign (ii) =
PRE
	ii : INT
THEN
	SELECT 
		(ii < 0) 
	THEN
		ss := -1
	WHEN
		(ii > 0)
	THEN
		ss := 1
	ELSE
		ss := 0
	END
END

END /*? End of file Math_0.mch ?*/



