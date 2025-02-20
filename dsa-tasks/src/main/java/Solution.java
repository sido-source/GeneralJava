//
//// someonenes solution very fasr 2ms: class Graph{https://leetcode.com/problems/course-schedule/
//    public int n;
//    public ArrayList<Integer>[] list;
//    Graph(int n){
//        this.n=n;
//        list=new ArrayList[n];
//        for(int i=0;i<n;i++){
//            list[i]=new ArrayList<>();
//        }
//    }
//    public void addEdge(int src,int dest){
//        list[src].add(dest);
//        // list[dest].add(src);
//    }
//    // public boolean hasPathDfs(int src,int dest,boolean[] visit){
//    //     if(src==dest) return true;
//    //     if(visit[src]) return false;
//    //     visit[src]=true;
//    //     for(int val:list[src]){
//    //         if(hasPathDfs(val,dest,visit)){
//    //             return true;
//    //         }
//    //     }
//    //     return false;
//    // }
//}
//class Solution {
//    int[] visit;
//    ArrayList<Integer>[] adj;
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        Graph obj=new Graph(numCourses);
//        for(int i=0;i<prerequisites.length;i++){
//            int src=prerequisites[i][1];
//            int dest=prerequisites[i][0];
//            obj.addEdge(src,dest);
//        }
//        this.adj=obj.list;
//        visit=new int[numCourses];
//        for(int sr=0;sr<numCourses;sr++){
//            if(visit[sr]==0 && canFinishHelper(sr)) return false;
//        }
//        return true;
//    }
//    public boolean canFinishHelper(int src){
//        if(visit[src]==1) return true;
//        if(visit[src]==2) return false;
//        visit[src]=1;
//        for(int nbr:adj[src]){
//            if(canFinishHelper(nbr)) return true;
//        }
//        visit[src]=2;
//        return false;
//    }
//}