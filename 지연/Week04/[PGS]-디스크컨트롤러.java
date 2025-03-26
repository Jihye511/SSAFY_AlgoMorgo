//한번더풀어야지..
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
         //요청시각 오름차순 정렬
        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
       
        PriorityQueue <int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        
        int time=0;
        int total =0;
        int idx=0;
        int count=0;
        
        while(count<jobs.length){
            while(idx<jobs.length&&jobs[idx][0]<=time){
                pq.offer(jobs[idx++]);
            }
            if(!pq.isEmpty()){
                int [] job = pq.poll();
                time +=job[1];
                total+=time-job[0];
                count++;
            }
            else{
                time=jobs[idx][0];
            }
        }
      answer=total/jobs.length;
        return answer;
    }
}
