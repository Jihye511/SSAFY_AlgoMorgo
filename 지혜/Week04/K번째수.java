import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        ArrayList<Integer> list = new ArrayList<>(); // 답
        for(int i =0; i<commands.length; i++){
            int start = commands[i][0];
            int end = commands[i][1];
            int[] temp = new int[end-start+1];
            int idx=0;
            for(int j =start; j<end+1; j++){
                temp[idx] = array[j-1];
                idx++;
            }
            Arrays.sort(temp);
            list.add(temp[commands[i][2]-1]);
        }
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
