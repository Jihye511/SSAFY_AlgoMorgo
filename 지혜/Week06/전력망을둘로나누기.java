import java.io.*;
import java.util.*;
class Solution {
    static ArrayList<Integer>[] list;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n+1];
        for(int i =1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            
            list[a].add(b);
            list[b].add(a);
        }
        for(int i =1; i<=n; i++){
            Collections.sort(list[i]);
        }
        
        int size = wires.length;
        for(int i=1; i<=size; i++){
            int getsize = list[i].size();
            for(int j=0; j<getsize; j++){
                int other = list[i].get(j);
                list[i].remove(Integer.valueOf(other));
                list[other].remove(Integer.valueOf(i));
                answer = Math.min(bfs(n), answer);
                list[i].add(other);
                list[other].add(i);
                
                for(int k =1; k<=n; k++){
                    Collections.sort(list[k]);
                }
            }    
        }
        
        return answer;
    }
    public static int bfs(int n){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        boolean[] v = new boolean[n+1];
        v[1] = true;
        int cnt=1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i =0; i<list[cur].size(); i++){
                int next = list[cur].get(i);
                if(!v[next]){
                    v[next] = true;
                    q.offer(next);
                    cnt++;
                }
                
            }
        }
        int cnt_other = n - cnt;
        return Math.abs(cnt_other - cnt);
    }
}
