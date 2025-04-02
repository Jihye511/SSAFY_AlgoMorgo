import java.util.*;
class Solution {
   static int check;
	static List<Integer> list;


	public int solution(String numbers) {
		int answer = 0;
		int[] array = new int[numbers.length()];
		for (int i = 0; i < numbers.length(); i++) {
			array[i] = numbers.charAt(i) - '0';
		}

		check = 0;
        list = new ArrayList<>();
		for (int i = 1; i <= numbers.length(); i++) {
			combination(0, -1, array, new int[i], new boolean[array.length]);
		}
		for (int i = 0; i < list.size(); i++) {
			numcheck(list.get(i));
           
		}

		answer = check;

		return answer;
	}

	public void combination(int idx, int num, int[] array, int[] numbers,boolean[] visited) {
		if (idx == numbers.length) {
			StringBuilder sb = new StringBuilder();
			
			for (int number : numbers) {
				sb.append(number);
               
			}
  
				if (!list.contains(Integer.parseInt(sb.toString()))) {
                   
					list.add(Integer.parseInt(sb.toString()));
                     
				}
			return;
		}
		for (int i = 0; i < array.length; i++) {
			if (!visited[i]){
                
               numbers[idx] = array[i];
visited[i]=true;
			combination(idx + 1, i, array, numbers,visited); 
                visited[i]=false;
            }
			

		}
	}

	public void numcheck(int num) {
		if (num < 2)
			return;

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return;
			}
		}

		check++;
	}

}
