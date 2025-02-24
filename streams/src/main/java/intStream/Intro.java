package intStream;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Intro {

    public static void main(String[] args) {

        // building methods
        IntStream.range(0, 5).forEach(System.out::println);

        int[] array = IntStream.of(1, 2, 3).toArray();  // Output: [1, 2, 3]

        // lazy init using stream  #lazy#stream
        // time, uuid, math random
        Stream<UUID> intStream = Stream.generate(UUID::randomUUID);
        List<String> listOfStrings = intStream.limit(2).map(uuid -> uuid.toString()).toList();

        IntStream in1 = IntStream.generate(() -> (int) (Math.random() * 100));
        in1.limit(4).skip(2).forEach(System.out::println);
        System.out.println("\n");

        // converting from primitive into Object


        // specifics methods for IntStream
        OptionalInt min = IntStream.range(0, 10).map(i -> 10 - i).min();
        System.out.println("Min : " +min.getAsInt());

        OptionalInt max = IntStream.of(1, 3, 4, 5, 112).max();
        System.out.println("Max : " +max.getAsInt());

        int sum = IntStream.of(3,5,22,1).sum();
        System.out.println("Sum is : " + sum);


        OptionalDouble avarage = IntStream.rangeClosed(0,3).average();
        System.out.println("Avarage : " + avarage.getAsDouble());

//        int initNumbers=3;
//        IntStream.generate(() -> {
//
//        })

    }
}
