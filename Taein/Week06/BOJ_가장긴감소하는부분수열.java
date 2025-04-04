import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < dp.length; j++) {
				if(nums[i] > nums[j]) dp[j] = Math.max(dp[j], dp[i] + 1);
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			if(result < dp[i]) result = dp[i];
		}
		System.out.println(result);
		
	}
}
