package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all total bijections from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a total bijection from the given domain to the given range should be an element
 * of this set.
 */
public class Bijection<D,R> extends TotalInject<D,R> {

	public Bijection(JMLCollection<D> p1, JMLCollection<R> p2) {
		super(p1, p2);
	}

	/*@ assignable \nothing;
		ensures \result == new TotalSurject<D, R>(left, right).has(rel);
	 */
	public boolean has(JMLEqualsToEqualsRelation<D,R> rel) {
		return super.has(rel) && new TotalSurject<D, R>(left, right).has(rel);
	}
}
