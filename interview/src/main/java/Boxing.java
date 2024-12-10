import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Boxing {

    public static void main(String[] args) {
        int[] array = List.of(1, 2, 3, 4).stream().mapToInt(Integer::intValue).toArray();


        List<Character> list = List.of('a', 'b', 'c', 'd');
        char[] arr = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        System.out.println(Arrays.toString(arr)); // [a, b, c, d]



        Boxing b = new Boxing();

    }


    //For example, if s is "cbaebabacd" and p is "abc", then the function should return [0, 6]


    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // Sort nums1
        Arrays.sort(nums2); // Sort nums2
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++; // Move the pointer in nums1
            } else if (nums1[i] > nums2[j]) {
                j++; // Move the pointer in nums2
            } else {
                res.add(nums1[i]); // Add common element
                i++;
                j++;
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}
