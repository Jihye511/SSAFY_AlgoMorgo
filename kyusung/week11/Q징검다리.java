import java.util.Arrays;

public class Q징검다리 {

	public static void main(String[] args) {
		
		int distance = 25;
		int[] rocks = {2, 14, 11, 21, 17};
		int n = 2;
		
		Arrays.sort(rocks);
		
		int l = 0;
		int r = distance;
		int m = (l + r + 1) / 2;
		
		while(l < r) {
			m = (l + r + 1) / 2;
			
			if(getCnt(rocks, distance, m) <= n) {	// 뭐가 참일까 ?
				l = m;	// 최소 거리를 늘리는 조건
			}else {
				r = m - 1;
			}
		}
		
		System.out.println(l);
		
	}

	private static int getCnt(int[] rocks, int distance, int m) {	// rocks에서 최소거리 m을 가지기 위해 제거해야하는 돌의 개수
		int before = 0;
		int remove = 0;
		int end = distance;
		
		for(int i = 0; i<rocks.length; i++) {
			if(rocks[i] - before < m) {
				remove ++;
				continue;
			}
			before = rocks[i];
		}
		
		if(end - before < m) {
			remove++;
		}
		
		return remove;
	}

}
