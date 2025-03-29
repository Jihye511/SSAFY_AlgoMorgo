import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x =x;
            this.y= y;
        }
    }
    static class Edge implements Comparable<Edge>{
        int from , to;
        double weight;
        public Edge(int from, int to, double weight){
            this.from = from;
            this.to = to;
            this.weight =weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static int num;
    static int N;
    static int[] parents;
    static Point[] points;
    static void make(){
        parents = new int[num];
        for(int i =0;i <num; i++){
            parents[i]  =i;
        }
    }
    public static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    public static boolean union(int a , int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            num = Integer.parseInt(br.readLine());
            points = new Point[num];
//            int[][] point = new int[num][2];
            StringTokenizer st;
            for(int i =0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j =0; j<num; j++){
                    if(i ==0){
                        points[j] = new Point(0,0);
                        points[j].x =Integer.parseInt(st.nextToken());
                    }
                    else points[j].y = Integer.parseInt(st.nextToken());
                }
            }
            double E = Double.parseDouble(br.readLine());
            //간선 리스트 만들기
            List<Edge> edges = new ArrayList<>();
            //거리 기준
            for(int i =0; i<num; i++){
                for(int j =i+1; j<num; j++){
                    double dist = Math.pow(points[i].x - points[j].x,2) + Math.pow(points[i].y - points[j].y,2);
                    edges.add(new Edge(i,j,dist));
                }
            }

            //크루스칼
            Collections.sort(edges);
            make();
            double total = 0;
            int cnt =0;
            for(Edge e : edges){
                if(union(e.from, e.to)){
                    total+=e.weight;
                    cnt++;
                    if(cnt == num-1)break;
                }
            }
            double result = E* total;
            System.out.println("#" +t + " " + Math.round(result));
        }
    }

}
