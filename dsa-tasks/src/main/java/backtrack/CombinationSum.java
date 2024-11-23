package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {


    // this solution is wrong because it contains duplicates
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        // 1 Constraint- when the sum is greter return, if sum is equal add it to res, make sure combination is unique
        // 2 Options - each combinations with duplicates
        // 3 Decisions - add


        List<List<Integer>> res = new ArrayList<>();

        dfs1(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }

    public void dfs1(List<List<Integer>> res, List<Integer> curr, int[] candidates, int currSum, int target) {
        if (currSum > target) {
            return;
        } else if (currSum == target) {
            res.add(new ArrayList<>(curr));
        }

        for(int i : candidates) {
            curr.add(i);
            currSum += i;
            dfs1(res, curr, candidates, currSum, target);

            //backtrack
            currSum -= i;
            curr.removeLast();
        }
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Sort to ensure combinations are generated in ascending order
        Arrays.sort(candidates);

        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), candidates, 0, 0, target);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> curr, int[] candidates, int start, int currSum, int target) {
        // Base case: If the sum exceeds the target, stop further exploration
        if (currSum > target) {
            return;
        }

        // Base case: If the sum equals the target, add the current combination
        if (currSum == target) {
            res.add(new ArrayList<>(curr)); // Add a copy of the current combination
            return;
        }

        // Explore all options starting from the current index
        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]); // Choose the current number
            currSum += candidates[i]; // Update the current sum

            // Recur with the updated combination and sum
            dfs(res, curr, candidates, i, currSum, target); // Allow reuse of the same element (index `i`)

            // Backtrack to explore other options
            currSum -= candidates[i];
            curr.remove(curr.size() - 1); // Undo the choice
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
