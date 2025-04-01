package SWEA;
import java.io.*;
import  java.util.*;
public class SWEA_1249_보급로 {
	static int N;
	static int[][] map;
	static int[][] dis;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t =1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dis = new int[N][N];
			for(int i =0; i<N; i++) {
				String str = br.readLine();
				for(int j =0; j<N; j++) {
					map[i][j] = str.charAt(j)-'0';
					dis[i][j] = Integer.MAX_VALUE;
				}
			}
			
			move(0,0,0);
			System.out.println("#"+t+" " + dis[N-1][N-1]);
		}

	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void move(int x, int y,int time) {
		PriorityQueue<int[]>pq= new PriorityQueue<>((a,b)->a[2]-b[2]);
		pq.offer(new int[] {x,y,time});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			for(int i =0; i<4; i++) {
				int nx = cur[0] +dx[i];
				int ny = cur[1] +dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(map[nx][ny]+ cur[2] < dis[nx][ny]) {
					dis[nx][ny] = map[nx][ny]+ cur[2];
					pq.offer(new int[] {nx,ny,dis[nx][ny]});
				}
			}
		}
	}

}
