package convexhull.com;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by haoxu on 6/10/17.
 * Given a set of 2D points, this class is returning a convex hull
 * in array list with Point class to denote each point.
 */
public class ConvexHull {
    /**
     * p0 is the lowest point in the convex hull.
     */
    private Point p0;
    /**
     * points is the convex hull vertex.
     */
    private ArrayList<Point> points;

    /**
     * Total number of all input points.s
     */
    private int n;

    /**
     * @param points input points
     * @param n total number of points
     */
    public ConvexHull(ArrayList<Point> points, int n) {
        this.points = points;
        this.n = n;
    }

    /**
     * @return the convex hull vertexes.
     */
    public ArrayList<Point> create() {

        int yMin = points.get(0).getY();
        int min = 0;
        for (int i = 0; i < n; i++) {
            int y = points.get(i).getY();

            if ((y < yMin)
                    || (y == yMin && points.get(i).getX() < points.get(min).getY())) {
                yMin = y;
                min = i;
            }
        }

        swap(0, min);

        p0 = points.get(0);

        points.sort((o1, o2) -> {
            int ori = p0.orientation(o1, o2);
            if (ori == 0) {
                return p0.dist(o1) - p0.dist(o2);
            }
            return ori;
        });

        int m = 1;
        for (int i = 1; i < n; i++) {
            while (i < n - 1 && p0.orientation(points.get(i), points.get(i + 1)) == 0) {
                i++;
            }
            points.set(m, points.get(i));
            m++;
        }

        if (m < 3) return null;

        Stack<Point> s = new Stack<>();
        s.push(points.get(0));
        s.push(points.get(1));
        s.push(points.get(2));

        for (int i = 3; i < m; i++) {
            while (getSecTop(s).orientation(s.peek(), points.get(i)) >= 0) {
                s.pop();
            }
            s.push(points.get(i));
        }

        return new ArrayList<>(s);
    }

    /**
     * @param s the stack to store the points.
     * @return the second point in the stack.
     */
    private static Point getSecTop(Stack<Point> s) {
        Point top = s.pop();
        Point res = s.peek();
        s.push(top);
        return res;
    }

    /**
     * swap two points in field points.
     * @param i, first point index
     * @param j, second point index
     */
    private void swap(int i, int j) {
        Point temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }

    /**
     * @param p first point
     * @param q second point
     * @param r third point
     * @return if the points are on segment
     */
    private boolean onSegment(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) &&
                q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param p1 first point
     * @param q1 second point
     * @param p2 third point
     * @param q2 forth point
     * @return if the points intersect
     */
    private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = p1.orientation(q1, p2);
        int o2 = p1.orientation(q1, q2);
        int o3 = p1.orientation(q2, p1);
        int o4 = p1.orientation(q2, q1);

        if (o1 * o2 < 0 && o3 * o4 < 0) {
            return true;
        }

        if (o1 == 0 && onSegment(p1, p2,q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    /**
     * @param p input point
     * @return if the point is inside the convex hull
     */
    public boolean isInside(Point p) {
        if (n < 3) return false;

        Point ex = new Point(Integer.MAX_VALUE, p.getY());

        int count = 0, i = 0;

        do {
            int next = (i + 1) % n;

            if (doIntersect(points.get(i), points.get(next), p, ex)) {
                if (points.get(i).orientation(p, points.get(next)) == 0) {
                    return onSegment(points.get(i), p, points.get(next));
                }
                count++;
            }
            i = next;
        } while (i != 0);
        return count % 2 == 1;
    }
}
