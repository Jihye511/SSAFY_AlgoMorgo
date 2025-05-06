import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            visited[i] = false;
        }
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                answer++;
                dfs(i, n,visited, computers);
            }            
        }
        
        return answer;
    }
    
    public void dfs(int node, int n,boolean[] visited, int[][] computers){
        visited[node] = true;
        
        for(int i = 0; i < n; i++){
            if(!visited[i] && computers[node][i] == 1){
                dfs(i, n,visited, computers);
            }
        }
    }
}
