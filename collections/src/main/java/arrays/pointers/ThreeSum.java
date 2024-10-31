package arrays.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        // 3 pointers technique with sliding widnow

        // [-1,0,1,2,-1,-4]
        // [-4,-1,-1,0,1,2]

        List<List<Integer>> res = new ArrayList<>();

        for (int i=0; i< nums.length-2; i++) {
            int currElem = nums[i];
            int left = i+1;
            int right = nums.length-1;

            while (left<right) {
                if (currElem + nums[left] + nums[right] == 0) {
                    res.add(List.of(currElem, nums[left], nums[right]));
                    right--;
                    left++;
                } else if ( currElem + nums[left] + nums[right] > 0) {
                    right--;
                } else if (currElem + nums[left] + nums[right] < 0) {
                    left++;
                }
            }
        }

        return res;

    }
}
