package postman.com;

/**
 * Created by haoxu on 6/6/17.
 */
public class Point {

    private int x;
    private int y;

    public Point () {
    }

    public Point (int s_x, int s_y) {
        this.x = s_x;
        this.y = s_y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equal(Point b) {
        return this.x == b.getX() && this.y == b.getY();
    }
}
