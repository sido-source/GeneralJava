package slidingWindow;

public class NumberOfArithmeticSlices {

    public int numberOfArithmeticSlices(int[] nums) {
        int start = 0;
        int res = 0;

        if(nums.length<3) return 0;


        for (int end = 2; end < nums.length; end++) {
            while (end - start + 1 >= 3) {
                if (isArithmetic(nums, start, end)) res++;
                start++;
            }
            start=0;
        }

        return res;
    }

    boolean isArithmetic(int[] nums, int start, int end) {

        int substractor = nums[start+1] - nums[start];
        for(int i = start + 1 ; i <= end; i++) {
            if (nums[i] - nums[i-1] != substractor) return false;
        }

        return true;
    }

    public int numberOfArithmeticSlices1(int[] nums) {
        int count = 0;
        int n = nums.length;
        for(int start = 0; start < n - 2; start++) {
            int d = nums[start + 1] - nums[start];
            for(int end = start + 2; end < n; end++) {
                if(nums[end] - nums[end-1] == d) {
                    count++;
                }
                else {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfArithmeticSlices numberOfArithmeticSlices = new NumberOfArithmeticSlices();
        int[] nums = {1,2,3,5,8,9,10};
        System.out.println(numberOfArithmeticSlices.numberOfArithmeticSlices(nums)); // Output: 3
    }
}
