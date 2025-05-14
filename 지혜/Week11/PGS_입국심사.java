import java.io.*;
import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        answer = binary(n, times);
    
        
        return answer;
    }
    public static long binary(int n, int[] times){
        long lo = 1;
        long hi = (long)times[0] * (long)n;
        while(lo<hi){
            long mid = (hi-lo)/2 + lo;
            
            if(countNum(mid, times)>=n){
                hi = mid;
            }
            else{ 
                lo = mid+1;
            }
        }
        return lo;
    }
    public static long countNum(long time, int[] times){
        long cnt=0;
        for(int i =0;i<times.length; i++){
            cnt += time/times[i];
        }
        return cnt;
    }
}
