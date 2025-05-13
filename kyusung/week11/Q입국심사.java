import java.util.*;
class Q입국심사{
        public static long solution(int n, int[] t) {
            long ans =0;

            Arrays.sort(t);

            long left =0;
            long right =(long) t[t.length -1] * n;
            long mid =  (left + right) / 2;
            long now = 0;

            while(left <= right){
                mid =  (left + right) / 2;
                now = 0;
                //mid 시간 당 각 times 해결 가능 인원 수 ?
                for(int i=0; i<t.length; i++){
                    now += mid / t[i];
                }

                if(now < n){
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                    ans = mid;
                }
                //System.out.println("n : " + n);
            }

            return ans;
        }
    }