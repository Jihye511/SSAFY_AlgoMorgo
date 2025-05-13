import java.util.*;

class Q가장먼노드 {
	
	class Node{
	    int loc, dist; 
	    
	    Node(int loc, int dist){
	        this.loc = loc;
	        this.dist = dist;
	    }
	}
	
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i = 0; i< n+1; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i =0; i<edge.length; i++){
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        
        int[] dist = new int[n+1];
        boolean[] v = new boolean[n+1];
        
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 0));
        v[1] = true;
        
        while(!queue.isEmpty()){
            Node now = queue.poll();
            //System.out.println(now.loc); 
            for(int i = 0; i<list[now.loc].size(); i++){
                int next = list[now.loc].get(i);
                //System.out.println(next); 
                if(v[next]) continue;
                else{
                    v[next] = true;
                    dist[next] = now.dist+1;
                    queue.add(new Node(next, now.dist + 1));
                } 
                
            }
        }
        
        //System.out.println(Arrays.toString(dist));
        int max = Arrays.stream(dist).max().getAsInt();
        int cnt = 0;
        for(int i =0; i<n+1; i++){
            if(max == dist[i]) cnt++;
        }

        answer = cnt;
        return answer;
    }
}

