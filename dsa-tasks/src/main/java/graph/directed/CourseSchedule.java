package graph.directed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        //
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Map with mapping course (key) -> prerequisites (values)
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] arr : prerequisites) {
            map.putIfAbsent(arr[0], new HashSet<>());
            map.get(arr[0]).add(arr[1]);
        }

        // Track the state of each node during DFS: 0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];

        // Check for cycles in the graph
        for (int key : map.keySet()) {
            if (visited[key] == 0 && dfsHelper(key, map, visited)) {
                return false;  // Cycle detected
            }
        }
        return true;  // No cycles, all courses can be completed
    }

    boolean dfsHelper(Integer key, Map<Integer, Set<Integer>> map, int[] visited) {
        if (visited[key] == 1) return true;  // Cycle detected (currently visiting node)
        if (visited[key] == 2) return false;  // Already fully processed, no cycle

        visited[key] = 1;  // Mark as currently visiting

        // Visit all the prerequisites (neighbors)
        for (Integer node : map.getOrDefault(key, new HashSet<>())) {
            if (dfsHelper(node, map, visited)) {
                return true;  // If any node leads to a cycle, return true
            }
        }

        visited[key] = 2;  // Mark as fully processed (no cycle found)
        return false;  // No cycle detected
    }
}

