import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int point = routes[0][1];
        for(int i = 1; i < routes.length; i++){
            if(point < routes[i][0]){
                answer++;
                point = routes[i][1];
            }
        }
        
            
        return answer;
    }
}
