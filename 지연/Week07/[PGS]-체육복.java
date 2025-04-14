import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
       int answer = 0;
    int[] array = new int[n+2];
    for(int i=0;i<lost.length;i++){
        array[lost[i]]=lost[i];
    }
   
        Arrays.sort(reserve);
    for(int i=0;i<reserve.length;i++){
      if(array[reserve[i]]==reserve[i]){
          array[reserve[i]]=0;
          reserve[i]=-1;
          continue;
      }
    
    }
            
          for(int i=0;i<reserve.length;i++){
              if(reserve[i]!=-1){
                   if(array[reserve[i]-1]!=0){
                   array[reserve[i]-1]=0;
                 continue;
                 
             }
           if(array[reserve[i]+1]!=0){
                   array[reserve[i]+1]=0;
                   continue;
             }
              }
            
          }
    

    for(int i=0;i<array.length;i++){
        if(array[i]==0){
            answer++;
        }
    }
    answer-=2;
    return answer;
    }
}   
    
