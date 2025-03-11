import java.util.*;
import java.io.*;

public class BOJ15683_2 {
	
	static class CCTV{
		int x, y, dire;
		CCTV(int x, int y, int dire){
			this.x = x;
			this.y = y;
			this.dire = dire;
		}
		@Override
		public String toString() {
			return "CCTV [x=" + x + ", y=" + y + ", dire=" + dire + "]";
		}
		
	}
	
	static int N, M, result;
	static ArrayList<CCTV> cctvs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		cctvs = new ArrayList<>();
		
		int temp;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp > 0 && temp != 6) {
					cctvs.add(new CCTV(i, j, temp));
				}
				
			}
		}
		
		result = Integer.MAX_VALUE;
		recur(map, 0);
		System.out.println(result);
		
	}
	private static int[][] clone_map(int[][] turn_map) {
		int[][] new_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_map[i][j] = turn_map[i][j];
			}
		}
		return new_map;
	}
	
	
	private static void recur(int[][] turn_map, int idx) {
		if(idx == cctvs.size()) {
			count(turn_map);
			
//			print(turn_map);
			return;
		}
		int[][] new_map = clone_map(turn_map);
		
		int[][] temp_map;
		int x = cctvs.get(idx).x;
		int y = cctvs.get(idx).y;
		switch(cctvs.get(idx).dire) {
			case 1:
				for (int i = 0; i < 4; i++) {
					temp_map = turn1(new_map, i, x, y);
					recur(temp_map, idx + 1);
				}
				break;
			case 2:
				for (int i = 0; i < 2; i++) {
					temp_map = turn2(new_map, i, x, y);
					recur(temp_map, idx + 1);
				}
				break;
			case 3:
				for (int i = 0; i < 4; i++) {
					temp_map = turn3(new_map, i, x, y);
					recur(temp_map, idx + 1);
				}
				break;
			case 4:
				for (int i = 0; i < 4; i++) {
					temp_map = turn4(new_map, i, x, y);
					recur(temp_map, idx + 1);
				}
				break;
			case 5:
				temp_map = turn5(new_map, x, y);
				recur(temp_map, idx + 1);
				break;
		}
	}
	private static void print(int[][] map){
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	private static int[][] left(int[][] turn_map, int x, int y){
		int[][] new_map = clone_map(turn_map);
		for (int i = y; i >= 0; i--) {
			y = y - 1;
			if(y < 0) break;
			if(new_map[x][y] == 0) {
				new_map[x][y] = -1;
			}
			else if (new_map[x][y] == 6){
				break;
			}
			
		}
		return new_map;
	}
	
	private static int[][] right(int[][] turn_map, int x, int y){
		int[][] new_map = clone_map(turn_map);
		for (int i = y; i < M; i++) {
			y = y + 1;
			if(y >= M) break;
			if(new_map[x][y] == 0) {
				new_map[x][y] = -1;
			}
			else if (new_map[x][y] == 6){
				break;
			}
			
		}
		return new_map;
	}
	
	private static int[][] up(int[][] turn_map, int x, int y){
		int[][] new_map = clone_map(turn_map);
		for (int i = x; i >= 0; i--) {
			x -= 1;
			if(x < 0) break;
			if(new_map[x][y] == 0) {
				new_map[x][y] = -1;
			}
			else if (new_map[x][y] == 6){
				break;
			}
			
		}
		return new_map;
	}
	
	private static int[][] down(int[][] turn_map, int x, int y){
		int[][] new_map = clone_map(turn_map);
		for (int i = x; i < N; i++) {
			x += 1;
			if(x >= N) break;
			if(new_map[x][y] == 0) {
				new_map[x][y] = -1;
			}
			else if (new_map[x][y] == 6){
				break;
			}
			
		}
		return new_map;
	}
	
	
	private static int[][] turn1(int[][] turn_map, int turn, int x, int y){
		int[][] new_map = clone_map(turn_map);
		
		int[][] temp = null;
		switch(turn) {
			// 우측
			case 0:
				temp = right(new_map, x, y);
				break;
			// 좌측
			case 1:
				temp = left(new_map, x, y);
				break;
			// 상단
			case 2:
				temp = up(new_map, x, y);
				break;
			// 하단
			case 3:
				temp = down(new_map, x, y);
				break;
		}
		
		return temp;
	}
	
	private static int[][] turn2(int[][] turn_map, int turn, int x, int y){
		int[][] new_map = clone_map(turn_map);
		int[][] temp = null;
		switch(turn) {
			// 좌, 우
			case 0:
				temp = left(new_map, x, y);
				temp = right(temp, x, y);
				break;
			// 상, 하
			case 1:
				temp = up(new_map, x, y);
				temp = down(temp, x, y);
				break;
		}
		
		return temp;
	}

	private static int[][] turn3(int[][] turn_map, int turn, int x, int y){
		int[][] new_map = clone_map(turn_map);
		int[][] temp = null;
		switch(turn) {
			// 상, 우
			case 0:
				temp = up(new_map, x, y);
				temp = right(temp, x, y);
				break;
			// 우, 하
			case 1:
				temp = right(new_map, x, y);
				temp = down(temp, x, y);
				break;
			// 하, 좌
			case 2:
				temp = down(new_map, x, y);
				temp = left(temp, x, y);
				break;
			// 좌 상
			case 3:
				temp = left(new_map, x, y);
				temp = up(temp, x, y);
				break;
		}
		
		return temp;
	}
	

	private static int[][] turn4(int[][] turn_map, int turn, int x, int y){
		int[][] new_map = clone_map(turn_map);
		int[][] temp = null;
		switch(turn) {
			// 좌, 상, 우
			case 0:
				temp = left(new_map, x, y);
				temp = up(temp, x, y);
				temp = right(temp, x, y);
				break;
			// 상, 우, 하
			case 1:
				temp = up(new_map, x, y);
				temp = right(temp, x, y);
				temp = down(temp, x, y);
				break;
			// 우, 하, 좌
			case 2:
				temp = right(new_map, x, y);
				temp = down(temp, x, y);
				temp = left(temp, x, y);
				break;
			// 하, 좌, 상
			case 3:
				temp = down(new_map, x, y);
				temp = left(temp, x, y);
				temp = up(temp, x, y);
				break;
		}
		
		return temp;
	}
	
	private static int[][] turn5(int[][] turn_map, int x, int y){
		int[][] new_map = clone_map(turn_map);
		int[][] temp;
		temp = down(new_map, x, y);
		temp = left(temp, x, y);
		temp = up(temp, x, y);
		temp = right(temp, x, y);
		
		return temp;
	}
	
	
	private static void count(int[][] turn_map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(turn_map[i][j] == 0) cnt +=1;
			}
		}
		result = Math.min(cnt, result);
	}
	
}
