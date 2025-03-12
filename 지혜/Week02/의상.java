import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] r : clothes){
            map.put(r[1], map.getOrDefault(r[1], 0) + 1);
        }
        int cnt =1;
        for(Integer i : map.values()){
                cnt *= (i+1);
        }
        return cnt-1;
    }
}
