import java.util.Scanner;
import java.io.FileInputStream;

class SweaQ3289{
	public static int[] arr;

	public static void main(String args[]) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		StringBuilder sb_ans = new StringBuilder();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();

			arr = new int[N+1];
			for(int i = 0; i<=N; i++) {
				arr[i] = i;
			}
			StringBuilder sb = new StringBuilder();

			for(int i =0; i<M; i++) {
				int oper = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();

				if(oper == 0) {
					union(a, b);
				}else if(oper == 1) {
					int x = find(a);
					int y = find(b);

					if(x == y) sb.append(1);
					else sb.append(0);
				}
			}

			sb_ans.append("#"+ test_case + " " + sb.toString()).append('\n');
		}
		
		System.out.print(sb_ans.toString());
	}

	private static int find(int a) {
		if(a != arr[a]) {			// root 노드가 아닌 경우
			arr[a] = find(arr[a]);	//  root 노드 찾아낼때까지 올라가서 해당 값을 반환
		}
		
		return arr[a];				// root면 상관 없고 아니면, 한단계 윗 놈을 보냄
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) {
			arr[rootA] = arr[rootB];
		}
	}

}