package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class TakeCharacter {
    // tags: sliding window, hash map
    //https://leetcode.com/problems/take-k-of-each-character-from-left-and-right
    // solution mostly get from: Piotr Maminski
    public int takeCharacters(String s, int k) {
        // Total counts
        int[] count = new int[3];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        if (count[0] < k ||  count[1] < k || count[2] < k) {
            return -1;
        }

        // Sliding Window - we are trying to find the max subsequence window of the letters that we can skip
        // but in order to do it we have to make sure that substrings from (0,l) and (r, s.length) contains each of a,b,c >= k
        int res = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;

        while (l < count.length) {
            count[s.charAt(r) - 'a']--;

            while (count[0] <= k &&  count[1] <= k && count[2] <= k) {
                count[s.charAt(l) - 'a']++;
                l++;
            }
            res = Math.min(res, s.length() - (r - l + 1));
            r++;
        }
        return res;
    }


    public static void main(String[] args) {
        TakeCharacter takeCharacter = new TakeCharacter();
        System.out.println(takeCharacter.takeCharacters("aabaaaacaabc", 2)); // Output: 6
    }

}
