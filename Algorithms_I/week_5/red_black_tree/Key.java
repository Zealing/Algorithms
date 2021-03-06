package red_black_tree;

public class Key implements Comparable<Key>{
	int key;
	
	public Key(int key) {
		this.key = key;
	}

	@Override
	public int compareTo(Key o) {
		if (this.key > o.key) return 1;
		if (this.key < o.key) return -1;
		else 				  return 0;
	}

}
