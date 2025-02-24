package map;

import java.util.HashMap;

public class ContainsNearbyDuplicate {

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
    }


    public static boolean containsNearbyDuplicate(int[] nums, int k) {
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
