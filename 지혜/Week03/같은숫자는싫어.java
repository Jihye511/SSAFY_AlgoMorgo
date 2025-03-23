import java.util.*;
import java.io.*;
public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int before =arr[0];
        list.add(before);
        for(int i =1; i<arr.length; i++){
            if(before == arr[i]) continue;
            list.add(arr[i]);
            before= arr[i];
            
        }
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
