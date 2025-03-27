import java.util.*;
import java.io.*;
class 디스크컨트롤러 {
    static class Disk implements Comparable<Disk>{
        int jobnum;
        int start;
        int extime;
        public Disk(int jobnum,int start,int extime){
            this.jobnum=jobnum;
            this.start=start;
            this.extime=extime;
        }
        @Override
        public int compareTo(Disk d){
            if(this.extime==d.extime){
                if(this.start==d.start){
                    return this.jobnum-d.jobnum;
                }
                return this.start-d.start;
            }
            return this.extime-d.extime;
        }
    }
    public int solution(int[][] jobs) {
        PriorityQueue<Disk> pq= new PriorityQueue<>();
        List<Disk> list=new ArrayList<>();
        for(int i=0;i<jobs.length;i++){
            list.add(new Disk(i,jobs[i][0],jobs[i][1]));
        }
        list.sort((a,b)->(a.start-b.start));
        int time=0;
        int sum=0;
        int idx=0;
        int cnt=0;
        while(cnt<list.size()){
            //가능한 친구들 다 pq에 넣기
            while(idx<list.size()){
                if(list.get(idx).start<=time){
                    pq.offer(list.get(idx++));
                }else
                    break;
            }
            if(!pq.isEmpty()){
                Disk now=pq.poll();
                cnt++;
                time+=now.extime;
                sum+=(time-now.start);
            }else{
                time++;
            }
        }
        return sum/jobs.length;
        
    }
}
