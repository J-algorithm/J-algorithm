import java.util.*;

class Solution {
    List<Edge>[] edgeList;
    public void init(int n, int[][] costs){
        edgeList = new LinkedList[n];
        for(int i = 0; i < n ; i++){
            edgeList[i] = new LinkedList<>();
        }

        for(int[] cost : costs){
            Edge edge1 = new Edge(cost[0], cost[2]);
            Edge edge2 = new Edge(cost[1], cost[2]);
            edgeList[cost[1]].add(edge1);
            edgeList[cost[0]].add(edge2);
        }
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // MST
        // 다익스트라
        init(n, costs);

        // 0부터 시작
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        int nodeCount = 1;
        while(!q.isEmpty() && nodeCount < n){
            int now = q.poll();
            for(Edge e : edgeList[now]){
                pq.add(e);
            }
            while(!pq.isEmpty()){
                Edge e = pq.poll(); // 현재 방문한 노드에 연결되어 있으며 가장 비용이 적은 edge
                if(visited[e.to]) continue; // 방문한 노드라면 = 이미 최단거리를 찾았다면 넘어간다.
                visited[e.to] = true;
                answer += e.cost;
                q.add(e.to);
                nodeCount++;
                break;
            }
        }

        return answer;
    }
}
class Edge {
    int to;
    int cost;
    public Edge (int to, int cost){
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int ans = s.solution(4, costs);
        System.out.println("ans : " + ans);
    }
}
