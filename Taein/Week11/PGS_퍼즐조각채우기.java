import java.util.*;
class Solution {
    static class Node implements Comparable<Node>{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Node o){
            if(o.x == this.x) return this.y - o.y;
            return this.x - o.x;
        }
        
    }
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<List<Node>> board_list;
    static List<List<Node>> table_list;
    static int answer = 0;
    static int len;
    public int solution(int[][] game_board, int[][] table) {
        board_list = new ArrayList<>();
        table_list = new ArrayList<>();
        len = game_board.length;
        
        for(int i =0; i < len; i++) {
        	for(int j = 0; j < len; j++) {
        		if(table[i][j] == 1) table[i][j] = 0;
        		else table[i][j] = 1;
        	}
        }
        
        boolean[][] board_visited = new boolean[len][len];
        boolean[][] table_visited = new boolean[len][len];

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(!board_visited[i][j] && game_board[i][j] == 0){
                    bfs(i, j, board_visited, game_board, board_list);
                }
                if(!table_visited[i][j] && table[i][j] == 0){
                    bfs(i, j, table_visited, table, table_list);
                }
            }
        }

        answer = compare(board_list, table_list);

        return answer;
    }

    static public int compare(List<List<Node>> board, List<List<Node>> table){
        int board_size = board.size();
        int table_size = table.size();
        int result = 0;

        boolean[] visited = new boolean[board.size()];

        for(int i = 0; i < table_size; i++){
            for(int j = 0; j < board_size; j++){
                if(visited[j] || board.get(j).size() != table.get(i).size()) continue;
                if(rotate(board.get(j), table.get(i))){
                    visited[j] = true;
                    result += board.get(j).size();
                    break;
                }
            }
        }
        return result;
    }

    static public boolean rotate(List<Node> board, List<Node> rotate){
        Collections.sort(board);

        List<Node> table = new ArrayList<>();
        for(Node node : rotate){
            table.add(new Node(node.x, node.y));
        }

        int cur_x, cur_y, temp;
        boolean check;
        for(int d = 0; d < 4; d++){
            Collections.sort(table);
            cur_x = table.get(0).x;
            cur_y = table.get(0).y;

            for(Node node : table){
                node.x -= cur_x;
                node.y -= cur_y;
            }

            check = true;
            for(int i = 0; i < board.size(); i++){
                if(board.get(i).x != table.get(i).x || board.get(i).y != table.get(i).y){
                    check = false;
                    break;
                }
            }

            if(check) return true;
            else{
                for(int i = 0; i < table.size(); i++){
                    temp = table.get(i).x;
                    table.get(i).x = table.get(i).y;
                    table.get(i).y = -temp;

                }
            }


        }
        return false;
    }


    static public void bfs(int x, int y, boolean[][] visited, int[][] map, List<List<Node>> main_list){
        Queue<Node> q = new ArrayDeque();
        List<Node> temp_list = new ArrayList<>();

        q.add(new Node(x, y));
        temp_list.add(new Node(0,0));
        visited[x][y] = true;

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
                    temp_list.add(new Node(nx - x, ny - y));
                    visited[nx][ny] = true;
                }
            }
        }
        main_list.add(temp_list);
    }



}
