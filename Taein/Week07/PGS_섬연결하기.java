// 프림
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

// 프림
/*
import java.util.*;
class Solution {
    static class Node implements Comparable<Node>{
        int to, from, weight;
        Node(int to, int from, int weight){
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node node){
            return this.weight - node.weight;
        }
    }
    
    static int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < costs.length; i++){
            nodes.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        Collections.sort(nodes);
        
        int to, from, weight;
        for(Node node : nodes){
            to = node.to;
            from = node.from;
            if(find(to) != find(from)){
                answer += node.weight;
                union(to, from);
            }
        }
        
        
        return answer;
    }
    
    public void union(int v1, int v2){
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 != p2) parents[p1] = p2;
    }
    
    public int find(int n){
        if(parents[n] == n) return n;
        return find(parents[n]);
    }
}

*/
