import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min_q = new PriorityQueue<>();
        PriorityQueue<Integer> max_q = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < operations.length; i++){
            String[] ord = operations[i].split(" ");
            int k = Integer.parseInt(ord[1]);
            if(ord[0].equals("I")){
                min_q.add(k);
                max_q.add(k);
            }
            else{
                if(k == -1){
                    if(min_q.size() == 0) continue;
                    int temp = min_q.poll();
                    max_q.remove(temp);
                }
                else{
                    if(max_q.size() == 0) continue;
                    int temp = max_q.poll();
                    min_q.remove(temp);
                }
            }
        }
        int[] result = new int[2];
        result[0] = 0;
        result[1] = 0;
        if(max_q.size() > 0){
            result[0] = max_q.poll();
        }

        
        if(min_q.size() > 0){
            result[1] = min_q.poll();
        }

        return result;
    }
}
