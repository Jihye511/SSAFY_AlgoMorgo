import java.util.*;

class Q체육복{
        public static int solution( int n , int[] lost, int[] res) {
            int ans = 0;
            
            int[] arr = new int[n+1];
            Arrays.fill(arr, 1);
            Arrays.sort(lost);
            Arrays.sort(res);
            
            for(int i : lost){
                arr[i] -= 1;
            }
            for(int i : res){
                arr[i] += 1;
            }
            
            for(int i = 0; i<lost.length; i++){
                for(int j = 0; j<res.length; j++){
                    if(Math.abs(lost[i] - res[j]) < 2 ){
                        if(arr[lost[i]] < 1 && arr[res[j]] > 1){
                            arr[lost[i]]++;
                            arr[res[j]]--;
                        }
                    }
                }
            }
            
            for(int i : arr){
                if( i > 0) ans ++; 
            }
            
            return ans - 1 ;
        }
    }