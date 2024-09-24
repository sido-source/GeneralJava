package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Intro {

    public static void main(String[] args) {

        // generate stream
        Stream.of("namew", "surname", "age")
                // Step 2: For each string, get its characters as a stream
                .flatMap(string -> IntStream.range(0, string.length())
                        // Convert each character to a string (char -> String)
                        .mapToObj(i -> string.charAt(i)))
                // Step 3: Filter characters that do not exist in the other strings
                .filter(character -> Stream.of("name", "surname", "age")
                        // Check if this character is unique to the string
                        .noneMatch(str -> str.indexOf(character) != -1))
                // Step 4: Print the unique characters
                .forEach(System.out::println);

        // builder
        Stream<Object> build = Stream.builder().add("ezz").add("surname").add("age").build();
        build.forEach(System.out::println);

        // generate #lazy#stream
        SomeObjectWithCollection someObjectWithCollection = new SomeObjectWithCollection("jan", Arrays.asList(new Objects[2]));
        SomeObjectWithCollection someObjectWithCollection1 = new SomeObjectWithCollection("jan1", Arrays.asList(new Objects[1]));
//        someObjectWithCollection.objectsList.stream()
    }
}


class SomeObjectWithCollection {
    String name;
    List<Objects> objectsList;

    public SomeObjectWithCollection(String name, List<Objects> objectsList) {
        this.name = name;
        this.objectsList = objectsList;
    }
}
