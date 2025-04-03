import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        String answer = "";
          List<String> list = new ArrayList<>();
        for(int i=0;i<numbers.length;i++){
            list.add(numbers[i]+"");
        }
  
             list.sort((a,b)->(b+a).compareTo(a+b));
      
        // 전부 0이면 맨 앞도 0임 그래서 아래로 처리가능
        //if (list.get(0).equals("0")) return "0";
        
     boolean firstcheck=false;
        
        for(int i=0;i<list.size()-1;i++){
         if(list.get(i+1).equals("0")&&!firstcheck){
             continue;
         }
            else{
                firstcheck=true;
                  sb.append(list.get(i));
            }
              
 
            
        }
        sb.append(list.get(list.size()-1));
        
      answer = sb.toString();
        return answer;
    }
}
