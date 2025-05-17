import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] size;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        size = new int[N];
        dp = new int[N];

        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++){
            size[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp,1);
        for(int i =0; i<N; i++){
            for(int j =i+1; j<N;j++){
                if(size[i] <size[j]){
                    dp[j] = Math.max(dp[j], dp[i]+1);
                }
            }
        }
        Arrays.sort(dp);
        System.out.println(dp[N-1]);        
    }
}
