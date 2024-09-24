package reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class ReduceSameElements {

    public static void main(String[] args) {
        int[] numbers= new int[]{1,3,5,5,4,3};

//        List<Integer> result = Arrays.stream(numbers)
//                .reduce(new HashSet<Integer>(), (a, b) -> );



        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
//        List<Character> result1 = Arrays.stream(chars) doesnt work

        List<Character> result1 = IntStream.range(0, chars.length)
                .mapToObj(index -> chars[index])
                .distinct()
                .toList();

        //System.out.println(result);
    }
}
