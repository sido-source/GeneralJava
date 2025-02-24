package reduce;

import java.util.Arrays;

public class SummingUp {

    public static void main(String[] args) {

        int[] numbers = {1, 2, 3, 4, 5};

        //int reduce = Arrays.stream(numbers)
        //                .reduce(0, ((left, right) -> left + right));

        int reduce = Arrays.stream(numbers)
                .reduce(0, (Integer::sum));

        System.out.println(reduce);


    }
}
