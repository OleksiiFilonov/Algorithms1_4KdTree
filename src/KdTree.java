import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

    private SET<Point2D> set;

    // construct an empty set of points
    public KdTree() {
        set = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        checkForNull(p, "Inserted point can't be null");
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        checkForNull(p, "Contains point can't be null");
        return set.contains(p);
    }

    private void checkForNull(Object p, String message) {
        if (p == null)
            throw new NullPointerException(message);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point2d : set) {
            point2d.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        checkForNull(rect, "Rectungular can't be null");
        List<Point2D> inside = new ArrayList<>();
        for (Point2D point2d : set) {
            if (rect.contains(point2d)) {
                inside.add(point2d);
            }
        }
        return inside;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        checkForNull(p, "Nearest point can't be null");
        Point2D nearestPoint = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Point2D point2d : set) {
            double distanceSquaredTo = p.distanceSquaredTo(point2d);
            if (distanceSquaredTo < nearestDistance) {
                nearestDistance = distanceSquaredTo;
                nearestPoint = point2d;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}