package convexhull.com;

/**
 * Created by haoxu on 6/8/17.
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

    public boolean equal(postman.com.Point b) {
        return this.x == b.getX() && this.y == b.getY();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(',');
        sb.append(y);
        return sb.toString();
    }

    public int orientation(Point q, Point r) {
        return  (q.getY() - this.y) * (r.getX() - q.getX()) -
                (r.getY() - q.getY()) * (q.getX() - this.x);
    }

    public int dist(Point b) {
        return (this.x - b.getX()) * (this.x - b.getX()) +
                (this.y - b.getY()) * (this.y - b.getY());
    }
}
