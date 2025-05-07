import java.util.*;

class Solution {
    public static int[] dx = {1,-1,0,0};
public static int[] dy = {0,0,1,-1};
public static int result;
    static class Game{
        int x;
        int y;
        int count;
        public Game(int x, int y, int count){
            this.x=x;
            this.y=y;
            this.count=count;
        }
    }
    public int solution(int[][] maps) {
        int answer = 0;
        result=Integer.MAX_VALUE;
        bfs(0,0,1,maps);
        answer = (result == Integer.MAX_VALUE) ? -1 : result;
        return answer;
    }
    public static void bfs(int x, int y, int count, int[][] maps){
        Queue<Game> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        queue.add(new Game(x,y,count));
         visited[x][y] = true;
        
        while(!queue.isEmpty()){
            Game pos = queue.poll();
            for(int i=0;i<4;i++){
                int nx=pos.x+dx[i];
                int ny=pos.y+dy[i];
                if(nx==maps.length-1&&ny==maps[0].length-1){
                    result=Math.min(pos.count+1,result);
                }
               if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length &&
                    maps[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Game(nx, ny, pos.count + 1));
                }
            }
            
        }
    }
}
