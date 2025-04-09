class PGS_모음사전 {
    public int solution(String word) {
        int answer = 0;
        
        char[] ch={'?','A','E','I','O','U'};
        StringBuilder sb=new StringBuilder();
        
        for(int a=1;a<=5;a++){
            sb.append(ch[a]);
            for(int b=0;b<=5;b++){
                if(b!=0) sb.append(ch[b]);
                for(int c=0;c<=5;c++){
                    if(c!=0) sb.append(ch[c]);
                    if(b==0&&c!=0) continue;
                    for(int d=0;d<=5;d++){
                        if(d!=0) sb.append(ch[d]);
                        if(c==0&&d!=0)continue;
                        for(int e=0;e<=5;e++){
                            if(e!=0) sb.append(ch[e]);
                            if(d==0&&e!=0)continue;
                            answer++;
                            // System.out.println(sb);
                            
                                if(word.equals(sb.toString()))
                                    return answer;
                            sb.setLength(4);
                        }
                        sb.setLength(3);
                    }
                    sb.setLength(2);
                }
                sb.setLength(1);
            }
            sb.setLength(0);
        }
        
        return answer;
    }
}

//sb.setLength(sb.length()-1); // hello
