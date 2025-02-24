package graph.dfs;

public class NumberOfIsland {

    public static int numIslands(char[][] grid) {

        int islands = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int[][] direction = {
                {1,0}, // down
                {-1,0}, // up
                {0,1}, // right
                {0,-1} // left
        };

        for(int i = 0 ; i < rows ; i++) {
            for(int j=0; j < cols ; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    visited[i][j]=true;
                    islands++;
                    dfs(i,j,grid,visited,direction);
                }
            }
        }
        return islands;
    }

    static void dfs(int row, int col, char[][] grid, boolean[][] visited, int[][] dir) {

        for(int[] direction : dir) {
            int newRow = row + direction[0]; // y axe
            int newCol = col + direction[1]; // x axe

            if (
                    newRow >= 0 && newRow < grid.length
                            && newCol >= 0 && newCol < grid[0].length
                            && !visited[newRow][newCol]
                            && grid[newRow][newCol] == '1'
            ) {
                visited[newRow][newCol] = true;
                dfs(newRow, newCol, grid, visited, dir);
            }
        }
    }

    public static void main(String[] args) {

        // https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150
        //An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

        
        // so basically we have to iterate through the array in o(col * row) time complexity
        // if we encounter 1 we are doing dfs, so recursively find some adjacent island and mark it as visited or just "0"
        // dfs time complextiy is o(col-currentX * row-currentY) as well
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        System.out.println(NumberOfIsland.numIslands(grid)); // Output: 1
        // The number of islands in the given grid is 1.
    }
}
