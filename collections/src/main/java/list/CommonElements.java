package list;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonElements {

    public static void main(String[] args) {


        java.util.List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e');
        java.util.List<Character> commons = Arrays.asList('a', 'd', 'c', 'a');


        Set<Character> collect = chars.stream()
                .filter(commons::contains)
                .collect(Collectors.toSet());
        //[a, c, d]

        System.out.println("Find common elements in char and commons");
        System.out.println(collect);

        System.out.println("\nFind all char arrays that have at least one common elements with commons list");

        List<char[]> listOfCharacters = Arrays.asList(
                new char[]{'w', 's', 'f', 'c'},
                new char[]{'i', 'h', 'e'},
                new char[]{'w', 'f'},
                new char[]{'a', 'd', 'e'}
        );

//      [w, s, f, c]
//      [a, d, e]
        List<char[]> charactersWithCommonsLetters = listOfCharacters.stream()
                .filter(array -> {
                    return IntStream.range(0, array.length)// get indices from IntStream
                            .mapToObj(index -> array[index]) // convert intStream into CharacterStream
                            .anyMatch(commons::contains);
                })
                .toList();

        for (char[] arr : charactersWithCommonsLetters) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(charactersWithCommonsLetters);

    }
}
