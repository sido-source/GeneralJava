package arrays;

//https://leetcode.com/problems/rotate-array/
public class RotateBy {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        rotateBy(arr, 2);

        for (int i = 0 ; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }


    }

    private static void rotateBy(int[] ints, int i) {
        reverseArray(ints, 0, ints.length-1);
        //{5, 4, 3, 2, 1},
        reverseArray(ints, 0, i-1);
        //{ 4, 5, 3, 2, 1},
        reverseArray(ints, i, ints.length-1);
        //{ 4, 5, 1, 2, 3}
    }

    // start inclusive
    // end inclusive
    // there might be a problem during inclusive and exclusive
    public static void reverseArray(int[] ints, int start, int end) {
        while (start < end) {
            int temp = ints[end];
            ints[end] = ints[start];
            ints[start] = temp;
            start++;
            end--;
        }
    }
}
