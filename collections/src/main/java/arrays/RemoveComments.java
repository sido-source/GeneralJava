package arrays;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

        public List<String> removeComments(String[] source) {
            List<String> res = new ArrayList<>();
            boolean isBlock = false; // Tracks whether we're inside a block comment
            StringBuilder newline = new StringBuilder(); // Temporary line buffer

            for (String line : source) {
                int i = 0;
                if (!isBlock) {
                    newline.setLength(0); // Clear buffer if outside of a block comment
                }
                char[] arr = line.toCharArray();
                while (i < arr.length) {
                    // Detect start of block comment
                    if (!isBlock && i + 1 < arr.length && arr[i] == '/' && arr[i + 1] == '*') {
                        isBlock = true;
                        i++; // Skip '*' after '/'
                    }
                    // Detect end of block comment
                    else if (isBlock && i + 1 < arr.length && arr[i] == '*' && arr[i + 1] == '/') {
                        isBlock = false;
                        i++; // Skip '/' after '*'
                    }
                    // Detect line comment
                    else if (!isBlock && i + 1 < arr.length && arr[i] == '/' && arr[i + 1] == '/') {
                        break; // Ignore the rest of the line
                    }
                    // Add valid characters to buffer
                    else if (!isBlock) {
                        newline.append(arr[i]);
                    }
                    i++;
                }
                // Append non-empty lines to result
                if (!isBlock && newline.length() > 0) {
                    res.add(newline.toString());
                }
            }
            return res;
        }

        public static void main(String[] args) {
            RemoveComments solution = new RemoveComments();

            // Example 1
            String[] source1 = {
                    "/*Test program */",
                    "int main()",
                    "{ ",
                    "  // variable declaration ",
                    "int a, b, c;",
                    "/* This is a test",
                    "   multiline  ",
                    "   comment for ",
                    "   testing */",
                    "a = b + c;",
                    "}"
            };
            System.out.println(solution.removeComments(source1));

            // Example 2
            String[] source2 = {"a/*comment", "line", "more_comment*/b"};
            System.out.println(solution.removeComments(source2));
        }

}
