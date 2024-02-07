import java.util.*;

public class PGS_전력망을둘로나누기_이지영 {

    class Solution {
        int N, START = 1;
        int[] cutWire;
        List<Integer>[] graph;
        public int solution(int n, int[][] wires) {

            int answer = n;

            init(n, wires);

            for (int i=0; i<wires.length; i++) {
                cutWire = wires[i];

                int visitedCnt = bfs();
                int unvisitedCnt = n - visitedCnt;

                answer = Math.min(answer, Math.abs(visitedCnt - unvisitedCnt));
            }
            return answer;
        }

        void init(int n, int[][] wires) {
            N = n;
            graph = new ArrayList[N+1];
            for (int i=1; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i=0; i<wires.length; i++) {
                int a = wires[i][0];
                int b = wires[i][1];

                graph[a].add(b);
                graph[b].add(a);
            }
        }

        int bfs() {
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N+1];
            int visitedCnt = 0;

            q.add(START);
            visited[START] = true;

            while(!q.isEmpty()) {
                int now = q.poll();
                visitedCnt++;

                for (int next : graph[now]) {
                    if(visited[next]) continue;
                    if(cutWire[0]==Math.min(now, next) && cutWire[1]==Math.max(now, next)) continue;

                    q.add(next);
                    visited[next] = true;
                }
            }

            return visitedCnt;
        }
    }

}
