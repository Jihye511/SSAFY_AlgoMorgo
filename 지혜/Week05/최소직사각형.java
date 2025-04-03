import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max=Integer.MIN_VALUE , min = Integer.MIN_VALUE;
        for(int i =0; i<sizes.length; i++){
            int[] card = sizes[i];
            int tempMax = Math.max(card[0], card[1]);
            int tempMin = Math.min(card[0], card[1]);
            max = Math.max(max, tempMax);
            min = Math.max(min, tempMin);
        }
        
        return max * min;
    }
}
