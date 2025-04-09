//부족
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 알고스탁 {
	static int[][] prices;
	static int N, L;
	static int Ms, Ma;
	static int maxAsset;
	static Map<Integer, Integer>[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
//예치금(MS) 월별 불입금액(Ma)
			// 종목 수(N) 과거 데이터 기간(L)
			st = new StringTokenizer(br.readLine());
			Ms = Integer.parseInt(st.nextToken());
			Ma = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			prices = new int[N][L + 1];
			int[] proceeds = new int[L + 1];

			proceeds[0] = Ms;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <= L; j++) {
					prices[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxAsset = 0;
			dp = new HashMap[L + 1];
			for (int i = 0; i <= L; i++) {
				dp[i] = new HashMap<>();

			}
			dfs(0, Ms);

			int totalInvested = Ms + Ma * L;
			int profit = maxAsset - totalInvested;
			System.out.printf("#%d %d\n", test, profit);
		}
	}

	public static void dfs(int month, int cash) {
		if (dp[month].getOrDefault(cash, -1) >= cash)
			return;
		dp[month].put(cash, cash);

		if (month == L) {
			maxAsset = Math.max(maxAsset, cash);
			return;
		}

		dfs(month + 1, cash + Ma);

		for (int[] comb : buyCombinations(cash, month)) {
			int spend = 0;
			int afterSell = cash;

			for (int i = 0; i < N; i++) {
				int qty = comb[i];
				spend += prices[i][month] * qty;
				afterSell -= prices[i][month] * qty;

			}
			for (int i = 0; i < N; i++) {
				int qty = comb[i];
				afterSell += prices[i][month + 1] * qty;
			}
			dfs(month + 1, afterSell + Ma);
		}
	}

	static List<int[]> buyCombinations(int cash, int month) {
		List<int[]> result = new ArrayList<>();
		int[] current = new int[N];
		backtrack(result, current, 0, cash, month);
		return result;
	}

	static void backtrack(List<int[]> result, int[] current, int index, int remaining, int month) {
		if (index == N) {
			result.add(current.clone());
			return;
		}

		int maxQty = remaining / prices[index][month];
		for (int i = 0; i <= maxQty; i++) {
			current[index] = i;
			backtrack(result, current, index + 1, remaining - i * prices[index][month], month);
		}
	}

}
