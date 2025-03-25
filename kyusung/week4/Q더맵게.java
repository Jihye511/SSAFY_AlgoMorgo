package ps;

import java.util.*;
class Q더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        for(int i =0; i<scoville.length; i++){
            pq.add(scoville[i]);
        }
        
        while(pq.size() > 1 && pq.peek() < K){
            int a = pq.poll();
            int b = pq.poll();
            
            int r = Math.min(a, b) + Math.max(a, b)*2;
            pq.add(r);
            answer++;
        }
        
        
        if(pq.size() < 2 && pq.peek() < K) return -1;
        return answer;
    }
}