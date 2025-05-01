class Q도둑질 {
    public int solution(int[] money) {
		int N = money.length;
		
		// 0번 집을 턴다 안턴다.
		// 이후에 DP 시작
		
		int [] dp1 = new int[N];		// 0번 집 턴다.	-> N-2에서 max
		int [] dp2 = new int[N];
		
		dp1[0] = money[0];
		dp1[1] = dp1[0];
		dp1[2] = dp1[0] + money[2];
		
		for(int i = 3; i<N; i++) {
			dp1[i] = Math.max(dp1[i-2] + money[i], dp1[i-1]);
		}
		
		dp2[0] = 0;
		dp2[1] = money[1];
		dp2[2] = Math.max(dp2[1], money[2]);
		
		for(int i = 3; i<N; i++) {
			dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
		}

		
		int max = Math.max(dp1[N-2], dp2[N-1]);
		return max;
    }
}