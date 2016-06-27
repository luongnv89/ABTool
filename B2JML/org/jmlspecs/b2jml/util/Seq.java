package org.jmlspecs.b2jml.util;
/** This class represents the set of all sequences of a given type.  Its
 *  primary use is in dealing with the B notion of sets as types - a sequence of
 *  elements of the given type should be an element of this set.
 */
public class Seq<E> {
	/*@ assignable \nothing;
	    ensures \result == col instanceof org.jmlspecs.models.JMLEqualsSequence<E>;
	 */
    public boolean has(org.jmlspecs.models.JMLEqualsSequence<E> col) {
    	return col instanceof org.jmlspecs.models.JMLEqualsSequence<?>;
    }
}
