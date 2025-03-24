package PGS;

import java.util.*;
import java.io.*;
class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) throws Exception {
        int k[]=new int[speeds.length];

        Queue<Integer> q=new ArrayDeque<>();
        for(int i=0;i<progresses.length;i++){
            progresses[i]=100-progresses[i];
            if(progresses[i]%speeds[i]==0){
                k[i]=progresses[i]/speeds[i];
            }else{
                k[i]=progresses[i]/speeds[i]+1;
            }
            System.out.print(k[i]+ " ");
        }
        int idx=0;
        int max=k[0];
        int cnt=0;
        
        List<Integer> res=new ArrayList<>();
        
        while(idx<k.length){
            
            if(k[idx]<=max){
                cnt++;
            }else{
                max=k[idx];
                res.add(cnt);
                cnt=0;
                continue;
            }
            if(idx==k.length-1)
                res.add(cnt);
            idx++;
        }
        int []answer=new int[res.size()];
        for(int i=0;i<res.size();i++){
            answer[i]=res.get(i);
        }

        
        return answer;
    }
}
