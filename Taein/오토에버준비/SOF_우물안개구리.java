import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int result = 0;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        adj = new ArrayList[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }
        adj[N] = new ArrayList<>();
        
        int a, b;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
                
        }
        
        for(int i = 1; i <= N; i++){
            bfs(i);
        }

        System.out.println(result);
        
    }
    public static void bfs(int x){
        for(Integer k : adj[x]) {
        	if(arr[k-1] >= arr[x-1]) return;
        }

        result ++;
    }
}
