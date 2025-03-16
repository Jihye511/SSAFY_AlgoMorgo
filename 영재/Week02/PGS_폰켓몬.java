import java.util.HashSet;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        HashSet<Integer> hs= new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
			hs.add(nums[i]);
		}
        
        if(nums.length/2>hs.size()) answer=hs.size();
        else
        	answer=nums.length/2;
        	
        return answer;
    }
}
