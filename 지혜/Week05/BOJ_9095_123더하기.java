package bj;
import java.io.*;
import java.util.*;
public class BOJ_9095 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(br.readLine());
		
			int[] memo = new int[11];
			for(int i =1; i<11; i++) {
				if(i ==1) memo[i]=1;
				if(i==2) memo[i] =2;
				if(i==3) memo[i] = 4;
				
				if(i>3) {
					memo[i] = memo[i-3]+memo[i-2]+memo[i-1];
				}
				
			}
				
				
			StringBuilder sb = new StringBuilder();	
			for(int t=1; t<=T; t++) {
				int n = Integer.parseInt(br.readLine());
				sb.append(memo[n]).append("\n");
			
			}
			System.out.println(sb);
		
	}

}
