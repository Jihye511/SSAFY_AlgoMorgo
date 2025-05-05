class Solution {
    public int solution(String name) {
        int answer = 0;
        int idx;
        int len = name.length();
        int move = len - 1;
        for(int i = 0; i < len; i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            idx = i + 1;
            while(idx < len && name.charAt(idx) == 'A'){
                idx += 1;
            }
            // 앞이 더 빠른경우
            move = Math.min(move, i * 2 + len - idx);
            // 뒤가 더 빠른경우
            move = Math.min(move, i + (len - idx) * 2);   
        }
        return answer + move;
    }
}
