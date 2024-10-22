package collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // Stream the array, box the int[] to Integer[], and collect into a sorted list
        List<Integer> sortedList = Arrays.stream(nums)
                .boxed()  // Convert int to Integer
                .distinct()  // Remove duplicates
                .sorted()  // Sort the elements
                .collect(Collectors.toList());  // Collect into a list

        // Copy the sorted, unique elements back to the nums array
        int size = sortedList.size();
        int index = 0;
        for (Integer i : sortedList) {
            nums[index++] = i;
        }

        // Set the remaining positions in the nums array to 0
        for (int i = size; i < nums.length; i++) {
            nums[i] = 0;
        }

        return size;
    }
}
