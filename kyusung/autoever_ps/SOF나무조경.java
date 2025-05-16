import java.io.*;
import java.util.*;

public class SOF나무조경 {

    public static int N, ans;
    public static int[][] map;
    public static int[] dX = {1, -1, 0, 0};
    public static int[] dY = {0, 0, -1, 1};
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ans = 0;
        map = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        boolean[][] selected = new boolean[N][N];
        dfs(0, 0, selected);

        System.out.println(ans);
    }

    public static void dfs(int depth, int next, boolean[][] selected){

        if(next >= N*N ) return;
        if(depth <= 4){
            int cnt = 0;
            
            for(int i = 0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(selected[i][j]){
                        cnt += map[i][j];
                    }
                }
            }
            
            ans = Math.max(ans, cnt);
        }else return;

        for (int idx = next; idx < N * N; idx++) {
            int i = idx / N;
            int j = idx % N;
            
            if (selected[i][j]) continue;
            
            for (int dir = 0; dir < 4; dir++) {
                int nX = i + dX[dir];
                int nY = j + dY[dir];
                if (nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
                if (selected[nX][nY]) continue;
        
                selected[i][j] = true;
                selected[nX][nY] = true;
                
                dfs(depth + 1, idx + 1, selected);
                
                selected[i][j] = false;
                selected[nX][nY] = false;
            }
        }

    }
}
