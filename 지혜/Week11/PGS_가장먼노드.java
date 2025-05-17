import java.io.*;
import java.util.*;
class Solution {
    static ArrayList<Integer>[] node;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        node = new ArrayList[n+1];
        for(int i =0;i<n+1; i++){
            node[i] = new ArrayList<>();
        }
        for(int i =0; i<edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            node[a].add(b);
            node[b].add(a);
        }
        answer = bfs(n);
        return answer;
    }
    public static int bfs(int n){
        int max = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[] v = new boolean[n+1];
        q.offer(new int[]{1,0});
        v[1] = true;
        while(!q.isEmpty()){
            
            int size = q.size();
            max =size;
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                for(int j =0; j<node[cur[0]].size(); j++){
                    int next = node[cur[0]].get(j);
                    if(!v[next]){
                        v[next]  =true;
                        q.offer(new int[]{next, cur[1]+1});
                    }
                }
            }

        }
        return max;
    }
}
