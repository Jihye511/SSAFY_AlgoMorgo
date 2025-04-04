import java.util.*;
class Solution {
    public List<Integer> solution(int[] answers) {
        int[] st1 = {1, 2, 3, 4, 5};
        int[] st2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] st3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        for(int i = 0; i < answers.length; i++){
            if(st1[i % st1.length] == answers[i]) cnt1 += 1;
            if(st2[i % st2.length] == answers[i]) cnt2 += 1;
            if(st3[i % st3.length] == answers[i]) cnt3 += 1;
        }
        int max = Math.max(Math.max(cnt1, cnt2), cnt3);
        List<Integer> answer = new ArrayList<>();
        
        if(cnt1 == max){
            answer.add(1);
        }
        if(cnt2 == max){
            answer.add(2);
        }
        if(cnt3 == max){
            answer.add(3);
        }
            
        
        
        
        return answer;
    }
}
