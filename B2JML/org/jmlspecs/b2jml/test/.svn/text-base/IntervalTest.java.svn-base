package org.jmlspecs.b2jml.test;
import org.junit.*;
import org.jmlspecs.b2jml.util.Interval;
import org.jmlspecs.models.JMLEqualsSet;

public class IntervalTest {
	private JMLEqualsSet<Integer> is;
	
	@Before
	public void setUp() {
		is = new JMLEqualsSet<Integer>(1);
		is = is.insert(2);
		is = is.insert(5);
	}
	
	@Test
	public void testHasTrue() {
		 Assert.assertTrue(new Interval(0, 7).has(is));
	}
	
	@Test
	public void testHasFalse() {
		Assert.assertFalse(new Interval(0, 4).has(is));
	}
}
