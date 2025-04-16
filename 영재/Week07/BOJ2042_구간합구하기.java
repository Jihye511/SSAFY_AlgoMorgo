package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2042_구간합구하기 {
	static int N, M, K;
	static long num[],tree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		num = new long [N + 1];

		for (int i = 1; i < N + 1; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		int size=1;
		int h=0;
		while(true) {
			if(Math.pow(2,size)>=N)
				break;
			size++;
		}
		h=size;
		size=(int) Math.pow(2, size+1);
//		System.out.println(size);
		tree=new long[size];
		
		
		init(1,1,N);
		


		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			


			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c =Long.parseLong(st.nextToken());
				change(1,1,N,c,b);
				
			} else if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c =Integer.parseInt(st.nextToken());
				System.out.println(find(1,1,N,b,c)); 
			}

		}

	}
	static void init(int node,int start,int end) {
		if(start==end) {
			tree[node]=num[start];
		}else {
			init(node*2,start,(start+end)/2);
			init(node*2+1,(start+end)/2+1,end);
			tree[node]=tree[node*2]+tree[node*2+1];
		}
	}
	
	
	//start와 end는 트리의 시작과 끝
	//left와 right는 찾아야하는 수의 시작과 끝
	static long find(int node, int start,int end, int left,int right) {
//		System.out.println(start+"  "+end+"  "+left+"   "+right);
		if(start>right||end<left) {
			return 0;
		}
		if(left<=start&&end<=right) {
			return tree[node];
		}
		//왼쪽시작값 찾기
		long lsum=find(node*2,start,(start+end)/2,left,right);
		long rsum=find(node*2+1,(start+end)/2+1,end,left,right);
		return lsum+rsum;
	}
	static void change(int node,int start,int end, long k,int idx) {
		if(end<idx||start>idx) {	
			return;
		}
		if(start==end) {
			tree[node]=k;
			return;
		}
		change(node*2,start,(start+end)/2,k,idx);
		change(node*2+1,(start+end)/2+1,end,k,idx);
		tree[node]=tree[node*2]+tree[node*2+1];
		
	}
}
