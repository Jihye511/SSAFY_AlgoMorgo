import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        HashMap<Integer, Integer> hash = new HashMap<>();
		Stack<Integer> result = new Stack<>();
		int day;
		for (int i = 0; i < progresses.length; i++) {
			day = (int) Math.ceil((double) (100 - progresses[i]) / (double) speeds[i]);
			if(result.isEmpty()) {
				result.add(day);
				hash.put(day, 1);
			}
			else if(result.peek() >= day) {
				hash.put(result.peek(), hash.get(result.peek()) + 1);
			}
			else {
				result.add(day);
				hash.put(day, 1);
			}
		}

		int[] answer = new int[result.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = hash.get(result.get(i));
		}
        return answer;
    }
}
