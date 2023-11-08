// Brute force.
// Write a program BruteCollinearPoints.java that examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.

// The method segments() should include each line segment containing 4 points exactly once. If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.

// Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the array is null, or if the argument to the constructor contains a repeated point.

// Performance requirement. The order of growth of the running time of your program should be n4 in the worst case and it should use space proportional to n plus the number of line segments returned.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
   private final Point[] points;
   private final List<LineSegment> segments = new ArrayList<>();

   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) {
      if (points == null || Arrays.asList(points).contains(null)) {
         throw new IllegalArgumentException("Argument to BruteCollinearPoints is null or contains null points");
      }
      int n = points.length;
      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
               for (int l = k + 1; l < n; l++) {
                  Point p = points[i];
                  Point q = points[j];
                  Point r = points[k];
                  Point s = points[l];
                  if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                     Point[] collinearPoints = {p, q, r, s};
                     Arrays.sort(collinearPoints);
                     LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[3]);
                     segments.add(segment);
                  }
               }
            }
         }
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

