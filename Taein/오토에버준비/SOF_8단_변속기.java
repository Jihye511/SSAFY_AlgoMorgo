import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[8];
        for(int i = 0; i < 8; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean check = arr[1] > arr[0] ? true : false;
        boolean mix = false;
        for(int i = 2; i < 8; i++){
            if(check && arr[i] > arr[i - 1]) continue;
            else if(!check && arr[i] < arr[i-1]) continue;
            else {
                mix = true;
                break;
                }
        }
        if(mix) System.out.println("mixed");
        else if(check) System.out.println("ascending");
        else System.out.println("descending");
        
    }
}
