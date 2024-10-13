import java.time.Duration;
import java.time.Instant;
import java.util.*;
import com.monolith.java.*;
import com.monolith.java.MyNode.*;


public class HugePrimitiveArrayProcess {

    public static void main(String[] args) {
        // Step 1: Generate input array
        List<Integer> inputArray = generateArray();

        // Convert List to primitive array for processArray methods that use int[] OOM ERROR
        int[] inputArrayPrimitive = inputArray.stream().mapToInt(Integer::intValue).toArray();

        //System.out.println(inputArray);

        System.out.println("Measuring the time for processArray ArrayList with Remove approach...");
        measureExecutionTime(() -> processArray(inputArrayPrimitive));

//        System.out.println("Measuring the time for processArray2 (LinkedList Approach)...");
//        measureExecutionTime(() -> processArray2(inputArrayPrimitive));

        System.out.println("Measuring the time for AVL");
        measureExecutionTime(() -> AVLTreeWithIndexDuplicates.processArrayAVL( inputArrayPrimitive));

    }

    // Generate an array of random integers between -150 and 150
    private static List<Integer> generateArray() {
        List<Integer> resp = new ArrayList<>(1_000_000_00);
        Random random = new Random();

        for (int i = 0; i < 1_000_000_0; i++) {
            int num = random.nextInt(1_000_000)-5_00_000;  // Range:

            // int num = random.nextInt(1_000_000_0)-5_00_000_0;  50 % / 50 % to positive either negative
            resp.add(num);
        }
        return resp;
    }

    // Method to measure execution time of a given task (lambda function)
    private static void measureExecutionTime(Runnable task) {
        Instant start = Instant.now();
        task.run();  // Execute the method passed as a lambda
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time: " + timeElapsed.toMillis() + " milliseconds");
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
        //System.out.println(result);
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

    public static List<Integer> processArrayAVL(int[] inputArray) {
        AVLTreeWithIndex root = new AVLTreeWithIndex();

        for (int value : inputArray) {
            if (value < 0) {
                // Insert negative value into the AVL tree
                root.insert(value);
            } else if (value > 0) {
                // Delete the n-th smallest element, if n <= size
                //if (value <= index) {
                    root.deleteKthSmallest(value);
                //}
            }
            // Do nothing for value == 0
        }

        // Return the remaining elements in level order (BFS)
        root.inOrder(root.getRoot());
        return null;
    }

}
