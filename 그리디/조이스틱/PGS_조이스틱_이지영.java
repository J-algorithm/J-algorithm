public class PGS_조이스틱_이지영 {

    class Solution {
        int N, K = 'Z' - 'A' + 1, answer;
        int[] visited;

        public int solution(String name) {
            init(name);
            if (isCompleted(name)) return 0;

            visited[0]++;
            dfs(0, 0, name);

            for (int i=0; i<N; i++) {
                char target = name.charAt(i);
                if (target!='A') answer += countUpDown('A', target);
            }

            return answer;
        }

        void init(String name) {
            N = name.length();
            answer = Integer.MAX_VALUE;
            visited = new int[N];
        }

        void dfs(int now, int sum, String name) {
            if (sum > answer) return;
            if (isCompleted(name)) {
                answer = Math.min(answer, sum);
                return;
            }

            visited[(now+1)%N]++;
            dfs((now+1)%N, sum+1, name);
            visited[(now+1)%N]--;

            visited[(now-1+N)%N]++;
            dfs((now-1+N)%N, sum+1, name);
            visited[(now-1+N)%N]--;
        }

        int countUpDown(char now, char target) {
            int move = Math.abs(now - target);
            return Math.min(move, K - move);
        }

        boolean isCompleted(String name) {
            for (int i=0; i<N; i++) {
                if (visited[i]==0 && name.charAt(i)!='A') return false;
            }

            return true;
        }

    }

}
