import java.io.*;
import java.util.*;
class Solution {
    static int[] n;
    static boolean[] v;
    static int ans;
    public static int solution(int k, int[][] dungeons) {
        int answer = -1;
        n = new int[dungeons.length];
        v = new boolean[dungeons.length];
        ans = 0;
        dfs(k,0,dungeons);
        answer = ans;
        return answer;
    }
    public static int simulation(int k,int[] n, int[][] dungeons){
        int cnt=0;
        for(int i =0; i<n.length; i++){
            if(k >= dungeons[n[i]][0]){
                cnt++;
                k-=dungeons[n[i]][1];
            }else break;
        }
        return cnt;
    }
    public static void dfs(int k,int idx,int[][] dungeons){
        if(idx == dungeons.length){
            //계산하러 고
            ans = Math.max(ans,simulation(k,n,dungeons));
            return;
        }
        for(int i =0; i<dungeons.length; i++){
            if(!v[i]){
                v[i] = true;
                n[idx] = i;
                dfs(k,idx+1, dungeons);
                v[i] = false;
            }
        }
    }
}
