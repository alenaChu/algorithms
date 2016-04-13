package points;
/**
 * Created by Chuikina Alena on 8.11.15.
 */
public class BruteCollinearPoints {
    private Point [] points;
    private LineSegment [] lineSegments;
    private int pointNum = 0;
    private int segmentNum = 0;

    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
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
    // !!!!   ????
        lineSegments = new LineSegment[pointNum];
        if (pointNum >= 4) {
            findCollinear();
        }
    }

/**
 *  proves all possible combinations of points of 4
 *  if they have the same slope - they are on the same line
 *  add the segment to the array
 */
    private void findCollinear(){
        for (int i = 0; i<pointNum; i++) {
            for (int j = i+1; j < pointNum; j++) {
                for (int k = j+1; k < pointNum; k++) {
                    for (int l = k+1; l < pointNum; l++) {

                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[l]);

                        if (slope1 == slope2 && slope2 == slope3) {
                            addSegment(points[i],points[j],points[k],points[l]);
                        }
                    }
                }
            }
        }
    }

    private void addSegment(Point p, Point q, Point r, Point s){
//     form temporary array of points-participants
        Point[] temp = new Point [4];
        temp[0] = p;
        temp[1] = q;
        temp[2] = r;
        temp[3] = s;

//    find the first and the last point of the segment
        Point minPoint = temp[0];
        Point maxPoint = temp[0];
        for (int i = 1; i< 4; i++) {
            if (temp[i].compareTo(minPoint) < 0){
                minPoint = temp[i];
            }
            if (temp[i].compareTo(maxPoint) > 0) {
                maxPoint = temp[i];
            }
        }

        lineSegments [segmentNum] = new LineSegment(minPoint, maxPoint);
        segmentNum ++;
    }

    public int numberOfSegments() {  // the number of line segments
        return segmentNum;
    }

    public LineSegment[] segments(){                // the line segments
//      copy segments in new final array
        LineSegment [] lineSegmentsFinal = new LineSegment[segmentNum];
        for (int i = 0; i< segmentNum; i++){
            lineSegmentsFinal[i] =  lineSegments [i];
        }
        return lineSegmentsFinal;
    }
}
