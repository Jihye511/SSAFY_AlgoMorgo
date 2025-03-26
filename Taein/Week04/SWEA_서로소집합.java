import java.util.*;
import java.io.*;
public class Solution {
	static int[] parents;
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());
		int ord, a, b, f1, f2;
		for (int t = 1; t < T+1; t++) {
			sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				ord = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(ord == 0) {
					union(a,b);
				}
				else {
					f1 = find(a);
					f2 = find(b);
					if(f1 == f2) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			
			System.out.println("#" + t + " " + sb);
		}
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot, bRoot;
		aRoot = find(a);
		bRoot = find(b);
		parents[bRoot] = aRoot;
		
	}

	private static void make() {
		parents = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
	}
	
	
}
