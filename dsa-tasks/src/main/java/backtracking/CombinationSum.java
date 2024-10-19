package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {


    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = combinationSum(candidates, target);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, res, new ArrayList<>(), 0, target);

        return res;
    }

    // choices | response | currently choose choices | constraints
    public static void dfs(int[] candidates, List<List<Integer>> res, List<Integer> curr, int sum, int target) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            res.add(new ArrayList(curr));
            return ;
        }


        for (int candidate : candidates) {
            curr.add(candidate);

            // sum is going to be sum + candidate in dfs
            dfs(candidates, res, curr, sum + candidate, target);

            //backtracking

            //but here the sum is still the same as it comes through input params
            // sum = sum - candidate; // so we dont need to do this

            curr.remove(curr.size()-1);

        }
    }
}
