import java.io.*;
import java.util.*;
class Solution {
    static ArrayList<Integer>[] list;
    static boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        list = new ArrayList[n];
        v = new boolean[n];
        for(int i =0; i<n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j]==1){
                    list[i].add(j);
                }
            }
        }
        for(int i=0; i<computers.length; i++){
            if(!v[i]){
                bfs(i);  
                answer ++;
            }
        }
        
        return answer;
    }
    public static void bfs(int n){
        Queue<Integer> q= new LinkedList<>();
        q.offer(n);
        v[n] =true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i =0; i<list[cur].size();i++){
                int next = list[cur].get(i);
                if(!v[next]){
                    v[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
