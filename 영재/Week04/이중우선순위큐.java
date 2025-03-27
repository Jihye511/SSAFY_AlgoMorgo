import java.util.*;
import java.io.*;



class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq=new PriorityQueue<>((o1,o2)->(o1-o2));
        PriorityQueue<Integer> reverse_pq=new PriorityQueue<>((o1,o2)->(o2-o1)); //거꾸로
        String tmp[];
        List<Character> list_ch=new ArrayList<>();
        List<Integer> list_num=new ArrayList<>();
        for(int i=0;i<operations.length;i++){
            tmp=operations[i].split(" ");
            list_ch.add(tmp[0].charAt(0));
            int tmp_num=Integer.parseInt(tmp[1]);
            list_num.add(tmp_num);
        }
        int size=0;
        for(int i=0;i<operations.length;i++){
            char ch=list_ch.get(i);
            int num=list_num.get(i);
            
            if(list_ch.get(i)=='I'){
                pq.add(num);
                reverse_pq.add(num);
                size++;
            }else if(list_ch.get(i)=='D'){ //D인 경우
                if(size==0)
                    continue;
                if(num==1){
                    int k=reverse_pq.poll();
                    pq.remove(k);
                }else{
                    int k=pq.poll();
                    reverse_pq.remove(k);
                }
                size--;
            }
            
        }
        // System.out.println(size);
        // System.out.println(pq);
        // System.out.println(reverse_pq);
        int[] answer = new int[2];
        
        if(size<=0){
            answer[0]=0;
            answer[1]=0;
        }else{
            answer[0]=reverse_pq.poll();
            answer[1]=pq.poll();
        }
        
        
        return answer;
    }
}
