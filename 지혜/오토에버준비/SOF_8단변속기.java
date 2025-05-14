import java.io.*;
import java.util.*;

public class Main {
    static int[] num = new int[8];
    public static void main(String[] args) throws IOException {
        String result="";
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<8; i++){
            
            sb.append(Integer.parseInt(st.nextToken()));
        }
        String input = sb.toString();
        if(input.equals("12345678")){
            result = "ascending";
        }else if(input.equals("87654321")){
            result = "descending";
        }else{
            result = "mixed";
        }
        System.out.println(result);
    }
}
