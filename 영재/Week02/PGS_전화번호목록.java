import java.util.*;
import java.io.*;
class Solution {
    public boolean solution(String[] phone_book) throws Exception{

        boolean answer=true;
        
        Arrays.sort(phone_book);
        
        
    	for(int i=0; i<phone_book.length-1;i++ ){
            if(phone_book[i+1].startsWith(phone_book[i]))
            {
                answer=false;
                break;
            }
        }
        
        
        return answer;
    }
}
