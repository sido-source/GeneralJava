import java.util.*;

public class MonoStackProcessor {

    // Monotonic stack to process the array of numbers
    private Deque<Integer> stack;

    public MonoStackProcessor() {
        stack = new ArrayDeque<>();
    }

    // Process the array of numbers
    public List<Integer> processNumbers(int[] numbers) {
        for (int number : numbers) {
            if (number < 0) {
                stack.push(number);  // Push negative numbers onto the stack
            } else if (number > 0) {
                removeAt(number);  // Remove n-th element based on the value of positive number
            }
            // Do nothing if number is 0
        }

        // Convert the final stack into a list and return it
        return new ArrayList<>(stack);
    }

    // Remove the n-th element from the stack if the index is valid
    private void removeAt(int n) {
        if (n <= stack.size()) {
            // Temporary stack to hold elements until we reach the n-th element
            Deque<Integer> tempStack = new ArrayDeque<>();

            // Pop elements from the stack until we reach the n-th element
            for (int i = 0; i < n; i++) {
                tempStack.push(stack.pop());
            }

            // Discard the n-th element (the one on top of the temp stack)
            tempStack.pop();

            // Push back the remaining elements from the temporary stack into the main stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
        // If n > stack size, do nothing
    }

    public static void main(String[] args) {
        MonoStackProcessor processor = new MonoStackProcessor();
        int[] numbers = { -1, -2, 2, 0, -3, 3, -4, 5, 0, -6, 2 };

        List<Integer> result = processor.processNumbers(numbers);
        System.out.println(result);  // Expected Output: [-1, -4, -6]
    }
}

