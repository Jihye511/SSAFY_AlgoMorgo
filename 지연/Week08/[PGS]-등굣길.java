import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        boolean[][] blocked = new boolean[n][m];

        // 물웅덩이 표시
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0] - 1; // 열
            int y = puddles[i][1] - 1; // 행
            blocked[y][x] = true;
        }

        // 시작 지점 초기화
        dp[0][0] = 1;

        // DP 계산
        for (int i = 0; i < n; i++) {        
            for (int j = 0; j < m; j++) {    
                if (blocked[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i > 0) {
                    dp[i][j] += dp[i - 1][j]; 
                }

                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }

                dp[i][j] %= 1_000_000_007;
            }
        }

        return dp[n - 1][m - 1];
    }
}

