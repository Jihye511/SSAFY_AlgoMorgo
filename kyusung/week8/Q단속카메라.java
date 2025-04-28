import java.util.*;

class Q단속카메라 {
    
    public int solution(int[][] routes) {
        int answer = 0;
        int N = routes.length;
        
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int last_check = routes[0][1];
        answer++;
        
        for(int i = 1; i<N; i++){
            if(routes[i][0] > last_check){
                answer++;
                last_check = routes[i][1];
            }
        }
        
        return answer;
    }
}