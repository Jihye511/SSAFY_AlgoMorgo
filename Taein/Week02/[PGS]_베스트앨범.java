import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap<>();
        HashMap<String, ArrayList<int[]>> hash_ord = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if(hash.containsKey(genres[i])){
                hash.put(genres[i], hash.get(genres[i]) + plays[i]);
            }
            else hash.put(genres[i], plays[i]);

            if(hash_ord.containsKey(genres[i])){
                hash_ord.get(genres[i]).add(new int[] {plays[i], i});
            }
            else {
                hash_ord.put(genres[i], new ArrayList<>());
                hash_ord.get(genres[i]).add(new int[] {plays[i], i});
            }

        }

        ArrayList<String> key = new ArrayList<>(hash.keySet());

        key.sort((o1, o2) -> hash.get(o2).compareTo(hash.get(o1)));
        String temp;
        
        for (int i = 0; i < key.size(); i++) {
            temp = key.get(i);
            if(hash_ord.get(temp).size() == 1){
                result.add(hash_ord.get(temp).get(0)[1]);
            }
            else {
                hash_ord.get(temp).sort(Comparator.comparingInt((int[] o) -> o[0]).reversed());
                result.add(hash_ord.get(temp).get(0)[1]);
                result.add(hash_ord.get(temp).get(1)[1]);
            }
        }
        int[] answer = new int[result.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = result.get(i);
        }
        return answer;
        
    }
}
