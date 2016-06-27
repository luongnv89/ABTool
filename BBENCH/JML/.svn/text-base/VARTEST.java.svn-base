import org.jmlspecs.models.*;
public abstract class VARTEST {
  //@ public model Integer x;
  //@ public model Integer y;
  //@ public model Integer z;


/*@ public invariant
org.jmlspecs.b2jml.util.B_Types.B_Nat.has(x)
 && org.jmlspecs.b2jml.util.B_Types.B_Nat.has(y)
 && org.jmlspecs.b2jml.util.B_Types.B_Int.has(z)
;*/

/*@ public normal_behavior
  requires true;
  assignable y;
   ensures (\exists Integer x; (\exists boolean b; 
   x==\old(1)&&
y==\old(2)&&
b==\old(true))); */ 
public abstract void test1();

/*@ public normal_behavior
  requires true;
  assignable x,y,z;
   ensures (\old(x<3) ==> (x==\old(3))) && 
(\old(x>5) ==> ((\exists Integer x; (\exists boolean b; (\exists Integer z; 
   x==\old(1)&&
y==\old(2)&&
b==\old(true)&&
z==\old(3)))))) && 
((!\old(x<3) && !\old(x>5)) ==> (z==\old(7))); */ 
public abstract void test2();

}
