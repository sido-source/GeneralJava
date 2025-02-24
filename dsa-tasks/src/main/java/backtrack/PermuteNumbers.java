package backtrack;

import java.util.ArrayList;
import java.util.List;

public class PermuteNumbers {
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {

        res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> curr, int[] nums){

        // base cases
        if (nums.length == curr.size()) {
            //res.add(curr); //is wrong. In the base case, instead of res.add(curr), you now use res.add(new ArrayList<>(curr)). This ensures you're adding a copy of curr to res so that further modifications to curr won't affect the already added permutations.
            // What happens if you add curr directly? If you add curr directly with res.add(curr) (without making a copy), every modification to curr will affect all elements previously added to res because you're adding the reference to the same curr list.
            res.add(new ArrayList<>(curr));
        }


        // logic
        for (Integer now : nums) {
            if (curr.contains(now)) {
                continue;
            }

            curr.add(now);
            dfs(res, curr, nums);
            //curr.remove(curr.size()-1);
            curr.removeLast(); //backtract

        }

    }

    public static void main(String[] args) {
        PermuteNumbers p = new PermuteNumbers();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = p.permute(nums);
        System.out.println(res);
    }
}
