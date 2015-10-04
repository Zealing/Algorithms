package PriorityQueue;

public class HeapSort {
	private Key[] set;
	
	public void sort(Key[] set) {
		int size = set.length;
		//construct the binary heap
		for (int k = size / 2; k >= 1; k--) {
			sink(set, k, size);
		}
		
		//sort down from the max
		while (size > 1) {
			exch(1, size);
			sink(set, 1, --size);
		}
	}
	
	private void sink(Key[] set, int parent, int size) {
		while (2*parent <= size) {
			int j = 2*parent;
			if (j < size && less(j, j + 1)) {
				j++;
			} 
			if (!less(parent, j)) break;
			exch(parent, j);
			parent = j;//keep going to the next node
		}
	}
	
	private boolean less(int i, int j) {
		return set[i].compareTo(set[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key temp = set[i];
		set[i] = set[j];
		set[j] = temp;
	}
	
	
	public HeapSort(Key[] set) {
		// TODO Auto-generated constructor stub
		this.set = set;
	}

}
