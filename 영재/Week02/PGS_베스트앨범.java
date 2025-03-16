import java.util.*;
import java.io.*;

class Solution {
	public static int[] solution(String[] genres, int[] plays) throws Exception {

		
		
		HashMap<String, Integer> genreTotalPlay = new HashMap<>();
		HashMap<String,List<int[]>> genreToSongs=new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			int value = genreTotalPlay.getOrDefault(genres[i], 0);
			genreTotalPlay.put(genres[i], value + plays[i]);
			
			
			genreToSongs.putIfAbsent(genres[i], new ArrayList<>());
			genreToSongs.get(genres[i]).add(new int[] {i,plays[i]});

		}

		Set<String> keys = genreTotalPlay.keySet();
		String[] keyList = keys.toArray(new String[0]);

		Arrays.sort(keyList, (a, b) -> (genreTotalPlay.get(b) - genreTotalPlay.get(a)));

		List<Integer> result=new ArrayList<>();

		for(String str : keyList) {
			
			List<int[]> songs=genreToSongs.get(str);
			
			songs.sort((a,b)->{
				if(a[1]==b[1]) return a[0]-b[0];
				return b[1]-a[1];
			});
			
			result.add(songs.get(0)[0]);
			if(songs.size()>1) {
				result.add(songs.get(1)[0]);
			}
			
		}
		int[] answer= new int[result.size()];
		
		for (int i = 0; i < result.size(); i++) {
			answer[i]=result.get(i);
		}
		
		
		
		return answer;
	}
}
