import java.util.*;
import java.io.*;

public class Main {
	static int N, H;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		List<Integer> bot = new ArrayList<>();
		List<Integer> top = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				bot.add(Integer.parseInt(br.readLine()));
			}
			else {
				top.add(Integer.parseInt(br.readLine()));
			}
		}
		
		int low, high, mid;
		low = 1;
		high = H;
		int b_cnt, h_cnt;
		int answer = Integer.MAX_VALUE;
		while(low <= high) {
			mid = (low + high) / 2;
			b_cnt = h_cnt = 0;
			
			for (int i : bot) {
				if(i >= mid) {
					b_cnt += 1;
				}
			}
			
			for (int i : top) {
				if(i >= mid) {
					h_cnt += 1;
				}
			}
			
			if(b_cnt <= h_cnt) {
				high = mid - 1;
				
			}
			else {
				low = mid + 1;
			}
			
			if(answer >= b_cnt + h_cnt) {
				answer = b_cnt + h_cnt;
			}

			
		}
		System.out.println(answer + " " + low + " " + high);
	}
}
