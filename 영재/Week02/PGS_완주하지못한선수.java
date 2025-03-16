import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String,Integer> hm=new HashMap<>();
        
        for(int i=0;i<participant.length;i++){
            int val=0;
            if(hm.get(participant[i])!=null){
                val=hm.get(participant[i]);
            }
            hm.put(participant[i],val+1);
        }
        
        for(int i=0;i<completion.length;i++){
            hm.put(completion[i],hm.get(completion[i])-1);
        }
        for(int i=0;i<participant.length;i++){
            if(hm.get(participant[i])!=0){
                answer=participant[i];
            }
        }
        
        
        
        return answer;
    }
}
