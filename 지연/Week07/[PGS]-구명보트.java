import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();
         Arrays.sort(people);
     for(int i=0;i<people.length;i++){
         list.add(people[i]);
     }  
        int start=0;
        int end=people.length-1;
     int count=list.size();
        while(start<end){
            if(list.get(start)+list.get(end)<=limit){
               // list.set(end,list.get(start)+list.get(end));
               // list.remove(start);
                count--;
               
                end--;
            }
            if(list.get(start)+list.get(end)>limit){
                  end--;
            }
            else{
                start++;
            }
          
            
        }
       answer=count;
      // System.out.println(count);
        return answer;
    }
   
}
