import java.util.*;

class Q의상 {
    
   public static int solution(String[][] clothes) {
        HashMap <String, Integer> map = new HashMap<>();
       
        int ans = 1;
        int len = clothes.length;
        for(int i = 0; i<len; i++){
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1); 
        }
       
        int cate = map.size();
        
        for(String str : map.keySet()){
            ans *= map.get(str) + 1;
        }
        
       System.out.println(ans);
       
        return ans - 1;
    }
    
    
}