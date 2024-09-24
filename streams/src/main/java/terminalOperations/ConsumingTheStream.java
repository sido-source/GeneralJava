package terminalOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsumingTheStream {

    public static void main(String[] args) {

        Stream<String> stream = Stream.of("a", "b", "c");

        // Terminal operation: forEach()
        stream.forEach(System.out::println);


        try {
            System.out.println(stream.collect(Collectors.toList()));
        } catch (IllegalStateException e) {
            System.out.println("Stream can be consumed by terminal operation only once");
            //e.printStackTrace();
        }


        System.out.println("This stream wont execute xd");
        // No terminal operation, so nothing will happen

        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e"),
                Arrays.asList("g", "h", "i", "x")
        );

        Stream<String> stream1 = list
                .stream()
                .peek(System.out::println)  // This will not print anything yet
                .flatMap(List::stream)      // This will not flatten anything yet
                .peek(System.out::println); // This will also not print anything yet

        System.out.println("Nothing happened");

        System.out.println("Add terminal operation into stream1, it will trigger the execution");
        stream1.forEach(str -> {});
    }


}
