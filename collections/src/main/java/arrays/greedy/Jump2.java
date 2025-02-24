package arrays.greedy;

import java.util.ArrayList;
import java.util.List;

public class Jump2 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums)); // Output: 2

        int[] nums1 = {4, 3, 1, 1, 4};
        System.out.println(jump(nums1)); // Output: 2
    }

    public static int jump(int[] nums) {
        if (nums.length <= 1) return 0; // No jumps needed if we’re already at the last index

        int jumps = 0;
        int currentEnd = 0; // End of the current jump range
        int farthest = 0; // Farthest we can reach with the next jump

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // Update the farthest reach

            // we are changing currentEnd to the farthest jump location
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // If currentEnd reaches or exceeds the last index, break early
                if (currentEnd >= nums.length - 1) break;
            }
        }

        return jumps;
    }


}
