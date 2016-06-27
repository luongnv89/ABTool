package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;
/** This class represents the set of all partial surjections from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a partial surjection from the given domain to the given range should be an element
 * of this set.
 */
public class PartialSurject<D,R> extends Partial<D,R> {
	
	public PartialSurject(JMLCollection<D> p1, JMLCollection<R> p2) {
		super(p1, p2);
	}

	/*@ assignable \nothing;
		ensures \result == rel.range().equals(right);
	 */
	public boolean has(JMLEqualsToEqualsRelation<D,R> rel) {
		return super.has(rel) && rel.range().equals(right);
	}
}
