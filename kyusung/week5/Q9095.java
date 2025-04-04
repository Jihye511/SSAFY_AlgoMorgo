import java.util.Scanner;

public class Q9095 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 0; tc<T; tc++) {
			
			int N = sc.nextInt();
			if(N == 1) {
				sb.append("1").append('\n');
				continue;
			}else if(N == 2) {
				sb.append("2").append('\n');
				continue;
			}else if(N == 3) {
				sb.append("4").append('\n');
				continue;
			}
			
			int[] dp = new int[N+1];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i = 4; i<=N; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			sb.append(dp[N]).append('\n');
		}
		
		System.out.println(sb.toString());
		
	}

}
