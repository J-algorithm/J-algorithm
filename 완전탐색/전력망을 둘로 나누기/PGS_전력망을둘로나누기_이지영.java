import java.util.*;

public class PGS_전력망을둘로나누기_이지영 {

    class Solution {
        int N, START = 1;
        boolean[][] graph;
        public int solution(int n, int[][] wires) {

            int answer = n;

            init(n, wires);

            for (int[] wire : wires) {
                int a = wire[0];
                int b = wire[1];

                graph[a][b] = false;
                graph[b][a] = false;

                int visitedCnt = bfs();
                int unvisitedCnt = n - visitedCnt;

                answer = Math.min(answer, Math.abs(visitedCnt - unvisitedCnt));

                graph[a][b] = true;
                graph[b][a] = true;
            }
            return answer;
        }

        void init(int n, int[][] wires) {
            N = n;
            graph = new boolean[N+1][N+1];

            for (int[] wire : wires) {
                int a = wire[0];
                int b = wire[1];

                graph[a][b] = true;
                graph[b][a] = true;
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

                for (int next=1; next<=N; next++) {
                    if(visited[next] || !graph[now][next]) continue;

                    q.add(next);
                    visited[next] = true;
                }
            }

            return visitedCnt;
        }
    }

}
