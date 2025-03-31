import java.util.*;

class Solution {
    public static class Node{
        int value;
        int idx;
            
        Node(int value, int idx){
            this.value = value;
            this.idx = idx;
        }
    }
    public static int[] solution(int[] prices){
        int len = prices.length;
        Stack<Node> stack = new Stack<>();
        int[] arr = new int[len];
        
        for(int i = 0; i<len; i++){
            int now = prices[i];
            
            while(!stack.isEmpty() && stack.peek().value > now){
                Node p = stack.pop();
                arr[p.idx] = i - p.idx;
            }
            
            stack.push(new Node(prices[i], i));
        }
                
        while(!stack.isEmpty()){
            Node p = stack.pop();
            arr[p.idx] = len - 1 - p.idx;
        }

        return arr;
    }
}