import java.util.*;

class Q기능개발 {
    
     public static int[] solution(int[] prog, int[] spds){
     
         ArrayDeque<Integer> queue = new ArrayDeque<>();
        
         for(int i =0; i<prog.length; i++){
             int rest = (int) Math.ceil( (100.0 - prog[i]) / spds[i] );
             queue.add(rest);
         }

         ArrayList<Integer> list = new ArrayList<>();
         while(!queue.isEmpty()){
            int key = queue.poll();         
            int cnt = 1;
             
            while(!queue.isEmpty() && key >= queue.peek()){
                queue.poll();
                cnt++;
            }
            list.add(cnt); 
         }
         
         return list.stream().mapToInt(Integer::intValue).toArray();
      }
}