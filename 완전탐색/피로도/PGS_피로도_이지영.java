public class PGS_피로도_이지영 {

    class Solution {
        int N, answer = 0;
        boolean[] visited;

        public int solution(int k, int[][] dungeons) {

            init(dungeons);

            for (int start=0; start<N; start++) {
                if(dungeons[start][0] > k) continue;

                visited[start] = true;
                visit(1, k-dungeons[start][1], dungeons);
                visited[start] = false;
            }

            return answer;
        }

        void init(int[][] dungeons) {
            N = dungeons.length;
            visited = new boolean[N];
        }

        void visit(int cnt, int hp, int[][] dungeons) {
            answer = Math.max(answer, cnt);

            if(cnt == N) return;

            for (int next=0; next<N; next++) {
                if(visited[next] || dungeons[next][0]>hp) continue;

                visited[next] = true;
                visit(cnt+1, hp-dungeons[next][1], dungeons);
                visited[next] = false;
            }
        }
    }

}
