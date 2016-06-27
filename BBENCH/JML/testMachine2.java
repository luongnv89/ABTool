import org.jmlspecs.models.*;
public abstract class testMachine2 {
  //@ public model boolean vv;
  //@ public model Integer ww;


/*@ public invariant
org.jmlspecs.b2jml.util.B_Types.B_Boolean.has(vv)
 && org.jmlspecs.b2jml.util.B_Types.B_Nat.has(ww)
;*/

/*@ public initially 
(\exists boolean vv';\old(org.jmlspecs.b2jml.util.B_Types.B_Boolean.has(vv')); vv == \old(vv'))&&
ww==34
;*/

/*@ public normal_behavior
  requires true;
  assignable vv;
   ensures vv==\old(ww>10); */ 
public abstract void test_1();

/*@ public normal_behavior
  requires true;
  assignable vv;
   ensures (\exists boolean vv';\old(org.jmlspecs.b2jml.util.B_Types.B_Boolean.has(vv')); vv == \old(vv')); */ 
public abstract void test_2();

/*@ public normal_behavior
  requires true;
  assignable vv;
   ensures (\exists Integer zz;\old(org.jmlspecs.b2jml.util.B_Types.B_Nat.has(zz)); vv==\old(zz<10)); */ 
public abstract void test_3();

}
