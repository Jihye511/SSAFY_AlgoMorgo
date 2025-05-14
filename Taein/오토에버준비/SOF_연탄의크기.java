import java.io.*;
import java.util.*;

public class Main {

    static int n, result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = 1;
        Arrays.sort(arr);
        int cnt;
        for(int i = 2; i < arr[arr.length - 1]; i++){
            cnt = 0;
            for(int j = 0; j < n; j++){
                if(arr[j] % i == 0){
                    cnt ++;
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }
}
