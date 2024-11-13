package slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams {
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (s.length() < p.length()) return res;

            int[] pFreq = new int[26];
            int[] windowFreq = new int[26];

            for (char c : p.toCharArray()) {
                pFreq[c - 'a']++;
            }

            // Initialize the window frequency for the first `p.length()` characters of `s`
            int start = 0;
            int end = 0;
            while (end < p.length()) {
                windowFreq[s.charAt(end) - 'a']++;
                end++;
            }

            // Check the initial window
            if (Arrays.equals(pFreq, windowFreq)) res.add(start);

            // Slide the window across the string `s`
            while (end < s.length()) {
                // Add new character to the window and remove the old one
                windowFreq[s.charAt(end) - 'a']++;
                windowFreq[s.charAt(start) - 'a']--;

                // Move the window
                start++;
                end++;

                // Check if the updated window is an anagram
                if (Arrays.equals(pFreq, windowFreq)) res.add(start);
            }

            return res;
        }


}
