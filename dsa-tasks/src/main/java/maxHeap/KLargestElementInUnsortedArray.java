package maxHeap;

import java.util.PriorityQueue;

public class KLargestElementInUnsortedArray {

    // https://leetcode.com/problems/kth-largest-element-in-an-array/

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b-a);

        for (int i : nums) {
            queue.add(i);
        }

        for (int i =0; i<k-1; i++) {
            queue.poll();
        }

        return queue.peek();
    }
}
