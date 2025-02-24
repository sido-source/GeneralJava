package bigCompanies.interview2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WaterFlow {

    // you are given the 2D matrix of heights and the start point like (1,0)
    // simulate the water flow by fallow this rules:

    // 1) water flow (up, down, left, right) if the adjacent cell's height is less than or equal to the current cell's height
    // 2) the water flow stops if all adjacent cells are higher than current cell's height

    // you have to return the 2D array in the way that each cell contains the time step when it becomes wet, starting from 0.
    // IF the cell wont be wet, return -1

    // input
    static int [][] heights = new int[][] {
            {3, 2, 1},
            {6, 5, 4},
            {9, 8, 7}
    };

    // output -> solution(heights, startX, startY)
    static int [][] solution = new int[][] {
            {-1, 1, 2},
            {-1, 0, 1},
            {-1, -1, -1}
    };


    public static void main(String[] args) {
        // solution = solution(heights, 1,1)

        int[][] res = solution(heights, 1,1);

        for (int[] arr: res) {
            for (int i : arr) {
                System.out.print(i+ " ");
            }
            System.out.println();
        }
    }


    static int[][] solution(int[][] inputMatrix, int startX, int startY) {

        if (startX < 0 || startX > inputMatrix.length) return null;
        if (startY < 0 || startY > inputMatrix[0].length) return null;

        int[][] directions = {
            {-1, 0}, // up
            {1, 0}, // down
            {0, -1}, // left
            {0, 1} // right
        };


        int[][] response = new int[inputMatrix.length][inputMatrix[0].length]; // prepare response
        for (int i =0; i < inputMatrix.length; i++) {
            Arrays.fill(response[i], 0, inputMatrix.length, -1);
        }

        bfsHelper(inputMatrix, startX, startY, directions, response);

        return response;
    }

    // it is good to have next function because sometimes this function returns something that we need
    // in this example it is just to be stick with this rules
    static void bfsHelper(int[][] adjacent, int row, int col, int[][] directions, int[][] response) {

        // !!!always above 4 steps are must have!!!!!
        boolean[][] visited = new boolean[adjacent.length][adjacent[0].length]; // can be set
        Deque<WaterFlowHelper> queue = new ArrayDeque<>();
        visited[row][col] = true;
        queue.add(new WaterFlowHelper(row, col, 0)); // first initialization is different than when we bolow use it in the loop

        while(!queue.isEmpty()) {

            WaterFlowHelper waterFlowHelper = queue.poll();
            response[waterFlowHelper.x][waterFlowHelper.y] = waterFlowHelper.timeStep;



            for(int[] arr : directions) {
                int rowNew = waterFlowHelper.x+arr[1];
                int colNew = waterFlowHelper.y+arr[0];

                // corner cases , when we not add to queue
                if(rowNew >= 0 && rowNew <= adjacent.length-1 && colNew >= 0 && colNew <= adjacent[0].length-1
                        && !visited[rowNew][colNew]) {
                    visited[rowNew][colNew] = true;
                    queue.add(new WaterFlowHelper(rowNew, colNew, waterFlowHelper.timeStep+1));
                }

            }
        }
    }

    // when there is the bfs (we are spreading in all directions) we should create some data structure which can help us
    // here we have to know the current coordination as well as coordination that we are come from
    static class WaterFlowHelper {
        int x,y,timeStep;

        public WaterFlowHelper(int x, int y, int timeStep) {
            this.x = x;
            this.y = y;
            this.timeStep = timeStep;
        }
    }
}
