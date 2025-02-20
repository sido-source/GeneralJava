package dp.minimumPathSum;

public class MinimumPathSum {

    //https://leetcode.com/problems/minimum-path-sum/description/


    public int minimumRecursion(int[][] grid) {
        return minimumRecursion(grid, 0, 0);
    }

    private int minimumRecursion(int[][] grid, int y, int x) {
        // Base case: if we reach the bottom-right corner, return its value
        if (y == grid.length - 1 && x == grid[0].length - 1) {
            return grid[y][x];
        }

        // Initialize path sums as "impossible" (maximum possible values)
        int rightPath = Integer.MAX_VALUE;
        int downPath = Integer.MAX_VALUE;

        // Check and calculate valid moves only
        if (x + 1 < grid[0].length) { // Move right
            rightPath = minimumRecursion(grid, y, x + 1);
        }
        if (y + 1 < grid.length) { // Move down
            downPath = minimumRecursion(grid, y + 1, x);
        }

        // Return current cell value + minimum of the valid paths
        return grid[y][x] + Math.min(rightPath, downPath);
    }


    public int minPathSum1Dp(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // 1D DP array
        int[] dp = new int[col];

        dp[0] = grid[0][0];

        // Initialize the first row
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i-1] + grid[0][i];
        }

        // Fill the dp array row by row
        for (int i = 1; i < row; i++) {
            dp[0] += grid[i][0]; // Update the first column
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }

        return dp[col-1];
    }


    public int minPathSum2DP(int[][] grid) {

        // standrd things
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];

        // init the dp 0 row
        // we base on prev(dp[0][i-1]) + current(grid[i])
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        // the same for the first row
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // now I think the order doesnt metter so much
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // take the smaller value from dp
                int min = Math.min(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = min + grid[i][j];
            }
        }

        return dp[row-1][col-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1,2,3},
                {4,2,3},
                {3,1,5}
                };

        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println(minimumPathSum.minimumRecursion(grid));
    }
}
