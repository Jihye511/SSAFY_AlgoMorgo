import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        HashMap<Integer, String> hash = new HashMap<>();
        int len = phone_book.length;
        for(int i = 0; i < len; i++){
            hash.put(i, phone_book[i]);
        }

        for(int i = 1; i < len; i++){
            if(hash.get(i).startsWith(hash.get(i-1))) {
                return false;
            }
        }
        return true;
    }
}
