package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	//create the times for experiment;
	private int time;
	
	//percolation threshold for each experiment in an array
	private double [] threshold;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if ( N <= 0 || T <= 0 ) {
			throw new IllegalArgumentException("Given number is not positive");
		}
		
		threshold = new double [T];
		
		time = T;//for we have to use times later on
		
		//for each experiment--create a new Percolation object and do open sites until we get percolates
		for (int i = 0; i < T; i++) {
			int count = 0;//used to count the number of opened sites
			
			Percolation p = new Percolation(N);
			while (!p.percolates()) {
				int row = StdRandom.uniform(N) + 1;
				int cln = StdRandom.uniform(N) + 1;
				
				if (!p.isOpen(row, cln)) {
					p.open(row, cln);
					count++;
				}
			}
			
			//calculate the threshold ratio after finished 
			threshold[i] = (double)count / (N*N);
		}
	}
	
	public double mean() {
		return StdStats.mean(threshold);
	}
	
	public double stddev() {
		return StdStats.stddev(threshold);
	}
	
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(time));
	}
	
	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(time));
	}
	
	public static void main(String[] args) {
		 PercolationStats ps=new PercolationStats(300,1000); 
	       StdOut.print("mean = "+ps.mean()+"\n");
	       StdOut.print("std dev = "+ps.stddev()+"\n");
	       StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());

	}

}
