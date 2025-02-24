package flatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlattenArraysOfArrays {

    public static void main(String[] args) {

    }

    static void flattenNestedArray() {

        String[][] arr = new String[][] {
                {"a", "b", "c"},
                {"d", "e"},
                {"g", "h", "i", "x"}
        };

        // First, create the stream of arrays
        Stream<String[]> stream = Arrays.stream(arr); // stream of arrays: [{"a", "b"}, {"c", "d"}, {"e", "f"}]

        // Print the stream of arrays (before flattening)
        System.out.println("flattenNestedArray after first transition");
        stream.forEach(array -> System.out.println(Arrays.toString(array)));

        // Recreate the stream because it has been consumed
        Stream<String[]> streamAgain = Arrays.stream(arr);

        // Flatten the stream and print the result
        Stream<String> stringStream = streamAgain
                .flatMap(Arrays::stream);

        System.out.println("\nflattenNestedArray after second transition");
        stringStream.forEach(System.out::println);
    }
}
