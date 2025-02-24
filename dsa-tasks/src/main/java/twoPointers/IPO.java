package twoPointers;

import java.util.PriorityQueue;
import java.util.Comparator;

public class IPO {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Min-Heap to store projects based on capital requirement
        PriorityQueue<int[]> minCapitalHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Max-Heap to store affordable projects based on profit
        PriorityQueue<int[]> maxProfitHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        // Push all projects into the Min-Heap
        for (int i = 0; i < profits.length; i++) {
            minCapitalHeap.add(new int[]{capital[i], profits[i]});
        }

        // Start selecting projects
        for (int i = 0; i < k; i++) {
            // Move all affordable projects from Min-Heap to Max-Heap
            while (!minCapitalHeap.isEmpty() && minCapitalHeap.peek()[0] <= w) {
                int[] project = minCapitalHeap.poll();
                maxProfitHeap.add(project);
            }

            // If no project is affordable, stop
            if (maxProfitHeap.isEmpty()) {
                break;
            }

            // Select the most profitable project
            w += maxProfitHeap.poll()[1];
        }

        return w;
    }

    public static void main(String[] args) {
        // Test Case
        int k = 2;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        System.out.println("Maximized Capital: " + findMaximizedCapital(k, w, profits, capital));
    }
}

