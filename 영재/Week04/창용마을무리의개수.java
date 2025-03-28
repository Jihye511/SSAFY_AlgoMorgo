import java.util.*;
import java.io.*;

public class 창용마을무리의개수 {
	static int[] sets,sizes;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringBuilder sb=new StringBuilder();
			
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			sets=new int[N+1];
			sizes=new int[N+1];
			for (int i = 0; i < N+1; i++) {
				sets[i]=i;
				sizes[i]=1;
			}
			
			int a,b;
			for (int i = 0; i < M; i++) {
				st=new StringTokenizer(br.readLine());
				a=Integer.parseInt(st.nextToken());
				b=Integer.parseInt(st.nextToken());
				
				union(a,b);
				
			}
			int cnt=-1;
			for (int i = 0; i < N+1; i++) {
				if(sizes[i]>0) cnt++;
//				System.out.println(i+" 번째"+sizes[i]+ " 의 크기");
			}

			sb.append("#").append(test_case).append(" ").append(cnt);
			System.out.println(sb);
		}
	}
	
	//헤드 찾기
	static int find(int k) {
		if(sets[k]==k) return k;
		return find(sets[k]);
		
	}
	
	static void union(int a,int b) {
		//헤드 찾기
		int k1=find(a);
		int k2=find(b);
//		System.out.println(k1+" "+k2);
		if(k1==k2) return;
		
		if(sizes[k1]>=sizes[k2]) {
			sets[k2]=k1;
			sizes[k1]++;
			sizes[k2]=0;
		}else {
			sets[k1]=k2;
			sizes[k2]++;
			sizes[k1]=0;
		}
	}
}
