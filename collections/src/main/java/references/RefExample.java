package references;

import java.util.ArrayList;
import java.util.List;

public class RefExample {

    public static void main(String[] args) {
        // Working with an array
        int[] numbers = {1, 2, 3};
        int[] refArray = numbers; // refArray references the same array object

        System.out.println("Before modifying refArray:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Modifying the array via the reference
        refArray[0] = 42;

        System.out.println("After modifying refArray:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Working with a list
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");

        List<String> refList = names; // refList references the same list object

        System.out.println("\nBefore modifying refList:");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // Modifying the list via the reference
        refList.add("Charlie");

        System.out.println("After modifying refList:");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // Reassigning the reference
        refList = new ArrayList<>();
        refList.add("New Name");

        System.out.println("\nAfter reassigning refList:");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();
    }
}

