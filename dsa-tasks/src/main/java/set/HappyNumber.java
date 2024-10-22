package set;

import java.util.HashSet;
import java.util.Set;

//REPEAT!, IMPORTANT,
public class HappyNumber {
    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    private static int getNext(int number) {
        int totalSum = 0;
        while (number > 0) {
            int digit = number % 10;
            totalSum += digit * digit;
            number /= 10;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        // Test the isHappy function
        int n1 = 19;
        int n2 = 2;

        System.out.println("Is " + n1 + " a happy number? " + isHappy(n1)); // Output: true
        System.out.println("Is " + n2 + " a happy number? " + isHappy(n2)); // Output: false
    }
}

/*
public static boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        // Tortoise and Hare cycle detection
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);       // Move slow pointer by one step
            fast = getNext(getNext(fast)); // Move fast pointer by two steps
        }

        return fast == 1;
    }

    private static int getNext(int number) {
        int totalSum = 0;
        while (number > 0) {
            int digit = number % 10;
            totalSum += digit * digit;
            number /= 10;
        }
        return totalSum;
    }
 */
