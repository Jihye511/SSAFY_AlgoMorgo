import java.util.*;
class Solution {
    public int solution(String name) {
        int answer = 0;
   
        List<Character> list = new LinkedList<>();
        for(int i=0;i<26;i++){
            list.add((char)('A'+i));
        }
   
       for(int i=0;i<name.length();i++){
           int num= list.indexOf(name.charAt(i));
 
                if(num>13){
               num=26-num;
           }
           answer+=num;
         
          
       }
        int rightnum=name.length()-1;
         for(int i=0;i<name.length();i++){
             int next=i+1;
             while(next<name.length()&&name.charAt(next)=='A'){
                 next++;
             }
         
           int right = i * 2 + (name.length() - next);
            int left = (name.length() - next) * 2 + i;
             rightnum=Math.min(rightnum,Math.min(right,left));
         
          
       }

      
        return answer+rightnum;
    }
}
