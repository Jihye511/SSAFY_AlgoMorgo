import java.util.*;

class Q폰켓몬 {
     public static int solution(int[] nums){
        
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
         
         for(int i =0; i<len; i++){
             int now = nums[i];
             
             if(!set.contains(now)){
                set.add(now);
                 if(set.size() >= len / 2) break;
             }
         }
         
        int answer = set.size();
        return answer;
    }
}