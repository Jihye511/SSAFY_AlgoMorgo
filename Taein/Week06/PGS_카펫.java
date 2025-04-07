import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 1; i <= yellow; i++){
            if(yellow % i == 0) arr.add(i);
        }
        int[] answer = new int[2];
        // i : 세로길이
        for(int i : arr){
            if(brown == 4 + i * 2 + (yellow / i) * 2){
                answer[0] = yellow / i + 2;
                answer[1] = i + 2;
                break;
            }
        }
        return answer;
    }
}
