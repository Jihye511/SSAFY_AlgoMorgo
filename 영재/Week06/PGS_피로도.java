

import java.util.Arrays;

class Solution {
	static int result;
	static int num;
    public int solution(int k, int[][] dungeons) {
        num=dungeons.length;
        result=0;
        find(new boolean[num],0,new int[num],dungeons,k);
        return result;
    }
    static void find(boolean v[],int cnt,int res[],int[][]dungeons,int k) {
    	if(cnt==num) {
    		go(res,dungeons,k);
    		return;
    	}
    	
    	for (int i = 0; i < num; i++) {
			if(v[i])
				continue;
			res[cnt]=i;
			v[i]=true;
			find(v,cnt+1,res,dungeons,k);
			v[i]=false;
		}
    }

    static void go(int res[],int [][]dungeons,int k) {
    	int cnt=0;
    	int piro=k;
    	for (int i = 0; i < dungeons.length; i++) {
			int idx=res[i];
			if(piro>=dungeons[idx][0]) {
				piro-=dungeons[idx][1];
				cnt++;
			}else {
				break;
			}
		}
    	if(cnt>result)result=cnt;
    }
}
