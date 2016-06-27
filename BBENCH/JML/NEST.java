import org.jmlspecs.models.*;
public abstract class NEST {

/*@ public normal_behavior
   requires x>0;
   assignable x;
   ensures x==\old(1);
also public exceptional_behavior
   requires !(x>0);
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void test(Integer x);

}
