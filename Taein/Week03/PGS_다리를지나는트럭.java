import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new ArrayDeque<>();
        int sum = 0;
        int time = 0;
        int temp;
        for (int i = 0; i < truck_weights.length; i++) {
            temp = truck_weights[i];
            while (true){
                if(q.isEmpty()){
                    q.add(temp);
                    sum = temp;
                    time += 1;
                    break;
                } else if (q.size() == bridge_length) {
                    sum -= q.poll();
                } else {
                    if(sum + temp <= weight){
                        q.add(temp);
                        sum += temp;
                        time += 1;
                        break;
                    }
                    else{
                        q.add(0);
                        time+=1;
                    }
                }
            }
        }

        return time + bridge_length;
    }
}
