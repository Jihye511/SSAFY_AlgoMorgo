import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Q섬연결하기 {

	public static class Node{
		int dest, dist;
		
		public Node(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {

		int N = 4;
		int[][] costs = {
				{0, 1, 1}, 
				{0, 2, 2}, 
				{1, 2, 5},
				{1, 3, 1},
				{2, 3, 8}
				};
		
		int M = costs.length;
		
		ArrayList<Node> list[] = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			list[costs[i][0]].add(new Node(costs[i][1], costs[i][2]));
			list[costs[i][1]].add(new Node(costs[i][0], costs[i][2]));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));
		boolean[] visited = new boolean[N]; 
		
		
		// 0번째 섬부터 시작
		pq.add(new Node(0, 0));
		int ans = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.dest]) continue;
			
			visited[now.dest] = true;
			ans += now.dist;
			
			for(int i = 0; i<list[now.dest].size(); i++) {
				Node next = list[now.dest].get(i);
				pq.add(next);
			}
			
		}
		
		System.out.println(ans);
		
	}

}
