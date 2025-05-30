import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for (int i = 4; i < N+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		} 
		System.out.println(dp[N]);
		
	}
}
