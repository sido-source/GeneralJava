package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {

    // space complexity: o(n) - because the max stack size is the max depth of the backtrack tree - recursion stack, which is independent of the heap space used by the result list (list) and temporary list (tempList).
    // time complexity: o(n * 2^n) - because there are 2^n subsets (each number either can be or can't be included)
    // and on each step we are copy the tempList into resultList what takes o(n)
    // below there is a countSubsets which has a o(2^n) time complexity, because it doesn't return the List result of subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            // include specific number
            tempList.add(nums[i]);

            // Explore next subsets starting from the next index (i+ 1)
            // we have to track elements i+1 because other way we would iterate in the loop and never stop !!!
            backtrack(list, tempList, nums, i + 1);

            // continue generating subsets without this specific number
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subset subset = new Subset();
        int[] nums = {1,2,3};
        List<List<Integer>> result = subset.subsets(nums);
        for (List<Integer> subsetList : result) {
            System.out.println(subsetList);
        }

        System.out.println("Only count subset : " + subset.countSubsets(nums));
    }

    public int countSubsets(int[] nums) {
        return backtrack(nums, 0);
    }

    private int backtrack(int[] nums, int start) {
        if (start == nums.length) {
            return 1; // Base case: one valid subset is counted
        }
        // Count subsets including nums[start] and excluding nums[start]
        int include = backtrack(nums, start + 1);
        int exclude = backtrack(nums, start + 1);
        return include + exclude;
    }

    /*
                                []
                   /                    \
              [1]                        []
           /      \                  /      \
      [1,2]      [1]            [2]         []
     /    \     /   \         /    \       /   \
[1,2,3] [1,2] [1,3] [1]   [2,3]  [2]    [3]   []

     */

}
