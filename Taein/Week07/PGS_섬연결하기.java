import java.util.*;
class Solution {
    static class Node implements Comparable<Node>{
        int next, cost;
        Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        ArrayList<Node>[] list = new ArrayList[n];
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<Node>();
        }
        
        int x, y;
        for(int i = 0; i < costs.length; i++){
            x = costs[i][0];
            y = costs[i][1];
            list[x].add(new Node(y, costs[i][2]));
            list[y].add(new Node(x, costs[i][2]));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0));
        boolean[] visited = new boolean[n];
        
        Node temp;
        while(!q.isEmpty()){
            temp = q.poll();
            if(visited[temp.next]) continue;
            else{
                visited[temp.next] = true;
                answer += temp.cost;
            }
            for(Node node : list[temp.next]){
                if(!visited[node.next]){
                    q.add(node);
                }
            }
                
        }
        
        return answer;
    }
}
