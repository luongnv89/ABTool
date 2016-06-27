package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all partial functions from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a partial function from the given domain to the given range should be an element
 * of this set.
 */
public class Partial<D,R> extends Relation<D,R> {

	public Partial(JMLCollection<D> p1, JMLCollection<R> p2) {
		super(p1, p2);
	}
	
	/*@ assignable \nothing;
	    ensures \result == (\forall JMLEqualsEqualsPair<D, R> p; map.has(p);
	                           (\forall JMLEqualsEqualsPair<D, R> p2; map.has(p2);
	                                !p.equals(p2) ==> !p.key.equals(p2.key)));
	 */
	public boolean has(JMLEqualsToEqualsRelation<D, R> map) {
		return map.isaFunction() && super.has(map);
	}
}
