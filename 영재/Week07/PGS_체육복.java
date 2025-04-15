import java.util.*;
class PGS_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        
        
        List<Integer> lost1=new ArrayList<>();
        List<Integer> reserve1=new ArrayList<>();
        
        for(int i=0;i<lost.length;i++){
            lost1.add(lost[i]);
        }
        
        for(int i=0;i<reserve.length;i++){
            reserve1.add(reserve[i]);
        }
        
        Collections.sort(lost1);
        Collections.sort(reserve1);
        
        boolean[] chk=new boolean[n+1];
        

        
        System.out.println(lost1.toString());
        
        //겹치는애들 찾고 삭제
        for(int i=0;i<lost.length;i++){
            if(reserve1.contains(lost[i])&&lost1.contains(lost[i])){
                reserve1.remove(Integer.valueOf(lost[i]));
                lost1.remove(Integer.valueOf(lost[i]));
            }
        }
        
        //시작 갯수
        int answer = n-lost1.size();
        
        for(int i=0;i<reserve1.size();i++){
            int now=reserve1.get(i);
            if(find(now-1,lost1)&&!chk[now-1]){
                answer++;
                chk[now-1]=true;
            }else if(find(now+1,lost1)&&!chk[now+1]){
                answer++;
                chk[now+1]=true;
            }
        }

        return answer;
    }
    static boolean find(int k,List<Integer> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i)==k)
                return true;
        }
        return false;
    }
}
