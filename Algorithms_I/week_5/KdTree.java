package kd_tree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private int size = 0;
	private Node root;
	
	private static class Node {
		private Point2D p;
		//corresponding rectangle to this node
		private RectHV rect;
		private Node lb;
		private Node rt;
		
		public Node(Point2D p) {
			this.p = p;
		}

		public int compareX(Point2D p2) {
			if 		(p.x() > p2.x()) return 1;
			else if (p.x() < p2.x()) return -1;
			else 					 return 0;
		}
		
		public int compareY(Point2D p2) {
			if 		(p.y() > p2.y()) return 1;
			else if (p.y() < p2.y()) return -1;
			else 					 return 0;
		}
		
		public void setRect(RectHV rect) {
			this.rect = rect;
		}
		
		public RectHV getRect() {
			return this.rect;
		}
	}
	
	//create an empty tree with one root node
	public KdTree() {

	}
	
	//whether the set is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	//number of points in the set
	public int size() {
		return size;
	}
	
	// add the point to the set (if it is not already in the set) --- similar to BST
	public void insert(Point2D p) {
		if (p == null) {
			throw new NullPointerException("invalid argument!");
		}

		if (root == null) {
			root = new Node(p);
			root.setRect(new RectHV(0.0, 0.0, 1.0, 1.0));
			size++;
		} else {
			if (root.compareX(p) > 0) {
				insert(root.lb, root, p,false, true);
			} else if (root.compareX(p) < 0) {
				insert(root.rt, root, p,false, false);
			} else {
				return;
			}
		}
	}
	
	private void insert(Node child, Node parent, Point2D p, boolean isEven, boolean isLb) {
		//if already reach the leaf -- just create a new node
		if (child == null) {
			Node newNode = new Node(p);
			RectHV parentRect = parent.getRect();
			RectHV rect = null;
			//if is in the left side --- set parent's lb to new Node
			if (isLb && isEven) {
				parent.lb = newNode;
				rect = new RectHV(parentRect.xmin(), parentRect.ymin(), 
						parentRect.xmax(), parent.p.y());
				newNode.setRect(rect);
			} else if (isLb && !isEven){
				parent.lb = newNode;
				rect = new RectHV(parentRect.xmin(), parentRect.ymin(), 
						parent.p.x(), parentRect.ymax());
				newNode.setRect(rect);
			} else if (!isLb && isEven) {
				parent.rt = newNode;
				rect = new RectHV(parentRect.xmin(), parent.p.y(),
						parentRect.xmax(), parentRect.ymax());
				newNode.setRect(rect);
			} else {
				parent.rt = newNode;
				rect = new RectHV(parent.p.x(), parentRect.ymin(),
						parentRect.xmax(), parentRect.ymax());
				newNode.setRect(rect);
			}
			size++;
		}
		
		//using compare method to avoid duplicate codes!!
		int cmp = 0;
		if (!isEven) {
			cmp = child.compareY(p);
		} else {
			cmp = child.compareX(p);
		}
		
		//if the node's p is smaller than new node's p --- go right
		if (cmp < 0) {
			insert(child.rt, child, p,!isEven, false);
		} else if (cmp > 0) {
			insert(child.lb, child, p,!isEven, true);
		} else {//if node's p is equal to new node's p -- just return
			return;
		}

	}
	
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new NullPointerException("invalid argument!");
		}
		if (root == null) {
			return false;
		} else {
			if (root.compareX(p) > 0) {
				return contains(root.lb, p, false);
			} else if (root.compareX(p) < 0) {
				return contains(root.rt, p, false);
			} else {
				return true;
			}
		}
	}
	
	private boolean contains(Node node, Point2D p, boolean isEven) {
		if (node == null) {
			return false;
		} else {
			int cmp = 0;
			if (!isEven) {
				cmp = node.compareY(p);
			} else {
				cmp = node.compareX(p);
			}
			
			if (cmp > 0) {
				return contains(node.lb, p, !isEven);
			} else if (cmp < 0) {
				return contains(node.rt, p, !isEven);
			} else {
				return true;
			}
		}
	}
	
	//draw all points using standard draw
	public void draw() {
		if (root == null) {
			return;
		}
		//first draw the root point -- and a vertical line
		Point2D point = root.p;
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius();
		StdDraw.line(point.x(), 0, point.x(), 1);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(point.x(), point.y());
		StdDraw.show();
		//then draw children 
		draw(root.lb, false);
		draw(root.rt, false);
	}
	
	private void draw(Node node, boolean isEven) {
		RectHV rect = node.getRect();
		Point2D point = node.p;
		//first draw the point
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(point.x(), point.y());

		//if it is in odd layer --- draw horizontal line
		if (!isEven) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setPenRadius();
			StdDraw.line(rect.xmin(), point.y(), rect.xmax(), point.y());
		} else {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius();
			StdDraw.line(point.x(), rect.ymin(), point.x(), rect.ymax());
		}
		StdDraw.show();
		draw(root.lb, !isEven);
		draw(root.rt, !isEven);
		
	}
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException("invalid argument!");
		}
		if (root == null) {
			return null;
		}
		//create a set to obtain all intersect points
		SET<Point2D> range = new SET<Point2D>();
		range(root, rect, range);
		return range;
	}
	
	private void range(Node node, RectHV rect, SET<Point2D> range) {
		if (rect.contains(node.p)) {
			range.add(node.p);
		}
		if (node.lb != null && node.lb.getRect().intersects(rect)) {
			range(node.lb, rect, range);
		}
		if (node.rt != null && node.rt.getRect().intersects(rect)) {
			range(node.rt, rect, range);
		}
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException("Null query point!");
		}
		if (root == null) {
			return null;
		}
		
		double minDist = Double.POSITIVE_INFINITY;
		
		return nearest(root, p, minDist, true);
	}
	
	private Point2D nearest(Node node, Point2D p, double minDist, boolean isEven) {
		double tempDist = node.p.distanceSquaredTo(p);
		Point2D nearest = null;
		if (tempDist < minDist) {
			nearest = node.p;
			minDist = tempDist;
		}
		
		Point2D lNearest = null, rNearest = null;
		
		if(isEven && p.x() < node.p.x()
				|| !isEven && p.y() < node.p.y()) {
			if (node.lb != null) {
				lNearest = nearest (node.lb, p, minDist, !isEven);
				if (lNearest != null) {
					minDist = p.distanceSquaredTo(lNearest);
					nearest = lNearest;
				}
			}
			
			if (node.rt != null) {
				double rtToP = node.rt.getRect().distanceSquaredTo(p);
				if (rtToP <= minDist) {
					rNearest = nearest(node.rt, p, minDist, !isEven);
					if (rNearest != null) {
						minDist = p.distanceSquaredTo(rNearest);
						nearest = rNearest;
					}
				}
			}
		} else { 
			if (node.rt != null) {
				rNearest = nearest (node.rt, p, minDist, !isEven);
				if (rNearest != null) {
					minDist = p.distanceSquaredTo(rNearest);
					nearest = rNearest;
				}
			}
			
			if (node.lb != null) {
				double lbToP = node.lb.getRect().distanceSquaredTo(p);
				if (lbToP <= minDist) {
					lNearest = nearest(node.lb, p, minDist, !isEven);
					if (lNearest != null) {
						minDist = p.distanceSquaredTo(lNearest);
						nearest = lNearest;
					}
				}
			}
		}
		return nearest;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
