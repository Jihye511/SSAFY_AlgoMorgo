import java.util.*;
class Solution {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        adj = new ArrayList[n];
        visited = new boolean[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < computers.length; i++){
            for(int j = 0; j < n; j++){
                if(computers[i][j] == 1){
                    adj[i].add(j);
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                answer += 1;
                bfs(i);
            }
        }
        
        
        
        return answer;
    }
    
    public static void bfs(int idx){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(idx);
        visited[idx] = true;
        int temp;
        while(!q.isEmpty()){
            temp = q.poll();
            for(int i : adj[temp]){
                if(!visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
