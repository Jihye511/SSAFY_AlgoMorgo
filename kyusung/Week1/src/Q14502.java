import java.util.ArrayDeque;
import java.util.Scanner;

public class Q14502 {
	
	public static int N, M;
	public static int[][] map;
	public static int[][] make_wall;
	public static ArrayDeque<Node> queue;
	public static int ans = Integer.MIN_VALUE;
	public static class Node{
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		 N = sc.nextInt();
		 M = sc.nextInt();
		
		map = new int[N][M];
		for(int i =0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		make_wall = new int[3][2];
		for(int i =0; i<3; i++) {
			for(int j =0; j<2; j++) {
				make_wall[i][j] = -1;
			}
		}
		
		queue = new ArrayDeque<>();
		dfs(0);
		
		System.out.println(ans);
	}

	private static void dfs(int sum) {
		if(sum == 3) {
			check_zone();
			//System.out.println(Arrays.deepToString(make_wall));
			return;
		}
		
		for(int i =0; i<N; i++) {
			A : for(int j =0; j<M; j++) {
				if(map[i][j] != 0) continue;
				for(int k =0; k < sum; k++) {
					// 골랐던 거면 나가
					if(i == make_wall[k][0] && j == make_wall[k][1]) continue A;
				}
				
				make_wall[sum][0] = i;
				make_wall[sum][1] = j;
				
				dfs(sum + 1);
			}
		}
	}

	public static int[] dX = {1, -1, 0, 0};
	public static int[] dY = { 0, 0, 1, -1};
	private static void check_zone() {
		int[][] new_map = arr_copy(map);
		for(int i =0; i<N; i++) {
			for(int j=0; j<M; j++) {		
				if(new_map[i][j] == 2) queue.add(new Node(i, j));
			}
		}
		
		for(int i = 0 ; i<3; i++) {
			new_map[make_wall[i][0]][make_wall[i][1]] = 1;
		}
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nX = now.x + dX[dir];
				int nY = now.y + dY[dir];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
				if(new_map[nX][nY] == 0) {
					new_map[nX][nY] = 2;
					queue.add(new Node(nX, nY));
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(new_map[i][j] == 0) cnt++;
			}
		}
		
		ans = Math.max(ans, cnt);
	}

	private static int[][] arr_copy(int[][] m) {
		int[][] new_arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_arr[i][j] = m[i][j];
			}
		}
		return new_arr;
	}

}
