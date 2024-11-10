package slidingWindow;

import java.util.HashMap;

public class CountSubstringsWithKFrequencyCharacters {


    public static int countSubstringsWithKFrequency(String s, int k) {
        int count = 0;

        for (int start = 0; start < s.length(); start++) {
            HashMap<Character, Integer> freqMap = new HashMap<>();

            for (int end = start; end < s.length(); end++) {
                char c = s.charAt(end);
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

                // Check if there's at least one character with frequency >= k
                boolean hasKFrequency = false;
                for (int freq : freqMap.values()) {
                    if (freq >= k) {
                        hasKFrequency = true;
                        break;
                    }
                }

                if (hasKFrequency) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countSubstringsWithKFrequency1(String s, int k) {
        int count = 0;
        int start = 0;
        int end = 0;
        HashMap<Character, Integer> freqMap = new HashMap<>();

        while (end < s.length()) {
            // Expand the window by including the character at 'end'
            char endChar = s.charAt(end);
            freqMap.put(endChar, freqMap.getOrDefault(endChar, 0) + 1);

            // While we have at least one character in the window with frequency >= k
            while (hasKFrequency(freqMap, k)) {
                // All substrings from 'start' to 'end' are valid
                count += s.length() - end;

                // Shrink the window from the left by removing the character at 'start'
                char startChar = s.charAt(start);
                freqMap.put(startChar, freqMap.get(startChar) - 1);
                if (freqMap.get(startChar) == 0) {
                    freqMap.remove(startChar);
                }
                start++;
            }

            // Move the end pointer to expand the window
            end++;
        }

        return count;
    }

    private static boolean hasKFrequency(HashMap<Character, Integer> map, int k) {
        for (int freq : map.values()) {
            if (freq >= k) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "wabacbr";
        int k = 2;
        System.out.println("Count of substrings with at least one character appearing at least " + k + " times: " + countSubstringsWithKFrequency1(s, k));
    }
}
