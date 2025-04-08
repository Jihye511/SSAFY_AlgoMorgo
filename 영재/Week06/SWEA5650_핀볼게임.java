package SWEA;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5650_핀볼게임 {
	static int N;
	static int map[][];
	static int er, ec;
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };
	static int direct[][] = { { 0, 2, 1, 3, 2, 2 }, { 0, 3, 3, 2, 0, 3 }, { 0, 1, 0, 0, 3, 0 }, { 0, 0, 2, 1, 1, 1 } };
	static List<Point> wormhole[];
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			res = 0;
			wormhole = new ArrayList[11];
			for (int i = 0; i < 11; i++) {
				wormhole[i] = new ArrayList<>();
			}

			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {

						wormhole[map[i][j]].add(new Point(i, j));
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {

						for (int d = 0; d < 4; d++) {
							er = i;
							ec = j;
							game(er, ec, d, 0);
						}
					}

				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(res);
			System.out.println(sb);

		}
	}

	public static void game(int sr, int sc, int dir, int score) {

		int nr = sr + dr[dir];
		int nc = sc + dc[dir];
		int point=0;
		if(nr>=0&&nc>=0&&nr<N&&nc<N) {
			point = map[nr][nc];
		}
		
		// 벽 만난 경우
		if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
			score++;
			dir = (dir + 2) % 4;
		} else if (point >= 1 && point <= 5) { // 블록
			score++;
			dir = direct[dir][point];
		} else if (point >= 6) { // 포탈
			if (nr == wormhole[point].get(0).x && nc == wormhole[point].get(0).y) {
				nr = wormhole[point].get(1).x;
				nc = wormhole[point].get(1).y;
			} else {
				nr = wormhole[point].get(0).x;
				nc = wormhole[point].get(0).y;
			}//블랙홀이나 시작점
		} else if (point == -1 || (nr == er && nc == ec)) {
			res=Math.max(score, res);
			return;
		}
		game(nr, nc, dir, score);
	}

}
