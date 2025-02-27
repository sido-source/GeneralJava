import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//This is a stateful intermediate operation.
public class LazyStreams_2 {

    /*
    Streams in Java are lazy, meaning that they won't perform any operations (like those inside peek() or flatMap()) until a terminal operation is invoked.
    Each intermediate operation like filter(), map(), flatMap() ... will return the new Stream<T> !!!

    The stream won't start processing without a terminal operation such as:
    forEach() : void
    collect()
    reduce()
    count() : long
    findFirst() :  java. util. Optional<T>
    anyMatch(), etc.
     */


    public static void main(String[] args) {

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // the below code is lazy, so it wont be performed till we add the terminal operation [uncomment line 44]
        Stream<String> step1 = names.stream() // // intermediate operation
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("A");
                });

        Stream<String> step2 = step1.peek(name -> System.out.println("After filter: " + name)); // // intermediate operation
        Stream<String> step3 = step2.map(name -> { //// intermediate operation
            System.out.println("Mapping: " + name);
            return name.toUpperCase();
        });

        Stream<String> step4 = step3.peek(name -> System.out.println("After map: " + name)); // intermediate operation

        // Set<String> collect = step4.collect(Collectors.toSet()); - terminate operation, we get the result other than new Stream ;)


        Stream<String> stream = Stream.of("a", "b", "c");

        // Terminal operation: forEach()
        stream.forEach(System.out::println);


        // Terminal operation: collect() - mutable collection
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
