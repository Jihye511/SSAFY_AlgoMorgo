import java.io.*;
import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        
        boolean[] v = new boolean[words.length];
        dfs(target, begin,0,0, words, v);
        
        if(answer == Integer.MAX_VALUE){
            answer =0;
        }
        return answer;
    }
    
    public static void dfs(String target, String cur,int idx, int depth, String[] words,boolean[] v){
        if(cur.equals(target)){
            answer = Math.min(answer, depth);    
            return;
        }

        for(int i =0; i< words.length; i++){
            if(!v[i]){
                if(check(words[i], cur)){
                    v[i] = true;
                    dfs(target, words[i], 0, depth +1, words, v);
                    v[i] = false;
                }
            }
        }
    }
    public static boolean check(String next, String cur){
        int size = next.length();
        int cnt =0;
        for(int i =0; i<size; i++){
            if(next.charAt(i) == cur.charAt(i)){
                cnt++;
            }
        }
        if(cnt ==size -1) return true;
        
        return false;
        
    }
} 
