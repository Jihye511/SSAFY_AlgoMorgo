import java.util.*;

class Q조이스틱{
	
    public static int solution(String str) {
		int change_alpha = 0;

		for(int i = 0 ; i<str.length(); i++){
			char ch = str.charAt(i);
			change_alpha += Math.min((int) (ch - 'A'), 1 + (int) ('Z' - ch));
		}

		int len = str.length();
		int change_idx = len - 1;
		
		for(int i = 0; i<len; i++) {
			int next = i + 1;
			while(next < len && str.charAt(next) == 'A') {
				next++;
			}
			
			int move = (i * 2) + len - next;
			int move2 = i + 2 * (len - next);   // 왼→오→끝
			change_idx = Math.min(change_idx, Math.min(move, move2));
		}
		
		int ans = change_idx + change_alpha;
		System.out.println( change_idx + " : " + change_alpha);

        return ans;
    }
}