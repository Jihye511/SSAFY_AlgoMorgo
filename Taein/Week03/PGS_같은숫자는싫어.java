import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> st = new Stack<>();

		int temp;
		for (int i : arr) {
			if(st.isEmpty()) {
				st.add(i);
			}
			else {
				temp = st.pop();
				if(temp == i) st.add(i);
				else {
					st.add(temp);
					st.add(i);
				}
				
			}
		}
        int[] result = new int[st.size()];
        for(int i = 0; i < st.size(); i++){
            result[i] = st.get(i);
        }
        return result;
    }
}
