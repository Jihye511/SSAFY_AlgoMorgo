import java.util.Arrays;

class Solution {
	static int result;
    static char pm[]= {'-','+'};
	public int solution(int[] numbers, int target) {
		result = 0;
    	find(new char[numbers.length],0,numbers,target);
    	
        return result;
    }
	static void find(char [] ch,int cnt,int[] numbers,int target) {
		if(cnt==ch.length) {
//			System.out.println(Arrays.toString(ch));
			if(cal(ch,numbers)==target) {
				result++;
			}
			return;
		}
		
		
		ch[cnt]=pm[0];
		find(ch,cnt+1,numbers,target);
		ch[cnt]=pm[1];
		find(ch,cnt+1,numbers,target);
		
		
	}
	static int cal(char[] ch,int [] numbers) {
		int result=numbers[0];
		if(ch[0]=='-')
			result*=-1;
			
		for (int i = 1; i < ch.length; i++) {
			if(ch[i]=='+')
				result+=numbers[i];
			else
				result-=numbers[i];
		}
		return result;
	}
}
