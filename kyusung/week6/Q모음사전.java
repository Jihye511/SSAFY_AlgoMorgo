import java.util.*;

class Q모음사전{
	public static String[] str_arr = {"A", "E", "I", "O", "U"};
	public static int num = 1, ans;
	public static String str_find;

	public static void dfs(int depth, String now){
		if(depth == 5) return;

		for (int i = 0; i < 5; i++) {
			String next = now + str_arr[i];

			if(next.equals(str_find)){
				ans = num;
				return;
			}

			num++;
			dfs(depth + 1, next);
		}
	}

	public static int solution(String str) {
		ans = 0;
		str_find = str;            
		dfs(0, "");

		return ans;
	}
}