package PGS;

import java.util.*;

public class 같은숫자는싫어 {
    public int[] solution(int []arr) {
        int[] answer;
        Deque<Integer> stack=new ArrayDeque<>();
        
        for(int i=0;i<arr.length;i++){
            if(!stack.isEmpty()&&stack.peekLast()==arr[i]) continue;
            stack.add(arr[i]);
        }
        int s=stack.size();
        answer=new int [stack.size()];

        for(int i=0;i<s;i++){
            answer[i]=stack.poll();
        }
        return answer;
    }
}
