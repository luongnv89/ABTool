/* 
   Description : Machine de test des appel de function
   Author : Boulanger jean-louis
   Copyright 2000-2010 Jean-Louis Boulanger
*/

MACHINE
	FuncCall

VARIABLES
	ff

INVARIANT
	ff = ff(xx,yy,zz)
&	ff = ff(xx,yy,zz) [aa]
&	ff = ff(xx,yy,zz)~[aa]
&	ff = ff ~ (xx,yy,zz)~[aa]
&	ff = ff ~ (xx,yy,zz) [aa]
&	ff = ff  (xx) (yy,zz)
&	ff = ff ~ (xx) (yy,zz)~[aa]

INITIALISATION
ff :(
	ff = ff(xx,yy,zz)
&	ff = ff(xx,yy,zz) [aa]
&	ff = ff(xx,yy,zz)~[aa]
&	ff = ff ~ (xx,yy,zz)~[aa]
&	ff = ff ~ (xx,yy,zz) [aa]
&	ff = ff   (xx) (yy,zz)
&	ff = ff ~ (xx) (yy,zz)~[aa]
)

OPERATIONS

OP =
	ff(xx)

END
