package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        // Edge case: if input is empty, return an empty list
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        // Map to store digit-to-letters mapping
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, map, digits, 0);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, Map<Character, String> map, String digits, int index) {
        // 1. Constraint / Base case: if the combination length matches the input digits, add to result
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        // 2. Options - Get the letters for the current digit
        char digit = digits.charAt(index);
        String letters = map.get(digit);

        // Explore all possible letters for the current digit
        for (char letter : letters.toCharArray()) {
            sb.append(letter); // 3. Decision -  Choosing one letter and adding it to the current path.
            dfs(res, sb, map, digits, index + 1); // Explore
            sb.deleteCharAt(sb.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();
        System.out.println(solution.letterCombinations("23")); // Example test case
    }
}

