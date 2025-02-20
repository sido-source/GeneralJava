package binarySearch;

public class SearchInRotatedArray {

    // tags: repeat
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
    // we can use binarySearch beacause we have monotonic values, we can also use True or False
    // like {4,5,6,7,0,1,2}; becasomes T,T,T,T,T,F,F
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int result = findMin(nums);
        System.out.println("Index of " + target + " is: " + result);
    }


    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left]; // the same nums[right]
    }

    /// sol 2

    public int search1(int[] nums, int target) {
        int n = nums.length;
        int pivot = findPivot(nums);

        // Binary search in the left sorted part if target is within range
        if (target >= nums[0] && target <= nums[pivot - 1]) {
            return binarySearch(nums, 0, pivot - 1, target);
        }
        // Binary search in the right sorted part if target is within range
        else {
            return binarySearch(nums, pivot, n - 1, target);
        }
    }

    // Helper method to find the pivot (index of the smallest element)
    private int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1; // Pivot is in the right half
            } else {
                right = mid; // Pivot is in the left half or at mid
            }
        }
        return left; // Pivot is the index of the smallest element
    }

    // Helper method for binary search
    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Target not found
    }
}
