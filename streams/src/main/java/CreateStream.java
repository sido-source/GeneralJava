import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.*;

public class CreateStream {

    // we dont need to delete AccessibleApplet, because it is a internal class of Applet class which was deprecated example of internal class which is a composition
    public static void main(String[] args) {

        /*
        In Java, the Stream API provides specialized streams for the three main primitive types: int, long, and double.
        These are IntStream, LongStream, and DoubleStream, respectively. For other primitive types, such as byte, char, and boolean, as well as for objects,
        Java uses object streams (Stream<T>), where the primitives can be boxed into their respective wrapper classes (Byte, Character, Boolean, etc.)
         */


        // primitives

        int[] intArray = {1, 2, 3};
        IntStream intStream = Arrays.stream(intArray);
        Stream<Integer> integerStream = Arrays.stream(intArray).boxed();

        long[] longArray = {1L, 2L, 3L};
        LongStream longStream = Arrays.stream(longArray);
        Stream<Long> longStream1 = longStream.boxed();

        double[] doubleArray = {1.0, 2.0, 3.0};
        DoubleStream doubleStream = Arrays.stream(doubleArray);


        // Java does not have a ByteStream. However, you can work with IntStream and box the values to Byte
        byte[] bytes = new byte[]{127,24,56,3,36,126};
        Stream<Byte> bytesBoxedStream = IntStream.range(0, bytes.length).mapToObj( i -> bytes[i]);



        // collection (spliterator?)

        Collection<Integer> someNumbers = java.util.List.of(1,4,5,6,7,2);
        long count = someNumbers
                .parallelStream()
                .peek(System.out::println) // we pass the consumer value as the println argument
                .filter(num -> num > 3)
                .peek(action -> System.out.println("Filtered number is " + action + "on time: " + Instant.now().toEpochMilli()))
                .count();
        System.out.println(count);


        // stream

        Stream<String> streamOf = Stream.of("apple", "banana", "cherry");
        streamOf.forEach(System.out::println);


        Stream<String> streamBuilder = Stream.<String>builder()
                .add("apple")
                .add("banana")
                .add("cherry")
                .build();
        streamBuilder.forEach(System.out::println);



        // others
        /*
        The mapToObj method in Java's Stream API is used to transform a primitive stream (like IntStream, LongStream, or DoubleStream) into a stream of objects (Stream<T>),
        where T is the type of object you're mapping to.
         */

        IntStream intStream1 = IntStream.of(1,2,34);
        DoubleStream ds1 = DoubleStream.of(14,5,2,5.24);
        Stream<Double> ds = Stream.of(65.0d, 66.7d, 68d);
        //ds.map(i -> i.intValue());
        Stream<Integer> integerStream1 = ds.map(Double::intValue);

        // Create a Stream<Double>
        Stream<Double> stream = Stream.of(65.0d, 66.7d, 68.0d);

        // Convert Stream<Double> to DoubleStream
//        DoubleStream doubleStream = stream.mapToDouble(Double::doubleValue);

        // Use DoubleStream (for example, calculate the average)
        double average = doubleStream.average().orElse(0.0);

        System.out.println("Average: " + average);

//        integerStream1.mapToInt(ma->ma.intValue()).mapToObj()
    }
}
