import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        //1. 장르별 재생횟수
        HashMap<String, Integer> genrePlayCount = new HashMap<>();
        // 2. 장르별 노래 리스트 (각 노래의 (인덱스, 재생 횟수) 저장)
        HashMap<String, ArrayList<int[]>> genreSongs = new HashMap<>();
        
        //데이터 담기
        for(int i =0; i<genres.length; i++){
            if(genrePlayCount.containsKey(genres[i])){
                genrePlayCount.put(genres[i], genrePlayCount.get(genres[i]) + plays[i]); 
            }else{
                genrePlayCount.put(genres[i],plays[i]); 
            }
             genreSongs.putIfAbsent(genres[i], new ArrayList<>());
            genreSongs.get(genres[i]).add(new int[]{i, plays[i]});
        }
        
        //장르를 재생횟수로 정렬
        ArrayList<String> keySet = new ArrayList<>(genrePlayCount.keySet());
        keySet.sort((o1, o2) -> genrePlayCount.get(o2).compareTo(genrePlayCount.get(o1)));
        
        List<Integer> bestAlbum = new ArrayList<>();
       for(String genre : keySet){
           //재생횟수 , 인덱스 기준으로 정렬
           List<int[]> songs = genreSongs.get(genre);
           songs.sort((a,b) -> b[1] !=  a[1] ? b[1] -a[1] : a[0] -b[0]);
           
           for(int i =0; i<Math.min(songs.size(),2); i++){
               bestAlbum.add(songs.get(i)[0]);
           }
        }
        int[] ans = new int[bestAlbum.size()];
        for(int i =0; i<bestAlbum.size(); i++){
            ans[i] = bestAlbum.get(i);
        }
        return ans;
    }
}
