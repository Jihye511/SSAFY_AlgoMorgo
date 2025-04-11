import java.util.*;
class Solution {
    static int result;
    static ArrayList<Integer>[] arr;
    public int solution(int n, int[][] wires) {
        result = Integer.MAX_VALUE;
        arr = new ArrayList[n+1];
        
        
        for(int i = 0; i < wires.length; i++){
            cut(n, i, wires, new boolean[n+1]);
        }
        
        return result;
    }
    static public void cut(int n, int idx, int[][] wires, boolean[] visited){
        for(int i = 0; i<= n; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i = 0; i < wires.length; i++){
            if(i == idx) continue;
            arr[wires[i][0]].add(wires[i][1]);
            arr[wires[i][1]].add(wires[i][0]);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(wires[idx][0]);
        int temp;
        int cnt = 0;
        while(!q.isEmpty()){
            temp = q.poll();
            if(!visited[temp]){
                visited[temp] = true;
                cnt += 1;
                for(int i : arr[temp]){
                    q.add(i);
                }
            }
                
        }
        result = Math.min(result, Math.abs(n - cnt * 2));
        
        
        
    }
}
