import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        
        int idx = 0;
        while(idx < number.length()){
            if(!stack.isEmpty()){
                while(k > 0 && !stack.isEmpty()){
                    if(stack.peek() < number.charAt(idx)){
                        stack.pop();
                        k -= 1;
                    }
                    else{
                        break;
                    }
                        
                }
                stack.push(number.charAt(idx));
                idx += 1;
            
            }
            else{
                stack.push(number.charAt(idx));
                idx += 1;
            }


        }
        if(k > 0) {
        	while(k > 0) {
        		stack.pop();
                k -= 1;
        	}
        }
        for(char c : stack){
            answer += c;
        }
        
        
        return answer;
    }
}
