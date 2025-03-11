import java.util.*;
import java.io.*;

public class BOJ15683 {
	
	static class CCTV{
		int x, y, dire;
		CCTV(int x, int y, int dire){
			this.x = x;
			this.y = y;
			this.dire = dire;
		}
	}
	
	static int N, M, result;
	static ArrayList<CCTV> cctvs;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
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
		
		
		result = 65;
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
			print(turn_map);
			count(turn_map);
			System.exit(0);
			return;
		}
		print(turn_map);
		int[][] new_map = clone_map(turn_map);
		
		int[][] temp_map;
		int x = cctvs.get(idx).x;
		int y = cctvs.get(idx).x;
		
		
		
	}
	
	private static void print(int[][] map){
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
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
