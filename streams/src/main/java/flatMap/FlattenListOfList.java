package flatMap;

import java.util.Arrays;
import java.util.List;

public class FlattenListOfList {

    public static void main(String[] args) {
        flattenNestedList();
    }

    static void flattenNestedList() {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e"),
                Arrays.asList("g", "h", "i", "x")
        );

        System.out.println("FlattenNestedList example");



        List<String> prepareStream =
                list
                        .stream()
                        .peek(array -> System.out.println("First peek: " + array))// stream of lists: [[a, b, c], [d, e], [g, h, i, x]]
                        .flatMap(List::stream) // stream of Lists converted to Stream of String
                        .peek(string -> System.out.println("Second peek: " + string))
                        .toList();

        //consume the stream
        System.out.println("Consume the stream: Stream<String>");
        prepareStream.forEach(string -> System.out.println("Consume: " + string));


        List<List<Integer>> listss = List.of(List.of(10), List.of(44));
    }
}
