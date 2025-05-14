import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        int mid, cnt, before;
        
        while(left <= right){
            mid = (left+ right) / 2;
            cnt = 0;
            before = 0;
            for(int rock : rocks){
                if(rock - before < mid) {
                    cnt ++;
                    continue;
                }
                before = rock;
            }
            if(distance - before < mid) cnt ++;
            
            if(cnt <= n){
                left = mid + 1;
                answer = mid;
            }
            else{
                right = mid - 1;
            }
                
        }
        return answer;
    }
}
