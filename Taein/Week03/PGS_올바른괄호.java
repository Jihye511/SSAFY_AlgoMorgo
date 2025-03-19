import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
		
		for (char c : s.toCharArray()) {
			if(st.isEmpty()) {
				st.push(c);
			}
			else if(st.peek() == '('){
				if(c == ')') {
					st.pop();
				}
				else {
					st.push(c);
				}
			}
			else {
				st.push(c);
			}
		
		}
		if(st.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
    }
}
