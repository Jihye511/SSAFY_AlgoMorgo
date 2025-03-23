import java.util.*;

class Q올바른괄호 {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            if(ch == '('){
                stack.add(1);
            }else{
                if(stack.isEmpty()){
                    answer = false;
                    break;
                }
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()) answer = false;
            
        return answer;
    }
}