package PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	//creating a collection to store all 4-points line segments
	private ArrayList<LineSegment> ls;
	
	public FastCollinearPoints(Point[] points) {
		//error-check 1
		if (points == null) {
			throw new NullPointerException("null points list!");
		}
		
		int n = points.length;
		ls = new ArrayList<LineSegment>();
		
		//error-check 2
		for (int i = 0; i < n; i++) {
			if (points[i] == null) {
				throw new NullPointerException("null point!");
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (points[j] == points[i]) {
					throw new IllegalArgumentException("repeated points!");
				}
			}
		}
		
		//error-checking variable
		Point lastpoint = null;
		
		//very essential step---make sure that the points are in order for selecting the original one
		Arrays.sort(points);
		
		Point[] sortedPoints = new Point[n];
		
		for (int i = 0; i < n; i++) {
			//pick up the original point
			Point p = points[i];
			
			//using auxiliary array to store sorted points
			System.arraycopy(points, 0, sortedPoints, 0, sortedPoints.length);
			
			//sort the array wrt the slope order 
			Arrays.sort(sortedPoints, i, n, p.slopeOrder());
			
			//make decision
			//first, get the first slope in the sorted points
			double lastSlope = p.slopeTo(sortedPoints[i]);
			
			//second, do if-else to determine whether there is more than 2 slopes equal to that slope
			//create controller in the array
			int lo = 0;
			int hi = 0;
			for (int k = i + 1; k < n; k++) {
				double currentSlope = p.slopeTo(sortedPoints[k]);
				
				//if the next slope value is equal to last one, check the next next slope until find 4 equal slope points
				if (currentSlope == lastSlope) {
					hi++;
				} else {//store the line segment into collection
					if (hi - lo >= 3 && sortedPoints[hi] != lastpoint) {
						lastpoint = sortedPoints[hi];
						
						ls.add(new LineSegment(sortedPoints[lo], sortedPoints[hi]));
					}
					
					//re-initialized all variables
					lo = k;
					hi = k;
					lastSlope = currentSlope;
				}
			}
			
			//check out whether the last several points are in same slope
			if (hi - lo >= 3 && sortedPoints[hi] != lastpoint) {
				lastpoint = sortedPoints[hi];
				
				ls.add(new LineSegment(sortedPoints[lo], sortedPoints[hi]));
			}
		}
	}

	public int numberOfSegments() {
		return ls.size();
	}
	
	public LineSegment[] segments() {
		return ls.toArray(new LineSegment[ls.size()]);
	}

	public static void main(String[] args) {
	    // read the N points from a file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    Point[] points = new Point[N];
	    for (int i = 0; i < N; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.show(0);
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	}
}
