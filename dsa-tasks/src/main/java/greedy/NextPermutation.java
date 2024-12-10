package greedy;

public class NextPermutation {
    // tags: GS, repeat
// https://leetcode.com/problems/next-permutation/description/
    public void nextPermutation(int[] nums) {
        // Step 1: Find the rightmost pair where nums[i] < nums[i + 1]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If we found a valid i, find the smallest element larger than nums[i]
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap nums[i] and nums[j]
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements to the right of i to get the smallest order
        reverse(nums, i + 1, nums.length - 1);
    }

    // Helper function to swap elements
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Helper function to reverse a portion of the array
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {2, 1, 3};
        nextPermutation.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
