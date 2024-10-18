package set;

import java.util.HashSet;
import java.util.Iterator;

public class IntroSet {

    public static void main(String[] args) {


        // can't get some particular index if the set !!
        // we have to iterate through each set

        // 1 way
        HashSet<String> set = new HashSet<>();
        set.add("Element 1");
        set.add("Element 2");
        set.add("Element 3");

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);  // You can retrieve each element
        }

        //2. way, for each
        for (String el : set) {
            System.out.print(el);
        }

        // 3. to array

        String[] array = set.toArray(new String[0]);
        System.out.println(array[0]);  // Access element by index


        // using stream, functional

        String element = set.stream().reduce("", (a, b) -> a + " xd " + b);
        System.out.println(element);
    }
}
