package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        // Handle edge case for invalid input
        if (n < 1) {
            return result;
        }

        // Call the DFS function to start generating valid combinations
        dfs(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    // DFS helper method with backtracking
    private void dfs(List<String> result, StringBuilder current, int open, int close, int n) {
        // Base case: when the current string has 'n' open and 'n' close parentheses
        if (open == n && close == n) {
            result.add(current.toString());
            return;
        }

        // Add an open parenthesis if we can (open < n)
        if (open < n) {
            current.append("(");  // Add open parenthesis
            dfs(result, current, open + 1, close, n);  // Recursively try adding more
            current.deleteCharAt(current.length() - 1);  // Backtrack (remove last char)
        }

        // Add a close parenthesis if we can (close < open)
        if (close < open) {
            current.append(")");  // Add close parenthesis
            dfs(result, current, open, close + 1, n);  // Recursively try adding more
            current.deleteCharAt(current.length() - 1);  // Backtrack (remove last char)
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis solution = new GenerateParenthesis();
        System.out.println(solution.generateParenthesis(3));  // Example test case
    }
}
