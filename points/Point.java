package points;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by Chuikina Alena on 8.11.15.
 * Class represents a 2D-point with integer coordidates
 */

public class Point implements Comparable<Point> {
    private final int x;                            //x-coordinate for this point
    private final int y;                            //y-coordinate for this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {                       // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public boolean equals(Object that){
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        Point p = (Point) that;
        if (x == p.x && y == p.y) return true;
        return false;
    }
    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertcal;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {       // the slope between this point and that point
        if (equals(that)) return Double.NEGATIVE_INFINITY;
        if (that.x == x) return Double.POSITIVE_INFINITY;
        if (that.y == y) return +0.0;
        return (double) (that.y - y)/(that.x - x);
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  p the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point p) {      // compare two points by y-coordinates, breaking ties by x-coordinates
        if (equals(p)) return 0;
        if (y < p.y) return -1;
        if (y > p.y) return  1;
        if (x < p.x) return -1;
        if (x > p.x) return  1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {             // compare two points by slopes they make with this point
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator <Point>{
        public int compare (Point p, Point q){
            double slopeP = slopeTo(p);
            double slopeQ = slopeTo(q);
            if (slopeP > slopeQ) return 1;
            if (slopeP < slopeQ) return -1;
            return 0;
        }
    }
}