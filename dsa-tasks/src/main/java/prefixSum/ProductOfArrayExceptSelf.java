package prefixSum;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Calculate prefix product and store in result
        result[0] = 1; // Prefix product for the first element
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Calculate suffix product and update result
        int suffix = 1; // Initialize suffix product
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix; // Multiply current result by suffix product
            suffix *= nums[i]; // Update suffix product
        }

        return result;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();

        // Example 1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println(java.util.Arrays.toString(result1)); // Output: [24, 12, 8, 6]

        // Example 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.println(java.util.Arrays.toString(result2)); // Output: [0, 0, 9, 0, 0]



        //"foo", t = "bar"
        // map

    }


}

