import java.util.*;
import java.io.*;

class PGS_모의고사 {
    public int[] solution(int[] answers) {
        int a=0;
        int b=0;
        int c=0;
        int as[]={1,2,3,4,5};
        int bs[]={2,1,2,3,2,4,2,5};
        int cs[]={3,3,1,1,2,2,4,4,5,5};
        int max=0;
        
        for(int i=0;i<answers.length;i++){
            if(answers[i]==as[i%as.length]){
                a++;
            }
            if(answers[i]==bs[i%bs.length]){
                b++;
            }
            if(answers[i]==cs[i%cs.length]){
                c++;
            }  
        }
        max=Math.max(a,max);
        max=Math.max(b,max);
        max=Math.max(c,max);
        
        
        List<Integer> list=new ArrayList<>();
        if(a==max)
            list.add(1);
        if(b==max)
            list.add(2);
        if(c==max)
            list.add(3);
        
        int answer[]=new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
}
