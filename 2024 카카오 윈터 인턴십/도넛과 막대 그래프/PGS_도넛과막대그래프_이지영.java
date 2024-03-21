public class PGS_도넛과막대그래프_이지영 {

    class Solution {

        final int ROOT = 0, DONUT = 1, STICK = 2, EIGHT = 3;

        int N;
        int[] in, out;

        public int[] solution(int[][] edges) {
            int[] answer = new int[4];

            init(edges);
            answer[ROOT] = findRoot();

            int total = out[answer[ROOT]];

            removeEdgeFromRoot(answer[ROOT], edges);

            answer[STICK] = countStick(answer[ROOT]);
            answer[EIGHT] = countEight();
            answer[DONUT] = total - answer[STICK] - answer[EIGHT];

            return answer;
        }

        void init(int[][] edges) {
            for (int[] edge : edges) {
                N = Math.max(N, edge[0]);
                N = Math.max(N, edge[1]);
            }
            N++;

            in = new int[N];
            out = new int[N];

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];

                out[a]++;
                in[b]++;
            }
        }

        int findRoot() {
            for (int i=1; i<N; i++) {
                if (in[i]==0 && out[i]>=2) {
                    return i;
                }
            }

            return -1;
        }

        void removeEdgeFromRoot(int root, int[][] edges) {
            for (int[] edge : edges) {
                if(edge[0] == root) {
                    out[edge[0]]--;
                    in[edge[1]]--;
                }
            }
        }

        int countStick(int root) {
            int result = 0;

            for (int i=1; i<N; i++) {
                if(i==root) continue;

                if (out[i]==0) {
                    result++;
                }
            }

            return result;
        }

        int countEight() {
            int result = 0;

            for (int i=1; i<N; i++) {
                if (in[i]==2 && out[i]==2) {
                    result++;
                }
            }

            return result;
        }
    }

}
