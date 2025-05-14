import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, result;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
    
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        result = 0;
        boolean[][] visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = true;
                dfs(i, j, visited, 0, 0);
                visited[i][j] = false;
                    
            }
        }
        System.out.println(result);
    }

    public static void dfs(int x, int y, boolean[][] visited, int cnt, int temp_result){
        if(cnt == 4){
            result = Math.max(result, temp_result);
            return;
        }
        
        int nx, ny, temp;
        boolean check;
        
        for(int d = 0; d < 4; d++){
            nx = x + dx[d];
            ny = y + dy[d];
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
            
            visited[nx][ny] = true;
            temp = temp_result + map[x][y] + map[nx][ny];
            result = Math.max(temp, result);
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j]){
                        visited[i][j] = true;
                        dfs(i, j, visited, cnt + 1, temp);
                        visited[i][j] = false;
                    }
                }
            }
            
            visited[nx][ny] = false;
        }

        
        
    }
}
