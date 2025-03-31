// 하나로
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SweaQ1251 {

	public static class Loca{
		int x, y;
		public Loca(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Node{
		double dist;
		int dest;
		
		public Node(double dist, int dest) {
			this.dist = dist;
			this.dest = dest;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Loca[] islands = new Loca[N];
		
		ArrayList<Node>[] list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int[][] arr = new int[2][N];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		double E = sc.nextDouble();
		for(int i = 0; i<N; i++) {
			islands[i] = new Loca(arr[0][i], arr[1][i]);
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = i; j<N; j++) {
				Loca a = islands[i];
				Loca b = islands[j];
				
				double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y , 2));
				double cost = E * Math.pow(distance, 2);
				
				list[i].add(new Node(cost, j));
				list[j].add(new Node(cost, i));
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> Double.compare(o1.dist, o2.dist));
		pq.add(new Node(0, 0));
		
		double total = 0;
		boolean[] visited = new boolean[N];
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.dest]) continue;
			
			visited[now.dest] = true;			
			total += now.dist;
			
			for(Node n : list[now.dest]) {
				if(!visited[n.dest]) {
					pq.add(n);
				}
			}
		}
		
		System.out.println(Math.round(total));
	}

}
