package graph.multisource;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {

        public static int orangesRotting(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            boolean[][] visited = new boolean[rows][cols];
            Deque<int[]> queue = new ArrayDeque<>();
            int freshOranges = 0;

            // Initialize the queue with all rotten oranges
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) {
                        queue.add(new int[]{i, j, 0});  // row, col, time
                        visited[i][j] = true;
                    } else if (grid[i][j] == 1) {
                        freshOranges++;
                    }
                }
            }

            int minTime = 0;
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            while (!queue.isEmpty()) {
                int[] orange = queue.poll();
                int row = orange[0];
                int col = orange[1];
                int time = orange[2];
                minTime = time;

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < rows &&
                            newCol >= 0 && newCol < cols &&
                            !visited[newRow][newCol] &&
                            grid[newRow][newCol] == 1) {
                        queue.add(new int[]{newRow, newCol, time + 1});
                        visited[newRow][newCol] = true;
                        freshOranges--;
                    }
                }
            }

            return freshOranges == 0 ? minTime : -1; // Return -1 if there are fresh oranges left
        }



    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid)); // Output: 4
    }

}
