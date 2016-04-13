package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by Chuikina Alena on 20.11.15.
 */
public class PointSET {

    private Node root;

    private class Node {
        private Point2D p;      // the point
        private Node left;        // the left subtree
        private Node right;        // the right/top subtree
        public Node(Point2D point){
            p = point;
            left = null;
            right = null;
        }
    }

    private SET<Point2D> pointSet;
    private int size = 0;

    public PointSET() {                              // construct an empty set of points
        pointSet = new SET <> ();
    }

    public boolean isEmpty() {                      // is the set empty?
        return pointSet.isEmpty();
    }

    // number of points in the set
    public int size()   {return pointSet.size();}

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {

        pointSet.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        return pointSet.contains(p);
    }
    public void draw() {                         // draw all points to standard draw

    }
    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle
        return new Stack<Point2D>();
    }
    public Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
        Node n = root;
        double minSquareDist = 0;
        while (n!=null){
            double tempDist = n.p.distanceSquaredTo(p);
            if (tempDist < minSquareDist) minSquareDist = tempDist;
            if (n.left != null) 

        }
        return new Point2D(0, 0);
    }

    public static void main(String[] args) {}                 // unit testing of the methods (optional)
}
