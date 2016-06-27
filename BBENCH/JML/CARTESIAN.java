import org.jmlspecs.models.*;
public abstract class CARTESIAN {
  //@ public final ghost JMLEqualsSet<Integer> X;
  //@ public static final ghost JMLEqualsSet<String> Y= JMLEqualsSet.convertFrom(new String[] {"red", "green"});
  //@ public model JMLEqualsToEqualsRelation<Integer,String> rel;


/*@ public invariant
(new org.jmlspecs.b2jml.util.Relation<Integer, String>(X, Y)).has(rel)
;*/

/*@ public initially 
rel.equals(org.jmlspecs.b2jml.util.ModelUtils.cartesian(X, Y))
;*/

}
