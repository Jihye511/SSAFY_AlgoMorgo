import java.io.*;
import java.util.*;
class Solution {
    static HashSet<Integer> hash = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        char [] ch = new char[numbers.length()];
        for(int i =0; i<numbers.length(); i++){
            ch[i] = numbers.charAt(i);
        }
        for(int i =1; i<=numbers.length(); i++){
            boolean[] v = new boolean[numbers.length()];
            dfs("", ch, i, 0, v);
        }
        
        return hash.size();
    }
    
    public static boolean checknum(int num){
        if(num ==1 || num==0) return false;
        for(int i =2; i<num; i++){
            if(num%i ==0){
                return false;
            }
        }
        return true;
    }
    public static void dfs(String str, char[] ch, int depth, int length, boolean[] v){
        if(str !=""){
            int n = Integer.parseInt(str);
            if(checknum(n)){
                hash.add(n);    
            }
            
        }
        
        for(int i =0; i<ch.length;i++){
            if(!v[i]){
                v[i] = true;
                dfs(str+ch[i],ch, depth+1, length, v);
                v[i] = false;
            }
        }
    }
}
