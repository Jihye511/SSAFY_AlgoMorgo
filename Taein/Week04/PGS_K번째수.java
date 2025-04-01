import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> arr;
        int[] answer = new int[commands.length];
        for(int t = 0; t < commands.length; t++){
            arr = new ArrayList<>();
            for(int i = commands[t][0]-1; i < commands[t][1]; i++){
                arr.add(array[i]);
            }
            Collections.sort(arr);
            answer[t] = arr.get(commands[t][2] - 1);
        }
        
        return answer;
    }
}
