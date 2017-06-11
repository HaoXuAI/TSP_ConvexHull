# TSP_ConvexHull

## User Manual

This is a challenge solving two problems, a modified travelling salesman problem and a convex hull computation problem in Java.

The main file is under src/postman/com and src/convexhull/com

To run the test, please go to each directory, using IDE or simply compile in terminal, supposing you are using junit4.12:
````
java -cp path/to/your/junitlib/junit4-4.12.jar PostmanTest
javac -cp junit-4.12.jar:. *.java
java -cp junit-4.12.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore PostmanTest
````
## 1. Postman

A postman is delivering mails in a square-shaped town. He starts from the start (S) and would arrive at the goal (G). On the way, he has to deliver mails at some checkpoints (X).
One possible configuration of the town is like:
````
########
#...G.X#
#.##.X.#
#S.X...#
########
````
Calculate the minimum distance the postman has to travel from the start to the goal while passing all the checkpoints.
Remarks:

'S' = start point

'G' = goal point

'X' = checkpoint

'.' = open-block that the postman can pass

'#' = closed-block that the postman cannot pass

- A movement means a vertical or horizontal step (up, down, left or right)
- Other types of movements, such as moving diagonally, or skipping one or more blocks, are NOT allowed.
- The postman cannot get out of the map.
- Distance is defined as the number of movements
- The postman CAN pass opened-blocks, checkpoints, the start, and the goal more than once if necessary.
- You can assume 1<=width<=100, 1<=height<=100.
- The maximum number of checkpoints is 18.
- Return -1 if given arguments do not satisfy specifications, or players cannot arrive at the goal from the start by passing all the checkpoints.

### Method
I'm dividing this problem into two steps:
1. find all pair position distance between start, checkpoints and goal using BFS.
2. re-construct the map with points derived from step 1, and then solving typical TSP problem using dynamic programming.




## 2. ConvexHull

1. Given a set of 2D points, compute the convex hull. (Convex hull is the smallest convex polygon containing
the points).
2. Given some more points, find if they are inside the convex hull

### Method

1. GrahamScan Algorithm
    - First find the bottom left most point w.r.t y-coordinate.
    - Sort points by polor angle in counterclockwise order around the first point. if two points have same angle, then remove all same angle points except the point farthest from P0.
    - if the new array size is less than 3, return -1
    - Use stack to store the points, push first three points.
    - For each other point i:
        1. Keep removing points from stack while orientation of following 3 points is not counterclockwise (or they don’t make a left turn).
            - the second top point in stack
            - the top point in stack
            - point i the loop
        2. push point i in the stack
2. Using segments intersect
    1) Draw a horizontal line to the right of each point and extend it to infinity
    2) Count the number of times the line intersects with polygon edges.
    3) A point is inside the polygon if either count of intersections is odd or
       point lies on an edge of polygon.  If none of the conditions is true, then 
       point lies outside.


## Test
For both postman problem and convexhull problem, I have two input files named `input1.txt` and `input2.txt`, and a random generated map test.