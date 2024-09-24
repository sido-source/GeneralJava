package flatMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlattenMaps {

    public static void main(String[] args) {

    }

    static Set<Character> flattenNestedMap() {

        // Convert the map to a stream of keys (char[])

        Map<char[], Integer> map = new HashMap<char[], Integer>();

        map.put(new char[]{'a', 'b', 'c'}, 1);
        map.put(new char[]{'d', 'e', 'f'}, 2);
        map.put(new char[]{'x', 'd'}, 1);



        // The error occurs because Arrays::stream cannot be used directly with char[]. The method Arrays::stream works for reference types like Integer[], String[], etc., but not for primitive
        //Stream<char[]> charStream = map.keySet().stream().flatMap(Arrays::stream);


        //also doesnt work because the char IntStream.map returns the IntStream not the Character stream
//        Stream<Character> characterStream = map
//                .keySet()
//                .stream()
//                .flatMap(chars -> IntStream
//                        .range(0, chars.length)
//                        .map(index -> chars[index])
//                        .boxed());

        Stream<Character> characterStream = map
                .keySet()
                .stream()
                .flatMap(chars -> IntStream.range(0, chars.length)
                        .mapToObj(index -> chars[index])
                );

        return characterStream.collect(Collectors.toSet());
    }
}
