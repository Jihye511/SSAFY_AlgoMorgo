import java.util.*;
class Solution {
    public static int result;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        result=0;
        dfs(numbers,0,numbers.length,0,target);
        answer=result;
        return answer;
    }
    
    public static void dfs(int[] numbers,int start,int max,int num,int target){
        if(start==max){
            if(num==target){
                result++;
               
            }
             return;
        }
        int count=num;
        count+=numbers[start];
        dfs(numbers,start+1,max,count,target);
        count=num;
        count-=numbers[start];
        dfs(numbers,start+1,max,count,target);
    }
}
