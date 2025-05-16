import java.io.*;
import java.util.*;

public class SOF스마트물류 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        String str = sc.next();
        char[] arr = str.toCharArray();

        boolean[] v = new boolean[N];
        int ans = 0;
        
        A : for(int i = 0; i<N; i++){
            if(arr[i] == 'P'){         // 로봇이면
                for(int j = i - k; j <= i + k; j++){
                    if(j < 0 || j >= N) continue;
                    if(arr[j] == 'H' && !v[j]) {
                        ans++;
                        v[j] = true;
                        continue A;
                    } 
                }
            }    
        }

        System.out.println(ans);
        
    }
}
