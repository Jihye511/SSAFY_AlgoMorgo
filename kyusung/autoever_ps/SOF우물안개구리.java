import java.io.*;
import java.util.*;

public class SOF우물안개구리 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int weight[] = new int[N];
        boolean[] aware = new boolean[N];
        for(int i = 0; i<N; i++){
            weight[i] = sc.nextInt();
        }

        for(int i = 0; i<M; i++){
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            if(weight[a] == weight[b]){
                aware[a] = aware[b] = true;
            }else if(weight[a] > weight[b]){
                aware[b] = true;
            }else{
                aware[a] = true;
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            if(!aware[i]) ans++;
        }

        System.out.println(ans);
    }
}
