import java.util.*;

public class PGS_섬연결하기_이지영 {

    class Edge implements Comparable<Edge> {
        int cost;
        int idx;

        public Edge(int cost, int idx) {
            this.cost = cost;
            this.idx = idx;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    class Solution {
        final int START = 0;

        boolean[] visited;
        List<Edge>[] graph;
        PriorityQueue<Edge> pq;

        public int solution(int n, int[][] costs) {
            int answer = 0;
            init(n, costs);

            visited[START] = true;
            for(Edge e : graph[START]) {
                pq.add(e);
            }

            while(!pq.isEmpty()) {
                Edge e = pq.poll();
                if(visited[e.idx]) continue;

                visited[e.idx] = true;
                answer += e.cost;
                for(Edge edge : graph[e.idx]) {
                    pq.add(edge);
                }
            }

            return answer;
        }

        void init(int n, int[][] costs) {
            visited = new boolean[n];
            graph = new ArrayList[n];
            pq = new PriorityQueue<>();

            for (int i=0; i<n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] arr : costs) {
                int a = arr[0];
                int b = arr[1];
                int cost = arr[2];

                graph[a].add(new Edge(cost, b));
                graph[b].add(new Edge(cost, a));
            }
        }
    }

}
