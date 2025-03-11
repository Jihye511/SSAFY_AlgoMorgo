import java.io.*;
import java.util.*;
public class BOJ_14502_연구소 {
    static int N,M;
    static int[][] map;
    static int maxArea=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(maxArea);
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void bfsVirus(int[][] tempMap){
        Queue<int[]> q= new LinkedList<>();
        for(int i =0; i<N; i++){
            for(int j =0; j<M; j++){
                if(tempMap[i][j] ==2){
                    q.offer(new int[]{i,j});
                }
            }
        }
        while (!q.isEmpty()){
            int[] cur = q.poll();
            for(int i =0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
                if(tempMap[nx][ny] != 0) continue;

                tempMap[nx][ny] =2;
                q.offer(new int[]{nx,ny});
            }
        }
    }
    public  static int countArea(int[][] tempMap){
        int cnt=0;
        for(int i =0; i<N; i++){
            for(int j =0; j<M; j++){
                if(tempMap[i][j] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static void dfs(int count){
        if(count ==3){
            int[][] tempMap = copyMap(map);
            bfsVirus(tempMap);
            maxArea = Math.max(countArea(tempMap),maxArea);
            return;
        }
        for(int i =0; i<N; i++){
            for(int j =0; j<M; j++){
                if(map[i][j] ==0) {
                    map[i][j] = 1;
                    dfs(count+1);
                    map[i][j] =0;
                }

            }
        }
    }
    public static int[][] copyMap(int[][] original){
        int[][] copy = new int[N][M];
        for(int i =0; i<N; i++){
            for(int j=0; j<M; j++){
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
}
