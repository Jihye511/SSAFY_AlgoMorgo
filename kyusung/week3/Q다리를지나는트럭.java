import java.util.*;

class Q다리를지나는트럭 {
    
    public static class Node{
        int weight, when;
        
        public Node(int weight , int when){
            this.weight = weight;
            this.when = when;
        }
    }
    
    public static int solution(int bridge_length, int weight, int[] truck_weights){

        ArrayDeque<Integer> waitings = new ArrayDeque<>();
        ArrayDeque<Node> on_bridge = new ArrayDeque<>();
        int now_on_weight = 0;
        int now_on_trucks = 0;
        
        for(int i =0; i<truck_weights.length; i++){
            waitings.add(truck_weights[i]);
        }
        
        int time = 0;
        while (!waitings.isEmpty() || !on_bridge.isEmpty()){
            time++;
            
            if(!on_bridge.isEmpty()){
                    Node who_out = on_bridge.peek();
                    if(time - who_out.when == bridge_length){
                        now_on_trucks--;
                        now_on_weight -= who_out.weight;
                        on_bridge.poll();
                    }
            }
            
            if(!waitings.isEmpty()){
                int who_in = waitings.peek();
                if(now_on_trucks < bridge_length && now_on_weight + who_in <= weight){
                    on_bridge.add(new Node(who_in, time));
                    now_on_trucks++;
                    now_on_weight += who_in;
                    waitings.poll();
                }
            }
        }

        return time;
    }
}