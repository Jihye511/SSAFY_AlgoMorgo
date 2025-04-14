import java.util.*;

class Q큰수만들기{
	
    public static String solution(String str, int k) {
    
    	String ans = "";
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        int[] ints = new int[chars.length];
        for(int i = 0; i<chars.length; i++){
            ints[i] = chars[i] - '0';
        }

        int len = chars.length;
        int chance = k;
        int fac = 0;                                    // 확정
        
        for(int i = 0; i < len - chance; i++){
            int max = 0;
            
            for(int j = fac; j <= i + chance; j++){    // 최대값 구하기
                if(max < ints[j]) {
                    max = ints[j];
                    fac = j + 1;
                }
            }
            sb.append(max);
        }
        
        return sb.toString();
    }
}