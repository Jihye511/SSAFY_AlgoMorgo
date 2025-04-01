import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Arrays;

class SweaQ1249
{
    	public static int[] dX = {0, 0, -1, 1};
	public static int[] dY = {1, -1, 0, 0};
	
	public static class Node{
		int x, y; 
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

    
	public static void main(String args[]) throws Exception
	{
	
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
int N = sc.nextInt();
		String[] strs = new String[N];
		for(int i =0; i<N; i++){
			strs[i] = sc.next();
		}
		
		int[][] map = new int[N][N];
		for(int i =0; i<N; i++) {
			for(int j =0; j<N; j++) {
				map[i][j] = strs[i].charAt(j) - '0';
			}
		}
	
		int[][] cost = new int[N][N];
		for(int i =0; i<N; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		
		cost[0][0] = map[0][0];
		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0));
		
		while(!queue.isEmpty()) {
			
			Node now = queue.poll();
			
			for(int dir = 0; dir<4; dir++) {
				
				int nX = now.x + dX[dir];
				int nY = now.y + dY[dir];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
				if(cost[nX][nY] < cost[now.x][now.y]) continue;  
				
				if(cost[nX][nY] > cost[now.x][now.y] + map[nX][nY] ) {
					cost[nX][nY] = cost[now.x][now.y] + map[nX][nY];
					queue.add(new Node(nX, nY));
				}
			}
		}
		
            System.out.println("#"+test_case + " "  + cost[N-1][N-1]);
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}
}