import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int mingap = Integer.MAX_VALUE;
        int hap = brown + yellow;
        
        ArrayList<int[]> list = new ArrayList<>();
        for(int i =1; i<=yellow; i++){
            if(yellow%i==0){
                list.add(new int[]{i, yellow/i});
            }
        }
        for(int i =0; i<list.size(); i++){
            int[] cur = list.get(i);
            int a = (cur[0] +2)*2;
            int b = cur[1]*2;
            if(a+b == brown){
                answer[0] = Math.max(cur[0]+2, cur[1]+2);
                answer[1] = Math.min(cur[0]+2, cur[1]+2);
            }
        } 
        
        return answer;
    }
}
