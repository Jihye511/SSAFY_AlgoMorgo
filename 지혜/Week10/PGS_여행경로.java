import java.io.*;
import java.util.*;
class Solution {
    static ArrayList<String> list;
    static String[][] ticket;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        ticket = tickets;
        list = new ArrayList<>();
        boolean[] v = new boolean[tickets.length];
        dfs("ICN" , "ICN", 0, v);
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }
    public static void dfs(String start, String path, int depth , boolean[] v){
        if(depth ==ticket.length){
            list.add(path);
            return;
        }
        
        for(int i =0; i<ticket.length; i++){
            if(!v[i] && ticket[i][0].equals(start)){
                v[i] = true;
                dfs(ticket[i][1], path+" "+ ticket[i][1], depth+1, v);
                v[i] =false;
            }
        }
    }
}
