import java.util.*;
class Solution {
    public static List<String> answerlist=null;
    public static boolean[] visited;

    public String[] solution(String[][] tickets) {
        String[] answer = {};
   Arrays.sort(tickets,(a,b)->a[1].compareTo(b[1]));
     visited = new boolean[tickets.length];

        List<String> list = new ArrayList<>();
        
          list.add("ICN");
      
       dfs("ICN",list,0,tickets);
        
          answer=new String[tickets.length+1];
        for(int i=0;i<answerlist.size();i++){
            answer[i]=answerlist.get(i);
        }
        return answer;
    }
    public static void dfs(String start,List<String> list,int count,String[][] tickets){
    
        if(count==tickets.length){
            if(answerlist==null){
                answerlist=new ArrayList<>(list);
             
            }
          
            return;
        }
         for(int i=0;i<tickets.length;i++){
            
                if(!visited[i]&&tickets[i][0].equals(start)){
                    visited[i]=true;
                   
                   list.add(tickets[i][1]);
          
                    
                    dfs(tickets[i][1],list,count+1,tickets);
                    list.remove(list.size()-1);
                     visited[i]=false;
                 
                }
            }
    }
 
}
