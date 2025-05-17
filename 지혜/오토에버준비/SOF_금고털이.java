import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static char[] location;
    static boolean[] v;
    static int result =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        location = new char[n];
        v = new boolean[n];
        for(int i =0; i<n; i++){
            location[i] = str.charAt(i);
        }
        int idx =0;
        for(int i =0; i<n; i++){
            if(location[i] =='P'){
                for(int j = idx; j<n; j++){
                    if(location[j] =='H' && Math.abs(i-j)<=k){
                        result++;
                        idx = j+1;
                        break;
                    }
                }
            }
        }
        
        System.out.println(result);
        
    }

}
