package spliterator_iterator;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorsIntro {

    public static void main(String[] args) {


        // Array of animals
        String[] animals = new String[]{"Dog", "Cat", "Lion", "Tiger"};

        // Create a Spliterator from the array
        Spliterator<String> spliterator = Arrays.spliterator(animals);

        // Create a sequential stream from the Spliterator
        Stream<String> sequentialStream = StreamSupport.stream(spliterator, false);
        System.out.println("Sequential Stream:");
        sequentialStream.forEach(System.out::println);

        // Create a parallel stream from the Spliterator
        Stream<String> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("\nParallel Stream:");
        parallelStream.forEach(System.out::println);

        // List of animals (created from the array)
        List<String> listOfAnimals = Arrays.asList(animals);

        // Create a sequential stream directly from the list
        Stream<String> listSequentialStream = listOfAnimals.stream();
        System.out.println("\nList Sequential Stream:");
        listSequentialStream.forEach(System.out::println);

        // Create a parallel stream directly from the list
        Stream<String> listParallelStream = listOfAnimals.parallelStream();
        System.out.println("\nList Parallel Stream:");
        listParallelStream.forEach(System.out::println);

    }
}
