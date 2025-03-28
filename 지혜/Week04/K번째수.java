import java.util.*;
import java.io.*;
class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> list=new ArrayList<>();
        int cnt=0;
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;i++){
            int start=commands[i][0];
            int end=commands[i][1];
            int idx=commands[i][2];
            int [] tmp=new int[end-start+1];
            System.arraycopy(array,start-1,tmp,0,end-start+1);
            Arrays.sort(tmp);
            answer[cnt++]=tmp[idx-1];
        }
        return answer;
    }
}
