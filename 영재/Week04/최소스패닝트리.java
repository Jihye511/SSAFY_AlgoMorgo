import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최소스패닝트리 {
	static int parents[];
	static class Node implements Comparable<Node> {

		int s,e,w;
		public Node(int s,int e,int w) {
			this.s=s;
			this.e=e;
			this.w=w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			st=new StringTokenizer(br.readLine());
			int V=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			parents=new int [V+1];
			
			for (int i = 0; i < parents.length; i++) {
				parents[i]=i;
			}
			
			Node nodes[]=new Node[E];
			
			
			int s,e,w;
			for (int i = 0; i < nodes.length; i++) {
				st=new StringTokenizer(br.readLine());
				s=Integer.parseInt(st.nextToken());
				e=Integer.parseInt(st.nextToken());
				w=Integer.parseInt(st.nextToken());
				
				nodes[i]=new Node(s,e,w);
			}
			Arrays.sort(nodes);
			int cnt=0;
			long sum=0;
			for (int i = 0; i < nodes.length; i++) {
				Node now=nodes[i];
				if(find(now.s)==find(now.e)) continue;
				union(now.s,now.e);
				sum+=now.w;
				cnt++;
				if(cnt==V-1) break;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(sum);
			System.out.println(sb);
			
			
		}

	}
	static int find(int k) {
		if(parents[k]==k) return k;
		else return parents[k]=find(parents[k]);
	}
	
	static void union(int s,int e) {
		if(find(s)==find(e)) return;
		
		parents[find(e)]=find(s);
	}

}
