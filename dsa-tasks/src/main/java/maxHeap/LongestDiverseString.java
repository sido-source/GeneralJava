package maxHeap;

import java.util.PriorityQueue;

public class LongestDiverseString {
    public static void main(String[] args) {
        String s = "aaabbcc";
        System.out.println(longestDiverseString(1,2,3));
    }

    public static String longestDiverseString(int a, int b, int c) {
        // Max-heap to store characters along with their counts
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);

        // Add characters and their counts to the heap if they are non-zero
        if (a > 0) pq.offer(new Pair('a', a));
        if (b > 0) pq.offer(new Pair('b', b));
        if (c > 0) pq.offer(new Pair('c', c));

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            Pair first = pq.poll(); // Get the character with the highest count

            // Check if we can append this character without breaking the "no three consecutive" rule
            if (result.length() >= 2 && result.charAt(result.length() - 1) == first.ch && result.charAt(result.length() - 2) == first.ch) {
                // We can't append this character, so we need to try the second highest
                if (pq.isEmpty()) {
                    // No other characters to use, we can't add anything more
                    break;
                }
                Pair second = pq.poll();
                result.append(second.ch); // Append the second highest
                second.count--;

                // Re-add the second pair back into the heap if it has remaining characters
                if (second.count > 0) {
                    pq.offer(second);
                }
                // Re-add the first pair back into the heap as well
                pq.offer(first);
            } else {
                // Safe to append the first character
                result.append(first.ch);
                first.count--;

                // Re-add the first pair back into the heap if it has remaining characters
                if (first.count > 0) {
                    pq.offer(first);
                }
            }
        }

        return result.toString();
    }

    // Helper class to store character and its count
    static class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}

