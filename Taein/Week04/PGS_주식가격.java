import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i : prices){
            q.add(i);
        }
        int now;
        for(int i = 0; i < prices.length; i++){
            now = q.poll();
            int time = 0;
            for(int k : q){
                time += 1;
                if(now > k){   
                    break;
                }
            }
            answer[i] = time;
        }
        
        return answer;
    }
}
