import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        answer=new int[2];
        int max=brown+yellow/3;
        
        for(int x=3;x<=max;x++){
int y=(brown+yellow)/x;
    if((x-2)*(y-2)==yellow){
        answer[0]=x;
        answer[1]=y;
  
    
}
            
            }
        return answer;
    }
}
