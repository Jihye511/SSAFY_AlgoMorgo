import java.io.*;
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
       String[] str = new String[numbers.length];
        
        for(int i =0; i<numbers.length; i++){
            str[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(str, (a,b) -> (b+a).compareTo(a+b));
        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
        }
        if(sb.charAt(0) =='0'){
            return "0";
        }
        return sb.toString();
    }

}
