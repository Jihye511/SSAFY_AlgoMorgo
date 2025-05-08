class Q단어변환 {

	public static int ans, strArrLen, wordLen;
	public static String tar;
	public static boolean changed;
	public static String[] words_arr;

	public int solution(String begin, String target, String[] words) {

		ans = Integer.MAX_VALUE;
		boolean[] visited = new boolean[words.length];

		tar = target;
		words_arr = words;
		strArrLen = words.length;
		wordLen = begin.length();

		dfs(0, begin, visited.clone());

		if(ans == Integer.MAX_VALUE) return 0;
		else return ans;
	}

	private static void dfs(int depth, String now, boolean[] visited) {
		
		if(now.equals(tar) && depth < ans) {
			changed = true;
			ans = depth;
			return;
		}

		int diff;
		for(int i = 0; i < strArrLen; i++) {		// 모든 단어에 대해
			if(visited[i]) continue;
			diff = 0;

			for(int j = 0; j < wordLen; j++) {	// 다른 글자 수 확인
				if(now.charAt(j) != words_arr[i].charAt(j)) diff++;
			}

			if(diff == 1) {						// 한 자리만 다르다면
				boolean[] visited_copy = visited.clone();
				visited_copy[i] = true;			// 사용한 단어 체크

				dfs(depth + 1, words_arr[i], visited_copy);		// 변경 처리
			}
		}

	}
}