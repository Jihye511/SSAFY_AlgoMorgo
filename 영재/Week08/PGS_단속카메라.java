import java.util.*;
class PGS_단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes,(a,b)->a[1]-b[1]);
        int end=Integer.MIN_VALUE;
        for(int i=0;i<routes.length;i++){
            if(routes[i][0]>end){
                end=routes[i][1];
                answer++;
            }
        }
        return answer;
    }
}
