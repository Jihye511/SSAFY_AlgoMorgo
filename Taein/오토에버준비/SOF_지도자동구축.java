import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[16];
        arr[1] = 3;
        arr[2] = 5;
        for(int i = 3; i <= 15; i++){
            arr[i] = arr[i-1] * 2 - 1;
        }

        System.out.println(arr[n] * arr[n]);
        
    }
}
