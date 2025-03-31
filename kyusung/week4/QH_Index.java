import java.util.*;

class QH_Index{
        public static int solution(int[] cita) {
            int len = cita.length;
            Arrays.sort(cita);
            
            int ans = 0;
            for(int i = 0; i < len; i++){
                while (len - i >= ans && cita[i] >= ans){
                    ans++;
                }
            }
            
            return ans - 1;
        }
    }