package points;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Chuikina Alena on 8.11.15.
 */
public class FastCollinearPoints {
    private Point [] points;
    private LineSegment [] lineSegments;
    private int pointNum = 0;
    private int segmentNum = 0;

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        this.points = points;
        if (points == null) {
            throw new NullPointerException("Argument is null");
        }

        pointNum = points.length;
        for (int  i= 0; i< pointNum; i++) {
            if (points[i] == null) {
                throw new NullPointerException("point is null");
            }
        }

    // ???  how long should the array be
        lineSegments = new LineSegment[2*pointNum];
        if (pointNum >= 4) {
            findCollinear();
        }
    }

    private void findCollinear(){
        for (int i = 0; i<pointNum; i++){
//          sort array according to slopes with the chosen point
            Point [] tempPoints = sortPoints(points[i], i);
            double [] slopes = new double [pointNum];

//          find line segments that go through p
            int counter = 0;                    //how many points have the same slope with p
            int startIndex = 0;
            double slope = Double.NEGATIVE_INFINITY;

            for (int j = 0; j < pointNum - i; j++) {
                slopes[j] = points[i].slopeTo(tempPoints[j]);
                if (slopes[j] == slope){
                    counter++;
                } else {
                    if (counter >= 3) {
                        addSegment(slope, tempPoints, startIndex, counter);
                    }
                    slope = slopes[j];
                    counter = 1;
                    startIndex = j;
                }
            }
//          check whether last points form a line segment
            if (counter >= 3) {
                addSegment(slope, tempPoints, startIndex, counter);
            }
        }
    }
    /**
     *  sort points with respect to chosen point p;
     */
    private Point [] sortPoints(Point p, int start){
        Point [] tempPoints = new Point[pointNum - start];
        for (int i = start; i< pointNum; i++){
            tempPoints [i - start] = points [i];
        }

        Comparator <Point> slopeComparator = p.slopeOrder();
        Arrays.sort(tempPoints, 0, pointNum-start, slopeComparator);
        return tempPoints;
    }

    /**
     *
     */
    private void addSegment(double slope, Point [] pointArray, int start, int number) {
        //     form temporary array of points-participants
        Point[] segmentPoints = new Point[number + 1];
        segmentPoints[0] = pointArray[0];
        for (int i = 1; i <= number; i++) {
            segmentPoints[i] = pointArray[i + start -1];
        }

//    find the first and the last point of the segment
        Point minPoint = segmentPoints[0];
        Point maxPoint = segmentPoints[0];
        for (int i = 1; i <= number; i++) {
            if (segmentPoints[i].compareTo(minPoint) < 0) {
                minPoint = segmentPoints[i];
            }
            if (segmentPoints[i].compareTo(maxPoint) > 0) {
                maxPoint = segmentPoints[i];
            }
        }

//      check whether the line segment is already in the list   ------- doesn't work
        LineSegment temp = new LineSegment(minPoint, maxPoint);
        boolean alreadyExists = false;
        for (int i = 0; i < segmentNum; i++) {
            if (lineSegments[i].equals(temp)) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists){
            lineSegments[segmentNum] = new LineSegment(minPoint, maxPoint);
            segmentNum++;
        }
    }

    public int numberOfSegments() {       // the number of line segments
        return segmentNum;
    }

    public LineSegment[] segments() {                // the line segments
//      copy segments in new final array
        LineSegment [] lineSegmentsFinal = new LineSegment[segmentNum];
        for (int i = 0; i< segmentNum; i++){
            lineSegmentsFinal[i] =  lineSegments [i];
        }
        return lineSegmentsFinal;
    }
}
