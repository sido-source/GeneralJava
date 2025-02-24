package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;


// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

public class PhoneLetterCombinations {
    // Map to store the digit-to-letters mapping.
    private static final Map<Character, String> DIGIT_TO_LETTER_MAP = new HashMap<>();

    static {
        DIGIT_TO_LETTER_MAP.put('2', "abc");
        DIGIT_TO_LETTER_MAP.put('3', "def");
        DIGIT_TO_LETTER_MAP.put('4', "ghi");
        DIGIT_TO_LETTER_MAP.put('5', "jkl");
        DIGIT_TO_LETTER_MAP.put('6', "mno");
        DIGIT_TO_LETTER_MAP.put('7', "pqrs");
        DIGIT_TO_LETTER_MAP.put('8', "tuv");
        DIGIT_TO_LETTER_MAP.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        // Result list to store the final combinations
        List<String> result = new ArrayList<>();

        // Base case: if the input is empty, return an empty list
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Call the backtracking function
        backtrack(result, new StringBuilder(), digits, 0);

        return result;
    }

    // connection between index == digits.length() is crucial, so digits combination can have max the index letters (index is the position of the specific element/char of input data string)
    private void backtrack(List<String> result, StringBuilder currentCombination, String digits, int index) {
        // Base case: if we have processed all digits, add the combination to the result
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // Get the current digit and the corresponding letters from the map
        char currentDigit = digits.charAt(index);
        String letters = DIGIT_TO_LETTER_MAP.get(currentDigit);  // Get the letters for this digit

        // For each letter corresponding to the current digit, add it to the combination
        // and recurse to process the next digit
        for (char letter : letters.toCharArray()) {
            currentCombination.append(letter);  // Add the letter
            backtrack(result, currentCombination, digits, index + 1);  // Recurse for the next digit
            currentCombination.deleteCharAt(currentCombination.length() - 1);  // Backtrack
        }
    }



    // solution 2
    private static final Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        backtrack1(result, "", digits);
        return result;
    }

    private void backtrack1(List<String> result, String current, String remaining) {
        if (remaining.length() == 0) {
            result.add(current);
            return;
        }
        String letters = phoneMap.get(remaining.charAt(0));
        for (char letter : letters.toCharArray()) {
            backtrack1(result, current + letter, remaining.substring(1));
        }
    }

    public static void main(String[] args) {
        PhoneLetterCombinations main = new PhoneLetterCombinations();
        System.out.println(main.letterCombinations("23"));  // Example test case
    }
}
