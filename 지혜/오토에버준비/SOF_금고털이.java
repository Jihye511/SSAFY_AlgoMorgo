import java.io.*;
import java.util.*;
class Item {
    int M,P;
    public Item(int M, int P){
        this.M = M;
        this.P = P;
    }
}
public class Main {
    static int W;
    static int N;
    static Item[] item; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        item = new Item[N];
        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p =Integer.parseInt(st.nextToken());
            item[i] = new Item(m,p);
        }
        Arrays.sort(item, (a, b) -> b.P - a.P);
        int cnt=0;
        
        for(int i =0; i<N; i++){
            if(W<=0) break;
            int num=0; //현재 금속 담을 갯수
            
            if(W >= item[i].M){
                num = item[i].M;
            }else{
                num = W;
            }
            cnt += num * item[i].P;
            W -= num;
        }
        System.out.println(cnt);
    }
}
