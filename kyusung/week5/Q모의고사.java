import java.util.*;

class Q모의고사 {
    public int[] solution(int[] answer) {
        int a = 0;
        int b = 0;
        int c = 0;
        
        for(int i = 0; i<answer.length; i++){
            if(answer[i] == (i%5) + 1) a++;
            if(i % 2 == 0){
                if(answer[i] == 2) b++;
            }else{
                if(i % 8 == 1 && answer[i] == 1) b++;
                else if(i % 8 == 3 && answer[i] == 3) b++;
                else if(i % 8 == 5 && answer[i] == 4) b++;
                else if(i % 8 == 7 && answer[i] == 5) b++;
            }
            if( (i % 10) / 2 == 0) {
                if(answer[i] == 3) c++;
            }else if((i % 10) / 2 == 1){
                if(answer[i] == 1) c++;
            }else if((i % 10) / 2 == 2){
                if(answer[i] == 2) c++;
            }else if((i % 10) / 2 == 3) {
                if(answer[i] == 4) c++;
            }else if((i % 10) / 2 == 4) {
                if(answer[i] == 5) c++;
            }
        }
        
        int max = Math.max(a, Math.max(b, c));
        ArrayList<Integer> list = new ArrayList<>();
        if(a == max) list.add(1);
        if(b == max) list.add(2);
        if(c == max) list.add(3);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}