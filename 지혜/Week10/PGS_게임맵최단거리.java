import java.io.*;
import java.util.*;
class Solution {
    static int[][] map;
    static boolean[][]v;
    public int solution(int[][] maps) {
        int answer = 0;
        map = maps;
        v = new boolean[maps.length][maps[0].length];
        bfs(0,0);
        if(!v[maps.length-1][maps[0].length-1]){
            answer =-1;
        }
        else {
            answer = map[maps.length-1][maps[0].length-1];
             }
        return answer;
    }
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};
    public static void bfs( int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y,1});
        v[x][x] = true;
        
        while(!q.isEmpty()){
            int[] cur= q.poll();
            for(int i =0; i<4; i++){
                int nx = cur[0]+dx[i];
                int ny = cur[1]+dy[i];
                
                if(nx<0 || nx>=map.length|| ny<0 || ny>=map[0].length) continue;
                if(v[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;
                
                v[nx][ny] = true;
                map[nx][ny]+=cur[2];
                q.offer(new int[]{nx,ny,map[nx][ny]});
            }
        }
    }
}
