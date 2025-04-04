import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        
        int[] p1 = {1,2,3,4,5};
        int[] p2 = {2,1,2,3,2,4,2,5};
        int[] p3 = {3,3,1,1,2,2,4,4,5,5};
        
        int[] correct = new int[4];
        for(int i =0; i<answers.length; i++){
            if( answers[i] ==p1[i%p1.length]) correct[1]++;
            if(answers[i]==p2[i%p2.length]) correct[2]++;
            if(answers[i]==p3[i%p3.length]) correct[3]++;
        }
        int max = 0;
        for(int i : correct){
            max = Math.max(max, i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i =1; i<4; i++){
            if(max == correct[i]){
                list.add(i);
            }
        }
        
        
        int[] answer = new int[list.size()];
        for(int i =0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
