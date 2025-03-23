import java.util.*;
class Q프로세스 {
    
    public static class Node{
        int idx, value ;
        
        public Node(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        ArrayDeque<Node> queue = new ArrayDeque<>();
        
        for(int i = 0; i<priorities.length; i++){
            pq.add(priorities[i]);
            queue.add(new Node(i, priorities[i]));
        }
        
        int cnt = 0;
        A:while(!pq.isEmpty()){
            int max = pq.poll();
            
            while(!queue.isEmpty()){
                Node now = queue.poll();
    
                if(now.value == max){
                   if(now.idx == location){
                       break A;
                   }
                    cnt++;
                    break;
                }else{
                    queue.add(now);
                }
            }
        }

        return cnt+1;
    }
}