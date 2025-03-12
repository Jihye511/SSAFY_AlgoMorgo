import java.util.*;
import java.io.*;

public class Solution {
	
	
	public static void main(String[] args) throws Exception{
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		
		HashMap<String, Boolean> hash = new HashMap<>();
		
		for (String str : completion) {
			if(! hash.containsKey(str)) {
				hash.put(str, true);
			}
		}
		for (String str : participant) {
			if(! hash.containsKey(str)) {
				System.out.println(str);
			}
		}
//		return null;
	}
}
