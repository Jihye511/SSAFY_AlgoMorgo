import java.util.*;
import java.awt.*;
class Solution {
    
    class Point {
        int r;
        int c;
        int cnt;
        
        Point(int r, int c, int cnt) {
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
    
    static int dr[]={-1,0,1,0};
    static int dc[]={0,1,0,-1};
    static int answer;
    // static boolean chk[][];
    public int solution(int[][] maps) {
        answer = -1;
        bfs(maps,0);
        
        
        return answer;
    }
    
    public void bfs(int[][] maps,int len){
        
        Queue<Point> q=new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        q.add(new Point(0,0,1));
        visited[0][0] = true;
        while(!q.isEmpty()){
            Point now=q.poll();
            if(now.r==maps.length-1&&now.c==maps[0].length-1) {
                answer = now.cnt;
                break;   
            }
            int sr=now.r;
            int sc=now.c;
            for(int i=0;i<4;i++){
                if(sr+dr[i]<0||sc+dc[i]<0||sr+dr[i]>=maps.length||sc+dc[i]>=maps[0].length) continue;
                int nr=sr+dr[i];
                int nc=sc+dc[i];
                if(maps[nr][nc]==0 || visited[nr][nc]) continue;
                q.add(new Point(nr,nc,now.cnt+1));
                visited[nr][nc] = true;
            }
            
        }
        
    }
    
}
