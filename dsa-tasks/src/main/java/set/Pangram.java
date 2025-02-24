package set;

import java.util.HashSet;
import java.util.Set;

public class Pangram {

    public static boolean checkIfPangram(String sentence) {
        Set<Character> letters = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            // Add only alphabet characters to the set
            if (Character.isLetter(c)) {
                letters.add(Character.toLowerCase(c));
            }
            // If the set already has 26 letters, we can return true
            if (letters.size() == 26) {
                return true;
            }
        }
        // Check if we collected all 26 letters
        return letters.size() == 26;
    }

}
