package study;

import java.util.*;

class Q디스크컨트롤러 {
    public static class Node {
        int in, len, idx;

        public Node(int in, int len, int idx) {
            this.in = in;
            this.len = len;
            this.idx = idx;
        }
    }

    public int solution(int[][] jobs) {
        
        // 모든 프로세스들
        PriorityQueue<Node> processes = new PriorityQueue<>((o1, o2) -> {
            if (o1.in != o2.in) return Integer.compare(o1.in, o2.in);
            else return Integer.compare(o1.idx, o2.idx);
        });
        
        for(int i =0; i<jobs.length; i++){
            processes.add(new Node(jobs[i][0], jobs[i][1], i));
        }
        
        // 처리 가능 (입장)
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.len != o2.len) return Integer.compare(o1.len, o2.len);
            else if (o1.in != o2.in) return Integer.compare(o1.in, o2.in);
            else return Integer.compare(o1.idx, o2.idx);
        });

        int sum = 0;
        boolean now_processing = false;
        int how_many_remain = 0;
        int time = 0; 
        
       while (!processes.isEmpty() || !pq.isEmpty() || now_processing){
            //System.out.println("time : " + time); 

            // 해결 중인 프로세스가 있다면
            if(now_processing){
                how_many_remain--;
                
                if(how_many_remain == 0) {
                    now_processing = false;
                }
            }
            
            // pq에 넣어야 할 숫자가 있다면
            while(!processes.isEmpty() && time == processes.peek().in){
                pq.add(processes.poll());
                //System.out.println(time + " : pr : " + processes.size() + " pq :" + pq.size()); 
            }
            
            // 착수 가능한지
            if(!pq.isEmpty() && !now_processing){
                Node now = pq.poll();
                
                now_processing = true;
                how_many_remain = now.len;
                sum += (time+now.len) - now.in;
                
                System.out.println("착수 : " + time + " : " + now.len + " " + now.in); 
            }
           time++;
        }
        
        

        return sum / jobs.length;
    }
}
