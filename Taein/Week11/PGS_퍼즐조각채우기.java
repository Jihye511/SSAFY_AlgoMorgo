// 못풀었지만 일단 올리겠습니다
import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;

        }

        public int compareTo(Node o){
            int res = Integer.compare(this.x, o.x);
            if(res==0){
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }

    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int len;
    static List<List<Node>> main_board;
    static List<List<Node>> block_board;

    public static void main(String[] args) throws Exception {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
        len = game_board.length;
        main_board = new ArrayList<>();
        block_board = new ArrayList<>();

        boolean[][] main_visited = new boolean[len][len];
        boolean[][] block_visited = new boolean[len][len];

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(!main_visited[i][j] && game_board[i][j] == 0){
                    bfs(i, j, game_board, main_board);
                }
                if(!block_visited[i][j] && game_board[i][j] == 0){
                    bfs(i, j, table, block_board);
                }
            }
        }

        int result = check_block(main_board, block_board);
        System.out.println(result);
    }

    static int check_block(List<List<Node>> main, List<List<Node>> block){
        int result = 0;
        int main_size = main_board.size();
        int block_size = block_board.size();

        boolean[] visited = new boolean[block_size];

        for(int i = 0; i < block_size; i ++){
            for(int j = 0; j < main_size; j++){
                if(visited[j] || main.get(i).size()!=block.get(j).size()) continue;
                if(rotate(block.get(i), main.get(j))){
                    visited[j] = true;
                    result += main.get(j).size();
                    break;
                }
            }
        }

        return result;

    }

    static boolean rotate(List<Node> block, List<Node> main){
        Collections.sort(main);

        for(int i=0; i<4; i++){

            Collections.sort(block);

            int curr_x = block.get(0).x;
            int curr_y = block.get(0).y;

            for(int j=0; j<block.size(); j++){
                block.get(j).x -= curr_x;
                block.get(j).y -= curr_y;
            }

            boolean check = true;
            for(int j=0; j< main.size(); j++){
                if(main.get(j).x != block.get(j).x || main.get(j).y != block.get(j).y){
                    check = false;
                    break;
                }
            }

            if(check){
                return true;
            }
            else{
                for(int j=0; j<block.size(); j++){
                    int temp = block.get(j).x;
                    block.get(j).x = block.get(j).y;
                    block.get(j).y = -temp;
                }
            }
        }

        return false;


    }

    static void bfs(int x, int y, int[][] map, List<List<Node>> list){
        Queue<Node> q = new ArrayDeque();
        List<Node> temp_list = new ArrayList<>();
        boolean[][] visited = new boolean[len][len];

        visited[x][y] = true;
        q.add(new Node(x,y));
        temp_list.add(new Node(0,0));

        int nx, ny;
        Node temp;
        while(!q.isEmpty()){
            temp = q.poll();
            for(int d = 0; d < 4; d++){
                nx = temp.x + dx[d];
                ny = temp.y + dy[d];
                if(nx < 0 || nx >= len || ny < 0 || ny >= len || visited[nx][ny]) continue;
                if(map[nx][ny] == 0){
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                    temp_list.add(new Node(nx - x, ny - y));
                }


            }
        }

        list.add(temp_list);
    }

}
