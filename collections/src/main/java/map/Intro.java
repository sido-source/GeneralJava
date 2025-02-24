package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Intro {

    /*
    Handling Reference Types in HashMap
    List or Set: Using List or Set within a HashMap is secure because both List and Set have hashCode() and equals() implemented. These implementations ensure that if you need to check for equality of lists or sets, it will work as expected.
    Array: If you use an Array as the value in the map (e.g., HashMap<Integer, Integer[]>), it’s less ideal for a few reasons:
    Arrays don’t override hashCode() or equals(), so two arrays with identical contents would be considered different.
    To compare arrays by content, you’d need to use Arrays.equals(array1, array2) manually.
    For collections with mutable data (like arrays), storing them in a map can be risky as modifying the array outside the map will affect the map’s content as well
     */

    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();



        /*
        When you declare a collection in Java, it doesn’t automatically initialize with an empty collection;
        it starts as null. So, before you add elements to a collection in a map, you need to initialize it first if it doesn’t already exist.
         */
        for (Integer i : List.of(1,4,1,6)) {
            map.putIfAbsent(i, new ArrayList<>());

            map.get(i).add(i*2);
        }

        // put directly is allowed as it is just mapping between 2 values not collections
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, new String("Asd"));
    }
}
