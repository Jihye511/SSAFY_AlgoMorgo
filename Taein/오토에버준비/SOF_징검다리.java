import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                if(arr[i] < arr[j]){
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                }
            }
        }

        int result = 0;
        for(int i : dp){
            result = Math.max(result, i);
        }
        System.out.println(result);
    }
}
