import java.util.Arrays;

class Q여행경로 {
	public static String[] path, ans;
	public static String[][] ts;
	public static boolean[] visited;
	public static int N;

	public String[] solution(String[][] tickets) {
		ts = tickets;
		N = tickets.length;
		visited = new boolean[N];

		path = new String[N + 1];
		path[0] = "ICN";

		Arrays.sort(ts, (o1, o2) -> {
			return o1[1].compareTo(o2[1]);
		});

		dfs(0, "ICN");
		return ans;
	}

	private static void dfs(int depth, String now) {
		if(depth == N && ans == null) {
			ans = path.clone();
			return;
		}

		for(int i = 0; i<N; i++) {
			if(visited[i]) continue;
			if(ts[i][0].equals(now)) {
				visited[i] = true;
				path[depth + 1] = ts[i][1];
				
				dfs(depth + 1, ts[i][1]);
				
				visited[i] = false;
				path[depth + 1] = "";
			}
		}
	}
}