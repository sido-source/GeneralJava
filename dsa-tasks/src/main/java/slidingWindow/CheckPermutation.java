package slidingWindow;

import java.util.Arrays;

/*

567. Permutation in String
https://leetcode.com/problems/permutation-in-string/


This problem is a classic application of the sliding window technique combined with character frequency tracking. The idea is to check if s1's permutation is a substring of s2, which translates to finding a substring in s2 that has the same character frequencies as s1.

Here's a step-by-step breakdown of how to approach this:

Step 1: Problem Analysis
We need to determine if any permutation of s1 is a substring of s2.
This is equivalent to finding any substring of s2 with the same character frequencies as s1.
Step 2: Setting Up Frequency Arrays
Create a frequency array freqS1 for s1 to track how often each character appears in s1.
Similarly, create a frequency array freqS2 that will keep track of characters within a sliding window on s2.


Step 3: Initializing the Frequency Array for s1
java
Copy code
int[] freqS1 = new int[26]; // Tracks frequency of each character in s1
for (char c : s1) {
    freqS1[c - 'a']++;
}
This loop fills freqS1 with the character counts of s1.
Step 4: Sliding Window over s2
Define two pointers, start and end, which represent the window in s2.
Slide this window over s2 while adjusting the frequency of characters in freqS2.
Step 5: Expand the Window and Update freqS2
For each character in s2 from start to end, update freqS2 by incrementing the frequency of s2[end].

 */
public class CheckPermutation {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];

        // Fill freqS1 for characters in s1
        for (char c : s1.toCharArray()) {
            freqS1[c - 'a']++;
        }

        int start = 0;

        // Sliding window over s2
        for (int end = 0; end < s2.length(); end++) {
            freqS2[s2.charAt(end) - 'a']++;

            // Check window size
            if (end - start + 1 == s1.length()) {
                if (Arrays.equals(freqS1, freqS2)) {
                    return true;
                }

                // Slide the window
                freqS2[s2.charAt(start) - 'a']--;
                start++;
            }
        }

        return false;
    }
}
