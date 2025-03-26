package ps;

import java.util.*;

class Q이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq_min = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        PriorityQueue<Integer> pq_max = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        
        for(int i = 0; i<operations.length; i++){
            String[] strs = operations[i].split(" ");
            
            if(strs[0].charAt(0) == 'I'){
                pq_min.add(Integer.parseInt(strs[1]));
                pq_max.add(Integer.parseInt(strs[1]));
            }else if(operations[i].equals("D 1")){
            	while(!pq_max.isEmpty() && !pq_min.contains(pq_max.peek())) {
                	pq_max.poll();
            	}
            	pq_max.poll();
            }else if(operations[i].equals("D -1")){
            	while(!pq_min.isEmpty() && !pq_max.contains(pq_min.peek())) {
                	pq_min.poll();
            	}
            	pq_min.poll();
            }
        }
        
        if(pq_max.size() == 0 || pq_min.size() == 0) {
        	return (new int[2]);
        }
        
        int[] maxs = pq_max.stream().mapToInt(Integer::intValue).toArray();
        int[] mins = pq_min.stream().mapToInt(Integer::intValue).toArray();
        int max_value = 0;
        int min_value = Integer.MAX_VALUE;

        for(int i = 0; i<maxs.length; i++){
            if(maxs[i] > max_value) {
                for(int j =0; j<mins.length; j++){
                    if(mins[j] == maxs[i]) max_value = maxs[i];
                }
            }
            
            if(maxs[i] < min_value) {
                for(int j =0; j<mins.length; j++){
                    if(mins[j] == maxs[i]) min_value = maxs[i];
                }
            }
        }
        
        answer[0] = max_value;
        answer[1] = min_value;
        return answer;
    }
}