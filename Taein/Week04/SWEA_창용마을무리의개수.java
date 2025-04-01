import java.util.*;
import java.io.*;
public class Solution {
     
    static int N, M, result;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int a, b;
        for (int t = 1; t < T + 1; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parents = new int[N + 1];
            result = 0;
            make();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
             
            Set<Integer> result = new HashSet<>();
            for (int i = 1; i < N + 1; i++) {
                result.add(find(i));
            }
             
            System.out.printf("#%d %d\n", t, result.size());
        }
         
    }
    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;
         
        parents[bRoot] = aRoot;
    }
    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    private static void make() {
        for (int i = 0; i < N+1; i++) {
            parents[i] = i;
        }
         
    }
     
}
