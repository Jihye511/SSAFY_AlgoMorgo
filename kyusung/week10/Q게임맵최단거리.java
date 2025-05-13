import java.util.ArrayDeque;

class Q게임맵최단거리 {
	
	public static class Node{
		int x, y, d;

		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static int[] dX = {-1, 1, 0, 0};
	public static int[] dY = {0, 0, 1, -1};

	public int solution(int[][] maps) {
		int N = maps.length;
		int M = maps[0].length;

		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 1));
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		int ans = -1;

		while(!queue.isEmpty()) {

			Node next = queue.poll();
			if(next.x == N-1 && next.y == M-1) {
				ans = next.d;
				break;
			}

			for(int dir = 0; dir < 4; dir++) {
				int nX = next.x + dX[dir];
				int nY = next.y + dY[dir];

				if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
				if(visited[nX][nY]) continue;
				if(maps[nX][nY] == 0) continue;

				visited[nX][nY] = true;
				queue.add(new Node(nX, nY, next.d + 1));
			}
		}

		return ans;		
	}
}