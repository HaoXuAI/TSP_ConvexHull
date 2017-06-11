package postman.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoxu on 6/10/17.
 * the class going through all checkpoints from start to goal
 */
public class Postman {

    /**
     * the points representing the location status
     */
    private char[][] points;

    /**
     * Construct the class with input map
     * @param inputs the input points
     */
    public Postman(char[][] inputs) {
        this.points = inputs;
    }

    /**
     * @return the shortest path from start to goal passing all checkpoints
     */
    public int[][] compute() {

        /* step 1, initiate environments */
        if (points == null || points.length == 0) return null;

        int n = points.length;
        int m = points[0].length;

        Point start;
        Point goal = new Point();
        List<Point> allPoints = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (points[i][j] == 'S') {
                    start = new Point(i, j);
                    allPoints.add(0, start);
                }
                if (points[i][j] == 'G') {
                    goal = new Point(i, j);
                }
                if (points[i][j] == 'X') {
                    allPoints.add(new Point(i, j));
                }
            }
        }

        allPoints.add(goal);

        /*
         * step 2, find the shortest dist for each pair of points of
         * start point, checkpoints, and end point using BFS
         */
        ShortestPath shortestPath = new ShortestPath(points, n, m);
        int num = allPoints.size();
        int[][] dist = new int[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                Point a = allPoints.get(i);
                Point b = allPoints.get(j);
                dist[i][j] = shortestPath.getPath(a, b);
            }
        }

        /* step 3, solve TSP problem */

        dist[num - 1][0] = 0; // to make end node as dummy of start node.
        TSP tsp = new TSP();
        ArrayList<Integer> idx = tsp.computeTSP(dist, allPoints.size());
        
        int distance = tsp.getDistance();
        System.out.println("the shortest distance is: " + distance);

        int[][] res = new int[num][2];
        int j = 0;
        for (int i : idx) {
            Point p = allPoints.get(i);
            res[j][0] = p.getX();
            res[j][1] = p.getY();
            j++;
        }
        return res;
    }
}
