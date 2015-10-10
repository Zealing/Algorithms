package red_black_tree;

import java.util.ArrayList;

public class IntervalST <Key extends Comparable<Key>, Val>{

	private class Node {
		Key lo;
		Val hi;
		Node left;
		Node right;
		Interval interval;
		Key max;
		
		public Node(Key key, Val val) {
			this.lo = key;
			this.hi = val;
		}
		
	}

	Node root;
	
	public void put(Key lo, Key hi, Val val) {
		
	}
	
	public Val get(Key lo, Key hi) {
		Node x = root;
		
		while (x != null) {
			if 		(x.interval.intersects(lo, hi)) return x.interval;
			else if (x.left == null) 				x = x.right;
			else if (x.left.max < lo)				x = x.right;
			else 									x = x.left;
		}
		return null;
	}
	
	public void delete(Key lo, Key hi) {
		
	}
	
	public Iterable<Val> intersects(Key lo, Key hi) {
		
	}
	
	public IntervalST() {
		// TODO Auto-generated constructor stub
	}

}
