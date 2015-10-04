package PriorityQueue;

public class UnorderedPQ <Key extends Comparable<Key>>{
	private Key[] pq;
	private int n; // number of elements in pq
	
	public UnorderedPQ(int capacity) {
		// TODO Auto-generated constructor stub
		pq = (Key[]) new Comparable[capacity];
	}

	public void insert(Key v) {
		pq[n++] = v;//remember to maintain the size of n after insertion
	}
	
	public Key delMax() {//do iteration through out the array to find the max and swap it with the last node and delete it
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (less(max, i)) max = i;
		}
		exch(max, n - 1);
		return pq[--n];
	}
	
	private void exch(int max, int i) {
		// TODO Auto-generated method stub
		int temp = 0;
		temp = max;
		max = i;
		i = temp;
	}

	private boolean less(int max, int i) {
		// TODO Auto-generated method stub
		if (pq[max].compareTo(pq[i]) == -1) return true;
		else 								return false;
	}

	public boolean isEmpty() {
		return n == 0;
	}
	
	public Key max() {
		return null;
	}
	
	public int size() {
		return n;
	}
}
