import java.util.*;
class 정수삼각형{

	public static int solution(int[][] tri) {
		int N = tri.length;

		int[][] dp = new int[N][N+1];
		dp[0][0] = tri[0][0];

		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) {
					dp[i][j] = dp[i-1][j] + tri[i][j];
				}else if(j == i) {
					dp[i][j] = dp[i-1][j-1] + tri[i][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-1]) + tri[i][j];
				}
			}
		}

		int max = Arrays.stream(dp[N-1]).max().getAsInt();
		return max;
	}
}