class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        
        int[] dp1=new int[money.length];
        int[] dp2=new int[money.length];
        
        //0번째 집 턴 경우
        dp1[0]=money[0];
        dp1[1]=money[0];

        
        for(int i=2;i<money.length-1;i++){
            dp1[i]=Math.max(dp1[i-2]+money[i],dp1[i-1]);
            
            
        }
        
        //1번째 집 턴 경우
        dp2[0]=0;
        dp2[1]=money[1];
        for(int i=2;i<money.length;i++){
            dp2[i]=Math.max(dp2[i-2]+money[i],dp2[i-1]);
            
            
        }
        
        answer=Math.max(dp1[money.length-2],dp2[money.length-1]);
        
        
        return answer;
    }
}
