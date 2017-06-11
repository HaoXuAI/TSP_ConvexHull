package postman.com;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by haoxu on 6/10/17.
 */
public class PostmanTest {

    @Test
    public void test1() throws Exception {
        File file = new File("src/postman/com/input");
        Scanner scanner;
        int lineCount = 0;
        String[] lines = new String[100];
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines[lineCount] = scanner.nextLine();
                lineCount++;
            }
            int width = lines[0].length();
            char[][] inputs = new char[lineCount][width];

            System.out.println("the map is:");
            for (int i = 0; i < lineCount; i++) {
                for (int j = 0; j < width; j++) {
                    inputs[i][j] = lines[i].charAt(j);
                    System.out.print(inputs[i][j]);
                }
                System.out.println();
            }

            Postman postman = new Postman(inputs);
            int[][] res = postman.compute();

            System.out.print("The path is: ");
            for (int[] i : res) {
                System.out.print(Arrays.toString(i));
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find the file");
        }
    }

    @Test
    public void randomTest() {
        Random randomGenerator = new Random();
        int n = randomGenerator.nextInt(100);
        int m = randomGenerator.nextInt(100);
        char[][] inputs = new char[n][m];

        char[] forms = new char[]{'S', '.','.','.','.', '#', 'X', 'G'};
        boolean startSet = false;
        boolean goalSet = false;
        int begin = 0;
        int end = forms.length;
        int xCount = 0;
        int wallCount = 0;

        Arrays.fill(inputs[0], '#');
        Arrays.fill(inputs[n - 1], '#');

        for (int i = 1; i < n - 1; i++) {
            inputs[i][0] = '#';
            inputs[i][m - 1] = '#';
            for (int j = 1; j < m - 1; j++) {
                if (startSet) begin = 1;
                if (goalSet) end = 7;
                if (xCount >= 18) end = 6;
                //if (wallCount >= 20) end = 4;
                int z = randomGenerator.nextInt(end - begin) + begin;
                if (forms[z] == 'S') startSet = true;
                if (forms[z] == 'G') goalSet = true;
                if (forms[z] == 'X') xCount++;
                if (forms[z] == '#') wallCount++;

                inputs[i][j] = forms[z];
            }
        }
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("the map is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(inputs[i][j]);
            }
            System.out.println();
        }

        Postman postman = new Postman(inputs);
        int[][] res = postman.compute();

        System.out.print("The path is: ");
        for (int[] i : res) {
            System.out.print(Arrays.toString(i));
        }
    }

}