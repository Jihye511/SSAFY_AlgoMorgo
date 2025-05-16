import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
       
        int[] dot = new int[16];
        dot[0] = 2;
        for(int i =1; i<16; i++){
            dot[i] = dot[i-1]+dot[i-1]-1;
        }
        
        System.out.println(dot[n]*dot[n]);
    }
}
