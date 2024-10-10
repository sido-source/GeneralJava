package sort.other;

import java.util.HashMap;
import java.util.Map;

//75. Sort Colors
// tags: #SORT, #INPLACE

public class ColorSort {
    public void sortColors(int[] nums) {

        Map<Integer,Integer> valuesAndFreq = new HashMap<>();

        for (int i = 0; i<nums.length; i++) {
            valuesAndFreq.put(nums[i], valuesAndFreq.getOrDefault(nums[i],0)+1);
        }

        int idx = 0;
        for (Integer i : valuesAndFreq.keySet()) {
            int freq = valuesAndFreq.getOrDefault(i, 0);
            for (int j = 0; j < freq; j++) {
                nums[idx] = i;
                idx++;
            }
        }
    }

    public void sortColors1(int[] nums) {
        int zeros = 0, ones  = 0, n = nums.length;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0) zeros++;
            if(nums[i] == 1) ones++;
        }

        for(int i=0;i<zeros;i++){
            nums[i] = 0;
        }
        for(int i=zeros; i<zeros+ones;i++){
            nums[i] = 1;
        }
        for(int i=zeros+ones;i<n;i++){
            nums[i] = 2;
        }
    }
}
