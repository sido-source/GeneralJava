package graph.connectedComponents;

public class NumberOfProvinces {

    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}}; // we know that there are as many nodes as the length of the isConnected
        NumberOfProvinces nop = new NumberOfProvinces();
        nop.findCircleNum(isConnected);

    }
    public int findCircleNum(int[][] isConnected) {

        int res = 0;
        boolean[] visited = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                res++;
                dfs(isConnected, i, visited);
            }
        }

        return res;
    }

    public void dfs(int[][] isConnected, int node, boolean[] visited) {

        visited[node] = true;
        for (int i = 0; i < isConnected[node].length; i++) {
            if (!visited[i] && isConnected[node][i] == 1) {
                dfs(isConnected, i, visited);
            }
        }
    }
}
