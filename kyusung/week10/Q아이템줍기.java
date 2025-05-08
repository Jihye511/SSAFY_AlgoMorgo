import java.util.ArrayDeque;
import java.util.Arrays;

class Q아이템줍기 {
    
    public static class Node{
		int x, y, d;
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static int[] dX = {0, 0, 1, -1};
	public static int[] dY = {1, -1, 0, 0};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
       int[][] map = new int[200][200];
		boolean[][] visited = new boolean[200][200];
		int N = rectangle.length;
		
		// 2배로 늘이기
		for(int i =0; i<N; i++) {
			for(int j = 0; j<4; j++) {
				rectangle[i][j] *= 2;
			}
		}
		characterX *= 2;
		characterY *= 2;
		itemX *= 2;
		itemY *= 2;		
		
		// 사각형 채우기
		for(int[] rec : rectangle) {
			for(int i = rec[0]; i <= rec[2]; i++) {
				for(int j = rec[1]; j <= rec[3]; j++) {
					if(i == rec[0] || i == rec[2] || j == rec[1] || j == rec[3]) {	// 테투리라면
						if(map[i][j] != 2) {										// 다른 사각형의 내부가 아니라면
							map[i][j] = 1;
						}
					}else {
						map[i][j] = 2;
					}		
				}
			}
		}
		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(new Node(characterX, characterY, 0));
		visited[characterX][characterY] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(now.x == itemX && itemY == now.y) {
                return now.d / 2;
			}
			
			for(int dir = 0; dir < 4; dir ++) {
				int nX = now.x + dX[dir];
				int nY = now.y + dY[dir];
				
				if(map[nX][nY] != 1) continue;
				if(visited[nX][nY]) continue;
				
				visited[nX][nY] = true;
				queue.add(new Node(nX, nY, now.d + 1));
			}
			
        }
        
        return 0;
        
    }
}