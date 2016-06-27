package org.jmlspecs.b2jml.test;

import junit.framework.Assert;

import org.jmlspecs.b2jml.util.ModelUtils;
import org.jmlspecs.models.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelUtilsTest {
	
	@Test
	public void testSuffixseq(){
		JMLEqualsSequence<Integer> t=new JMLEqualsSequence<Integer>(1);
		t=t.insertBack(2);
		t=t.insertBack(3);
		t=t.insertBack(4);
		JMLEqualsSequence<Integer> t2=new JMLEqualsSequence<Integer>(3);
		t2=t2.insertBack(4);
		assertEquals(ModelUtils.suffixseq(t, 2), t2);
	}
	
	@Test
	public void testMin(){
		JMLEqualsSet<Integer> t=new JMLEqualsSet<Integer>(2);
		t=t.insert(5);
		t=t.insert(1);
		t=t.insert(4);
		assertEquals(ModelUtils.min(t), new Integer(1));
	}
	
	@Test
	public void testMax(){
		JMLEqualsSet<Integer> t=new JMLEqualsSet<Integer>(2);
		t=t.insert(5);
		t=t.insert(1);
		t=t.insert(4);
		assertEquals(ModelUtils.max(t), new Integer(5));		
	}
	
	@Test
	public void testOverrideForward(){
		JMLEqualsToEqualsRelation<Integer, Integer> t=new JMLEqualsToEqualsRelation<Integer,Integer>();
		t=t.add(2, 1);
		t=t.add(2, 8);
		t=t.add(3, 9);
		t=t.add(4, 7);
		t=t.add(4, 9);
		JMLEqualsToEqualsRelation<Integer,Integer> t2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		t2=t2.add(0, -1);
		t2=t2.add(1, 7);
		t2=t2.add(2, 9);
		JMLEqualsToEqualsRelation<Integer,Integer> t3=new JMLEqualsToEqualsRelation<Integer,Integer>();
		t3=t3.add(0, -1);
		t3=t3.add(1, 7);
		t3=t3.add(2, 9);
		t3=t3.add(3, 9);
		t3=t3.add(4, 7);
		t3=t3.add(4, 9);
		assertEquals(ModelUtils.overrideforward(t, t2),t3);
	}
	
	@Test
	public void testRelprod(){
		JMLEqualsToEqualsRelation<Integer, Integer> t=new JMLEqualsToEqualsRelation<Integer,Integer>();
		t=t.add(0, 0);
		t=t.add(1, 10);
		t=t.add(1, 30);
		t=t.add(2, 20);
		JMLEqualsToEqualsRelation<Integer,Integer> t2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		t2=t2.add(0, 0);
		t2=t2.add(1, 20);
		t2=t2.add(2, 40);
		t2=t2.add(3, 60);
		JMLEqualsToEqualsRelation<Integer, JMLEqualsEqualsPair<Integer, Integer>> t3=new JMLEqualsToEqualsRelation<Integer, JMLEqualsEqualsPair<Integer, Integer>>();
		t3=t3.add(0, new JMLEqualsEqualsPair<Integer, Integer>(0,0));
		t3=t3.add(1, new JMLEqualsEqualsPair<Integer, Integer>(10,20));
		t3=t3.add(1, new JMLEqualsEqualsPair<Integer, Integer>(30,20));
		t3=t3.add(2, new JMLEqualsEqualsPair<Integer, Integer>(20,40));
		assertEquals(ModelUtils.relprod(t, t2),t3);
	}
	
	@Test
	public void testMaplet(){
		int l=1;
		int l2=2;
		assertEquals(ModelUtils.maplet(l, l2), new JMLEqualsEqualsPair<Integer, Integer>(1,2));
	}
	
	@Test
	public void testConcat() {
		JMLEqualsSequence<JMLEqualsSequence<Integer>> seqs = new JMLEqualsSequence<JMLEqualsSequence<Integer>>();
		Assert.assertEquals(ModelUtils.concat(seqs), new JMLEqualsSequence<Integer>());
		JMLEqualsSequence<Integer> one = new JMLEqualsSequence<Integer>().insertFront(2).insertFront(1);
		seqs = seqs.insertFront(one);
		Assert.assertEquals(ModelUtils.concat(seqs), one);
		JMLEqualsSequence<Integer> two = new JMLEqualsSequence<Integer>().insertFront(4).insertFront(3);
		seqs = seqs.insertFront(two);
		Assert.assertEquals(ModelUtils.concat(seqs), two.concat(one));
	}
	
	@Test
	public void testUnion() {
		JMLEqualsSet<JMLEqualsSet<Integer>> sets = new JMLEqualsSet<JMLEqualsSet<Integer>>();
		Assert.assertEquals(ModelUtils.union(sets), new JMLEqualsSet<Integer>());
		JMLEqualsSet<Integer> one = new JMLEqualsSet<Integer>().insert(1).insert(2);
		sets = sets.insert(one);
		Assert.assertEquals(ModelUtils.union(sets), one);
		JMLEqualsSet<Integer> two = new JMLEqualsSet<Integer>().insert(3).insert(2).insert(0);
		sets = sets.insert(two);
		Assert.assertEquals(ModelUtils.union(sets), one.union(two));
	}
	
	@Test
	public void testInter() {
		JMLEqualsSet<JMLEqualsSet<Integer>> sets = new JMLEqualsSet<JMLEqualsSet<Integer>>();
		Assert.assertEquals(ModelUtils.inter(sets), new JMLEqualsSet<Integer>());
		JMLEqualsSet<Integer> one = new JMLEqualsSet<Integer>().insert(1).insert(2);
		sets = sets.insert(one);
		Assert.assertEquals(ModelUtils.inter(sets), one);
		JMLEqualsSet<Integer> two = new JMLEqualsSet<Integer>().insert(3).insert(2).insert(0);
		sets = sets.insert(two);
		Assert.assertEquals(ModelUtils.inter(sets), new JMLEqualsSet<Integer>().insert(2));
	}
	
	@Test
	public void testToRelation() {
		JMLEqualsSet<JMLEqualsEqualsPair<Integer, Integer>> set = new JMLEqualsSet<JMLEqualsEqualsPair<Integer, Integer>>();
		set = set.insert(new JMLEqualsEqualsPair<Integer, Integer>(3, 4));
		set = set.insert(new JMLEqualsEqualsPair<Integer, Integer>(5, 6));
		JMLEqualsToEqualsRelation<Integer, Integer> rel = ModelUtils.toRel(set);
		for (JMLEqualsEqualsPair<Integer, Integer> p : set) {
			Assert.assertTrue(rel.has(p.key, p.value));
		}
	}
	
	
	@Test
	public void testToFunction() {
		JMLEqualsSet<JMLEqualsEqualsPair<Integer, Integer>> set = new JMLEqualsSet<JMLEqualsEqualsPair<Integer, Integer>>();
		set = set.insert(new JMLEqualsEqualsPair<Integer, Integer>(3, 4));
		set = set.insert(new JMLEqualsEqualsPair<Integer, Integer>(5, 6));
		JMLEqualsToEqualsMap<Integer, Integer> rel = ModelUtils.toMap(set);
		for (JMLEqualsEqualsPair<Integer, Integer> p : set) {
			Assert.assertTrue(rel.has(p.key, p.value));
		}
	}
	
	@Test
	public void testToRel() {
		JMLEqualsEqualsPair<Integer, Integer> e1 = new JMLEqualsEqualsPair<Integer, Integer>(3, 4);
		JMLEqualsEqualsPair<Integer, Integer> e2 = new JMLEqualsEqualsPair<Integer, Integer>(5, 6);
		JMLEqualsToEqualsRelation<Integer, Integer> rel = ModelUtils.toRel(e1, e2);
		Assert.assertTrue(rel.has(3, 4));
		Assert.assertTrue(rel.has(5, 6));
	}
	
	@Test
	public void testToSet() {
		JMLEqualsSet<Integer> set = ModelUtils.toSet(3, 2, 4);
		Assert.assertTrue(set.has(2));
		Assert.assertTrue(set.has(3));
		Assert.assertTrue(set.has(4));
	}
}
