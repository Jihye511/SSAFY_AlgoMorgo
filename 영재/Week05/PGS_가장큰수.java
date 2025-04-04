import java.util.*;
import java.io.*;
class PGS_가장큰수 {
    public String solution(int[] numbers) {
        StringBuilder sb=new StringBuilder();
        String[] str=new String[numbers.length];
        
        for(int i=0;i<numbers.length;i++){
            str[i]=Integer.toString(numbers[i]);
        }
        Arrays.sort(str,(o1,o2)->((o2+o1).compareTo(o1+o2)));
        
        for(int i=0;i<str.length;i++){
            sb.append(str[i]);
        }
        String answer=sb.toString();
        if(answer.charAt(0)=='0')
            return "0";
        return answer;
    }
}
