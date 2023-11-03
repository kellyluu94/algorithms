public class FastCollinearPoints {
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   public           int numberOfSegments()        // the number of line segments
   public LineSegment[] segments()                // the line segments
}

// The method segments() should include each maximal line segment containing 4 (or more) points exactly once. For example, if 5 points appear on a line segment in the order p→q→r→s→t, then do not include the subsegments p→s or q→t.

// Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the array is null, or if the argument to the constructor contains a repeated point.

// Performance requirement. The order of growth of the running time of your program should be n2 log n in the worst case and it should use space proportional to n plus the number of line segments returned. FastCollinearPoints should work properly even if the input has 5 or more collinear points.

