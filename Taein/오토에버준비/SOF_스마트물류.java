import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] arr = new char[N];

        String n_list = br.readLine();
        for(int i = 0; i < N; i++){
            arr[i] = n_list.charAt(i);
        }

        int burden = 0;
        int result = 0;
        
        for(int i = 0; i < N; i++){
            if(arr[i] == 'P'){
                for(int j = burden; j < N; j++){
                    if(arr[j] == 'H' && Math.abs(i - j) <= K){
                        result++;
                        burden = j + 1;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
        
    }
}
