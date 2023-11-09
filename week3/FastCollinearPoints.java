// Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.
// - Think of p as the origin.
// - For each other point q, determine the slope it makes with p.
// - Sort the points according to the slopes they makes with p.
// - Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
// Applying this method for each of the n points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.

// The method segments() should include each maximal line segment containing 4 (or more) points exactly once. For example, if 5 points appear on a line segment in the order p→q→r→s→t, then do not include the subsegments p→s or q→t.

// Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the array is null, or if the argument to the constructor contains a repeated point.

// Performance requirement. The order of growth of the running time of your program should be n2 log n in the worst case and it should use space proportional to n plus the number of line segments returned. FastCollinearPoints should work properly even if the input has 5 or more collinear points.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
   private final List<LineSegment> segments = new ArrayList<>();
   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points) {
      if (points == null || Arrays.asList(points).contains(null)) {
         throw new IllegalArgumentException("Argument to FastCollinearPoints is null or contains null points");
      }
      List<Point> pointList = Arrays.asList(points);
      if (pointList.size() != pointList.stream().distinct().count()) {
         throw new IllegalArgumentException("Argument to FastCollinearPoints contains a repeated point");
      }
   }

   // the number of line segments
   public int numberOfSegments() {
      return segments.size();
   }

   // the line segments
   public LineSegment[] segments() {
      return segments.toArray(new LineSegment[0]);
   }
}

