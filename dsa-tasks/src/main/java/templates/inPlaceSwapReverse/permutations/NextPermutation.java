package templates.inPlaceSwapReverse.permutations;

import org.junit.jupiter.api.Test;
import templates.inPlaceSwapReverse.Common;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NextPermutation {

    // this task is about find the right decreasing sub-array
    // also we can visualize using graph, so the array with decreasing all elements will be the last permutation lexicographically (on the graph it will be the line from up-left(peak) to right-down)


    public static void main(String[] args) {
        int[] nums = {5, 2, 1, 7, 4, 2};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // Output: [5, 2, 2, 1, 4, 7]
    }

    static int[] nextPermutation(int[] initPermutation) {

        int pivot = -1; // the most rightside element from initPermutation that would be changed

        // direction from right to left
        for (int i = initPermutation.length-1; i > 1; i--) {
            // find the first number that is greater than the previous number
            if (initPermutation[i] > initPermutation[i-1]) {
                pivot = i-1;
                break;
            }
        }

        // if we dont find the pivot
        if (pivot == -1) {
            Common.reverse(initPermutation, 0, initPermutation.length-1);
            return initPermutation;
        }

        // we found the pivot so we have to find the NEXT GREATER element than initPermutation[pivot] - remember that this part is sorted descending
        int nextGreaterIndex = initPermutation.length-1;
        for ( int i = initPermutation.length-1; i > pivot; i--) {
            if (initPermutation[pivot] < initPermutation[i]) {
                nextGreaterIndex = i;
                break;
            }
        }

        // then swap these - pivot and nextGreater
        Common.swap(initPermutation, pivot, nextGreaterIndex);

        // Step 4: Reverse the suffix starting right after the pivot
        Common.reverse(initPermutation, pivot+1, initPermutation.length-1);

        return new int[]{pivot};
    }



    @Test
    public void findThePivot() {
        int[] nums = {5, 2, 1, 7, 4, 2};
        int pivot = -1;
        for (int i = nums.length - 1; i > 1; i--) {

            // find the first number that is greater than the previous number
            if (nums[i] > nums[i - 1]) {
                pivot = i-1;
                break;
            }
        }

        assertEquals(pivot, 2);
    }

}
