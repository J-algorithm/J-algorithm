import java.util.*;

public class PGS_양과늑대_이지영 {

    class Solution {
        static final int ROOT = 0, SHEEP = 0, WOLF = 1;
        int N, answer = 0;
        boolean[] visited;
        List<Integer>[] graph;

        public int solution(int[] info, int[][] edges) {
            init(info, edges);

            visit(ROOT, 0, 0, info);

            return answer;
        }

        void init(int[] info, int[][] edges) {
            N = info.length;

            visited = new boolean[N];
            graph = new ArrayList[N];
            for (int i=0; i<N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];

                graph[a].add(b);
            }
        }

        void visit(int now, int sheep, int wolf, int[] info) {
            if (visited[now]) return;
            visited[now] = true;

            if(info[now] == SHEEP) sheep++;
            else wolf++;

            if (sheep <= wolf) {
                visited[now] = false;
                return;
            }
            answer = Math.max(answer, sheep);

            for (int i=0; i<N; i++) {
                if(!visited[i]) continue;
                for (int next : graph[i]) {
                    visit(next, sheep, wolf, info);
                }
            }

            visited[now] = false;
        }
    }

}
