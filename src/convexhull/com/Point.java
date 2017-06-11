package convexhull.com;

/**
 * Created by haoxu on 6/8/17.
 * The class represent a point
 */
public class Point {
    /**
     * x-axis coordinate
     */
    private int x;
    /**
     * y-axis coordinate
     */
    private int y;

    /**
     * an empty constructor
     */
    public Point () {
    }

    /**
     * initiate a point wit x and y
     * @param s_x
     * @param s_y
     */
    public Point (int s_x, int s_y) {
        this.x = s_x;
        this.y = s_y;
    }

    /**
     * @return the x coordinate of point
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate of point
     */
    public int getY() {
        return y;
    }

    /**
     * @param b the other point
     * @return if two points are equal
     */
    public boolean equal(postman.com.Point b) {
        return this.x == b.getX() && this.y == b.getY();
    }

    /**
     * @return the point in string format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(',');
        sb.append(y);
        return sb.toString();
    }

    /**
     * @param q the middle point
     * @param r the last point
     * @return the orientation of three points
     */
    public int orientation(Point q, Point r) {
        return  (q.getY() - this.y) * (r.getX() - q.getX()) -
                (r.getY() - q.getY()) * (q.getX() - this.x);
    }

    /**
     * @param b the other point
     * @return distance to the other point
     */
    public int dist(Point b) {
        return (this.x - b.getX()) * (this.x - b.getX()) +
                (this.y - b.getY()) * (this.y - b.getY());
    }
}
