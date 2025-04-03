package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1463_1로만들기 {
	static int memo[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		memo=new int [1000001];
		Arrays.fill(memo, -1);
		memo[0]=0;
		memo[1]=0;
		memo[2]=1;
		memo[3]=1;	
		
		System.out.println(f(n));

	}
	static int f(int n) {
		
		if(memo[n]==-1&&n>3) {
//			System.out.println(n);
			if(n%2!=0&&n%3!=0) {
				return memo[n]=f(n-1)+1;
			}else if(n%3==0&&n%2==0) {
				return memo[n]=f(n/3)+1<f(n/2)+1?f(n/3)+1:f(n/2)+1;
			}else if(n%3==0) {
				return memo[n]=f(n-1)+1<f(n/3)+1?f(n-1)+1:f(n/3)+1;
			}else if(n%2==0) {
				return memo[n]=f(n-1)+1<f(n/2)+1?f(n-1)+1:f(n/2)+1;
			}
		}
		return memo[n];
	}

}
