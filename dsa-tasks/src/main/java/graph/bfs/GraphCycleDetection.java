package graph.bfs;

import java.util.*;

public class GraphCycleDetection {

    // Detect cycle in an undirected graph using BFS
    public boolean hasCycle(Map<Integer, List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();

        for (int node : adjList.keySet()) {
            // If the node has not been visited, initiate a BFS
            if (!visited.contains(node)) {
                if (bfsCycleCheck(node, adjList, visited)) {
                    return true; // Cycle found
                }
            }
        }

        return false; // No cycle found in the graph
    }

    // Helper method to perform BFS and check for a cycle
    private boolean bfsCycleCheck(int startNode, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        Queue<int[]> queue = new LinkedList<>();

        // Add the starting node to the queue with a null parent (-1 here)
        queue.add(new int[] {startNode, -1});
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int parent = current[1];

            // Traverse all neighbors of the current node
            for (int neighbor : adjList.getOrDefault(currentNode, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    // Mark neighbor as visited and add to queue with current node as its parent
                    visited.add(neighbor);
                    queue.add(new int[] {neighbor, currentNode});
                } else if (neighbor != parent) {
                    // If neighbor is visited and not parent, cycle detected
                    return true;
                }
            }
        }

        return false; // No cycle detected in this component
    }

    // Main method to test the cycle detection
    public static void main(String[] args) {
        GraphCycleDetection graph = new GraphCycleDetection();

        // Sample adjacency list
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(1, Arrays.asList(2, 3));
        adjList.put(2, Arrays.asList(1, 4));
        adjList.put(3, Arrays.asList(1, 4));
        adjList.put(4, Arrays.asList(2, 3));

        System.out.println("Cycle detected: " + graph.hasCycle(adjList));
    }
}
