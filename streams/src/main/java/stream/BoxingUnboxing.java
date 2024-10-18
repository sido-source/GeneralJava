package stream;

import java.util.Arrays;

public class BoxingUnboxing {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4};

        // toArray called on Stream<E> object will always return [] Object  -> Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray()
        // use the parameter to cast the stream to the specific type np Integer
        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
    }
}
