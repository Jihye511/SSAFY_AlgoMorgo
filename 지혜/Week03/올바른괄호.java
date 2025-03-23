import java.util.*;
import java.io.*;

class Solution {
    boolean solution(String s) {
        Queue<Character> q =new LinkedList<>();
        for(int i =0; i<s.length();i++){
            q.offer(s.charAt(i));
        }
        int num=0;
        for(int i =0; i<s.length();i++){
            char c = q.poll();
            if(c== '('){
                num +=1;
            }else if(c ==')'){
                num -=1;
            }
            
            if(num<0 ) return false;
        }
        if(num!=0) return false;
        
        return true;
    }
}
