package hashMaps;

import java.util.HashMap;

public class NearbyDuplicates {


    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 2, 3};
        int k = 3;

        System.out.println(containsNearbyDuplicate(nums, k)); // Output: true
    }


    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        // made byt sujal_1105

        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                int index=map.get(nums[i]);
                if((i-index)<=k)
                    return true;
                else
                    map.put(nums[i],i);

            }
            else{
                map.put(nums[i],i);
            }
        }
        return false;
    }
}
