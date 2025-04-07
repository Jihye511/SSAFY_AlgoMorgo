class Solution {
    static int answer, N;
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        N = dungeons.length;
        recur(k, new boolean[N], dungeons, 0);
        return answer;
    }
    
    public void recur(int stress, boolean[] visited, int[][] dungeons, int cnt){
        if(answer < cnt) answer = cnt;

        for(int i = 0; i < N; i++){
            if(!visited[i] && stress >= dungeons[i][0]){
                    visited[i] = true;
                    recur(stress - dungeons[i][1], visited, dungeons, cnt + 1);
                    visited[i] = false;
            }
        }
    }
    
}
