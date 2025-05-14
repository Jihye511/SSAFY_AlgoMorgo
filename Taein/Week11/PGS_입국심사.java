import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        long answer = right;
        long mid;
        long cnt;
        while(left <= right){
            mid = (left + right) / 2;
            cnt = 0;
            for(int i : times){
                cnt += mid / i;
            }
            if(cnt >= n){
                right = mid - 1;
                answer = mid;
            }
            else{
                left = mid + 1;
            }
        }
            
        return answer;
    }
}
