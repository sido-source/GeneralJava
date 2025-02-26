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


    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {

        // min heap with min sum
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a,b) -> a.get(0) + a.get(1) - b.get(0) + b.get(1));

        for (int i : nums1) {
            for(int j : nums2) {
                queue.add(List.of(i,j));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        int counter =0;

        while (counter < k) {
            res.add(queue.poll());
            counter++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {1, 1, 6};
        int k = 3;

        List<List<Integer>> result = kSmallestPairs1(nums1, nums2, k);
        System.out.println(result); // [[1, 2], [1, 4], [1, 6]]
    }

}
