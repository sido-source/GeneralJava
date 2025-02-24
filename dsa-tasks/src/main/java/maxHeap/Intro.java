package maxHeap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Intro {

    /*
    PriorityQueue: It's like a special type of binary tree (called a binary heap) stored in an array under the hood.
    The smallest (or largest, depending on the queue type) element is always at the top (the "root").
    We can only take the smallest element one by one, not randomly like in an AVL tree.


    What will we need:
    1) PriorityQueue
    2) Comparator (we want to sort elements in a specific order)

     */

    public static void main(String[] args) {
        // Min-Heap (default behavior)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Smallest element is at the root
        minHeap.add(5);
        minHeap.add(1);
        minHeap.add(10);

        System.out.println("Min-Heap root (smallest element): " + minHeap.peek()); // Output: 1

        // Max-Heap using Comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(5);
        maxHeap.add(1);
        maxHeap.add(1);
        maxHeap.add(10);


        System.out.println("Max-Heap root (largest element): " + maxHeap.peek()); // Output: 10
    }
}
