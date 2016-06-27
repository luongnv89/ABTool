package org.jmlspecs.b2jml.util;
import org.jmlspecs.models.*;

/** This class represents the set of all mathematical relations from a given domain
 * to a given range.  Its primary use is in dealing with the B notion of sets as
 * types - a relation from the given domain to the given range should be an element
 * of this set.
 */
public class Relation<D,R>{

	protected JMLEqualsSet<D> left;
	protected JMLEqualsSet<R> right;

	public Relation(JMLCollection<D> p1, JMLCollection<R> p2) {
		if (p1 instanceof JMLEqualsToEqualsRelation)
			left=JMLEqualsSet.convertFrom(p1);
		else 
			left=(JMLEqualsSet<D>) p1;
		if (p2 instanceof JMLEqualsToEqualsRelation)
			right=JMLEqualsSet.convertFrom(p2);
		else 
			right=(JMLEqualsSet<R>) p2;		
	}

	public JMLCollection<D> getLeft() {
		return left;
	}

	public JMLCollection<R> getRight() {
		return right;
	}

	/*@ assignable \nothing;
	    ensures \result == rel.domain().isSubset(left) && rel.range().isSubset(right);
	 */
	public boolean has(JMLEqualsToEqualsRelation<D,R> rel) {
		return rel.domain().isSubset(left) && rel.range().isSubset(right);
	}
}
