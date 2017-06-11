package postman.com;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by haoxu on 6/6/17.
 * ShortestPath class use BFS to compute the shortest distance
 * from start to goal.
 */
public class ShortestPath {

    /**
     * the direction represents up down left and right
     */
    private static final int[] DIRECTION_X = {0, 0, 1, -1};
    private static final int[] DIRECTION_Y = {1, -1, 0, 0};

    /**
     * the shortest path
     */
    private int[][] path;
    /**
     * the input map
     */
    private char[][] inputs;
    /**
     * the height of the map
     */
    private int n;
    /**
     * the width of the map
     */
    private int m;

    /**
     * build the path map constructor
     * @param inputs represent the map
     * @param n height of the map
     * @param m width of the map
     */
    public ShortestPath(char[][] inputs, int n, int m) {
        this.path = new int[n][m];
        this.inputs = inputs;
        this.n = n;
        this.m = m;
    }

    /**
     * Using BFS algorithm
     * @param start the start point
     * @param goal the end point
     * @return the shortest distance from start to goal
     */
    public int getPath(Point start, Point goal) {

        if (start.equal(goal)) return 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                path[i][j] = -1;
            }
        }

        Queue<Point> queue = new LinkedList<>();

        path[start.getX()][start.getY()] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = temp.getX() + DIRECTION_X[i];
                int y = temp.getY() + DIRECTION_Y[i];
                if (x == goal.getX() && y == goal.getY()) {
                    return 1 + path[temp.getX()][temp.getY()];
                }
                if (x >= 0 && y >= 0 && x < n && y < m
                        && inputs[x][y] != '#' && path[x][y] == -1) {
                    path[x][y] = 1 + path[temp.getX()][temp.getY()];
                    queue.add(new Point(x, y));
                }
            }
        }

        return -1;
    }
}
