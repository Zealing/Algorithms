package PriorityQueue;

public class BinaryHeap {
	private Key[] bh;
	private int n; //size of the heap

	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	private void exch(int i, int k) {
		// TODO Auto-generated method stub
		Key temp = bh[i];
		bh[i] = bh[k];
		bh[k] = temp;
	}

	//so clever!!!
	private boolean less(int i, int k) {
		// TODO Auto-generated method stub
		return bh[i].compareTo(bh[k]) < 0; 
	}
	
	public void insert(Key i) {
		bh[++n] = i;
		swim(n);//from the bottom of the heap to swim up 
	}
	
	//very elegant and brilliant!!
	public void sink(int parent) {
		while (2*parent <= n) {
			int j = 2*parent;
			if (j < n && less(j, j + 1)) {
				j++;
			} 
			if (!less(parent, j)) break;
			exch(parent, j);
			parent = j;
		}

	}
	
	public Key delMax() {
		Key max = bh[1];
		exch(1, n--);
		sink(1);
		bh[n+1] = null; //cleaning the garbage!!
		return max;
	}

	public BinaryHeap(int capacity) {
		// TODO Auto-generated constructor stub
		bh = new Key[capacity + 1]; //staring from 1 NOT 0
	}

}
