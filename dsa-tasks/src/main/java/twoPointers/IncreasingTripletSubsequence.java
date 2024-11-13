package twoPointers;

import java.util.ArrayList;
import java.util.List;

public class IncreasingTripletSubsequence {

    //334. Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {

        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i : nums) {
            if (i < firstMin) {
                firstMin = i;
            } else if (i > firstMin && i < secondMin) {
                secondMin = i;
            } else if (i > secondMin) {
                // We found a valid triplet
                return true;
            }
        }

        return false;
    }

    public List<List<Integer>> findAllIncreasingTriplets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Triple nested loop to find all (i, j, k) combinations
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] < nums[j]) {  // Check if nums[i] < nums[j]
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[j] < nums[k]) {  // Check if nums[j] < nums[k]
                            // Add the triplet indices to the result list
                            List<Integer> triplet = new ArrayList<>();
                            triplet.add(i);
                            triplet.add(j);
                            triplet.add(k);
                            result.add(triplet);
                        }
                    }
                }
            }
        }

        return result;
    }

    static public int lengthOfLIS(int[] nums) {

        // sliding window + variable
        int maxLength = 0;
        int currLength = 0;
        int start = 0;
        int end = 0;
        int numsLength = nums.length-1;
        int lastMinValue = Integer.MIN_VALUE;

        while (start < end) {

            if (nums[end] > lastMinValue) {
                lastMinValue = nums[end];
                currLength++;
                maxLength = Math.max(currLength, maxLength);
                if (end < numsLength) end++;
            } else {
                currLength = 0;
                lastMinValue = nums[end];
                start++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence increasingTriplet = new IncreasingTripletSubsequence();
        int[] nums = {2,1,5,0,4,6};
        System.out.println(increasingTriplet.increasingTriplet(nums));  // Output: true


        List<List<Integer>> triplets = increasingTriplet.findAllIncreasingTriplets(nums);
        System.out.println(triplets);  // Output: [[0, 1, 2]]
    }
}
