import java.util.Arrays;
import java.util.Scanner;

public class SOF징검다리 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[N];
        dp[0] = 1;

        for(int i = 1; i<N; i++){
            
            int max = 0;
            for(int j = 0; j < i; j++){
                if(arr[i] <= arr[j]) continue;
                
                max = Math.max(max, dp[j]);
            }
            dp[i] = Math.max(1, max + 1);
            
        }
        
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
