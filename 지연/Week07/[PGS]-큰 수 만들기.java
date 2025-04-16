import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int[] arr = new int[number.length()-k];
        int start=0;
       int len = number.length();
        int idx=0;
       
      
        for(int i=0;i<len-k;i++){
          int max=0;
            for(int j=start;j<=k+i;j++){
                int num=number.charAt(j)-'0';
                if(num>max){
                    max=num;
                    start=j+1;
                }
            }
            arr[idx++]=max;
            
               
        
           
        }
        StringBuilder sb = new StringBuilder();
       for(int n: arr){
           sb.append(n);
       }
        answer=sb.toString();
        return answer;
    }
}
