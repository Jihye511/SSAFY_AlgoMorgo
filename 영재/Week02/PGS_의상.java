import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] clothes) throws Exception{
        HashMap<String,Integer> hm=new HashMap<>();
        int answer = 1;
        for(int i=0;i<clothes.length;i++){
            int value=hm.getOrDefault(clothes[i][1],1);
            hm.put(clothes[i][1],value+1);
        }
        
        Collection<Integer> col=hm.values();
        for(int k : col){
            answer*=k;
        }
        
        
        return answer-1;
    }
}
