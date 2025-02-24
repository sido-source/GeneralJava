package map;

import java.util.HashMap;
import java.util.Map;

public class SplitArrayConsecutiveSubsequences {

    // tags: repeat, hard one xd
    //https://leetcode.com/problems/split-array-into-consecutive-subsequences/description/

    public boolean isPossible(int[] nums) {
        // Step 1: Frequency map to count occurrences of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: Map to track how many subsequences can be extended
        Map<Integer, Integer> appendFreq = new HashMap<>();

        // Step 3: Iterate through nums to build subsequences
        for (int num : nums) {
            if (freq.get(num) == 0) {
                // If the number is already used, skip it
                continue;
            }

            // Try to append `num` to an existing subsequence
            if (appendFreq.getOrDefault(num - 1, 0) > 0) {
                appendFreq.put(num - 1, appendFreq.get(num - 1) - 1);
                appendFreq.put(num, appendFreq.getOrDefault(num, 0) + 1);
            }
            // Try to create a new subsequence [num, num + 1, num + 2]
            else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                appendFreq.put(num + 2, appendFreq.getOrDefault(num + 2, 0) + 1);
            } else {
                // If neither option is possible, return false
                return false;
            }

            // Decrement the count of the current number
            freq.put(num, freq.get(num) - 1);
        }

        // If all numbers are used properly, return true
        return true;
    }

    public boolean isPossible1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
        for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
        for (int i : nums) {
            if (freq.get(i) == 0) continue;
            else if (appendfreq.getOrDefault(i,0) > 0) {
                appendfreq.put(i, appendfreq.get(i) - 1);
                appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
            }
            else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
            }
            else return false;
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayConsecutiveSubsequences solution = new SplitArrayConsecutiveSubsequences();

        // Example 1
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        System.out.println(solution.isPossible(nums1)); // Output: true

        // Example 2
        int[] nums2 = {1, 2, 3, 3, 4, 4, 5, 5};
        System.out.println(solution.isPossible(nums2)); // Output: true

        // Example 3
        int[] nums3 = {1, 2, 3, 4, 4, 5};
        System.out.println(solution.isPossible(nums3)); // Output: false
    }
}

