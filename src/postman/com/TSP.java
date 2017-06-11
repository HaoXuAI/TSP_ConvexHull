package postman.com;

import java.util.ArrayList;

/**
 * Created by haoxu on 6/6/17.
 */
public class TSP {
    private ArrayList<Integer> res;
    private int g[][], p[][], npow, N, d[][];

    private int result;

    public int getResult() {
        return result;
    }

    public ArrayList<Integer> getRes() {
        return res;
    }

    public TSP() {
        this.res = new ArrayList<>();
        this.result = 0;
    }

    public void computeTSP(int[][] inputArray, int n) {
        N = n;
        npow = (int) Math.pow(2, n);
        g = new int[n][npow];
        p = new int[n][npow];
        d = inputArray;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < npow; j++) {
                g[i][j] = -1;
                p[i][j] = -1; }
        }
        //initialize based on distance matrix
        for (int i = 0; i < n; i++) {
            g[i][0] = inputArray[i][0];
        }

        result = tsp(0, npow - 2);
        res.add(0);
        getPath(0, npow - 2);
    }

    private int tsp(int start, int set) {
        int masked, mask, result = -1, temp;

        if (g[start][set] != -1) {
            return g[start][set];
        } else {
            for (int x = 0; x < N; x++) {
                mask = npow - 1 - (int) Math.pow(2, x);
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

    private void getPath(int start, int set) {
        if (p[start][set] == -1) {
            return;
        }
        int x = p[start][set];
        int mask = npow - 1 - (int) Math.pow(2, x);
        int masked = set & mask;

        res.add(x);
        getPath(x, masked);
    }

}
