package postman.com;

/**
 * Created by haoxu on 6/6/17.
 * A simple class to represent the point in map
 *
 */
public class Point {

    /**
     * the x coordinate of point
     */
    private int x;
    /**
     * the y coordinate of point
     */
    private int y;

    /**
     * empty constructor
     */
    public Point () {
    }

    /**
     * instantiate a point with x and y
     * @param s_x
     * @param s_y
     */
    public Point (int s_x, int s_y) {
        this.x = s_x;
        this.y = s_y;
    }

    /**
     * @return the x coordinate of the point
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate of the point
     */
    public int getY() {
        return y;
    }

    /**
     * @param b the other point
     * @return if the point is equal to the other point
     */
    public boolean equal(Point b) {
        return this.x == b.getX() && this.y == b.getY();
    }
}
