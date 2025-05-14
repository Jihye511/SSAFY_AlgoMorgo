import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

class Q퍼즐조각채우기 {
    
    public static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int[] dX = {1, -1, 0, 0};
	public static int[] dY = {0, 0, 1, -1};
    
    public int solution(int[][] game_board, int[][] table) {

        int N = table.length;
		int M = table[0].length;
		// 1. gameboard 에서 빈칸 모음 배열 형태로 보관 -> 최종 모양 2차원을 갖는 list
		boolean[][] game_board_visited = new boolean[N][M];
		ArrayList<int[][]> emptys = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(game_board_visited[i][j]) continue;
				
				if(game_board[i][j] == 0) {
					ArrayList<Node> list = new ArrayList<>();
					ArrayDeque<Node> queue = new ArrayDeque<>();
					
					int minX = i; int maxX = i;
					int minY = j; int maxY = j;
					
					queue.add(new Node(i, j));
					list.add(new Node(i, j));
					game_board_visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nX = now.x + dX[dir];
							int nY = now.y + dY[dir];
							
							if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
							if(game_board_visited[nX][nY]) continue;
							if(game_board[nX][nY] == 1) continue; 
								
							game_board_visited[nX][nY] = true;
							
							if(nX > maxX) maxX = nX;
							if(nX < minX) minX = nX;
							if(nY > maxY) maxY = nY;
							if(nY < minY) minY = nY;
							
							list.add(new Node(nX, nY));
							queue.add(new Node(nX, nY));
						}
					}
					
					// 이어진 모든 노드 완성 -> 2차원 배열 생성
					int[][] empty = new int[maxX - minX + 1][maxY - minY + 1];
					for(Node now : list) {
						empty[now.x - minX][now.y - minY] = 1;
					}
					
					emptys.add(empty);
				}
			}
		}
		
		// 2. table에서 조각 찾을 때마다 비교
		boolean[][] table_visited = new boolean[N][M];
		ArrayList<int[][]> pieces = new ArrayList<>();
	
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(table_visited[i][j]) continue;
				
				if(table[i][j] == 1) {
					ArrayList<Node> list = new ArrayList<>();
					ArrayDeque<Node> queue = new ArrayDeque<>();
					
					int minX = i; int maxX = i;
					int minY = j; int maxY = j;
					
					queue.add(new Node(i, j));
					list.add(new Node(i, j));
					table_visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Node now = queue.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nX = now.x + dX[dir];
							int nY = now.y + dY[dir];
							
							if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
							if(table_visited[nX][nY]) continue;
							if(table[nX][nY] == 0) continue; 
								
							table_visited[nX][nY] = true;
							
							if(nX > maxX) maxX = nX;
							if(nX < minX) minX = nX;
							if(nY > maxY) maxY = nY;
							if(nY < minY) minY = nY;
							
							list.add(new Node(nX, nY));
							queue.add(new Node(nX, nY));
						}
					}
					
					// 이어진 모든 노드 완성 -> 2차원 배열 생성
					int[][] piece = new int[maxX - minX + 1][maxY - minY + 1];
					for(Node now : list) {
						piece[now.x - minX][now.y - minY] = 1;
					}
					pieces.add(piece);	
				}
			}
		}

		// 3. 돌려서 맞는거 찾으면 끝
		boolean[] matched_e = new boolean[emptys.size()];
		boolean[] matched_p = new boolean[pieces.size()];
		int ans = 0;
		
		for(int i = 0; i < emptys.size(); i++) {
			for(int j = 0; j < pieces.size(); j++) {
				if(matched_e[i]) continue;
				if(matched_p[j]) continue;
				
				int[][] e = emptys.get(i);
				int[][] p = pieces.get(j);
				
				if(check(e, p)) {
					// 맞으면 e에 방문처리, 크기 확인
	
					matched_e[i] = true;
					matched_p[j] = true;

					for(int r = 0; r < e.length; r++) {
						for(int c =0; c < e[0].length; c++) {
							if(e[r][c] == 1) ans++;
						}
					}
//					for(int r = 0; r < p.length; r++) {
//						System.out.println(Arrays.toString(p[r]));
//					}
//					System.out.println(ans);
					
				}
			}
		}
		
		return ans;
        
    }
    private static boolean check(int[][] e, int[][] p) {
		int eX = e.length;
		int eY = e[0].length;
		int pX = p.length;
		int pY = p[0].length;
		
		// 배열 크기 우선 비교, 조건 안맞으면 false return
		if(eX == pX && eY == pY) {			// 1. 동일 모양으로 들어온 경우
			// 본 모양 일치 여부 확인
			if(degree0(e, p)) return true;
			// 180 회전 일치 모양 확인
			else if(degree180(e, p)) return true;
		}
		if(eX == pY && eY == pX) {			// 2. 다른 모양
			// 90도 회전 모양 일치 확인
			if(degree90(e, p)) return true;
			// 270도 회전 모양 일치 확인
			else if(degree270(e, p)) return true;
		}

		
		return false;
	}

	private static boolean degree270(int[][] e, int[][] p) {
		int[][] q = new int[p[0].length][p.length];
		
		for(int i = 0; i < p[0].length; i++) {
			for(int j = 0; j < p.length; j++) {
				q[i][j] = p[j][p[0].length - i - 1];
			}
		}
		
		return degree0(e, q);
	}

private static boolean degree180(int[][] e, int[][] p) {
    int rows = p.length, cols = p[0].length;
    int[][] q = new int[rows][cols];
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            // 행도 뒤집고, 열도 뒤집는다
            q[i][j] = p[rows - 1 - i][cols - 1 - j];
        }
    }
    return degree0(e, q);
}

	
	private static boolean degree90(int[][] e, int[][] p) {
		int[][] q = new int[p[0].length][p.length];
		
		for(int i = 0; i < p[0].length; i++) {
			for(int j = 0; j < p.length; j++) {
				q[i][j] = p[p.length - j - 1][i];
			}
		}
		
		return degree0(e, q);
	}

	private static boolean degree0(int[][] e, int[][] p) {
		for(int i = 0; i<e.length; i ++) {
			for(int j = 0; j<e[0].length; j++) {
				if(e[i][j] != p[i][j]) return false;
			}
		}
		
		return true;
	}
}