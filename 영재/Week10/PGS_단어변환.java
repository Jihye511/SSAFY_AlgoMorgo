class 단어변환 {
    static int answer;
    static boolean visited[];
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        visited=new boolean[words.length];
        dfs(begin,target,0,words,0,false);

        return answer;
    }
    
    public void dfs(String begin, String target,int idx, String[]words,int cnt,boolean chk){
        
        // System.out.println(begin);
        if(begin.equals(target)){
            if(answer==0){
                answer=cnt;
                return;
            }else{
                System.out.println(cnt);
                answer=Math.min(cnt,answer);
                return;
            }
        }
        
        if(idx==words.length){
            if(chk==false)
                return;
            chk=false;
            idx=0;
        }
            

        for(int i=0;i<target.length();i++){
            
            //바꿔야할때
            if(begin.charAt(i)!=words[idx].charAt(i)&&!visited[idx]){
                  
                String tmp=begin.substring(0,i)+words[idx].charAt(i)+begin.substring(i+1);  
                if(!tmp.equals(words[idx])) continue;
                // System.out.println(tmp);
                chk=true;
                visited[idx]=true;
                dfs(tmp,target,idx+1,words,cnt+1,chk);
                chk=false;
                visited[idx]=false;
            }
        }
        dfs(begin,target,idx+1,words,cnt,chk);
    }
}
