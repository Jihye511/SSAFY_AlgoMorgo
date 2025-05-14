import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] len;
    static int[] num;
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        len = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i =0; i< n; i++){
            len[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(len);
        num = new int[len[n-1]+1];

        for(int i =2; i<=len[n-1]; i++){
            for(int j =0; j<n; j++){
                if(len[j] %i==0) {
                    num[i] +=1;
                }
            }
        }
        Arrays.sort(num);
     
        System.out.println(num[len[n-1]]);
    }

}
