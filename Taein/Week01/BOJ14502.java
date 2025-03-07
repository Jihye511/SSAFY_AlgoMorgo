import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, result;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = 0;
        DFS(0);
        System.out.println(result);
    }
    private static void DFS(int cnt){
        if(cnt == 3){
            flood();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    DFS(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }

    }

    private static void flood(){
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 2) q.offer(new Point(i, j));
            }
        }

        int[][] new_map = map_clone();

        int nx, ny;
        Point p;

        while (!q.isEmpty()){
            p = q.poll();
            for (int i = 0; i < 4; i++) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || new_map[nx][ny] == 1) continue;

                if(new_map[nx][ny] == 0){
                    new_map[nx][ny] = 2;
                    q.add(new Point(nx, ny));
                }

            }
        }

        int cnt = count(new_map);
        result = Math.max(cnt, result);

    }

    private static int count(int[][] new_map){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(new_map[i][j] == 0) cnt += 1;
            }
        }
        return cnt;
    }

    private static int[][] map_clone(){
        int[][] new_map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                new_map[i][j] = map[i][j];
            }
        }
        return new_map;
    }

    private static void print(int[][] new_map){
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(new_map[i]));
        }
        System.out.println();
    }
}
