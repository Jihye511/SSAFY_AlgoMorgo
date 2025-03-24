package PGS;

import java.util.*;
import java.io.*;

class  다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Deque<Integer> q=new ArrayDeque<>();
        int sec=0;
        int idx=0; //지금까지 나간 트럭 위치
        int weight_now=0; //현재 다리무게
        int cnt_now=0; //현재 다리 위 차 개수
        int cnt_end=0; //현재 끝난 차 개수
        
        for(int i=0;i<bridge_length;i++){
            q.offer(0);
        }
        while(true){
            
            //큐사이즈 초과(트럭 하나 건너편 도착)
            int end=q.poll();
            weight_now-=end;
            if(end!=0){ //트럭 하나 도착
                cnt_end++;
                cnt_now--;
            }
            
            

            //더이상 들어올 차가 없을때
            if(idx>=truck_weights.length){
                q.offer(0);
            }
                
            //새로운 차 들어올 수 있음
            else if(weight_now+truck_weights[idx]<=weight&&cnt_now+1<=bridge_length){
                weight_now+=truck_weights[idx];
                q.offer(truck_weights[idx++]);
                
            
            //새로운 차 못들어올때
            }else{
                q.offer(0);
            }      
            
            
            sec++;
            Integer [] t=q.toArray(new Integer[0]);
            // System.out.println(Arrays.toString(t));
            if(cnt_end==truck_weights.length)
                break; 
        }
        return sec;
        
        
    }
}
