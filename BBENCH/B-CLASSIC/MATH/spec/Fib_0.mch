/*?
        Author  :       BOULANGER Jean-Louis
        Date    :       February 2000
        Descr   :       Fib function
        File    :       Fib_0.mch

?*/

/*
        February 2000	Creation.
*/      

MACHINE
	Fib_0

ABSTRACT_CONSTANTS
	fibonacci

PROPERTIES
	fibonacci : NATURAL --> NATURAL
&	fibonacci (0) = 1
&	fibonacci (1) = 1
&	!xx.((xx:NATURAL1) 
		=> (fibonacci(xx+1) = fibonacci(xx)+fibonacci(xx-1))
	)

OPERATIONS

yy <-- FIB (xx) = 
PRE
	xx : NAT
THEN
	yy := fibonacci(xx)
END;

yy <-- FIB_ (xx) = 
PRE
	xx : NAT
THEN
	ANY
		fib
	WHERE
		fib : NATURAL --> NATURAL
	&	fib (0) = 1
	&	fib (1) = 1
	&	!xxx.((xxx:NATURAL1) 
			=> (fib(xxx+1) = fib(xxx)+fib(xxx-1))
		)
	THEN
		yy := fib(xx)
	END
END

END /*? of file Fib_0.mch ?*/