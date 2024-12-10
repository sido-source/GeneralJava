package merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // Sort intervals by their start times
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // If merged list is empty or current interval doesn't overlap with the previous one
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // There is an overlap, so we merge the intervals
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}, {0,2}};
        int[][] result1 = solution.merge(intervals1);
        System.out.println("Merged intervals 1:");
        for (int[] interval : result1) {
            System.out.println(Arrays.toString(interval));
        }

        int[][] intervals2 = {{1,4},{4,5}};
        int[][] result2 = solution.merge(intervals2);
        System.out.println("Merged intervals 2:");
        for (int[] interval : result2) {
            System.out.println(Arrays.toString(interval));
        }
    }
}

