import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] citations) throws Exception{
        Integer k[]=new Integer[citations.length];
        for(int i=0;i<k.length;i++){
            k[i]=citations[i];
        }
        int ans=0;
        Arrays.sort(k,Collections.reverseOrder());
        int len=k.length;
        boolean chk=false;
        for(int i=0;i<k.length;i++){
            if(k[i]<=(i+1)){
                if(k[i]==i+1){
                    ans=k[i];
                }else{
                    ans=i;
                }
                chk=true;
                break;
            }
        }
        if(!chk){
            ans=k.length;
        }
        return ans;
    }
}
