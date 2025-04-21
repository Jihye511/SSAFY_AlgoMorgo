import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int i=0; i<routes.length; i++){
            pq.offer(new int[]{routes[i][0], routes[i][1]});
        }
        
        int[] range = pq.poll();
        for(int i =1; i<routes.length; i++){
            int[] cur = pq.poll();
            if(cur[0]<=range[1]){
                range[0] = cur[0];
                range[1] = Math.min(range[1], cur[1]);
            }
            else{
                answer++;
                range[0] = cur[0];
                range[1] = cur[1];
            }
        }
       return answer++;
    }
}
