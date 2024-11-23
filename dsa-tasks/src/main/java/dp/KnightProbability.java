package dp;

public class KnightProbability {

    public double knightProbability(int n, int k, int row, int column) {
        // Define all 8 possible knight moves
        int[][] directions = {
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
        };

        // Initialize DP array for current and previous moves
        double[][][] dp = new double[k + 1][n][n];
        dp[0][row][column] = 1; // Base case: knight starts at (row, column)

        // Iterate over the number of moves
        for (int move = 1; move <= k; move++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    // Transition: calculate probability for dp[move][r][c]
                    for (int[] direction : directions) {
                        int prevR = r - direction[0];
                        int prevC = c - direction[1];
                        if (prevR >= 0 && prevR < n && prevC >= 0 && prevC < n) {
                            dp[move][r][c] += dp[move - 1][prevR][prevC] / 8.0;
                        }
                    }
                }
            }
        }

        // Sum up probabilities for all cells after k moves
        double result = 0.0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                result += dp[k][r][c];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        KnightProbability kp = new KnightProbability();
        System.out.println(kp.knightProbability(3, 2, 0, 0)); // Output: 0.0625
        System.out.println(kp.knightProbability(1, 0, 0, 0)); // Output: 1.0
    }
}

