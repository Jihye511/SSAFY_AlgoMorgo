import java.util.Arrays;

class Q피로도 {
    
    public static int N, K, answer;
	public static boolean[] visited;
	public static int[][] dung;
    
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        K = k;
		N = dungeons.length;
		
		dung = new int[N][2];
		visited = new boolean[N];
		
		for(int i = 0; i<N; i++) {
			dung[i] = dungeons[i].clone();
		}
		
		permu(0);
        
        return answer;
    }
    
    private static void permu(int phase) {
		answer = Math.max(answer, phase);
		
		for(int i = 0; i<N; i++) {
			if(!visited[i] && K >= dung[i][0]) {
				visited[i] = true;
				K -= dung[i][1];
				
				permu(phase + 1);
				
				K += dung[i][1];
				visited[i] = false;
			}
		}
		
	}
}