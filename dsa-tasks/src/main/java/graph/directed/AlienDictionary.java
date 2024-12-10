package graph.directed;


import java.util.*;

public class AlienDictionary {

    // time : o(m + n + V + E ) where m is the total words in dict and n is the all letters in all words
    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String res = ad.findOrder(new String[]{"baa", "abcd", "abca", "cab", "cad"}, 4);
        System.out.println(STR."r: \{res}"); // Output: "bdac"
    }

    public String findOrder(String[] dict, int k) {
        // Step 1: Create the graph
        Map<Character, List<Character>> graph = new HashMap<>();

        // Initialize the graph with all possible characters
        for (int i = 0; i < k; i++) {
            char key = (char) ('a' + i);
            graph.putIfAbsent(key, new ArrayList<>());
        }

        // Step 2: Populate the graph with edges based on the dictionary
        for (int i = 0; i < dict.length - 1; i++) {
            String parent = dict[i];
            String child = dict[i + 1];

            // Compare two adjacent words
            for (int j = 0; j < Math.min(parent.length(), child.length()); j++) {
                char pChar = parent.charAt(j);
                char cChar = child.charAt(j);

                if (pChar != cChar) {
                    graph.get(pChar).add(cChar); // Add edge pChar -> cChar, parent will have all values which are after.
                    // In dfs we would take each not already processed key and go as deep as possible using its values
                    // so we would look for the last character which has no childs
                    break; // Only the first differing character matters
                }
            }
        }

        //{a=[c], b=[a, d], c=[], d=[a]}
        //Node order: cadb
        System.out.println(graph);

        // Step 3: Perform DFS to detect cycles and get topological order
        StringBuilder sb = new StringBuilder();
        int[] visited = new int[k]; // 0 = unvisited, 1 = visiting, 2 = processed

        for (char c : graph.keySet()) {
            int index = c - 'a';
            if (visited[index] == 0) {
                if (dfs(graph, c, visited, sb)) {
                    return ""; // Cycle detected
                }
            }
        }

        System.out.println("Node order: " + sb);
        // Step 4: Reverse the result and validate
        return (sb.length() == k) ? sb.reverse().toString() : "";
    }

    private boolean dfs(Map<Character, List<Character>> graph, char c, int[] visited, StringBuilder sb) {
        int index = c - 'a';

        if (visited[index] == 1) {
            return true; // Cycle detected
        }
        if (visited[index] == 2) {
            return false; // Already processed
        }

        visited[index] = 1; // Mark as visiting

        for (char neighbor : graph.get(c)) {
            if (dfs(graph, neighbor, visited, sb)) {
                return true; // Propagate cycle detection
            }
        }

        visited[index] = 2; // Mark as fully processed
        sb.append(c); // Add to result
        return false;
    }
}

