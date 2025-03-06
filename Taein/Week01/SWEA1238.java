import java.util.*;
import java.io.*;

public class Solution {

    static int N, max_node;
    static boolean[][] graph;
    static int[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());


            graph = new boolean[101][101];
            visited = new int[101];
            int from, to;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                graph[from][to] = true;
            }

            visited[start] = 1;
            BFS(start);
            int result = -1;

            for (int i = 100; i > 0 ; i--) {
                if(visited[i] == max_node) {
                    result = i;
                    break;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }


    private static void BFS(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        int node;
        max_node = 0;
        while (!q.isEmpty()){
            node = q.poll();
            for (int i = 0; i < 101; i++) {
                if(graph[node][i] && visited[i] == 0){
                    q.add(i);
                    visited[i] = visited[node] + 1;
                }
            }
            max_node = Math.max(max_node, visited[node]);
        }

    }

}
