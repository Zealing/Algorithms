package PriorityQueue;

public class Key implements Comparable<Key>{
	public int value;

	public Key(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public int compareTo(Key o) {
		// TODO Auto-generated method stub
		int temp = 0;
				
		if (this.value > o.value) 		temp = 1;
		else if (this.value < o.value)  temp = -1;
		else 							temp = 0;
		
		return temp;
	}

}
