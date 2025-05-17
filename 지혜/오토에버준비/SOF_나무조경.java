import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int result= 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map= new int[n][n];
        for(int i =0;i< n; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                
            }
        }
        boolean[][] v = new boolean[n][n];
        
        dfs(v, 0,0);
        System.out.println(result);
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void dfs(boolean[][]v, int depth, int sum){
        result = Math.max(result , sum); // 결과 다 저장
        
        if(depth >3) return;

        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(!v[i][j]){
                    for(int d =0;d<4;d++){
                        int nx = i +dx[d];
                        int ny = j +dy[d];
                        if(nx <0 || nx>=n || ny<0 || ny>= n) continue;

                        if(!v[nx][ny]){
                            v[nx][ny] = true;
                            v[i][j] = true;
                            dfs(v, depth+1, sum + map[nx][ny]+ map[i][j]);
                            v[nx][ny] = false;
                            v[i][j] = false;
                        }
                    }
                }
            }
        }
        
    }
}
