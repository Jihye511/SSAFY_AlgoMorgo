import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int left=0;
        int right=0;
        //큰 값들끼리 곱하기
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0]>=sizes[i][1]){
                left=Math.max(left,sizes[i][0]);
                 right=Math.max(right,sizes[i][1]);
            }
            else{
                left=Math.max(left,sizes[i][1]);
                right=Math.max(right,sizes[i][0]);
            }
            
                
                
        }
        answer=right*left;
        
        return answer;
    }
}
