// @(#)$Id: JMLRelationImageEnumerator.java-generic,v 1.23 2005/12/24 21:20:31 chalin Exp $

// Copyright (C) 2005 Iowa State University
//
// This file is part of the runtime library of the Java Modeling Language.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation; either version 2.1,
// of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JML; see the file LesserGPL.txt.  If not, write to the Free
// Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
// 02110-1301  USA.


package org.jmlspecs.models;

/** Enumerator for pairs of keys and their relational images.  This
 *  enumerator returns pairs of type {@link JMLEqualsValuePair},
 *  each of which has a "key" field of type {@link _DomainType_} and a
 *  "value" field of type {@link JMLValueSet}s containing {@link
 *  _RangeType_}.
 *
 * @version $Revision: 1.23 $
 * @author Gary T. Leavens
 * @see JMLEnumeration
 * @see JMLValueType
 * @see JMLEqualsToValueRelationEnumerator
 * @see JMLEqualsToValueRelation
 * @see JMLEqualsToValueMap
 * @see JMLEnumerationToIterator
 * @see JMLValueSet
 */
// FIXME: adapt this file to non-null-by-default and remove the following modifier.
/*@ nullable_by_default @*/ 
public class JMLEqualsToValueRelationImageEnumerator<K, V extends JMLType>
    implements JMLEnumeration<JMLEqualsValuePair<K, JMLValueSet<V>>>, JMLValueType {

    //@ public model JMLValueSet<JMLEqualsValuePair<K, JMLValueSet<V>>> uniteratedImagePairs;     in objectState;

    /** An enumerator for the image pairs in this relation.
     */
    protected /*@ non_null @*/ JMLValueSetEnumerator<JMLEqualsValuePair<K, JMLValueSet<V>>> pairEnum;
    //@                                             in uniteratedImagePairs;

    /*@ protected represents uniteratedImagePairs <- abstractValue();
      @*/

    /*@ protected
      @   invariant moreElements <==> pairEnum.moreElements;
      @*/

    //@ public invariant elementType == \type(JMLEqualsValuePair<K, JMLValueSet<V>>);
    //@ public invariant !returnsNull;

    /** Initialize this with the given relation.
     */
    /*@ normal_behavior
      @   requires rel != null;
      @   assignable uniteratedImagePairs;
      @   assignable moreElements, elementType, returnsNull, owner;
      @   ensures uniteratedImagePairs.equals(rel.imagePairSet());
      @    ensures owner == null;
      @*/
    JMLEqualsToValueRelationImageEnumerator(/*@ non_null @*/
                                           JMLEqualsToValueRelation<K, V> rel)
    {
        //@ set owner = null;
        //@ set elementType = \type(JMLEqualsValuePair<K, JMLValueSet<V>>);
        //@ set returnsNull = false;
        pairEnum = rel.imagePairSet_.elements();
        
    }

    /*@ normal_behavior
      @   requires pEnum != null;
      @   assignable uniteratedImagePairs;
      @   assignable moreElements, elementType, returnsNull, owner;
      @   ensures owner == null;
      @*/
    protected
        JMLEqualsToValueRelationImageEnumerator(/*@ non_null @*/
                                                   JMLValueSetEnumerator<JMLEqualsValuePair<K, JMLValueSet<V>>> pEnum)
    {
        //@ set owner = null;
        //@ set elementType = \type(JMLEqualsValuePair);
        //@ set returnsNull = false;
        pairEnum =  (JMLValueSetEnumerator<JMLEqualsValuePair<K, JMLValueSet<V>>>)pEnum.clone();
    }

    /** Tells whether this enumerator has more uniterated elements.
     *  @see #nextElement
     *  @see #nextImagePair
     */
    /*@ also
      @  public normal_behavior
      @    ensures \result == !uniteratedImagePairs.isEmpty();
      @*/
    public boolean hasMoreElements() {
        return pairEnum.hasMoreElements();
    }

    /** Return the next image pair in this, if there is one.
     *  @exception JMLNoSuchElementException when this is empty.
     *  @see #hasMoreElements
     *  @see #nextImagePair
     */
    /*@ also
      @  public normal_behavior
      @    requires !uniteratedImagePairs.isEmpty();
      @    assignable uniteratedImagePairs;
      @    ensures \old(uniteratedImagePairs).has((JMLType)\result)
      @        && uniteratedImagePairs
      @           .equals(\old(uniteratedImagePairs).remove((JMLType)\result));
      @ also
      @  public exceptional_behavior
      @    requires uniteratedImagePairs.isEmpty();
      @    assignable \nothing;
      @    signals_only JMLNoSuchElementException;
      @*/
    public JMLEqualsValuePair<K, JMLValueSet<V>> nextElement() throws JMLNoSuchElementException {
        if (pairEnum.hasMoreElements()) {
            JMLEqualsValuePair<K, JMLValueSet<V>> o = pairEnum.nextElement();
            //@ assume o != null && \typeof(o) == elementType;
            return o;
        } else {
            throw new JMLNoSuchElementException();
        }
    }

    /** Return the next image pair in this, if there is one.
     *  @exception JMLNoSuchElementException when this is empty.
     *  @see #hasMoreElements
     *  @see #nextElement
     */
    /*@ public normal_behavior
      @    requires !uniteratedImagePairs.isEmpty();
      @    assignable uniteratedImagePairs, moreElements;
      @    ensures \old(uniteratedImagePairs).has(\result)
      @         && uniteratedImagePairs.equals(
      @               \old(uniteratedImagePairs).remove(\result));
      @ also
      @  public exceptional_behavior
      @    requires uniteratedImagePairs.isEmpty();
      @    assignable \nothing;
      @    signals_only JMLNoSuchElementException;
      @
      @ also
      @    modifies uniteratedImagePairs;
      @    modifies moreElements;
      @    ensures \old(moreElements);
      @    signals_only JMLNoSuchElementException;
      @    signals (JMLNoSuchElementException) \old(!moreElements);
      @*/
    public /*@ non_null @*/ JMLEqualsValuePair<K, JMLValueSet<V>> nextImagePair()
        throws JMLNoSuchElementException {
        //@ assume moreElements;
        JMLEqualsValuePair<K, JMLValueSet<V>> p = nextElement();
        //@ assume p != null && p instanceof JMLEqualsValuePair;
        return p;
    } //@ nowarn NonNullResult, Post;

    /** Return a clone of this enumerator.
     */
    public /*@ non_null @*/ Object clone() {
        return new JMLEqualsToValueRelationImageEnumerator<K, V>(pairEnum);
    } //@ nowarn Post; 

    /** Return true just when this enumerator has the same state as
     *  the given argument.
     */
    public boolean equals(/*@ nullable @*/ Object oth) {
        if  (oth == null
             || !(oth instanceof JMLEqualsToValueRelationImageEnumerator)){
            return false;
        } else {
            JMLEqualsToValueRelationImageEnumerator<K, V> js
                = (JMLEqualsToValueRelationImageEnumerator<K, V>) oth;
            return abstractValue().equals(js.abstractValue());
        }
    }

    /** Return a hash code for this enumerator.
     */
    public int hashCode() {
        return abstractValue().hashCode();
    }

    /** Return the set of uniterated pairs from this enumerator.
     */
    protected /*@ spec_public pure @*/ /*@ non_null @*/
        JMLValueSet<JMLEqualsValuePair<K, JMLValueSet<V>>> abstractValue()
    {
        JMLValueSet<JMLEqualsValuePair<K, JMLValueSet<V>>> ret = new JMLValueSet<JMLEqualsValuePair<K, JMLValueSet<V>>>();
        JMLEqualsToValueRelationImageEnumerator<K, V> enum2
            = (JMLEqualsToValueRelationImageEnumerator<K, V>) clone();
        while (enum2.hasMoreElements()) {
            JMLEqualsValuePair<K, JMLValueSet<V>> aPair = enum2.nextImagePair();
            ret = ret.insert(aPair);
        }
        return ret;
    } //@ nowarn Exception;
}
