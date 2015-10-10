package red_black_tree;

public class LLRBT {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		Key key;
		Val val;
		Node left;
		Node right;
		boolean color;
		
		public Node(Key key, Val val, boolean color) {
			this.key = key;
			this.val = val;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if (x == null) return false; //null link are black!!
		return x.color == RED;
	}
	
	private Node rotateLeft(Node h) {
		assert isRed(h.right);
		Node x  = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//sometimes we also need rotate to right!
	private Node rotateRight(Node h) {
		assert isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//a vital process to deal with 4-node situation!!
	private void flip(Node h) {
		assert isRed(h);
		assert isRed(h.left);
		assert isRed(h.right);
		
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	//insertion implementation -- using above methods to handle all situations!!
	private Node put (Node h, Key key, Val val) {//h is current node, key is new node's key
		//if cannot find, create a new node, insert at the bottom and color RED -- BASE CASE
		if (h == null) return new Node(key, val, RED);
		
		int cmp = key.compareTo(h.key);
		//if the key smaller than current node -- search current node's left recursively
		if      (cmp < 0) h.left = put(h.left, key, val);
		//if larger, search right recursively
		else if (cmp > 0) h.right = put(h.right, key, val);
		//if equals to current node--just change current node's value to new node's value
		else			  h.val = val;
		
		//start to determine the red-black situations
		//if right link is RED -- rotate it
		if (isRed(h.right) && !isRed(h.left)) 	  h = rotateLeft(h);
		//if all RED links in the left side -- rotate TOP at first
		if (isRed(h.left)  && isRed(h.left.left)) h = rotateRight(h);
		//if both sides are RED --- flip colors
		if (isRed(h.left)  && isRed(h.right)) 	  flip(h);
		//no need to consider two red links in the right side---will NOT happen in this implementation
		
		return h;
	}
	
	Node root;
	
	public Val get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			if (cmp > 0) x = x.right;
			else 		 return x.val;
		}
		return null;
	}
	
	public LLRBT() {
		// TODO Auto-generated constructor stub
	}

}
