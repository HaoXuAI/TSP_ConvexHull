package postman.com;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by haoxu on 6/6/17.
 */
public class ShortestPath {

    private static final int[] DIRECTION_X = {0, 0, 1, -1};
    private static final int[] DIRECTION_Y = {1, -1, 0, 0};

    private int[][] dist;
    private char[][] inputs;
    private int n;
    private int m;

    public ShortestPath(char[][] inputs, int n, int m) {
        this.dist = new int[n][m];
        this.inputs = inputs;
        this.n = n;
        this.m = m;
    }

    public int getPath(Point start, Point goal) {

        if (start.equal(goal)) return 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = -1;
            }
        }

        Queue<Point> queue = new LinkedList<>();

        dist[start.getX()][start.getY()] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = temp.getX() + DIRECTION_X[i];
                int y = temp.getY() + DIRECTION_Y[i];
                if (x == goal.getX() && y == goal.getY()) {
                    return 1 + dist[temp.getX()][temp.getY()];
                }
                if (x >= 0 && y >= 0 && x < n && y < m
                        && inputs[x][y] != '#' && dist[x][y] == -1) {
                    dist[x][y] = 1 + dist[temp.getX()][temp.getY()];
                    queue.add(new Point(x, y));
                }
            }
        }

        return -1;
    }
}
