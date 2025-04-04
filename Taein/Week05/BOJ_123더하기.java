import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] stairs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i < 12; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		int N;
		
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
		
		
	}
}
