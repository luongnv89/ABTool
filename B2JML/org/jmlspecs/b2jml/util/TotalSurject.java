package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all total surjections from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a total surjection from the given domain to the given range should be an element
 * of this set.
 */
public class TotalSurject<D,R> extends Total<D,R> {

	public TotalSurject(JMLCollection<D> p1, JMLCollection<R> p2) {
		super(p1, p2);
	}

	/*@ assignable \nothing;
		ensures \result == rel.range().equals(right);
	 */
	public boolean has(JMLEqualsToEqualsRelation<D,R> rel) {
		return super.has(rel) && rel.range().equals(right);
	}
}
