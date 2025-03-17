import java.util.*;

public class Q같은숫자는싫어 {
    public int[] solution(int []arr) {
    
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i<arr.length; i++){
            if(queue.isEmpty() || queue.peekLast() != arr[i]){
                queue.addLast(arr[i]);
            }
        }
        
        int N = queue.size();
        int[] answer = new int[N];
        for(int i = 0; i<N; i++){
            answer[i] = queue.pollFirst();
        }
        
        return answer;
    }
}