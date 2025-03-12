import java.util.*;

class Q전화번호목록{
        public static boolean solution(String[] numb) {
            Arrays.sort(numb);
            
            for(int i = 1; i<numb.length; i++){
                String prev = numb[i-1];
                
                if(numb[i].startsWith(prev)) return false;
            }
            
            return true;
        }
    }