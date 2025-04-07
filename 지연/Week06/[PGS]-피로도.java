import java.util.*;
class Solution {
    static int result;

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        result=0;
        boolean[] visited=new boolean[dungeons.length];
        recursive(k,0,dungeons,visited);
        if(result!=0){
            return result;
        }
        return answer;
    }
    public static void recursive(int k,int idx,int[][] dungeons,boolean[] visited){
      result=Math.max(result,idx);
        for(int i=0;i<dungeons.length;i++){
            if(!visited[i]&&k>=dungeons[i][0]){
                visited[i]=true;
                int new_k = k-dungeons[i][1];
                int count=idx+1;
                recursive(new_k,count,dungeons,visited);
                 visited[i]=false;
            }
            
        }
    }
}
