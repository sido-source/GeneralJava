package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PermuteUnique {


    static List<List<Integer>> res;

    public static List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to handle duplicates
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public static void dfs(List<List<Integer>> response, List<Integer> curr, int[] nums, boolean[] used) {

        // Base case: if the current permutation is complete
        if (curr.size() == nums.length) {
            response.add(new ArrayList<>(curr)); // Add a copy of curr to response
            return;
        }

        // Recursive logic to explore permutations
        for (int i = 0; i < nums.length; i++) {
            // Skip the used element
            if (used[i]) continue;

            // Skip duplicates by checking if the current number is the same as the previous one
            // and the previous one has not been used in this recursive call
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // Add the current element to the current permutation
            curr.add(nums[i]);
            used[i] = true; // Mark this element as used

            // Recursively generate further permutations
            dfs(response, curr, nums, used);

            // Backtrack: remove the last element and mark it as unused
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(IntStream.of(2,3,2,1).toArray()));
    }
}
