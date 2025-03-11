import java.util.*;

class Q완주하지못한선수 {
    public static String solution(String[] part, String[] comp){

        Map <String, Integer> map = new HashMap<>();
        for(String str : part){
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        
        for(String out : comp){
            map.put(out, map.get(out)-1);
        }

       ArrayList<String> list = new ArrayList<>();
        for(String strs : map.keySet()){
            if(map.get(strs) != 0){
                list.add(strs);
                break;
            }
        }
        
        return list.get(0);
        
    }
}