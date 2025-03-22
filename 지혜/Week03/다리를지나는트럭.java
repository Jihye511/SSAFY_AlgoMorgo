import java.io.*;
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int hap=0;
        int cnt=0;
        if(truck_weights.length ==1){
            return bridge_length+1;
        }
        if(bridge_length==1){
            return truck_weights.length+1;
        }
        
        for(int t : truck_weights){
            while(true){
                if(q.isEmpty()){
                    q.offer(t);
                    cnt++;
                    hap+=t;
                    break;
                }else if(q.size()==bridge_length){
                    //빼기
                    hap -=q.poll();
                }
                else{
                    if(hap+t> weight){
                        q.offer(0);
                        cnt++;
                    }else{
                        q.offer(t);
                        hap+=t;
                        cnt++;
                        break;
                    }
                }
            }
        }
        cnt+= bridge_length;
        return cnt;
    }
}
