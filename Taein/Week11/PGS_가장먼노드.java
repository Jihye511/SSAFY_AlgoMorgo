import java.util.*;
class Solution {
    static ArrayList<Integer>[] adj;
    static int[] dist;
    static int size;
    public int solution(int n, int[][] edge) {
        adj = new ArrayList[n+1];
        dist = new int[n+1];
        size = n;
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        for(int i = 0; i <= n; i++){
            adj[i] = new ArrayList<>();
        }
        
        int a, b, cnt;
        for(int[] node : edge){
            a = node[0];
            b = node[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        
        cnt = bfs();
        int answer = 0;
        
        for(int k : dist){
            if(k == cnt-1){
                answer ++;
            }
        }
        
        
        return answer;
    }
    
    public static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        
        boolean[] visited = new boolean[size+1];
        int x, q_size;
        int cnt = 0;
        while(!q.isEmpty()){
            q_size = q.size();
            for(int i = 0; i < q_size; i++){
                x = q.poll();
                if(visited[x]) continue;
                visited[x] = true;
                dist[x] = Math.min(cnt + 1, dist[x]);
                for(int node : adj[x]){
                    q.add(node);
                }
                
            }
            cnt ++;
        }
        return cnt;
            
    }
    
}
