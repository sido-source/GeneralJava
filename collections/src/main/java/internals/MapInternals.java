package internals;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapInternals {

    public static void main(String[] args) {
        demonstrateHashMap();
        demonstrateHashtable();
        demonstrateTreeMap();
        concurrentHashMap();
    }

    private static void concurrentHashMap() {
        /*
        When multiple threads modify a HashMap simultaneously, race conditions can occur.
        Race Conditions: Two or more threads try to read, write, or modify the HashMap at the same time.
        Since HashMap does not lock or synchronize access, the result of these operations is unpredictable:
         */
        concurrentMethodStressTest(new HashMap<String, Integer>());
        concurrentMethodStressTest(new Hashtable<String, Integer>());
        concurrentMethodStressTest(new ConcurrentHashMap<String, Integer>());// xd
    }

    private static void concurrentMethodStressTest(Map<String, Integer> map) {


        Runnable task1 = () -> {
            for (int i = 0; i < 10000 ; i++) {
                map.put("Key-" + i, i);
            }
        };

        Runnable task2 = () -> {
            for (int i = 10000; i < 20002 ; i++) {
                map.put("Key-" + i, i);
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {

            // why we are joining
            // The join() method waits for this thread to die.
            // If the current thread is interrupted while waiting,
            // it is interrupted and throws InterruptedException.

            // If the current thread is not interrupted,
            // the thread waits indefinitely until it is either interrupted or dies.

            // This method is used to wait for the completion of a thread.
            // The main thread doesn't wait for the threads which has been started to finish;
            // it continues executing the subsequent lines of code

            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the size of the map and describe its behavior under concurrent access
        System.out.println("Map Type: " + map.getClass().getSimpleName());
        System.out.println("Size after concurrent operations: " + map.size());
    }

    // Demonstrates specific characteristics and methods of HashMap
    private static void demonstrateHashMap() {
        System.out.println("=== HashMap Demo ===");

        // HashMap is non-synchronized, allows one null key and multiple null values
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, null); // Allows null key
        hashMap.put("first", 1);
        hashMap.put("second", null); // Allows null value

        System.out.println("HashMap allows null key and values:");
        for (String key : hashMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + hashMap.get(key));
        }

        // Specific method: computeIfAbsent
        hashMap.computeIfAbsent("third", k -> 3);
        System.out.println("After computeIfAbsent: " + hashMap);

        // Specific method: getOrDefault
        int value = hashMap.getOrDefault("fourth", 0);
        System.out.println("Using getOrDefault for a non-existent key: " + value);

        boolean contains = hashMap.containsKey("first");
        boolean contains2 = hashMap.containsValue(2);

        System.out.println("hashMap.containsKey(\"first\"): " + contains);
        System.out.println("hashMap.containsValue(2) " + contains2);
    }

    // Demonstrates specific characteristics and methods of Hashtable
    private static void demonstrateHashtable() {
        System.out.println("=== Hashtable Demo ===");

        // Hashtable is synchronized, does not allow null keys or null values
        Map<String, Integer> hashTable = new Hashtable<>();

        try {
            hashTable.put(null, 1); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Cannot put null key in Hashtable.");
        }

        try {
            hashTable.put("first", null); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Cannot put null value in Hashtable.");
        }

        hashTable.put("first", 1);
        hashTable.put("second", 2);

        System.out.println("Hashtable does not allow null key or values:");
        for (Map.Entry<String, Integer> entry : hashTable.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Specific method: computeIfPresent
        hashTable.computeIfPresent("first", (k, v) -> v + 1);
        System.out.println("After computeIfPresent: " + hashTable);
    }

    // Demonstrates specific characteristics and methods of TreeMap
    private static void demonstrateTreeMap() {
        System.out.println("=== TreeMap Demo ===");

        // TreeMap is not synchronized, does not allow null keys, but allows null values
        // also is sorted by keys
        Map<String, Integer> treeMap = new TreeMap<>();

        try {
            treeMap.put(null, 1); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Cannot put null key in TreeMap.");
        }

        treeMap.put("first", 1);
        treeMap.put("second", null); // Allows null values
        treeMap.put("third", 3);

        System.out.println("TreeMap allows null values but not null keys:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        treeMap.putIfAbsent("second", 14);

        treeMap.putIfAbsent("second", 19);

        treeMap.put("azzz", 3);

        // TreeMap is sorted based on the natural ordering of keys
        System.out.println("TreeMap is sorted by key: " + treeMap);

        // Specific method: firstKey and lastKey
        String firstKey = ((TreeMap<String, Integer>) treeMap).firstKey();
        String lastKey = ((TreeMap<String, Integer>) treeMap).lastKey();
        System.out.println("First Key: " + firstKey + ", Last Key: " + lastKey);


        Map<String, Integer> map = new TreeMap<>((key1, key2) -> {
            Integer value1 = treeMap.get(key1);
            Integer value2 = treeMap.get(key2);

            // Calculate the sum of key length and the value
            int sum1 = key1.length() + value1;
            int sum2 = key2.length() + value2;

            // First compare by the sum
            int comparison = Integer.compare(sum1, sum2);

            // If sums are equal, compare by the natural order of the keys
            return (comparison != 0) ? comparison : key1.compareTo(key2);
        });

        map.putAll(treeMap);
//        map.put("first", 1);
//        map.put("second", null);

        System.out.println("TreeMap sorted by key length + value:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}

