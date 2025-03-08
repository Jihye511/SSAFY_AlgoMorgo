import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502 {
	static int[][] area;
	static int N, M;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static List<zero> wall;
	static int[] numbers;
	static int result;

	static class zero {
		int x;
		int y;

		zero(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = new int[N][M];
		wall = new LinkedList<>();
		numbers = new int[3];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if (area[i][j] == 0) {
					wall.add(new zero(i, j));
				}
			}
		}

		// System.out.println(wall.size());
		// 벽 3개 조합 만들고 그 만들면 바이러스 퍼지고 이제 0의 개수
		dfs(0, 0);
		System.out.println(result);
	}

	public static void dfs(int cnt, int start) {
		if (cnt == 3) {

			int[][] temp_area = deepCopy(area);
			for (int i = 0; i < 3; i++) {
				temp_area[wall.get(numbers[i]).x][wall.get(numbers[i]).y] = 1;

			}
			for (int a = 0; a < N; a++) {

				for (int b = 0; b < N; b++) {

					if (temp_area[a][b] == 2) {
						spread(a, b, temp_area);

					}
				}
			}

			count(temp_area);

			return;
		}
		for (int i = start; i < wall.size(); i++) {

			numbers[cnt] = i;
			dfs(cnt + 1, i + 1);
		}

	}

	public static void spread(int i, int j, int[][] temp_area) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.add(new int[] { i, j });
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			int pos[] = queue.poll();
			int x = pos[0], y = pos[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && temp_area[nx][ny] == 0) {
					visited[nx][ny] = true;
					temp_area[nx][ny] = 2;
					queue.add(new int[] { nx, ny });
				}
			}
		}
	}

	public static void count(int[][] temp_area) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp_area[i][j] == 0) {
					count++;
				}
			}
		}
		result = Math.max(result, count);
	}

	public static int[][] deepCopy(int[][] original) {
		if (original == null)
			return null;
		int[][] copy = new int[original.length][];
		for (int i = 0; i < original.length; i++) {
			copy[i] = original[i].clone();
		}
		return copy;
	}

}
