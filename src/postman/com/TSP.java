package postman.com;

import java.util.ArrayList;

/**
 * Created by haoxu on 6/6/17.
 * for traveling salesman problem, solving by DP.
 */
public class TSP {
    /**
     * the shortest path
     */
    private ArrayList<Integer> path;

    private int N;
    private int nPow;
    private int[][] g;
    private int[][] p;
    private int[][] d;

    /**
     * the total distance of the path
     */
    private int distance;

    /**
     * @return the distance of the path
     */
    public int getDistance() {
        return distance;
    }

    /**
     * build the constructor
     */
    public TSP() {
        this.path = new ArrayList<>();
        this.distance = 0;
    }

    /**
     * @param inputArray input map
     * @param n total number of all positions
     * @return the path
     */
    public ArrayList<Integer> computeTSP(int[][] inputArray, int n) {
        N = n;
        nPow = (int) Math.pow(2, n);
        g = new int[n][nPow];
        p = new int[n][nPow];
        d = inputArray;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nPow; j++) {
                g[i][j] = -1;
                p[i][j] = -1; }
        }
        //initialize based on distance matrix
        for (int i = 0; i < n; i++) {
            g[i][0] = inputArray[i][0];
        }

        distance = tsp(0, nPow - 2);
        path.add(0);
        getPath(0, nPow - 2);
        return path;
    }

    /**
     * @param start the start position
     * @param set the end point pos in pow
     */
    private int tsp(int start, int set) {
        int masked, mask, result = -1, temp;

        if (g[start][set] != -1) {
            return g[start][set];
        } else {
            for (int x = 0; x < N; x++) {
                mask = nPow - 1 - (int) Math.pow(2, x);
                masked = set & mask;

                if (masked != set) {
                    temp = d[start][x] + tsp(x, masked);
                    if (result == -1 || result > temp) {
                        result = temp;
                        p[start][set] = x;
                    }
                }
            }
            g[start][set] = result;
            return result;
        }
    }

    /**
     * get the path
     * @param start the start position
     * @param set the end point position in pow
     */
    private void getPath(int start, int set) {
        if (p[start][set] == -1) {
            return;
        }
        int x = p[start][set];
        int mask = nPow - 1 - (int) Math.pow(2, x);
        int masked = set & mask;

        path.add(x);
        getPath(x, masked);
    }

}
