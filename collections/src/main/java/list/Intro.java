package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intro {

    public static void main(String[] args) {

        java.util.List<Integer> l1 = List.of(1,2,3,4);
        List<String> l2 = Arrays.asList("one", "two", "three");
        List<Character> l3 = new ArrayList<>(List.of('c', 'e', 'r', 'y'));
        List<Character> l4 = new ArrayList<>(Arrays.asList('c', 'e', 'r', 'y'));

        l3.add('d');

        // Mutable list
        List<Integer> mutableList = Stream.of(1, 2, 3)
                .collect(Collectors.toList());
        mutableList.add(4); // ✅ Works
        System.out.println("Mutable: " + mutableList); // [1, 2, 3, 4]

        // Immutable list
        List<Integer> immutableList = Stream.of(1, 2, 3)
                .toList();
        immutableList.add(4); // 🚫 Throws UnsupportedOperationException

    }
}
