package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1238_Contact {
	static class game{
		int n;
		int idx;
		public game(int n,int idx) {
			this.n=n;
			this.idx=idx;
		}
	}
	
	static boolean num[][];
	static boolean v[];
	static int res;
	static int max_idx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int start=Integer.parseInt(st.nextToken());
			int from,to;
			st=new StringTokenizer(br.readLine());
			num=new boolean [101][101];
			v=new boolean[101];
			res=0;
			max_idx=0;
			for (int i = 0; i < N/2; i++) {
				from=Integer.parseInt(st.nextToken());
				to=Integer.parseInt(st.nextToken());
				num[from][to]=true;
			}		
			bfs(start);
			System.out.println("#"+test_case+" "+res);
		}		
	}
	static void bfs(int start) {
		Queue<game> q=new LinkedList<>();
		q.add(new game(start,1));
		v[start]=true;
		while(!q.isEmpty()) {
			game k=q.poll();
			if(k.idx>max_idx) {
				res=k.n;
				max_idx=k.idx;
			}
			if(k.idx==max_idx) {
				if(k.n>res)res=k.n;
			}
			int now=k.n;
			if(now>res)res=now;
			
			for (int i = 1; i < 101; i++) {
				if(num[now][i]&&!v[i]) {
					v[i]=true;
					q.add(new game(i,k.idx+1));
				}
			}
			
		}
		
		
	}

}
