import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[N+1];
        memo[1] = 0;
        if(N>1 )memo[2] = 1;
        if(N>2)memo[3] = 1;
        for(int i =4; i<=N; i++){
            if(i%3 ==0 && i%2 ==0){
                memo[i] = Math.min(memo[i/3],Math.min(memo[i/2],memo[i-1]));
            }else{
                if(i % 3 ==0){
                    memo[i] = Math.min(memo[i-1], memo[i/3]);
                }
                if(i % 2 ==0){
                    memo[i] = Math.min(memo[i-1], memo[i/2]);
                }
                if(i %2 !=0 && i%3 !=0){
                    memo[i] = memo[i-1];
                }
            }

            memo[i] +=1;
        }
        System.out.println(memo[N]);
    }

}
