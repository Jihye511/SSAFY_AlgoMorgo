import java.util.*;
import java.io.*;
class PGS_전력망둘로나누기 {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        
        boolean line[][]=new boolean [n+1][n+1];
        for(int i=0;i<wires.length;i++){
            line[wires[i][0]][wires[i][1]]=true;
            line[wires[i][1]][wires[i][0]]=true;
        }
        
        boolean chk[]=new boolean[n+1];
        
        for(int i=0;i<wires.length;i++){
            int a = wires[i][0];
            int b = wires[i][1];

            line[a][b] = false;
            line[b][a] = false;

            int cnt = find(a, n, line);
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);

            // 간선 복구
            line[a][b] = true;
            line[b][a] = true;

        }

        return answer;
    }
    
    static int find(int start,int n,boolean v[][]){
        int cnt=1;
        boolean visited[]=new boolean[n+1];
        Queue<Integer> q=new LinkedList<>();
        q.add(start);
        visited[start]=true;
        while(!q.isEmpty()){
            int now=q.poll();
            
            for(int i=1;i<=n;i++){
                if(v[now][i]&&!visited[i]){
                    visited[i]=true;
                    q.add(i);
                    cnt++;
                }
            }
            
        }
        System.out.println(cnt);
        return cnt;
    }
}
