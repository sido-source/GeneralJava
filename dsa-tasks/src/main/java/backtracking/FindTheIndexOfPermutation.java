package backtracking;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FindTheIndexOfPermutation {


    public static void main(String[] args) {
        int [] arr = new int[]{3,1,2};


    }


    public static int findPermutation(int[] arr) {
        Set<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toSet());

        // have a colllect of the nums

        // start with the first letter and start create permutation

        //dfs()
    }

}
