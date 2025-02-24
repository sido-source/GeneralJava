package arrays;

public class MergeSort {

    public static void main(String[] args) {
    int[] nums1 = new int[]{4,5,6,0,0,0};
    int[] nums2 = new int[]{1,2,3};
    int m = nums1.length;
    int n = nums2.length;
    merge(nums1, n, nums2, n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int index = nums1.length;

        while (m > 0 && n > 0) {
            if (nums1[m-1] > nums2[n-1]) {
                nums1[--index] = nums1[--m];
            } else {
                nums1[--index] = nums2[--n];
            }
        }

        while (m > 0) {
            nums1[--index] = nums1[--m];
        }

        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }
}
