import java.util.*;
import java.io.*;
class PGS_조이스틱 {
    static int total_len;
    static int min;
    public int solution(String name) {
        int answer = 0;
        min=100;
        char[] arr=name.toCharArray();
        int[] num=new int[arr.length];
        total_len=arr.length;
        int cnt=0;
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]!='A') list.add(i);
            answer+=Math.min(arr[i]-'A','Z'-arr[i]+1);
        }
 
        johap(0,list,new int[list.size()],new boolean [list.size()]);
        
        answer+=min;

        return answer;
    }
    static void johap(int idx,List<Integer> list,int[] res,boolean v[]  ){
        
        if(idx==list.size()){
            min=Math.min(min,cal(res));
            return;
        }
        
        
        for(int i=0;i<list.size();i++){
            if(v[i]) continue;
            res[idx]=list.get(i);
            v[i]=true;
            johap(idx+1,list,res,v);
            v[i]=false;
            
            
        }
        
    }
    //length-지금+x
    static int cal(int [] res){
        int cnt=0;
        int curser=0;
        for(int i=0;i<res.length;i++){
            int a=Math.abs(res[i]-curser);
            int b=Math.min(total_len-res[i]+curser,total_len-curser+res[i]);
            
            cnt+=Math.min(a,b);
            curser=res[i];
        }
        return cnt;
        
    }
}
