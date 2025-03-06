import java.io.*;
import java.util.*;

public class swea_1238_Contact {
    static int size, start;
    static List<List<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i = 0; i < 101; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < size / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(from).add(to);
            }

            ArrayList<Integer> result = bfs(start);
            int maxNode = Collections.max(result);

            System.out.println("#" + t + " " + maxNode);
        }
    }

    public static ArrayList<Integer> bfs(int s) {
        boolean[] visited = new boolean[101];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;

        ArrayList<Integer> lastNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            int length = queue.size();
            lastNodes.clear(); // 새로운 depth 탐색 시 초기화

            for (int i = 0; i < length; i++) {
                int cur = queue.poll();
                lastNodes.add(cur);

                for (int next : list.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return lastNodes;
    }
}
