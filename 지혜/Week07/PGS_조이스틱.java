import java.io.*;
import java.util.*;
class Solution {
    public int solution(String name) {
        int answer = 0;
        int idx =0;
        int move =name.length()-1;
        boolean[] v = new boolean[name.length()];
        for(int i =0; i<name.length(); i++){
            char c = name.charAt(i);
            //위아래 움직이기
            answer += Math.min(c - 65, 91-c);
        
            
            //A가 끝나는 지점 찾기
            int next = i+1;
            while(next<name.length() && name.charAt(next)=='A'){
                next ++;
            }
            
            //좌우이동 최소횟수 구하기
            move = Math.min(move, Math.min(i*2 + name.length()-next , (name.length()-next)*2 +i));
        }
        
        answer +=move;
        
        return answer;
    }
}
