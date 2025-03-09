package algo;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {
	static int N, M;
	static int map[][];
	static List<Point> list;
	static int dr[]= {-1,0,1,0};
	static int dc[]= {0,1,0,-1};
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		map = new int[N][M];
		res=0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					list.add(new Point(i, j));
			}
		}

		dfs(0, 0, 0);
		System.out.println(res);
	}

	static void dfs(int r, int c, int idx) {
		if (idx == 3) {
//			print(map);
			int[][] nMap=new int[N][M];
			nMap=copy(map);
			bfs(nMap);
//			print(nMap);
			count(nMap);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;

					dfs(i, j, idx + 1);
					map[i][j] = 0;
				}
			}
		}

	}

	static void bfs(int map[][]) {
		Queue<Point> q=new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		
		while(!q.isEmpty()) {
			Point now=q.poll();
			for (int i = 0; i < 4; i++) {
				int nr=now.x+dr[i];
				int nc=now.y+dc[i];
				
				if(nr<0||nc<0||nr>=N||nc>=M) continue;
				if(map[nr][nc]!=0) continue;
				map[nr][nc]=2;
				q.add(new Point(nr,nc));
			}
		}
	}
	
	static void count(int[][] map) {
		int tmp=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) {
					tmp++;
				}
			}
		}
//		System.out.println("안전영역 개수 "+ tmp);
		if(tmp>res)res=tmp;
	}
	static int[][] copy(int[][] map){
		int[][] nMap=new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				nMap[i][j]=map[i][j];
			}
		}
		return nMap;
	}
	
	static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
