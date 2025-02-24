package arrays;

public class MinimumSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int minimalSubArrayLength = Integer.MAX_VALUE;


        int startArr = 0;
        int currSum = 0;
        int currSize = 0;

        int currIndex = 0;
        while (currIndex < nums.length) {

            currSum =+ nums[currIndex];
            currSize++;

            if (currSum >= target) {
                minimalSubArrayLength = Math.min(minimalSubArrayLength, currSize);
                currSum = currSum - nums[startArr];
                startArr++;
                currSize--;
            }

            currIndex++;
        }
        return minimalSubArrayLength;
    }

    public static void main(String[] args) {

    }
}
