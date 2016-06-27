package org.jmlspecs.b2jml.test;


import org.jmlspecs.b2jml.util.Total;
import org.jmlspecs.models.JMLEqualsSet;
import org.jmlspecs.models.JMLEqualsToEqualsRelation;
import org.junit.Test;
import static org.junit.Assert.*;


public class TotalTest {
	
	@Test
	public void testHas(){
		JMLEqualsSet<Integer> d=new JMLEqualsSet<Integer>(1);
		d=d.insert(2);
		JMLEqualsSet<Integer> r=new JMLEqualsSet<Integer>(-1);
		r=r.insert(-2);
		Total<Integer, Integer> b=new Total<Integer, Integer>(d, r);

		JMLEqualsToEqualsRelation<Integer,Integer> l2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		l2=l2.add(1, -1);
		l2=l2.add(2, -1);
		assertTrue(b.has(l2));
	}

	@Test
	public void testHasNot(){
		JMLEqualsSet<Integer> d=new JMLEqualsSet<Integer>(1);
		d=d.insert(2);
		d=d.insert(3);
		JMLEqualsSet<Integer> r=new JMLEqualsSet<Integer>(-1);
		r=r.insert(-2);
		r=r.insert(-3);
		Total<Integer, Integer> b=new Total<Integer, Integer>(d, r);

		JMLEqualsToEqualsRelation<Integer,Integer> l2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		l2=l2.add(1, -1);
		assertFalse(b.has(l2));
	}
}