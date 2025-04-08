import java.util.*;

class Q전력망을둘로나누기 {
    public static int N, k;
    public static ArrayList<Integer>[] list;
    public static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        N = n;
        k = wires.length;
        int answer = Integer.MAX_VALUE;
        
        for(int p = 0; p < k; p++){
            list = new ArrayList[N+1];
            visited = new boolean[N+1];
            
            int v1 = wires[p][0];
            int v2 = wires[p][1];
            
            for(int i = 0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < k; i++){
                if(i == p) continue;
                list[wires[i][0]].add(wires[i][1]);
                list[wires[i][1]].add(wires[i][0]);
            }
            
            // v1 시작하는 개수, v2 에서 시작하는 개수를 비교해서 최소값 갱신
            boolean[] visited = new boolean[N+1];            
            int n1 = dfs(v1);
            int n2 = dfs(v2);
            
            answer = Math.min(answer, Math.abs(n1 - n2));
        }
        
        System.out.print("ans : " + answer);
        return answer;
    }
    
    public static int dfs(int now){
        int cnt = 0;
        visited[now] = true;
        
        for(int i = 0; i<list[now].size(); i++){
         if(!visited[list[now].get(i)]){
                cnt += dfs(list[now].get(i));
            }
        }
        
        return cnt + 1;
    }
    
    
}