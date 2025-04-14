import java.util.*;
class Solution {
    static char[] alphabat={'A','E','I','O','U'};
    static ArrayList<String>list;
    public int solution(String word) {
        int answer = 0;
        list=new ArrayList<>();
        combination(0,"");
        Collections.sort(list);
        answer = list.indexOf(word)+1;
   
        return answer;
    }
    public static void combination(int index,String str){
        if(index>=5) return;
        for(int i=0;i<alphabat.length;i++){
            list.add(str+alphabat[i]);
            combination(index+1,str+alphabat[i]);
        }
    }
}
