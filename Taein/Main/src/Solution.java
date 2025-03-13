import java.util.*;
public class Solution {

	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		Arrays.sort(phone_book);
		System.out.println(Arrays.toString(phone_book));
        for(int i = 0; i < phone_book.length; i++){
            for(int j = i+1; j < phone_book.length; j++){
                if(phone_book[j].startsWith(phone_book[i])) {
                    System.out.println(phone_book[j] + " " + phone_book[i]);
                }
            }
        }
        
        
    }
}
