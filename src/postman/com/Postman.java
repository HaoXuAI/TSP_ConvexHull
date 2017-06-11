package postman.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoxu on 6/10/17.
 */
public class Postman {

    private static final int[] DIRECTION_X = {0, 0, 1, -1};
    private static final int[] DIRECTION_Y = {1, -1, 0, 0};

    private char[][] inputs;

    public Postman(char[][] inputs) {
        this.inputs = inputs;
    }

    public int[][] compute() {

        /* step 1, initiate environments */
        if (inputs == null || inputs.length == 0) return null;

        int n = inputs.length;
        int m = inputs[0].length;

        Point start;
        Point goal = new Point();
        List<Point> allPoints = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inputs[i][j] == 'S') {
                    start = new Point(i, j);
                    allPoints.add(0, start);
                }
                if (inputs[i][j] == 'G') {
                    goal = new Point(i, j);
                }
                if (inputs[i][j] == 'X') {
                    allPoints.add(new Point(i, j));
                }
            }
        }

        allPoints.add(goal);

        /* step 2, find the shortest path for each pair of points using BFS */
        ShortestPath shortestPath = new ShortestPath(inputs, n, m);
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
        tsp.computeTSP(dist, allPoints.size());

        ArrayList<Integer> idx = tsp.getRes();
        int path = tsp.getResult();
        System.out.println("the shortest distance is: " + path);

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
