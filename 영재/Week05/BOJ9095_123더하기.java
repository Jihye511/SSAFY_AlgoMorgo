package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9095_123더하기 {
	static int memo[];
	public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int T = Integer.parseInt(br.readLine());

	        for (int t = 1; t <= T; t++) {
	        	memo=new int [11];
	    
	        	Arrays.fill(memo, -1);
	        	int n=Integer.parseInt(br.readLine());
	        	memo[1]=1;
	        	memo[2]=2;
	        	memo[3]=4;
	        	System.out.println(f(n));
	        }
	}
	static int f(int n) {
		if(memo[n]==-1) {
			return memo[n]=f(n-1)+f(n-2)+f(n-3);
		}
		return memo[n];
	}

}
