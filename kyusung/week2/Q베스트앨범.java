package prj1;
import java.util.*;

class Solution {
    public static class Node{
        String gen; 
        int play, idx;
        
        public Node(String gen, int play, int idx){
            this.gen = gen;
            this.play = play;
            this.idx = idx;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        int N = genres.length;
        Map<String, Integer> played_genre = new HashMap<>();
        
        for(int i =0; i<N; i++){
            played_genre.put(genres[i] , played_genre.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.play, o1.play));
        Node[] nodes = new Node[N];
        
        int i2 = 0;
        for(Map.Entry<String, Integer> s1 : played_genre.entrySet()){
            pq.add(new Node(s1.getKey(), s1.getValue(), i2++));
        }
        
        for(int i = 0; i<N; i++) {
        	String g = genres[i];
        	int p = plays[i];
        	
        	nodes[i] = new Node(g, p, i);
        }
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o2.play, o1.play));
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<played_genre.size(); i++) {
        	Node now = pq.poll();
        	
        	String now_make = now.gen;
        	boolean done_first = false;
        	A:for(int j=0; j<N; j++) {
        		if(nodes[j].gen.equals(now_make)) {
        			if(!done_first) {
        				list.add(nodes[j].idx);
        				done_first = true;
        			}else {
        				list.add(nodes[j].idx);
        				break A;
        			}
        		}
        	}
        }
        
        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}