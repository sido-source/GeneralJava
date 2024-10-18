package arrays;

public class RemoveDuplicates {

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,1,2,2,3};
        removeDuplicates(nums);

        for (int i : nums) {
            System.out.print(" " + i);
        }
    }

    // Input: nums = [1,1,1,2,2,3]
    // Output: 5, nums = [1,1,2,2,3,_]
    // only 2 occurrences near  each other

    public static int removeDuplicates(int[] nums) {
        int prev = Integer.MIN_VALUE;
        int currIndex = 0;
        int replicas = 0;

        for (int num : nums) {
            if (prev != num) {
                System.out.print("new: " + num +"\n");
                nums[currIndex++] = num;
                replicas = 1;
                prev=num;
                continue;
            }

            if (prev == num && replicas == 1) {
                System.out.print("replicas: " + num +"\n");
                nums[currIndex++] = num;
                replicas++;
                prev=num;
            }
        }

        System.out.print(currIndex + "\n");
        return currIndex;
    }

}

