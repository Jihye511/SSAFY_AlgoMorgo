import java.util.*;
class 아이템줍기 {
    static int dr[]={-1,0,1,0};
    static int dc[]={0,1,0,-1};
    static int map[][][];
    static int answer;
    public class Man{
        int x;
        int y;
        int len;
        public Man(int x,int y,int len){
            this.x=x;
            this.y=y;
            this.len=len;
        }
        
    }
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;
        map=new int[51][51][10];
        
        for(int k=0;k<rectangle.length;k++){
            int sX=rectangle[k][0];
            int sY=rectangle[k][1];
            int lX=rectangle[k][2];
            int lY=rectangle[k][3];
            //가로 칠하기
            for(int x=sX;x<=lX;x++){
                map[x][sY][0]=1;
                map[x][lY][0]=1;
                map[x][sY][k+1]=1;
                map[x][lY][k+1]=1;
            }
            //세로 칠하기
            for(int y=sY;y<=lY;y++){
                map[sX][y][0]=1;
                map[lX][y][0]=1;
                map[sX][y][k+1]=1;
                map[lX][y][k+1]=1;
            }
        }
        // print();
        bfs(rectangle, characterX, characterY, itemX,itemY);
        
        //먼저 지도 그리고 이동할때마다 bfs로 네모안인지 확인하면서 탐색하기
        
        return answer;
    }
    // 1,2,3,4 : 각각 사각형의 테두리
    // 9 : 이미 방문함
    public void bfs(int[][] rectangle, int cX, int cY, int itemX, int itemY){
        Queue<Man> q=new LinkedList<>();
        q.add(new Man(cX,cY,0));
        map[cX][cY][0]=9;
        while(!q.isEmpty()){
            Man now=q.poll();
            if(now.x==itemX&&now.y==itemY){
                answer=now.len;
                break;
            } 
            for(int i=0;i<4;i++){
                int nx=now.x+dr[i];
                int ny=now.y+dc[i];
                
                if(nx<=0||ny<=0||nx>50||ny>50) continue;
                if(map[nx][ny][0]!=1)continue;
                
                boolean chk=false;
                //같은 테두리인가? 
                boolean chk2=false;
                //만약 같은 테두리이여도 잘못된 이동을 하는가?
                boolean chk3=false;
                
                for(int k=0;k<rectangle.length;k++){
                    int sX=rectangle[k][0];
                    int sY=rectangle[k][1];
                    int lX=rectangle[k][2];
                    int lY=rectangle[k][3];

                    //사각형안에 있다면 이게 잘못됐다
                    if(nx>sX&&nx<lX&&ny>sY&&ny<lY){
                        chk=true;
                        break;
                    }
                    //이동하는곳이 같은 테두리인지 확인
                    if(map[now.x][now.y][k+1]==1&&map[nx][ny][k+1]==1){
                        chk2=true;
                        if((sX==now.x||lX==now.x)&&(sY!=now.y&&lY!=now.y)&&(now.x!=nx)){
                            chk3=true;
                                continue;
                        } 
                        if((sX!=now.x&&lX!=now.x)&&(sY==now.y||lY==now.y)&&(now.y!=ny)){
                            chk3=true;
                            continue;
                        }
                    }
                }
                if(chk) continue;
                if(!chk2) continue;
                if(chk3) continue;

                map[nx][ny][0]=9;
                q.add(new Man(nx,ny,now.len+1));

            }
        }
        
    }

    
}
