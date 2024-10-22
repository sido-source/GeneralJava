package AVL_Tree_with_indexes_and_duplicates;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;

import com.monolith.java.AVLTreeWithIndexDuplicates;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HugePrimitiveArrayProcess {

    static List<List<Integer>> responses = new ArrayList<>();

    public static void main(String[] args) {
        // Step 1: Generate input array
        List<Integer> inputArray = generateArray();

        // Convert List to primitive array for processArray methods that use int[]
        int[] inputArrayPrimitive = inputArray.stream().mapToInt(Integer::intValue).toArray();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {

            // Step 2: Submit both tasks to the executor
            Future<List<Integer>> future1 = executor.submit(() -> {
                Instant start = Instant.now();
                try {
                    return processArray(inputArrayPrimitive);
                } finally {
                    Duration duration = Duration.between(start, Instant.now());
                    System.out.println("Task 1 completed in " + duration.toMillis() + " milliseconds.");
                }
            });
            Future<List<Integer>> future2 = executor.submit(() -> {
                return measureExecutionTime(() -> AVLTreeWithIndexDuplicates.processArrayAVL(inputArrayPrimitive), "AVL_Tree_with_indexes_and_duplicates.AVLTree Process");
            });

            // Wait for both tasks to complete
            List<Integer> result1 = future1.get();
            List<Integer> result2 = future2.get();

            // Add both results to the responses list
            responses.add(new ArrayList<>(result1));
            responses.add(new ArrayList<>(result2));

            // Step 3: Compare the two lists
            if (areListsEqual(result1, result2)) {
                System.out.println("The two lists are equal in size and content.");
            } else {
                System.out.println("The two lists are NOT equal.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    // Generate an array of random integers between -150 and 150
    private static List<Integer> generateArray() {
        List<Integer> resp = new ArrayList<>(1_000_000_00);
        Random random = new Random();


        for (int i = 0; i < 1_000_000_0; i++) {
            int num = random.nextInt(1_000_000_0) - 5_000_000;  // Range between -5_000_000 and 5_000_000
            resp.add(num);
        }
        return resp;
    }

    // Method to compare two lists for size and content equality
    private static boolean areListsEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    // ProcessArray1 method (ArrayList with removal for each positive number)
    public static List<Integer> processArray(int[] numbers) {
        List<Integer> result = new ArrayList<>();

        for (int num : numbers) {
            if (num < 0) {
                result.add(num);
            } else if (num > 0) {
                int indexToRemove = num - 1;
                if (indexToRemove < result.size()) {
                    result.remove(indexToRemove);
                }
            }
        }
        System.out.println("ArrayList, array size: " + result.size());
        return result;
    }

    // ProcessArray2 method (LinkedList approach for better removal)
    public static List<Integer> processArray2(int[] numbers) {
        LinkedList<Integer> result = new LinkedList<>();

        for (int num : numbers) {
            if (num < 0) {
                result.add(num);
            } else if (num > 0) {
                int indexToRemove = num - 1;
                if (indexToRemove < result.size()) {
                    result.remove(indexToRemove);
                }
            }
        }
        //System.out.println(result);
        return result;
    }

    public static <T> T measureExecutionTime(Callable<T> task, String taskName) throws Exception {
        Instant start = Instant.now();
        try {
            return task.call();
        } finally {
            Duration duration = Duration.between(start, Instant.now());
            System.out.println(taskName + " completed in " + duration.toMillis() + " milliseconds.");
        }
    }

}