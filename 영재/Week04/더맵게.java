import java.util.*;
import java.io.*;
class 더맵게 {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        
        for(int i=0;i<scoville.length;i++){
            minHeap.offer(scoville[i]);
        }
        int cnt=0;
        while(true){
            int min1=minHeap.peek();
            if(min1>=K) break;
            if(minHeap.size()==1){
                cnt=-1;
                break;
            }
            
            min1=minHeap.poll();
            int min2=minHeap.poll();
            
            minHeap.offer(min1+min2*2);
            cnt++;
            
        }
        int answer = cnt;
        return answer;
    }
}
