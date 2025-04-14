import java.util.*;
class Solution {
    static ArrayList<String> arr;
    static String[] alpha = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        int answer = 0;
        arr = new ArrayList<>();
        recur(0, "");
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(word)) return i + 1;
        }
        return 0;
    }
    
    public void recur(int idx, String str){
        if(idx == 5) return;
        for(int i = 0; i < 5; i++){
            arr.add(str + alpha[i]);
            recur(idx + 1, str + alpha[i]);
        }
        
    }
}
