package terminalOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//This is a stateful intermediate operation.
public class LazyStreams {

    /*
    Streams in Java are lazy, meaning that they won't perform any operations (like those inside peek() or flatMap()) until a terminal operation is invoked.

    In your case, the stream won't start processing without a terminal operation such as:

    forEach()
    collect()
    reduce()
    count()
    anyMatch(), etc.
     */


    public static void main(String[] args) {


        Stream<String> stream = Stream.of("a", "b", "c");

        // Terminal operation: forEach()
        stream.forEach(System.out::println);


        // Terminal operation: collect()
        Stream<String> stream2 = Stream.of("a", "b", "c");
        System.out.println(stream2.collect(Collectors.toList()));  // Output: [a, b, c]

        // Terminal operation: reduce()
        Stream<String> stream3 = Stream.of("a", "b", "c");
        System.out.println(stream3.reduce((a, b) -> a + b));  // Output: abc

        // Terminal operation: count()
        Stream<String> stream4 = Stream.of("a", "b", "c");
        System.out.println(stream4.count());  // Output: 3

        // Terminal operation: anyMatch()
        Stream<String> stream5 = Stream.of("a", "b", "c");
        System.out.println(stream5.anyMatch(s -> s.equals("a")));  // Output: true

        // Terminal operation: noneMatch()
        Stream<String> stream6 = Stream.of("a", "b", "c");
        System.out.println(stream6.noneMatch(s -> s.equals("z")));  // Output: true

        // Terminal operation: findFirst()
        Stream<String> stream7 = Stream.of("a", "b", "c");
        System.out.println(stream7.findFirst().orElse("Not found"));  // Output: a

        // Terminal operation: findAny()
        Stream<String> stream8 = Stream.of("a", "b", "c");
        System.out.println(stream8.findAny().orElse("Not found"));  // Output: a (or any element, depends on parallelism)

        // Terminal operation: findAny()

    }
}
