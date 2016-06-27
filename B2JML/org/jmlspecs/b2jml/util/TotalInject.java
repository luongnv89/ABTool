package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all total injections from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a total injection from the given domain to the given range should be an element
 * of this set.
 */
public class TotalInject<D, R> extends Total<D, R> {

	public TotalInject(JMLCollection<D> p1, JMLCollection<R> p2) {
		super(p1, p2);
	}

	/*@ assignable \nothing;
		ensures \result == rel.inverse().isaFunction();
	 */
	public boolean has(JMLEqualsToEqualsRelation<D, R> rel) {
		return rel.inverse().isaFunction() && super.has(rel);
	}
}
