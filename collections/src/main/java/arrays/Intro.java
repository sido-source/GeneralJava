package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Intro {

    public static void main(String[] args) {
        int[] arr1 = {6, 3, 1};
        int[] arr2 = {5, 4, 2};

        int[] result = new int[arr1.length + arr2.length];

        // Using System.arraycopy
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);

        // Printing concatenated array
        for (int num : result) {
            System.out.print(num + " ");
        }


        System.out.println("\n" + Arrays.compare(arr2, arr1));
        System.out.println(Arrays.mismatch(arr2, arr1));
        arr1[0]=5;
        System.out.println(Arrays.mismatch(arr2, arr1));
        arr1[1]=4;
        System.out.println(Arrays.mismatch(arr2, arr1));

        Integer[] arr3 = new Integer[]{4,2,1,3,4,1};

        // 1 - 4 < 0
        Arrays.sort(arr3, (a,b) -> a - b);
        System.out.println("Ascending: " + Arrays.toString(arr3));
        // 4 - 1 > 0
        Arrays.sort(arr3, (a,b) -> b - a);
        System.out.println("Descending: " + Arrays.toString(arr3));


        List<int[]> list = List.of();
        try {
            list = List.of(result); // ImmutableCollections - final
            list.set(0, new int[] {3,4,5}); // cant add, modify, delete
            list.add(new int[] {3,4,5}); // cant add, modify, delete
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> list3 = list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());

        try{
            List<int[]> list1 = Arrays.asList(result); // Allow change except those methods that would change the size of the returned list
            list1.set(0, new int[] {3,4,5}); // can modify
            list1.add(new int[] {2,3}); // cant add
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        List<Integer> collect = Arrays.stream(result).boxed().collect(Collectors.toList());
        collect.add(2);
        System.out.println(collect);


        String[] arr = new String[5];
        Arrays.fill(arr, "HiH");
        System.out.println("\nArray after filling with HiH: " + Arrays.toString(arr));
        Arrays.fill(arr, 1, 2, "Changed");
        System.out.println("Array after changing values: " + Arrays.toString(arr));
    }
}
