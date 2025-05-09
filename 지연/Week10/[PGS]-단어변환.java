import java.util.*;
class Solution {
    public static int result;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int count=0;
       result = Integer.MAX_VALUE;
        boolean[] visited = new boolean[words.length];
        dfs(visited,begin,target,count,words);
       
answer=result;
      return answer == Integer.MAX_VALUE ? 0 : result;
    }
    public static void dfs( boolean[] visited,String word,String target,int count,String[] words){  
       
        for(int i=0;i<words.length;i++){
            if(compareword(word,words[i])&&!visited[i]){
         
              if (words[i].equals(target)) {
    result = Math.min(result, count + 1);
    return;
}
                else{
                     visited[i]=true;
                    dfs(visited,words[i],target,count+1,words);
                       visited[i]=false;
                }
            }
          
        }
        
    }
    
    public static boolean compareword(String myword, String word){
        int check=0;
        for(int i=0;i<word.length();i++){
            if(myword.charAt(i)!=word.charAt(i)){
                check++;
            }
        }
        if(check==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean check(int length,String target,String[] words){
        for(int i=0;i<length;i++){
            if(words[i].equals(target)){
                return true;
            }
        }
        return false;
    }
 
}
