package internals;

import java.util.*;
/*
Overview of Set Implementations
HashSet:
Backed by: A hash table.
Order: Does not maintain any order of elements.
Performance: Offers constant-time performance for basic operations like add, remove, contains, and size (O(1) on average).
Null Handling: Allows one null element.
Use Case: When you need a collection that prevents duplicates and you don't care about the order.


LinkedHashSet:
Backed by: A hash table with a linked list running through it.
Order: Maintains insertion order.
Performance: Slightly slower than HashSet due to the additional overhead of maintaining the linked list, but still offers constant-time performance for basic operations (O(1) on average).
Null Handling: Allows one null element.
Use Case: When you need to maintain insertion order while preventing duplicates.


TreeSet:
Backed by: A Red-Black tree (a type of self-balancing binary search tree).
Order: Maintains natural ordering (ascending) or a custom order if provided with a Comparator.
Performance: Provides guaranteed log(n) time cost for basic operations (O(log n)).
Null Handling: Does not allow null elements (throws NullPointerException).
Use Case: When you need a sorted set or need to perform range-based operations.
 */
public class SetComparison {
    public static void main(String[] args) {
        // Using HashSet
        Set<String> hashSet = new HashSet<>();
        System.out.println("HashSet Example:");
        performCommonSetOperations(hashSet);

        // Using LinkedHashSet
        Set<String> linkedHashSet = new LinkedHashSet<>();
        System.out.println("\nLinkedHashSet Example:");
        performCommonSetOperations(linkedHashSet);

        // Using TreeSet
        Set<String> treeSet = new TreeSet<>();
        System.out.println("\nTreeSet Example:");
        performCommonSetOperations(treeSet);
    }

    private static void performCommonSetOperations(Set<String> set) {
        // Adding elements to the set
        set.add("Apple");
        set.add("Banana");
        set.add("Orange");
        set.add("Mango");
        set.add("Grape");

        // Demonstrating duplicate element rejection
        boolean isAdded = set.add("Apple"); // Trying to add duplicate element
        System.out.println("Trying to add 'Apple' again: " + (isAdded ? "Added" : "Not Added"));

        // Demonstrating null handling
        try {
            set.add(null); // HashSet and LinkedHashSet allow null, TreeSet does not
            System.out.println("Null is allowed in " + set.getClass().getSimpleName());
        } catch (NullPointerException e) {
            System.out.println("Null is NOT allowed in " + set.getClass().getSimpleName());
        }

        // Printing elements
        System.out.println("Elements in " + set.getClass().getSimpleName() + ": " + set);

        // Removing an element
        set.remove("Banana");
        System.out.println("After removing 'Banana': " + set);

        // Checking for the existence of an element
        boolean containsMango = set.contains("Mango");
        System.out.println("Does the set contain 'Mango'? " + containsMango);
    }
}

