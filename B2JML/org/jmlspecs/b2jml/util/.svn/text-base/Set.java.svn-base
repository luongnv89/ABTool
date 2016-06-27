package org.jmlspecs.b2jml.util;
/** This class represents the set of all sets of a given type.  Its
 *  primary use is in dealing with the B notion of sets as types - a set of
 *  elements of the given type should be an element of this set.
 */
public class Set<E> {
	/*@ assignable \nothing;
	    ensures \result == col instanceof org.jmlspecs.models.JMLEqualsSet<E>;
	 */
    public boolean has(org.jmlspecs.models.JMLEqualsSet<E> col) {
    	return col instanceof org.jmlspecs.models.JMLEqualsSet<?>;
    }
}
