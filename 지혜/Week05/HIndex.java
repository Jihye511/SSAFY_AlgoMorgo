import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        for(int i =0; i<citations[citations.length-1]; i++){
            int idx =0;
            for(int j=0; j<citations.length; j++){
                if(citations[j]>= i){
                    idx =j;
                    break;
                }
            }
            
            int size = citations.length -idx;
            if(size >= i){
                answer =i;        
            }else break;
            
        }
        return answer;
    }
}
