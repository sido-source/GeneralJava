package graph.connectedComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfConnectedComponents {
    // in undirected graph based on edges

    public int countComponents(int n, int[][] edges) {

        // create the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0 ; i < n; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
        }

        for( int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        //System.out.println(graph);

        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0 ; i < n; i++) {
            if (!visited[i]) { // if we already processed and count the node we just skip it
                dfs(graph, i, visited);
                res++;
            }
        }

        return res;
    }

    public void dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited) {

        visited[node] = true;

        for (int neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                dfs(graph, neighbour, visited);
            }
        }
    }
}
