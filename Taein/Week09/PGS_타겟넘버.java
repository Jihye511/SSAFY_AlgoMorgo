class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, 0, 0, target, numbers.length);
        return answer;
    }
    public static void dfs(int[] numbers, int idx, int sum, int target, int size){
        if(idx == size){
            if(sum == target){
                answer += 1;
            }
            return;
        }
        
        dfs(numbers, idx + 1, sum + numbers[idx], target, size);
        dfs(numbers, idx + 1, sum - numbers[idx], target, size);        
    }
}
