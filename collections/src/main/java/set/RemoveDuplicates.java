package set;

import java.util.*;

public class RemoveDuplicates {

    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        // Create a HashSet to store unique elements
        Set<Integer> s = new HashSet<>();

        // Add elements of the nums array to the set
        for (int num : nums) {
            s.add(num);
        }

        // Convert the set to a list so we can sort it
        List<Integer> sortedList = new ArrayList<>(s);
        Collections.sort(sortedList);

        // Get the size of the set (number of unique elements)
        int size = sortedList.size();
        int index = 0;

        // Copy the sorted elements back to the nums array
        for (Integer i : sortedList) {
            nums[index++] = i;
        }

        // Set the remaining positions in the nums array to 0
        for (int i = size; i < nums.length; i++) {
            nums[i] = 0;
        }

        return size;
    }

    public int removeDuplicates1(int[] nums) {
        // Convert int[] to Integer[] using boxing
        Integer[] numsBoxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        // Create a HashSet from the Integer[] array
        Set<Integer> s = new HashSet<>(Arrays.asList(numsBoxed));

        // Rest of the logic is the same
        int size = s.size();
        int index = 0;
        for (Integer i : s) {
            nums[index++] = i;
        }
        for (int i = size; i < nums.length; i++) {
            nums[i] = 0;
        }
        return size;
    }
}
