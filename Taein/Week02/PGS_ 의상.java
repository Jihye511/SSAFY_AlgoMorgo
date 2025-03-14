import java.util.*;
public class Solution {
	
	static ArrayList<Integer> n_list;
	static int result;
	public static void main(String[] args) {
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		HashMap<String, Integer> hash = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
			if(hash.containsKey(clothes[i][1])) {
				hash.put(clothes[i][1], hash.get(clothes[i][1]) + 1);
			}
			else {
				hash.put(clothes[i][1], 1);
			}
		}
        n_list = new ArrayList<>();
        for (int i : hash.values()) {
			n_list.add(i);
		}
        
        result = 0;
        
        recur(0, 1);
        System.out.println(result - 1);
     
    }
	private static void recur(int idx, int sum) {
		if(idx == n_list.size()) {
			result += sum;
			return;
		}
		
		recur(idx + 1, sum * n_list.get(idx));
		recur(idx + 1, sum);
		
	}
	
	
}
