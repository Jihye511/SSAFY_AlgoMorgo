import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2]; 
        PriorityQueue<Integer> smallqueue = new PriorityQueue<>();
        PriorityQueue<Integer> bigqueue = new PriorityQueue<>((a, b) -> b - a);
         int cnt_I = 0;  
        int cnt_D = 0;
        for (int i = 0; i < operations.length; i++) {
            String str = operations[i]; 
            String a = str.substring(0, 1); 
            if (a.equals("I")) { 
                cnt_I++;
                int num = Integer.parseInt(str.substring(2, str.length())); 
                smallqueue.add(num);
                bigqueue.add(num);
            } else {
                if (!bigqueue.isEmpty() && !smallqueue.isEmpty()) {
                    
                    if (str.length() == 3) { 
                        bigqueue.poll();
                        cnt_D++;
                    } else {
                        smallqueue.poll();
                        cnt_D++;
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        if ((bigqueue.isEmpty() || smallqueue.isEmpty())||cnt_I<=cnt_D) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            while (!smallqueue.isEmpty()) {
                list.add(smallqueue.poll());
            }
            while (!bigqueue.isEmpty()) {
                int a = bigqueue.poll();
                if (list.contains(a)) {
                    result.add(a);
                }
            }
            Collections.sort(result); 
            answer[0] = result.get(result.size() - 1); 
            answer[1] = result.get(0); 
        }

        return answer;
    }
}
