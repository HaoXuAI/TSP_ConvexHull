package convexhull.com;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;


/**
 * Created by haoxu on 6/10/17.
 */
public class ConvexHullTest {

    private static void printList(ArrayList<Point> res) {
        for (Point r : res) {
            System.out.println(r.toString());
        }
    }

    private ArrayList<Point> readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<Point> points = new ArrayList<>();
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String[] point = scanner.nextLine().split(",");
                int x = Integer.parseInt(point[0]);
                int y = Integer.parseInt(point[1]);
                points.add(new Point(x, y));
            }
            return points;
        }
        throw new FileNotFoundException("Can not find the file");
    }

    @Test
    public void test1() {
        ArrayList<Point> points = null;
        try {
            points = readFile("src/convexhull/com/input1");
        } catch (FileNotFoundException e) {
            System.err.println("Please put correct input");
        } finally {
            if (points != null) {
                ConvexHull cH = new ConvexHull(points, points.size());
                ArrayList<Point> res = cH.create();

                Point p = new Point(5, 5);
                System.out.println("The convex hull is:");
                printList(res);
                System.out.print("Given Point: " + p.toString());
                System.out.println();
                if (cH.isInside(p)) {
                    System.out.println("it is inside the hull");
                } else {
                    System.out.println("it is not inside the hull");
                }
            }
        }
    }

    @Test
    public void test2() throws Exception {
        ArrayList<Point> points = null;
        try {
            points = readFile("src/convexhull/com/input2");
        } catch (FileNotFoundException e) {
            System.err.println("Please put correct input");
        } finally {
            if (points != null) {
                ConvexHull cH = new ConvexHull(points, points.size());
                ArrayList<Point> res = cH.create();

                Point p = new Point(3, 5);
                System.out.println("The convex hull is:");
                printList(res);
                System.out.print("Given Point: " + p.toString());
                System.out.println();
                if (cH.isInside(p)) {
                    System.out.println("it is inside the hull");
                } else {
                    System.out.println("it is not inside the hull");
                }
            }
        }
    }

    @Test
    public void randomTest() throws Exception {
        Random randomGenerator = new Random();
        int n = randomGenerator.nextInt(100);
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = randomGenerator.nextInt(100);
            int y = randomGenerator.nextInt(100);
            points.add(new Point(x, y));
        }

        ConvexHull cH = new ConvexHull(points, points.size());
        ArrayList<Point> res = cH.create();
        Point p = new Point(14, 22);
        System.out.println("The convex hull is:");
        printList(res);
        System.out.print("Given Point: " + p.toString());
        System.out.println();
        if (cH.isInside(p)) {
            System.out.println("it is inside the hull");
        } else {
            System.out.println("it is not inside the hull");
        }

    }


}