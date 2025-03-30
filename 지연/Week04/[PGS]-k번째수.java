import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
          answer = new int[commands.length];
        List<Integer>[] list = new ArrayList[commands.length];
    
        for(int i=0;i<commands.length;i++){
             list[i]=new ArrayList<>();
           for(int j=(commands[i][0]-1);j<(commands[i][1]);j++){
               list[i].add(array[j]);               
           }
            list[i].sort(Comparator.naturalOrder());
            answer[i]=list[i].get(commands[i][2]-1);
        }
    
        return answer;
    }
}
