package org.jmlspecs.b2jml.test;
import org.jmlspecs.b2jml.util.Relation;
import org.jmlspecs.models.JMLCollection;
import org.jmlspecs.models.JMLEqualsEqualsPair;
import org.jmlspecs.models.JMLEqualsSet;
import org.jmlspecs.models.JMLEqualsToEqualsRelation;
import org.junit.Test;
import static org.junit.Assert.*;


public class RelationTest {
	
	@Test
	public void testHas(){
		JMLEqualsSet<Integer> d=new JMLEqualsSet<Integer>(1);
		d=d.insert(2);
		d=d.insert(3);
		d=d.insert(4);
		JMLEqualsSet<Integer> r=new JMLEqualsSet<Integer>(-1);
		r=r.insert(-2);
		r=r.insert(-3);
		r=r.insert(-4);
		Relation<Integer, Integer> b=new Relation<Integer, Integer>(d, r);

		JMLEqualsToEqualsRelation<Integer,Integer> l2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		l2=l2.add(1, -1);
		l2=l2.add(2, -2);
		assertTrue(b.has(l2));
		
		JMLEqualsToEqualsRelation<JMLEqualsToEqualsRelation<Integer,Integer>, Integer> x= new 
			JMLEqualsToEqualsRelation<JMLEqualsToEqualsRelation<Integer,Integer>, Integer>();
		x=x.add(l2, 10);
		
		JMLEqualsToEqualsRelation<Integer, Integer> d2=new JMLEqualsToEqualsRelation<Integer, Integer>();
		d2=d2.add(1, -1);
		d2=d2.add(2, -2);
		//d2=d2.add(3, -3);
		JMLEqualsSet<Integer> r2=new JMLEqualsSet<Integer>(5);
		r2=r2.insert(7);
		r2=r2.insert(9);
		r2=r2.insert(10);
		JMLEqualsSet<JMLEqualsToEqualsRelation<Integer, Integer>> d3=new JMLEqualsSet<JMLEqualsToEqualsRelation<Integer, Integer>>();
		d3=d3.insert(d2);
		d2=d2.add(3, -3);
		d3=d3.insert(d2);
		Relation<JMLEqualsToEqualsRelation<Integer, Integer>, Integer> b2 = new Relation<JMLEqualsToEqualsRelation<Integer, Integer>, Integer>(d3,r2);
		assertTrue(b2.has(x));	
	}
	
	@Test
	public void testHasNot(){
		JMLEqualsSet<Integer> d=new JMLEqualsSet<Integer>(1);
		d=d.insert(2);
		d=d.insert(3);
		d=d.insert(4);
		JMLEqualsSet<Integer> r=new JMLEqualsSet<Integer>(-1);
		r=r.insert(-2);
		r=r.insert(-3);
		r=r.insert(-4);
		Relation<Integer, Integer> b=new Relation<Integer, Integer>(d, r);

		JMLEqualsToEqualsRelation<Integer,Integer> l2=new JMLEqualsToEqualsRelation<Integer,Integer>();
		l2=l2.add(0, -1);
		l2=l2.add(2, -1);
		assertFalse(b.has(l2));
	}	
}