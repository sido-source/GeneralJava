package flatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenListOfArrays {

    public static void main(String[] args) {

    }

    public static void mixed2DCollections() {
        List<int[]> listOfArrays = Arrays.asList(
                new int[]{1, 2, 3},
                new int[]{4, 5},
                new int[]{6, 7, 8}
        );

        // Flatten the List<int[]> into a single List<Integer>
        List<Integer> flattenedList = listOfArrays.stream()
                .flatMapToInt(Arrays::stream)  // This works with primitive int[]
                .boxed()  // Convert IntStream to Stream<Integer>
                .collect(Collectors.toList());

        System.out.println(flattenedList);  // Output: [1, 2, 3, 4, 5, 6, 7, 8]
    }
}
