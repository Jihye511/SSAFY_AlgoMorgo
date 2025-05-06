import java.util.*;
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int[] dpO = new int[money.length];  //첫집 O
        int[] dpX = new int[money.length]; //첫집 X
        int len = money.length;
        
        dpO[0] = money[0];
        dpO[1] = dpO[0];
        
        dpX[0]=0;
        dpX[1]=money[1];

        
        //현재 집 x 앞집  or 현재집 + 앞앞집
        for(int i=2;i<len;i++){
            dpO[i] = Math.max(dpO[i - 1], money[i] + dpO[i - 2]);
            dpX[i] = Math.max(dpX[i - 1], money[i] + dpX[i - 2]);
        }
        answer=Math.max(dpO[len-2],dpX[len-1]);
        return answer;
    }
}
