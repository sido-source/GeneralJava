package arrays.greedy;

public class CanJump {

    public boolean canJump(int[] nums) {
        // start from the end
        int target = nums.length - 1;


        for (int index = nums.length - 2; index >= 0 ; index--) {
            if (nums[index] + index >= target) {
                target = index;
            }
        }

        return target == 0;
    }
}
