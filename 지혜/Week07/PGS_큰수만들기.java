import java.io.*;
import java.util.*;

class Solution {
    static int [] num;
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i<number.length(); i++){
            int cur = number.charAt(i)-'0';
            while(!stack.isEmpty() && k>0 && stack.peek()<cur){
                stack.pop();
                k--;
            }
            stack.add(cur);
        }
        
        while(k-->0){
            stack.pop();
        }
        
        for(int s : stack){
            sb.append(s);
        }
        
        return sb.toString();
    }
}
