package Puzzle8;

import java.util.Stack;

public class Board {
	private final int[][] blocks;
	private final int n;//dimension
	
	public Board(int[][] blocks) {
		int x = blocks[0].length;
		int y = blocks.length;

		if (x != y) {
			throw new IllegalArgumentException("have to be N by N board!");
		}
		
		n = x;
		this.blocks = new int[n][n];
		
		//copy the given board into the class
		copy(blocks, this.blocks);
	}
	
	private void copy(int[][] origin, int[][] copy) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] < 0 || blocks[i][j] > (n*n - 1)) {
					throw new IllegalArgumentException("have to between 0 and N^2 - 1!");
				}
				
				copy[i][j] = origin[i][j];
			}
		}
	}
	
	public int dimension() {
		return n;
		
	}
	
	private int getIndexAt(int x, int y) {
		return y + x * n + 1;
	}
	
	//number of blocks which in the wrong places
	public int hamming() {
		int count = 0;
		
		//check whether the block value equals to the modified index value except for 0
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int index = getIndexAt(i, j);
				int value = blocks[i][j];
				if (value != index && (value != 0)) {//the place of 9 is not important -- still work!
					count++;
				} 
			}
		}
		
		return count;
		
	}
	
	//return the sum of distance from wrong place for each block
	public int manhattan() {
		int sum = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int index = getIndexAt(i, j);
				int value = blocks[i][j];
				
				//if in wrong place -- find the initial position and deduct them!! -- very brilliant approach
				if (value != 0 && value != index) {
					int initialX = (value - 1) / n;
					int initialY = value - 1 - initialX * n;
					int distance = Math.abs(initialX - i) + Math.abs(initialY - j);
					sum += distance;
				}
			}
		}
		
		return sum;
		
	}
	
	public boolean isGoal() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int index = getIndexAt(i, j);
				int value = blocks[i][j];
				if (value != 0 && value != index) {//index of 9 is NOT important!!
					return false;
				}
			}
		}
		return true;
		
	}
	
	public Board twin() {
		Board twins = new Board(blocks);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (twins.blocks[i][j] != 0 && twins.blocks[i][j + 1] != 0) {
					swap(i, j, i, j + 1, twins.blocks);
					return twins;
				}
				
			}
		}
		return twins;
		
	}
	
	private boolean swap(int i, int j, int it, int jt, int[][] blocks) {
        if (it < 0 || it >= n || jt < 0 || jt >= n) {
            return false;
        }
        
		int temp = blocks[i][j];
		blocks[i][j] = blocks[it][jt];
		blocks[it][jt] = temp;
		return true;
	}
	
	public boolean equals(Object y) {
		if (y == this) return true;
		
		if (y == null) return false;
		
		if (y.getClass() != this.getClass()) return false;
		
		Board that = (Board) y;
		
		//check if they have same length
		if (this.n != that.n) {
			return false;
		}
		
		//check whether they have same value
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.blocks[i][j] != that.blocks[i][j]) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	//find out all neighboring boards 
	public Iterable<Board> neighbors() {
		//first, have to find the place of value 0
		int i0 = 0, j0 = 0;
		boolean found = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					i0 = i;
					j0 = j;
					found = true;
				}
			}
		}
		
		//then, create a stack to store all neighbor boards
		Stack<Board> stack = new Stack<Board>();
		//if have left node. create a new one and swap the left node with 0, add it to the satck
		if (i0 - 1 >= 0 && j0 >= 0) {
			Board left = new Board(blocks);
			swap(i0, j0, i0 - 1, j0, left.blocks);
			stack.push(left);
		}
		
		//if have right node, as above
		if (i0 + 1 < n && j0 >= 0) {
			Board right = new Board(blocks);
			swap(i0, j0, i0 + 1, j0, right.blocks);
			stack.push(right);
		}
		
		//for top node
		if (i0 >= 0 && j0 - 1 >= 0) {
			Board top = new Board(blocks);
			swap(i0, j0, i0, j0 - 1, top.blocks);
			stack.push(top);
		}
		
		//for bottom node
		if (i0 >= 0 && j0 + 1 < n) {
			Board bottom = new Board(blocks);
			swap(i0, j0, i0, j0 + 1, bottom.blocks);
			stack.push(bottom);
		}
		
		return stack;
		
	}
	
	//string representation of board
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
		
	}
	
	public static void main (String[] args) {

	}

}
