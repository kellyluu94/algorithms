// Create an immutable data type Point that represents a point in the plane. The methods draw(), drawTo(), and toString() will be provided. Your job is to add the following components.
// - The compareTo() method should compare points by their y-coordinates, breaking ties by their x-coordinates. Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
// - The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as positive zero; treat the slope of a vertical line segment as positive infinity; treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
// - The slopeOrder() method should return a comparator that compares its two argument points by the slopes they make with the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
// - Do not override the equals() or hashCode() methods.

// Corner cases. To avoid potential complications with integer overflow or floating-point precision, you may assume that the constructor arguments x and y are each between 0 and 32,767.

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {}

    // draws the line segment from this point to that point
    public void drawTo(Point that) {}

    // string representation
    public String toString() {}

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
            return 1;
        } else {
            return 0;
        }
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }
        return (that.y - this.y) / (that.x - this.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return (point1, point2) -> {
            double slope1 = slopeTo(point1);
            double slope2 = slopeTo(point2);
            if (slope1 < slope2) {
                return -1;
            } else if (slope1 > slope2) {
                return 1;
            } else {
                return 0;
            }
        };
    }
}