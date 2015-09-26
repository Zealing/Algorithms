package Deque;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> str = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			str.enqueue(StdIn.readString());
		}
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			StdOut.println(str.sample());
		}
	}

}
