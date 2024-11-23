package binarySearch;

public class SearchInRotatedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid;
            }

            // Determine if the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Target is in the sorted left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else { // Target is in the right half
                    left = mid + 1;
                }
            } else { // Right half must be sorted
                // Target is in the sorted right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else { // Target is in the left half
                    right = mid - 1;
                }
            }
        }

        // Target was not found
        return -1;
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
