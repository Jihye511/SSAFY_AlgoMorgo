import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        for(int i : lost){
            dp[i] -= 1;
        }
        for(int i : reserve){
            dp[i] += 1;
        }
        
        for(int i = 1; i <= n; i++){
            if(dp[i] == 0){
                if(dp[i-1] == 2) {
                    dp[i] += 1;
                    dp[i-1] -= 1;
                }
                else if(i + 1 <= n && dp[i+1] == 2){
                    dp[i] += 1;
                    dp[i+1] -= 1;
                }
            }
        }
        for(int i = 1; i <= n; i++){
            if(dp[i] > 0) answer += 1;
        }
        return answer;
    }
}
