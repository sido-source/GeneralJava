package arrays;

public class Intro {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};

        int[] result = new int[arr1.length + arr2.length];

        // Using System.arraycopy
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);

        // Printing concatenated array
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
