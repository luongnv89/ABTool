import org.jmlspecs.models.*;
public abstract class SEQUENCE {
  //@ public model JMLEqualsSequence<Integer> s;
  //@ public model JMLEqualsSequence<JMLEqualsSequence<Integer>> ss;


/*@ public invariant
new org.jmlspecs.b2jml.util.Seq<Integer>().has(s)
 && s.int_size()>0
 && s.first()==1
 && s.header().reverse().int_size()>0
 && new org.jmlspecs.b2jml.util.Seq<JMLEqualsSequence<Integer>>().has(ss)
 && org.jmlspecs.b2jml.util.ModUtils.concat(ss).int_size()>1
 && s.itemAt(1 - 1)==1
;*/

}
