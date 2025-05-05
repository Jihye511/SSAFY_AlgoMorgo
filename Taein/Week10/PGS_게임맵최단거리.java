import java.util.*;
class Solution {
    static class Node{
        int x, y, dist;
        Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m;
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        board = new int[n][m];
        board[0][0] = 1;
        bfs(0, 0, maps);
        if(board[n-1][m-1] > 0) return board[n-1][m-1];
        return -1;
    }
    
    public static void bfs(int x, int y, int[][] maps){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y, 1));
        Node temp;
        int nx, ny;
        boolean[][] visited = new boolean[n][m];
        while(!q.isEmpty()){
            temp = q.poll();
            for(int d = 0; d < 4; d++){
                nx = temp.x + dx[d];
                ny = temp.y + dy[d];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx][ny] == 0) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    board[nx][ny] = temp.dist + 1;
                    q.add(new Node(nx, ny, temp.dist + 1));
                }
                
            }
                
        }
    }
}
