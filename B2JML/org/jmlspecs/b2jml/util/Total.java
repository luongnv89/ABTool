package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all total functions from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a total function from the given domain to the given range should be an element
 * of this set.
 */
public class Total<D,R> extends Partial<D,R> {

	public Total(JMLCollection<D> p1, JMLCollection<R> p2){
		super(p1,p2);
	}

	/*@ assignable \nothing;
	    ensures rel.domain().equals(left);
	 */
	public boolean has(JMLEqualsToEqualsRelation<D,R> rel) {
		return rel.domain().equals(left) && super.has(rel);
	}
}
