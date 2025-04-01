import java.util.*;

class Q최소직사각형{
    public int solution(int[][] sizes) {
        int answer = 0;
        int len = sizes.length;
        for(int i =0; i<len ; i++){
            Arrays.sort(sizes[i]);
        }

        int horiz = Integer.MIN_VALUE;
        int verti = Integer.MIN_VALUE;
        for(int i =0; i<len; i++){
            horiz = Math.max(horiz, sizes[i][0]);
            verti = Math.max(verti, sizes[i][1]);
        }
        
        answer = horiz * verti;
        return answer;
    }
}