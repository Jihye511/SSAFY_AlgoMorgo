import java.util.Arrays;
import java.util.Scanner;

public class Q1463 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
	
		int[] dp = new int[X+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[1] = 0;
		
		if(X > 1) {
			dp[2] = 1;
		}
		
		if(X > 2) {
			dp[3] = 1;			
		}

		if(X > 3) {
			for(int i = 4; i <= X; i++) {
				dp[i] = Math.min(dp[i], dp[i-1] + 1);
				
				if(i%3 == 0) {
					dp[i] = Math.min(dp[i], dp[i/3] + 1);
				}
				
				if(i%2 == 0) {
					dp[i] = Math.min(dp[i], dp[i/2] + 1);
				}
			}
		}
		
		
		System.out.println(dp[X]);
	}
}
