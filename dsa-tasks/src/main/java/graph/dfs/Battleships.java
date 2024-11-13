package graph.dfs;

public class Battleships {
    //https://leetcode.com/problems/battleships-in-a-board/
    //https://www.youtube.com/watch?v=wBG6078g1gE
    static public int countBattleships(char[][] board) {
        int res = 0;
        for (int i =0; i < board.length; i++) {
            for (int j=0; j < board[0].length; j++) {
                if (board[i][j]=='X') {
                    res++;
                    sink(board,i ,j);
                }
            }
        }

        return res;
    }

    static public void sink(char[][] board, int i, int j) {
        if (
                i < 0 || i >= board.length
                        || j < 0 || j >= board[0].length
                        || board[i][j] != 'X'
        ) {
            return;
        }

        board[i][j] = '.';
        sink(board, i+1,j);
        sink(board, i-1,j);
        sink(board, i,j+1);
        sink(board, i,j-1);
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', '.', '.', 'X'},
                {'X', 'X', '.', 'X'},
                {'X', '.', '.', 'X'},
        };
        System.out.println(countBattleships(board)); // Output: 2
    }
}
