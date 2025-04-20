import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        boolean[] v = new boolean[people.length];
        int start = 0;
        int end = people.length - 1;
        while(start<end){
            if(people[start] + people[end] <= limit){
                answer++;
                v[start] = v[end] = true;
                start ++;
                end --;
            }
            else{
                end--;
            }
        }
        for(boolean i : v){
            if(i == false) answer ++;
        }
        
        return answer;
    }

}
