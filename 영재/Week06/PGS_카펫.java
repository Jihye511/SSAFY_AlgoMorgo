import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int a=1, b=1;
         int[] answer=new int[2];
        
        for(int width=1;width<=(brown+2)/2;width++){
            for(int length=3;length<=yellow+2;length++){
                if(2*width+2*length-4==brown&&(width-2)*(length-2)==yellow){
                    a=width;b=length;
                    break;
                }
            }
        }
      
        
        answer[0]=a;
        answer[1]=b;
        return answer;
    }
}
