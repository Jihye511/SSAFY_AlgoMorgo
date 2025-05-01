import java.util.*;

class Q네트워크{
	public static int solution(int N, int[][] computers){
		boolean[] visited = new boolean[N];
		int ans = 0;

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i = 0; i<N; i++) {
			if(!visited[i]) {				
				ans++;
				queue.add(i);
			}
			else continue;

			while(!queue.isEmpty()) {
				int now = queue.poll();
				//System.out.println(now); 

				visited[now] = true;

				for(int j = 0; j<N; j++) {
					if(now == j) continue;
					if(computers[now][j] == 1 && !visited[j]) queue.add(j);
				}


			}

		}

		return ans;
	}
}