package reduce;

import java.util.Arrays;

public class TheLongestWord {

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cherry", "date"};
        String longestWord = Arrays.stream(words)
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);
        System.out.println(longestWord);  // Output: "banana"

    }
}
