package arrays;

public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        rotateClockwiseRight(matrix);

        // Print the rotated matrix
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void rotateClockwiseRight(int[][] matrix) {
        // some variables
        int column = matrix[0].length;
        int row = matrix.length;

        // 1. Transpose the matrix (swap row with column elements)
        // We swap elements above the diagonal only (i.e., when j > i)
        for(int i=0; i<row; i++) {
            for(int j=i; j<column; j++) {
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }

        // 2. Reverse each row to achieve the 90-degree clockwise rotation
        for (int i=0; i<row; i++) {
            for (int j = 0; j < column/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][column-j-1];
                matrix[i][column-j-1]= tmp;
            }
        }
    }

    public static void rotateClockwiseLeft(int[][] matrix) {

        int row = matrix.length;
        int column = matrix[0].length;

        // 1. transpose each
        for (int i=0; i< row; i++) {
            for (int j=i; j<column; j++) {
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }

        // 2. Reverse each row
        for (int i=0; i<row/2; i++) {
            for (int j=0; j < column; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[row-i-1][j];
                matrix[row-i-1][j] = temp;
            }
        }
    }
}
