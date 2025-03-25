import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i : scoville){
            q.add(i);
        }
        int a, b;
        int cnt = 0;
        while(q.size() > 1){
            if(q.peek() >= K) break;
            a = q.poll();
            b = q.poll();
            q.add(a + (2 * b));
            cnt += 1;
        }
        if(q.peek() < K) return -1;

        return cnt;
    }
}
