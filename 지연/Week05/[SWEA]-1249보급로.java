import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_1249 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N, result;
	static int[][] area;
	static int[][] dist;

	static class Path implements Comparable<Path> {
		int x;
		int y;
		int recovery;

		public Path(int x, int y, int recovery) {
			this.x = x;
			this.y = y;
			this.recovery = recovery;

		}

		@Override
		public int compareTo(Path o) {
			return this.recovery - o.recovery;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			area = new int[N][N];
			dist = new int[N][N];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					area[i][j] = str.charAt(j) - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			bfs();
			System.out.printf("#%d %d\n", test, result);
		}

	}

	public static void bfs() {
		PriorityQueue<Path> queue = new PriorityQueue<>();
		dist[0][0] = 0;
		queue.add(new Path(0, 0, 0));
		while (!queue.isEmpty()) {
			Path path = queue.poll();
			int x = path.x;
			int y = path.y;
			int recovery = path.recovery;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					int nextRecovery = recovery + area[nx][ny];
					if (nextRecovery < dist[nx][ny]) {
						dist[nx][ny] = nextRecovery;

						queue.add(new Path(nx, ny, nextRecovery));
					}

				}

				if (x == N - 1 && y == N - 1) {
					result = Math.min(result, recovery);
				}

			}
		}
	}
}
