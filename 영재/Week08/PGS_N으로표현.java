import java.util.*;
class Solution {
    public int solution(int N, int number) {
        
        
        Set<Integer> list[]=new HashSet[9];
        for(int i=1;i<=8;i++){
            list[i]=new HashSet<>();
        }
        list[1].add(N);
        
        String str=Integer.toString(N);
        for(int i=1;i<=8;i++){
            String new_str="";
                for(int q=0;q<i;q++){
                    new_str+=str;
                }

                list[i].add(Integer.parseInt(new_str));
            for(int j=1;j<i;j++){
                for(Integer a : list[i-j]){
                    for(Integer b : list[j]){
                        list[i].add(a+b);
                        list[i].add(a-b);
                        list[i].add(a*b);
                        if(b!=0)
                            list[i].add(a/b);
                
                        list[i].add(b-a);
                        if(a!=0)
                            list[i].add(b/a);
                        }
            }
            
                
                
            }
            
        }
        int answer = -1;
        for(int i=1;i<=8;i++){
            if(list[i].contains(number)){
                answer=i;
                break;
            }
                
        }
        return answer;
    }
}
