import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
       PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =0; i<scoville.length; i++){
            pq.offer(scoville[i]);
        }
        int cnt=0;
        while(true){
            if(pq.peek() >=K) break;
            if(pq.size()==1) {
                cnt=-1;
                break;
            }
            int cur = pq.poll();
            int next = pq.poll();
            
            int result = cur +(next*2);
            cnt++;
            pq.offer(result);
        }
        return cnt;
    }       
}
