import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1251 {
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;

	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);

	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = (V * (V - 1)) / 2;
			int[][] area = new int[V][2];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				area[j][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				area[j][1] = Integer.parseInt(st.nextToken());
			}
			double C = Double.parseDouble(br.readLine());

			edgeList = new Edge[E];
			int b = 0;
			for (int i = 0; i < V; i++) {
				for (int j = i + 1; j < V; j++) {
					double dist = Math.pow(area[i][0] - area[j][0], 2) + Math.pow(area[i][1] - area[j][1], 2);
					edgeList[b] = new Edge(i, j, dist * C);
					b++;
				}

			}
			make(); // v개의 독립된 트리 생성
			Arrays.sort(edgeList);
			double result = 0, count = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++count == V - 1) {
						break;
					}
				}
			}
			System.out.printf("#%d %d\n", test, Math.round(result));

		}
	}

}
