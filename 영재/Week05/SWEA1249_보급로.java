package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1249_보급로 {

    static int N;
    static int[][] map;
    static int ans;
    static int[][] dist; 
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N]; 
            
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            ans=Integer.MAX_VALUE;
            bfs();
            System.out.println("#" + t + " " + ans);
        }
    }
    
    public static void bfs() {
    	Queue<Man> q=new LinkedList<>();
    	q.add(new Man(0,0,0));
    	
    	
    	while(!q.isEmpty()) {
    		Man now=q.poll();
    		
    		if(now.r==N-1&&now.c==N-1) {
    			ans=Math.min(ans, now.len);
    			continue;
    		}
    		
    		
    		for (int i = 0; i < 4; i++) {
				int nr=now.r+dr[i];
				int nc=now.c+dc[i];
				
				if(nr<0||nc<0||nr>=N||nc>=N)
					continue;
				int nd=now.len+map[nr][nc];
				
				if(nd>=dist[nr][nc])
					continue;
				dist[nr][nc]=nd;
				q.add(new Man(nr,nc,nd));
				
			}
    		
    		
    	}
    	
    }


    static class Man {
        int r, c,len;

        public Man(int r, int c, int len) {
            this.r = r;
            this.c = c;
            this.len=len;
        }
    }
}
