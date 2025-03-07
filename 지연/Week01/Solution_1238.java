import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238 {
	static int N, S;
	static int maxinum, result;
	static List<Node> list;
	static boolean[] visited;

	static class Node {
		int from;
		int to;
		int sum;

		public Node(int from, int to, int sum) {
			super();

			this.from = from;
			this.to = to;
			this.sum = sum;
		}

		public String toString() {
			return "(" + from + " -> " + to + ")";
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int test = 1; test <= 10; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			list = new LinkedList<>();
			maxinum = 0;
			result = 0;
			visited = new boolean[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list.add(new Node(from, to, 0));

			}
			visited[S] = true;
			bfs(S, 0);

			for (int i = 0; i < list.size(); i++) {
				if (maxinum == list.get(i).sum) {

					result = Math.max(result, list.get(i).to);
				}

			}
			System.out.printf("#%d %d\n", test, result);
		}

	}

	public static void bfs(int start, int sum) {
		Queue<int[]> queue = new LinkedList();
		queue.add(new int[] { start, sum });
		while (!queue.isEmpty()) {
			int pos[] = queue.poll();
			int x = pos[0], s = pos[1];
			maxinum = Math.max(maxinum, s);

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).from == x && !visited[list.get(i).to]) {
					visited[list.get(i).to] = true;
					queue.add(new int[] { list.get(i).to, s + 1 });
					list.get(i).sum = s + 1;

				}
			}
		}

	}

}
