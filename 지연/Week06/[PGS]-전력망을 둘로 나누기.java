import java.util.*;
class Solution {
   static int[][] arr;
    public int solution(int n, int[][] wires) {
        int answer = n;
      arr=new int[n+1][n+1];
        
        for(int i=0;i<wires.length;i++){
            arr[wires[i][0]][wires[i][1]]=1;
            arr[wires[i][1]][wires[i][0]]=1;
                     
        }
        int a,b;
        for(int i=0;i<wires.length;i++){
            a=wires[i][0];
            b=wires[i][1];
            
            arr[a][b]=0;
            arr[b][a]=0;
            
            answer=Math.min(answer,bfs(n,a));
            
            arr[a][b]=1;
            arr[b][a]=1;
        }

        return answer;
    }
    static Integer bfs(int n, int start){
        int[] visited = new int[n+1];
        int cnt=1;
        Queue <Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        while(!queue.isEmpty()){
            int point=queue.poll();
            visited[point]=1;
            
            for(int i=1;i<=n;i++){
                if(visited[i]==1)continue;
                if(arr[point][i]==1){
                    queue.offer(i);
                    cnt++;
                }
                
            }
        }
        //cnt-(n-cnt)
        return (int)Math.abs(n-2*cnt);
    }
}
