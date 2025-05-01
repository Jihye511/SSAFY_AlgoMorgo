import java.util.Arrays;

public class Q사칙연산 {

	public static int N;
	
	public static void main(String[] args) {
		
		String[] arr = {"1", "-", "3", "+","5" , "-", "8"};
		// 숫자로 시작, 숫자로 끝
		N = arr.length/2+1;
		
		int[] nums = new int[N];
		char[] opers = new char[N-1];
		
		for(int i =0; i<arr.length; i++) {
			if(i % 2 == 0) {
				nums[i/2] = Integer.parseInt(arr[i]);
			}else {
				opers[i/2] = arr[i].charAt(0);
			}
		}

		int ans = getMinMaxValue(nums, opers);
		
		System.out.println(ans);
	}

	private static int getMinMaxValue(int[] nums, char[] opers) {
		int max = 0;
		
		int[][] minDp = new int[nums.length][nums.length];
		int[][] maxDp = new int[nums.length][nums.length];
		
		for(int i = 0; i <N; i++) {
			for(int j = 0; j< N; j++) {
				
				if(i == j) {
					minDp[i][j] = nums[i];
					maxDp[i][j] = nums[i];
					continue;
				}
				
				minDp[i][j] = Integer.MAX_VALUE;
				maxDp[i][j] = Integer.MIN_VALUE;
			}
		}
		
		for(int step = 0; step < N; step++) {			// 간격
			
			for(int i = 0; i<N - step; i++) {			// 시작
				int j = i + step;						// 종료
				
				for(int k = i; k < j; k++) {			// 부호 확인
					char oper = opers[k];
					
					if(oper == '+') {					// 다음 부호가 + 면
						
						maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] + maxDp[k+1][j]);
						minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] + minDp[k+1][j]);
						
					}else if(oper == '-') {
						
						maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i][k] - minDp[k+1][j]); 
						minDp[i][j] = Math.min(minDp[i][j], minDp[i][k] - maxDp[k+1][j]);
					}
					
				}
				
			}
			
		}
		
		
		return maxDp[0][N-1];
	}

}
