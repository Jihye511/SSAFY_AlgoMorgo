import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] num;
    static ArrayList<Integer>[] node;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N+1];
        node =new ArrayList[N+1];
        for(int i =1; i<=N; i++){
            node[i] = new ArrayList<>();
        }
        st= new StringTokenizer(br.readLine());
        for(int i =1; i<=N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i =0; i<M; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            node[a].add(b);
            node[b].add(a);
            
        }
        int result =0;
        for(int i =1; i<=N; i++){
            int cur=num[i];
            int cnt=0;
            for(int j =0; j<node[i].size(); j++){
                int next = node[i].get(j);
                int weight = num[next];
                if(cur <= weight) cnt++;
            }
            if(cnt ==0) result ++;
        }
        System.out.println(result);
    }
    
}
