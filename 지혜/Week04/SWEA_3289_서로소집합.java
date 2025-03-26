package SWEA;
import java.io.*;
import java.util.*;
public class SWEA_3289_서로소집합 {
	static void make() {
		parents=new int[n+1];
		for(int i =1; i<=n; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int b, int c) {
		int bRoot = find(b);
		int cRoot = find(c);
		
		if(bRoot == cRoot) return false;
		parents[bRoot] = cRoot;
		return true;
	}
	static int T,n,m;
	static int[] parents;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			make();
			StringBuilder sb = new StringBuilder();
			for(int i =0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c =Integer.parseInt(st.nextToken());
				
				if(a==1) {
					//같은 집합인지 확인
					int bRoot = find(b);
					int cRoot = find(c);
					if(bRoot == cRoot) sb.append(1);
					else sb.append(0);
				}else if(a ==0) {
					//합치기
					union(b, c);
				}
			}
			System.out.println("#"+t+" "+sb.toString());
			
			
		}

	}

}
