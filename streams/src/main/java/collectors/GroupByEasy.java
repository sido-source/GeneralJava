package collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByEasy {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", 30),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25)
        );

        Map<Integer, List<Person>> collect = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        List<Map.Entry<Integer, List<Person>>> list = collect.entrySet()
                .stream().filter(entrySet -> entrySet.getValue().getFirst().getName().length() > 4)
                .toList();
        System.out.println(collect);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
