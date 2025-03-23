import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < priorities.length; i++) {
            q.add(priorities[i]);
        }
        int cnt = 0;
        while (!q.isEmpty()){
            for (int i = 0; i < priorities.length; i++) {
                if(priorities[i] == q.peek()){
                    q.poll();
                    cnt +=1;
                    if(i == location){
                        return cnt;
                    }
                }
            }
        }
        return cnt;

    
    }
}
