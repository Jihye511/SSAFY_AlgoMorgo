import java.util.*;
class Solution {
  
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[] p1={1,2,3,4,5};
        int[] p2={2,1,2,3,2,4,2,5};
        int[] p3={3,3,1,1,2,2,4,4,5,5};
        int[] p_num=new int[3];

        int result=0;
    
        for(int i=0;i<answers.length;i++){
            
        int num=answers[i];
            if(person(p1,num,i)){
                p_num[0]++;
                result=Math.max(result, p_num[0]);
            }
            if(person(p2,num,i)){
               p_num[1]++;
                   result=Math.max(result, p_num[1]);
            }
            if(person(p3,num,i)){
                p_num[2]++;
                  result=Math.max(result, p_num[2]);
            }
        }
           
        if(result!=0){
            
             List<Integer> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(result==p_num[i]){
                list.add(i+1);
            }
        }
      
        answer=new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        }
        else{
            answer=new int[1];
            answer[0]=0;
        }
      
      
        return answer;
    }
    public static boolean person(int[] p,int num,int idx){
        int myidx = idx%p.length;
            if(p[myidx]==num){
                return true;
            }
        return false;
        
        
    }
   
}
