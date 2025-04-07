import java.util.Arrays;
import java.util.HashSet;

class Q소수찾기 {
    public static char[] chars, arr;
	public static boolean[] visited;
	public static int N, cnt;
	public static HashSet<Integer> set;
    
    public int solution(String numbers) {
        chars = numbers.toCharArray();
        N = chars.length;
		cnt = 0;
		
		visited = new boolean[chars.length];
		arr = new char[N];
		set = new HashSet<>();
		
		make_permu(0);
        
        return cnt;
    }
    
    private static void make_permu(int now) {
		if(now <= N && now > 0) {
			is_abs();
		}
		
		for(int i = 0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[now] = chars[i];
				make_permu(now+1);	
				visited[i] = false;
				arr[now] = ' ';
			}
		}
	}
    
    private static void is_abs() {
		StringBuilder sb = new StringBuilder();
		//System.out.println(Arrays.toString(arr));
		
		for(char ch : arr) {
			if(ch >= '0' && ch <= '9') sb.append(ch);
		}
		
		int now = Integer.parseInt(sb.toString());
		if(now == 0) return;
		
		if(set.contains(now)) return;
		else set.add(now);
		
		if(now == 1) return;
		for(int i = 2; i < now; i++) {
			if(now % i == 0) return;
		}
		
		System.out.println(now);
		cnt ++;
	}
}