package maxHeap.comperators;

import java.util.Comparator;

public class Intro {


    public static void main(String[] args) {

        /*
        The Comparator is like a rulebook that the PriorityQueue uses to figure out which item should be smaller or larger. Imagine you have a bunch of toy cars, and you want to put them in a line. How do you decide which car comes first?

        Maybe you line them up based on speed (fastest first).
        Maybe you line them up by size (smallest car first).
        Maybe you line them up by color (red cars first, then blue).
        The Comparator is that rule that tells the queue how to compare two cars (or in our case, two numbers, or pairs of numbers).

        How Does a Comparator Work?
        Think of two numbers, a and b. The Comparator tells us which one is "smaller" by following a simple rule:

        If it returns a negative number, it means "a is smaller than b."
        If it returns a positive number, it means "b is smaller than a."
        If it returns zero, it means "a and b are the same size."
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        };

        Comparator<Integer> comparator1 = (a, b) -> a - b;
        /*
        If a is smaller than b, a - b gives a negative result.
        If a is bigger than b, a - b gives a positive result.
        If they’re the same, a - b equals 0.

        // if there is a decreasing order then each comperator should return positive result
        // if there is a increasing order then each comperator should return negative result
         */

        Comparator<String> comparator2 = String::compareTo;



    }
}
