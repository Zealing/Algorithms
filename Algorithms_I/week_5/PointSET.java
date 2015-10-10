package kd_tree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	//the SET data type already implements RED-BLACK tree!!--no need to do it again
	private SET<Point2D> points;
	
	//create an empty set of points
	public PointSET() {
		points = new SET<Point2D>();
	}

	//whether the set is empty
	public boolean isEmpty() {
		return points.isEmpty();
	}
	
	//number of points in the set
	public int size() {
		return points.size();
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) {
			throw new NullPointerException("invalid argument!");
		}
		points.add(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new NullPointerException("invalid argument!");
		}
		return points.contains(p);
	}
	
	//draw all points using standard draw
	public void draw() {
		for (Point2D p: points) {
			StdDraw.point(p.x(), p.y());
		}
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException("invalid argument!");
		}
		SET<Point2D> range = new SET<Point2D>();
		for (Point2D p: points) {
			if (rect.contains(p)) {
				range.add(p);
			}
		}
		return range;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new NullPointerException("invalid argument!");
		}
		if (points.isEmpty()) {
			return null;
		}
		
		Point2D minP = null;
		double distanceSq = Double.POSITIVE_INFINITY;
		for (Point2D setP: points) {
			double tempDis = setP.distanceSquaredTo(p);
			if (tempDis < distanceSq) {
				minP = setP;
				distanceSq = tempDis;
			}
		}
		return minP;
	}
	
	public static void main(String[] args) {
        
	}

}
