package org.jmlspecs.b2jml.util;
/* This class supplements the JML model classes with definitions of operators
    commonly used in in B.
    @author Tim Wahls
    @author Danni Yu
    @version 8/9/2011
 */

import org.jmlspecs.models.*;

public class ModelUtils {

	/*@ assignable \nothing;
	    ensures \result.int_size() == keys.int_size() * vals.int_size() && 
	      (\forall K key; keys.has(key); (\forall V val; vals.has(val); \result.has(key, val)));
	 */
	public static <K, V> JMLEqualsToEqualsRelation<K, V> cartesian(
			JMLCollection<K> keys, JMLCollection<V> vals) {
		JMLEqualsToEqualsRelation<K, V> res = new JMLEqualsToEqualsRelation<K, V>();
		for (K key : keys) {
			for (V val : vals) {
				res = res.add(key, val);
			}
		}
		return res;
	}

	/*@ assignable \nothing;
	    ensures \result.equals(rel.restrictDomainTo(rel.domain().difference(sub)));
	 */
	public static <K, V> JMLEqualsToEqualsRelation<K, V> domain_subtraction(
			JMLEqualsSet<K> sub, JMLEqualsToEqualsRelation<K, V> rel) {
		JMLEqualsSet<K> newDom = rel.domain().difference(sub);
		return rel.restrictDomainTo(newDom);
	}

	/*@ assignable \nothing;
	    ensures \result.equals(rel.restrictRangeTo(rel.range().difference(sub)));
	 */
	public static <K, V> JMLEqualsToEqualsRelation<K, V> range_subtraction(
			JMLEqualsToEqualsRelation<K, V> rel, JMLEqualsSet<V> sub) {
		JMLEqualsSet<V> newRange = rel.range().difference(sub);
		return rel.restrictRangeTo(newRange);
	}

	/*@ requires rel.isaFunction();
	    assignable \nothing;
	    ensures \result.equals(rel.toFunction());
	    also requires !rel.isaFunction();
	    assignable \nothing;
	    signals (Exception);
	 */
	public static <K, V> JMLEqualsToEqualsMap<K, V> toFunction(
			JMLEqualsToEqualsRelation<K, V> rel) {
		if (rel.isaFunction()) {
			return rel.toFunction();
		} else {
			throw new RuntimeException("ERROR: expected function, not relation");
		}
	}

	/** @return a subsequence including the last n elements in p1 */
	/*@ requires n >= 0; 
	    assignable \nothing;
	    ensures (n > p1.int_length()) ? \result.isEmpty() : 
	            \result.equals(p1.subsequencep1.int_length()-n, p1.int_length());
	 */
	public static <E> JMLEqualsSequence<E> suffixseq(JMLEqualsSequence<E> p1,
			int n) throws JMLSequenceException{
		if (n>p1.int_length() || n<0) throw new JMLSequenceException();
		else if (n==p1.int_length()) return new JMLEqualsSequence<E>();
		return p1.subsequence(p1.int_length()-n, p1.int_length());
	}

	/** @return min of a set */
	/*@ requires p1.int_size() > 0;
	    assignable \nothing;
	    \ensures (\forall Integer i; p1.has(1); \result <= i) && p1.has(\result);
	 */
	public static Integer min(JMLEqualsSet<Integer> p1) {
		Integer minimum = p1.choose();
		for (Integer i : p1) {
			if (i < minimum)
				minimum = i;
		}
		return minimum;
	}

	/** @return max of a set */
	/*@ requires p1.int_size() > 0;
	    assignable \nothing;
	    \ensures (\forall Integer i; p1.has(1); \result >= i) && p1.has(\result);
	 */
	public static Integer max(JMLEqualsSet<Integer> p1) {
		Integer maximum = p1.choose();
		for (Integer i : p1) {
			if (i > maximum)
				maximum = i;
		}
		return maximum;
	}

	/** Elements of p2 in (x1-zi) notation overwrite any elements (x1-yi) in R1 */
	/*@ assignable \nothing
	    ensures (\forall D d; p2.domain.has(d); \result.elementImage(d).equals(p2.elementImage(d))) &&
	            (\forall D d; !p2.domain.has(d); \result.elementImage(d).equals(p1.elementImage(d)))
	 */
	public static <D, R> JMLEqualsToEqualsRelation<D, R> overrideforward(
			JMLEqualsToEqualsRelation<D, R> p1,JMLEqualsToEqualsRelation<D, R> p2) {
		JMLEqualsSet<D> d1 = p1.domain();
		JMLEqualsSet<D> d2 = p2.domain();
		for (D o : d1) {
			if (d2.has(o))
				p1=p1.removeFromDomain(o);
		}
		return p1.union(p2);
	}

