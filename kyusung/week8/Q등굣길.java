import java.util.ArrayDeque;
import java.util.Arrays;

public class Q등굣길 {

	public static void main(String[] args) {

		int m = 4;
		int n = 3;
		int[][] puddles = {{2, 2}};

		int[][] map = new int[n][m];
		for(int i = 0; i<puddles.length; i++) {
			map[puddles[i][0] - 1][puddles[i][1] - 1] = -1;
		}
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j < m; j++) {
				
				if(map[i][j] == -1) continue;
				
				if(i == 0 && j == 0) {
					map[i][j] = 1;
				}else if(i == 0) {
					map[i][j] = map[i][j-1];
				}else if(j == 0) {
					map[i][j] = map[i-1][j];
				}else {
					map[i][j] = Math.max(0, map[i][j-1]) + Math.max(0, map[i-1][j]);
				}
				
			}
		}
		
		System.out.println(Arrays.deepToString(map));
		System.out.println(map[n-1][m-1]);
		
	}

}
