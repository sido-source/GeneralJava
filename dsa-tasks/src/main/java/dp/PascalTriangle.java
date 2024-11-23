package dp;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);  // First element of each row

            // Calculate middle elements of the row based on the previous row
            // j starts from 1 so it will be activated where i = 2.3.4.5....n

            // i tells about which level do we currently have
            // j tells what element  do we have in the i level

            for (int j = 1; j < i; j++) { // this is very important   j < i !!

                row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }

            if (i > 0) row.add(1);  // Last element of each row

            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        List<List<Integer>> triangle = pascalTriangle.generate(5);

        for (List<Integer> row : triangle) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

