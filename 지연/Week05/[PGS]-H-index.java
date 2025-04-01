import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
      Arrays.sort(citations);
   
        int start=0;
        int end=citations[citations.length-1];
        
        
            while(start<=end){
                int mid=(start+end)/2;
               
                    if(check(mid,citations)>=mid){
                        start=mid+1;
                        
                        answer=Math.max(mid,answer);
                    }
                else{
                    end=mid-1;
                
                }
            }
         return answer;
    }
    public static int check(int mid,int[] length){
       int count=0;
        for(int i=0;i<length.length;i++){
            if(mid<=length[i]){
                count++;
            }
        }
        return count;
    }
    
        

}
