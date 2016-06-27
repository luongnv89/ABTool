package org.jmlspecs.b2jml.util;

/** This class tests membership of Java values in built-in
    B types.  This helps in converting the B notion of types as sets into
    the Java notion of types as labels for values.
 */

public class B_Types {
	
	public static class B_Boolean {
		/*@ assignable \nothing;
		    ensures \result <=> (b == true || b == false);
		 */
		public static boolean has(boolean b) {
			return true;
		}
	}
	
	public static class B_Int {
		/*@ assignable \nothing;
	    	ensures \result <=> x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Int1 {
		/*@ assignable \nothing;
	    	ensures \result <=> x != 0 && x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x != 0 && x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Integer {
		/*@ assignable \nothing;
	    	ensures \result <=> x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Integer1 {
		/*@ assignable \nothing;
	    	ensures \result <=> x != 0 && x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x != 0 && x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Nat {
		/*@ assignable \nothing;
	    	ensures \result <=> x >= 0 && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x >= 0 && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Nat1 {
		/*@ assignable \nothing;
	    	ensures \result <=> x > 0 && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x > 0 && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Natural {
		/*@ assignable \nothing;
	    	ensures \result <=> x >= 0 && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x >= 0 && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_Natural1 {
		/*@ assignable \nothing;
	    	ensures \result <=> x > 0 && x <= Integer.MAX_VALUE;
		*/
		public static boolean has(int x) {
			return x > 0 && x <= Integer.MAX_VALUE;
		}		
	}
	
	public static class B_String {
		/*@ assignable \nothing;
		    ensures \result <=> s instanceof String;
		 */
		public static boolean has(String s) {
			return s instanceof String;
		}
	}
}
