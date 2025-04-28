import java.io.*;
import java.util.*;
class Solution {
    static int[] number;
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        number = numbers;
        dfs(0, 0, target);
        
        return answer;
    }
    public static void dfs(int hap, int idx,int target){
        if(idx == number.length){
            if(hap == target){
                answer++;
            }
            return;
        }
        dfs(hap+number[idx],idx+1, target);
        dfs(hap-number[idx],idx+1, target);
    }
}
