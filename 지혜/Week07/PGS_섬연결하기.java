import java.io.*;
import java.util.*;

class Node{
    int end;
    int cost;
    public Node(int end, int cost){
        this.end = end;
        this.cost = cost;
    }
}
class Solution {
    static PriorityQueue<int[]> pq;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        ArrayList<Node>[]connect = new ArrayList[n];
        for(int i =0; i<n; i++){
            connect[i] = new ArrayList<>();
        }
        for(int i =0; i<costs.length; i++){
            int s = costs[i][0];
            int e = costs[i][1];
            connect[s].add(new Node(e, costs[i][2]));
            connect[e].add(new Node(s, costs[i][2]));
        }
        //거리 기준 정렬
        pq = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        
        boolean[]v = new boolean[n];
        pq.offer(new int[]{0,0}); //(도착지, 비용)
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(!v[cur[0]]){
                v[cur[0]]=true;
                answer+= cur[1];
                for(int i =0; i< connect[cur[0]].size(); i++){
                    Node next = connect[cur[0]].get(i);
                    if(!v[next.end]){
                        pq.offer(new int[]{next.end, next.cost});
                    }
                }
            }
        }
        
        
       
        
        
        return answer;
    }
}
