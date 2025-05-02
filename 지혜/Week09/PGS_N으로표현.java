import java.io.*;
import java.util.*;
class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        if(N==number) return 1;
        HashSet<Integer>[] set = new HashSet[9];
        for(int i=0; i<9; i++){
            set[i] = new HashSet<>();
        }
        boolean check=false;
        int temp =N;
        ArrayList<Integer>[] templist = new ArrayList[9];
        for(int i=0; i<9; i++){
            templist[i] = new ArrayList<>();
        }
        for(int i =1; i<9; i++){            
            templist[i].add(temp); // 5555...
            set[i].add(temp);
            temp = temp *10 +N;
        }
        
        //5555.. 끼리 계산
        for(int i =1; i<9; i++){
            for(int j =1; j<i; j++){
                int a = templist[j].get(0);
                int b = templist[i-j].get(0);
                // System.out.println("a : " + a + " b: "+b);
                set[i].add(a-b);
                set[i].add(b-a);
                set[i].add(a/b);
                set[i].add(b/a);
                set[i].add(a+b);
                set[i].add(a*b);
                
            }
        }
        
        for(int i =2; i<9; i++){
            for(int e =1; e<i; e++){
                ArrayList<Integer> first = new ArrayList<>(set[e]);//전 꺼
                ArrayList<Integer> second = new ArrayList<>(set[i-e]);//전 꺼
                ArrayList<Integer> cur = new ArrayList<>(); 
                for(int j =0; j<first.size(); j++){
                    for(int k =0; k<second.size(); k++){
                        cur.add(first.get(j) + second.get(k));
                        //빼기,나누기

                        cur.add(first.get(j) - second.get(k));
                        
                        if(first.get(j) !=0){
                            cur.add(second.get(k)/first.get(j));
                        }

                        
                        //곱하기
                        cur.add(first.get(j) * second.get(k));        
                    }            
                }
                for(int j=0; j<cur.size(); j++){

                    set[i].add(cur.get(j));
                }
                
                for(int d : set[i]){
                    if(d == number){
                        check = true;
                        break;
                    }
                }
            
                if(check){
                    break;
                }
            }
            if(check){
                    answer = i;
                    break;
                }
        
        }
        return answer;
    }
}
