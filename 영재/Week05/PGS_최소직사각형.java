import java.util.*;
import java.io.*;
class PGS_최소직사각형 {
    public int solution(int[][] sizes) {
        int max_g=0;
        int max_s=0;
        
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0]<sizes[i][1]){
                int tmp=0;
                tmp=sizes[i][0];
                sizes[i][0]=sizes[i][1];
                sizes[i][1]=tmp;
            }
            max_g=Math.max(max_g,sizes[i][0]);
            max_s=Math.max(max_s,sizes[i][1]);
        }
        int answer = max_g*max_s;
        return answer;
    }
}