	/** Represents the direct product of p1 and p2, i.e.(a1-b1) from p1 and
	 * (a1-c1) from p2 would return a1-(b1-c1) */
	/*@ assignable \nothing;
	    ensures \forall (D d; p1.domain.has(d) && p2.domain.has(d);
	       (\forall R1 r1; (\forall R2 r2; 
	         \result.elementImage(d).has(r1, r2) <=> 
	           (p.elementImage(d).has(r1) && p2.elementImage(d).has(r2))))
	 */
	public static <D, R1, R2> JMLEqualsToEqualsRelation<D, JMLEqualsEqualsPair<R1, R2>> relprod(
			JMLEqualsToEqualsRelation<D, R1> p1, JMLEqualsToEqualsRelation<D, R2> p2) {
		JMLEqualsSet<D> d=p1.domain().intersection(p2.domain());
		JMLEqualsToEqualsRelation<D, JMLEqualsEqualsPair<R1, R2>> result =
			  new JMLEqualsToEqualsRelation<D, JMLEqualsEqualsPair<R1, R2>>();
		for (D o : d){
			for (R1 r1 : p1.elementImage(o)) {
				for (R2 r2 : p2.elementImage(o)) {
					result=result.add(o, new JMLEqualsEqualsPair<R1, R2>(r1,r2));
				}
			}
		}
		return result;
	}

	/*
	 * Binary correspondence of p1 to p2.
	 */
	public static <D, R> JMLEqualsEqualsPair<D, R> maplet(D p1, R p2) {
		return new JMLEqualsEqualsPair<D, R>(p1, p2);
	}
	
	/*@
	  assignable \nothing;
	  ensures (seqs.int_size() == 0 ==> \result.equals(new JMLEqualsSequence<F>()))
	       && (seqs.int_size() > 0 ==> \result.equals(seqs.itemAt(0).concat(
	             ModelUtils.concat(seqs.trailer())))); 
	 */
	public static <F> JMLEqualsSequence<F> concat(JMLEqualsSequence<JMLEqualsSequence<F>> seqs) {
		JMLEqualsSequence<F> res = new JMLEqualsSequence<F>();
		for (JMLEqualsSequence<F> seq : seqs) {
			res = res.concat(seq);
		}
		return res;
	}
	
	/*@ assignable \nothing;
	  ensures (sets.int_size() == 0 ==> \result.equals(new JMLEqualsSet<F>()))
	       && (sets.int_size() > 0 ==> 
	            (\exists JMLEqualsSet<F> s; sets.has(s);
	                \result.equals(s.union(ModelUtils.union(sets.remove(s)))))); 
	 */
	public static <F> JMLEqualsSet<F> union(JMLEqualsSet<JMLEqualsSet<F>> sets) {
		JMLEqualsSet<F> res = new JMLEqualsSet<F>();
		for (JMLEqualsSet<F> set : sets) {
			res = res.union(set);
		}
		return res;
	}
	
	/*@ assignable \nothing;
	    ensures (sets.int_size() == 0 ==> \result.equals(new JMLEqualsSet<F>()))
	    	 && (sets.int_size() == 1 ==> \result.equals(sets.choose()))
	         && (sets.int_size() > 1 ==> 
	              (\exists JMLEqualsSet<F> s; sets.has(s);
	                  \result.equals(s.intersection(ModelUtils.inter(sets.remove(s)))))); 
	 */
	public static <F> JMLEqualsSet<F> inter(JMLEqualsSet<JMLEqualsSet<F>> sets) {
		JMLEqualsSet<F> res = new JMLEqualsSet<F>();
		if (sets.int_size() != 0) {
			java.util.Iterator<JMLEqualsSet<F>> iter = sets.iterator();
			res = iter.next();
			while (iter.hasNext()) {
				res = res.intersection(iter.next());
			}
		}
		return res;
	}
	
	/*@ assignable \nothing;
	    \ensures (\forall JMLEqualsEqualsPair<K, V> p; \result.has(p) <=> set.has(p));
	 */
	public static <K, V> JMLEqualsToEqualsRelation<K, V> toRel(JMLCollection<JMLEqualsEqualsPair<K, V>> set) {
		JMLEqualsToEqualsRelation<K, V> res = new JMLEqualsToEqualsRelation<K, V>();
		for (JMLEqualsEqualsPair<K, V> p : set) {
			res = res.insert(p);
		}
		return res;
	}
	
	/*@ assignable \nothing;
        ensures (\forall JMLEqualsEqualsPair<K, V> p; \result.has(p) <=> set.has(p));
    */
	public static <K, V> JMLEqualsToEqualsMap<K, V> toMap(JMLCollection<JMLEqualsEqualsPair<K, V>> set) {
		return toRel(set).toFunction();
	}
	
	/*@ assignable \nothing;
        ensures (\forall JMLEqualsEqualsPair<K, V> p; \result.has(p) <=> 
                  (\exists int i; 0 <= i && i < elems.length; p.equals(elems[i])));
    */
	public static <K, V> JMLEqualsToEqualsRelation<K, V> toRel(JMLEqualsEqualsPair<K, V> ... elems) {
		JMLEqualsToEqualsRelation<K, V> res = new JMLEqualsToEqualsRelation<K, V>();
		for (JMLEqualsEqualsPair<K, V> el : elems) {
			res = res.insert(el);
		}
		return res;
	}
	
	/*@ assignable \nothing;
        ensures (\forall E e; \result.has(e) <=> 
              (\exists int i; 0 <= i && i < elems.length; e.equals(elems[i])));
    */
	public static <E> JMLEqualsSet<E> toSet(E ... elems) {
		JMLEqualsSet<E> res = new JMLEqualsSet<E>();
		for (E el : elems) {
			res = res.insert(el);
		}
		return res;
	}

}
