import java.util.*;

class PGS_큰수만들기 {
    public String solution(String number, int k) {
        String answer = "";
        
        char[] ch=number.toCharArray();
        Deque<Integer> dq=new ArrayDeque<>();
        

        int cnt=0; //자른 횟수
        int idx=0; //현재 주소
        int i=0;
        for(i=0;i<ch.length;i++){
           while(!dq.isEmpty()&&dq.peekLast()<ch[i]-'0'&&cnt<k){
               dq.pollLast();
               cnt++;
           }
            dq.offerLast(ch[i]-'0');
            
        }
        
        while(cnt<k){
            dq.pollLast();
            cnt++;
        }
        
        StringBuilder sb=new StringBuilder();
        int size=dq.size();
        for(int j=0;j<size;j++){
            
            sb.append(dq.pollFirst());
        }
        return sb.toString();
    }
}
