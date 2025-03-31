import java.util.*;
import java.io.*;
public class Solution {
     
	static class Node implements Comparable<Node>{
		int from, to;
		long cost;
		
		Node(int from, int to, long cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
		
		
	}
	
	static int N;
	static long[] x;
    static long[] y;
    static double E;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;
        
        for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			x = new long[N];
			y = new long[N];
			parents = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			make();
			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			long dist;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					pq.add(new Node(i, j, dist));
				}
			}
			
			long result = 0;
			int cnt = 0;
			
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				if(union(node.from, node.to)) continue;
				result += node.cost;
				if(++cnt == N - 1) break;
			}
			System.out.println("#" + t + " " + Math.round(result * E));
		}
	}
	private static boolean union(int from, int to) {
		int aRoot = find(from);
		int bRoot = find(to);
		if(aRoot == bRoot) return true;
		parents[bRoot] = aRoot;
		return false;
	}
	private static int find(int from) {
		if(parents[from] == from) return from;
		return parents[from] = find(parents[from]);
	}
	private static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
     
    
    
}
