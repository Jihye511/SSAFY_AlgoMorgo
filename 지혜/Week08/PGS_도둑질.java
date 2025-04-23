import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int size = money.length;
        int[] includeZero = new int[size];
        int[] excludeZero = new int[size];
        includeZero[0] =includeZero[1]= money[0];
        excludeZero[1] = money[1];
        
        for(int i =2; i<size; i++){
            includeZero[i] = Math.max(includeZero[i-2] + money[i], includeZero[i-1]);
            excludeZero[i] = Math.max(excludeZero[i-2]+money[i], excludeZero[i-1]);
        }
        return Math.max(includeZero[size-2], excludeZero[size-1]);
    }
}
