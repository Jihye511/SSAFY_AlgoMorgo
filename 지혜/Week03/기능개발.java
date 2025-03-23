import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int days = (100 - progresses[0]) % speeds[0] ==0 ? (100 - progresses[0])/speeds[0] : (100 - progresses[0])/speeds[0] +1;
        int cnt =1;
        for(int i =1; i<progresses.length;i++){
            int tempday = (100 - progresses[i]) % speeds[i] ==0 ? (100 - progresses[i])/speeds[i] : (100 - progresses[i])/speeds[i] +1;
            if(days >= tempday){
                cnt++;
            }else {
                list.add(cnt);
                days = tempday;
                cnt=1;
            }
        }
        
        list.add(cnt);
        
       
        int[] answer = new int[list.size()];
        for(int i =0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
