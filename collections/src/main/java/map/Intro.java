package map;

import java.util.*;
import java.util.stream.IntStream;

public class Intro {

    /*
    Handling Reference Types in HashMap
    List or Set: Using List or Set within a HashMap is secure because both List and Set have hashCode() and equals() implemented. These implementations ensure that if you need to check for equality of lists or sets, it will work as expected.
    Array: If you use an Array as the value in the map (e.g., HashMap<Integer, Integer[]>), it’s less ideal for a few reasons:
    Arrays don’t override hashCode() or equals(), so two arrays with identical contents would be considered different.
    To compare arrays by content, you’d need to use Arrays.equals(array1, array2) manually.
    For collections with mutable data (like arrays), storing them in a map can be risky as modifying the array outside the map will affect the map’s content as well


    Summary of Best Practices:
    Avoid using arrays as keys in HashMap. Use List or Set instead.
    Use immutable objects as keys to prevent unintended modifications.
    Override equals() and hashCode() correctly for custom objects.
    Be cautious with mutable objects in maps.
    Use ConcurrentHashMap for thread-safe operations.
    Set appropriate initial capacity and load factor for better performance.
     */

    public static void main(String[] args) {

        // Arrays in Java (e.g., Integer[], String[]) do not override equals() or hashCode() from the Object class. This means:
        // Two arrays with the same content are not considered equal when using equals().
        // The hashCode() of an array is based on its memory address, not its content.

        //Workaround:
        //Use Arrays.equals(array1, array2) to compare arrays by content.
        //Use Arrays.hashCode(array) to compute a hash code based on the array’s content.
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 3};
        System.out.println(array1.equals(array2)); // false
        System.out.println(array1.hashCode() == array2.hashCode()); // false
        System.out.println(Arrays.equals(array1, array2)); // true
        System.out.println(Arrays.hashCode(array1) == Arrays.hashCode(array2)); // true

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        System.out.println(list1.equals(list2)); // true
        System.out.println(list1.hashCode() == list2.hashCode()); // true


        // If you use mutable objects (e.g., arrays, custom objects with mutable fields) as keys in a HashMap, modifying them after insertion can break the map’s integrity.
        // Best Practices:
        //Use immutable objects as keys (e.g., String, Integer, or custom immutable classes).
        //If you must use mutable objects, ensure they are not modified after being added to the map.
        //For custom objects, override equals() and hashCode() correctly and ensure they are consistent with the object’s state.
        HashMap<List<Integer>, String> map1 = new HashMap<>();
        List<Integer> key = new ArrayList<>(Arrays.asList(1, 2, 3));
        map1.put(key, "Value");
        System.out.println(map1);

        key.add(4); // Modify the key
        System.out.println(map1.get(key)); // null (key's hash changed)






        HashMap<Integer, List<Integer>> map = new HashMap<>();

        /*
        When you declare a collection in Java, it automatically initialize with an empty/null collection;
        So, before you add elements to a collection in a map, you need to initialize it first if it doesn’t already exist.
         */
        for (Integer i : List.of(1,4,6)) {
            map.putIfAbsent(i, new ArrayList<>()); // !!
            map.get(i).add(i*2);
        }


        Set<Map.Entry<Integer, List<Integer>>> entries = map.entrySet();
        Set<Integer> integers = map.keySet();

        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println(map.containsValue(List.of(2)));
        System.out.println(map.containsKey(1));

        map.getOrDefault(2, new ArrayList<>());
        map.remove(1, List.of(2));
        System.out.println(map.get(1));

        // put directly is allowed as it is just mapping between 2 Objects [ not collections ]
        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "Asd");
    }
}
