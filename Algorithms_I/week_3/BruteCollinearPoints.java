package PatternRecognition;

import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	//using arraylist or any other kinds of collection to store all 4-points segments
	private ArrayList<LineSegment> ls;
	
	public BruteCollinearPoints(Point[] points) {
		//error-check 1
		if (points == null) {
			throw new NullPointerException("null points list!");
		}
		
		ls = new ArrayList<LineSegment>();
		int n = points.length;
		
		//error-check 2
		for (int i = 0; i < n; i++) {
			if (points[i] == null) {
				throw new NullPointerException("null point!");
			}
		}
		//check whether the points array contains repeated points
		//pay attention to the corner number!
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (points[j] == points[i]) {
					throw new IllegalArgumentException("repeated points!");
				}
			}
		}
		
		//main loops
		for (int i = 0; i < n; i++) {
			Point p = points[i];
			
			//check whether the point is null
			if (p == null) {
				throw new NullPointerException("null point!");
			}
			for (int j = i + 1; j < n; j++) {
				Point q = points[j];
				if (q == null) {
					throw new NullPointerException("null point!");
				}
				for (int k = j + 1; k < n; k++) {
					Point r = points[k];
					if (r == null) {
						throw new NullPointerException("null point!");
					}
					
					//if the former three points are not in the same line---just skip the last check--save time
					if (p.slopeOrder().compare(q, r) != 0) {
						continue;
					}
					
					for (int l = k + 1; l < n; l++) {
						Point s = points[l];
						if (s == null) {
							throw new NullPointerException("null point!");
						}
						
						
						//if those slopes are the same--add this segment into the arraylist
						if ((p.slopeOrder().compare(q, r) == 0) && (p.slopeOrder().compare(r, s) == 0)) {
							ls.add(new LineSegment(p, s));
						}
					}
				}
			}
		}
	}
	
	//return the number of 4-points segements in the array list
	public int numberOfSegments() {
		return ls.size();
	}
	
	//return the array format of all 4-points segments
	public LineSegment[] segments() {
		//very awkward but it works
		return ls.toArray(new LineSegment[ls.size()]);
	}
	
	public static void main(String[] args) {
	    // read the N points from a file
//	    In in = new In(args[0]);
//	    int N = in.readInt();
	    Point[] points = new Point[5];
//	    for (int i = 0; i < N; i++) {
//	        int x = in.readInt();
//	        int y = in.readInt();
//	        points[i] = new Point(x, y);
//	    }
	    
	    points[0] = new Point(1000, 1000);
	    points[1] = new Point(2000, 2000);
	    points[2] = new Point(3000, 3000);
	    points[3] = new Point(4000, 4000);
	    points[4] = new Point(5000, 5000);

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
