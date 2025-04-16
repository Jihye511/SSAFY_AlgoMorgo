import java.util.Scanner;

public class Q2042 {
	
	public static long[] arr, tree;
	
	private static void init(int t, int l, int r) {
		if(l == r) {
			tree[t] = arr[l];
			return;
		}
		
		int m = (l + r) / 2;
		init(t*2, l, m);
		init(t*2+1, m + 1, r);
		
		tree[t] = tree[t * 2] + tree[t*2 + 1];
	}
	
	private static void update(int t, int l, int r, int idx, long val) {
		if( idx < l || idx > r ) return;		// 탐색 범위가 idx의 범위가 아닌 경우
		if(l == r) {							// 위치를 찾은 경우
			tree[t] = val;
			arr[idx] = val;
			return;
		}
		int m = (l + r) / 2;
		
		update(t*2, l, m, idx, val);
		update(t*2 + 1, m+1, r, idx, val);
		
		tree[t] = tree[t*2] + tree[t*2 + 1];
	}

	private static long query(int t, int l, int r, int start, int end) {
		if( l > end || r < start) return 0;		// 탐색 범위가 아닌 경우
		if( start <= l && r <= end) return tree[t];		// 탐색 범위가 해당 범위 일부를 완전히 포함하는 경우
		
		int m = (l + r) / 2;
		long lsum = query(t*2 , l, m, start, end);
		long rsum = query(t*2 + 1 , m+1, r, start, end);
		
		return lsum + rsum;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		arr = new long[N];
		for(int i = 0; i<N; i++) {
			arr[i] = sc.nextLong();
		}
		
		int height = (int) Math.ceil(Math.log(N) / Math.log(2));
		int tree_size = 1 << (height+1);
		tree = new long[tree_size];
		
		init(1, 0, N-1);
		
		for(int i = 0; i < M + K; i++) {
			int cmd = sc.nextInt();
			
			if(cmd == 1) {	// 수정
				int idx = sc.nextInt();
				long val = sc.nextLong();
			
				update(1, 0, N-1, idx-1, val);
				
			}else {			// 구간합
			
				int left = sc.nextInt();
				int right = sc.nextInt();
				
				System.out.println(query(1, 0, N-1, left-1, right-1)); 
				
			}
			
		}
		
	}

}
