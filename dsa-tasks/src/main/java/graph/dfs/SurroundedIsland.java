package graph.dfs;

import java.util.Arrays;

public class SurroundedIsland {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        boolean[][] visited = new boolean[board.length][board[0].length];

        // Check y borders (left and right)
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0, visited);
            if (board[i][board[0].length - 1] == 'O') dfs(board, i, board[0].length - 1, visited);
        }

        // Check x borders (top and bottom)
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') dfs(board, 0, i, visited);
            if (board[board.length - 1][i] == 'O') dfs(board, board.length - 1, i, visited);
        }

        // Flip all unvisited 'O's to 'X' and restore 'T' to 'O'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfs(char[][] board, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != 'O' || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        board[row][col] = 'T'; // Temporarily mark this cell as part of a safe region

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] dir : directions) {
            dfs(board, row + dir[0], col + dir[1], visited);
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };

        SurroundedIsland island = new SurroundedIsland();
        island.solve(board);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
