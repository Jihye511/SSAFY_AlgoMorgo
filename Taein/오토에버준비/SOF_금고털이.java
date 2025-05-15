import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int m, p;
        Node(int m, int p){
            this.m = m;
            this.p = p;
        }

        @Override
        public int compareTo(Node o){
            return o.p - this.p;
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Node> arr = new ArrayList<>();

        int a, b;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr.add(new Node(a, b));
        }

        Collections.sort(arr);

        int result = 0;
        for(int i = 0; i < N; i++){
            if (W >= arr.get(i).m){
                W -= arr.get(i).m;
                result += arr.get(i).m * arr.get(i).p;
            }
            else{
                result += W * arr.get(i).p;
                break;
            }

        }

        System.out.println(result);


    }
}
