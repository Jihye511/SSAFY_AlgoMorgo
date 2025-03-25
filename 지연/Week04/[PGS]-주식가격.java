import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
     answer=new int[prices.length];
        Stack<Integer> index = new Stack<>();
        
  
          for(int i=0;i<prices.length;i++){
              int num=prices[i];
               while(!index.isEmpty()&&num<prices[index.peek()]){
                  
                   int p=index.pop();
                   answer[p]=i-p;
        }
              index.push(i);
          
       
          }
        while(!index.isEmpty()){
            int i=index.pop();
            answer[i]=(prices.length-1)-i;
        }
      
     
      
        return answer;
    }
}
