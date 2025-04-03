import java.util.*;
import java.io.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		Arrays.fill(dp, 1000001);
		dp[N] = 0;
		for (int i = N; i > 0; i--) {
			if(i % 3 == 0) {
				dp[i / 3] = Math.min(dp[i/3], dp[i] + 1);
			}
			if(i % 2 == 0) {
				dp[i / 2] = Math.min(dp[i/2], dp[i] + 1);
			}
			if(i - 1 > 0) {
				dp[i - 1] = Math.min(dp[i-1], dp[i] + 1);
			}
		}
		
		System.out.println(dp[1]);
	}
}
