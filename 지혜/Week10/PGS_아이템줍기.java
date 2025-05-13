import java.io.*;
import java.util.*;


class Solution {
    static class Rect{
        int x1, x2, y1,y2;
        public Rect(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
    static List<Rect> list;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int x,y;
    static int answer =Integer.MAX_VALUE;
    static int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        x =characterX;
        y = characterY;
        list = new ArrayList<>();
        map = new int[101][101];
        for(int i =0; i<rectangle.length; i++){
            int newX =rectangle[i][2]*2;
            int newY = rectangle[i][3]*2;
            list.add(new Rect(rectangle[i][0]*2, rectangle[i][1] *2,newX ,newY));
            
            //map테두리는 1, 안은 2, 사각형 없음 0
            for(int j = rectangle[i][0]*2; j<=newX; j++){ //x 좌표
                for(int k = rectangle[i][1]*2; k<=newY; k++){ //y 좌표
                    //테두리만 1
                    if(j == rectangle[i][0]*2 || j == newX || k == rectangle[i][1]*2 || k==newY){
                        if(map[j][k] ==1 || map[j][k] ==0) {
                            map[j][k] =1;
                        }else {
                            map[j][k] =2;
                        }                        
                    }
                    else {
                        map[j][k] = 2;
                    }
                }
            }    
        }
        
        answer = bfs(characterX*2,characterY*2,itemX *2,itemY*2);  
        
        return answer/2 ;
    }
    public static int bfs(int characterX, int characterY, int itemX, int itemY){
        int cnt =Integer.MAX_VALUE;
    
        Queue<int[]> q= new LinkedList<>();
        q.offer(new int[] {characterX,characterY ,0});
        map[characterX][characterY] = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == itemX && cur[1] == itemY){
                cnt = Math.min(cnt,cur[2]);
            }
            
            for(int i =0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || nx>= map.length ||ny<0 || ny>=map[0].length) continue;

                if(map[nx][ny] == 1){
                    map[nx][ny] =0;
                    q.offer(new int[]{nx,ny, cur[2] + 1});
                    
                }       
            }
        }

        return cnt;
    }
}
