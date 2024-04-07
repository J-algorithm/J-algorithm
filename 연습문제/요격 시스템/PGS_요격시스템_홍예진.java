import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 미사일 최소
        // x좌표 : 실수 
        PriorityQueue<Bomber> pq = new PriorityQueue<>();
        pq.add(new Bomber(100000000, 100000001));
        for(int[] target : targets){
            pq.add(new Bomber(target[0], target[1]));
        }
        
        while(pq.size() > 1){
            Bomber b = pq.poll();
            int x = b.end;
            while(pq.peek().start < x)
                pq.poll();
            
            answer++;
        }
        
        
        return answer;
    }
}
class Bomber implements Comparable<Bomber> {
    int start, end;
    public Bomber(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int compareTo(Bomber b){
        return this.end - b.end;
    }
}