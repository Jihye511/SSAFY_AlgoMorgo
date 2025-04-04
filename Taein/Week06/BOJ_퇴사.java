import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 0 : 소요 시간, 1 : 비용 
		int[][] couns = new int[N+1][2];
		int[] dp = new int[N+1];
		
		StringTokenizer st;
		int a, b;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			couns[i][0] = a;
			couns[i][1] = b;
		}
		
		for (int i = 0; i < N; i++) {
			if(i + couns[i][0] <= N) {
				dp[i + couns[i][0]] = Math.max(dp[i + couns[i][0]], dp[i] + couns[i][1]);
				for (int j = i + couns[i][0]+1; j <=N; j++) {
					dp[j] = Math.max(dp[j], dp[j-1]);
				}
			}
			
			
		}
		System.out.println(dp[N]);
	}
}
