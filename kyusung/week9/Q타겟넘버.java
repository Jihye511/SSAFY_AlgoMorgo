import java.util.*;

class Q타겟넘버{
    
    static int[] numbers;
	static int target, answer;
    
    public static int solution( int[] nums, int tar) {

        numbers = nums;
		target = tar;
		
		dfs(0, numbers.length, 0);
		
		return answer;
        
    }
    
    private static void dfs(int now, int max, int val) {
		if(now == max) {
			if(val == target) answer ++;
			return;
		}
		
		dfs(now + 1, max, val + numbers[now]);
		dfs(now + 1, max, val - numbers[now]);
		
	}
}