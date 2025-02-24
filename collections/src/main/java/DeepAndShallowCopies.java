import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    Person(String name) { this.name = name; }
    public String toString() { return name; }
}

public class DeepAndShallowCopies {
    public static void main(String[] args) {
        shallowCopy();
        deepCopy();
    }

    private static void deepCopy() {
        List<Person> originalList = new ArrayList<>(List.of(new Person("Alice"), new Person("Bob")));

        // Deep copy using copy constructor
        List<Person> deepCopy = new ArrayList<>();
//        for (Person p : originalList) {
//            deepCopy.add(new Person(p.name));  // Create a new instance for each object
//        }
        deepCopy = originalList.stream().map(person -> new Person(person.name)).toList();

        // Modify the object in deepCopy
        deepCopy.get(0).name = "Charlie";

        System.out.println("Original List: " + originalList); // [Alice, Bob]
        System.out.println("Deep Copy: " + deepCopy);         // [Charlie, Bob]
    }

    private static void shallowCopy() {
        List<Person> originalList = new ArrayList<>(List.of(new Person("Alice"), new Person("Bob")));

        // Shallow copy
        List<Person> shallowCopy = new ArrayList<>(originalList);

        // Modify the object in shallowCopy
        shallowCopy.get(0).name = "Charlie";

        System.out.println("Original List: " + originalList); // [Charlie, Bob]
        System.out.println("Shallow Copy: " + shallowCopy);   // [Charlie, Bob]
    }
}
