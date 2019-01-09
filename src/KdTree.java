import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

    private Node root;

    private static class Node {
        private final Point2D point; // the point
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree
        private int size; // number of nodes in subtree including this

        private Node(Point2D p) {
            point = p;
            size = 1;
        }
    }

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        checkForNull(p, "Inserted point can't be null");
        if (root == null) {
            root = new Node(p);
        } else {
            if (p.x() < root.point.x()) {
                // go left
                Node leftNode = new Node(p);
                root.lb = leftNode;
                root.size++;
            } else {
                // go right
                Node rightNode = new Node(p);
                root.rt = rightNode;
                root.size++;
            }
        }
    }

    /**
     *
     * @param p
     * @param rootNode
     * @param rootLevel
     *            - <code>true<code> for X coordinate, <code>false</code> for Y
     *            coordinate
     * @return
     */
    private Node insertPoint(Point2D p, Node rootNode, boolean rootLevel) {
        if (rootNode == null) {
            Node newNode = new Node(p);
            return newNode;
        }
        if (rootNode.point.equals(p)) {
            return rootNode;
        } else {
            if (rootLevel) {
                // compare X
                if (rootNode.point.x() < p.x()) {
                    // go left X
                    if (rootNode.lb == null) {
                        Node newNode = new Node(p);
                        return newNode;
                    } else {
                        Node newNode = insertPoint(p, rootNode.lb, !rootLevel);

                    }
                } else {
                    // go right X
                    if (rootNode.rt == null) {
                        Node newNode = new Node(p);
                        return newNode;
                    } else {
                        insertPoint(p, rootNode.rt, !rootLevel);
                    }
                }
            } else {
                // compare Y
                if (rootNode.point.x() < p.x()) {
                    // go left X
                    if (rootNode.lb == null) {
                        Node newNode = new Node(p);
                        return newNode;
                    } else {
                        insertPoint(p, rootNode.lb, !rootLevel);
                    }
                } else {
                    // go right X
                    if (rootNode.rt == null) {
                        Node newNode = new Node(p);
                        return newNode;
                    } else {
                        insertPoint(p, rootNode.rt, !rootLevel);
                    }
                }
            }
        }

    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        checkForNull(p, "Contains point can't be null");
        return p.compareTo(root.point) == 0;
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