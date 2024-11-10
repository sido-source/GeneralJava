package arrays.matrix;

public class MaxAreaOfIslands {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j, visited));
                }
            }
        }
        return maxArea; // Make sure to return maxArea
    }

    int dfs(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0 || visited[row][col]) {
            return 0; // Base case: out of bounds or already visited or water
        }

        visited[row][col] = true; // Mark the cell as visited
        int area = 1; // Initial area for the current cell

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        for (int[] dir : directions) {
            area += dfs(grid, row + dir[0], col + dir[1], visited); // Accumulate area
        }

        return area; // Return the total area of the island
    }
}
