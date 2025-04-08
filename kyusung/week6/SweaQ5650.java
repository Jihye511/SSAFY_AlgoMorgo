import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SweaQ5650 {

	public static class Node{
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int[] dX = {0, 1, 0, -1};
	public static int[] dY = {1, 0, -1, 0};
	public static int score, N;
	public static int[][] map;
	public static HashMap<Integer, Integer> hmap;
	public static int[][] dirChange = {
			{2, 0, 3, 1},
			{2, 3, 1, 0},
			{1, 3, 0, 2},
			{3, 2, 0, 1},
			{2, 3, 0, 1}
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		int portal_num = 0;

		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				map[i][j] = sc.nextInt();

				if(map[i][j] >= 6) {
					portal_num = Math.max(portal_num, map[i][j] - 5);
				}
			}
		}

		// portal mapping
		int[][] finded_portal = new int[portal_num][2];
		for(int i = 0; i<portal_num; i++) {
			Arrays.fill(finded_portal[i], -1);
		}

		hmap = new HashMap<>();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] >= 6) {
					int find = map[i][j] - 6;

					if(finded_portal[find][0] == -1) {
						finded_portal[find][0] = i;
						finded_portal[find][1] = j;
					}else {
						int a = i * 100 + j;
						int b = finded_portal[find][0] * 100 + finded_portal[find][1];
						hmap.put(a, b);
						hmap.put(b, a);
					}
				}
			}
		}

		score = 0;
		// simul
		for(int i = 0; i<N; i++) {
			for (int j = 0; j < N; j++) {		
				if(map[i][j] != 0) continue;
				for (int d = 0; d < 4; d++) {
					//System.out.println("시작 : " + i + " " + j + " " + d);
					int s = go(i, j, d);
					score = Math.max(score, s);
				}
			}
		}

		System.out.println(score);
	}

	private static int go(int r, int c, int d) {
		int score = 0;
		int nowR = r;
		int nowC = c;
		int move = 0;
		
		while(true) {
			
			if(nowR == r && nowC == c && move > 0) break;
			if(map[nowR][nowC] == -1) break;
			
			nowR += dX[d];
			nowC += dY[d];
			move++;
			
			// 벽
			if(nowR < 0 || nowR >= N || nowC < 0 || nowC >= N) {
				nowR -= dX[d];
				nowC -= dY[d];
				d = (d+2) % 4;
				score ++;
			}
			
			// 블록
			if(map[nowR][nowC] >= 1 && map[nowR][nowC] < 6) {
				int block = map[nowR][nowC];
				
				d = dirChange[block-1][d];
				score++;
				continue;
			}
			
			// 웜홀
			if(map[nowR][nowC] > 5) {
				int next = hmap.get(nowR * 100 + nowC);
				nowR = next / 100;
				nowC = next % 100;
			}
			
		}

		return score;
	}
}
