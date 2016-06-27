import org.jmlspecs.models.*;
public abstract class BOUND {
  //@ public final ghost JMLEqualsSet<Integer> X;
  //@ public model Integer y;


/*@ public invariant
X.has(y)
;*/


/*@ public normal_behavior
   requires (\forall Integer x;(X.has(x))
 ==> (x.equals(y)));
   assignable \nothing;
   ensures true;
also public exceptional_behavior
   requires !((\forall Integer x;(X.has(x))
 ==> (x.equals(y))));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void test();

}
