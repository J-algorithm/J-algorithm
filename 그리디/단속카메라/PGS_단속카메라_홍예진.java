import java.util.*;

class Solution {
    PriorityQueue<Route> pq;
    public void init(int[][] routes){
        pq = new PriorityQueue<>((r1, r2) -> r1.end - r2.end);
        for(int[] route : routes){
            pq.add(new Route(route[0], route[1]));
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        init(routes);
        while(!pq.isEmpty()){
            Route route = pq.poll();
            answer++;
//            System.out.println("카메라 설치 지점 : "+route.end);
            while(!pq.isEmpty() && pq.peek().start <= route.end){
                pq.poll();
            }
        }
        return answer;
    }
}
class Route {
    int start, end;
    public Route(int start, int end){
        this.start = start;
        this.end = end;
    }
}