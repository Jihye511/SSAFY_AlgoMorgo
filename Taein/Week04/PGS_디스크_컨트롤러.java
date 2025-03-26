import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        
        Arrays.sort(jobs, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int idx = 0;
        int total_time = 0;
        int cnt = 0;
        int end = 0;
        
        while(cnt < jobs.length){
            while(idx < jobs.length && jobs[idx][0] <= end){
                q.add(jobs[idx++]);
            }
            
            if(q.isEmpty()){
                end = jobs[idx][0];
            }
            else{
                int[] cur = q.poll();
                total_time += cur[1] + end - cur[0];
                end += cur[1];
                cnt += 1;
            }
            
        }
        
        return total_time / jobs.length;
    }
}
