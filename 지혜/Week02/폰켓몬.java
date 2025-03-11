import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> map = new HashSet<>();
        
        for(int i =0; i<nums.length; i++){
            map.add(nums[i]);
        }
        int n = map.size();
        if(nums.length /2 <= n){
            answer = nums.length/2;
        }else {
            answer = n;
        }
        
        return answer;
    }
}
