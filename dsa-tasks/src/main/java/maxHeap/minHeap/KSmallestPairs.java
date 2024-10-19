package maxHeap.minHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        // min heap
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> a.sum - b.sum);
        List<List<Integer>> list = new ArrayList<>();

        for (int num1 : nums1) {
            for (int num2: nums2)
            {
                queue.add(new Pair(List.of(num1, num2), num1+num2));
            }
        }

        for(int i=0; i<k; i++) {
            list.add(queue.poll().list);
        }

        return list;
    }


    static class Pair{
        public List<Integer> list;
        public int sum;

        public Pair(List<Integer> list, int sum) {
            this.list = list;
            this.sum = sum;
        }
    }

}
