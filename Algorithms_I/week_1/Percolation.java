package Percolation;

import java.util.Scanner;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	//create all global variable
	private boolean [][] opened;
	private int size;
	private int top = 0;
	private int bottom;
	private WeightedQuickUnionUF qf;
	
	//create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException("Given N <= 0");
		}
		//set the size be the N
		size = N;
		
		//set the bottom node
		bottom = size * size + 1;
		
		//set the QuickUnionUF data structure--make sure it involves top and bottom nodes--from number 0 to size^2 + 2
		qf = new WeightedQuickUnionUF(size * size + 2);
		
		//set the rest of all sites should be opened--at first, they are all false;
		opened = new boolean [size][size];
	}
	
	// open site (row i, column j) if it is not open already
	public void open (int i, int j) {
		if ( i <= 0 || i > size || j <= 0 || j > size) {
			throw new IndexOutOfBoundsException("Given number is out of range");
		}
		
		//take care of corner cases---in Java, using numbers from 0--in practice, using from 1
		if (!isOpen(i, j)) {
			opened[i-1][j-1] = true;
			
			//union the site with the top node---make it percolated by the top if it is in the row 1
			if (i == 1) {
				qf.union(getQFIndex(i, j), top);
			}
			
			//if it is in the last line--make it percolated with the bottom
			if ( i == size) {
				qf.union(getQFIndex(i, j), bottom);
			}
			
			//determine whether it should be connected with neighborhood
			//first, check the left if it has left
			if (j > 1 && isOpen(i, j - 1)) {
				qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
			}
			
			//then, check the right if it has right
			if (j < size && isOpen(i, j + 1)) {
				qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
			}
			
			//check the up
			if (i > 1 && isOpen(i - 1, j)) {
				qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
			}
			
			//check the down
			if (i < size && isOpen(i + 1, j)) {
				qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
			}
		}
	}
	
	// is site (row i, column j) open?
	public boolean isOpen (int i, int j) {
		if ( i <= 0 || i > size || j <= 0 || j > size) {
			throw new IndexOutOfBoundsException("Given number is out of range");
		}
		return opened [i - 1][j - 1];
	}
	
	// is site (row i, column j) full?
	public boolean isFull (int i, int j) {
		if ( i <= 0 || i > size || j <= 0 || j > size) {
			throw new IndexOutOfBoundsException("Given number is out of range");
		}
		//using the connected method in WeightedQuickFindUF to check it
		return (qf.connected(getQFIndex(i, j), top));
	}
	
	// does the system percolate?
	public boolean percolates () {
		return (qf.connected(top, bottom));
	}
	
	//get the Quick-Find structure's index for specific site---take care of numbers!
	private int getQFIndex(int i, int j) {
		return size*(i - 1) + j;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Percolation p = new Percolation(3);
		p.open(1, 3);
		System.out.println(p.isFull(1, 3));
		p.open(2, 3);
		System.out.println(p.isFull(2, 3));
		p.open(3, 3);
		System.out.println(p.isOpen(1, 1));
		System.out.println(p.isOpen(1, 3));
		System.out.println(p.percolates());
	}

}
