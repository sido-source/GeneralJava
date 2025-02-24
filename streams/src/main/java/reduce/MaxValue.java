package reduce;

import java.util.Arrays;

public class MaxValue {

    public static void main(String[] args) {
        int[] numbers = {3, 1, 4, 1, 5, 9};
        int max = Arrays.stream(numbers).reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(max);  // Output: 9

    }
}
