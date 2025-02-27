import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.*;

public class CreateStream_1 {

    public static void main(String[] args) {

        /*
        In Java, the Stream API provides specialized streams for the three main primitive types: int, long, and double.
        These are IntStream, LongStream, and DoubleStream, respectively.
        Java uses object streams (Stream<T>), where the primitives can be boxed into their respective wrapper classes (Byte, Character, Boolean, etc.)
         */


        //1. Stream from primitives
        // Arrays.stream() or IntStream.range(0, length).mapToObj(i -> char[i])


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

        // there is a bit different in case of the chars and Character stream
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        Stream<Character> charStream = new String(chars).chars()
                .mapToObj(c -> (char) c);

        char[] chars1 = new char[]{'a', 'b', 'c', 'd', 'e'};
        Stream<Character> charStream1 = IntStream.range(0, chars1.length)
                .mapToObj(i -> chars1[i]);
        charStream.forEach(System.out::println);


        //2.  Stream from collection
        // Collection.stream


        Collection<Integer> someNumbers = java.util.List.of(1,4,5,6,7,2);
        long count = someNumbers
                .parallelStream()
                .peek(System.out::println) // we pass the consumer value as the println argument
                .filter(num -> num > 3)
                .peek(action -> System.out.println("Filtered number is " + action + "on time: " + Instant.now().toEpochMilli()))
                .count();
        System.out.println(count);


        // 3. Stream from its own utility methods
        // Stream.of(T...), Stream.<T>builder(), Stream.generate() -> it creates infinite stream

        Stream<String> streamOf = Stream.of("apple", "banana", "cherry");
        streamOf.forEach(System.out::println);


        Stream<String> streamBuilder = Stream.<String>builder()
                .add("apple")
                .add("banana")
                .add("cherry")
                .build();
        streamBuilder.forEach(System.out::println);

        Stream<Integer> iterate = Stream.iterate(0, new UnaryOperator<Integer>() {

            // UnaryOperator is the special type of function which returns the same param as it takes
            @Override
            public Integer apply(Integer integer) {
                return integer + 4;
            }
        });

        // infinite stream until we limit it
        iterate
                .limit(3)
                .forEach(System.out::println);



        /*
        Stream.generate(Supplier):
        Creates an infinite stream by repeatedly calling a Supplier function.
        Useful for generating streams of random numbers, sequences, or other values.
        You'll typically use limit() to make it a finite stream.
         */
        Stream<Double> randomNumbers1 = Stream.generate(Math::random).limit(5);
        Stream<String> helloStream = Stream.generate(() -> "Hello").limit(3);

        /*
        Stream.iterate(seed, UnaryOperator)
        Creates an infinite stream by repeatedly applying a UnaryOperator to the previous element.
        Useful for generating sequences of numbers or other values.
        You'll typically use limit() to make it a finite stream.
         */
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2).limit(5);
        Stream<Long> fibonacci = Stream.iterate(new long[] { 0, 1 }, f -> new long[] { f[1], f[0] + f[1] }).map(f -> f[0]).limit(10);


        // others
        /*
        The mapToObj method in Java's Stream API is used to transform a primitive stream (like IntStream, LongStream, or DoubleStream) into a stream of objects (Stream<T>),
        where T is the type of object you're mapping to.
         */

        IntStream intStream1 = IntStream.of(1,2,34);
        DoubleStream ds1 = DoubleStream.of(14,5,2,5.24);
        Stream<Double> ds = Stream.of(65.0d, 66.7d, 68d);
        Stream<Integer> integerStream1 = ds.map(Double::intValue);

        // Create a Stream<Double>
        Stream<Double> stream = Stream.of(65.0d, 66.7d, 68.0d);


        // Stream from IO

        //Files.lines(Path):
        //Creates a stream of lines from a text file.
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //BufferedReader.lines()
        //Creates a stream of lines from a BufferedReader.
        try(BufferedReader reader = new BufferedReader(new FileReader("file.txt", StandardCharsets.UTF_16))) {
            reader.lines().forEach(System.out::println);
            //reader.readLine()
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt"), StandardCharsets.UTF_8))) {
            reader.lines().forEach(System.out::println);
            //reader.readLine()
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("file.txt"))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                String text = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8); // Manual decoding
                System.out.print(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Processing Bytes with Functional Operations
        try (InputStream inputStream = new FileInputStream("file.bin")) {
            IntStream byteStream = IntStream.generate(() -> {
                try {
                    return inputStream.read();
                } catch (IOException e) {
                    return -1;
                }
            }).takeWhile(b -> b != -1);

            // Filter bytes (e.g., keep only bytes greater than 64)
            byteStream.filter(b -> b > 64).forEach(b -> System.out.println((byte) b));

            // Map bytes (e.g., add 1 to each byte)
            IntStream mappedBytes = byteStream.map(b -> b + 1);

            // Reduce bytes (e.g., calculate the sum of all bytes)
            int sum = mappedBytes.reduce(0, Integer::sum);
            System.out.println("Sum of bytes: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Collecting Bytes into a List or Array
        String filePath = "file.bin"; // Replace with your binary file

        try (InputStream inputStream = new FileInputStream(filePath)) {
            IntStream byteStream = IntStream.generate(() -> {
                try {
                    return inputStream.read();
                } catch (IOException e) {
                    return -1;
                }
            }).takeWhile(b -> b != -1);

            // Collect into a List<Byte>
            List<Byte> byteList = byteStream.mapToObj(b -> (byte) b).collect(Collectors.toList());
            System.out.println("Byte List: " + byteList);

            // Collect into a byte[]
            int[] intArray1 = byteStream.toArray(); // Get int[] first
            byte[] byteArray = new byte[intArray1.length];
            for (int i = 0; i < intArray1.length; i++) {
                byteArray[i] = (byte) intArray1[i]; // Convert each int to byte
            }

            System.out.println("Byte Array: " + java.util.Arrays.toString(byteArray));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
