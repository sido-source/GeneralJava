package graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
/*
Problem Overview
You are given a grid represented as a 2D matrix where:

- Each cell has a value of either 1 (open path), 0 (blocked path), or 44 (the starting point).
- You need to find the shortest path from the starting point (44) to any boundary of the grid,
where the boundary is defined as any cell along the perimeter of the grid that has a 0 value (indicating an exit).
- You should use Breadth-First Search (BFS) because it is optimal for finding the shortest path in an
unweighted graph-like structure, such as this grid.


Efficient Patterns to Use
- BFS for Shortest Path: Use BFS because it explores all nodes at the current depth level before
moving on to nodes at the next level. This guarantees that the first time you reach a boundary cell,
you have found the shortest path to it.

- Visited Tracking: Use a boolean 2D array visited to track cells you have already explored,
preventing infinite loops and reducing redundant processing.

- Boundary Conditions: Carefully handle boundary conditions to avoid accessing out-of-bounds indices in the grid.

- Queue for BFS: Use a queue to store the coordinates of cells to explore next. The BFS approach uses the
First-In-First-Out (FIFO) principle, so you add neighbors of a cell to the queue and process them in order.

 */
public class FindTheFastestExitWay {

    public static void main(String[] args) {

        // adjacency list
        int[][] grid = {
                {1, 0, 0, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1}
        };

        // start point
        int startX = 2;
        int startY = 2;

        // Call the BFS function to find the shortest path to the exit
        // we have to handle what function returns
        // - the function should find the shortest path
        // - return -1 in case if there are no solutions

        int shortestDistance = bfsToExit(grid, startX, startY);
        if (shortestDistance == -1) {
            System.out.println("No exit found!");
        } else {
            System.out.println("The shortest distance to an exit is: " + shortestDistance);
        }
    }

    private static int bfsToExit(int[][] grid, int startX, int startY) {
        // Directions for moving: right, left, down, up
        int[][] directions = {
                {0, 1},   // right
                {0, -1},  // left
                {1, 0},   // down
                {-1, 0}   // up
        };

        // Visited array to keep track of explored cells
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[startX][startY] = true;

        // Queue to perform BFS, storing the coordinates and current distance
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(new Coordinate(startX, startY, 0));  // Starting point with distance 0

        // Perform BFS
        while (!queue.isEmpty()) {
            // Poll the next cell to explore
            Coordinate current = queue.poll();

            // Check if the current cell is on the boundary and is an exit (value 1)
            // if we find the value then 
            if (isBoundary(current.x, current.y, grid) && grid[current.x][current.y] == 1) {
                return current.distance;  // Shortest distance to an exit
            }

            // Explore all 4 neighbors
            // And put on the queue these elements which meet the criteria
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // Check if the neighbor is :
                // 1) within bounds
                // 2) not visited
                // 3) "1" as we can only

                if (isValid(newX, newY, grid) && !visited[newX][newY] && grid[newX][newY] != 0) {
                    visited[newX][newY] = true;  // Mark as visited
                    queue.add(new Coordinate(newX, newY, current.distance + 1));  // Add to queue with updated distance
                }
            }
        }
        return -1;  // No exit found
    }

    // Helper method to check if a cell is within grid bounds
    private static boolean isValid(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    // Helper method to check if a cell is on the boundary of the grid
    private static boolean isBoundary(int x, int y, int[][] grid) {
        return x == 0 || x == grid.length - 1 || y == 0 || y == grid[0].length - 1;
    }



    // Coordinate class to store x, y, and the distance from the start
    static class Coordinate {
        int x, y, distance;

        public Coordinate(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
