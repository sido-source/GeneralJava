import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionExamples {


    public static void main(String[] args) {

        // ArrayList
        Collection<Integer> numbers = new ArrayList<>(3);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // shallow copy = deep copy in case of immutable objects like Integer
        Collection<Integer> numbers2 = new ArrayList<>(numbers);
        numbers.add(5);
        numbers2.remove(1);

        System.out.println("Original numbers: " + numbers);
        System.out.println("Copied numbers: " + numbers2);

        boolean b = numbers.containsAll(List.of(1, 2));
        System.out.println(b);

        numbers.removeIf((a) -> a%2 == 0);
        System.out.println(numbers);

        numbers.addAll(List.of(100));
        System.out.println(numbers);

        System.out.println(numbers.size());
    }
}
