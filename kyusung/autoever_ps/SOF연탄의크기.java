import java.io.*;
import java.util.*;

public class SOF연탄의크기 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] homes = new int[N];
        for(int i = 0; i<N; i++){
            homes[i] = sc.nextInt();
        }

        Arrays.sort(homes);
        int max = homes[N-1];
        int ans = 1;
        
        for(int k = 2; k <= max; k++){
            int cnt = 0;
            for(int i = 0; i<N; i++){
                if(homes[i] % k == 0){
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }
}
