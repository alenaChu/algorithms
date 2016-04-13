package points;
import edu.princeton.cs.algs4.*;

/**
 * takes the name of an input file as a command-line argument;
 * read the input file ;
 * prints to standard output the line segments that are discovered, one per line;
 * and draws to standard draw the line segments
 */
public class SampleClient {
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
        if (args.length <= 1 || args[1].equalsIgnoreCase("brute")) {
            BruteCollinearPoints collinear = new BruteCollinearPoints(points);
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        }
        else{
            FastCollinearPoints collinear = new FastCollinearPoints(points);
  /*        LineSegment [] segment = collinear.segments();
            for (int i = 0; i< collinear.numberOfSegments(); i++){
                StdOut.println(segment[i]);
                segment[i].draw();
            }
*/
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        }
    }
}
