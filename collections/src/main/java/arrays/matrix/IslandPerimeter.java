package arrays.matrix;
public class IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) { // If the cell is land
                    perimeter += 4; // Add 4 for each land cell

                    // Check if the neighboring cells are land to subtract shared sides
                    if (row > 0 && grid[row - 1][col] == 1) { // Check top
                        perimeter -= 2;
                    }
                    if (col > 0 && grid[row][col - 1] == 1) { // Check left
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        System.out.println("Perimeter of the island: " + islandPerimeter(grid));
    }
}
