import java.util.List;

public class Stateless_Stateful_operations {


    public static void main(String[] args) {

        System.out.println("Stateless operation : filter");
        List.of("Tom", "Bob", "Alice", "Kaya")
                .stream()
                .peek(name -> System.out.println("Start processing name: " + name))
                .filter(name -> {
                    System.out.println("Filtering name: " + name);
                    return name.contains("a");
                })
                .peek(name -> System.out.println("Found name that contains 'a' : " + name))
                .forEach(System.out::println);


        System.out.println('\n' + "Stateful operation : sorted");
        // need to process entire input before producing results
        List.of("Tom", "Bob", "Alice", "Kaya")
                .stream()
                .peek(name -> System.out.println("Start processing name: " + name))
                .sorted() // the elements go one by one, but then the sort has to remember all elements and compare them in right order
                .peek(name -> System.out.println("Sorted name: " + name))
                .forEach(System.out::println);
    }
}
