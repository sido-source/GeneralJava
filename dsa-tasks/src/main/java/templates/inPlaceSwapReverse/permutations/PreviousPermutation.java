package templates.inPlaceSwapReverse.permutations;

import templates.inPlaceSwapReverse.Common;

import java.util.Arrays;

public class PreviousPermutation {


    public static void main(String[] args) {
        int[] nums = new int[] {2,4,2,1,3};
        System.out.println(Arrays.toString(nums));
        previousPermutation(nums);
        System.out.println(Arrays.toString(nums)); // Output: [2,4,1,3,2]
    }

    // the smallest permutation is when the all numbers are increasing like 1,2,3 the the graph visualization is like line from left-bottom to right-up
    static void previousPermutation(int[] nums) {

        int pivot = -1;

        // direction from left to right
        for (int i =0; i < nums.length-2; i++) {
            if (nums[i] > nums[i+1]) {
                pivot = i + 1;
                break;
            }
        }

        if (pivot == -1) {
            Common.reverse(nums, 0, nums.length);
            return;
        }

        // swap with the next right the smallest element
        for (int i = pivot; i < nums.length-1; i++ ) {
            if (nums[i] > nums[i+1]) {
                Common.swap(nums, pivot, i+1);
            }
        }

        // important to reverse from pivot + 1 !!
        Common.reverse(nums, pivot+1, nums.length-1);
    }
}
