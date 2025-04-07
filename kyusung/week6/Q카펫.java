import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Q카펫 {
    public int[] solution(int brown, int yellow) {
        int[] ans = new int[2];
        
        int to_divide = (brown-4) / 2;
		int x = 1;
		int y = to_divide-1;
		while(x * y != yellow) {
			x++;
			y--;
		}
		
		ans[0] = x + 2;
		ans[1] = y + 2;
		
		ans = Arrays.stream(ans).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        
        
        return ans;
    }
}