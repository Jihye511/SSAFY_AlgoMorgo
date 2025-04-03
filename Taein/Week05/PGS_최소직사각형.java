import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        ArrayList<Integer> row = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>();
        for(int i = 0; i < sizes.length; i++){
            if(sizes[i][0] > sizes[i][1]){
                row.add(sizes[i][0]);
                col.add(sizes[i][1]);
            }
            else{
                row.add(sizes[i][1]);
                col.add(sizes[i][0]);
            }
        }
        Collections.sort(row);
        Collections.sort(col);
        answer = row.get(row.size() - 1) * col.get(col.size() - 1);
        
        return answer;
    }
}
