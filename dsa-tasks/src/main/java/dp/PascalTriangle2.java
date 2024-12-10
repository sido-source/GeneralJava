package dp;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {

        // time complexity: O(n^2)
        // space complexity: O(n)

        // i = level
        // j = element in i level
        List<List<Integer>> levels = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        for(int i =0; i <= rowIndex; i++) {
            List<Integer> curr = new ArrayList<>();
            curr.add(1);

            for (int j = 1; j < i ; j++) {
                System.out.print( "i: " + i + " j : " + j);
                curr.add(levels.get(i-1).get(j-1) + levels.get(i-1).get(j));
            }

            if (i >= 1) curr.add(1);
            levels.add(curr);
            System.out.println(levels);
        }

        return levels.get(rowIndex);
    }


    // time complexity  and space complexity o(n)
    // we take advantage of binomial coefficients - take a look on the jpg in folder

    public List<Integer> getRowOptimized(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        long current = 1; // First element is always 1

        for (int j = 0; j <= rowIndex; j++) {
            res.add((int) current);
            // Update current element using the recurrence formula
            current = current * (rowIndex - j) / (j + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        PascalTriangle2 pascalTriangle = new PascalTriangle2();
        List<Integer> row = pascalTriangle.getRow(3);

        for (Integer num : row) {
            System.out.print(num + " ");
        }
    }
}
