import java.util.*;

class Q구명보트{
	
	public static int solution(int[] p, int limit) {
		Arrays.sort(p);
		int l = 0;
		int r = p.length - 1;
		int cnt = 0;

		while(l <= r) {
			if( l == r) {
				cnt ++;
				break;
			}

			int lweight = p[l];
			int rweight = p[r];

			if(lweight + rweight <= limit) {
				l++;
				r--;

				cnt++;
			}else {
				r--;
				cnt++;
			}
		}

		return cnt;
	}
	
}
