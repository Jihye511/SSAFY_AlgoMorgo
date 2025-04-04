import java.util.*;
class Solution {
    static HashSet<Integer> set;
    static int[] nums;
    public int solution(String numbers) {
        set = new HashSet<>();
        nums = new int[numbers.length()];
        for(int i = 0; i < nums.length; i++){
            nums[i] = numbers.charAt(i) - '0';
        }
        
        recur("", new boolean[numbers.length()], 0);
        int answer = set.size();
        return answer;

    }
    
    public static void recur(String num, boolean[] visited, int idx){
        if(idx == nums.length){
            int temp = intString(num);
            if(temp != -1) set.add(temp);
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                recur(num + nums[i],visited, idx + 1);
                visited[i] = false;
                recur(num, visited, idx + 1);
            }
        }
        
    }
    public static int intString(String num){
    	if(num.isEmpty()) return -1;
        int temp = Integer.parseInt(num);
        if(temp < 2) return -1;
        for(int i = 2; i < (int) Math.sqrt(temp)+1; i++){
            if(temp % i == 0) return -1;
        }
        return temp;
    }

}
