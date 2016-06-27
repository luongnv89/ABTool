package org.jmlspecs.b2jml.util;

import org.jmlspecs.models.JMLEqualsSet;
/** This class represents the set of all integer intervals from a given lower bound
 * to a given upper bound.  Its primary use is in dealing with the B notion of sets as
 * types - a set of integers whose elements lie in given range should be an element
 * of this set.
 */
public class Interval {	
	private int lower, upper;
	
	public Interval(int s, int e){
		lower = s;
		upper = e;
	}
	
	/*@ assignable \nothing;
	    ensures \result <=> (\forall Integer i; s.has(i); i >= lower && i <= upper);
	 */
	public boolean has(JMLEqualsSet<Integer> s) {
		for (Integer i : s) {
			if (i < lower || i > upper) {
				return false;
			}
		}
		return true;
	}
}
