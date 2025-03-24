package PGS;

import java.util.*;
import java.io.*;
class 올바른괄호 {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<Character> stack=new ArrayDeque<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.offerLast(s.charAt(i));
            }else{
                if(!stack.isEmpty())
                    stack.pollLast();
                else{
                    answer=false;
                    return answer;
                }
            }
        }
        
        if(stack.isEmpty()) answer=true;
        else answer=false;
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}
