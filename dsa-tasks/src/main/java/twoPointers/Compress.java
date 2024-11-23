package twoPointers;

public class Compress {

    // https://leetcode.com/problems/string-compression/submissions/1460207477/

    public int compress(char[] chars) {
        int index = 0; // Pointer to write compressed characters
        int i = 0;     // Pointer to traverse the input array

        while (i < chars.length) {
            char currChar = chars[i];
            int count = 0;

            // Count occurrences of the current character
            while (i < chars.length && chars[i] == currChar) {
                count++;
                i++;
            }

            // Write the character
            chars[index++] = currChar;

            // Write the count if greater than 1
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[index++] = c;
                }
            }
        }

        return index; // New length of the compressed array
    }

    public static void main(String[] args) {
        Compress compress = new Compress();
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'};
        int compressedLength = compress.compress(chars);
    }
}
